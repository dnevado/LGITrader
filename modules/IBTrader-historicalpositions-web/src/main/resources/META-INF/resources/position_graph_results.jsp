<%@page import="com.ibtrader.util.*"%>

<%@ include file="/init.jsp" %>
<%@ page import="com.liferay.portal.kernel.json.*" %>

<canvas id="myChart" width="400" height="400"></canvas>

<%

/*
  		OPERACIONES --> OPERACIONES POR TIPO 
        MARGENBENEFICIO --> MARGEN 
        BENEFICIO --> TOTAL BENEFIOCIO 
	    INVERTIDO  --> TOTAL INVERTIDO 
	    TIPO --> OPERACION
	    
*/

JSONArray results =  (JSONArray) request.getAttribute("_jsonResults");

double profitBUY = 0;	    
double profitSELL = 0;
 
if (results != null && results.length() > 0) {
	for (int i = 0; i < results.length(); i++) {
		
		JSONObject result = results.getJSONObject(i);
		String  type = result.getString("TIPO");
		if 	(type.equals("SELL"))
			profitSELL = result.getDouble("MARGENBENEFICIO");
		else
			profitBUY = result.getDouble("MARGENBENEFICIO");
			
	}				
		
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
			
			console.log('It works!1');

			var ctx = document.getElementById("myChart").getContext('2d');
			var myChart = new Chart(ctx, {
			    type: 'bar',
			    data: {
			        labels: ["BUY", "SELL"],
			        datasets: [{
			            label: '<liferay-ui:message key="profit"/>',
			            data: [<%=profitBUY%>,<%=profitSELL%>],
			            backgroundColor: [
			            	'rgba(75, 192, 192, 0.2)',			                
			                'rgba(255, 99, 132, 0.2)'
			            ],
			            borderColor: [
			            	'rgba(75, 192, 192, 1)',			                
			                'rgba(255,99,132,1)'
			            ],
			            borderWidth: 1
			        }]
			    },
			    options: {
			        scales: {
			            yAxes: [{
			                ticks: {
			                    beginAtZero:true
			                }
			            }]
			        }
			    }
			});
		});
 });
</script>

<script>
    define.amd = define._amd;
</script>