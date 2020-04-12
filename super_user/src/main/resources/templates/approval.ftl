<div class="contenttitile">
    <h3>审批</h3>
</div>

<div class="contentbody">

    <#-- ------展示表-------   -->
    <div class="tablepart" id="tablepart">

        <table class="productstable">
            <tr class="firstline">
                <td>id</td>
                <td>进货单编号</td>
                <td>金额</td>
                <td>创建时间</td>
                <td>状态</td>
            </tr>

            <#if purchasinglines??>
                <#list purchasinglines as purchasingline>
                    <tr>
                        <td><span>${purchasingline.id}</span></td>
                        <td><a href="" onclick="topurchasingdetail(${purchasingline.plId});return false">${(purchasingline.plId)!''}</a></td>
                        <td><span>${purchasingline.amount}</span></td>
                        <td><span>${purchasingline.createtime?string('yyyy-MM-dd')}</span></td>
                        <td><span>${purchasingline.status}</span></td>
                    </tr>
                </#list>
            </#if>
        </table>



        <#---------页码按钮组-------->
        <div class="pagebuttons">
            <span>第${page.pageNum}页/共${page.pages}页&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <#if page.pageNum lte 1>
                <span>首页&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <span>上一页&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <#else >
                <a href="" onclick="topurchasinglinepage(1);return false">首页&nbsp;&nbsp;&nbsp;&nbsp;</a>
                <a href="" onclick="topurchasinglinepage(${page.pageNum-1});return false">上一页&nbsp;&nbsp;&nbsp;&nbsp;</a>
            </#if>

            <#if page.pageNum gte page.pages>
                <span>下一页&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <span>尾页&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <#else >
                <a href="" onclick="topurchasinglinepage(${page.pageNum+1});return false" >下一页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
                <a href="" onclick="topurchasinglinepage(${page.pages});return false">尾页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
            </#if>
        </div>
    </div>
</div>
