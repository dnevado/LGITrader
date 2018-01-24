<%@ include file="/init.jsp" %>
<%@ include file="/init.jsp" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="com.ibtrader.data.model.Market" %>
<%@ page import="com.ibtrader.util.ConfigKeys" %>



<%

 String redirect = ParamUtil.getString(request, "redirect");
 String  paramPortletName = renderResponse.getNamespace() + "f"; 
 
 portletDisplay.setShowBackIcon(true);
 portletDisplay.setURLBack(redirect);

 
%>

<portlet:actionURL name="addeditConfiguration" var="addeditConfigurationURL" />


<div class="container-fluid-1280">
<aui:form action="${addeditConfigurationURL}" name="<%=paramPortletName%>" method="POST">
	
	<liferay-ui:success key="configuration.success" message="configuration.success"/>	
	<liferay-ui:error key="configuration.error.formatparameters" message="configuration.error.formatparameters"/>	
    <aui:fieldset collapsed="false" collapsible="false"  label="interactive.tws.config" id="interactive.tws.config">
        <aui:input type="text" placeholder="127.0.0.1"  label="interactive.tws.ip"  name="ip" value="${_HOST}">
                <aui:validator  name="required" />	
                <aui:validator name="maxLength">75</aui:validator>
        </aui:input>       
         <aui:input type="number" placeholder="7897" label="interactive.tws.port"  name="port" value="${_PORT}">
             <aui:validator name="number" />
             <aui:validator  name="required" />
			 <aui:validator name="range">[1,65536]</aui:validator>
       </aui:input>
     </aui:fieldset>       
     <aui:fieldset collapsed="false" collapsible="false"  label="interactive.tws.credentials" id="interactive.tws.credentials">
	       <aui:input type="text"  label="interactive.tws.user"  name="user" value="${_USER_TWS}">
	                <aui:validator  name="required" />	    
	                <aui:validator name="maxLength">75</aui:validator>                          
	       </aui:input>
	       <aui:input type="password"  label="interactive.tws.password"  name="password" value="${_PWD_TWS}">
	                <aui:validator  name="required" />	    
	                <aui:validator name="maxLength">75</aui:validator>                          
	       </aui:input>  
       </aui:fieldset>       
       <aui:fieldset collapsed="false" collapsible="false"  label="interactive.tws.notifications" id="interactive.tws.notifications">            
        	<aui:input label="desktop_notifications" name="desktop_notifications" type="toggle-card" checked="${_DESTOP_NOTIFICATION eq 'S' ? 'true':''}"/>       
        	<aui:input label="email_notifications" name="email_notifications"   type="toggle-card" checked="${_MAIL_NOTIFICATION eq 'S' ? 'true':''}"/>                     
       </aui:fieldset>       
	 <aui:button-row>
        <aui:button type="submit"></aui:button>
        <aui:button type="cancel" onClick="<%=redirect%>"></aui:button>
    </aui:button-row>
</aui:form>
</div>