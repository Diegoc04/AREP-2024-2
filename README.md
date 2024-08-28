# Taller 2: Microframeworks WEB
Este taller se centra en la evolución de un servidor web existente para convertirlo en un marco de trabajo web completo y funcional, especializado en la gestión de servicios REST y archivos estáticos. La idea es proporcionar a los desarrolladores las herramientas necesarias para crear aplicaciones web robustas, con la posibilidad de definir rutas REST mediante expresiones lambda, gestionar parámetros de consulta en las solicitudes, y organizar de manera eficiente los recursos estáticos de la aplicación.

![image](https://github.com/user-attachments/assets/9866b785-ab44-4f03-b93b-45c6bf41441f)

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
![image](https://github.com/user-attachments/assets/5ec6d71b-84b9-4b24-919c-006f93ca202c)
2. clonaremos el repositorio usando la siguiente URL: https://github.com/Diegoc04/AREP-2024-2.git, una vez clonado nos moveremos a la carpeta AREP usando el comado cd.

![image](https://github.com/user-attachments/assets/e1f81299-f2bb-4a9a-80a3-5ca462f6e509)

3. Nos moveremos a la rama Taller-2 usando el comando git checkout.
   
![image](https://github.com/user-attachments/assets/ce5ff5eb-1b3b-4139-ac94-98ac22f49464)


# Ejecutar la aplicación
1. Para ejecutar la aplicación Ejecutaremos el siguiente comando: mvn clean compile exec:java '-Dexec.mainClass=org.example.model.SimpleWebServer'
   
![image](https://github.com/user-attachments/assets/191022df-3f39-4c17-b33f-f3529741eba5)

El anterior comando limpiará las contrucciones previas, compilará y empaquetará el código en un jar y luego ejecutará la aplicación.

2. Una vez tengamos el proyecto corriendo iremos a nuestro browser y pondremos la siguiente URL: http://localhost:8080/nintendo.html

![image](https://github.com/user-attachments/assets/b512303a-4eec-48a3-8ff2-e6c573723880)

# Mostrando la funcionalidad.




# Ejecutar las pruebas automaticas

Para ejecutar las pruebas pare la ejecucuión del proyecto y utilice el siguiente comando: mvn test

![image](https://github.com/user-attachments/assets/b4fbec6b-8fdf-4c8a-8409-33ed7e9692c4)

![image](https://github.com/user-attachments/assets/5982b69b-86da-4a4d-9fed-50edf76c81fb)


# Arquitectura del proyecto


# Construido con
[Apache NeatBeans IDE 22](https://netbeans.apache.org/front/main/download/nb22/) - entorno de desarrollo.
   
# Autores
Diego Fernando Castellanos Amaya - [Diegoc04](https://github.com/Diegoc04)

# Agradecimientos
* Al profesor Daniel Benavides por impartir esta clase y tener la pasión de enseñar.

