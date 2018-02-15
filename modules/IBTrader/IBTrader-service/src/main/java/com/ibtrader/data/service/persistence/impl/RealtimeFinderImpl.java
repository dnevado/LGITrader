package com.ibtrader.data.service.persistence.impl;

import java.util.Date;
import java.util.List;

import com.ibtrader.data.model.Realtime;
import com.ibtrader.data.model.impl.RealtimeImpl;
import com.ibtrader.data.service.persistence.RealtimeFinder;
import com.liferay.portal.dao.orm.custom.sql.CustomSQLUtil;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;

public class RealtimeFinderImpl extends RealtimeFinderBaseImpl  implements RealtimeFinder {
	
	@SuppressWarnings("unchecked")
	public List<Double[]> findMinMaxRealTime(Date from, Date to, long shareId, long companyId, long groupId)
	{
	 List<Double[]> lRealtime = null;
	 Session session = null;
	  try {
	        session = openSession();

	        String sql = CustomSQLUtil.get(getClass(),FIND_MINMAX_REALTIME);

	        SQLQuery q = session.createSQLQuery(sql);
	        q.setCacheable(false);
	        q.addScalar("min(value)", com.liferay.portal.kernel.dao.orm.Type.DOUBLE);
	        q.addScalar("max(value)", com.liferay.portal.kernel.dao.orm.Type.DOUBLE);	        
	        //q.addEntity("IBTrader_Realtime", RealtimeImpl.class);

	        QueryPos qPos = QueryPos.getInstance(q);
	        qPos.add(from);
	        qPos.add(to);
	        qPos.add(shareId);
	        qPos.add(companyId);
	        qPos.add(groupId);

	        lRealtime = (List<Double[]>) QueryUtil.list(q, getDialect(), 0, 10);
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
	 
		public static final String FIND_MINMAX_REALTIME = RealtimeFinder.class.getName() + ".findMinMaxRealTime";
		public static final String FIND_LAST_REALTIME = RealtimeFinder.class.getName() + ".findLastRealTime";
		
}