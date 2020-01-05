<%@ include file="/init.jsp" %>

<%@page import="com.ibtrader.util.Utilities"%>
<%@page import="com.liferay.portal.kernel.theme.ThemeDisplay"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayPortletMode"%>


<%	ThemeDisplay _themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);	%> 

<div class="strategyshareresults container-fluid-1280">

<%@include file="/html/includes/tabs.jsp"%>
<aui:container cssClass='super-awesome-container'>
<portlet:renderURL var="addShareURL">
    <portlet:param name="mvcRenderCommandName" value="/html/add_edit_share"></portlet:param>
    <portlet:param name="shareId" value="0"></portlet:param>
</portlet:renderURL>

<portlet:renderURL var="addMarketURL">
    <portlet:param name="mvcRenderCommandName" value="/html/add_edit_market"></portlet:param>
    <portlet:param name="marketId" value="0"></portlet:param>
</portlet:renderURL>


<script type="text/javascript">

function <portlet:namespace/>switchStrategyShare(StrategyShare)
{
	console.log('<%=_themeDisplay.getPortletDisplay().getPortletName()%>');
	var paramPortletName = '<%= renderResponse.getNamespace()%>';
	var StrategyID = StrategyShare.id.replace(paramPortletName,"");  
	StrategyID= StrategyID.replace("strategy_","");
	console.log(StrategyID);
	console.log (StrategyShare.checked);
	
	
	AUI().use("liferay-portlet-url", function(a) {
		var actionURL = Liferay.PortletURL.createActionURL();
		actionURL.setWindowState("<%=LiferayWindowState.NORMAL.toString() %>");
		actionURL.setPortletMode("<%=LiferayPortletMode.VIEW %>");
		actionURL.setParameter("strategyId",StrategyID);
		actionURL.setParameter("shareId", "${share.shareId}");	
		actionURL.setParameter("active", StrategyShare.checked); // activa o no	
		actionURL.setParameter("javax.portlet.action","addStrategyShare");
		actionURL.setPortletId("<%=_themeDisplay.getPortletDisplay().getId() %>");
		
		jQuery.ajax({
		    type: 'POST',
		    url: actionURL,
		    success: function(data){
		    	//alert( data);
		    }
		  });
	
	});
}

/* QUITAMOS EL AUTOFIT DE LA TABLE */
AUI().ready(function() {

	$("#strategyshareresults table").removeClass("table-autofit");
	
	/* LOS TEXTOS DE LOS TOGGLE */
	$(".toggle-card-container .toggle-card-label span").text("");
})

</script>


<liferay-frontend:add-menu>
    <liferay-frontend:add-menu-item title='<%= LanguageUtil.get(request,"share.addshare") %>' url="<%= addShareURL.toString() %>" />
    <liferay-frontend:add-menu-item title='<%= LanguageUtil.get(request,"share.addmarket") %>' url="<%= addMarketURL.toString() %>" />
</liferay-frontend:add-menu>
<aui:row>
<aui:col span="12">
<jsp:include page="/html/strategyshare_filter.jsp"/>
</aui:col>
</aui:row>
<aui:row>
<aui:col span="12">
<div id="strategyshareresults">
<liferay-ui:search-container id="strategyshareresults" searchContainer="${searchStrategy}" iteratorURL="${iteratorURL}"> 
<liferay-ui:search-container-results results="${searchStrategy.getResults()}"/>    
<liferay-ui:search-container-row  className="com.ibtrader.data.model.Strategy" keyProperty="strategyId" modelVar="Strategy">
<liferay-ui:search-container-column-text cssClass="col-md-2" name="strategy.name" value="${Strategy.name}" />
<liferay-ui:search-container-column-text cssClass="col-md-6" name="strategy.description" value="${Strategy.description}"/>
<liferay-ui:search-container-column-jsp  cssClass="col-md-2" path="/html/add_edit_strategyshare_actions.jsp"/>
</liferay-ui:search-container-row>    
<liferay-ui:search-iterator markupView="lexicon"/>
</liferay-ui:search-container>
</div>
</aui:col>
</aui:row>
</aui:container>
</div>
