<%@ include file="/init.jsp" %>
<liferay-ui:asset-categories-error />
<liferay-ui:asset-tags-error />

<portlet:actionURL name="addStrategy" var="addStrategyURL" />

<div class="container-fluid-1280">
<aui:form action="${addStrategyURL}" name="<portlet:namespace />fm" method="POST">
    <aui:fieldset>
        <aui:input  name="nombre" value="${Strg.name}">
                <aui:validator  name="required" />	
                <aui:validator name="maxLength">75</aui:validator>
        </aui:input>
        <aui:input name="descripcion">
               <aui:validator  name="required" />	
                <aui:validator name="maxLength">75</aui:validator>	
       </aui:input>
    </aui:fieldset>
	<aui:fieldset>
		<aui:input name="active" type="radio" value="active" />
        <aui:select label="Tipo" name="<portlet:namespace />tipo">
        	<aui:option  value="TODAS"  >COMPRA/VENTA</aui:option>
			<aui:option  value="COMPRA" >COMPRA</aui:option>
			<aui:option  value="VENTA"  >VENTA</aui:option>		
		</aui:select>
		<aui:input name="className">
				<aui:validator  name="required" />	
                <aui:validator name="maxLength">75</aui:validator>
        </aui:input>
    </aui:fieldset>
    <aui:fieldset-group markupView="lexicon"> 
     
    <aui:fieldset collapsed="<%= true %>" collapsible="<%= true %>" label="categorization">
        <aui:input name="categories" type="assetCategories" />
        <aui:input name="tags" type="assetTags" />
    </aui:fieldset>
    
	</aui:fieldset-group>
    <aui:button-row>
        <aui:button cssClass="btn-lg" type="submit"></aui:button>
        <aui:button type="cancel" cssClass="btn-lg" onClick="${viewURL.toString()}"></aui:button>
    </aui:button-row>
</aui:form>
</div>