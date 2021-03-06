CREATE DEFINER=`root`@`localhost` EVENT `REALTIME_MAINTANCE`
	ON SCHEDULE
		EVERY 4 HOUR STARTS '2018-05-01 10:59:31'
	ON COMPLETION NOT PRESERVE
	ENABLE
	COMMENT 'DEJAMOS x DIAS DE REALTIME SOLO, EL RESTO SE BORRA. OJO, MIRAMOS'
	DO BEGIN

DECLARE NUM_DAYS_PAST_REALTIME int;
DECLARE DATE_PAST DATE;
SET NUM_DAYS_PAST_REALTIME=-1;
/* PONEMOS UN NUMERO MUY ALTO ANTERIOR PARA INICIALIZARLA  */
SET DATE_PAST = DATE_SUB(now(),INTERVAL 40000 DAY);
SELECT 
	value  INTO NUM_DAYS_PAST_REALTIME
FROM 
	ibtrader_config
WHERE 
	config_key = 'NUM_DAYS_PAST_REALTIME'; 

IF (NUM_DAYS_PAST_REALTIME<>-1) THEN
	
	
	DELETE FROM ibtrader_realtime 
				WHERE date(createDate) <=
	(
	select max(fecha) from
	( 	
  			   select distinct date(createDate) FECHA  from ibtrader_realtime 
				where date(createdate)< date(DATE_ADD(now(), INTERVAL -NUM_DAYS_PAST_REALTIME DAY))  
				order by createDate DESC	
	) t
	);
	 
END IF;



END