<%@ include file="/init.jsp" %>
<%@page import="com.ibtrader.util.Utilities"%>
<%@page import="com.liferay.expando.kernel.model.ExpandoColumnConstants"%>
<%@ page import="com.ibtrader.util.ConfigKeys" %>
<%@ page import="com.ibtrader.data.model.Share" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ibtrader.data.model.impl.StrategyImpl"%>
<%@ page import="com.liferay.expando.kernel.model.ExpandoColumn"%>
<%@ page import="java.util.Calendar" %>
<%@ page import="com.liferay.portal.kernel.util.*" %>
<%@ page import="com.liferay.portal.kernel.theme.*" %>

<%@ page import="com.liferay.portal.kernel.json.JSONFactoryUtil" %>
<%@ page import="com.liferay.portal.kernel.json.JSONObject" %>



<liferay-ui:success key="share.success" message="share.success"/>
<liferay-ui:error key="share.error.exists" message="share.error.exists"/>
<liferay-ui:error key="share.error.missingparameters" message="share.error.missingparameters"/>
<liferay-ui:error key="share.error.formatparameters" message="share.error.formatparameters"/>
<liferay-ui:error key="strategyshare.strategyminmax.errorparams2" message="strategyshare.strategyminmax.errorparams2"/>
<liferay-ui:error key="strategyshare.strategyminmax.errorparams" message="strategyshare.strategyminmax.errorparams"/>




<%

/*  renderRequest.setAttribute("share", share);
renderRequest.setAttribute("strategy", Strategy);
renderRequest.setAttribute("_lMarket", _lMarket);
renderRequest.setAttribute("implemented_strategy", _strategyImpl);
renderRequest.setAttribute("tab_selected", tab_selected);
renderRequest.setAttribute("jsonStrategyShareParams
		
*/




String redirect = ParamUtil.getString(request, "redirect");
String _URL ="";

Share share = (Share) request.getAttribute("share"); 
StrategyImpl _StrategyType = (StrategyImpl) request.getAttribute("implemented_strategy");
JSONObject jsonStrategyShareParams = (JSONObject) request.getAttribute("jsonStrategyShareParams");



%>
<c:set  var="strategyshare_numbertopurchase" value="<%=jsonStrategyShareParams.getLong("numbertopurchase") %>"/> 
<c:set  var="strategyshare_percentual_limit_buy" value="<%=jsonStrategyShareParams.getDouble("percentual_limit_buy") %>"/>
<c:set  var="strategyshare_percentual_stop_lost" value="<%=jsonStrategyShareParams.getDouble("percentual_stop_lost") %>"/>
<c:set  var="strategyshare_percentual_stop_profit" value="<%=jsonStrategyShareParams.getDouble("percentual_stop_profit") %>"/>


<portlet:actionURL name="editStrategyShareParams" var="editStrategyShareParamsURL" />


<%@include file="/html/includes/tabs.jsp"%>


<aui:form action="<%=editStrategyShareParamsURL%>"  name="fm" method="POST">
	<aui:fieldset-group markupView="lexicon">
	
	
	 <aui:fieldset collapsed="false" collapsible="true"  label="strategy" id="datastrategyname">
		<aui:input  readonly="readonly" type="text" label="share.name" name="name"  value="${share.name}">
		</aui:input>
		<aui:input  readonly="readonly" type="text" label="share.symbol" name="symbol"  value="${share.symbol}">
		</aui:input>
		<aui:input  readonly="readonly" type="text" label="strategy.name" name="strategy.name"  value="${strategy.name}">
		</aui:input>
	</aui:fieldset>
	
    <aui:fieldset collapsed="false" collapsible="true"  label="share.paramdata" id="datashare">
  		
  		<aui:fieldset>	
				<aui:input type="number" label="share.number" name="numbertopurchase"  value="${empty strategyshare_numbertopurchase ? share.numbertopurchase : strategyshare_numbertopurchase}">
				  <aui:validator  name="required"  />		            
			 </aui:input>
    	</aui:fieldset>		
	  	 <aui:fieldset>		
			<aui:input  type="text" name="percentual_limit_buy" label="share.percentual_limit_buy"   value="${empty strategyshare_percentual_limit_buy ? share.percentual_limit_buy : strategyshare_percentual_limit_buy}">				  	
	                <aui:validator name="min">0</aui:validator>
               		<aui:validator name="max">100</aui:validator>
               		<aui:validator name="number" />
	              
			 </aui:input>
	    </aui:fieldset> 
	    <aui:fieldset>		
			<aui:input label="share.percentual_stop_lost" type="text"  name="percentual_stop_lost"   value="${empty strategyshare_percentual_stop_lost ? share.percentual_stop_lost : strategyshare_percentual_stop_lost}">				  	
	              <aui:validator name="min">0</aui:validator>
               <aui:validator name="max">100</aui:validator>
               <aui:validator name="number" />
	              
			 </aui:input>
	    </aui:fieldset> 
	    <aui:fieldset>		
			<aui:input label="share.percentual_stop_profit"  type="text"  name="percentual_stop_profit"   value="${empty strategyshare_percentual_stop_profit ? share.percentual_stop_profit : strategyshare_percentual_stop_profit}">				  	
	            <aui:validator name="min">0</aui:validator>
               <aui:validator name="max">100</aui:validator>
               <aui:validator name="number" />
	              
			 </aui:input>
	    </aui:fieldset> 	       
    </aui:fieldset> 
    <!--  DATOS DE EXPANDOS    -->
    <aui:fieldset collapsed="false" collapsible="true"  label="strategy.paramdata" id="datastrategy">    
    
    <%
    
    List<ExpandoColumn> StrategyParams =  _StrategyType.getIBStrategyParams();
    for (ExpandoColumn StrategyParameter : StrategyParams)
    {
    	String _type = "text";
    	    
    	
    	String _pname = Utilities._IBTRADER_STRATEGY_CUSTOM_FIELDS_.concat(StrategyParameter.getName());
    	String _pvalue = (jsonStrategyShareParams.get(StrategyParameter.getName())!=null ? jsonStrategyShareParams.get(StrategyParameter.getName()).toString() : ""); 
    	if (StrategyParameter.getType()==ExpandoColumnConstants.LONG || StrategyParameter.getType()==ExpandoColumnConstants.INTEGER || 
    			StrategyParameter.getType()==ExpandoColumnConstants.FLOAT || StrategyParameter.getType()==ExpandoColumnConstants.DOUBLE)
    	{
    				_type = "number";    				
    	}
    	
    %>
    	<aui:fieldset>	
				<aui:input value="<%=_pvalue%>" 
						type="<%=_type%>" label="<%=StrategyParameter.getDisplayName(themeDisplay.getLocale()) %>" name="<%=_pname%>">
				  <aui:validator  name="required"  />	
	              <aui:validator name="<%=_type%>" />
	              <aui:validator name="min">0</aui:validator>
			 </aui:input>
    	</aui:fieldset>		
    	
    <% } %>      
	
	 </aui:fieldset>
	
    <aui:input type="hidden"  name="shareId"  value="${share.shareId}"/>
    <aui:input type="hidden"  name="strategyId"  value="${strategy.strategyID}"/>
  	
   
    <aui:button-row>
        <aui:button type="submit"></aui:button>
        <aui:button type="cancel" onClick="<%=redirect%>"></aui:button>
    </aui:button-row>
    </aui:fieldset-group>
</aui:form>