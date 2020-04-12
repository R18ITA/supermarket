// 商品相关页面初始化
function loadProductsPart() {
    $.ajax({
        type: "post",
        url: "/Guider/toproductsPart",
        success: function (data) {
           $("#contentpart").empty();
           $("#contentpart").append(data);
        }
    });
}

//销售相关页面初始化
function loadSalePart() {
    $.ajax({
        type: "post",
        url: "/Guider/tosalePart",
        success: function (data) {
            $("#contentpart").empty();
            $("#contentpart").append(data);
        }
    });
}

//库存管理
function loadStorePart() {
    $.ajax({
        type: "post",
        url: "/Guider/tostorepart",
        success: function (data) {
            $("#contentpart").empty();
            $("#contentpart").append(data);
        }
    });
}

//进货管理
function loadpurchasing() {
    $.ajax({
        type: "post",
        url: "/Guider/toPurchasingPart",
        success: function (data) {
            $("#contentpart").empty();
            $("#contentpart").append(data);
        }
    });
}