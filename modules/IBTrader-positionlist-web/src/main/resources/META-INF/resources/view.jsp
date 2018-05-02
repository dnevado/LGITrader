<%@ include file="/init.jsp" %>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayPortletMode"%>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@page import="com.liferay.portal.kernel.theme.ThemeDisplay"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>




<% 
String redirect = themeDisplay.getURLCurrent();
String  paramPortletName = renderResponse.getNamespace() + "f"; 

/* portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);
*/
%>


<portlet:resourceURL var="PositionListResourceURL">
    <portlet:param name="redirect" value="<%=themeDisplay.getURLCurrent()%>"/>
</portlet:resourceURL>

<%	ThemeDisplay _themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY); %> 


<script>

	var tPositions;

    define._amd = define.amd;
    define.amd = false;
</script>

<script>
	
	
	
	
	function callProcessAction(url) {
	    window.location.href = url;
	}
	
	function <portlet:namespace/>CallBackRefreshPosition() {
	 
		/* CREACIOM DE UN RENDER Y UN ICON PARA 
		1.CERRAR LA POSICION EN UN MOMENTO DADO
		*/
		AUI().use("liferay-portlet-url", function(a) {
			var actionURL = Liferay.PortletURL.createActionURL();
			actionURL.setWindowState("<%=LiferayWindowState.NORMAL.toString() %>");
			actionURL.setPortletMode("<%=LiferayPortletMode.VIEW %>");
			actionURL.setParameter("PositionId", "-1");		
			actionURL.setParameter("action","DeletePosition");
			actionURL.setParameter("redirect",'<%=redirect%>');
			actionURL.setPortletId("<%=_themeDisplay.getPortletDisplay().getId() %>");
			
			console.log("do some stuff DOM changes ");
			console.log(actionURL);
			
			});

	
		
		
		
		
		
		
	} 

	function <portlet:namespace/>RefreshPosition() {
	
		$.fn.dataTable.ext.errMode = 'none';	
		console.log("adding setInterval 4 seconds");
		setInterval( function () {tPositions.ajax.reload(<portlet:namespace/>CallBackRefreshPosition());}, 4000 );
	} 

	
	AUI().ready(function() {
		console.log('It works!');		
	
		require ('jquery', function (jquery) {
			console.log('It works!1');
			require ('dataTables', function (dataTables) {
				console.log('It works!2');
				tPositions = jquery('#positions').DataTable( {
			        "language": {
			        		"sProcessing":     "Procesando...",
			        		"sLengthMenu":     "Mostrar _MENU_ registros",
			        		"sZeroRecords":    "No se encontraron resultados",
			        		"sEmptyTable":     "Ningún dato disponible en esta tabla",
			        		"sInfo":           "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
			        		"sInfoEmpty":      "Mostrando registros del 0 al 0 de un total de 0 registros",
			        		"sInfoFiltered":   "(filtrado de un total de _MAX_ registros)",
			        		"sInfoPostFix":    "",
			        		"sSearch":         "Buscar:",
			        		"sUrl":            "",
			        		"sInfoThousands":  ",",
			        		"sLoadingRecords": "Cargando...",
			        		"oPaginate": {
			        			"sFirst":    "Primero",
			        			"sLast":     "Último",
			        			"sNext":     "Siguiente",
			        			"sPrevious": "Anterior"
			        		},
			        		"oAria": {
			        			"sSortAscending":  ": Activar para ordenar la columna de manera ascendente",
			        			"sSortDescending": ": Activar para ordenar la columna de manera descendente"
			        		}
			        	
			        },			      
				 	 "Processing": true,
			        "ServerSide": true, 
			        columns: [
			        	 { data: "symbol" },	
			        	 { data: "state" },
			        	 { data: "type" },
			        	 { data: "date_in" },
			        	 { data: "number" },
			        	 { data: "price_in" },
			             { data: "price_out" },
			             { data: "roi" },
			             { data: "stop_lost" },
			        	 { data: "stop_profit" },			             			             
			             { data: "date_out" },
			             { data: "state_in" },
			             { data: "state_out" },
			             { data: "modify_link" }			             			             
			        ],
			        select: true ,
			        "ajax": '<%=PositionListResourceURL.toString()%>'
			        
			    });
				<portlet:namespace/>RefreshPosition();

		}); 
		
	}); // end require jquery
}); // end document ready
</script>
<script>
    define.amd = define._amd;
</script>

<div class="container-fluid-1280">
<table class="table responsive table-striped table-bordered table-hover"  id="positions">
       <thead>
            <tr>
                <th><liferay-ui:message key="Symbol"/></th>
                <th><liferay-ui:message key="State"/></th>                
                <th><liferay-ui:message key="Type"/></th>
                <th><liferay-ui:message key="Date.In"/></th>
                <th><liferay-ui:message key="Number"/></th>
                <th><liferay-ui:message key="Price.In"/></th>
                <th><liferay-ui:message key="Price.Out"/></th>
                <th><liferay-ui:message key="Rendimiento"/></th>
                <th><liferay-ui:message key="StopLost"/></th>
                <th><liferay-ui:message key="StopProfit"/></th>
             	<th><liferay-ui:message key="Date.Out"/></th>
             	<th><liferay-ui:message key="State.In"/></th>
                <th><liferay-ui:message key="State.Out"/></th>               
                <th></th>                
            </tr>
        </thead>
</table>
</div>
