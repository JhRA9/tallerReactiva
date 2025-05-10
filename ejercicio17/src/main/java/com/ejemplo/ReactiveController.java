package com.ejemplo;

import io.reactivex.rxjava3.core.Flowable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import reactor.adapter.rxjava.RxJava3Adapter;
import reactor.core.publisher.Flux;

@RestController
public class ReactiveController {

    // 1) Endpoint que devuelve un Flux (Reactor) convertido desde un Flowable (RxJava)
    @GetMapping("/rx-numeros")
    public Flux<Long> numeros() {
        // Crea un flujo RxJava que emite un número cada segundo
        Flowable<Long> rxFlow = Flowable.interval(1000, java.util.concurrent.TimeUnit.MILLISECONDS)
                                         .take(5); // Emite 5 valores: 0,1,2,3,4

        // Adapta Flowable → Flux para que WebFlux lo entienda
        return RxJava3Adapter.flowableToFlux(rxFlow);
    }

    // 2) Otro ejemplo puro Reactor sin RxJava, solo para comparar
    @GetMapping("/reactor-numeros")
    public Flux<Long> reactorNumeros() {
        return Flux.interval(Duration.ofSeconds(1))
                   .take(5);
    }
}
