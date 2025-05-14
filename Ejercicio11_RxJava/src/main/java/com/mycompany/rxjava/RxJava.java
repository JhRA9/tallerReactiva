package com.mycompany.rxjava;

import io.reactivex.rxjava3.core.Observable;

public class RxJava 
{
    public static void main(String[] args) 
    {
        Observable.range(1,100)
                .filter (num -> num%3 == 0)
                .subscribe
                (
                        num -> System.out.println("Multiplo de 3: "+ num),
                        Throwable::printStackTrace,
                        () -> System.out.println("Secuencia Completa")
                );
    }
}
