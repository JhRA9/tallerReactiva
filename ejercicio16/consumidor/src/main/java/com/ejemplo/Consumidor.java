package com.ejemplo;

import com.rabbitmq.client.*;

public class Consumidor {
    private final static String QUEUE_NAME = "colaEventos";

    public static void main(String[] args) throws Exception {
        // Aqui configuro la conexion con el servidor RabbitMQ
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");       // Conecto al servidor RabbitMQ en localhost
        factory.setPort(5672);              // Uso el puerto predeterminado de RabbitMQ
        factory.setUsername("guest");       // Usuario predeterminado
        factory.setPassword("guest");       // Contraseña predeterminada

        // Abro una conexion y creo un canal para comunicarme con RabbitMQ
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // Declaro la cola que voy a consumir. Si no existe, se creara automaticamente
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println("Estoy listo para recibir mensajes de la cola '" + QUEUE_NAME + "'...");

        // Aqui defino como voy a manejar los mensajes que reciba de la cola
        DeliverCallback callback = (consumerTag, delivery) -> {
            String mensaje = new String(delivery.getBody(), "UTF-8");
            System.out.println("Mensaje recibido: " + mensaje);
        };

        // Inicio el consumo de mensajes desde la cola. Los mensajes se procesaran automaticamente
        channel.basicConsume(QUEUE_NAME, true, callback, consumerTag -> {
            System.out.println("El consumo ha sido cancelado");
        });
    }
}