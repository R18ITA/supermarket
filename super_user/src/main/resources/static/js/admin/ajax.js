//加载管理员页面
function  loadAdmin() {
    $.ajax({
        type:"post",
        url:"/getAdmin",//请求后台路径
        success:function (data) {
            $("#right_load").empty();//清空div内容
            $("#right_load").append(data);//往div加载页面
        }
    })
}
//加载普通用户页面
function  loadUser() {
    $.ajax({
        type:"post",
        url:"/getUser",//请求后台路径
        success:function (data) {
            $("#right_load").empty();//清空div内容
            $("#right_load").append(data);//往div加载页面
        }
    })
}
//加载角色页面
function  loadrole() {
    $.ajax({
        type:"post",
        url:"/getRoles",//请求后台路径
        success:function (data) {
            $("#right_load").empty();//清空div内容
            $("#right_load").append(data);//往div加载页面
        }
    })
}
//加载用户关联角色页面
function  loadUR() {
    $.ajax({
        type:"post",
        url:"/getUserrole",//请求后台路径
        success:function (data) {
            $("#right_load").empty();//清空div内容
            $("#right_load").append(data);//往div加载页面
        }
    })
}
//加载权限页面
function  loadpermission() {
    $.ajax({
        type:"post",
        url:"/getPermission",//请求后台路径
        success:function (data) {
            $("#right_load").empty();//清空div内容
            $("#right_load").append(data);//往div加载页面
        }
    })
}
//加载角色关联权限页面
function  loadRP() {
    $.ajax({
        type:"post",

        url:"/getRolePermission",//请求后台路径
        success:function (data) {
            $("#right_load").empty();//清空div内容
            $("#right_load").append(data);//往div加载页面
        }
    })
}

//添加用户
function adduser(usertype) {
    var createuId=document.getElementById('createuId').valueOf().value;
    var createuName=document.getElementById('createuName').valueOf().value;
    var createuPassword=document.getElementById('createuPassword').valueOf().value;
    var createuPhone=document.getElementById('createuPhone').valueOf().value;
    $.ajax({
        type:"post",
        url:"/createuser",
        dataType:"text",
        data:{
            "createuId":createuId,
            "createuName":createuName,
            "createuPassword":createuPassword,
            "createuPhone":createuPhone,
            "usertype":usertype
        },
        success:function (data) {
            alert(data);
            $('#addadmin').modal('hide');
            $(".modal-backdrop").remove();
            $("body").removeClass('modal-open');
            if (usertype==0){
                loadAdmin();
            }else {
                loadUser();
            }
        }
    });
}
//删除用户
function deleteuser(id,usertype,uId) {
    $.ajax({
        type:"post",
        url:"/deleteuser",
        dataType:"text",
        data:{
            "id":id,
            "uId":uId
        },
        success:function (data) {
            alert(data);
            if (usertype==0){
                loadAdmin();
            }else {
                loadUser();
            }
        }
    })
}
//修改用户
function updateuser(usertype,hid) {
    var Id=document.getElementsByName('id');
    var uId=document.getElementsByName('uId');
    var uName=document.getElementsByName('uName');
    var uPassword=document.getElementsByName('uPassword');
    var uPhone=document.getElementsByName('uPhone');
    var uid;
    var uname;
    var upassword;
    var uphone;
    alert("hid"+hid);
    for(var i=0;i<Id.length;i++){
        if(Id[i].valueOf().value==hid){
            uid=uId[i].valueOf().value;
            uname=uName[i].valueOf().value;
            upassword=uPassword[i].valueOf().value;
            uphone=uPhone[i].valueOf().value;
            break;
        }
    }
    $.ajax({
        type:"post",
        url:"/updateuser",
        dataType:"text",
        data:{
            "id":hid,
            "uId":uid,
            "uName":uname,
            "uPassword":upassword,
            "uPhone":uphone
        },
        success:function (data) {
            alert(data);
            if (usertype==0){
                loadAdmin();
            }else {
                loadUser();
            }
        }
    })
}
//模糊查询
function finduser(key,usertype) {
    $.ajax({
        type:"post",
        url:"/searchuser",
        dataType:"text",
        data:{
            "key":key
        },
        success:function (data) {
        alert(key+"查询成功！")

        }

    })
}

//添加角色
function addrole() {
    var rid=document.getElementById('createRid').valueOf().value;
    var rname=document.getElementById('createRnam').valueOf().value;
    $.ajax({
        type:"post",
        url:"/createrole",
        dataType:"text",
        data:{
            "createRid":rid,
            "createRname":rname
        },
        success:function (data) {
            alert(data);
            $('#addrole').modal('hide');
            $(".modal-backdrop").remove();
            $("body").removeClass('modal-open');
            loadrole();
        }
    })
}
//删除角色
function deleterole(id,rId){
    $.ajax({
        type:"post",
        url:"/deleterole",
        dataType:"text",
        data:{
            "id":id,
            "rId":rId
        },
        success:function (data) {
            alert(data);
            loadrole();
        }
    })
}
//修改角色
function updaterole(id){
    var Id=document.getElementsByName('id');
    var rid=document.getElementsByName('rid');
    var rname=document.getElementsByName('rname');
    var rId;
    var  rName;
    for(var i=0;i<rid.length;i++){
        if(Id[i].valueOf().value==id){
            rId=rid[i].valueOf().value;
            rName=rname[i].valueOf().value;
            break;
        }
    }
    $.ajax({
        type:"post",
        url:"/updaterole",
        dataType:"text",
        data:{
            "id":id,
            "rId":rId,
            "rName":rName
        },
        success:function (data) {
            alert(data);
            loadrole();
        }
    })
}

//添加权限
function addpermission() {
    var pid=document.getElementById('createPid').valueOf().value;
    var pname=document.getElementById('createPname').valueOf().value;
    var pdetail=document.getElementById('createPdetail').valueOf().value;
    $.ajax({
        type:"post",
        url:"/createpermission",
        dataType:"text",
        data:{
            "createPid":pid,
            "createPname":pname,
            "createPdetail":pdetail
        },
        success:function (data) {
            alert(data);
            $('#addpermission').modal('hide');
            $(".modal-backdrop").remove();
            $("body").removeClass('modal-open');
            loadpermission();
        }
    })
}
//删除权限
function deletepermission(id) {
    $.ajax({
        type:"post",
        url:"/deletepermission",
        dataType:"text",
        data:{
            "id":id
        },
        success:function (data) {
            alert(data);
            loadpermission();
        }
    })
}
//修改权限
function updatepermission(id) {
    var Id=document.getElementsByName('hid');
    var pid=document.getElementsByName('pId');
    var pname=document.getElementsByName('pName');
    var pid=document.getElementsByName('pDetail');
    var pId;
    var pName;
    var pDetail;
    for(var i=0;i<Id.length;i++){
        if(Id[i].valueOf().value==id){
            pId=pid[i].valueOf().value;
            pName=pname[i].valueOf().value;
            pDetail=pDetail.valueOf().value;
            break;
        }
    }
    $.ajax({
        type:"post",
        url:"/updatepermission",
        dataType:"text",
        data:{
            "hid":id,
            "pId":pId,
            "pName":pName,
            "pDetail":pDetail
        },
        success:function (data) {
            alert(data);
            loadpermission();
        }
    })

}

//添加用户角色关系
function addUR() {
    var uid=document.getElementById('createURuid').valueOf().value;
    var rid=document.getElementById('createURrid').valueOf().value;
    $.ajax({
        type:"post",
        url:"/createUR",
        dataType:"text",
        data:{
            "createURuid":uid,
            "createURrid":rid
        },
        success:function (data) {
            alert(data);
            $('#addUR').modal('hide');
            $(".modal-backdrop").remove();
            $("body").removeClass('modal-open');
            loadUR();
        }
    })
}
//删除关联
function deleteUR(id) {
    $.ajax({
        type:"post",
        url:"/deleteUR",
        dataType:"text",
        data:{
            "id":id
        },
        success:function (data) {
            alert(data);
            loadUR();
        }
    })
}
//修改关联
function updataUR(id) {
    var Id=document.getElementsByName('hid');
    var uid=document.getElementsByName('uid');
    var rid=document.getElementsByName('rid');
    var rId;
    var uId;
    for(var i=0;i<Id.length;i++){
        if(Id[i].valueOf().value==id){
            uId=uid[i].valueOf().value;
            rId=rid[i].valueOf().value;
            break;
        }
    }
    $.ajax({
        type:"post",
        url:"/updateUR",
        dataType:"text",
        data:{
            "hid":id,
            "uid":uId,
            "rid":rId
        },
        success:function (data) {
            alert(data);
            loadUR();
        }
    })
}

//添加角色权限关系
function addRP() {
    var rid=document.getElementById('createRPrid').valueOf().value;
    var pid=document.getElementById('createRPpid').valueOf().value;
    $.ajax({
        type:"post",
        url:"/createRP",
        dataType:"text",
        data:{
            "createRPrid":rid,
            "createRPpid":pid
        },
        success:function (data) {
            alert(data);
            $('#addRP').modal('hide');
            $(".modal-backdrop").remove();
            $("body").removeClass('modal-open');
            loadRP();
        }
    })
}
//删除关联
function deleteRP(id) {
    $.ajax({
        type:"post",
        url:"/deleteRP",
        dataType:"text",
        data:{
            "id":id
        },
        success:function (data) {
            alert(data);
            loadRP();
        }
    })
}
//修改关联
function updataRP(id) {
    var Id=document.getElementsByName('hid');
    var rid=document.getElementsByName('rid');
    var pid=document.getElementsByName('pid');
    var rId;
    var pId;
    for(var i=0;i<Id.length;i++){
        if(Id[i].valueOf().value==id){
            pId=pid[i].valueOf().value;
            rId=rid[i].valueOf().value;
            break;
        }
    }
    $.ajax({
        type:"post",
        url:"/updateRP",
        dataType:"text",
        data:{
            "hid":id,
            "rid":rId,
            "pid":pId
        },
        success:function (data) {
            alert(data);
            loadRP();
        }
    })
}

//admin换页
function toadminpage(pageNum) {
    $.ajax({
        url:"/getAdmin",
        type:"post",
        // dataType:"text",
        data:{
            "pageNum":pageNum
        },
        success:function (data) {
            $("#right_load").empty();//清空div内容
            $("#right_load").append(data);//往div加载页面
        }
    })
}
//user换页
function touserpage(pageNum) {
    $.ajax({
        url:"/getUser",
        type:"post",
        data:{
            "pageNum":pageNum
        },
        success:function (data) {
            $("#right_load").empty();//清空div内容
            $("#right_load").append(data);//往div加载页面
        }
    })
}
//role换页
function torolepage(pageNum) {
    $.ajax({
        url:"/getRoles",
        type:"post",
        data:{
            "pageNum":pageNum
        },
        success:function (data) {
            $("#right_load").empty();//清空div内容
            $("#right_load").append(data);//往div加载页面
        }
    })
}
//permission换页
function topermissionpage(pageNum) {
    $.ajax({
        url:"/getPermission",
        type:"post",
        data:{
            "pageNum":pageNum
        },
        success:function (data) {
            $("#right_load").empty();//清空div内容
            $("#right_load").append(data);//往div加载页面
        }
    })
}
//userrole换页
function toURpage(pageNum) {
    $.ajax({
        url:"/getUserrole",
        type:"post",
        data:{
            "pageNum":pageNum
        },
        success:function (data) {
            $("#right_load").empty();//清空div内容
            $("#right_load").append(data);//往div加载页面
        }
    })
}
//rolepermission换页
function toRPpage(pageNum) {
    $.ajax({
        url:"/getRolePermission",
        type:"post",
        data:{
            "pageNum":pageNum
        },
        success:function (data) {
            $("#right_load").empty();//清空div内容
            $("#right_load").append(data);//往div加载页面
        }
    })
}


//注册
function register() {
    var uid=document.getElementById('uid').valueOf().value;
    var uname=document.getElementById('uname').valueOf().value;
    var uphone=document.getElementById('uphone').valueOf().value;
    var upassword=document.getElementById('upassword').valueOf().value;
    var repeat=document.getElementById('repeat').valueOf().value;
    var usertype=1;
    if (upassword==repeat){
        $.ajax({
            type:"post",
            url:"/createuser",
            datatype:"text",
            data:{
                "createuId":uid,
                "createuName":uname,
                "createuPassword":upassword,
                "createPhone":uphone,
                "usertype":usertype
            },
            success:function (data) {
                alert(data);
                $('#signin_modal').modal('hide')
                $(".modal-backdrop").remove();
            }
        })
    } else{
        alert("两次输入的密码不相同！请重新填写！");
    }
}

//登录
function login() {
    var uid=document.getElementById('login_uid').valueOf().value;
    var password=document.getElementById('login_password').valueOf().value;
    $.ajax({
        type:"post",
        url:"/checkout",
        dataType:"text",
        data:{
            "uid":uid,
            "password":password
        },
        success:function (data) {
            if(data!=0){//若data不为0，即用户存在，则能登录
                distinguish(uid);
            }else {
                alert("用户不存在！账号或密码不存在！");
            }

        }
    })
}
//普通用户和管理员用户分别登录不同的页面
function distinguish(uid) {
    $.ajax({
        type:"post",
        url:"/checkadmin",
        dataType:"text",
        data:{
            "uid":uid
        },
        success:function (data) {
            if(data!=0){//若data不为0，则为普通用户

                window.location.href="/Guider/tocommon";
            }else{//反之为管理员用户
                window.location.href="/Guider/toSystemAdmin";
            }
        }
    })
}