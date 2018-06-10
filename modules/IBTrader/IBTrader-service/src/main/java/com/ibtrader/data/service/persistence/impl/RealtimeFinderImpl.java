package com.ibtrader.data.service.persistence.impl;

import java.util.Date;
import java.util.List;

import com.ibtrader.data.model.Realtime;
import com.ibtrader.data.model.impl.RealtimeImpl;
import com.ibtrader.data.service.persistence.RealtimeFinder;
import com.ibtrader.interactive.TIMApiWrapper;
import com.liferay.portal.dao.orm.custom.sql.CustomSQLUtil;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactory;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.StringUtil;

public class RealtimeFinderImpl extends RealtimeFinderBaseImpl  implements RealtimeFinder {
	
	Log _log = LogFactoryUtil.getLog(RealtimeFinderImpl.class);
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
	 
		
	
		public static final String FIND_MINMAX_REALTIME = RealtimeFinder.class.getName() + ".findMinMaxRealTime";
		public static final String FIND_LAST_REALTIME = RealtimeFinder.class.getName() + ".findLastRealTime";
		public static final String FIND_LAST_REALTIMES_GROUP_BY_PERIODS = RealtimeFinder.class.getName() + ".findSimpleMobileAvgGroupByPeriods";
		public static final String FIND_LAST_REALTIME_LESS_THAN_DATE = RealtimeFinder.class.getName() + ".findLastRealTimeLessThanDate";
		

		
}