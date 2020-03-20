package com.ibtrader.data.service.persistence.impl;

import java.sql.Connection;
import java.sql.Statement;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.List;

import com.ibtrader.constants.IBTraderConstants;
import com.ibtrader.data.model.HistoricalRealtime;
import com.ibtrader.data.model.Realtime;
import com.ibtrader.data.model.impl.HistoricalRealtimeImpl;
import com.ibtrader.data.model.impl.RealtimeImpl;
import com.ibtrader.data.service.ConfigLocalServiceUtil;
import com.ibtrader.data.service.persistence.HistoricalRealtimeFinder;
import com.ibtrader.data.service.persistence.RealtimeFinder;
import com.ibtrader.interactive.TIMApiWrapper;
import com.ibtrader.util.ConfigKeys;
import com.ibtrader.util.Utilities;
import com.liferay.portal.dao.orm.custom.sql.CustomSQLUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactory;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;

public class RealtimeFinderImpl extends RealtimeFinderBaseImpl  implements RealtimeFinder {
	
	Log _log = LogFactoryUtil.getLog(RealtimeFinderImpl.class);
	
	
	/* BORRADO DE DATOS DE REALTIME ANTERIORES
	 DEJAMOS x DIAS DE REALTIME SOLO. EXCEPTO LOS PRECIOS DE CIERRE
	 */
	public void  removeScheduledRealTimes( long companyId, long groupId)
	{
	
	 Connection con = null;
	 Statement st = null;		
	
		  
	    /* DELETE FROM */
	    try 
		{
	 	long daysFrom = Long.valueOf(Utilities.getConfigurationValue(IBTraderConstants.keyNUM_DAYS_PAST_REALTIME, companyId, groupId)).intValue();  // el dos para leer, el 3 para escribir	
		
		if (daysFrom>0)
		{
			
		   _log.debug("Executing removeScheduledRealTimes from " + daysFrom + " days ago");
			String sql = CustomSQLUtil.get(getClass(),REMOVE_SCHEDULED_REALTIME);
	        sql = StringUtil.replace(sql, "?", String.valueOf(daysFrom));
			con = DataAccess.getUpgradeOptimizedConnection();
			st = con.createStatement();
			int result = st.executeUpdate(sql);
			_log.debug("Executed removeScheduledRealTimes  code:" +result );
	    }
		}
		catch (Exception e) {
			_log.debug(e.getMessage());
		}
	    finally {
	    	DataAccess.cleanUp(con, st);
	    }        
     
	    
	}
	
	@SuppressWarnings("unchecked")
	public Realtime findSumVolumeBetweenBars(Date from, Date to, long shareId, long companyId, long groupId)
	{
	 List<Realtime> lRealtime = null;
	 Session session = null;
	  try {
	        session = openSession();

	        String sql = CustomSQLUtil.get(getClass(),FIND_VOLUMEN_BETWEEN_BARS);

	        SQLQuery q = session.createSQLQuery(sql);
	        q.setCacheable(false);
	        q.addEntity("IBTrader_Realtime", RealtimeImpl.class);
	       // q.addScalar("total_volume", com.liferay.portal.kernel.dao.orm.Type.LONG);
	        QueryPos qPos = QueryPos.getInstance(q);        
	        qPos.add(companyId);
	        qPos.add(groupId);
	        qPos.add(shareId);
	        qPos.add(from);
	        qPos.add(to);
	        
	        
	        lRealtime = (List) QueryUtil.list(q, getDialect(), 0, 10);
	        if (!lRealtime.isEmpty())
	        		return lRealtime.get(0);
	        else
	        		return null;
	        
	    }
	    catch (Exception e) {
	        try {
	            throw new SystemException(e);
	        }
	        catch (SystemException se) {
	            se.printStackTrace();
	        }
	    }
	    finally {
	        closeSession(session);
	    }

		return null;
		}
	
	
	
	@SuppressWarnings("unchecked")
	public List findMinMaxRealTime(Date from, Date to, long shareId, long companyId, long groupId)
	{
	 List lRealtime = null;
	 Session session = null;
	  try {
	        session = openSession();

	        String sql = CustomSQLUtil.get(getClass(),FIND_MINMAX_REALTIME);

	        SQLQuery q = session.createSQLQuery(sql);
	        q.setCacheable(false);
	        q.addScalar("min_value", com.liferay.portal.kernel.dao.orm.Type.DOUBLE);
	        q.addScalar("max_value", com.liferay.portal.kernel.dao.orm.Type.DOUBLE);	        
	        //q.addEntity("IBTrader_Realtime", RealtimeImpl.class);

	        QueryPos qPos = QueryPos.getInstance(q);
	        qPos.add(from);
	        qPos.add(to);
	        qPos.add(shareId);
	        qPos.add(companyId);
	        qPos.add(groupId);
	        
	        
	        lRealtime = (List) QueryUtil.list(q, getDialect(), 0, 10);
	        if (!lRealtime.isEmpty())
	        		return lRealtime;
	        else
	        		return null;
	        
	    }
	    catch (Exception e) {
	        try {
	            throw new SystemException(e);
	        }
	        catch (SystemException se) {
	            se.printStackTrace();
	        }
	    }
	    finally {
	        closeSession(session);
	    }

		return null;
		}
	
	@SuppressWarnings("unchecked")
	public Realtime findMaxRealTime(Date from, Date to, long shareId, long companyId, long groupId)
	{
	 List<Realtime> lRealtime = null;
	 Session session = null;
	  try {
	        session = openSession();

	        String sql = CustomSQLUtil.get(getClass(),FIND_MAX_REALTIME);

	        SQLQuery q = session.createSQLQuery(sql);
	        q.setCacheable(false);
	        q.addEntity("IBTrader_Realtime", RealtimeImpl.class);

	        QueryPos qPos = QueryPos.getInstance(q);
	        qPos.add(from);
	        qPos.add(to);
	        qPos.add(shareId);
	        qPos.add(companyId);
	        qPos.add(groupId);
	        
	        lRealtime = (List<Realtime>) QueryUtil.list(q, getDialect(), 0, 10);
	        if (!lRealtime.isEmpty())
	        		return lRealtime.get(0);
	        else
	        		return null;
	        
	    }
	    catch (Exception e) {
	        try {
	            throw new SystemException(e);
	        }
	        catch (SystemException se) {
	            se.printStackTrace();
	        }
	    }
	    finally {
	        closeSession(session);
	    }

		return null;
		}
	
	@SuppressWarnings("unchecked")
	public Realtime findMinRealTime(Date from, Date to, long shareId, long companyId, long groupId)
	{
	 List<Realtime> lRealtime = null;
	 Session session = null;
	  try {
	        session = openSession();

	        String sql = CustomSQLUtil.get(getClass(),FIND_MIN_REALTIME);

	        SQLQuery q = session.createSQLQuery(sql);
	        q.setCacheable(false);
	        q.addEntity("IBTrader_Realtime", RealtimeImpl.class);

	        QueryPos qPos = QueryPos.getInstance(q);
	        qPos.add(from);
	        qPos.add(to);
	        qPos.add(shareId);
	        qPos.add(companyId);
	        qPos.add(groupId);
	        
	        lRealtime = (List<Realtime>) QueryUtil.list(q, getDialect(), 0, 10);
	        if (!lRealtime.isEmpty())
	        		return lRealtime.get(0);
	        else
	        		return null;
	        
	    }
	    catch (Exception e) {
	        try {
	            throw new SystemException(e);
	        }
	        catch (SystemException se) {
	            se.printStackTrace();
	        }
	    }
	    finally {
	        closeSession(session);
	    }

		return null;
		}
	
	
	public List findMinMaxRealTimesGroupedByBars(Date from, Date to, long shareId, long companyId, long groupId, long timebars,String openMarketUTC,String closeMarketUTC)
	{
	 List lRealtime = null;
	 Session session = null;
	  try {
	        session = openSession();

	        String sql = CustomSQLUtil.get(getClass(),FIND_MINMAX_REALTIME_GROUPED_BY_BARS);
	        SQLQuery q = session.createSQLQuery(sql);
	        q.setCacheable(false);
	        q.addScalar("min_value", com.liferay.portal.kernel.dao.orm.Type.DOUBLE);
	        q.addScalar("max_value", com.liferay.portal.kernel.dao.orm.Type.DOUBLE);	  
	        q.addScalar("barDate", com.liferay.portal.kernel.dao.orm.Type.TIMESTAMP);	  

	        QueryPos qPos = QueryPos.getInstance(q);	
	        
	        qPos.add(timebars);
	        qPos.add(timebars);
	        qPos.add(from);
	        qPos.add(to);
	        qPos.add(shareId);
	        qPos.add(companyId);
	        qPos.add(groupId);
	        qPos.add(openMarketUTC);
	        qPos.add(closeMarketUTC);
	        q.setMaxResults(ConfigKeys.INDICATORS_MIN_SERIE_COUNT);
	        
	        
	        lRealtime = (List<Realtime>) QueryUtil.list(q, getDialect(), 0, ConfigKeys.INDICATORS_MIN_SERIE_COUNT);
	        
	        if (_log.isDebugEnabled())
	        	_log.info(sql);
	        
	        if (!lRealtime.isEmpty())
	        		return lRealtime;
	        else
	        		return null;
	        
	    }
	    catch (Exception e) {
	        try {
	            throw new SystemException(e);
	        }
	        catch (SystemException se) {
	            se.printStackTrace();
	        }
	    }
	    finally {
	        closeSession(session);
	    }

		return null;
		}
	
	@SuppressWarnings("unchecked")
	public Realtime findLastRealTime(long shareId, long companyId, long groupId)
	{
	 List<Realtime> lRealtime = null;
	 Session session = null;
	    try {
	        session = openSession();

	        String sql = CustomSQLUtil.get(getClass(),FIND_LAST_REALTIME);

	        SQLQuery q = session.createSQLQuery(sql);
	        q.setCacheable(false);
	        q.addEntity("IBTrader_Realtime", RealtimeImpl.class);

	        QueryPos qPos = QueryPos.getInstance(q);	    
	        qPos.add(shareId);
	        qPos.add(companyId);
	        qPos.add(groupId);
	        qPos.add(shareId);
	        qPos.add(companyId);
	        qPos.add(groupId);

	        lRealtime = (List<Realtime>) QueryUtil.list(q, getDialect(), 0, 10);
	        if (!lRealtime.isEmpty())
	        		return lRealtime.get(0);
	        else
	        		return null;
	        
	    }
	    catch (Exception e) {
	        try {
	            throw new SystemException(e);
	        }
	        catch (SystemException se) {
	            se.printStackTrace();
	        }
	    }
	    finally {
	        closeSession(session);
	    }

		return null;
		}
	
	
	/* @SuppressWarnings("unchecked")
	public Realtime findCloseRealTimeDate(long shareId, long companyId, long groupId, Date date, boolean isClosePrice)
	{
	 List<Realtime> lRealtime = null;
	 Session session = null;
	    try {
	        session = openSession();

	        String sql = CustomSQLUtil.get(getClass(),FIND_CLOSE_REALTIME_DATE);

	        SQLQuery q = session.createSQLQuery(sql);
	        q.setCacheable(false);	   	  
	        q.addEntity("IBTrader_Realtime", RealtimeImpl.class);

	        QueryPos qPos = QueryPos.getInstance(q);	    
	        qPos.add(shareId);
	        qPos.add(companyId);
	        qPos.add(groupId);	      
	        qPos.add(date);
	        qPos.add(isClosePrice);

	        lRealtime = (List<Realtime>) QueryUtil.list(q, getDialect(), 0, 10);
	        if (!lRealtime.isEmpty())
	        		return lRealtime.get(0);
	        else
	        		return null;
	        
	    }
	    catch (Exception e) {
	        try {
	            throw new SystemException(e);
	        }
	        catch (SystemException se) {
	            se.printStackTrace();
	        }
	    }
	    finally {
	        closeSession(session);
	    }

		return null;
		}
	*/
	@SuppressWarnings("unchecked")
	public Realtime findLastRealTimeLessThanDate(long shareId, long companyId, long groupId, Date To)
	{
	 List<Realtime> lRealtime = null;
	 Session session = null;
	    try {
	        session = openSession();

	        String sql = CustomSQLUtil.get(getClass(),FIND_LAST_REALTIME_LESS_THAN_DATE);

	        SQLQuery q = session.createSQLQuery(sql);
	        q.setCacheable(false);	   	  
	        q.addEntity("IBTrader_Realtime", RealtimeImpl.class);

	        QueryPos qPos = QueryPos.getInstance(q);	    
	        qPos.add(shareId);
	        qPos.add(companyId);
	        qPos.add(groupId);
	        qPos.add(shareId);
	        qPos.add(companyId);
	        qPos.add(groupId);
	        qPos.add(To);

	        lRealtime = (List<Realtime>) QueryUtil.list(q, getDialect(), 0, 10);
	        if (!lRealtime.isEmpty())
	        		return lRealtime.get(0);
	        else
	        		return null;
	        
	    }
	    catch (Exception e) {
	        try {
	            throw new SystemException(e);
	        }
	        catch (SystemException se) {
	            se.printStackTrace();
	        }
	    }
	    finally {
	        closeSession(session);
	    }

		return null;
		}
	
	@SuppressWarnings("unchecked")
	public List findSimpleMobileAvgGroupByPeriods(long shareId, long companyId, long groupId,Date from, Date to, List<String> mobileAvgDates)
	{
	 List lSampleMobileAvg = null;
	 
	 Session session = null;
	 try {
        session = openSession();

        String sql = CustomSQLUtil.get(getClass(),FIND_LAST_REALTIMES_GROUP_BY_PERIODS);
        
        String Dates_IN = String.join("','", mobileAvgDates);
        StringBuilder sDatesIN = new StringBuilder("'");
        sDatesIN.append(Dates_IN);
        //StringBuilder sDatesIN = new StringBuilder(Dates_IN);
        sDatesIN.append("'");
        sql = StringUtil.replace(sql, "[$TIMEBAR_DATES$]", sDatesIN.toString());

        SQLQuery q = session.createSQLQuery(sql);
        
        q.setCacheable(false);
      //  q.addEntity("IBTrader_Realtime", RealtimeImpl.class);
        q.addScalar("value", com.liferay.portal.kernel.dao.orm.Type.DOUBLE);
	    q.addScalar("periodsfound", com.liferay.portal.kernel.dao.orm.Type.LONG);	   
	    
	    _log.debug("findSimpleMobileAvgGroupByPeriods:" + sql + ",q:" + q);
         
        QueryPos qPos = QueryPos.getInstance(q);	    
        qPos.add(from);
        qPos.add(to);
        qPos.add(shareId);
        qPos.add(companyId);
        qPos.add(groupId);        
        
        // no funciona 
        //qPos.add(mobileAvgDates.toArray(new Date[mobileAvgDates.size()]));
      //  System.out.println(sql);
       
        
        
        lSampleMobileAvg = (List) QueryUtil.list(q, getDialect(), 0, 10);
        
        if (_log.isDebugEnabled())
        	_log.info(sql + ",from:" + from + ",to:" + to + ",share:" + shareId + ",compamy:" + companyId + ",groupid:" + groupId + ",isnull:" + lSampleMobileAvg.isEmpty());
        
        if (!lSampleMobileAvg.isEmpty())
    		return lSampleMobileAvg;
        else
    		return null;
	        
	    }
	    catch (Exception e) {
	        try {
	            throw new SystemException(e);
	        }
	        catch (SystemException se) {
	            se.printStackTrace();
	        }
	    }
	    finally {
	        closeSession(session);
	    }

		return null;
		}
	 
	
	@SuppressWarnings("unchecked")
	public List<Realtime> findCloseRealTimes(long shareId, long companyId, long groupId,Date from, Date to, List<String> mobileAvgDates)
	{
	 List lExponentialMobile = null;
	 
	 Session session = null;
	 try {
        session = openSession();

        String sql = CustomSQLUtil.get(getClass(),FIND_CLOSE_REALTIMES);
        
        /* 20190515. A PESAR DE QUE LA QUERY CONTEMPLA  UN < to, parece que las barras de 59.59 no me las coge, le sumo un segundo, no debe 
         * afectar ya que los IN los obtiene igualmente y filtra por minuto 59 cogiendo el maximo.
         *    */ 
         
        Instant  instanceAddedDate  = to.toInstant();
        instanceAddedDate = instanceAddedDate.plusSeconds(1);
        to = Date.from(instanceAddedDate);        
        
        /* String Dates_IN = String.join("','", mobileAvgDates);
        StringBuilder sDatesIN = new StringBuilder("'");
        sDatesIN.append(Dates_IN);
        //StringBuilder sDatesIN = new StringBuilder(Dates_IN);
        sDatesIN.append("'");
        sql = StringUtil.replace(sql, "[$TIMEBAR_DATES$]", sDatesIN.toString());
		*/
        SQLQuery q = session.createSQLQuery(sql);        
        q.setCacheable(false);
        q.addEntity("IBTrader_Realtime", RealtimeImpl.class);
        q.setMaxResults(ConfigKeys.INDICATORS_MIN_SERIE_COUNT);
        
        QueryPos qPos = QueryPos.getInstance(q);	    
        qPos.add(from);
        qPos.add(to);
        qPos.add(shareId);
        qPos.add(companyId);
        qPos.add(groupId);        
        qPos.add(ConfigKeys.DEFAULT_TIMEBAR_MINUTES); // 5 
        qPos.add(ConfigKeys.DEFAULT_TIMEBAR_MINUTES-1); // RESTO PARA QUE SEAM 34 39 44 
        
       
        
        
        
        lExponentialMobile = (List<Realtime>) QueryUtil.list(q, getDialect(), 0, ConfigKeys.INDICATORS_MIN_SERIE_COUNT);
        
       
        _log.debug(sql + ",periods:" + mobileAvgDates + ",from:" + from + ",to:" + to + ",share:" + shareId + ",compamy:" + companyId + ",groupid:" + groupId + ",results:" + (lExponentialMobile.isEmpty() ? 0 :lExponentialMobile.size()));
        
        if (!lExponentialMobile.isEmpty())
        {
            if (_log.isDebugEnabled())
            {
	        	for (Object  otime : lExponentialMobile)
	        	{
	        		
	        		Realtime rtime = (Realtime)  otime;
	        		_log.debug("realtime date: " + rtime.getCreateDate() + ",value=" + rtime.getValue());
	        	}
            }
        	return lExponentialMobile;
        }
        else
    		return null;
	        
	    }
	    catch (Exception e) {
	        try {
	            throw new SystemException(e);
	        }
	        catch (SystemException se) {
	            se.printStackTrace();
	        }
	    }
	    finally {
	        closeSession(session);
	    }

		return null;
		}
	
	@SuppressWarnings("unchecked")
	public Realtime findFirstRealTimeBetweenDates(long shareId, long companyId, long groupId, Date from, Date to)
	{
	 List<Realtime> lRealtime = null;
	 Session session = null;
	    try {
	        session = openSession();

	        String sql = CustomSQLUtil.get(getClass(),FIND_FIRST_REALTIME_LESS_THAN_DATE);

	        SQLQuery q = session.createSQLQuery(sql);
	        q.setCacheable(false);	   	  
	        q.addEntity("IBTrader_Realtime", RealtimeImpl.class);

	        QueryPos qPos = QueryPos.getInstance(q);	    
	        qPos.add(shareId);
	        qPos.add(companyId);
	        qPos.add(groupId);
	        qPos.add(shareId);
	        qPos.add(companyId);
	        qPos.add(groupId);
	        qPos.add(from);
	        qPos.add(to);

	        lRealtime = (List<Realtime>) QueryUtil.list(q, getDialect(), 0, 10);
	        if (!lRealtime.isEmpty())
	        		return lRealtime.get(0);
	        else
	        		return null;
	        
	    }
	    catch (Exception e) {
	        try {
	            throw new SystemException(e);
	        }
	        catch (SystemException se) {
	            se.printStackTrace();
	        }
	    }
	    finally {
	        closeSession(session);
	    }

		return null;
		}
		
	
		public static final String FIND_MINMAX_REALTIME = RealtimeFinder.class.getName() + ".findMinMaxRealTime";
		public static final String FIND_MAX_REALTIME = RealtimeFinder.class.getName() + ".findMaxRealTime";
		public static final String FIND_MIN_REALTIME = RealtimeFinder.class.getName() + ".findMinRealTime";
		public static final String FIND_LAST_REALTIME = RealtimeFinder.class.getName() + ".findLastRealTime";
		public static final String FIND_LAST_REALTIMES_GROUP_BY_PERIODS = RealtimeFinder.class.getName() + ".findSimpleMobileAvgGroupByPeriods";
		public static final String FIND_LAST_REALTIME_LESS_THAN_DATE = RealtimeFinder.class.getName() + ".findLastRealTimeLessThanDate";
		public static final String FIND_CLOSE_REALTIME_DATE = RealtimeFinder.class.getName() + ".findCloseRealTimeDate";
		public static final String FIND_CLOSE_REALTIMES = RealtimeFinder.class.getName() + ".findCloseRealTimes";
		public static final String FIND_MINMAX_REALTIME_GROUPED_BY_BARS = RealtimeFinder.class.getName() + ".findMinMaxRealTimesBars";
		public static final String REMOVE_SCHEDULED_REALTIME = RealtimeFinder.class.getName() + ".removeScheduledRealTimes";
		public static final String FIND_FIRST_REALTIME_LESS_THAN_DATE = RealtimeFinder.class.getName() + ".findFirstRealTimeBetweenDates";		
		public static final String FIND_VOLUMEN_BETWEEN_BARS = RealtimeFinder.class.getName() + ".findVolumenBetweenDates";
		


		
}