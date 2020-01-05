<%@ include file="/init.jsp" %>
<%@ page import="com.ibtrader.data.model.Strategy" %>
<%@ page import="com.liferay.portal.kernel.language.*" %>

<%@ page import="java.util.List" %>

<div class="container-fluid-1280">

<aui:container cssClass='super-awesome-container'>

<portlet:renderURL var="addEStrategyURL">
    <portlet:param name="mvcPath" value="/add_strategy.jsp"></portlet:param>
    <portlet:param name="strategyId" value="0"></portlet:param>
</portlet:renderURL>

<aui:button-row>
    <aui:button onClick="${addEStrategyURL}" value="Add Strategy"></aui:button>
</aui:button-row>

<liferay-ui:search-container  searchContainer="${searchStrategy}"> 
<liferay-ui:search-container-results results="${searchStrategy.getResults() }"/>    
<liferay-ui:search-container-row  className="com.ibtrader.data.model.Strategy" keyProperty="strategyId" modelVar="Strategy">
<liferay-ui:search-container-column-text  name="strategy.name"  property="name"/>
<liferay-ui:search-container-column-text name="strategy.description" property="description"/>
<liferay-ui:search-container-column-text name="strategy.active" property="active"/>
<liferay-ui:search-container-column-text name="strategy.createdate" property="createDate"/>
<liferay-ui:search-container-column-text name="strategy.type" property="type"/>
<liferay-ui:search-container-column-jsp path="/strategy_actions.jsp"/>
</liferay-ui:search-container-row>    
<liferay-ui:search-iterator/>
</liferay-ui:search-container>
</aui:container>

</div>