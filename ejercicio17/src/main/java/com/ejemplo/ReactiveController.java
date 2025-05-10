package com.ejemplo;

import io.reactivex.rxjava3.core.Flowable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import reactor.adapter.rxjava.RxJava3Adapter;
import reactor.core.publisher.Flux;

@RestController
public class ReactiveController {

    // Este endpoint devuelve un flujo de datos (Flux) que adapto desde un Flowable de RxJava
    @GetMapping("/rx-numeros")
    public Flux<Long> numeros() {
        // Aqui creo un flujo de RxJava que emite un numero cada segundo
        Flowable<Long> rxFlow = Flowable.interval(1000, java.util.concurrent.TimeUnit.MILLISECONDS)
                                         .take(5); // Limito el flujo para que solo emita 5 valores: 0, 1, 2, 3, 4

        // Luego convierto este Flowable de RxJava a un Flux de Reactor para que sea compatible con WebFlux
        return RxJava3Adapter.flowableToFlux(rxFlow);
    }

    // Este endpoint devuelve un flujo de datos (Flux) que genero directamente con Reactor
    @GetMapping("/reactor-numeros")
    public Flux<Long> reactorNumeros() {
        // Aqui creo un flujo de Reactor que emite un numero cada segundo y lo limito a 5 valores
        return Flux.interval(Duration.ofSeconds(1))
                   .take(5);
    }
}