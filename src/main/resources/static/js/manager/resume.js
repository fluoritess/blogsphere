$(function () {
    //获取表格信息
    function getTableInfo() {
        // 获取表格信息
        $.ajax(
            {
                url: "/index/manager/getResumeInfo",
                // data: JSON.stringify(dataInfo),
                // dataType:"json",
                contentType: "application/json",
                type: "post",
                success: function (result) {
                    if (result) {
                        // console.log(result.data[0]);
                        var list = result.data;
                        var tbd = $('table tbody');
                        for (var i = 0; i<list.length; i++) {
                            tbd.append("<tr>\n" +
                                // "<td><input type=\"checkbox\" name=\"checkItem\" /></td>"+
                                "                <td>" + list[i].id + "</td>\n" +
                                "                <td>" + list[i].address + "</td>\n" +
                                "                <td>" + list[i].years + "</td>\n" +
                                "                <td>" + list[i].subject + "</td>\n" +
                                "                <td>" + list[i].detail + "</td>\n" +
                                "                <td ><span class='type' style='display:inline-block;" +
                                "height: 25px;width: 45px;line-height: 25px;color: #e2fdff'>" + list[i].type + "</span></td>\n" +
                                "                <td>" + transformDate(list[i].modifyDate) + "</td>\n" +
                                "            </tr>")
                        }
                        initTableCheckbox();
                        type_color();
                    }
                },
                error: function () {
                    alert('请求出错');
                },
            });
    }

    getTableInfo();
    // initTableCheckbox();


});


//获取选中的元素
function getCheckIndex(act) {
    var indexArray = [];
    $('table tbody').find(':checkbox:checked').each(function () {
        var val = $(this).parent().parent().index();
        indexArray.push(val);
        // console.log(val)
    });
    if (act === 'modify') {
        if (indexArray.length > 1) {
            alert('只能选择一行信息！');
            return false;
        } else if (indexArray.length === 0) {
            alert('选中信息不能为空！');
            return false;
        }
        var index = indexArray[0];
        var ty = $('table tbody');
        var id = ty.find('tr').eq(index).children('td').eq(1).text();
        var address = ty.find('tr').eq(index).children('td').eq(2).text();
        var years=ty.find('tr').eq(index).children('td').eq(3).text();
        var subject=ty.find('tr').eq(index).children('td').eq(4).text();
        var detail=ty.find('tr').eq(index).children('td').eq(5).text();
        var type=ty.find('tr').eq(index).children('td').eq(6).text();
        $('#addUserModal').modal('show');
        $("#id").val(id);
        $("#address").val(address);
        $("#detail").val(detail);
        $("#years").val(years);
        $("#subject").val(subject);
        $("#type").val(type);
        $("#func").val("modify");
    }else if (act === "delete"){
        if (indexArray.length === 0) {
            alert('选中信息不能为空！');
            return false;
        }
        if (!confirm_delete()) {
            return false;
        }
        var idArray=[];
        var ty = $('table tbody');
        for (var i=0;i<indexArray.length;i++){
            idArray.push(ty.find('tr').eq(indexArray[i]).children('td').eq(1).text());
        }
        delete_info(idArray);

    }

}

// 删除信息
function delete_info(id) {
    if (!id) {
        alert('Error！');
        return false;
    }
    var data={"data":id};
    $.ajax(
        {
            url: "/index/manager/deleteResumeInfo",
            data: JSON.stringify(data),
            contentType: "application/json",
            type: "post",
            beforeSend: function () {
                $("#tip").html("<span style='color:blue'>正在处理...</span>");
                return true;
            },
            success: function (data) {
                if (data.code === '0') {
                    alert('操作成功');
                    $("#tip").html("<span style='color:blueviolet'>恭喜，删除成功！</span>");
                    location.reload();
                }
                else {
                    $("#tip").html("<span style='color:red'>失败，请重试</span>");
                    alert('操作失败');
                }
            },
            error: function () {
                alert('请求出错');
            },
            complete: function () {
                // $('#tips').hide();
            }
        });

    return false;
}

// 提交表单
function check_form() {
    var func = $("#func").val();
    if (func==="modify"){
        modifyInfo();
    } else {
        addInfo();
    }
    return false;
}

//添加信息
function addInfo(){
    var act = $.trim($('#act').val());
    var form_data = $('#form_data').serialize();
    // 异步提交数据到增加信息
    $.ajax(
        {
            url: "/index/manager/addResumeInfo",
            data: form_data,
            type: "post",
            beforeSend: function () {
                $("#tip").html("<span style='color:blue'>正在处理...</span>");
                return true;
            },
            success: function (data) {
                if (data.code === '0') {

                    var msg = "添加";
                    if (act === "edit") msg = "编辑";
                    $("#tip").html("<span style='color:blueviolet'>恭喜，" + msg + "成功！</span>");
                    alert(msg + "OK！");
                    fresh_table();
                }
                else {
                    $("#tip").html("<span style='color:red'>失败，请重试</span>");
                    alert('操作失败');
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

//修改信息
function modifyInfo(){
    var act = $.trim($('#act').val());
    var form_data = $('#form_data').serialize();
    // 异步提交数据到增加信息
    $.ajax(
        {
            url: "/index/manager/updateResumeInfo",
            data: form_data,
            type: "post",
            beforeSend: function () {
                $("#tip").html("<span style='color:blue'>正在处理...</span>");
                return true;
            },
            success: function (data) {
                if (data.code === '0') {

                    var msg = "添加";
                    if (act === "edit") msg = "编辑";
                    $("#tip").html("<span style='color:blueviolet'>恭喜，" + msg + "成功！</span>");
                    alert(msg + "OK！");
                    $("#func").val("add");
                    fresh_table();
                }
                else {
                    $("#tip").html("<span style='color:red'>失败，请重试</span>");
                    alert('操作失败');
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

$(function () {
    $('#addUserModal').on('hide.bs.modal', function () {
        // 关闭时清空edit状态为add
        $("#act").val("add");
        fresh_table();
    })
});

function fresh_table() {
    $("#id").val("");
    $("#address").val("");
    $("#subject").val("");
    $("#detail").val("");
    $("#years").val("");
    location.reload();
    return true;
}

function type_color() {
    var type=$(".type");
    for (var i=0;i<type.length;i++){
        var typeInfo=type.eq(i);
        if (typeInfo.text()==="教育"){
            typeInfo.css("background-color","#e3443c");
        }else if (typeInfo.text()==="经历"){
            typeInfo.css("background-color","#3f96e4");
        }
    }

}