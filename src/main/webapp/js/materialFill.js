
function show(){
    document.getElementById('fieldset').disabled = false;
    document.getElementById('change').style.display = "none";
    document.getElementById('confirmation').style.display = "block";
}

let materialHashmap = {};
function hide(){
    document.getElementById('fieldset').disabled = true;
    document.getElementById('change').style.display = "block";
    document.getElementById('confirmation').style.display = "none";

    materialHashmap['softwood'] = document.getElementById('softwood').value;
    console.log(materialHashmap);
}



    document.querySelector("#confirmation").addEventListener("click", function(){
        let formData = new FormData(document.querySelector("#POSTmaterialForm"));
        let encData = new URLSearchParams(formData.entries());
        console.log(encData);

        fetch("restservices/account", {method: 'POST', body : encData})
            .then(response => response.json())
            .then(function(myJson){
                console.log(myJson)
            })
    })

