
<%@page import="com.ibtrader.util.*"%>

<%@ include file="/init.jsp" %>		


<div class="container-fluid-1280">
<aui:row>
<aui:col span="12">
<jsp:include page="/position_filter.jsp"/>
</aui:col>
</aui:row>
<aui:row>
<aui:col span="9">
	<aui:container cssClass='super-awesome-container'>
	<liferay-ui:search-container cssClass="table  table-striped table-bordered table-hover" searchContainer="${searchPosition}" iteratorURL="${iteratorURL}"> 
	<liferay-ui:search-container-results results="${searchPosition.getResults()}"/>    
	<liferay-ui:search-container-row  className="com.ibtrader.data.model.Position" keyProperty="PositionId" modelVar="Position">
	<liferay-ui:search-container-column-jsp name="Symbol" path="/extra_info.jsp"/>	
	<liferay-ui:search-container-column-text name="Price.In" value="${Position.price_real_in}"/>
	<liferay-ui:search-container-column-text name="Price.Out" value="${Position.price_real_out}"/>
	<liferay-ui:search-container-column-text name="Operation" value="${Position.type}"/>
	<liferay-ui:search-container-column-text name="Rendimiento">
	<%
	
	String redirect = themeDisplay.getURLCurrent();
	
	double profit =  Position.getPrice_real_out() - Position.getPrice_real_in();
	double percentual_profit=0;
	if (Position.getType().equals(PositionStates.statusTWSFire.SELL.toString())) // operacion de compra normal..??
		profit =   Position.getPrice_real_in() - Position.getPrice_real_out();
	percentual_profit = (profit /  Position.getPrice_real_in() * 100);
	percentual_profit = Utilities.RoundPrice(percentual_profit);
	if (profit>0) {%>     
		<span class="badge badge-pill badge-success"><%=percentual_profit %></span>
		<% } else { %>
		<span class="badge badge-pill badge-danger"><%=percentual_profit %></span>
	<% } %>       
	</liferay-ui:search-container-column-text>
	<liferay-ui:search-container-column-text name="StopLost" value="${Position.percentualstoplost_out}"/>
	<liferay-ui:search-container-column-text name="StopProfit" value="${Position.percentualstopprofit_out}"/>
	<liferay-ui:search-container-column-text name="Number" value="${Position.share_number}"/>
	<liferay-ui:search-container-column-text name="Date.In" value="<%= Utilities.getWebFormattedDate(Position.getDate_in(),user) %>" />
	<liferay-ui:search-container-column-text name="Date.Out">  
	 	<c:set var = "DateOut"  value = ""/>
	 	<c:if test="${!empty Position.date_out}"> 
			  <%=Utilities.getWebFormattedDate(Position.getDate_out(),user) %>			
		</c:if>
	</liferay-ui:search-container-column-text> 
	<c:set var="strategy_in" value="${fn:split(Position.strategy_in, '.')}" />
	<c:set var="strategy_out" value="${fn:split(Position.strategy_out, '.')}"/>
	<liferay-ui:search-container-column-text name="Strategy" value="${strategy_in[fn:length(strategy_in)-1]} ${strategy_out[fn:length(strategy_out)-1]}"/>	
	<liferay-ui:search-container-column-jsp path="/position_actions.jsp"/>
	</liferay-ui:search-container-row>    
	<liferay-ui:search-iterator markupView="lexicon"/>
	</liferay-ui:search-container>
	</aui:container>
</aui:col>	
<aui:col span="3">
	<jsp:include page="/position_graph_results.jsp"/>	
</aui:col>	
</aui:row>
</div>
