$(function () {
    //获取表格信息
    function getTableInfo() {
        // 获取表格信息
        $.ajax(
            {
                url: "/index/getMainInfo",
                // data: JSON.stringify(dataInfo),
                // dataType:"json",
                contentType: "application/json",
                type: "post",
                success: function (result) {
                    if (result) {
                        initMainInfo(result.mainSiteInfo);
                        initProfessionalInfo(result.professionalInfo);
                        initDataStatisticsInfo(result.dataStatistics);
                        initCapacityInfo(result.capacity);
                        initProjectInfo(result.project);
                        initResumeInfo(result.resume);
                        initQuotes(result.quotes);
                        $('#feedback').val("");
                        $('#contactInfo').val("");
                        // init();
                        initData();
                    }
                },
                error: function () {
                    alert('请求出错');
                },
            });
    }

    getTableInfo();
    getVisitedInfo();
});

function initMainInfo(mainSiteInfo) {
    $(".element").typed({
        strings: [mainSiteInfo.signOne, mainSiteInfo.signTwo],
        typeSpeed: 1,
        backSpeed: 1,
        backDelay: 1e3,
        loop: !0
    });
    $('#title_name').html(mainSiteInfo.name);
    $('#main_detail').html(mainSiteInfo.detail);
    $('#resume_url').attr("href", mainSiteInfo.resumeFile);
    $('#personal_pic').attr("src", mainSiteInfo.personalPic);
}

function initProfessionalInfo(professionalInfo) {
    var space = $('#space');
    for (var i = 0; i < professionalInfo.length; i++) {
        space.append("<h5>" + professionalInfo[i].lang + "</h5>");
        space.append("<div class='progress progress-striped active'>\n" +
            "                <div class='progress-bar progress-bar-" + professionalInfo[i].color + "'  aria-valuenow='90' aria-valuemax='100'\n" +
            "                     aria-valuemin='0' style='width: " + professionalInfo[i].percent + ";" +
            "               '></div>\n" +
            "              </div>")
    }
}

function initDataStatisticsInfo(dataStatistics) {
    var statistics = $('#statistics');
    for (var i = 0; i < dataStatistics.length; i++) {
        statistics.append("<div class='col-md-4'>\n" +
            "                <p class='h2'><i class='" + dataStatistics[i].icon + "'></i> <span class='count' data-from='0' data-to='" + dataStatistics[i].num + "' data-speed='5000' data-refresh-interval='50'></span></p>\n" +
            "                <h5 class='text-weight'>" + dataStatistics[i].name + "</h5>\n" +
            "              </div>")
    }
}

function initCapacityInfo(capacity) {
    var capacityInfo = $('#capacity_info');
    for (var i = 0; i < capacity.length; i++) {
        if (i % 2 === 0) {
            capacityInfo.append("<div class='row' id='capacity" + i + "'></div>");
        }
        var cap;
        if (i % 2 === 1) {
            cap = $('#capacity' + (i - 1));
        } else {
            cap = $('#capacity' + i);
        }
        cap.append("<div class='col-sm-6 animated'>\n" +
            "                <div class='panel panel-default hvr-grow-shadow'>\n" +
            "                  <div class='panel-body'>\n" +
            "                    <div class='icon-item'>\n" +
            "                      <i class='" + capacity[i].icon + "'></i>\n" +
            "                      <h5>" + capacity[i].name + "</h5>\n" +
            "                    </div>\n" +
            "                    <div class='text-justify'>\n" +
            "                      <p>" + capacity[i].detail + "</p>\n" +
            "                    </div>\n" +
            "                  </div>\n" +
            "                </div>\n" +
            "              </div>");
    }
}

function initProjectInfo(project) {
    var projectInfo = $('#project_info');
    var projectHide = $('#project_hide');
    for (var i = 0; i < project.length; i++) {
        if (i < 4) {
            projectInfo.append("<figure class='cross-image animated'>\n" +
                "              <img src='" + project[i].pictureUrl + "' alt='" + project[i].name + "' />\n" +
                "              <figcaption>\n" +
                "                <h2>" + project[i].name + "</h2>\n" +
                "                <p>" + project[i].detail + "</p>\n" +
                "                <a href='" + project[i].projectUrl + "'>了解项目</a>\n" +
                "              </figcaption>\n" +
                "            </figure>")
        } else {
            projectHide.append("<figure class='cross-image'>\n" +
                "              <img src='" + project[i].pictureUrl + "' alt='" + project[i].name + "' />\n" +
                "              <figcaption>\n" +
                "                <h2>" + project[i].name + "</h2>\n" +
                "                <p>" + project[i].detail + "</p>\n" +
                "                <a href='" + project[i].projectUrl + "'>了解项目</a>\n" +
                "              </figcaption>\n" +
                "            </figure>")
        }

    }
}

function initResumeInfo(resumeInfo) {
    var resumeEdu = $('#resume_edu');
    var resumeExp = $('#resume_exp');
    for (var i = 0; i < resumeInfo.length; i++) {
        if (resumeInfo[i].type === '教育') {
            resumeEdu.append("<div class='col-sm-8 col-md-8 col-md-push-4 animated'>\n" +
                "            <h4>" + resumeInfo[i].subject + "</h4>\n" +
                "            <p>" + resumeInfo[i].detail + "</p>\n" +
                "            <hr class='hidden-xs'>\n" +
                "          </div>\n" +
                "\n" +
                "          <div class='col-sm-4 col-md-4 col-md-pull-8 animated'>\n" +
                "            <div class='icon-list'>\n" +
                "              <div class='icon-list-icon'>\n" +
                "                <i class='fa fa-graduation-cap'></i>\n" +
                "              </div>\n" +
                "              <div class='icon-list-content'>\n" +
                "                <div class='icon-list-title'>" + resumeInfo[i].address + "</div>\n" +
                "                <div class='icon-list-info'>\n" +
                "                  <span>" + resumeInfo[i].years + "</span>\n" +
                "                </div>\n" +
                "              </div>\n" +
                "            </div>\n" +
                "            <hr class='visible-xs'>\n" +
                "          </div>")
        } else if (resumeInfo[i].type === '经历') {
            resumeExp.append("<div class='col-sm-8 col-md-8 col-md-push-4 animated'>\n" +
                "            <h4>" + resumeInfo[i].subject + "</h4>\n" +
                "            <p>" + resumeInfo[i].detail + "</p>\n" +
                "            <hr class='hidden-xs'>\n" +
                "          </div>\n" +
                "\n" +
                "          <div class='col-sm-4 col-md-4 col-md-pull-8 animated'>\n" +
                "            <div class='icon-list'>\n" +
                "              <div class='icon-list-icon'>\n" +
                "                <i class='fa fa-graduation-cap'></i>\n" +
                "              </div>\n" +
                "              <div class='icon-list-content'>\n" +
                "                <div class='icon-list-title'>" + resumeInfo[i].address + "</div>\n" +
                "                <div class='icon-list-info'>\n" +
                "                  <span>" + resumeInfo[i].years + "</span>\n" +
                "                </div>\n" +
                "              </div>\n" +
                "            </div>\n" +
                "            <hr class='visible-xs'>\n" +
                "          </div>")
        }
    }
}

function submit_form() {
    var fd = new FormData();
    var feedbackInfo = $('#feedback').val().toString().replace(/^\s+|\s+$/g, "");
    var contactInfo = $('#contactInfo').val().toString().replace(/^\s+|\s+$/g, "");
    if (feedbackInfo === "") {
        alert("请输入反馈意见！");
        return false;
    }
    var tmp = getCookie("feedbackTime");
    if (tmp) {
        alert("请等待提交时间！");
        return false;
    }
    fd.append("feedbackInfo", feedbackInfo);
    fd.append("contactInfo", contactInfo);
    $.ajax(
        {
            url: "/index/submitFeedback",
            // contentType: "application/json",
            processData: false,
            contentType: false,
            data: fd,
            dataType: "json",
            type: "post",
            success: function (result) {
                if (result.code === '0') {
                    alert("提交成功，感谢您的反馈！");
                    $("#feedback").val("");
                    $("#contactInfo").val("");
                    var secs=6*10*5;
                    for (var i = 1; i <= secs; i++) {
                        window.setTimeout("updateButton( " + i + ","+secs+") ", i * 1000);
                    }
                    setCookie("feedbackTime", secs+"s", secs+"s")
                } else if (result.code === '500') {
                    alert("服务器错误！");
                }
                // location.reload();
            },
            error: function () {
                alert('提交出错');
                $("#feedback").val("");
                $("#contactInfo").val("");
            }
        });
}

function updateButton(num,secs) {
    if (num === secs) {
        $("#submit_info").html("提交意见");
        $("#submit_info").attr("disabled", false);
    }
    else {
        printnr = secs - num;
        $("#submit_info").html("距离下次操作还剩" + printnr + "秒");
        $("#submit_info").attr("disabled", true);
    }
}

//设置自定义过期时间cookie
function setCookie(name, value, time) {
    var msec = getMsec(time); //获取毫秒
    var exp = new Date();
    exp.setTime(exp.getTime() + msec * 1);
    document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
}

//将字符串时间转换为毫秒,1秒=1000毫秒
function getMsec(str) {
    var timeNum = str.substring(0, str.length - 1) * 1; //时间数量
    var timeStr = str.substring(str.length - 1, str.length); //时间单位前缀，如h表示小时

    if (timeStr === "s") //20s表示20秒
    {
        return timeNum * 1000;
    }
    else if (timeStr === "h") //12h表示12小时
    {
        return timeNum * 60 * 60 * 1000;
    }
    else if (timeStr === "d") {
        return timeNum * 24 * 60 * 60 * 1000; //30d表示30天
    }
}

function getCookie(name) {
    var cookies = document.cookie;
    var list = cookies.split("; ");          // 解析出名/值对列表

    for(var i = 0; i < list.length; i++) {
        var arr = list[i].split("=");          // 解析出名和值
        if(arr[0] === name)
            return decodeURIComponent(arr[1]);   // 对cookie值解码
    }
    return null;
}

function initQuotes(quotesInfo) {
    var quotes = $('#quotes');
    for (var i = 0; i < quotesInfo.length; i++) {
        quotes.append("<div class='item'>\n" +
            "                        <blockquote>\n" +
            "                            <div class='row'>\n" +
            "                                <div class='col-sm-3 text-center'>\n" +
            "                                    <img class='img-circle' src='"+quotesInfo[i].imgUrl+"' alt='"+quotesInfo[i].author+"'>\n" +
            "                                </div>\n" +
            "                                <div class='col-sm-9'>\n" +
            "                                    <p class='text-item'>"+quotesInfo[i].detail+"</p>\n" +
            "                                    <small>"+quotesInfo[i].author+"</small>\n" +
            "                                </div>\n" +
            "                            </div>\n" +
            "                        </blockquote>\n" +
            "                    </div>")
    }
}

function getVisitedInfo() {
    var url={"url":"/index"};
    $.ajax(
        {
            url: "/index/getVisitedInfo",
            data: JSON.stringify(url),
            dataType:"json",
            contentType: "application/json",
            type: "post",
            success: function (result) {
                if (result.code==='0') {
                    var data=result.data;
                    $('#rank_num').html(data.nowNum);
                    $('#day_num').html(data.dayNum);
                    $('#all_num').html(data.allNum);
                }else {
                    console.log("error")
                }
            },
            error: function () {
                alert('请求出错');
            },
        });
}

