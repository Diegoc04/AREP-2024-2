## Taller de árboles binarios
En este taller se implementa el codigo de un arbol binario en el que se le pueden adicionar, buscar y eliminar nodos, tambien usa metodos transversales para buscar in-order, pre-order y post-order, tambien encuentra el minimo y el maximo del arbol, su altura, revisa si está balanceado, limpia el arbol y cuenta el número de nodos.

## prerrequisitos
* Git - Control de versiones
* Java - Lenguaje de programación

## Instalación
Para instalar el proyecto en su maquina local realice los siguientes pasos.

1. Abra git bash en donde quiera guardar el proyecto.
2. clone el repositorio usando la siguiente URL: https://github.com/Diegoc04/AREP-2024-2.git, una vez clonado muevase a la carpeta AREP-2024-2 usando el comado cd.
3. Muevase a la rama Taller-ArbolBinario usando el comando git checkout.
4. Abra el proyecto en su IDE de preferencia.

## Pruebas
Debido a la situación que no encontrabamos en clase y debido a no poseer maven, hice las pruebas usando los metodos del codigo de la clase BST e imprimiendo los resultados en la consola.

![image](https://github.com/user-attachments/assets/cd34475a-2a38-43d6-a70d-9a47b7e5c86a)

## Arquitecutra del proyecto
Clase Node<T>:
Representa la unidad básica de un árbol binario. Cada nodo tiene un valor o referencias a dos nodos hijos: uno izquierdo y uno derecho.
Esta clase contiene:
Un valor genérico T, que debe implementar Comparable<T> para permitir comparaciones entre los valores.
Referencias a sus hijos izquierdo y derecho.
Clase BST<T>:
Implementa el árbol binario de búsqueda (BST) en sí mismo, donde cada nodo está organizado de manera que, para cualquier nodo, los valores en su subárbol izquierdo son menores, y los del subárbol derecho son mayores.
Esta clase contiene métodos para:
Insertar un nodo (insert).
Buscar un nodo (search).
Eliminar un nodo (delete).
Realizar recorridos (en orden, preorden, y postorden).


# Versiones 
Java: 17.0.10

# Autor
Diego Fernando Castellanos Amaya - [Diegoc04](https://github.com/Diegoc04)

# Agradecimientos
* Al profesor Daniel Benavides por impartir esta clase y tener la pasión de enseñar.
