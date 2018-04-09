<%@include file="/init.jsp"%>
<%@ page import="com.ibtrader.data.model.Share" %>

	<portlet:renderURL var="statusURL"/>
	
	
	<portlet:renderURL var="detailsShareURL">
    	<portlet:param name="mvcRenderCommandName" value="/html/add_edit_share"></portlet:param>
    	<portlet:param name="tab" value="share.details"></portlet:param>
    	<portlet:param name="shareId" value="${share.shareId}"></portlet:param>
	</portlet:renderURL>
	
	<portlet:renderURL var="StrategiesShareURL">
    	<portlet:param name="mvcRenderCommandName" value="/html/view_strategyshare"></portlet:param>
    	<portlet:param name="tab" value="share.strategy"></portlet:param>
    	<portlet:param name="shareId" value="${share.shareId}"></portlet:param>
	</portlet:renderURL>

	<portlet:renderURL var="RulesShareURL">
    	<portlet:param name="mvcRenderCommandName" value="/html/view_rulesshare"></portlet:param>
    	<portlet:param name="tab" value="share.rule"></portlet:param>
    	<portlet:param name="shareId" value="${share.shareId}"></portlet:param>
	</portlet:renderURL>
	
    <% 
    Share _TabsShare = (Share) request.getAttribute("share");    
    if (_TabsShare!=null) { %>
		<liferay-ui:tabs names="share.details,share.strategy,share.rule" param="tab" value="${tab_selected}" 	
			url0="${detailsShareURL }"
			url1="${StrategiesShareURL }"
			url2="${RulesShareURL }"/>
	<% } else {  %>
			<liferay-ui:tabs names="share.details" param="tab" value="${tab_selected}" 	
				url0="${detailsShareURL }"/>
	<% }  %>