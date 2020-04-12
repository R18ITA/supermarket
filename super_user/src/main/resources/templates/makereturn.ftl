<div class="contenttitile">
    <h3>退单</h3>
</div>

<div class="contentbody">
    <#-- -----搜索栏-----   -->
    <div class="searchpart">
        <#if searchcondition??>
            <input type="text" id="searchcondition" value="${searchcondition}">
        <#else >
            <input type="text" id="searchcondition">
        </#if>
        <button class="btn-info"  onclick="searchofmakereturn()">查询</button>
    </div>

    <#-- ------展示表-------   -->
    <div class="tablepart" id="tablepart">

        <table class="productstable">
            <tr class="firstline">
                <td>id</td>
                <td>商品编号</td>
                <td>商品名称</td>
                <td>商品种类</td>
                <td>库存</td>
                <td>加入退单列表</td>
            </tr>

            <#if products??>
                <#list products as product>
                    <tr>
                        <td><span>${product.id}</span></td>
                        <td><span>${product.pId}</span></td>
                        <td><span>${product.pName}</span></td>
                        <#list kinds as kind>
                            <#if kind.kId==product.kId>
                                <td><span>${kind.kName}</span></td>
                                <#break >
                            </#if>
                        </#list>
                        <td>${product.store}</td>
                        <td><button class="btn-link" onclick="checkstoreofreturn(${product.id})">
                                <span class="glyphicon glyphicon-plus-sign"></span>
                            </button>
                        </td>

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
                <a href="" onclick="toPagemakereturn(1);return false">首页&nbsp;&nbsp;&nbsp;&nbsp;</a>
                <a href="" onclick="toPagemakereturn(${page.pageNum-1});return false">上一页&nbsp;&nbsp;&nbsp;&nbsp;</a>
            </#if>

            <#if page.pageNum gte page.pages>
                <span>下一页&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <span>尾页&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <#else >
                <a href="" onclick="toPagemakereturn(${page.pageNum+1});return false" >下一页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
                <a href="" onclick="toPagemakereturn(${page.pages});return false">尾页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
            </#if>
        </div>
    </div>
</div>

<div id="temporaryreturn">

</div>
