function loadPosts(posts) {
    posts.forEach(post => {
        addPostToStream(post);
    });
}

function addPostToStream(post) {
    let ul = document.getElementById("posts-stream");
    let li = document.createElement("li");
    li.setAttribute("class", "flex justify-between gap-x-6 py-5");

    placeContent(li, post);
    placeDate(li, post);
    ul.prepend(li);
}

function placeContent(li, post) {
    let principal = document.createElement("div");
    let content = document.createElement("div");
    let author = document.createElement("p");
    let image = document.createElement("img");
    let stream = document.createElement("p");

    // Generar URL de imagen aleatoria
    let gender = Math.random() < 0.5 ? "men" : "women";
    let randomId = Math.floor(Math.random() * 100); // IDs de 0 a 99 en randomuser.me
    let imageUrl = `https://randomuser.me/portraits/${gender}/${randomId}.jpg`;

    // Asignar clases y atributos
    content.setAttribute("class", "min-w-0 flex-auto");
    principal.setAttribute("class", "flex min-w-0 gap-x-4");
    author.setAttribute("class", "text-lg font-semibold leading-6 text-gray-900");
    image.setAttribute("class", "h-12 w-12 flex-none rounded-full bg-gray-50");
    image.setAttribute("src", imageUrl);
    stream.setAttribute("class", "mt-1 text-md leading-6 text-gray-600 text-wrap");

    // Asignar contenido de texto
    author.innerHTML = post.username;
    stream.innerHTML = post.content;

    // Estructurar el contenido en el DOM
    content.appendChild(author);
    content.appendChild(stream);
    principal.appendChild(image); // Agregar imagen de perfil al contenedor principal
    principal.appendChild(content);
    li.appendChild(principal);
}

function placeDate(li, post) {
    let div = document.createElement("div");
    div.setAttribute("class", "text-sm");

    let p = document.createElement("p");
    p.setAttribute("class", "text-md font-semibold leading-6 text-gray-900");
    p.innerHTML = post.creationDate;

    div.appendChild(p);
    li.appendChild(div);
}
