<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
<meta name="viewport" id="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="format-detection" content="telephone=no"/>
<link rel="stylesheet" href="/sell/buyer/css/ydui.css" type="text/css"/>
<link rel="stylesheet" href="/sell/buyer/css/demo.css" type="text/css"/>
<link href="/sell/buyer/css/reset.css" rel="stylesheet" type="text/css">
<link href="/sell/buyer/css/receiving-adress.css" rel="stylesheet" type="text/css">
<script src="/sell/buyer/js/ydui.flexible.js"></script>
</head>
<body>
<div class="near-box">
	<div class="header">
<#--		<a href="receiving-adress-list.ftl" class="left"></a>-->
		收货地址
	</div>

    <form action="/sell/buyer/order/createOrder" method="post">

        <textarea name="items" hidden="hidden">${items}</textarea>

        <input type="hidden"  name="openid" value="${openid}"><br>






        <div class="bigbox">
		 <div class="receiv-text1">地址信息</div>
         <div class="receiv-box1"><input type="text" name="name" class="receiv-input1" placeholder="收货人姓名（请使用真实姓名）"></div>
         <div class="receiv-box1"><input type="number" name="phone" class="receiv-input1" placeholder="手机号码"></div>
         <div class="delivery-layer-box">
    		<div class="cell-item">
                <div class="cell-right cell-arrow">
                    <input type="text"  name="address" class="cell-input" readonly id="J_Address" placeholder="请选择收货地址">
                </div>
            </div>
	    </div>
         <div class="receiv-box2">
             <textarea name="address" class="receiv-input2" placeholder="详细地址"></textarea>
         </div>
         <button class="set-text1">确定并支付</button>
	</div>
    </form>

</div>
<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="/sell/buyer/js/ydui.citys.js"></script>
<script type="text/javascript" src="/sell/buyer/js/ydui.js"></script>
<script type="text/javascript">
	/**
 * 默认调用
 */
!function () {
	var $target = $('#J_Address');

	$target.citySelect();

	$target.on('click', function (event) {
		event.stopPropagation();
		$target.citySelect('open');
	});

	$target.on('done.ydui.cityselect', function (ret) {
		$(this).val(ret.provance + ' ' + ret.city + ' ' + ret.area);
	});
}();
</script>
</body>
</html>