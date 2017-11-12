create index IX_7C9D33FE on ibtrader_Config (companyId, config_key[$COLUMN_LENGTH:75$]);
create index IX_279C27D8 on ibtrader_Config (config_key[$COLUMN_LENGTH:75$], globaldefault);
create index IX_33B037F4 on ibtrader_Config (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_380E2B76 on ibtrader_Config (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_273F8717 on ibtrader_IBOrder (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_45CE759 on ibtrader_IBOrder (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_D43E7B1E on ibtrader_Market (start_hour[$COLUMN_LENGTH:75$], end_hour[$COLUMN_LENGTH:75$], active_);
create index IX_F83507EE on ibtrader_Market (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_2D9AB1F0 on ibtrader_Market (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_5C9B7DDF on ibtrader_Position (positionId_tws_out);
create index IX_BB6FD65B on ibtrader_Position (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_7C20379D on ibtrader_Position (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_AB0F841 on ibtrader_Realtime (shareId);
create index IX_4CC4359D on ibtrader_Realtime (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_72277F5F on ibtrader_Realtime (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_294BE4E7 on ibtrader_Share (active_, marketId);
create index IX_2B607221 on ibtrader_Share (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_DCF10CE3 on ibtrader_Share (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_2BA54552 on ibtrader_Strategy (companyId, active_);
create index IX_A5F719BF on ibtrader_Strategy (groupId, status);
create index IX_95F2591B on ibtrader_Strategy (groupId, strategyID);
create index IX_244BCDD7 on ibtrader_Strategy (status);
create index IX_B6BAD7A5 on ibtrader_Strategy (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_2FEF0367 on ibtrader_Strategy (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_C838DDEE on ibtrader_StrategyShare (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_B89C07F0 on ibtrader_StrategyShare (uuid_[$COLUMN_LENGTH:75$], groupId);