<%@ include file="/init.jsp" %>


<div class="text-center ${simulated  ? 'alert alert-warning':'alert alert-info'}" role="alert">
	<liferay-ui:message key="${simulated ?  'simulated.mode':'real.mode'}"/>
	<liferay-ui:message key="${IsDayTraderPattern ?  'isDayTraderPattern.yes':'isDayTraderPattern.no'}"/>
</div>

