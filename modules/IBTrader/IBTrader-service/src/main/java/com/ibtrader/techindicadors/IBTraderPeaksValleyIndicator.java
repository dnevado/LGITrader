package com.ibtrader.techindicadors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;


/**
 * IBTraderStochasticPeaksValleyIndicator
 *  *  
 *  
 *  TIENEN QUE VENIR ORDENADOS POR FECHA DESCENENTE 
 *  
 */
public class IBTraderPeaksValleyIndicator {

	// PEAKS
	private double[] values;
	// VALLEYS	
	private int  num_bars_peakvalley = 3; // para considerar un valle o un peak
	
	public enum StochasticValleysPeaksMode {
	    FIRST_PEAK ("FIRST_PEAK"),
	    LAST_PEAK ("LAST_PEAK"),
	    FIRST_VALLEY ("FIRST_VALLEY"),
	    LAST_VALLEY  ("LAST_VALLEY"), 	    
	    ALL_VALLEYS  ("ALL_VALLEYS"),
	    ALL_PEAK  ("ALL_PEAKS"),	    ;
	   
		private String name;       

	    private StochasticValleysPeaksMode(String s) {
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


	private static Log _log = LogFactoryUtil.getLog(IBTraderPeaksValleyIndicator.class);

	/* MINIMOS DECREACIENTES , 
	 * 1. EL VALOR ACTUAL ES MENOR QUE EL VALLE ENCONTRADO
	 * 2. NO HAY NINGUN VALOR POR ENCIMA DE EL EN EL INTERVALO 
	 *  */
	
	public boolean IsMinimumIncreasing() {		
		boolean IsMinimumIncreasing = Boolean.FALSE;
		try {
			
			this.getAllValley();
			if (valleys_values.size()==2)		
			{
				double firtvalley =  valleys_values.size()>0 ? valleys_values.get(0).doubleValue() : 0;				

				IsMinimumIncreasing  =  valleys_values.get(0) > valleys_values.get(1) &&
												min_until_first_valley >= firtvalley;
			}		
			if (IsMinimumIncreasing)
			{
				_log.debug("IBTraderStochasticPeaksValleyIndicator IsMinimumIncreasing : "  + IsMinimumIncreasing + ", firtvalley found: index:" + this.foundedIndFirstValley);
				_log.debug("Prices values : "   +  Arrays.toString(this.values)) ;
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return IsMinimumIncreasing;
	}
	
	
	public boolean IsMaximumDecreasing() {		
		boolean IsMaximumDecreasing = Boolean.FALSE;
		try {
			
			this.getAllPeak();		
			
			if (peaks_values.size()==2)		
			{
				double firtpeak =   peaks_values.size()>0 ? peaks_values.get(0).doubleValue() : 0;			
				IsMaximumDecreasing  =  peaks_values.get(1) > peaks_values.get(0) &&
						max_until_first_peak <= firtpeak; 
			}
			if (IsMaximumDecreasing)
			{
				_log.debug("IBTraderStochasticPeaksValleyIndicator IsMaximumDecreasing : "  + IsMaximumDecreasing + ", firtpeak found: index:" + this.foundedIndFirstPeak);
				_log.debug("Prices values : "  +  Arrays.toString(this.values)) ;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return IsMaximumDecreasing;
	}
	
	public IBTraderPeaksValleyIndicator(double[] values) {
		super();		
		this.values = values;
		peaks_values = new ArrayList<Double>();
		valleys_values = new ArrayList<Double>();
	
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


	public Double[]  getFirtPeak() throws Exception {
		
		return getPeaks(StochasticValleysPeaksMode.FIRST_PEAK);
	}
	public Double[]  getAllPeak() throws Exception {
		
		return getPeaks(StochasticValleysPeaksMode.ALL_PEAK);
	}
	public Double[]  getLastPeak() throws Exception {
		
		return getPeaks(StochasticValleysPeaksMode.LAST_PEAK);
	}
	
	public Double[]  getFirtValley() throws Exception {
		
		return getValleys(StochasticValleysPeaksMode.FIRST_VALLEY);
	}
	public Double[]  getAllValley() throws Exception {
		
		return getValleys(StochasticValleysPeaksMode.ALL_VALLEYS);
	}
	public Double[]  getLastValley() throws Exception {
		
		return getValleys(StochasticValleysPeaksMode.LAST_VALLEY);
	}

	
	private Double[]  getPeaks(StochasticValleysPeaksMode mode) throws Exception {
		
		peaks_values = new ArrayList<Double>();

		 for (int i = 0; i < values.length-num_bars_peakvalley; i++)
		 {	
			 	
			/* VAMOS ALMACENANDO EL MINIMO VALOR HASTA EL PRIMER VALLE */
			 max_until_first_peak = values[i] > max_until_first_peak ? values[i] : max_until_first_peak;
		 
		    if (i>=num_bars_peakvalley)
		    {	 
			 
				if (values[i - 1] < values[i] && values[i] > values[i + 1]
			 			&& values[i - 2] < values[i-1] && values[i+1] > values[i + 2]
			 					&& values[i - 3] < values[i-2] && values[i+2] > values[i + 3] )
				{    
						
						peaks_values.add(values[i]);
						if (peaks_values.size()==1) // INDICE DEL PRIMER PICO ENCONTRADO 
							foundedIndFirstPeak = i;
						foundedIndLastPeak = i; // 
						
						if (peaks_values.size()==2) break;
						
						
				}
		    }
		 } 
		
		 Double[] array = (Double[]) peaks_values.toArray(new Double[peaks_values.size()]);
	 
		 return array; // fill the array 		 
	}
	
	private Double[]  getValleys(StochasticValleysPeaksMode mode) throws Exception {
		
		
		valleys_values = new ArrayList<Double>();

		
		 for (int i = 0; i < values.length-num_bars_peakvalley; i++)
		 {	
			 
				/* VAMOS ALMACENANDO EL MINIMO VALOR HASTA EL PRIMER VALLE */
			 	min_until_first_valley = values[i] < min_until_first_valley ? values[i] : min_until_first_valley;
			 
			    if (i>=num_bars_peakvalley)
			    {
				 	if (values[i - 1] > values[i] && values[i] < values[i + 1]
				 			&& values[i - 2] > values[i-1] && values[i+1] < values[i + 2]
				 					&& values[i - 3] > values[i-2] && values[i+2] < values[i + 3])
				 	{
						valleys_values.add(values[i]);
						if (valleys_values.size()==1) // INDICE DEL PRIMER PICO ENCONTRADO 
							foundedIndFirstValley = i;
						foundedIndLastValley = i; // 
						
						if (valleys_values.size()==2) break;
						

						
				 	}	
			    }
		}	 
		 Double[] array = (Double[]) valleys_values.toArray(new Double[valleys_values.size()]);
			 
		 return array; // fill the array 
	}

	public static void main(String[] args) throws Exception{
		double[] data = {1,3,4,5,4,3.2,3.1, 4, 3.1,2,2.5,2.6};
		
		IBTraderPeaksValleyIndicator test = new IBTraderPeaksValleyIndicator(data);
		test.getAllPeak();
		test.getAllValley();
		

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