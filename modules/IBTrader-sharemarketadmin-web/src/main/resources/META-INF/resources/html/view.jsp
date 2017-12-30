
<%@page import="com.ibtrader.util.Utilities"%>
<%@ include file="/init.jsp" %>		




<aui:container cssClass='super-awesome-container'>

<portlet:renderURL var="addShareURL">
    <portlet:param name="mvcRenderCommandName" value="/html/add_edit_share"></portlet:param>
    <portlet:param name="shareId" value="0"></portlet:param>
</portlet:renderURL>

<portlet:renderURL var="addMarketURL">
    <portlet:param name="mvcRenderCommandName" value="/html/add_edit_market"></portlet:param>
    <portlet:param name="marketId" value="0"></portlet:param>
</portlet:renderURL>
<portlet:renderURL var="viewMarketURL">
    <portlet:param name="mvcRenderCommandName" value="/html/view_market"></portlet:param>    
</portlet:renderURL>



<liferay-frontend:add-menu>
    <liferay-frontend:add-menu-item title='<%= LanguageUtil.get(request,"share.addshare") %>' url="<%= addShareURL.toString() %>" />    
</liferay-frontend:add-menu>

<aui:button-row>    
    <aui:button onClick="${viewMarketURL}" value="market.view_market"></aui:button>
</aui:button-row>


<liferay-ui:search-container  searchContainer="${searchShare}" iteratorURL="${iteratorURL}"> 
<liferay-ui:search-container-results results="${searchShare.getResults()}"/>    
<liferay-ui:search-container-row  className="com.ibtrader.data.model.Share" keyProperty="ShareId" modelVar="Share">
<liferay-ui:search-container-column-text name="share.name" value="${Share.name}" />
<liferay-ui:search-container-column-text name="share.symbol" value="${Share.symbol}"/>
<liferay-ui:search-container-column-text name="share.active">
<c:choose>
   <c:when test="${Share.active}">
		<liferay-ui:message key="share.activeYES"/>
   </c:when>
    <c:otherwise>
    	<liferay-ui:message key="share.activeNO"/>
    </c:otherwise>
</c:choose>
</liferay-ui:search-container-column-text>
<liferay-ui:search-container-column-text name="share.numbertopurchase" value="${Share.numbertopurchase}"/>
<liferay-ui:search-container-column-text name="share.percentual_limit_buy" value="${Share.percentual_limit_buy}"/>
<liferay-ui:search-container-column-text name="share.percentual_stop_lost" value="${Share.percentual_stop_lost}"/>
<liferay-ui:search-container-column-text name="share.percentual_stop_profit" value="${Share.percentual_stop_profit}"/>
<liferay-ui:search-container-column-text name="share.security_type" value="${Share.security_type}"/>
<liferay-ui:search-container-column-text name="share.primary_exchange" value="${Share.primary_exchange}"/>
<liferay-ui:search-container-column-text name="share.createDate">
		  <%=Utilities.getWebFormattedDate(Share.getCreateDate()) %>			
</liferay-ui:search-container-column-text>
<liferay-ui:search-container-column-jsp path="/html/share_actions.jsp"/>
</liferay-ui:search-container-row>    
<liferay-ui:search-iterator markupView="lexicon"/>
</liferay-ui:search-container>
</aui:container>

