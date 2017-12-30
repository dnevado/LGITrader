<%@ include file="/init.jsp" %>
<%@page import="com.ibtrader.util.Utilities"%>
<%@page import="com.liferay.portal.kernel.theme.ThemeDisplay"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayPortletMode"%>


<%	ThemeDisplay _themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);	%> 

<aui:container cssClass='super-awesome-container'>

<portlet:renderURL var="addMarketURL">
    <portlet:param name="mvcRenderCommandName" value="/html/add_edit_market"></portlet:param>
    <portlet:param name="marketId" value="0"></portlet:param>
</portlet:renderURL>


<liferay-frontend:add-menu>
    <liferay-frontend:add-menu-item title='<%= LanguageUtil.get(request,"market.addmarket") %>' url="<%= addMarketURL.toString() %>" />    
</liferay-frontend:add-menu>


<liferay-ui:search-container  searchContainer="${searchMarket}" iteratorURL="${iteratorURL}"> 
<liferay-ui:search-container-results results="${searchMarket.getResults()}"/>    
<liferay-ui:search-container-row  className="com.ibtrader.data.model.Market" keyProperty="MarketId" modelVar="Market">
<liferay-ui:search-container-column-text name="market.name" value="${Market.name}" />
<liferay-ui:search-container-column-text name="market.description" value="${Market.description}"/>
<liferay-ui:search-container-column-text name="market.currency" value="${Market.currency}"/>
<liferay-ui:search-container-column-text name="market.start" value="${Market.start_hour}"/>
<liferay-ui:search-container-column-text name="market.end" value="${Market.end_hour}"/>
<liferay-ui:search-container-column-text name="market.active">      
</liferay-ui:search-container-column-text>
<liferay-ui:search-container-column-jsp  path="/html/market_actions.jsp"/>
</liferay-ui:search-container-row>    
<liferay-ui:search-iterator markupView="lexicon"/>
</liferay-ui:search-container>
</aui:container>

