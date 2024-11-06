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

## Autores

Daniel Santiago Torres Acosta [https://github.com/RulosS290](https://github.com/RulosS290)
Diego Fernando Castellanos Amaya [https://github.com/Diegoc04/AREP-2024-2](https://github.com/Diegoc04/AREP-2024-2)

## Agradecimientos

Daniel Benavides - Profesor AREP

Santiago Parra - Monitor

