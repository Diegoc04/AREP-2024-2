import os
from fastapi import FastAPI, Form
from fastapi.responses import HTMLResponse, JSONResponse
from fastapi.staticfiles import StaticFiles
from langchain_openai import ChatOpenAI
from vectordb import VectorDatabase
from contextlib import asynccontextmanager
import json


# Configura la clave de API de OpenAI
os.environ["OPENAI_API_KEY"] = "API_KEY"

# Configura la API de OpenAI
llm = ChatOpenAI(model="gpt-4")

# Inicializa vector_db globalmente
vector_db = VectorDatabase(embedding_dimension=1536)

# Cargar documentos desde el archivo JSON
with open("documents.json", "r", encoding="utf-8") as f:
    documents = json.load(f)

# Cargar los documentos en la base de datos vectorial
for doc in documents:
    vector_db.upsert_document(doc_id=doc["id"], text=doc["text"])
print("Documentos cargados exitosamente desde documents.json.")


# Carga los documentos en la base de datos vectorial
for doc in documents:
    vector_db.upsert_document(doc_id=doc["id"], text=doc["text"])
print("Documentos cargados exitosamente.")

# Define el contexto de ciclo de vida para manejar el cierre de la base de datos
@asynccontextmanager
async def lifespan(app: FastAPI):
    yield  # Permite que la aplicación corra
    vector_db.close()  # Cierra la base de datos vectorial al finalizar

# Definición de la aplicación con ciclo de vida
app = FastAPI(
    title="LangChain Q&A and Translation API",
    version="1.0",
    description="API for Q&A and Translation with LangChain",
    lifespan=lifespan
)

# Montar archivos estáticos
app.mount("/static", StaticFiles(directory="static"), name="static")

# Ruta para el playground de preguntas y traducción
@app.get("/ask/playground", response_class=HTMLResponse)
async def ask_playground():
    with open("playground.html", encoding="utf-8") as f:
        html_content = f.read()
    return HTMLResponse(content=html_content)

# Endpoint para preguntas y respuestas (solo usa documentos)
@app.post("/ask")
async def ask(question: str = Form(...)):
    print(f"Pregunta recibida: {question}")
    
    # Recuperar documentos relevantes usando VectorDatabase
    retrieved_docs = vector_db.retrieve_documents(question)
    print(f"Documentos recuperados: {retrieved_docs}")

    # Si no se encuentran documentos relevantes, devolver un mensaje explícito
    if not retrieved_docs:
        print("No se encontraron documentos relevantes en la base de datos.")
        return JSONResponse(content={"answer": "No se encontraron documentos relevantes para responder a esta pregunta."})

    # Crear el contexto y el prompt con los documentos recuperados
    context = " ".join(retrieved_docs)
    prompt = (
        f"Usa la siguiente información para responder la pregunta. "
        f"Si no tienes suficiente información en el contexto proporcionado, responde: 'No tengo información en mi base de datos'.\n"
        f"Contexto: {context}\nPregunta: {question}"
    )
    
    # Intentar obtener una respuesta del modelo de lenguaje utilizando el contexto específico
    try:
        response = llm.invoke(prompt)
        return JSONResponse(content={"answer": response.content})
    except Exception as e:
        print(f"Error al invocar el modelo: {e}")
        return JSONResponse(content={"error": "Error al generar la respuesta."}, status_code=500)


# Endpoint para el servicio de traducción (usa respuestas generales)
@app.post("/translate")
async def translate(text: str = Form(...), language: str = Form(...)):
    print(f"Texto a traducir: {text}, Idioma de destino: {language}")
    prompt = f"Translate the following into {language}: {text}"
    try:
        response = llm.invoke(prompt)
        print(f"Traducción generada: {response.content}")
        return JSONResponse(content={"translation": response.content})
    except Exception as e:
        print(f"Error al invocar el modelo para traducción: {e}")
        return JSONResponse(content={"error": "Hubo un error al generar la traducción."}, status_code=500)

# Ejecutar la aplicación en el puerto especificado
if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="localhost", port=8000)






