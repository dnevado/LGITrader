<%@page import="com.ibtrader.util.*"%>

<%@ include file="/init.jsp" %>
<%@ page import="com.liferay.portal.kernel.json.*" %>

<canvas id="percentageprofit"></canvas>
<canvas id="profit"></canvas>


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
	// code here
	}
	});
	} 





 AUI().ready(function() {
		console.log('It works!');		
	
		require ('Chart', function (jquery) {
			
			console.log('It works!1');
			
			var ctx = document.getElementById("percentageprofit").getContext('2d');
			var myDoughnutChart = new Chart(ctx, {
			    type: 'doughnut',
			    data: {
			        labels: ["BUY", "SELL"],
			        datasets: [{
			            label: '<liferay-ui:message key="profit"/>',
			            data: [1,2],			         
			            borderColor: [
			            	'rgba(75, 192, 192, 1)',			                
			                'rgba(255,99,132,1)'
			            ],
			            borderWidth: 1
			        }]
			    },
			    options: {
			        scales: {
			            xAxes: [{ barPercentage: 0.5 }]
			        }
			    }
			});
			var ctx = document.getElementById("profit").getContext('2d');
			var myDoughnutChart = new Chart(ctx, {
			    type: 'doughnut',
			    data: {
			        labels: ["BUY", "SELL"],
			        datasets: [{
			            label: '<liferay-ui:message key="profit"/>',
			            data: [1,2],			         
			            borderColor: [
			            	'rgba(75, 192, 192, 1)',			                
			                'rgba(255,99,132,1)'
			            ],
			            borderWidth: 1
			        }]
			    },
			    options: {
			        scales: {
			            xAxes: [{ barPercentage: 0.5 }]
			        }
			    }
			});

		
			
		
			
		});
 });
 
 	
</script>

<script>
    define.amd = define._amd;
</script>