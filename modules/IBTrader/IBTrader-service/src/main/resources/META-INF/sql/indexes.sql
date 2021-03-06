create index IX_C19C77D4 on ibtrader_AuditIndicatorsStrategy (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_49C8E356 on ibtrader_AuditIndicatorsStrategy (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_A4DA9DB5 on ibtrader_BackTesting (shareId, companyId, groupId);
create index IX_48457CB on ibtrader_BackTesting (status[$COLUMN_LENGTH:75$], companyId, groupId, shareId);
create index IX_3325966B on ibtrader_BackTesting (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_AB66BBAD on ibtrader_BackTesting (uuid_[$COLUMN_LENGTH:75$], groupId);

create unique index IX_A132C258 on ibtrader_Config (companyId, groupId, config_key[$COLUMN_LENGTH:75$]);
create index IX_279C27D8 on ibtrader_Config (config_key[$COLUMN_LENGTH:75$], globaldefault);
create index IX_91075835 on ibtrader_Config (iscron, value[$COLUMN_LENGTH:75$]);
create index IX_33B037F4 on ibtrader_Config (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_380E2B76 on ibtrader_Config (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_4F02CB1C on ibtrader_HistoricalRealtime (companyId, shareId, createDate, groupId, closeprice);
create index IX_4D317491 on ibtrader_HistoricalRealtime (createDate);
create index IX_4110BD87 on ibtrader_HistoricalRealtime (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_10E8F9C9 on ibtrader_HistoricalRealtime (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_F327818C on ibtrader_IBOrder (ordersId, companyId, groupId, ibclientId);
create index IX_BEE53524 on ibtrader_IBOrder (ordersId, companyId, shareID, groupId, ibclientId);
create index IX_7227788A on ibtrader_IBOrder (ordersId, ibclientId);
create index IX_5CF57ACE on ibtrader_IBOrder (removable_on_reboot, companyId, groupId, createDate);
create index IX_C752A169 on ibtrader_IBOrder (shareID, companyId, groupId);
create index IX_5EA94F5 on ibtrader_IBOrder (shareID, companyId, ordersId);
create index IX_273F8717 on ibtrader_IBOrder (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_45CE759 on ibtrader_IBOrder (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_966EA7F6 on ibtrader_Indicators (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_B04CB3F8 on ibtrader_Indicators (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_87CDBD8D on ibtrader_Market (companyId, groupId, active_);
create index IX_1A1ACBD9 on ibtrader_Market (companyId, groupId, identifier[$COLUMN_LENGTH:75$]);
create index IX_7EDE2FBB on ibtrader_Market (companyId, groupId, name[$COLUMN_LENGTH:75$]);
create index IX_46891320 on ibtrader_Market (groupId, companyId, start_hour[$COLUMN_LENGTH:75$], end_hour[$COLUMN_LENGTH:75$], active_);
create index IX_F83507EE on ibtrader_Market (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_2D9AB1F0 on ibtrader_Market (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_FD6177DF on ibtrader_Position (companyId, groupId, date_in, state_[$COLUMN_LENGTH:75$]);
create index IX_2B00D884 on ibtrader_Position (companyId, groupId, date_in, state_in[$COLUMN_LENGTH:75$]);
create index IX_30B6CA36 on ibtrader_Position (companyId, groupId, date_out, state_out[$COLUMN_LENGTH:75$]);
create index IX_A0D0F116 on ibtrader_Position (companyId, groupId, pendingcancelled, shareId, position_mode[$COLUMN_LENGTH:75$]);
create index IX_AB2D63EA on ibtrader_Position (companyId, groupId, position_mode[$COLUMN_LENGTH:75$], shareId);
create index IX_6FFA0B0E on ibtrader_Position (companyId, groupId, shareId, state_[$COLUMN_LENGTH:75$], position_mode[$COLUMN_LENGTH:75$], backtestingId);
create index IX_D3FAB215 on ibtrader_Position (groupId, companyId, positionId_tws_in, clientId_in, position_mode[$COLUMN_LENGTH:75$]);
create index IX_5CB4C243 on ibtrader_Position (groupId, companyId, positionId_tws_out, clientId_out, position_mode[$COLUMN_LENGTH:75$]);
create index IX_2F0E62E1 on ibtrader_Position (groupId, companyId, shareId, date_in);
create index IX_B316CC76 on ibtrader_Position (groupId, companyId, shareId, date_out);
create index IX_1F62F1F7 on ibtrader_Position (groupId, companyId, shareId, date_real_in, date_real_out, date_in, date_out);
create index IX_89A86F04 on ibtrader_Position (groupId, companyId, shareId, position_mode[$COLUMN_LENGTH:75$]);
create index IX_62F7B2D8 on ibtrader_Position (groupId, companyId, shareId, state_[$COLUMN_LENGTH:75$], date_out);
create index IX_51DC01E3 on ibtrader_Position (groupId, companyId, shareId, state_[$COLUMN_LENGTH:75$], date_real_out, date_out, position_mode[$COLUMN_LENGTH:75$], backtestingId);
create index IX_BB6FD65B on ibtrader_Position (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_7C20379D on ibtrader_Position (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_6E4B2B46 on ibtrader_Realtime (companyId, shareId, createDate, groupId, closeprice);
create index IX_228E3527 on ibtrader_Realtime (createDate);
create index IX_4CC4359D on ibtrader_Realtime (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_72277F5F on ibtrader_Realtime (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_CA9D3D19 on ibtrader_Share (companyId, groupId, name[$COLUMN_LENGTH:75$], marketId);
create index IX_E81EB47B on ibtrader_Share (companyId, groupId, shareId);
create index IX_12016A7B on ibtrader_Share (companyId, groupId, symbol[$COLUMN_LENGTH:75$]);
create index IX_70261769 on ibtrader_Share (groupId, companyId, active_, marketId);
create index IX_8788D816 on ibtrader_Share (groupId, companyId, marketId);
create index IX_2B607221 on ibtrader_Share (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_DCF10CE3 on ibtrader_Share (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_2BA54552 on ibtrader_Strategy (companyId, active_);
create index IX_F01A050E on ibtrader_Strategy (companyId, className[$COLUMN_LENGTH:75$], groupId);
create index IX_A5F719BF on ibtrader_Strategy (groupId, status);
create index IX_95F2591B on ibtrader_Strategy (groupId, strategyID);
create index IX_244BCDD7 on ibtrader_Strategy (status);
create index IX_B6BAD7A5 on ibtrader_Strategy (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_2FEF0367 on ibtrader_Strategy (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_C28E62F7 on ibtrader_StrategyShare (shareId, active_, groupId, companyId);
create index IX_4BDEDA0E on ibtrader_StrategyShare (shareId, groupId, companyId);
create index IX_AE04E7F0 on ibtrader_StrategyShare (shareId, strategyId, groupId, companyId);
create index IX_C838DDEE on ibtrader_StrategyShare (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_B89C07F0 on ibtrader_StrategyShare (uuid_[$COLUMN_LENGTH:75$], groupId);