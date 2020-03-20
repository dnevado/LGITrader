package com.ibtrader.util;

import java.util.Calendar;
import java.util.Date;

import com.ibtrader.data.model.HistoricalRealtime;
import com.ibtrader.data.model.Realtime;
import com.ibtrader.data.model.Share;
import com.ibtrader.data.service.HistoricalRealtimeLocalServiceUtil;
import com.ibtrader.data.service.RealtimeLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;

public class CandleUtil {
	
	/* En una tendencia alcista: si una vela doji con una gran mecha aparece tras un movimiento alcista, las probabilidades de un cambio bajista son elevadas.
	En una tendencia alcista: si una vela doji con una gran mecha aparece tras un movimiento bajista, las probabilidades de un cambio alcista son elevadas.
	*
	*
	*
	*	CONSIDEREMOS AL MENOS  UN 50% DE MECHA ENTRE LA APERTURA, CIERRE Y EL MAXIMO Y MINIMO
	*
	*
	*https://www.novatostradingclub.com/formacion/velas-japonesas-una-cuestion-de-sentido-comun/ Velas quieroynopuedo

	*/
	
	private static double candle_max_value;
	private static double candle_min_value;
	private static double candle_close_value;
	private static double candle_start_value;
	
	
	@SuppressWarnings("unused")
	private static double candle_body_close_start;
	private static double candle_body_total;	
	
	
	/* CUANTO TIENE QUE SER LA MECHA CON RESPECTO AL ANCHO REAL DE LA APERTURA CIERRE */
	private static final double AVERAGE_WICK_SIZE = ConfigKeys.AVERAGE_WICK_SIZE;
	
	private static void  fillCandleData(Date to, Share share, boolean simulation, long timebars)
	{
		
		candle_max_value = 0;
		candle_min_value = 0;
		candle_close_value = 0;
		candle_start_value = 0;
		
		candle_body_close_start = 0;
		candle_body_total = 0;
		
		
		Calendar _from = Calendar.getInstance();
		_from.setTime(to);
		_from.add(Calendar.MINUTE, - (int) timebars);
		Date from = _from.getTime();
		
		if (!simulation)
		{
			Realtime MinMaxRealtime = RealtimeLocalServiceUtil.findMinMaxRealTime(from, to, share.getShareId(), share.getCompanyId(), share.getGroupId());
			Realtime StartRealtime =  RealtimeLocalServiceUtil.findFirstRealTimeBetweenDates(share.getShareId(), share.getCompanyId(), share.getGroupId(), from, to);
			Realtime CloseRealtime =  RealtimeLocalServiceUtil.findLastRealTimeLessThanDate(share.getShareId(), share.getCompanyId(), share.getGroupId(), to);
			if (Validator.isNotNull(MinMaxRealtime) && Validator.isNotNull(StartRealtime) &&  Validator.isNotNull(CloseRealtime)
					&& MinMaxRealtime.getMax_value()>0  && MinMaxRealtime.getMin_value()>0 && 
					StartRealtime.getValue()>0  && CloseRealtime.getValue()>0)
			{	
			
				candle_max_value = MinMaxRealtime.getMax_value();
				candle_min_value = MinMaxRealtime.getMin_value();
				candle_close_value = CloseRealtime.getValue();
				candle_start_value = StartRealtime.getValue();
				
				
			}
					
		}
		else
		{	
			HistoricalRealtime MinMaxRealtime = HistoricalRealtimeLocalServiceUtil.findMinMaxRealTime(from, to, share.getShareId(), share.getCompanyId(), share.getGroupId());
			HistoricalRealtime StartRealtime =  HistoricalRealtimeLocalServiceUtil.findFirstRealTimeBetweenDates(share.getShareId(), share.getCompanyId(), share.getGroupId(), from, to);
			HistoricalRealtime CloseRealtime =  HistoricalRealtimeLocalServiceUtil.findLastRealTimeLessThanDate(share.getShareId(), share.getCompanyId(), share.getGroupId(), to);
			
			if (Validator.isNotNull(MinMaxRealtime) && Validator.isNotNull(StartRealtime) &&  Validator.isNotNull(CloseRealtime)
					&& MinMaxRealtime.getMax_value()>0  && MinMaxRealtime.getMin_value()>0 && 
					StartRealtime.getValue()>0  && CloseRealtime.getValue()>0)
			{	
			
				candle_max_value = MinMaxRealtime.getMax_value();
				candle_min_value = MinMaxRealtime.getMin_value();
				candle_close_value = CloseRealtime.getValue();
				candle_start_value = StartRealtime.getValue();
				
				
			}
			
				
		}
		
		candle_body_close_start = Math.abs(candle_close_value -  candle_start_value);
		candle_body_total = Math.abs(candle_max_value - candle_min_value);
		
	}
	/* 	CONSIDEREMOS AL MENOS  UN 50% DE MECHA ENTRE LA APERTURA, CIERRE Y EL MAXIMO Y MINIMO 
	 *  Así pues, la forma de estas velas sólo puede ser de una manera: Cuerpo pequeño en una esquina y sombra larga, cuanto más larga mejor, pues más marcado estará el efecto de euforia frustrada.
		Si la sombra está hacia arriba, significa que el precio subía y ahora toca bajar. Si está hacia abajo, habla de una fuerte bajada que ha sido frustrada y da paso a una nueva subida.	
	 * */
	public static synchronized boolean  wickUp(Date to, Share share, boolean simulation, long timebars)
	{
		
		fillCandleData(to, share, simulation, timebars);
		// cogemos el mayor del precio del cirre / apertura
		double max_internal_value = (candle_close_value > candle_start_value ? candle_close_value : candle_start_value);		
		return max_internal_value <  candle_max_value  - (candle_body_total *  AVERAGE_WICK_SIZE); 
		
	}
	public static  synchronized boolean  wickDown(Date to, Share share, boolean simulation, long timebars)
	{
		fillCandleData(to, share, simulation,timebars);// cogemos el mayor del precio del cirre / apertura
		// cogemos el menor  del precio del cirre / apertura
		double min_internal_value = (candle_close_value < candle_start_value ? candle_close_value : candle_start_value);		
		return min_internal_value >  candle_min_value  + (candle_body_total *  AVERAGE_WICK_SIZE); 
	
		
	}
}
