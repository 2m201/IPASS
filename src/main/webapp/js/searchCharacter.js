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

    try {
        for (const item of data) {
            let characterA = document.createElement('a');
            let characterLi = document.createElement('li');

            characterLi.appendChild(document.createElement('small')).textContent = item.name;
            characterA.setAttribute('href', 'character.html');
            characterA.onclick = function () {
                passVariable(item.name)
            };

            characterA.appendChild(characterLi);
            characterNav.appendChild(characterA);
            mainContainer.appendChild(characterNav);
        }
    }catch(error) {window.alert("No character exists with the searched characteristic.")}

}

function search(name) { // geen eventListener nodig cuz u have onclick
    if (document.querySelector("#searchCharacter").value == "") {
        window.alert("Please fill in the field");
    } else {

        let character = document.querySelector("#searchCharacter").value;

        let fetchoptions = {
            method: 'GET',
            headers: {'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")}
        };

        fetch("restservices/characters/" + character, fetchoptions)//with GET-method, no body is sent. So no need for a formData or encData.
            .then(response => response.json())
            .then(function (data) {
                if (data.error === "The character you are trying to find does not exist.") {
                } else {
                    appendData(data)
                }
            }).catch(error => console.log(error))
    }

}

