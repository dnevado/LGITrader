<%@ include file="/init.jsp" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="com.ibtrader.data.model.Market" %>
<%@ page import="com.ibtrader.util.ConfigKeys" %>



<%

/* https://groups.io/g/twsapi

https://groups.io/g/twsapi

*/

Market  _market = (Market) request.getAttribute("market");


Calendar cal = Calendar.getInstance();
 int startTimeDay = cal.get(Calendar.DAY_OF_MONTH);
 int startTimeMonht = cal.get(Calendar.MONTH);
 int startTimeYear = cal.get(Calendar.YEAR);
 
 
 String redirect = ParamUtil.getString(request, "redirect");
 String  paramPortletName = renderResponse.getNamespace() + "f"; 
 
 portletDisplay.setShowBackIcon(true);
 portletDisplay.setURLBack(redirect);


 
%>

<portlet:actionURL name="addeditMarket" var="addeditMarketURL">
 <portlet:param name="redirect" value="<%=themeDisplay.getURLCurrent()%>"/>
 </portlet:actionURL>


<div class="container-fluid-1280">
<aui:form action="${addeditMarketURL}" name="<%=paramPortletName%>" method="POST">
	
	<liferay-ui:success key="market.success" message="market.success"/>
	<liferay-ui:error key="market.error.missingparameters" message="market.error.missingparameters"/>
	<liferay-ui:error key="market.error.exists" message="market.error.exists"/>
	<liferay-ui:error key="share.error.formatparameters" message="share.error.formatparameters"/>
	<liferay-ui:error key="market.error.endhour_gt_estarthour" message="market.error.endhour_gt_estarthour"/>
	
	<aui:fieldset-group markupView="lexicon">
	<aui:row>
    		
    <aui:fieldset>
    	<aui:col span="4">
        <aui:input type="text"   name="name" value="${market.name}">
                <aui:validator  name="required" />	
                <aui:validator name="maxLength">75</aui:validator>
        </aui:input>
        </aui:col>
        <aui:col span="4">
         <aui:input type="text"  name="identifier" value="${market.identifier}">
               <aui:validator  name="required"  />	
                <aui:validator name="maxLength">75</aui:validator>	
       </aui:input>
       </aui:col>
        <aui:col span="4">
         <aui:input label="market.active" name="active" type="toggle-card" checked="${market.active ? 'true':''}"/>    
        </aui:col>
     </aui:fieldset>
     </aui:row>
     <aui:row>      
       <aui:fieldset> 
       <aui:col span="8">
         <aui:input type="text"  name="description" value="${market.description}">
               <aui:validator  name="required"  />	
                <aui:validator name="maxLength">150</aui:validator>	
       </aui:input>        
       </aui:col>
     </aui:fieldset>
     </aui:row> 
     <aui:row>     
     <aui:col span="4">     
     <aui:fieldset>		
		   <aui:select label="market.currency" name="currency">
        	<aui:option  value="<%=ConfigKeys.CURRENCY_DOLLAR%>"  ><%=ConfigKeys.CURRENCY_DOLLAR%></aui:option>
			<aui:option  value="<%=ConfigKeys.CURRENCY_EURO%>" ><%=ConfigKeys.CURRENCY_EURO%></aui:option>										
		</aui:select>
    </aui:fieldset>    
      </aui:col>
    <% 
    
    	int  H1  = _market!=null  && _market.getStart_hour()!=null ? Long.valueOf(_market.getStart_hour().substring(0, 2)).intValue() : 0;
    	int  M1  = _market!=null  && _market.getStart_hour()!=null ? Long.valueOf(_market.getStart_hour().substring(2, 4)).intValue() : 0;
    	int  H2  = _market!=null  && _market.getStart_hour()!=null ? Long.valueOf(_market.getEnd_hour().substring(0, 2)).intValue(): 0;
    	int  M2  = _market!=null  && _market.getEnd_hour()!=null  ? Long.valueOf(_market.getEnd_hour().substring(2, 4)).intValue() :0;
    
    %>
     <aui:col span="4">  
       <aui:fieldset>
        <liferay-ui:message key="market.starthour"/>
  		<liferay-ui:input-time name="starthour" minuteInterval="15" hourValue="<%=H1%>" minuteValue="<%=M1%>"  minuteParam="00" amPmParam="PM" hourParam="00"></liferay-ui:input-time>  		
  	    <liferay-ui:message key="market.endhour"/>
  	    <liferay-ui:input-time name="endhour" minuteInterval="15"  hourValue="<%=H2%>" minuteValue="<%=M2%>"  minuteParam="00" amPmParam="PM" hourParam="00"></liferay-ui:input-time>	
    	 </aui:fieldset>
     </aui:col>    
    </aui:row>  
    
     <aui:input  name="marketId" type="hidden" value="${market.marketId}"/>       
	 <aui:button-row>
        <aui:button type="submit" cssClass="btn-lg"></aui:button>
        <aui:button type="cancel" cssClass="btn-lg" onClick="<%=redirect%>"></aui:button>
    </aui:button-row>
    </aui:fieldset-group>
</aui:form>
</div>