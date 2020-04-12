<div class="contenttitile">
    <h2>供应商</h2>
</div>

<div class="contentbody">

    <#-- -----搜索栏-----   -->
    <div class="searchpart">
        <#if searchcondition??>
            <input type="text" id="searchcondition" value="${searchcondition}">
        <#else >
            <input type="text" id="searchcondition">
        </#if>
        <button class="btn-info"  onclick="searchsupplier()">查询</button>
        <button class="btn-success" data-toggle="modal" data-target="#addsuppliermodal">增加供应商</button>
    </div>

    <#-- ------展示表-------   -->
    <div class="tablepart" id="tablepart">
        <table class="supplierstable">
            <tr class="firstline">
                <td>id</td>
                <td>供应商编号</td>
                <td>供应商名</td>
                <td>联系方式</td>
                <td>邮箱</td>
                <td>地址</td>
                <td>负责人</td>
                <td>负责人电话</td>
                <td>操作</td>
            </tr>

            <#if suppliers??>
                <#list suppliers as supplier>
                    <tr>
                        <td><span>${supplier.id}</span></td>
                        <td><input type="text" value="${supplier.sId}" id="${supplier.id}sid"></td>
                        <td><input type="text" value="${supplier.sName}" id="${supplier.id}sname"></td>
                        <td><input type="text" value="${supplier.sPhone}" id="${supplier.id}sphone"></td>
                        <td><input type="text" value="${supplier.sEmail}" id="${supplier.id}semail"></td>
                        <td><input type="text" value="${supplier.sAddress}" id="${supplier.id}saddress"></td>
                        <td><input type="text" value="${supplier.leader}" id="${supplier.id}leader"></td>
                        <td><input type="text" value="${supplier.leaderphone}" id="${supplier.id}leaderphone"></td>

                        <td>
                            <a href="" onclick="deletesupplier(${supplier.id});return false">删除</a>
                            <a href="" onclick="updatesupplier(${supplier.id});return false">修改</a>
                        </td>
                    </tr>
                </#list>
            </#if>

        </table>

        <#--   -------页码按钮组------     -->
        <div class="pagebuttons">
            <span>第${page.pageNum}页/共${page.pages}页&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <#if page.pageNum lte 1>
                <span>首页&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <span>上一页&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <#else >
                <a href="" onclick="tosupplierPage(1);return false">首页&nbsp;&nbsp;&nbsp;&nbsp;</a>
                <a href="" onclick="tosupplierPage(${page.pageNum-1});return false">上一页&nbsp;&nbsp;&nbsp;&nbsp;</a>
            </#if>

            <#if page.pageNum gte page.pages>
                <span>下一页&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <span>尾页&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <#else >
                <a href="" onclick="tosupplierPage(${page.pageNum+1});return false" >下一页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
                <a href="" onclick="tosupplierPage(${page.pages});return false">尾页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
            </#if>
        </div>

    </div>
</div>


<!-- 增加供应商模态框（Modal） -->
<div class="modal fade" id="addsuppliermodal" tabindex="-1" role="dialog" aria-labelledby="addsuppliermodal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">增加供应商</h4>
            </div>
            <div class="modal-body">
                <p>供应商编号：<input type="text" id="newsid"></p>
                <p>供应商名称：<input type="text" id="newsname"></p>
                <p>供应商联系方式：<input type="text" id="newsphone"></p>
                <p>邮箱：<input type="text" id="newsemail"></p>
                <p>地址：<input type="text" id="newsaddress"></p>
                <p>负责人：<input type="text" id="newleader"></p>
                <p>负责人电话：<input type="text" id="newleaderphone"></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn" onclick="addsupplier()">确认</button>
            </div>
        </div>
    </div>
</div>