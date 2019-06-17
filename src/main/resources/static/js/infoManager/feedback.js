var page = 1;
var type = "0";//代表未读数据
var pageSize = 5;

$(function () {
    getTableInfo(page, type, pageSize);
});

//添加翻页前面的两个按钮
function addTarget() {
    var pagingFoot = $('#paging_foot');
    var pagingLi = $('#paging_foot .active');
    if (pagingLi[0].childNodes[0].innerHTML === '1') {
        pagingFoot.prepend("<li><a title='Go to previous page'>上一页</a></li>");
        pagingFoot.prepend("<li><a title='Go to first page'>首页</a></li>");
    }
}

//修改每页显示条数
function pageSizeChange() {
    var webPageSize = $('#webPageSize').find('option:selected').val();
    pageSize = webPageSize;
    getTableInfo(page, type, pageSize);
}

//获取表格信息
function getTableInfo(page, type, pageSize) {
    var fd = new FormData();
    fd.append("nowPage", page);
    fd.append("limitName", "is_read");
    fd.append("limitValue", type);
    fd.append("pageSize", pageSize);
    // 获取表格信息
    $.ajax(
        {
            url: "/index/info/getFeedbackInfo",
            // data: JSON.stringify(dataInfo),
            dataType: "json",
            data: fd,
            // contentType: "application/json",
            contentType: false,
            processData: false,
            type: "post",
            success: function (result) {
                if (result) {
                    $('#data_table tbody').empty();
                    $('#table_checkbox').remove();
                    // console.log(result.data[0]);
                    var list = result.data.list;
                    var tbd = $('table tbody');
                    for (var i = 0; i < list.length; i++) {
                        var is_read = "";
                        var bc_color = "";
                        if (list[i].is_read === 0) {
                            is_read = "未阅读";
                            bc_color = '#f0a63f';
                        } else {
                            is_read = "已阅读";
                            bc_color = '#5cb85c';
                        }
                        tbd.append("<tr>\n" +
                            "                <td>" + list[i].id + "</td>\n" +
                            "                <td>" + list[i].value + "</td>\n" +
                            "                <td>" + list[i].contact_info + "</td>\n" +
                            "                <td>" + list[i].ip_address +
                            "                </td>\n" +
                            "                <td>" + transformDate(list[i].date) + "</td>\n" +
                            "                <td><span class='type' style='color: #ffffff;background-color: " + bc_color + " '>" + is_read + "</span></td>\n" +
                            "            </tr>")
                    }
                    initTableCheckbox();
                    if (result.data.allDataNum!==0){
                        setPage(page, type, pageSize, result.data.allPageNum);
                        addTarget();
                    }
                }
            },
            error: function () {
                alert('请求出错');
            },
        });
}

function setPage(nowPage, is_read, pageSize, totalPages) {
    $(".pagination").bootstrapPaginator({
        //设置版本号
        bootstrapMajorVersion: 3,
        // 显示第几页
        currentPage: nowPage,
        // 总页数
        totalPages: totalPages,
        //一页显示5个按钮
        numberOfPages: 6,
        alignment: "center",
        itemTexts: function (type, page, current) {
            switch (type) {
                case "first":
                    return "首页";
                case "prev":
                    return "上一页";
                case "next":
                    return "下一页";
                case "last":
                    return "末页";
                case "page":
                    return page;
            }
        },
        //当单击操作按钮的时候, 执行该函数, 调用ajax渲染页面
        onPageClicked: function (event, originalEvent, type, page) {
            var currentTarget = $(event.currentTarget);

            switch (type) {
                case "first":
                    currentTarget.bootstrapPaginator("showFirst");
                    getTableInfo(page, is_read, pageSize);
                    break;
                case "prev":
                    currentTarget.bootstrapPaginator("showPrevious");
                    getTableInfo(page, is_read, pageSize);
                    break;
                case "next":
                    currentTarget.bootstrapPaginator("showNext");
                    getTableInfo(page, is_read, pageSize);
                    break;
                case "last":
                    currentTarget.bootstrapPaginator("showLast");
                    getTableInfo(page, is_read, pageSize);
                    break;
                case "page":
                    currentTarget.bootstrapPaginator("show", page);
                    getTableInfo(page, is_read, pageSize);
                    break;
            }
        }
    })
}

//获取选中的元素
function getCheckIndex() {
    var indexArray = [];
    $('table tbody').find(':checkbox:checked').each(function () {
        var val = $(this).parent().parent().index();
        indexArray.push(val);
        // console.log(val)
    });
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
    var feedback_info = ty.find('tr').eq(index).children('td').eq(2).text();
    var contact_info = ty.find('tr').eq(index).children('td').eq(3).text();
    var ip_address = ty.find('tr').eq(index).children('td').eq(4).text();
    var submit_date = ty.find('tr').eq(index).children('td').eq(5).text();

    $("#id").val(id);
    $("#feedback_info").val(feedback_info);
    $("#contact_info").html(contact_info);
    $("#ip_address").html(ip_address);
    $("#submit_date").html(submit_date);
    var active_li=$('#info_type .active')[0];
    $('#modal-footer').empty();
    if (active_li.childNodes[0].innerHTML==="未读消息"){
        $('#modal-footer').append("<button type='button' class='btn btn-default' data-dismiss='modal'>关闭\n" +
            "                    </button>\n" +
            "                    <button type='submit' class='btn btn-primary'>\n" +
            "                        确认阅读\n" +
            "                    </button>\n" +
            "                    <span id='tip'> </span>")
    }else {
        $('#modal-footer').append("<button type='button' class='btn btn-default' data-dismiss='modal'>关闭\n" +
            "                    </button>")
    }
    $('#addUserModal').modal('show');
    // $("#func").val("modify");
}

function modifyInfo() {
    var act = $.trim($('#act').val());
    var data = {"id": $("#id").val()};
    // 异步提交数据到增加信息
    $.ajax(
        {
            url: "/index/info/readFeedbackInfo",
            data: JSON.stringify(data),
            contentType: "application/json",
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
                    $("#id").val("");
                    $("#feedback_info").val("");
                    $("#contact_info").html("");
                    $("#ip_address").html("");
                    $("#submit_date").html("");
                    window.parent[0].location.reload();
                    // getInfoNum();
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
                $('#acting_tips').hide();
            }
        });
    return false;
}

$(function () {
    $('#addUserModal').on('hide.bs.modal', function () {
        // 关闭时清空edit状态为add
        // location.reload();
        $("#tip").html("");
        $('table').find(':checkbox:checked').each(function () {
            $(this).prop("checked",false);
            // console.log(val)
        });
    })
});

//切换页面
$(function () {
    $('#info_type>li>a').click(function () {
        $this = $(this);
        $("#info_type>li").removeClass("active info_type_active");
        $this.parent().addClass("active info_type_active");
        if ($this.html() === "已读消息") {
            type = "1";
            $('#paging_foot').empty();
            getTableInfo(page, type, pageSize);
        } else if ($this.html() === "未读消息") {
            type = "0";
            $('#paging_foot').empty();
            getTableInfo(page, type, pageSize);
        }
    });
});