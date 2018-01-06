package com.ibtrader.util;

import java.util.ArrayList;

public class ConfigKeys {

    public static final String NUM_DAYS_PAST_REALTIME = "NUM_DAYS_PAST_REALTIME";
    public static final Long STRATEGY_BUY_MIN_MAX = new Long(1);
    public static final Long STRATEGY_SELL_STOP_PROFIT = new Long(2);
    public static final Long STRATEGY_SELL_STOP_LOST = new Long(4);
    public static final Long STRATEGY_SELL_CLOSEALLPOSITIONS = new Long(3);
    public static final Long STRATEGY_BUY_AVGMOBILE_8_PERIODS_5_MINBAR = new Long(5);
    public static final Long STRATEGY_SELL_AVGMOBILE_8_PERIODS_5_MINBAR = new Long(6);
    
    
    public static int  _TICKTYPE_CLOSE = 9;
    public static int _TICKTYPE_LAST  =4;
    public static int _TICKTYPE_DELAYED_LAST  =68;
    		
    
    public static final Long RULE_TRADE_TIME_PERIOD = new Long(1);
    public static final Long RULE_TRADE_MAX_MONEY_TRADED = new Long(2);
    public static final Long RULE_TRADE_REPEAT_SHARE = new Long(3);
    

    
    public static final String FILTER_CONSOLA_OPEN = "Abiertas";
    public static final String FILTER_CONSOLA_EXECUTED = "Ejecutadas";
    public static final String FILTER_CONSOLA_ALL = "Todas";
    
    public static final String SECURITY_TYPE_STOCK = "STK";
    public static final String SECURITY_TYPE_FUTUROS = "FUT";
    
    
    public static final String CURRENCY_DOLLAR = "USD";
    public static final String CURRENCY_EURO = "EUR";
    
    
    public static final String ERROR_CODE_PACING_VIOLATION = "162";
    
    //http://interactivebrokers.github.io/tws-api/market_data_type.html#gsc.tab=0
    public static final int MARKET_DATA_TYPE_DELAYED_LIVE = 3;
    
    public static final int _AUDIT_TIME_CRON_READ_IN_MILLISECONDS = 5000;   // 5 SEGUNDOS SE GRABA EL MODIFIED DATABASE 
    
    

    
    public enum IDENTITY_VALUE_TYPE {
	    MAX ("Maximo"),
	    MIN ("Minimo");
	   
	    private String name;       

	    private IDENTITY_VALUE_TYPE(String s) {
	        name = s;
	    }
	}
    
    public static String[] _MARKET_EXCHANGES = new String[]{"SMART","GLOBEX","DTB","NYMEX","IPE"};
    public static String[] _PRIMARY_MARKET_EXCHANGES = new String[]{"NASDAQ","GLOBEX","DTB","NYMEX","IPE"};
    
    public static String[] _FUTURES_MONTHS = new String[]{"January","February","March","April","May","June","July","August","September","October","November","December"};
    
    public static String[] _FUTURES_DAYOFWEEK = new String[]{"Sunday", "Monday","Tuesday","Wednesday","Thrusday","Friday","Saturday"};
    
    
    public static final String _SIMULATED_TRADING_MODE = "SIMULATED_T";
    public static final String _REAL_TRADING_MODE = "REAL_T";
    
    public static final String _CONFIG_KEY_ACCOUNT_NAME = "ACCOUNT_IB_NAME";
    public static final String _CONFIG_KEY_SIMULATE_NAME = "SIMULATION_MODE";
    
    public static  int TWS_CONNECTION_PORT =7497; 
    

    
	    
}
