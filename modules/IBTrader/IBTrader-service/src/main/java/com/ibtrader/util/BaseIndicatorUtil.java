package 	com.ibtrader.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ta4j.core.BaseBar;
import org.ta4j.core.BaseTimeSeries;
import org.ta4j.core.TimeSeries;
import org.ta4j.core.indicators.EMAIndicator;
import org.ta4j.core.indicators.MACDIndicator;
import org.ta4j.core.indicators.SMAIndicator;
import org.ta4j.core.indicators.StochasticOscillatorDIndicator;
import org.ta4j.core.indicators.StochasticOscillatorKIndicator;
import org.ta4j.core.indicators.adx.ADXIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.indicators.pivotpoints.PivotLevel;
import org.ta4j.core.indicators.pivotpoints.PivotPointIndicator;
import org.ta4j.core.indicators.pivotpoints.StandardReversalIndicator;
import org.ta4j.core.indicators.pivotpoints.TimeLevel;

import com.ib.client.Types.TimeInForce;
import com.ib.controller.Bar;
import com.ibtrader.data.model.HistoricalRealtime;
import com.ibtrader.data.model.Market;
import com.ibtrader.data.model.Realtime;
import com.ibtrader.data.model.Share;
import com.ibtrader.data.service.HistoricalRealtimeLocalServiceUtil;
import com.ibtrader.data.service.RealtimeLocalServiceUtil;
import com.ibtrader.data.service.RealtimeServiceUtil;
import com.ibtrader.data.service.ShareLocalServiceUtil;
import com.ibtrader.strategy.IBStrategySimpleMobileAverage;
import com.ibtrader.techindicadors.IBTraderEMAIndicator;
import com.ibtrader.techindicadors.IBTraderPivotPointIndicator;
import com.ibtrader.techindicadors.IBTraderSMAIndicador;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;



public class BaseIndicatorUtil {

	
	private static Log _log = LogFactoryUtil.getLog(BaseIndicatorUtil.class);

	
	private static double movingAvarage(double St, double Yt) {
	    double alpha = 0.01, oneMinusAlpha = 0.99;
	    if(St <= 0D) {
	        St = Yt;
	    } else {
	        St = alpha*Yt + oneMinusAlpha*St;
	    }
	    return St;
	   }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	   double[] array = {7716.0,7716.25,7716.25,7726.25,7722.25,7725.0,7726.5,7723.25,7724.5,7723.0,7725.5,7726.5,7730.25,7702.5,7701.25,7712.75,7701.25,7695.25,7696.5,7701.75,7707.25,7702.25,7704.75,7701.5,7704.0,7703.0,7708.75};
	   IBTraderEMAIndicator EMA = new IBTraderEMAIndicator();
	   try {
		   System.out.println("Lenght prices:" + array.length);
		   System.out.println(EMA.calculate(array, 26));
		for (int j = 0 ; j<EMA.getEMA().length; j++)
			System.out.println("Perido: " + j + ":" +  EMA.getEMA()[j]);
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
		TimeSeries series = new BaseTimeSeries("getStochasticOscillatorD");
		ZonedDateTime endTime = ZonedDateTime.now();
        int counter = 1;		
    	Calendar barTime = Calendar.getInstance();
        barTime.set(Calendar.SECOND, 0);
		barTime.add(Calendar.SECOND, -1); // maximo y minimo
	   
		/*series.addBar(new BaseBar(endTime.plusMinutes(3),0.0,197,149,168,0.0));
		series.addBar(new BaseBar(endTime.plusMinutes(4),0.0,182,132,170,0.0));
		series.addBar(new BaseBar(endTime.plusMinutes(5),0.0,190,150,171,0.0));
		series.addBar(new BaseBar(endTime.plusMinutes(6),0.0,183,149,175,0.0));
		series.addBar(new BaseBar(endTime.plusMinutes(7),0.0,183,149,170,0.0));
		series.addBar(new BaseBar(endTime.plusMinutes(8),0.0,197,140,172,0.0));
		series.addBar(new BaseBar(endTime.plusMinutes(9),0.0,190,134,176,0.0));
		series.addBar(new BaseBar(endTime.plusMinutes(11),0.0,183,140,179,0.0));
		series.addBar(new BaseBar(endTime.plusMinutes(12),0.0,197,134,178,0.0));
		series.addBar(new BaseBar(endTime.plusMinutes(13),0.0,182 ,433, 186,0.0));
		series.addBar(new BaseBar(endTime.plusMinutes(14),0.0,190 ,141, 192,0.0));		
		series.addBar(new BaseBar(endTime.plusMinutes(15),0.0,183 ,129, 183,0.0));	
		series.addBar(new BaseBar(endTime.plusMinutes(17),0.0,182 ,136, 177,0.0));
		series.addBar(new BaseBar(endTime.plusMinutes(18),0.0,190 ,141, 172,0.0));
		 series.addBar(new BaseBar(endTime.plusMinutes(19),0.0,190 ,160, 167,0.0));
		series.addBar(new BaseBar(endTime.plusMinutes(20),0.0,165 ,140, 177,0.0)); */
	   
		
		series.addBar(new BaseBar(endTime.plusMinutes(0),0.0,7935.25,7928.25,8033.0,0.0));
		series.addBar(new BaseBar(endTime.plusMinutes(1),0.0,7939.0,7932.5,8031.75,0.0));
		series.addBar(new BaseBar(endTime.plusMinutes(2),0.0,7937.5,7932.0,8030.75,0.0));
		series.addBar(new BaseBar(endTime.plusMinutes(3),0.0,7934.25,7924.0,8028.0,0.0));
		series.addBar(new BaseBar(endTime.plusMinutes(4),0.0,7931.75,7928.0,8030.25,0.0));
		series.addBar(new BaseBar(endTime.plusMinutes(5),0.0,7933.75,7929.25,8035.75,0.0));
		series.addBar(new BaseBar(endTime.plusMinutes(6),0.0,7934.25,7929.0,8034.5,0.0));
		series.addBar(new BaseBar(endTime.plusMinutes(7),0.0,7933.0,7926.75,8033.25,0.0));
		series.addBar(new BaseBar(endTime.plusMinutes(8),0.0,7929.25,7925.5,8034.75,0.0));
		series.addBar(new BaseBar(endTime.plusMinutes(9),0.0,7933.5,7927.25,8035.0,0.0));
		series.addBar(new BaseBar(endTime.plusMinutes(10),0.0,7936.5,7932.0,8036.0,0.0));
		series.addBar(new BaseBar(endTime.plusMinutes(11),0.0,7935.0,7932.25,8037.25,0.0));
		series.addBar(new BaseBar(endTime.plusMinutes(12),0.0,7937.0,7931.75,8039.0,0.0));
		series.addBar(new BaseBar(endTime.plusMinutes(13),0.0,7938.5,7934.5,8039.75,0.0));
		
		
		
		series.addBar(new BaseBar(endTime.plusMinutes(0),0.0,8096.25,8086.0,8092.25,0.0));
		series.addBar(new BaseBar(endTime.plusMinutes(1),0.0,8094.5,8083.5,8087.0,0.0));
		series.addBar(new BaseBar(endTime.plusMinutes(2),0.0,8098.5,8089.25,8089.75,0.0));
		series.addBar(new BaseBar(endTime.plusMinutes(3),0.0,8094.75,8074.0,8092.25,0.0));
		series.addBar(new BaseBar(endTime.plusMinutes(4),0.0,8040.5,8036.5,8036.5,0.0));		
		series.addBar(new BaseBar(endTime.plusMinutes(5),0.0,8040.25,8038.25,8039.75,0.0));
		series.addBar(new BaseBar(endTime.plusMinutes(6),0.0,8039.0,8036.25,8039.0,0.0));
		series.addBar(new BaseBar(endTime.plusMinutes(7),0.0,8037.25,8035.5,8037.25,0.0));
		series.addBar(new BaseBar(endTime.plusMinutes(8),0.0,8037.0,8034.75,8036.0,0.0));
		series.addBar(new BaseBar(endTime.plusMinutes(9),0.0,8037.25,8034.25,8035.0,0.0));
		series.addBar(new BaseBar(endTime.plusMinutes(10),0.0,8036.75,8030.25,8034.75,0.0));
		series.addBar(new BaseBar(endTime.plusMinutes(11),0.0,8034.75,8031.25,8033.25,0.0));
		series.addBar(new BaseBar(endTime.plusMinutes(12),0.0,8038.25,8032.75,8034.5,0.0));
		series.addBar(new BaseBar(endTime.plusMinutes(13),0.0,8041.5,8028.75,8035.75,0.0));
		
		long periodsToCalculate = 14;
		StochasticOscillatorKIndicator stochasticOscillatorKIndicator = new StochasticOscillatorKIndicator(series, Long.valueOf(periodsToCalculate).intValue());
		StochasticOscillatorDIndicator _stochasticOscillatorDIndicator = new StochasticOscillatorDIndicator(stochasticOscillatorKIndicator);
     
		for (int j=0;j<periodsToCalculate;j++)
		{ 
			try {System.out.println("_stochasticOscillatorDIndicator:" + j + ":" +  _stochasticOscillatorDIndicator.getValue(j).doubleValue());} catch (Exception e) {}	
		}

		
		
		

		
	   System.exit(1);
	   
	    double St = 0D;
	    for(int i=0; i<array.length; i++) {
	        St = movingAvarage(St, array[i]);
	        System.out.println(St);
	    }
	 
		
		
		Realtime realtime  = null;
	
		int timebar = 5;
		
		int _ModMinuteToEntry = barTime.get(Calendar.MINUTE) % timebar;
		if (_ModMinuteToEntry!=0)  // NO ESTOY EN EL MINUTO 5,10,15,20..etc (para las barras de 5)
			 barTime.add(Calendar.MINUTE, timebar - _ModMinuteToEntry);
		
		
		/* 201 valores */
		int barras = 201;
		int companyId = 20116;
		int groupId = 101213;
		int shareId = 2702; // appl
		
	
		
		//series.addBar(new BaseBar(endTime.plusMinutes(2),0.0,183,149,160,0.0));	
		

		



		TimeSeries series_pivotPointIndicator = new BaseTimeSeries("_pivotPointIndicator");
		series_pivotPointIndicator.addBar(new BaseBar(endTime.plusMinutes(1),0.0,200 ,180, 185,0.0));
		
		 periodsToCalculate = 8;
		
		PivotPointIndicator _pivotPointIndicator  = new PivotPointIndicator(series_pivotPointIndicator, TimeLevel.BARBASED);
		StandardReversalIndicator _standardReversalIndicator1 = new StandardReversalIndicator(_pivotPointIndicator, PivotLevel.RESISTANCE_1);
		StandardReversalIndicator _standardReversalIndicator2 = new StandardReversalIndicator(_pivotPointIndicator, PivotLevel.RESISTANCE_2);
		StandardReversalIndicator _standardReversalIndicator3 = new StandardReversalIndicator(_pivotPointIndicator, PivotLevel.RESISTANCE_3);
		StandardReversalIndicator _standardReversalIndicator4 = new StandardReversalIndicator(_pivotPointIndicator, PivotLevel.SUPPORT_1);
		StandardReversalIndicator _standardReversalIndicator5 = new StandardReversalIndicator(_pivotPointIndicator, PivotLevel.SUPPORT_2);
		StandardReversalIndicator _standardReversalIndicator6 = new StandardReversalIndicator(_pivotPointIndicator, PivotLevel.SUPPORT_3);
		
		System.out.println(_pivotPointIndicator.getValue(0));
		System.out.println(_standardReversalIndicator1.getValue(0));
		System.out.println(_standardReversalIndicator2.getValue(0));
		System.out.println(_standardReversalIndicator3.getValue(0));
		System.out.println(_standardReversalIndicator4.getValue(0));
		System.out.println(_standardReversalIndicator5.getValue(0));
		System.out.println(_standardReversalIndicator6.getValue(0));
		
		
		
		
		
		
		StochasticOscillatorKIndicator _stochasticOscillatorKIndicator = new StochasticOscillatorKIndicator(series, Long.valueOf(15).intValue());
		
		for (int j=0;j<periodsToCalculate;j++)
		{ 
			try {System.out.println("_stochasticOscillatorKIndicator:" + j + ":" +  _stochasticOscillatorKIndicator.getValue(j).doubleValue());} catch (Exception e) {}	
		}
		ClosePriceIndicator closePrice = new ClosePriceIndicator(series);

		SMAIndicator shortSma = new SMAIndicator(closePrice, 5);
		// Here is the 5-ticks-SMA value at the 42nd index
		for (int j=0;j<periodsToCalculate;j++)
		{ 
			try {System.out.println("SMAIndicator:" + j + ":" +  shortSma.getValue(j).doubleValue());} catch (Exception e) {}	
		}
		
	

        EMAIndicator emea = new EMAIndicator(closePrice, (int) periodsToCalculate);

        
        for (int j=0;j<periodsToCalculate;j++)
		{ 
			try {System.out.println("exponentialAvgMobile:" + j + ":" +  emea.getValue(j).doubleValue());} catch (Exception e) {}	
		}
        
        
        ADXIndicator ADXIndicator            = new ADXIndicator(series, (int) ConfigKeys.ADXR_PERIODS);
        
		double _ADX         = ADXIndicator.getValue((int) (periodsToCalculate-1)) !=null 	?  ADXIndicator.getValue((int) (periodsToCalculate-1)).doubleValue() : 0;
		double _ADXR 		 = ADXIndicator.getValue((int) (periodsToCalculate-1))!=null 	? (_ADX + ADXIndicator.getValue((int) (periodsToCalculate-1 - ConfigKeys.ADXR_PERIODS)).doubleValue()) / 2 : 0;
           
		System.out.println("_ADX:" + _ADX);	
	    System.out.println("_ADXR:" + _ADXR);	
			
        MACDIndicator _macdIndicator = new MACDIndicator(closePrice, Long.valueOf(5).intValue(), Long.valueOf(13).intValue());
       
		for (int j=0;j<periodsToCalculate;j++)
		{ 
			try {System.out.println("_macdIndicator:" + j + ":" +  _macdIndicator.getValue(j).doubleValue());} catch (Exception e) {}	
		}
        

	    
	    
		barTime.add(Calendar.MINUTE, -timebar); 
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	182	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	134	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	151	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	143	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	189	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	183	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	141	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	157	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	197	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	129	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	182	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	135	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	167	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	136	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	140	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	183	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	141	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	158	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	197	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	138	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	179	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	182	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	137	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	174	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	152	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	153	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	183	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	127	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	175	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	197	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	144	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	188	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	182	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	150	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	167	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	131	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	184	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	183	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	152	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	159	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	197	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	140	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	161	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	182	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	138	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	165	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	155	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	167	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	183	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	141	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	170	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	197	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	146	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	163	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	182	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	149	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	156	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	149	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	162	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	183	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	132	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	154	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	197	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	150	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	182	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	149	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	178	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	140	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	180	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	183	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	154	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	174	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	197	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	134	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	174	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	182	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	140	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	142	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	134	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	188	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	183	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	143	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	182	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	197	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	141	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	160	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	182	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	129	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	157	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	135	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	172	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	183	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	136	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	177	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	197	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	141	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	184	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	182	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	138	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	162	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	137	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	158	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	183	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	152	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	153	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	197	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	127	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	129	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	182	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	144	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	173	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	150	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	167	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	183	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	131	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	145	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	197	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	152	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	182	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	140	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	148	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	138	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	140	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	183	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	155	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	160	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	197	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	141	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	165	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	182	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	146	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	178	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	149	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	152	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	183	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	149	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	181	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	197	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	132	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	168	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	182	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	150	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	159	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	149	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	162	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	183	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	134	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	147	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	197	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	143	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	186	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	182	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	141	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	171	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	129	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	155	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	183	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	135	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	146	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	197	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	136	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	179	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	182	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	141	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	174	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	138	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	161	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	183	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	137	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	164	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	197	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	152	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	165	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	182	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	127	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	136	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	144	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	184	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	183	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	150	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	170	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	197	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	131	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	151	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	182	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	152	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	159	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	140	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	167	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	183	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	138	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	160	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	197	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	155	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	182	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	141	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	148	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	146	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	152	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	183	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	149	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	165	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	197	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	149	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	165	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	182	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	132	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	145	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	150	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	153	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	183	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	149	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	154	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	197	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	140	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	192	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	182	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	154	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	170	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	134	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	135	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	183	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	140	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	142	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	197	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	134	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	184	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	182	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	143	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	145	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	141	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	170	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	183	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	129	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	158	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	197	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	135	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	193	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	182	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	136	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	139	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	141	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	154	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	183	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	138	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	152	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	197	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	137	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	138	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	182	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	152	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	168	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	127	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	131	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	183	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	144	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	169	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	197	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	150	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	185	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	182	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	131	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	148	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	152	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	184	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	183	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	140	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	172	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	197	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	138	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	156	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	182	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	155	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	176	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	141	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	158	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	183	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	146	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	149	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	197	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	149	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	174	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	182	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	149	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	152	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	132	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	166	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	183	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	150	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	162	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	197	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	149	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	182	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	182	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	134	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	169	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	143	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	176	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	183	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	141	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	172	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	197	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	129	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	192	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	182	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	135	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	171	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	136	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	166	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	183	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	141	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	172	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	197	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	138	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	169	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	182	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	137	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	163	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	152	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	184	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	183	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	127	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	149	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	197	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	144	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	164	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	182	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	150	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	156	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	131	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	172	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	183	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	152	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	175	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	197	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	140	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	156	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	182	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	138	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	147	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	155	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	169	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	183	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	141	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	145	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	197	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	146	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	150	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	182	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	149	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	170	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	149	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	162	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	183	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	132	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	137	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	197	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	150	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	159	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	182	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	149	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	164	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	140	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	186	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	183	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	154	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	175	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	197	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	134	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	168	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	182	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	140	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	178	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	134	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	139	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	183	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	143	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	177	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	197	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	141	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	149	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	182	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	129	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	163	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	135	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	167	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	183	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	136	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	178	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	197	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	141	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	196	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	182	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	138	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	140	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	137	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	152	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	183	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	152	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	166	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	197	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	127	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	133	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	182	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	144	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	179	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	150	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	161	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	183	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	131	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	156	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	197	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	152	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	194	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	182	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	140	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	164	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	138	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	139	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	183	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	155	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	177	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	197	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	141	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	142	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	182	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	146	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	181	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	149	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	163	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	183	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	149	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	153	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	197	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	132	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	170	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	182	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	150	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	156	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	149	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	155	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	183	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	134	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	158	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	197	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	143	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	192	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	182	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	141	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	180	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	129	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	162	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	183	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	135	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	150	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	197	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	136	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	191	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	182	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	141	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	151	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	138	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	146	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	183	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	137	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	169	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	197	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	152	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	168	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	182	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	127	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	161	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	144	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	184	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	183	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	150	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	180	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	197	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	131	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	195	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	182	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	152	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	180	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	140	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	142	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	183	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	138	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	168	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	197	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	155	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	175	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	182	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	141	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	164	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	146	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	176	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	183	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	149	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	160	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	197	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	149	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	184	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	182	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	132	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	142	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	150	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	152	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	183	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	149	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	151	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	197	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	140	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	182	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	154	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	169	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	134	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	184	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	183	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	140	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	156	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	197	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	134	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	157	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	182	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	143	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	171	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	141	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	174	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	183	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	129	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	138	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	197	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	135	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	166	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	182	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	136	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	176	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	141	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	142	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	190	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	160	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	160	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	165	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	140	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	165	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		realtime  = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));barTime.add(Calendar.SECOND, -2); realtime.setValue(	170	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);realtime.setValue(	150	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.SECOND, +1);realtime.setValue(	170	);realtime.setGroupId(groupId);realtime.setCompanyId(companyId);realtime.setShareId(shareId); realtime.setCreateDate(barTime.getTime()); realtime.setModifiedDate(barTime.getTime());RealtimeLocalServiceUtil.updateRealtime(realtime);barTime.add(Calendar.MINUTE, -timebar);
		
		
		

	}
	
	
	/* avgMobile --> MEDIA MOVIL 
	 * oRTimeWidthRange -> Max y Minimo de la barra 
	 * oRTimeEnTramo --> Cierre de la barra
	 */

	public static boolean _IsBuySignalMM8_5MINBar(double _avgMobileSimple ,Double max_value, Double min_value,double _WidthBarRangePercent , Double oRTimeEnTramoBar)
	{
					
		/* A/ La barra debe cruzar la MM8 y al cierre el 75% de su cuerpo debe ser superior al precio cierre de la MM.
		y B/ Además, el precio cierre de la barra será => que el 75% del rango.
		*/
		
		
		if (_avgMobileSimple<= (max_value - _WidthBarRangePercent)
				&& (oRTimeEnTramoBar.doubleValue() >= (min_value + _WidthBarRangePercent)))
		{
			_log.debug("MM <= Max - Ancho = " + _avgMobileSimple+ "<="  + oRTimeEnTramoBar.doubleValue() + "-" + _WidthBarRangePercent);
			_log.debug("Cierre  >= Min + Ancho = " + oRTimeEnTramoBar.doubleValue() + ">="  + min_value + "+" + _WidthBarRangePercent);
		}
		
		return (_avgMobileSimple  <= (max_value - _WidthBarRangePercent) && (oRTimeEnTramoBar.doubleValue() >= (min_value+ _WidthBarRangePercent)));
	}
	
	
	public static boolean _IsSellSignalMM8_5MINBar(double _avgMobileSimple, Double max_value, Double min_value,double _WidthBarRangePercent , Double oRTimeEnTramoBar)
	{
	
		
		if (_avgMobileSimple  >= (min_value + _WidthBarRangePercent)
				&& (oRTimeEnTramoBar.doubleValue() <= (max_value - _WidthBarRangePercent)))
		{
			_log.debug("MM >= Min + Ancho = " + _avgMobileSimple + ">="  + oRTimeEnTramoBar.doubleValue() + "+" + _WidthBarRangePercent);
			_log.debug("Cierre  <= Max - Ancho = " + oRTimeEnTramoBar.doubleValue() + "<="  + min_value + "-" + _WidthBarRangePercent);
		}
		
		return (_avgMobileSimple>= (min_value + _WidthBarRangePercent) && (oRTimeEnTramoBar.doubleValue() <= (max_value - _WidthBarRangePercent)));
	}
	
	/* DEVUELVE UNA LISTA DE FECHAS CON LOS PERIODOS DE TRAMOS DE BARRAS
	 * COMO SON PRECIOS DE LA NARRA +1, RESTAMOS UNS SEGUNDO PARA QUE ESTE EN EL MINUTO PREVIAMENTE ANTERIOR DE CIERRE DE BARRA 
	 *   lastBarIncluded --> Avgsimple , en la ewtrategia de medias simples, no incluimos la ultima barra para la media 
	 *   				  --> en la exponencial SI 
	 *  
	 *  
	 *  
	 *  EL HISTORICAL TIENE DOS ENTRADAS  21:59:59 --> CIERRE DE LA BARRA 
	 *  								  2019-10-03 21:59:58 --> MAX Y MIN
	 *  
	 *  
	 *  _openMarket VALE PARA LOS DIAS FUTUROS , NO PARA EL HISTORICAL, DEBO IGNORARLO, DEBO COGER EL MAX Y MIN DEL DIA PARA SABER LA APARTURA Y CIERRE 
	 *  								  
	 *  */
	public   static List<String> getPeriodsMinutesMobileAvg(Date to, long PeriodN, long TimeBars, boolean lastBarIncluded, Market market, boolean simulation, long shareId)
	{
		SimpleDateFormat _sdf = new SimpleDateFormat(Utilities.__IBTRADER_SQL_DATE_);
		
		
		SimpleDateFormat _sdfdebug = new SimpleDateFormat("yyyyMMdd HH:mm:ss:SSS");
		
		Share share = ShareLocalServiceUtil.fetchShare(shareId);
		
		Calendar _cFrom = Calendar.getInstance();
		_cFrom.setTime(to);  
		Calendar _cPeriodsMinutesMobileAvg = Calendar.getInstance();
		_cPeriodsMinutesMobileAvg.setTimeInMillis(to.getTime());
		_cPeriodsMinutesMobileAvg.set(Calendar.SECOND, 0);
		_cPeriodsMinutesMobileAvg.set(Calendar.MILLISECOND, 0);
		_cPeriodsMinutesMobileAvg.add(Calendar.SECOND,-1);
		
		List<String> lAvgMobileDates = new ArrayList<String>();
		
		try 
		{
			/* BUSCAMOS 6 DIAS DE TRADING , ya que 125 periodos * 5 = 12 HORAS, contando fines de semana etc...  */
			int foundedDates = 0;
			for (int MAX_DAYS_TRADING=0;MAX_DAYS_TRADING<10;MAX_DAYS_TRADING++)
			{
				_cFrom.add(Calendar.DATE, -1);
				Market marketInfo = Utilities.getOpenCloseMarket(share, _cFrom.getTime(), simulation);
				if (Validator.isNotNull(marketInfo))
				{
					lAvgMobileDates.add(_sdf.format(_cFrom.getTime()));			
					_log.trace(_sdfdebug.format(_cPeriodsMinutesMobileAvg.getTime()));
					foundedDates++;
				}
				if (foundedDates==6) break;
				
			}			
		}
		catch (Exception e)
		{
			_log.error(e.getMessage());
		}
		return lAvgMobileDates;
		
	}
	
	
	public   static List<String> getPeriodsMinutesMobileAvg_OLD(Date to, long PeriodN, long TimeBars, boolean lastBarIncluded, Market market, boolean simulation, long shareId)
	{
		SimpleDateFormat _sdf = new SimpleDateFormat(Utilities.__IBTRADER_SQL_DATE_);
		SimpleDateFormat _cacheFormat  = new SimpleDateFormat(Utilities.__IBTRADER_LONG_DATE_FORMAT);
		
		SimpleDateFormat _sdfdebug = new SimpleDateFormat("yyyyMMdd HH:mm:ss:SSS");
		
		/* CACHE OPEN TIMES  Y LASTTRADING DATES OF A DAY */
		Map<String,LocalTime> cachedOpenTimes = new HashMap<String, LocalTime>();    // 2019-10-01, 1330 
		Map<String,Date> cachedPrecedingTradingDates = new HashMap<String, Date>();  // 2019-10-02, 2019-10-01,  DIA, DIA ANTERIOR DE TRADING  
		Map<String,LocalTime> cachedCloseTimes = new HashMap<String, LocalTime>();    // 2019-10-01, 1330 
		 
		
		Share share = ShareLocalServiceUtil.fetchShare(shareId);
		
		Calendar _cPeriodsMinutesMobileAvg = Calendar.getInstance();
		_cPeriodsMinutesMobileAvg.setTimeInMillis(to.getTime());
		_cPeriodsMinutesMobileAvg.set(Calendar.SECOND, 0);
		_cPeriodsMinutesMobileAvg.set(Calendar.MILLISECOND, 0);
		_cPeriodsMinutesMobileAvg.add(Calendar.SECOND,-1);
		List<String> lAvgMobileDates = new ArrayList<String>();
		
		try 
		{
			for (int j=1;j<=PeriodN;j++)
			{ 
				if (j!=1 || (j==1 && !lastBarIncluded))
				{					
					/* puede ser que la barra este fuera del horario de mercado, controlamos la hora de apertura y fin */				
					_cPeriodsMinutesMobileAvg.add(Calendar.MINUTE,  (int) - (TimeBars)); /* -5, -10, -15,-20 */
					
					Calendar _openMarket;
					Calendar _closeMarket;
					
					String openTrading = "";
					String closeTrading = market.getEnd_hour();
					
					LocalTime  open = null;
					LocalTime close = null;
					
					String  cachedDay =  _cacheFormat.format(_cPeriodsMinutesMobileAvg.getTime());
					
					if (!cachedCloseTimes.containsKey(cachedDay))
					{
							close = Utilities.getPastCloseTradingHours(share, _cPeriodsMinutesMobileAvg.getTime());
							cachedCloseTimes.put(cachedDay, close);
					}
					else
						    close = cachedCloseTimes.get(cachedDay);
					
					LocalDate today = LocalDate.now();
					LocalDate _to = to.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					/* CACHE POR DIA !!!!!!!!!!!!!!!!!!!!!*/ 
					
					if (!cachedOpenTimes.containsKey(cachedDay))
					{
							open =  Utilities.getOpenTradingHour(share, _cPeriodsMinutesMobileAvg.getTime(), TimeBars, simulation); 
							cachedOpenTimes.put(cachedDay, open);
					}
					else
							open = cachedOpenTimes.get(cachedDay);
					
					
					/* no encontramos datos de trading ese dia, close excepto el dia de hoy que no hay  */ 
					if (Validator.isNull(open) || (Validator.isNull(close) && !today.equals(_to))) 
					{
						Date lastTradingBarDay =  null;
						if (!cachedPrecedingTradingDates.containsKey(cachedDay))
						{
							    lastTradingBarDay = Utilities.getBeforeDayTradinng(share, _cPeriodsMinutesMobileAvg.getTime(), simulation);	
							    cachedPrecedingTradingDates.put(cachedDay, lastTradingBarDay);
						}
						else
								lastTradingBarDay = cachedPrecedingTradingDates.get(cachedDay);
						
					    int daysDiff = Utilities.daysDiff(lastTradingBarDay, _cPeriodsMinutesMobileAvg.getTime());
						_cPeriodsMinutesMobileAvg.add(Calendar.DAY_OF_MONTH, -daysDiff);  // buscamos la barra del dia anterior del fin de mercado
						 cachedDay =  _cacheFormat.format(_cPeriodsMinutesMobileAvg.getTime());
						
						if (!cachedCloseTimes.containsKey(cachedDay))
						{
								close = Utilities.getPastCloseTradingHours(share, _cPeriodsMinutesMobileAvg.getTime());
								cachedCloseTimes.put(cachedDay, close);
						}
						else
							    close = cachedCloseTimes.get(cachedDay);
					  //  open =  Utilities.getPastOpenTradingHours(share, _cPeriodsMinutesMobileAvg.getTime(),TimeBars);
					    
					    if (Validator.isNull(close))  break; 
					    
				    	_cPeriodsMinutesMobileAvg.set(Calendar.HOUR_OF_DAY, close.getHour());
				    	_cPeriodsMinutesMobileAvg.set(Calendar.MINUTE, close.getMinute());
				    	_cPeriodsMinutesMobileAvg.add(Calendar.MINUTE, -1);  // -1 para que sea las 59 antes del cierre
					    
	
					}
					else  // hay open y cierre 
					{
						
						openTrading = String.valueOf(open).replace(":","").substring(0,4);
						_openMarket = Utilities.getNewCalendarWithHour(_cPeriodsMinutesMobileAvg.getTime(),openTrading);					
						if (_cPeriodsMinutesMobileAvg.before(_openMarket)) // no podemos pedir una barra de una hora que no hay mercado 
						{
							
							Date lastTradingBarDay =  null;
							if (!cachedPrecedingTradingDates.containsKey(cachedDay))
							{
								    lastTradingBarDay = Utilities.getBeforeDayTradinng(share, _cPeriodsMinutesMobileAvg.getTime(), simulation);	
								    cachedPrecedingTradingDates.put(cachedDay, lastTradingBarDay);
							}
							else
								lastTradingBarDay = cachedPrecedingTradingDates.get(cachedDay);
							
							
							/* controlamos el dia anterior para restar activo , si es lunes, deberia al viernes */
						    int daysDiff = Utilities.daysDiff(lastTradingBarDay, _cPeriodsMinutesMobileAvg.getTime());
							_cPeriodsMinutesMobileAvg.add(Calendar.DAY_OF_MONTH, -daysDiff);  // buscamos la barra del dia anterior del fin de mercado
							cachedDay =  _cacheFormat.format(_cPeriodsMinutesMobileAvg.getTime());

							if (!cachedCloseTimes.containsKey(cachedDay))
							{
									close = Utilities.getPastCloseTradingHours(share, _cPeriodsMinutesMobileAvg.getTime());
									cachedCloseTimes.put(cachedDay, close);
							}
							else
								    close = cachedCloseTimes.get(cachedDay);
							
							closeTrading =  String.valueOf(close).replace(":","").substring(0,4);
							_closeMarket = Utilities.getNewCalendarWithHour(_cPeriodsMinutesMobileAvg.getTime(),closeTrading);
							_cPeriodsMinutesMobileAvg.set(Calendar.HOUR_OF_DAY, _closeMarket.get(Calendar.HOUR_OF_DAY));
							_cPeriodsMinutesMobileAvg.set(Calendar.MINUTE, _closeMarket.get(Calendar.MINUTE)); 
							_cPeriodsMinutesMobileAvg.add(Calendar.MINUTE, -1);  // -1 para que sea las 59 antes del cierre
							
						}
					}
					
					 
				} 
					
				/* NO ES EN LA BARRA 00, 05, 10, SON PRECIOS DE CIRRE DE CADA BARRA, RESTAMOS UN SEGUNDO */
				//			
				lAvgMobileDates.add(_sdf.format(_cPeriodsMinutesMobileAvg.getTime()));			
				_log.trace(_sdfdebug.format(_cPeriodsMinutesMobileAvg.getTime()));
				
			}
		}
		catch (Exception e)
		{
			_log.error(e.getMessage());
		}
		return lAvgMobileDates;
		
	}
	
	
	
	/* HORA DE LA NARRA ACTUAL, PARA CALCULAR LOS N PERIOS PREVIOS 
	 * EN ESTA MEDIA, TENGO DUDAS DE SI COJO LA ULTIMA BARRA CALCULADA , VER EN EL SIMPLEMOBILEAVERAGE  
	 * 
	 * */
	public  static Double getSimpleAvgMobile(Date _ActualDateBar, long TimeBars,long shareId, long companyId, long groupId, long PeriodN, boolean simulation, Market market)
	{
		
		Double returnAvgValue = null;
		List<String> lAvgPeriods = new ArrayList<String>();
		
		Calendar _cSimpleAvgMobileDateFrom = Calendar.getInstance();
		
		try
		{
		
		_cSimpleAvgMobileDateFrom.setTime(_ActualDateBar);
		
		
		lAvgPeriods = getPeriodsMinutesMobileAvg(_ActualDateBar, PeriodN, TimeBars, Boolean.TRUE, market, simulation, shareId);
		
		/* MODIFICACION, MEDIAS MOVILES HASTA EL PERIDO N, N-1, N-2 */
		_cSimpleAvgMobileDateFrom.add(Calendar.MINUTE,  (int) - (PeriodN * TimeBars));
		
		// le resto un segundo para que coga la primera barra especificada, asi coge el 59:59 
		_cSimpleAvgMobileDateFrom.add(Calendar.SECOND,-1);
		
		
		 /* ESTE FROM ESTA RESTADO DE LAS BARRAS POR LOS PERIODOS, PERO NO CONTEMPLA LOS CIERRES Y APERTURAS DE MERCADO */
    	/* SOLUCION, COGER EL FROM DE LA PRIMERA BARRA DE getPeriodsMinutesMobileAvg, QUE SI CONTEMPLA LAS APERTURAS 
    	 * */
		SimpleDateFormat _sdf = new SimpleDateFormat(Utilities.__IBTRADER_SQL_DATE_);
        Date fromWithOpenMarketsTimes=null;
        Calendar cfromWithOpenMarketsTimes = null;
        try {
			 fromWithOpenMarketsTimes = _sdf.parse(lAvgPeriods.get(lAvgPeriods.size()-1));
			 cfromWithOpenMarketsTimes = Calendar.getInstance();
			 cfromWithOpenMarketsTimes.setTimeInMillis(fromWithOpenMarketsTimes.getTime());
			 cfromWithOpenMarketsTimes.add(Calendar.MINUTE, 1);
			 cfromWithOpenMarketsTimes.add(Calendar.SECOND, -1);
			 cfromWithOpenMarketsTimes.set(Calendar.MILLISECOND, 0);
		} catch (ParseException e) {}
		
		
		/* formato 04:54:59*/
			
		TimeSeries series = new BaseTimeSeries("getSimpleAvgMobile");
		series = BarSeriesCacheUtil.closeBarTimeSeries(shareId, companyId, groupId, cfromWithOpenMarketsTimes.getTime(), _ActualDateBar, lAvgPeriods, simulation, TimeBars);
		
		double[] closesprices = new double[(int) PeriodN];		
		_log.debug("Periodos para Media Movil  hasta " + _ActualDateBar);
		int counter = 0;
		for (int   i = (int) (series.getBarCount() - PeriodN) ; i <= series.getBarCount() - 1  ; i++) 
		{
			org.ta4j.core.Bar currentBar = series.getBar(i);		
			closesprices[counter]  = currentBar.getClosePrice().doubleValue();
			_log.debug(closesprices[counter]);
			counter++;			
		}
		IBTraderSMAIndicador SMA = new IBTraderSMAIndicador();
		SMA.calculate(closesprices, (int) PeriodN-1);
		returnAvgValue = SMA.getSMA()[(int) PeriodN-1];
    	_log.debug("Calculting SMA  from " + cfromWithOpenMarketsTimes.getTime() + " until :" + _ActualDateBar + ",shareId:" + shareId);
    	
    	if (_log.isDebugEnabled())
    	{
    		for (int j=0;j<PeriodN;j++)
    		{ 
    			try 
    			{
    				_log.trace("getSimpleAvgMobile: 9 " + j + ":" + SMA.getSMA()[j]+ "closeprice:" + closesprices[j]);}
    				//System.out.print(closesprices[j] + ","); 
    			catch (Exception e) {}	
    		}
    	}
    	
		}
		catch (Exception e)
		{
			_log.debug(e.getMessage());
		}
        
		
		/* if (!simulation)
		{
			Realtime simpleAvgMobile = RealtimeLocalServiceUtil.findSimpleMobileAvgGroupByPeriods(shareId, companyId, groupId, cfromWithOpenMarketsTimes.getTime(), _ActualDateBar, lAvgPeriods);
			returnAvgValue =  simpleAvgMobile!=null && simpleAvgMobile.getVolume()==lAvgPeriods.size() ?  simpleAvgMobile.getValue() : null;
		}
		else
		{
			HistoricalRealtime simpleHistoricalAvgMobile =  HistoricalRealtimeLocalServiceUtil.findSimpleMobileAvgGroupByPeriods(shareId, companyId, groupId, cfromWithOpenMarketsTimes.getTime(), _ActualDateBar, lAvgPeriods);
			returnAvgValue =  simpleHistoricalAvgMobile!=null && simpleHistoricalAvgMobile.getVolume()==lAvgPeriods.size() ?  simpleHistoricalAvgMobile.getValue() : null;
		}*/
		
		return returnAvgValue;
				
		
	}
	
	/* HORA DE LA NARRA ACTUAL, PARA CALCULAR LOS N PERIOS PREVIOS 
	 * COGEMOS LA ULTIMA BARRA INCLUIDA!! 
	 * 
	 * expAvgMobile = (CLOSE (i) * P) + (EMA (i - 1) * (1 - P))
	 *  P = 2/(1+PERIODOS)
	 * 
	 *  Step 1. Let’s say that we want to calculate the 12-day EMA of Exxon Mobil’s stock price. We first need to get historic stock prices – you can do that with this bulk stock quote downloader.

		Step 2. Calculate the simple average of the first 12 prices with Excel’s Average() function. In the screengrab below, in cell C16 we have the formula =AVERAGE(B5:B16) where B5:B16 contains the first 12 close prices

		Step 3. Just below the cell used in Step 2, enter the EMA formula above
	 * 
	 * 
	 * 
	 * 
	 * Since EMA’s use the prior value in their calculation, 
	 * the values depend on how many prior values you have. Related, there is some question 
	 * as to how to initialize the first value of an EMA. The first problem is generally 
	 * solved by including at least 200 Bars of TimeSeries data prior to the indices 
	 * for which you are interested. 
	 * The second problem is currently solved by setting the first EMA value to the first data value:
	 * 
	 * */
	public  static Double getExponentialAvgMobile(Date _ActualDateBar, double closeprice,  long TimeBars,long shareId, long companyId, 
			long groupId, long PeriodN, boolean simulation, Market market)
	{
		
		
		/* Calendar cAvgExp = Calendar.getInstance();
		cAvgExp.setTimeInMillis(_ActualDateBar.getTime());
		cAvgExp.add(Calendar.MINUTE, (int) -TimeBars);*/
		
		Double exponentialAvgMobile = null;
		try
		{		
		List<String> lExponentialAvgPeriods = new ArrayList<String>();

		Calendar _cExponentialAvgMobile = Calendar.getInstance();
		_cExponentialAvgMobile.setTime(_ActualDateBar);
		int periodsToCalculate =   ConfigKeys.INDICATORS_MIN_SERIE_COUNT;
		lExponentialAvgPeriods = getPeriodsMinutesMobileAvg(_ActualDateBar, periodsToCalculate , TimeBars, Boolean.TRUE, market, simulation, shareId);
		SimpleDateFormat _sdf = new SimpleDateFormat(Utilities.__IBTRADER_SQL_DATE_);
        Date fromWithOpenMarketsTimes=null;
        Calendar cfromWithOpenMarketsTimes = null;
        try {
			 fromWithOpenMarketsTimes = _sdf.parse(lExponentialAvgPeriods.get(lExponentialAvgPeriods.size()-1));
			 cfromWithOpenMarketsTimes = Calendar.getInstance();
			 cfromWithOpenMarketsTimes.setTimeInMillis(fromWithOpenMarketsTimes.getTime());
			 cfromWithOpenMarketsTimes.add(Calendar.MINUTE, 1);
			 cfromWithOpenMarketsTimes.add(Calendar.SECOND, -1);  // tiene que ser en .59 
			 cfromWithOpenMarketsTimes.set(Calendar.MILLISECOND, 0);
		} catch (ParseException e) {}
		
		TimeSeries series = new BaseTimeSeries("getExponentialAvgMobile");
		series = BarSeriesCacheUtil.closeBarTimeSeries(shareId, companyId, groupId, cfromWithOpenMarketsTimes.getTime(), _ActualDateBar, lExponentialAvgPeriods, simulation, TimeBars);
		
		double[] closesprices = new double[(int) PeriodN];		
		_log.debug("Periodos para Media Movil Exponencial hasta " + _ActualDateBar);
		int counter = 0;
		for (int   i = (int) (series.getBarCount() - PeriodN) ; i <= series.getBarCount() - 1  ; i++) 
		//for (int counter=0;counter<periodsToCalculate;counter++)		
		{
			org.ta4j.core.Bar currentBar = series.getBar(i);		
			closesprices[counter]  = currentBar.getClosePrice().doubleValue();
			_log.debug(closesprices[counter]);
			counter++;			
		}
		IBTraderEMAIndicator EMA = new IBTraderEMAIndicator();	
    	EMA.calculate(closesprices, (int) PeriodN-1);
    	exponentialAvgMobile = EMA.getEMA()[(int) PeriodN-1]; 
    	_log.debug("Calculting ExpAvg  from " + cfromWithOpenMarketsTimes.getTime() + " until :" + _ActualDateBar + ",shareId:" + shareId);
    
    	if (_log.isDebugEnabled())
    	{
    		for (int j=0;j<periodsToCalculate;j++)
    		{ 
    			try 
    			{
    				_log.trace("getExponentialAvgMobile 26:" + j + ":" + EMA.getEMA()[j] + "closeprice:" + closesprices[j]);
    				_log.trace("getExponentialAvgMobile: 9 " + j + ":" + EMA.getEMA()[j]+ "closeprice:" + closesprices[j]);}
    				//System.out.print(closesprices[j] + ","); 
    			catch (Exception e) {}	
    		}
    	}
        }        	
	    catch (Exception e)
		{
			_log.debug(e.getMessage());
		}
		return  exponentialAvgMobile;

		
	}
	
	private static MACDIndicator  getMACDIndicator(Date _ActualDateBar,  long TimeBars,long shareId, long companyId, long groupId, long shortMACDPeriods, long longMACDPeriods, boolean simulation, 
			Market market)
	{
		MACDIndicator _macdIndicator = null;
		try
		{		
		List<String> lMACDPeriods = new ArrayList<String>();
		Calendar _cMACDDateFrom = Calendar.getInstance();
		_cMACDDateFrom.setTime(_ActualDateBar);
		
		int periodsToCalculate = ConfigKeys.INDICATORS_MIN_SERIE_COUNT;
		
		lMACDPeriods = getPeriodsMinutesMobileAvg(_ActualDateBar, periodsToCalculate , TimeBars, Boolean.TRUE, market, simulation, shareId);
		
		/* MODIFICACION, MEDIAS MOVILES HASTA EL PERIDO N, N-1, N-2 */
		_cMACDDateFrom.add(Calendar.MINUTE,  (int) - ((periodsToCalculate) * TimeBars));
		_cMACDDateFrom.set(Calendar.MILLISECOND,0);	

		// le resto un segundo para que coga la primera barra especificada, asi coge el 59:59 
		_cMACDDateFrom.add(Calendar.SECOND,-1);
		

        /* ESTE FROM ESTA RESTADO DE LAS BARRAS POR LOS PERIODOS, PERO NO CONTEMPLA LOS CIERRES Y APERTURAS DE MERCADO */
    	/* SOLUCION, COGER EL FROM DE LA PRIMERA BARRA DE getPeriodsMinutesMobileAvg, QUE SI CONTEMPLA LAS APERTURAS */
        SimpleDateFormat _sdf = new SimpleDateFormat(Utilities.__IBTRADER_SQL_DATE_);
        Date fromWithOpenMarketsTimes=null;
        Calendar cfromWithOpenMarketsTimes = null;
        try {
			 fromWithOpenMarketsTimes = _sdf.parse(lMACDPeriods.get(lMACDPeriods.size()-1));
			 cfromWithOpenMarketsTimes = Calendar.getInstance();
			 cfromWithOpenMarketsTimes.setTimeInMillis(fromWithOpenMarketsTimes.getTime());
			 cfromWithOpenMarketsTimes.add(Calendar.MINUTE, 1);
			 cfromWithOpenMarketsTimes.add(Calendar.SECOND, -1);
			 cfromWithOpenMarketsTimes.set(Calendar.MILLISECOND, 0);
		} catch (ParseException e) {}
		
		
				
		TimeSeries series = new BaseTimeSeries("getMACD");
		series = BarSeriesCacheUtil.closeBarTimeSeries(shareId, companyId, groupId, cfromWithOpenMarketsTimes.getTime(), _ActualDateBar, lMACDPeriods, simulation, TimeBars);
		
        ClosePriceIndicator closePrice = new ClosePriceIndicator(series);
        _macdIndicator =  new MACDIndicator(closePrice, Long.valueOf(shortMACDPeriods).intValue(), Long.valueOf(longMACDPeriods).intValue());
        if (_log.isDebugEnabled())
        {
			for (int j=0;j<periodsToCalculate;j++)
			{ 
				try {_log.debug("getMACDIndicator:" + j + ":" +  _macdIndicator.getValue(j).doubleValue());} catch (Exception e) {}	
			}
        }
		}
        catch (Exception e)
		{
			_log.debug(e.getMessage());
		}
		return _macdIndicator;
		

	}
	
	
	private static StochasticOscillatorDIndicator  getDStochasticDIndicator(Date _ActualDateBar,  long TimeBars,long shareId, long companyId, long groupId, 
				long periods, boolean simulation,  Market market)
	{
		StochasticOscillatorKIndicator _stochasticOscillatorKIndicator = null;
		StochasticOscillatorDIndicator _stochasticOscillatorDIndicator = null;

		try
		{		
		List<String> lStochasticOscillatorDPeriods = new ArrayList<String>();
		Calendar _cStochasticOscillatorDDateFrom = Calendar.getInstance();
		_cStochasticOscillatorDDateFrom.setTime(_ActualDateBar);
		
	//	int periodsToCalculate = ConfigKeys.INDICATORS_MIN_SERIE_COUNT;
		long  periodsToCalculate = periods;
		
		lStochasticOscillatorDPeriods = getPeriodsMinutesMobileAvg(_ActualDateBar, periodsToCalculate , TimeBars, Boolean.TRUE, market, simulation, shareId);
		
		/* MODIFICACION, MEDIAS MOVILES HASTA EL PERIDO N, N-1, N-2 */
		_cStochasticOscillatorDDateFrom.add(Calendar.MINUTE,  (int) - ((periodsToCalculate) * TimeBars));
		_cStochasticOscillatorDDateFrom.set(Calendar.MILLISECOND,0);	

		// le resto un segundo para que coga la primera barra especificada, asi coge el 59:59 
		_cStochasticOscillatorDDateFrom.add(Calendar.SECOND,-1);
		

        /* ESTE FROM ESTA RESTADO DE LAS BARRAS POR LOS PERIODOS, PERO NO CONTEMPLA LOS CIERRES Y APERTURAS DE MERCADO */
    	/* SOLUCION, COGER EL FROM DE LA PRIMERA BARRA DE getPeriodsMinutesMobileAvg, QUE SI CONTEMPLA LAS APERTURAS */
        SimpleDateFormat _sdf = new SimpleDateFormat(Utilities.__IBTRADER_SQL_DATE_);
        Date fromWithOpenMarketsTimes=null;
        Calendar cfromWithOpenMarketsTimes = null;
        try {
			 fromWithOpenMarketsTimes = _sdf.parse(lStochasticOscillatorDPeriods.get(lStochasticOscillatorDPeriods.size()-1));
			 cfromWithOpenMarketsTimes = Calendar.getInstance();
			 cfromWithOpenMarketsTimes.setTimeInMillis(fromWithOpenMarketsTimes.getTime());
			 cfromWithOpenMarketsTimes.add(Calendar.MINUTE, 1);
			 cfromWithOpenMarketsTimes.add(Calendar.SECOND, -1);
			 cfromWithOpenMarketsTimes.set(Calendar.MILLISECOND, 0);
		} catch (ParseException e) {}
		
		
				
		TimeSeries series = new BaseTimeSeries("getStochasticOscillatorD");
		
		/* el calculo del estocastico requiere los min max y cieres */
		series = BarSeriesCacheUtil.closeMinMaxBarTimeSeries(shareId, companyId, groupId, _ActualDateBar, simulation, TimeBars,periodsToCalculate, lStochasticOscillatorDPeriods);
		
		//series = BarSeriesCacheUtil.closeBarTimeSeries(shareId, companyId, groupId, cfromWithOpenMarketsTimes.getTime(), _ActualDateBar, lStochasticOscillatorDPeriods, simulation, TimeBars);
	     
		
		/* 
		 * 
		 * Oscilador estocástico rápido
				%K rápido = %K calculado con la fórmula original de George Lane.
				%D rápido = media móvil simple del %K rápido.
		   Oscilador estocástico lento
				%K lento = %K rápido suavizado por medio de una media móvil simple de 3 periodos.
				%D lento = media móvil de 3 periodos del %K lento.
			Oscilador estocástico total
				%K total: %K rápido suavizado por medio de una media móvil simple de X periodos.
				%D total: media móvil simple de X periodos del %K total.
				
				e acuerdo al mismo Lane, las divergencias del %D son las únicas señales realmente fiables que pueden utilizarse para realizar una compra o venta en el mercado.
				
		 */
		
		_stochasticOscillatorKIndicator =  new StochasticOscillatorKIndicator(series, Long.valueOf(periods).intValue());
		_stochasticOscillatorDIndicator =  new StochasticOscillatorDIndicator(_stochasticOscillatorKIndicator);
        if (_log.isDebugEnabled())
        {
			for (int j=0;j<periodsToCalculate;j++)
			{ 
				try {
						_log.trace("BarTime: " + _ActualDateBar + " _stochasticOscillatorDIndicator:" + j + ":" +  _stochasticOscillatorDIndicator.getValue(j).doubleValue());						
					} 
				catch 
					(Exception e) 
				{
					_log.debug(e.getMessage());	
				}	
			}
			for (int j=0;j<periodsToCalculate;j++)
			{ 
				try {
						_log.trace("series.addBar(new BaseBar(endTime.plusMinutes("  +  j  + "),0.0," + series.getBar(j).getMaxPrice() + "," +  series.getBar(j).getMinPrice() + "," +
								series.getBar(j).getClosePrice() + ",0.0))");
						
						
					} 
				catch 
					(Exception e) 
				{
					_log.debug(e.getMessage());	
				}	
			}
        }
		}
        catch (Exception e)
		{
			_log.debug(e.getMessage());
		}
		return _stochasticOscillatorDIndicator;
		

	}
	
	public  static Double getDStochastic(Date _ActualDateBar,  long TimeBars, long shareId, long companyId, long groupId, long periods, 
					boolean simulation, Market market)
	{
		double stochastic = 0d;
		try
		{		
			
			long result_index = periods-1;
			StochasticOscillatorDIndicator  DStochasticIndicator = getDStochasticDIndicator(_ActualDateBar, TimeBars , shareId, companyId, groupId, periods,simulation, market);
			_log.debug("DStochasticIndicator:" +  DStochasticIndicator.getValue(Long.valueOf(result_index).intValue()));			
			stochastic =  DStochasticIndicator.getValue(Long.valueOf(result_index).intValue()).doubleValue();
		}
		catch (Exception e)
		{
			_log.debug(e.getMessage());
		}
		return stochastic;
				
		
	}
	
	/* COMO OPERAMOS EN GRAFICAS DE X MINUTOS, COGEMOS EL PIVOT POINT DE ESA BARRA , NO CONTEMPLAMOS PIVOTS MENSUALES DIARIOS O SEMANALES 
	 * 
	 *  Los pivot points que de verdad funcionan
			Hay dos niveles que sí son relevantes de verdad, mucho más que los pivot points de arriba, y que la inmensa mayoría de los traders vigilan atentamente (intradiarios, pero también de marcos temporales superiores).

			Se trata del máximo y del mínimo de la vela (diaria) anterior.

			Olvida la apertura y el cierre. Sólo quieres los dos extremos.
	 * 
	 * 
	 * 
	 * 
	 *
	 * 
	 *  https://www.bolsaytrading.com/2014/09/pivot-points-que-son-como-se-calculan-como-se-hace-trading-con-ellos.html
	 *  
	 *  Una vez tenemos los pivots calculados con datos diarios hay que bajar la temporalidad a nuestro marco temporal 
	 *  preferido para hacer trading, en mi caso 5 minutos. Por tanto tomaré decisiones de compra o venta en 5 minutos con los pivot points calculados en gráfico diario.
	 *  
	 * 
	 * PENDIENTE, PROBAR EL PIVOT POINT ANTERIOR DE 5 MINUTOS , DE MOMENTO LO CALCULAMOS DIARIO 
	 * */
	
	
	
	/* EL DE LA SESION PREVIA */
	public   static IBTraderPivotPointIndicator getSessionPivotPointIndicator(Date _ActualDateBar,  long TimeBars, Share share,  boolean simulation, Market market)
	{
		return  getPivotPointIndicator(_ActualDateBar, TimeBars, share, simulation, market, Boolean.FALSE);
	}
	/* EL DE LA BARRA PREVIA */
	public   static IBTraderPivotPointIndicator getBarPivotPointIndicator(Date _ActualDateBar,  long TimeBars, Share share,  boolean simulation, Market market)
	{
		return getPivotPointIndicator(_ActualDateBar, TimeBars, share, simulation, market, Boolean.TRUE);
	}
	
	/* barValue : TRUE --> LA BARRA ANTERIOR */
	/* barValue : FALSE -->  CIERRE DE LA SESSION */
	private  static IBTraderPivotPointIndicator getPivotPointIndicator(Date _ActualDateBar,  long TimeBars, Share share,  boolean simulation, Market market, boolean barValue)
	{
		IBTraderPivotPointIndicator retPivotIBTraderIndicador = null;

		try
		{		
		
			
		/* fecha actual o de simulacion */	
			
		/* DIA ANTERIOR INICIO DE MERCADO Y FIN */	
		Calendar closeDate = Calendar.getInstance();		
		Calendar startDate = Calendar.getInstance();
		HistoricalRealtime _closeHPrice;
		Realtime _closeRPrice;		
		Realtime MiMaxSession;
		HistoricalRealtime MiMaxHSession; 
		
		double closingPrice = 0d;
		double maxPrice = 0d;
		double minPrice = 0d;
		
		Date lastTradingBarDay = null; 
		
		closeDate.setTime(_ActualDateBar); // simulacion o no , daria igual 
		/* el dia anterior puede ser un domingo que no hay mercado, realmente el dia a considerar es el anterior con mercado abierto */
		
		if (!barValue) // valor de la ultima session 
		{	
		
			closeDate.add(Calendar.DATE, -1);
			/* closeDate.set(Calendar.HOUR_OF_DAY, 23);
			closeDate.set(Calendar.MINUTE, 59);
			closeDate.set(Calendar.SECOND, 59);
			closeDate.set(Calendar.MILLISECOND, 59);*/	
			
			lastTradingBarDay = Utilities.getBeforeDayTradinng(share, closeDate.getTime(), simulation); 
			 
			if (Validator.isNull(lastTradingBarDay))
			{			 
				_log.trace("No se encuuentra la fecha anterior del dia de trading ");
				return retPivotIBTraderIndicador;
			}
			
			closeDate.setTime(lastTradingBarDay);
			
			/*startDate.setTime(closeDate.getTime());
			 startDate.set(Calendar.HOUR_OF_DAY, 0);
			startDate.set(Calendar.MINUTE, 0);
			startDate.set(Calendar.SECOND, 0);
			startDate.set(Calendar.MILLISECOND, 0);*/
			
			_closeHPrice = HistoricalRealtimeLocalServiceUtil.findCloseRealTime(share.getShareId(), share.getCompanyId() , share.getGroupId(), closeDate.getTime());	
			closingPrice = Validator.isNotNull(_closeHPrice) ? _closeHPrice.getValue() : 0;		 
			
		}
		else  // criterio de la ultima barra 
		{
			closeDate.add(Calendar.MINUTE,  -  (int) TimeBars);
			if  (!simulation)
			{
				_closeRPrice = RealtimeLocalServiceUtil.findLastRealTimeLessThanDate(share.getShareId(), share.getCompanyId() , share.getGroupId(), closeDate.getTime());
				closingPrice = Validator.isNotNull(_closeRPrice) ? _closeRPrice.getValue() : 0;		 

			}
			else
			{
				_closeHPrice = HistoricalRealtimeLocalServiceUtil.findLastRealTimeLessThanDate(share.getShareId(), share.getCompanyId() , share.getGroupId(), closeDate.getTime());
				closingPrice = Validator.isNotNull(_closeHPrice) ? _closeHPrice.getValue() : 0;		 
			}
			

		}
		
		/* CLOSE TIMES  NO SE BORRAN DE LA TABLA REALTIME 		
		 * */
		
		/* me indica que dia fue el anterior con operativa, ya que no hay forma de terlo en las tradinghours, resuelve el dia anterior de los lunes  
		 *   ultima barra del dia anterior */
		
		
		if (closingPrice==0)
		{			 
			_log.trace("No se encuuentra la closingPrice ");
			return retPivotIBTraderIndicador;
		}
		
		/* BUSCAMOS LOS MAXIMOS  Y MINIMOS */
		
		Calendar closeLastBar = Calendar.getInstance();			 
		if  (!simulation)
		{
			
			 /* hay que coger la ultima barra para coger el maximo y minimo  
			  DE MOMENTO LO CALCULAMOS DIARIO  */		
			 closeLastBar.setTime(closeDate.getTime()); 	
			 if (barValue) // modo ultima barra para sacar el pivot point 
			 {
				 startDate.setTime(closeLastBar.getTime());
				 closeDate.add(Calendar.MINUTE,  -  (int) TimeBars);
			 }
			 else   // modo pivot points de la ultima sesion 
			 {
				 LocalTime  closeTrading  = Utilities.getUTCTodayCloseTradingHours(share.getTrading_hours()); // 1500
				 if (Validator.isNull(closeTrading)) 
				 {
					 _log.trace("No se encuuentra el closeTrading ");
						return retPivotIBTraderIndicador;
				 }
				 closeLastBar.set(Calendar.HOUR_OF_DAY, closeTrading.getHour());
				 closeLastBar.set(Calendar.MINUTE, closeTrading.getMinute()); 
				 closeLastBar.set(Calendar.SECOND, 0);			 
				 startDate.setTime(closeLastBar.getTime());
				 startDate.set(Calendar.HOUR_OF_DAY, 0);
				 startDate.set(Calendar.MINUTE, 0);
				 
			 }
			 MiMaxSession = RealtimeLocalServiceUtil.findMinMaxRealTime(startDate.getTime(), closeLastBar.getTime(), share.getShareId(), share.getCompanyId(), share.getGroupId());
			/*  Se trata del máximo y del mínimo de la vela (diaria) anterior. */
			 maxPrice = Validator.isNotNull(MiMaxSession) ? MiMaxSession.getMax_value(): 0;
			 minPrice = Validator.isNotNull(MiMaxSession) ? MiMaxSession.getMin_value() : 0;
	 	 /* cogemos el cierre de hoy para saber la ultima barra del dia anterior */
		 
		 
		 /* INICIO DE LA PRIMERA BARRA 
		 startDate.add(Calendar.MINUTE, - Long.valueOf(TimeBars).intValue());*/
		 /*DE MOMENTO LO CALCULAMOS DIARIO  */
		} // if  (!simulation)		
		else
		{
			/* NO TENGO CLOSE DATE, ME FIO DE LA ULTIMA NARRA , SON DE ESTE ESTILO */	
			/* 2019-09-21 00:59:59.000000
			2019-09-21 00:59:58.000000
			2019-09-21 00:54:59.000000 */
			if (!barValue) // modo max y min de la sesion anterior  pivot point
			{
				closeLastBar.setTime(lastTradingBarDay); // ultimo historical data
				closeLastBar.add(Calendar.SECOND, 1);
				startDate.setTime(closeLastBar.getTime());
				startDate.set(Calendar.HOUR_OF_DAY, 0);
				startDate.set(Calendar.MINUTE, 0);
			}
			else
			{
				/* BUSCAMOS LA BARRA INDEMIATAMENTE ANTERIOR */
				closeLastBar.setTime(closeDate.getTime()); // ultimo historical data		
				startDate.setTime(closeDate.getTime()); // ultimo historical data);
				startDate.add(Calendar.MINUTE,  -  (int) TimeBars);				
			}
			
			/* startDate.add(Calendar.MINUTE, - Long.valueOf(TimeBars).intValue()); */
			/* >=startDate  y <endDte */
			 /*DE MOMENTO LO CALCULAMOS DIARIO  */
			 			
			MiMaxHSession = HistoricalRealtimeLocalServiceUtil.findMinMaxRealTime(startDate.getTime(), closeLastBar.getTime(), share.getShareId(), share.getCompanyId(), share.getGroupId());
			/*  Se trata del máximo y del mínimo de la vela (diaria) anterior. */
			maxPrice = Validator.isNotNull(MiMaxHSession) ? MiMaxHSession.getMax_value():  0;
			minPrice = Validator.isNotNull(MiMaxHSession) ? MiMaxHSession.getMin_value() : 0;
			
			
		}
	
		
		if (closingPrice>0 && maxPrice>0 && minPrice>0 && maxPrice!=minPrice)
			
		{
		
			TimeSeries PivotPointDseries = new BaseTimeSeries("getPivotPointD");
			
			
			PivotPointDseries.addBar(new BaseBar(ZonedDateTime.now(),0.0,  maxPrice, minPrice, closingPrice, 0.0));
			
			_log.debug("Vela PP de " + (barValue ?   "barra previa " : " sesión dia anterior ")  + closeLastBar.getTime() + ",max:" +  maxPrice + ",min:" + minPrice + ",close:" + closingPrice);
			
			PivotPointIndicator _pivotPointIndicator  = new PivotPointIndicator(PivotPointDseries, TimeLevel.BARBASED);
			StandardReversalIndicator _standardReversalIndicatorR1 = new StandardReversalIndicator(_pivotPointIndicator, PivotLevel.RESISTANCE_1);
			StandardReversalIndicator _standardReversalIndicatorR2 = new StandardReversalIndicator(_pivotPointIndicator, PivotLevel.RESISTANCE_2);
			StandardReversalIndicator _standardReversalIndicatorR3 = new StandardReversalIndicator(_pivotPointIndicator, PivotLevel.RESISTANCE_3);
			StandardReversalIndicator _standardReversalIndicatorS1 = new StandardReversalIndicator(_pivotPointIndicator, PivotLevel.SUPPORT_1);
			StandardReversalIndicator _standardReversalIndicatorS2 = new StandardReversalIndicator(_pivotPointIndicator, PivotLevel.SUPPORT_2);
			StandardReversalIndicator _standardReversalIndicatorS3 = new StandardReversalIndicator(_pivotPointIndicator, PivotLevel.SUPPORT_3);		
			
			if (Validator.isNotNull(_pivotPointIndicator) &&  Validator.isNotNull(_standardReversalIndicatorR1)  &&  Validator.isNotNull(_standardReversalIndicatorR2) 
					 &&  Validator.isNotNull(_standardReversalIndicatorR3)   &&  Validator.isNotNull(_standardReversalIndicatorS1)  
					 	&&  Validator.isNotNull(_standardReversalIndicatorS2) &&  Validator.isNotNull(_standardReversalIndicatorS3)) 
			
			{
				
				retPivotIBTraderIndicador = new IBTraderPivotPointIndicator(_pivotPointIndicator.getValue(0).doubleValue(), _standardReversalIndicatorR1.getValue(0).doubleValue(),
						_standardReversalIndicatorR2.getValue(0).doubleValue(),
						_standardReversalIndicatorR3.getValue(0).doubleValue(),_standardReversalIndicatorS1.getValue(0).doubleValue(), 
							_standardReversalIndicatorS2.getValue(0).doubleValue(), _standardReversalIndicatorS3.getValue(0).doubleValue());
				
			}
			
	        if (_log.isDebugEnabled())
	        {
				try {
					_log.debug("_PivotPointDIndicator::" +  _pivotPointIndicator.getValue(0).doubleValue());
					_log.debug("_standardReversalIndicatorR1::" +  _standardReversalIndicatorR1.getValue(0).doubleValue());
					_log.debug("_standardReversalIndicatorR2::" +  _standardReversalIndicatorR2.getValue(0).doubleValue());
					_log.debug("_standardReversalIndicatorR3::" +  _standardReversalIndicatorR3.getValue(0).doubleValue());
					_log.debug("_standardReversalIndicatorS1::" +  _standardReversalIndicatorS1.getValue(0).doubleValue());
					_log.debug("_standardReversalIndicatorS2::" +  _standardReversalIndicatorS2.getValue(0).doubleValue());
					_log.debug("_standardReversalIndicatorS3::" +  _standardReversalIndicatorS3.getValue(0).doubleValue());
					
				} 
				
				catch (Exception e) {}	
				
	        }
		}
		}
        catch (Exception e)
		{
			_log.debug(e.getMessage());
		}
		return retPivotIBTraderIndicador;
				
		
	}
	
	
	public  static Double getMACD(Date _ActualDateBar,  long TimeBars,long shareId, long companyId, long groupId, long shortMACDPeriods, long longMACDPeriods, boolean simulation, Market market)
	{
		double MACD = 0d;
		try
		{		
			MACDIndicator  MACDIndicator = getMACDIndicator(_ActualDateBar, TimeBars , shareId, companyId, groupId,shortMACDPeriods,longMACDPeriods,simulation, market);
			_log.debug(MACDIndicator.getValue(Long.valueOf(ConfigKeys.INDICATORS_MIN_SERIE_COUNT-1).intValue()));			
			MACD =  MACDIndicator.getValue(Long.valueOf(ConfigKeys.INDICATORS_MIN_SERIE_COUNT-1).intValue()).doubleValue();
		}
		catch (Exception e)
		{
			_log.debug(e.getMessage());
		}
		return MACD;
				
		
	}
	
	public  static Double getMACDPrevious(Date _ActualDateBar,  long TimeBars,long shareId, long companyId, long groupId, long shortMACDPeriods, long longMACDPeriods, boolean simulation, Market market)
	{
		double MACD = 0d;
		try
		{
		
			MACDIndicator  MACDIndicator = getMACDIndicator(_ActualDateBar, TimeBars , shareId, companyId, groupId, shortMACDPeriods, longMACDPeriods,simulation, market);
			_log.debug(MACDIndicator.getValue(Long.valueOf(ConfigKeys.INDICATORS_MIN_SERIE_COUNT-2).intValue()));			
			MACD = MACDIndicator.getValue(Long.valueOf(ConfigKeys.INDICATORS_MIN_SERIE_COUNT-2).intValue()).doubleValue();
		}
		catch (Exception e)
		{
			_log.debug(e.getMessage());
		}
        
		return MACD;
		
	}
	
	
	/* SERIES DE MACD DE 9, APLICAS UNA MEDIA EXPONENECIAL */
	public  static Double getMACDSignal(Date _ActualDateBar,  long TimeBars,long shareId, long companyId, long groupId, long shortMACDPeriods, 
			long longMACDPeriods, long signalMACDPeriods , boolean simulation, Market market)
	{
		
		/* macd 26, 0..26*/
		double MACDSignal = 0d;
		try
		{
		
			MACDIndicator  MACDIndicator = getMACDIndicator(_ActualDateBar, TimeBars , shareId, companyId, groupId, shortMACDPeriods, longMACDPeriods, simulation, market);
			TimeSeries series = new BaseTimeSeries("getMACDSignal");
	        ZonedDateTime endTime = ZonedDateTime.now();        
	        for (int j=0;j<ConfigKeys.INDICATORS_MIN_SERIE_COUNT;j++)
	        {
	        	series.addBar(new BaseBar(endTime.plusMinutes(TimeBars*(j+1)),0.0,0.0,0.0, MACDIndicator.getValue(j).doubleValue(),0.0));        	
	        }
			
	        ClosePriceIndicator closePrice = new ClosePriceIndicator(series); 
	        EMAIndicator macd_emea = new EMAIndicator(closePrice, (int) signalMACDPeriods);
	        
	
	        if (_log.isDebugEnabled())
	        {
		        for (int j=0;j<ConfigKeys.INDICATORS_MIN_SERIE_COUNT;j++)
				{ 
					try {_log.debug("getMACDSignal:" + j + ":" +  macd_emea.getValue(j).doubleValue());} catch (Exception e) {}	
				}
	        }
	        MACDSignal =  macd_emea.getValue((int) ConfigKeys.INDICATORS_MIN_SERIE_COUNT-1).doubleValue();
		}
		catch (Exception e)
		{
			_log.debug(e.getMessage());
		}
		return MACDSignal;
		
				
		
	}
	/* SERIES DE MACD DE 9, APLICAS UNA MEDIA EXPONENECIAL */
	public  static Double getMACDSignalPrevious(Date _ActualDateBar,  long TimeBars,long shareId, long companyId, long groupId, long shortMACDPeriods, 
			long longMACDPeriods, long signalMACDPeriods, boolean simulation, Market market)
	{
		
		/* macd 26, 0..26*/
		double MACDSignal = 0d;
		try
		{
		
		MACDIndicator  MACDIndicator = getMACDIndicator(_ActualDateBar, TimeBars , shareId, companyId, groupId, shortMACDPeriods, longMACDPeriods, simulation, market);
		TimeSeries series = new BaseTimeSeries("getMACDSignal");
        ZonedDateTime endTime = ZonedDateTime.now();        
        for (int j=0;j<ConfigKeys.INDICATORS_MIN_SERIE_COUNT;j++)
        {
        	series.addBar(new BaseBar(endTime.plusMinutes(TimeBars*(j+1)),0.0,0.0,0.0, MACDIndicator.getValue(j).doubleValue(),0.0));        	
        }
		
        ClosePriceIndicator closePrice = new ClosePriceIndicator(series); 
        EMAIndicator macd_emea = new EMAIndicator(closePrice, (int) signalMACDPeriods);
        

        if (_log.isDebugEnabled())
        {
	        for (int j=0;j<ConfigKeys.INDICATORS_MIN_SERIE_COUNT;j++)
			{ 
				try {_log.debug("getMACDSignal:" + j + ":" +  macd_emea.getValue(j).doubleValue());} catch (Exception e) {}	
			}
        }
        MACDSignal =  macd_emea.getValue((int) ConfigKeys.INDICATORS_MIN_SERIE_COUNT-2).doubleValue();
		}
		catch (Exception e)
		{
			_log.debug(e.getMessage());
		}
		return MACDSignal;
		
		
		
	}
	
	/* >= y <= */
	
	public static Realtime getMinMaxBarFromShare( Calendar _To, long TimeBars,long PeriodN, long shareId, long companyId, long groupId)
	{
		Calendar _TimeBarWidthFrom = Calendar.getInstance(); 
		_TimeBarWidthFrom.setTimeInMillis(_To.getTimeInMillis());		
		_TimeBarWidthFrom.add(Calendar.MILLISECOND, 0);

		Calendar _TimeBarWidthTo = Calendar.getInstance();
		_TimeBarWidthTo.setTimeInMillis(_To.getTimeInMillis());							 
		_TimeBarWidthFrom.add(Calendar.MINUTE, (int) -((PeriodN+1) * TimeBars));
		_TimeBarWidthTo.add(Calendar.MINUTE, (int) -(PeriodN * TimeBars));   // 20150228 errores en el concepto ini fin de barra

		Realtime oRTimeWidthRange = RealtimeLocalServiceUtil.findMinMaxRealTime(_TimeBarWidthFrom.getTime(), _TimeBarWidthTo.getTime(), shareId, companyId, groupId);	
		
		return oRTimeWidthRange;
	}
	
	public static HistoricalRealtime getHistoricalMinMaxBarFromShare( Calendar _To, long TimeBars,long PeriodN, long shareId, long companyId, long groupId)
	{
		Calendar _TimeBarWidthFrom = Calendar.getInstance(); 
		_TimeBarWidthFrom.setTimeInMillis(_To.getTimeInMillis());		
		_TimeBarWidthFrom.add(Calendar.MILLISECOND, 0);

		Calendar _TimeBarWidthTo = Calendar.getInstance();
		_TimeBarWidthTo.setTimeInMillis(_To.getTimeInMillis());							 
		_TimeBarWidthFrom.add(Calendar.MINUTE, (int) -((PeriodN+1) * TimeBars));
		_TimeBarWidthTo.add(Calendar.MINUTE, (int) -(PeriodN * TimeBars));   // 20150228 errores en el concepto ini fin de barra

		HistoricalRealtime oRTimeWidthRange = HistoricalRealtimeLocalServiceUtil.findMinMaxRealTime(_TimeBarWidthFrom.getTime(), _TimeBarWidthTo.getTime(), shareId, companyId, groupId);	
		
		return oRTimeWidthRange;
	}
	
	
	
	public  static long getVolumeBetweenBars(Date from, Date to,long shareId, long companyId, long groupId,  boolean simulation)
	{
		if (!simulation)
			return RealtimeLocalServiceUtil.findSumVolumeBetweenBars(from, to, shareId, companyId, groupId);
		else
			return HistoricalRealtimeLocalServiceUtil.findSumVolumeBetweenBars(from, to, shareId, companyId, groupId);
		
		
	}
	
	/*public static Realtime getMinMaxBarFromShare( Calendar _To, int TimeBars, int ShareStrategyID, boolean Simulation )
	{
		
		
		return getMinMaxBarFromShare(_To, TimeBars,ShareStrategyID,0, Simulation);
	}
*/
	
	
	
	
	
	

}
