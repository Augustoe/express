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
	<script src="<%=request.getContextPath()%>/dwr/interface/getPointInf.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/dwr/util.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/point/chart_point.js" type="text/javascript"></script> 	
	
<title>站点分析</title>
	<%@include file="NavBar.jsp"%>
	<div class="container-fluid main-content">
        <div class="row content-row">
        	<div class="row list-row input-row">
                <div class="row info-row">
                    <div class="row dynamic-info">
                    	<!-- select_point_type -->
                        <div class="form-group" id="my_point_type">
                        		<span class="n" id="choose_point">站点类型:</span>
                        		<select class="form-control select-staff" id="point_type" >
							            <option value="1" selected=true>营业厅</option>
							            <option value="2">中转中心</option>
							    </select>
                        </div>
                        <div class="form-group" id="my_city">
                        		<span class="n" id="choose_City">站点城市:</span>
                        		<select class="form-control select-staff" id="city" >
							    </select>
                        </div>
                        <div class="form-group" id="my_point">
                        		<span class="n" id="choose_Point">站点名称:</span>
                        		<select class="form-control select-staff" id="point" >
							    </select>
                        </div>
                        <span class="border-btn" onclick="confirm()">确认</span>
                        <br>
				        <br>
                        
                        <div class="tabbable" id="tabs-pointer">
								<ul class="nav nav-tabs">
									<a></a>
									<p></p>
									<li class="active">
										<a href="#panel-day" data-toggle="tab">按天</a>
									</li>
									<li>
										<a href="#panel-month" data-toggle="tab">按月</a>
									</li>
								</ul>
								<div class="tab-content">
									<div class="tab-pane active " id="panel-day">
										<p></p>
										<div id="chart_point_day" style="width:800px;height:450px;margin-top:0px;"></div>
										<p id="day_summary" style="font-size:16px">月度总结：<br></p>
									</div>
									<div class="tab-pane " id="panel-month">
										<p></p>
										<div id="chart_point_month" style="width:800px;height:450px;margin-top:0px;"></div>
										<p id="month_summary" style="font-size:16px">年度总结：<br></p>
									</div>
								</div>
							</div>
							
                        
                    </div>
                </div>
            </div>
        </div>
    </div>
</head>
<body>

</body>
<script type="text/javascript">
	$(function(){
		addCity()
		$('#city').click(function(){
    		selectCity();
    		});
	});

	function addCity(){
		var id=0;
		var name;
		$("#city").empty();
		getPositionInf.getCity(function(data){
			var json=eval('('+data+')');
			$.each(json, function(i, item) {
				   htm="<option value= \""+item.point_id+"\">"+item.point_name+"</option>";
				   if(i==0){
					   htm="<option value= \""+item.point_id+"\" selected=true>"+item.point_name+"</option>";
					   }
				   $("#city").append(htm);
			});
	    });
	}

	function selectCity(){
		var type=document.getElementById('point_type').value;
		var city= document.getElementById('city').value;
		if(type==2){
			$("#point").empty();
			htm="<option value= \""+city+"\" selected=true>"+$("#city").find("option:selected").text()+"中转中心</option>";
			$("#point").append(htm);
		}else{
			getPositionInf.getDistribute(city,function(data){
				$("#point").empty();
				var json=eval('('+data+')');
				$.each(json, function(i, item) {
					   htm="<option value= \""+item.point_id+"\">"+item.point_name+"</option>";
					   if(i==0){
						   htm="<option value= \""+item.point_id+"\" selected=true>"+item.point_name+"</option>";
						   }
					   $("#point").append(htm);
				});
		    });
		}
		}

	function confirm(){
		var pointer_id=document.getElementById('point').value;
		paintStaffchart('chart_point_day','day_summary',"day",pointer_id);
		paintStaffchart('chart_point_month','month_summary',"month",pointer_id);
		}
	
</script>
</html>