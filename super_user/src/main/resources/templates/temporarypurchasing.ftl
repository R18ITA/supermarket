<div id="temporarytitle">
    <h2>进货列表</h2>
</div>

<div id="temporarytable">
    <table class="temporarytable">
        <tr class="firstline">
            <td>商品</td>
            <td>数量</td>
            <td>金额</td>
        </tr>

        <#if tempurchasing??>
            <#list tempurchasing as tpurchasing>
                <tr>
                    <td>${tpurchasing.pName}</td>
                    <td>1</td>
                    <td>${tpurchasing.purchasingprice}</td>
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
        <button class="btn-success" onclick="addtopurchasing()">生成进货单</button>
    </div>
</div>