<%@ include file="/init.jsp" %>
<%@ page import="com.ibtrader.util.ConfigKeys" %>
<%@ page import="com.ibtrader.data.model.Share" %>
<%@ page import="com.liferay.portal.kernel.json.JSONObject" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<%

Share _share = (Share) request.getAttribute("share");


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


<script>

var _nameSpace = '<portlet:namespace/>';

AUI().ready(function() {
	jQuery.noConflict();
	(function( $ ) {
	  $(function() {
		 	
		    console.log("entering futureexpirationdays2");	
			updateAssetType();    	
			
			//		https://uxsolutions.github.io/bootstrap-datepicker/?markup=component&format=&weekStart=&startDate=&endDate=&startView=0&minViewMode=0&maxViewMode=4&todayBtn=false&clearBtn=false&language=en&orientation=auto&multidate=&multidateSeparator=&keyboardNavigation=on&forceParse=on#sandbox
			//      https://bootstrap-datepicker.readthedocs.io/en/latest/
			console.log($('#' + _nameSpace  +'expirationdate'));
			jQuery('#' + _nameSpace  +'expirationdate').datepicker({
					format: "dd/mm/yyyy",
				    todayBtn: "linked",
				    clearBtn: true,
				    multidate: true,
				    calendarWeeks: true,
				   // autoclose: true,
				    todayHighlight: true,
				//    toggleActive: true,
				    daysOfWeekHighlighted : [0,6]

			});
			jQuery('#' + _nameSpace  +'expirationdate').datepicker('setDates',[${expirationDates}]);
			
	  });
	})(jQuery);
});
</script>



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
			<aui:input label="share.validated" disabled="true"  name="validated_trader_provider" type="toggle-card" checked="${share.validated_trader_provider ? 'true':''}"/>
		 </div>  		 
		 <div class="col col-sm-2">	
			<aui:input label="share.firtDateHistorical" disabled="true"  name="firstDateHistorical"  type="text"  value="${_startHistorical}"/>		
			<aui:input label="share.lastDateHistorical"  disabled="true"  name="lastDateHistorical" type="text"  value="${_endHistorical}"/>
			<%@include file="/html/add_edit_share_last_realtime.jsp"%>
		 </div>				 		   	 		 		   	 	   	  	   	     					 		 
		 <div class="col col-sm-4">			
		 	 <c:set var="count" value="0" scope="page" />		 		
			 <c:forEach items="${currentTradingHours}" var="hour">
      			  <aui:input name="${count}"  type="text" disabled="true" value="${hour}"/>
      			  <c:set var="count" value="${count + 1}" scope="page"/>      			  
      		</c:forEach>
			
		 </div>
      </aui:fieldset>
       <aui:row>    
    		<aui:col span="3">
		      <aui:fieldset> 
				<aui:input type="number" label="share.number" name="numbertopurchase"  value="${share.numbertopurchase}">
					  <aui:validator  name="required"  />	
					  <aui:validator name="min">1</aui:validator>
					  <aui:validator name="digits"/>
				 </aui:input>
			 </aui:fieldset>
		   </aui:col>
				<aui:col span="3">
		        <aui:fieldset> 
		  			   <!--  html5 versus liferay debido a los numbers con dcecimales  -->    
		     			 <label class="control-label" for="<%=portletId%>_tick_futures"><liferay-ui:message key="share.tickfutures"/></label><input  id="<%=portletId%>_tick_futures" class="field form-control"  min="0"  type="number"  step="0.01"   formnovalidate="formnovalidate"   pattern="[0-9]+([,][0-9]+)?" placeholder="0,00" name="<%=portletId%>_tick_futures"  value="${share.tick_futures gt 0 ? share.tick_futures : '0.01'}"/>			    	
			     </aui:fieldset>
		   </aui:col>
		</aui:row>	 
    <aui:row>    
    	<aui:col span="6">
	    	 <aui:fieldset>		         
	       	 <label class="control-label" for="<%=portletId%>_share.percentual_limit_buy"><liferay-ui:message key="share.percentual_limit_buy"/></label><input  id="<%=portletId%>_percentual_limit_buy" class="field form-control"  min="0"  max="100" type="number"  step="0.01"   formnovalidate="formnovalidate"   pattern="[0-9]+([,][0-9]+)?" placeholder="0,00" name="<%=portletId%>_percentual_limit_buy"  value="${share.percentual_limit_buy gt 0 ? share.percentual_limit_buy : ''}"/> 	    		
	    	</aui:fieldset> 
	      	<aui:fieldset>		
	      	<label class="control-label" for="<%=portletId%>_share.percentual_stop_lost"><liferay-ui:message key="share.detail.percentual_stop_lost"/></label><input ${readonlyStopLost}  id="<%=portletId%>_percentual_stop_lost" class="field form-control"  min="0"  max="100" type="number"  step="0.01"   formnovalidate="formnovalidate"   pattern="[0-9]+([,][0-9]+)?" placeholder="0,00" name="<%=portletId%>_percentual_stop_lost"  value="${share.percentual_stop_lost gt 0 ? share.percentual_stop_lost : ''}"/> 	    	      		
	    	</aui:fieldset> 
       </aui:col>
       <aui:col span="6">
	       <aui:fieldset>		
	    	  <label class="control-label" for="<%=portletId%>_share.percentual_stop_profit"><liferay-ui:message key="share.detail.percentual_stop_profit"/></label><input ${readonlyStopProfit}  id="<%=portletId%>_percentual_stop_profit" class="field form-control"  min="0"  max="100" type="number"  step="0.01"   formnovalidate="formnovalidate"   pattern="[0-9]+([,][0-9]+)?" placeholder="0,00" name="<%=portletId%>_percentual_stop_profit"  value="${share.percentual_stop_profit gt 0 ? share.percentual_stop_profit : ''}"/> 	    	      	    		
	    	</aui:fieldset> 
	        <aui:fieldset>		
	    	  <label class="control-label" for="<%=portletId%>_share.percentual_trailling_stop_lost"><liferay-ui:message key="share.detail.percentual_trailling_stop_lost"/></label><input  ${readonlyTrailingStopLost} id="<%=portletId%>_percentual_trailling_stop_lost"  class="field form-control"  min="0"  max="100" type="number"  step="0.01"   formnovalidate="formnovalidate"   pattern="[0-9]+([,][0-9]+)?" placeholder="0,00" name="<%=portletId%>_percentual_trailling_stop_lost"  value="${share.percentual_trailling_stop_lost gt 0 ? share.percentual_trailling_stop_lost : ''}"/> 	    	      	    		
	    	</aui:fieldset>
       </aui:col>
	</aui:row>	         
   <liferay-ui:panel-container accordion="true" extended="true" id="datafutures">
		<liferay-ui:panel title="share.datafutures" markupView="lexicon">		
		<aui:row>    	
	   	   <aui:col span="6">		
	   	     <aui:fieldset>  	  
	   	       <aui:input  class="field form-control" label="data.expirationdate" name="expirationdate" id="expirationdate" type="text"/>	   	     			
			</aui:fieldset>	     		  				
  			</aui:col>   		 		    			    		  
		     <aui:col span="3">
			     <label class="control-label" for="<%=portletId%>_share.multiplier"><liferay-ui:message key="share.multiplier"/></label><input  id="<%=portletId%>_multiplier" class="field form-control"  min="0"  type="number"  step="0.01"   formnovalidate="formnovalidate"   pattern="[0-9]+([,][0-9]+)?" placeholder="0,00" name="<%=portletId%>_multiplier"  value="${share.multiplier gt 0 ? share.multiplier : ''}"/>
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