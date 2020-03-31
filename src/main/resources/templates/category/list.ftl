<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="utf-8">
    <title>类目列表</title>

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
                                    <h2 class="crypto-stitle"> 商品管理列表  </h2>
                                    <div class="rth-table">
                                        <table class="table cryptodash-tableA">
                                            <thead>
                                            <tr>
                                                <th style="text-align: center">类目Id</th>
                                                <th style="text-align: center">类目名称</th>
                                                <th style="text-align: center">类目类型</th>
                                                <th style="text-align: center">创建时间</th>
                                                <th style="text-align: center">修改时间</th>
                                                <th colspan="2" style="text-align: center">操作</th>
                                            </tr>
                                            </thead>


                                            <#list  productCategoryPage.content as productCategory>


                                                <tr>

                                                    <td style="text-align: center">${productCategory.categoryId}</td>
                                                    <td style="text-align: center">${productCategory.categoryName}</td>
                                                    <td style="text-align: center">${productCategory.categoryType}</td>
                                                    <td style="text-align: center">${productCategory.createTime}</td>
                                                    <td style="text-align: center">${productCategory.updateTime}</td>


                                                    <td style="text-align: center">
                                                        <a href="/sell/seller/category/update?categoryId=${productCategory.categoryId}" style="color: #01cd8c ; font-size: large" > 修改</a>
                                                    </td>

                                                        <td style="text-align: center ; color: red" >
                                                            <a href="/sell/seller/category/delete?categoryId=${productCategory.categoryId}" style="color:  red ; font-size: medium; display: none" >删除</a>
                                                        </td>



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
                                        <li><a href="/sell/seller/category/list?page=${currentPage-1}&size=${size}">上一页</a></li>
                                    </#if>

                                    <#if currentPage == 1  >
                                        <li class="disabled"><a href="#">上一页</a></li>
                                    </#if>




                                    <#list 1..productCategoryPage.totalPages as index>

                                        <#if currentPage == index >
                                            <li  class="disabled"><a href="#" >${index}</a></li>
                                        </#if>

                                        <#if  currentPage != index >
                                            <li ><a href="/sell/seller/category/list?page=${index}&size=${size}" >${index}</a></li>
                                        </#if>

                                    </#list>



                                    <#if  productCategoryPage.totalPages  gt currentPage>
                                        <li><a href="/sell/seller/category/list?page=${currentPage+1}&size=${size}">下一页</a></li>
                                    </#if>


                                    <#if  productCategoryPage.totalPages == currentPage>
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
