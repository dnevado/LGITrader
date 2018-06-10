
<%@page import="com.ibtrader.util.*"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>

<%@ include file="/init.jsp" %>
		
<portlet:actionURL name="searchStrategyShare" var="searchStrategyShareURL" >
 	<portlet:param name="tab" value="share.strategy"></portlet:param>
    <portlet:param name="shareId" value="${share.shareId}"></portlet:param>
</portlet:actionURL>

<%

int j =0;

/* Date _ini = (Date) request.getAttribute("_DateINI");
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
*/

%>
<aui:form action="<%=searchStrategyShareURL%>"   name="fm" method="POST">
	   <aui:row>
	   <aui:col span="2">
	    	
			 <aui:fieldset>	  	
			    <aui:select label="strategy.filter" name="strategyselected">		
			       <aui:option  value="SELECTED"><%= LanguageUtil.get(request,"strategy.selected") %></aui:option>
			       <aui:option  value="ALL"><%= LanguageUtil.get(request,"strategy.all") %></aui:option>				 		   				    				   	
				</aui:select>
  			 </aui:fieldset>	
	    	
	   </aui:col>
	  </aui:row>      
	
     <aui:button-row>
     	<aui:button type="submit" cssClass="btn-lg" value="strategy.search"/>		                
    </aui:button-row>     
</aui:form>
  

