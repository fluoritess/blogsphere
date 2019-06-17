//表格插入复选框
function initTableCheckbox() {
    var $thr = $('table thead tr');
    var $checkAllTh = $('<th width="60px" id="table_checkbox"><input type="checkbox" id="checkAll" name="checkAll" /></th>');
    /*将全选/反选复选框添加到表头最前，即增加一列*/
    $thr.prepend($checkAllTh);
    /*“全选/反选”复选框*/
    var $checkAll = $thr.find('input');
    $checkAll.click(function (event) {
        /*将所有行的选中状态设成全选框的选中状态*/
        $tbr.find('input').prop('checked', $(this).prop('checked'));
        /*并调整所有选中行的CSS样式*/
        if ($(this).prop('checked')) {
            $tbr.find('input').parent().parent().addClass('active');
        } else {
            $tbr.find('input').parent().parent().removeClass('active');
        }
        /*阻止向上冒泡，以防再次触发点击操作*/
        event.stopPropagation();
    });
    /*点击全选框所在单元格时也触发全选框的点击操作*/
    $checkAllTh.click(function () {
        $(this).find('input').click();
    });
    var $tbr = $('table tbody tr');
    var $checkItemTd = $('<td><input type="checkbox" name="checkItem" /></td>');
    /*每一行都在最前面插入一个选中复选框的单元格*/
    $tbr.prepend($checkItemTd);
    /*点击每一行的选中复选框时*/
    $tbr.find('input').click(function (event) {
        /*调整选中行的CSS样式*/
        $(this).parent().parent().toggleClass('active');
        /*如果已经被选中行的行数等于表格的数据行数，将全选框设为选中状态，否则设为未选中状态*/
        $checkAll.prop('checked', $tbr.find('input:checked').length == $tbr.length ? true : false);
        /*阻止向上冒泡，以防再次触发点击操作*/
        event.stopPropagation();
    });
    /*点击每一行时也触发该行的选中操作*/
    $tbr.click(function () {
        $(this).find('input').click();
    });
}

//转换时间
function transformDate(date) {
    if (!date) {
        return false;
    }
    var month = date.substring(0, 8);
    var day=parseInt(date.substring(8,10));
    var hours=parseInt(date.substring(11,13));
    hours=hours+8;
    if (hours >= 24){
        day++;
        hours=hours-24;
    }
    var minu=date.substring(13,19);
    if (day<10){
        day="0"+day;
    }
    if (hours<10){
        hours="0"+hours;
    }
    return month+day+" "+hours+minu;
}

//确认删除
function confirm_delete() {
    if (confirm("确认删除吗？")){
        return true;
    } else {
        location.reload();
        return false;
    }
}
//检查文件是否合法
function checkFile(objectName,maxSize,pattern) {
    var fileObject=$(objectName);
    var imgName=fileObject.val();
    var imgArr=imgName.split('\\');
    var myImg=imgArr[imgArr.length-1];
    var ext=myImg.lastIndexOf('.');
    var finalExt=myImg.substring(ext,myImg.length).toUpperCase();
    var file=fileObject.get(0).files[0];
    //判断是否上传文件
    if (typeof (file)==="undefined"){
        $("#tip").html("<span style='color:red'>请上传文件！</span>");
        return false
    } else{
        var fileSize=file.size;
        //文件最大值
        var maxSizeNum=1048576*parseInt(maxSize);
        if (pattern ==='IMG'||pattern==='img'){
            //检查是否为图片格式
            if (finalExt !== '.PNG' && finalExt !== '.GIF' && finalExt !== '.JPG'
                && finalExt !== '.JPEG' && finalExt !== '.BMP') {
                $("#tip").html("<span style='color:red'>文件类型错误,请上传图片类型</span>");
                return false;
            }
        }else if (pattern === 'PDF'||pattern==='pdf'){
            //检查是否是pdf格式
            if (finalExt !== '.PDF') {
                $("#tip").html("<span style='color:red'>文件类型错误,请上传PDF格式</span>");
                return false;
            }
        }else {
            return false;
        }
        //检查文件大小
        if(parseInt(fileSize)>=maxSizeNum){
            $("#tip").html("<span style='color:red'>上传的文件不能超过"+maxSize+"MB</span>");
            return false;
        } else {
            return true;
        }
    }
}

function getProjectName() {
    var curWwwPath=window.document.location.href;
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    var localhostPath=curWwwPath.substring(0,pos);
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return localhostPath+projectName;
}