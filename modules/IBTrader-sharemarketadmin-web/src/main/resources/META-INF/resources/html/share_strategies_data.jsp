<%@ include file="/init.jsp" %>

<%


ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

//System.out.println(row.getObject());

Share share = (Share) row.getObject();



List<Strategy> strategies = StrategyLocalServiceUtil.findStrategies(share.getShareId(), share.getCompanyId(), share.getGroupId());
  // exista como estratagia de la accion y activada 


if (strategies!=null && !strategies.isEmpty()) {
	for (Strategy strategy : strategies) {
		
		StrategyShare strategyshare = StrategyShareLocalServiceUtil.getByCommpanyShareStrategyId(share.getGroupId(), share.getCompanyId(), share.getShareId(), strategy.getStrategyID());
		if (strategyshare!=null && strategyshare.getActive()) {		
%>
		<div><%=strategy.getName()%> |</div>		
<% 		} 
	}		
} %>



 
