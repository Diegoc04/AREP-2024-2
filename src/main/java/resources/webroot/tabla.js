let GameList = []
let GameConsult = ''

function getGames(type) {
    let course = document.getElementById(type).value;
    const xhttp = new XMLHttpRequest();
    GameConsult = type;
    xhttp.onload = function() {
        GameList = JSON.parse(this.responseText);
        var previous = document.getElementById("previous");
        var next = document.getElementById("next");
        previous.setAttribute('data-page',0);
        next.setAttribute('data-page', 1);
        nextGames("previous");
    }
    xhttp.open("GET", "/"+type + ".json") ;
    xhttp.send();
}

function createTable(limit,start){
    let table = document.getElementById("GameTable").getElementsByTagName('tbody')[0];
    table.innerHTML = '';
    for (let i = start; i < limit; i++) {
        let game = GameList[i];
        let row = table.insertRow();
        let cell1 = row.insertCell(0);
        let cell2 = row.insertCell(1);
        let cell3 = row.insertCell(2);
        let cell4 = row.insertCell(3);
        cell1.innerHTML = game.nombre;
        cell2.innerHTML = game.fecha;
        cell3.innerHTML = game.consola;
        cell4.innerHTML = game.ventas;    
    }
}

function nextGames(button) {
    var page = parseInt(document.getElementById(button).getAttribute('data-page'));
    var course = document.getElementById('text');
    course.textContent = GameConsult+":Pagina-"+ String(page+1);
    var parar = 0;
    var previous = document.getElementById("previous");
    var next = document.getElementById("next");
    if ((page+1) * 10 > GameList.length) {
        previous.setAttribute('aria-disabled', false);
        next.setAttribute('aria-disabled', true);
        next.setAttribute('tabindex', -1);
        previous.setAttribute('tabindex', 1);
        previous.setAttribute('data-page', page - 1);
        next.setAttribute('data-page', page);
        parar = GameList.length;
    } else if (page == 0) {
        previous.setAttribute('aria-disabled', true);
        next.setAttribute('aria-disabled', false);
        previous.setAttribute('tabindex', -1);
        next.setAttribute('tabindex', 1);
        previous.setAttribute('data-page', 0);
        next.setAttribute('data-page', 1);
        parar = 10;
    } else {
        previous.setAttribute('aria-disabled', false);
        next.setAttribute('aria-disabled', false);
        previous.setAttribute('tabindex', 1);
        next.setAttribute('tabindex', 2);
        previous.setAttribute('data-page', page - 1);
        next.setAttribute('data-page', page + 1);
        parar = (page*10) + 10;
    }
    createTable(parar, page*10);
    changeColor();
}

function changeColor(){
    var newColor = document.getElementById(GameConsult).getAttribute('data-color');
    const tableThreat = document.querySelectorAll(newColor+' thead th');
    tableThreat.style.backgroundColor = '#000000';
}

