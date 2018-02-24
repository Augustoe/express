<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common/bootstrap.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common/common.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common/info.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common/select_here.css" />
	
	<script src="${pageContext.request.contextPath}/js/common/jquery-3.1.1.min.js" type="text/javascript" ></script>
	<script src="${pageContext.request.contextPath}/js/common/bootstrap.js" type="text/javascript" ></script>
	<script src="${pageContext.request.contextPath}/js/common/echarts.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/common/cookie.js" type="text/javascript" ></script>
	
	<script src="<%=request.getContextPath()%>/dwr/engine.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/dwr/interface/getPositionInf.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/dwr/interface/getCustomerInf.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/dwr/util.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/customer/chart_customer.js" type="text/javascript"></script> 	
	
<title>客户分析</title>
</head>
<body>
	<%@include file="NavBar.jsp"%>
	<div class="container-fluid main-content">
        <div class="row content-row">
        	<div class="row list-row input-row">
                <div class="row info-row">
                    <div class="row dynamic-info">
                    	<div class="row dynamic-info">
                    	
                    	<div class="form-group" id="my_point_type">
                    		<span class="n" id="choose_point"  style="float: left">选择类型:</span>
                    		<span class="border-btn" style="float: left" id="amount" onclick="confirm(this)">总件数</span>
                    		<span class="border-btn" style="float: left" id="price" onclick="confirm(this)">总金额</span>
                    		<span class="border-btn" style="float: left" id="weight" onclick="confirm(this)">总重量</span>
                    		<span class="border-btn" style="float: left" id="distance" onclick="confirm(this)">总路程</span>
                    	
                    	</div>
                    	</div>
                    	<div class="row dynamic-info">
                    		<p style="font-size:18px;padding-top: 20px">排名前10的客户信息</p>
                    		<div id="chart_customer" style="width:800px;height:350px;margin-top:0px;"></div>
                    	</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript">
	function confirm(obj){
			var key=obj.id;
			init_chart_customer('chart_customer',key);
		}
</script>
</html>