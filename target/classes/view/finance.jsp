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
	<script src="<%=request.getContextPath()%>/dwr/interface/getFinanceInf.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/dwr/util.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/finance/chart_month.js" type="text/javascript"></script> 	
	<script src="<%=request.getContextPath()%>/js/finance/chart_place.js" type="text/javascript"></script> 	

<title>财务分析</title>
</head>
<body>
	<%@include file="NavBar.jsp"%>
	<div class="container-fluid main-content">
        <div class="row content-row">
        	<div class="row list-row input-row">
                <div class="row info-row">
                    <div class="row dynamic-info">
                    	<p style="font-size:18px">今年以来月收比较图</p>
                    	<div id="chart_finance_month" style="width:800px;height:350px;margin-top:0px;"></div>
                    </div>
                </div>
            </div>
            <div class="row list-row input-row">
                <div class="row info-row">
                    <div class="row dynamic-info">
                    	<p style="font-size:18px">当月各地区收入比较</p>
                    	<div id="chart_place_bar" style="width:100%;height:350px;margin-top:0px;"></div>
                    	<div id="pie_group" style="width:100%;margin-top:0px;">
	                    	<div id="chart_place_pie1" style="width:33%;height:300px;margin-top:0px;float:left"></div>
	                    	<div id="chart_place_pie2" style="width:33%;height:300px;margin-top:0px;float:left"></div>
	                    	<div id="chart_place_pie3" style="width:33%;height:300px;margin-top:0px;float:left"></div>
                    	</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript">
	$(function(){
		init_chart_month("chart_finance_month");
		init_chart_place("chart_place_bar","chart_place_pie");
	});
	
</script>
</html>