<%@ include file="/init.jsp" %>

<%
String mvcPath = ParamUtil.getString(request, "mvcPath");

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

//System.out.println(row.getObject());

Share share = (Share) row.getObject();

long ShareId = share.getShareId();


%>



<portlet:renderURL   var="RemoveShareURL">
 	<portlet:param name="mvcRenderCommandName" value="/html/add_edit_share"/>
    <portlet:param name="shareId" value="<%= String.valueOf(ShareId) %>"/>   
</portlet:renderURL>

<portlet:renderURL   var="EditShareURL">
	<portlet:param name="mvcRenderCommandName" value="/html/add_edit_share"/>
    <portlet:param name="shareId" value="<%= String.valueOf(ShareId) %>"/>    
</portlet:renderURL>  
 
<liferay-ui:icon-menu>
<liferay-ui:icon image="edit" message="share.edit" url="${EditShareURL}" />
<liferay-ui:icon image="delete" message="share.delete" url="${RemoveShareURL}" />
                         
</liferay-ui:icon-menu>