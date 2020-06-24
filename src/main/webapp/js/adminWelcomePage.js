function logout(){
    window.sessionStorage.removeItem("myJWT");
    window.location.href = "/index.html";
}
// document.querySelector("#logoutButton").addEventListener("click", logout);

function createCharacter(){
    let formData = new FormData(document.querySelector("#POSTCharacter"));
    let encData = new URLSearchParams(formData.entries());

    fetch("restservices/characters", {method: 'POST', body : encData})
        .then(function (response) {
            if (response.ok) {window.alert("Character has been created.");}
            else if (response.status === 400) {window.alert("Please fill in everything.");}
            else if (response.status === 409) {window.alert("The character you are trying to create already exists.")}
        }).catch(error => console.log(error))
}

function deleteCharacter() {
    const CHARACTERNAME = document.querySelector("#characterNameD").value;

    if (CHARACTERNAME == "") {
        window.alert("Please fill in a name");
    } else {

        let fetchoptions = {
            method: 'DELETE',
            headers: {'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")}
        };

        fetch("restservices/characters/" + CHARACTERNAME, fetchoptions)
            .then(async response => {
                if (response.status === 200) {
                    window.alert("The character has been deleted");
                } else {
                    const JSON = await response.json();
                    window.alert(JSON.error);
                    console.log(JSON.error)
                }
            })
            .catch(error => console.log(error))
    }
}

function changeCharacter(){
    let character = document.querySelector("#characterChange").value;

    let characterName = document.querySelector("#characterName").value;
    let characterURL = document.querySelector("#characterURL").value;
    let female = document.getElementById("Female");
    let male = document.getElementById("Male");
    let characterPersonality = document.querySelector("#characterPersonality").value;
    let characterSpecies = document.querySelector("#characterSpecies").value;
    let characterBirthday = document.querySelector("#characterBirthday").value;
    let characterCatchphrase = document.querySelector("#characterCatchphrase").value;
    let characterDescription = document.querySelector("#characterDescription").value;

    if (character === "") {
        return window.alert("Please enter a character you wish to modify.");
    } else if (characterName === "" && characterURL === ""&& (male.checked === false) && (female.checked === false)  &&
        characterPersonality === "" && characterSpecies === "" && characterBirthday === "" &&
        characterCatchphrase === "" && characterDescription === ""
    )   { return window.alert("Please fill in at least one field to modify.");}

    // if (female.checked === false  && male.checked === false) {
    //
    // }

    let formData = new FormData(document.querySelector("#PATCHCharacter"));
    let encData = new URLSearchParams(formData.entries());
    let fetchoptions = { method: 'PATCH', body: encData, headers : {'Authorization' : 'Bearer ' + window.sessionStorage.getItem("myJWT")}};

    fetch("restservices/characters/"+character, fetchoptions)
        .then(async response => {
            if (response.status === 200) {window.alert("The character has been modified");
                console.log("it has been modified")}
            else { const JSON = await response.json();
                window.alert(JSON.error);
                console.log(JSON.error)}
        })
        .catch(error => console.log(error))
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