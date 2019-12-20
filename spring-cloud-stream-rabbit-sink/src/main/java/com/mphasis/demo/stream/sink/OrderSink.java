package com.mphasis.demo.stream.sink;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBinding(Sink.class)
@Configuration
public class OrderSink {
    private static final Logger logger = LoggerFactory.getLogger(OrderSink.class);
    
    @Autowired
    RabbitTemplate rabbitTemplate;
    
    String consumerQueue= "mphasis-consumer-queue";
    
    @Bean
    public RabbitTemplate rabbitTemplate() throws Exception {
    	return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        return connectionFactory;
    }
    
    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }
    
    @Bean
    public Queue myQueue() {
        return new Queue(consumerQueue);
    }
    
	/*
	 * @Bean(name="rabbitListenerContainerFactory") public
	 * SimpleRabbitListenerContainerFactory
	 * rabbitListenerContainerlistenerFactory(){
	 * SimpleRabbitListenerContainerFactory factory = new
	 * SimpleRabbitListenerContainerFactory();
	 * factory.setConnectionFactory(connectionFactory()); return factory; }
	 */

	@StreamListener(target = Sink.INPUT)
    public void listenForOrder(Order order) {
	  if(order == null) return;
      logger.info(" received new order ["+order.toString()+"] ");
      try {
    	  rabbitTemplate.convertAndSend(consumerQueue,order.toString());
          logger.info(" sending new order ["+order.toString()+"] to consumer:"+consumerQueue);
      }catch (Exception e) {
    	  e.printStackTrace(System.out);
      }
    }
    

}
