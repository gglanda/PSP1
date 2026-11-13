import java.util.concurrent.*;

public class ThreadPoolExecuteSubmit {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
            2, 2, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>()
        );
        try {
            executor.execute(() ->
                System.out.println("Runnable ejecutado con execute()")
            );
            Future<Integer> futuro = executor.submit(() -> 10 + 20);
            System.out.println("Resultado Callable: " + futuro.get());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            System.out.println("Error: " + e.getCause());
        } finally {
            executor.shutdown();
        }
    }
}
