alert("Hello, world");

function showDateTime() {
    let now = new Date();
    let day = now.getDate();
    let month = now.getMonth() + 1; // Los meses empiezan desde 0
    let year = now.getFullYear();
    let hours = now.getHours();
    let minutes = now.getMinutes();
    let seconds = now.getSeconds();

    let date = `${day}/${month}/${year}`;
    let time = `${hours}:${minutes}:${seconds}`;

    // Retornamos el resultado para que se pueda mostrar en el alert
    return `Fecha: ${date} Hora: ${time}`;
}

alert(showDateTime());