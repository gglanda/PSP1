import java.util.concurrent.*;

public class FutureMetodos {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        try {
            Future<String> futuro = executor.submit(() -> {
                Thread.sleep(1500);
                return "Tarea completada";
            });
            System.out.println("¿Ha terminado? " + futuro.isDone());
            System.out.println("Resultado: " + futuro.get());
            System.out.println("¿Ha terminado? " + futuro.isDone());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            System.out.println("Error: " + e.getCause());
        } finally {
            executor.shutdown();
        }
    }
}
