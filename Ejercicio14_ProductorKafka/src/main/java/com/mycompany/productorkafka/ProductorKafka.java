package com.mycompany.productorkafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.util.Properties;

public class ProductorKafka {

    public static void main(String[] args) {
        // Configuramos las propiedades básicas del productor
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092"); // Dirección del broker de Kafka
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // Creamos el productor Kafka
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        // Enviamos 5 mensajes al tópico 'eventos'
        for (int i = 1; i <= 5; i++) {
            String mensaje = "Mensaje número " + i;
            producer.send(new ProducerRecord<>("eventos", mensaje));
            System.out.println("Enviado: " + mensaje);
        }

        // Cerramos el productor para liberar recursos
        producer.close();
    }
}
