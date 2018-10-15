<%@ include file="/init.jsp" %>

<%
String mvcPath = ParamUtil.getString(request, "mvcPath");

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

//System.out.println(row.getObject());

BackTesting backtesting = (BackTesting) row.getObject();

long BackTestingId = backtesting.getbackTId();


%>



<portlet:actionURL   var="RemoveBackTestingURL" name="RemoveBackTesting"> 	
    <portlet:param name="backtestingId" value="<%= String.valueOf(BackTestingId) %>"/> 
    <portlet:param name="redirect" value="<%=themeDisplay.getURLCurrent()%>"/>
 </portlet:actionURL>
 
<liferay-ui:icon-menu>
<liferay-ui:icon image="delete" message="BackTesting.delete" url="${RemoveBackTestingURL}" />                         
</liferay-ui:icon-menu>
