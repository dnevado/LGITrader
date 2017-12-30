<%@ include file="/init.jsp" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="com.ibtrader.data.model.Market" %>
<%@ page import="com.ibtrader.util.ConfigKeys" %>



<%

Calendar cal = Calendar.getInstance();
 int startTimeDay = cal.get(Calendar.DAY_OF_MONTH);
 int startTimeMonht = cal.get(Calendar.MONTH);
 int startTimeYear = cal.get(Calendar.YEAR);
 
 
 String redirect = ParamUtil.getString(request, "redirect");
 String  paramPortletName = renderResponse.getNamespace() + "f"; 
 
 portletDisplay.setShowBackIcon(true);
 portletDisplay.setURLBack(redirect);


%>

<portlet:actionURL name="addeditMarket" var="addeditMarketURL" />

<div class="container-fluid-1280">
<aui:form action="${addeditMarketURL}" name="<%=paramPortletName%>" method="POST">
	
	<liferay-ui:success key="market.success" message="market.success"/>
	<liferay-ui:error key="market.error.missingparameters" message="market.error.missingparameters"/>
	<liferay-ui:error key="market.error.exists" message="market.error.exists"/>
	<liferay-ui:error key="share.error.formatparameters" message="share.error.formatparameters"/>
	<liferay-ui:error key="market.error.endhour_gt_estarthour" message="market.error.endhour_gt_estarthour"/>
	

    <aui:fieldset>
        <aui:input type="text"   name="name" value="${market.name}">
                <aui:validator  name="required" />	
                <aui:validator name="maxLength">75</aui:validator>
        </aui:input>
         <aui:input type="text"  name="identifier" value="${market.identifier}">
               <aui:validator  name="required"  />	
                <aui:validator name="maxLength">75</aui:validator>	
       </aui:input>
         <aui:input type="text"  name="description" value="${market.description}">
               <aui:validator  name="required"  />	
                <aui:validator name="maxLength">150</aui:validator>	
       </aui:input>
       <aui:input label="market.active" name="active" type="toggle-card" checked="${market.active ? 'true':''}"/>       
              
    </aui:fieldset>
   
     <aui:fieldset>		
		   <aui:select label="market.currency" name="currency">
        	<aui:option  value="<%=ConfigKeys.CURRENCY_DOLLAR%>"  ><%=ConfigKeys.CURRENCY_DOLLAR%></aui:option>
			<aui:option  value="<%=ConfigKeys.CURRENCY_EURO%>" ><%=ConfigKeys.CURRENCY_EURO%></aui:option>										
		</aui:select>
    </aui:fieldset>    
    
    <aui:fieldset>
    	<liferay-ui:message key="starthour"/>
  		<liferay-ui:input-time name="starthour" minuteParam="00" amPmParam="PM" hourParam="00"></liferay-ui:input-time>
  			
  	    <liferay-ui:message key="endhour"/>
  	    <liferay-ui:input-time name="endhour" minuteParam="00" amPmParam="PM" hourParam="00"></liferay-ui:input-time>

  			
  	 </aui:fieldset>    
    
     <aui:input  name="marketId" type="hidden" value="${market.marketId}"/>       
	 <aui:button-row>
        <aui:button type="submit"></aui:button>
        <aui:button type="cancel" onClick="${redirect}"></aui:button>
    </aui:button-row>
</aui:form>
</div>