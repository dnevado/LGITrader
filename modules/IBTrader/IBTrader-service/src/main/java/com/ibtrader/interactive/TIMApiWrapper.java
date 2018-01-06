package com.ibtrader.interactive;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.ib.client.Bar;
import com.ib.client.CommissionReport;
import com.ib.client.Contract;
import com.ib.client.ContractDescription;
import com.ib.client.ContractDetails;
import com.ib.client.DeltaNeutralContract;
import com.ib.client.DepthMktDataDescription;
import com.ib.client.EClientSocket;
import com.ib.client.EJavaSignal;
import com.ib.client.EReaderSignal;
import com.ib.client.EWrapper;
import com.ib.client.Execution;
import com.ib.client.FamilyCode;
import com.ib.client.HistogramEntry;
import com.ib.client.HistoricalTick;
import com.ib.client.HistoricalTickBidAsk;
import com.ib.client.HistoricalTickLast;
import com.ib.client.NewsProvider;
import com.ib.client.Order;
import com.ib.client.OrderState;
import com.ib.client.PriceIncrement;
import com.ib.client.SoftDollarTier;
import com.ib.client.TickAttr;


//! [ewrapperimpl]
public class TIMApiWrapper implements EWrapper {
	//! [ewrapperimpl]
	
	//! [socket_declare]
	protected EReaderSignal readerSignal;
	protected EClientSocket clientSocket;
	protected int currentOrderId = -1;
	//! [socket_declare]
	
	//! [socket_init]
	public TIMApiWrapper() {
		readerSignal = new EJavaSignal();
		clientSocket = new EClientSocket(this, readerSignal);
	}
	//! [socket_init]
	public EClientSocket getClient() {
		return clientSocket;
	}
	
	public EReaderSignal getSignal() {
		return readerSignal;
	}
	
	public int getCurrentOrderId() {
		return currentOrderId;
	}
	
	 //! [tickprice]

	//! [tickprice]
	
	//! [ticksize]
	@Override
	public void tickSize(int tickerId, int field, int size) {
		//System.out.println("Tick Size. Ticker Id:" + tickerId + ", Field: " + field + ", Size: " + size);
	}
	//! [ticksize]
	
	//! [tickoptioncomputation]
	@Override
	public void tickOptionComputation(int tickerId, int field,
			double impliedVol, double delta, double optPrice,
			double pvDividend, double gamma, double vega, double theta,
			double undPrice) {
		System.out.println("TickOptionComputation. TickerId: "+tickerId+", field: "+field+", ImpliedVolatility: "+impliedVol+", Delta: "+delta
                +", OptionPrice: "+optPrice+", pvDividend: "+pvDividend+", Gamma: "+gamma+", Vega: "+vega+", Theta: "+theta+", UnderlyingPrice: "+undPrice);
	}
	//! [tickoptioncomputation]
	
	//! [tickgeneric]
	@Override
	public void tickGeneric(int tickerId, int tickType, double value) {
		//System.out.println("Tick Generic. Ticker Id:" + tickerId + ", Field: " + ", Value: " + value);
	}
	//! [tickgeneric]
	
	//! [tickstring]
	@Override
	public void tickString(int tickerId, int tickType, String value) {
	//	System.out.println("Tick string. Ticker Id:" + tickerId + ", Type: " + tickType + ", Value: " + value);
	}
	//! [tickstring]
	@Override
	public void tickEFP(int tickerId, int tickType, double basisPoints,
			String formattedBasisPoints, double impliedFuture, int holdDays,
			String futureLastTradeDate, double dividendImpact,
			double dividendsToLastTradeDate) {
		System.out.println("TickEFP. "+tickerId+", Type: "+tickType+", BasisPoints: "+basisPoints+", FormattedBasisPoints: "+
			formattedBasisPoints+", ImpliedFuture: "+impliedFuture+", HoldDays: "+holdDays+", FutureLastTradeDate: "+futureLastTradeDate+
			", DividendImpact: "+dividendImpact+", DividendsToLastTradeDate: "+dividendsToLastTradeDate);
	}
	//! [orderstatus]
	
	
	//! [openorder]
	@Override
	public void openOrder(int orderId, Contract contract, Order order,
			OrderState orderState) {
		System.out.println("OpenOrder. ID: "+orderId+", "+contract.symbol()+", "+contract.secType()+" @ "+contract.exchange()+": "+
			order.action()+", "+order.orderType()+" "+order.totalQuantity()+", "+orderState.status());
	}
	//! [openorder]
	
	//! [openorderend]
	@Override
	public void openOrderEnd() {
		System.out.println("OpenOrderEnd");
	}
	//! [openorderend]
	
	//! [updateaccountvalue]
	@Override
	public void updateAccountValue(String key, String value, String currency,
			String accountName) {
		System.out.println("UpdateAccountValue. Key: " + key + ", Value: " + value + ", Currency: " + currency + ", AccountName: " + accountName);
	}
	//! [updateaccountvalue]
	
	
	
	//! [updateaccounttime]
	@Override
	public void updateAccountTime(String timeStamp) {
		System.out.println("UpdateAccountTime. Time: " + timeStamp+"\n");
	}
	//! [updateaccounttime]
	
	//! [accountdownloadend]
	@Override
	public void accountDownloadEnd(String accountName) {
		System.out.println("Account download finished: "+accountName+"\n");
	}
	//! [accountdownloadend]
	
	//! [nextvalidid]
	@Override
	public void nextValidId(int orderId) {
		System.out.println("Next Valid Id: ["+orderId+"]");
		currentOrderId = orderId;
	}
	//! [nextvalidid]
	
	//! [contractdetails]
	@Override
	public void contractDetails(int reqId, ContractDetails contractDetails) {
		System.out.println("ContractDetails. ReqId: ["+reqId+"] - ["+contractDetails.contract().symbol()+"], ["+contractDetails.contract().secType()+"], ConId: ["+contractDetails.contract().conid()+"] @ ["+contractDetails.contract().exchange()+"]");
	}
	//! [contractdetails]
	@Override
	public void bondContractDetails(int reqId, ContractDetails contractDetails) {
		System.out.println("bondContractDetails");
	}
	//! [contractdetailsend]
	@Override
	public void contractDetailsEnd(int reqId) {
		System.out.println("ContractDetailsEnd. "+reqId+"\n");
	}
	//! [contractdetailsend]
	
	//! [execdetails]
	@Override
	public void execDetails(int reqId, Contract contract, Execution execution) {
		System.out.println("ExecDetails. "+reqId+" - ["+contract.symbol()+"], ["+contract.secType()+"], ["+contract.currency()+"], ["+execution.execId()+"], ["+execution.orderId()+"], ["+execution.shares()+"]");
	}
	//! [execdetails]
	
	//! [execdetailsend]
	@Override
	public void execDetailsEnd(int reqId) {
		System.out.println("ExecDetailsEnd. "+reqId+"\n");
	}
	//! [execdetailsend]
	
	//! [updatemktdepth]
	@Override
	public void updateMktDepth(int tickerId, int position, int operation,
			int side, double price, int size) {
		System.out.println("UpdateMarketDepth. "+tickerId+" - Position: "+position+", Operation: "+operation+", Side: "+side+", Price: "+price+", Size: "+size+"");
	}
	//! [updatemktdepth]
	@Override
	public void updateMktDepthL2(int tickerId, int position,
			String marketMaker, int operation, int side, double price, int size) {
		System.out.println("updateMktDepthL2");
	}
	//! [updatenewsbulletin]
	@Override
	public void updateNewsBulletin(int msgId, int msgType, String message,
			String origExchange) {
		System.out.println("News Bulletins. "+msgId+" - Type: "+msgType+", Message: "+message+", Exchange of Origin: "+origExchange+"\n");
	}
	//! [updatenewsbulletin]
	
	//! [managedaccounts]
	@Override
	public void managedAccounts(String accountsList) {
		System.out.println("Account list: " +accountsList);
	}
	//! [managedaccounts]

	//! [receivefa]
	@Override
	public void receiveFA(int faDataType, String xml) {
		System.out.println("Receing FA: "+faDataType+" - "+xml);
	}
	//! [receivefa]
	
	//! [historicaldata]

	//! [historicaldata]
	
	//! [scannerparameters]
	@Override
	public void scannerParameters(String xml) {
		System.out.println("ScannerParameters. "+xml+"\n");
	}
	//! [scannerparameters]
	
	//! [scannerdata]
	@Override
	public void scannerData(int reqId, int rank,
			ContractDetails contractDetails, String distance, String benchmark,
			String projection, String legsStr) {
		System.out.println("ScannerData. "+reqId+" - Rank: "+rank+", Symbol: "+contractDetails.contract().symbol()+", SecType: "+contractDetails.contract().secType()+", Currency: "+contractDetails.contract().currency()
                +", Distance: "+distance+", Benchmark: "+benchmark+", Projection: "+projection+", Legs String: "+legsStr);
	}
	//! [scannerdata]
	
	//! [scannerdataend]
	@Override
	public void scannerDataEnd(int reqId) {
		System.out.println("ScannerDataEnd. "+reqId);
	}
	//! [scannerdataend]
	
	//! [realtimebar]
	@Override
	public void realtimeBar(int reqId, long time, double open, double high,
			double low, double close, long volume, double wap, int count) {
		System.out.println("RealTimeBars. " + reqId + " - Time: " + time + ", Open: " + open + ", High: " + high + ", Low: " + low + ", Close: " + close + ", Volume: " + volume + ", Count: " + count + ", WAP: " + wap);
	}
	//! [realtimebar]
	@Override
	public void currentTime(long time) {
		System.out.println("currentTime");
	}
	//! [fundamentaldata]
	@Override
	public void fundamentalData(int reqId, String data) {
		System.out.println("FundamentalData. ReqId: ["+reqId+"] - Data: ["+data+"]");
	}
	//! [fundamentaldata]
	@Override
	public void deltaNeutralValidation(int reqId, DeltaNeutralContract underComp) {
		System.out.println("deltaNeutralValidation");
	}
	//! [ticksnapshotend]
	@Override
	public void tickSnapshotEnd(int reqId) {
		System.out.println("TickSnapshotEnd: "+reqId);
	}
	//! [ticksnapshotend]
	
	//! [marketdatatype]
	@Override
	public void marketDataType(int reqId, int marketDataType) {
		System.out.println("MarketDataType. ["+reqId+"], Type: ["+marketDataType+"]\n");
	}
	//! [marketdatatype]
	
	//! [commissionreport]
	@Override
	public void commissionReport(CommissionReport commissionReport) {
		System.out.println("CommissionReport. ["+commissionReport.m_execId+"] - ["+commissionReport.m_commission+"] ["+commissionReport.m_currency+"] RPNL ["+commissionReport.m_realizedPNL+"]");
	}
	//! [commissionreport]
	
	
	
	//! [positionend]
	@Override
	public void positionEnd() {
		System.out.println("PositionEnd \n");
	}
	//! [positionend]
	
	//! [accountsummary]
	@Override
	public void accountSummary(int reqId, String account, String tag,
			String value, String currency) {
		System.out.println("Acct Summary. ReqId: " + reqId + ", Acct: " + account + ", Tag: " + tag + ", Value: " + value + ", Currency: " + currency);
	}
	//! [accountsummary]
	
	//! [accountsummaryend]
	@Override
	public void accountSummaryEnd(int reqId) {
		System.out.println("AccountSummaryEnd. Req Id: "+reqId+"\n");
	}
	//! [accountsummaryend]
	@Override
	public void verifyMessageAPI(String apiData) {
		System.out.println("verifyMessageAPI");
	}

	@Override
	public void verifyCompleted(boolean isSuccessful, String errorText) {
		System.out.println("verifyCompleted");
	}

	@Override
	public void verifyAndAuthMessageAPI(String apiData, String xyzChallange) {
		System.out.println("verifyAndAuthMessageAPI");
	}

	@Override
	public void verifyAndAuthCompleted(boolean isSuccessful, String errorText) {
		System.out.println("verifyAndAuthCompleted");
	}
	//! [displaygrouplist]
	@Override
	public void displayGroupList(int reqId, String groups) {
		System.out.println("Display Group List. ReqId: "+reqId+", Groups: "+groups+"\n");
	}
	//! [displaygrouplist]
	
	//! [displaygroupupdated]
	@Override
	public void displayGroupUpdated(int reqId, String contractInfo) {
		System.out.println("Display Group Updated. ReqId: "+reqId+", Contract info: "+contractInfo+"\n");
	}
	//! [displaygroupupdated]
	@Override
	public void error(Exception e) {
		System.out.println("Exception: "+e.getMessage());
	}

	@Override
	public void error(String str) {
		System.out.println("Error STR");
	}
	//! [error]
	@Override
	public void error(int id, int errorCode, String errorMsg) {
		System.out.println("Error. Id: " + id + ", Code: " + errorCode + ", Msg: " + errorMsg + "\n");
	}
	//! [error]
	@Override
	public void connectionClosed() {
		System.out.println("Connection closed");
	}

	//! [connectack]
	@Override
	public void connectAck() {
		if (clientSocket.isAsyncEConnect()) {
			System.out.println("Acknowledging connection");
			clientSocket.startAPI();
		}
	}
	@Override
	public void tickPrice(int tickerId, int field, double price, TickAttr attrib) {
		System.out.println("tickPrice: " + tickerId + ", field: " + field);

		
	}
	@Override
	public void orderStatus(int orderId, String status, double filled, double remaining, double avgFillPrice,
			int permId, int parentId, double lastFillPrice, int clientId, String whyHeld, double mktCapPrice) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void updatePortfolio(Contract contract, double position, double marketPrice, double marketValue,
			double averageCost, double unrealizedPNL, double realizedPNL, String accountName) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void historicalData(int reqId, Bar bar) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void position(String account, Contract contract, double pos, double avgCost) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void positionMulti(int reqId, String account, String modelCode, Contract contract, double pos,
			double avgCost) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void positionMultiEnd(int reqId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void accountUpdateMulti(int reqId, String account, String modelCode, String key, String value,
			String currency) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void accountUpdateMultiEnd(int reqId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void securityDefinitionOptionalParameter(int reqId, String exchange, int underlyingConId,
			String tradingClass, String multiplier, Set<String> expirations, Set<Double> strikes) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void securityDefinitionOptionalParameterEnd(int reqId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void softDollarTiers(int reqId, SoftDollarTier[] tiers) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void familyCodes(FamilyCode[] familyCodes) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void symbolSamples(int reqId, ContractDescription[] contractDescriptions) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void historicalDataEnd(int reqId, String startDateStr, String endDateStr) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mktDepthExchanges(DepthMktDataDescription[] depthMktDataDescriptions) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void tickNews(int tickerId, long timeStamp, String providerCode, String articleId, String headline,
			String extraData) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void smartComponents(int reqId, Map<Integer, Entry<String, Character>> theMap) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void tickReqParams(int tickerId, double minTick, String bboExchange, int snapshotPermissions) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void newsProviders(NewsProvider[] newsProviders) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void newsArticle(int requestId, int articleType, String articleText) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void historicalNews(int requestId, String time, String providerCode, String articleId, String headline) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void historicalNewsEnd(int requestId, boolean hasMore) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void headTimestamp(int reqId, String headTimestamp) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void histogramData(int reqId, List<HistogramEntry> items) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void historicalDataUpdate(int reqId, Bar bar) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void rerouteMktDataReq(int reqId, int conId, String exchange) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void rerouteMktDepthReq(int reqId, int conId, String exchange) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void marketRule(int marketRuleId, PriceIncrement[] priceIncrements) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void pnl(int reqId, double dailyPnL, double unrealizedPnL) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void pnlSingle(int reqId, int pos, double dailyPnL, double unrealizedPnL, double value) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void historicalTicks(int reqId, List<HistoricalTick> ticks, boolean done) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void historicalTicksBidAsk(int reqId, List<HistoricalTickBidAsk> ticks, boolean done) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void historicalTicksLast(int reqId, List<HistoricalTickLast> ticks, boolean done) {
		// TODO Auto-generated method stub
		
	}
	
	

}
