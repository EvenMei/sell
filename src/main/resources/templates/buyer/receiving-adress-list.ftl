<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
<meta name="viewport" id="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="format-detection" content="telephone=no"/>
<link href="./css/reset.css" rel="stylesheet" type="text/css">
<link href="./css/receiving-adress.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="near-box">
	<div class="header">
		<a href="personal-center.ftl" class="left"></a>
		收货地址
	</div>
	<div class="bigbox">
		 <div class="sh-adress-box1">
		 	 <a href="receiving-adress.ftl" class="sh-adress-box2">
		 	 	<span class="sh-adress-text1">张三1</span>
		 	 	<span class="sh-adress-text1 text-r">186****9865</span>
		 	 	<span class="sh-adress-text2">北京 北京市 通州区 经济技术开发区科创十街恺王服饰2号楼4楼401</span>
		 	 </a>
		 	 <div class="sh-adress-box3">
		 	 	<div class="sh-adress-text3">设为默认地址</div>
		 	 	<div class="sh-adress-text4">删除</div>
		 	 </div>
		 </div>

		 <div class="sh-adress-box1">
		 	 <a href="" class="sh-adress-box2">
		 	 	<span class="sh-adress-text1">张三2</span>
		 	 	<span class="sh-adress-text1 text-r">186****9865</span>
		 	 	<span class="sh-adress-text2">北京 北京市 通州区 经济技术开发区科创十街恺王服饰2号楼4楼401</span>
		 	 </a>
		 	 <div class="sh-adress-box3">
		 	 	<div class="sh-adress-text3">设为默认地址</div>
		 	 	<div class="sh-adress-text4">删除</div>
		 	 </div>
		 </div>

		 <div class="sh-adress-box1">
		 	 <a href="" class="sh-adress-box2">
		 	 	<span class="sh-adress-text1">张三3</span>
		 	 	<span class="sh-adress-text1 text-r">186****9865</span>
		 	 	<span class="sh-adress-text2">北京 北京市 通州区 经济技术开发区科创十街恺王服饰2号楼4楼401</span>
		 	 </a>
		 	 <div class="sh-adress-box3">
		 	 	<div class="sh-adress-text3">设为默认地址</div>
		 	 	<div class="sh-adress-text4">删除</div>
		 	 </div>
		 </div>
         <a href="receiving-adress.ftl" class="set-text1">添加新地址</a>
		 <!--确认删除弹层-->
		 <div class="delete-layer-bg" style="display: none;"></div>
		 <div class="delete-layer" style="display: none;">
	         <div class="active-a2">确认后地址将被删除！</div>
	         <div class="active-a3">
	        	<span class="other2 other1">取消</span>
	        	<span class="other2" id="deletebox">确定</span>
	        	<div class="active-a3-x1"></div>
	         </div>
		 </div>
	</div>

</div>
<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript">
	var that;
	$(".sh-adress-text3").click(function () {
		 $(this).toggleClass("img2");
		 $(this).parent(".sh-adress-box3").parent(".sh-adress-box1").siblings(".sh-adress-box1")
		 .find(".sh-adress-text3").removeClass("img2");
	});
	$(".sh-adress-text4").click(function () {
		 $(".delete-layer-bg").show();
		 $(".delete-layer").show();
		 $("body").addClass("ovflowhide");
		 that = $(this);
	});
	$(".delete-layer-bg").click(function () {
		 $(".delete-layer-bg").hide();
		 $(".delete-layer").hide();
		 $("body").removeClass("ovflowhide");
	});
	$(".other1").click(function () {
		 $(".delete-layer-bg").hide();
		 $(".delete-layer").hide();
		 $("body").removeClass("ovflowhide");
	});
	$("#deletebox").click(function () {
		 $(".delete-layer-bg").hide();
		 $(".delete-layer").hide();
		 $("body").removeClass("ovflowhide");
		 $(that).parent(".sh-adress-box3").parent(".sh-adress-box1").remove();
	});
</script>
</body>
</html>