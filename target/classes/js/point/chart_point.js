function paintStaffchart(chart_id,word_id,type,point_id){
	var date=[];
	var amount=[];
	var weight=[];
	var price=[];
	
	var colors = ['#6699A1', '#DC9FB4', '#6A4C9C'];
	var count=0;
	
	getPointInf.getPointData(point_id,type,function(data){
		var json=eval('('+data+')');
		$.each(json, function(i, item) {
			date.push(item.date);
			amount.push(parseInt(item.amount));
			weight.push(parseInt(item.weight));
			price.push(parseInt(item.price));
			
			if(type=="day"){
				if(point_id<100){
					if(item.amount>690){ count++; }
				}else{
					if(item.amount>280){ count++; }
				}
			}
		});
		
		
		if(type=="day"){
			$("#"+word_id).empty();
			$("#"+word_id).append("月度总结：<br>当前站点本月共超负荷运营"+count+"天。<br>");
			
			var name="营业厅";
			if(point_id<100){ name="中转中心"; }
			
			if(count>20){
				 $("#"+word_id).append("属于严重超负荷运营状况，建议在本地区增设新的"+name+"。<br>"); 
			}else if(count>10){ $("#"+word_id).append("属于超负荷运营状况，建议在本地区增设新的"+name+"。<br>"); }
			else if(count>5){ $("#"+word_id).append("属于轻微超负荷运营状况，建议对本地区的"+name+"进行协调。<br>"); }
			else if(count>0){ $("#"+word_id).append("属于正常负荷运营状况。<br>"); }
			else{ $("#"+word_id).append("属于中低负荷运营状况，建议对本地区的"+name+"进行协调。<br>"); }
		}else{
			$("#"+word_id).empty();
			$("#"+word_id).append("月度总结：<br>下个月的运营预期为快递"+amount[0]+"件，总重"+weight[0]+"kg，总额"+price[0]+"￥。<br>");
		}
		
		
		var chartContainer = document.getElementById(chart_id);
	    var myChart = echarts.init(chartContainer);
	    myChart.setOption(option = {
	    	    color: colors,
	    	    tooltip: {
	    	        trigger: 'axis',
	    	        axisPointer: {
	    	            type: 'cross'
	    	        }
	    	    },
	    	    grid: {
	    	        right: '20%'
	    	    },
	    	    toolbox: {
	    	        feature: {
	    	            dataView: {show: true, readOnly: false},
	    	            restore: {show: true},
	    	            saveAsImage: {show: true}
	    	        }
	    	    },
	    	    legend: {
	    	        data:['快递件数','总重','总价']
	    	    },
	    	    xAxis: [
	    	        {
	    	            type: 'category',
	    	            axisTick: {
	    	                alignWithLabel: true
	    	            },
	    	            data: date
	    	        }
	    	    ],
	    	    yAxis: [
	    	        {
	    	            type: 'value',
	    	            name: '快递件数',
	    	            position: 'right',
	    	            axisLine: {
	    	                lineStyle: {
	    	                    color: colors[0]
	    	                }
	    	            },
	    	            axisLabel: {
	    	                formatter: '{value}件'
	    	            }
	    	        },
	    	        {
	    	            type: 'value',
	    	            name: '总重',
	    	            position: 'right',
	    	            offset: 80,
	    	            axisLine: {
	    	                lineStyle: {
	    	                    color: colors[1]
	    	                }
	    	            },
	    	            axisLabel: {
	    	                formatter: '{value}kg'
	    	            }
	    	        },
	    	        {
	    	            type: 'value',
	    	            name: '总价',
	    	            position: 'left',
	    	            axisLine: {
	    	                lineStyle: {
	    	                    color: colors[2]
	    	                }
	    	            },
	    	            axisLabel: {
	    	                formatter: '{value}￥'
	    	            }
	    	        }
	    	    ],
	    	    series: [
	    	        {
	    	            name:'快递件数',
	    	            type:'bar',
	    	            data:amount
	    	        },
	    	        {
	    	            name:'总重',
	    	            type:'bar',
	    	            yAxisIndex: 1,
	    	            data:weight
	    	        },
	    	        {
	    	            name:'总价',
	    	            type:'line',
	    	            yAxisIndex: 2,
	    	            data:price
	    	        }
	    	    ]
	    	}
	    		);
	});
}