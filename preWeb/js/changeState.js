function deleteHouse() {
    var reportId = document.getElementById("reportId").innerHTML;
    console.log(reportId);
    $.ajax({
        url : "http://localhost:8080/checkReportDelete",
        type : "post",
        async : false,
        contentType: "application/json",
        data : "{\"id\":"+ reportId+"}",
        dataType: "json",
        success : function(result) {
           console.log(result);
        },
        error : function(e){
            console.log(e.status);
            console.log(e.responseText);
        }
    });
}
function ignoreHouse() {
    var reportId = document.getElementById("reportId").innerHTML;
    $.ajax({
        url : "http://localhost:8080/checkReportIgnore",
        type : "post",
        async : false,
        contentType: "application/json",
        data : "{\"id\": "+reportId+"}",
        dataType: "json",
        success : function(result) {
           console.log(result);
        },
        error : function(e){
            console.log(e.status);
            console.log(e.responseText);
        }
    });
}

function sendNews(reportId,content) {
    $.ajax({
        url : "http://localhost:8080/addNews"+reportId,
        type : "post",
        async : false,
        contentType: "application/json",
        data : "{\"content\": "+content+"}",
        dataType: "json",
        success : function(result) {

        },
        error : function(e){
            console.log(e.status);
            console.log(e.responseText);
        }
    });
}