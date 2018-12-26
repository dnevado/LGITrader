package com.ibtrader.constants;

public class IBTraderConstants {
	
	/* valores INI IBCONTROLLER */
	public static String IBCONTROLLER_PASSWORDENCRYPTED ="PasswordEncrypted"; // YES / NO
	public static String IBCONTROLLER_PASSWORD ="IbPassword";
	public static String IBCONTROLLER_USER ="IbLoginId";	
	public static String IBCONTROLLER_TRADINGMODE_PAPER ="paper";
	public static String IBCONTROLLER_TRADINGMODE_LIVE ="live";
	public static String IBCONTROLLER_IBAUTOCLOSEDOWN ="IbAutoClosedown"; // no
	public static String IBCONTROLLER_TRADING_MODE ="Paper";
	
	
	public static String IBCONTROLLER_UNIX_PATH ="/home/tradeable/ibcontroller";
	public static String IBCONTROLLER_WINDOWS_PATH ="c:/IbController";
	
	
	
	
	

	
	
	/* valores */
	public static String	vACCOUNT_IB_NAME="ACCOUNT_IB_NAME";
	public static long   	vNUM_DAYS_PAST_REALTIME =2;
	public static double 	vDEFAULT_TICK_FUTURE =0.25;
	public static long 	    vLAST_POSITIONS_TO_CHECK_IN_HOURS = 1; // ESTO ES POR SI HAY EJECUCIONES EN LA TWS QUE NECESITAN ESTAR EN LA BBDD PORQUE SE CAIGA, ASI SINCRONIZAMOS 
	public static String 	vTWS_HOST = "127.0.0.1";
	public static long  	vTWS_PORT = 7497;
	public static long   	vCRON_READING_STATUS = 0;  // para saber si esta ejecutando o no , ojo a las caidas y exceptiones 
	public static long   	vCRON_TRADING_STATUS = 0;  // para saber si esta ejecutando o no , ojo a las caidas y exceptiones
	public static long   	vCRON_ORDERSCHECKER_CLIENT_INITIAL = 5;  // para saber si esta ejecutando o no , ojo a las caidas y exceptiones
	public static long   	vCRON_CONTRACTCHECKER_CLIENT_INITIAL = 2;  // para saber si esta ejecutando o no , ojo a las caidas y exceptiones
	public static long   	vCRON_TRADING_CLIENT_INITIAL = 3;  // para saber si esta ejecutando o no , ojo a las caidas y exceptiones
	public static long  	vCRON_READING_CLIENT_INITIAL = 4;  // para saber si esta ejecutando o no , ojo a las caidas y exceptiones
	public static long  	vCRON_FILLHISTORICALDATA = 6;  // para saber si esta ejecutando o no , ojo a las caidas y exceptiones	
	public static String    vUSER_TWS = "pmdemo";  // para saber si esta ejecutando o no , ojo a las caidas y exceptiones
	public static String    vUSER_PWD = "demouser";   // para saber si esta ejecutando o no , ojo a las caidas y exceptiones
	public static long   	vENABLE_DESKTOP_NOTIFICATIONS = 1;  // para saber si esta ejecutando o no , ojo a las caidas y exceptiones
	public static long   	vENABLE_MAIL_NOTIFICATIONS = 1;  // para saber si esta ejecutando o no , ojo a las caidas y exceptiones
	public static String   	vSIMULATION_MODE = "";  // para saber si esta ejecutando o no , ojo a las caidas y exceptiones
	public static String    vPATH_TO_CONFIGURATION_FILE = "{PLATFORM_PATH}/IbController_live/IBController.ini";   // para saber si esta ejecutando o no , ojo a las caidas y exceptiones
	public static String   	vPATH_TO_EXECUTABLE_FILE = "{PLATFORM_PATH}/IbController_live/IBControllerStart";   // para saber si esta ejecutando o no , ojo a las caidas y exceptiones

	public static long   	vFAKE_MODE = 0;  // 1, entramos en fake mode, para testeo 
	
	public static String    vPAPER_USER_TWS = "edemo"; 
	public static String    vPAPER_USER_PWD = "demouser"; 
	public static String    vPAPER_ACCOUNT_IB_NAME = "PAPER_ACCOUNT_IB_NAME";
	public static String    vPAPER_TWS_HOST = "127.0.0.1";
	public static long      vPAPER_TWS_PORT = 7498;
	public static String    vPAPER_PATH_TO_CONFIGURATION_FILE = "{PLATFORM_PATH}/IbController_paper/IBController.ini";  // para saber si esta ejecutando o no , ojo a las caidas y exceptiones
	public static String    vPAPER_PATH_TO_EXECUTABLE_FILE = "{PLATFORM_PATH}/IbController_paper/IBControllerStart";  // para saber si esta ejecutando o no , ojo a las caidas y exceptiones
	/* keys */
	
	public static String    keyNUM_DAYS_PAST_REALTIME ="NUM_DAYS_PAST_REALTIME";
	public static String    keyLAST_POSITIONS_TO_CHECK_IN_HOURS ="LAST_POSITIONS_TO_CHECK_IN_HOURS";	
	public static String    keyDEFAULT_TICK_FUTURE ="DEFAULT_TICK_FUTURE";
	public static String    keyACCOUNT_IB_NAME="ACCOUNT_IB_NAME";
	public static String    keyTWS_HOST = "TWS_HOST";
	public static String    keyTWS_PORT = "TWS_PORT";
	public static String    keyCRON_READING_STATUS = "CRON_READING_STATUS";
	public static String    keyCRON_TRADING_STATUS = "CRON_TRADING_STATUS";	
	public static String    keyCRON_CONTRACTCHECKER_CLIENT_INITIAL = "CRON_CONTRACTCHECKER_CLIENT_INITIAL"; 
	public static String    keyCRON_ORDERSCHECKER_CLIENT_INITIAL = "CRON_ORDERSCHECKER_CLIENT_INITIAL";
	public static String    keyCRON_FILLHISTORICALDATA = "CRON_FILLHISTORICALDATA";
	public static String    keyCRON_TRADING_CLIENT_INITIAL = "CRON_TRADING_CLIENT_INITIAL"; 
	public static String    keyCRON_READING_CLIENT_INITIAL = "CRON_READING_CLIENT_INITIAL";
	
	public static String    keyFAKE_MODE = "FAKE_MODE";
	
	
	
	
	
	public static String   keyUSER_TWS = "USER_TWS"; 
	public static String   keyUSER_PWD = "USER_PWD"; 
	public static String   keyENABLE_DESKTOP_NOTIFICATIONS = "ENABLE_DESKTOP_NOTIFICATIONS"; 
	public static String   keyENABLE_MAIL_NOTIFICATIONS = "ENABLE_MAIL_NOTIFICATIONS";
	
	public static String   keySIMULATION_MODE = "SIMULATION_MODE";  
	public static String   keyPATH_TO_CONFIGURATION_FILE = "PATH_TO_CONFIGURATION_FILE";
	public static String   keyPATH_TO_EXECUTABLE_FILE = "PATH_TO_EXECUTABLE_FILE";  

	public static String   keyPAPER_USER_TWS = "PAPER_USER_TWS"; 
	public static String   keyPAPER_USER_PWD = "PAPER_USER_PWD"; 
	public static String   keyPAPER_ACCOUNT_IB_NAME="PAPER_ACCOUNT_IB_NAME";
	public static String   keyPAPER_TWS_HOST = "PAPER_TWS_HOST";
	public static String   keyPAPER_TWS_PORT = "PAPER_TWS_PORT";
	public static String   keyPAPER_PATH_TO_CONFIGURATION_FILE = "PAPER_PATH_TO_CONFIGURATION_FILE";
	public static String   keyPAPER_PATH_TO_EXECUTABLE_FILE = "PAPER_PATH_TO_EXECUTABLE_FILE";  
	
	public enum statusSimulation {	    
	    Pending  ("Pending"),
	    Processed  ("Processed");
	    
	    private String name;       

	    private statusSimulation(String s) {
	        name = s;
	    }
	}

	
}
