var testEditor;
var fileNameStr=GetQueryString("fileName");
var url=GetQueryString("url");
$(function () {
    var saveFile=$("#saveFile");
    if(url!==null){
        saveFile.attr("onclick","saveMarkdown()");
        saveFile.html("保存文档");
        var sendData={};
        if (url==="maintain_log"){
            sendData['url']="maintain_log";
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
});

function saveMarkdown() {
    // 获取Markdown源码
    var mdstr=testEditor.getMarkdown();
    console.log(mdstr);
    //保存到服务器
    $.ajax({
        url: "/index/util/saveMarkdown",
        data: {fileName:fileNameStr,url:url,content:mdstr},
        type: "post",
        success: function (data) {
            if(data.code==="0"){
                alert("保存成功！")
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