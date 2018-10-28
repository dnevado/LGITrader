package com.ibtrader.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
	
	
	private static TimeSeries closeMinMaxBarTimeSeries =  null;
	private static String closeMinMaxBarTimeBarKey  =  null;  // SHAREID + COMPANY + TO
	
	private static TimeSeries closeBarTimeSeries =  null;
	private static String closeBarTimeBarKey  =  null;  // SHAREID + COMPANY + TO
	
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
		
		try 
		{
		/* COINCIDE EL CACHE */
		if (Validator.isNotNull(closeBarTimeBarKey) &&  closeBarTimeBarKey.equals(keyBuilder.toString()))
				return closeBarTimeSeries;
		else
		{
			TimeSeries tmpTimeSeries = new BaseTimeSeries("barTimeSeries");
	        ZonedDateTime endTime = ZonedDateTime.now();
	        int counter = 1;
			if (!simulation)
			{		
				List<Realtime> closeRealTimes = RealtimeLocalServiceUtil.findCloseRealTimes(shareId, companyId, groupId, from, to, closingDates);
				if (Validator.isNotNull(closeRealTimes))
				{
						for (Realtime closepricebar : closeRealTimes)
						{
							tmpTimeSeries.addBar(new BaseBar(endTime.plusMinutes(timebars*counter),0.0,0.0,0.0, closepricebar.getValue(),0.0));
							counter++;
						}
				}
			}
			else
			{
				List<HistoricalRealtime> closeRealTimes = HistoricalRealtimeLocalServiceUtil.findCloseRealTimes(shareId, companyId, groupId, from, to, closingDates);
				if (Validator.isNotNull(closeRealTimes))
				{				
					for (HistoricalRealtime closepricebar : closeRealTimes)
			        {
						tmpTimeSeries.addBar(new BaseBar(endTime.plusMinutes(timebars*counter),0.0,0.0,0.0, closepricebar.getValue(),0.0));
			        	counter++;
			        }
				}
	
			}
			closeBarTimeSeries = tmpTimeSeries;
			closeBarTimeBarKey = keyBuilder.toString();			
		}
		}// end try
		catch (Exception e)
		{
			_log.debug(e.getMessage());
		}
		return closeBarTimeSeries;
		
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

		try 
		{
			
		/* COINCIDE EL CACHE */
		if (Validator.isNotNull(closeMinMaxBarTimeBarKey) &&  closeMinMaxBarTimeBarKey.equals(keyBuilder.toString()))
				return closeMinMaxBarTimeSeries;
		else
		{
			Calendar  cPeriodFrom = Calendar.getInstance(); 
		    cPeriodFrom.setTimeInMillis(to.getTime());
		    cPeriodFrom.add(Calendar.MINUTE, - new Long(timebars * (periodsToCalculate) ).intValue());
		    cPeriodFrom.set(Calendar.MILLISECOND,0);	
		    
	        ZonedDateTime endTime = ZonedDateTime.now();
			List<String> lClosingPeriods = new ArrayList<String>();
			
			Share share =  ShareLocalServiceUtil.fetchShare(shareId);
			Market market =  MarketLocalServiceUtil.fetchMarket(share.getMarketId());			
			lClosingPeriods = MobileAvgUtil.getPeriodsMinutesMobileAvg(to, periodsToCalculate , timebars, Boolean.TRUE, market);

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
				 fromWithOpenMarketsTimes = _sdf.parse(lClosingPeriods.get(lClosingPeriods.size()-1));
				 cfromWithOpenMarketsTimes = Calendar.getInstance();
				 cfromWithOpenMarketsTimes.setTimeInMillis(fromWithOpenMarketsTimes.getTime());
				 cfromWithOpenMarketsTimes.add(Calendar.MINUTE, 1);
				 cfromWithOpenMarketsTimes.add(Calendar.MINUTE, -(int) timebars);
				 cfromWithOpenMarketsTimes.set(Calendar.MILLISECOND, 0);
			} catch (ParseException e) {}
	        
	        if (!simulation)
	        {	        
	        	
	        	
	        	
	        	List<Realtime> lRMinMax = RealtimeLocalServiceUtil.findMinMaxRealTimesGroupedByBars(cfromWithOpenMarketsTimes.getTime(), to, shareId, companyId, groupId,timebars,market);
	        	// findCloseRealTimes
	        	List<Realtime> lRcloseValue = RealtimeLocalServiceUtil.findCloseRealTimes(shareId, companyId, groupId, cfromWithOpenMarketsTimes.getTime(), to, lClosingPeriods);
	        	
	        	if (Validator.isNotNull(lRMinMax) && Validator.isNotNull(lRcloseValue) 
	        			&& lRcloseValue.size()==periodsToCalculate && lRMinMax.size()==periodsToCalculate)
	        			/* TODO OK */
	        	{
	        		  for (int j=0;j<lRcloseValue.size();j++)
	        		  {
	        			
	        			  Realtime realtimeMINMAX = lRMinMax.get(j);
	        			  Realtime realtimeCLOSE = lRMinMax.get(j);
	        			  if (realtimeMINMAX.getMax_value()<=0  || realtimeMINMAX.getMin_value()<=0 || realtimeCLOSE.getValue()<=0)
							break;
	        			  
				          max_value = realtimeMINMAX.getMax_value();
				          min_value = realtimeMINMAX.getMin_value();
				          close_value =realtimeCLOSE.getValue();
	        			  
	        			  series.addBar(new BaseBar(endTime.plusMinutes(timebars*j),0.0,max_value ,min_value, close_value,0.0));

	        			  
	        		  }
	        	}
	        }
	        	
	        else
	        {
	        	
	        	List<HistoricalRealtime> lHMinMax = HistoricalRealtimeLocalServiceUtil.findMinMaxRealTimesGroupedByBars(cfromWithOpenMarketsTimes.getTime(), to, shareId, companyId, groupId,timebars,market);	        	
	        	List<HistoricalRealtime> lHcloseValue = HistoricalRealtimeLocalServiceUtil.findCloseRealTimes(shareId, companyId, groupId, cfromWithOpenMarketsTimes.getTime(), to, lClosingPeriods);
	        	
	        	if (Validator.isNotNull(lHMinMax) && Validator.isNotNull(lHcloseValue) 
	        			&& lHcloseValue.size()==periodsToCalculate && lHMinMax.size()==periodsToCalculate)
	        			/* TODO OK */
	        	{
	        		  for (int j=0;j<lHcloseValue.size();j++)
	        		  {
	        			
	        			  HistoricalRealtime realtimeMINMAX = lHMinMax.get(j);
	        			  HistoricalRealtime realtimeCLOSE = lHcloseValue.get(j);
	        			  if (realtimeMINMAX.getMax_value()<=0  || realtimeMINMAX.getMin_value()<=0 || realtimeCLOSE.getValue()<=0)
							break;
	        			  
				          max_value = realtimeMINMAX.getMax_value();
				          min_value = realtimeMINMAX.getMin_value();
				          close_value =realtimeCLOSE.getValue();
	        			  
	        			  series.addBar(new BaseBar(endTime.plusMinutes(timebars*j),0.0,max_value ,min_value, close_value,0.0));

	        			  
	        		  }
	        	}
	        	
	        }
	        
			
		}
		}// end try
		catch (Exception e)
		{
			_log.debug(e.getMessage());
		}
		
        closeMinMaxBarTimeSeries = series;
		closeMinMaxBarTimeBarKey = keyBuilder.toString();
		return closeMinMaxBarTimeSeries;
	}

}
