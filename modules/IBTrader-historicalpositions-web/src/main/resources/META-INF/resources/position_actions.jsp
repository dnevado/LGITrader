<%@ include file="/init.jsp" %>

<%
String mvcPath = ParamUtil.getString(request, "mvcPath");

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

//System.out.println(row.getObject());

Position position = (Position) row.getObject();

long ShareId = position.getShareId();
long PositionId = position.getPositionId(); 


%>

<portlet:renderURL   var="positionURL">
    <portlet:param name="positionId" value="<%= String.valueOf(PositionId) %>" />
    <portlet:param name="mvcRenderCommandName" value="/position/view_entry" />
</portlet:renderURL>
  

<portlet:renderURL   var="EditPositioURL">
    <portlet:param name="positionId" value="<%= String.valueOf(PositionId) %>" />
    <portlet:param name="mvcRenderCommandName" value="/position/edit_entry" />
</portlet:renderURL>  
 
<liferay-ui:icon-menu>
<liferay-ui:icon image="view" message="View" url="${positionURL}%>" />
<liferay-ui:icon image="edit" message="Edit" url="${EditPositioURL}%>" />                          
</liferay-ui:icon-menu>
