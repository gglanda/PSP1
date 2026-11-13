import java.util.concurrent.*;

public class ThreadPoolExecutorCola {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
            2, 4, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2)
        );
        try {
            for (int i = 1; i <= 6; i++) {
                final int numero = i;
                executor.execute(() -> {
                    System.out.println("Ejecutando tarea " + numero
                        + " en " + Thread.currentThread().getName());
                    try { Thread.sleep(1500); }
                    catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            }
        } catch (RejectedExecutionException e) {
            System.out.println("Tarea rechazada: " + e.getMessage());
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
