$(document).ready(function () {
    $(".btn-second").css("display","none");
    $(".btn-first").css("display","block");
    $(".choose-row").css("display","none");
});

function refresh() {
    window.location.reload();
}
function modifyInfo() {
    $(".info-input").attr("disabled",false);
    $(".btn-second").css("display","block");
    $(".btn-first").css("display","none");
}

function modifyInfoSave() {
//	var username="${customer.cname}";
//	alert("here user="+username);
    var cardnumber = $("#info-cardnumber").val();
    
    var reg = /^[0-9]*$/;
    if(cardnumber==""){
        $("#error-dynamic-info").text("卡号不能为空！");
        setTimeout("$('#error-dynamic-info').text('');",1000);
        return false;
    }else if(!reg.test(cardnumber)){
        $("#error-dynamic-info").text("卡号须为数字！");
        setTimeout("$('#error-dynamic-info').text('');",1000);
        return false;
    }else if((cardnumber.length)!=12){
    	$("#error-dynamic-info").text("卡号须为12位！");
        setTimeout("$('#error-dynamic-info').text('');",1000);
        return false;
    }
       
    $.ajax({
        type : "post",
        url : "changeCard",
        data : 
        {//设置数据源
//        	username : username,
        	cardnumber : cardnumber
        },
        dataType : "json",//设置需要返回的数据类型
        success : function(data) {
        	if(data.message!="succeed"){
        		$("#error-dynamic-info").text(data.message);
        		setTimeout("$('#error-modify-psw').text('');",1000);
        	    setTimeout(refresh,1200);
//        		return false;
        	}
        },
        error : function(data) {
          $("error-dynamic-info").text("连接失败，请重试");
          setTimeout("$('#error-dynamic-info').text('');",1000);
          return false;
        }
    });
    
    
    $("#info-cardnumber").val(cardnumber);
    $(".btn-second").css("display","none");
    $(".btn-first").css("display","block");
    $(".choose-row").css("display","none");
    
//    $("#error-dynamic-info").text("修改成功");
//    setTimeout("$('#error-modify-psw').text('');",500);
//    setTimeout(refresh,1200);
    
    return true;
}

function modifyCancel() {
    refresh();
}

function modifyPsw() {
    clear();
    $(".modify-psw-row").css("display","block");
}

function modifyPswSave() {
    var password = $("#modify-psw-password").val();
    var passwordN = $("#modify-psw-password-new").val();
    var passwordNA = $("#modify-psw-password-new-again").val();
    var vId = $("#vId").val();
    if(password==""){
        $("#error-modify-psw").text("原密码不能为空！");
        setTimeout("$('#error-modify-psw').text('');",1000);
        return false;
    }else if(passwordN=="" || passwordNA==""){
        $("#error-modify-psw").text("新密码不能为空！");
        setTimeout("$('#error-modify-psw').text('');",1000);
        return false;
    }else if(passwordNA!=passwordN){
        $("#error-modify-psw").text("前后密码不一致！");
        setTimeout("$('#error-modify-psw').text('');",1000);
        return false;
    }

    $('#error-modify-psw').text("修改成功");
    setTimeout("$('#error-modify-psw').text('');",500);
    setTimeout(refresh,1200);
}

function clear(){
    $('.choose-row .reg-input').val("");
}