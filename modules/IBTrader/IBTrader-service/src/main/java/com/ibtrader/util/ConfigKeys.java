package com.ibtrader.util;

import java.util.ArrayList;

public class ConfigKeys {

	
	/* DIAS PARA CERRAR UN ONTRATO ANTES DE SU EXPIRACION  Y PARA PODER ENTRAR */
    public static final Long NUM_DAYS_CLOSE_FUTURE_CONTRACT= new Long(1);

	
    public static final String NUM_DAYS_PAST_REALTIME = "NUM_DAYS_PAST_REALTIME";
    public static final Long STRATEGY_BUY_MIN_MAX = new Long(1);
    public static final Long STRATEGY_SELL_STOP_PROFIT = new Long(2);
    public static final Long STRATEGY_SELL_STOP_LOST = new Long(4);
    public static final Long STRATEGY_SELL_CLOSEALLPOSITIONS = new Long(3);
    public static final Long STRATEGY_BUY_AVGMOBILE_8_PERIODS_5_MINBAR = new Long(5);
    public static final Long STRATEGY_SELL_AVGMOBILE_8_PERIODS_5_MINBAR = new Long(6);
    
    
    public static int  _TICKTYPE_CLOSE = 9;
    public static int _TICKTYPE_LAST  = 4;
    public static int _TICKTYPE_DELAYED_LAST  = 68;
    public static int _TICKTYPE_VOLUME  = 8;
    		
    
    public static final Long RULE_TRADE_TIME_PERIOD = new Long(1);
    public static final Long RULE_TRADE_MAX_MONEY_TRADED = new Long(2);
    public static final Long RULE_TRADE_REPEAT_SHARE = new Long(3);
    

    
    public static final String FILTER_CONSOLA_OPEN = "Abiertas";
    public static final String FILTER_CONSOLA_EXECUTED = "Ejecutadas";
    public static final String FILTER_CONSOLA_ALL = "Todas";
    
    public static final String SECURITY_TYPE_STOCK = "STK";
    public static final String SECURITY_TYPE_FUTUROS = "FUT";
    public static final String SECURITY_TYPE_INDICES = "IND";
    public static final String SECURITY_TYPE_FOREX = "CASH";
    
    
	 public static final String   _BUDGET_USER_EXPANDO = "maxuserbudgetfortrading";

	 
    public static final String CURRENCY_DOLLAR = "USD";
    public static final String CURRENCY_EURO = "EUR";
    
    public static final int ADXR_PERIODS = 14;
    public static final int INDICATORS_MIN_SERIE_COUNT = 125;  // PARA EL CALCULO DEL ADXR / MACD ..., NECESITAMOS PARA TENER PRECISION 125 BARRAS 
    
    public static final int DEFAULT_TIMEBAR_MINUTES = 5;  // barra por detecto para recargar realtimes 
    public static final int DEFAULT_ATR_VALUE = 14;  // barra por detecto para recargar realtimes
    public static final int DEFAULT_ATR_NUM_PERIODS_UP = 4;  // CUANTOS PERIDOS SEGUIDS ULTIMOS  CONTAR PARA SABER SI ES CRECIENTE  
    public static final int DEFAULT_MIN_BARS_PEAKS_VALLEY = 1;
    
    
    /* PARA LAS MECHAS DE LAS VELAS */
    public static final double  AVERAGE_WICK_SIZE = 0.5; // 75 % DE LA BARRA SON SOMBRAS 
    
    
    
    public static final int DAYTRADER_PATTERN_MAX_DAYS = 4;  // MAXIMO NUMERO DE DIAS CON OPERACIONES INTRADIA PARA ENTRAR EN DAYTRADER_PATTERN   
    public static final int DAYTRADER_PATTERN_PERIOD = 7;  // QUE PERIODO CONTAR ESOS 4 DAY TRADING ANTERIORES, ES DECIR, 4 DIAS EN 5 BUSINESS DAYS, 1 SEMANA YA QUE SABADO Y DOMINGO NO CUENTA 
    
    public static final String ERROR_CODE_PACING_VIOLATION = "162";
    
    public static final long DEFAULT_BACKTESTINGID_VALUE = -1;
    
    public static final long MAX_TRADING_PERIODS_TO_SHOW = 4;

    //http://interactivebrokers.github.io/tws-api/market_data_type.html#gsc.tab=0
    public static final int MARKET_DATA_TYPE_DELAYED_LIVE = 3;
    public static final int MARKET_DATA_TYPE_LIVE = 1;
    public static final int MARKET_DATA_TYPE_FROZEM = 2;
    
    public static final int SIMULATION_MINUTES_BAR_SIZE = 5; // 5 MINUTOS DE HISTORICAL DATA 
    public static final String CLOSE_BAR_SIZE = "1 day"; // PRECIOS DE CIERRE  
    public static final String  SIMULATION_INTERVAL_PERIOD = "1 W"; // 7 dias, es lo máximo a pedir con una barra de 5 minutos 
    // https://interactivebrokers.github.io/tws-api/historical_limitations.html

    
    
    public static final int _AUDIT_TIME_CRON_READ_IN_MILLISECONDS = 30000;   // 30 SEGUNDOS SE GRABA EL MODIFIED DATABASE 
    
    public static final String  _FIELD_NUMBER_TO_PURCHASE = "numbertopurchase";   // 5 SEGUNDOS SE GRABA EL MODIFIED DATABASE
    public static final String _FIELD_TRAILLING_STOP_LOST = "percentual_trailling_stop_lost";   // 5 SEGUNDOS SE GRABA EL MODIFIED DATABASE
    public static final String _FIELD_STOP_LOST = "percentual_stop_lost";   // 5 SEGUNDOS SE GRABA EL MODIFIED DATABASE
    public static final String _FIELD_STOP_PROFIT = "percentual_stop_profit";   // 5 SEGUNDOS SE GRABA EL MODIFIED DATABASE
    public static final String _FIELD_PERCENTUAL_LIMIT_GAP_IN = "percentual_limit_buy";   // 5 SEGUNDOS SE GRABA EL MODIFIED DATABASE
    
    public static final String _LASTBAR_TWS_STRING_HISTORICAL = "finished";   // 5 SEGUNDOS SE GRABA EL MODIFIED DATABASE

    
    public static final String  _FIELD_PIVOTS_POINTS_PARAMS_TIMEBARS = "_num_macdT";   // 5 SEGUNDOS SE GRABA EL MODIFIED DATABASE
    public static final String _FIELD_PIVOTS_POINTS_PARAMS_GAPTOENTER = "_atrmultipler_gaptoEnter";   // 5 SEGUNDOS SE GRABA EL MODIFIED DATABASE
    public static final String _FIELD_PIVOTS_POINTS_PARAMS_OPERATIONFILTER = "operationfilter";   // 5 SEGUNDOS SE GRABA EL MODIFIED DATABASE
    public static final String _FIELD_PIVOTS_POINTS_PARAMS_VOLUMEN_INCREASED = "volume_increased";   // 5 SEGUNDOS SE GRABA EL MODIFIED DATABASE
    public static final String _FIELD_PIVOTS_POINTS_PARAMS_ATR_INCREASED = "atr_increased";   // 5 SEGUNDOS SE GRABA EL MODIFIED DATABASE
    
    

    
    
    public enum IDENTITY_VALUE_TYPE {
	    MAX ("Maximo"),
	    MIN ("Minimo");
	   
	    private String name;       

	    private IDENTITY_VALUE_TYPE(String s) {
	        name = s;
	    }
	}
    
    public static String[] _MARKET_EXCHANGES = new String[]{"SMART","GLOBEX","DTB","NYMEX","IPE","BM","MATIF","ECBOT","IDEALPRO"};
    public static String[] _PRIMARY_MARKET_EXCHANGES = new String[]{"NASDAQ","GLOBEX","DTB","NYMEX","IPE", "BM","MATIF","ECBOT","IDEALPRO"};
    
    public static String[] _FUTURES_MONTHS = new String[]{"January","February","March","April","May","June","July","August","September","October","November","December"};
    
    public static String[] _FUTURES_DAYOFWEEK = new String[]{"Sunday", "Monday","Tuesday","Wednesday","Thrusday","Friday","Saturday"};
    
    
    public static final String _SIMULATED_TRADING_MODE = "SIMULATED_T";
    public static final String _REAL_TRADING_MODE = "REAL_T";
    
    public static final String _CONFIG_KEY_ACCOUNT_NAME = "ACCOUNT_IB_NAME";
    public static final String _CONFIG_KEY_SIMULATE_NAME = "SIMULATION_MODE";
    
    public static  int TWS_CONNECTION_PORT =7497; 
    

    
	    
}
