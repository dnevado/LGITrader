<%@ include file="/init.jsp" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="com.ibtrader.data.model.BackTesting" %>
<%@ page import="com.ibtrader.util.ConfigKeys" %>
<%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>


<jsp:useBean id="utilities" class="com.ibtrader.util.Utilities"/>


<%





BackTesting  _backtesting = (BackTesting) request.getAttribute("backtesting");
 
 String redirect = ParamUtil.getString(request, "redirect");
 String  paramPortletName = renderResponse.getNamespace() + "f"; 
 
 portletDisplay.setShowBackIcon(true);
 portletDisplay.setURLBack(redirect);
 
 Calendar c = Calendar.getInstance();
 Calendar cFrom = Calendar.getInstance();

 long shareId = 0; 
 
 if (_backtesting!=null)
 {
		c.setTime(_backtesting.getToDate());
 		cFrom.setTime(_backtesting.getFromDate());
 		shareId =  _backtesting.getShareId();
 }
 else
	 	 shareId =  GetterUtil.getLong("shareId", 0);
 
 
 
 int  D1  = _backtesting!=null  && _backtesting.getFromDate()!=null ? _backtesting.getFromDate().getDate() : Calendar.getInstance().get(Calendar.DATE);
 int  M1  = _backtesting!=null  && _backtesting.getFromDate()!=null ? _backtesting.getFromDate().getMonth() : Calendar.getInstance().get(Calendar.MONTH);
 int  Y1  = _backtesting!=null  && _backtesting.getFromDate()!=null ? cFrom.get(Calendar.YEAR) : Calendar.getInstance().get(Calendar.YEAR);
 int  D2  = _backtesting!=null  && _backtesting.getToDate()!=null ? _backtesting.getToDate().getDate() :  Calendar.getInstance().get(Calendar.DATE);
 int  M2  = _backtesting!=null  && _backtesting.getToDate()!=null ? _backtesting.getToDate().getMonth() : Calendar.getInstance().get(Calendar.MONTH);
 int  Y2  = _backtesting!=null  && _backtesting.getToDate()!=null ? c.get(Calendar.YEAR) : Calendar.getInstance().get(Calendar.YEAR);

 

 
 boolean disabled = _backtesting!=null;


%>



<script type="text/javascript">
           
        function <portlet:namespace />extractCodeFromEditor() {
                   var x = document.<portlet:namespace />fm.<portlet:namespace />content.value = window.<portlet:namespace />editor.getHTML();
        }
</script>   

<portlet:actionURL name="addeditBackTesting" var="addeditBackTestingURL">
 <portlet:param name="redirect" value="<%=themeDisplay.getURLCurrent()%>"/>
  <portlet:param name="shareId" value="${shareId}"/>
 
 </portlet:actionURL>


<div class="container-fluid-1280">
<aui:form action="<%=addeditBackTestingURL%>"   name="fm" method="POST" onsubmit="<portlet:namespace />extractCodeFromEditor()">	
	<liferay-ui:success key="backtesting.success" message="backtesting.success"/>
	<liferay-ui:error key="backtesting.error.missingparameters" message="backtesting.error.missingparameters"/>
	<liferay-ui:error key="backTesting.error.exists" message="backTesting.error.exists"/>
	<liferay-ui:error key="backtesting.error.formatparameters" message="backtesting.error.formatparameters"/>
	<liferay-ui:error key="backtesting.notremovable" message="backtesting.notremovable"/>
	<liferay-ui:error key="backtesting.notstartable" message="backtesting.notstartable"/>
	<liferay-ui:error key="backtesting.notstoppable" message="backtesting.notstoppable"/>
	
	<% if (_backtesting==null) { %>	
	<div class="alert alert-warning" role="alert">
  		  <liferay-ui:message key="BackTesting.intro"/>
	</div>
	<% } %>	
	
	<aui:fieldset-group markupView="lexicon">
	<% if (_backtesting!=null) { %>	
	<aui:row>      	
       <aui:fieldset> 
       <aui:col span="6">
        <aui:input  readonly="readonly" type="text" name="name" label="Stock.name" value="${share.name}"/>                              
       </aui:col>
       <aui:col span="6">
        <aui:input  readonly="readonly" type="text" name="symbol" label="Stock.symbol" value="${share.symbol}"/>                              
       </aui:col>
     </aui:fieldset>
     </aui:row>     
     <% } %> 
     <aui:row>      
       <aui:fieldset> 
       <aui:col span="6">
        <liferay-ui:message key="BackTesting.From"/>
        <liferay-ui:input-date
                dayParam="fromDay"
                dayValue="<%=D1%>"
                disabled="<%= disabled %>"
                firstDayOfWeek="0"
                monthParam="fromMonth"
                monthValue="<%=M1%>"
                yearParam="fromYear"
                yearValue="<%=Y1%>"
                />
       </aui:col>
       <aui:col span="6">
        <liferay-ui:message key="BackTesting.To"/>
       <liferay-ui:input-date
                dayParam="toDay"
                dayValue="<%=D2%>"
                disabled="<%= disabled %>"
                firstDayOfWeek="0"
                monthParam="toMonth"
             	 monthValue="<%=M2%>"
                yearParam="toYear"
                yearValue="<%=Y2%>"    
               />
       </aui:col>
     </aui:fieldset>
     </aui:row>
   </aui:fieldset-group>  
     <aui:fieldset-group markupView="lexicon">
     <aui:fieldset> 
	 <aui:row>
   		<aui:col span="12">	    
        <aui:field-wrapper label="BackTesting.Description">
                <liferay-ui:input-editor required="true"  contents="${backtesting.description}" showSource="true" />
                <aui:input name="content" type="hidden" />
              
        </aui:field-wrapper>
		</aui:col>   			
	</aui:row>
	 </aui:fieldset>
    </aui:fieldset-group>  
     <aui:button-row>
        <aui:button type="submit" cssClass="btn-lg"></aui:button>          
        <aui:button type="cancel" cssClass="btn-lg" onClick="<%=redirect%>"></aui:button>
    </aui:button-row>
       
      <aui:input name="backtestingId"  value="${backtesting.backTId}" type="hidden" />
      <aui:input  name="shareId" value="${shareId}" type="hidden" />
      <aui:input  name="redirect" value="<%=redirect%>" type="hidden" />
      
     
</aui:form>
</div>