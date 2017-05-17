package com.bytetrend.sandbox.java.kafka;


import java.util.Properties;
import java.util.Random;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import org.apache.kafka.clients.producer.RecordMetadata;

public class MultiBrokerProducer {
    private static Producer<String, String> producer;
    private final Properties props = new Properties();

    public MultiBrokerProducer() {
        props.put("metadata.broker.list", "localhost:9092, localhost:9093 ");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("partitioner.class", "com.bytetrend.sandbox.java.kafka.SimplePartitioner");
        props.put("request.required.acks", "1");
        ProducerConfig config = new ProducerConfig(props);
        producer = new Producer<String, String>(config);
    }

    public static void main(String[] args) {
        MultiBrokerProducer sp = new MultiBrokerProducer();
        Random rnd = new Random();
        String topic = (String) args[0];
        for (long messCount = 0; messCount < 10; messCount++) {
            Integer key = rnd.nextInt(255);
            String msg = "This message is for key - " + key;
            KeyedMessage<String, String> data1 = new
                    KeyedMessage<String, String>(topic, key.toString(), msg);
             producer.send(data1);
        }
        producer.close();
    }
}
