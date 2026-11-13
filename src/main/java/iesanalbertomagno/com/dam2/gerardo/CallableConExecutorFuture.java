import java.util.concurrent.*;

public class CallableConExecutorFuture {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        try {
            Callable<Integer> tarea = () -> {
                Thread.sleep(1000);
                return 10 + 20;
            };
            Future<Integer> futuro = executor.submit(tarea);
            System.out.println("Resultado: " + futuro.get());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            System.out.println("Error: " + e.getCause());
        } finally {
            executor.shutdown();
        }
    }
}
