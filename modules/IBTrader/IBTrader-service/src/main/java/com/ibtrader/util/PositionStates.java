package com.ibtrader.util;

/* usadas para colocar ordenes de compra y para marcar en las tablas la  operacion */
public class PositionStates {
	
	
	
	public enum statusTWSCallBack {
	    Cancelled ("Cancelled"),
	    PendingSubmit  ("PendingSubmit"),
	    PendingCancel  ("PendingCancel"),
	    PreSubmitted ("PreSubmitted"),
	    Submitted ("Submitted"),	 
	    Filled("Filled"),
	    Inactive ("Inactive");
	   
	    private String name;       

	    private statusTWSCallBack(String s) {
	        name = s;
	    }
	}
	
	public enum statusTWSFire {
	    BUY ("BUY"),
	    SELL ("SELL"),
	    SHORT ("SHORT");
	    
	    private String name;       

	    private statusTWSFire(String s) {
	        name = s;
	    }
	}
	
	public enum status {	 
	    PENDING_BUY ("PENDING_BUY"),
	    BUY_OK ("BUY_OK"),
	    PENDING_SELL ("PENDING_SELL"),
	    SELL_OK ("SELL_OK"),
	    CANCEL_BUY ("CANCEL_BUY"),
	    CANCEL_SELL ("CANCEL_SELL");	    

	    private String name;       

	    private status(String s) {
	        name = s;
	    }
	}
	public enum ordertypes {
	    LMT ("LMT"), // LIMITADA 
	    MKT ("MKT"),  // MERCADO 
	    TRAIL ("TRAIL");  // TRAIL , CON PORCENTAJE DE STOP 
	    
	    private String nametypes;       

	    private ordertypes (String s) {
	    	nametypes = s;
	    }
	}
	public enum HISTORICALDATA_DURATION {
	    SECONDS ("S");
	    private String nametypes;       

	    private HISTORICALDATA_DURATION (String s) {
	    	nametypes = s;
	    }
	}
	public enum position_mode_type {
		SIMULATED ("SIMULATED"), 
		REAL ("REAL"),   
		BACKTESTING ("BACKTESTING");   
	    
	    private String position_mode_type;       

	    private position_mode_type (String s) {
	    	position_mode_type = s;
	    }
	}
	
	
	/* private enum status {BUY,SELL,PENDING_BUY,BUY_OK, PENDING_SELL, SELL_OK,CANCEL_BUY, CANCEL_SELL}
	public static status PositionValues;*/
	

}
