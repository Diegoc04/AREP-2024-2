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
Se retornarán los servicios que se estipularón en HelloService usando el browser.

Servicios: 

![image](https://github.com/user-attachments/assets/51938f6c-05c3-4ee6-a478-baa08465046b)

1. http://localhost:8080/App/hello

![image](https://github.com/user-attachments/assets/d7cc1264-4515-448d-a8e0-c8a4f0433f9b)

![image](https://github.com/user-attachments/assets/ec4eaa68-dc9d-4707-8527-df4b6f0d8825)

2. http://localhost:8080/App/pi
   
![image](https://github.com/user-attachments/assets/70fd245b-69a7-4879-b276-4415233774fb)

![image](https://github.com/user-attachments/assets/4ef0c89b-6396-4390-8973-174824b8b082)

3. http://localhost:8080/App/luz
   
![image](https://github.com/user-attachments/assets/b32273e5-8729-4ddf-a725-887d15b99673)

![image](https://github.com/user-attachments/assets/bd4457e5-e187-4ec7-9215-3c6d947f93c7)

4. http://localhost:8080/App/bye
   
![image](https://github.com/user-attachments/assets/4b65573f-9ceb-44bb-99f1-ced169a4c01b)

![image](https://github.com/user-attachments/assets/9f9a4aa1-ac3f-4130-94ad-3175f45aa167)

5. http://localhost:8080/App/planck
   
![image](https://github.com/user-attachments/assets/6f06819d-5b08-43cf-8f1d-6b30732777b7)

![image](https://github.com/user-attachments/assets/502bc8ec-b4aa-4ac3-b5e5-cfc7c49777f1)

5. http://localhost:8080/App/greeting?name=Diego
   
![image](https://github.com/user-attachments/assets/cb8e61a4-0f78-4adb-9132-a3f5187e4edc)

![image](https://github.com/user-attachments/assets/85de493a-c72f-44bf-b90f-b984656cecfb)

Devolución de archivos estaticos

1. http://localhost:8080/App/pagina
   
![image](https://github.com/user-attachments/assets/f84aec68-cab7-4dfd-aae5-659e4e9b35e9)

![image](https://github.com/user-attachments/assets/60cbd9bc-deb9-422a-8f60-54adf76f0e4c)

2. http://localhost:8080/App/imagen

# Ejecutar las pruebas automaticas

Para ejecutar las pruebas pare la ejecucuión del proyecto y utilice el siguiente comando: mvn test

![image](https://github.com/user-attachments/assets/75aa3f8b-7909-4e7a-b361-5bdb1d6fb98f)

![image](https://github.com/user-attachments/assets/1f90a9a7-5afa-44c8-8e01-35f2b1bbc108)

# Arquitectura del proyecto
Estructura de Paquetes
   
com.mycompany.springeci: Este es el paquete principal que contiene todas las clases necesarias para el servidor web y sus controladores.

Clases Principales
   
SpringECI: Es la clase principal del servidor web.


Responsabilidades:

* Inicializar los servicios REST anotados.
* Escuchar y aceptar conexiones de clientes.
* Procesar solicitudes HTTP y devolver respuestas.
* Manejar archivos estáticos como HTML e imágenes.
  
Métodos clave:

* init(String controllerClassName): Inicializa el servidor cargando los métodos anotados en un controlador.

* startServer(int port): Inicia el servidor y escucha en un puerto específico.

* handleClient(Socket clientSocket): Maneja una solicitud de un cliente, identificando el servicio adecuado y generando la respuesta.

* prepareMethodArguments(Method method, Map<String, String> queryParams): Prepara los argumentos para los métodos anotados, extrayendo los parámetros de la consulta.

HelloService: Este es un controlador que define varios servicios REST.

Responsabilidades:

* Proveer respuestas a las solicitudes HTTP en función de las rutas definidas.
* Manejar diferentes tipos de respuestas, como texto o archivos binarios (por ejemplo, imágenes).
  

Manejo de Archivos Estáticos

* El servidor tiene la capacidad de devolver archivos estáticos como HTML e imágenes.

* Los archivos están ubicados en un directorio webroot dentro de target/classes.

Flujo de Ejecución

1. El servidor es iniciado por la clase SpringECI con el comando main.

2. init() carga el controlador y sus métodos anotados en un mapa (services).

3. startServer() inicia el servidor y espera conexiones en el puerto 8080.

4. handleClient() procesa las solicitudes, identifica el servicio correspondiente, invoca el método asociado y envía la respuesta al cliente.

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
