create index IX_7C9D33FE on ibtrader_Config (companyId, config_key[$COLUMN_LENGTH:75$]);
create index IX_279C27D8 on ibtrader_Config (config_key[$COLUMN_LENGTH:75$], globaldefault);
create index IX_33B037F4 on ibtrader_Config (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_380E2B76 on ibtrader_Config (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_273F8717 on ibtrader_IBOrder (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_45CE759 on ibtrader_IBOrder (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_46891320 on ibtrader_Market (groupId, companyId, start_hour[$COLUMN_LENGTH:75$], end_hour[$COLUMN_LENGTH:75$], active_);
create index IX_F83507EE on ibtrader_Market (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_2D9AB1F0 on ibtrader_Market (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_B8234567 on ibtrader_Position (companyId, date_in, state_[$COLUMN_LENGTH:75$]);
create index IX_3C85640C on ibtrader_Position (companyId, date_in, state_in[$COLUMN_LENGTH:75$]);
create index IX_F34693BE on ibtrader_Position (companyId, date_out, state_out[$COLUMN_LENGTH:75$]);
create index IX_FD6177DF on ibtrader_Position (companyId, groupId, date_in, state_[$COLUMN_LENGTH:75$]);
create index IX_2B00D884 on ibtrader_Position (companyId, groupId, date_in, state_in[$COLUMN_LENGTH:75$]);
create index IX_30B6CA36 on ibtrader_Position (companyId, groupId, date_out, state_out[$COLUMN_LENGTH:75$]);
create index IX_E1297A9D on ibtrader_Position (groupId, companyId, positionId_tws_out);
create index IX_2F0E62E1 on ibtrader_Position (groupId, companyId, shareId, date_in);
create index IX_1F62F1F7 on ibtrader_Position (groupId, companyId, shareId, date_real_in, date_real_out, date_in, date_out);
create index IX_62F7B2D8 on ibtrader_Position (groupId, companyId, shareId, state_[$COLUMN_LENGTH:75$], date_out);
create index IX_BB6FD65B on ibtrader_Position (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_7C20379D on ibtrader_Position (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_AE70EA89 on ibtrader_Realtime (companyId, shareId, createDate);
create index IX_4CC4359D on ibtrader_Realtime (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_72277F5F on ibtrader_Realtime (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_294BE4E7 on ibtrader_Share (active_, marketId);
create index IX_70261769 on ibtrader_Share (groupId, companyId, active_, marketId);
create index IX_2B607221 on ibtrader_Share (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_DCF10CE3 on ibtrader_Share (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_2BA54552 on ibtrader_Strategy (companyId, active_);
create index IX_A5F719BF on ibtrader_Strategy (groupId, status);
create index IX_95F2591B on ibtrader_Strategy (groupId, strategyID);
create index IX_244BCDD7 on ibtrader_Strategy (status);
create index IX_B6BAD7A5 on ibtrader_Strategy (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_2FEF0367 on ibtrader_Strategy (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_4BDEDA0E on ibtrader_StrategyShare (shareId, groupId, companyId);
create index IX_C838DDEE on ibtrader_StrategyShare (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_B89C07F0 on ibtrader_StrategyShare (uuid_[$COLUMN_LENGTH:75$], groupId);