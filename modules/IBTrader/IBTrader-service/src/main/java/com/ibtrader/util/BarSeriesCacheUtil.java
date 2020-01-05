package com.ibtrader.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.ta4j.core.BaseBar;
import org.ta4j.core.BaseTimeSeries;
import org.ta4j.core.TimeSeries;

import com.ibtrader.data.model.HistoricalRealtime;
import com.ibtrader.data.model.Market;
import com.ibtrader.data.model.Realtime;
import com.ibtrader.data.model.Share;
import com.ibtrader.data.service.HistoricalRealtimeLocalServiceUtil;
import com.ibtrader.data.service.MarketLocalServiceUtil;
import com.ibtrader.data.service.RealtimeLocalServiceUtil;
import com.ibtrader.data.service.ShareLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;



/* LA IDEA ES QUE SE MANTENGA EL ULTIMO ARRAY DE BARRAS NECESARIAS PARA CALCULAR LOS INDICADORES
 * DEBIDO AL RENDIMIENTO DE OBTENER LOS CIERRES DE 125 PERIODOS 
 * 
 *  LA CLASE ESTATICA DEBE MANTENER LAS BARRAS POR SHAREID Y COMPANYID E INCLUSO POR FECHA 
 *  
 *  */

public class BarSeriesCacheUtil {
	
	
	/* private static TimeSeries closeMinMaxBarTimeSeries =  null;
	private static String closeMinMaxBarTimeBarKey  =  null;  // SHAREID + COMPANY + TO
	
	private static TimeSeries closeBarTimeSeries =  null;
	private static String closeBarTimeBarKey  =  null;  // SHAREID + COMPANY + TO
	*/
	private static int CACHE_MAX_ELEMENTS = 50; 
	
	/*- LinkedHashMapproporciona un constructor especial que nos permite especificar, entre el factor de carga personalizado (LF) 
	y la capacidad inicial,un mecanismo/estrategia de ordenamiento diferente denominado orden de acceso:

	El primer parámetro es la capacidad inicial, seguido del factor de carga y el último parámetroes 
	el modo de pedido. Por lo tanto, al pasartrue, obtuvimos el orden de acceso, mientras que el 
	orden predeterminado era el orden de inserción.
	*/
	
	/* Caches  LINKEDIN HASKMAP PARA MANTENER CACHES Y ORDEN Y ELIMINAR LOS MENOS USADOS RECIENTEMENTE  */
	private static LinkedHashMap<String, TimeSeries> closeBarTimeSeriesCacheLRUMap  = new LinkedHashMap<String,TimeSeries>();
	private static LinkedHashMap<String,TimeSeries> closeMinMaxBarTimeSeriesLRUMap  = new LinkedHashMap<String,TimeSeries>();
	
	
	private static final Log _log = LogFactoryUtil.getLog(BarSeriesCacheUtil.class.getClass());
	
	/* SOLO SACA EL CIERRE DE LA BARRA */
	public static TimeSeries closeBarTimeSeries(long shareId, long companyId, long groupId,Date from, Date to, List<String> closingDates, boolean simulation, long timebars) 
	{
		
		SimpleDateFormat df = new SimpleDateFormat(Utilities.__IBTRADER_HISTORICAL_DATE_FORMAT); 
		
		StringBundler  keyBuilder= new StringBundler(String.valueOf(shareId));
		keyBuilder.append(StringPool.PIPE);
		keyBuilder.append(String.valueOf(companyId));
		keyBuilder.append(StringPool.PIPE);
		keyBuilder.append(String.valueOf(groupId));
		keyBuilder.append(StringPool.PIPE);
		keyBuilder.append(String.valueOf(timebars));
		keyBuilder.append(StringPool.PIPE);
		keyBuilder.append(String.valueOf(df.format(to)));
		keyBuilder.append(StringPool.PIPE);
		keyBuilder.append(String.valueOf(df.format(from)));
		keyBuilder.append(StringPool.PIPE);
		keyBuilder.append(String.valueOf(simulation));
		keyBuilder.append(StringPool.PIPE);
		keyBuilder.append(String.valueOf(closingDates.size()));  // puede ser que las series esten para una media exponencia de 27 periodos y los macd para 125 
		
		TimeSeries returnTimeSeries = null;

		_log.debug("closeBarTimeSeries  for shareId " + shareId + ",from:" + from + ",to:" + to + ",simulation:" + simulation);

		try 
		{
		/* COINCIDE EL CACHE */
		if (Validator.isNotNull(closeBarTimeSeriesCacheLRUMap) &&  closeBarTimeSeriesCacheLRUMap.containsKey(keyBuilder.toString()))
				returnTimeSeries =  closeBarTimeSeriesCacheLRUMap.get(keyBuilder.toString());
		else
		{
			TimeSeries tmpTimeSeries = new BaseTimeSeries("barTimeSeries");
	        ZonedDateTime endTime = ZonedDateTime.now(); // PRA 
	        int counter = 1;
			if (!simulation)
			{	
				
				List<Realtime> closeRealTimes = RealtimeLocalServiceUtil.findCloseRealTimes(shareId, companyId, groupId, from, to, closingDates);
				List<Realtime> modifiableCloseRealTimes = new ArrayList<Realtime>(closeRealTimes);
				modifiableCloseRealTimes.sort(Comparator.comparing(Realtime::getCreateDate));

				if (Validator.isNotNull(modifiableCloseRealTimes))
				{
						for (Realtime closepricebar : modifiableCloseRealTimes)
						{
							_log.debug("Realtime Close Price: " + closepricebar.getValue() + " for " + closepricebar.getCreateDate());
							tmpTimeSeries.addBar(new BaseBar(endTime.plusMinutes(timebars*counter),0.0,0.0,0.0, closepricebar.getValue(),0.0));
							counter++;
						}
				}
			}
			else
			{
				List<HistoricalRealtime> closeRealTimes = HistoricalRealtimeLocalServiceUtil.findCloseRealTimes(shareId, companyId, groupId, from, to, closingDates);
				List<HistoricalRealtime> modifiableCloseRealTimes = new ArrayList<HistoricalRealtime>(closeRealTimes);
				modifiableCloseRealTimes.sort(Comparator.comparing(HistoricalRealtime::getCreateDate));
				if (Validator.isNotNull(modifiableCloseRealTimes))
				{				
					for (HistoricalRealtime closepricebar : modifiableCloseRealTimes)
			        {	
						_log.debug("HistoricalRealtime Close Price: " + closepricebar.getValue() + " for " + closepricebar.getCreateDate());
						
						tmpTimeSeries.addBar(new BaseBar(endTime.plusMinutes(timebars*counter),0.0,0.0,0.0, closepricebar.getValue(),0.0));
			        	counter++;
			        }
				}
	
			}
			/* lo creamos la primera vez */
			if (Validator.isNull(closeBarTimeSeriesCacheLRUMap))		
						closeBarTimeSeriesCacheLRUMap = new PriceTimeSeriesCacheList<>(CACHE_MAX_ELEMENTS, .75f, true);						
			
			closeBarTimeSeriesCacheLRUMap.put(keyBuilder.toString(), tmpTimeSeries);
			returnTimeSeries =  closeBarTimeSeriesCacheLRUMap.get(keyBuilder.toString());
			/* closeBarTimeSeries = tmpTimeSeries;
			closeBarTimeBarKey = keyBuilder.toString(); */			
		}
		}// end try
		catch (Exception e)
		{
			_log.debug(e.getMessage());
		}
		return returnTimeSeries;
		
	}
	
	/* DEVUELVE LA SERIE COMPLETA */
	public static TimeSeries closeMinMaxBarTimeSeries(long shareId, long companyId, long groupId,Date to,  boolean simulation, long timebars, long periodsToCalculate, List<String> closingDates) 
	{
		
		SimpleDateFormat df = new SimpleDateFormat(Utilities.__IBTRADER_HISTORICAL_DATE_FORMAT); 
		
		StringBundler  keyBuilder= new StringBundler(String.valueOf(shareId));
		keyBuilder.append(StringPool.PIPE);
		keyBuilder.append(String.valueOf(companyId));
		keyBuilder.append(StringPool.PIPE);
		keyBuilder.append(String.valueOf(groupId));
		keyBuilder.append(StringPool.PIPE);
		keyBuilder.append(String.valueOf(timebars));
		keyBuilder.append(StringPool.PIPE);
		keyBuilder.append(String.valueOf(df.format(to)));
		keyBuilder.append(StringPool.PIPE);		
		keyBuilder.append(String.valueOf(simulation));
		keyBuilder.append(StringPool.PIPE);
		keyBuilder.append(String.valueOf(periodsToCalculate));  // puede ser que las series esten para una media exponencia de 27 periodos y los macd para 125
		
		TimeSeries series = new BaseTimeSeries("closeMinMaxBarTimeSeries");

		TimeSeries returnTimeSeries = null;

		
		try 
		{
			
		/* COINCIDE EL CACHE */			
		if (Validator.isNotNull(closeMinMaxBarTimeSeriesLRUMap) &&  closeMinMaxBarTimeSeriesLRUMap.containsKey(keyBuilder.toString()))
			returnTimeSeries =  closeMinMaxBarTimeSeriesLRUMap.get(keyBuilder.toString());	
		else
		{
			ZonedDateTime endTime = ZonedDateTime.now(); // PRA
			List<String> lClosingPeriods = new ArrayList<String>();
			
			Share share =  ShareLocalServiceUtil.fetchShare(shareId);
			Market market =  MarketLocalServiceUtil.fetchMarket(share.getMarketId());			

			double max_value = 0;
	        double min_value = 0;
	        double close_value = 0;
			
	        /* ESTE FROM ESTA RESTADO DE LAS BARRAS POR LOS PERIODOS, PERO NO CONTEMPLA LOS CIERRES Y APERTURAS DE MERCADO */
        	/* SOLUCION, COGER EL FROM DE LA PRIMERA BARRA DE getPeriodsMinutesMobileAvg, QUE SI CONTEMPLA LAS APERTURAS, YENDOME A LA BARRA ANTERIOR DE CIERRE
        	 * 
        	 *  14:49 14:54 ....*/
	        SimpleDateFormat _sdf = new SimpleDateFormat(Utilities.__IBTRADER_SQL_DATE_);
	        Date fromWithOpenMarketsTimes=null;
	        Calendar cfromWithOpenMarketsTimes = null;
	        try {
				 fromWithOpenMarketsTimes = _sdf.parse(closingDates.get(closingDates.size()-1));
				 cfromWithOpenMarketsTimes = Calendar.getInstance();
				 cfromWithOpenMarketsTimes.setTimeInMillis(fromWithOpenMarketsTimes.getTime());
				 cfromWithOpenMarketsTimes.add(Calendar.MINUTE, 1);
				 cfromWithOpenMarketsTimes.add(Calendar.MINUTE, -(int) timebars);
				 cfromWithOpenMarketsTimes.set(Calendar.MILLISECOND, 0);
			} catch (ParseException e) {}
	        
	        if (!simulation)
	        {	        
	        	
	        	
	        	
	        	List<Realtime> lRMinMax = RealtimeLocalServiceUtil.findMinMaxRealTimesGroupedByBars(cfromWithOpenMarketsTimes.getTime(), to, shareId, companyId, groupId,timebars,market);
	        	List<Realtime> modifiableRMinMax = new ArrayList<Realtime>(lRMinMax);
				modifiableRMinMax.sort(Comparator.comparing(Realtime::getCreateDate));
	        	// findCloseRealTimes
	        	List<Realtime> lRcloseValue = RealtimeLocalServiceUtil.findCloseRealTimes(shareId, companyId, groupId, cfromWithOpenMarketsTimes.getTime(), to, lClosingPeriods);
	        	List<Realtime> modifiableRcloseValue = new ArrayList<Realtime>(lRcloseValue);
	        	modifiableRcloseValue.sort(Comparator.comparing(Realtime::getCreateDate));

	        	
	        	List<Realtime> lRMinMaxShorter = null;
	        	List<Realtime> lRcloseShorter = null;
	        	
	        	/* NOS INTERESAN LOS X ULTIMOS VALORES EN ORDEN */
	        	
	        	// && lRcloseValue.size()==periodsToCalculate && lRMinMax.size()==periodsToCalculate
	        	if (Validator.isNotNull(modifiableRMinMax) && Validator.isNotNull(modifiableRcloseValue))  // ya se rellenan los datos con loe huecos 
	        			
	        			/* TODO OK */
	        	{
	        		lRcloseShorter   = modifiableRcloseValue.subList((int) (modifiableRcloseValue.size()-periodsToCalculate), modifiableRcloseValue.size());
	        		lRMinMaxShorter  =  modifiableRMinMax.subList((int) (modifiableRMinMax.size()-periodsToCalculate), modifiableRMinMax.size());
	        		  
	        		  
	        		  for (int j=0;j<lRMinMaxShorter.size();j++)
	        		  {
	        			
	        			  Realtime realtimeMINMAX = lRMinMaxShorter.get(j);
	        			  Realtime realtimeCLOSE = lRcloseShorter.get(j);
	        			  if (realtimeMINMAX.getMax_value()<=0  || realtimeMINMAX.getMin_value()<=0 || realtimeCLOSE.getValue()<=0)
							break;
	        			  
				          max_value = realtimeMINMAX.getMax_value();
				          min_value = realtimeMINMAX.getMin_value();
				          close_value = realtimeCLOSE.getValue();
	        			  
				          /* ANTES TENIA PLUSMINUTES, COMO LO  CAMBIA LA QUERY EL ORDEN A DESC, LO NECESIO*/
	        			  series.addBar(new BaseBar(endTime.plusMinutes(timebars*j),0.0,max_value ,min_value, close_value,0.0));
	        			  
	        			  _log.debug("Periodo:" + j + "from:" + cfromWithOpenMarketsTimes.getTime() + ",to:" + to + ",MinMax:" +  max_value + "," + min_value + ",CloseValue:" + close_value);

	        			  
	        		  }
	        	}
	        	
	        }
	        	
	        else
	        {
	        	
	        	
	        	List<HistoricalRealtime> lRMinMax = HistoricalRealtimeLocalServiceUtil.findMinMaxRealTimesGroupedByBars(cfromWithOpenMarketsTimes.getTime(), to, shareId, companyId, groupId,timebars,market);
	        	List<HistoricalRealtime> modifiableRMinMax = new ArrayList<HistoricalRealtime>(lRMinMax);
				modifiableRMinMax.sort(Comparator.comparing(HistoricalRealtime::getCreateDate));
	        	// findCloseRealTimes
	        	List<HistoricalRealtime> lRcloseValue = HistoricalRealtimeLocalServiceUtil.findCloseRealTimes(shareId, companyId, groupId, cfromWithOpenMarketsTimes.getTime(), to, lClosingPeriods);
	        	List<HistoricalRealtime> modifiableRcloseValue = new ArrayList<HistoricalRealtime>(lRcloseValue);
	        	modifiableRcloseValue.sort(Comparator.comparing(HistoricalRealtime::getCreateDate));
	        	
	        	
	        	List<HistoricalRealtime> lRMinMaxShorter = null;
	        	List<HistoricalRealtime> lRcloseShorter = null;
	        	
	        	/* NOS INTERESAN LOS X ULTIMOS VALORES EN ORDEN */
	        	if (Validator.isNotNull(modifiableRMinMax) && Validator.isNotNull(modifiableRcloseValue))
	        			/* TODO OK */
	        	{
	        		lRcloseShorter  = modifiableRcloseValue.subList((int) (modifiableRcloseValue.size()-periodsToCalculate), modifiableRcloseValue.size());
	        		lRMinMaxShorter =  modifiableRMinMax.subList((int) (modifiableRMinMax.size()-periodsToCalculate), modifiableRMinMax.size());
	        		  
	        		  for (int j=0;j<lRMinMaxShorter.size();j++)
	        		  {
	        			
	        			  HistoricalRealtime realtimeMINMAX = lRMinMaxShorter.get(j);
	        			  HistoricalRealtime realtimeCLOSE = lRcloseShorter.get(j);
	        			  if (realtimeMINMAX.getMax_value()<=0  || realtimeMINMAX.getMin_value()<=0 || realtimeCLOSE.getValue()<=0)
							break;
	        			  
				          max_value = realtimeMINMAX.getMax_value();
				          min_value = realtimeMINMAX.getMin_value();
				          close_value =realtimeCLOSE.getValue();
				          /* ANTES TENIA PLUSMINUTES, COMO LO  CAMBIA LA QUERY EL ORDEN A DESC, LO NECESIO */
	        			  series.addBar(new BaseBar(endTime.plusMinutes(timebars*j),0.0,max_value ,min_value, close_value,0.0));

	        			
	        		  }
	        	}
	        	
	        }
	        /* lo creamos la primera vez */
			if (Validator.isNull(closeMinMaxBarTimeSeriesLRUMap))		
				closeMinMaxBarTimeSeriesLRUMap = new PriceTimeSeriesCacheList<>(CACHE_MAX_ELEMENTS, .75f, true);						
			
			closeMinMaxBarTimeSeriesLRUMap.put(keyBuilder.toString(), series);
			returnTimeSeries =  closeMinMaxBarTimeSeriesLRUMap.get(keyBuilder.toString());
			
		}
		}// end try
		catch (Exception e)
		{
			_log.debug(e.getMessage());
		}		
       /*  closeMinMaxBarTimeSeries = series;
		closeMinMaxBarTimeBarKey = keyBuilder.toString(); */
		return returnTimeSeries;
	}

}
