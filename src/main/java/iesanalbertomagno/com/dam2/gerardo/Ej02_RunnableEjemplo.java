package iesanalbertomagno.com.dam2.gerardo;

public class Ej02_RunnableEjemplo {
    public static void main(String[] args) {
        Runnable tarea = () ->
            System.out.println("Ejecutando una tarea sin resultado");
        Thread hilo = new Thread(tarea);
        hilo.start();
    }
}
