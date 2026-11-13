package iesanalbertomagno.com.dam2.gerardo;

public class Ej01_ThreadBasico {
    public static void main(String[] args) {
        Thread hilo = new Thread(() -> {
            System.out.println("Trabajo dentro del hilo");
            try { Thread.sleep(2000); }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            System.out.println("Hilo terminado");
        });
        hilo.start();
        System.out.println("El hilo se ha iniciado");
    }
}
