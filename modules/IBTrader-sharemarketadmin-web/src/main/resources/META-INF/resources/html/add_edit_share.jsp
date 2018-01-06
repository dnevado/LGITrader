<%@ include file="/init.jsp" %>
<%@ page import="com.ibtrader.util.ConfigKeys" %>
<%@ page import="com.ibtrader.data.model.Share" %>
<%@ page import="com.liferay.portal.kernel.json.JSONObject" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<script>    
    var _nameSpace = '<portlet:namespace/>';
    AUI().ready(function() {    
    	require ('jquery', function (jquery) {
    		updateAssetType();
    	});
    });
    
</script>
<%

Share _share = (Share) request.getAttribute("share");
JSONObject jsonFutureParams = (JSONObject) request.getAttribute("jsonFutureParams");
String expirationmonth = "";
String expirationweek = "";
String expirationdayweek = "";

if (jsonFutureParams!=null && jsonFutureParams.getString("expirationmonth")!=null && !jsonFutureParams.getString("expirationmonth").equals(""))
	expirationmonth = jsonFutureParams.getString("expirationmonth");
if (jsonFutureParams!=null && jsonFutureParams.getString("expirationweek")!=null  &&  !jsonFutureParams.getString("expirationweek").equals(""))
	expirationweek = jsonFutureParams.getString("expirationweek");
if (jsonFutureParams!=null && jsonFutureParams.getString("expirationdayweek")!=null && !jsonFutureParams.getString("expirationdayweek").equals(""))
	expirationdayweek = jsonFutureParams.getString("expirationdayweek");




Calendar cal = Calendar.getInstance();


if (_share!=null && _share.getExpiry_date()!=null)
{
	cal.setTime( _share.getExpiry_date());
	
}
int startTimeDay = cal.get(Calendar.DATE);
int startTimeMonth = cal.get(Calendar.MONTH);
int startTimeYear = cal.get(Calendar.YEAR);
 


String redirect = ParamUtil.getString(request, "redirect");
portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);

%>




<liferay-ui:success key="share.success" message="share.success"/>
<liferay-ui:error key="share.error.exists" message="share.error.exists"/>
<liferay-ui:error key="share.error.missingparameters" message="share.error.missingparameters"/>
<liferay-ui:error key="share.error.formatparameters" message="share.error.formatparameters"/>
<liferay-ui:error key="share.error.futuresparameters" message="share.error.futuresparameters"/>
<portlet:actionURL name="addShare" var="addShareURL" />
<portlet:actionURL name="editShare" var="editShareURL" />

<% 


String _URL = addShareURL;
if (_share!=null)
	_URL = editShareURL;

List<String> _months;
if (!expirationmonth.equals(""))
	_months= new ArrayList<String>(Arrays.asList(expirationmonth.split(",")));			    		
else
	_months= new ArrayList<String>();

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
  				<aui:select multiple="true"  label="data.expirationmonth" name="expirationmonth">
				    <% int monthCounter=0;				    				    
				    for (String s: ConfigKeys._FUTURES_MONTHS)  { %>	
				        <aui:option  value="<%=monthCounter%>" selected="<%=(_months.contains(String.valueOf(monthCounter)) ? true :  false)%>"><%=s%></aui:option> 
				    <% monthCounter+=1;} %>
				    								
				</aui:select> 
				<aui:select   label="data.expirationweek" name="expirationweek">		
				   <aui:option  value="1" selected="<%=("1".equals(expirationweek) ? true :  false)%>">1</aui:option>
				   <aui:option  value="2" selected="<%=("2".equals(expirationweek) ? true :  false)%>">2</aui:option>
				   <aui:option  value="3" selected="<%=("3".equals(expirationweek) ? true :  false)%>">3</aui:option>
				   <aui:option  value="4" selected="<%=("4".equals(expirationweek) ? true :  false)%>">4</aui:option>
				   <aui:option  value="5" selected="<%=("5".equals(expirationweek) ? true :  false)%>">5</aui:option>
				</aui:select>
			    <aui:select label="data.expirationdayweek" name="expirationdayweek">		
			       <% int dayCounter=1;
			       	for (String s: ConfigKeys._FUTURES_DAYOFWEEK)  { %>        	    				    	
				        <aui:option  value="<%=dayCounter%>" selected="<%=(expirationdayweek.equals(String.valueOf(dayCounter)) ? true :  false)%>"><%=s%></aui:option> 
				    <% dayCounter+=1;} %>		   				    				   		   
				</aui:select>
  				 </aui:fieldset>
  				
  				<aui:fieldset>		
  				  				
  				
  				<liferay-ui:message key="data.expirationdate"/>
  				<liferay-ui:input-date dayParam="startDateDay"
					     dayValue="<%= startTimeDay %>"
					     disabled="true"					     
					     monthParam="startDateMonth"
					     monthValue="<%= startTimeMonth %>"
					     name="expiredate"
					     yearParam="startDateYear"
					     yearValue="<%= startTimeYear %>"/>
  			
  				 </aui:fieldset>    
     		   
			   <aui:input label="share.tickfutures"  type="text"  name="tick_futures"  value="${not empty share.tick_futures ? share.tick_futures : '0.0'}">				
	                 <aui:validator name="min">0.0</aui:validator>
                 	 <aui:validator name="max">100</aui:validator>               		
	         
	            </aui:input>
	              <aui:input label="share.multiplier"  type="text"  name="multiplier"  value="${not empty share.multiplier ? share.multiplier : '0.0'}">
	           		<aui:validator name="min">0</aui:validator>	           		                  		            

				    	
	            	  
	            </aui:input>
    </aui:fieldset>   

     <aui:fieldset collapsible="true" label="share.datamarket" id="datamarket" >
     		  <aui:fieldset>		
     		  
				   <aui:select label="share.exchange" name="exchange">
				    <% for (String s: ConfigKeys._MARKET_EXCHANGES)  {%>        				    	
				        <aui:option  value="<%=s%>" selected="<%=(_share!=null &&  s.equals(_share.getExchange()) ? true :  false)%>"><%=s%></aui:option> 
				    <% } %>
				    								
				</aui:select>
		     </aui:fieldset>	
		     <aui:fieldset>		
				   <aui:select label="share.primaryexchange" name="primaryexchange">
				   <% for (String p: ConfigKeys._PRIMARY_MARKET_EXCHANGES)  {%>				    	        
				        <aui:option  value="<%=p%>" selected="<%=(_share!=null && p.equals(_share.getPrimary_exchange()) ? true : false)%>"><%=p%></aui:option> 
				    <% } %>				   		        	  								
				</aui:select>
		     </aui:fieldset>
		     <aui:fieldset>		
				   <aui:select onchange="updateAssetType();" label="share.type" name="security_type">
				   
				   <% 
				   	  boolean _selectedStock = _share!=null && _share.getSecurity_type().equals(ConfigKeys.SECURITY_TYPE_STOCK) ? true : false;    
				      boolean _selectedFutures = _share!=null && _share.getSecurity_type().equals(ConfigKeys.SECURITY_TYPE_FUTUROS) ? true: false; 				   	
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