# Build a Simple LLM Application with LCEL
Crear una aplicación LLM simple con LangChain. Traducirá texto del inglés a otro idioma.

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

# Funcionamiento

Se corre la clase langchainserver.py y se abre la siguiente URL: http://localhost:8000/chain/playground/

![image](https://github.com/user-attachments/assets/f0648f99-dce5-4e7e-b33c-ac4c9e9676ae)

![image](https://github.com/user-attachments/assets/bb14c217-4822-49f2-b45a-a647c4da3cd2)

Se coloca como lenguage el español y el emsaje es "this works like a charm!" el cual se traduce correctamente.

![image](https://github.com/user-attachments/assets/9d45eeec-5110-41ba-8fb5-f78008827408)

Se coloca como lenguaje el italiano y el mensaje es "hi" el cual se traduce correctamente.


