<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="utf-8">
    <title>订单列表</title>

    <#include  "../common/link.ftl">

    <script>
        function finish() {
            location.href="/sell/seller/order/finish?orderId=${orderDTO.orderId}";
        }

        function cancel() {
            location.href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}";
        }

    </script>

</head>


<body>



<div class="cryptodash-full-wrapper">
    <div class="crypto-dash-sidenav">
        <div id="sideNavWrapper" class="active">

            <#--侧边栏-->
            <#include  "../common/sidebar.ftl">
            <#--侧边栏-->






            <#--内容区域-->

            <div id="page-content-wrapper" class="dashboard-content">

                <div class="page-content inset">
                    <div class="crypto-container">

                        <div class="row">


                            <#--总览表格-->
                            <div class="col-xl-12">
                                <div class="recent-transaction-history">
                                    <h2 class="crypto-stitle">总览</h2>
                                    <div class="rth-table">


                                        <table class="table cryptodash-tableA" >

                                            <thead>
                                            <tr>
                                                <th style="text-align: center">订单Id</th>
                                                <th style="text-align: center">订单总金额</th>
                                            </tr>
                                            </thead>

                                            <tr>
                                                <td style="text-align: center">${orderDTO.orderId}</td>
                                                <td style="text-align: center">${orderDTO.orderAmount}</td>
                                            </tr>


                                        </table>

                                    </div>
                                </div>
                            </div>
                            <#--总览表格-->





                            <#--细则表格-->
                            <div class="col-xl-12">
                                <div class="recent-transaction-history">
                                    <h2 class="crypto-stitle">细则</h2>
                                    <div class="rth-table">
                                        <table class="table cryptodash-tableA" >

                                            <thead>
                                            <tr>
                                                <th style="text-align: center">商品Id</th>
                                                <th style="text-align: center">商品名称</th>
                                                <th style="text-align: center">价格</th>
                                                <th style="text-align: center">数量</th>
                                                <th style="text-align: center">总额</th>
                                            </tr>
                                            </thead>

                                            <#list orderDetailList as orderDetail>
                                                <tr>
                                                    <td style="text-align: center">${orderDetail.productId}</td>
                                                    <td style="text-align: center">${orderDetail.productName}</td>
                                                    <td style="text-align: center">${orderDetail.productPrice}</td>
                                                    <td style="text-align: center">${orderDetail.productQuantity}</td>
                                                    <td style="text-align: center">${orderDetail.getTotalAmount()}</td>
                                                </tr>
                                            </#list>


                                        </table>
                                    </div>
                                </div>
                            </div>
                            <#--细则表格-->




                        </div>

                        <div class="row" style="margin-top: 20px">
                            <div class="col-md-12 column">

                                <#if orderDTO.orderStatus == 0 >
                                    <button type="button" class="btn btn-lg btn-success"  onclick="finish()">完结订单</button>
                                </#if>

                                <#if orderDTO.orderStatus !=0 >
                                    <button type="button" class="btn btn-lg btn-success"  onclick="finish()" disabled="disabled">完结订单</button>
                                </#if>





                                <#if orderDTO.orderStatus == 2 >
                                    <button  type="button" class="btn btn-danger btn-lg" onclick="cancel()"  disabled="disabled">取消订单</button>
                                </#if>

                                <#if orderDTO.orderStatus == 0 >
                                    <button  type="button" class="btn btn-danger btn-lg" onclick="cancel()" >取消订单</button>
                                </#if>

                            </div>
                        </div>

                    </div>

                </div>

            </div>

            <#--内容区域-->

        </div>
    </div>
</div>


<#include "../common/script.ftl">





</body>
</html>
