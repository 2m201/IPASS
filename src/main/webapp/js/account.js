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

function loadInformation(){
    getCurrentCharacters();
    getFavouriteCharacters();
}

function getCurrentCharacters(){
    let fetchoptions = { method: 'GET', headers : {'Authorization' : 'Bearer ' + window.sessionStorage.getItem("myJWT")}};


    fetch("restservices/characers/current", fetchoptions)
        .then(async response => {
            if (response.status === 200) {console.log("Characters are being shown");
                return response.json()}
            else { const JSON = await response.json();
                window.alert(JSON.error);
                console.log(JSON.error)}
        }).then(function(data) {
        appendCharacters(data);
    })
        .catch(error => console.log(error))
}

function getFavouriteCharacters(){
    let fetchoptions = { method: 'GET', headers : {'Authorization' : 'Bearer ' + window.sessionStorage.getItem("myJWT")}};

    fetch("restservices/characers/favourite", fetchoptions)
        .then(async response => {
            if (response.status === 200) {console.log("Characters are being shown");
                return response.json()}
            else { const JSON = await response.json();
                window.alert(JSON.error);
                console.log(JSON.error)}
        }).then(function(data) {
        appendCharacters(data);
    })
        .catch(error => console.log(error))
}