function login() {
    var id = document.getElementById("signup-username").value;
    var password = document.getElementById("signup-password").value;
    $.ajax({
        url : "http://47.94.170.167:8080/login",
        type : "post",
        async : false,
        contentType: "application/json",
        data : "{\"id\": "+id+",\"password\":"+password+"}",
        dataType: "text",
        success : function(result) {
            console.log(result);
            if(result==="ok"){
                window.location.href="index.html";
            }
        },
        error : function(e){
            console.log(e.status);
            console.log(e.responseText);
        }
    });
}
