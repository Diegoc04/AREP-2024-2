from fastapi import FastAPI, Form
from fastapi.responses import HTMLResponse, JSONResponse
from fastapi.staticfiles import StaticFiles
from langchain_openai import ChatOpenAI
import os

# Configurar clave de API de OpenAI
os.environ["OPENAI_API_KEY"] = "API_KEY"

# Crear el modelo de lenguaje
llm = ChatOpenAI(model="gpt-4")

# App definition
app = FastAPI(
    title="LangChain Q&A and Translation API",
    version="1.0",
    description="API for Q&A and Translation with LangChain"
)

# Montar archivos estáticos
app.mount("/static", StaticFiles(directory="static"), name="static")

# Ruta para el playground de preguntas y traducción
@app.get("/ask/playground", response_class=HTMLResponse)
async def ask_playground():
    with open("playground.html", encoding="utf-8") as f:
        html_content = f.read()
    return HTMLResponse(content=html_content, media_type="text/html; charset=utf-8")


# Ruta para preguntas y respuestas generales
@app.post("/ask")
async def ask(question: str = Form(...)):
    response = llm.invoke(question)
    return JSONResponse(content={"answer": response.content})

# Ruta para el servicio de traducción
@app.post("/translate")
async def translate(text: str = Form(...), language: str = Form(...)):
    prompt = f"Translate the following into {language}: {text}"
    response = llm.invoke(prompt)
    return JSONResponse(content={"translation": response.content})

if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="localhost", port=8000)







