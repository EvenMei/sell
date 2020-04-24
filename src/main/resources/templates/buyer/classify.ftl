<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<meta name="viewport" id="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<meta name="format-detection" content="telephone=no" />
		<link href="./css/reset.css" rel="stylesheet" type="text/css">
		<link href="./css/classify.css" rel="stylesheet" type="text/css">
	</head>

	<body>
		
		
		
		
		
		<div class="near-box">
		</div>
		
		
		
		
		<!--底部-->
		<div class="kaola-bottom">
		<!--<a href="./index.ftl" class="kaola-bottom-box1">
			<span class="kaola-bottom-img1"><img src="./images/home.png"></span>
			<span class="kaola-bottom-text1 text2">首页</span>
		</a>-->
		<a href="index.ftl" class="kaola-bottom-box1">
			<span class="kaola-bottom-img1"><img src="./images/shop-cart1.png"></span>
			<span class="kaola-bottom-text1">购买</span>
		</a>
		<a href="classify.ftl" class="kaola-bottom-box1">
			<span class="kaola-bottom-img1"><img src="./images/search3.png"></span>
			<span class="kaola-bottom-text1">消息</span>
		</a>
		
		<a href="personal-center.ftl" class="kaola-bottom-box1">
			<span class="kaola-bottom-img1"><img src="./images/people1.png"></span>
			<span class="kaola-bottom-text1">我的</span>
		</a>
	</div>
		</div>
		<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
		<script type="text/javascript">
			function switab(tab, con, tab_c_css, tab_n_css, no) {
				$(tab).each(function(i) {
					if(i == no) {
						$(this).addClass(tab_c_css);
					} else {
						$(this).removeClass(tab_c_css);
						$(this).addClass(tab_n_css);
					}
				})
				if(con) {
					$(con).each(function(i) {
						if(i == no) {
							$(this).show();
						} else {
							$(this).hide();
						}
					})
				}
			}
			$(document).ready(function() {
				$(".classify-text1").each(function(i) {
					$(this).click(function() {
						switab('.classify-text1', '.content2', 'pitch-on2', '', i);
					})
				})
			});

			$(".price").click(function() {
				$(this).children(".sort-img").hide();
				$(this).children(".sort-img2").show();
				$(this).children(".sort-img2").toggleClass("img3");
			});
			$(".classifyrt-text1").click(function() {
				$(this).addClass("tcolor-yellow");
				$(this).siblings(".classifyrt-text1").removeClass("tcolor-yellow");
			});
			$(".list-show").click(function() {
				$(this).parent(".classify-right-title").next(".classify-list").slideToggle();
			});

			$(".shop-cart-add").click(function() {
				var multi = 0;
				var vall = $(this).prev().val();
				vall++;
				$(this).prev().val(vall);
				TotalPrice();
			});
			$(".shop-cart-subtract").click(function() {
				var multi = 0;
				var vall = $(this).next().val();
				vall--;
				if(vall <= 0) {
					vall = 0;
				}
				$(this).next().val(vall);
				TotalPrice();
			});
		</script>
	</body>

</html>