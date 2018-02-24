function init_chart_month(id){
	var date1=[];
	var date2=[];
	var income1=[];
	var income2=[];
	var cost1=[];
	var cost2=[];
	var balance1=[];
	var balance2=[];
	
	var colors = ['#5793f3', '#d14a61', '#675bba'];

	$("#"+id).empty();
	
	getFinanceInf.getMonth(function(data){
		var json=eval('('+data+')');
		$.each(json, function(i, item) {
			if(i<=5){
				date1.push(item.date);
				income1.push(parseInt(item.Income));
				cost1.push(parseInt(item.cost));
				balance1.push(parseInt(item.balance));
			}else{
				date2.push(item.date);
				income2.push(parseInt(item.Income));
				cost2.push(parseInt(item.cost));
				balance2.push(parseInt(item.balance));
			}
		});
			
			var chartContainer = document.getElementById(id);
		    var myChart = echarts.init(chartContainer);
		    myChart.setOption(option = {
		    	    color: colors,
		    	    tooltip: {
		    	        trigger: 'none',
		    	        axisPointer: {
		    	            type: 'cross',
		    	            crossStyle: {
				                color: '#999'
				            }
		    	        }
		    	    },
		    	    legend: {
		    	        data:['去年同期月净收','当年月净收','去年同期月收入','当年月收入'],
		    	    },
//		    	    grid: {
//		    	        top: 70,
//		    	        bottom: 50
//		    	    },
		    	    xAxis: [
		    	        {
		    	            type: 'category',
		    	            axisTick: {
		    	                alignWithLabel: true
		    	            },
		    	            axisLine: {
		    	                onZero: false,
		    	                lineStyle: {
		    	                    color: colors[1]
		    	                }
		    	            },
		    	            axisPointer: {
		    	                label: {
		    	                    formatter: function (params) {
		    	                        return '月净收  ' + params.value
		    	                            + (params.seriesData.length ? '：' + params.seriesData[0].data : '');
		    	                    }
		    	                }
		    	            },
		    	            data: date1
		    	        },
		    	        {
		    	            type: 'category',
		    	            axisTick: {
		    	                alignWithLabel: true
		    	            },
		    	            axisLine: {
		    	                onZero: false,
		    	                lineStyle: {
		    	                    color: colors[0]
		    	                }
		    	            },
		    	            axisPointer: {
		    	                label: {
		    	                    formatter: function (params) {
		    	                        return '月净收  ' + params.value
		    	                            + (params.seriesData.length ? '：' + params.seriesData[0].data : '');
		    	                    }
		    	                }
		    	            },
		    	            data: date2
		    	        }
		    	    ],
		    	    yAxis: [
		    	        {
		    	            type: 'value',
		    	            min: '10000000'
		    	        }
		    	    ],
		    	    series: [
		    	        {
		    	            name:'去年同期月净收',
		    	            type:'line',
		    	            xAxisIndex: 1,
		    	            smooth: true,
		    	            data: balance2
		    	        },
		    	        {
		    	            name:'当年月净收',
		    	            type:'line',
		    	            smooth: true,
		    	            data: balance1
		    	        },
		    	        {
		    	            name:'去年同期月收入',
		    	            type:'line',
		    	            xAxisIndex: 1,
		    	            smooth: true,
		    	            data: income2
		    	        },
		    	        {
		    	            name:'当年月收入',
		    	            type:'line',
		    	            smooth: true,
		    	            data: income1
		    	        }
		    	    ]
		    	}
		    );
		
	});
		
}