<%@ include file="/init.jsp" %>
<div class="text-center"><liferay-ui:message key="lastrealtime"/>
<canvas id="lastrealtime" width="100" height="70"></canvas>
</div>


<%@ page import="com.ibtrader.data.model.Realtime" %>

<%
Realtime _realtime = null;
double realtime=0;
if (request.getAttribute("realtime")!=null)
{
	_realtime = (Realtime) request.getAttribute("realtime");
	realtime = _realtime.getValue();
}


%>
 
<script>
    define._amd = define.amd;
    define.amd = false;
</script>

<script>


 AUI().ready(function() {
		console.log('It works!');		
	
		require ('Chart', function (jquery) {
			
			
			var realtime = <%=realtime%>;
			
			console.log('It works!1');
			if (<%=realtime%>>0)
			{
				Chart.pluginService.register({
				    beforeDraw: function (chart) {
				        var width = chart.chart.width,
				            height = chart.chart.height,
				            ctx = chart.chart.ctx;
				        ctx.restore();
				        var fontSize = (height / 100).toFixed(2);
				        ctx.font = fontSize + "em Work Sans,sans-serif";
				        ctx.textBaseline = "middle";
				        var text = chart.config.options.elements.center.text,
				            textX = Math.round((width - ctx.measureText(text).width) / 2),
				            textY = height / 2;
				        ctx.fillText(text, textX, textY);
				        ctx.save();
				    }
				});
				// chart1
				var data = {
				    labels: ['<liferay-ui:message key="lastrealtime"/>',''],
				    datasets: [{
				        data: [realtime,realtime],
				        backgroundColor: ["#668cff", "#668cff"]		       
				    }]
				};
				
				var promisedDeliveryChart = new Chart(document.getElementById('lastrealtime'), {
				    type: 'doughnut',
				    data: data,
				    options: {
				        elements: {
				            center: {
				                text:  realtime   //set as you wish
				            }
				        },
				        cutoutPercentage: 75,
				        legend: {
				            display: false
				        }
				    }
				});
				
				$("#lastrealtime").show();
			}
			else
				$("#lastrealtime").hide();
				
		});
 });
</script>

<script>
    define.amd = define._amd;
</script>