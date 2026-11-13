package iesanalbertomagno.com.dam2.gerardo;

import java.util.concurrent.*;

public class Ej10_EjemploCompletoPedidos {
    public static void main(String[] args) {
        System.out.println("INICIO DEL PROGRAMA");

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
            2, 3, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>()
        );

        Thread hiloEstado = new Thread(() -> {
            System.out.println("Comprobando estado del sistema...");
            try { Thread.sleep(2000); }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            System.out.println("Sistema operativo");
        });

        Runnable registrarPedido = () -> {
            System.out.println("Registrando pedido...");
            try { Thread.sleep(3000); }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            System.out.println("Pedido registrado");
        };

        Callable<Double> calcularPedido1 = () -> {
            System.out.println("Calculando pedido 1...");
            Thread.sleep(4000);
            return 150.50;
        };

        Callable<Double> calcularPedido2 = () -> {
            System.out.println("Calculando pedido 2...");
            Thread.sleep(2000);
            return 250.75;
        };

        hiloEstado.start();
        executor.execute(registrarPedido);
        Future<Double> resultado1 = executor.submit(calcularPedido1);
        Future<Double> resultado2 = executor.submit(calcularPedido2);

        try {
            System.out.println("Esperando resultados...");
            Double pedido1 = resultado1.get();
            Double pedido2 = resultado2.get();
            System.out.println("Importe pedido 1: " + pedido1);
            System.out.println("Importe pedido 2: " + pedido2);
            System.out.println("Total: " + (pedido1 + pedido2));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            executor.shutdownNow();
            return;
        } catch (ExecutionException e) {
            System.out.println("Error en una tarea: " + e.getCause());
        }

        try {
            hiloEstado.join();
            System.out.println("El hilo de estado ha terminado");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            executor.shutdownNow();
            return;
        }

        executor.shutdown();
        try {
            boolean terminado = executor.awaitTermination(30, TimeUnit.SECONDS);
            if (terminado) {
                System.out.println("Todos los trabajadores han terminado");
            } else {
                System.out.println("Se agotó el tiempo de espera");
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
            return;
        }

        System.out.println("PROGRAMA FINALIZADO");
    }
}
