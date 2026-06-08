# Taller Sincronización - Patrón de sincronización por barrera.

En este laboratorio se da un programa que se crea N hilos que realizan una misma tarea a una velocidad diferente,
y al final promedia el tiempo de ejecución de todos lo hilos. 

## Problema inical

En la primera versión del programa, al ejecutarlo, se crean 20 hilos, estos empiezan con sus tareas y van reportándose en diferente orden según su velocidad.
Sin embargo, antes de que se muestren los mensajes de los hilos en ejecución, se muestra primero el mensaje: "El tiempo promedio de ejecución fue de: 0". Esto es
incorrecto, ya que el mensaje se debería mostrar al final de todo el proceso después de que todos los hilos hayan terminado, y el resultado, naturalmente debe ser mayor a 0.

Esto indica que no se está controlando correctamente la ejecución del cálculo del tiempo total, ya que no hay nada que "pause" la ejecución
mientras el resto de hilos terminen su tarea.

![1](images/img1.png)


## Solución

Para solucionar el problema, se usó la estrategia de sincronización por barrera. Para esto, se crea una instancia de la clase CyclicBarrier
de Java con el número de hilos que se van a crear más uno, para contar el hilo del proceso main. Luego se le pasa este objeto en el constructor a todos
los hilos, que lo guardan en un atributo. Luego, dentro del método run de los hilos, al final, después de calcular el tiempo total de ejecución, se hace un llamado a la 
barrera con el metodo await. Dentro del método main también se llama el metodo await de la barrera desdpués de llamar la función star de todos los hilos.

De esta manera, cuando cada hilo termina su ejecución, informa a la barrera, y cuando los 20 hilos terminan, y la funcion main también llama a la 
funcion await, se termina de ejecutar el resto del codigo dentro de los objetos. En el caso de los hilos de procesos, no tienen nada que hacer entonces mueren, y en caso del hilo main,
continua con el cálculo del promedio de tiempo de ejecución.

![2](images/img2.png)
![3](images/img3.png)
![4](images/img4.png)