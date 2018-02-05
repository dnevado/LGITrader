<?xml version="1.0" encoding="UTF-8"?>
<custom-sql>

    <sql id="com.ibtrader.data.service.persistence.findMinMaxRealTime">
    <![CDATA[ 
        SELECT 
        		min(value) as min_value, max(value) as max_value
        FROM 
        		IBTrader_Realtime      
        WHERE
            (IBTrader_Realtime.createDate>=?) AND
            (IBTrader_Realtime.createDate<=?) AND
            (IBTrader_Realtime.shareId=?)
            AND (IBTrader_Realtime.companyid=?)
            AND (IBTrader_Realtime.groupid=?)
            
            
     ]]>

    </sql>
     <sql id="com.ibtrader.data.service.persistence.findLastRealTime">
    <![CDATA[ 
        SELECT 
        		value
        FROM 
        		IBTrader_Realtime      
        WHERE            
            (IBTrader_Realtime.shareId=?)
            AND (IBTrader_Realtime.companyid=?)
            AND (IBTrader_Realtime.groupid=?)
         	AND (IBTrader_Realtime.createDate)=  (SELECT  max(IBTrader_Realtime.createDate) FROM 
					         			IBTrader_Realtime      
					      	  		WHERE            
					           			 (IBTrader_Realtime.shareId=?)
					          		  AND (IBTrader_Realtime.companyid=?)
					          		  AND (IBTrader_Realtime.groupid=?))
            
            
     ]]>

    </sql>
</custom-sql>