var page = 1;
var pageSize = 5;

$(function () {
    $('#search_value').val("");
    $('#search_type').val("");
    getTableInfo(page, pageSize);
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
    getTableInfo(page, pageSize);
}

//搜索信息
function searchInfo() {
    var searchName = $('#search_type').find('option:selected').val();
    var searchValue = $('#search_value').val();
    getTableInfo(page, pageSize, searchName, searchValue);
}

//获取表格信息
function getTableInfo(page, pageSize, searchName, searchValue) {
    var fd = new FormData();
    fd.append("nowPage", page);
    if (typeof searchValue !== "undefined" && typeof searchName !== "undefined" && searchValue !== "" && searchName !== "") {
        fd.append("searchName", searchName);
        fd.append("searchValue", searchValue);
    }
    fd.append("pageSize", pageSize);
    // 获取表格信息
    $.ajax(
        {
            url: "/index/info/getIpAddressPoolInfo",
            // data: JSON.stringify(dataInfo),
            dataType: "json",
            data: fd,
            // contentType: "application/json",
            contentType: false,
            processData: false,
            type: "post",
            success: function (result) {
                if (result) {
                    $('#all_num').html("总共" + result.data.allDataNum + "条");
                    $('#data_table tbody').empty();
                    $('#table_checkbox').remove();
                    // console.log(result.data[0]);
                    var list = result.data.list;
                    var tbd = $('table tbody');
                    for (var i = 0; i < list.length; i++) {
                        tbd.append("<tr>\n" +
                            "                <td>" + list[i].id + "</td>\n" +
                            "                <td>" + list[i].ip + "</td>\n" +
                            "                <td>" + list[i].address +
                            "                <td>" + list[i].visited_num + "</td>\n" +
                            "                </td>\n" +
                            "                <td>" + transformDate(list[i].modify_date) + "</td>\n" +
                            "            </tr>")
                    }
                    initTableCheckbox();
                    if (result.data.allDataNum !== 0) {
                        setPage(page, pageSize, result.data.allPageNum, searchName, searchValue);
                        addTarget();
                    }
                }
            },
            error: function () {
                alert('请求出错');
            },
        });
}

function setPage(nowPage, pageSize, totalPages, searchName, searchValue) {
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
                    getTableInfo(page, pageSize, searchName, searchValue);
                    break;
                case "prev":
                    currentTarget.bootstrapPaginator("showPrevious");
                    getTableInfo(page, pageSize, searchName, searchValue);
                    break;
                case "next":
                    currentTarget.bootstrapPaginator("showNext");
                    getTableInfo(page, pageSize, searchName, searchValue);
                    break;
                case "last":
                    currentTarget.bootstrapPaginator("showLast");
                    getTableInfo(page, pageSize, searchName, searchValue);
                    break;
                case "page":
                    currentTarget.bootstrapPaginator("show", page);
                    getTableInfo(page, pageSize, searchName, searchValue);
                    break;
            }
        }
    })
}

