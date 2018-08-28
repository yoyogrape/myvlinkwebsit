$('.o-m .ul-o li').click(function () {
    var linum = $(this).index()
    $('.o-m .ul-t li').eq(linum).stop().show().siblings().hide()
})

$('.o-m .ul-o li p').click(function(){
    $(this).css("color","#ffffff").closest("li").siblings().find("p").css("color","#5A5A5A");
});

$(function () {
    var slip = $('.li-mask');
    var a = $('.o-m .ul-o li:first');
    //初始化滑块
    slip.css({
        'top': parseInt(a.position().top) + 'px'
    });

    // $('.o-m .ul-o li').mouseenter(function () {
    $('.o-m .ul-o li').click(function () {
        //显示滑块
        if (slip.css('display') == 'none') {
            slip.show();
        }

        //移动滑块
        slip.stop().animate({
            top: parseInt($(this).position().top) + 'px'
        }, 300);
    });
});