create table ibtrader_AuditIndicatorsStrategy (
	uuid_ VARCHAR(75) null,
	groupId LONG not null,
	companyId LONG not null,
	auditDate VARCHAR(75) not null,
	auditstrategy VARCHAR(75) not null,
	shareId LONG not null,
	auditData TEXT null,
	primary key (groupId, companyId, auditDate, auditstrategy, shareId)
);

create table ibtrader_BackTesting (
	uuid_ VARCHAR(75) null,
	backTId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	fromDate DATE null,
	toDate DATE null,
	lastRunDate DATE null,
	shareId LONG,
	countordersBUY LONG,
	countordersSELL LONG,
	profitordersBUY DOUBLE,
	profitordersSELL DOUBLE,
	status VARCHAR(75) null,
	description TEXT null,
	startDate DATE null,
	endDate DATE null
);

create table ibtrader_Config (
	uuid_ VARCHAR(75) null,
	configId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	name VARCHAR(75) null,
	value VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	globaldefault BOOLEAN,
	iscron BOOLEAN,
	config_key VARCHAR(75) null,
	description TEXT null
);

create table ibtrader_HistoricalRealtime (
	uuid_ VARCHAR(75) null,
	realtimeId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	shareId LONG,
	value DOUBLE,
	createDate DATE null,
	modifiedDate DATE null,
	max_value DOUBLE,
	min_value DOUBLE,
	volume INTEGER,
	avg_volume INTEGER,
	closeprice BOOLEAN
);

create table ibtrader_IBOrder (
	uuid_ VARCHAR(75) null,
	orderIdPk LONG not null primary key IDENTITY,
	ordersId LONG,
	groupId LONG,
	companyId LONG,
	shareID LONG,
	checked BOOLEAN,
	createDate DATE null,
	modifiedDate DATE null,
	ibclientId LONG,
	removable_on_reboot BOOLEAN
);

create table ibtrader_Indicators (
	uuid_ VARCHAR(75) null,
	groupId LONG not null,
	companyId LONG not null,
	createDate DATE not null,
	name VARCHAR(75) not null,
	value DOUBLE not null,
	shareId LONG,
	primary key (groupId, companyId, createDate, name, value)
);

create table ibtrader_Market (
	uuid_ VARCHAR(75) null,
	marketId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	active_ BOOLEAN,
	createDate DATE null,
	modifiedDate DATE null,
	start_hour VARCHAR(75) null,
	end_hour VARCHAR(75) null,
	identifier VARCHAR(75) null,
	currency_ VARCHAR(75) null,
	name VARCHAR(75) null,
	description TEXT null
);

create table ibtrader_Position (
	uuid_ VARCHAR(75) null,
	positionId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	shareId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	state_ VARCHAR(75) null,
	state_in VARCHAR(75) null,
	state_out VARCHAR(75) null,
	description TEXT null,
	price_in DOUBLE,
	price_real_in DOUBLE,
	limit_price_in DOUBLE,
	date_in DATE null,
	date_real_in DATE null,
	positionId_tws_in LONG,
	positionId_tws_out LONG,
	type_ VARCHAR(75) null,
	price_out DOUBLE,
	price_real_out DOUBLE,
	limit_price_out DOUBLE,
	date_out DATE null,
	date_real_out DATE null,
	share_number LONG,
	clientId_in LONG,
	clientId_out LONG,
	strategy_in VARCHAR(75) null,
	strategy_out VARCHAR(75) null,
	percentualstoplost_out DOUBLE,
	pricestoplost_out DOUBLE,
	percentualstopprofit_out DOUBLE,
	pricestopprofit_out DOUBLE,
	percentual_trailling_stop_lost DOUBLE,
	pricetrailling_stop_lost DOUBLE,
	pendingcancelled LONG,
	position_mode VARCHAR(75) null,
	totalcommision DOUBLE,
	forceclose BOOLEAN,
	backtestingId LONG
);

create table ibtrader_Realtime (
	uuid_ VARCHAR(75) null,
	realtimeId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	shareId LONG,
	value DOUBLE,
	createDate DATE null,
	modifiedDate DATE null,
	max_value DOUBLE,
	min_value DOUBLE,
	volume INTEGER,
	avg_volume INTEGER,
	closeprice BOOLEAN
);

create table ibtrader_Share (
	uuid_ VARCHAR(75) null,
	shareId LONG not null primary key,
	name VARCHAR(75) null,
	symbol VARCHAR(75) null,
	groupId LONG,
	companyId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	active_ BOOLEAN,
	numbertopurchase LONG,
	percentual_limit_buy DOUBLE,
	percentual_stop_lost DOUBLE,
	percentual_stop_profit DOUBLE,
	percentual_stop_profit_position DOUBLE,
	percentual_trailling_stop_lost DOUBLE,
	expiry_date DATE null,
	expiry_expression TEXT null,
	tick_futures DOUBLE,
	multiplier DOUBLE,
	security_type VARCHAR(75) null,
	exchange VARCHAR(75) null,
	primary_exchange VARCHAR(75) null,
	userCreatedId LONG,
	marketId LONG,
	validated_trader_provider BOOLEAN,
	date_validated_trader_provider DATE null,
	last_error_trader_provider TEXT null,
	simulation_end_date DATE null,
	trading_hours TEXT null,
	date_filled_realtime_gaps DATE null
);

create table ibtrader_Strategy (
	uuid_ VARCHAR(75) null,
	strategyID LONG not null primary key,
	groupId LONG,
	companyId LONG,
	name VARCHAR(75) null,
	description TEXT null,
	createDate DATE null,
	modifiedDate DATE null,
	active_ BOOLEAN,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null,
	type_ VARCHAR(75) null,
	can_override_params BOOLEAN,
	className VARCHAR(75) null,
	userId LONG,
	visible BOOLEAN
);

create table ibtrader_StrategyShare (
	uuid_ VARCHAR(75) null,
	strategyshareId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	strategyId LONG,
	shareId LONG,
	active_ BOOLEAN,
	visible BOOLEAN,
	strategyparamsoverride TEXT null,
	description TEXT null
);