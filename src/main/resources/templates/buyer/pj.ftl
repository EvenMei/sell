<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
<meta name="viewport" id="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="format-detection" content="telephone=no"/>
<link href="./css/pj.css" rel="stylesheet" type="text/css">
<link href="./css/reset.css" rel="stylesheet" type="text/css">
<script src="./js/jquery-1.8.3.min.js"></script>
<script src="./js/jquery.raty.js" type="text/javascript"></script>
</head>
<body>
<div class="near-box">
    <div class="header">
		<a href="./my-indent-pj.html" class="left"></a>
		发表评价
	</div>
    <div class="jd-qrdd-bigbox">
       <div class="tb-pj-a1">
       	  <span class="tbpj-a1-i1"><img src="./del/fruits-img7.png"></span>
       	  <span class="tbpj-a1-t1">新鲜青柠500g/盒 约5粒，保证新鲜！</span>
       </div>
       <div class="tb-pj-a2">
       	  <textarea class="tbpj-a2-input"></textarea>
       </div>
       <div class="tb-pj-a1">
       	  <span class="tbpj-a1-i2 check"></span>
       	  <span class="tbpj-a1-t2">匿名评价</span>
       	  <span class="tbpj-a1-t3">你写的评价会以匿名形式展现</span>
       </div>
       <div class="tb-pj-a1">
	       	<span class="tbpj-a1-t4">描述相符</span>
	       	<div class="tbpj-a1-b1">
			    <div id="star1"></div>
				<div id="result1"></div>
			</div>
       </div>
        <div class="serve-type-box1">
	       <span class="serve-type-text2">上传评价：</span>
	       <span class="serve-type-img2">
	       	   <input type="file" name="" class="serve-type-btn1">
	           <i class="serve-type-img3"></i>
	           <i class="serve-type-text3">上传凭证(最多一张)</i>
	       </span>
        </div>
       <a href="" class="tb-pj-c1">发表评价</a>
    </div>
</div>
<script src="./js/jquery-1.8.3.min.js"></script>
<script src="./js/jquery.raty.js" type="text/javascript"></script>
<script type="text/javascript">
rat('star1','result1',5);
rat('star2','result2',1);
function rat(star,result,m){

	star= '#' + star;
	result= '#' + result;
	$(result).hide();//将结果DIV隐藏

	$(star).raty({
		hints: ['10','20', '30', '40', '50'],
		path: "images/img",
		starOff: 'star-off-big.png',
		starOn: 'star-on-big.png',
		size: 24,
		start: 40,
		showHalf: true,
		target: result,
		targetKeep : true,//targetKeep 属性设置为true，用户的选择值才会被保持在目标DIV中，否则只是鼠标悬停时有值，而鼠标离开后这个值就会消失
		click: function (score, evt) {
			//第一种方式：直接取值
		}
	});
	/*第二种方式可以设置一个隐蔽的HTML元素来保存用户的选择值，然后可以在脚本里面通过jQuery选中这个元素来处理该值。 弹出结果
	var text = $(result).text();
	$('img').click(function () {
		if ($(result).text() != text) {
			alert('你的评分是'+$(result).text()/m+'分');
			alert(result);
			return;
		}
	});*/
}
$(".tbpj-a1-i2").click(function () {
    	$(this).toggleClass("check");
    });
</script>
</body>

</html>