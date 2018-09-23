
<%@page import="com.ibtrader.util.Utilities"%>
<%@ include file="/init.jsp" %>		

<div id="shares_list" class="container-fluid-1280">

<liferay-ui:success key="share.delete.success" message="share.delete.success"/>
<liferay-ui:error key="share.error.positionexists" message="share.error.positionexists"/>
<liferay-ui:error key="share.error.missingparameters" message="share.error.missingparameters"/>
<liferay-ui:error key="market.error.shareexists" message="market.error.shareexists"/>
<liferay-ui:success key="market.delete.success" message="market.delete.success"/>


<aui:container cssClass='super-awesome-container'>

<portlet:renderURL var="addShareURL">
    <portlet:param name="mvcRenderCommandName" value="/html/add_edit_share"></portlet:param>
    <portlet:param name="redirect" value="<%=themeDisplay.getURLCurrent()%>"/>
    <portlet:param name="shareId" value="0"></portlet:param>
</portlet:renderURL>

<portlet:renderURL var="addMarketURL">
    <portlet:param name="mvcRenderCommandName" value="/html/add_edit_market"></portlet:param>
    <portlet:param name="redirect" value="<%=themeDisplay.getURLCurrent()%>"/>
    <portlet:param name="marketId" value="0"></portlet:param>
</portlet:renderURL>
<portlet:renderURL var="viewMarketURL">
    <portlet:param name="mvcRenderCommandName" value="/html/view_market"></portlet:param>
    <portlet:param name="redirect" value="<%=themeDisplay.getURLCurrent()%>"/>    
</portlet:renderURL>



<liferay-frontend:add-menu>
    <liferay-frontend:add-menu-item title='<%= LanguageUtil.get(request,"share.addshare") %>' url="<%= addShareURL.toString() %>" />    
</liferay-frontend:add-menu>

<aui:button-row>    
    <aui:button   cssClass="btn btn-lg btn-primary btn-default" onClick="${viewMarketURL}" value="market.view_market"></aui:button>
</aui:button-row>


<liferay-ui:search-container cssClass="table table-responsive table-striped table-bordered table-hover"  searchContainer="${searchShare}" iteratorURL="${iteratorURL}"> 
<liferay-ui:search-container-results results="${searchShare.getResults()}"/>    
<liferay-ui:search-container-row  className="com.ibtrader.data.model.Share" keyProperty="ShareId" modelVar="Share">
<liferay-ui:search-container-column-text  orderable="<%= true %>"  orderableProperty="name" name="share.name" value="${Share.name}(${Share.symbol})" />
<liferay-ui:search-container-column-text name="share.active">
<c:set var="checked" value="false"/>
<c:choose>
   <c:when test="${Share.active}">
   		<c:set var="checked" value="true"/>		
   </c:when>    
</c:choose> 
<aui:input  readOnly="readOnly"   id="active_${Share.shareId}" name="share.active" type="toggle-switch"  checked="${checked}" />
</liferay-ui:search-container-column-text>
<liferay-ui:search-container-column-text name="share.validated">
<c:set var="checked2" value="false"/>
<c:choose>
   <c:when test="${Share.validated_trader_provider}">
   		<c:set var="checked2" value="true"/>		
   </c:when>    
</c:choose>
<aui:input  readOnly="readOnly"   id="validated_${Share.shareId}" name="share.validated" type="toggle-switch"  checked="${checked2}" />
</liferay-ui:search-container-column-text>
<liferay-ui:search-container-column-text orderable="<%= true %>"  property="numbertopurchase" orderableProperty="numbertopurchase" name="share.numbertopurchase" value="${Share.numbertopurchase}"/> 
<liferay-ui:search-container-column-text orderable="<%= true %>"  property="security_type" orderableProperty="security_type" name="share.security_type" value="${Share.security_type}"/>
<liferay-ui:search-container-column-text orderable="<%= true %>"  property="primary_exchange" orderableProperty="primary_exchange" name="share.primary_exchange" value="${Share.primary_exchange}"/>
<liferay-ui:search-container-column-jsp path="/html/share_market_data.jsp"/>
<liferay-ui:search-container-column-jsp path="/html/share_strategies_data.jsp"/>
<liferay-ui:search-container-column-jsp path="/html/share_actions.jsp"/>
</liferay-ui:search-container-row>    
<liferay-ui:search-iterator markupView="lexicon"/>
</liferay-ui:search-container>
</aui:container>
<!--  SI SE QUIERE LA FECCHA DE CREACION  liferay -ui :s earch-container-column-text name="share.createDate">
		  Utilities.getWebFormattedDate(${Share.createDate})			
< liferay - ui : search-container-column-text>-->
</div>