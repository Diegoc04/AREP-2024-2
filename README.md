# Taller 1: Diseño y estructuración de aplicaciones distribuidas en internet
Este taller explora la arquitectura de las aplicaciones distribuidas; en este taller se crea un servidor web que lee los archivos del disco local y retornar todos los archivos solicitados, incluyendo páginas html, archivos java script, css e imágenes. Tambien incluye en la comunicación asíncrona con unos servicios REST en el backend. NO se usan frameworks web como Spark o Spring para manejo de la red entre el servidor y el browser.

![image](https://github.com/user-attachments/assets/0bb08982-3fd2-4ab0-a9a3-ca48b70112ad)

# Empezando
Estas instrucciones le permitirán obtener una copia del proyecto en funcionamiento en su máquina local para fines de desarrollo y prueba. 

# prerrequisitos
* Git - Sistema de control de versiones distribuido.
* Apache NeatBeans - Entorno de desarrollo integrado (IDE).
* Browser - Aplicación de software para navegar y visualizar sitios web en Internet.

(Es necesario tener instalado Git y apache NeatBeans para poder ejecutar el proyecto.)

# Instalación
Para instalar el proyecto en su maquina local realizaremos los siguientes pasos.
1. Abriremos git bash en donde quiera guardar el proyecto.
![image](https://github.com/user-attachments/assets/88a41f67-60cc-4fa5-a909-3346c2a61ef5)
2. clonaremos el repositorio usando la siguiente URL: https://github.com/Diegoc04/AREP-2024-2.git, una vez clonado nos moveremos a la carpeta AREP usando el comado cd.

![image](https://github.com/user-attachments/assets/8dcde7c3-bbb8-4878-a12d-3abe75633ec4)

3. Nos moveremos a la rama Taller-1 usando el comando git checkout.
   
![image](https://github.com/user-attachments/assets/9864e4b8-5106-4fc6-8913-e5786716a215)

4. Al seguir estos pasos nos daremos cuenta que tendremos acceso a la carpeta llamada Taller1
![image](https://github.com/user-attachments/assets/4a4f495d-9b18-4441-bc88-608283b6d0e0)

#Ejecutar la aplicación
1. Para ejecutar la aplicación abriremos Apache NeatBeans y le daremos a abrir proyecto y buscaremos la carpeta Taller1 y abriremos el proyecto.
   
![image](https://github.com/user-attachments/assets/28534300-93c3-4253-be4f-c07d559658da)
![image](https://github.com/user-attachments/assets/214165f8-cafe-4cbe-8709-89d0fb36479d)
![image](https://github.com/user-attachments/assets/e42f38ac-f2c6-4b45-b566-5a1dcfb9894f)

2. Una vez tengamos el proyecto abierto en NeatBeans clickeamos el la felcha para que corra el proyecto.

![image](https://github.com/user-attachments/assets/bbc28a93-2729-4339-8acf-1c96bea922be)

3. Una vez el proyecto arranque en NeatBeans iremos a nuestro browser y pondermos la siguiente URL: http://localhost:8080/

![image](https://github.com/user-attachments/assets/c3a998ac-c541-4536-847c-d1eb4f132964)

# Ejecutando las pruebas
Primero solicitaremos varios archivos que se encuentran alojados en el disco local, estos archivos son:
* texto.txt
* png.png
* jpg.jpg
* jpeg.jpeg
* prueba.html
* javaScript.js
* css1.css
* css2.css
* css3.css

Si solicitamos el .txt veremos que el servidor trae el archivo usango Get y lo trae como texto plano.

![image](https://github.com/user-attachments/assets/80909720-b3b2-4279-abdd-d6a99a2b8f93)
![image](https://github.com/user-attachments/assets/a2e953e1-59c4-415a-898d-921491df405b)

Para el caso de las imagenes, nos devuelve la imagen convertida a base 64 para que el browser la pueda interpretar y pueda mostrar en pantalla.

![image](https://github.com/user-attachments/assets/da7114a7-ebe8-4f9c-962d-7cd9b35e79c9)
![image](https://github.com/user-attachments/assets/9d17de8a-9dac-43db-acec-50903cc0b26f)

En el caso del .html la pagina retorna la pagina abriendola en una nueva pestaña.

![image](https://github.com/user-attachments/assets/27c2b17d-605d-45d3-bcf3-214f606d3b7e)

Para el caso del .js el servidor retorna el archivo y la pagina es capaz de interpretar el tipo de archivo y muestra las alertas que se encuentran en el archivo.

![image](https://github.com/user-attachments/assets/5f575e34-8ce8-425e-a35d-ebdca94fc95c)
![image](https://github.com/user-attachments/assets/ec3293c2-cf8d-4794-8575-3e615bc18c40)
![image](https://github.com/user-attachments/assets/e37b551a-2f62-4462-b211-400bfe586bbd)

Pra el caso de los .css el servidor devuelve las hojas de estilo y la pagina es capaz de interpretarlas y aplicarlas a su propio estilo.

![image](https://github.com/user-attachments/assets/8d4145d3-1ec7-4411-9882-93c8ee0e8d5a)
![image](https://github.com/user-attachments/assets/1fa07333-6f50-4dee-82c8-772a8429c2d6)

Por ultimo, si tratamos de traer un archivo el cual no se encuentra alojado, la pagina retornara un error de tipo 404 File Not Found.

![image](https://github.com/user-attachments/assets/0373894b-16f9-483b-9b85-37352de99164)
![image](https://github.com/user-attachments/assets/55a36eb9-18cd-4504-8f81-a18d312c48b1)

Ahora, revisaremos el servicio REST en su forma GET y Post

1.  
![image](https://github.com/user-attachments/assets/98953509-0543-4c22-b025-62b4a8b46483)
![image](https://github.com/user-attachments/assets/fdd588b3-c538-45b5-8940-bcce40e0197f)


Cuando se realiza la solicitud GET a la ruta /hello, el servidor extrae el parámetro name de la URL y construye una respuesta en texto plano que dice "Hello, [name]!" y envía una respuesta HTTP con el código de estado 200 OK y el contenido en texto plano al cliente.

2.

  ![image](https://github.com/user-attachments/assets/d9ea6d39-9eba-4f55-b7e6-6383911a9464)
  ![image](https://github.com/user-attachments/assets/484eb91d-df1b-46c2-bb78-68816d18f43b)
   
Funcionamiento:
Cuando se realiza la solicitud POST a la ruta /hellopost, el servidor lee el cuerpo de la solicitud para obtener los datos enviados. Luego construye una respuesta en texto plano que dice "POST recibido: [name]", y envía una respuesta HTTP con el código de estado 200 OK y el contenido en texto plano al cliente.

# Construido con
[Apache NeatBeans IDE 22](https://netbeans.apache.org/front/main/download/nb22/) - entorno de desarrollo.
   
# Autores
Diego Fernando Castellanos Amaya - [Diegoc04](https://github.com/Diegoc04)

# Agradecimientos
*
* Las imagenes fuerón sacadas del las siguientes paginas:
  1. [png](https://www.flaticon.es/icono-gratis/simbolo-de-formato-de-archivo-png_29072) 
  2. [jpg]([https://www.flaticon.es/icono-gratis/simbolo-de-formato-de-archivo-png_29072](https://www.ensalza.com/blog/diccionario/que-es-jpg/)) 
  3. [jpeg]([[https://www.flaticon.es/icono-gratis/simbolo-de-formato-de-archivo-png_29072](https://www.ensalza.com/blog/diccionario/que-es-jpg/)](https://www.shutterstock.com/es/image-vector/modern-flat-design-jpeg-file-icon-1953084310)) 














