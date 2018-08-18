/*
 * Movimiento direccional (+DM y –DM): Este indicador representa la mayor parte del día que se encuentra fuera del rango del día anterior. Por ejemplo, si el rango del día de ayer estuvo entre 18 y 22 y el de hoy ha estado entre 17 y 24, entonces +DM es 2 y –DM es igual a 0. En un Inside Day en el que el máximo y el mínimo se encuentran dentro del rango del día anterior no existiría movimiento direccional. Este indicador no representa precios de cierre, en el ejemplo anterior aún si el cierre estuviera por debajo del mínimo precedente el movimiento direccional sería considerado positivo, porque la mayor parte del rango diario ha estado por encima del rango diario de la sesión anterior. El –DM siempre es positivo, así si el rango de hoy se encuentra entre 17 y 20 y ayer ha estado entre 19-19,5 entonces el –DM es 2 y el +DM es igual a 0.

Índice de movimiento direccional (+DI y –DI): Este indicador se calcula combinando el movimiento direccional (+DM y –DM) explicado anteriormente con el rango verdadero (TR). El rango verdadero es el mayor valor entre:

A. La diferencia entre el máximo de hoy y el mínimo de hoy.
B. La diferencia entre el máximo de hoy y el cierre de ayer.
C. La diferencia entre el mínimo de hoy y el cierre de ayer.

A continuación construimos el índice de movimiento direccional como el ratio entre el DM y el TR de la siguiente forma:

+DI= +DM/TR
-DI= -DM/TR

Este indicador representa la fuerza que ha tenido el movimiento de hoy en comparación con el rango verdadero.

Índice de movimiento direccional suavizado: Se calcula añadiendo una media móvil (generalmente exponencial de 14 sesiones) sobre cada uno de los movimientos direccionales (+DI) y (–DI).

Indicador direccional (DX): Es el ratio de la diferencia entre el +DI suavizado y el –DI suavizado, se calcula de la siguiente forma: 

DX = [+DI(14) - -DI(14)] / [+DI(14) + -DI(14)]

El +DI(14) es el movimiento direccional positivo suavizado durante los últimos 14 días y el –DI(14) es el movimiento direccional negativo suavizado durante los últimos 14 días.

Índice de movimiento direccional (ADX): Se calcula simplemente añadiendo una media móvil (generalmente exponencial de 14 sesiones) sobre el indicador direccional (DX).
 */

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

public class AaronIndicatorUtil {

	 
	private double _ADX = 0d;
	private double _plusDI = 0d;
	private double _minusDI = 0d;
	private double _lastPlusDI = 0d;
	private double _lastMinusDI = 0d;
	private double _DX = 0d; // ADX= [ADX( ayer) *13 + DX (hoy)] / 14
	private double _ADXR = 0d; // ADXR= [ADX(hoy) + ADX( 14 velas atrás)] /2
	
	/*
	 * El rango verdadero es el mayor valor entre:
		A. La diferencia entre el máximo de hoy y el mínimo de hoy.
		B. La diferencia entre el máximo de hoy y el cierre de ayer.
		C. La diferencia entre el mínimo de hoy y el cierre de ayer.
	 */
	
	/*
	 * ADX- Average Directional 
	 */
	private static Log _log = LogFactoryUtil.getLog(AaronIndicatorUtil.class);

	public AaronIndicatorUtil(Date dateTo, long timebar,  long shareId, long companyId, long groupId) {
		getAvgDirectionalRate(dateTo,  timebar, shareId, companyId, groupId);
	}
	/* para las medias armonizadas de  DM */

	
	
	/*
	 * ADXR- Average Directional Movement Index Rating
	 * dateTo --> barra actual , 15:05:00, 
	 * timebar --> 5 en minutos
	 * periods --> para calcular el ADXR , normalmente 14 
	 */
	private void  getAvgDirectionalRate(Date dateTo, long timebar, long shareId, long companyId, long groupId)
	{
		
		
		int periodsToCalculate = ConfigKeys.INDICATORS_MIN_SERIE_COUNT; // https://estrategiastrading.com/indicador-adx-formula/
		Calendar  cPeriodFrom = Calendar.getInstance(); 
	    cPeriodFrom.setTimeInMillis(dateTo.getTime());
	    cPeriodFrom.add(Calendar.MINUTE, - new Long(timebar * (periodsToCalculate) ).intValue());
	    cPeriodFrom.set(Calendar.MILLISECOND,0);	
	    
	    
		TimeSeries series = new BaseTimeSeries("getAvgDirectionalRate");
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
		MinusDIIndicator MinusDIIndicator    = new MinusDIIndicator(series, (int) ConfigKeys.ADXR_PERIODS);
		PlusDIIndicator PlusDIIndicator      = new PlusDIIndicator(series, (int) ConfigKeys.ADXR_PERIODS);
		ADXIndicator ADXIndicator            = new ADXIndicator(series, (int) ConfigKeys.ADXR_PERIODS);
        
		_plusDI 		=     PlusDIIndicator.getValue(periodsToCalculate-1)!=null  ?  PlusDIIndicator.getValue(periodsToCalculate-1).doubleValue() : 0;
		_minusDI 		=     MinusDIIndicator.getValue(periodsToCalculate-1)!=null ?  MinusDIIndicator.getValue(periodsToCalculate-1).doubleValue() : 0; 
		_lastPlusDI 	=     PlusDIIndicator.getValue(periodsToCalculate-2)!=null  ?  PlusDIIndicator.getValue(periodsToCalculate-2).doubleValue() : 0;  
		_lastMinusDI 	=     MinusDIIndicator.getValue(periodsToCalculate-2)!=null ?  MinusDIIndicator.getValue(periodsToCalculate-2).doubleValue() : 0;  
		_ADX            = 	  ADXIndicator.getValue(periodsToCalculate-1) !=null 	?  ADXIndicator.getValue(periodsToCalculate-1).doubleValue() : 0;
		_ADXR 			= 	  ADXIndicator.getValue(periodsToCalculate-1)!=null 	? (_ADX + ADXIndicator.getValue(periodsToCalculate-1 - ConfigKeys.ADXR_PERIODS).doubleValue()) / 2 : 0;
		
        _log.debug("_plusDI:" + _plusDI);
        _log.debug("_minusDI:" + _minusDI);
        _log.debug("_lastPlusDI:" + _lastPlusDI);
        _log.debug("_lastMinusDI:" + _lastMinusDI);
        _log.debug("_ADX:" + _ADX);
        _log.debug("_ADX (n -14) :" + ADXIndicator.getValue(periodsToCalculate-1 - ConfigKeys.ADXR_PERIODS).doubleValue());
        _log.debug("_ADXR: (_ADX +  ADX (n-14) / 2)" + _ADXR);

		
		/* for (int j=0;j<=periodsToCalculate;j++)
		{ 
			try {_log.debug("PlusDIIndicator:" + j + ":" +  PlusDIIndicator.getValue(j).doubleValue());} catch (Exception e) {}	
		}
		for (int j=0;j<=periodsToCalculate;j++)
		{ 
			try {_log.debug("MinusDIIndicator:" + j + ":" +  MinusDIIndicator.getValue(j).doubleValue());} catch (Exception e) {}	
		}
		for (int j=0;j<=periodsToCalculate;j++)
		{ 
			try {_log.debug("ADXIndicator:" + j + ":" +  ADXIndicator.getValue(j).doubleValue());} catch (Exception e) {}	
		}
		*/

		
	}
	public static void main (String[] args) {

		/* 
		 * 2018-07-25 01:39:59.999000
		 */
		Calendar cData = Calendar.getInstance();
		cData.set(2018, 6, 25, 1, 40, 0);
		AaronIndicatorUtil ADXR =  new AaronIndicatorUtil(cData.getTime(), 5,  2602, 20116, 101213);
		ADXR.getADXR();
		
		

}
	public double getADXR() {
		return _ADXR;
	}
	
	public double getPlusDI() {
		return _plusDI;
	}
	public double getMinusDI() {
		return _minusDI;
	}
	
	public double getDX() {
		return _DX;
	}
	public double getADX() {
		return _ADX;
	}

	/* comparativa con el anterior DI - DI, ha habido un corte  */
	public boolean isCrossDIUpWard() {
		
		return (_lastMinusDI >  _lastPlusDI && _plusDI > _minusDI) ;
		
	}
	/* comparativa con el anterior DI - DI, ha habido un corte  */
	public boolean isCrossDIDownWard() {
		
		return (_lastPlusDI > _lastMinusDI && _minusDI > _plusDI );
		
	}
	
}
