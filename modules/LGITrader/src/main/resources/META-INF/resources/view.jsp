<%@ include file="/init.jsp" %>


<portlet:actionURL name="CronRead" var="CronRead" />

<aui:a href="<%= CronRead %>" >CronRead</aui:a>

<portlet:actionURL name="CronTrade" var="CronTrade" />

<aui:a href="<%= CronTrade %>" >CronTrade</aui:a>

<portlet:actionURL name="CronVerifyContract" var="CronVerifyContract" />

<aui:a href="<%= CronVerifyContract %>" >CronVerifyContract</aui:a>


