$(function () {
    var height=$(document).height();
    var width=$(document).width();
    var web_body=$('#web_body');
    web_body.css("height",height);
    web_body.css("width",width);
    var par=null;
    $("#main-nav>li>a").click(function () {
        $this = $(this);
        $("#main-nav>li").removeClass("active");
        if (par!==null&&par[0]!==$this.parent()[0]) {
            $("#main-nav>li>ul>li").removeClass("li_active");
        }
        $this.parent().addClass("active");
    });
    $("#main-nav>li>ul>li>a").click(function () {
        $this = $(this);
        $("#main-nav>li>ul>li").removeClass("li_active");
        $this.parent().addClass("li_active");
        $("#main-nav>li").removeClass("active");
        $this.parent().parent().parent().addClass("active");
        par=$this.parent().parent().parent();
        var now_li=par[0];
        var all_li=$("#main-nav .nav-header").parent();
        for(var i=0;i<all_li.length;i++){
            if (all_li[i]!==now_li){
                all_li[i].childNodes[3].classList.remove("in");
                all_li[i].childNodes[1].classList.add("collapsed");
                if (all_li[i].childNodes[1].getAttribute("aria-expanded")!==null){
                    all_li[i].childNodes[1].getAttribute("aria-expanded").replace("true","false");
                }
                if (all_li[i].childNodes[3].getAttribute("aria-expanded")!==null) {
                    all_li[i].childNodes[3].getAttribute("aria-expanded").replace("true","false");
                }
            }
        }
    });
    getInfoNum();
});

function getInfoNum() {
    // 信息管理菜单
    var info_menu=$('#informationManager_a');
    //反馈信息菜单
    var feedback_a=$('#feedback_a');

    //反馈信息显示数目
    var feedbackNum=getFeedbackNum();
    if (feedbackNum!==0){
        feedback_a.append("<span class='label label-warning pull-right'>"+feedbackNum+"</span>");
    }

    //信息管理显示数目
    var info_num=feedbackNum;
    if (info_num===0){
        info_menu.append("<span class='pull-right glyphicon glyphicon-chevron-down'></span>");
    }else {
        info_menu.append("<span class='label label-warning pull-right'>"+info_num+"</span>");
    }
}

function getFeedbackNum() {
    var num=0;
    $.ajax(
        {
            url: "/index/info/getNotReadFeedbackNum",
            dataType:"json",
            contentType: false,
            processData: false,
            type: "post",
            async:false,
            success: function (result) {
                if (result) {
                    num = result.num;
                }
            },
            error: function () {
                console.log("菜单请求消息数据出错!");
            },
        });
    return num;
}
