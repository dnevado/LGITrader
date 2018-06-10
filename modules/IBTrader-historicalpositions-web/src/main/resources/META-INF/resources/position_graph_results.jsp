<%@page import="com.ibtrader.util.*"%>

<%@ include file="/init.jsp" %>
<%@ page import="com.liferay.portal.kernel.json.*" %>

<canvas id="percentageprofit" width="400" height="400"></canvas>
<canvas id="profit" width="125" height="50"></canvas>
<canvas id="amount" width="125" height="50"></canvas>
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

double totalpositions = 0;
double totalprofit = 0;
double totalamount = 0;






if (results != null && results.length() > 0) {
	for (int i = 0; i < results.length(); i++) {
		
		JSONObject result = results.getJSONObject(i);
		String  type = result.getString("TIPO");
		totalpositions +=  result.getLong("OPERACIONES");
		totalprofit    +=  result.getLong("BENEFICIO");
		totalamount    +=  result.getLong("INVERTIDO");		
		if 	(type.contains("SELL"))
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

var positiveRGB = "rgba(129, 183, 88, 0.2)";
var negativeRGB = "rgba(255, 99, 132, 0.2)";

 AUI().ready(function() {
		console.log('It works!');		
	
		require ('Chart', function (jquery) {
			
			console.log('It works!1');
			
			
			
			/* % beneficio*/ 
			var ctx = document.getElementById("percentageprofit").getContext('2d');
			var myChart = new Chart(ctx, {
			    type: 'bar',
			    data: {
			        labels: ["BUY", "SELL"],
			        datasets: [{
			            label: '<liferay-ui:message key="profit"/>',
			            data: [<%=profitBUY%>,<%=profitSELL%>],
			            backgroundColor: [
			            	<%=profitBUY%>>0 ? positiveRGB: negativeRGB,			                
			            	<%=profitSELL%>>0 ? positiveRGB: negativeRGB
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
			            xAxes: [{ barPercentage: 0.5 }]
			        }
			    }
			});
			/* beneficio*/ 
			var ctx2 = document.getElementById("profit").getContext('2d');
			var myChart = new Chart(ctx2, {
			    type: 'horizontalBar',
			    data: {
			     //   labels: ["Profit"],
			        datasets: [{			        
			        	label: 'Profit',			        
			            data: [<%=totalprofit%>],
			            backgroundColor: [
			            	  'rgba(255, 159, 64, 0.2)'			            	  
			            ],
			            borderColor: [
			            	'rgba(75, 192, 192, 1)'
			            ],
			            borderWidth: 1
			        }]
			    },
			    options: {
			        scales: {
			            xAxes: [{ barPercentage: 0.3 }]
			        }
			    }
			});
			/* beneficio*/ 
			var ctx2 = document.getElementById("amount").getContext('2d');
			var myChart = new Chart(ctx2, {
			    type: 'horizontalBar',
			    data: {
			     //   labels: ["Total Amount"],
			        datasets: [{			        
			        	label: 'Total Amount',			        
			            data: [<%=totalamount%>],
			            backgroundColor: [			            	  
			            	  'rgba(255, 205, 86, 0.2)'
			            ],
			            borderColor: [			            				               
			                'rgba(255,99,132,1)'
			            ],
			            borderWidth: 1
			        }]
			    },
			    options: {
			        scales: {
			            xAxes: [{ barPercentage: 0.3 }]
			        }
			    }
			});
		
			
		
			
		});
 });
</script>

<script>
    define.amd = define._amd;
</script>