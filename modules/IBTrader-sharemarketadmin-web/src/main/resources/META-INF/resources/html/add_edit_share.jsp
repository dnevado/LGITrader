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
String portletId= "_" + portletDisplay.getId();



%>




<liferay-ui:success key="share.success" message="share.success"/>
<liferay-ui:error key="share.error.exists" message="share.error.exists"/>
<liferay-ui:error key="share.error.missingparameters" message="share.error.missingparameters"/>
<liferay-ui:error key="share.error.formatparameters" message="share.error.formatparameters"/>
<liferay-ui:error key="share.error.futuresparameters" message="share.error.futuresparameters"/>
<liferay-ui:error key="share.error.positionexistsactive" message="share.error.positionexistsactive"/>
<portlet:actionURL name="addShare" var="addShareURL">
 <portlet:param name="redirect" value="<%=themeDisplay.getURLCurrent()%>"/>
 </portlet:actionURL>

<portlet:actionURL name="editShare" var="editShareURL"> 
   <portlet:param name="redirect" value="<%=themeDisplay.getURLCurrent()%>"/>
 </portlet:actionURL>


<div class="container-fluid-1280">

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
		 <div class="col col-sm-2">	
			<aui:input label="share.active" name="active" type="toggle-card" checked="${share.active ? 'true':''}"/>
		 </div>  
		 <div class="col col-sm-2">	
			<aui:input label="share.validated" disabled="true"  name="validated_trader_provider" type="toggle-card" checked="${share.validated_trader_provider ? 'true':''}"/>
		 </div>
      </aui:fieldset>
      <aui:fieldset> 
		<aui:input type="number" label="share.number" name="numbertopurchase"  value="${share.numbertopurchase}">
			  <aui:validator  name="required"  />	
			  <aui:validator name="min">1</aui:validator>
			  <aui:validator name="digits"/>
		 </aui:input>
    </aui:fieldset>
    <aui:row>
    	<aui:col span="6">
	    	 <aui:fieldset>		         
	       	 <label class="control-label" for="<%=portletId%>_share.percentual_limit_buy"><liferay-ui:message key="share.percentual_limit_buy"/></label><input  id="<%=portletId%>_percentual_limit_buy" class="field form-control"  min="0"  max="100" type="number"  step="0.01"   formnovalidate="formnovalidate"   pattern="[0-9]+([,][0-9]+)?" placeholder="0,00" name="<%=portletId%>_percentual_limit_buy"  value="${share.percentual_limit_buy gt 0 ? share.percentual_limit_buy : ''}"/> 	    		
	    	</aui:fieldset> 
	      	<aui:fieldset>		
	      	<label class="control-label" for="<%=portletId%>_share.percentual_stop_lost"><liferay-ui:message key="share.percentual_stop_lost"/></label><input  id="<%=portletId%>_percentual_stop_lost" class="field form-control"  min="0"  max="100" type="number"  step="0.01"   formnovalidate="formnovalidate"   pattern="[0-9]+([,][0-9]+)?" placeholder="0,00" name="<%=portletId%>_percentual_stop_lost"  value="${share.percentual_stop_lost gt 0 ? share.percentual_stop_lost : ''}"/> 	    	      		
	    	</aui:fieldset> 
       </aui:col>
       <aui:col span="6">
	       <aui:fieldset>		
	    	  <label class="control-label" for="<%=portletId%>_share.percentual_stop_profit"><liferay-ui:message key="share.percentual_stop_profit"/></label><input  id="<%=portletId%>_percentual_stop_profit" class="field form-control"  min="0"  max="100" type="number"  step="0.01"   formnovalidate="formnovalidate"   pattern="[0-9]+([,][0-9]+)?" placeholder="0,00" name="<%=portletId%>_percentual_stop_profit"  value="${share.percentual_stop_profit gt 0 ? share.percentual_stop_profit : ''}"/> 	    	      	    		
	    	</aui:fieldset> 
	        <aui:fieldset>		
	    	  <label class="control-label" for="<%=portletId%>_share.percentual_trailling_stop_lost"><liferay-ui:message key="share.percentual_trailling_stop_lost"/></label><input  id="<%=portletId%>_percentual_trailling_stop_lost" class="field form-control"  min="0"  max="100" type="number"  step="0.01"   formnovalidate="formnovalidate"   pattern="[0-9]+([,][0-9]+)?" placeholder="0,00" name="<%=portletId%>_percentual_trailling_stop_lost"  value="${share.percentual_trailling_stop_lost gt 0 ? share.percentual_trailling_stop_lost : ''}"/> 	    	      	    		
	    	</aui:fieldset>
       </aui:col>
	</aui:row>	         
   <liferay-ui:panel-container accordion="true" extended="true" id="datafutures">
		<liferay-ui:panel title="share.datafutures" markupView="lexicon">
		<aui:row>
    	<aui:col span="6">
			    <aui:fieldset>	  				
  				<aui:select multiple="true"  label="data.expirationmonth" name="expirationmonth">
				    <% int monthCounter=0;				    				    
				    for (String s: ConfigKeys._FUTURES_MONTHS)  { %>	
				        <aui:option  value="<%=monthCounter%>" selected="<%=(_months.contains(String.valueOf(monthCounter)) ? true :  false)%>"><%=s%></aui:option> 
				    <% monthCounter+=1;} %>				     							
				</aui:select> 
				</aui:fieldset>
	     </aui:col> 
	     <aui:col span="2">
	      		<aui:fieldset>	  		
				<aui:select   label="data.expirationweek" name="expirationweek">		
				   <aui:option  value="1" selected="<%=("1".equals(expirationweek) ? true :  false)%>">1</aui:option>
				   <aui:option  value="2" selected="<%=("2".equals(expirationweek) ? true :  false)%>">2</aui:option>
				   <aui:option  value="3" selected="<%=("3".equals(expirationweek) ? true :  false)%>">3</aui:option>
				   <aui:option  value="4" selected="<%=("4".equals(expirationweek) ? true :  false)%>">4</aui:option>
				   <aui:option  value="5" selected="<%=("5".equals(expirationweek) ? true :  false)%>">5</aui:option>
				</aui:select>
				</aui:fieldset>
		</aui:col> 
	     <aui:col span="2">
	     		<aui:fieldset>	  	
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
  		    </aui:col>  	
	    	</aui:row>	 
  			<aui:row>
    		<aui:col span="6">
  			   <!--  html5 versus liferay debido a los numbers con dcecimales  -->    
     			 <label class="control-label" for="<%=portletId%>_tick_futures">share.tickfutures</label><input  id="<%=portletId%>_tick_futures" class="field form-control"  min="0"  type="number"  step="0.01"   formnovalidate="formnovalidate"   pattern="[0-9]+([,][0-9]+)?" placeholder="0,00" name="<%=portletId%>_tick_futures"  value="${share.tick_futures gt 0 ? share.tick_futures : ''}"/>			    	
	        </aui:col>  	
		     <aui:col span="6">
			     <label class="control-label" for="<%=portletId%>_share.multiplier">share.multiplier</label><input  id="<%=portletId%>_multiplier" class="field form-control"  min="0"  type="number"  step="0.01"   formnovalidate="formnovalidate"   pattern="[0-9]+([,][0-9]+)?" placeholder="0,00" name="<%=portletId%>_multiplier"  value="${share.multiplier gt 0 ? share.multiplier : ''}"/>
			 </aui:col>
	        </aui:row>	  
         </liferay-ui:panel>
	</liferay-ui:panel-container>
	<liferay-ui:panel-container accordion="true" extended="true" id="datamarket">
		<liferay-ui:panel title="share.datamarket" markupView="lexicon">
			<aui:row>
    			<aui:col span="3">
     		 	<aui:fieldset>		     		  
				   <aui:select label="share.exchange" name="exchange">
				    <% for (String s: ConfigKeys._MARKET_EXCHANGES)  {%>        				    	
				        <aui:option  value="<%=s%>" selected="<%=(_share!=null &&  s.equals(_share.getExchange()) ? true :  false)%>"><%=s%></aui:option> 
				    <% } %>				    							
				   </aui:select>
		     	</aui:fieldset>
		        </aui:col>	
			     <aui:col span="3">
			     <aui:fieldset>		
				   <aui:select label="share.primaryexchange" name="primaryexchange">
				   <% for (String p: ConfigKeys._PRIMARY_MARKET_EXCHANGES)  {%>				    	        
				        <aui:option  value="<%=p%>" selected="<%=(_share!=null && p.equals(_share.getPrimary_exchange()) ? true : false)%>"><%=p%></aui:option> 
				    <% } %>				   		        	  								
					</aui:select>
		    	  </aui:fieldset>
		     	</aui:col>	
		     <aui:col span="3">
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
		    </aui:col>
		    <aui:col span="3">
		     <aui:fieldset>		
			  	<aui:select label="share.market" name="marketId">
			  	 <c:forEach items="${_lMarket}" var="market">
			      	 <aui:option value="${market.marketId}" selected="${share.marketId == market.marketId || market.marketId ==marketId ? true : false}">${market.name}</aui:option>
			    </c:forEach>
			  	</aui:select>													
		     </aui:fieldset>
		     </aui:col>
		     </aui:row>	  
		     <aui:input type="hidden"  name="shareId"  value="${share.shareId}"/>
  	   </liferay-ui:panel>
	</liferay-ui:panel-container>
   
    <aui:button-row>
        <aui:button type="submit" cssClass="btn-lg"></aui:button>
        <aui:button type="cancel" cssClass="btn-lg" onClick="<%=redirect%>"></aui:button>
    </aui:button-row>
    </aui:fieldset-group>
</aui:form>


</div>