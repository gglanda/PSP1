
package iesanalbertomagno.com.dam2.gerardo;
public class RunnableEjemplo {
    public static void runnableEjemplo(String[] args) {
        Runnable tarea = () ->
            System.out.println("Ejecutando una tarea sin resultado");
        Thread hilo = new Thread(tarea);
        hilo.start();
    }
}
