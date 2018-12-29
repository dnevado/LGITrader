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
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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

import com.liferay.portal.kernel.exception.PortalException;
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
import com.ibtrader.cron.IBTraderTrade;
import com.ibtrader.data.model.Config;
import com.ibtrader.data.model.Position;
import com.ibtrader.data.model.StrategyShare;
import com.ibtrader.data.service.ConfigLocalService;
import com.ibtrader.data.service.ConfigLocalServiceUtil;
import com.ibtrader.data.service.PositionLocalService;
import com.ibtrader.data.service.PositionLocalServiceUtil;
import com.ibtrader.interactive.TIMApiGITrader_NOVALE;


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


	@Reference(unbind = "-")
	protected void setConfigService(ConfigLocalService configLocalService) {
		_configLocalService = configLocalService;
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
  
   
   
  
    public static void closeTWSConnection(TIMApiGITrader_NOVALE oTWS)
    {
    try
    {
		if (oTWS.GITraderTWSIsConnected())
		{
			oTWS.GITraderDisconnectFromTWS();
		}
    }
    catch (Exception e) {}
    }
    
    
    public static String  getWebFormattedShortDate(Date _date)
   	{       	
    	SimpleDateFormat _Format = new SimpleDateFormat(_IBTRADER_WEB_FORMAT_SHORTDATE);   	
    	return  _Format.format(_date);
   		
   	}
    
    
    public static String  getWebFormattedShortDate(Date _date, User user)
   	{       	
    	SimpleDateFormat _Format = new SimpleDateFormat(_IBTRADER_WEB_FORMAT_SHORTDATE);   	
    	return  _Format.format(getIBDateByUserDate(user,_date));
   		
   	}
    
    public static String  getWebFormattedDate(Date _date, User user)
   	{       	
    	SimpleDateFormat _Format = new SimpleDateFormat(_IBTRADER_WEB_FORMAT_DATE);   	
    	return  _Format.format(getIBDateByUserDate(user,_date));
   		
   	}
    public static String  getWebFormattedTime(Date _date, User user)
   	{       	
    	SimpleDateFormat _Format = new SimpleDateFormat(__IBTRADER_LONG_HOUR_FORMAT);   	
    	return  _Format.format(getIBDateByUserDate(user,_date));

   		
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
    	SimpleDateFormat Format = new SimpleDateFormat(__IBTRADER_LONG_HOUR_FORMAT);   		
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
    	
    	
   		/* ZoneId zoneId = ZoneId.of(_user.getTimeZoneId());
   		Date date = new Date();
   		ZonedDateTime zonedDateTime = date.toInstant().atZone(zoneId);
   		return Date.to(zonedDateTime.toInstant());
   		*/
    	
    }
    
    /* HORA PARA EL USUARIO DEPEDE DE LA HORA DEL SERVIDOR Y SU ZONA UTC */
    private static  Date getIBDateByUserDate(User _user, Date date)
    {
    	    	
   		ZoneId zoneId = ZoneId.of(_user.getTimeZoneId());
   		ZonedDateTime zonedDateTime = date.toInstant().atZone(zoneId);
   		return Date.from(zonedDateTime.toInstant());
    	
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
    
    
 
    
  /*   public static  String getDateNowFormat()
	{
    	SimpleDateFormat Format = new SimpleDateFormat("yyyyMMdd");
//		Format.applyPattern("yyyyMMDD"); 
		Calendar  oCalendar = Calendar.getInstance();
		
		return  Format.format(oCalendar.getTime());
		
	}
    */
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
    /* 
    public static Double GetTickMaxMinValueFromArray(ArrayList<Double> ticksValue, IDENTITY_VALUE_TYPE type)
    {
    	double number =0;
    	number = Double.MAX_VALUE;
    	if (type.equals(IDENTITY_VALUE_TYPE.MAX))
    	{	
    		number = Double.MIN_VALUE;    		
    	}
    	double _tmpTick=0;
    	for(int i = 0; i < ticksValue.size(); i++) 
    	{
    		  _tmpTick= ticksValue.get(i).doubleValue();
    		  if (type.equals(IDENTITY_VALUE_TYPE.MAX))
    	      {	
    			   if (_tmpTick > number) {
    				   number = ticksValue.get(i);
    			   	}    		
    	      }
    		  else
    		  {
    			  if (_tmpTick < number) {
   				   	number = ticksValue.get(i);
   			   	   }
    			  
    		  }
    	}		  
    	
		return number;
    	
    	
    }*/
    
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
	
    
	/* public static  String getActualHourFormat()
	{
		SimpleDateFormat Format = new SimpleDateFormat();
		Format.applyPattern(__IBTRADER_SHORT_HOUR_FORMAT);
		Calendar  oCalendar = Calendar.getInstance();
		Date Now = new Date();
		
		
		return  Format.format(Now);
		
	}*/
	
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
    
    
    /* POR CADA ESTRATEGIA DE BBDD, DEVOLVEMOS LOS IMPLEMENTATIONS ASOCIADOS A CADA CLASSNAME 
    public static java.util.List<Strategy>  LoadStrategies(List StrategiesBBDD) throws Exception
    {
    	List<Strategy> lFactoryStra = new ArrayList<Strategy>();
    	for (int i=0;i<StrategiesBBDD.size();i++)	    				
    	{
    		
    		Strategy oStratAbstract =  (Strategy) StrategiesBBDD.get(i);    		
    		
    		// cargo la estrategia del classname y ya tengo los datos
    		Class oFactoryStrat =  Class.forName(oStratAbstract.getClassName());
    		//Strategy oStrategyFactory = new Strategy(); 
    		
    		Strategy oStrategyFactory = (Strategy) oFactoryStrat.newInstance();
    		// copiamos de la clase abastracta los nuevos valores de la nueva clase implementada
    		BeanUtils.copyProperties(oStrategyFactory, oStratAbstract);
//    		oStrategyFactory = copyFields(oStrategyFactory, oStratAbstract);
    		
    		lFactoryStra.add(oStrategyFactory);
    		
    		
    	}
		return lFactoryStra;
		
		
         
    }*/
    
    /* POR CADA REGLA DE BBDD, DEVOLVEMOS LOS IMPLEMENTATIONS ASOCIADOS A CADA CLASSNAME 
    public static java.util.List<Rule>  LoadRules(List RulesBBDD) throws Exception
    {
    	List<Rule> lFactoryStra = new ArrayList<Rule>();
    	for (int i=0;i<RulesBBDD.size();i++)	    				
    	{
    		
    		Rule oStratAbstract =  (Rule) RulesBBDD.get(i);    		
    		
    		// cargo la estrategia del classname y ya tengo los datos
    		Class oFactoryStrat =  Class.forName(oStratAbstract.getClassName());
    		//Strategy oStrategyFactory = new Strategy(); 
    		
    		Rule oStrategyFactory = (Rule) oFactoryStrat.newInstance();
    		// copiamos de la clase abastracta los nuevos valores de la nueva clase implementada
    		BeanUtils.copyProperties(oStrategyFactory, oStratAbstract);
//    		oStrategyFactory = copyFields(oStrategyFactory, oStratAbstract);
    		
    		lFactoryStra.add(oStrategyFactory);
    		
    		
    	}
		return lFactoryStra;
		
		
         
    }
    
    
   
	*/


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
    String Months 1,3,5 jANUARY, mARCH..
    String DayOfWeek 1 Monday.. 
    , String WeekMonth 3 Third week of current month
    
    */
    
    public static String getActiveFutureByDate(String Months,String DayOfWeek, String WeekMonth,
    		Calendar _FutureDate, String ShortLongFormat)
    
    {
    	SimpleDateFormat sdfLONG = new SimpleDateFormat();
    	SimpleDateFormat sdfSHORT = new SimpleDateFormat();
    	SimpleDateFormat sdfSorted = new SimpleDateFormat();
    	sdfLONG.applyPattern(_IBTRADER_FUTURE_LONG_DATE);
    	sdfSHORT.applyPattern(_IBTRADER_FUTURE_SHORT_DATE);    	
    	sdfSorted.applyPattern(_IBTRADER_FUTURE_DATE_SORTED);
    	Calendar _Today = Calendar.getInstance();
    	
    	_Today.setTimeInMillis(_FutureDate.getTimeInMillis());    	    
    	int [] _years = {_Today.get(Calendar.YEAR), _Today.get(Calendar.YEAR) +1}; 
    	TreeSet<String> _FutDate = new TreeSet<String>();
    	/* Pregeneramos los intervalos en funcion de los meses que se nos pasa del año actual y del siguiente */
    	for (int y=0;y<_years.length;y++)
    	{
    		String[] aMonths= Months.split(",");
	    	for (int k=0;k<aMonths.length;k++)
	    	{
	    		
	    		Calendar _fdate = _Today;
	    		_fdate.setMinimalDaysInFirstWeek(1);
	    		_fdate.set(Calendar.YEAR, _years[y]);  // SPAIN MONDAY
	    		_fdate.set(Calendar.DAY_OF_WEEK, Integer.parseInt(DayOfWeek));  // SPAIN MONDAY
	    		_fdate.set(Calendar.MONTH, Integer.parseInt(aMonths[k].trim()));  // months -1
	    	    // add +1 to week if first weekday of mounth > dayOfWeek
	    		int localWeek = Integer.parseInt(WeekMonth);
	    	    if (_fdate.get(_fdate.DAY_OF_WEEK) > Integer.parseInt(DayOfWeek)-1) {
	    	        localWeek++;
	    	    }
	    		
	    		_fdate.set(Calendar.WEEK_OF_MONTH, localWeek);  // months -1	    		
	    		_FutDate.add(sdfSorted.format(_fdate.getTime()));
	    	}
    	
    	}
    	String _DateSortedValue =  _FutDate.higher(sdfSorted.format(_FutureDate.getTime()));
    	Date  _DateRetValue = new Date();
    	if (_DateSortedValue!=null)    		
    	{
    		// from yyyy/mm/dd    		
    		try {	
				_DateRetValue = sdfSorted.parse(_DateSortedValue);
				if (ShortLongFormat.equals("L"))					
					return sdfLONG.format(_DateRetValue);
				else
					return sdfSHORT.format(_DateRetValue);
					
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				return "";
			}
    		
    	
    	}
    	else
    		return "";
  //  	else
    //		return "";
    }
    
    public static String getActiveFutureDate(String Months,String DayOfWeek, String WeekMonth )
    {
    	return  getActiveFutureByDate( Months, DayOfWeek, WeekMonth, Calendar.getInstance(), "L");
    	
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
    
    
    public static void main(String[] args) throws Exception {
 		// TODO Auto-generated method stub
    	System.out.println(new Date());
    	Calendar now = Calendar.getInstance();
        System.out.println(now.getTimeZone());
        System.out.println(now.getTime());
        System.out.println(	(new Date()));
        
         ZoneId Paris = ZoneId.of("Europe/Paris"); 
        ZonedDateTime zParis = ZonedDateTime.now ( Paris );
        ZonedDateTime nowUtc = zParis.withZoneSameInstant( ZoneOffset.UTC );
        
        
        System.out.println(zParis.toLocalDate());
        System.out.println(nowUtc.toLocalDate());
       
        
		//System.out.println(TickLimit_WithMultiplier(new Double(2750.25), new Double(0.25), new Double(2749.9), true));
		
 	}	


}
