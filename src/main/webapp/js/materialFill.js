
function show(){
    document.getElementById("materialSection").style.display = "none";
    document.getElementById("changeMaterialForm").style.display = "block";
}
function hide(){
    document.getElementById("materialSection").style.display = "block";
    document.getElementById("changeMaterialForm").style.display = "none";
    addMaterials();
}
function logout(){
    window.sessionStorage.removeItem("myJWT");
    window.location.href = "/index.html";
}



function getMaterials(){
    fetch("restservices/materials", {method : 'GET'})
        .then(response => response.json())
        .then(function(data) {
            appendData(data)
        })
        .then(function(myJson){
            console.log(myJson)
        }).catch(error => console.log(error))
}

function appendData(data){
    if (isEmpty(data)) {
        document.getElementById("softwoodTable").textContent = "0";
        document.getElementById("treebranchTable").textContent = "0";
        document.getElementById("clayTable").textContent = "0";
        document.getElementById("goldnuggetTable").textContent = "0";
        document.getElementById("starfragmentTable").textContent = "0";
        document.getElementById("ironnuggetTable").textContent = "0";
        document.getElementById("woodTable").textContent = "0";
        document.getElementById("hardwoodTable").textContent = "0";
        document.getElementById("stoneTable").textContent = "0";
        document.getElementById("clumpofweedsTable").textContent = "0";
    }

}

function isEmpty(obj){
    for (let key in obj){
        if (obj.hasOwnProperty(key)){
            return false;
        }
    }
    return true;
}

function addMaterials(){
    let formData = new FormData(document.querySelector("#POSTmaterialForm"));
    let encData = new URLSearchParams(formData.entries());

    console.log(encData);

    fetch("restservices/materials", {method: 'PATCH', body : encData})
        .then(function(response){
            if (response.ok) {console.log("it works")}
            else if (response.status === 400) {window.alert("Fill everything in.")}
        }).catch( error => console.log(error));
}

    // document.querySelector("#confirmation").addEventListener("click", function(){
    //     let formData = new FormData(document.querySelector("#POSTmaterialForm"));
    //     let encData = new URLSearchParams(formData.entries());
    //     console.log(encData);
    //
    //     fetch("restservices/account", {method: 'POST', body : encData})
    //         .then(response => response.json())
    //         .then(function(myJson){
    //             console.log(myJson)
    //         })
    // });

