<div class="contenttitile">
    <h2>商品管理</h2>
</div>

<div class="contentbody">

    <#-- -----搜索栏-----   -->
    <div class="searchpart">

        <select id="selectkind" onchange="showBykind()">
            <#if showkind=="全部">
                <option value="全部" selected>全部</option>
            <#else >
                <option value="全部">全部</option>
            </#if>

            <#if kinds??>
                <#list kinds as kind>
                    <#if showkind==kind.kId>
                        <option value="${kind.kId}" selected>${kind.kName}</option>
                    <#else >
                        <option value="${kind.kId}">${kind.kName}</option>
                    </#if>
                </#list>
            </#if>
        </select>

        <#if searchcondition??>
            <input type="text" id="searchcondition" value="${searchcondition}">
        <#else >
            <input type="text" id="searchcondition">
        </#if>
        <button class="btn-info"  onclick="searchproduct()">查询</button>
        <button class="btn-success" data-toggle="modal" data-target="#addproductmodal">增加商品</button>
    </div>


    <#-- ------展示表-------   -->
    <div class="tablepart" id="tablepart">
        <table class="productstable">
            <tr class="firstline">
                <td>id</td>
                <td>商品编号</td>
                <td>商品名称</td>
                <td>商品种类</td>
                <td>进货价</td>
                <td>销售价</td>
                <td>单位</td>
                <td>供应商</td>
                <td>库存</td>
                <td>折扣</td>
                <td>操作</td>
            </tr>

            <#if products??>
                <#list products as product>
                    <tr>
                    <td><span>${product.id}</span></td>
                    <td><input type="text" value="${product.pId}" id="${product.id}pid"></td>
                    <td><input type="text" value="${product.pName}" id="${product.id}pname"></td>
                    <td><select id="${product.id}kid">
                        <#if kinds??>
                            <#list kinds as kind>
                                <#if kind.kId==product.kId>
                                    <option value="${kind.kId}" selected>${kind.kName}</option>
                                <#else >
                                    <option value="${kind.kId}">${kind.kName}</option>
                                </#if>
                            </#list>
                        </#if>
                    </select>
                    </td>
                    <td><input type="text" value="${product.purchasingprice}" id="${product.id}purchasingprice"></td>
                    <td><input type="text" value="${product.saleprice}" id="${product.id}saleprice"></td>
                    <td><input type="text" value="${product.unit}" id="${product.id}unit"></td>
                        <td><select id="${product.id}sid">
                                <#if suppliers??>
                                    <#list suppliers as supplier>
                                        <#if supplier.sId==product.sId>
                                            <option value="${supplier.sId}" selected>${supplier.sName}</option>
                                        <#else >
                                            <option value="${supplier.sId}">${supplier.sName}</option>
                                        </#if>
                                    </#list>
                                </#if>
                            </select>
                        </td>
                    <td><input type="text" value="${product.store}" id="${product.id}store"></td>
                    <td><input type="text" value="${product.discount}" id="${product.id}discount"></td>
                    <td>
                        <a href="" onclick="deleteproduct(${product.id});return false">删除</a>
                        <a href="" onclick="updateproduct(${product.id});return false">修改</a>
                    </td>
                    </tr>
                </#list>
            </#if>
        </table>

        <#--   -------页码按钮组------     -->
        <div class="pagebuttons">
            <span>第${page.pageNum}页/共${page.pages}页&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <#if page.pageNum lte 1>
                <span>首页&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <span>上一页&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <#else >
                <a href="" onclick="toproductPage(1);return false">首页&nbsp;&nbsp;&nbsp;&nbsp;</a>
                <a href="" onclick="toproductPage(${page.pageNum-1});return false">上一页&nbsp;&nbsp;&nbsp;&nbsp;</a>
            </#if>

            <#if page.pageNum gte page.pages>
                <span>下一页&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <span>尾页&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <#else >
                <a href="" onclick="toproductPage(${page.pageNum+1});return false" >下一页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
                <a href="" onclick="toproductPage(${page.pages});return false">尾页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
            </#if>
        </div>

    </div>
</div>


<!-- 增加商品模态框（Modal） -->
<div class="modal fade" id="addproductmodal" tabindex="-1" role="dialog" aria-labelledby="addproductmodal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">增加商品</h4>
            </div>
            <div class="modal-body">
                <p>商品编号：<input type="text" id="newpid"></p>
                <p>商品名称：<input type="text" id="newpname"></p>
                <p>商品种类：
                    <select id="newkid">
                        <#if kinds??>
                            <#list kinds as kind>
                                <option value="${kind.kId}">${kind.kName}</option>
                            </#list>
                        </#if>
                    </select>
                </p>
                <p>进货价格：<input type="text" id="newpurchasingprice"></p>
                <p>销售价格：<input type="text" id="newsaleprice"></p>
                <p>单位：<input type="text" id="newunit"></p>
                <p>供应商：
                    <select id="newsid">
                        <#if suppliers??>
                            <#list suppliers as supplier>
                                <option value="${supplier.sId}">${supplier.sName}</option>
                            </#list>
                        </#if>
                    </select>
                </p>
                <p>库存：<input type="text" id="newstore"></p>
                <p>折扣：<input type="text" id="newdiscount"></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn" onclick="addproducts()">确认</button>
            </div>
        </div>
    </div>
</div>

