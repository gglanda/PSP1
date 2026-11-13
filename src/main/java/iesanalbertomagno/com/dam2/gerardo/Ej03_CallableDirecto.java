package iesanalbertomagno.com.dam2.gerardo;

import java.util.concurrent.Callable;

public class Ej03_CallableDirecto {
    public static void main(String[] args) throws Exception {
        Callable<Integer> tarea = () -> 10 + 20;
        // call() directamente no crea un hilo nuevo.
        System.out.println("Resultado: " + tarea.call());
    }
}
