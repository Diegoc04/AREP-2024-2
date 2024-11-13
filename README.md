# TALLER 7: MICROSERVICIOS

## Descripción del Taller

**Objetivo General:** El objetivo de este taller es diseñar, desarrollar y desplegar una aplicación distribuida basada en microservicios que permita a los usuarios realizar publicaciones breves (similar a Twitter), explorando la implementación monolítica inicial y su posterior transición a una arquitectura basada en microservicios. La aplicación deberá incluir capacidades de seguridad utilizando JWT y tecnologías en la nube como AWS Lambda y AWS S3.


### Empezando

Estas instrucciones le permitirán obtener una copia del proyecto en funcionamiento en su máquina local para fines de desarrollo y prueba.

### Requisitos

Para ejecutarlo de manera local necesitamos Docker para facilitar la base de datos, ya se cuenta con los archivos Docker
para los ajustes del contenerdor.

* [Git](https://git-scm.com/) - Control de versiones
* [Maven](https://maven.apache.org/) - Manejador de dependencias
* [Java](https://www.oracle.com/java/technologies/downloads/#java17) - Lenguaje de programación
* [Docker](https://www.docker.com/) - Plataforma de contenedores
* [AWS (Amazon Web Services)](https://aws.amazon.com/es/) - Plataforma de servicios en la nube



### Instalación

Realice los siguientes pasos para clonar el proyecto en su máquina local.

1. Clonar repositorio.

```bash
git clone https://github.com/Diegoc04/AREP-2024-2
```

2. Cambiar de rama.

```bash
git checkout Taller-7
```

### Ejecutando la aplicación

* Construir el proyecto
```bash
mvn clean package
```

* Construir el proyecto
```bash
docker-compose up -d
```
Vaya a la siguiente dirección [Link](http://localhost:8080/) y puede loguear con los siguientes usuarios:

* Username: daniel
* Password: rulos

* Username: diego
* Password: diejoto

### Ejecutando Pruebas

```bash
mvn test
```

### Arquitectura

1. Arquitectura General
   
Backend REST API: Utiliza Java con Quarkus para construir y desplegar servicios RESTful. Cada controlador y servicio se estructura de forma independiente para manejar funcionalidades específicas como gestión de publicaciones y autenticación de usuarios.
Base de Datos NoSQL (MongoDB): Se utiliza MongoDB para almacenar datos de publicaciones y usuarios, configurado para ejecutarse en un contenedor separado (referenciado como mongodatabase).
Autenticación JWT: Implementa autenticación usando tokens JWT (JSON Web Tokens) para asegurar las rutas y restringir el acceso a usuarios autenticados.
Frontend HTML/CSS/JavaScript: Una interfaz de usuario simple para la interacción, con páginas HTML que permiten a los usuarios iniciar sesión, crear publicaciones y ver una lista de publicaciones recientes.

2. Componentes del Backend
   
Controladores (Controllers):
StreamController: Expone endpoints para obtener y crear publicaciones (GET y POST).
TokenSecuredResource: Expone un endpoint de autenticación (/login) donde los usuarios reciben un token JWT si las credenciales son válidas.
UserController: Proporciona acceso a la lista de usuarios (GET).
Servicios (Services):
StreamService: Gestiona las publicaciones, interactuando con MongoDB para insertar y recuperar documentos de la colección stream.
UserService: Proporciona métodos para la autenticación de usuarios, gestión de contraseñas (usando SHA-256 para hashearlas) y acceso a la colección users en MongoDB.
GenerateToken: Encargado de crear tokens JWT, verificando las credenciales de usuario y generando un token seguro para el usuario autenticado.

3. Autenticación y Seguridad
   
El proyecto implementa JWT para autenticar usuarios y proteger recursos sensibles.
El archivo de configuración application.properties establece los detalles para firmar y verificar los tokens, con un requerimiento de emisor (issuer) y claves pública y privada para validar y firmar los tokens JWT.
La ruta /secured/login permite iniciar sesión, y si el usuario es válido, se le devuelve un JWT que puede utilizar para autenticar solicitudes futuras.

4. Persistencia (MongoDB)
   
Las colecciones de MongoDB (stream para publicaciones y users para usuarios) se configuran en un contenedor separado accesible desde la aplicación, permitiendo almacenar y acceder a los datos desde los servicios StreamService y UserService.
MongoDB se accede mediante MongoClient, con métodos específicos para cada operación de CRUD.

5. Frontend
    
HTML/CSS y JavaScript: La interfaz de usuario incluye páginas de login y visualización/creación de publicaciones. Los scripts en app.js se encargan de gestionar eventos de login, logout y envío de publicaciones, que se comunican con la API del backend.

### Video

https://www.youtube.com/watch?v=ydldOQpLMbs&ab_channel=Daniel

### Autores

Daniel Santiago Torres Acosta [https://github.com/RulosS290](https://github.com/RulosS290)

Diego Fernando Castellanos Amaya [https://github.com/Diegoc04/AREP-2024-2](https://github.com/Diegoc04/AREP-2024-2)

## Agradecimientos

Daniel Benavides - Profesor AREP

Santiago Parra - Monitor

