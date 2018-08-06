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

import com.ibtrader.data.model.Realtime;
import com.ibtrader.data.service.RealtimeLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;

public class DirectionalMovementADXRUtil {

	
	// +DM
	private static  double plusDirectionMovement;
	private  static double minusDirectionMovement;// –DM
	
	private static  double closevalue; // periodo  anterior
	private static  double maxvalue; // // periodo actual
	private static  double minvalue; // periodo actual
	
	private  static  long trueRange; // dia de hoy
	
	private static  List<Map<String, Double>> listADXR = new ArrayList<Map<String, Double>>();

	private static String _TRUERANGE_KEY = "TrueRange";
	private static String _MAX__KEY = "MAX";
	private static String _MIN_KEY = "MIN";
	private static String _PLUS_AVG_DM_KEY = "PLUS_AVG_DM";
	private static String _MINUS_AVG_DM_KEY = "MNUS_AVG_DM";
	private static String _AVG_TRUERANGE_KEY = "AVG_TRUERANGE";
	private static String _PLUS_DI_KEY = "PLUS_DI";
	private static String _MINUS_DI_KEY = "MNUS_DI";
	private static String _PLUS_DM_KEY = "PLUS_DM";
	private static String _MINUS_DM_KEY = "MNUS_DM";
	private static  String _DX_KEY = "DX";
	private static  String _ADX_KEY = "ADX";
	
	/* 
	 * ATTREIBUTES 
	 */
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
	
	/* para las medias armonizadas de TRUERANGE Y DM 
	 * 
	 * El primer TR14 es la media simple de los 14 primeros TR, es decir la suma de estos valores dividido entre 14.
		Los siguientes TR14 = (13 / 14 ) * Anterior TR14  + TR1 actual / 14
	 * 
	 * */
	private static double getAvgTrueRangeIndicator(double currentTrueRange, double avgTrueRangePrevious, long periods, long curretIndex)
	{
		double  totalIndicator1=0d;
		double  totalIndicator2=0d;
		
		double  avgIndicador=0d;
		
		if (curretIndex<=periods) // hasta el resultado 15 no hay datos 
			return 0; 
		
		//here is the logic 
//		private List<Map<String, Double>> listADXR = new ArrayList<Map<String, Double>>();
		
		if (curretIndex==periods+1) // el primer TR 14
		{
			long index = 1;
			for (Map<String, Double> map : listADXR) {
			        /*  for (Map.Entry<String, Double> entry : map.entrySet()) { */		    	
			    	 // hay que buscar el primer TR14
			    	
		    		if (index>curretIndex-periods)
		    			totalIndicator1 += map.get(_TRUERANGE_KEY);
		    		if (index>curretIndex-periods-1)
		    			totalIndicator2 += map.get(_TRUERANGE_KEY);
			    	
			    	index++; 		    	
			    	// salimos para la fila actual de index 
			    	
			    	
			    	if (index>=curretIndex)  // el ultimo 
			    	{
			    		avgIndicador = totalIndicator1 -   (totalIndicator2 / periods)  + currentTrueRange;
			    		break;
			    	}		 		
			}
		}
		else
		{
			avgIndicador = avgTrueRangePrevious - (avgTrueRangePrevious / periods) + currentTrueRange;
			
		}
		
		return avgIndicador;
		
	}
	public DirectionalMovementADXRUtil(Date dateTo, long timebar, long periods, long shareId, long companyId, long groupId) {
		getAvgDirectionalRate(dateTo,  timebar, periods, shareId, companyId, groupId);
	}
	/* para las medias armonizadas de  DM */
	private static double getAvgDirectionalMovementIndicator(double currentDM, double avgDMPrevious, String keyDM,  long periods, long curretIndex)
	{
		double  totalIndicator1=0d;
		
		
		double  avgIndicador=0d;
		
		if (curretIndex<=periods) // hasta el resultado 15 no hay datos 
			return 0;
		
		//here is the logic 
//		private List<Map<String, Double>> listADXR = new ArrayList<Map<String, Double>>();
		
		if (curretIndex==periods+1) // el primer TR 14
		{
			long index = 1;
			for (Map<String, Double> map : listADXR) {
			        /*  for (Map.Entry<String, Double> entry : map.entrySet()) { */		    	
			    	 // hay que buscar el primer TR14
			    	
					if (index>curretIndex-periods-1)
		    			totalIndicator1 += map.get(keyDM);		    	
			    	
			    	index++;		    	
			    	// salimos para la fila actual de index 
			    	
			    	
			    	if (index>=curretIndex)  // el ultimo 
			    	{
			    		avgIndicador = totalIndicator1 -  (totalIndicator1 / periods)  + currentDM;
			    		break;
			    	}		 		
			}
		}
		else
		{
			avgIndicador = avgDMPrevious - (avgDMPrevious / periods) + currentDM;
			
		}
		
		return avgIndicador;
		
	}
	
	
	private static double getADX(double currentDX, double avgADXPrevious,long periods, long curretIndex)
	{
		double  totalIndicator1=0d;
		double  avgIndicador=0d;
		
		if (curretIndex<periods*2) // hasta el resultado 28  (periodo *2)   no hay datos 
			return 0;
		
		//here is the logic 
//		private List<Map<String, Double>> listADXR = new ArrayList<Map<String, Double>>();
		
		if (curretIndex==periods*2) // el primer adxr 28
		{
			long index = 1;
			for (Map<String, Double> map : listADXR) {
			        /*  for (Map.Entry<String, Double> entry : map.entrySet()) { */		    	
			    	 // hay que buscar el primer TR14
			    	
		    		if (index >= curretIndex-periods+1) // fila 15 y adelante
		    			totalIndicator1 += map.get(_DX_KEY);		    	
			    	
			    	index++;		    	
			    	// salimos para la fila actual de index 
			    	
			    	
			    	if (index>curretIndex)
			    	{
			    		avgIndicador =  (totalIndicator1 / periods) ;  // PROMEDIO 
			    		break;
			    	}		 		
			}
		}
		else
		{
			avgIndicador = ((avgADXPrevious  * periods -1) + currentDX ) / periods ;
			
		}
		
		return avgIndicador;
		
	}
	
	

	/*
	 * ADXR- Average Directional Movement Index Rating
	 * dateTo --> barra actual , 15:05:00, 
	 * timebar --> 5 en minutos
	 * periods --> para calcular el ADXR , normalmente 14 
	 */
	private void  getAvgDirectionalRate(Date dateTo, long timebar, long periods, long shareId, long companyId, long groupId)
	{
		long periodsToCalculate = periods * 3; // https://estrategiastrading.com/indicador-adx-formula/
		double  maxvalueN1 = 0d;
		double  minvalueN1 = 0d;
		
		
	    Calendar  cPeriodFrom = Calendar.getInstance(); 
	    cPeriodFrom.setTimeInMillis(dateTo.getTime());
	    cPeriodFrom.add(Calendar.MINUTE, - new Long(timebar * (periods * 2 + 1)  ).intValue());
	    
	    double avgTrueRange = 0d;
		double avgPDM = 0d; // +dm
		double avgMDM = 0d; // -dm
		double avgADX = 0d; // -dm
		double avgDX = 0d; // -dm
	    
		
		double plusDI = 0d; // +dm
		double minusDI = 0d; // -dm
		
		double lastPlusDI = 0d; // +dm
		double lastMinusDI = 0d; // -dm
		
		double avgLastTrueRange = 0d;
		double avgLastPDM = 0d; // +dm
		double avgLastMDM = 0d; // -dm
		
		double avgLastADX = 0d; // -dm
		
		double avgADX_N14 = 0d; // ADX DE 14 VELAS ATRAS 
		double avgADXR = 0d; // ADX DE 14 VELAS ATRAS 
		
	    
		for (int j=1;j<=periodsToCalculate;j++)
		{
			 /* MAX Y MIN POR BARRA  15:05:00  */
			Calendar  cPeriodTo = Calendar.getInstance();
			cPeriodTo.setTime(cPeriodFrom.getTime());
			cPeriodTo.add(Calendar.MINUTE, new Long(timebar).intValue());
			//cPeriodTo.add(Calendar.SECOND,  -1);
		    
			double trueRange = 0d;
			
		    
			Date dateMinMaxFrom = cPeriodFrom.getTime();		    		
	        Date dateMinMaxTo = cPeriodTo.getTime();
		    
			Realtime minMax = RealtimeLocalServiceUtil.findMinMaxRealTime(dateMinMaxFrom, dateMinMaxTo, shareId, companyId, groupId);
			Realtime closeValue = RealtimeLocalServiceUtil.findLastRealTimeLessThanDate(shareId, companyId, groupId, dateMinMaxTo);
			
			if (minMax.getMax_value()<=0  || minMax.getMin_value()<=0 ||  Validator.isNull(closeValue))
				break;
			
			maxvalue = minMax.getMax_value();
			minvalue = minMax.getMin_value();
			closevalue  = closeValue.getValue();
			
			
			Map<String,Double> _DIData = new HashMap<String,Double>();
			if (j==1)  
			{
				_DIData.put(_TRUERANGE_KEY, 0d);
				_DIData.put(_MAX__KEY, 0d);
				_DIData.put(_MIN_KEY, 0d);		
				_DIData.put(_PLUS_DM_KEY, 0d);
				_DIData.put(_MINUS_DM_KEY, 0d);
				_DIData.put(_AVG_TRUERANGE_KEY, 0d);
				_DIData.put(_PLUS_AVG_DM_KEY, 0d);
				_DIData.put(_MINUS_AVG_DM_KEY, 0d);							
				_DIData.put(_PLUS_DI_KEY, 0d);
				_DIData.put(_MINUS_DI_KEY, 0d);				
				_DIData.put(_DX_KEY, 0d);
				_DIData.put(_ADX_KEY, 0d);
				
			}
			else
			{
				

				trueRange = (double) Stream.of(Math.abs(maxvalue-minvalue), Math.abs(maxvalue-closevalue), Math.abs(minvalue-closevalue)).max(Comparator.comparing(Double::valueOf)).get();;
				double diffmax_maxN1 = maxvalue - maxvalueN1 ;
				double diffmin_minN1 = minvalueN1 - minvalue ;
				
				plusDirectionMovement = diffmax_maxN1 >0 && diffmax_maxN1 >  diffmin_minN1  ? diffmax_maxN1: 0;
				minusDirectionMovement = diffmin_minN1 > 0 && diffmin_minN1 >  diffmax_maxN1  ? diffmin_minN1: 0;

				
				_DIData.put(_TRUERANGE_KEY, trueRange);
				_DIData.put(_MAX__KEY, maxvalue - maxvalueN1);
				_DIData.put(_MIN_KEY, minvalueN1 - minvalue);	 	
				
				_DIData.put(_PLUS_DM_KEY, plusDirectionMovement);
				_DIData.put(_MINUS_DM_KEY, minusDirectionMovement);
				
				if (j>=periods+1) // la primera con el true range relleno   
				{
					avgTrueRange = getAvgTrueRangeIndicator(trueRange, avgLastTrueRange, periods, j);
					avgPDM       = getAvgDirectionalMovementIndicator(plusDirectionMovement, avgLastPDM, _PLUS_DM_KEY, periods, j); // +dm avg
					avgMDM       = getAvgDirectionalMovementIndicator(minusDirectionMovement, avgLastMDM, _MINUS_DM_KEY, periods, j); // +dm avg
					
					_DIData.put(_AVG_TRUERANGE_KEY, avgTrueRange);
					_DIData.put(_PLUS_AVG_DM_KEY, avgPDM);
					_DIData.put(_MINUS_AVG_DM_KEY, avgMDM);
								
					
					plusDI = avgPDM / avgTrueRange * 100; // +dm
					minusDI = avgMDM / avgTrueRange * 100; // -dm
					
					
					
					/* falta el round */
					_DIData.put(_PLUS_DI_KEY, plusDI);
					_DIData.put(_MINUS_DI_KEY, minusDI);
					
				
					
					avgDX = Math.abs((_DIData.get(_PLUS_DI_KEY)-_DIData.get(_MINUS_DI_KEY)))  /  (_DIData.get(_PLUS_DI_KEY) +_DIData.get(_MINUS_DI_KEY))*100;
					_DIData.put(_DX_KEY,  avgDX);
				
					if (j>=periods*2) // la primera con el ADX relleno
					{
						avgADX = getADX(avgDX, avgLastADX, periods, j);					
						if (j==periods*2)  avgADX_N14 =  avgADX;  //  avgDX 14 velas atras
					}					
					if (j>=periods*3) // la primera con el ADXR relleno   						
						avgADXR = (avgADX + avgADX_N14) / 2;
					
					_DIData.put(_ADX_KEY, avgADX);
					
					
				}
				
				avgLastTrueRange = avgTrueRange;
				avgLastPDM = avgPDM; // +dm
				avgLastMDM = avgMDM; // -dm
				avgLastADX = avgADX;
				
				if (j!=periodsToCalculate) // guardamos el ultimo para obtener el corte 
				{
					lastPlusDI = plusDI; // +dm
					lastMinusDI = minusDI; // -dm
				}
				System.out.println("max:" + maxvalue + ",min:" + minvalue + ",close:" + closevalue + ",trueRange:" + trueRange + ",diffmax_maxN1:" 
						+ diffmax_maxN1 + ",diffmin_minN1:" + diffmin_minN1 + ",plusDirectionMovement:" 
						+ plusDirectionMovement + ",minusDirectionMovement:" + minusDirectionMovement
						+ ",avgTrueRange:" + avgTrueRange  + ",avgPDM:" + avgPDM + ",avgMDM:"+ avgMDM + ",_PLUS_DI_KEY:" + _DIData.get(_PLUS_DI_KEY) + ",_MINUS_DI_KEY:" + _DIData.get(_MINUS_DI_KEY) + ",_DX_KEY:" + _DIData.get(_DX_KEY));
				
				
				System.out.println("");
				
				
			}
			listADXR.add(_DIData);
			
			maxvalueN1  = maxvalue; 
			minvalueN1  = minvalue;
		    cPeriodFrom.add(Calendar.MINUTE,  new Long(timebar).intValue());
		         
			
		}
		
		 
		_DX = avgDX;
		_plusDI = plusDI; 
		_minusDI = minusDI;		
		_lastPlusDI = lastPlusDI; 
		_lastMinusDI = lastMinusDI;		
		_ADX = avgADX;
		_ADXR = avgADXR;
		
		
		
	}
	public static void main (String[] args) {

		/* 
		 * 2018-07-25 01:39:59.999000
		 */
		Calendar cData = Calendar.getInstance();
		cData.set(2018, 6, 25, 1, 40, 0);
		DirectionalMovementADXRUtil ADXR =  new DirectionalMovementADXRUtil(cData.getTime(), 5, 14, 2602, 20116, 101213);
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
