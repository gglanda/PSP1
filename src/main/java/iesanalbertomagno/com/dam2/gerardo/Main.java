package iesanalbertomagno.com.dam2.gerardo;

import java.util.Scanner;
import iesanalbertomagno.com.dam2.gerardo.Main;



public class Main {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        int opcion;

        do {
            // Mostrar el menú
            System.out.println("\n==============================");
            System.out.println("       MENU PRINCIPAL");
            System.out.println("==============================");
            System.out.println("1. Crear un Thread");
            System.out.println("2. Ejecutar una tarea Runnable");
            System.out.println("3. Ejecutar una tarea Callable");
            System.out.println("4. Utilizar un ThreadPoolExecutor");
            System.out.println("5. Salir");
            System.out.println("==============================");
            System.out.print("Selecciona una opcion: ");

            // Leer la opción elegida
            opcion = teclado.nextInt();

            // Ejecutar la opción correspondiente
            switch (opcion) {

                case 1:
                    System.out.println("\nHas seleccionado Thread.");
                    // Aqui puedes llamar al ejemplo 01
                    break;

                case 2:
                    System.out.println("\nHas seleccionado Runnable.");
                    Main main =new Main();
                    main..RunnableEjemplo();
                    break;

                case 3:
                    System.out.println("\nHas seleccionado Callable.");
                    // Aqui puedes llamar al ejemplo 03
                    break;

                case 4:
                    System.out.println("\nHas seleccionado ThreadPoolExecutor.");
                    // Aqui puedes llamar al ejemplo 04
                    break;

                case 5:
                    System.out.println("\nCerrando el programa...");
                    break;

                default:
                    System.out.println("\nOpcion no valida. Intentalo de nuevo.");
                    break;
            }

        } while (opcion != 5);

        teclado.close();
    }
}
