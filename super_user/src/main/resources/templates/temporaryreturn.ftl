<div id="temporarytitle">
    <h2>退货列表</h2>
</div>

<div id="temporarytable">
    <table class="temporarytable">
        <tr class="firstline">
            <td>商品</td>
            <td>数量</td>
            <td>金额</td>
        </tr>

        <#if temporaryreturn??>
            <#list temporaryreturn as treturn>
                <tr>
                    <td>${treturn.pName}</td>
                    <td>1</td>
                    <#if treturn.discount==0>
                        <td>${treturn.saleprice}</td>
                    <#else >
                        <td>${(treturn.saleprice*treturn.discount)/10}</td>
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
        <button class="btn-success" onclick="addtoreturn()">退货</button>
    </div>
</div>