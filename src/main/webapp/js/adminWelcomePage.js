function logout(){
    window.sessionStorage.removeItem("myJWT");
    window.location.href = "/index.html";
}
// document.querySelector("#logoutButton").addEventListener("click", logout);