<div class="contenttitile">
    <h3>订单详情</h3>
</div>

<div class="contentbody">
    <#-- ------展示表-------   -->
    <div class="tablepart" id="tablepart">

        <table class="orderdetailstable">
            <tr class="firstline">
                <td>id</td>
                <td>订单编号</td>
                <td>商品编号</td>
                <td>数量</td>
                <td>折扣</td>
            </tr>

            <#if orderdetails??>
                <#list orderdetails as orderdetail>
                    <tr>
                        <td>${orderdetail.id}</td>
                        <td>${orderdetail.odId}</td>
                        <#list products as product>
                            <#if product.pId==orderdetail.pId>
                                <td>${product.pName}</td>
                            </#if>
                        </#list>

                        <td>${orderdetail.num}</td>
                        <td>${orderdetail.discount}</td>
                    </tr>
                </#list>
            </#if>
        </table>
</div>