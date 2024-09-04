# Taller 3: Taller de Arquitecturas de Servidores de Aplicaciones, Meta protocolos de objetos, Patrón IoC, Reflexión

Este taller explora la creación de un prototipo mínimo que demuestra capacidades reflexivas de JAVA y permite cargar un bean (POJO) y derivar una aplicación Web a partir de él similar a la de Spring.

# Empezando
Estas instrucciones le permitirán obtener una copia del proyecto en funcionamiento en su máquina local para fines de desarrollo y prueba. 

# prerrequisitos
* Git - Sistema de control de versiones distribuido.
* Maven - Manejador de dependencias
* Browser - Aplicación de software para navegar y visualizar sitios web en Internet.

(Es necesario tener instalado Git y Maven para poder ejecutar el proyecto.)

# Instalación
Para instalar el proyecto en su maquina local realizaremos los siguientes pasos.
1. Abriremos git bash en donde quiera guardar el proyecto.
   
![image](https://github.com/user-attachments/assets/c0ba6ddd-137b-4be4-8c63-e24c9c847e51)

3. clonaremos el repositorio usando la siguiente URL: https://github.com/Diegoc04/AREP-2024-2.git, una vez clonado nos moveremos a la carpeta AREP usando el comado cd.

![image](https://github.com/user-attachments/assets/7782aa31-7797-43ae-b757-7c7d4d8b6cec)

3. Nos moveremos a la rama Taller-3 usando el comando git checkout.
   
![image](https://github.com/user-attachments/assets/8fc85ce2-6d3a-406b-8c5a-10a4bb4cce69)

# Ejecutar la aplicación
1. Para ejecutar la aplicación Ejecutaremos el siguiente comando:  java -cp target/classes/ com.mycompany.springeci.SpringECI com.mycompany.springeci.HelloService
   
![image](https://github.com/user-attachments/assets/6fc130da-7252-4efc-891f-8abe94b2b3c5)

El anterior comando inicia la aplicación Java compiladas y ubicadas en un directorio de salida (target/classes/).

2. Una vez tengamos el proyecto corriendo iremos a nuestro browser y pondremos la siguiente URL: http://localhost:8080

![image](https://github.com/user-attachments/assets/af2b421e-57f5-4cef-ac9c-ec0e4cb8f942)


# Mostrando la funcionalidad.

# Ejecutar las pruebas automaticas

Para ejecutar las pruebas pare la ejecucuión del proyecto y utilice el siguiente comando: mvn test

![image](https://github.com/user-attachments/assets/75aa3f8b-7909-4e7a-b361-5bdb1d6fb98f)

![image](https://github.com/user-attachments/assets/1f90a9a7-5afa-44c8-8e01-35f2b1bbc108)

# Arquitectura del proyecto


# Construido con
[Apache NeatBeans IDE 22](https://netbeans.apache.org/front/main/download/nb22/) - entorno de desarrollo.

[maven](https://maven.apache.org/) - Herramienta de automatización de proyectos

[java](https://www.java.com/es/) - Lenguaje de programación

# Versiones 
maven: 3.9.9
Java: 17.0.10
   
# Autores
Diego Fernando Castellanos Amaya - [Diegoc04](https://github.com/Diegoc04)

# Agradecimientos
* Al profesor Daniel Benavides por impartir esta clase y tener la pasión de enseñar.
