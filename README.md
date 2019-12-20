# Spring-cloud-stream

This is RabbitMQ Source and RabbitMQ Sink stream application..
publish message to orders-exchange -->Source  --> outbound channel (orders-exchange.orders-queue) --> Sink listens to orders-exchange.orders-queue and outbound the message to consumer mphasis-consumer-queue
Additionally you could have processor or also called as transformer sitting between Source and sink to apply additional business transformation.. you can have n number of transformers. Each Source, transformer, Sink can be tapped to get copy of message for any purpose such as auditing  or history or to start new spring cloud stream flow itself.

############################################################################################
