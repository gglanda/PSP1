
package iesanalbertomagno.com.dam2.gerardo;

import java.util.concurrent.*;

public class Ej08_ShutdownAwaitTermination {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        for (int i = 1; i <= 3; i++) {
            final int numero = i;
            executor.execute(() -> {
                System.out.println("Iniciando tarea " + numero);
                try { Thread.sleep(2000); }
                catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
                System.out.println("Finalizando tarea " + numero);
            });
        }

        executor.shutdown();
        try {
            boolean terminado = executor.awaitTermination(30, TimeUnit.SECONDS);
            if (terminado) {
                System.out.println("Todas las tareas han terminado");
            } else {
                System.out.println("Se agotó el tiempo de espera");
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
