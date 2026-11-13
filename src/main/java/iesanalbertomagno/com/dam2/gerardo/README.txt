EJEMPLOS DE PROGRAMACION CONCURRENTE EN JAVA

01_ThreadBasico.java: crear e iniciar un Thread.
02_Runnable.java: tarea Runnable sin resultado.
03_CallableDirecto.java: llamar a call() directamente (no crea otro hilo).
04_CallableConExecutorFuture.java: Callable con submit() y Future.get().
05_ThreadPoolExecuteSubmit.java: diferencias entre execute() y submit().
06_FutureMetodos.java: uso de get() e isDone().
07_JoinVariosHilos.java: esperar varios hilos con join().
08_ShutdownAwaitTermination.java: cerrar un executor y esperar su finalizacion.
09_ThreadPoolExecutorCola.java: ThreadPoolExecutor con cola limitada y rechazo.
10_EjemploCompletoPedidos.java: ejemplo integrado de los conceptos principales.

COMPILAR Y EJECUTAR
Desde la terminal, dentro de esta carpeta:
    javac 01_ThreadBasico.java
    java ThreadBasico

Repite el proceso con cada archivo. Se recomienda Java 8 o superior.

NOTAS
- El orden de los mensajes de distintos hilos puede variar.
- En los ejemplos se gestiona InterruptedException restaurando la interrupcion.
- El ejemplo 09 usa una cola limitada para ilustrar el rechazo de tareas.
