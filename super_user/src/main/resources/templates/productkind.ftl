<div class="contenttitile">
    <h2>商品种类</h2>
</div>

<div class="contentbody">

<#-- -----搜索栏-----   -->
    <div class="searchpart">
        <#if searchcondition??>
            <input type="text" id="searchcondition" value="${searchcondition}">
        <#else >
            <input type="text" id="searchcondition">
        </#if>
        <button class="btn-info"  onclick="searchkind()">查询</button>
        <button class="btn-success" data-toggle="modal" data-target="#addkindmodal">增加种类</button>
    </div>

<#-- ------展示表-------   -->
    <div class="tablepart" id="tablepart">
        <table class="kindtable">
            <tr class="firstline">
                <td>id</td>
                <td>种类编号</td>
                <td>种类名称</td>
                <td>操作</td>
            </tr>

            <#list kinds as kind>
                <tr>
                    <td><span>${kind.id}</span></td>
                    <td><input type="text" value="${kind.kId}" id="${kind.id}kid"></td>
                    <td><input type="text" value="${kind.kName}" id="${kind.id}kname"></td>
                    <td>
                        <a href="" onclick="deletekind(${kind.id});return false">删除</a>
                        <a href="" onclick="updateKind(${kind.id});return false">修改</a>
                    </td>
                </tr>
            </#list>
        </table>

<#--   -------页码按钮组------     -->
        <div class="pagebuttons">
          <span>第${page.pageNum}页/共${page.pages}页&nbsp;&nbsp;&nbsp;&nbsp;</span>
          <#if page.pageNum lte 1>
              <span>首页&nbsp;&nbsp;&nbsp;&nbsp;</span>
              <span>上一页&nbsp;&nbsp;&nbsp;&nbsp;</span>
          <#else >
              <a href="" onclick="tokindPage(1);return false">首页&nbsp;&nbsp;&nbsp;&nbsp;</a>
              <a href="" onclick="tokindPage(${page.pageNum-1});return false">上一页&nbsp;&nbsp;&nbsp;&nbsp;</a>
          </#if>

          <#if page.pageNum gte page.pages>
              <span>下一页&nbsp;&nbsp;&nbsp;&nbsp;</span>
              <span>尾页&nbsp;&nbsp;&nbsp;&nbsp;</span>
          <#else >
              <a href="" onclick="tokindPage(${page.pageNum+1});return false" >下一页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
              <a href="" onclick="tokindPage(${page.pages});return false">尾页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
          </#if>
      </div>

    </div>
</div>


<!-- 增加种类模态框（Modal） -->
<div class="modal fade" id="addkindmodal" tabindex="-1" role="dialog" aria-labelledby="addkindmodal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">增加种类</h4>
            </div>
            <div class="modal-body">
                <p>种类编号：<input type="text" id="newkid"></p>
                <p>种类名：<input type="text" id="newkname"></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn" onclick="addkind();">确认</button>
            </div>
        </div>
    </div>
</div>