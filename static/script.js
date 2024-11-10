// Función para enviar preguntas
async function submitQuestion(event) {
    event.preventDefault();
    const question = document.getElementById('question').value;
    const response = await fetch('/ask', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: new URLSearchParams({ 'question': question })
    });
    const result = await response.json();
    document.getElementById('response').innerText = result.answer || "Error en la respuesta.";
}

async function submitTranslation(event) {
    event.preventDefault();
    const text = document.getElementById('text').value;
    const language = document.getElementById('language').value;
    const response = await fetch('/translate', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: new URLSearchParams({ 'text': text, 'language': language })
    });
    const result = await response.json();
    document.getElementById('translation').innerText = result.translation || "Error en la traducción.";
}

