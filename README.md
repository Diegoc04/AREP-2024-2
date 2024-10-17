# Taller 6: Enterprise Architecture Workshop: Security Application Design
En este taller, diseñaremos e implementaremos una aplicación segura y escalable utilizando la infraestructura de AWS con un enfoque en las mejores prácticas de seguridad.

![image](https://github.com/user-attachments/assets/d545ff75-39fb-41cc-b88a-e975b38fb17a)

## prerrequisitos
* AWS  - Plataforma de servicios en la nube
* Git - Control de versiones
* Java - Lenguaje de programación
* Maven - Manejador de dependencias

## Instalación
Para instalar el proyecto en su maquina local realizaremos los siguientes pasos.

1. Abriremos 2 git bash en donde quiera guardar el proyecto.
2. clonaremos el repositorio usando la siguiente URL: https://github.com/Diegoc04/AREP-2024-2.git, una vez clonado nos moveremos a la carpeta AREP-2024-2 usando el comado cd.
3. Nos moveremos a la rama Taller-5 usando el comando git checkout.
4. nos moveremos a las carpetas BlackJackRoyale_Back y BlackJackRoyale_FRONT usando el comado cd.
5. Corremos el back y el front:
   ![image](https://github.com/user-attachments/assets/b1f0a16c-5ed4-40ce-9248-5b373ddf9df8)
   ![image](https://github.com/user-attachments/assets/bddd4ad9-d04a-46f9-a92d-2c0afa338a81)
   ![image](https://github.com/user-attachments/assets/9daed5d1-7498-4333-9dc9-88b91394a96a)
6. Una vez ambos estén corriendo podemos ir a la url: http://localhost:3000/

## Pruebas
Para ejecutar las pruebas utilizamos el comando: mvn test (BlackJackRoyale)
![image](https://github.com/user-attachments/assets/df562eda-8d54-4d48-802f-c48104c6551e)

## Arquitecutra del proyecto
1. Frontend (React)
   
Framework: React

Puerto: localhost:3000

Componentes: El frontend manejará la interfaz de usuario para el juego de BlackJack y las interacciones de login y registro de usuarios. Utiliza HTTP requests para comunicarse con el backend a través de rutas y API REST.

2. Backend (Spring Boot)
   
Framework: Spring Boot 3.3.3

Puerto: Usualmente corre en el puerto 8080, aunque no lo has especificado en la configuración que has mostrado.

Controlador (UserController):

Maneja las solicitudes de registro (/register) y de inicio de sesión (/login) a través de las anotaciones @PostMapping.

Las solicitudes para obtener todos los usuarios están mapeadas en la ruta /blackjack/Users usando @GetMapping.

Usa @CrossOrigin para permitir que el frontend (en localhost:3000) interactúe con el backend sin restricciones de CORS.

Servicio (UserService):

Implementa la lógica de negocio para la gestión de usuarios: creación, obtención y validación de usuarios.

Se conecta con el repositorio para realizar operaciones en la base de datos.

Repositorio (UserRepository):

Extiende MongoRepository, lo que permite realizar operaciones CRUD en la colección Taller6 de MongoDB.

También define una consulta personalizada para buscar usuarios por su email.

3. Base de Datos (MongoDB)
   
Colección: Taller6

Entidad (User):

Documentos que representan usuarios con los siguientes campos: id (generado automáticamente), email (índice único), name y password.

4. Comunicación Frontend-Backend
   
El frontend realiza solicitudes HTTP al backend para registro y login de usuarios.

Utiliza JSON como formato de comunicación entre frontend y backend para enviar y recibir datos.

5. Arquitectura General
   
Cliente (React en localhost:3000): Envia peticiones al servidor Spring Boot.

Servidor (Spring Boot en localhost:8080): Recibe las peticiones, procesa la lógica de negocio en el servicio UserService y se comunica con MongoDB a través del repositorio UserRepository.

Base de Datos (MongoDB en MongoDB Atlas o una instancia local): Almacena los usuarios y maneja las operaciones CRUD relacionadas.


## Video presentación 
El siguiente enlace contiene el video en el que se muestra la funcionalidad de la aplicación.
[Link](https://www.youtube.com/watch?v=fmX1lC0wjOI)

# Versiones 
maven: 3.9.9

Java: 17.0.10

# Autor
Diego Fernando Castellanos Amaya - [Diegoc04](https://github.com/Diegoc04)

# Agradecimientos
* Al profesor Daniel Benavides por impartir esta clase y tener la pasión de enseñar.
