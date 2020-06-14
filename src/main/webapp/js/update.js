function logout(){
    window.sessionStorage.removeItem("myJWT");
    window.location.href = "/index.html";
}

function changeCharacter(){
        let character = document.querySelector("#characterChange").value;

        let formData = new FormData(document.querySelector("#PUTCharacter"));
        let encData = new URLSearchParams(formData.entries());

        fetch("restservices/characters/"+character, {method: 'PUT', body : encData})
            .then(function(response) {
                if (response.ok) {window.alert("Character has been modified")}
                else if (response.status === 400) {window.alert("Please fill everything in")}
                else if (response.status === 409) {window.alert("The character you are trying to modify does not exist.")}
            }).catch(error => console.log(error))
}