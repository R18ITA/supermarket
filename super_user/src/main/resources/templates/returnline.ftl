<div class="contenttitile">
    <h3>退货单</h3>
</div>

<div class="contentbody">
    <#-- -----搜索栏-----   -->
    <div class="searchpart">
        <#if searchcondition??>
            <input type="text" id="searchcondition" value="${searchcondition}">
        <#else >
            <input type="text" id="searchcondition">
        </#if>
        <button class="btn-info"  onclick="searchreturnline()">查询</button>
    </div>

    <#-- ------展示表-------   -->
    <div class="tablepart" id="tablepart">

        <table class="returnlinetable">
            <tr class="firstline">
                <td>id</td>
                <td>退货单编号</td>
                <td>退货单金额</td>
                <td>退货单时间</td>
                <td>操作</td>
            </tr>

            <#if returnlines??>
                <#list returnlines as returnline>
                    <tr>
                        <td>${returnline.id}</td>
                        <td><a href="" onclick="toreturndetails(${returnline.rlId});return false">${returnline.rlId}</a></td>
                        <td>${returnline.amount}</td>
                        <td>${returnline.createtime?string('yyyy-MM-dd')}</td>
                        <td><a href="" onclick="deletereturn(${returnline.id});return false">删除</a></td>
                    </tr>
                </#list>
            </#if>

        </table>



        <#--        -------页码按钮组-------->
        <div class="pagebuttons">
            <span>第${page.pageNum}页/共${page.pages}页&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <#if page.pageNum lte 1>
                <span>首页&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <span>上一页&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <#else >
                <a href="" onclick="toPagereturnline(1);return false">首页&nbsp;&nbsp;&nbsp;&nbsp;</a>
                <a href="" onclick="toPagereturnline(${page.pageNum-1});return false">上一页&nbsp;&nbsp;&nbsp;&nbsp;</a>
            </#if>

            <#if page.pageNum gte page.pages>
                <span>下一页&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <span>尾页&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <#else >
                <a href="" onclick="toPagereturnline(${page.pageNum+1});return false" >下一页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
                <a href="" onclick="toPagereturnline(${page.pages});return false">尾页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
            </#if>
        </div>
    </div>
</div>