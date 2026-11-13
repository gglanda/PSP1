package iesanalbertomagno.com.dam2.gerardo;
import java.util.concurrent.*;

public class Ej09_ThreadPoolExecutorCola {
    public static void main(String[] args) {
        // 2 hilos fijos, hasta 4 en total y una cola con 2 huecos:
        // caben 6 tareas a la vez (4 ejecutandose + 2 en cola).
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
            2, 4, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2)
        );
        try {
            // Se envian 8 tareas: las dos ultimas no caben y se rechazan.
            for (int i = 1; i <= 8; i++) {
                final int numero = i;
                try {
                    executor.execute(() -> {
                        System.out.println("Ejecutando tarea " + numero
                            + " en " + Thread.currentThread().getName());
                        try { Thread.sleep(1500); }
                        catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    });
                } catch (RejectedExecutionException e) {
                    // Se captura dentro del bucle para seguir con las demas.
                    System.out.println("Tarea " + numero
                        + " rechazada: pool y cola llenos");
                }
            }
        } finally {
            executor.shutdown();
            try {
                if (!executor.awaitTermination(30, TimeUnit.SECONDS))
                    executor.shutdownNow();
            } catch (InterruptedException e) {
                executor.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }
}
