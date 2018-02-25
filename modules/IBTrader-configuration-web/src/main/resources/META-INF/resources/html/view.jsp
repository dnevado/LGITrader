<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@ include file="/init.jsp" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="com.ibtrader.data.model.Market" %>
<%@ page import="com.ibtrader.util.ConfigKeys" %>
<%@ page import="com.liferay.portal.kernel.portlet.*" %>
<%@ page import="com.liferay.portal.kernel.theme.ThemeDisplay" %>
<%@ page import="com.liferay.portal.kernel.util.*" %>



<%

 String redirect = ParamUtil.getString(request, "redirect");
 String  paramPortletName = renderResponse.getNamespace() + "f"; 
 portletDisplay.setShowBackIcon(true);
 portletDisplay.setURLBack(redirect);
 
// ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
 
 
%>

<div class="container-fluid-1280">


 <portlet:actionURL name="addeditConfiguration" var="addeditConfigurationURL" />

<aui:form action="${addeditConfigurationURL}" name='<%=renderResponse.getNamespace() +"f"%>'  id='<%=renderResponse.getNamespace()+"f"%>' method="POST">
	<liferay-ui:success key="configuration.success" message="configuration.success"/>	
	<liferay-ui:error key="configuration.error.formatparameters" message="configuration.error.formatparameters"/>
	<liferay-ui:error key="configuration.user.invalidexefilename" message="configuration.user.invalidexefilename"/>
	<liferay-ui:error key="configuration.user.passwordinvalid" message="configuration.user.passwordinvalid"/>
	<liferay-ui:error key="configuration.user.userinvalid" message="configuration.user.userinvalid"/>
	<liferay-ui:error key="configuration.user.portinvalid" message="configuration.user.portinvalid"/>
	<liferay-ui:error key="configuration.user.ipinvalid" message="configuration.user.ipinvalid"/>
	<liferay-ui:error key="configuration.user.invalidfilename" message="configuration.user.invalidfilename"/>
	<c:if test = "${isOmniadmin}"><!--  no lo presentamos si no son onmiadmins  -->          	
    	<liferay-ui:panel-container accordion="true" extended="true" markupView="lexicon">
      		 <liferay-ui:panel title="interactive.tws.realaccount" markupView="lexicon">
    	 	<aui:fieldset>	  	     
	        <aui:input type="text" placeholder="127.0.0.1"  label="interactive.tws.ip"  name="ip" value="${_HOST}" disabled="${!isOmniadmin}">        
	                <aui:validator  name="required" />	
	                <aui:validator name="maxLength">75</aui:validator>
	        </aui:input>       
	        <aui:input type="number" placeholder="7897" label="interactive.tws.port"  name="port" value="${_PORT}" disabled="${!isOmniadmin}">
	             <aui:validator name="number" />
	             <aui:validator  name="required" />
				 <aui:validator name="range">[1,65536]</aui:validator>
	        </aui:input>
	        <aui:input type="text" placeholder="C:\ibcontroller\IBControllerStart.bat"  label="interactive.tws.ibcontrollerexe" disabled="${!isOmniadmin}"  name="ibcontrollerexe" value="${_PATH_TO_EXECUTABLE_FILE}">
	                <aui:validator  name="required" />	
	                <aui:validator name="maxLength">150</aui:validator>
	        </aui:input>           
	       	<aui:input type="text" placeholder="C:\ibcontroller\IBController.ini"  label="interactive.tws.ibcontroller" disabled="${!isOmniadmin}"  name="ibcontrollerpath" value="${_PATH_TO_CONFIGURATION_FILE}">
	                <aui:validator  name="required" />	
	                <aui:validator name="maxLength">150</aui:validator>
	        </aui:input>  
	        </aui:fieldset>      
   	       </liferay-ui:panel>
	 	</liferay-ui:panel-container> 
     	<liferay-ui:panel-container accordion="true" extended="true" markupView="lexicon">
      		 <liferay-ui:panel title="interactive.tws.paperaccount" markupView="lexicon">
    	  
	        <aui:input type="text" placeholder="127.0.0.1"  label="interactive.tws.ip"  name="paper_ip" value="${_PAPER_TWS_HOST}" disabled="${!isOmniadmin}">        
	                <aui:validator  name="required" />	
	                <aui:validator name="maxLength">75</aui:validator>
	        </aui:input>       
	        <aui:input type="number" placeholder="7897" label="interactive.tws.port"  name="paper_port" value="${_PAPER_TWS_PORT}" disabled="${!isOmniadmin}">
	             <aui:validator name="number" />
	             <aui:validator  name="required" />
				 <aui:validator name="range">[1,65536]</aui:validator>
	        </aui:input>
	        <aui:input type="text" placeholder="C:\ibcontroller\IBControllerStart.bat"  label="interactive.tws.ibcontrollerexe" disabled="${!isOmniadmin}"  name="paper_ibcontrollerexe" value="${_PAPER_PATH_TO_EXECUTABLE_FILE}">
	                <aui:validator  name="required" />	
	                <aui:validator name="maxLength">150</aui:validator>
	        </aui:input>           
	       	<aui:input type="text" placeholder="C:\ibcontroller\IBController.ini"  label="interactive.tws.ibcontroller" disabled="${!isOmniadmin}"  name="paper_ibcontrollerpath" value="${_PAPER_PATH_TO_CONFIGURATION_FILE}">
	                <aui:validator  name="required" />	
	                <aui:validator name="maxLength">150</aui:validator>
	        </aui:input>             	
     	  </liferay-ui:panel>
	 	</liferay-ui:panel-container> 
     </c:if>
      <liferay-ui:panel-container accordion="true" extended="true" markupView="lexicon">
       <liferay-ui:panel title="interactive.tws.userrealaccount"  markupView="lexicon">
		      <aui:input type="text"  label="interactive.tws.user"  name="user" value="${_USER_TWS}">
		                <aui:validator  name="required" />	    
		                <aui:validator name="maxLength">75</aui:validator>                          
		       </aui:input>
		       <aui:input type="password"  label="interactive.tws.password"  name="password" value="${_PWD_TWS}">
		                <aui:validator  name="required" />	    
		                <aui:validator name="maxLength">75</aui:validator>                          
	       </aui:input>  
	       </liferay-ui:panel>
	 	</liferay-ui:panel-container>     
	 <liferay-ui:panel-container accordion="true" extended="true" markupView="lexicon">
     	<liferay-ui:panel  title="interactive.tws.userpaperaccount"  markupView="lexicon"> 
	       <aui:input type="text"  label="interactive.tws.user"  name="paper_user" value="${_PAPER_USER_TWS}">
	                <aui:validator  name="required" />	    
	                <aui:validator name="maxLength">75</aui:validator>                          
	       </aui:input>
	       <aui:input type="password"  label="interactive.tws.password"  name="paper_password" value="${_PAPER_USER_PWD}">
	                <aui:validator  name="required" />	    
	                <aui:validator name="maxLength">75</aui:validator>                          
	       </aui:input>         	   
      	 </liferay-ui:panel>
	 </liferay-ui:panel-container>     
       <aui:fieldset  label="interactive.tws.notifications" id="interactive.tws.notifications">            
        	<aui:input label="desktop_notifications" name="desktop_notifications" type="toggle-card" checked="${_DESTOP_NOTIFICATION eq '1' ? 'true':''}"/>       
        	<aui:input label="email_notifications" name="email_notifications"   type="toggle-card" checked="${_MAIL_NOTIFICATION  eq '1' ? 'true':''}"/>                     
        	<aui:input label="simulation_mode" name="simulation_mode"   type="toggle-card" checked="${_SIMULATION_MODE eq '1' ? 'true':''}"/>        	
      </aui:fieldset>
      
        
      
      <!--
       <div class="alert alert-danger" role="alert">
		  <strong><liferay-ui:message key="configuration.user.needreboot_body"/></strong>   
       </div> -->
	 <aui:button-row>	 	
	 	 <aui:button cssClass="btn-lg" type="submit"/> 	     
        <aui:button type="cancel"cssClass="btn-lg" onClick="<%=redirect%>"></aui:button>
    </aui:button-row>
</aui:form>
</div>