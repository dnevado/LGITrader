/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.ibtrader.data.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.ibtrader.data.exception.NoSuchMarketException;
import com.ibtrader.data.model.Market;
import com.ibtrader.data.model.impl.MarketImpl;
import com.ibtrader.data.model.impl.MarketModelImpl;
import com.ibtrader.data.service.persistence.MarketPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the market service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MarketPersistence
 * @see com.ibtrader.data.service.persistence.MarketUtil
 * @generated
 */
@ProviderType
public class MarketPersistenceImpl extends BasePersistenceImpl<Market>
	implements MarketPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link MarketUtil} to access the market persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = MarketImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MarketModelImpl.ENTITY_CACHE_ENABLED,
			MarketModelImpl.FINDER_CACHE_ENABLED, MarketImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MarketModelImpl.ENTITY_CACHE_ENABLED,
			MarketModelImpl.FINDER_CACHE_ENABLED, MarketImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MarketModelImpl.ENTITY_CACHE_ENABLED,
			MarketModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(MarketModelImpl.ENTITY_CACHE_ENABLED,
			MarketModelImpl.FINDER_CACHE_ENABLED, MarketImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(MarketModelImpl.ENTITY_CACHE_ENABLED,
			MarketModelImpl.FINDER_CACHE_ENABLED, MarketImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			MarketModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(MarketModelImpl.ENTITY_CACHE_ENABLED,
			MarketModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the markets where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching markets
	 */
	@Override
	public List<Market> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the markets where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarketModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of markets
	 * @param end the upper bound of the range of markets (not inclusive)
	 * @return the range of matching markets
	 */
	@Override
	public List<Market> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the markets where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarketModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of markets
	 * @param end the upper bound of the range of markets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching markets
	 */
	@Override
	public List<Market> findByUuid(String uuid, int start, int end,
		OrderByComparator<Market> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the markets where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarketModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of markets
	 * @param end the upper bound of the range of markets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching markets
	 */
	@Override
	public List<Market> findByUuid(String uuid, int start, int end,
		OrderByComparator<Market> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid, start, end, orderByComparator };
		}

		List<Market> list = null;

		if (retrieveFromCache) {
			list = (List<Market>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Market market : list) {
					if (!Objects.equals(uuid, market.getUuid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_MARKET_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(MarketModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				if (!pagination) {
					list = (List<Market>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Market>)QueryUtil.list(q, getDialect(), start,
							end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first market in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching market
	 * @throws NoSuchMarketException if a matching market could not be found
	 */
	@Override
	public Market findByUuid_First(String uuid,
		OrderByComparator<Market> orderByComparator)
		throws NoSuchMarketException {
		Market market = fetchByUuid_First(uuid, orderByComparator);

		if (market != null) {
			return market;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMarketException(msg.toString());
	}

	/**
	 * Returns the first market in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching market, or <code>null</code> if a matching market could not be found
	 */
	@Override
	public Market fetchByUuid_First(String uuid,
		OrderByComparator<Market> orderByComparator) {
		List<Market> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last market in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching market
	 * @throws NoSuchMarketException if a matching market could not be found
	 */
	@Override
	public Market findByUuid_Last(String uuid,
		OrderByComparator<Market> orderByComparator)
		throws NoSuchMarketException {
		Market market = fetchByUuid_Last(uuid, orderByComparator);

		if (market != null) {
			return market;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMarketException(msg.toString());
	}

	/**
	 * Returns the last market in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching market, or <code>null</code> if a matching market could not be found
	 */
	@Override
	public Market fetchByUuid_Last(String uuid,
		OrderByComparator<Market> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Market> list = findByUuid(uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the markets before and after the current market in the ordered set where uuid = &#63;.
	 *
	 * @param marketId the primary key of the current market
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next market
	 * @throws NoSuchMarketException if a market with the primary key could not be found
	 */
	@Override
	public Market[] findByUuid_PrevAndNext(long marketId, String uuid,
		OrderByComparator<Market> orderByComparator)
		throws NoSuchMarketException {
		Market market = findByPrimaryKey(marketId);

		Session session = null;

		try {
			session = openSession();

			Market[] array = new MarketImpl[3];

			array[0] = getByUuid_PrevAndNext(session, market, uuid,
					orderByComparator, true);

			array[1] = market;

			array[2] = getByUuid_PrevAndNext(session, market, uuid,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Market getByUuid_PrevAndNext(Session session, Market market,
		String uuid, OrderByComparator<Market> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MARKET_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(MarketModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(market);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Market> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the markets where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (Market market : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(market);
		}
	}

	/**
	 * Returns the number of markets where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching markets
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MARKET_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "market.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "market.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(market.uuid IS NULL OR market.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(MarketModelImpl.ENTITY_CACHE_ENABLED,
			MarketModelImpl.FINDER_CACHE_ENABLED, MarketImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			MarketModelImpl.UUID_COLUMN_BITMASK |
			MarketModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(MarketModelImpl.ENTITY_CACHE_ENABLED,
			MarketModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the market where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchMarketException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching market
	 * @throws NoSuchMarketException if a matching market could not be found
	 */
	@Override
	public Market findByUUID_G(String uuid, long groupId)
		throws NoSuchMarketException {
		Market market = fetchByUUID_G(uuid, groupId);

		if (market == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchMarketException(msg.toString());
		}

		return market;
	}

	/**
	 * Returns the market where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching market, or <code>null</code> if a matching market could not be found
	 */
	@Override
	public Market fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the market where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching market, or <code>null</code> if a matching market could not be found
	 */
	@Override
	public Market fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof Market) {
			Market market = (Market)result;

			if (!Objects.equals(uuid, market.getUuid()) ||
					(groupId != market.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_MARKET_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				List<Market> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					Market market = list.get(0);

					result = market;

					cacheResult(market);

					if ((market.getUuid() == null) ||
							!market.getUuid().equals(uuid) ||
							(market.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, market);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (Market)result;
		}
	}

	/**
	 * Removes the market where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the market that was removed
	 */
	@Override
	public Market removeByUUID_G(String uuid, long groupId)
		throws NoSuchMarketException {
		Market market = findByUUID_G(uuid, groupId);

		return remove(market);
	}

	/**
	 * Returns the number of markets where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching markets
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_MARKET_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "market.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "market.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(market.uuid IS NULL OR market.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "market.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(MarketModelImpl.ENTITY_CACHE_ENABLED,
			MarketModelImpl.FINDER_CACHE_ENABLED, MarketImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(MarketModelImpl.ENTITY_CACHE_ENABLED,
			MarketModelImpl.FINDER_CACHE_ENABLED, MarketImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			MarketModelImpl.UUID_COLUMN_BITMASK |
			MarketModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(MarketModelImpl.ENTITY_CACHE_ENABLED,
			MarketModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the markets where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching markets
	 */
	@Override
	public List<Market> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the markets where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarketModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of markets
	 * @param end the upper bound of the range of markets (not inclusive)
	 * @return the range of matching markets
	 */
	@Override
	public List<Market> findByUuid_C(String uuid, long companyId, int start,
		int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the markets where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarketModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of markets
	 * @param end the upper bound of the range of markets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching markets
	 */
	@Override
	public List<Market> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<Market> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the markets where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarketModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of markets
	 * @param end the upper bound of the range of markets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching markets
	 */
	@Override
	public List<Market> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<Market> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] { uuid, companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] {
					uuid, companyId,
					
					start, end, orderByComparator
				};
		}

		List<Market> list = null;

		if (retrieveFromCache) {
			list = (List<Market>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Market market : list) {
					if (!Objects.equals(uuid, market.getUuid()) ||
							(companyId != market.getCompanyId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_MARKET_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(MarketModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				if (!pagination) {
					list = (List<Market>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Market>)QueryUtil.list(q, getDialect(), start,
							end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first market in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching market
	 * @throws NoSuchMarketException if a matching market could not be found
	 */
	@Override
	public Market findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Market> orderByComparator)
		throws NoSuchMarketException {
		Market market = fetchByUuid_C_First(uuid, companyId, orderByComparator);

		if (market != null) {
			return market;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMarketException(msg.toString());
	}

	/**
	 * Returns the first market in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching market, or <code>null</code> if a matching market could not be found
	 */
	@Override
	public Market fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Market> orderByComparator) {
		List<Market> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last market in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching market
	 * @throws NoSuchMarketException if a matching market could not be found
	 */
	@Override
	public Market findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Market> orderByComparator)
		throws NoSuchMarketException {
		Market market = fetchByUuid_C_Last(uuid, companyId, orderByComparator);

		if (market != null) {
			return market;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMarketException(msg.toString());
	}

	/**
	 * Returns the last market in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching market, or <code>null</code> if a matching market could not be found
	 */
	@Override
	public Market fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Market> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<Market> list = findByUuid_C(uuid, companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the markets before and after the current market in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param marketId the primary key of the current market
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next market
	 * @throws NoSuchMarketException if a market with the primary key could not be found
	 */
	@Override
	public Market[] findByUuid_C_PrevAndNext(long marketId, String uuid,
		long companyId, OrderByComparator<Market> orderByComparator)
		throws NoSuchMarketException {
		Market market = findByPrimaryKey(marketId);

		Session session = null;

		try {
			session = openSession();

			Market[] array = new MarketImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, market, uuid,
					companyId, orderByComparator, true);

			array[1] = market;

			array[2] = getByUuid_C_PrevAndNext(session, market, uuid,
					companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Market getByUuid_C_PrevAndNext(Session session, Market market,
		String uuid, long companyId,
		OrderByComparator<Market> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_MARKET_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_1);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(MarketModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(market);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Market> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the markets where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (Market market : findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(market);
		}
	}

	/**
	 * Returns the number of markets where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching markets
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_MARKET_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "market.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "market.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(market.uuid IS NULL OR market.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "market.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVESTARTENDHOUR =
		new FinderPath(MarketModelImpl.ENTITY_CACHE_ENABLED,
			MarketModelImpl.FINDER_CACHE_ENABLED, MarketImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByActiveStartEndHour",
			new String[] {
				String.class.getName(), String.class.getName(),
				Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVESTARTENDHOUR =
		new FinderPath(MarketModelImpl.ENTITY_CACHE_ENABLED,
			MarketModelImpl.FINDER_CACHE_ENABLED, MarketImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByActiveStartEndHour",
			new String[] {
				String.class.getName(), String.class.getName(),
				Boolean.class.getName()
			},
			MarketModelImpl.START_HOUR_COLUMN_BITMASK |
			MarketModelImpl.END_HOUR_COLUMN_BITMASK |
			MarketModelImpl.ACTIVE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ACTIVESTARTENDHOUR = new FinderPath(MarketModelImpl.ENTITY_CACHE_ENABLED,
			MarketModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByActiveStartEndHour",
			new String[] {
				String.class.getName(), String.class.getName(),
				Boolean.class.getName()
			});

	/**
	 * Returns all the markets where start_hour = &#63; and end_hour = &#63; and active = &#63;.
	 *
	 * @param start_hour the start_hour
	 * @param end_hour the end_hour
	 * @param active the active
	 * @return the matching markets
	 */
	@Override
	public List<Market> findByActiveStartEndHour(String start_hour,
		String end_hour, boolean active) {
		return findByActiveStartEndHour(start_hour, end_hour, active,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the markets where start_hour = &#63; and end_hour = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarketModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start_hour the start_hour
	 * @param end_hour the end_hour
	 * @param active the active
	 * @param start the lower bound of the range of markets
	 * @param end the upper bound of the range of markets (not inclusive)
	 * @return the range of matching markets
	 */
	@Override
	public List<Market> findByActiveStartEndHour(String start_hour,
		String end_hour, boolean active, int start, int end) {
		return findByActiveStartEndHour(start_hour, end_hour, active, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the markets where start_hour = &#63; and end_hour = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarketModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start_hour the start_hour
	 * @param end_hour the end_hour
	 * @param active the active
	 * @param start the lower bound of the range of markets
	 * @param end the upper bound of the range of markets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching markets
	 */
	@Override
	public List<Market> findByActiveStartEndHour(String start_hour,
		String end_hour, boolean active, int start, int end,
		OrderByComparator<Market> orderByComparator) {
		return findByActiveStartEndHour(start_hour, end_hour, active, start,
			end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the markets where start_hour = &#63; and end_hour = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarketModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start_hour the start_hour
	 * @param end_hour the end_hour
	 * @param active the active
	 * @param start the lower bound of the range of markets
	 * @param end the upper bound of the range of markets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching markets
	 */
	@Override
	public List<Market> findByActiveStartEndHour(String start_hour,
		String end_hour, boolean active, int start, int end,
		OrderByComparator<Market> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVESTARTENDHOUR;
			finderArgs = new Object[] { start_hour, end_hour, active };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVESTARTENDHOUR;
			finderArgs = new Object[] {
					start_hour, end_hour, active,
					
					start, end, orderByComparator
				};
		}

		List<Market> list = null;

		if (retrieveFromCache) {
			list = (List<Market>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Market market : list) {
					if (!Objects.equals(start_hour, market.getStart_hour()) ||
							!Objects.equals(end_hour, market.getEnd_hour()) ||
							(active != market.getActive())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_MARKET_WHERE);

			boolean bindStart_hour = false;

			if (start_hour == null) {
				query.append(_FINDER_COLUMN_ACTIVESTARTENDHOUR_START_HOUR_1);
			}
			else if (start_hour.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ACTIVESTARTENDHOUR_START_HOUR_3);
			}
			else {
				bindStart_hour = true;

				query.append(_FINDER_COLUMN_ACTIVESTARTENDHOUR_START_HOUR_2);
			}

			boolean bindEnd_hour = false;

			if (end_hour == null) {
				query.append(_FINDER_COLUMN_ACTIVESTARTENDHOUR_END_HOUR_1);
			}
			else if (end_hour.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ACTIVESTARTENDHOUR_END_HOUR_3);
			}
			else {
				bindEnd_hour = true;

				query.append(_FINDER_COLUMN_ACTIVESTARTENDHOUR_END_HOUR_2);
			}

			query.append(_FINDER_COLUMN_ACTIVESTARTENDHOUR_ACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(MarketModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindStart_hour) {
					qPos.add(start_hour);
				}

				if (bindEnd_hour) {
					qPos.add(end_hour);
				}

				qPos.add(active);

				if (!pagination) {
					list = (List<Market>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Market>)QueryUtil.list(q, getDialect(), start,
							end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first market in the ordered set where start_hour = &#63; and end_hour = &#63; and active = &#63;.
	 *
	 * @param start_hour the start_hour
	 * @param end_hour the end_hour
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching market
	 * @throws NoSuchMarketException if a matching market could not be found
	 */
	@Override
	public Market findByActiveStartEndHour_First(String start_hour,
		String end_hour, boolean active,
		OrderByComparator<Market> orderByComparator)
		throws NoSuchMarketException {
		Market market = fetchByActiveStartEndHour_First(start_hour, end_hour,
				active, orderByComparator);

		if (market != null) {
			return market;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("start_hour=");
		msg.append(start_hour);

		msg.append(", end_hour=");
		msg.append(end_hour);

		msg.append(", active=");
		msg.append(active);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMarketException(msg.toString());
	}

	/**
	 * Returns the first market in the ordered set where start_hour = &#63; and end_hour = &#63; and active = &#63;.
	 *
	 * @param start_hour the start_hour
	 * @param end_hour the end_hour
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching market, or <code>null</code> if a matching market could not be found
	 */
	@Override
	public Market fetchByActiveStartEndHour_First(String start_hour,
		String end_hour, boolean active,
		OrderByComparator<Market> orderByComparator) {
		List<Market> list = findByActiveStartEndHour(start_hour, end_hour,
				active, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last market in the ordered set where start_hour = &#63; and end_hour = &#63; and active = &#63;.
	 *
	 * @param start_hour the start_hour
	 * @param end_hour the end_hour
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching market
	 * @throws NoSuchMarketException if a matching market could not be found
	 */
	@Override
	public Market findByActiveStartEndHour_Last(String start_hour,
		String end_hour, boolean active,
		OrderByComparator<Market> orderByComparator)
		throws NoSuchMarketException {
		Market market = fetchByActiveStartEndHour_Last(start_hour, end_hour,
				active, orderByComparator);

		if (market != null) {
			return market;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("start_hour=");
		msg.append(start_hour);

		msg.append(", end_hour=");
		msg.append(end_hour);

		msg.append(", active=");
		msg.append(active);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMarketException(msg.toString());
	}

	/**
	 * Returns the last market in the ordered set where start_hour = &#63; and end_hour = &#63; and active = &#63;.
	 *
	 * @param start_hour the start_hour
	 * @param end_hour the end_hour
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching market, or <code>null</code> if a matching market could not be found
	 */
	@Override
	public Market fetchByActiveStartEndHour_Last(String start_hour,
		String end_hour, boolean active,
		OrderByComparator<Market> orderByComparator) {
		int count = countByActiveStartEndHour(start_hour, end_hour, active);

		if (count == 0) {
			return null;
		}

		List<Market> list = findByActiveStartEndHour(start_hour, end_hour,
				active, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the markets before and after the current market in the ordered set where start_hour = &#63; and end_hour = &#63; and active = &#63;.
	 *
	 * @param marketId the primary key of the current market
	 * @param start_hour the start_hour
	 * @param end_hour the end_hour
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next market
	 * @throws NoSuchMarketException if a market with the primary key could not be found
	 */
	@Override
	public Market[] findByActiveStartEndHour_PrevAndNext(long marketId,
		String start_hour, String end_hour, boolean active,
		OrderByComparator<Market> orderByComparator)
		throws NoSuchMarketException {
		Market market = findByPrimaryKey(marketId);

		Session session = null;

		try {
			session = openSession();

			Market[] array = new MarketImpl[3];

			array[0] = getByActiveStartEndHour_PrevAndNext(session, market,
					start_hour, end_hour, active, orderByComparator, true);

			array[1] = market;

			array[2] = getByActiveStartEndHour_PrevAndNext(session, market,
					start_hour, end_hour, active, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Market getByActiveStartEndHour_PrevAndNext(Session session,
		Market market, String start_hour, String end_hour, boolean active,
		OrderByComparator<Market> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_MARKET_WHERE);

		boolean bindStart_hour = false;

		if (start_hour == null) {
			query.append(_FINDER_COLUMN_ACTIVESTARTENDHOUR_START_HOUR_1);
		}
		else if (start_hour.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_ACTIVESTARTENDHOUR_START_HOUR_3);
		}
		else {
			bindStart_hour = true;

			query.append(_FINDER_COLUMN_ACTIVESTARTENDHOUR_START_HOUR_2);
		}

		boolean bindEnd_hour = false;

		if (end_hour == null) {
			query.append(_FINDER_COLUMN_ACTIVESTARTENDHOUR_END_HOUR_1);
		}
		else if (end_hour.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_ACTIVESTARTENDHOUR_END_HOUR_3);
		}
		else {
			bindEnd_hour = true;

			query.append(_FINDER_COLUMN_ACTIVESTARTENDHOUR_END_HOUR_2);
		}

		query.append(_FINDER_COLUMN_ACTIVESTARTENDHOUR_ACTIVE_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(MarketModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindStart_hour) {
			qPos.add(start_hour);
		}

		if (bindEnd_hour) {
			qPos.add(end_hour);
		}

		qPos.add(active);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(market);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Market> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the markets where start_hour = &#63; and end_hour = &#63; and active = &#63; from the database.
	 *
	 * @param start_hour the start_hour
	 * @param end_hour the end_hour
	 * @param active the active
	 */
	@Override
	public void removeByActiveStartEndHour(String start_hour, String end_hour,
		boolean active) {
		for (Market market : findByActiveStartEndHour(start_hour, end_hour,
				active, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(market);
		}
	}

	/**
	 * Returns the number of markets where start_hour = &#63; and end_hour = &#63; and active = &#63;.
	 *
	 * @param start_hour the start_hour
	 * @param end_hour the end_hour
	 * @param active the active
	 * @return the number of matching markets
	 */
	@Override
	public int countByActiveStartEndHour(String start_hour, String end_hour,
		boolean active) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ACTIVESTARTENDHOUR;

		Object[] finderArgs = new Object[] { start_hour, end_hour, active };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_MARKET_WHERE);

			boolean bindStart_hour = false;

			if (start_hour == null) {
				query.append(_FINDER_COLUMN_ACTIVESTARTENDHOUR_START_HOUR_1);
			}
			else if (start_hour.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ACTIVESTARTENDHOUR_START_HOUR_3);
			}
			else {
				bindStart_hour = true;

				query.append(_FINDER_COLUMN_ACTIVESTARTENDHOUR_START_HOUR_2);
			}

			boolean bindEnd_hour = false;

			if (end_hour == null) {
				query.append(_FINDER_COLUMN_ACTIVESTARTENDHOUR_END_HOUR_1);
			}
			else if (end_hour.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ACTIVESTARTENDHOUR_END_HOUR_3);
			}
			else {
				bindEnd_hour = true;

				query.append(_FINDER_COLUMN_ACTIVESTARTENDHOUR_END_HOUR_2);
			}

			query.append(_FINDER_COLUMN_ACTIVESTARTENDHOUR_ACTIVE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindStart_hour) {
					qPos.add(start_hour);
				}

				if (bindEnd_hour) {
					qPos.add(end_hour);
				}

				qPos.add(active);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_ACTIVESTARTENDHOUR_START_HOUR_1 = "market.start_hour IS NULL AND ";
	private static final String _FINDER_COLUMN_ACTIVESTARTENDHOUR_START_HOUR_2 = "market.start_hour = ? AND ";
	private static final String _FINDER_COLUMN_ACTIVESTARTENDHOUR_START_HOUR_3 = "(market.start_hour IS NULL OR market.start_hour = '') AND ";
	private static final String _FINDER_COLUMN_ACTIVESTARTENDHOUR_END_HOUR_1 = "market.end_hour IS NULL AND ";
	private static final String _FINDER_COLUMN_ACTIVESTARTENDHOUR_END_HOUR_2 = "market.end_hour = ? AND ";
	private static final String _FINDER_COLUMN_ACTIVESTARTENDHOUR_END_HOUR_3 = "(market.end_hour IS NULL OR market.end_hour = '') AND ";
	private static final String _FINDER_COLUMN_ACTIVESTARTENDHOUR_ACTIVE_2 = "market.active = ?";

	public MarketPersistenceImpl() {
		setModelClass(Market.class);
	}

	/**
	 * Caches the market in the entity cache if it is enabled.
	 *
	 * @param market the market
	 */
	@Override
	public void cacheResult(Market market) {
		entityCache.putResult(MarketModelImpl.ENTITY_CACHE_ENABLED,
			MarketImpl.class, market.getPrimaryKey(), market);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { market.getUuid(), market.getGroupId() }, market);

		market.resetOriginalValues();
	}

	/**
	 * Caches the markets in the entity cache if it is enabled.
	 *
	 * @param markets the markets
	 */
	@Override
	public void cacheResult(List<Market> markets) {
		for (Market market : markets) {
			if (entityCache.getResult(MarketModelImpl.ENTITY_CACHE_ENABLED,
						MarketImpl.class, market.getPrimaryKey()) == null) {
				cacheResult(market);
			}
			else {
				market.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all markets.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(MarketImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the market.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Market market) {
		entityCache.removeResult(MarketModelImpl.ENTITY_CACHE_ENABLED,
			MarketImpl.class, market.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((MarketModelImpl)market);
	}

	@Override
	public void clearCache(List<Market> markets) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Market market : markets) {
			entityCache.removeResult(MarketModelImpl.ENTITY_CACHE_ENABLED,
				MarketImpl.class, market.getPrimaryKey());

			clearUniqueFindersCache((MarketModelImpl)market);
		}
	}

	protected void cacheUniqueFindersCache(MarketModelImpl marketModelImpl,
		boolean isNew) {
		if (isNew) {
			Object[] args = new Object[] {
					marketModelImpl.getUuid(), marketModelImpl.getGroupId()
				};

			finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
				Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
				marketModelImpl);
		}
		else {
			if ((marketModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						marketModelImpl.getUuid(), marketModelImpl.getGroupId()
					};

				finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
					Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
					marketModelImpl);
			}
		}
	}

	protected void clearUniqueFindersCache(MarketModelImpl marketModelImpl) {
		Object[] args = new Object[] {
				marketModelImpl.getUuid(), marketModelImpl.getGroupId()
			};

		finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

		if ((marketModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			args = new Object[] {
					marketModelImpl.getOriginalUuid(),
					marketModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new market with the primary key. Does not add the market to the database.
	 *
	 * @param marketId the primary key for the new market
	 * @return the new market
	 */
	@Override
	public Market create(long marketId) {
		Market market = new MarketImpl();

		market.setNew(true);
		market.setPrimaryKey(marketId);

		String uuid = PortalUUIDUtil.generate();

		market.setUuid(uuid);

		market.setCompanyId(companyProvider.getCompanyId());

		return market;
	}

	/**
	 * Removes the market with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param marketId the primary key of the market
	 * @return the market that was removed
	 * @throws NoSuchMarketException if a market with the primary key could not be found
	 */
	@Override
	public Market remove(long marketId) throws NoSuchMarketException {
		return remove((Serializable)marketId);
	}

	/**
	 * Removes the market with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the market
	 * @return the market that was removed
	 * @throws NoSuchMarketException if a market with the primary key could not be found
	 */
	@Override
	public Market remove(Serializable primaryKey) throws NoSuchMarketException {
		Session session = null;

		try {
			session = openSession();

			Market market = (Market)session.get(MarketImpl.class, primaryKey);

			if (market == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMarketException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(market);
		}
		catch (NoSuchMarketException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected Market removeImpl(Market market) {
		market = toUnwrappedModel(market);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(market)) {
				market = (Market)session.get(MarketImpl.class,
						market.getPrimaryKeyObj());
			}

			if (market != null) {
				session.delete(market);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (market != null) {
			clearCache(market);
		}

		return market;
	}

	@Override
	public Market updateImpl(Market market) {
		market = toUnwrappedModel(market);

		boolean isNew = market.isNew();

		MarketModelImpl marketModelImpl = (MarketModelImpl)market;

		if (Validator.isNull(market.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			market.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (market.getCreateDate() == null)) {
			if (serviceContext == null) {
				market.setCreateDate(now);
			}
			else {
				market.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!marketModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				market.setModifiedDate(now);
			}
			else {
				market.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (market.isNew()) {
				session.save(market);

				market.setNew(false);
			}
			else {
				market = (Market)session.merge(market);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !MarketModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((marketModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { marketModelImpl.getOriginalUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { marketModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((marketModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						marketModelImpl.getOriginalUuid(),
						marketModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						marketModelImpl.getUuid(),
						marketModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((marketModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVESTARTENDHOUR.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						marketModelImpl.getOriginalStart_hour(),
						marketModelImpl.getOriginalEnd_hour(),
						marketModelImpl.getOriginalActive()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACTIVESTARTENDHOUR,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVESTARTENDHOUR,
					args);

				args = new Object[] {
						marketModelImpl.getStart_hour(),
						marketModelImpl.getEnd_hour(),
						marketModelImpl.getActive()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACTIVESTARTENDHOUR,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVESTARTENDHOUR,
					args);
			}
		}

		entityCache.putResult(MarketModelImpl.ENTITY_CACHE_ENABLED,
			MarketImpl.class, market.getPrimaryKey(), market, false);

		clearUniqueFindersCache(marketModelImpl);
		cacheUniqueFindersCache(marketModelImpl, isNew);

		market.resetOriginalValues();

		return market;
	}

	protected Market toUnwrappedModel(Market market) {
		if (market instanceof MarketImpl) {
			return market;
		}

		MarketImpl marketImpl = new MarketImpl();

		marketImpl.setNew(market.isNew());
		marketImpl.setPrimaryKey(market.getPrimaryKey());

		marketImpl.setUuid(market.getUuid());
		marketImpl.setMarketId(market.getMarketId());
		marketImpl.setGroupId(market.getGroupId());
		marketImpl.setCompanyId(market.getCompanyId());
		marketImpl.setActive(market.isActive());
		marketImpl.setCreateDate(market.getCreateDate());
		marketImpl.setModifiedDate(market.getModifiedDate());
		marketImpl.setStart_hour(market.getStart_hour());
		marketImpl.setEnd_hour(market.getEnd_hour());
		marketImpl.setIdentifier(market.getIdentifier());
		marketImpl.setCurrency(market.getCurrency());

		return marketImpl;
	}

	/**
	 * Returns the market with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the market
	 * @return the market
	 * @throws NoSuchMarketException if a market with the primary key could not be found
	 */
	@Override
	public Market findByPrimaryKey(Serializable primaryKey)
		throws NoSuchMarketException {
		Market market = fetchByPrimaryKey(primaryKey);

		if (market == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchMarketException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return market;
	}

	/**
	 * Returns the market with the primary key or throws a {@link NoSuchMarketException} if it could not be found.
	 *
	 * @param marketId the primary key of the market
	 * @return the market
	 * @throws NoSuchMarketException if a market with the primary key could not be found
	 */
	@Override
	public Market findByPrimaryKey(long marketId) throws NoSuchMarketException {
		return findByPrimaryKey((Serializable)marketId);
	}

	/**
	 * Returns the market with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the market
	 * @return the market, or <code>null</code> if a market with the primary key could not be found
	 */
	@Override
	public Market fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(MarketModelImpl.ENTITY_CACHE_ENABLED,
				MarketImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Market market = (Market)serializable;

		if (market == null) {
			Session session = null;

			try {
				session = openSession();

				market = (Market)session.get(MarketImpl.class, primaryKey);

				if (market != null) {
					cacheResult(market);
				}
				else {
					entityCache.putResult(MarketModelImpl.ENTITY_CACHE_ENABLED,
						MarketImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(MarketModelImpl.ENTITY_CACHE_ENABLED,
					MarketImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return market;
	}

	/**
	 * Returns the market with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param marketId the primary key of the market
	 * @return the market, or <code>null</code> if a market with the primary key could not be found
	 */
	@Override
	public Market fetchByPrimaryKey(long marketId) {
		return fetchByPrimaryKey((Serializable)marketId);
	}

	@Override
	public Map<Serializable, Market> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Market> map = new HashMap<Serializable, Market>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Market market = fetchByPrimaryKey(primaryKey);

			if (market != null) {
				map.put(primaryKey, market);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(MarketModelImpl.ENTITY_CACHE_ENABLED,
					MarketImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Market)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_MARKET_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append(String.valueOf(primaryKey));

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (Market market : (List<Market>)q.list()) {
				map.put(market.getPrimaryKeyObj(), market);

				cacheResult(market);

				uncachedPrimaryKeys.remove(market.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(MarketModelImpl.ENTITY_CACHE_ENABLED,
					MarketImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the markets.
	 *
	 * @return the markets
	 */
	@Override
	public List<Market> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the markets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarketModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of markets
	 * @param end the upper bound of the range of markets (not inclusive)
	 * @return the range of markets
	 */
	@Override
	public List<Market> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the markets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarketModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of markets
	 * @param end the upper bound of the range of markets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of markets
	 */
	@Override
	public List<Market> findAll(int start, int end,
		OrderByComparator<Market> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the markets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarketModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of markets
	 * @param end the upper bound of the range of markets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of markets
	 */
	@Override
	public List<Market> findAll(int start, int end,
		OrderByComparator<Market> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<Market> list = null;

		if (retrieveFromCache) {
			list = (List<Market>)finderCache.getResult(finderPath, finderArgs,
					this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_MARKET);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_MARKET;

				if (pagination) {
					sql = sql.concat(MarketModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Market>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Market>)QueryUtil.list(q, getDialect(), start,
							end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the markets from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Market market : findAll()) {
			remove(market);
		}
	}

	/**
	 * Returns the number of markets.
	 *
	 * @return the number of markets
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_MARKET);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return MarketModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the market persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(MarketImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_MARKET = "SELECT market FROM Market market";
	private static final String _SQL_SELECT_MARKET_WHERE_PKS_IN = "SELECT market FROM Market market WHERE marketId IN (";
	private static final String _SQL_SELECT_MARKET_WHERE = "SELECT market FROM Market market WHERE ";
	private static final String _SQL_COUNT_MARKET = "SELECT COUNT(market) FROM Market market";
	private static final String _SQL_COUNT_MARKET_WHERE = "SELECT COUNT(market) FROM Market market WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "market.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Market exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Market exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(MarketPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid", "active", "currency"
			});
}