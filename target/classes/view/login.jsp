<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common/login.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common/bootstrap.css" />
	
	<script src="${pageContext.request.contextPath}/js/common/jquery-3.1.1.min.js" type="text/javascript" ></script>
	<script src="${pageContext.request.contextPath}/js/common/bootstrap.js" type="text/javascript" ></script>
	<script src="${pageContext.request.contextPath}/js/common/cookie.js" type="text/javascript" ></script>
	<script src="${pageContext.request.contextPath}/js/login.js" type="text/javascript" ></script>

<title>用户登录</title>
</head>
<body>
	<div class="container-fluid main-content">
        <div class="row" id="logReg">
            <div class="row">
                <div class="top-wrapper">
                    <img src="${pageContext.request.contextPath}/img/common/logo_co.png">
                    <p></p>
                </div>
            </div>

            <div class="row" id="switch-content">
                <div class="login">
                    <div class="login-wrapper">
                        <form class="form-login">
                            <div class="input-wrapper">
                                <input type="text" name="username" id="username-login" placeholder="用户名">
                            </div>

                            <div class="input-wrapper">
                                <input type="password" name="password" id="password-login" placeholder="密码">
                                <a onclick="forgetPwd()" data-href="/forgetPwd" class="tip">忘记密码</a>
                            </div>
                            <input type="button" class="button btn-block submit-login" value="登录" onclick="login()">
            				<div class="clearfix-wrapper">
                                <div class="errorField" id="error-login"></div>
                            </div>
            		</div>
        		</div>
        		<br>
		        <br>
		        <br>
		        <br>
		        <br>
		        <br>
		        <br>
		        <br>
		        <br>
		        <br>
		        
    		</div>
    	</div>
    </div>
</body>
</html>