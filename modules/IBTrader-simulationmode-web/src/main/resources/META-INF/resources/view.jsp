<%@ include file="/init.jsp" %>

<div class="text-center ${simulated=='1' ? 'alert alert-warning':'alert alert-info'}" role="alert"><liferay-ui:message key="${simulated=='1' ? 'simulated.mode':'real.mode'}"/></div>

