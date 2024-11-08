# Build a Simple LLM Application with LCEL
Crear una aplicación LLM simple con LangChain. Traducirá texto del inglés a otro idioma.

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
3. muevase a la rama Taller-LangChain con el comando: git checkout Taller-LangChain
4. Abra el proyecto en su IDE y en el archivo "langchainserver.py" en la linea 11 modifique "API_KEY" con su llave.

## Ejecutar la aplicación

1. ejecute todas las celdas del archivo "langchainbasicapp.ipynb" para instalar las dependecias necesarias, de ser necesario, modifique "API_KEY" con su llave en la tercera celda de codigo.

2. Ejecute el archivo "langchainserver.py", deberia verse de la siguiente manera:

   ![image](https://github.com/user-attachments/assets/48fca4ae-0de8-4106-b8e6-0eeebb519c7f)

3. Dirijase a la siguiente URL: http://localhost:8000/chain/playground/

![image](https://github.com/user-attachments/assets/b4d01deb-3a7b-43df-9a7b-be456471903f)

# Funcionamiento

![image](https://github.com/user-attachments/assets/bb14c217-4822-49f2-b45a-a647c4da3cd2)

Se coloca como lenguage el español y el emsaje es "this works like a charm!" el cual se traduce correctamente.

![image](https://github.com/user-attachments/assets/9d45eeec-5110-41ba-8fb5-f78008827408)

Se coloca como lenguaje el italiano y el mensaje es "hi" el cual se traduce correctamente.

# Arquitectura 

1. Cliente: Envía una solicitud HTTP con parámetros específicos (idioma y texto a traducir).
2. API Web (FastAPI): Recibe la solicitud y la pasa al Chain.
3. Chain (Cadena de Procesamiento): Compuesto por tres componentes principales:
   
   . Prompt Template: Genera el prompt personalizado para el modelo.
      
   . Modelo (ChatOpenAI con GPT-4): Genera la traducción basada en el prompt.
      
   . Parser: Procesa y formatea la respuesta del modelo.

4. Respuesta: El resultado final se devuelve al cliente en formato de texto.

# Detalles

1. Entorno y Configuración Inicial

Instalación de Paquetes:

langchain: Biblioteca para construir aplicaciones basadas en modelos de lenguaje.
       
langchain-openai: Integración específica para modelos de OpenAI.  
        
langserve: Permite exponer cadenas de LangChain como servicios web.
  

Configuración de la Clave API:

Se establece la variable de entorno OPENAI_API_KEY con la clave de API de OpenAI necesaria para autenticar las solicitudes al modelo GPT-4.

2. Componentes Principales
   
a. Prompt Template: Este prompt se utiliza para instruir al modelo GPT-4 sobre cómo realizar la traducción.

b. Modelo: Es una instancia del modelo GPT-4 proporcionado por OpenAI.

c. parser: Convierte la respuesta del modelo en un formato de cadena simple.

3. API Web con FastAPI:
   
Utiliza add_routes de langserve para exponer la cadena chain como un endpoint en la API. El endpoint /chain acepta solicitudes POST con los parámetros necesarios.

4. Ejecución del Servidor:

       if __name__ == "__main__":
        import uvicorn
        uvicorn.run(app, host="localhost", port=8000)

Inicia el servidor ASGI utilizando Uvicorn, que aloja la aplicación FastAPI. El servidor escucha en localhost en el puerto 8000.

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


