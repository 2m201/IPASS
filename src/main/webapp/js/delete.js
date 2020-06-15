function logout(){
    window.sessionStorage.removeItem("myJWT");
    window.location.href = "/index.html";
}

function deleteCharacter(){

    const CHARACTERNAME = document.querySelector("#characterName").value;

    fetch("restservices/characters/" + CHARACTERNAME, {method: 'DELETE'})
        .then(function(response) {
            if (response.ok) {window.alert("Character has been deleted.")}
            else if (response.status === 400) {window.alert("Please fill in the field.")}
            else if (response.status === 405) {window.alert("Something went wrong.")}
            else if (response.status === 404){window.alert("Character is not registered in our system.")}
        }).catch(error => console.log(error));
}
