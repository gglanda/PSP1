package iesanalbertomagno.com.dam2.gerardo;

public class Ej07_JoinVariosHilos {
    private static Thread crearHilo(String nombre, long espera) {
        return new Thread(() -> {
            System.out.println(nombre + " comienza");
            try { Thread.sleep(espera); }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            System.out.println(nombre + " termina");
        });
    }

    public static void main(String[] args) {
        Thread hilo1 = crearHilo("Hilo 1", 1500);
        Thread hilo2 = crearHilo("Hilo 2", 2500);
        Thread hilo3 = crearHilo("Hilo 3", 1000);
        hilo1.start();
        hilo2.start();
        hilo3.start();

        try {
            hilo1.join();
            hilo2.join();
            hilo3.join();
            System.out.println("Los tres hilos han terminado");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
