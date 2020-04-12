// ------下单---------
//加载下单页面
function loadmakereturn() {
    $.ajax({
        type: "post",
        url: "/Guider/tomakereturn",
        success: function (data) {
            $("#storePartcontent").empty();
            $("#storePartcontent").append(data);
            loadtemporaryreturn();
        }
    });
}

//临时退单页面
function loadtemporaryreturn() {
    $.ajax({
        type: "post",
        url: "/Guider/totemporaryreturn",
        success: function (data) {
            $("#temporaryreturn").empty();
            $("#temporaryreturn").append(data);
        }
    });
}

//换页
function toPagemakereturn(pageNum) {
    var searchcondition = document.getElementById("searchcondition").valueOf().value;
    $.ajax({
        type: "post",
        url: "/Guider/tomakereturn",
        data:{
            "searchcondition":searchcondition,
            "pageNum":pageNum
        },
        success: function (data) {
            $("#storePartcontent").empty();
            $("#storePartcontent").append(data);
            loadtemporaryreturn();
        }
    });
}

//条件查询
function searchofmakereturn() {
    var searchcondition = document.getElementById("searchcondition").valueOf().value;
    $.ajax({
        type: "post",
        url: "/Guider/tomakereturn",
        data:{
            "searchcondition":searchcondition
        },
        success: function (data) {
            $("#storePartcontent").empty();
            $("#storePartcontent").append(data);
            loadtemporaryreturn();
        }
    });
}

//加入临时退货单前检验商品库存是否足够
function checkstoreofreturn(id) {
    $.ajax({
        type: "post",
        url: "/ReturnManagement/checkStoreofReturn",
        data:{
            "id":id
        },
        success: function (data) {
            if(data.checkresult =="商品足够")
            {
                addtotemporaryreturn(id);
            }
            else {
                alert(data.checkresult);
            }
        }
    });
}

//加入临时退货单
function addtotemporaryreturn(id) {
    $.ajax({
        type: "post",
        url: "/ReturnManagement/addtotemporaryreturn",
        data:{
            "id":id
        },
        success: function (data) {
            loadmakereturn();
            $("#temporaryreturn").empty();
            $("#temporaryreturn").append(data);
        }
    });
}

//生成退货单单
function addtoreturn() {
    $.ajax({
        type: "post",
        url: "/ReturnManagement/addReturn",
        dataType: "json",
        success: function (data) {
            alert(data.addresult);
            if(data.addresult=="退货单生成成功!"||data.addresult=="退货单生成失败!")
            {
                loadtemporaryreturn();
            }
        }
    });
}



// ----------退货单------------
//加载退货单页面
function loadreturnline() {
    $.ajax({
        type: "post",
        url: "/Guider/toreturnline",
        success: function (data) {
            $("#storePartcontent").empty();
            $("#storePartcontent").append(data);
        }
    });
}

//换页
function toPagereturnline(pageNum) {
    var searchcondition = document.getElementById("searchcondition").valueOf().value;
    $.ajax({
        type: "post",
        url: "/Guider/toreturnline",
        data:{
            "searchcondition":searchcondition,
            "pageNum":pageNum
        },
        success: function (data) {
            $("#storePartcontent").empty();
            $("#storePartcontent").append(data);
        }
    });
}

//条件查询
function searchreturnline() {
    var searchcondition = document.getElementById("searchcondition").valueOf().value;
    $.ajax({
        type: "post",
        url: "/Guider/toreturnline",
        data:{
            "searchcondition":searchcondition
        },
        success: function (data) {
            $("#storePartcontent").empty();
            $("#storePartcontent").append(data);
        }
    });
}

//查看退货单详情
function toreturndetails(id){
    $.ajax({
        type: "post",
        url: "/Guider/toreturndetails",
        data:{
            "id":id
        },
        success: function (data) {
            $("#storePartcontent").empty();
            $("#storePartcontent").append(data);
        }
    });
}


//删除订单
function deletereturn(id){
    $.ajax({
        type: "post",
        url: "/ReturnManagement/deleteReturn",
        data:{
            "id":id
        },
        success: function (data) {
            alert(data.deleteresult)
            loadreturnline();
        }
    });
}