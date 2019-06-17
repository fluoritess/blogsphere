$(function () {
    //获取表格信息
    function getTableInfo() {
        // 获取主页信息
        $.ajax(
            {
                url: "/index/manager/getMainInfo",
                contentType: "application/json",
                type: "post",
                success: function (data) {
                    $('#name').val(data.data.name);
                    $('#introduce1').val(data.data.signOne);
                    $('#introduce2').val(data.data.signTwo);
                    $('#introduce3').val(data.data.detail);
                    $('#modify_date').html(transformDate(data.data.modifyDate));
                    $('#pre_background').attr('href',getProjectName()+'/'+data.data.backgroundUrl);
                    $('#pre_resume').attr('href',getProjectName()+'/'+data.data.resumeFile);
                    $('#pre_personal_pic').attr('href',getProjectName()+'/'+data.data.personalPic);
                },
                error: function () {
                    alert('请求出错');
                },
            });
    }
    getTableInfo();
});

function submit_form() {
    var fd=new FormData();
    var file1=$("#backgroundUrl").get(0).files[0];
    var file2=$("#resumeFile").get(0).files[0];
    var file3=$("#personalPic").get(0).files[0];
    //判断背景图片文件是否合法
    if (typeof (file1)!=="undefined" ) {
        if (!checkFile('#backgroundUrl',4,'IMG')){
            return false;
        }
        fd.append("file1",file1);
    }else{
        fd.append("file1",null);
    }
    //判断简历文件是否合法
    if (typeof (file2)!=="undefined" ) {
        if (!checkFile('#resumeFile',5,'PDF')){
            return false;
        }
        fd.append("file2",file2);
    }else{
        fd.append("file2",null);
    }
    //判断个人照片是否合法
    if (typeof (file3)!=="undefined" ) {
        if (!checkFile('#personalPic',4,'IMG')){
            return false;
        }
        fd.append("file3",file3);
    }else{
        fd.append("file3",null);
    }
    fd.append("name",$('#name').val().toString().replace(/^\s+|\s+$/g,""));
    fd.append("signOne",$("#introduce1").val().toString().replace(/^\s+|\s+$/g,""));
    fd.append("signTwo",$("#introduce2").val().toString().replace(/^\s+|\s+$/g,""));
    fd.append("detail",$("#introduce3").val().toString().replace(/^\s+|\s+$/g,""));
    $.ajax(
        {
            url: "/index/manager/modifyMainInfo",
            // contentType: "application/json",
            processData:false,
            contentType:false,
            data: fd,
            dataType: "json",
            type: "post",
            success: function (result) {
                if (result.code === '0') {
                    alert("修改成功！");
                    $("#backgroundUrl").val("");
                    $("#resumeFile").val("");
                    $("#personalPic").val("");

                }else if (result.code === '500') {
                    alert("服务器错误！");
                }
                location.reload();
            },
            error: function () {
                alert('请求出错');
                console.log("修改失败");
            }
        });
}
