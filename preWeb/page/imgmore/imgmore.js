$(function() {
	var flag = true; //防止用户快速多次点击
	var zoom_n = 1;//鼠标滚轮缩放
	
	init(); //初始化
	//点击切换图片
	$(".moveimg").click(function() {
		$(".moveimg").removeClass('active');
		$(this).addClass('active');
		var thisSrc = $(this).attr('src');
		$(".big-img img").attr('src', thisSrc);//获取点击图片的地址并赋值
		$(".big-img .img-parent").attr('style','');//切换图片从正位开始
		zoom_n = 1;
		$(".big-img .img-parent img").css('transform','scale(1)');
	});
	//左右移动
	$(".small-img .left").on('click', function() {
		flag ? left() : "";
	});

	$(".small-img .right").on('click', function() {
		flag ? right() : "";
	});

	function left() {
		flag = false;
		//计算最后
		var imgPosition = $(".moveimg:last").offset().left + $(".moveimg:last").width();
		var boxPosition = $(".small-img").offset().left + $(".small-img").width();
		if(imgPosition >= boxPosition) {
			$('.small-img ul').animate({
				left: '-=105'
			}, 500, function() {
				flag = true;
			});
		} else {
			flag = true;
		}
	}

	function right() {
		flag = false;
		//计算第一个
		var imgPosition = $(".moveimg:first").offset().left;
		var boxPosition = $(".small-img").offset().left;
		if(imgPosition < boxPosition) {
			$('.small-img ul').animate({
				left: '+=105'
			}, 500, function() {
				flag = true;
			});
		} else {
			flag = true;
		}
	}

	function init() {
		var numImg = $('.moveimg').length;
		//重新定义ul 宽度
		$(".small-img ul").css('width', numImg * 105 + 'px');
		$($('.moveimg')[0]).addClass('active'); //第一个给默认选中
		$(".big-img img").attr('src', $($('.moveimg')[0]).attr('src'));
	}
	
	
	//图片拖拽
    var $div_img = $(".big-img .img-parent");
    //绑定鼠标左键按住事件
    $div_img.bind("mousedown", function (event) {
        event.preventDefault && event.preventDefault(); //去掉图片拖动响应
        //获取需要拖动节点的坐标
        var offset_x = $(this)[0].offsetLeft;//x坐标
        var offset_y = $(this)[0].offsetTop;//y坐标
        //获取当前鼠标的坐标
        var mouse_x = event.pageX;
        var mouse_y = event.pageY;
        //绑定拖动事件
        //由于拖动时，可能鼠标会移出元素，所以应该使用全局（document）元素
        $(".big-img .img-parent").bind("mousemove", function (ev) {
            // 计算鼠标移动了的位置
            var _x = ev.pageX - mouse_x;
            var _y = ev.pageY - mouse_y;
            //设置移动后的元素坐标
            var now_x = (offset_x + _x ) + "px";
            var now_y = (offset_y + _y ) + "px";
            //改变目标元素的位置
            $div_img.css({
                top: now_y,
                left: now_x
            });
        });
    });
    //当鼠标左键松开，接触事件绑定
    $(".big-img .img-parent").bind("mouseup", function () {
        $(this).unbind("mousemove");
    });
	
	
	//旋转
    var spin_n = 0;
    $(".img-rotate.left").click(function () {
        spin_n -= 15;
        $(".big-img .img-parent").css({
            "transform":" rotate("+ spin_n +"deg)",
            "-moz-transform":" rotate("+ spin_n +"deg)",
            "-ms-transform":" rotate("+ spin_n +"deg)",
            "-o-transform":" rotate("+ spin_n +"deg)",
            "-webkit-transform":" rotate("+ spin_n +"deg)"
        });
    });
    $(".img-rotate.right").click(function () {
        spin_n += 15;
        $(".big-img .img-parent").css({
            "transform":" rotate("+ spin_n +"deg) ",
            "-moz-transform":" rotate("+ spin_n +"deg) ",
            "-ms-transform":" rotate("+ spin_n +"deg)",
            "-o-transform":" rotate("+ spin_n +"deg)",
            "-webkit-transform":" rotate("+ spin_n +"deg)"
        });
    });
	
	
	
	 //鼠标滚轮缩放图片
    function zoomImg(o,delta) {
    	if(delta == 'up'){
    		zoom_n -= 0.1;
    		zoom_n = zoom_n <= 0.1 ? 0.1 : zoom_n;
    	}else{
    		zoom_n += 0.1;
    	}
        $(".big-img .img-parent img").css({
             "transform": "scale(" + zoom_n + ")",
             "-moz-transform": "scale(" + zoom_n + ")",
             "-ms-transform": "scale(" + zoom_n + ")",
             "-o-transform": "scale(" + zoom_n + ")",
             "-webkit-transform": "scale(" + zoom_n + ")"
        });
    }
    //绑定鼠标滚轮缩放图片
    $(".big-img .img-parent img").bind("mousewheel DOMMouseScroll", function(e) {
    	console.log(1231);
    	e = e || window.event;
    	var delta = e.originalEvent.wheelDelta || e.originalEvent.detail;
    	var dir = delta > 0 ? 'up' : 'down';
    	zoomImg(this,dir);
    	return false;
    });
});