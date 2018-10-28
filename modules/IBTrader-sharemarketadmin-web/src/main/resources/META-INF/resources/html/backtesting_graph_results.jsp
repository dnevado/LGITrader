<%@ include file="/init.jsp" %>

<%
String mvcPath = ParamUtil.getString(request, "mvcPath");

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

//System.out.println(row.getObject());
BackTesting backtesting = (BackTesting) row.getObject();

long BackTestingId = backtesting.getBackTId();


%>
<canvas id="test" width="400" height="350"></canvas>

<!--  TOTAL 
<canvas id="graph_total<%=BackTestingId%>" width="400" height="350"></canvas>

<canvas id="graph_total_buy<%=BackTestingId%>" width="400" height="350"></canvas>
<canvas id="graph_total_sell<%=BackTestingId%>" width="400" height="350"></canvas>
<canvas id="graph_profit<%=BackTestingId%>" width="400" height="350"></canvas>
<canvas id="graph_profit_buy<%=BackTestingId%>" width="400" height="350"></canvas>
<canvas id="graph_profit_sell<%=BackTestingId%>" width="400" height="350"></canvas> -->
<style>
	canvas {
		-moz-user-select: none;
		-webkit-user-select: none;
		-ms-user-select: none;
	}
	</style>

<script>
AUI().ready(function() {    
	require ('Chart', function (jquery) {
			Chart.pluginService.register({
			    beforeDraw: function (chart) {
			        var width = chart.chart.width,
			            height = chart.chart.height,
			            ctx = chart.chart.ctx;
			        ctx.restore();
			        var fontSize = (height / 114).toFixed(2);
			        ctx.font = fontSize + "em sans-serif";
			        ctx.textBaseline = "middle";
			        var text = chart.config.options.elements.center.text,
			            textX = Math.round((width - ctx.measureText(text).width) / 2),
			            textY = height / 2;
			        ctx.fillText(text, textX, textY);
			        ctx.save();
			    }
			});
				// chart1
			var profit = 56;
			var data = {		    
			    datasets: [{
			        data: [profit]       
			    }]
			};
			var promisedDeliveryChart = new Chart(document.getElementById("test"), {
			    type: 'doughnut',
			    data: data,
			    options: {
			        elements: {
			            center: {
			                text: 'f'   //set as you wish
			            }
			        },
			        cutoutPercentage: 75,
			        legend: {
			            display: false
			        }
			    }
			});
			$("#test").show();
	});
});

</script>
 