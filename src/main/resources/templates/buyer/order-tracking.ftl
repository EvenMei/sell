<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
<meta name="viewport" id="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="format-detection" content="telephone=no"/>
<link href="/sell/buyer/css/reset.css" rel="stylesheet" type="text/css">
<link href="/sell/buyer/css/order-tracking.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="near-box">
    <a href="#" class="jd-ddgz-topbox">
         <span class="jdddgz-top-t1">物流详情</span>
    </a>
    <!--此处是订单内容部分-->
    <div class="jd-ddgz-bigbox">
        <div class="ddgz-a1">
            <span class="ddgz-a1-t1">订单编号：<i>${orderId !""}</i></span>
            <span class="ddgz-a1-t1">快递单号：<i>${expressNumber !""}</i></span>
        </div>
        
        <div class="ddgz-a2">


            <div class="ddgz-a2-b1 first">
                <span class="ddgz-a2-yuan"></span>
                <span class="ddgz-a2-t1">${traceData[0].context}</span>
                <span class="ddgz-a2-t2">${traceData[0].time}</span>
            </div>


          <#list 1..(traceSize-1) as trace>
              <div class="ddgz-a2-b1">
                  <span class="ddgz-a2-yuan"></span>
                  <span class="ddgz-a2-t1">${traceData[trace].context}</span>
                  <span class="ddgz-a2-t2">${traceData[trace].time}</span>
              </div>
          </#list>



<#--            <div class="ddgz-a2-b1 end">-->
<#--                <span class="ddgz-a2-yuan"></span>-->
<#--                <span class="ddgz-a2-t1">您提交了订单，请等待系统确认您提交了订单，请等待系统确认您提交了订单，请等待系统确认</span>-->
<#--                <span class="ddgz-a2-t2">2016-06-28 17:00:51</span>-->
<#--            </div>-->

        </div>
    </div>    
</div>
</body>

</html>