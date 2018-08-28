$(function () {
    $("#fadeout_nav-sub").on({
        mouseover: function () {
            var isIndexUrl = window.location.href.indexOf("/index.html"),
                isModuleIndexUrl = window.location.href.indexOf("/module/");
            if ($('.top_position').width() > 925 && isIndexUrl != -1) {
                $("#phoneview-delhref-925px").attr("href", "./module/visualization-index.html");
            } else if ($('.top_position').width() > 925 && isModuleIndexUrl != -1) {
                $("#phoneview-delhref-925px").attr("href", "./visualization-index.html");
            } else {
                // $("#phoneview-delhref-925px").attr("href", "javascript:return false;");
                $("#phoneview-delhref-925px").attr("href", "javascript:void(0);");
            }
            $(".nav-sub").css("display", "block");
        },
        mouseout: function () {
            $(".nav-sub").css("display", "none");
        }
    });
});