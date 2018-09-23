
package com.ibtrader.util;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.ta4j.core.BaseBar;
import org.ta4j.core.BaseTimeSeries;
import org.ta4j.core.TimeSeries;
import org.ta4j.core.indicators.ATRIndicator;
import org.ta4j.core.indicators.AroonDownIndicator;
import org.ta4j.core.indicators.AroonOscillatorIndicator;
import org.ta4j.core.indicators.AroonUpIndicator;
import org.ta4j.core.indicators.MACDIndicator;
import org.ta4j.core.indicators.adx.ADXIndicator;
import org.ta4j.core.indicators.adx.MinusDIIndicator;
import org.ta4j.core.indicators.adx.PlusDIIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;

import com.ibtrader.data.model.Realtime;
import com.ibtrader.data.service.RealtimeLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

/*https://www.tecnicasdetrading.com/2011/12/indicador-tecnico-aroon.html */
public class AroonIndicatorUtil {

	private double _aroonUp = 0d;
	
	public double getAroonUp() {
		return _aroonUp;
	}
	public double getAroonDown() {
		return _aroonDown;
	}
	public double getLastAroonUp() {
		return _lastAroonUp;
	}
	public double getLastAroonDown() {
		return _lastAroonDown;
	}
	
	private double _aroonDown = 0d;
	private double _lastAroonUp = 0d;
	private double _lastAroonDown = 0d;
	
	private static Log _log = LogFactoryUtil.getLog(AroonIndicatorUtil.class);

	public AroonIndicatorUtil(Date dateTo, long timebar,  long shareId, long companyId, long groupId, long periods) {
		getAaronIndicatorRate(dateTo,  timebar, shareId, companyId, groupId, periods);
	}
	/* para las medias armonizadas de  DM */
	private void   getAaronIndicatorRate(Date dateTo, long timebar, long shareId, long companyId, long groupId, long periods)
	{
		
		
		int periodsToCalculate = ConfigKeys.INDICATORS_MIN_SERIE_COUNT; // https://estrategiastrading.com/indicador-adx-formula/
		Calendar  cPeriodFrom = Calendar.getInstance(); 
	    cPeriodFrom.setTimeInMillis(dateTo.getTime());
	    cPeriodFrom.add(Calendar.MINUTE, - new Long(timebar * (periodsToCalculate) ).intValue());
	    cPeriodFrom.set(Calendar.MILLISECOND,0);	
	    
	    
		TimeSeries series = new BaseTimeSeries("getAaronIndicatorRate");
        ZonedDateTime endTime = ZonedDateTime.now();       
        for (int j=0;j<periodsToCalculate;j++)
		{
        	 /* MAX Y MIN POR BARRA  15:05:00  */
			Calendar  cPeriodTo = Calendar.getInstance();
			cPeriodTo.setTime(cPeriodFrom.getTime());
			cPeriodTo.add(Calendar.MINUTE, new Long(timebar).intValue());
			//cPeriodTo.add(Calendar.SECOND,  -1);
		    
			Date dateMinMaxFrom = cPeriodFrom.getTime();		    		
	        Date dateMinMaxTo = cPeriodTo.getTime();
		    
			Realtime minMax = RealtimeLocalServiceUtil.findMinMaxRealTime(dateMinMaxFrom, dateMinMaxTo, shareId, companyId, groupId);
			Realtime closeValue = RealtimeLocalServiceUtil.findLastRealTimeLessThanDate(shareId, companyId, groupId, dateMinMaxTo);
			
			if (minMax.getMax_value()<=0  || minMax.getMin_value()<=0 ||  Validator.isNull(closeValue))
				break;
			
			series.addBar(new BaseBar(endTime,0.0,minMax.getMax_value() ,minMax.getMin_value(), closeValue.getValue(),0.0));
			
			_log.debug("Serie " + j  + " " + series.getBar(j).getClosePrice() + "," + series.getBar(j).getMaxPrice() + "," + series.getBar(j).getMinPrice());
			
			cPeriodFrom.add(Calendar.MINUTE, new Long(timebar).intValue());
			endTime = endTime.plusMinutes(timebar);
        	
		}
        
        _log.debug("Series count:" + series.getBarCount());
       
        
       // ATRIndicator atr = new ATRIndicator(series, (int) ConfigKeys.ADXR_PERIODS);
        AroonDownIndicator AroonDownIndicator    = new AroonDownIndicator(series, (int) periods);
        AroonUpIndicator AroonUpIndicator   	 = new AroonUpIndicator(series, (int) periods);
        AroonOscillatorIndicator AroonOscilator  = new AroonOscillatorIndicator(series, (int) periods);
	    

		for (int j=0;j<=periodsToCalculate;j++)
		{ 
			try {_log.debug("AroonUpIndicator:" + j + ":" +  AroonUpIndicator.getValue(j).doubleValue());
				_log.debug("AroonDownIndicator:" + j + ":" +  AroonDownIndicator.getValue(j).doubleValue());
				_log.debug("AroonOscilator:" + j + ":" +  AroonOscilator.getValue(j).doubleValue());
				_log.debug("Bar:" + j + ":" + series.getBar(j).getClosePrice().doubleValue());
				} catch (Exception e) {}	
				
		}
    	
        
        
    	_aroonUp 		=     AroonUpIndicator.getValue(periodsToCalculate-1)!=null  ?  AroonUpIndicator.getValue(periodsToCalculate-1).doubleValue() : 0;
    	_aroonDown 		=     AroonDownIndicator.getValue(periodsToCalculate-1)!=null ?  AroonDownIndicator.getValue(periodsToCalculate-1).doubleValue() : 0; 
    	_lastAroonUp 	=     AroonUpIndicator.getValue(periodsToCalculate-2)!=null  ?  AroonUpIndicator.getValue(periodsToCalculate-2).doubleValue() : 0;  
    	_lastAroonDown 	=     AroonDownIndicator.getValue(periodsToCalculate-2)!=null ?  AroonDownIndicator.getValue(periodsToCalculate-2).doubleValue() : 0;  
		
		
        _log.debug("_aroonUp:" + _aroonUp);
        _log.debug("_aroonDown:" + _aroonDown);
        _log.debug("_lastAroonUp:" + _lastAroonUp);
        _log.debug("_lastAroonDown:" + _lastAroonDown);
        
		
	}
	public static void main (String[] args) {

		/* 
		 * 2018-07-25 01:39:59.999000
		 */
		
}

	/* comparativa con el anterior DI - DI, ha habido un corte  */
	public boolean isCrossAroonUpWard() {
		
		return (_lastAroonDown >  _lastAroonUp && _aroonUp > _aroonDown) ;
		
	}
	/* comparativa con el anterior DI - DI, ha habido un corte  */
	public boolean isCrossAroonDownWard() {
		
		return (_lastAroonUp > _lastAroonDown && _aroonDown > _aroonUp );
		
	}
	
}
