<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="css/slide.css"rel="stylesheet" type="text/css">
    
</head>

<body>
<div class="tab-menu eightrow">
        <span id="bg"></span>
        <ul id="list">
            <li class="active" type="1">24h</li>
            <li type="2">48h</li>
            <li type="3">72h</li>
            <li type="4">96h</li>
            <li type="5">120h</li>
            <li type="6">144h</li>
            <li type="7">168h</li>
            <li type="8">192h</li>
            <li type="9">216h</li>
            <li type="10">240h</li>
            <li type="11">264h</li>
            <li type="12">288h</li>
            <li type="13">312h</li>
            <li type="14">336h</li>
        </ul>
</div>



<div class="tab-menu tworow">
    <span id="thirdbg"></span>
    <ul id="hourlist">
        <li class="active" type="1">24h</li>
        <li type="2">48h</li>
    </ul>
</div>


<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
<script src="script/slide.js"></script>
<script>

    new Slideicon($("#list"),{
        index:0,
        cover:$("#bg"),
        callback:function (data) {
            console.log(data)
        }
    });
    new Slideicon($("#hourlist"),{
        index:0,
        cover:$("#thirdbg"),
        callback:function (data) {
            console.log(data)
        }
    });

</script>
</body>
</html>