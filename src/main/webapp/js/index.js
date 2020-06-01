function login(event){
    document.querySelector("#login").addEventListener("click", function (){

        let formData = new FormData(document.querySelector("#loginForm"));
        let encData = new URLSearchParams(formData.entries());

        fetch ("restservices/authentication", {method : 'POST', body : encData})
            .then(function(response){
                if (response.ok){
                    console.log("it works");
                }
            })
            .then (myJson => {window.sessionStorage.setItem("myJWT", myJson.JWT);
            window.location.href = "/welcomePage.html"})
            .catch( error => console.log(error))
    })
}