
<%@page import="com.ibtrader.util.*"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>

<%@ include file="/init.jsp" %>
		
<portlet:actionURL name="searchPosition" var="searchPositionURL" >
 	<portlet:param name="redirect" value="<%=themeDisplay.getURLCurrent()%>"/>
</portlet:actionURL>

<%

Date _ini = (Date) request.getAttribute("_DateINI");
Date _end = (Date)  request.getAttribute("_DateEND");

Calendar cini = Calendar.getInstance();
Calendar cend = Calendar.getInstance();

cini.setTime(_ini);
cend.setTime(_end);

int startTimeDay = cini.get(Calendar.DATE);
int startTimeMonth = cini.get(Calendar.MONTH);
int startTimeYear = cini.get(Calendar.YEAR);


int endTimeDay = cend.get(Calendar.DATE);
int endTimeMonth = cend.get(Calendar.MONTH);
int endTimeYear = cend.get(Calendar.YEAR);


%>
<aui:form action="<%=searchPositionURL%>"   name="fm" method="POST">
	 <liferay-ui:panel collapsible="false" defaultState="collapsed"  markupView="lexicon" extended="true" title="">
	   <aui:row>
	   <aui:col span="2">
	    	 <aui:fieldset>	
	    	<liferay-ui:message key="position.from"/>
  				<liferay-ui:input-date dayParam="startDateDay"
					     dayValue="<%= (startTimeDay)%>"					   	    
					     monthParam="startDateMonth"
					     monthValue="<%= startTimeMonth %>"
					     name="from"
					     yearParam="startDateYear"
					     yearValue="<%= startTimeYear %>"/>
  			
  			</aui:fieldset>
	    	
	   </aui:col>
	   <aui:col span="2"> 
	      	<aui:fieldset>		
 			<liferay-ui:message key="position.to"/>
  				<liferay-ui:input-date dayParam="endDateDay"
					     dayValue="<%= endTimeDay %>"					     					    
					     monthParam="endDateMonth"
					     monthValue="<%= endTimeMonth %>"
					     name="to"
					     yearParam="endDateYear"
					     yearValue="<%= endTimeYear %>"/>
  			
  				 </aui:fieldset>    	    	            
      </aui:col>
      <aui:col span="6"> 
    	 <aui:button-row>
	     	<aui:button type="submit" cssClass="btn-lg" value="position.search"/>		                
	    </aui:button-row>
      </aui:col>
        </aui:row>
	   </liferay-ui:panel>     
</aui:form>
  

