//预约产品演示 form 表单弹出
$("#center_sky_font").on('click', function viewModalForm() {
    var modalForm = document.getElementById("controlViewModalForm");
    modalForm.style.display = "";
    //每次打开文本框的dialog默认选择一个演示产品。
    $("input[name='orderproduct']")[0].checked = true;
});

//关闭view
$(".myformbutton").on('click', closeViewModalForm);

function closeViewModalForm() {
    var modalForm = document.getElementById("controlViewModalForm");
    modalForm.style.display = "none";
    cleanForm();
}

// 预约演示表单验证
var companynameisok, companycustomerisok, phonenumisok, orderproductisok = true;

$("input[name='companyname']").blur(function () {
    if ($("input[name='companyname']").val() == "") {
        $("#errormsg_company").css("display", "");
        companynameisok = false;
    } else {
        $("#errormsg_company").css("display", "none");
        companynameisok = true;
    }
});
$("input[name='companycustomer']").blur(function () {
    if ($("input[name='companycustomer']").val() == "") {
        $("#errormsg_name").css("display", "");
        companycustomerisok = false;
    } else {
        $("#errormsg_name").css("display", "none");
        companycustomerisok = true;
    }
});
$("input[name='phonenum']").blur(function () {
    if ($("input[name='phonenum']").val() == "") {
        $("#errormsg_phone").text("请填写电话号码。");
        phonenumisok = false;
    } else {
        if (!checkTel($("input[name='phonenum']").val())) {
            $("#errormsg_phone").text("正确号码格式：158xxxx8888/010-xxxx8888");
            phonenumisok = false;
        } else {
            $("#errormsg_phone").text("");
            phonenumisok = true;
        }
    }
});
$("input[name='orderproduct']").change(function () {
    //复选框数组
    var orderProductsArr = [];
    $("input[name='orderproduct']:checked").each(function (i, n) {
        orderProductsArr[i] = $(this).val();
    });
    if (orderProductsArr.length == 0) {
        $("#errormsg_checkbox").css("display", "");
        orderproductisok = false;
    } else {
        $("#errormsg_checkbox").css("display", "none");
        orderproductisok = true;
    }
});

//点击发送邮件事件
$(".submitstyle").on('click', function sendMailInfo() {
    //验证信息是否全部填写。
    // console.log("companynameisok",companynameisok,"companycustomerisok",companycustomerisok,"phonenumisok",phonenumisok,"orderproductisok",orderproductisok)
    if (!(companynameisok && companycustomerisok && phonenumisok && orderproductisok)) {
        if ($("input[name='companyname']").val() == "") {
            $("#errormsg_company").css("display", "");
        }
        if ($("input[name='companycustomer']").val() == "") {
            $("#errormsg_name").css("display", "");
        }
        if ($("input[name='phonenum']").val() == "") {
            $("#errormsg_phone").text("请填写电话号码。");
        }
        return;
    }
    //复选框选中的值
    var orderProductsArr = [];
    $("input[name='orderproduct']:checked").each(function (i, n) {
        orderProductsArr[i] = $(this).val();
    });
    // 封装页面中获取的数据对象
    var emailInfo = {
        conpanyName: $("input[name='companyname']").val(),
        conpanyCustomer: $("input[name='companycustomer']").val(),
        phoneNum: $("input[name='phonenum']").val(),
        orderProducts: orderProductsArr,
    };
    $.ajax({
        type: "POST",
        url: "/mail/sendSimpleMail",
        data: JSON.stringify(emailInfo),
        async: true,
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        success: function (data) {
            console.log(data.msg)
        }
    });
    alertMsg({msg: "您的预约申请已经提交，稍后我们会及时与您联系。", state: 200});
});

//清空表单
function cleanForm() {
    $("input[name='companyname']").val("");
    $("input[name='companycustomer']").val("");
    $("input[name='phonenum']").val("");
    $("input[name='orderproduct']").each(function (i, n) {
        $(this)[0].checked = false;
    });
    $("#errormsg_company").css("display", "none");
    $("#errormsg_name").css("display", "none");
    $("#errormsg_phone").text("");
    $("#errormsg_checkbox").css("display", "none");
}

// 电话验证
function checkTel(tel) {
    var mobile = /^1[3-9]\d{9}$/, phone = /^0\d{2,3}-?\d{7,8}$/;
    return mobile.test(tel) || phone.test(tel);
}

// 预约成功、失败提示弹窗
function alertMsg(data) {
    var isIndexUrl = window.location.href.indexOf("static/index.html");
    if (data.state == 200) {
        if (isIndexUrl != -1) {
            $("#isokorderimg").attr("src", "./image/index/orderproduct_true.png");
        } else {
            $("#isokorderimg").attr("src", "../image/index/orderproduct_true.png");
        }

        $("#isokordertatle").text("预约成功");
        $("#isokordermsg").text(data.msg);
    } else {
        if (isIndexUrl != -1) {
            $("#isokorderimg").attr("src", "./image/index/orderproduct_false.png");
        } else {
            $("#isokorderimg").attr("src", "../image/index/orderproduct_false.png");
        }
        $("#isokordertatle").text("预约失败");
        $("#isokordermsg").text(data.msg);
    }
    $(".mymodalbox").css("display", "");
    setTimeout("closeMsgAndDia()", 2500);
}

//关闭弹窗和dia
function closeMsgAndDia() {
    $(".mymodalbox").css("display", "none");
    closeViewModalForm();
}




