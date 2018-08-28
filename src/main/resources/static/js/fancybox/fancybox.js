//腰部导航点击切换
$(function () {
    var tabs =$("#tab").find(".tab");
    var showArr =$(".boxs").find(".center_show");
    for(var i = 0; i < tabs.length; i ++){
        tabs[i].index=i;
        tabs[i].onmouseover = function () {
            //所有的都不显示
            for(var j = 0; j < tabs.length; j ++) {
                showArr[j].classList.remove("selectShow");
            }
            showArr[this.index].classList.add("selectShow");
        };
    }

});
function goResPage(url) {
    document.location.href=url;
}