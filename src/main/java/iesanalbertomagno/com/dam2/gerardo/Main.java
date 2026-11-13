package iesanalbertomagno.com.dam2.gerardo;



import java.util.Scanner;

/**
 * Menu que lanza cualquiera de los ejemplos de la carpeta.
 * Compilar:  javac -encoding UTF-8 *.java
 * Ejecutar:  java Main
 */
public class Main {

    /** Un ejemplo es cualquier metodo main (puede lanzar excepciones). */
    private interface Ejemplo {
        void ejecutar(String[] args) throws Exception;
    }

    private static final String[] TITULOS = {
        "Crear e iniciar un Thread",
        "Tarea Runnable sin resultado",
        "Callable llamando a call() directamente",
        "Callable con submit() y Future.get()",
        "ThreadPoolExecutor: execute() frente a submit()",
        "Future: get() e isDone()",
        "Esperar varios hilos con join()",
        "shutdown() y awaitTermination()",
        "ThreadPoolExecutor con cola limitada y rechazo",
        "Ejemplo completo: pedidos"
    };

    private static final Ejemplo[] EJEMPLOS = {
        Ej01_ThreadBasico::main,
        Ej02_RunnableEjemplo::main,
        Ej03_CallableDirecto::main,
        Ej04_CallableConExecutorFuture::main,
        Ej05_ThreadPoolExecuteSubmit::main,
        Ej06_FutureMetodos::main,
        Ej07_JoinVariosHilos::main,
        Ej08_ShutdownAwaitTermination::main,
        Ej09_ThreadPoolExecutorCola::main,
        Ej10_EjemploCompletoPedidos::main
    };

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        while (true) {
            mostrarMenu();

            if (!teclado.hasNextLine()) {      // se cerro la entrada
                break;
            }
            String linea = teclado.nextLine().trim();

            int opcion;
            try {
                opcion = Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.println("\nEscribe un numero del 0 al " + EJEMPLOS.length + ".");
                continue;
            }

            if (opcion == 0) {
                break;
            }
            if (opcion < 1 || opcion > EJEMPLOS.length) {
                System.out.println("\nOpcion no valida. Intentalo de nuevo.");
                continue;
            }

            System.out.println("\n--- Ejemplo " + opcion + ": " + TITULOS[opcion - 1] + " ---");
            ejecutarYEsperar(EJEMPLOS[opcion - 1]);
            System.out.println("--- Fin del ejemplo " + opcion + " ---");
        }

        System.out.println("\nCerrando el programa...");
        teclado.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n==============================");
        System.out.println("       MENU PRINCIPAL");
        System.out.println("==============================");
        for (int i = 0; i < TITULOS.length; i++) {
            System.out.printf("%2d. %s%n", i + 1, TITULOS[i]);
        }
        System.out.println(" 0. Salir");
        System.out.println("==============================");
        System.out.print("Selecciona una opcion: ");
    }

    /**
     * Ejecuta un ejemplo y no vuelve al menu hasta que han terminado
     * todos los hilos que ese ejemplo haya creado. Asi la salida de
     * los hilos no se mezcla con el menu.
     */
    private static void ejecutarYEsperar(Ejemplo ejemplo) {
        // Los hilos creados por el ejemplo heredan este grupo.
        ThreadGroup grupo = new ThreadGroup("ejemplo");

        Thread lanzador = new Thread(grupo, () -> {
            try {
                ejemplo.ejecutar(new String[0]);
            } catch (Exception e) {
                System.out.println("El ejemplo ha fallado: " + e);
            }
        });
        lanzador.start();

        try {
            lanzador.join();
            // Esperar a los hilos que el ejemplo dejo trabajando.
            Thread[] hilos = new Thread[grupo.activeCount() + 10];
            int cuantos = grupo.enumerate(hilos);
            for (int i = 0; i < cuantos; i++) {
                hilos[i].join();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
