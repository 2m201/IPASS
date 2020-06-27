let character = document.querySelector("#characterChange");

let characterName = document.querySelector("#characterName");
let characterURL = document.querySelector("#characterURL");
let female = document.getElementById("Female");
let male = document.getElementById("Male");
let characterPersonality = document.querySelector("#characterPersonality");
let characterSpecies = document.querySelector("#characterSpecies");
let characterBirthday = document.querySelector("#characterBirthday");
let characterCatchphrase = document.querySelector("#characterCatchphrase");
let characterDescription = document.querySelector("#characterDescription");

function logout(){
    window.sessionStorage.removeItem("myJWT");
    window.location.href = "/index.html";
}

function createCharacter(){
    let formData = new FormData(document.querySelector("#POSTCharacter"));
    let encData = new URLSearchParams(formData.entries());

    let fetchoptions = {
        method: 'POST', body : encData,
        headers: {'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")}
    };

    fetch("restservices/characters",fetchoptions)
        .then(function (response) {
            if (response.ok) {window.alert("The character has been created.");}
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
    if (character.value == "") {
        return window.alert("Please enter a character you wish to modify.");
    } else if (characterName.value == "" && characterURL.value == ""&& (male.checked == false) && (female.checked == false)  &&
        characterPersonality.value == "" && characterSpecies.value == "" && characterBirthday.value == "" &&
        characterCatchphrase.value == "" && characterDescription.value == "")
        { return window.alert("Please fill in at least one field to modify.");
    }

        let formData = new FormData(document.querySelector("#PATCHCharacter"));
        let encData = new URLSearchParams(formData.entries());
        let fetchoptions = {
            method: 'PATCH',
            body: encData,
            headers: {'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")}
        };

        fetch("restservices/characters/" + character.value, fetchoptions)
            .then(async response => {
                if (response.status === 200) {
                    window.alert("The character has been modified.");
                } else {
                    const JSON = await response.json();
                    window.alert(JSON.error);
                    console.log(JSON.error)
                }
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