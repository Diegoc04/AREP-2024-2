// Función para enviar preguntas
async function submitQuestion(event) {
    event.preventDefault();  // Evita que el formulario recargue la página
    const question = document.getElementById('question').value;
    const response = await fetch('/ask', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: new URLSearchParams({ 'question': question })
    });
    const result = await response.json();
    document.getElementById('response').innerText = result.answer || result.error;
}

// Función para enviar traducción
async function submitTranslation(event) {
    event.preventDefault();  // Evita que el formulario recargue la página
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
