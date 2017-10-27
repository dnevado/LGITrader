<%@ include file="/init.jsp" %>


<portlet:actionURL name="CronRead" var="CronRead" />

<aui:a href="<%= CronRead %>" >CronRead</aui:a>

<portlet:actionURL name="CronTrade" var="CronTrade" />

<aui:a href="<%= CronTrade %>" >CronTrade</aui:a>




<table id="dashboard" class="table responsive table-bordered table-hover table-striped"  style="width: 100%;"  role="grid">
<thead>
    <tr>    	
        <th>Código</th>
        <th>Titulo</th>               
        <th>Descripción</th>
        <th title=" Activo">Activo</th>
        <th title=" Visible">Visible</th>
        <th> </th>              
    </tr>
</thead>
<tbody>
<tr  class="ui-state-default">
		<td>Código</td>
        <td>Titulo</td>               
        <td>Descripción</td>
        <td title=" Activo">Activo</td>
        <td title=" Visible">Visible</td>
        <td> </th>     
</tr>        
</tbody>
</table>


<script>
$( document ).ready(function() {			
	 
	 _DoctorTableList=$("#dashboard").DataTable( {
		    responsive: true
	 } );
}); 

</script>
<p>
	<b><liferay-ui:message key="LGITrader.caption"/></b>
</p>