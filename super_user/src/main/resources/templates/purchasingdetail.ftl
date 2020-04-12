<div class="contenttitile">
    <h3>${plId}进货单详情</h3>
</div>

<div class="contentbody">

    <#-- ------展示表-------   -->
    <div class="tablepart" id="tablepart">

        <table class="productstable">
            <tr class="firstline">
                <td>id</td>
                <td>进货单编号</td>
                <td>商品编号</td>
                <td>进货数量</td>
                <td>供应商编号</td>
                <td>种类编号</td>
                <td>单价</td>
                <td>单位</td>
                <td>金额</td>
            </tr>

            <#if purchasingdetails??>
                <#list purchasingdetails as purchasingdetail>
                    <tr>
                        <td><span>${purchasingdetail.id}</span></td>
                        <td><span>${purchasingdetail.pdId}</span></td>
                        <td><span>${purchasingdetail.pId}</span></td>
                        <td><span>${purchasingdetail.pdNum}</span></td>
                        <td><span>${purchasingdetail.sId}</span></td>
                        <td><span>${(purchasingdetail.kId)!''}</span></td>
                        <td><span>${purchasingdetail.purchasingprice}</span></td>
                        <td><span>${purchasingdetail.pUnit}</span></td>
                        <td><span>${purchasingdetail.amount}</span></td>
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
                <a href="" onclick="topurchasingdetailpage(${plId},1);return false">首页&nbsp;&nbsp;&nbsp;&nbsp;</a>
                <a href="" onclick="topurchasingdetailpage(${plId},${page.pageNum-1});return false">上一页&nbsp;&nbsp;&nbsp;&nbsp;</a>
            </#if>

            <#if page.pageNum gte page.pages>
                <span>下一页&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <span>尾页&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <#else >
                <a href="" onclick="topurchasingdetailpage(${plId},${page.pageNum+1});return false" >下一页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
                <a href="" onclick="topurchasingdetailpage(${plId},${page.pages});return false">尾页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
            </#if>
        </div>
    </div>
</div>

