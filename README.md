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
![image](https://github.com/user-attachments/assets/5ec6d71b-84b9-4b24-919c-006f93ca202c)
2. clonaremos el repositorio usando la siguiente URL: https://github.com/Diegoc04/AREP-2024-2.git, una vez clonado nos moveremos a la carpeta AREP usando el comado cd.

![image](https://github.com/user-attachments/assets/431c338b-7351-4811-9c06-1c78d647da87)

3. Nos moveremos a la rama Taller-2 usando el comando git checkout.
   
![image](https://github.com/user-attachments/assets/ce5ff5eb-1b3b-4139-ac94-98ac22f49464)


# Ejecutar la aplicación
1. Para ejecutar la aplicación Ejecutaremos el siguiente comando: mvn clean compile exec:java '-Dexec.mainClass=org.example.model.SimpleWebServer'
   
![image](https://github.com/user-attachments/assets/191022df-3f39-4c17-b33f-f3529741eba5)

El anterior comando limpiará las contrucciones previas, compilará y empaquetará el código en un jar y luego ejecutará la aplicación.

2. Una vez tengamos el proyecto corriendo iremos a nuestro browser y pondremos la siguiente URL: http://localhost:8080/nintendo.html

![image](https://github.com/user-attachments/assets/b512303a-4eec-48a3-8ff2-e6c573723880)

# Mostrando la funcionalidad.
1. El primer requerimiento de este taller era hacer un metodo GET estatico para servicios REST.
   si nos movemos al codigo del serivor (SimpleWebServer.java) y vamos a la linea 23 veremos el metodo pedido.

![image](https://github.com/user-attachments/assets/9bd4b731-8fff-4d61-87d8-6741055c5d4f)

Para revisar si el metodo funciona pondremos la siguiente URL en el broswer: http://localhost:8080/hi

![image](https://github.com/user-attachments/assets/bafac5b7-284a-4c1d-987c-63afebf4f722)

2. El segundo requerimiento era hacer un mecanismo de extracción de valor de consulta.
   si nos movemos al codigo del serivor (SimpleWebServer.java) y vamos a la linea 25 veremos el metodo pedido.

   ![image](https://github.com/user-attachments/assets/d7e57c6c-0de3-45bd-8755-00040f89b0bd)

   Para revisar si el metodo funciona pondremos la siguiente URL en el broswer: http://localhost:8080/hello?name=Nombre
   el parametro Nombre se puede cambiar por el nombre de cada usuario.

   ![image](https://github.com/user-attachments/assets/55c70057-15d1-4147-a3f1-ddf406e57c28)

3. El tercer requerimiento era la especificación de la locación de los archivos estaticos mediante un metodo staticfiles()
   si nos movemos al codigo del serivor (SimpleWebServer.java) y vamos a la linea 21 veremos el metodo pedido.
   
   ![image](https://github.com/user-attachments/assets/9c067582-d896-4387-b82c-58c0193785e7)

   si cambiamos la ruta usando uso del metodo, el broswer será incapaz de encontrar los archivos solicitados.

   ![image](https://github.com/user-attachments/assets/b347887b-4086-478c-b8ee-6529cd29d4c0)

   en este ejemplo cambiamos la ruta y quitamos webroot, al guardar el cambio y correr el proyecto nos daremos cuenta que no encuentra los archivos.

   ![image](https://github.com/user-attachments/assets/58a1b8b1-6566-4dc1-a9ac-a0cde87a2b5a)


5. El codigo tambien responde a solicitudes de archivos estaticos como se muestra a continuación.
   
   ![image](https://github.com/user-attachments/assets/3fd388ee-6aa7-4d5e-9461-d694bc1169a5)

   ![image](https://github.com/user-attachments/assets/239bce88-8bfe-4bc6-a961-47138af233ca)


# Ejecutar las pruebas automaticas

Para ejecutar las pruebas pare la ejecucuión del proyecto y utilice el siguiente comando: mvn test

![image](https://github.com/user-attachments/assets/b4fbec6b-8fdf-4c8a-8409-33ed7e9692c4)

![image](https://github.com/user-attachments/assets/5982b69b-86da-4a4d-9fed-50edf76c81fb)


# Arquitectura del proyecto
La arquitectura del proyecto para esta segunda entrega se divide en 2, el funcionamiento de los metodos GET y el funcionamiento del metodo staticfiles()

1. Los metodos GET establecen la ruta por la que van a funcionar y reciben los parametros req (request) y rep (response) y devuelven información, se urliza un hashmap para poder mapear los servicios.

   ![image](https://github.com/user-attachments/assets/04d987ae-4500-48f0-9d61-e30fb31307ca)

   ![image](https://github.com/user-attachments/assets/41e70b50-9c96-4b42-9031-56e7aced3326)

   ![image](https://github.com/user-attachments/assets/f5fa4afa-7131-401b-a129-54c572e53432)

   Se utliza la interface Service la cual permite obtenr los valores de request y response, en la imagen se ve que req es de tipo request mientras que res es de tipo string, esto se debe a que para el metodo de extraccion de valores necesitamos obtener el request por      lo que con response no hacemos nada y lo dejamos como string.

   ![image](https://github.com/user-attachments/assets/4b90bbbc-8ba7-442e-aa49-0705ca50cefd)

   Para la extracción de valores dijimos que necesitamos hacer que req sean de tipo request, por lo que creamos la clase.

   ![image](https://github.com/user-attachments/assets/79cb448a-1029-42d8-9cd1-524da5e82392)

   la clase analiza una línea de solicitud HTTP y extrae la ruta del recurso y los parámetros de consulta. 
   
2. El metodo recibe un parametro el cual es un String y lo que hace es asignarle a la variable webRoot el valor de "target/" mas el argumento que recibe el metodo.
   
   ![image](https://github.com/user-attachments/assets/fdd13e3e-c769-443a-833f-34ed55c39fb4)

   variable webRoot donde se guarda la dirección de los archivos.

   ![image](https://github.com/user-attachments/assets/4df589fb-fc26-4100-afbc-a8f9283e4129)

   Uso del metodo:

   ![image](https://github.com/user-attachments/assets/4d4f0219-ef0f-45f7-b5f2-4e8a68a0cc45)

   Para este taller se dejo la misma ruta que el taller pasado para no tener que mover los archivos de dirección.


# Construido con
[Apache NeatBeans IDE 22](https://netbeans.apache.org/front/main/download/nb22/) - entorno de desarrollo.
   
# Autores
Diego Fernando Castellanos Amaya - [Diegoc04](https://github.com/Diegoc04)

# Agradecimientos
* Al profesor Daniel Benavides por impartir esta clase y tener la pasión de enseñar.
