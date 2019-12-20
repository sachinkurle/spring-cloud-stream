# Spring-cloud-stream


RabbitMQ Source

The "rabbit" source enables receiving messages from RabbitMQ.

The queue(s) must exist before the stream is deployed; they are not created automatically.
You can easily create a Queue using the RabbitMQ web UI.

RabbitMQ can be installed from - https://www.rabbitmq.com/download.html

To publish message to source-

POST http://localhost:8091/orders/publish
Content-Type : application/json
{
"order_name":"new order",
"amount": 22222,
"status": "new"
}

Source publishes  the message to orders-exchange.orders-queue

If Sink is off use Rabbit MQ dashboard to read message
source queue - http://localhost:15672/api/queues/%2f/orders-exchange.orders-queue/get

------------------------------------------------------------------------------------


RabbitMQ Sink

The "rabbit" Sink sinks receiving messages from RabbitMQ.

Receives message from Source on queue : orders-exchange.orders-queue
and then send to consumer queue : mphasis-consumer-queue

From Rabbit dashboard you can read the queue -
http://localhost:15672/api/queues/%2f/mphasis-consumer-queue/get
