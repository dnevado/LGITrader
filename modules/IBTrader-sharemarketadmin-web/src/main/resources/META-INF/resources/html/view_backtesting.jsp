<%@ include file="/init.jsp" %>
<%@page import="com.ibtrader.util.*"%>
<%@page import="com.ibtrader.util.Utilities"%>
<%@page import="com.liferay.portal.kernel.theme.ThemeDisplay"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@ page import="java.time.*" %>
<%@ page import="java.time.temporal.ChronoUnit" %>


<div class="container-fluid-1280">


<%	

	String redirect = ParamUtil.getString(request, "redirect");	
	
	portletDisplay.setShowBackIcon(true);
	portletDisplay.setURLBack(redirect);
	
	ThemeDisplay _themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
    double graph_value = -1.0;  // usada para pintar los canvas  
	String canvaId= ""; // identificador de canva por backtesting ;


%>
<style>
.progress {  
  width: 100%;
}
</style>
<jsp:useBean id="utilities" class="com.ibtrader.util.Utilities"/>


<liferay-ui:success key="backtesting.success" message="backtesting.success"/>
<liferay-ui:error key="backtesting.notremovable" message="backtesting.notremovable"/>
<liferay-ui:error key="backtesting.notstartable" message="backtesting.notstartable"/>
<liferay-ui:error key="backtesting.notstoppable" message="backtesting.notstoppable"/>

<aui:row>
<aui:col>

<portlet:renderURL var="addBackTestingURL">
    <portlet:param name="mvcRenderCommandName" value="/html/add_edit_backtesting"></portlet:param>
    <portlet:param name="redirect" value="<%=themeDisplay.getURLCurrent()%>"/>
    <portlet:param name="shareId" value="${shareId}"></portlet:param>
</portlet:renderURL>


<c:if test="${totalExecuting == 0}">
	<liferay-frontend:add-menu>
	    <liferay-frontend:add-menu-item title='<%= LanguageUtil.get(request,"BackTesting.add") %>' url="<%= addBackTestingURL.toString() %>" />    
	</liferay-frontend:add-menu>
</c:if>
</aui:col>	
</aui:row>
<aui:row>
<aui:col span="12">
	<div class="alert alert-warning" role="alert">
 		  <liferay-ui:message key="BackTesting.intro_list"/>
	</div>
</aui:col>	
</aui:row>
	
<aui:row>
<aui:col span="12">
	<aui:container cssClass='super-awesome-container'>
		<liferay-ui:search-container cssClass="table  table-striped table-bordered table-hover" searchContainer="${searchBackTesting}" iteratorURL="${iteratorURL}"> 
		<liferay-ui:search-container-results results="${searchBackTesting.getResults()}"/>    
		<liferay-ui:search-container-row  className="com.ibtrader.data.model.BackTesting" keyProperty="backTId" modelVar="BackTesting">
		<%
		String fullDate =  "<div>" + utilities.getWebFormattedShortDate(BackTesting.getFromDate(),user) + "</div>" +
						   "<div>" + utilities.getWebFormattedShortDate(BackTesting.getToDate(),user) + "</div>"   +
						   "<div>" + utilities.getWebFormattedShortDate(BackTesting.getLastRunDate(),user) + "</div>";
		%>
		<liferay-ui:search-container-column-text name="BackTesting.FromTo" value="<%=fullDate%>"/>
		<%
		LocalDate ldTo = BackTesting.getToDate().toInstant()
                .atZone(ZoneId.systemDefault()) // Specify the correct timezone
                .toLocalDate(); 
		LocalDate ldFrom =  BackTesting.getFromDate().toInstant()
                .atZone(ZoneId.systemDefault()) // Specify the correct timezone
                .toLocalDate();
		LocalDate ldExecuted =  BackTesting.getLastRunDate().toInstant()
                .atZone(ZoneId.systemDefault()) // Specify the correct timezone
                .toLocalDate();
		
                
		Double daysBetween = Double.parseDouble(ChronoUnit.DAYS.between(ldFrom, ldTo) + "");
		Double elapasedTime = Double.parseDouble(ChronoUnit.DAYS.between(ldFrom,ldExecuted)+ "");

		Double executed = (elapasedTime /   daysBetween) * 100;
		
        if (ldExecuted.isAfter(ldTo))        
			executed = 100d;
		
        String status =  "<div>" + BackTesting.getStatus() +  "(" + executed.longValue() + "%)</div>" +
				 "<div class=\"progress_" + BackTesting.getBackTId() + "\"><div id=\"dynamic\" class=\"progress-bar progress-bar-success progress-bar-striped active\" role=\"progressbar\" aria-valuenow=\"0\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: 0%\">" + 
				 "<span id=\"current-progress\"></span></div></div>";
				 
				
		%>	

		
		<script>
		var current_progress_<%=BackTesting.getBackTId()%> = <%=executed.longValue()%>;
		AUI().ready(function() {    
			$(".progress_<%=BackTesting.getBackTId()%> #dynamic")
		      .css("width", current_progress_<%=BackTesting.getBackTId()%> + "%")
		      .attr("aria-valuenow", current_progress_<%=BackTesting.getBackTId()%>)
		      .text(current_progress_<%=BackTesting.getBackTId()%> + "%"); 
		
		});
		</script>		
		<liferay-ui:search-container-column-text name="BackTesting.Status" value="<%=status%>"/>
		
		<liferay-ui:search-container-column-text name="BackTesting.count" value="${BackTesting.countordersSELL + BackTesting.countordersBUY}"/>
		<liferay-ui:search-container-column-text name="BackTesting.countBUY" value="${ BackTesting.countordersBUY}"/>
		<liferay-ui:search-container-column-text name="BackTesting.countSELL" value="${BackTesting.countordersSELL }"/>
		<liferay-ui:search-container-column-text name="BackTesting.profit">
	    <div class="chart-container text-center"><canvas id="test${BackTesting.backTId}_profit" width="140" height="100"></canvas></div>		
		<script>
		AUI().ready(function() {    
				showBackTestingResults("test${BackTesting.backTId}_profit", "${utilities.RoundPrice(BackTesting.profitordersSELL + BackTesting.profitordersBUY)}"); 
		
		});
		</script>		
		</liferay-ui:search-container-column-text>
		
		<liferay-ui:search-container-column-text name="BackTesting.profitBUY">
	    <div class="chart-container text-center"><canvas id="test${BackTesting.backTId}_profitBUY" width="140" height="100"></canvas></div>
		
		<script>
		AUI().ready(function() {    
				showBackTestingResults("test${BackTesting.backTId}_profitBUY", "${utilities.RoundPrice(BackTesting.profitordersBUY)}"); 
		
		});
		</script>		
		</liferay-ui:search-container-column-text>
		
		<liferay-ui:search-container-column-text name="BackTesting.profitSELL">
		<div class="chart-container text-center"><canvas id="test${BackTesting.backTId}_profitSELL" width="140" height="100"></canvas></div>
		
		<script>
		AUI().ready(function() {    
			showBackTestingResults("test${BackTesting.backTId}_profitSELL", "${utilities.RoundPrice(BackTesting.profitordersSELL)}"); 

		});
		</script>		
		</liferay-ui:search-container-column-text>
		
	
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


