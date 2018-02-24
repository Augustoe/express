function init_chart_customer(chart_id,key){
	var customer=[];
	var attr=[];
	
	getCustomerInf.getTopCustomer(key,function(data){
		var json=eval('('+data+')');
		$.each(json, function(i, item) {
			customer.push(item.customer_name);
			attr.push(parseInt(item.attr));
		});
		
		
		var chartContainer = document.getElementById(chart_id);
	    var myChart = echarts.init(chartContainer);
	    myChart.setOption(option = {
	    	    tooltip : {
	    	        trigger: 'axis',
	    	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	    	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
	    	        }
	    	    },
	    	    legend: {
	    	        data: [key]
	    	    },
	    	    grid: {
	    	        left: '3%',
	    	        right: '4%',
	    	        bottom: '3%',
	    	        containLabel: true
	    	    },
	    	    xAxis:  {
	    	        type: 'value'
	    	    },
	    	    yAxis: {
	    	        type: 'category',
	    	        data:customer
	    	    },
	    	    series: [
	    	        {
	    	        	name: key,
	    	            type: 'bar',
	    	            stack: '总量',
	    	            label: {
	    	                normal: {
	    	                    show: true,
	    	                    position: 'insideRight'
	    	                }
	    	            },
	    	            data: attr
	    	        }
	    	    ]
	    	}
	    );
	    
	});
}