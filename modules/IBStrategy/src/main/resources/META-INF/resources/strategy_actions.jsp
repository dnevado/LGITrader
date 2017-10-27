<%@ include file="/init.jsp" %>



<%
String mvcPath = ParamUtil.getString(request, "mvcPath");

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Strategy strategy = (Strategy )row.getObject();

boolean _CanUpdate = false;
try
{
	_CanUpdate  = StrategyPermission.contains(permissionChecker, strategy.getStrategyID(), ActionKeys.DELETE);	
}
catch (Exception e)
{ 
	int error = e.hashCode();
}


boolean _CanDelete = false;
try
{
	_CanDelete  = StrategyPermission.contains(permissionChecker, strategy.getStrategyID(), ActionKeys.UPDATE);
}
catch (Exception e)
{
	int error = e.hashCode();
}
%>

<portlet:actionURL   name="delete" var="deleteURL">
    <portlet:param name="strategyId" value="<%= String.valueOf(strategy.getStrategyID()) %>" />
    <portlet:param name="mvcPath" value="/edit_strategy.jsp" />
</portlet:actionURL>
  

<portlet:renderURL var="editURL">
    <portlet:param name="strategyId" value="<%= String.valueOf(strategy.getStrategyID()) %>" />
    <portlet:param name="mvcPath" value="/edit_strategy.jsp" />
</portlet:renderURL>
<liferay-ui:icon-menu> 



		 <c:if test="<%=_CanUpdate%>">
	         <liferay-ui:icon image="edit" message="Edit" url="${editURL}%>" />
        </c:if>
        <c:if test="<%=_CanDelete%>">                
       		 <liferay-ui:icon image="delete" message="Borrar" url="${deleteURL}%>" />	  
    	 </c:if>
</liferay-ui:icon-menu>