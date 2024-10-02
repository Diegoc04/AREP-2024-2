# Taller 5: Trabajo individual en patrones arquitecturales
Este taller explora patrones arquitecturales en Amazon Web Services (AWS) y su implementación en aplicaciones web. La aplicación web es un servicio CRUD de una base de datos sql sobre clientes la cual se despliega en dos instancias EC2 de AWS.

![image](https://github.com/user-attachments/assets/bdc6d74e-abe6-4fce-bd42-bed7fb1627c9)


## prerrequisitos
* AWS  - Plataforma de servicios en la nube
* Git - Control de versiones
* Java - Lenguaje de programación
* Maven - Manejador de dependencias

## Instalación
Para instalar el proyecto en su maquina local realizaremos los siguientes pasos.

1. Abriremos git bash en donde quiera guardar el proyecto.
2. clonaremos el repositorio usando la siguiente URL: https://github.com/Diegoc04/AREP-2024-2.git, una vez clonado nos moveremos a la carpeta AREP usando el comado cd.
3. Nos moveremos a la rama Taller-5 usando el comando git checkout.

## Pruebas
Para ejecutar las pruebas utilizamos el comando: mvn test
![image](https://github.com/user-attachments/assets/e54c1374-2429-4bd0-b4a0-a4bada21a681)

## Arquitecutra del proyecto
1. Capa de Modelo 
Entidad Customer: Esta clase define el modelo de datos, es decir, cómo se representan los clientes en la base de datos. Utiliza las anotaciones de JPA para mapear la clase a una tabla en la base de datos relacional.
Tiene campos como firstName, lastName, address, price, size, y description, que son atributos de un cliente.
Implementa los métodos getter y setter, así como un constructor personalizado para inicializar los objetos Customer.
2. Capa de Repositorio (Repository)
CustomerRepository: Es la interfaz que extiende JpaRepository, proporcionando acceso a las operaciones CRUD básicas y paginación. También incluye algunos métodos personalizados como findByLastName y findById, que permiten realizar búsquedas específicas.
Repositorio: Actúa como la capa de persistencia, interactuando directamente con la base de datos para realizar operaciones CRUD.
3. Capa de Servicio (Service)
CustomerService: Contiene la lógica de negocio para gestionar los clientes.
Se encarga de las operaciones de crear, actualizar, eliminar y recuperar clientes de la base de datos.
Es un intermediario entre el controlador y el repositorio, asegurando que la lógica de negocio esté desacoplada del acceso a la base de datos.
En este servicio se manejan excepciones como ResourceAccessException y RelationNotFoundException cuando no se encuentran los recursos necesarios.
4. Capa de Controlador (Controller)
CustomerController: Este controlador maneja las solicitudes HTTP entrantes relacionadas con los clientes. Utiliza CustomerService para ejecutar las operaciones necesarias y devuelve vistas o redirige al usuario.
Los métodos del controlador manejan solicitudes GET y POST para listar, crear, actualizar y eliminar clientes.
Utiliza anotaciones de Spring MVC como @GetMapping, @PostMapping, @ModelAttribute y @PathVariable para mapear las rutas y manejar los datos de entrada y salida.
Interacción con vistas: El controlador interactúa con las vistas a través de plantillas (como las vistas index y create) y utiliza Model para pasar datos a estas vistas.
5. Capa de Vista (View)
Aunque no has incluido las plantillas en tu código, es probable que estés utilizando un motor de plantillas como Thymeleaf o JSP para renderizar las vistas.
Las plantillas mencionadas (index, create) permiten que los usuarios interactúen con la aplicación a través de una interfaz web.
6. Pruebas
CustomerControllerTests: Aunque el código de prueba proporcionado es mínimo, se utiliza @SpringBootTest para verificar que el contexto de la aplicación se carga correctamente. Esto asegura que todos los componentes estén correctamente configurados y que las dependencias estén disponibles.

## Video presentación 
El siguiente enlace contiene el video en el que se muestra la funcionalidad de la aplicación.
[Link](https://www.youtube.com/watch?v=2OG1bqW0_cc&ab_channel=DiegoCastellanos)

# Construido con
[Apache NeatBeans IDE 22](https://netbeans.apache.org/front/main/download/nb22/) - entorno de desarrollo.

[maven](https://maven.apache.org/) - Herramienta de automatización de proyectos

[java](https://www.java.com/es/) - Lenguaje de programación

# Versiones 
maven: 3.9.9

Java: 17.0.10

# Autor
Diego Fernando Castellanos Amaya - [Diegoc04](https://github.com/Diegoc04)

# Agradecimientos
* Al profesor Daniel Benavides por impartir esta clase y tener la pasión de enseñar.
