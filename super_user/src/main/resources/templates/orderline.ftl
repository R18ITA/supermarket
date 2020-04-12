<div class="contenttitile">
    <h3>订单</h3>
</div>

<div class="contentbody">
    <#-- -----搜索栏-----   -->
    <div class="searchpart">
        <#if searchcondition??>
            <input type="text" id="searchcondition" value="${searchcondition}">
        <#else >
            <input type="text" id="searchcondition">
        </#if>
        <button class="btn-info"  onclick="searorderline()">查询</button>
    </div>

    <#-- ------展示表-------   -->
    <div class="tablepart" id="tablepart">

        <table class="orderlinetable">
            <tr class="firstline">
                <td>id</td>
                <td>订单编号</td>
                <td>订单金额</td>
                <td>订单时间</td>
                <td>操作</td>
            </tr>

            <#if orderlines??>
                <#list orderlines as orderline>
                    <tr>
                        <td>${orderline.id}</td>
                        <td><a href="" onclick="toorderdetails(${orderline.olId});return false">${orderline.olId}</a></td>
                        <td>${orderline.amount}</td>
                        <td>${orderline.createtime?string('yyyy-MM-dd')}</td>
                        <td><a href="" onclick="deleteorder(${orderline.id});return false">删除</a></td>
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
                <a href="" onclick="toPageorderline(1);return false">首页&nbsp;&nbsp;&nbsp;&nbsp;</a>
                <a href="" onclick="toPageorderline(${page.pageNum-1});return false">上一页&nbsp;&nbsp;&nbsp;&nbsp;</a>
            </#if>

            <#if page.pageNum gte page.pages>
                <span>下一页&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <span>尾页&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <#else >
                <a href="" onclick="toPageorderline(${page.pageNum+1});return false" >下一页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
                <a href="" onclick="toPageorderline(${page.pages});return false">尾页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
            </#if>
        </div>
    </div>
</div>