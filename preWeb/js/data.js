//拥有信息:house_type house_id user_id 举报id
//需要信息:房子的所有信息+举报的所有信息
//一个接口，传入house_type + house_id 返回该房源所有信息
//根据举报id拿到所有举报信息
function getUnDealReports() {
    $.ajax({
        url : "http://47.94.170.167:8080/unDealReport",
        // url:"http://localhost:8080/unDealReport",
        type : "get",
        async : false,
        contentType: "application/json",
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

function getRentInfo(id) {
    var result1 ='';
    $.ajax({
        url : "http://47.94.170.167:8080/rentAllInfo/"+id,
        type : "get",
        async : false,
        contentType: "application/json",
        dataType: "json",
        success : function(result) {
            // console.log(result);
            result1=result;
        },
        error : function(e){
            console.log(e.status);
            console.log(e.responseText);
        }
    });
    return result1;
}

function getSellInfo(id) {
    var result1 = '';
    $.ajax({
        url : "http://47.94.170.167:8080/sellAllInfo/"+id,
        type : "get",
        async : false,
        contentType: "application/json",
        dataType: "json",
        success : function(result) {
            // console.log(result);
            result1 = result;
        },
        error : function(e){
            console.log(e.status);
            console.log(e.responseText);
        }
    });
    return result1;
}

function getReportInfo(id) {
    var result1 = '';
    $.ajax({
        url : "http://47.94.170.167:8080/showReport",
        type : "post",
        async : false,
        contentType: "application/json",
        data : "{\"id\": "+id+"}",
        dataType: "json",
        success : function(result) {
            // console.log(result);
            // console.log(result.house_type);
            // house_type = result.house_type;
            result1=result;
        },
        error : function(e){
            console.log(e.status);
            console.log(e.responseText);
        }
    });
    return result1;
}

function getDeatils(reportId) {
    var houseId = 0;
    var houseType = 0;
    $.ajax({
        url : "http://47.94.170.167:8080/showReport",
        type : "post",
        async : false,
        contentType: "application/json",
        data : "{\"id\": "+reportId+"}",
        dataType: "json",
        success : function(result) {
            houseId = result.house_id;
            houseType = result.house_type;
        },
        error : function(e){
            console.log(e.status);
            console.log(e.responseText);
        }
    });
    var params = [];
    if(houseType===0){
        params.push(getReportInfo(reportId));
        params.push(getRentInfo(houseId));
        return params;
    }
    else if(houseType===1){
        params.push(getReportInfo(reportId));
        params.push(getSellInfo(houseId));
        return params;
    }
}

//从index页面获取reportId传过来即可
function fill(reportId){
    console.log(getDeatils(reportId));
    // console.log(getDeatils(reportId)[1].sellInfo.title);
    var details = getDeatils(reportId);
    //租房渲染
    if(details[0].house_type===0){
        $('#title').html(details[1].rentInfo.title);
        $('#price').html(details[1].rentInfo.price+"￥/月");
        $('#introduction').html(details[1].rentInfo.address +" "+details[1].rentInfo.orientation+" "+details[1].rentInfo.type);
        $('#reason').html(details[0].content);
        $('#reportId').html(details[0].id);
        //图片赋值
        if(details[1].imageList.length!=0){
            //赋值大图
            $('#zoom1').attr("src",details[1].imageList[0].imageUrl);
            $("#zoom1").elevateZoom({
                gallery:details[1].imageList[0].imageUrl,
                responsive : true,
                cursor: 'crosshair',
                zoomType : 'inner'
            });
        }

    }
    //卖房渲染
    else if(details[0].house_type===1){
        $('#title').html(details[1].sellInfo.title);
        $('#price').html(details[1].sellInfo.price+"￥");
        $('#introduction').html(details[1].sellInfo.address +" "+details[1].sellInfo.orientation+" "+details[1].sellInfo.type);
        $('#reason').html(details[0].content);
        $('#reportId').html(details[0].id);
        if(details[1].imageList.length!=0) {
            //赋值大图
            $('#zoom1').attr("src", details[1].imageList[0].imageUrl);
            $("#zoom1").elevateZoom({
                gallery: details[1].imageList[0].imageUrl,
                responsive: true,
                cursor: 'crosshair',
                zoomType: 'inner'
            });
            //赋值小图
        }
    }
}

