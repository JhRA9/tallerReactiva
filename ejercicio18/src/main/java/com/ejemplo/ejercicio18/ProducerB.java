package com.ejemplo.ejercicio18;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.util.Properties;

public class ProducerB {
    public static void main(String[] args) throws InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
        for (int i = 1; i <= 10; i++) {
            String msg = "B-msg-" + i;
            producer.send(new ProducerRecord<>("eventos", Integer.toString(i), msg));
            System.out.println("ProducerB envió: " + msg);
            Thread.sleep(700);
        }
        producer.close();
    }
}