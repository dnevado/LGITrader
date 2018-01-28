package com.ibtrader.constants;

public class IBTraderConstants {
	
	/* valores INI IBCONTROLLER */
	public static String IBCONTROLLER_PASSWORDENCRYPTED ="PasswordEncrypted"; // YES / NO
	public static String IBCONTROLLER_PASSWORD ="Password";
	public static String IBCONTROLLER_PAPER ="Paper";
	public static String IBCONTROLLER_LIVE ="live";
	public static String IBCONTROLLER_IBAUTOCLOSEDOWN ="IbAutoClosedown"; // no
	

	
	
	/* valores */
	public static String vACCOUNT_IB_NAME="ACCOUNT_IB_NAME";
	public static long   vNUM_DAYS_PAST_REALTIME =2;
	public static double vDEFAULT_TICK_FUTURE =0.25; 
	public static String vTWS_HOST = "127.0.0.1";
	public static long   vTWS_PORT = 7497;
	public static long   vCRON_READING_STATUS = 0;  // para saber si esta ejecutando o no , ojo a las caidas y exceptiones 
	public static long   vCRON_TRADING_STATUS = 0;  // para saber si esta ejecutando o no , ojo a las caidas y exceptiones
	public static long   vCRON_CONTRACTCHECKER_CLIENT_INITIAL = 2;  // para saber si esta ejecutando o no , ojo a las caidas y exceptiones
	public static long   vCRON_TRADING_CLIENT_INITIAL = 3;  // para saber si esta ejecutando o no , ojo a las caidas y exceptiones
	public static long   vCRON_READING_CLIENT_INITIAL = 4;  // para saber si esta ejecutando o no , ojo a las caidas y exceptiones
	public static String   vUSER_TWS = "edemo";  // para saber si esta ejecutando o no , ojo a las caidas y exceptiones
	public static String   vUSER_PWD = "demouser";   // para saber si esta ejecutando o no , ojo a las caidas y exceptiones
	public static long   vENABLE_DESKTOP_NOTIFICATIONS = 1;  // para saber si esta ejecutando o no , ojo a las caidas y exceptiones
	public static long   vENABLE_MAIL_NOTIFICATIONS = 1;  // para saber si esta ejecutando o no , ojo a las caidas y exceptiones
	public static long   vSIMULATION_MODE = 0;  // para saber si esta ejecutando o no , ojo a las caidas y exceptiones
	public static String   vPATH_TO_CONFIGURATION_FILE = "C:\\ibcontroller\\IBController.ini";  // para saber si esta ejecutando o no , ojo a las caidas y exceptiones

	
	/* keys */
	public static String   keyACCOUNT_IB_NAME="ACCOUNT_IB_NAME";
	public static String   keyNUM_DAYS_PAST_REALTIME ="NUM_DAYS_PAST_REALTIME";
	public static String   keyDEFAULT_TICK_FUTURE ="DEFAULT_TICK_FUTURE";
	public static String   keyTWS_HOST = "TWS_HOST";
	public static String   keyTWS_PORT = "TWS_PORT";
	public static String   keyCRON_READING_STATUS = "CRON_READING_STATUS";
	public static String   keyCRON_TRADING_STATUS = "CRON_TRADING_STATUS";
	
	public static String   keyCRON_CONTRACTCHECKER_CLIENT_INITIAL = "CRON_CONTRACTCHECKER_CLIENT_INITIAL"; 
	public static String   keyCRON_TRADING_CLIENT_INITIAL = "CRON_TRADING_CLIENT_INITIAL"; 
	public static String   keyCRON_READING_CLIENT_INITIAL = "CRON_READING_CLIENT_INITIAL";
	
	
	public static String   keyUSER_TWS = "USER_TWS"; 
	public static String   keyUSER_PWD = "USER_PWD"; 
	public static String   keyENABLE_DESKTOP_NOTIFICATIONS = "ENABLE_DESKTOP_NOTIFICATIONS"; 
	public static String   keyENABLE_MAIL_NOTIFICATIONS = "ENABLE_MAIL_NOTIFICATIONS";
	
	public static String   keySIMULATION_MODE = "SIMULATION_MODE";  
	public static String   keyPATH_TO_CONFIGURATION_FILE = "PATH_TO_CONFIGURATION_FILE";  

	
}
