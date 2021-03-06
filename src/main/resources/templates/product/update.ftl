<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8"/>
    <title>商品更新</title>

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


    <#--<script src="/sell/assets/js/jquery-1.10.2.js"></script>-->

</head>
<script>
    function mesubmit() {
        //获取 stock 的值
        var stock = document.getElementById("stock").value;
        if(stock.length>6){
            alert("库存数量是不是有点大？不要超过六位数！");
            event.preventDefault();
        }else{
            var form =document.getElementById("my_form");
            form.submit();
        }
    }





    function deleteProduct() {
        location.href="/sell/seller/product/deleteProduct?productId=${productInfo.productId}&iconPath=${productInfo.productIcon}";
    }


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
                    <div class="col-md-12 column">

                        <h2 class="crypto-stitle">修改商品信息</h2>

                        <div class="row">


                            <#--名称、价格、描述-->
                            <div class="col-xl-6">
                                <div class="crypto-form-wrapper">

                                    <form class="crypto-form"  id="my_form" method="post"  action="/sell/seller/product/submit" > <#--  method="post"    action="/sell/seller/product/submit" -->

                                        <div class="cfi">
                                            <label  >名称</label>
                                            <input type="text" placeholder="输入商品的名称" name="productName" value="${productInfo.productName}" style="font-size: large ;" >
                                        </div>

                                        <div class="row">

                                            <div class="col-xl-4">
                                                <div class="cfi">
                                                    <label >价格</label>
                                                    <input type="text" name="productPrice"  placeholder="输入商品的价格" value="${productInfo.productPrice}">
                                                </div>
                                            </div>

                                            <div class="col-xl-4">
                                                <div class="cfi">
                                                    <label>库存</label>
                                                    <input type="number"  id="stock" name="productStock"  placeholder="输入库存量" value="${productInfo.productStock?replace("," , "")}">
                                                </div>
                                            </div>

                                            <div class="col-xl-4">
                                                <div class="cfi">
                                                    <label for="confirmNewPassword">类目</label>
                                                    <select  id="type_selector" name="productCategory">
                                                        <option >---请选择---</option>
                                                        <#list productCategoryList as productCatetory>
                                                            <#if productInfo.categoryType == productCatetory.categoryType>
                                                                <option value="${productCatetory.categoryType}" selected="selected">${productCatetory.categoryName}</option>
                                                            </#if>

                                                            <#if productInfo.categoryType != productCatetory.categoryType>
                                                                <option value="${productCatetory.categoryType}">${productCatetory.categoryName}</option>
                                                            </#if>

                                                        </#list>


                                                    </select>
                                                </div>
                                            </div>

                                        </div>



                                        <div class="cfi">
                                            <label >描述</label>
                                            <textarea  name="productDescription" rows="4" style="height: auto; width: 100%" >${productInfo.productDescription}</textarea>
                                        </div>

                                        <input type="hidden" name="productId" value="${productInfo.productId}">

                                        <div class="row">

                                            <div class="cfi col-xl-12 ">
                                                <button onclick="mesubmit()" class="btn btn-block btn-success btn-lg" style="width: 100%">提交</button>
                                            </div>

                                        </div>
                                    </form>



                                        <div class="cfi">
                                        <button  onclick="deleteProduct()" class="btn btn-block btn-danger btn-lg" style="width: 100%">删除商品</button>
                                        </div>


                                </div>
                            </div>
                            <#--名称、价格、描述-->






                            <#--图片部分-->
                            <div class="col-xl-6">



                                <div class="crypto-overview-wrapper">

                                    <div class="crypto-overview">
                                        <div class="crypto-current-balance">


                                            <div class="row">
                                                <div class="col-xl-4" >

                                                    <h4>当前商品图片：</h4>
                                                    <img src="${productInfo.productIcon !"" }"  alt="图片挂掉了！" style="width: 100%; height: auto; margin-top: 60px">


                                                </div>

                                                <div class="col-xl-8"  >

                                                    <form enctype="multipart/form-data" action="/sell/seller/product/upload" method="post">

                                                        <div class="form-group"  >

                                                            <input id="file-4" type="file"   name="uploadFile">   <#--multiple=true-->
                                                        </div>


                                                        <input  name="productId" type="hidden"value="${productInfo.productId}">
                                                        <input  name="iconPath" type="hidden"value="${productInfo.productIcon}">

                                                    </form>

                                                </div>


                                            </div>



                                            <div class="row">

                                                <div class="col-xl-12" >





                                                </div>


                                            </div>


                                        </div>
                                    </div>
                                </div>





                            </div>
                            <#--图片部分-->


                        </div>


                    </div>

                </div>
            </div>
        </div>


    </div>
</div>
</div>









<script>
    $('#file-zh').fileinput({
        language: 'zh',
        uploadUrl: '#',
        allowedFileExtensions : ['jpg', 'png','gif'],
    });



    $('#file-zh-TW').fileinput({
        language: 'zh-TW',
        uploadUrl: '#',
        allowedFileExtensions : ['jpg', 'png','gif'],
    });



    $("#file-0").fileinput({
        'allowedFileExtensions' : ['jpg', 'png','gif'],
    });


    $("#file-1").fileinput({
        uploadUrl: '#', // you must set a valid URL here else you will get an error
        allowedFileExtensions : ['jpg', 'png','gif'],
        overwriteInitial: false,
        maxFileSize: 500,
        maxFilesNum: 10,
        //allowedFileTypes: ['image', 'video', 'flash'],
        slugCallback: function(filename) {
            return filename.replace('(', '_').replace(']', '_');
        }
    });



    $("#file-3").fileinput({
        showUpload: false,
        showCaption: false,
        browseClass: "btn btn-primary btn-lg",
        fileType: "any",
        previewFileIcon: "<i class='glyphicon glyphicon-king'></i>"
    });


    $("#file-4").fileinput({
        uploadExtraData: {kvId: '10'}
    });


    $(".btn-warning").on('click', function() {
        if ($('#file-4').attr('disabled')) {
            $('#file-4').fileinput('enable');
        } else {
            $('#file-4').fileinput('disable');
        }
    });


    $(".btn-info").on('click', function() {
        $('#file-4').fileinput('refresh', {previewClass:'bg-info'});
    });



    $(document).ready(function() {
        $("#test-upload").fileinput({
            'showPreview' : false,
            'allowedFileExtensions' : ['jpg', 'png','gif'],
            'elErrorContainer': '#errorBlock'
        });

    });



</script>

<#include "../common/script.ftl">



</body>



</html>