<%@ include file="/init.jsp" %>
<%@page import="com.ibtrader.util.*"%>
<%@page import="com.ibtrader.util.Utilities"%>
<%@page import="com.liferay.portal.kernel.theme.ThemeDisplay"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>

<div class="container-fluid-1280">


<%	ThemeDisplay _themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

%>

<aui:row>
<aui:col>

<portlet:renderURL var="addBackTestingURL">
    <portlet:param name="mvcRenderCommandName" value="/html/add_backtesting"></portlet:param>
    <portlet:param name="redirect" value="<%=themeDisplay.getURLCurrent()%>"/>
    <portlet:param name="shareId" value="{share}"></portlet:param>
</portlet:renderURL>


<liferay-frontend:add-menu>
    <liferay-frontend:add-menu-item title='<%= LanguageUtil.get(request,"BackTesting.add") %>' url="<%= addBackTestingURL.toString() %>" />    
</liferay-frontend:add-menu>

</aui:col>	
</aui:row>

<aui:row>
<aui:col span="12">
	<aui:container cssClass='super-awesome-container'>
		<liferay-ui:search-container cssClass="table  table-striped table-bordered table-hover" searchContainer="${searchBackTesting}" iteratorURL="${iteratorURL}"> 
		<liferay-ui:search-container-results results="${searchBackTesting.getResults()}"/>    
		<liferay-ui:search-container-row  className="com.ibtrader.data.model.BackTesting" keyProperty="backTId" modelVar="BackTesting">
		<liferay-ui:search-container-column-text name="BackTesting.From" value="<%=Utilities.getWebFormattedDate(BackTesting.getFromDate(),user)%>"/>
		<liferay-ui:search-container-column-text name="BackTesting.To" value="<%=Utilities.getWebFormattedDate(BackTesting.getToDate(),user)%>"/>
		<liferay-ui:search-container-column-text name="BackTesting.Description" value="${BackTesting.description}"/>
		<liferay-ui:search-container-column-text name="BackTesting.count" value="${BackTesting.countordersBUY + BackTesting.countordersSELL}"/>
		<liferay-ui:search-container-column-text name="BackTesting.countBUY" value="${BackTesting.countordersBUY}"/>
		<liferay-ui:search-container-column-text name="BackTesting.countSELL" value="${BackTesting.countordersSELL}"/>
		<liferay-ui:search-container-column-text name="BackTesting.profit" value="${BackTesting.profitordersBUY + BackTesting.profitordersSELL}"/>		
		<liferay-ui:search-container-column-text name="BackTesting.profitBUY" value="${BackTesting.profitordersBUY}"/>
		<liferay-ui:search-container-column-text name="BackTesting.profitSELL" value="${BackTesting.profitordersSELL}"/>
		<liferay-ui:search-container-column-jsp  path="/html/backtesting_graph_results.jsp"/>
		<liferay-ui:search-container-column-jsp  path="/html/backtesting_actions.jsp"/>
				
		</liferay-ui:search-container-row>    
		<liferay-ui:search-iterator markupView="lexicon"/>
	</liferay-ui:search-container>
	</aui:container>
</aui:col>	
</aui:row>
</div>

 
<script>
    define._amd = define.amd;
    define.amd = false;
</script>

<script>

AUI().ready(function() {
	console.log('It works!');		

	require ('Chart', function (jquery) {
		
		console.log('It works!1');
		

	});
});

	
</script>

<script>
define.amd = define._amd;
</script>


