$(document).ready(function(){
    $("body").on("focus()","input",function(){

    })
});

function login(){
	
    var username = $("#username-login").val();
    var password = $("#password-login").val();

    if(username==""){
        $("#error-login").text("账号不能为空！");
        setTimeout("$('#error-login').text('');",1000);
        return false;
    }else if(password==""){
        $("#error-login").text("密码不能为空！");
        setTimeout("$('#error-login').text('');",1000);
        return false;
    }
    if(password.length>20||password.length<6){
        $("#error-login").text("密码长度为6-20！");
        setTimeout("$('#error-login').text('');",1000);
        return false;
    }
    
    if((username=="m00001")&&(password=="123456")){
    	$("#error-login").text("密码正确");
		setCookie("username",username);
		window.location.href="staff.jsp";
    }else{
    	$("#error-login").text("密码和用户名不匹配");
    }
    return true;
}

function forgetPwd() {
    $("#error-login").text("暂未实现，敬请期待！");
    setTimeout("$('#error-login').text('');",1000);
    return false;
}
