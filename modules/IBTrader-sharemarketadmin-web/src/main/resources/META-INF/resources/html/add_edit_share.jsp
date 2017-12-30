<%@ include file="/init.jsp" %>
<%@ page import="com.ibtrader.util.ConfigKeys" %>
<%@ page import="com.ibtrader.data.model.Share" %>
<%@ page import="java.util.Calendar" %>

<script>    
    var _nameSpace = '<portlet:namespace/>';
    AUI().ready(function() {    
    	require ('jquery', function (jquery) {
    		updateAssetType();
    	});
    });
    
</script>
<%

Calendar cal = Calendar.getInstance();
 int startTimeDay = cal.get(Calendar.DAY_OF_MONTH);
 int startTimeMonht = cal.get(Calendar.MONTH);
 int startTimeYear = cal.get(Calendar.YEAR);


String redirect = ParamUtil.getString(request, "redirect");
portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);

%>



<liferay-ui:success key="share.success" message="share.success"/>
<liferay-ui:error key="share.error.exists" message="share.error.exists"/>
<liferay-ui:error key="share.error.missingparameters" message="share.error.missingparameters"/>
<liferay-ui:error key="share.error.formatparameters" message="share.error.formatparameters"/>
<portlet:actionURL name="addShare" var="addShareURL" />
<portlet:actionURL name="editShare" var="editShareURL" />

<% 

Share _share = (Share) request.getAttribute("share");
String _URL = addShareURL;
if (_share!=null)
	_URL = editShareURL;

%>

<%@include file="/html/includes/tabs.jsp"%>


<aui:form action="<%=_URL%>"   name="fmshare" method="POST">
	<aui:fieldset-group markupView="lexicon">
    <aui:fieldset>
        <liferay-ui:icon-help message="share.name.help"/><aui:input type="text" label="share.name"  name="name" value="${share.name}">
                <aui:validator  name="required" />	
                <aui:validator name="maxLength">75</aui:validator>
        </aui:input>
        
        <liferay-ui:icon-help message="share.symbol.help"/><aui:input type="text"  name="symbol" label="share.symbol" value="${share.symbol}">
                <aui:validator  name="required"  />	
                <aui:validator name="maxLength">75</aui:validator>	
       </aui:input>
    </aui:fieldset>
	<aui:fieldset>
		<aui:input label="share.active" name="active" type="toggle-card" checked="${share.active ? 'true':''}"/>       
		<aui:input type="number" label="share.number" name="numbertopurchase"  value="${share.numbertopurchase}">
			  <aui:validator  name="required"  />	
			  <aui:validator name="min">1</aui:validator>
             
		 </aui:input>
    </aui:fieldset>
    <aui:fieldset>		
		<aui:input type="text"  name="percentual_limit_buy" label="share.percentual_limit_buy"  value="${share.percentual_limit_buy}">			  	
               <aui:validator name="min">0</aui:validator>
               <aui:validator name="max">100</aui:validator>
               <aui:validator name="number" />
		 </aui:input>
    </aui:fieldset> 
      <aui:fieldset>		
		<aui:input label="share.percentual_stop_lost" type="text"  name="percentual_stop_lost"  value="${share.percentual_stop_lost}">			  
            <aui:validator name="min">0</aui:validator>
                <aui:validator name="max">100</aui:validator>
               <aui:validator name="number" />
              
		 </aui:input>
    </aui:fieldset> 
    <aui:fieldset>		
		<aui:input label="share.percentual_stop_profit"  type="text"  name="percentual_stop_profit"  value="${share.percentual_stop_profit}">			  	
                   <aui:validator name="min">0</aui:validator>
                 <aui:validator name="max">100</aui:validator>
               <aui:validator name="number" />
		 </aui:input>
    </aui:fieldset> 
     
    
  <aui:fieldset collapsed="true" collapsible="true"  label="share.datafutures" id="datafutures">
  				
  				<aui:fieldset>		
  				
  				<liferay-ui:message key="data.expirationdate"/>
  				<liferay-ui:input-date dayParam="startDateDay"
					     dayValue="<%= cal.get(Calendar.DATE) %>"
					     disabled="<%= false %>"
					     firstDayOfWeek="<%= cal.getFirstDayOfWeek() - 1 %>"
					     monthParam="startDateMonth"
					     monthValue="<%= cal.get(Calendar.MONTH) %>"
					     name="expiredate"
					     yearParam="startDateYear"
					     yearValue="<%= cal.get(Calendar.YEAR) %>"/>
  			
  				 </aui:fieldset>    
     		   
			   <aui:input label="share.tickfutures"  type="text"  name="tickfutures"  value="${share.tick_futures}">				
	               <aui:validator name="number" />
	               <aui:validator name="min">0</aui:validator>
	         
	            </aui:input>
	              <aui:input label="share.multiplier"  type="text"  name="multiplier"  value="${share.multiplier}">
	           		<aui:validator name="min">0</aui:validator>	           		                  		            
               		<aui:validator name="number" />
				    	
	            	  
	            </aui:input>
    </aui:fieldset>   

     <aui:fieldset collapsible="true" label="share.datamarket" id="datamarket" >
     		  <aui:fieldset>		
     		  
				   <aui:select label="share.exchange" name="exchange">
				    <% for (String s: ConfigKeys._MARKET_EXCHANGES)  {%>        				    	
				        <aui:option  value="<%=s%>" selected="<%=(s.equals(_share.getExchange()) ? true :  false)%>"><%=s%></aui:option> 
				    <% } %>
				    								
				</aui:select>
		     </aui:fieldset>	
		     <aui:fieldset>		
				   <aui:select label="share.primaryexchange" name="primaryexchange">
				   <% for (String p: ConfigKeys._PRIMARY_MARKET_EXCHANGES)  {%>				    	        
				        <aui:option  value="<%=p%>" selected="<%=(p.equals(_share.getPrimary_exchange()) ? true : false)%>"><%=p%></aui:option> 
				    <% } %>				   		        	  								
				</aui:select>
		     </aui:fieldset>
		     <aui:fieldset>		
				   <aui:select onchange="updateAssetType();" label="share.type" name="security_type">
				   
				   <% 
				   	  boolean _selectedStock = _share.getSecurity_type().equals(ConfigKeys.SECURITY_TYPE_STOCK) ? true : false;    
				      boolean _selectedFutures = _share.getSecurity_type().equals(ConfigKeys.SECURITY_TYPE_FUTUROS) ? true: false; 				   	
				   	%>				   
		        	<aui:option  value="<%=ConfigKeys.SECURITY_TYPE_STOCK%>" selected="<%=_selectedStock %>" ><%=ConfigKeys.SECURITY_TYPE_STOCK%></aui:option>
					<aui:option cssClass="futures" value="<%=ConfigKeys.SECURITY_TYPE_FUTUROS%>" selected="<%=_selectedFutures %>"><%=ConfigKeys.SECURITY_TYPE_FUTUROS%></aui:option>										
				</aui:select>
		    </aui:fieldset>
		    
		     <aui:fieldset>		
			  	<aui:select label="share.market" name="marketId">
			  	 <c:forEach items="${_lMarket}" var="market">
			      	 <aui:option value="${market.marketId}" selected="${share.marketId == market.marketId || market.marketId ==marketId ? true : false}">${market.name}</aui:option>
			    </c:forEach>
			  	</aui:select>													
		     </aui:fieldset>
		     <aui:input type="hidden"  name="shareId"  value="${share.shareId}"/>
  	</aui:fieldset>
   
    <aui:button-row>
        <aui:button type="submit"></aui:button>
        <aui:button type="cancel" onClick="<%=redirect%>"></aui:button>
    </aui:button-row>
    </aui:fieldset-group>
</aui:form>