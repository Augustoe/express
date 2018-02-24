/*document.write("<script language='javascript' src='one.js'></script>");*/
function paintStaffchart(id,type,staff_id){
	var date=[];
	var count_num=[];
	var count_price=[];
	var lose_num=[];
	var break_num=[];
	$("#"+id).empty();
	getStaffPerf.getStaffPerform(type,staff_id,function(data){
		var json=eval('('+data+')');
		$.each(json, function(i, item) {
			date.push(item.date);
			count_num.push(parseInt(item.count_num));
			lose_num.push(parseInt(item.lose_num));
			break_num.push(parseInt(item.break_num));
			count_price.push(parseInt(item.count_price));
		});
		
		var chartContainer = document.getElementById(id);
	    var myChart = echarts.init(chartContainer);
	    myChart.setOption(option = {
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
		        data:['总件数','破损数','丢失数','总金额']
		    },
		    xAxis: [
		        {
		            type: 'category',
		            data: date,
		            axisPointer: {
		                type: 'shadow'
		            }
		        }
		    ],
		    yAxis: [
		        {
		            type: 'value',
		            name: '件数',
		            //min: 0,
		            //: 250,
		            //interval: 50,
		            //splitNumber:5,
		            axisLabel: {
		                formatter: '{value} 件'
		            }
		        },
		        {
		            type: 'value',
		            name: '金额',
		            //min: 0,
		            //max: 25,
		            //interval: 5,
		            //splitNumber:5,
		            axisLabel: {
		                formatter: '{value} 元'
		            }
		        }
		    ],
		    series: [
		        {
		            name:'总件数',
		            type:'bar',
		            data:count_num
		        },
		        {
		            name:'破损数',
		            type:'bar',
		            data:break_num
		        },
		        {
		            name:'丢失数',
		            type:'bar',
		            data:lose_num
		        },
		        {
		            name:'总金额',
		            type:'line',
		            yAxisIndex: 1,
		            data:count_price
		        }
		    ]
		});
	    myChart.showLoading("加载中。。。");
    });
}