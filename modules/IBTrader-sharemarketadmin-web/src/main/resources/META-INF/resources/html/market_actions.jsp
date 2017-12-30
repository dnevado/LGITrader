<%@ include file="/init.jsp" %>

<%
String mvcPath = ParamUtil.getString(request, "mvcPath");

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

//System.out.println(row.getObject());

Market market = (Market) row.getObject();

long MarketId = market.getMarketId();


%>



<portlet:actionURL   var="RemoveMarketURL" name="RemoveMarket"> 	
    <portlet:param name="marketId" value="<%= String.valueOf(MarketId) %>"/>   
</portlet:actionURL>

<portlet:renderURL   var="EditMarketURL">
	<portlet:param name="mvcRenderCommandName" value="/html/add_edit_market"/>
    <portlet:param name="marketId" value="<%= String.valueOf(MarketId) %>"/>    
</portlet:renderURL>
<portlet:renderURL   var="AddShareMarketURL">
	<portlet:param name="mvcRenderCommandName" value="/html/add_edit_share"/>
    <portlet:param name="marketId" value="<%= String.valueOf(MarketId) %>"/>    
</portlet:renderURL>  
 
<liferay-ui:icon-menu>
<liferay-ui:icon image="add" message="market.addshare" url="${AddShareMarketURL}" />
<liferay-ui:icon image="edit" message="market.edit" url="${EditMarketURL}" />
<liferay-ui:icon image="delete" message="market.delete" url="${RemoveMarketURL}" />                         
</liferay-ui:icon-menu>
