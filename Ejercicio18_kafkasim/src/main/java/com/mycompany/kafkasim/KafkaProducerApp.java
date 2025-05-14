package com.mycompany.kafkasim;

import org.apache.kafka.clients.producer.*;
import java.util.Properties;

public class KafkaProducerApp implements Runnable {
    private final String topic;
    private final String producerId;

    public KafkaProducerApp(String topic, String producerId) {
        this.topic = topic;
        this.producerId = producerId;
    }

    @Override
    public void run() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        for (int i = 0; i < 10; i++) {
            String value = "Mensaje " + i + " de " + producerId;
            ProducerRecord<String, String> record = new ProducerRecord<>(topic, value);
            producer.send(record, (metadata, exception) -> {
                if (exception == null) {
                    System.out.printf("Producer %s envio: %s%n", producerId, value);
                } else {
                    exception.printStackTrace();
                }
            });
            try { Thread.sleep(500); } catch (InterruptedException ignored) {}
        }

        producer.close();
    }
}