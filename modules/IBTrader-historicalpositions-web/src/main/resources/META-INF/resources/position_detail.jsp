
<%@page import="com.ibtrader.util.*"%>

<%@ include file="/init.jsp" %>
		
<portlet:actionURL name="editPosition" var="editPositionURL" >
 <portlet:param name="redirect" value="<%=themeDisplay.getURLCurrent()%>"/>
 </portlet:actionURL>


<% 
String redirect = ParamUtil.getString(request, "redirect");
String  paramPortletName = renderResponse.getNamespace() + "f"; 

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);

%>



<div class="container-fluid-1280">
<aui:form action="<%=editPositionURL%>"   name="fm" method="POST" onsubmit="<portlet:namespace />extractCodeFromEditor()">
	<aui:fieldset-group markupView="lexicon">
    <aui:fieldset>
	<liferay-ui:success key="share.success" message="share.success"/>
	<liferay-ui:error key="share.error.exists" message="share.error.exists"/>
	
	<aui:fieldset>
        <script type="text/javascript">
               
            function <portlet:namespace />extractCodeFromEditor() {
                       var x = document.<portlet:namespace />fm.<portlet:namespace />content.value = window.<portlet:namespace />editor.getHTML();
            }
    </script>
        </script>
        <aui:field-wrapper label="position.observaciones">
                <liferay-ui:input-editor   contents="${position.description}" showSource="true" />
                <aui:input name="content" type="hidden" />
                <aui:input name="positionId"  value="${position.positionId}" type="hidden" />
        </aui:field-wrapper>
	</aui:fieldset>
     <aui:button-row>
        <aui:button type="submit" cssClass="btn-lg"></aui:button>
        <aui:button type="cancel" cssClass="btn-lg" onClick="<%=redirect%>"></aui:button>
    </aui:button-row>
     </aui:fieldset>
    </aui:fieldset-group>
</aui:form>
        
</div>
