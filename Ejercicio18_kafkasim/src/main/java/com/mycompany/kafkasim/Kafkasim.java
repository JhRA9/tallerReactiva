/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.kafkasim;

/**
 *
 * @author casas
 */
public class Kafkasim {

    public static void main(String[] args) {
        String topic = "eventos";

        // Lanzar dos productores
        new Thread(new KafkaProducerApp(topic, "Productor-A")).start();
        new Thread(new KafkaProducerApp(topic, "Productor-B")).start();

        // Lanzar dos consumidores
        new Thread(new KafkaConsumerApp(topic, "Grupo-1")).start();
        new Thread(new KafkaConsumerApp(topic, "Grupo-2")).start();
    }
}
