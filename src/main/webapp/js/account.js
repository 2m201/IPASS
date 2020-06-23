const SOFTWOOD = document.getElementById("softwood1");
const IRONNUGGET = document.getElementById("ironnugget");
const TREEBRANCH = document.getElementById("treebranch");
const WOOD = document.getElementById("wood1");
const CLAY = document.getElementById("clay1");
const HARDWOOD = document.getElementById("hardwood");
const GOLDNUGGET = document.getElementById("goldnugget");
const STONE = document.getElementById("stone1");
const STARFRAGMENT = document.getElementById("starfragment");
const CLUMPOFWEEDS = document.getElementById("clumpofweeds");


const DIALOG = document.getElementById("changeMaterialDialog");

function logout(){
    window.sessionStorage.removeItem("myJWT");
    window.location.href = "/index.html";
}
function saveNewPassword(){
    let formData = new FormData(document.querySelector("#PATCHAccount"));
    let encData = new URLSearchParams(formData.entries());
    let fetchoptions = { method: 'PATCH', body: encData, headers : {'Authorization' : 'Bearer ' + window.sessionStorage.getItem("myJWT")}};

    fetch("restservices/accounts/account", fetchoptions)
        .then(async response => {
            if (response.status === 200) {window.alert("Your password has been changed.")}
            else { let json = await response.json();
            window.alert(json.error)}
        })
        .catch(error => console.log(error))

}
function show(){
    DIALOG.show();
}
function cancelSave(){
    DIALOG.close();
}
function loadingChar(){
    getCurrentCharacters();
    getFavouriteCharacters();
    getMaterials();
}
function passVariable(name){
    console.log(name);
    window.localStorage.setItem('name', name);
}

function appendCharacters(data, list){
    let currentContainer = document.getElementById("currentCharacters");
    let favouriteContainer = document.getElementById("favouriteCharacters");


    let characterNav = document.createElement('nav');

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

        if (list ==="current") {
            currentContainer.appendChild(characterNav);
        } else if (list === "favourite") {
            favouriteContainer.appendChild(characterNav);

        }

    }
}

function appendFavourite(data){
    let favouriteContainer = document.getElementById("favouriteCharacters");

    let characterNav = document.createElement('nav');

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
        favouriteContainer.appendChild(characterNav);

    }
}

function appendMaterials(data){

    document.getElementById("Soft wood").textContent = data["Soft wood"];
    document.getElementById("Tree branch").textContent = data["Tree branch"];
    document.getElementById("Clay").textContent = data["Clay"];
    document.getElementById("Gold nugget").textContent = data["Gold nugget"];
    document.getElementById("Star fragment").textContent = data["Star fragment"];
    document.getElementById("Iron nugget").textContent = data["Iron nugget"];
    document.getElementById("Wood").textContent = data["Wood"];
    document.getElementById("Hard wood").textContent = data["Hard wood"];
    document.getElementById("Stone").textContent = data["Stone"];
    document.getElementById("Clump of weeds").textContent = data["Clump of weeds"];

} //TODO CLEAR DIALOG INPUTS

function getMaterials(){
    let fetchoptions = { method: 'GET', headers : {'Authorization' : 'Bearer ' + window.sessionStorage.getItem("myJWT")}};

    fetch("restservices/materials",fetchoptions)
        .then(response => response.json())
        .then(function(data) {
            console.log("Get data: " + data);
            appendMaterials(data)
        })
        .catch(error => console.log(error))
}

function getCurrentCharacters(){
    let fetchoptions = { method: 'GET', headers : {'Authorization' : 'Bearer ' + window.sessionStorage.getItem("myJWT")}};


    fetch("restservices/characters/current", fetchoptions)
        .then(async response => {
            if (response.status === 200) {console.log("Characters are being shown");
                return response.json()}
            else { const JSON = await response.json();
                window.alert(JSON.error);
                console.log(JSON.error)}
        }).then(function(data) {
        appendCharacters(data, "current");
    })
        .catch(error => console.log(error))
}

function getFavouriteCharacters(){
    let fetchoptions = { method: 'GET', headers : {'Authorization' : 'Bearer ' + window.sessionStorage.getItem("myJWT")}};

    fetch("restservices/characters/favourite", fetchoptions)
        .then(async response => {
            if (response.status === 200) {console.log("Characters are being shown");
                return response.json()}
            else { const JSON = await response.json();
                window.alert(JSON.error);
                console.log(JSON.error)}
        }).then(function(data) {
        appendCharacters(data, "favourite");
    })
        .catch(error => console.log(error))
}


function addMaterials() { //works

    if (SOFTWOOD.value == "" && TREEBRANCH.value == "" && CLAY.value == "" && GOLDNUGGET.value == "" && STARFRAGMENT.value == "" && IRONNUGGET.value == "" && WOOD.value == "" && HARDWOOD.value == "" && STONE.value == "" && CLUMPOFWEEDS.value == "") {
        window.alert("Please fill in at least one field");
    } else if (SOFTWOOD.value < 0 || TREEBRANCH.value < 0 || CLAY.value < 0 || GOLDNUGGET.value < 0 || STARFRAGMENT.value < 0 || IRONNUGGET.value < 0 || WOOD.value < 0 || HARDWOOD.value < 0 || STONE.value < 0 || CLUMPOFWEEDS.value < 0 ){
        window.alert("Please only fill in positive numbers");
    } else {
        if (SOFTWOOD.value == "") {SOFTWOOD.value = -1;}
        if (TREEBRANCH.value == "") {TREEBRANCH.value = -1;}
        if (CLAY.value == "") {CLAY.value = -1;}
        if (GOLDNUGGET.value == "") {GOLDNUGGET.value = -1;}
        if (STARFRAGMENT.value == "") {STARFRAGMENT.value = -1;}
        if (IRONNUGGET.value == "") {IRONNUGGET.value = -1;}
        if (WOOD.value == "") {WOOD.value = -1;}
        if (HARDWOOD.value == "") {HARDWOOD.value = -1;}
        if (STONE.value == "") {STONE.value = -1;}
        if (CLUMPOFWEEDS.value == "") {CLUMPOFWEEDS.value = -1;}

        let formData = new FormData(document.querySelector("#PATCHmaterialForm"));
        let encData = new URLSearchParams(formData.entries());
        let fetchoptions = {
            method: 'PATCH',
            body: encData,
            headers: {'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")}
        };

        fetch("restservices/materials", fetchoptions)
            .then(async response => {
                if (response.status === 200) {
                    window.alert("The materials have been added");
                    DIALOG.close();
                    location.reload();
                    return response;

                }
            })
            .then(response => response.json())
            .then(function (data) {
                appendMaterials(data)
            })
            .then(function (myJson) {
            }).catch(error => console.log(error))
    }

}
