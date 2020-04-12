//进货单换页
function topurchasinglinepage(pageNum) {
    $.ajax({
        typpe:"post",
        url:"/getPurchasingline",
        data:{
            "pageNum":pageNum
        },
        success:function (data) {//把查询结果加载进页面中
            $('#contentpart').empty();
            $('#contentpart').append(data);
        }
    })
}
//进入进货单详情
function topurchasingdetail(plId) {
    var pageNum=1;//进去之后显示第一页
    $.ajax({
        type:"post",
        url:"/getPurchasingdetail",
        data:{
            "plId":plId,
            "pageNum":pageNum
        },
        success:function (data) {//把查询结果加载进页面中
            $('#contentpart').empty();
            $('#contentpart').append(data)
        }
    })
}
//进货单详情信息换页
function topurchasingdetailpage(plId,pageNum) {
    $.ajax({
        type:"post",
        url:"/getPurchasingdetail",
        data:{
            "plId":plId,
            "pageNum":pageNum
        },
        success:function (date) {//加载页面中
            $('#contentpart').empty();
            $('#contentpart').append(data)
        }
    })
}