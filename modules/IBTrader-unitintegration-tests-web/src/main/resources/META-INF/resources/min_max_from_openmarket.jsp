<%@ include file="/init.jsp" %>
<%@page import="java.util.Calendar" %>


<%

String redirect = ParamUtil.getString(request, "redirect");
String  paramPortletName = renderResponse.getNamespace() + "f"; 

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);
%>

<div class="container-fluid-1280"> Generador de información base para generar un estrategia de entrada  y de salida de Mínimo y Máximos.<br>
1. ESTABLECEMOS EL MAXIMO Y MINIMO.COMO SE BORRA TODO, DEBE FUNCIONAR  <br>
2. A LA HORA DE LA SEÑAL METEMOS LA SEÑAL ENTRADA A LARGO <br>
3. A LOS 30 SEGUNDOS GENERAMOS EL VALOR PARA SALIR POR STOPROFIT <br>
4. A LOS 60 SEGUNDOS GENERAMOS EL VALOR PARA ENTRADA A CORTO <br>
5. A LOS 90 SEGUNDOS GENERAMOS EL VALOR PARA SALIR POR STOPLOST <br>
6. A LOS 120 SEGUNDOS METEMOS LA SEÑAL ENTRADA A LARGO <br>
7. A LOS 150 SEGUNDOS SALE POR TRAILING STOP   <br>
<strong>Si existe un valor máximo lo coge, si no el que se pasa como parámetro</strong>

 <br>
<portlet:actionURL name="fillDataStrategyMinMax" var="fillDataStrategyMinMaxURL" />
<aui:form action="${fillDataStrategyMinMaxURL}" name="<%=paramPortletName%>" method="POST">
<aui:fieldset>
	<aui:input type="number" name="OffSet1FromOpenMarket" value="1" label="OffSet1FromOpenMarket"/>	
</aui:fieldset>	 
<aui:fieldset>
	<aui:input type="number" name="percentual_limit_buy" value="2" label="percentual_limit_buy"/>	
</aui:fieldset>
<aui:fieldset>
	<aui:input type="number" name="OffSet2FromOpenMarket" value="30" label="OffSet2FromOpenMarket"/>	
</aui:fieldset>
<aui:fieldset>
		<aui:input type="number" name="percentual_stop_lost" value="2" label="percentual_stop_lost"/>	
</aui:fieldset>
<aui:fieldset>
		<aui:input type="number" name="percentual_stop_profit" value="2" label="percentual_stop_profit"/>	
</aui:fieldset>
<aui:fieldset>
		<aui:input type="number" name="percentual_trailling_stop_lost" value="3" label="percentual_trailling_stop_lost"/>	
</aui:fieldset>
<aui:fieldset>
		<aui:input type="number" name="percentual_gap" value="2" label="percentual_gap"/>	
</aui:fieldset>
<aui:fieldset>
		<aui:input type="number" name="max" value="250" label="max"/>	
</aui:fieldset>
<aui:fieldset>
		<aui:input type="number" name="min" value="50" label="min"/>	
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