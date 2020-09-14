var $, tab, dataStr, layer, cacheStr = window.sessionStorage.getItem("cache"),
	oneLoginStr = window.sessionStorage.getItem("oneLogin");
layui.config({
	base: "js/"
}).extend({
	"bodyTab": "bodyTab"
})
layui.use(['bodyTab', 'form', 'element', 'layer', 'jquery'], function() {
	var form = layui.form,
		element = layui.element;
	$ = layui.$;
	layer = parent.layer === undefined ? layui.layer : top.layer;
	tab = layui.bodyTab({
		openTabNum: "50", //最大可打开窗口数量
		url: "json/navs.json" //获取菜单json地址
	});

	//通过顶部菜单获取左侧二三级菜单   实际开发中通过接口传参的方式获取导航数据
	function getData(json) {
		$.getJSON(tab.tabConfig.url, function(data) {
			if (json) {
				dataStr = data.contentManagement;
				//重新渲染左侧菜单
				tab.render();
			}
		})
	}

	// 注：此处只做演示之用，实际开发中通过接口传参的方式获取导航数据
	getData("contentManagement");

	$("body").on("click", ".layui-nav .layui-nav-item a:not('.mobileTopLevelMenus .layui-nav-item a')", function() {
		var url = $(this).data('url');
		var text = $(this).find('cite').text()
		if (url) {
			$('#iframe').attr('src', url);
			$('.main_hd h2').text(text);
			$('.layui-layout-admin').removeClass('showMenu');
			$('body').removeClass('site-mobile');
			document.getElementById("iframe").height=0;
		}
	});



	//退出
	$(".signOut").click(function() {
		window.sessionStorage.removeItem("menu");
		menu = [];
		window.sessionStorage.removeItem("curmenu");
	});

	//	窗口滚动事件
	$(window).bind('scroll', function() {
		if ($(window).scrollTop() > 150) {
			$('.back-to-top').fadeIn();
		} else {
			$('.back-to-top').fadeOut();
		}
	});
	//返回顶部
	$('.back-to-top').click(function() {
		$('body,html').animate({
			scrollTop: 0
		}, 500);
	});

});


//iframe 自适应内容高度
function reinitIframe() {
	var iframe = document.getElementById("iframe");
	try {
		var bHeight = iframe.contentWindow.document.body.scrollHeight;
		var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
		var height = Math.max(bHeight, dHeight);
		iframe.height = height;
	} catch (ex) {}
}

window.setInterval("reinitIframe()", 600);

window.onresize = function() {
	reinitIframe();
}
