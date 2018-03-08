package com.ibtrader.util;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.ibtrader.data.model.Realtime;
import com.ibtrader.data.service.RealtimeLocalServiceUtil;
import com.ibtrader.strategy.IBStrategySimpleMobileAverage;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;



public class MobileAvgUtil {

	
	private static Log _log = LogFactoryUtil.getLog(MobileAvgUtil.class);

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	/* avgMobile --> MEDIA MOVIL 
	 * oRTimeWidthRange -> Max y Minimo de la barra 
	 * oRTimeEnTramo --> Cierre de la barra
	 */

	public static boolean _IsBuySignalMM8_5MINBar(double _avgMobileSimple ,Realtime oRTimeWidthRange,double _WidthBarRangePercent , Realtime oRTimeEnTramoBar)
	{
					

		if (_avgMobileSimple<= (oRTimeWidthRange.getMax_value() - _WidthBarRangePercent)
				&& (oRTimeEnTramoBar.getValue() >= (oRTimeWidthRange.getMin_value() + _WidthBarRangePercent)))
		{
			_log.info("MM <= Max - Ancho = " + _avgMobileSimple+ "<="  + oRTimeEnTramoBar.getValue() + "-" + _WidthBarRangePercent);
			_log.info("Cierre  >= Min + Ancho = " + oRTimeEnTramoBar.getValue() + ">="  + oRTimeWidthRange.getMin_value() + "+" + _WidthBarRangePercent);
		}
		
		return (_avgMobileSimple  <= (oRTimeWidthRange.getMax_value() - _WidthBarRangePercent) && (oRTimeEnTramoBar.getValue() >= (oRTimeWidthRange.getMin_value() + _WidthBarRangePercent)));
	}
	
	
	public static boolean _IsSellSignalMM8_5MINBar(double _avgMobileSimple, Realtime oRTimeWidthRange,double _WidthBarRangePercent , Realtime oRTimeEnTramoBar)
	{
	
		
		if (_avgMobileSimple  >= (oRTimeWidthRange.getMin_value() + _WidthBarRangePercent)
				&& (oRTimeEnTramoBar.getValue() <= (oRTimeWidthRange.getMax_value() - _WidthBarRangePercent)))
		{
			_log.info("MM >= Min + Ancho = " + _avgMobileSimple + ">="  + oRTimeEnTramoBar.getValue() + "+" + _WidthBarRangePercent);
			_log.info("Cierre  <= Max - Ancho = " + oRTimeEnTramoBar.getValue() + "<="  + oRTimeWidthRange.getMin_value() + "-" + _WidthBarRangePercent);
		}
		
		return (_avgMobileSimple>= (oRTimeWidthRange.getMin_value() + _WidthBarRangePercent) && (oRTimeEnTramoBar.getValue() <= (oRTimeWidthRange.getMax_value() - _WidthBarRangePercent)));
	}
	
	/* DEVUELVE UNA LISTA DE FECHAS CON LOS PERIODOS DE TRAMOS DE BARRAS */
	private static List<String> getPeriodsMinutesMobileAvg(Date to, long PeriodN, long TimeBars)
	{
		SimpleDateFormat _sdf = new SimpleDateFormat(Utilities.__IBTRADER_SQL_DATE_);
		Calendar _cPeriodsMinutesMobileAvg = Calendar.getInstance();
		_cPeriodsMinutesMobileAvg.setTime(to);
		List<String> lAvgMobileDates = new ArrayList<String>();
		for (int j=1;j<=PeriodN;j++)
		{ 
			_cPeriodsMinutesMobileAvg.add(Calendar.MINUTE,  (int) - (TimeBars)); /* -5, -10, -15,-20 */
			lAvgMobileDates.add(_sdf.format(_cPeriodsMinutesMobileAvg.getTime()));
		}
		return lAvgMobileDates;
		
	}
	
	/* HORA DE LA NARRA ACTUAL, PARA CALCULAR LOS N PERIOS PREVIOS */
	public  static Double getSimpleAvgMobile(Date _ActualDateBar, long TimeBars,long shareId, long companyId, long groupId, long PeriodN, boolean Simulation )
	{
		
		Realtime _realtimeAvgMobile = null;
		List<String> lAvgPeriods = new ArrayList<String>();
		
		Calendar _cSimpleAvgMobileDateFrom = Calendar.getInstance();
		_cSimpleAvgMobileDateFrom.setTime(_ActualDateBar);
		
		
		lAvgPeriods = getPeriodsMinutesMobileAvg(_ActualDateBar, PeriodN, TimeBars);
		
		/* OJO, TENEMOS QUE VER QUE LAS MEDIAS MOVILES SON DE LAS BARRAS PREVIAS Y NO DE LA ACTUAL 
		_MM8DateFrom.add(Calendar.MINUTE, - (Periods  * TimeBars));
		*/ 
		
		/* MODIFICACION, MEDIAS MOVILES HASTA EL PERIDO N, N-1, N-2 */
		_cSimpleAvgMobileDateFrom.add(Calendar.MINUTE,  (int) - (PeriodN * TimeBars));
		
	//	System.out.println("getAvgMobileMM8 : Periodo -" + PeriodN + ", _oDateFrom:" + _sdf.format(_MM8DateFrom.getTime()) + ",_oDateTo:" + _sdf.format(_MM8DateTo.getTime()));
		
		Realtime simpleAvgMobile = RealtimeLocalServiceUtil.findSimpleMobileAvgGroupByPeriods(shareId, companyId, groupId, _cSimpleAvgMobileDateFrom.getTime(), _ActualDateBar, lAvgPeriods);
		/* CUANTOS PERIODOS CALCULADOS ??' */
		if (simpleAvgMobile!=null && simpleAvgMobile.getVolume()==lAvgPeriods.size())
		{
			return simpleAvgMobile.getValue();
		}
		else
			return null;
		
		
	}
	
	/* public static Realtime getAvgMobileMM8(Calendar _oDateFrom, Calendar _oDateTo, int Periods, int TimeBars, int ShareStrategyID, boolean Simulation)
	{
		return getAvgMobileMM8(_oDateFrom, _oDateTo, Periods, TimeBars, ShareStrategyID, 0, Simulation);
		
		
	}
	 */
	
	/* >= y <= */
	
	public static Realtime getMinMaxBarFromShare( Calendar _To, long TimeBars,long PeriodN, long shareId, long companyId, long groupId,  boolean Simulation)
	{
		Calendar _TimeBarWidthFrom = Calendar.getInstance(); 
		_TimeBarWidthFrom.setTimeInMillis(_To.getTimeInMillis());		
				
		Calendar _TimeBarWidthTo = Calendar.getInstance();
		_TimeBarWidthTo.setTimeInMillis(_To.getTimeInMillis());							 
		_TimeBarWidthFrom.add(Calendar.MINUTE, (int) -((PeriodN+1) * TimeBars));
		_TimeBarWidthTo.add(Calendar.MINUTE, (int) -(PeriodN * TimeBars));   // 20150228 errores en el concepto ini fin de barra
		_TimeBarWidthTo.add(Calendar.SECOND, -1);

		Realtime oRTimeWidthRange = RealtimeLocalServiceUtil.findMinMaxRealTime(_TimeBarWidthFrom.getTime(), _TimeBarWidthTo.getTime(), shareId, companyId, groupId);	
		
		return oRTimeWidthRange;
	}
	
	/*public static Realtime getMinMaxBarFromShare( Calendar _To, int TimeBars, int ShareStrategyID, boolean Simulation )
	{
		
		
		return getMinMaxBarFromShare(_To, TimeBars,ShareStrategyID,0, Simulation);
	}
*/
	

}