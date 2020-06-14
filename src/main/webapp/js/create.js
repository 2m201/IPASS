function logout(){
    window.sessionStorage.removeItem("myJWT");
    window.location.href = "/index.html";
}

function createCharacter(){
    let formData = new FormData(document.querySelector("#POSTCharacter"));
    let encData = new URLSearchParams(formData.entries());

    fetch("restservices/characters", {method: 'POST', body : encData})
        .then(function (response) {
            if (response.ok) {console.log("it works");}
            else if (response.status === 400) {window.alert("Please fill in everything.");}
            else if (response.status === 409) {window.alert("The character you are trying to create already exists.")}
        }).catch(error => console.log(error))
}