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
    <script src="../static/js/admin/ajax.js"></script>


</head>
<body>
    <div class="showtable">
        <div class="title_context" >
            <h2>管理员用户</h2>
        </div>
        <div class="select_create" style=" margin: 30px;">
            <div class="row">
                <div class="col-md-6">
                    <div  class="select" style="float: right;">
                        查询：
                        <form>
                            <input type="text" id="key" placeholder="请输入查询信息">
                            <button type="submit" id="keybutton" class="btn btn-primary btn-sm" >查询</button>
                        </form>
                    </div>
                </div>

                <div class="col-md-6">
                    <button type="submit" class="btn btn-default" data-toggle="modal" data-target="#addadmin" >添加管理员用户</button>
                </div>
            </div>
        </div>

        <div class="row" >
            <div class="container">
                <div class="sign-content">
                    <div class="table table-striped">
                        <div class="table-responsive maintable">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th>用户编号</th>
                                    <th>账号</th>
                                    <th>用户名</th>
                                    <th>密码</th>
                                    <th>联系方式</th>
                                    <th>创建时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list admins as admin>
                                    <tr>
                                        <form>
                                            <td><input type="text" name="id" value="${admin.id}" disabled></td>
                                                <input type="hidden" name="hid" value="${admin.id}">
                                            <td><input type="text" name="uId" value="${admin.uId}"></td>
                                            <td><input type="text" name="uName" value="${admin.uName}"></td>
                                            <td><input type="text" name="uPassword" value="${admin.uPassword}"></td>
                                            <td><input type="text" name="uPhone" value="${(admin.uPhone)!''}"></td>
                                            <td><input type="text" name="createtime" value="${admin.createtime?string('yyyy-MM-dd')}" disabled></td>
                                            <td>
                                                <button type="submit" class="btn btn-link" onclick="updateuser(0,${admin.id})">修改</button>
                                                <a href="" onclick="deleteuser(${admin.id},0,${admin.uId});return false">删除</a>
                                            </td>
                                        </form>
                                    </tr>
                                </#list>
                                </tbody>
                            </table>
<#--                            分页页码按钮-->
                            <div class="pagebuttons">
                                <span>第${page.pageNum}页/共${page.pages}页&nbsp;&nbsp;</span>
                                <#if page.pageNum lte 1>
                                    <span>首页&nbsp;&nbsp;</span>
                                    <span>上一页&nbsp;&nbsp;</span>
                                    <#else >
                                    <a href="" onclick="toadminpage(1);return false">首页&nbsp;&nbsp;</a>
                                    <a href="" onclick="toadminpage(${page.pageNum-1});return false">上一页&nbsp;&nbsp;</a>
                                </#if>
                                <#if page.pageNum gte page.pages>
                                    <span>下一页&nbsp;&nbsp;</span>
                                    <span>尾页&nbsp;&nbsp;</span>
                                    <#else >
                                    <a href="" onclick="toadminpage(${page.pageNum+1});return false">下一页&nbsp;&nbsp;</a>
                                    <a href="" onclick="toadminpage(${page.pages});return false">尾页&nbsp;&nbsp;</a>
                                </#if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- 增加模态框（Modal） -->
        <div class="modal fade" id="addadmin" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
                        <form>
                            <table>
                                <thead><h4>管理员用户</h4></thead>
                                <tr><td><input type="text" class="form-control" id="createuId" placeholder="请输入账号" required></td></tr>
                                <tr><td><input type="text" class="form-control" id="createuName" placeholder="请输入用户名" required></td></tr>
                                <tr><td><input type="text" class="form-control" id="createuPassword" placeholder="请输入密码" required></td></tr>
                                <tr>  <td><input type="text" class="form-control" id="createuPhone" placeholder="请输入联系方式"></td></tr>

                            </table>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-primary" onclick="adduser(0)">
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