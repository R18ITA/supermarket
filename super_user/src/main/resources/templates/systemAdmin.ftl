<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>超市进存销管理系统</title>
    <!--css js-->
    <script src="../static/jquery/jquery-3.3.1.min.js"></script>
    <link href="../static/bootstrap/css/bootstrap.css" rel="stylesheet">
    <script src="../static/bootstrap/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="../static/css/admin/common.css">
    <link rel="stylesheet" href="../static/css/admin/systemAdmin.css">
    <script src="../static/js/admin/ajax.js"></script>
    <style>


    </style>

</head>
<body>
    <!--头部-->
    <div class="top_head">
       <#include "top.ftl">
    </div>

    <div class="center_block">
        <!--左边列表-->
        <div class="left_block" >

            <div class="panel-group" id="accordion">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a href="#collapseOne" data-toggle="collapse" data-parent="#accordion">用户管理</a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse in">
                        <div class="panel-body">
                            <ul>
                                <li><a href="" onclick="loadAdmin();return false">管理员用户</a></li>
                                <li><a href="" onclick="loadUser();return false">普通用户</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default"> <div class="panel-heading">
                        <h4 class="panel-title">
                            <a href="#collapseTwo" data-toggle="collapse" data-parent="#accordion">角色管理</a>
                        </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul>
                                <li><a href="" onclick="loadrole();return false">角色表</a></li>
                                <li><a href="" onclick="loadUR();return false">用户关联角色</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default"> <div class="panel-heading">
                        <h4 class="panel-title">
                            <a href="#collapseThree" data-toggle="collapse" data-parent="#accordion">权限管理</a>
                        </h4>
                    </div>
                    <div id="collapseThree" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul>
                                <li><a href="" onclick="loadpermission();return false">权限表</a></li>
                                <li><a href="" onclick="loadRP();return false">角色关联权限</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <!--右边列表-->
        <div class="right_block" id="right_block">
            <div class="right_default" id="right_load">
                <h2>欢迎来到超市管理系统管理员模块</h2>
            </div>
        </div>
    </div>


</body>
</html>
