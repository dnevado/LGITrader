
<%@page import="com.ibtrader.util.*"%>

<%@ include file="/init.jsp" %>
	

<% 
String redirect = ParamUtil.getString(request, "redirect");
String  paramPortletName = renderResponse.getNamespace() + "f"; 

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);

String portletId= "_" + portletDisplay.getId();

Position _position=(Position) request.getAttribute("position");
boolean EnabledStrategyClosePosition = false; 
if (request.getAttribute("EnabledStrategyClosePosition")!=null)
	EnabledStrategyClosePosition = (Boolean) request.getAttribute("EnabledStrategyClosePosition");

boolean EnabledStrategyCancelPosition = false; 
if (request.getAttribute("EnabledStrategyCancelPosition")!=null)
	EnabledStrategyCancelPosition = (Boolean) request.getAttribute("EnabledStrategyCancelPosition");


%>

 <portlet:actionURL name="editPosition" var="editPositionURL" >
 	<portlet:param name="redirect" value="<%=redirect%>"/>
 	<portlet:param name="positionId" value="${position.positionId}"/>
 </portlet:actionURL>
 <portlet:actionURL name="closePosition" var="closePositionURL" >
 	<portlet:param name="redirect" value="<%=redirect%>"/>
 	<portlet:param name="positionId" value="${position.positionId}"/>
 </portlet:actionURL>
 <portlet:actionURL name="cancelPosition" var="cancelPositionURL" >
 	<portlet:param name="redirect" value="<%=redirect%>"/>
 	<portlet:param name="positionId" value="${position.positionId}"/>
 </portlet:actionURL>
 
 
<div class="container-fluid-1280">
<aui:form action="<%=editPositionURL%>"   name="fm" method="POST" onsubmit="<portlet:namespace />extractCodeFromEditor()">
	<aui:fieldset-group markupView="lexicon">
    <aui:fieldset>
	<liferay-ui:success key="share.success" message="share.success"/>
	<liferay-ui:error key="share.error.exists" message="share.error.exists"/>
	
	
	<liferay-ui:panel-container accordion="true" extended="true" id="dataprices">
		<liferay-ui:panel title="position.prices" markupView="lexicon">
		 <aui:fieldset>
		<aui:row>
    		<aui:col span="6">	
    		<aui:input  readonly="readonly" type="text" label="Stock.name" name="Stock.name"  value="${share.name}">
			</aui:input>		
			<aui:input  readonly="readonly" type="text" label="Price.In" name="Price.In"  value="${position.price_real_in}">
			</aui:input>			
			<aui:input  readonly="readonly" type="text" label="Price.Out" name="Price.Out"  value="${position.price_real_out}">
			</aui:input>	
			<aui:input  readonly="readonly" type="text" label="Strategy.In" name="Strategy.In"  value="${position.strategy_in}">
			</aui:input>
			</aui:col>	
			<aui:col span="6">
			<aui:input  readonly="readonly" type="text" label="Realtime.last" name="Realtime.last"  value="${realtime.value}">
			</aui:input>
			<aui:input  readonly="readonly" type="text" label="Type" name="Type"  value="${position.type}">
			</aui:input>
			<aui:input  readonly="readonly" type="text" label="Strategy.Out" name="Strategy.Out"  value="${position.strategy_out}">
			</aui:input>
			</aui:col>					
		</aui:row>
		</aui:fieldset>	
		</liferay-ui:panel> 	
	</liferay-ui:panel-container>
	
	<% if (!EnabledStrategyCancelPosition) { %>
	
        <liferay-ui:alert
        closeable="true"
        icon="exclamation-full"
        message="La estrategia CancelPosition debe estar habilitada para poder cerrar una posición abierta"
        type="success"
/>
        
	
	<% } %>
	
	
	<% if (!EnabledStrategyClosePosition) { %>
	
        <liferay-ui:alert
        closeable="true"
        icon="exclamation-full"
        message="La estrategia ClosePosition debe estar habilitada para poder cerrar una posición abierta"
        type="success"
/>
        
	
	<% } %>
	
	
	<aui:row>
    		<aui:col span="4">		 
				 <aui:fieldset>		
			      	<label class="control-label" for="<%=portletId%>_share.percentual_stop_lost"><liferay-ui:message key="share.percentual_stop_lost"/></label><input  <%=_position.IsOpen()==false ? "readonly" : "" %> id="<%=portletId%>_percentual_stop_lost" class="field form-control"  min="0"  max="100" type="number"  step="0.01"   formnovalidate="formnovalidate"   pattern="[0-9]+([,][0-9]+)?" placeholder="0,00" name="<%=portletId%>_percentual_stop_lost"  value="${position.percentualstoplost_out gt 0 ? position.percentualstoplost_out : ''}"/> 	    	      		
			    </aui:fieldset> 
			</aui:col>   
			<aui:col span="4">		 
			    <aui:fieldset>		
			    	<label class="control-label" for="<%=portletId%>_share.percentual_stop_profit"><liferay-ui:message key="share.percentual_stop_profit"/></label><input <%=_position.IsOpen()==false ? "readonly" : "" %>  id="<%=portletId%>_percentual_stop_profit" class="field form-control"  min="0"  max="100" type="number"  step="0.01"   formnovalidate="formnovalidate"   pattern="[0-9]+([,][0-9]+)?" placeholder="0,00" name="<%=portletId%>_percentual_stop_profit"  value="${position.percentualstopprofit_out gt 0 ? position.percentualstopprofit_out : ''}"/> 	    	      	    		
			    </aui:fieldset> 
			</aui:col>   
			<aui:col span="4">		
				<!--  ponemos el trailling pendiente de una fase para modificarlo  -->		  
				 <aui:fieldset>		
			    	<label class="control-label" for="<%=portletId%>_share.percentual_trailling_stop_lost"><liferay-ui:message key="share.percentual_trailling_stop_lost"/></label><input disabled="disabled" <%=_position.IsOpen()==false ? "readonly" : "" %>  id="<%=portletId%>_percentual_trailling_stop_lost" class="field form-control"  min="0"  max="100" type="number"  step="0.01"   formnovalidate="formnovalidate"   pattern="[0-9]+([,][0-9]+)?" placeholder="0,00" name="<%=portletId%>_percentual_stop_profit"  value="${position.percentual_trailling_stop_lost gt 0 ? position.percentual_trailling_stop_lost : ''}"/> 	    	      	    		
			    </aui:fieldset> 
			</aui:col>   			
	</aui:row>
	
    <script type="text/javascript">
               
            function <portlet:namespace />extractCodeFromEditor() {
                       var x = document.<portlet:namespace />fm.<portlet:namespace />content.value = window.<portlet:namespace />editor.getHTML();
            }
    </script>   
   	<aui:row>
   		<aui:col span="12">	    
        <aui:field-wrapper label="position.observaciones">
                <liferay-ui:input-editor   contents="${position.description}" showSource="true" />
                <aui:input name="content" type="hidden" />
                <aui:input name="positionId"  value="${position.positionId}" type="hidden" />
        </aui:field-wrapper>
		</aui:col>   			
	</aui:row>
	<!--  PONGO ESTO EN VEZ DE BUTTON PORQUE EN LINUX ME DA UN ERROR DE END OF INPUT DE JAVASCRIPT  -->
	 <aui:button-row>
        <aui:button type="submit" cssClass="btn-lg"></aui:button>
        <%if (_position.IsCloseable() && EnabledStrategyClosePosition) { %>
        	<a class="btn btn-lg btn-primary btn-default" href="${closePositionURL}">
				<span class="lfr-btn-label"><liferay-ui:message key ="position.exit"/></span>
    		</a>
        	<!--<aui:button onClick="<%=closePositionURL%>" value="position.exit" cssClass="btn btn-lg btn-primary"></aui:button> -->
        <%} %>
        <%if (_position.IsCancelable() && EnabledStrategyCancelPosition) { %>
        	<a class="btn btn-lg btn-primary btn-default" href="${cancelPositionURL}">
				<span class="lfr-btn-label"><liferay-ui:message key ="position.cancel"/></span>
    		</a>
        	<!-- <aui:button onClick="<%=cancelPositionURL%>" value="position.cancel" cssClass="btn btn-lg btn-primary"></aui:button>  -->
        <%} %>
        <aui:button type="cancel" cssClass="btn-lg" onClick="<%=redirect%>"></aui:button>
    </aui:button-row>
     </aui:fieldset>
    </aui:fieldset-group>
</aui:form>
        
</div>
