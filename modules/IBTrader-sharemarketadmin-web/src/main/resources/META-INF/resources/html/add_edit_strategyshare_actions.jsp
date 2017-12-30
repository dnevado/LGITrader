<%@ include file="/init.jsp" %>

<%
String mvcPath = ParamUtil.getString(request, "mvcPath");

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

//System.out.println(row.getObject());

Strategy strategy = (Strategy) row.getObject();

long StrategyId = strategy.getStrategyID();

Share share=null;


long shareId =0;
if (request.getAttribute("share")!=null)
{
	share = (Share) request.getAttribute("share");
	shareId = share.getShareId();

}
%>


     


<portlet:renderURL   var="EditStrategyShareURL">
	<portlet:param name="mvcRenderCommandName" value="/html/add_edit_strategyshare"/>
    <portlet:param name="shareId" value="<%= String.valueOf(shareId) %>"/>
    <portlet:param name="strategyId" value="<%= String.valueOf(StrategyId) %>"/>    
    <portlet:param name="tab" value="share.strategy"></portlet:param>
</portlet:renderURL>  
 
<liferay-ui:icon-menu>
<liferay-ui:icon image="edit" message="strategyshare.edit" url="${EditStrategyShareURL}" />                        
</liferay-ui:icon-menu>
