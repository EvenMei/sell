<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<meta name="viewport" id="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<meta name="format-detection" content="telephone=no" />
		<title>我想蟹蟹你呀</title>
		<link href="/sell/buyer/css/reset.css" rel="stylesheet" type="text/css">
		<link href="/sell/buyer/css/classify.css" rel="stylesheet" type="text/css">
		
		<link rel="stylesheet" type="text/css" href="/sell/buyer/css/bootstrap.min.css">
		<link href="http://www.jq22.com/jquery/font-awesome.4.6.0.css" rel="stylesheet" media="screen">
		<link rel="stylesheet" type="text/css" href="/sell/buyer/css/style.css">
		
		<link rel="stylesheet" href="/sell/buyer/css/reset.css">
		<link rel="stylesheet" href="/sell/buyer/css/style2.css">


		<script>
			function test(currentType) {
				window.location.href="/sell/buyer/index?categoryType="+currentType;
				// alert("msg:   hello! " );
				// closeWindow();
				<#--/sell/buyer/index?categoryType=${category.categoryType}-->
			}

			function closeWindow(){
				var userAgent = navigator.userAgent;
				if (userAgent.indexOf("Firefox") != -1 || userAgent.indexOf("Chrome") !=-1) {
					window.location.href="about:blank";
					window.close();
				} else {
					window.opener = null;
					window.open("", "_self");
					window.close();
				}
			}

		</script>
	</head>

	<body>
		
		<input type="hidden" id="input_openid" value="${Session.openid !""}" />

		<!--显示分类菜单-->
		<div class="sucaihuo-container">
				<section class="cd-section">
					<a class="cd-bouncy-nav-trigger" href="#0">切换菜单类目</a>
				</section>

				<div class="cd-bouncy-nav-modal">

					<nav>

						<ul class="cd-bouncy-nav">

							<#list productCategoryList as category>


								<#if currentCategoryType  == category.categoryType>
									<li>
										<a href="javascript:test(${category.categoryType})" style="color: red ; text-decoration-line: none ;text-decoration: none">【${category.categoryName}】</a>
									</li>
								</#if>

								<#if currentCategoryType != category.categoryType >
									<li>
										<a href="javascript:test(${category.categoryType})" style="text-decoration: none; text-decoration-line: none" >【${category.categoryName}】</a>
									</li>
								</#if>




							</#list>
						</ul>
					</nav>
					<a href="#" class="cd-close">Close modal</a>
				</div>
			</div>
		<!--显示分类菜单-->
			
			
		
		<main>
				<div class="container">
					<div class="row">


						<div class="col-md-9">
							<div class="row">

								<#list  productInfoList as productInfo>

									<div class="col-sm-4">
										<div class="ct-product">
											<div class="image"><img src="${productInfo.productIcon}" alt=""></div>
											<div class="inner">
												<a href="#" class="btn btn-motive ct-product-button"><i class="fa fa-shopping-cart"></i></a>
												<h2 class="ct-product-title">${productInfo.productName}</h2>
												<input type="hidden" name="product_id" " value="${productInfo.productId}" />
												<p class="ct-product-description">
														${productInfo.productDescription}
												</p>
												<span class="ct-product-price ">¥${productInfo.productPrice}</span>
											</div>
										</div>
									</div>


								</#list>

							</div>

						</div>

						<div class="col-md-3">
							<div class="widget">
								<h2 class="widget-header">购物车</h2>
								<div class="ct-cart"></div>
							</div>
						</div>


					</div>
				</div>
			</main>
			
		
		
		
		
		<!--底部-->
		<div class="kaola-bottom">
		<!--<a href="./index.ftl" class="kaola-bottom-box1">
			<span class="kaola-bottom-img1"><img src="./images/home.png"></span>
			<span class="kaola-bottom-text1 text2">首页</span>
		</a>-->
		<a href="/sell/buyer/index" class="kaola-bottom-box1">
			<span class="kaola-bottom-img1"><img src="/sell/buyer/images/shop-cart1.png"></span>
			<span class="kaola-bottom-text1">购买</span>
		</a>
		<a href="#" class="kaola-bottom-box1">
			<span class="kaola-bottom-img1"><img src="/sell/buyer/images/search3.png"></span>
			<span class="kaola-bottom-text1">消息</span>
		</a>
		
		<a href="/sell/buyer/mine" class="kaola-bottom-box1">
			<span class="kaola-bottom-img1"><img src="/sell/buyer/images/people1.png"></span>
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
		
		
		
		<script src="/sell/buyer/js/jquery-1.11.3.min.js"></script>
			<script src="/sell/buyer/js/shop.min.js"></script>
			<script>
				$('body').ctshop({
					currency: '¥',
					paypal: {
						currency_code: 'CNY'
					}
				});
			</script>
		</div>
		<div style="text-align:center;margin:-50px 0; font:normal 14px/24px 'MicroSoft YaHei';">
		</div>

		<script src="http://www.jq22.com/jquery/1.11.1/jquery.min.js"></script>
		<script src="/sell/buyer/js/main.js"></script>
		
		
		
	</body>

</html>