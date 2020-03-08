package com.ibtrader.techindicadors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;


/**
 * IBTraderPeaksValleyIndicator
 * 
 *  3 BARRAS POR DEFECTO DE VERIFICACION  A LA BARRA QUE VERIFICAMOS
 *  
 *  TIENEN QUE VENIR ORDENADOS POR FECHA DESCENENTE 
 *  
 */
public class IBTraderPricesPeaksValleyIndicator {

	// PEAKS
	private double[] max_values;
	// VALLEYS
	private double[] min_values;
	
	private int  num_bars_peakvalley = 3; // para considerar un valle o un peak
	
	public enum PricesValleysPeaksMode {
	    FIRST_PEAK ("FIRST_PEAK"),
	    LAST_PEAK ("LAST_PEAK"),
	    FIRST_VALLEY ("FIRST_VALLEY"),
	    LAST_VALLEY  ("LAST_VALLEY"), 	    
	    ALL_VALLEYS  ("ALL_VALLEYS"),
	    ALL_PEAK  ("ALL_PEAKS"),	    ;
	   
		private String name;       

	    private PricesValleysPeaksMode(String s) {
	        name = s;
	    }
	}
	
	
	// PEAKS
	private List<Double> peaks_values ;
	// VALLEYS
	private List<Double> valleys_values;
	
	private int foundedIndFirstPeak;
	private int foundedIndLastPeak;
	private int foundedIndFirstValley;
	private int foundedIndLastValley;
	
	/* VALOR MINIMO DE LA SERIE ENCONTRADO HASTA EL PRIMER VALLE PARA COMPARARLO CON EL VALLE  Y VER 
	 * SI LA LINEA RECTA NO SE CORTA ENTRE MEDIAS, PARA QUE SEA UN MINIMO CRECIENTE  * /  
	 */
	private double min_until_first_valley;
	private double max_until_first_peak;  

	double relativeMinOfPeak = 0;
	double RelativeMaxOfValley = 0;

	
 /* 	private Double current_max;
	private Double current_min;
	*/
	private static Log _log = LogFactoryUtil.getLog(IBTraderPricesPeaksValleyIndicator.class);

	/* MINIMOS DECREACIENTES , 
	 * 1. EL VALOR ACTUAL ES MENOR QUE EL VALLE ENCONTRADO
	 * 2. NO HAY NINGUN VALOR POR ENCIMA DE EL EN EL INTERVALO 
	 *  */
	public boolean IsMinimumDecreasing() {		
		boolean IsMinimumDecreasing = Boolean.FALSE;
		try {
			this.getAllValley();
			
			if (valleys_values.size()==2) // valles decrecientes y que no haya un valor menor entre ellos 
			{
				double firtvalley =  valleys_values.size()>0 ? valleys_values.get(0).doubleValue() : 0;				
				IsMinimumDecreasing  =  valleys_values.get(0) > valleys_values.get(1) &&
													min_until_first_valley >= firtvalley; 
			}
			if (IsMinimumDecreasing)
			{
				_log.debug("IBTraderPricesPeaksValleyIndicator IsMinimumDecreasing : "  + IsMinimumDecreasing + ", valley found: index:" + this.foundedIndFirstValley);
				_log.debug("Prices values : "  +    Arrays.toString(this.min_values)) ;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return IsMinimumDecreasing;
	}
	public boolean IsMaximumIncreasing() {		
		boolean IsMaximumIncreasing = Boolean.FALSE;
		try {
			this.getAllPeak();		
			
			if (peaks_values.size()==2)	
			{
				double firtpeak =   peaks_values.size()>0 ? peaks_values.get(0).doubleValue() : 0;			
				IsMaximumIncreasing  =  peaks_values.get(1) > peaks_values.get(0) &&
								max_until_first_peak <= firtpeak; 
			}
			if (IsMaximumIncreasing)
			{
				_log.debug("IBTraderPricesPeaksValleyIndicator IsMaximumIncreasing : "  + IsMaximumIncreasing + ", firtpeak found: index:" + this.foundedIndFirstPeak);
				_log.debug("Prices values : " + Arrays.toString(this.max_values)) ;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return IsMaximumIncreasing;
	}
	
	public IBTraderPricesPeaksValleyIndicator(double[] max_values, double[] min_values) {
		super();
		this.max_values = max_values;
		this.min_values = min_values;
		peaks_values = new ArrayList<Double>();
		valleys_values = new ArrayList<Double>();

	}

	

	
	/* ESTOS SE RELLENAN CON LOS VALORES MAXIMOS MINIMOS QUE ENCONTRAMOS ENTRE LOS DOS PICOS O VALLES QUE NO SERVIRAN PARA 
	 * CONFIRMAR LA TENDENCIA DE LA DIVERGENCIA COMO SOPORTES Y RESISTENCIAS 
	 */
	

	public double getRelativeMinOfPeak() {
		
		return this.relativeMinOfPeak;
		
	}
	
	public double getRelativeMaxOfValley() {
			return this.RelativeMaxOfValley;
	}
	
	public int getFoundedIndFirstPeak() {
		return foundedIndFirstPeak;
	}


	public int getFoundedIndLastPeak() {
		return foundedIndLastPeak;
	}


	public int getFoundedIndFirstValley() {
		return foundedIndFirstValley;
	}


	public int getFoundedIndLastValley() {
		return foundedIndLastValley;
	}


	public void  getAllValley() throws Exception {
		
		 getValleys(PricesValleysPeaksMode.ALL_VALLEYS);
	}
	
	public void   getAllPeak() throws Exception {
		
		 getPeaks(PricesValleysPeaksMode.ALL_PEAK);
	}
	/* 
	public Double[]  getFirtPeak() throws Exception {
		
		return getPeaks(PricesValleysPeaksMode.FIRST_PEAK);
	}
	
	public Double[]  getLastPeak() throws Exception {
		
		return getPeaks(PricesValleysPeaksMode.LAST_PEAK);
	}
	
	public Double[]  getFirtValley() throws Exception {
		
		return getValleys(PricesValleysPeaksMode.FIRST_VALLEY);
	}
	public Double[]  getAllValley() throws Exception {
		
		return getValleys(PricesValleysPeaksMode.ALL_VALLEYS);
	}
	public Double[]  getLastValley() throws Exception {
		
		return getValleys(PricesValleysPeaksMode.LAST_VALLEY);
	}*/

	/* SACAMOS LOS DOS PICOS MAS CERCANOS AL PRECIO ACTUAL CONTANDO CON LA BARRA ACTUAL  */
	private Double[]  getPeaks(PricesValleysPeaksMode mode) throws Exception {
		
		peaks_values = new ArrayList<Double>();
		
		
		for (int i = 0; i < max_values.length-num_bars_peakvalley; i++)
		 {	
			 	
			/* VAMOS ALMACENANDO EL maximo  VALOR HASTA EL PRIMER PICO  */
			 max_until_first_peak = max_values[i] > max_until_first_peak ? max_values[i] : max_until_first_peak;
		 
			
		    if (i>=num_bars_peakvalley)
		    {	 
			 
				if (max_values[i - 1] < max_values[i] && max_values[i] > max_values[i + 1]
			 			&& max_values[i - 2] < max_values[i-1] && max_values[i+1] > max_values[i + 2]
			 					&& max_values[i - 3] < max_values[i-2] && max_values[i+2] > max_values[i + 3] )
				{    
						peaks_values.add(max_values[i]);
						if (peaks_values.size()==1) // INDICE DEL PRIMER PICO ENCONTRADO 
							foundedIndFirstPeak = i;
						foundedIndLastPeak = i; // 
						
						if (peaks_values.size()==2) break;
						
				}
		    }
		 } 
		 Double[] array = (Double[]) peaks_values.toArray(new Double[peaks_values.size()]);
	 
		 double[] prices_between_peak;
		 prices_between_peak  = Arrays.copyOfRange(min_values, this.foundedIndFirstPeak, this.foundedIndLastPeak + 1);
		 if (prices_between_peak.length >0)
		 {
			 this.relativeMinOfPeak =  Arrays.stream(prices_between_peak).min().getAsDouble();
		 }
		   
		 
		 return array; // fill the array 		 
	}
	
	private Double[]  getValleys(PricesValleysPeaksMode mode) throws Exception {
		
		
		valleys_values = new ArrayList<Double>();

		 for (int i = 0; i < min_values.length-num_bars_peakvalley; i++)
		 {	
			 	/* VAMOS ALMACENANDO EL MINIMO VALOR HASTA EL PRIMER VALLE */
			 	min_until_first_valley = min_values[i] < min_until_first_valley ? min_values[i] : min_until_first_valley;
		
				
			    if (i>=num_bars_peakvalley)
			    {
				 	if (min_values[i - 1] > min_values[i] && min_values[i] < min_values[i + 1]
				 			&& min_values[i - 2] > min_values[i-1] && min_values[i+1] < min_values[i + 2]
				 					&& min_values[i - 3] > min_values[i-2] && min_values[i+2] < min_values[i + 3])
				 	{
				 		
				 		valleys_values.add(min_values[i]);
						if (valleys_values.size()==1) // INDICE DEL PRIMER PICO ENCONTRADO 
							foundedIndFirstValley = i;
						foundedIndLastValley = i; // 
						
						if (valleys_values.size()==2) break;
				 	}	
			    }
		}	 
		
		 Double[] array = (Double[]) valleys_values.toArray(new Double[valleys_values.size()]);
		
		 double[] prices_between_valley;
		 prices_between_valley  = Arrays.copyOfRange(max_values, this.foundedIndFirstValley, this.foundedIndLastValley + 1);
		 if (prices_between_valley.length >0)
		 {
			 this.RelativeMaxOfValley =  Arrays.stream(prices_between_valley).max().getAsDouble();
		 }
		 
		 return array; // fill the array 
	}

	public static void main(String[] args) throws Exception{
		
		double[] max = {1,3,4,5,4,3.2,3.1, 0.8,1,1,1,1,2,3,4,3.1,2,1,3}; //  5 y 4
		
		double[] min = {0.1,3,4,5,0.2,4,3.2,3.1,1,1,0.09,1,2,3,4, 3.1,2,1,3};
		IBTraderPricesPeaksValleyIndicator test = new IBTraderPricesPeaksValleyIndicator(max, min);
		test.getAllPeak();
		test.getAllValley();
		
		System.out.println("Price Ind First Peak:" + test.getFoundedIndFirstPeak());
		System.out.println("Price Ind Last Peak:" + test.getFoundedIndLastPeak());
		System.out.println("Price Minimo relativo Peak:" + test.getRelativeMinOfPeak());;

		
		double[] macd = {1,3,4,5,4,3.2,3.1, 0.8,1,1,1,1,2,3,4,3.1,2,1,3}; //  5 y 4
		IBTraderPeaksValleyIndicator test2 = new IBTraderPeaksValleyIndicator(macd);
		test2.getAllPeak();
		test2.getAllValley();
		
		System.out.println("MACD Ind First Valley:" + test2.getFoundedIndFirstPeak());
		System.out.println("MACD Ind Last Peak:" + test2.getFoundedIndLastPeak());
		
		
		/* IBTraderPricesPeaksValleyIndicator test = new IBTraderPricesPeaksValleyIndicator(max, min);
		test.getAllPeak();
		test.getAllValley();
		*/
	/*	IBTraderPeaksValleyIndicator test = new IBTraderPeaksValleyIndicator(data, data);
		System.out.println("FIRST peaks:");	
		for (Double valley : test.getPeaks(PricesValleysPeaksMode.FIRST_PEAK))
		{
			System.out.println(valley);	
		}
		 test = new IBTraderPeaksValleyIndicator(data, data);
		System.out.println("LAST  peaks:");
		for (Double valley : test.getPeaks(PricesValleysPeaksMode.LAST_PEAK))
		{
			System.out.println(valley);	
		}	
		 test = new IBTraderPeaksValleyIndicator(data, data);

		System.out.println("ALL  peaks:");

		for (Double valley : test.getPeaks(PricesValleysPeaksMode.ALL_PEAK))
		{
			System.out.println(valley);	
		}
		 test = new IBTraderPeaksValleyIndicator(data, data);
		System.out.println("FIRST valley:");
		for (Double valley : test.getValleys(PricesValleysPeaksMode.FIRST_VALLEY))
		{
			System.out.println(valley);	
		}
		 test = new IBTraderPeaksValleyIndicator(data, data);
			System.out.println("LAST   valley:");
		for (Double valley : test.getValleys(PricesValleysPeaksMode.LAST_VALLEY))
		{
			System.out.println(valley);	
		}
		System.out.println("ALL   valley:");
		 test = new IBTraderPeaksValleyIndicator(data, data);
		for (Double valley : test.getValleys(PricesValleysPeaksMode.ALL_VALLEYS))
		{
			System.out.println(valley);	
		}
	 */
		
	}
}