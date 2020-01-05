<%@ include file="/init.jsp" %>

<%

/* MODIFICAR ESTOS LOST  Y SALIR DE LA POSICION */ 

String mvcPath = ParamUtil.getString(request, "mvcPath");

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

//System.out.println(row.getObject());

Position position = (Position) row.getObject();

long ShareId = position.getShareId();
long PositionId = position.getPositionId(); 


%>

<portlet:renderURL   var="EditPositioURL">
    <portlet:param name="positionId" value="<%= String.valueOf(PositionId) %>" />
    <portlet:param name="mvcPath" value="/position_detail.jsp" />
    <portlet:param name="redirect" value="<%=themeDisplay.getURLCurrent()%>"/>    
</portlet:renderURL> 


<aui:button value="Detalle" onClick='<%=EditPositioURL.toString()%>'></aui:button>
