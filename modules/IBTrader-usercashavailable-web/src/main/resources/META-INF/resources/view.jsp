<%@ include file="/init.jsp" %>

<%@page import ="com.liferay.portal.kernel.util.*" %>
<%@page import ="com.liferay.portal.kernel.theme.*" %>


<style>


</style>

<div class="userbudget-detail">
 <c:choose>         
   <c:when test = "${userBudget > 0}">
      <c:set var="total" value="${progress}" />
      	<div class="nav-budget"><span class="glyphicon glyphicon-eur"></span>	
	    ${positionsLiquid} of ${userBudget} </div>	
	  	<div class="progress">		  
		  	<div  class="progress-bar bg-warning" role="progressbar" style="width: ${total}%;" aria-valuenow="${total}" aria-valuemin="0" aria-valuemax="100">${total}%</div>		  		
	 	</div>	 		 
     </c:when>       
   <c:otherwise>
    <a href="/web<%=themeDisplay.getScopeGroup().getFriendlyURL()%>/perfil">
			<div class="nav-budget"><span class="glyphicon glyphicon-eur"></span>
			<liferay-ui:message  key="nobudget-filled"/>
			</div>
	</a>
   </c:otherwise>
</c:choose>
</div>
