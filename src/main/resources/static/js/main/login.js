function refreshVerify() {
    $('#verifyImg').attr("src", "/index/captcha.jpg?t=" + $.now());
}

function login() {
    if ($("#username").val() === "" || $("#password").val() === "" || $("#verify").val() === "") {
        $("#error_info").html("请完整填写信息!");
        return false;
    }
    var form_data = $('#form-info').serialize();
    // 异步提交数据到增加信息
    $.ajax(
        {
            url: "/index/login",
            data: form_data,
            type: "post",
            success: function (data) {
                if (data.code === '0') {
                    window.location.href = "/index/html/manager/home.html";
                }
                else {
                    $("#error_info").html(data.msg);
                    $("#username").val("");
                    $("#password").val("");
                    $("#verify").val("");
                    refreshVerify();
                }
            },
            error: function () {
                alert('请求出错');
            },
            complete: function () {
                $('#acting_tips').hide();
            }
        });
}