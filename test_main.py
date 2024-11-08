# test_main.py
import pytest
from fastapi.testclient import TestClient
from RAGserver import app  # Asegúrate de que el nombre coincide con tu archivo principal
from langchain_openai import ChatOpenAI  # Importa ChatOpenAI para poder sobrescribirlo

client = TestClient(app)

# Simulación de la función `llm.invoke` para hacer pruebas unitarias sin depender de la API de OpenAI
class FakeLLMResponse:
    def __init__(self, content):
        self.content = content

class FakeLLM:
    def invoke(self, prompt):
        # Devolver una respuesta simulada dependiendo de la pregunta
        if "capital de españa" in prompt.lower():
            return FakeLLMResponse("La capital de España es Madrid.")
        elif "translate the following into german" in prompt.lower():
            return FakeLLMResponse("Hallo, wie geht es dir?")
        return FakeLLMResponse("Respuesta simulada.")

# Sobrescribir el modelo de lenguaje en la aplicación para las pruebas
app.dependency_overrides[ChatOpenAI] = lambda: FakeLLM()

# Prueba para el endpoint /ask/playground
def test_playground():
    response = client.get("/ask/playground")
    assert response.status_code == 200
    assert "Asistente de Preguntas y Respuestas" in response.text  # Verifica que se cargue el HTML correctamente

# Prueba para el endpoint /ask
def test_ask():
    response = client.post("/ask", data={"question": "¿Cuál es la capital de España?"})
    assert response.status_code == 200
    assert response.json() == {"answer": "La capital de España es Madrid."}

# Prueba para el endpoint /translate
def test_translate():
    response = client.post("/translate", data={"text": "Hello, how are you?", "language": "German"})
    assert response.status_code == 200
    assert response.json() == {"translation": "Hallo, wie geht es dir?"}

# Prueba para el endpoint /ask con una pregunta sobre otro país
def test_ask_france_capital():
    response = client.post("/ask", data={"question": "¿Cuál es la capital de Francia?"})
    assert response.status_code == 200
    assert response.json() == {"answer": "La capital de Francia es París."}

# Prueba para el endpoint /translate con francés como idioma de destino
def test_translate_to_french():
    response = client.post("/translate", data={"text": "Hello, how are you?", "language": "French"})
    assert response.status_code == 200
    assert response.json() == {"translation": "Bonjour, comment ça va?"}

# Prueba para el endpoint /translate con español como idioma de destino
def test_translate_to_spanish():
    response = client.post("/translate", data={"text": "Hello, how are you?", "language": "Spanish"})
    assert response.status_code == 200
    assert response.json() == {"translation": "Hola, ¿cómo estás?"}