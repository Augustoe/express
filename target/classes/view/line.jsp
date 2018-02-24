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
	<script src="<%=request.getContextPath()%>/dwr/interface/getLineInf.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/dwr/util.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/line/chart_line.js" type="text/javascript"></script> 	
	
<title>物流质量</title>
</head>
<body>
	<%@include file="NavBar.jsp"%>
	<div class="container-fluid main-content">
        <div class="row content-row">
        	<div class="row list-row input-row">
                <div class="row info-row">
                    <div class="row dynamic-info">
                    	<!-- select_line_type -->
                        <div class="form-group" id="my_line_type">
                        		<span class="n" id="choose_line">线路类型:</span>
                        		<select class="form-control select-staff" id="line_type" >
							            <option value="1" selected=true>营业厅-营业厅</option>
							            <option value="2">营业厅-中转中心</option>
							            <option value="3">中转中心-营业厅</option>
							            <option value="4">中转中心-中转中心</option>
							    </select>
                        </div>
                        <div class="form-group" id="my_deliver_type">
                        		<span class="n" id="choose_deliver">递送方式:</span>
                        		<select class="form-control select-staff" id="deliver_type" >
							            <option value="1" selected=true>汽运</option>
							            <option value="2">铁运</option>
							            <option value="3">空运</option>
							    </select>
                        </div>
                        <div class="form-group" id="my_city">
                        		<span class="n" id="choose_startCity">起点城市:</span>
                        		<select class="form-control select-staff" id="start_city" >
							    </select>
                        </div>
                        <div class="form-group" id="my_point">
                        		<span class="n" id="choose_startPoint">起点名称:</span>
                        		<select class="form-control select-staff" id="start_point" >
							    </select>
                        </div>
                        <span class="border-btn" onclick="confirm()">确认</span>
                        <br>
				        <br>
                        <div class="row dynamic-info">
                    		<p style="font-size:18px">本月线路质量图</p>
                    		<div id="chart_line" style="width:800px;height:350px;margin-top:0px;"></div>
                    	</div>
                    	<div class="row dynamic-info" style="display: inline">
                    		<p style="font-size:18px;">雷达图</p>
                    		<!-- <div id="chart_radar" style="width:400px;height:400px;margin-top:0px;display: inline-block"></div>
                    		<span id="rank" style="height:350px;margin-top:0px;vertical-align:top;display:inline-block">说明
                    		</span> -->
                    		<div class="row-fluid">
									<div class="row-fluid">
										<div class="col-md-7">
											<div id="chart_radar" style="width:100%;height:400px;margin-top:0px;display: inline-block"></div>
										</div>
										<div class="col-md-5">
											<p id="rank" style="height:350px;margin-top:0px;font-size:16px">
                    						</p>
										</div>
								</div>
							</div>
                    	</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
            
</body>
<script type="text/javascript">
	$(function(){
		$("#my_deliver_type").hide();
		addCity();
		
		$('#line_type').click(function(){
			selectLineType();
			});
		$('#start_city').click(function(){
    		selectCity();
    		});
	});

	function addCity(){
		var id=0;
		var name;
		$("#start_city").empty();
		getPositionInf.getCity(function(data){
			var json=eval('('+data+')');
			$.each(json, function(i, item) {
				   htm="<option value= \""+item.point_id+"\">"+item.point_name+"</option>";
				   if(i==0){
					   htm="<option value= \""+item.point_id+"\" selected=true>"+item.point_name+"</option>";
					   }
				   $("#start_city").append(htm);
			});
	    });
	}

	function selectCity(){
		var type=document.getElementById('line_type').value;
		var city= document.getElementById('start_city').value;
		if(type==4 || type==3){
			$("#start_point").empty();
			htm="<option value= \""+city+"\" selected=true>"+$("#city").find("option:selected").text()+"中转中心</option>";
			$("#start_point").append(htm);
		}else{
			getPositionInf.getDistribute(city,function(data){
				$("#start_point").empty();
				var json=eval('('+data+')');
				$.each(json, function(i, item) {
					   htm="<option value= \""+item.point_id+"\">"+item.point_name+"</option>";
					   if(i==0){
						   htm="<option value= \""+item.point_id+"\" selected=true>"+item.point_name+"</option>";
						   }
					   $("#start_point").append(htm);
				});
		    });
		}
		}

	function selectLineType(){
		var type=document.getElementById('line_type').value;
		if(type==4){
			$("#my_deliver_type").show();
		}else{
			$("#my_deliver_type").hide();
		}
	}

	function confirm(){
		/* var type=document.getElementById('line_type').value;
		var deliver=1;
		if(type==4){
			deliver=document.getElementById('deliver_type').value;
			}
		var start_place=document.getElementById('start_point').value; */
		init_chart_line();
		}

	
</script>
</html>