// const swood =document.getElementById("softwood").value;
// const inugget =document.getElementById("ironnugget").value;
// const tbranch =document.getElementById("treebranch").value;
// const rwood =document.getElementById("wood").value;
// const rclay =document.getElementById("clay").value;
// const hwood =document.getElementById("hardwood").value;
// const gnugget =document.getElementById("goldnugget").value;
// const rstone =document.getElementById("stone").value;
// const sfragment =document.getElementById("starfragment").value;
// const weeds =document.getElementById("clumpofweeds").value;


function show(){
    document.getElementById("materialSection").style.display = "none";
    document.getElementById("changeMaterialForm").style.display = "block";
}

function logout(){
    window.sessionStorage.removeItem("myJWT");
    window.location.href = "/index.html";
}

function appendData(data){

    console.log("Clump" + data["Clump of weeds"]);

        document.getElementById("Soft wood").textContent = data["Soft wood"];
        document.getElementById("Tree branch").textContent = data["Tree branch"];
        document.getElementById("Clay").textContent = data["Clay"];
        document.getElementById("Gold nugget").textContent = data["Gold nugget"];
        document.getElementById("Star fragment").textContent = data["Star fragment"];
        document.getElementById("Iron nugget").textContent = data["Iron nugget"];
        document.getElementById("Wood").textContent = data["Wood"];
        document.getElementById("Hard wood").textContent = data["Hard wood"];
        document.getElementById("Stone").textContent = data["Stone"];
        document.getElementById("Clump of weeds").textContent = data["Clump of weeds"];

        window.location.href = "/materialFill.html";

    }




function getMaterials(){
    let fetchoptions = { method: 'GET', headers : {'Authorization' : 'Bearer ' + window.sessionStorage.getItem("myJWT")}};

    fetch("restservices/materials",fetchoptions)
        .then(response => response.json())
        .then(function(data) {
            console.log("Get data: " + data);

            appendData(data)
        })
        .catch(error => console.log(error))
}



function addMaterials() { //works


        let formData = new FormData(document.querySelector("#PATCHmaterialForm"));
        let encData = new URLSearchParams(formData.entries());
        console.log(encData);
        let fetchoptions = {
            method: 'PATCH',
            body: encData,
            headers: {'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")}
        };

        console.log(encData);

        fetch("restservices/materials", fetchoptions)
            .then(async response => {
                if (response.status === 200) {
                    window.alert("The materials have been added");
                    return response;
                }
            })
            .then(response => response.json())
            .then(function (data) {
                appendData(data)

            })
            .then(function (myJson) {
            }).catch(error => console.log(error))

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

