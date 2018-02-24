function init_chart_line(id){
	var chart_line="chart_line";
	var chart_radar="chart_radar";
	var words="rank";
		
	var type=document.getElementById('line_type').value;
	var deliver=1;
	if(type==4){
		deliver=document.getElementById('deliver_type').value;
		}
	var start_place=document.getElementById('start_point').value;
	
	var toCity=[];
	var amount=[];
	var toolate=[];
	var quick=[];
	var rank=[];
	var radar=[];
	var rankword="同类型路线本月排名:<br/><br/>";
	getLineInf.getLineData(type,deliver,start_place,function(data){
		var json=eval('('+data+')');
		$.each(json, function(i, item) {
			toCity.push("~"+item.end_point);
			amount.push(parseInt(item.amount));
			toolate.push(parseInt(item.toolate));
			quick.push(parseInt(item.quick));
			rank.push(parseInt(item.rank));
			
			var radar_arr={
			         "name" : "~"+item.end_point,
			         "value" : [parseInt(item.a_grade),parseInt(item.t_grade),
			        	 parseInt(item.q_grade),parseInt(item.b_grade),parseInt(item.l_grade)]
			}
			radar.push(radar_arr);
			rankword=rankword+item.start_point+'~'+item.end_point+"&nbsp&nbsp&nbsp"+item.rank+"<br/>";
		});
		
		$("#"+words).empty();
		$("#"+words).append(rankword);
		
		var chartContainer_line = document.getElementById(chart_line);
	    var myChart_line = echarts.init(chartContainer_line);
	    myChart_line.setOption(
	    		option = {
	    			    tooltip: {
	    			        trigger: 'axis',
	    			        axisPointer: {
	    			            type: 'cross',
	    			            crossStyle: {
	    			                color: '#999'
	    			            }
	    			        }
	    			    },
	    			    toolbox: {
	    			        feature: {
	    			            dataView: {show: true, readOnly: false},
	    			            magicType: {show: true, type: ['line', 'bar']},
	    			            restore: {show: true},
	    			            saveAsImage: {show: true}
	    			        }
	    			    },
	    			    legend: {
	    			        data:['总件数','慢于预期','快于预期']
	    			    },
	    			    xAxis: [
	    			        {
	    			            type: 'category',
	    			            data: toCity,
	    			            axisPointer: {
	    			                type: 'shadow'
	    			            }
	    			        }
	    			    ],
	    			    yAxis: [
	    			        {
	    			            type: 'value',
	    			            name: '件数',
	    			            axisLabel: {
	    			                formatter: '{value}'
	    			            }
	    			        },
	    			    ],
	    			    series: [
	    			        {
	    			            name:'总件数',
	    			            type:'bar',
	    			            data:amount
	    			        },
	    			        {
	    			            name:'慢于预期',
	    			            type:'bar',
	    			            data:toolate
	    			        },
	    			        {
	    			            name:'快于预期',
	    			            type:'bar',
	    			            data:quick
	    			        }
	    			    ]
	    			}
	    		);
	    
	    var chartContainer_radar = document.getElementById(chart_radar);
	    var myChart_radar = echarts.init(chartContainer_radar);
	    myChart_radar.setOption(option = {
	    	    tooltip: {},
	    	    legend: {
	    	        data: toCity
	    	    },
	    	    radar: {
	    	        // shape: 'circle',
	    	        indicator: [
	    	           { name: '件数评分', max: 20},
	    	           { name: '慢件评分', max: 20},
	    	           { name: '快件评分', max: 20},
	    	           { name: '破损评分', max: 20},
	    	           { name: '丢失评分', max: 20},
	    	        ]
	    	    },
	    	    series: [{
	    	        name: '物流质量评分',
	    	        type: 'radar',
	    	        // areaStyle: {normal: {}},
	    	        data :radar
	    	    }]
	    	});
	
	
	});
	
	
}