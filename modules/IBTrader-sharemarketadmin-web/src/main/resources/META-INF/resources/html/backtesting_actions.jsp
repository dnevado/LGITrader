<%@ include file="/init.jsp" %>
<%@page import="com.ibtrader.constants.IBTraderConstants" %>
<%@page import="com.liferay.portal.kernel.util.Validator" %>
<%@page import="com.liferay.portal.kernel.theme.ThemeDisplay"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>

<%
String mvcPath = ParamUtil.getString(request, "mvcPath");

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

//System.out.println(row.getObject());
BackTesting backtesting = (BackTesting) row.getObject();

long BackTestingId = backtesting.getBackTId();

ThemeDisplay _themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

String  paramPortletName = renderResponse.getNamespace() + "f"; 


%>

<script type="text/javascript">
function CallBackRefreshSimulation() {

		window.location.reload();
}
</script>


<portlet:renderURL   var="EditBackTesting">
	<portlet:param name="mvcRenderCommandName" value="/html/add_edit_backtesting"/>
    <portlet:param name="backtestingId" value="<%= String.valueOf(BackTestingId) %>"/>
    <portlet:param name="shareId" value="<%= String.valueOf(backtesting.getShareId()) %>"/>
    <portlet:param name="redirect" value="<%=themeDisplay.getURLCurrent()%>"/>    
</portlet:renderURL>


 <portlet:actionURL   var="RemoveBackTestingURL" name="RemoveBackTesting"> 	
    <portlet:param name="backtestingId" value="<%= String.valueOf(BackTestingId) %>"/> 
    <portlet:param name="redirect" value="<%=themeDisplay.getURLCurrent()%>"/>
 </portlet:actionURL>
 <portlet:actionURL   var="StopBackTestingURL" name="StopBackTesting"> 	
    <portlet:param name="backtestingId" value="<%= String.valueOf(BackTestingId) %>"/> 
    <portlet:param name="redirect" value="<%=themeDisplay.getURLCurrent()%>"/>
 </portlet:actionURL>
  <portlet:actionURL   var="StartBackTestingURL" name="StartBackTesting"> 	
    <portlet:param name="backtestingId" value="<%= String.valueOf(BackTestingId) %>"/> 
    <portlet:param name="redirect" value="<%=themeDisplay.getURLCurrent()%>"/>
 </portlet:actionURL>
<liferay-ui:icon-menu>
<liferay-ui:icon image="edit" message="BackTesting.edit" url="${EditBackTesting}" />
<% /* arrancado */
	if (backtesting.isStoppable()) { %>
       <liferay-ui:icon image="check" message="BackTesting.stop" url="${StopBackTestingURL}" />
<%  } %>  
<% /* arrancado */
	if (backtesting.isRemovable()) { %>
       <liferay-ui:icon image="delete" message="BackTesting.delete" url="${RemoveBackTestingURL}" />
<%  } %>  
<c:set  var="isStartable" value="<%=backtesting.isStartable()%>"/>
<c:if test="${totalExecuting == 0 && isStartable eq 'true'}">
       <liferay-ui:icon image="check" message="BackTesting.start" url="${StartBackTestingURL}" />
</c:if>    
<liferay-ui:icon image="message" message="BackTesting.refresh" url="javascript:CallBackRefreshSimulation()" />

</liferay-ui:icon-menu>
