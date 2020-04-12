<div class="contenttitile">
    <h3>退货单详情</h3>
</div>

<div class="contentbody">
    <#-- ------展示表-------   -->
    <div class="tablepart" id="tablepart">

        <table class="returndetailstable">
            <tr class="firstline">
                <td>id</td>
                <td>订单编号</td>
                <td>商品</td>
                <td>数量</td>
                <td>供应商</td>
            </tr>

            <#if returndetails??>
                <#list returndetails as returndetail>
                    <tr>
                        <td>${returndetail.id}</td>
                        <td>${returndetail.rdId}</td>
                        <#list products as product>
                            <#if product.pId==returndetail.pId>
                                <td>${product.pName}</td>
                            </#if>
                        </#list>
                        <td>${returndetail.rdNum}</td>

                        <#list suppliers as supplier>
                            <#if supplier.sId = returndetail.sId>
                                <td>${supplier.sName}</td>
                            </#if>
                        </#list>
                    </tr>
                </#list>
            </#if>
        </table>
    </div>