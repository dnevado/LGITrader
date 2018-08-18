<%@ include file="/init.jsp" %>
<%@page import="java.util.Calendar" %>


<%

String redirect = ParamUtil.getString(request, "redirect");
String  paramPortletName = renderResponse.getNamespace() + "f"; 

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);
%>

<div class="container-fluid-1280"> Generador de información base para generar un estrategia de entrada  y de salida de fillDataStrategyATRMAC<br> 

<portlet:actionURL name="fillDataStrategyATRMAC" var="fillDataStrategyATRMACURL" />
<aui:form action="${fillDataStrategyATRMACURL}" name="<%=paramPortletName%>" method="POST">
<aui:fieldset>
	<aui:input type="number" name="periods" value="201" label="Simple Mobile Average Periods"/>	
</aui:fieldset>	 
<aui:fieldset>
	<aui:input type="number" name="timebars" value="5" label="Simple Mobile Average TimeBars"/>	
</aui:fieldset>
<aui:fieldset>
	<aui:select  name="share" label="Share">
	  <c:if test="${not empty lShares}">	
	  <c:forEach items="${lShares}" var="option">
        <aui:option value="${option.shareId}">${option.name}</aui:option>
    </c:forEach>
    </c:if>
	</aui:select> 
</aui:fieldset>
<aui:fieldset>
		<div>Hour of signal (note user Timezone,user test in local, -1 hour localtime) <br/>
		This data filled. Data will be removed to prevent collisions and make sure integrity  </div>
  		<liferay-ui:input-time name="starthour" minuteInterval="5" hourValue="" minuteValue="" 
  		 minuteParam="00" amPmParam="PM" hourParam="00"/>  		   	
</aui:fieldset>
      <aui:button-row>
        <aui:button type="submit" cssClass="btn-lg"></aui:button>
        <aui:button type="cancel" cssClass="btn-lg" onClick="<%=redirect%>"></aui:button>
    </aui:button-row>

</aui:form>
</div>