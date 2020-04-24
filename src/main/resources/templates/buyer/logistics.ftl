<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
<meta name="viewport" id="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="format-detection" content="telephone=no"/>
<link href="./css/pay.css" rel="stylesheet" type="text/css">
<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>

</head>
<body>
<div class="near-box">
   <a href="news-center.ftl" class="fund-top">发货物流</a>
   <div class="fund-bogbox">
      <div class="pay-a1">
        <div class="pay-a1-b1" data-click="false">
        	<span class="pay-a1-t1">爱鲜蜂配送时间？</span>
        	<span class="pay-a1-i1"></span>
        </div>
        <div class="pay-a1-t2">9：00-次日02：00，晚间营业时间以当前地址的商户营业时间为准。</div>
      </div>
      
      <div class="pay-a1">
        <div class="pay-a1-b1" data-click="false">
        	<span class="pay-a1-t1">爱鲜蜂客服服务时间？</span>
        	<span class="pay-a1-i1"></span>
        </div>
        <div class="pay-a1-t2">
          爱鲜蜂人工客服服务时间是9：00-24:00，智能客服的服务时间是7X24小时。
        </div>
      </div>

      <div class="pay-a1">
        <div class="pay-a1-b1" data-click="false">
        	<span class="pay-a1-t1">可配送哪些城市？</span>
        	<span class="pay-a1-i1"></span>
        </div>
        <div class="pay-a1-t2">
            爱鲜蜂已在北京、上海、天津、广州、佛山、深圳、廊坊、南京、苏州、西安、成都、杭州、三河13个城市开通配送服务，其他城市将会陆续开通，敬请关注。
        </div>
      </div>

      <div class="pay-a1">
        <div class="pay-a1-b1" data-click="false">
        	<span class="pay-a1-t1">如何在爱鲜蜂下单购买商品？</span>
        	<span class="pay-a1-i1"></span>
        </div>
        <div class="pay-a1-t2">
            1)打开爱鲜蜂APP客户端；或者进入“爱鲜蜂”服务号点击“我要下单”；选择商品，加入购物车；
            <br>
            2)  进入购物车页面，选择收货地址、收货时间、确认商品数量等信息，点击【选好了】；
            <br>
            3)  进入订单结算页，选择优惠券、支付方式，确认付款金额，点击【确认付款】完成下单。
        </div>
      </div>

      <div class="pay-a1">
        <div class="pay-a1-b1" data-click="false">
          <span class="pay-a1-t1">如何新增、编辑和删除收货地址？</span>
          <span class="pay-a1-i1"></span>
        </div>
        <div class="pay-a1-t2">
            通过以下方式进入收货地址页，点击地址旁边的编辑功能可编辑或删除当前地址；点击页面下方的【新增地址】可添加收货地址。
            <br>
            方式1：在首页点击顶部的【配送至：XXX】进入收货地址页；
            <br>
            方式2：在首页底部点击【我的】，再点击【我的收货地址】进入收货地址页；
            <br>
            方式3：选择商品后在购物车页，点击地址进入收货地址页。
        </div>
      </div>
      
      <div class="pay-a1">
        <div class="pay-a1-b1" data-click="false">
          <span class="pay-a1-t1">什么是“鲜蜂精选”商品？</span>
          <span class="pay-a1-i1"></span>
        </div>
        <div class="pay-a1-t2">
            在商品名称前面显示“精选”二字标签的商品都是“鲜蜂精选”商品。
        </div>
      </div>

      <div class="pay-a1">
        <div class="pay-a1-b1" data-click="false">
          <span class="pay-a1-t1">退款流程</span>
          <span class="pay-a1-i1"></span>
        </div>
        <div class="pay-a1-t2">
            1)  进入订单详情，点击下方的【申请退款】，爱鲜蜂客服审核通过后会短信通知您；
            <br>
            2)  退款时间说明： 爱鲜蜂会在48小时内自动退款，按原支付方式退回，2-15个工作日内到账。
            <br>
            如果未及时收到退款，请您与第三方支付平台联系核实：
            <br>
            微信支付：请您登陆财付通，进入“钱包-交易记录”或联系财付通客服查询退款；
            <br>
            支付宝支付：请您登陆支付宝，进入“账单”或联系支付宝客服查询退款；
            <br>
            QQ钱包支付：请您登陆QQ钱包，进入“个人中心-交易记录“或联系QQ钱包客服查询退款。
        </div>
      </div>

       
    </div>
</div>
<script type="text/javascript">

$('.pay-a1-b1').click(function(){
        var oClickName = $(this).attr('data-click');
        // console.log(oClickName);
        // return;
        if(oClickName=='true'){
           $(this).siblings('div').slideUp(300);
           $(this).attr('data-click','false')
        }else{
           $(this).siblings('div').slideDown(300);
           $(this).attr('data-click','true')
        }
    });

</script>
</body>
</html>