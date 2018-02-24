<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>导航栏</title>
<link href="${pageContext.request.contextPath}/css/common/navBar.css" type="text/css" rel="stylesheet"/>

</head>
<body>
<div class="top">
		<div class="logo">
			<img alt="" src="${pageContext.request.contextPath}/img/common/logo.png">
		</div>	
		
		<div class="container">
			<ul class="menu" >
				<li><a href="staff.jsp">员工绩效</a></li>
				<li><a href="line.jsp">物流质量</a></li>
				<li><a>业务拓展</a>
					<ul class="submenu">
						<li><a href="point.jsp">站点分析</a></li>
						<li><a href="customer.jsp">客户分析</a></li>
					</ul>
				</li>
				<li><a href="finance.jsp">财务分析</a></li>
			</ul>	
		</div>
		
		<div id="login-area" >
			<!--用户已登录 注销 -->
			<ul class="logined" style="list-style: none">
				<li class="header-signup" style="padding:0px ; margin: 0px">
                    <a class="logout" style="padding:0px ; margin: 0px; cursor:pointer">注销</a>
                </li>
				<li id="signined" class="header-signin" style="padding:0px ; margin: 0px">
                    <a style="padding:0px ; margin: 0px;margin-right: 5px">已登录
                </li>	
                <li>
					<img class='user' style="padding:0px ; margin: 0px;margin-top: 5px;" src='${pageContext.request.contextPath}/img/common/user.jpg' />
				</li>
			
			</ul>
		</div>
		</div>
		
		<script type="text/javascript">
			$(function(){
				showUserInfo();
			});
			
			//鍒ゆ柇鏄惁宸茬粡鐧诲綍
			function showUserInfo(){
				userName=getCookie("username");
				
				if(userName!= ''){
				}
				else{
					window.location.href="login.jsp";
					}
					
			}
			//注销
			$(".logout").click(function(){
				EmptyCookie("username");
				window.location.href="login.jsp";
			});
		</script>
</body>
</html>