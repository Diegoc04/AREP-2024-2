# Build a Retrieval Augmented Generation (RAG) App
Implementar los conocimientos de los dos tutoriales para crear un proyecto RAG personalizado, mi proyecto consta de una pagina la cual le permite hacer preguntas a el modelo de gpt-4 y ademas permite traducir textos del ingles a otros idiomas(Español, frances, italiano, portugués). Dependiendo de las preguntas las respuestas pueden demorar un poco en salir.

## Empezando
Estas instrucciones le permitirán obtener una copia del proyecto en funcionamiento en su máquina local para fines de desarrollo y prueba.

## prerrequisitos
* Git - Control de versiones.
* python - Lenguaje de programación.
* OPENAI_API_KEY - Debido a temas de seguridad no es posible alojar la llave en el repositorio, por eso debe contar con una para reemplazarla en el codigo para que funcione.

## Instalación 
Para instalar el proyecto en su maquina local realizaremos los siguientes pasos.

1. clone el proyecto con el siguiente comando: git clone https://github.com/Diegoc04/AREP-2024-2.git
2. Muevase a la carpeta Arep-2024-2 con el comando: cd Arep-2024-2/
3. muevase a la rama RAG con el comando: git checkout RAG
4. Abra el proyecto en su IDE y en el archivo "RAGserver.py" en la linea 12 modifique "API_KEY" con su llave.

## Ejecutar la aplicación

1. ejecute las celdas del archivo "langchainbasicapp.ipynb" para instalar las dependecias necesarias, de ser necesario, modifique "API_KEY" con su llave en la decima celda de codigo.

2. Ejecute el archivo "langchainserver.py", puede utilizar el siguiente comando: python RAGserver.py o correrlo directamente en el IDE.

   ![image](https://github.com/user-attachments/assets/e004521e-750d-4738-8f39-2df1e3530f85)


3. Dirijase a la siguiente URL: http://127.0.0.1:8000/ask/playground

![image](https://github.com/user-attachments/assets/4bf0aaa0-e736-47c1-b21e-a7334b319e4b)


# Funcionamiento

![image](https://github.com/user-attachments/assets/43e5941c-890e-4495-992d-803911948ca2)

Le hacemos una pregunta al modelo y esperamos su respuesta.

![image](https://github.com/user-attachments/assets/422c0d67-fc82-4efc-b299-e3d85c1232b8)

le pasamos un texto y esperamos su traducción.

# Arquitectura 

### Componentes de la Arquitectura

Frontend (Cliente):

HTML: La estructura de la página está definida en un archivo HTML (playground.html), incluye formularios para realizar preguntas y solicitar traducciones.

CSS: El archivo styles.css define el diseño y estilo visual de la interfaz de usuario.

JavaScript: El archivo script.js contiene funciones que envían solicitudes HTTP a los endpoints del backend y maneja las respuestas para mostrarlas en la interfaz de usuario sin recargar la página.

### Backend (Servidor):

FastAPI: FastAPI es el framework que se utiliza para el servidor web. Define las rutas HTTP (/ask y /translate) y maneja las solicitudes del frontend.

LangChain y OpenAI API: FastAPI integra el modelo ChatOpenAI a través de la biblioteca LangChain, que envía preguntas al modelo de lenguaje de OpenAI (GPT-4) y devuelve respuestas o traducciones.

### Comunicación entre Cliente y Servidor:

Fetch API: El frontend utiliza fetch() en JavaScript para realizar solicitudes HTTP POST asíncronas al servidor.

Respuestas JSON: El servidor responde en formato JSON, que el JavaScript en el frontend procesa para mostrar la respuesta o traducción en la interfaz de usuario.

### Interacción del Usuario:

El usuario abre la aplicación en un navegador y ve la interfaz generada por el archivo HTML, con formularios para preguntas y traducción.

Cuando el usuario envía una pregunta o texto a traducir, la función JavaScript captura la acción.

### Comunicación Cliente-Servidor:

La función JavaScript envía la información capturada a través de una solicitud HTTP POST al backend.

El endpoint /ask maneja preguntas generales, y el endpoint /translate se encarga de traducciones a un idioma específico.

### Procesamiento en el Backend:

El backend recibe los datos y usa el modelo ChatOpenAI (de LangChain y OpenAI) para generar una respuesta a la pregunta o traducir el texto.

FastAPI encapsula la respuesta en un JSON y la envía de vuelta al cliente.

### Visualización de la Respuesta:

El JavaScript en el frontend recibe el JSON con la respuesta o traducción y la muestra en el elemento adecuado de la interfaz de usuario sin recargar la página.

# Ejecutar las pruebas 

Para ejecutar las pruebas utilice el siguiente comando: pytest test_main.py

![image](https://github.com/user-attachments/assets/4be67840-e835-4058-b419-de7cbb725002)

## Construido con
[Visual Studio Code]([https://netbeans.apache.org/front/main/download/nb22/](https://code.visualstudio.com/)) - entorno de desarrollo.

[python]([https://www.java.com/es/](https://www.python.org/)) - Lenguaje de programación.

[jupyter]([https://www.docker.com/](https://jupyter.org/)) - Documentación de codigo en vivo.

## Versiones 
Python 3.12.5

Visual Studio Code: 1.95.1

## Autores
Diego Fernando Castellanos Amaya - [Diegoc04](https://github.com/Diegoc04)

## Agradecimientos
* Al profesor Daniel Benavides por impartir esta clase y tener la pasión de enseñar.
