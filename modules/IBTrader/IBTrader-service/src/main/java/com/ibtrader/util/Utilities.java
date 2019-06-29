package com.ibtrader.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.osgi.service.component.annotations.Reference;
import org.w3c.dom.Document;

import org.w3c.dom.NodeList;

import org.w3c.dom.Node;

import org.w3c.dom.Element;



import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelperUtil;
import com.liferay.portal.kernel.scheduler.SchedulerException;
import com.liferay.portal.kernel.scheduler.messaging.SchedulerResponse;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.ibtrader.constants.IBTraderConstants;
import com.ibtrader.data.model.Config;
import com.ibtrader.data.model.Market;
import com.ibtrader.data.model.Position;
import com.ibtrader.data.model.StrategyShare;
import com.ibtrader.data.service.ConfigLocalService;
import com.ibtrader.data.service.ConfigLocalServiceUtil;
import com.ibtrader.data.service.MarketLocalServiceUtil;
import com.ibtrader.data.service.PositionLocalService;
import com.ibtrader.data.service.PositionLocalServiceUtil;



public class Utilities {
	
    public final static long SECOND_MILLIS = 1000;
    public final static long MINUTE_MILLIS = SECOND_MILLIS*60;
    public final static long DAYS_MILLIS = MINUTE_MILLIS*60*24;
    
    
    
   public final static String __IBTRADER_SHORT_HOUR_FORMAT="HHmm";
   public final static String __IBTRADER_LONG_HOUR_FORMAT="HHmm";
   
   public final static String __IBTRADER_LONG_DATE_FORMAT="yyyyMMdd"; 
   public final static String __IBTRADER_SQL_DATE_="yyyy-MM-dd HH:mm"; // PARA EL CUSTOM SQL DE MOBILE AVERAGE 
   public final static String __IBTRADER_ORDERS_EXECUTED__DATE_FORMAT ="yyyymmdd hh:mm:ss"; // PARA EL execution time de ordenes de la tws 
   public final static String __IBTRADER_HISTORICAL_DATE_FORMAT ="yyyyMMdd HH:mm:ss"; // PARA EL execution time de ordenes de la tws 
   public final static String __IBTRADER_TRADINHOURS_DATE_FORMAT ="yyyyMMdd HHmm"; //  

   
   //public final static String _IBTRADER_DATE_FORMAT="HHmm";
   
   
   public final static String _IBTRADER_FUTURE_DATE_SORTED="yyyy/MM/dd";
   public final static String _IBTRADER_FUTURE_SHORT_DATE="yyyyMM";
   public final static String _IBTRADER_FUTURE_LONG_DATE="dd/MM/yyyy";
   
   
   public final static String _IBTRADER_WEB_FORMAT_DATE="dd-MM-yyyy HH:mm";
   public final static String _IBTRADER_WEB_FORMAT_SHORTDATE="dd-MM-yyyy";
   
   
   public final static String _IBTRADER_STRATEGY_CUSTOM_FIELDS_="exp_";
   
   public final static String _DEFAULT_USER_DEMO_="edemo";
   
   private static final String TIME24HOURS_PATTERN ="([01]?[0-9]|2[0-3]):[0-5][0-9]";
   
   private final static Log _log = LogFactoryUtil.getLog(Utilities.class);
   
   
   private static ConfigLocalService _configLocalService;
   
   private static Log log = LogFactoryUtil.getLog(Utilities.class.getName());

	@Reference(unbind = "-")
	protected void setConfigService(ConfigLocalService configLocalService) {
		_configLocalService = configLocalService;
	}

   
	/* OBTIENE LA HORA DE INICIO Y FIN ACTUAL SI EXISTE PARA ACTUALIZAR LOS HORARIOS DE LOS MERCADOS EN CUESTION */
	/* OJO CON LOS FUTUROS QUE PUEDEN ESTAR DE 00 A 23:59 */
	public static Market fillOpenEndHoursMarket(String _jsonTradingHours)
	{
		
		Market market = null;
		
	    /* SHORT */
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Utilities.__IBTRADER_LONG_DATE_FORMAT);
	    /* LONG */
	    DateTimeFormatter formatterlong = DateTimeFormatter.ofPattern(Utilities.__IBTRADER_TRADINHOURS_DATE_FORMAT);
	    /* TIME */
	    DateTimeFormatter formattertime = DateTimeFormatter.ofPattern(Utilities.__IBTRADER_SHORT_HOUR_FORMAT);
		
		/* HOURS IN UTC, BUSCAMOS EL DIA DE HOY  */		
		try {
			JSONArray   jsonTradingHours = JSONFactoryUtil.createJSONArray(_jsonTradingHours);			
		
		  JSONObject tradingDate = (JSONObject) jsonTradingHours.getJSONObject(0);			      
		
	      /* UTC DATES */
	      LocalDate fromDate = LocalDate.parse(tradingDate.getString("fromDate").split(" ")[0],formatter);	
	      LocalDate toDate = LocalDate.parse(tradingDate.getString("toDate").split(" ")[0],formatter);			    

	      
	 
    	  /* PROBLEMA CON LOS FUTUROS */
    	  //[{"fromDate":"20190328 2200","toDate":"20190329 2015"},{"fromDate":"20190329 2030","toDate":"20190329 2100"}
    	  /* 1. SI CAMBIO DE DIA, ENTONCES 00:00 23:59 */
    	  /* 2. SI HAY VARIOS TRAMOS PARA EL MISMO DIA, ETONCES 00:00 23:59 */			    	 
    	  /* UTC DATES */
    	  LocalDateTime fromDateTime = LocalDateTime.parse(tradingDate.getString("fromDate"),formatterlong);
	      LocalDateTime toTime = LocalDateTime.parse(tradingDate.getString("toDate"),formatterlong);
    	  /* CONTROLAMOS LA FECHAS DE INICIO Y FIN 
    	   * SUPONEMOS QUE SIEMPRE HAY UN FROM CON EL DIA DE HOY, SI NO , NO ACTUALIZAMOS */
    	  String _startHour = fromDate.equals(toDate) ? fromDateTime.format(formattertime) : "0000";
    	  String _endHour =  fromDate.equals(toDate) ? toTime.format(formattertime) : "2359";		
    	  
    	  market = MarketLocalServiceUtil.createMarket(-1); // no persiste 			    	  
    	  market.setStart_hour(_startHour);
    	  market.setEnd_hour(_endHour);	
    	  
    	  log.debug("Market start / end hour  " + market.getStart_hour() + "," +  market.getEnd_hour() );
	    	  	    		     
	
		} catch (JSONException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		}		
	return market;
		
	}
	
	public static boolean IsTradingEnabledFromHours(String _jsonTradingHours)
	{
		
		boolean isTradingEnabled = Boolean.FALSE;
		/* HOURS IN UTC, BUSCAMOS EL DIA DE HOY  */		
		try {
			JSONArray   jsonTradingHours = JSONFactoryUtil.createJSONArray(_jsonTradingHours);			
			for (int i = 0; i < jsonTradingHours.length(); i++) 
			{
				  JSONObject tradingDate = (JSONObject) jsonTradingHours.getJSONObject(i);			      
				  DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Utilities.__IBTRADER_TRADINHOURS_DATE_FORMAT);

			      /* UTC DATES */
			      LocalDateTime fromDate = LocalDateTime.parse(tradingDate.getString("fromDate"),formatter);
			      LocalDateTime to = LocalDateTime.parse(tradingDate.getString("toDate"),formatter);
			      LocalDateTime _now  = LocalDateTime.now();
			     if (fromDate.isBefore(_now) && to.isAfter(_now))  // es tradeable 
			     {
			    	  isTradingEnabled = Boolean.TRUE;
			    	  break;
			     }

			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		}		
	return isTradingEnabled;
		
	}
	
	/* OBTIENE LOS 7 PRIMEROS PERIODOS CONVERTIDO  A LA ZONA DEL USUARIO */
	public static List<String> getCurrentTradingHours(String _jsonTradingHours, User user)
	{
		List<String> tradingHours = new ArrayList<String>();
		/* HOURS IN UTC, BUSCAMOS EL DIA DE HOY  */		
		try {
			JSONArray   jsonTradingHours = JSONFactoryUtil.createJSONArray(_jsonTradingHours);			
			for (int i = 0; i < jsonTradingHours.length();	 i++) 
			{
				  if (i<ConfigKeys.MAX_TRADING_PERIODS_TO_SHOW)
				  {	  
					  JSONObject tradingDate = (JSONObject) jsonTradingHours.getJSONObject(i);			      
					  DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Utilities.__IBTRADER_TRADINHOURS_DATE_FORMAT);
	
				      /* UTC DATES */
				      LocalDateTime fromDate = LocalDateTime.parse(tradingDate.getString("fromDate"),formatter);
				      LocalDateTime to = LocalDateTime.parse(tradingDate.getString("toDate"),formatter);
				    
				      
				      Instant instantFrom = fromDate.toInstant(ZoneOffset.UTC);
				      Instant instantTo = to.toInstant(ZoneOffset.UTC);

				      String _periodHours = Utilities.getWebFormattedDate(Date.from(instantFrom),user);
				      _periodHours = _periodHours.concat(" ");
				      _periodHours = _periodHours.concat(Utilities.getWebFormattedDate(Date.from(instantTo),user));
				      tradingHours.add( _periodHours);
				     
				  }

			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		}		
	  return tradingHours;
		
	}
	
	

  
   public static boolean isNumber (String amount){
	    try {
	        Double.parseDouble(amount);             
	    }
	    catch(NumberFormatException e){
	        return false;            
	    }
	    return true;
	}
   
   public enum OSType {
	    Windows, MacOS, Linux, Other
	  };

   
   public static int getTotalJobsRunnig(String JobclassName)
   {
	   /*  VERIFICAMOS SI NO ESTA EJECUTANDO, SI ME DA  DOS, LO CANCELO , SOLO PERMITIMOS 1 */
	   int total = 0;
	    List<SchedulerResponse> jobs = null;
		try {
			jobs = SchedulerEngineHelperUtil.getScheduledJobs();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	    if (jobs==null) return 0;
  	 	for (SchedulerResponse shdR :jobs)
  	 	{	
  	 			_log.info("job:" + shdR.getJobName());
  	 			
  	 			if (shdR.getJobName().equalsIgnoreCase(JobclassName))
				{
  	 				
					total++;
				}
		
  	 	}
		return total;
   }
	  
  public static OSType getOperatingSystemType() {
	  OSType detectedOS;
      String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
      if ((OS.indexOf("mac") >= 0) || (OS.indexOf("darwin") >= 0)) {
        detectedOS = OSType.MacOS;
      } else if (OS.indexOf("win") >= 0) {
        detectedOS = OSType.Windows;
      } else if (OS.indexOf("nux") >= 0) {
        detectedOS = OSType.Linux;
      } else {
        detectedOS = OSType.Other;
      }
      return detectedOS;
    }
  
  public static boolean getSimulatedTrading(long companyId, long groupId)
  {
  
	boolean return_simulated = Boolean.FALSE;  
	String simulated = Utilities.getConfigurationValue(IBTraderConstants.keySIMULATION_MODE, companyId, groupId);
	return_simulated = simulated.equalsIgnoreCase(PositionStates.position_mode_type.SIMULATED.toString());
	return return_simulated;
	 	
  
  }
  public static String  getPositionModeType(Date backTesting, long companyId, long groupId)
  {
	  
	   String _retValue = "";
	   if (Validator.isNotNull(backTesting))
		   _retValue = PositionStates.position_mode_type.BACKTESTING.toString();
	   else
	   {
		     try 
		     {		    
		    	 String simulated = Utilities.getConfigurationValue(IBTraderConstants.keySIMULATION_MODE, companyId,groupId);
		    	 if (simulated.equalsIgnoreCase(PositionStates.position_mode_type.REAL.toString()))
		    		 _retValue = PositionStates.position_mode_type.REAL.toString();
		    	 else
		    		 _retValue = PositionStates.position_mode_type.SIMULATED.toString();

		     }
		     catch (Exception e)
		     {
		    	 _log.debug("No se encuentra el sevicio getConfigurationValue "  + IBTraderConstants.keySIMULATION_MODE + " " + groupId + ",message:" + e.getMessage()) ;
		     }
	   }
	   return _retValue;
	   
  }
  
  
   public static String  getConfigurationValue(String  keyValue, long companyId, long _groupId)
   {
	   /* MODO DE SIMULACION */	
	   String _ConfigValue = "";
	   try 
	   {		    
		   // long total = ConfigLocalServiceUtil.getConfigsCount();
			Config _conf = ConfigLocalServiceUtil.findByKeyCompanyGroup(keyValue, companyId, _groupId);
		
			if (_conf!=null)				
			{
				 _ConfigValue = _conf.getValue();
				
			}
	   }
		catch (Exception e)
		{
			_log.debug("No se encuentra el sevicio getConfigurationValue "  + keyValue + " " + _groupId + ",message:" + e.getMessage()) ;
		}
		
		return _ConfigValue;
	   
   }
   
   	/**
	 * Validate time in 24 hours format with regular expression
	 * @param time time address for validation
	 * @return true valid time fromat, false invalid time format
	 */
	public static boolean validateTime24hours(String time){
	
		 Pattern pattern;
		 Matcher matcher;
		 pattern = Pattern.compile(TIME24HOURS_PATTERN);

		 matcher = pattern.matcher(time);
		 return matcher.matches();
	
	}
  
    
    
    public static String  getWebFormattedShortDate(Date _date)
   	{       	
    	SimpleDateFormat _Format = new SimpleDateFormat(_IBTRADER_WEB_FORMAT_SHORTDATE);   	
    	return  _Format.format(_date);
   		
   	}
    
    
    public static String  getWebFormattedShortDate(Date _date, User user)
   	{       	
    	
    	/* SHORT */
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Utilities._IBTRADER_WEB_FORMAT_SHORTDATE);	 
    	return  formatter.format(getIBDateByUserDate(user,_date));
   		
   	}
    
    public static String  getWebFormattedDate(Date _date, User user)
   	{
    	
    	/* SHORT */
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Utilities._IBTRADER_WEB_FORMAT_DATE);
	  
    	return  formatter.format(getIBDateByUserDate(user,_date));
   		
   	}
    public static String  getWebFormattedTime(Date _date, User user)
   	{       	
    	
    	/* SHORT */
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Utilities.__IBTRADER_LONG_HOUR_FORMAT);
		return  formatter.format(getIBDateByUserDate(user,_date));

   		
   	}
    /* AUTORELLENAMOS LOS DATOS PARA QUE NO PASEN POR LA TWS */
    public static Position  fillStatesOrder(Position position)
   	{       	
    	long guestGroupId;
    	boolean standalone_mode = Boolean.FALSE;
    	Position _position = position;
    	double avgFillPrice = 0;
		try {
			guestGroupId = GroupLocalServiceUtil.getGroup(PortalUtil.getDefaultCompanyId(), GroupConstants.GUEST).getGroupId();
			standalone_mode = (Utilities.getConfigurationValue(IBTraderConstants.keyFAKE_MODE, PortalUtil.getDefaultCompanyId(), guestGroupId).equals("1") ? Boolean.TRUE : Boolean.FALSE);	  // el dos para leer, el 3 para escribir
			
			// backtesting controlamos tb.
			standalone_mode = standalone_mode ||  position.getPosition_mode().equals(PositionStates.position_mode_type.BACKTESTING.toString());
			

		} catch (PortalException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		if (_position.getDate_out()==null) // OUT
		{
			_position.setState_in(standalone_mode ? PositionStates.statusTWSCallBack.Filled.toString() :  PositionStates.statusTWSCallBack.PendingSubmit.toString());
			_position.setState(standalone_mode ? PositionStates.status.BUY_OK.toString() :  PositionStates.status.PENDING_BUY.toString());
			if  (standalone_mode)
			{
				_position.setDate_real_in(_position.getDate_in());
				_position.setPrice_real_in(_position.getPrice_in());		
				avgFillPrice = _position.getPrice_in();
				
				double priceStopLost = 0;
				double priceStopProfit = 0;	
			
				if (_position.getType().equals(PositionStates.statusTWSFire.SELL.toString()))  	{ //short
					if (_position.getPercentualstoplost_out()>0)
						priceStopLost = avgFillPrice +  (avgFillPrice *  _position.getPercentualstoplost_out() / 100);
					if (_position.getPercentualstopprofit_out()>0)
						priceStopProfit = avgFillPrice  - (avgFillPrice *  _position.getPercentualstopprofit_out() / 100); }
				else {  //long
					if (_position.getPercentualstoplost_out()>0)
						priceStopLost    = avgFillPrice  - (avgFillPrice *  _position.getPercentualstoplost_out() / 100);
					if (_position.getPercentualstopprofit_out()>0)
						priceStopProfit = avgFillPrice  + (avgFillPrice *  _position.getPercentualstopprofit_out() / 100); }	    				 	    		
				
				_position.setPricestoplost_out(Utilities.RoundPrice(priceStopLost));
				_position.setPricestopprofit_out(Utilities.RoundPrice(priceStopProfit));
				
			}
			
		}
		else // OUT
		{
			_position.setState_out(standalone_mode ? PositionStates.statusTWSCallBack.Filled.toString() :  PositionStates.statusTWSCallBack.PendingSubmit.toString());
			_position.setState(standalone_mode ? PositionStates.status.SELL_OK.toString() :  PositionStates.status.PENDING_SELL.toString());
			if  (standalone_mode)
			{
				_position.setDate_real_out(_position.getDate_out());
				_position.setPrice_real_out(_position.getPrice_out());		
				avgFillPrice = _position.getPrice_out();
			}
		}
		
		/* END MODO FAKE CUENTA DEMO */

   		return _position;
   	}

    public static ZonedDateTime getUTCDate(String HourMinutes)
   	{   
    			
   		return getIBUTCDateByUser(HourMinutes);
   	}
    
    public static ZonedDateTime getLocalDate(User _user, String HourMinutes)
   	{   
    			
   		return getIBLocalDateByUser(_user,HourMinutes);
   	}
    
    public static ZonedDateTime getLocalDate(User _user)
   	{   
    			
   		return getIBLocalDateByUser(_user, "");
   	}
    
    public static String getIBFormattedUserLocalTime(User _user, String HourMinutes)
    {
    	
    	ZonedDateTime dUTCOpen  = getUTCDate(HourMinutes);
		
		LocalDateTime dOpenUTC 	= dateLocalFromUTC(dUTCOpen, _user);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Utilities.__IBTRADER_SHORT_HOUR_FORMAT);

		return dOpenUTC.toLocalTime().format(formatter); 
    }
    
    
    
    /* HORA AJUSTADA A LA ZONA DEL USUARIO */
    public static Date getDate(User _user)
   	{   
    	Date returnDate = null;
   		returnDate = getIBDateByUser(_user);		
   		return returnDate;
   	}
    public static  String getDateNowFormat(User _user)
   	{
       	SimpleDateFormat Format = new SimpleDateFormat(__IBTRADER_LONG_DATE_FORMAT);   		
   		return  Format.format(getIBDateByUser(_user));
   		
   	}
    public static  String getHourNowFormat(User _user)
   	{
    	SimpleDateFormat Format = new SimpleDateFormat(__IBTRADER_LONG_HOUR_FORMAT);   		
   		return  Format.format(getIBDateByUser(_user));
   		
   	}
    public static  String getHourNowFormat(User _user, Date date)
   	{
		DateTimeFormatter Format = DateTimeFormatter.ofPattern(Utilities.__IBTRADER_LONG_HOUR_FORMAT);
   		return  Format.format(getIBDateByUserDate(_user,date));
   		
   	}
    
    
    /* HORA PARA EL USUARIO DEPEDE DE LA HORA DEL SERVIDOR Y SU ZONA UTC */
    private static  Date getGlobalIBDate()
    {
    	    	   		
   		return new Date();
    }
    public static  String getGlobalIBDateNowFormat()
   	{
    	
    	SimpleDateFormat Format = new SimpleDateFormat(__IBTRADER_LONG_HOUR_FORMAT);   		
   		return  Format.format(getGlobalIBDate());
   	}
       	   		
    
    /* HORA PARA EL USUARIO DEPEDE DE LA HORA DEL SERVIDOR Y SU ZONA UTC 
     * 
     *  VIENE SIEMPRE EN UTC POR LA JVM 
     *  LO PASO A GMT DEL USUARIO 
     * */
    @SuppressWarnings("static-access")
	private static  Date getIBDateByUser(User _user) 
    {
    	// UTC 
    	Calendar local =  Calendar.getInstance();	
		//Here you say to java the initial timezone. This is the secret
    	
    	Calendar UserTime =  Calendar.getInstance();	
    	UserTime.setTimeZone(_user.getTimeZone());
    	UserTime.setTimeInMillis(local.getTimeInMillis());
    	
    	
	   ZoneId userZone = ZoneId.of(_user.getTimeZone().getID()); 
       ZonedDateTime userZoneDT = ZonedDateTime.now ( userZone );
       
		return new Date().from(userZoneDT.toInstant());
    	
    	
   		/* ZoneId zoneId = ZoneId.of(_user.getTimeZoneId());
   		Date date = new Date();
   		ZonedDateTime zonedDateTime = date.toInstant().atZone(zoneId);
   		return Date.to(zonedDateTime.toInstant());
   		*/
    	
    }
    private static  ZonedDateTime getIBLocalDateByUser(User _user, String HourMinutes) 
    {
    	// UTC 
		//Here you say to java the initial timezone. This is the secret
    	ZonedDateTime userZoneDT = null;
    	if (!HourMinutes.equals(""))
    	{
    		  int hour = Integer.parseInt(HourMinutes.substring(0, 2));
    		  int minute = Integer.parseInt(HourMinutes.substring(2,4));
    		  userZoneDT = LocalDateTime.now().withHour(hour).withMinute(minute).atZone(ZoneId.of(_user.getTimeZone().getID()));  
    	}
    	else
    		userZoneDT = LocalDateTime.now().atZone(ZoneId.of(_user.getTimeZone().getID()));  
    	
    	  	
		return userZoneDT;
    	
    	
   		/* ZoneId zoneId = ZoneId.of(_user.getTimeZoneId());
   		Date date = new Date();
   		ZonedDateTime zonedDateTime = date.toInstant().atZone(zoneId);
   		return Date.to(zonedDateTime.toInstant());
   		*/
    	
    }
    private static  ZonedDateTime getIBUTCDateByUser(String HourMinutes) 
    {
    	// UTC 
		//Here you say to java the initial timezone. This is the secret
    	ZonedDateTime utcZoneDT = null;    	
		int hour = Integer.parseInt(HourMinutes.substring(0, 2));
		int minute = Integer.parseInt(HourMinutes.substring(2,4));
		utcZoneDT = LocalDateTime.now().withHour(hour).withMinute(minute).atZone(ZoneId.of("UTC"));  
    	  	
		return utcZoneDT;
    	
    }
    
    /* HORA PARA EL USUARIO DEPEDE DE LA HORA DEL SERVIDOR Y SU ZONA UTC */
    private static  LocalDateTime getIBDateByUserDate(User _user, Date date)
    {
    	
    	ZoneId zoneId = ZoneId.systemDefault(); // UTC 
    	LocalDateTime lct = LocalDateTime.ofInstant(date.toInstant(), zoneId);
    	ZonedDateTime zonedDateTimeUTC= lct.atZone(zoneId);
   		 // UTC    	
   		ZoneId userZoneId = ZoneId.of(_user.getTimeZoneId()); 
   		ZonedDateTime zonedDateTime = zonedDateTimeUTC.withZoneSameInstant(userZoneId);
   		return zonedDateTime.toLocalDateTime();
    	
    }
   
   
    /* DATE ES UNA FECHA EN LA ZONA DEL USUARIO, LA CONVERTIMOS A UTC  */

    public static LocalDateTime dateLocalToUTC(ZonedDateTime date){
	
    	ZoneId UTCZone = ZoneId.of("UTC");
    	return date.withZoneSameInstant(UTCZone).toLocalDateTime();

    }
    public static LocalDateTime dateLocalFromUTC(ZonedDateTime date, User user){
    	
   		ZoneId zoneId = ZoneId.of(user.getTimeZoneId());
    	return date.withZoneSameInstant(zoneId).toLocalDateTime();

    }
    
    
 
    
 
    /* para los stocks que llevan un tick distinto del punto, cuartos, etc..(futuros), aplicamos 
     * el calculo de los valores limitados para ellos acorde
     * 2546.50 --> 2546.75 -->
     bReachedMin : Min o Max para saber si es corto o largo.
     */
    public static double TickLimit_WithMultiplier(double OrderValue, double Multiplier, double ValueLimit, boolean bReachedMin)
    {
        // Generamos un array con multiplicadores desde el value de compra hasta el limite  +1 para saber el mas cercano
    	int _Steps = 0;
    	ArrayList<Double> aTicks = new ArrayList<Double>();
        _Steps = (int) (Math.abs(ValueLimit - OrderValue) / Multiplier);        
        for (int j=0;j<_Steps+2;j++)
        {
        	if (bReachedMin)
        		aTicks.add(OrderValue - (j*Multiplier));   // corto
        	else
        		aTicks.add(OrderValue + (j*Multiplier));   // largo
        	
        }
        
        	
        return findNearestValue(aTicks, ValueLimit);
    	
    }
   
    
    /* FORMATO HHMM */
	public static  String getActualHourFormatPlusMinutes(String HourMinutes, Integer Minutes)
	{
		    Date _dNow = getGlobalIBDate();
		    Calendar _cNow = Calendar.getInstance();
		    _cNow.setTimeInMillis(_dNow.getTime());
			return getActualHourFormatPlusMinutes(_cNow,HourMinutes,Minutes);
			
			
	}
	
	/* FORMATO HHMM */
	public static  String getActualHourFormatPlusMinutes(Calendar _Date, String HourMinutes, Integer Minutes)
	{
		try 
		
		{
			if (HourMinutes.contains(":"))
				HourMinutes = HourMinutes.replaceAll(":", "");
			Calendar Hoy = Calendar.getInstance();
			
			Hoy.setTimeInMillis(_Date.getTimeInMillis());
				
			if (Minutes!=null)
			{
				
			    int hour = Integer.parseInt(HourMinutes.substring(0, 2));
			    int minute = Integer.parseInt(HourMinutes.substring(2));
			    
			    
			    Hoy.set(Calendar.HOUR_OF_DAY, hour);
			    Hoy.set(Calendar.MINUTE, minute);
			    
			    Hoy.add(Calendar.MINUTE,Minutes );
			    
				
				SimpleDateFormat Format = new SimpleDateFormat();
				Format.applyPattern(__IBTRADER_SHORT_HOUR_FORMAT);
				
				return  Format.format(Hoy.getTime());
			}
			else
			{
				return  "-1";
			}
		}
		catch (Exception e) {return  "-1";}
		
	
	
		
	}
	
	
	/* FORMATO HHMM */
	public static  String getActualHourFormatSubstractMinutes(String HourMinutes, Integer Minutes)
	{
		try 
		
		{
			if (HourMinutes.contains(":"))
				HourMinutes = HourMinutes.replaceAll(":", "");
			Calendar Hoy = null;
			if (Minutes!=null)
			{
				
			
			    Hoy = Calendar.getInstance();
			    
			    int hour = Integer.parseInt(HourMinutes.substring(0, 2));
			    int minute = Integer.parseInt(HourMinutes.substring(2));
			    
			    
			    Hoy.set(Calendar.HOUR_OF_DAY, hour);
			    Hoy.set(Calendar.MINUTE, minute);
			    
			    Hoy.add(Calendar.MINUTE,-Minutes );
			    
				
				SimpleDateFormat Format = new SimpleDateFormat();
				Format.applyPattern(__IBTRADER_SHORT_HOUR_FORMAT);
				
				return  Format.format(Hoy.getTime());
			}
			else
			{
				return  "-1";
			}
		}
		catch (Exception e) {return  "-1";}
		
	
	
		
	}
	
	/* HOUR HHMM */
	public static  Calendar getNewCalendarWithHour(String HourMinutes)
	{
		
		
		Calendar Hoy = Calendar.getInstance();
	    
		// 23:59 -_> erroneo 
		if (HourMinutes.contains(":"))
			HourMinutes = HourMinutes.replaceAll(":", "");
		return getNewCalendarWithHour(Hoy.getTime(), HourMinutes);
	    
	}
	
	/* HOUR HHMM */
	public static  Calendar getNewCalendarWithHour(Date  _oDate, String HourMinutes)
	{
		Calendar Hoy = Calendar.getInstance();
		
		Hoy.setTimeInMillis(_oDate.getTime());
	    
	    int hour = Integer.parseInt(HourMinutes.substring(0, 2));
	    int minute = Integer.parseInt(HourMinutes.substring(2,4));
	    
	    
	    Hoy.set(Calendar.HOUR_OF_DAY, hour);
	    Hoy.set(Calendar.MINUTE, minute);
	    Hoy.set(Calendar.SECOND, 0);
	    Hoy.set(Calendar.MILLISECOND, 0);
		
	    
	    return Hoy;
	    
	}
	/* HOUR HHMM */
	
	
	/* HOUR HHMM */
	public static  Date setDateWithHour(Date _oDate, String HourMinutes)
	{
		Calendar Hoy = Calendar.getInstance();
		
		Hoy.setTimeInMillis(_oDate.getTime());
	    
	    int hour = Integer.parseInt(HourMinutes.substring(0, 2));
	    int minute = Integer.parseInt(HourMinutes.substring(2,4));
	    
	    
	    Hoy.set(Calendar.HOUR_OF_DAY, hour);
	    Hoy.set(Calendar.MINUTE, minute);
	    Hoy.set(Calendar.SECOND, 0);
	    Hoy.set(Calendar.MILLISECOND, 0);
		
	    
	    return Hoy.getTime();
	    
	}
	
    
	public static double _Porcentaje100(double num)
	{
		return (num * 100);		
	}
		
	
	public static double RoundPrice(double num) {
		double result = num * 100;
		result = Math.round(result);
		result = result / 100;
		return result;
		}
	
	public static int secondsDiff( Date earlierDate, Date laterDate )
    {
        if( earlierDate == null || laterDate == null ) return 0;
        
        return (int) ((laterDate.getTime() - earlierDate.getTime()) / SECOND_MILLIS);
    }
	
    /**
     * Get the minutes difference
     */
    public static int minutesDiff( Date earlierDate, Date laterDate )
    {
        if( earlierDate == null || laterDate == null ) return 0;
        
        return (int)((laterDate.getTime()/MINUTE_MILLIS) - (earlierDate.getTime()/MINUTE_MILLIS));
    }
    /**
     * Get the minutes difference
     */
    
    public static int daysDiff( Date earlierDate, Date laterDate )
    {
        if( earlierDate == null || laterDate == null ) return 0;
        
        return (int)((laterDate.getTime()/DAYS_MILLIS) - (earlierDate.getTime()/DAYS_MILLIS));
    }
    
    
   


    public static  boolean IsPar(int Number) { 
        return ((Number % 2) == 0);
    }
    
    
    /*  NOS DEVUELVE LA ULTIMA ORDEN VALIDA. NECESITAMOS SABER LA ULTIMA ENTRE LAS 
     * COMPRAS Y LAS VENTAS. 
    */
    public static long  LastPositionIDTws(long companyId, long groupId) throws SQLException, Exception { 
    
    	
    	long PositionID=1;
    	String position_mode = Utilities.getPositionModeType(null, companyId, groupId);

    	Position _position =  PositionLocalServiceUtil.findByCompanyGroup(companyId, groupId, position_mode);
    	if (_position!=null)    		
    		PositionID = _position.getPositionId_tws_in() +1;    	    	
    	return PositionID;

    	
    } 
    
    public static Double findNearestValue(ArrayList<Double> arr, Double value) {
    	
    	 
    	 Double minDiff=new Double(100000.0);
    	 Double ans=null;
    	 for(int z=0;z<arr.size();z++)
    	 {
    	         Double  m=Math.abs(value-arr.get(z).doubleValue());
    	         if(m<minDiff)
    	         { 
    	                minDiff=m; 
    	                ans=arr.get(z).doubleValue(); 
    	         }
    	 }
    	 return ans;
    	
      }
	
 
    public  static  boolean fn_IsStrategyInShareStrategies(long StrategyToFind,java.util.List<StrategyShare>_lStratShare)
	{
		boolean retValue=false;
		for (int j=0;j<_lStratShare.size();j++)
		{
			StrategyShare _oStrategyShare = _lStratShare.get(j);
			if (_oStrategyShare.getStrategyId()==StrategyToFind)
			{
				retValue=true;
				break;
			}
		}
		return retValue;
	}


    /* 
    lDates --> Lista con las fechas de vencimiento de los futuros, tenemos todos, incluso los pasados. formato dd-MM-yyyy   
   */
    
    private static String getActiveFutureByDate(List<String> lExpirationDates,
    		Calendar _FutureDate, String ShortLongFormat)
    
    {
    	SimpleDateFormat sdfLONG = new SimpleDateFormat();    	
    	sdfLONG.applyPattern(_IBTRADER_FUTURE_LONG_DATE);
    	
    	
    	/* esta ordenada */ 
    	
    	Date _retExpiredDate  = null;
    	
    	Calendar cFutureDate = Calendar.getInstance();
    	cFutureDate.setTime(_FutureDate.getTime());
    	cFutureDate.set(Calendar.HOUR, 23);
    	cFutureDate.set(Calendar.MINUTE, 59);
    	cFutureDate.set(Calendar.SECOND, 59);
    	
    	
    	for (String dateString : lExpirationDates)
    	{
    		Date expirationDate;
			try {
				expirationDate = sdfLONG.parse(dateString);
				Calendar cExpiration = Calendar.getInstance();
	    		cExpiration.setTimeInMillis(expirationDate.getTime());
	    		cExpiration.set(Calendar.HOUR, 23);
	    		cExpiration.set(Calendar.MINUTE, 59);
	    		cExpiration.set(Calendar.SECOND, 59);
	    		/* si no  ha pasado la fecha  y hay menos dias, la eligo. */
	    		if (cExpiration.after(_FutureDate) || cExpiration.equals(cFutureDate))
	    		{
	    			_retExpiredDate = cExpiration.getTime();
	    			break;	    			
	    		}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				log.debug(e.getMessage());				
			}			
    		
    	}
    	if (Validator.isNotNull(_retExpiredDate))
    		return sdfLONG.format(_retExpiredDate);    	    	
    	else
    		return "";
    }
    
    
    public static String getActiveFutureDateByDate(List<String> lDates, Calendar cDate)
    {
    	return  getActiveFutureByDate(lDates, cDate, "L");
    	
    }
    
    public static String getActiveFutureDate(List<String> lDates)
    {
    	return  getActiveFutureByDate( lDates, Calendar.getInstance(), "L");
    	
    }
    
    public static String getExpFutMonths(String _value) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException 
    {
    	return parseXmlFutureInfo(_value,"months");
    }
    public static String getExpFutWeek(String _value) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException 
    {
    	return parseXmlFutureInfo(_value,"week");
    }
    public static String getExpFutDayOfWeek(String _value) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException 
    {
    	return parseXmlFutureInfo(_value,"day");
    }
    
    /* 
    <?xml version="1.0"><expiration><months>2,3</months><week>4</week><day>5</day></expiration> 
     */
    private static  String parseXmlFutureInfo(String _Xml, String Field) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException
    {
    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    	
		InputStream is = new ByteArrayInputStream(_Xml.getBytes());
    	Document doc = dBuilder.parse(is);
    	
    	
    	XPath xPath =  XPathFactory.newInstance().newXPath();
    	String expression = "/expiration/" + Field;
    	 
    	//read a string value
    	String FieldValue = xPath.compile(expression).evaluate(doc);
    	
    	return FieldValue;
		
    		
    }
    
    public static ClassLoader getContextClassLoader() {
    	if(_ClassLoader==null) {
    		ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
    	   _ClassLoader=currentClassLoader;
    	}
    	
    	return _ClassLoader;
    }

    private static ClassLoader _ClassLoader;
    
    
 

    private  static  Map<String, String> getAllZoneIds(List<String> zoneList) {

     
    	   Map<String, String> result = new HashMap<>();
        LocalDateTime dt = LocalDateTime.now();

        for (String zoneId : zoneList) {

            ZoneId zone = ZoneId.of(zoneId);
            ZonedDateTime zdt = dt.atZone(zone);
            ZoneOffset zos = zdt.getOffset();

            //replace Z to +00:00
            String offset = zos.getId().replaceAll("Z", "+00:00");

            result.put(zone.toString(), offset);

        }

        return result;

    }

    public static void main(String[] args) throws Exception {
 		// TODO Auto-generated method stub
    	System.out.println(new Date());
    	Calendar now = Calendar.getInstance();
        
    	Date nowDate = new Date();
    	System.out.println("nowDate" + nowDate);
    	ZoneId zoneId = ZoneId.of("UTC"); // UTC 
    	LocalDateTime lct = LocalDateTime.ofInstant(nowDate.toInstant(), zoneId);
    	System.out.println("lct" + lct);
    	ZonedDateTime zonedDateTimeUTC= lct.atZone(zoneId);
    	System.out.println("zonedDateTimeUTC" + zonedDateTimeUTC);
   		 // UTC    	
   		ZoneId userZoneId = ZoneId.of("Europe/Paris"); 
   		ZonedDateTime zonedDateTime = zonedDateTimeUTC.withZoneSameInstant(userZoneId);
   		System.out.println("zonedDateTime" + zonedDateTime);   	
   		System.out.println(Date.from(zonedDateTime.toInstant()));
    	
    	
    	
    	/* LocalDateTime nowLocal = LocalDateTime.now();
    	System.out.println("nowDate:" + nowDate);
    	System.out.println("nowLocal:" + nowLocal);
    	
    	ZoneId zoneId = ZoneId.of("UTC");    	
    	ZoneId zoneId2 = ZoneId.of("Europe/Paris");
    	
    
    	ZonedDateTime z2 = ZonedDateTime.of(nowLocal, zoneId );   		
   		ZonedDateTime zonedDateTime = nowDate.toInstant().atZone(zoneId);
   		ZonedDateTime zonedDateTime2 = nowLocal.atZone(zoneId);

   		System.out.println("UTC toUserZoneId:" + zonedDateTime);
   		System.out.println("UTC toUserZoneId2:" + zonedDateTime2);
   		System.out.println("UTC z2:" + z2);
   		
   		ZonedDateTime z12 = ZonedDateTime.of(nowLocal, zoneId2 );   		
   		ZonedDateTime zonedDateTime1 = nowDate.toInstant().atZone(zoneId2);
   		ZonedDateTime zonedDateTime12 = nowLocal.atZone(zoneId2);
   		
   		System.out.println("Europe toUserZoneId:" + zonedDateTime1);
   		System.out.println("Europe  toUserZoneId2:" + zonedDateTime12);
   		System.out.println("Europe  z2:" + z12);
    	
    	
    	LocalDate  now2 = LocalDate.now();	
		ZoneId UTCZone = ZoneId.systemDefault(); // UTC 	
   		ZonedDateTime startOfDay = now2.atStartOfDay(UTCZone);
   		
   		System.out.println("startOfDay:" + startOfDay);
    	System.out.println(now.getTimeZone());
        System.out.println(now.getTime());
        System.out.println(	(new Date()));
        
        ZoneId Paris = ZoneId.of("EET"); 
        ZonedDateTime zParis = ZonedDateTime.now ( Paris );
        ZonedDateTime nowUtc = zParis.withZoneSameInstant( ZoneOffset.UTC );
        
        
        System.out.println("zParis: " + zParis.toLocalDateTime());
        System.out.println("zutc: " + nowUtc.toLocalDateTime());
        */
      

        Map<String, String> sortedMap = new LinkedHashMap<>();

        List<String> zoneList = new ArrayList<>(ZoneId.getAvailableZoneIds());

        Map<String, String> allZoneIds = getAllZoneIds(zoneList);

        //sort map by key
        /*allZoneIds.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(e -> sortedMap.put(e.getKey(), e.getValue()));*/

        //sort by value, descending order
        allZoneIds.entrySet().stream()
                .sorted(Map.Entry.<String, String>comparingByValue().reversed())
                .forEachOrdered(e -> sortedMap.put(e.getKey(), e.getValue()));

        // print map
        sortedMap.forEach((k, v) ->
        {
            String out = String.format("%35s (UTC%s) %n", k, v);
        //    System.out.printf(out);
        });

		System.out.println("\nTotal Zone IDs " + sortedMap.size());
                
       Calendar june = Calendar.getInstance();
       june.set(Calendar.MONTH, 5);
       
        
		//System.out.println(TickLimit_WithMultiplier(new Double(2750.25), new Double(0.25), new Double(2749.9), true));
		
 	}	


}
