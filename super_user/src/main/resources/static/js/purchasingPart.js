// ------进货---------
//加载进货单页面
function loadmakepurchasing() {
    $.ajax({
        type: "post",
        url: "/Guider/tomakepurchasing",
        success: function (data) {
            $("#purchasingPartcontent").empty();
            $("#purchasingPartcontent").append(data);
            loadtemporarypurchasing();
        }
    });
}

//临时进货单页面
function loadtemporarypurchasing() {
    $.ajax({
        type: "post",
        url: "/Guider/totemporarypurchasing",
        success: function (data) {
            $("#temporarypurchasing").empty();
            $("#temporarypurchasing").append(data);
        }
    });
}

//换页
function toPagemakepurchasing(pageNum) {
    var searchcondition = document.getElementById("searchcondition").valueOf().value;
    $.ajax({
        type: "post",
        url: "/Guider/tomakepurchasing",
        data:{
            "searchcondition":searchcondition,
            "pageNum":pageNum
        },
        success: function (data) {
            $("#purchasingPartcontent").empty();
            $("#purchasingPartcontent").append(data);
            loadtemporarypurchasing();
        }
    });
}

//条件查询
function searchofmakepurchasing() {
    var searchcondition = document.getElementById("searchcondition").valueOf().value;
    $.ajax({
        type: "post",
        url: "/Guider/tomakepurchasing",
        data:{
            "searchcondition":searchcondition
        },
        success: function (data) {
            $("#purchasingPartcontent").empty();
            $("#purchasingPartcontent").append(data);
            loadtemporarypurchasing();
        }
    });
}

//加入临时进货单
function addtotemporarypurchasing(id) {
    $.ajax({
        type: "post",
        url: "/addtotempurchasing",
        data:{
            "id":id
        },
        success: function (data) {
            loadmakeorder();
            $("#temporarypurchasing").empty();
            $("#temporarypurchasing").append(data);

        }
    });
}


//生成进货单
function addtopurchasing() {
    $.ajax({
        type: "post",
        url: "/addtoPurchasing",
        dataType: "json",
        success: function (data) {
            alert(data.addresult);
            if(data.addresult=="生成订货单成功!"||data.addresult=="生成订货单失败!")
            {
                loadtemporarypurchasing();
            }
        }
    });
}



// -------查看进货单----------
//加载进货单页面
function loadpurchasingline() {
    $.ajax({
        type: "post",
        url: "/Guider/topurchasingline",
        success: function (data) {
            $("#purchasingPartcontent").empty();
            $("#purchasingPartcontent").append(data);
        }
    });
}

//换页
function toPagepurchasingline(pageNum) {
    var searchcondition = document.getElementById("searchcondition").valueOf().value;
    $.ajax({
        type: "post",
        url: "/Guider/topurchasingline",
        data:{
            "searchcondition":searchcondition,
            "pageNum":pageNum
        },
        success: function (data) {
            $("#purchasingPartcontent").empty();
            $("#purchasingPartcontent").append(data);
        }
    });
}

//条件查询
function searchpurchasingline() {
    var searchcondition = document.getElementById("searchcondition").valueOf().value;
    $.ajax({
        type: "post",
        url: "/Guider/topurchasingline",
        data:{
            "searchcondition":searchcondition
        },
        success: function (data) {
            $("#purchasingPartcontent").empty();
            $("#purchasingPartcontent").append(data);
        }
    });
}