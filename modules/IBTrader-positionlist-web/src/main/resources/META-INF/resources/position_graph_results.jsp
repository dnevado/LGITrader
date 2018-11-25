<%@page import="com.ibtrader.util.*"%>

<%@ include file="/init.jsp" %>
<%@ page import="com.liferay.portal.kernel.json.*" %>

<canvas id="percentageprofit" width="400" height="350"></canvas>
<canvas id="profit" width="400" height="350"></canvas>
<style>
	canvas {
		-moz-user-select: none;
		-webkit-user-select: none;
		-ms-user-select: none;
	}
	</style>

<portlet:resourceURL var="PositionResultsResourceURL">
    <portlet:param name="redirect" value="<%=themeDisplay.getURLCurrent()%>"/>
    <portlet:param name="positionResult" value="1"/>
</portlet:resourceURL>

<%

/*
  		OPERACIONES --> OPERACIONES POR TIPO 
        MARGENBENEFICIO --> MARGEN 
        BENEFICIO --> TOTAL BENEFIOCIO 
	    INVERTIDO  --> TOTAL INVERTIDO 
	    TIPO --> OPERACION
	    
*/

 %>
 
 <script>

    define._amd = define.amd;
    define.amd = false;
</script>

<script>

function <portlet:namespace/>RefreshResults() {		
	jQuery.ajax({
	type: "POST",
	url: "<%=renderResponse.encodeURL(PositionResultsResourceURL.toString())%>",		
	success: function(msg) {
		
		if (msg!=null && msg!='')
			<portlet:namespace/>showResults(msg)
	}
	});
	} 


function <portlet:namespace/>showResults(data) {	
	
	
	var Data = $.parseJSON(data);
	var profit;
	var profitrate;
	
	if (Data!=null &&  Data.dataResults!=null && Data.dataResults[0]!=null)
	{
		profit=Data.dataResults[0]["BENEFICIO"];
		profitrate=Data.dataResults[0]["MARGENBENEFICIO"];
		
		var backgroundcolor = "#FF4500" // rojo
		var remaining;
		if (profit>0)
			backgroundcolor = "#81B758" // verde
			
		
		/* lo quitamos el signo ya que el color da el valor */
		profitrate = Math.abs(profitrate);
		remaining = 100-profitrate;	
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
		    labels: ['<liferay-ui:message key="profitrate"/>',''],
		    datasets: [{
		        data: [profitrate,remaining],
		        backgroundColor: [backgroundcolor, "#8cb29d"]		       
		    }]
		};
		var promisedDeliveryChart = new Chart(document.getElementById('percentageprofit'), {
		    type: 'doughnut',
		    data: data,
		    options: {
		        elements: {
		            center: {
		                text: profitrate + "%"  //set as you wish
		            }
		        },
		        cutoutPercentage: 75,
		        legend: {
		            display: false
		        }
		    }
		});
		// chart2
		var data = {
			labels: ['<liferay-ui:message key="profit"/>'],
		    datasets: [{
		        data: [profit],
		        backgroundColor: [backgroundcolor]
		    }]
		};
		var promisedDeliveryChart = new Chart(document.getElementById('profit'), {
		    type: 'doughnut',
		    data: data,
		    options: {
		        elements: {
		            center: {
		                text: profit //set as you wish
		            }
		        },
		        cutoutPercentage: 75,
		        legend: {
		        	display: false
		        }
		    }
		});
		$("#percentageprofit").show();
		$("#profit").show();
		
	} // end if (Data!=null &&  Data.dataResults!=null && Data.dataResultss[0]!=null)
	else
	{
		$("#percentageprofit").hide();
		$("#profit").hide();
	}
	
			
}  // <portlet:namespace/>showResults(data) {	



 AUI().ready(function() {
		console.log('It works!');		
	
		require ('Chart', function (jquery) {
			
			console.log('It works!1');
			
	
		});
 });
 
 	
</script>

<script>
    define.amd = define._amd;
</script>