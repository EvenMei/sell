<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8"/>
    <title>上架新商品</title>

    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
    <#--    <link href="http://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">-->


    <link rel="stylesheet" type="text/css" href="/sell/assets/css/default.css">
    <link href="/sell/assets/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
    <script src="http://cdn.bootcss.com/jquery/1.11.0/jquery.min.js" type="text/javascript"></script>

    <script src="/sell/assets/js/fileinput.js" type="text/javascript"></script>
    <!--简体中文-->
    <script src="/sell/assets/js/locales/zh.js" type="text/javascript"></script>
    <!--繁体中文-->
    <script src="/sell/assets/js/locales/zh-TW.js" type="text/javascript"></script>


    <script src="https://cdn.bootcss.com/twitter-bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <#--    <script src="http://cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>-->

    <#--图片上传-->
    <#--图片上传-->


    <link href="https://fonts.googleapis.com/css?family=Raleway:300,400,500,700" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Poppins:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i&display=swap" rel="stylesheet">


    <#--修改-->
    <link href="https://cdn.bootcss.com/twitter-bootstrap/4.0.0-alpha.2/css/bootstrap.css" rel="stylesheet">
    <#--    <link rel="stylesheet" href="https://www.jq22.com/jquery/bootstrap-4.2.1.css">-->
    <#--修改-->


    <link rel="stylesheet" href="https://www.jq22.com/jquery/jquery-ui-1.11.0.css">

    <link rel="stylesheet" href="/sell/assets/css/font-awesome.min.css">

    <link rel="stylesheet" href="/sell/assets/dist/metismenu/metisMenu.min.css">

    <link rel="stylesheet" href="/sell/assets/css/animate.css">

    <link rel="stylesheet" href="/sell/assets/css/style.css">

    <link rel="stylesheet" href="/sell/assets/css/responsive.css">

    <link rel="shortcut icon" type="image/png" href="/sell/assets/img/favicon.ico">


    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script src="/sell/assets/js/jquery-1.10.2.js"></script>


</head>
<script>

</script>

<body>


<div class="cryptodash-full-wrapper">
    <div class="crypto-dash-sidenav">
        <div id="sideNavWrapper" class="active">

            <#--侧边栏-->
            <#include  "../common/sidebar.ftl">
            <#--侧边栏-->



            <div class="container">
                <div class="row clearfix">
                    <#--                    <div class="col-md-12 column">-->

                    <h2 class="crypto-stitle">商品发货</h2>


                    <div class="crypto-form-wrapper">

                        <form class="crypto-form"  id="my_form" method="post"  action="/sell/seller/order/deliverOrder"> <#--  method="post"    action="/sell/seller/product/submit" -->

                            <div class="cfi">
                                <label  >快递名称</label>
                                <input type="text" placeholder="输入快递名称" name="expressName"  style="font-size: large ;" >
                            </div>


                            <div class="row">

                                <div class="col-md-12">
                                    <div class="cfi">
                                        <label >快递单号</label>
                                        <input type="text" name="expressNumber"  placeholder="输入快递单号" >
                                    </div>
                                </div>

                            </div>

                            <input type="hidden"  name="orderId" value="${orderId}">


                            <button <#--onclick="mesubmit()"--> class="cfi-button" style="width: 100%">确定并提交</button>

                        </form>
                    </div>


                </div>
            </div>
        </div>


    </div>
</div>
</div>





<#--图片上传-->

<#--图片上传-->

<#include "../common/script.ftl">



</body>



</html>