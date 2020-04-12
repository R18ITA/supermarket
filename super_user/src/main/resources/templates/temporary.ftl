<div id="temporarytitle">
    <h2>点单列表</h2>
</div>

<div id="temporarytable">
    <table class="temporarytable">
        <tr class="firstline">
            <td>商品</td>
            <td>单价</td>
            <td>数量</td>
            <td>金额</td>
        </tr>

        <#if temporaryorder??>
            <#list temporaryorder as torder>
                <tr>
                    <td>${torder.pName}</td>
                    <td>${torder.saleprice}</td>
                    <td>1</td>
                    <#if torder.discount==0>
                        <td>${torder.saleprice}</td>
                    <#else >
                        <td>${(torder.saleprice*torder.discount)/10}</td>
                    </#if>

                </tr>
            </#list>
        </#if>
    </table>

    <div class="sumandpay">
        <#if sumamount??>
            <p>总计${sumamount}元</p>
        <#else >
            <p>总计0元</p>
        </#if>

        <button class="btn-success" onclick="addtoorder()">结算</button>
    </div>
</div>