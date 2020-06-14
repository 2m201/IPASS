
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

        document.getElementById("characterName").value = item.name;
        document.getElementById("title").textContent = item.name;
    }
}

const NAME = localStorage['name'];

function getCharacter() {

    fetch("restservices/characters/" + NAME, {method: 'GET'})//with GET-method, no body is sent. So no need for a formData or encData.
        .then(response => response.json())
        .then(function (data) {
            appendData(data)
        })
        .then(function (myJson) {
            console.log(myJson)
        }).catch(error => console.log(error))

}

function saveCharacter(){
    const SELECT = document.getElementById('characterList');
    let list =  SELECT.value;
    console.log("list: " + list);

    let formData = new FormData(document.querySelector('#charFORM'));
    let encData = new URLSearchParams(formData.entries());

    console.log("encdata: " + encData);
    fetch("restservices/characters/save" + list, {method: 'POST', body: encData})
        .then (function(response) {
            if (response.ok) {
                console.log("it workie");
            } else if (response.status === 409) {
                window.alert("The max capacity of current villagers has been reached.")
            } else if (response.status === 400) {
                window.alert("Something went wrong.")
            }
        }).catch(error => console.log(error));
}

function logout(){
    window.sessionStorage.removeItem("myJWT");
    window.location.href = "/index.html";
}

