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
	<script src="<%=request.getContextPath()%>/dwr/interface/getStaffPerf.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/dwr/util.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/staff/staffchart.js" type="text/javascript"></script> 	
<title>员工绩效</title>
</head>
<body>
	<%@include file="NavBar.jsp"%>
	<div class="container-fluid main-content">
        <div class="row content-row">
        	<div class="row list-row input-row">
                <div class="row info-row">
                    <div class="row dynamic-info">
                    	<!-- select_staff -->
                        <div class="form-group" id="select_staff">
                        		<span class="n" id="choose_staff">选择职位:</span>
                        		<select class="form-control select-staff" id="staff" >
							            <option value="poster" selected=true>快递员</option>
							            <option value="distribute">营业厅业务员</option>
							            <option value="transit">中转中心业务员</option>
							            <option value="storehouse">仓库管理员</option>
							            <option value="driver">司机</option>
							    </select>
                        </div>
                        <!-- select_place -->
                        <div class="form-group" id="select_city">
                        		<span class="n" id="choose_city">选择城市:</span>
                        		<select class="form-control select-staff" id="city" >
							    </select>
                        </div>
                        <!-- select_point -->
                        <div class="form-group" id="select_point">
                        		<span class="n" id="choose_point">选择地点:</span>
                        		<select class="form-control select-staff" id="point" >
							    </select>
                        </div>
                        <!-- select_place -->
                        <div class="form-group" id="select_person">
                        		<span class="n" id="choose_person">选择员工:</span>
                        		<select class="form-control select-staff" id="person" >
							    	<!-- <option selected=true>选择人员</option> -->
							    </select>
                        </div>
                        <span class="border-btn" onclick="confirm()">确认</span>
                        <br>
				        <br>
                        
							<div class="tabbable" id="tabs-945477">
								<ul class="nav nav-tabs">
									<a></a>
									<p></p>
									<li class="active">
										<a href="#panel-890595" data-toggle="tab">按天</a>
									</li>
									<li>
										<a href="#panel-222716" data-toggle="tab">按月</a>
									</li>
								</ul>
								<div class="tab-content">
									<div class="tab-pane active " id="panel-890595">
										<p></p>
										<div id="chart_day" style="width:800px;height:450px;margin-top:0px;"></div>
									</div>
									<div class="tab-pane " id="panel-222716">
										<p></p>
										<div id="chart_month" style="width:800px;height:450px;margin-top:0px;"></div>
									</div>
								</div>
							</div>
                        
                	</div>
                </div>
        	</div>
        </div>
    </div>
    
    
    <script type="text/javascript">
   /*  addCity();
    alert("city="+document.getElementById('city').value);
    selectPoint(); */

    
	   $(function(){
	    	addCity();
	    	$('#city').click(function(){
	    		selectStaff();
	    		});
	    	$('#staff').click(function(){
	    		selectStaff();
	    	});
	    	$('#point').click(function(){
	    		selectPoint();
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

		function selectStaff(){
			var staff = document.getElementById('staff').value;
			/* alert($("#staff").find("option:selected").text()); */
			var city= document.getElementById('city').value;
			var type = 0; // 0:poster,distribute,driver
			switch(staff){
			case "transit": type=1; break;
			case "storehouse": type=1; break;
			default:
			}
			if(type==1){
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
		function selectCity(){
			selectStaff();
		}

		function selectPoint(){
			var staff = document.getElementById('staff').value;
			var point = document.getElementById('point').value;
			getPositionInf.getStaff(staff,point,function(data){
				$("#person").empty();
				var json=eval('('+data+')');
				$.each(json, function(i, item) {
					   htm="<option value= \""+item.staff_id+"\">"+item.staff_name+"</option>";
					   if(i==0){
						   htm="<option value= \""+item.staff_id+"\" selected=true>"+item.staff_name+"</option>";
						   }
					   $("#person").append(htm);
				});
		    });
		}
		function confirm(){
			var person = document.getElementById('person').value;
			paintStaffchart('chart_day',"day",person);
			paintStaffchart('chart_month',"month",person);
			}
    </script>
    
</body>
</html>