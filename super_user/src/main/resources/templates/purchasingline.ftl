<div class="contenttitile">
    <h3>进货单</h3>
</div>

<div class="contentbody">
    <#-- -----搜索栏-----   -->
    <div class="searchpart">
        <#if searchcondition??>
            <input type="text" id="searchcondition" value="${searchcondition}">
        <#else >
            <input type="text" id="searchcondition">
        </#if>
        <button class="btn-info"  onclick="searchpurchasingline()">查询</button>
    </div>

    <#-- ------展示表-------   -->
    <div class="tablepart" id="tablepart">

        <table class="orderlinetable">
            <tr class="firstline">
                <td>id</td>
                <td>进货单编号</td>
                <td>进货单金额</td>
                <td>时间</td>
                <td>状态</td>
            </tr>

            <#if purchasinglines??>
                <#list purchasinglines as purchasingline>
                    <tr>
                        <td>${purchasingline.id}</td>
                        <td><a href="" onclick=";return false">${purchasingline.plId}</a></td>
                        <td>${purchasingline.amount}</td>
                        <td>${purchasingline.createtime?string('yyyy-MM-dd')}</td>
                        <td>${purchasingline.status}</td>
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
                <a href="" onclick="toPagepurchasingline(1);return false">首页&nbsp;&nbsp;&nbsp;&nbsp;</a>
                <a href="" onclick="toPagepurchasingline(${page.pageNum-1});return false">上一页&nbsp;&nbsp;&nbsp;&nbsp;</a>
            </#if>

            <#if page.pageNum gte page.pages>
                <span>下一页&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <span>尾页&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <#else >
                <a href="" onclick="toPagepurchasingline(${page.pageNum+1});return false" >下一页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
                <a href="" onclick="toPagepurchasingline(${page.pages});return false">尾页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
            </#if>
        </div>
    </div>
</div>