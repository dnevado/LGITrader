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

