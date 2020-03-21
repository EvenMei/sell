<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="utf-8">
    <title>订单列表</title>

    <#include  "../common/link.ftl">

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


                            <#--表格-->
                            <div class="col-xl-12">
                                <div class="recent-transaction-history">
                                    <h2 class="crypto-stitle">订单列表</h2>
                                    <div class="rth-table">
                                        <table class="table cryptodash-tableA" >

                                            <thead >
                                            <tr >
                                                <th style="text-align: center">订单号</th>
                                                <th  style="text-align: center">客户名称</th>
                                                <th style="text-align: center">电话</th>
                                                <th style="text-align: center">地址</th>
                                                <th style="text-align: center">金额</th>
                                                <th style="text-align: center">订单状态</th>
                                                <th style="text-align: center">支付状态</th>
                                                <th style="text-align: center">下单时间</th>
                                                <th colspan="2" style="text-align: center">操作</th>
                                            </tr>
                                            </thead>

                                            <#list orderDTOPage.content as orderDTO>

                                                <tr style="text-align: center">
                                                    <td>${orderDTO.orderId}</td>
                                                    <td>${orderDTO.buyerName}</td>
                                                    <td>${orderDTO.buyerPhone}</td>
                                                    <td>${orderDTO.buyerAddress}</td>
                                                    <td>${orderDTO.orderAmount}</td>
                                                    <td>${orderDTO.getOrderStatusEnum()}</td>
                                                    <td>${orderDTO.getPayStatusEnum()}</td>
                                                    <td>${orderDTO.createTime}</td>
                                                    <th>
                                                        <a href="/sell/seller/order/detail?orderId=${orderDTO.orderId}"  >详情</a>
                                                    </th>


                                                    <th>
                                                        <#if  orderDTO.orderStatus == 0>
                                                            <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}">取消</a>
                                                        </#if>
                                                    </th>


                                                </tr>

                                            </#list>

                                        </table>
                                    </div>
                                </div>
                            </div>
                            <#--表格-->


                            <#--分页-->
                            <div class="col-md-12 column">
                                <ul class="pagination pull-right" >

                                    <#if currentPage gt 1  >
                                        <li><a href="/sell/seller/order/list?page=${currentPage-1}&size=${size}">上一页</a></li>
                                    </#if>

                                    <#if currentPage == 1  >
                                        <li class="disabled"><a href="#">上一页</a></li>
                                    </#if>




                                    <#list 1..orderDTOPage.totalPages as index>

                                        <#if currentPage == index >
                                            <li  class="disabled"><a href="#" >${index}</a></li>
                                        </#if>

                                        <#if  currentPage != index >
                                            <li ><a href="/sell/seller/order/list?page=${index}&size=${size}" >${index}</a></li>
                                        </#if>

                                    </#list>



                                    <#if  orderDTOPage.totalPages gt currentPage>
                                        <li><a href="/sell/seller/order/list?page=${currentPage+1}&size=${size}">下一页</a></li>
                                    </#if>


                                    <#if  orderDTOPage.totalPages == currentPage>
                                        <li class="disabled"><a href="#" >下一页</a></li>
                                    </#if>



                                </ul>
                            </div>
                            <#--分页-->


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
