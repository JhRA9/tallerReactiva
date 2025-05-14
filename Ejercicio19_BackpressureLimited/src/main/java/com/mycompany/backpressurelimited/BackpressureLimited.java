package com.mycompany.backpressurelimited;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class BackpressureLimited {

    public static void main(String[] args) 
    {
        Flowable.range(1, 50)
                .onBackpressureBuffer
                (
                        5, () -> System.out.println("El Buffer se desbordo"),
                        io.reactivx.rxjava3.core.BackpressureOverflowStrategy.ERROR //Lanza una excepción cuando se desborda
                )
                .observeOn(Schedulers.io())
                .subscribe
                (
                    item ->
                    {
                        Thread.sleep(200); //Procesamiento de 2 segundos
                        System.out.println("Procesando: "+ item);
                    },
                    error -> System.err.println("El error es: "+ error),
                    () -> System.out.println("Se ha completado")
                );
        
        Thread.sleep(6000); //Tiempo de la duración de ejecución
    }
}
