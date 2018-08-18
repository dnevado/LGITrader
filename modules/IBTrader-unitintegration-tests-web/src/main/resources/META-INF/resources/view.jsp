<%@ include file="/init.jsp" %>
<%@page import="java.util.Calendar" %>



CARGA DE INFORMACION PARA LAS DOS ESTRATEGIAS
<portlet:renderURL var="MediaMovil8URL">
    <portlet:param name="mvcPath" value="/mobile_average_8.jsp" />
</portlet:renderURL>
<portlet:renderURL var="MaxMinStrategyURL">    
    <portlet:param name="mvcPath" value="/min_max_from_openmarket.jsp" />
</portlet:renderURL>
<portlet:renderURL var="fillDataStrategyATRMACURL">    
    <portlet:param name="mvcPath" value="/atr_mcad_exp_average.jsp" />
</portlet:renderURL>
<aui:button-row>

<aui:button-row>    
    <aui:button onClick="${MediaMovil8URL}" value="mediamovilData"></aui:button>
    <aui:button onClick="${MaxMinStrategyURL}" value="MinMaxData"></aui:button>
    <aui:button onClick="${fillDataStrategyATRMACURL}" value="fillDataStrategyATRMA"></aui:button>
</aui:button-row>	
</aui:button-row>

