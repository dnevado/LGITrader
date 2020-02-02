
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
<%@ page import="java.util.regex.*" %>

<%@ page import="com.liferay.portal.kernel.json.JSONFactoryUtil" %>
<%@ page import="com.liferay.portal.kernel.json.JSONObject" %>
<%@ page import="java.util.*" %>


<div class="container-fluid-1280">

<liferay-ui:success key="share.success" message="share.success"/>
<liferay-ui:error key="share.error.exists" message="share.error.exists"/>
<liferay-ui:error key="share.error.missingparameters" message="share.error.missingparameters"/>
<liferay-ui:error key="share.error.formatparameters" message="share.error.formatparameters"/>
<liferay-ui:error key="strategyshare.strategyminmax.errorparams2" message="strategyshare.strategyminmax.errorparams2"/>
<liferay-ui:error key="strategyshare.strategyminmax.errorparams" message="strategyshare.strategyminmax.errorparams"/>

<%

String redirect = ParamUtil.getString(request, "redirect");
String _URL ="";

Share share = (Share) request.getAttribute("share"); 
StrategyImpl _StrategyType = (StrategyImpl) request.getAttribute("implemented_strategy");
JSONObject jsonStrategyShareParams = (JSONObject) request.getAttribute("jsonStrategyShareParams");

String portletId= "_" + portletDisplay.getId();




%>
<c:set  var="strategyshare_numbertopurchase" value="<%=jsonStrategyShareParams.getLong(ConfigKeys._FIELD_NUMBER_TO_PURCHASE) %>"/> 
<c:set  var="strategyshare_percentual_limit_buy" value="<%=jsonStrategyShareParams.getDouble("percentual_limit_buy") %>"/>
<c:set  var="strategyshare_percentual_stop_lost" value="<%=jsonStrategyShareParams.getDouble(ConfigKeys._FIELD_STOP_LOST) %>"/>
<c:set  var="strategyshare_percentual_stop_profit" value="<%=jsonStrategyShareParams.getDouble(ConfigKeys._FIELD_STOP_PROFIT) %>"/>
<c:set  var="strategyshare_percentual_trailling_stop_lost" value="<%=jsonStrategyShareParams.getDouble(ConfigKeys._FIELD_TRAILLING_STOP_LOST) %>"/>


<portlet:actionURL name="editStrategyShareParams" var="editStrategyShareParamsURL">
 <portlet:param name="redirect" value="<%=themeDisplay.getURLCurrent()%>"/>
 </portlet:actionURL>



<%@include file="/html/includes/tabs.jsp"%>


<aui:form action="<%=editStrategyShareParamsURL%>"  name="fm" method="POST">
	<aui:fieldset-group markupView="lexicon">	  
	 <aui:fieldset collapsed="false" collapsible="true"  label="strategy" id="datastrategyname">
	  <aui:row>      
	   <aui:col span="4">   	
		<aui:input  readonly="readonly" type="text" label="share.name" name="name"  value="${share.name}">
		</aui:input> 
	   </aui:col>
	   <aui:col span="4">   	
		<aui:input  readonly="readonly" type="text" label="share.symbol" name="symbol"  value="${share.symbol}">
		</aui:input>
		</aui:col>
	   <aui:col span="4">   	
		<aui:input  readonly="readonly" type="text" label="strategy.name" name="strategy.name"  value="${strategy.name}">
		</aui:input>
	   </aui:col>
	  </aui:row>     
	   <aui:row> 
	    <aui:col span="12">   
			<aui:input type="textarea" label="strategy.description"rows="5" cols="30" id="description" name="description" value="${not empty strategyshare.description ? strategyshare.description : strategy.description}" />
	   </aui:col>
	   </aui:row>     
	  </aui:fieldset>
	 
	
	<c:if test="${strategy.can_override_params}">

    <aui:fieldset collapsed="false" collapsible="true"  label="share.paramdata" id="datashare">
  		  <aui:row>      
	   		<aui:col span="12">   
 			 <aui:input type="number" label="share.number" name="numbertopurchase"  value="${strategyshare_numbertopurchase>=0  ?  strategyshare_numbertopurchase : share.numbertopurchase}">
		 	<aui:validator  name="required"  />	
		  	<aui:validator name="min">1</aui:validator>
		  	<aui:validator name="digits"/>
		  	</aui:input>
		  	</aui:col>
	    </aui:row>     
		 <aui:row>  	           
	   		<aui:col span="6">
		  		 <aui:fieldset>		         
			        <label class="control-label" for="<%=portletId%>_share.percentual_limit_buy"><liferay-ui:message key="share.percentual_limit_buy"/></label><input  id="<%=portletId%>_percentual_limit_buy" class="field form-control"  min="0"  max="100" type="number"  step="0.01"   formnovalidate="formnovalidate"   pattern="[0-9]+([,][0-9]+)?" placeholder="0,00" name="<%=portletId%>_percentual_limit_buy"  value="${strategyshare_percentual_limit_buy<=0 ? share.percentual_limit_buy : strategyshare_percentual_limit_buy}"/> 	    		
			    </aui:fieldset> 
	         	  <aui:fieldset>		
			      	<label class="control-label" for="<%=portletId%>_share.percentual_stop_lost"><liferay-ui:message key="share.detail.percentual_stop_lost"/></label><input ${readonlyStopLost} id="<%=portletId%>_percentual_stop_lost" class="field form-control"  min="0"  max="100" type="number"  step="0.01"   formnovalidate="formnovalidate"   pattern="[0-9]+([,][0-9]+)?" placeholder="0,00" name="<%=portletId%>_percentual_stop_lost"  value="${strategyshare_percentual_stop_lost<=0 ? share.percentual_stop_lost : strategyshare_percentual_stop_lost}"/> 	    	      		
			    </aui:fieldset>
				</aui:col> 	   
	   		<aui:col span="6">
			    <aui:fieldset>		
			    	<label class="control-label" for="<%=portletId%>_share.percentual_stop_profit"><liferay-ui:message key="share.detail.percentual_stop_profit"/></label><input ${readonlyStopProfit}  id="<%=portletId%>_percentual_stop_profit" class="field form-control"  min="0"  max="100" type="number"  step="0.01"   formnovalidate="formnovalidate"   pattern="[0-9]+([,][0-9]+)?" placeholder="0,00" name="<%=portletId%>_percentual_stop_profit"  value="${strategyshare_percentual_stop_profit<=0 ? share.percentual_stop_profit : strategyshare_percentual_stop_profit}"/> 	    	      	    		
		        </aui:fieldset>		   
		          <aui:fieldset>		
			    	<label class="control-label" for="<%=portletId%>_share.percentual_trailling_stop_lost"><liferay-ui:message key="share.detail.percentual_trailling_stop_lost"/></label><input ${readonlyTrailingStopLost} id="<%=portletId%>_percentual_trailling_stop_lost" class="field form-control"  min="0"  max="100" type="number"  step="0.01"   formnovalidate="formnovalidate"   pattern="[0-9]+([,][0-9]+)?" placeholder="0,00" name="<%=portletId%>_percentual_trailling_stop_lost"  value="${strategyshare_percentual_trailling_stop_lost<=0 ? share.percentual_trailling_stop_lost : strategyshare_percentual_trailling_stop_lost}"> 	    	      	    		
		        </aui:fieldset>
		    </aui:col>
		  </aui:row>  
    </aui:fieldset> 
    </c:if>
    <!--  DATOS DE EXPANDOS    -->
        
    
    <%
    
    List<ExpandoColumn> StrategyParams =  _StrategyType.getIBStrategyParams();
    
    if (StrategyParams!=null)
    { %>
    <aui:fieldset collapsed="false" collapsible="true"  label="strategy.paramdata" id="datastrategy">
    <%
    long counter = 0;
    for (ExpandoColumn StrategyParameter : StrategyParams)
    {
    
    	if ( (counter & 1) == 0 ) { out.println("<div class=\"row\">"); }%> 
    	
    	<aui:col span="6">	
    	<%
    	String _type = "text";
    	String _step = "1";
    	String _default_value = "";// {12}
    	String _pattern = "";

    	List<String> _lValues = null;

    	
    	String _pname = Utilities._IBTRADER_STRATEGY_CUSTOM_FIELDS_.concat(StrategyParameter.getName());
    	String _pvalue = (jsonStrategyShareParams.get(StrategyParameter.getName().trim())!=null ? jsonStrategyShareParams.get(StrategyParameter.getName().trim()).toString() : "");

    	_pvalue = _pvalue.trim();
    	
    	/* buscamos el default value */
    	if (_pname.contains("{") &&  _pname.contains("}") && _pvalue.equals(""))
    	{
    			// var1[value1]
    		 	Pattern pattern = Pattern.compile("(\\{)([0-9]+)(\\})");
    	        Matcher matcher = pattern.matcher(_pname);

    	        if (matcher.find())
    	        	_default_value = matcher.group();
    	        
				
    	        
    	        _pvalue =  _default_value.replace("{", "").replace("}", "");
    	        //_pname  =  _default_value.replace("{", "").replace("}", "");
    	}
    	if (StrategyParameter.getType()==ExpandoColumnConstants.LONG || StrategyParameter.getType()==ExpandoColumnConstants.INTEGER || 
    			StrategyParameter.getType()==ExpandoColumnConstants.FLOAT || StrategyParameter.getType()==ExpandoColumnConstants.DOUBLE)
    	{
    				_type = "number";    		
    				if (StrategyParameter.getType()==ExpandoColumnConstants.FLOAT || StrategyParameter.getType()==ExpandoColumnConstants.DOUBLE)
    				{    					
    					//_pvalue = (jsonStrategyShareParams.getDouble(StrategyParameter.getName())!=null ? jsonStrategyShareParams.getDouble(StrategyParameter.getName()).toString() : "");
    					_type = "double";
    					_pattern = "[0-9]+([,][0-9]+)?";
    					_step = "0.01";
    				}
    	}
    	if (StrategyParameter.getType()==ExpandoColumnConstants.STRING_ARRAY)  // para tipos de operaciones 
		{
			_type = "select";
			if (_pname.contains("[") && _pname.contains("]") )
			{
			 String _ListValue =_pname.substring(_pname.indexOf("[") + 1, _pname.indexOf("]"));
			_lValues =  new  ArrayList<String>(Arrays.asList(_ListValue.split(",")));
			}
		}
     	if (_type.equals("double")) // por el tema de los aui number con decimales 
     	{%>
     		 <aui:fieldset>		
 	    		<label class="control-label" for="<%=portletId%>_<%=_pname%>"><%=StrategyParameter.getDisplayName(themeDisplay.getLocale()) %></label>
	 	    	<input  required id="<%=portletId%>_<%=_pname%>" class="field form-control"  min="0"  type="number"  step="<%=_step%>"   formnovalidate="formnovalidate"  
 	    	 	pattern="<%=_pattern%>" placeholder="0.00" name="<%=portletId%>_<%=_pname%>"  value="<%=_pvalue%>"/> 	    	      	    		
         	</aui:fieldset>
     	<%} if (_type.equals("number"))
     	{%>
    		<aui:fieldset>	
				<aui:input value="<%=_pvalue%>" 
						type="<%=_type%>" label="<%=StrategyParameter.getDisplayName(themeDisplay.getLocale()) %>" name="<%=_pname%>">
				  <aui:validator  name="required"  />	
	              <aui:validator name="<%=_type%>" />	              
	              <aui:validator name="digits"/>
	              
			 </aui:input>
    	</aui:fieldset>		
		 
    	<% } if (_type.equals("select") && _lValues!=null) { /* casos de operaciones, para poder elegir venta, compra o todas*/ %>   
    			
    			<aui:fieldset>	
				<aui:select required="true"  inlineLabel="true"  label="<%=StrategyParameter.getDisplayName(themeDisplay.getLocale()) %>" name="<%=_pname%>">
				<% for (String selectvalue : _lValues) {
					boolean selected = _pvalue.equalsIgnoreCase(selectvalue.trim()) ? true : false;%>
					<aui:option  value="<%=selectvalue.trim()%>" selected="<%=selected %>"><%=selectvalue %></aui:option>
				<% } %>		
						  	              
			   </aui:select>
    		  </aui:fieldset>	
    				
    			<% }%>    	    	
    		</aui:col>    
    <% 
    if ( (counter & 1) == 1 ) { out.println("</div>"); }    	   	
    counter++;} %>
    </aui:fieldset>
    <% } %>      
	
	 
	
    <aui:input type="hidden"  name="shareId"  value="${share.shareId}"/>
    <aui:input type="hidden"  name="strategyId"  value="${strategy.strategyID}"/>
  	
   
    <aui:button-row>
        <aui:button type="submit" cssClass="btn-lg"></aui:button>
        <aui:button type="cancel" cssClass="btn-lg" onClick="<%=redirect%>"></aui:button>
    </aui:button-row>
    </aui:fieldset-group>
</aui:form>
</div>