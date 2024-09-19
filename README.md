# Bono Parcial 1

## Empezando
Estas instrucciones le permitirán obtener una copia del proyecto en funcionamiento en su máquina local para fines de desarrollo y prueba.

## prerrequisitos
* Git - Control de versiones
* Java - Lenguaje de programación

(Es necesario tener instalado Git y Maven para poder ejecutar el proyecto.)

## Instalación 
Para instalar el proyecto en su maquina local realizaremos los siguientes pasos.

1. clone el proyecto con el siguiente comando: git clone https://github.com/Diegoc04/AREP-2024-2.git
2. Muevase a la carpeta Arep-2024-2 con el comando: cd Arep-2024-2/
3. muevase a la rama Taller-4 con el comando: git checkout Bono
4. Lo abrimos usando la IDE de su preferencia



## Ejecutar la aplicación
1. Ejecute el comando: mvn clean
2. Ejecute el comando: mcn package
3. Ejecute el comando: mvn compile
4. Ejecute el comando: java -cp "target/classes;target/dependency/*" co.edu.escuelaing.RestServiceApplication

El anterior comando limpiará las construcciones previas, compilará y luego ejecutará la aplicación.

Una vez tengamos el proyecto corriendo iremos a nuestro browser y pondremos la siguiente URL: http://localhost:35000

## Mostrando la funcionalidad 
