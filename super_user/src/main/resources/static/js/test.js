//测试上传图片
function upload() {
    var data = new FormData();
    data.append("img",$('#img').get(0).files[0]);

    $.ajax({
        url: '/Guider/upload',
        type: 'post',
        contentType: false,
        data: data ,
        processData: false,
        success:function(info) {
            alert(info.imgname+"上传成功");
        }
    });

}