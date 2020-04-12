
// ------------1.商品种类-------------
//商品种类页面初始化
function loadkind() {
    $.ajax({
        type: "post",
        url: "/Guider/toproductkind",
        success: function (data) {
            $("#productsPartcontent").empty();
            $("#productsPartcontent").append(data);
        }
    });
}

//换页
function tokindPage(pageNum) {
    var searchcondition = document.getElementById('searchcondition').valueOf().value;
        $.ajax({
            url:"/kindManagement/searchKind",
            type:"POST",
            data:{
                "searchcondition":searchcondition,
                "pageNum": pageNum
            },
            success:function(data){
                $("#productsPartcontent").empty();
                $("#productsPartcontent").append(data);
            }
        });
    // }
}

//修改商品种类
function updateKind(id) {
    var kid = document.getElementById(id+'kid').valueOf().value;
    var kname = document.getElementById(id+'kname').valueOf().value;
    var args={
        "id": id,
        "kId":kid,
        "kName":kname
    }
    $.ajax({
        url:"/kindManagement/updateKind",
        type:"POST",
        data:JSON.stringify(args),
        contentType:"application/json",
        dataType: "json",
        success:function(data){
            alert(data.updateresult)
            loadkind();
        }
    });
}

//增加商品种类
function addkind() {
    var newkid = document.getElementById('newkid').valueOf().value;
    var newkname = document.getElementById("newkname").valueOf().value;
    var args={
        "kId":newkid,
        "kName":newkname
    }
    $.ajax({
        url:"/kindManagement/addKind",
        type:"POST",
        data:JSON.stringify(args),
        contentType:"application/json",
        dataType: "json",
        success:function(data){
            alert(data.addresult)
            if(data.addresult=="添加成功"||data.addresult=="添加失败")
            {
                $('#addkindmodal').modal('hide')
                $(".modal-backdrop").remove();
                loadkind();
            }

        }
    });

}

//删除商品种类
function deletekind(id) {
    $.ajax({
        url:"/kindManagement/deleteKind",
        type:"POST",
        data:{
          "id":id
        },
        dataType: "json",
        success:function(data){
            alert(data.deleteresult)
            if(data.deleteresult=="删除成功")
            {
                loadkind();
            }

        }
    });

}

//查询商品种类
function searchkind() {
    var searchcondition = document.getElementById('searchcondition').valueOf().value;
    $.ajax({
        url:"/kindManagement/searchKind",
        type:"POST",
        data:{
            "searchcondition":searchcondition
        },
        success:function(data){
            $("#productsPartcontent").empty();
            $("#productsPartcontent").append(data);
        }
    });

}


// ------------2.商品----------------
// 商品页面初始化
function loadproducts() {
    $.ajax({
        type: "post",
        url: "/Guider/toproducts",
        success: function (data) {
            $("#productsPartcontent").empty();
            $("#productsPartcontent").append(data);
        }
    });
}

// 商品换页
function toproductPage(pageNum) {
    var showkind = document.getElementById('selectkind').valueOf().value;
    var searchcondition = document.getElementById('searchcondition').valueOf().value;

    if(searchcondition=="") {
        $.ajax({
            type: "post",
            url: "/Guider/toproducts",
            data: {
                "pageNum": pageNum,
                "showkind":showkind
            },
            success: function (data) {
                $("#productsPartcontent").empty();
                $("#productsPartcontent").append(data);
            }
        });
    }
    else {
        $.ajax({
            type: "post",
            url: "/productsManagement/searchProducts",
            data: {
                "pageNum": pageNum,
                "searchcondition": searchcondition
            },
            success: function (data) {
                $("#productsPartcontent").empty();
                $("#productsPartcontent").append(data);
            }
        });
    }

}

//按种类显示商品
function showBykind(){
    var showkind = document.getElementById('selectkind').valueOf().value;
    $.ajax({
        type: "post",
        url: "/Guider/toproducts",
        data: {
            "showkind": showkind
        },
        success: function (data) {
            $("#productsPartcontent").empty();
            $("#productsPartcontent").append(data);
        }
    });
}

//修改商品
function updateproduct(id) {
    var pid = document.getElementById(id+"pid").valueOf().value;
    var pname = document.getElementById(id+"pname").valueOf().value;
    var kid = document.getElementById(id+"kid").valueOf().value;
    var purchasingprice = document.getElementById(id+"purchasingprice").valueOf().value;
    var saleprice = document.getElementById(id+"saleprice").valueOf().value;
    var unit = document.getElementById(id+"unit").valueOf().value;
    var sid = document.getElementById(id+"sid").valueOf().value;
    var store = document.getElementById(id+"store").valueOf().value;
    var discount = document.getElementById(id+"discount").valueOf().value;

    if(purchasingprice=="")
        purchasingprice=0;
    if(saleprice=="")
        saleprice=0;
    if(store=="")
        store=0;
    if(discount=="")
        discount=0;
    var args={
        "id": id,
        "pId":pid,
        "pName":pname,
        "kId":kid,
        "purchasingprice":purchasingprice,
        "saleprice":saleprice,
        "unit":unit,
        "sId":sid,
        "store":store,
        "discount":discount
    }

    $.ajax({
        url:"/productsManagement/updateProducts",
        type:"POST",
        data:JSON.stringify(args),
        contentType:"application/json",
        dataType: "json",
        success:function(data){
            alert(data.updateresult)
            loadproducts();
        }
    });
}

//增加商品
function addproducts() {
    var pid = document.getElementById("newpid").valueOf().value;
    var pname = document.getElementById("newpname").valueOf().value;
    var kid = document.getElementById("newkid").valueOf().value;
    var purchasingprice = document.getElementById("newpurchasingprice").valueOf().value;
    var saleprice = document.getElementById("newsaleprice").valueOf().value;
    var unit = document.getElementById("newunit").valueOf().value;
    var sid = document.getElementById("newsid").valueOf().value;
    var store = document.getElementById("newstore").valueOf().value;
    var discount = document.getElementById("newdiscount").valueOf().value;

    if(purchasingprice=="")
        purchasingprice=0;
    if(saleprice=="")
        saleprice=0;
    if(store=="")
        store=0;
    if(discount=="")
        discount=0;
    var args={
        "pId":pid,
        "pName":pname,
        "kId":kid,
        "purchasingprice":purchasingprice,
        "saleprice":saleprice,
        "unit":unit,
        "sId":sid,
        "store":store,
        "discount":discount
    }

    $.ajax({
        url:"/productsManagement/addProducts",
        type:"POST",
        data:JSON.stringify(args),
        contentType:"application/json",
        dataType: "json",
        success:function(data){
            alert(data.addresult)
            if(data.addresult=="增加成功"||data.addresult=="增加失败")
            {
                $('#addproductmodal').modal('hide')
                $(".modal-backdrop").remove();
                loadproducts();
            }

        }
    });
}

//删除商品
function deleteproduct(id) {
    $.ajax({
        url:"/productsManagement/deleteProduct",
        type:"POST",
        data:{
            "id":id
        },
        dataType: "json",
        success:function(data){
            alert(data.deleteresult)
                loadproducts();
        }
    });
}

//条件查询商品
function searchproduct() {
    var searchcondition = document.getElementById('searchcondition').valueOf().value;
    $.ajax({
        url:"/productsManagement/searchProducts",
        type:"POST",
        data:{
            "searchcondition":searchcondition
        },
        success:function(data){
            $("#productsPartcontent").empty();
            $("#productsPartcontent").append(data);
        }
    });
}


// ----------------3.供应商---------------
//供应商页面初始化
function loadsupplier() {
    $.ajax({
        type: "post",
        url: "/Guider/tosuppliers",
        success: function (data) {
            $("#productsPartcontent").empty();
            $("#productsPartcontent").append(data);
        }
    });
}

//换页
function tosupplierPage(pageNum) {
    var searchcondition = document.getElementById('searchcondition').valueOf().value;
    $.ajax({
        url:"/suppliersManagement/searchSupplier",
        type:"POST",
        data:{
            "searchcondition":searchcondition,
            "pageNum": pageNum
        },
        success:function(data){
            $("#productsPartcontent").empty();
            $("#productsPartcontent").append(data);
        }
    });
}

//修改供应商
function updatesupplier(id) {
    var sid = document.getElementById(id+'sid').valueOf().value;
    var sname = document.getElementById(id+'sname').valueOf().value;
    var sphone = document.getElementById(id+'sphone').valueOf().value;
    var semail = document.getElementById(id+'semail').valueOf().value;
    var saddress = document.getElementById(id+'saddress').valueOf().value;
    var leader = document.getElementById(id+'leader').valueOf().value;
    var leaderphone = document.getElementById(id+'leaderphone').valueOf().value;

    var args={
        "id":id,
        "sId":sid,
        "sName":sname,
        "sPhone":sphone,
        "sEmail":semail,
        "sAddress":saddress,
        "leader":leader,
        "leaderphone":leaderphone
    }

    $.ajax({
        url:"/suppliersManagement/updateSupplier",
        type:"POST",
        data:JSON.stringify(args),
        contentType:"application/json",
        dataType: "json",
        success:function(data){
            alert(data.updateresult)
            loadsupplier();
        }
    });
}

//增加供应商
function addsupplier() {
    var sid = document.getElementById('newsid').valueOf().value;
    var sname = document.getElementById('newsname').valueOf().value;
    var sphone = document.getElementById('newsphone').valueOf().value;
    var semail = document.getElementById('newsemail').valueOf().value;
    var saddress = document.getElementById('newsaddress').valueOf().value;
    var leader = document.getElementById('newleader').valueOf().value;
    var leaderphone = document.getElementById('newleaderphone').valueOf().value;

    var args={
        "sId":sid,
        "sName":sname,
        "sPhone":sphone,
        "sEmail":semail,
        "sAddress":saddress,
        "leader":leader,
        "leaderphone":leaderphone
    }

    $.ajax({
        url:"/suppliersManagement/addSupplier",
        type:"POST",
        data:JSON.stringify(args),
        contentType:"application/json",
        dataType: "json",
        success:function(data){
            alert(data.addresult)
            if(data.addresult=="增加成功"||data.addresult=="增加失败")
            {
                $('#addsuppliermodal').modal('hide')
                $(".modal-backdrop").remove();
                loadsupplier();
            }
        }
    });
}

//删除供应商
function deletesupplier(id){
    $.ajax({
        url:"/suppliersManagement/deleteSupplier",
        type:"POST",
        data:{
            "id":id
        },
        dataType: "json",
        success:function(data){
            alert(data.deleteresult)
            loadsupplier();
        }
    });
}

//查询供应商
function searchsupplier(){
    var searchcondition = document.getElementById('searchcondition').valueOf().value;
    $.ajax({
        url:"/suppliersManagement/searchSupplier",
        type:"POST",
        data:{
            "searchcondition":searchcondition
        },
        success:function(data){
            $("#productsPartcontent").empty();
            $("#productsPartcontent").append(data);
        }
    });
}