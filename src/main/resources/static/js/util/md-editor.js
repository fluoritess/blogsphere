var testEditor;
var fileNameStr=GetQueryString("fileName");
var url=GetQueryString("url");
var isUser=false;
var expType="";
$(function () {
    var saveFile=$("#saveFile");
    if(url!==null){
        saveFile.attr("onclick","saveMarkdown()");
        saveFile.html("保存文档");
        var sendData={};
        if (url==="maintain_log"){
            sendData['url']="maintain_log";
            isUser=true;
        }
        $.ajax({
            url: "/index/util/getMarkdown",
            data: sendData,
            type: "post",
            success: function (data) {
                if(data.code==="0"){
                    var content=data.value;
                    $("#my-editormd-markdown-doc").text(content);
                }else if (data.code==="500"){
                    alert(data.msg);
                }
            },error: function () {
                alert('请求出错');
            }
        });
    }else {
        saveFile.attr("onclick","choiceFileType()");
        saveFile.html("导出文档");
    }
    if (isUser){
        testEditor = editormd("my-editormd", {
            width: "95%",
            height: 700,
            syncScrolling: "single",
            path: "../../plugins/editor_md/lib/",
            saveHTMLToTextarea: true,
            imageUpload : true,
            imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
            imageUploadURL : "/index/util/uploadImage",//注意你后端的上传图片服务地址
        });
    } else {
        testEditor = editormd("my-editormd", {
            width: "95%",
            height: 700,
            syncScrolling: "single",
            path: "../../plugins/editor_md/lib/",
            saveHTMLToTextarea: true,
        });
    }
});

function saveMarkdown() {
    // 获取Markdown源码
    var mdstr=testEditor.getMarkdown();
    if (mdstr===""){
        alert("文件内容必须不为空！");
        $('#addUserModal').modal('hide');
        return false;
    }
    //保存到服务器
    $.ajax({
        url: "/index/util/saveMarkdown",
        data: {fileName:fileNameStr,url:url,fileType:expType,isUser:isUser,content:mdstr},
        type: "post",
        success: function (data) {
            if(data.code==="0"){
                if (!isUser){
                    window.open(data.filePath,"_blank");
                    location.reload(true);
                } else {
                    alert("保存成功！")
                }
            }else if (data.code==="500"){
                alert(data.msg);
            }
        },error: function () {
            alert('请求出错');
        }
    });
}

function choiceFileType() {
    $('#addUserModal').modal('show');
}

function exportType(type) {
    if (type==="html"){
        expType="html";
        saveMarkdown();
    } else if (type==="md"){
        expType="md";
        saveMarkdown();
    } else {
        return false;
    }
}