var new_element = document.createElement("script");
var clickCount=0;
new_element.setAttribute("type", "text/javascript");
new_element.setAttribute(
    "src",
    "../../js/changeState.js"
);
document.body.appendChild(new_element);

layui.use(['form','layer','laydate','table','laytpl'],function(){
    var form = layui.form,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table;

    //举报列表
    var tableIns = table.render({
        elem: '#list',
        // url : '../../json/newsList.json',
        url:"http://47.94.170.167:8080/unDealReport",
        cellMinWidth : 95,
        page : true,
        defaultToolbar: ['filter', 'exports', 'print'],
        limit : 20,
        limits : [10,15,20,25],
        id : "list",
        cols : [[
            {field: 'id', title: 'ID', width:60, align:"center" ,fixed:"left"},
            {field: 'title', title: '被举报房源标题', width:400},
            {field: 'content', title: '举报原因', width:220},
            {field: 'house_type',title:'交易类型',width: 90,templet: '#type'},
            {title: '查看详情', align:'center',templet:'#showDetails'},
        ]]
    });

    //
})