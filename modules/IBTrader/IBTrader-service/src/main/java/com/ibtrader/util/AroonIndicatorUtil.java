
package com.ibtrader.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.ibtrader.data.model.HistoricalRealtime;
import com.ibtrader.data.model.Market;
import com.ibtrader.data.model.Realtime;
import com.ibtrader.data.service.HistoricalRealtimeLocalServiceUtil;
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

	public AroonIndicatorUtil(Date dateTo, long timebar,  long shareId, long companyId, long groupId, long periods, boolean simulation, Market market) {
		getAaronIndicatorRate(dateTo,  timebar, shareId, companyId, groupId, periods, simulation, market );
	}
	/* para las medias armonizadas de  DM */
	private void   getAaronIndicatorRate(Date dateTo, long timebar, long shareId, long companyId, long groupId, long periods,boolean simulation, Market market)
	{
		
		try
		{
			
			
		List<String> lMACDPeriods = new ArrayList<String>();
		Calendar _cMACDDateFrom = Calendar.getInstance();
		_cMACDDateFrom.setTime(dateTo);
		
		int periodsToCalculate = ConfigKeys.INDICATORS_MIN_SERIE_COUNT;
		
		lMACDPeriods = BaseIndicatorUtil.getPeriodsMinutesMobileAvg(dateTo, periodsToCalculate , timebar, Boolean.TRUE, market, simulation, shareId);
	
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
	        	
			
		TimeSeries series = new BaseTimeSeries("getAaronIndicatorRate");
		series = BarSeriesCacheUtil.closeMinMaxBarTimeSeries(shareId, companyId, groupId, dateTo, simulation, timebar,ConfigKeys.INDICATORS_MIN_SERIE_COUNT, lMACDPeriods);
			
        _log.debug("Series count:" + series.getBarCount());
         
       // ATRIndicator atr = new ATRIndicator(series, (int) ConfigKeys.ADXR_PERIODS);
        AroonDownIndicator AroonDownIndicator    = new AroonDownIndicator(series, (int) periods);
        AroonUpIndicator AroonUpIndicator   	 = new AroonUpIndicator(series, (int) periods);
        AroonOscillatorIndicator AroonOscilator  = new AroonOscillatorIndicator(series, (int) periods);
	    
        if (_log.isDebugEnabled())
        {
			for (int j=0;j<=ConfigKeys.INDICATORS_MIN_SERIE_COUNT;j++)
			{ 
				try {_log.debug("AroonUpIndicator:" + j + ":" +  AroonUpIndicator.getValue(j).doubleValue());
					_log.debug("AroonDownIndicator:" + j + ":" +  AroonDownIndicator.getValue(j).doubleValue());
					_log.debug("AroonOscilator:" + j + ":" +  AroonOscilator.getValue(j).doubleValue());
					_log.debug("Bar:" + j + ":" + series.getBar(j).getClosePrice().doubleValue());
					} catch (Exception e) {}	
					
			}
        }
    	
        
        
    	_aroonUp 		=     AroonUpIndicator.getValue(ConfigKeys.INDICATORS_MIN_SERIE_COUNT-1)!=null  ?  AroonUpIndicator.getValue(ConfigKeys.INDICATORS_MIN_SERIE_COUNT-1).doubleValue() : 0;
    	_aroonDown 		=     AroonDownIndicator.getValue(ConfigKeys.INDICATORS_MIN_SERIE_COUNT-1)!=null ?  AroonDownIndicator.getValue(ConfigKeys.INDICATORS_MIN_SERIE_COUNT-1).doubleValue() : 0; 
    	_lastAroonUp 	=     AroonUpIndicator.getValue(ConfigKeys.INDICATORS_MIN_SERIE_COUNT-2)!=null  ?  AroonUpIndicator.getValue(ConfigKeys.INDICATORS_MIN_SERIE_COUNT-2).doubleValue() : 0;  
    	_lastAroonDown 	=     AroonDownIndicator.getValue(ConfigKeys.INDICATORS_MIN_SERIE_COUNT-2)!=null ?  AroonDownIndicator.getValue(ConfigKeys.INDICATORS_MIN_SERIE_COUNT-2).doubleValue() : 0;  
		
		
        _log.debug("_aroonUp:" + _aroonUp);
        _log.debug("_aroonDown:" + _aroonDown);
        _log.debug("_lastAroonUp:" + _lastAroonUp);
        _log.debug("_lastAroonDown:" + _lastAroonDown);
		}
		catch (Exception e)
		{
			_log.debug(e.getMessage());
		}
		
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
