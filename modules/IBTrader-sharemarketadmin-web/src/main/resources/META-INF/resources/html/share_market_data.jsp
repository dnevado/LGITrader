<%@ include file="/init.jsp" %>

<%


ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

//System.out.println(row.getObject());

Share share = (Share) row.getObject();

long MarketId = share.getMarketId();



Market market = MarketLocalServiceUtil.fetchMarket(MarketId);
if (market!=null) {
%>
<div><%=market.getName()%></div>
<div><%=market.getStart_hour() %> | <%=market.getEnd_hour() %></div>
<% } %>



 
