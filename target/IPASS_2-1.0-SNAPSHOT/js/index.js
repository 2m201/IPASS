function login(event){
    let formData = new FormData(document.querySelector("#login"));
    let encData = new URLSearchParams(formData.entries());

    fetch("restservices/authentication", {method: 'POST', body : encData})
        .then(function(response){
            if (response.ok) {console.log("it works");
                return response.json()}
            else if (response.status === 400){window.alert("Please fill in every field.")}
            else if (response.status === 404) {window.alert("Account does not exist.")}
            else if (response.status === 409) {window.alert("Wrong password.")}
        })
        .then (myJson => {window.sessionStorage.setItem("myJWT", myJson.JWT);

        let jwtData = myJson.JWT.split('.')[1];
        let decodedJwtJsonData = window.atob(jwtData);
        let decodedJwtData = JSON.parse(decodedJwtJsonData);

        let userRole = decodedJwtData.role;

        if (userRole === "admin"){
            window.location.href = "/adminWelcomePage.html"}
        else{
            window.location.href = "/account.html"
        }}
            )
        .catch(error => console.log(error))
}
document.querySelector("#loginbutton").addEventListener("click", login);
