<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="utf-8">
    <title>订单列表</title>

    <#include  "../common/link.ftl">
    <script src="/sell/assets/js/jquery-1.10.2.js"></script>
    <script>
        //此处用来设置 textarea 自适应文字的高度
        $(function () {
            $('textarea').each(function () {
                this.setAttribute('style', 'height:' + (this.scrollHeight) + 'px;overflow-y:hidden;'+'border:none;'+'readonly:readonly;'+'resize:none;');
            }).on('input', function () {
                this.style.height = 'auto';
                this.style.height = (this.scrollHeight) + 'px';
            });

        });
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


                            <#--表格-->
                            <div class="col-xl-12">
                                <div class="recent-transaction-history">
                                    <h2 class="crypto-stitle"> 商品管理列表  </h2>
                                    <div class="rth-table">
                                        <table class="table cryptodash-tableA">
                                            <thead>
                                            <tr>
                                                <th style="text-align: center">商品Id</th>
                                                <th style="text-align: center">名称</th>
                                                <th style="text-align: center">图片</th>
                                                <th style="text-align: center">单价</th>
                                                <th style="text-align: center">库存</th>
                                                <th style="text-align: center">描述</th>
                                                <th style="text-align: center">类目</th>
                                                <th style="text-align: center">创建时间</th>
                                                <th style="text-align: center">修改时间</th>
                                                <th colspan="2" style="text-align: center">操作</th>
                                            </tr>
                                            </thead>


                                            <#list  productInfoDTOPage.content as productInfo>


                                            <tr>

                                                <td style="text-align: center">${productInfo.productId}</td>
                                                <td style="text-align: center">${productInfo.productName}</td>
                                                <td style="text-align: center">
                                                    <div>
                                                        <img src="  ${productInfo.productIcon !""}" alt="未找到图片" style="width: 80px ; height: 80px" >
                                                    </div>
                                                </td>

                                                <td style="text-align: center">${productInfo.productPrice}</td>
                                                <td style="text-align: center">${productInfo.productStock}</td>
                                                <td style="text-align: center">
<#--                                                    <textarea   id="my_textarea" name="productDescription" rows="5"  readonly="readonly"  style="height: auto; width: auto; border: none; resize: none;overflow-y: visible" >${productInfo.productDescription}</textarea>-->
                                                    <textarea   id="my_textarea" name="productDescription"  style=" border: none; " >${productInfo.productDescription}</textarea>
                                                </td>
                                                <td style="text-align: center">${productInfo.categoryName}</td>
                                                <td style="text-align: center">${productInfo.createTime}</td>
                                                <td style="text-align: center">${productInfo.updateTime}</td>


                                                <td style="text-align: center">
                                                    <a href="/sell/seller/product/update?productId=${productInfo.productId}" style="color: #01cd8c ; font-size: large" > 修改</a>
                                                </td>

                                                <#--已下架-->
                                                <#if productInfo.productStatus == 1>
                                                    <td style="text-align: center ; color: red">
                                                        <a href="/sell/seller/product/modify?productId=${productInfo.productId}&productStatus=${productInfo.productStatus}" style="color:  red ; font-size: medium" >已下架</a>
                                                    </td>
                                                </#if>
                                                <#--已下架-->


                                                <#--在售中-->
                                                <#if productInfo.productStatus == 0>
                                                    <td style="text-align: center ; color: #00b0ff ;">
                                                        <a href="/sell/seller/product/modify?productId=${productInfo.productId}&productStatus=${productInfo.productStatus}" style="color:#01cd8c ; font-size: medium" > 在售中</a>
                                                    </td>
                                                </#if>
                                                <#--在售中-->


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
                                        <li><a href="/sell/seller/product/list?page=${currentPage-1}&size=${size}">上一页</a></li>
                                    </#if>

                                    <#if currentPage == 1  >
                                        <li class="disabled"><a href="#">上一页</a></li>
                                    </#if>




                                    <#list 1..productInfoDTOPage.totalPages as index>

                                        <#if currentPage == index >
                                            <li  class="disabled"><a href="#" >${index}</a></li>
                                        </#if>

                                        <#if  currentPage != index >
                                            <li ><a href="/sell/seller/product/list?page=${index}&size=${size}" >${index}</a></li>
                                        </#if>

                                    </#list>



                                    <#if  productInfoDTOPage.totalPages  gt currentPage>
                                        <li><a href="/sell/seller/product/list?page=${currentPage+1}&size=${size}">下一页</a></li>
                                    </#if>


                                    <#if  productInfoDTOPage.totalPages == currentPage>
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
