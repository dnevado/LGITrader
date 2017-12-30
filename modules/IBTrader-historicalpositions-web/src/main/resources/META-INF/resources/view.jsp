
<%@page import="com.ibtrader.util.Utilities"%>
<%@ include file="/init.jsp" %>		


<div class="container-fluid-1280">
<aui:container cssClass='super-awesome-container'>
<liferay-ui:search-container  searchContainer="${searchPosition}" iteratorURL="${iteratorURL}"> 
<liferay-ui:search-container-results results="${searchPosition.getResults()}"/>    
<liferay-ui:search-container-row  className="com.ibtrader.data.model.Position" keyProperty="PositionId" modelVar="Position">
<liferay-ui:search-container-column-jsp path="/extra_info.jsp"/>
<liferay-ui:search-container-column-text name="Date.In" value="<%= Utilities.getWebFormattedDate(Position.getDate_in()) %>" />
<liferay-ui:search-container-column-text name="Price.In" value="${Position.price_in}"/>
<liferay-ui:search-container-column-text name="Date.Out">  
 	<c:set var = "DateOut"  value = ""/>
 	<c:if test="${!empty Position.date_out}"> 
		  <%=Utilities.getWebFormattedDate(Position.getDate_out()) %>			
	</c:if>
</liferay-ui:search-container-column-text>
<liferay-ui:search-container-column-text name="Price.Out" value="${Position.price_out}"/> 
<liferay-ui:search-container-column-text name="Number" value="${Position.share_number}"/> 
<liferay-ui:search-container-column-text name="Rendimiento" value="0"/>
<liferay-ui:search-container-column-jsp path="/position_actions.jsp"/>
</liferay-ui:search-container-row>    
<liferay-ui:search-iterator/>
</liferay-ui:search-container>
</aui:container>
</div>
