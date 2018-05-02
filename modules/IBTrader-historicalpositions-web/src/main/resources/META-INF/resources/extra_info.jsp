<%@ include file="/init.jsp" %>

<%
String mvcPath = ParamUtil.getString(request, "mvcPath");

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

//System.out.println(row.getObject());

Position position = (Position) row.getObject();

long ShareId = position.getShareId();
long PositionId = position.getPositionId(); 

Share _share = ShareLocalServiceUtil.fetchShare(ShareId);


%>
  
<%=_share.getSymbol()%>
