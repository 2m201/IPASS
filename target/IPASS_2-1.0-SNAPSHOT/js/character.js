const NAME = localStorage['name'];


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

function getCharacter() {

    fetch("restservices/characters/" + NAME, {method: 'GET'})//with GET-method, no body is sent. So no need for a formData or encData.
        .then(response => response.json())
        .then(function (data) {
            console.log(data);
            appendData(data)

        })
        .then(function (myJson) {
            console.log(myJson)
        }).catch(error => console.log(error))

}

function saveCharacter(){
    const SELECT = document.getElementById('characterList');
    let list =  SELECT.value;

    if (list === "off") {
        window.alert("Please select a list to add the character to.")
    } else {
        let formData = new FormData(document.querySelector('#charFORM'));
        let encData = new URLSearchParams(formData.entries());
        let fetchoptions = {
            method: 'PATCH',
            body: encData,
            headers: {'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")}
        };

        fetch("restservices/characters/save/" + list, fetchoptions)
            .then(async response => {
                if (response.status === 200) {
                    window.alert("The character has been added to the desired list.");
                } else {
                    const JSON = await response.json();
                    window.alert(JSON.error);
                    console.log(JSON.error)
                }
            })
            .catch(error => console.log(error))
    }
}

function logout(){
    window.sessionStorage.removeItem("myJWT");
    window.location.href = "/index.html";
}

function previousPage(){
    history.back();
    // const NAME = localStorage['name'];
    // window.location.href = "/characters/" + NAME;
}

