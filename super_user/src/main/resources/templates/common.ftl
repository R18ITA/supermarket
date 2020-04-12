<html>
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
        <link href="../static/bootstrap/css/bootstrap.css" rel="stylesheet">
        <link href="../static/css/common.css" rel="stylesheet">
        <link href="../static/css/part.css" rel="stylesheet">
        <script type="application/javascript" src="../static/js/common.js"></script>
        <script type="application/javascript" src="../static/js/productsPart.js"></script>
        <script type="application/javascript" src="../static/js/salePart.js"></script>
        <script type="application/javascript" src="../static/js/storePart.js"></script>
        <script type="application/javascript" src="../static/js/purchasingPart.js"></script>
        <script type="application/javascript" src="../static/js/approval.js"></script>
        <script src="../static/jquery/jquery-3.3.1.min.js"></script>
        <script src="../static/bootstrap/js/bootstrap.min.js"></script>
    </head>

    <body onload="loadProductsPart();loadproducts();">
    <!--头部-->
    <header id="header">
        <div class="headertop">
            <h1>超市进存销系统</h1>
            <ul class="top">
                <li>
                    <a href=" "><span class="glyphicon glyphicon-user"></span>个人信息</a>
                </li>
                <li><a href="/Guider/index">退出登录</a></li>
            </ul>
        </div>

        <div class="headerguider">
            <ul class="nav nav-pills nav-stacked guider">
                <li><a href="" onclick="loadProductsPart();return false">商品相关</a></li>
                <li><a href="" onclick="loadSalePart();return false">销售管理</a></li>
                <li><a href="" onclick="loadStorePart();return false">库存管理</a></li>
                <li><a href="" onclick="loadpurchasing();return false">进货管理</a></li>
                <li><a href="" onclick="topurchasinglinepage(1);return false">审批</a></li>
            </ul>
        </div>
    </header>

<#--内容展示区（默认显示商品相关）-->
    <div id="contentpart">

    </div>

    </body>
</html>
