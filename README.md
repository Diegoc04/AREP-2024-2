# TALLER 5: TALLER DE DE MODULARIZACIÓN CON VIRTUALIZACIÓN E INTRODUCCIÓN A DOCKER

En este taller se profundizan los conceptos de modulación por medio de virtualización usando Docker. La aplicación se compone de un servicio, este es un servicio que permite agregar un dato a una base de datos mongoDB y permite visualizar los ultimos 10 datos creados junto a su fecha.

![image](https://github.com/user-attachments/assets/03ff7f75-71c9-4b74-9f68-3d62ce502d3c)

![image](https://github.com/user-attachments/assets/26df0a22-1f56-4d97-bec5-de5af8f8d27a)


## Empezando
Estas instrucciones le permitirán obtener una copia del proyecto en funcionamiento en su máquina local para fines de desarrollo y prueba.

## prerrequisitos
* Git - Control de versiones
* Maven - Gestor de dependencias
* Java - Lenguaje de programación
* Docker - Motor de contenedores

(Es necesario tener instalado Git y Maven para poder ejecutar el proyecto.)

## Instalación 
Para instalar el proyecto en su maquina local realizaremos los siguientes pasos.

1. clone el proyecto con el siguiente comando: git clone https://github.com/Diegoc04/AREP-2024-2.git
2. Muevase a la carpeta Arep-2024-2 con el comando: cd Arep-2024-2/
3. muevase a la rama Taller-4 con el comando: git checkout Taller-4

## Ejecutar la aplicación
1. Ejecute el comando: mvn clean
2. Ejecute el comando: mcn package
3. Ejecute el comando: mvn compile
4. Ejecute el comando: java -cp "target/classes;target/dependency/*" co.edu.escuelaing.RestServiceApplication

El anterior comando limpiará las construcciones previas, compilará y luego ejecutará la aplicación.

Una vez tengamos el proyecto corriendo iremos a nuestro browser y pondremos la siguiente URL: http://localhost:35000

## Mostrando la funcionalidad 

![image](https://github.com/user-attachments/assets/c72e741d-33dc-4c15-a2d0-dc3f8de03af5)

![image](https://github.com/user-attachments/assets/b7aa1291-d6d7-4686-a768-b1b53daef9fe)

![image](https://github.com/user-attachments/assets/4fb226b9-49f2-4914-bfa0-00b727a079dd)

![image](https://github.com/user-attachments/assets/569936bd-923e-4d65-8213-9fe0282754fe)



##Arquitectura del proyecto  
Los módulos de la aplicación son los siguientes:

1. Componentes Principales:

LoadBalancerService: Este servicio se encarga de realizar el balanceo de carga entre múltiples instancias del servicio de logs. Mantiene una lista de URLs de los servicios de logs y devuelve la URL del próximo servicio en la lista de manera cíclica.

LogServiceController: Este controlador REST recibe solicitudes para registrar mensajes (/log) y para obtener mensajes (/messages). Utiliza el MessageService para guardar y recuperar mensajes.

Message: Es una clase de documento que representa la estructura de los mensajes almacenados en MongoDB. Incluye campos como id, content y date.

MessageService: Este servicio maneja la lógica de negocio relacionada con los mensajes. Permite guardar mensajes en MongoDB y recuperar los últimos 10 mensajes ordenados por fecha.

MessageRepository: Aunque no está explícitamente mostrado en el código, se asume que esta interfaz extiende de MongoRepository o una interfaz similar, que proporciona métodos para interactuar con la base de datos MongoDB.

RestServiceApplication: La clase principal de la aplicación Spring Boot que arranca el servidor en el puerto especificado por la variable de entorno PORT o en el puerto 35000 por defecto.

2. Flujo de Datos:

Balanceo de Carga:

Cuando se hace una solicitud para registrar un mensaje, el LoadBalancerService selecciona una URL del servicio de logs y redirige la solicitud a uno de los servicios de logs disponibles.
Registro de Mensajes:

El LogServiceController recibe una solicitud POST en el endpoint /log con el contenido del mensaje en el cuerpo de la solicitud.
El LogServiceController llama al MessageService para guardar el mensaje.
El MessageService crea un nuevo objeto Message, establece su contenido y la fecha actual, y guarda el mensaje en la base de datos MongoDB utilizando MessageRepository.
Recuperación de Mensajes:

El LogServiceController puede recibir solicitudes GET en el endpoint /messages para obtener una lista de los últimos mensajes.
El MessageService consulta la base de datos para obtener los últimos 10 mensajes, ordenados por fecha descendente, y devuelve estos mensajes al cliente.
3. Base de Datos:

MongoDB: La aplicación utiliza MongoDB como su sistema de base de datos NoSQL. La colección tasks almacena documentos de tipo Message, que contienen los campos id, content, y date.


## Como generar las imagenes
![image](https://github.com/user-attachments/assets/9533e124-8367-48aa-8f9d-779dae042eab)
![image](https://github.com/user-attachments/assets/7ef45352-85b2-45c6-a444-2dab0b9fc91f)
![image](https://github.com/user-attachments/assets/a3d0cb81-fd6b-4ad5-91a7-248eda5971b4)
![image](https://github.com/user-attachments/assets/53e1090b-a252-4239-8491-ee4b6e0a8c02)


## Video con los despliegues

[Video](https://youtu.be/7xSXo_kp254)

## Ejecutar las pruebas automaticas

Para ejecutar las pruebas pare la ejecucuión del proyecto y utilice el siguiente comando: mvn test

![image](https://github.com/user-attachments/assets/c9dc9b8e-e4f3-431f-b0f0-69b793c5fd9e)


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
