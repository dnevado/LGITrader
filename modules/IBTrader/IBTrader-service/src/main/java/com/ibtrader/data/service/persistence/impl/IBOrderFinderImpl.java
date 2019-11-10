package com.ibtrader.data.service.persistence.impl;

import java.sql.Connection;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.List;

import com.ibtrader.constants.IBTraderConstants;
import com.ibtrader.data.model.Realtime;
import com.ibtrader.data.model.impl.RealtimeImpl;
import com.ibtrader.data.service.ConfigLocalServiceUtil;
import com.ibtrader.data.service.persistence.IBOrderFinder;
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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

public class IBOrderFinderImpl extends IBOrderFinderBaseImpl  implements IBOrderFinder {
	
	Log _log = LogFactoryUtil.getLog(IBOrderFinderImpl.class);
	
	
	/* BORRADO DE DATOS DE ORDERS  ANTERIORES 
	 */
	public void  deleteRemovableIBOrders( Date untilDate)
	{
	
	   Connection con = null;
	   Statement st = null;		
	
		  
	    /* DELETE FROM */
	    try 
		{
			
	    	SimpleDateFormat sdf = new SimpleDateFormat(Utilities.__IBTRADER_SQL_DATE_);
		   _log.debug("Executing deleteRemovableIBOrders UNTIL  " + untilDate);		   
			String sql = CustomSQLUtil.get(getClass(),REMOVE_REMOVABLE_IBORDERS);
			/* DESDE EL DIA DE HOY , LA QUERY RESTA DIAS */
			sql = StringUtil.replace(sql, "?", String.valueOf(0));
	        //sql = StringUtil.replace(sql, "?", StringPool.APOSTROPHE.concat(sdf.format(untilDate)).concat(StringPool.APOSTROPHE)); 
			con = DataAccess.getUpgradeOptimizedConnection();
			st = con.createStatement();
			int result = st.executeUpdate(sql);
			_log.debug("Executed deleteRemovableIBOrders  code:" +result );			

		}
		catch (Exception e) {
			_log.debug(e.getMessage());
		}
	    finally {
	    	DataAccess.cleanUp(con, st);
	    }        
     
	    
	}
	

		public static final String REMOVE_REMOVABLE_IBORDERS = IBOrderFinder.class.getName() + ".deleteRemovableIBOrders";

		
}