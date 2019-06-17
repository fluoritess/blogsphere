var onClick = document.getElementsByClassName("onClick");
var onon=[];
var hide = [];
var onClicklength = onClick.length;
for (var i = 0; i < onClicklength; i++) {
    onon[i]=onClick[i].getElementsByClassName("onon")[0];
    hide[i] = onClick[i].getElementsByClassName("hide")[0];
}
var hidelength = hide.length;
var ononlength=onon.length;
function onhide(a) {
    a.style.display = "block";
}
function offhide(a) {
    a.style.display = "none";
}
function menu() {
    for (var i = 0; i < onClicklength; i++) {
        onon[i].index = i;
        onon[i].onclick = function () {
            var index = this.index;
            var dis = hide[index].style.display;
            if (dis == "block") {
                offhide(hide[index]);
            } else {
                for (var j = 0; j < hidelength; j++) {
                    offhide(hide[j]);
                }
                onhide(hide[index]);
            }
        }
    }
}
menu();
