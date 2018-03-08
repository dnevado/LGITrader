<%@page import="com.ibtrader.data.service.StrategyShareLocalServiceUtil"%>
<%@ include file="/init.jsp" %>

<%
String mvcPath = ParamUtil.getString(request, "mvcPath");

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
Strategy strategy = (Strategy) row.getObject();
long StrategyId = strategy.getStrategyID();
Share share=null;
long shareId =0;
StrategyShare strategyshare = null;
boolean showActions = true;
if (request.getAttribute("share")!=null)
{
	share = (Share) request.getAttribute("share");
	shareId = share.getShareId();
	/* EXISTE YA CONFIGURADA */	
	 strategyshare = StrategyShareLocalServiceUtil.getByCommpanyShareStrategyId(share.getGroupId(), share.getCompanyId(), shareId, StrategyId);
	 showActions = strategyshare !=null; 
}


%>
<c:set  var="showActions" value="<%=showActions %>"/> 
<c:set  var="StrategyId" value="<%=StrategyId %>"/> 
<portlet:renderURL   var="EditStrategyShareURL">
	<portlet:param name="mvcRenderCommandName" value="/html/add_edit_strategyshare"/>
    <portlet:param name="shareId" value="<%= String.valueOf(shareId) %>"/>
    <portlet:param name="strategyId" value="<%= String.valueOf(StrategyId) %>"/>    
    <portlet:param name="tab" value="share.strategy"></portlet:param>
</portlet:renderURL>

 <aui:input label="strategyshare.enabledisabled" id="strategy_${StrategyId}"  onClick="<%= renderResponse.getNamespace() + \"switchStrategyShare(this)\"%>"  name="strategy_${StrategyId}" value="1" type="toggle-card" checked="${showActions ? 'true':''}"/>       
 <c:if test="${showActions}">
 	<liferay-ui:icon-menu>
		<liferay-ui:icon image="edit" message="strategyshare.edit" url="${EditStrategyShareURL}" />
	</liferay-ui:icon-menu>    

</c:if>