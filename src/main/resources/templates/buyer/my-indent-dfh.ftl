<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<meta name="viewport" id="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<meta name="format-detection" content="telephone=no" />
		<link rel="stylesheet" href="/sell/buyer/css/reset.css" type="text/css"/>
		<link href="/sell/buyer/css/my-indent.css" rel="stylesheet" type="text/css">
		<title></title>
	</head>
	<body>
		<div class="zjzz-buylist-wear">
			
			<div class="zjzz-buylist-top">
				<a  href="javascript:history.go(-1)" class="zjzz-buylist-t1"></a>
				<span class="zjzz-buylist-t2">我的订单</span>
				<span class="zjzz-buylist-t3">
					<span class="zjzz-buylist-t4"></span>
				</span>
			</div>
			<div class="zjzz-buylist-top2">

				<a href="/sell/buyer/allOrder?openid=${Session.openid !""}" class="zjzz-buylist-tp1">
					<i>全部</i>
				</a>

				<a href="/sell/buyer/waitToPay?openid=${Session.openid !""}" class="zjzz-buylist-tp1">
					<i>待付款</i>
					<#if orderStatusDetail.waitToPay gt 0>
						<span class="zjzz-buylist-tp3">${orderStatusDetail.waitToPay}</span>
					</#if>
				</a>


				<a href="/sell/buyer/waitToDeliver?openid=${Session.openid !""}" class="zjzz-buylist-tp1">
					<i class="zjzz-buylist-tp2"> 待发货 </i>

					<#if orderStatusDetail.waitToDeliver gt 0>
						<span class="zjzz-buylist-tp3">${orderStatusDetail.waitToDeliver}</span>
					</#if>

				</a>
				<a href="/sell/buyer/waitToReceive?openid=${Session.openid !""}" class="zjzz-buylist-tp1">
					<i>待收货</i>

					<#if orderStatusDetail.waitToReceive gt 0 >
						<span class="zjzz-buylist-tp3">${orderStatusDetail.waitToReceive}</span>
					</#if>

				</a>

				<!--<a href="./my-indent-pj.ftl" class="zjzz-buylist-tp1">
					<i>评价</i>
					<span class="zjzz-buylist-tp3">4</span>
				</a>-->
			</div>		
			<div class="zjzz-buylist-mid">
				<!--全部-->
				<div class="zjzz-buylist-m1">

					<#list waitToDeliverList as orderDTO>


					<div class="zjzz-buylist-goods1">
						<div class="zjzz-buylist-gtime">
							<span class="zjzz-buylist-gtime1">${orderDTO.getCreateTime()}</span>
							<span class="zjzz-buylist-gtime2">待发货</span>
						</div>

						<#list orderDTO.orderDetailList as orderDetail >

						<div class="zjzz-buylist-det">
							<img src="${orderDetail.productIcon}"/>
							<div class="zjzz-buylist-gdetail">
								<span class="zjzz-buylist-gtit1">${orderDetail.productName}</span>
								<span class="zjzz-buylist-gmoney">
									<i class="zjzz-buylist-gm1">￥${orderDetail.productPrice}</i>
									<i class="zjzz-buylist-gm2">x  ${orderDetail.productQuantity}</i>
								</span>
							</div>
						</div>

							</#list>

						<span class="zjzz-buylist-goodsm">
							<i class="zjzz-buylist-gm3">共1件</i>
							<i>应付总额：<i class="zjzz-buylist-gm4">￥${orderDTO.orderAmount}</i></i>
						</span>

						<div class="zjzz-buylist-btn">
							<a href="/sell/buyer/order/cancel?openid=${Session.openid}&orderId=${orderDTO.getOrderId()}" class="zjzz-buylist-btn3">取消订单</a>
							<a href="#" class="zjzz-buylist-btn1">催单</a>
						</div>

					</div>
						</#list>

				</div>
			</div>
		</div>
	</body>
</html>
