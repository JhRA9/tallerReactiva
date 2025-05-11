/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejemplo.ejercicio18;

/**
 *
 * @author PC-JHON
 */

import org.apache.kafka.clients.producer.*;
import java.util.Properties;

public class ProducerA {
    public static void main(String[] args) throws InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
        for (int i = 1; i <= 10; i++) {
            String msg = "A-msg-" + i;
            producer.send(new ProducerRecord<>("eventos", Integer.toString(i), msg));
            System.out.println("ProducerA envió: " + msg);
            Thread.sleep(500);
        }
        producer.close();
    }
}
