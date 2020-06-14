function logout(){
    window.sessionStorage.removeItem("myJWT");
    window.location.href = "/index.html";
}



function passVariable(name){
    console.log(name);
    window.localStorage.setItem('name', name);
}

function appendData(data) {
    document.getElementById("myData").innerHTML ="";

    let mainContainer = document.getElementById("myData");
    let characterNav = document.createElement('nav');

    for (const item of data) {
        let characterA = document.createElement('a');
        let characterLi = document.createElement('li');

        characterLi.appendChild(document.createElement('small')).textContent= item.name;
        characterA.setAttribute('href', 'character.html');
        characterA.onclick =function() {passVariable(item.name)};

        characterA.appendChild(characterLi);
        characterNav.appendChild(characterA);
        mainContainer.appendChild(characterNav);

    }

}

function search(event) { // geen eventListener nodig cuz u have onclick

    window.addEventListener('error', function(e){window.alert("The character does not exist");}, true);

    let character = document.querySelector("#searchCharacter").value;

    fetch("restservices/characters/" + character, {method: 'GET'})//with GET-method, no body is sent. So no need for a formData or encData.
        .then(response => response.json())
        .then(function (data) {
            if (data.error === "Character doesn't exist") {
                window.alert("The character does not exist");
            } else {
                appendData(data)
            }
        }).catch(error => console.log(error))


}