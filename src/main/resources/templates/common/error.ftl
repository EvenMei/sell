<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>操作错误提示</title>
    <#--    <link href="../css/bootstrap.min.css" rel="stylesheet">-->
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
<script>
    setTimeout( 'location.href="${url}" ', 3000);

</script>

</head>

<body>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="alert alert-dismissable alert-danger">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <h4>
                    错误！
                </h4> <strong>${msg}</strong> <a href="${url}" class="alert-link">3s 后自动跳转</a>
            </div>
        </div>
    </div>
</div>


</body>



</html>