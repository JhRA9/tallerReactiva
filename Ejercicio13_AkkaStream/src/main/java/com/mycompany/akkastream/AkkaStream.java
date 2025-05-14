package com.mycompany.akkastream;

import akka.actor.ActorSystem;
import akka.stream.Materializer;
import akka.stream.SystemMaterializer;
import akka.stream.javadsl.Source;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class AkkaStream{

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("AkkaStreamSystem");

        // Usar SystemMaterializer en lugar de ActorMaterializer
        Materializer materializer = SystemMaterializer.get(system).materializer();

        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5);

        Map<Integer, String> textoNumeros = Map.of(
                1, "uno",
                2, "dos",
                3, "tres",
                4, "cuatro",
                5, "cinco"
        );

        Source.from(numeros)
                .map(numero -> textoNumeros.getOrDefault(numero, "desconocido"))
                .runForeach(System.out::println, materializer)
                .thenRun(system::terminate);
    }
}