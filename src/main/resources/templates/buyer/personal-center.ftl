<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
<meta name="viewport" id="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="format-detection" content="telephone=no"/>
<link href="/sell/buyer/css/reset.css" rel="stylesheet" type="text/css">
<link href="/sell/buyer/css/personal-center.css" rel="stylesheet" type="text/css">

	<script src="/sell/buyer/js/jquery-1.11.3.min.js"></script>
	<script>
		$(function () {
			var openid = $("#input_openid").val();
			$.ajax({
				url: "/sell/buyer/orderDetail",
				type: "GET",
				data: {"openid": openid},
				contentType: "application/json",
				dataType: "json" ,
				success: function (data) {
					if(data.waitToPay > 0){
						$("#wait_to_pay").text(data.waitToPay);
					}
					if(data.waitToDeliver > 0){
						$("#wait_to_deliver").text(data.waitToDeliver);
					}

					if(data.waitToReceive > 0){
						$("#wait_to_receive").text(data.waitToReceive);
					}

					if(data.totalAmount > 0){
						$("#total_order").text(data.totalAmount);
					}



				}
			});


		});
	</script>

</head>
<body>
<div class="near-box">
      <div class="index-bigbox" style="margin-top: 0px;">
          <!--头部开始-->
          <div class="personal-top">
          	 <img src="/sell/buyer/images/599aa36d5e4a4.jpg" alt="" class="personal-img">
          	 <div class="personal-top-box1">
          	 	<span class="personal-top-img1"><img src="/sell/buyer/del/timg1.jpg" alt=""></span>
          	 	<div class="personal-top-box2">
          	 		<span class="personal-top-text1">青春微凉伴离殇</span>
					<input  id="input_openid" type="hidden" value="${Session.openid !""}">
          	 	</div>
          	 </div>
          </div>
          <!--头部结束--> 
          <!--代付款分类开始-->
          <div class="personal-box1 mbt-05">
          	
          	   <a href="/sell/buyer/waitToPay?openid=${Session.openid !""}" class="personal-box2 box3">
          	      <span class="personal-box1-img1"><img src="/sell/buyer/images/personal-center-img1.png" alt=""></span>
          	      <span class="personal-box1-text1">待付款 
          	      	<span class="redFont" id="wait_to_pay"></span>
          	      </span>
          	   </a>
          	   

          	   <a href="/sell/buyer/waitToDeliver?openid=${Session.openid !""}" class="personal-box2 box3">
          	      <span class="personal-box1-img1"><img src="/sell/buyer/images/personal-center-img2.png" alt=""></span>
          	      <span class="personal-box1-text1">
          	      	待发货
          	      	<span class="redFont" id="wait_to_deliver"></span>
          	      </span>
          	   </a>
          	   <a href="/sell/buyer/waitToReceive?openid=${Session.openid !""}" class="personal-box2 box3">
          	      <span class="personal-box1-img1"><img src="/sell/buyer/images/personal-center-img3.png" alt=""></span>
          	      <span class="personal-box1-text1">
          	      	待收货
          	      <span class="redFont" id="wait_to_receive"></span>
          	      </span>
          	   </a>
          	   
          	   
          	   <a href="/sell/buyer/allOrder?openid=${Session.openid !""}" class="personal-box2 box3">
          	      <span class="personal-box1-img1"><img src="/sell/buyer/images/personal-center-img5.png" alt=""></span>
          	      <span class="personal-box1-text1">
					  全部订单
					  <span class="redFont"  id="total_order"></span>
				  </span>
          	   </a>



          </div>
          <!--代付款分类结束-->

          <!--功能分类开始-->
          <a href="/sell/buyer/discount-coupon.html" class="personal-box5">
          	  <span class="personal-box5-img1"><img src="/sell/buyer/images/personal-center-img10.png" alt=""></span>
          	  <span>我的优惠券</span>
          </a>
          <a href="receiving-adress-list.ftl" class="personal-box5">
          	  <span class="personal-box5-img1"><img src="/sell/buyer/images/personal-center-img11.png" alt=""></span>
          	  <span>收货地址</span>
          </a>
          <a href="news-center.ftl" class="personal-box5">
          	  <span class="personal-box5-img1"><img src="/sell/buyer/images/personal-center-img12.png" alt=""></span>
          	  <span>售后服务</span>
          </a>
         
      </div>
      
      <!--bigbox结束-->
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

</body>
</html>