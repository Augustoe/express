function init_chart_place(bar_id,pie_id){
	var date=[];
	var city=['北京','天津','上海','杭州','南京'];
	var income=[];
	var cost=[];
	var balance=[];

	$("#"+bar_id).empty();
	
	getFinanceInf.getDomain(function(data){
		var json=eval('('+data+')');
		$.each(json, function(i, item) {
				date.push(item.date);
				income.push(parseInt(item.Income));
				cost.push(parseInt(item.cost)*(-1));
				balance.push(parseInt(item.balance));
		});
			
			var chartContainer_bar = document.getElementById(bar_id);
		    var myChart_bar = echarts.init(chartContainer_bar);
		    myChart_bar.setOption(option = {
		    tooltip : {
		        trigger: 'axis',
		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		        }
		    },
		    legend: {
		        data:['利润', '收入','支出' ]
		    },
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '3%',
		        containLabel: true
		    },
		    xAxis : [
		        {
		            type : 'value'
		        }
		    ],
		    yAxis : [
		        {
		            type : 'category',
		            axisTick : {show: false},
		            data : city
		        }
		    ],
		    series : [
		        {
		            name:'利润',
		            type:'bar',
		            label: {
		                normal: {
		                    show: true,
		                    position: 'inside'
		                }
		            },
		            data:balance
		        },
		        {
		            name:'收入',
		            type:'bar',
		            stack: '总量',
		            label: {
		                normal: {
		                    show: true
		                }
		            },
		            data:income
		        },
		        {
		            name:'支出',
		            type:'bar',
		            stack: '总量',
		            label: {
		                normal: {
		                    show: true,
		                    position: 'left'
		                }
		            },
		            data:cost
		        }
		    ]
		}
		);
		    
		    var color1=['#601a19','#892623','#a32c29','#b7322e','#d76662'];//利润
		    var color2=['#2f4554','#416a86','#3f7ba4','#5195c4','#71b1dc'];//收入
		    var color3=['#61a0a8','#77bac3','#8eced6','#9cdde5','#beeff5'];//支出
		    var color4=['#577C8A','#A5DEE4','#77969A','#3A8FB7','#566C73']
		    var radius_min=30;
		    var radius_max=90;
		    
		    var income_json=[];
		    var cost_json=[];
		    var balance_json=[];
		    for(var i=0;i<5;i++){
		    	var cost_arr={
				         "name" : city[i],
				         "value" : cost[i]*(-1)
				     }
		    	cost_json.push(cost_arr);
		    	var balance_arr={
				         "name" : city[i],
				         "value" : balance[i]
				     }
		    	balance_json.push(balance_arr);
		    	var income_arr={
				         "name" : city[i],
				         "value" : income[i]
				     }
		    	income_json.push(income_arr);
		    }
		    //pie1
		    var chartContainer_pie = document.getElementById(pie_id+"1");
		    var myChart_pie1 = echarts.init(chartContainer_pie);
		    myChart_pie1.setOption(option = {
		    	    title : {
		    	        subtext: '利润',
		    	        x:'center'
		    	    },
		    	    tooltip : {
		    	        trigger: 'item',
		    	        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    	    },
		    	    calculable : true,
		    	    series : [
		    	        {
		    	            color:color2,
		    	        	name:'本月利润',
		    	            type:'pie',
		    	            radius : [radius_min, radius_max],
		    	            center : ['50%', '50%'],
		    	            roseType : 'radius',
		    	            label: {
		    	                normal: {
		    	                    show: false
		    	                },
		    	                emphasis: {
		    	                    show: true
		    	                }
		    	            },
		    	            lableLine: {
		    	                normal: {
		    	                    show: false
		    	                },
		    	                emphasis: {
		    	                    show: true
		    	                }
		    	            },
		    	            data:balance_json
		    	        }
		    	    ]
		    	});
		  //pie2
		    var chartContainer_pie = document.getElementById(pie_id+"2");
		    var myChart_pie2 = echarts.init(chartContainer_pie);
		    myChart_pie2.setOption(option = {
		    	    title : {
		    	        subtext: '收入',
		    	        x:'center'
		    	    },
		    	    tooltip : {
		    	        trigger: 'item',
		    	        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    	    },
		    	    calculable : true,
		    	    series : [
		    	        {
		    	            color:color2,
		    	        	name:'本月收入',
		    	            type:'pie',
		    	            radius : [radius_min, radius_max],
		    	            center : ['50%', '50%'],
		    	            roseType : 'radius',
		    	            label: {
		    	                normal: {
		    	                    show: false
		    	                },
		    	                emphasis: {
		    	                    show: true
		    	                }
		    	            },
		    	            lableLine: {
		    	                normal: {
		    	                    show: false
		    	                },
		    	                emphasis: {
		    	                    show: true
		    	                }
		    	            },
		    	            data:income_json
		    	        }
		    	    ]
		    	});
		  //pie3
		    var chartContainer_pie = document.getElementById(pie_id+"3");
		    var myChart_pie3 = echarts.init(chartContainer_pie);
		    myChart_pie3.setOption(option = {
		    	    title : {
		    	        subtext: '支出',
		    	        x:'center'
		    	    },
		    	    tooltip : {
		    	        trigger: 'item',
		    	        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    	    },
		    	    calculable : true,
		    	    series : [
		    	        {
		    	            color:color2,
		    	        	name:'本月支出',
		    	            type:'pie',
		    	            radius : [radius_min, radius_max],
		    	            center : ['50%', '50%'],
		    	            roseType : 'radius',
		    	            label: {
		    	                normal: {
		    	                    show: false
		    	                },
		    	                emphasis: {
		    	                    show: true
		    	                }
		    	            },
		    	            lableLine: {
		    	                normal: {
		    	                    show: false
		    	                },
		    	                emphasis: {
		    	                    show: true
		    	                }
		    	            },
		    	            data:cost_json
		    	        }
		    	    ]
		    	});
		
	});
		
}