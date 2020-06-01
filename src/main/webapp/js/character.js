
function appendData(data){
    let item;
    for (item of data) {
        document.getElementById("header").textContent = item.name;
        document.getElementById("subheader").textContent = item.catchphrase;
        document.getElementById("picture").src = item.picture;
        document.getElementById("description").textContent = item.description;
        document.getElementById("name").textContent = item.name;
        document.getElementById("gender").textContent = item.gender;
        document.getElementById("personality").textContent = item.personality;
        document.getElementById("species").textContent = item.species;
        document.getElementById("birthday").textContent = item.birthday;
    }
}

function getCharacter() {
    let name = localStorage['name'];
    fetch("restservices/characters/" + name, {method: 'GET'})//with GET-method, no body is sent. So no need for a formData or encData.
        .then(response => response.json())
        .then(function (data) {
            appendData(data)
        })
        .then(function (myJson) {
            console.log(myJson)
        });

}
