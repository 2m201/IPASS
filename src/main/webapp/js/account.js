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