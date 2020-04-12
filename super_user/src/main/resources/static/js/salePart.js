// ------下单---------
//加载下单页面
function loadmakeorder() {
    $.ajax({
        type: "post",
        url: "/Guider/tomakeOrder",
        success: function (data) {
            $("#salePartcontent").empty();
            $("#salePartcontent").append(data);
            loadtemporary();
        }
    });
}

//换页
function toPagemakeorder(pageNum) {
    var searchcondition = document.getElementById("searchcondition").valueOf().value;
    $.ajax({
        type: "post",
        url: "/Guider/tomakeOrder",
        data:{
            "searchcondition":searchcondition,
            "pageNum":pageNum
        },
        success: function (data) {
            $("#salePartcontent").empty();
            $("#salePartcontent").append(data);
            loadtemporary();
        }
    });
}

//条件查询
function searchofmakeorder() {
    var searchcondition = document.getElementById("searchcondition").valueOf().value;
    $.ajax({
        type: "post",
        url: "/Guider/tomakeOrder",
        data:{
            "searchcondition":searchcondition
        },
        success: function (data) {
            $("#salePartcontent").empty();
            $("#salePartcontent").append(data);
            loadtemporary();
        }
    });
}

//初始化加载临时订单页面
function loadtemporary(){
    $.ajax({
        type: "post",
        url: "/Guider/totemporary",
        success: function (data) {
            $("#temporaryorder").empty();
            $("#temporaryorder").append(data);
        }
    });
}

//加入临时订单前检验商品库存是否足够
function checkproductsstore(id) {
    $.ajax({
        type: "post",
        url: "/OrderManagement/checkStore",
        data:{
            "id":id
        },
        success: function (data) {
            if(data.checkresult=="商品足够")
            {
                addtotemporary(id);
            }
            else {
                alert(data.checkresult);
            }
        }
    });
}

//加入临时订单,传入加入临时订单的商品到后台
function addtotemporary(id) {
    $.ajax({
        type: "post",
        url: "/OrderManagement/addtotemporary",
        data:{
            "id":id
        },
        success: function (data) {
            loadmakeorder();
            $("#temporaryorder").empty();
            $("#temporaryorder").append(data);

        }
    });
}

//结算生成订单
function addtoorder() {
    $.ajax({
        type: "post",
        url: "/OrderManagement/addtoOrder",
        dataType: "json",
        success: function (data) {
            alert(data.addresult);
            if(data.addresult=="结算成功!"||data.addresult=="结算失败!")
            {
                loadtemporary();
            }
        }
    });
}



// -------查看订单----------
//加载订单页面
function loadorderline() {
    $.ajax({
        type: "post",
        url: "/Guider/toorderline",
        success: function (data) {
            $("#salePartcontent").empty();
            $("#salePartcontent").append(data);
        }
    });
}

//换页
function toPageorderline(pageNum) {
    var searchcondition = document.getElementById("searchcondition").valueOf().value;
    $.ajax({
        type: "post",
        url: "/Guider/toorderline",
        data:{
            "searchcondition":searchcondition,
            "pageNum":pageNum
        },
        success: function (data) {
            $("#salePartcontent").empty();
            $("#salePartcontent").append(data);
        }
    });
}

//条件查询
function searorderline() {
    var searchcondition = document.getElementById("searchcondition").valueOf().value;
    $.ajax({
        type: "post",
        url: "/Guider/toorderline",
        data:{
            "searchcondition":searchcondition
        },
        success: function (data) {
            $("#salePartcontent").empty();
            $("#salePartcontent").append(data);
        }
    });
}

//删除订单
function deleteorder(id){
    $.ajax({
        type: "post",
        url: "/OrderManagement/deleteOrder",
        data:{
            "id":id
        },
        success: function (data) {
            alert(data.deleteresult)
            loadorderline();
        }
    });
}

//查看订单详情
function toorderdetails(id){
    $.ajax({
        type: "post",
        url: "/Guider/toorderdetails",
        data:{
            "id":id
        },
        success: function (data) {
            $("#salePartcontent").empty();
            $("#salePartcontent").append(data);
        }
    });
}

