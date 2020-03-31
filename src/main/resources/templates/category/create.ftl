<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="utf-8">
    <title>类目新增</title>

    <#include  "../common/link.ftl">
    <script>
        function create() {
            var form  = document.getElementById("my_form");
            var categoryName = document.getElementById("input_categoryName").value;
            var categoryType = document.getElementById("input_categoryType").value;

            if(categoryName == null || categoryName ==""){
                alert("msg:   categoryName 为空！" );
                event.preventDefault();
            }
            if(categoryType == null || categoryType ==""){
                alert("msg： categoryType 为空！");
                event.preventDefault();
            }else{
               form.submit();
            }


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



                            <#--名称、价格、描述-->
                            <div class="col-xl-12">
                                <div class="crypto-form-wrapper">


                                    <form  id="my_form" action="/sell/seller/category/create" method="post">


                                        <div class="row">

                                            <div class="col-xl-8">
                                                <div class="cfi">
                                                    <label  >类目名称</label>
                                                    <input type="text" placeholder="输入类目的名称" id="input_categoryName" name="categoryName"  style="font-size: large ; text-align: center" >
                                                </div>
                                            </div>

                                            <div class="col-xl-4">
                                                <div class="cfi">
                                                    <label >类目类型</label>
                                                    <input type="text" name="categoryType"  id="input_categoryType" placeholder="输入类目的类型，如1，2，3 不可以重复！" style="text-align: center">
                                                </div>
                                            </div>

                                        </div>

                                    </form>

                                    <button   onclick="create()"  class="btn btn-block btn-success btn-lg" style="width: 100%"> 确定并提交</button>



                                </div>

                            </div>
                            <#--名称、价格、描述-->



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
