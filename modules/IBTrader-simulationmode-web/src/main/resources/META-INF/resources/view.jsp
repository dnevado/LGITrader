<%@ include file="/init.jsp" %>


<portlet:actionURL name="change_trading" var="change_trading">
 <portlet:param name="redirect" value="<%=themeDisplay.getURLCurrent()%>"/>
</portlet:actionURL>

<aui:form action="<%=change_trading%>"   name="fmshare" method="POST">


<div class="text-center">
<!--  <div class="text-center ${simulated  ? 'alert alert-warning':'alert alert-info'}" role="alert"> -->
	<liferay-ui:message key="${simulated ?  'simulated.mode':'real.mode'}"/>
	<liferay-ui:message key="${IsDayTraderPattern ?  'isDayTraderPattern.yes':'isDayTraderPattern.no'}"/>		
</div>
<div class="text-center">
<button type="submit" class="btn btn-labeled  ${IsTradingEnabled ?  'btn-success':'btn-danger'}">
           <span class="btn-label"><i class="glyphicon glyphicon-${IsTradingEnabled ?  'ok':'remove'}"></i></span>
           <liferay-ui:message key="${IsTradingEnabled ?  'stop_trading':'start_trading'}"/>           
    </button>
    <aui:input type="hidden" name="change_trading" value="${IsTradingEnabled ?  '1':'0'}"/>
</div>
</aui:form>