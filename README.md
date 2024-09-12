#TALLER 5: TALLER DE DE MODULARIZACIÓN CON VIRTUALIZACIÓN E INTRODUCCIÓN A DOCKER

En este taller se profundizan los conceptos de modulación por medio de virtualización usando Docker. La aplicación se compone de un servicio, este es un servicio que permite agregar un dato a una base de datos mongoDB y permite visualizar los ultimos 10 datos creados junto a su fecha.

![image](https://github.com/user-attachments/assets/03ff7f75-71c9-4b74-9f68-3d62ce502d3c)

![image](https://github.com/user-attachments/assets/26df0a22-1f56-4d97-bec5-de5af8f8d27a)


##Empezando
Estas instrucciones le permitirán obtener una copia del proyecto en funcionamiento en su máquina local para fines de desarrollo y prueba.

prerrequisitos
* Git - Control de versiones
* Maven - Gestor de dependencias
* Java - Lenguaje de programación
* Docker - Motor de contenedores

(Es necesario tener instalado Git y Maven para poder ejecutar el proyecto.)

##Instalación 
Para instalar el proyecto en su maquina local realizaremos los siguientes pasos.

1. clone el proyecto con el siguiente comando: git clone https://github.com/Diegoc04/AREP-2024-2.git
2. Muevase a la carpeta Arep-2024-2 con el comando: cd Arep-2024-2/
3. muevase a la rama Taller-4 con el comando: git checkout Taller-4

Ejecutar la aplicación
1. Ejecute el comando: mvn clean
2. Ejecute el comando: mcn package
3. Ejecute el comando: mvn compile
4. Ejecute el comando: java -cp "target/classes;target/dependency/*" co.edu.escuelaing.RestServiceApplication

El anterior comando limpiará las construcciones previas, compilará y luego ejecutará la aplicación.

Una vez tengamos el proyecto corriendo iremos a nuestro browser y pondremos la siguiente URL: http://localhost:35000

##Mostrando la funcionalidad -----

##Arquitectura del proyecto  -----
Los módulos de la aplicación son los siguientes:


##Como generar las imagenes
![image](https://github.com/user-attachments/assets/9533e124-8367-48aa-8f9d-779dae042eab)
![image](https://github.com/user-attachments/assets/7ef45352-85b2-45c6-a444-2dab0b9fc91f)
![image](https://github.com/user-attachments/assets/a3d0cb81-fd6b-4ad5-91a7-248eda5971b4)
![image](https://github.com/user-attachments/assets/53e1090b-a252-4239-8491-ee4b6e0a8c02)


##Video con los despliegues

[Video](https://youtu.be/7xSXo_kp254)

## Construido con
[Apache NeatBeans IDE 22](https://netbeans.apache.org/front/main/download/nb22/) - entorno de desarrollo.

[maven](https://maven.apache.org/) - Herramienta de automatización de proyectos.

[java](https://www.java.com/es/) - Lenguaje de programación.

[Docker](https://www.docker.com/) - plataforma de software para aplicaciones en contenedores.

## Versiones 
maven: 3.9.9

Java: 17.0.10

   
## Autores
Diego Fernando Castellanos Amaya - [Diegoc04](https://github.com/Diegoc04)

## Agradecimientos
* Al profesor Daniel Benavides por impartir esta clase y tener la pasión de enseñar.
