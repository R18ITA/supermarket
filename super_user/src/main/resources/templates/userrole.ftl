<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>超市进存销管理系统</title>
    <!--css js-->
    <script src="../static/jquery/jquery-3.3.1.min.js"></script>
    <link href="../static/bootstrap/css/bootstrap.css" rel="stylesheet">
    <script src="../static/bootstrap/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="../static/css/admin/common.css">
    <#--    <link rel="stylesheet" href="/css/admin.css">-->

</head>
<body>
<div class="showtable">
    <div class="title_context" >
        <h2>用户关联角色表</h2>
    </div>
    <div class="select_create" style=" margin: 30px;">
        <div class="row">
            <div class="col-md-6">
                <div  class="select" style="float: right;">
                    查询：
                    <form method="post" action="/searchUR">
                        <input type="text" name="key" placeholder="请输入查询信息">
                        <button type="submit" class="btn btn-primary btn-sm">查询</button>
                    </form>
                </div>
            </div>

            <div class="col-md-6">
                <button type="submit" class="btn btn-default" data-toggle="modal" data-target="#addUR" >添加管理员用户</button>
            </div>
        </div>
    </div>


    <div class="row">
        <div class="container">
            <div class="sign-content">
                <div class="table table-striped">
                    <div class="table-responsive maintable">
                        <table class="table">
                                <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>用户帐号</th>
                                    <th>角色编号</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list urs as ur>
                                    <tr>
                                        <form>
                                        <td><input type="text" name="id" value="${ur.id}" disabled></td>
                                            <input type="hidden" name="hid" value="${ur.id}">
                                        <td><input type="text" name="uid" value="${(ur.uId)!''}"></td>
                                        <td><input type="text" name="rid" value="${(ur.rId)!''}"></td>
                                        <td>
                                            <button type="button" class="btn btn-link" onclick="updataUR(${ur.id})">修改</button>
                                            <a href="" onclick="deleteUR(${ur.id})">删除</a>
                                        </form>
                                        </td>
                                    </tr>
                                </#list>
                                </tbody>
                            </table>
                        <#--                        分页显示-->
                        <div class="pagebuttons">
                            <span>第${page.pageNum}页/共${page.pages}页&nbsp;&nbsp;</span>
                            <#if page.pageNum lte 1>
                                <span>首页&nbsp;&nbsp;</span>
                                <span>上一页&nbsp;&nbsp;</span>
                            <#else >
                                <a href="" onclick="toURpage(1);return false">首页&nbsp;&nbsp;</a>
                                <a href="" onclick="toURpage(${page.pageNum-1});return false">上一页&nbsp;&nbsp;</a>
                            </#if>
                            <#if page.pageNum gte page.pages>
                                <span>下一页&nbsp;&nbsp;</span>
                                <span>尾页&nbsp;&nbsp;</span>
                            <#else >
                                <a href="" onclick="toURpage(${page.pageNum+1});return false">下一页&nbsp;&nbsp;</a>
                                <a href="" onclick="toURpage(${page.pages});return false">尾页&nbsp;&nbsp;</a>
                            </#if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- 增加模态框（Modal） -->
    <div class="modal fade" id="addUR" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        增加
                    </h4>
                </div>
                <div class="modal-body">
                    <form >
                        <table>
                            <thead><h4>用户关联权限</h4></thead>
                            <tr><td><input type="text" class="form-control" id="createURuid" placeholder="请输入用户账号" required></td></tr>
                            <tr><td><input type="text" class="form-control" id="createURrid" placeholder="请输入角色编号" required></td></tr>
                        </table>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary"  onclick="addUR()">
                                提交
                            </button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">
                                取消
                            </button>
                        </div>
                    </form>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>

</div>
</body>
</html>