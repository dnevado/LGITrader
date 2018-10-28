/* COGEMOS EL 	style="futures" */

define('jquery', function () {
	
			return window.jQuery;
	
		});

function updateAssetType() {
     require ('jquery', function (jquery) {
			 /* NO ME CARGA JQUERY , NECESITARIA EL CONFIG.JS */
			 StockSelected = document.getElementById(_nameSpace + "security_type");
			// console.log(StockSelected);
			 Futures = StockSelected.options[StockSelected.selectedIndex].className; 
				if (Futures=='futures')
				
					document.getElementById("datafutures").style.display ="block";
				else
					document.getElementById("datafutures").style.display ="none";
		});
			 
		
}

/* RENDERIZA LOS GRAFICOS DEL BACKTESTING */

function showBackTestingResults(canvaId, data) {	
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
	var data = {		    
	    datasets: [{
	        data: [data],
	        backgroundColor: "#668cff"		       
	    }]
	};
	var promisedDeliveryChart = new Chart(document.getElementById(canvaId), {
	    type: 'doughnut',
	    data: data,
	    options: {
	        elements: {
	            center: {
	                text: data   //set as you wish
	            }
	        },
	        cutoutPercentage: 75,
	        legend: {
	            display: false
	        }
	    }
	});
	$("#" + canvaId).show();
	});
			
}

