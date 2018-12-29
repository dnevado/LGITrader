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

import com.ibtrader.data.exception.NoSuchHistoricalRealtimeException;
import com.ibtrader.data.model.HistoricalRealtime;
import com.ibtrader.data.model.impl.HistoricalRealtimeImpl;
import com.ibtrader.data.model.impl.HistoricalRealtimeModelImpl;
import com.ibtrader.data.service.persistence.HistoricalRealtimePersistence;

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

import java.sql.Timestamp;

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
 * The persistence implementation for the historical realtime service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HistoricalRealtimePersistence
 * @see com.ibtrader.data.service.persistence.HistoricalRealtimeUtil
 * @generated
 */
@ProviderType
public class HistoricalRealtimePersistenceImpl extends BasePersistenceImpl<HistoricalRealtime>
	implements HistoricalRealtimePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HistoricalRealtimeUtil} to access the historical realtime persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HistoricalRealtimeImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(HistoricalRealtimeModelImpl.ENTITY_CACHE_ENABLED,
			HistoricalRealtimeModelImpl.FINDER_CACHE_ENABLED,
			HistoricalRealtimeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(HistoricalRealtimeModelImpl.ENTITY_CACHE_ENABLED,
			HistoricalRealtimeModelImpl.FINDER_CACHE_ENABLED,
			HistoricalRealtimeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HistoricalRealtimeModelImpl.ENTITY_CACHE_ENABLED,
			HistoricalRealtimeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(HistoricalRealtimeModelImpl.ENTITY_CACHE_ENABLED,
			HistoricalRealtimeModelImpl.FINDER_CACHE_ENABLED,
			HistoricalRealtimeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(HistoricalRealtimeModelImpl.ENTITY_CACHE_ENABLED,
			HistoricalRealtimeModelImpl.FINDER_CACHE_ENABLED,
			HistoricalRealtimeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			HistoricalRealtimeModelImpl.UUID_COLUMN_BITMASK |
			HistoricalRealtimeModelImpl.SHAREID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(HistoricalRealtimeModelImpl.ENTITY_CACHE_ENABLED,
			HistoricalRealtimeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the historical realtimes where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching historical realtimes
	 */
	@Override
	public List<HistoricalRealtime> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the historical realtimes where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoricalRealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of historical realtimes
	 * @param end the upper bound of the range of historical realtimes (not inclusive)
	 * @return the range of matching historical realtimes
	 */
	@Override
	public List<HistoricalRealtime> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the historical realtimes where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoricalRealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of historical realtimes
	 * @param end the upper bound of the range of historical realtimes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching historical realtimes
	 */
	@Override
	public List<HistoricalRealtime> findByUuid(String uuid, int start, int end,
		OrderByComparator<HistoricalRealtime> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the historical realtimes where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoricalRealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of historical realtimes
	 * @param end the upper bound of the range of historical realtimes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching historical realtimes
	 */
	@Override
	public List<HistoricalRealtime> findByUuid(String uuid, int start, int end,
		OrderByComparator<HistoricalRealtime> orderByComparator,
		boolean retrieveFromCache) {
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

		List<HistoricalRealtime> list = null;

		if (retrieveFromCache) {
			list = (List<HistoricalRealtime>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (HistoricalRealtime historicalRealtime : list) {
					if (!Objects.equals(uuid, historicalRealtime.getUuid())) {
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

			query.append(_SQL_SELECT_HISTORICALREALTIME_WHERE);

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
				query.append(HistoricalRealtimeModelImpl.ORDER_BY_JPQL);
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
					list = (List<HistoricalRealtime>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<HistoricalRealtime>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Returns the first historical realtime in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching historical realtime
	 * @throws NoSuchHistoricalRealtimeException if a matching historical realtime could not be found
	 */
	@Override
	public HistoricalRealtime findByUuid_First(String uuid,
		OrderByComparator<HistoricalRealtime> orderByComparator)
		throws NoSuchHistoricalRealtimeException {
		HistoricalRealtime historicalRealtime = fetchByUuid_First(uuid,
				orderByComparator);

		if (historicalRealtime != null) {
			return historicalRealtime;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchHistoricalRealtimeException(msg.toString());
	}

	/**
	 * Returns the first historical realtime in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching historical realtime, or <code>null</code> if a matching historical realtime could not be found
	 */
	@Override
	public HistoricalRealtime fetchByUuid_First(String uuid,
		OrderByComparator<HistoricalRealtime> orderByComparator) {
		List<HistoricalRealtime> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last historical realtime in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching historical realtime
	 * @throws NoSuchHistoricalRealtimeException if a matching historical realtime could not be found
	 */
	@Override
	public HistoricalRealtime findByUuid_Last(String uuid,
		OrderByComparator<HistoricalRealtime> orderByComparator)
		throws NoSuchHistoricalRealtimeException {
		HistoricalRealtime historicalRealtime = fetchByUuid_Last(uuid,
				orderByComparator);

		if (historicalRealtime != null) {
			return historicalRealtime;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchHistoricalRealtimeException(msg.toString());
	}

	/**
	 * Returns the last historical realtime in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching historical realtime, or <code>null</code> if a matching historical realtime could not be found
	 */
	@Override
	public HistoricalRealtime fetchByUuid_Last(String uuid,
		OrderByComparator<HistoricalRealtime> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<HistoricalRealtime> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the historical realtimes before and after the current historical realtime in the ordered set where uuid = &#63;.
	 *
	 * @param realtimeId the primary key of the current historical realtime
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next historical realtime
	 * @throws NoSuchHistoricalRealtimeException if a historical realtime with the primary key could not be found
	 */
	@Override
	public HistoricalRealtime[] findByUuid_PrevAndNext(long realtimeId,
		String uuid, OrderByComparator<HistoricalRealtime> orderByComparator)
		throws NoSuchHistoricalRealtimeException {
		HistoricalRealtime historicalRealtime = findByPrimaryKey(realtimeId);

		Session session = null;

		try {
			session = openSession();

			HistoricalRealtime[] array = new HistoricalRealtimeImpl[3];

			array[0] = getByUuid_PrevAndNext(session, historicalRealtime, uuid,
					orderByComparator, true);

			array[1] = historicalRealtime;

			array[2] = getByUuid_PrevAndNext(session, historicalRealtime, uuid,
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

	protected HistoricalRealtime getByUuid_PrevAndNext(Session session,
		HistoricalRealtime historicalRealtime, String uuid,
		OrderByComparator<HistoricalRealtime> orderByComparator,
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

		query.append(_SQL_SELECT_HISTORICALREALTIME_WHERE);

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
			query.append(HistoricalRealtimeModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(historicalRealtime);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<HistoricalRealtime> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the historical realtimes where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (HistoricalRealtime historicalRealtime : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(historicalRealtime);
		}
	}

	/**
	 * Returns the number of historical realtimes where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching historical realtimes
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_HISTORICALREALTIME_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "historicalRealtime.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "historicalRealtime.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(historicalRealtime.uuid IS NULL OR historicalRealtime.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(HistoricalRealtimeModelImpl.ENTITY_CACHE_ENABLED,
			HistoricalRealtimeModelImpl.FINDER_CACHE_ENABLED,
			HistoricalRealtimeImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			HistoricalRealtimeModelImpl.UUID_COLUMN_BITMASK |
			HistoricalRealtimeModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(HistoricalRealtimeModelImpl.ENTITY_CACHE_ENABLED,
			HistoricalRealtimeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the historical realtime where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchHistoricalRealtimeException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching historical realtime
	 * @throws NoSuchHistoricalRealtimeException if a matching historical realtime could not be found
	 */
	@Override
	public HistoricalRealtime findByUUID_G(String uuid, long groupId)
		throws NoSuchHistoricalRealtimeException {
		HistoricalRealtime historicalRealtime = fetchByUUID_G(uuid, groupId);

		if (historicalRealtime == null) {
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

			throw new NoSuchHistoricalRealtimeException(msg.toString());
		}

		return historicalRealtime;
	}

	/**
	 * Returns the historical realtime where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching historical realtime, or <code>null</code> if a matching historical realtime could not be found
	 */
	@Override
	public HistoricalRealtime fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the historical realtime where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching historical realtime, or <code>null</code> if a matching historical realtime could not be found
	 */
	@Override
	public HistoricalRealtime fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof HistoricalRealtime) {
			HistoricalRealtime historicalRealtime = (HistoricalRealtime)result;

			if (!Objects.equals(uuid, historicalRealtime.getUuid()) ||
					(groupId != historicalRealtime.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_HISTORICALREALTIME_WHERE);

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

				List<HistoricalRealtime> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					HistoricalRealtime historicalRealtime = list.get(0);

					result = historicalRealtime;

					cacheResult(historicalRealtime);

					if ((historicalRealtime.getUuid() == null) ||
							!historicalRealtime.getUuid().equals(uuid) ||
							(historicalRealtime.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, historicalRealtime);
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
			return (HistoricalRealtime)result;
		}
	}

	/**
	 * Removes the historical realtime where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the historical realtime that was removed
	 */
	@Override
	public HistoricalRealtime removeByUUID_G(String uuid, long groupId)
		throws NoSuchHistoricalRealtimeException {
		HistoricalRealtime historicalRealtime = findByUUID_G(uuid, groupId);

		return remove(historicalRealtime);
	}

	/**
	 * Returns the number of historical realtimes where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching historical realtimes
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_HISTORICALREALTIME_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "historicalRealtime.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "historicalRealtime.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(historicalRealtime.uuid IS NULL OR historicalRealtime.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "historicalRealtime.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(HistoricalRealtimeModelImpl.ENTITY_CACHE_ENABLED,
			HistoricalRealtimeModelImpl.FINDER_CACHE_ENABLED,
			HistoricalRealtimeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(HistoricalRealtimeModelImpl.ENTITY_CACHE_ENABLED,
			HistoricalRealtimeModelImpl.FINDER_CACHE_ENABLED,
			HistoricalRealtimeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			HistoricalRealtimeModelImpl.UUID_COLUMN_BITMASK |
			HistoricalRealtimeModelImpl.COMPANYID_COLUMN_BITMASK |
			HistoricalRealtimeModelImpl.SHAREID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(HistoricalRealtimeModelImpl.ENTITY_CACHE_ENABLED,
			HistoricalRealtimeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the historical realtimes where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching historical realtimes
	 */
	@Override
	public List<HistoricalRealtime> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the historical realtimes where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoricalRealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of historical realtimes
	 * @param end the upper bound of the range of historical realtimes (not inclusive)
	 * @return the range of matching historical realtimes
	 */
	@Override
	public List<HistoricalRealtime> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the historical realtimes where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoricalRealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of historical realtimes
	 * @param end the upper bound of the range of historical realtimes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching historical realtimes
	 */
	@Override
	public List<HistoricalRealtime> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<HistoricalRealtime> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the historical realtimes where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoricalRealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of historical realtimes
	 * @param end the upper bound of the range of historical realtimes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching historical realtimes
	 */
	@Override
	public List<HistoricalRealtime> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<HistoricalRealtime> orderByComparator,
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

		List<HistoricalRealtime> list = null;

		if (retrieveFromCache) {
			list = (List<HistoricalRealtime>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (HistoricalRealtime historicalRealtime : list) {
					if (!Objects.equals(uuid, historicalRealtime.getUuid()) ||
							(companyId != historicalRealtime.getCompanyId())) {
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

			query.append(_SQL_SELECT_HISTORICALREALTIME_WHERE);

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
				query.append(HistoricalRealtimeModelImpl.ORDER_BY_JPQL);
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
					list = (List<HistoricalRealtime>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<HistoricalRealtime>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Returns the first historical realtime in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching historical realtime
	 * @throws NoSuchHistoricalRealtimeException if a matching historical realtime could not be found
	 */
	@Override
	public HistoricalRealtime findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<HistoricalRealtime> orderByComparator)
		throws NoSuchHistoricalRealtimeException {
		HistoricalRealtime historicalRealtime = fetchByUuid_C_First(uuid,
				companyId, orderByComparator);

		if (historicalRealtime != null) {
			return historicalRealtime;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchHistoricalRealtimeException(msg.toString());
	}

	/**
	 * Returns the first historical realtime in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching historical realtime, or <code>null</code> if a matching historical realtime could not be found
	 */
	@Override
	public HistoricalRealtime fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<HistoricalRealtime> orderByComparator) {
		List<HistoricalRealtime> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last historical realtime in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching historical realtime
	 * @throws NoSuchHistoricalRealtimeException if a matching historical realtime could not be found
	 */
	@Override
	public HistoricalRealtime findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<HistoricalRealtime> orderByComparator)
		throws NoSuchHistoricalRealtimeException {
		HistoricalRealtime historicalRealtime = fetchByUuid_C_Last(uuid,
				companyId, orderByComparator);

		if (historicalRealtime != null) {
			return historicalRealtime;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchHistoricalRealtimeException(msg.toString());
	}

	/**
	 * Returns the last historical realtime in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching historical realtime, or <code>null</code> if a matching historical realtime could not be found
	 */
	@Override
	public HistoricalRealtime fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<HistoricalRealtime> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<HistoricalRealtime> list = findByUuid_C(uuid, companyId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the historical realtimes before and after the current historical realtime in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param realtimeId the primary key of the current historical realtime
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next historical realtime
	 * @throws NoSuchHistoricalRealtimeException if a historical realtime with the primary key could not be found
	 */
	@Override
	public HistoricalRealtime[] findByUuid_C_PrevAndNext(long realtimeId,
		String uuid, long companyId,
		OrderByComparator<HistoricalRealtime> orderByComparator)
		throws NoSuchHistoricalRealtimeException {
		HistoricalRealtime historicalRealtime = findByPrimaryKey(realtimeId);

		Session session = null;

		try {
			session = openSession();

			HistoricalRealtime[] array = new HistoricalRealtimeImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, historicalRealtime,
					uuid, companyId, orderByComparator, true);

			array[1] = historicalRealtime;

			array[2] = getByUuid_C_PrevAndNext(session, historicalRealtime,
					uuid, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected HistoricalRealtime getByUuid_C_PrevAndNext(Session session,
		HistoricalRealtime historicalRealtime, String uuid, long companyId,
		OrderByComparator<HistoricalRealtime> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_HISTORICALREALTIME_WHERE);

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
			query.append(HistoricalRealtimeModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(historicalRealtime);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<HistoricalRealtime> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the historical realtimes where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (HistoricalRealtime historicalRealtime : findByUuid_C(uuid,
				companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(historicalRealtime);
		}
	}

	/**
	 * Returns the number of historical realtimes where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching historical realtimes
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_HISTORICALREALTIME_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "historicalRealtime.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "historicalRealtime.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(historicalRealtime.uuid IS NULL OR historicalRealtime.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "historicalRealtime.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_DATE = new FinderPath(HistoricalRealtimeModelImpl.ENTITY_CACHE_ENABLED,
			HistoricalRealtimeModelImpl.FINDER_CACHE_ENABLED,
			HistoricalRealtimeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByDate",
			new String[] {
				Date.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DATE = new FinderPath(HistoricalRealtimeModelImpl.ENTITY_CACHE_ENABLED,
			HistoricalRealtimeModelImpl.FINDER_CACHE_ENABLED,
			HistoricalRealtimeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByDate",
			new String[] { Date.class.getName() },
			HistoricalRealtimeModelImpl.CREATEDATE_COLUMN_BITMASK |
			HistoricalRealtimeModelImpl.SHAREID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DATE = new FinderPath(HistoricalRealtimeModelImpl.ENTITY_CACHE_ENABLED,
			HistoricalRealtimeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByDate",
			new String[] { Date.class.getName() });

	/**
	 * Returns all the historical realtimes where createDate = &#63;.
	 *
	 * @param createDate the create date
	 * @return the matching historical realtimes
	 */
	@Override
	public List<HistoricalRealtime> findByDate(Date createDate) {
		return findByDate(createDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the historical realtimes where createDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoricalRealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param start the lower bound of the range of historical realtimes
	 * @param end the upper bound of the range of historical realtimes (not inclusive)
	 * @return the range of matching historical realtimes
	 */
	@Override
	public List<HistoricalRealtime> findByDate(Date createDate, int start,
		int end) {
		return findByDate(createDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the historical realtimes where createDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoricalRealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param start the lower bound of the range of historical realtimes
	 * @param end the upper bound of the range of historical realtimes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching historical realtimes
	 */
	@Override
	public List<HistoricalRealtime> findByDate(Date createDate, int start,
		int end, OrderByComparator<HistoricalRealtime> orderByComparator) {
		return findByDate(createDate, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the historical realtimes where createDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoricalRealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param start the lower bound of the range of historical realtimes
	 * @param end the upper bound of the range of historical realtimes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching historical realtimes
	 */
	@Override
	public List<HistoricalRealtime> findByDate(Date createDate, int start,
		int end, OrderByComparator<HistoricalRealtime> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DATE;
			finderArgs = new Object[] { createDate };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_DATE;
			finderArgs = new Object[] { createDate, start, end, orderByComparator };
		}

		List<HistoricalRealtime> list = null;

		if (retrieveFromCache) {
			list = (List<HistoricalRealtime>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (HistoricalRealtime historicalRealtime : list) {
					if (!Objects.equals(createDate,
								historicalRealtime.getCreateDate())) {
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

			query.append(_SQL_SELECT_HISTORICALREALTIME_WHERE);

			boolean bindCreateDate = false;

			if (createDate == null) {
				query.append(_FINDER_COLUMN_DATE_CREATEDATE_1);
			}
			else {
				bindCreateDate = true;

				query.append(_FINDER_COLUMN_DATE_CREATEDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(HistoricalRealtimeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCreateDate) {
					qPos.add(new Timestamp(createDate.getTime()));
				}

				if (!pagination) {
					list = (List<HistoricalRealtime>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<HistoricalRealtime>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Returns the first historical realtime in the ordered set where createDate = &#63;.
	 *
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching historical realtime
	 * @throws NoSuchHistoricalRealtimeException if a matching historical realtime could not be found
	 */
	@Override
	public HistoricalRealtime findByDate_First(Date createDate,
		OrderByComparator<HistoricalRealtime> orderByComparator)
		throws NoSuchHistoricalRealtimeException {
		HistoricalRealtime historicalRealtime = fetchByDate_First(createDate,
				orderByComparator);

		if (historicalRealtime != null) {
			return historicalRealtime;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createDate=");
		msg.append(createDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchHistoricalRealtimeException(msg.toString());
	}

	/**
	 * Returns the first historical realtime in the ordered set where createDate = &#63;.
	 *
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching historical realtime, or <code>null</code> if a matching historical realtime could not be found
	 */
	@Override
	public HistoricalRealtime fetchByDate_First(Date createDate,
		OrderByComparator<HistoricalRealtime> orderByComparator) {
		List<HistoricalRealtime> list = findByDate(createDate, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last historical realtime in the ordered set where createDate = &#63;.
	 *
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching historical realtime
	 * @throws NoSuchHistoricalRealtimeException if a matching historical realtime could not be found
	 */
	@Override
	public HistoricalRealtime findByDate_Last(Date createDate,
		OrderByComparator<HistoricalRealtime> orderByComparator)
		throws NoSuchHistoricalRealtimeException {
		HistoricalRealtime historicalRealtime = fetchByDate_Last(createDate,
				orderByComparator);

		if (historicalRealtime != null) {
			return historicalRealtime;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createDate=");
		msg.append(createDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchHistoricalRealtimeException(msg.toString());
	}

	/**
	 * Returns the last historical realtime in the ordered set where createDate = &#63;.
	 *
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching historical realtime, or <code>null</code> if a matching historical realtime could not be found
	 */
	@Override
	public HistoricalRealtime fetchByDate_Last(Date createDate,
		OrderByComparator<HistoricalRealtime> orderByComparator) {
		int count = countByDate(createDate);

		if (count == 0) {
			return null;
		}

		List<HistoricalRealtime> list = findByDate(createDate, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the historical realtimes before and after the current historical realtime in the ordered set where createDate = &#63;.
	 *
	 * @param realtimeId the primary key of the current historical realtime
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next historical realtime
	 * @throws NoSuchHistoricalRealtimeException if a historical realtime with the primary key could not be found
	 */
	@Override
	public HistoricalRealtime[] findByDate_PrevAndNext(long realtimeId,
		Date createDate, OrderByComparator<HistoricalRealtime> orderByComparator)
		throws NoSuchHistoricalRealtimeException {
		HistoricalRealtime historicalRealtime = findByPrimaryKey(realtimeId);

		Session session = null;

		try {
			session = openSession();

			HistoricalRealtime[] array = new HistoricalRealtimeImpl[3];

			array[0] = getByDate_PrevAndNext(session, historicalRealtime,
					createDate, orderByComparator, true);

			array[1] = historicalRealtime;

			array[2] = getByDate_PrevAndNext(session, historicalRealtime,
					createDate, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected HistoricalRealtime getByDate_PrevAndNext(Session session,
		HistoricalRealtime historicalRealtime, Date createDate,
		OrderByComparator<HistoricalRealtime> orderByComparator,
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

		query.append(_SQL_SELECT_HISTORICALREALTIME_WHERE);

		boolean bindCreateDate = false;

		if (createDate == null) {
			query.append(_FINDER_COLUMN_DATE_CREATEDATE_1);
		}
		else {
			bindCreateDate = true;

			query.append(_FINDER_COLUMN_DATE_CREATEDATE_2);
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
			query.append(HistoricalRealtimeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindCreateDate) {
			qPos.add(new Timestamp(createDate.getTime()));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(historicalRealtime);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<HistoricalRealtime> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the historical realtimes where createDate = &#63; from the database.
	 *
	 * @param createDate the create date
	 */
	@Override
	public void removeByDate(Date createDate) {
		for (HistoricalRealtime historicalRealtime : findByDate(createDate,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(historicalRealtime);
		}
	}

	/**
	 * Returns the number of historical realtimes where createDate = &#63;.
	 *
	 * @param createDate the create date
	 * @return the number of matching historical realtimes
	 */
	@Override
	public int countByDate(Date createDate) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_DATE;

		Object[] finderArgs = new Object[] { createDate };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_HISTORICALREALTIME_WHERE);

			boolean bindCreateDate = false;

			if (createDate == null) {
				query.append(_FINDER_COLUMN_DATE_CREATEDATE_1);
			}
			else {
				bindCreateDate = true;

				query.append(_FINDER_COLUMN_DATE_CREATEDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCreateDate) {
					qPos.add(new Timestamp(createDate.getTime()));
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

	private static final String _FINDER_COLUMN_DATE_CREATEDATE_1 = "historicalRealtime.createDate IS NULL";
	private static final String _FINDER_COLUMN_DATE_CREATEDATE_2 = "historicalRealtime.createDate = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYSHARE =
		new FinderPath(HistoricalRealtimeModelImpl.ENTITY_CACHE_ENABLED,
			HistoricalRealtimeModelImpl.FINDER_CACHE_ENABLED,
			HistoricalRealtimeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyShare",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYSHARE =
		new FinderPath(HistoricalRealtimeModelImpl.ENTITY_CACHE_ENABLED,
			HistoricalRealtimeModelImpl.FINDER_CACHE_ENABLED,
			HistoricalRealtimeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyShare",
			new String[] { Long.class.getName(), Long.class.getName() },
			HistoricalRealtimeModelImpl.COMPANYID_COLUMN_BITMASK |
			HistoricalRealtimeModelImpl.SHAREID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYSHARE = new FinderPath(HistoricalRealtimeModelImpl.ENTITY_CACHE_ENABLED,
			HistoricalRealtimeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyShare",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the historical realtimes where companyId = &#63; and shareId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @return the matching historical realtimes
	 */
	@Override
	public List<HistoricalRealtime> findByCompanyShare(long companyId,
		long shareId) {
		return findByCompanyShare(companyId, shareId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the historical realtimes where companyId = &#63; and shareId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoricalRealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param start the lower bound of the range of historical realtimes
	 * @param end the upper bound of the range of historical realtimes (not inclusive)
	 * @return the range of matching historical realtimes
	 */
	@Override
	public List<HistoricalRealtime> findByCompanyShare(long companyId,
		long shareId, int start, int end) {
		return findByCompanyShare(companyId, shareId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the historical realtimes where companyId = &#63; and shareId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoricalRealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param start the lower bound of the range of historical realtimes
	 * @param end the upper bound of the range of historical realtimes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching historical realtimes
	 */
	@Override
	public List<HistoricalRealtime> findByCompanyShare(long companyId,
		long shareId, int start, int end,
		OrderByComparator<HistoricalRealtime> orderByComparator) {
		return findByCompanyShare(companyId, shareId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the historical realtimes where companyId = &#63; and shareId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoricalRealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param start the lower bound of the range of historical realtimes
	 * @param end the upper bound of the range of historical realtimes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching historical realtimes
	 */
	@Override
	public List<HistoricalRealtime> findByCompanyShare(long companyId,
		long shareId, int start, int end,
		OrderByComparator<HistoricalRealtime> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYSHARE;
			finderArgs = new Object[] { companyId, shareId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYSHARE;
			finderArgs = new Object[] {
					companyId, shareId,
					
					start, end, orderByComparator
				};
		}

		List<HistoricalRealtime> list = null;

		if (retrieveFromCache) {
			list = (List<HistoricalRealtime>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (HistoricalRealtime historicalRealtime : list) {
					if ((companyId != historicalRealtime.getCompanyId()) ||
							(shareId != historicalRealtime.getShareId())) {
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

			query.append(_SQL_SELECT_HISTORICALREALTIME_WHERE);

			query.append(_FINDER_COLUMN_COMPANYSHARE_COMPANYID_2);

			query.append(_FINDER_COLUMN_COMPANYSHARE_SHAREID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(HistoricalRealtimeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(shareId);

				if (!pagination) {
					list = (List<HistoricalRealtime>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<HistoricalRealtime>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Returns the first historical realtime in the ordered set where companyId = &#63; and shareId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching historical realtime
	 * @throws NoSuchHistoricalRealtimeException if a matching historical realtime could not be found
	 */
	@Override
	public HistoricalRealtime findByCompanyShare_First(long companyId,
		long shareId, OrderByComparator<HistoricalRealtime> orderByComparator)
		throws NoSuchHistoricalRealtimeException {
		HistoricalRealtime historicalRealtime = fetchByCompanyShare_First(companyId,
				shareId, orderByComparator);

		if (historicalRealtime != null) {
			return historicalRealtime;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", shareId=");
		msg.append(shareId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchHistoricalRealtimeException(msg.toString());
	}

	/**
	 * Returns the first historical realtime in the ordered set where companyId = &#63; and shareId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching historical realtime, or <code>null</code> if a matching historical realtime could not be found
	 */
	@Override
	public HistoricalRealtime fetchByCompanyShare_First(long companyId,
		long shareId, OrderByComparator<HistoricalRealtime> orderByComparator) {
		List<HistoricalRealtime> list = findByCompanyShare(companyId, shareId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last historical realtime in the ordered set where companyId = &#63; and shareId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching historical realtime
	 * @throws NoSuchHistoricalRealtimeException if a matching historical realtime could not be found
	 */
	@Override
	public HistoricalRealtime findByCompanyShare_Last(long companyId,
		long shareId, OrderByComparator<HistoricalRealtime> orderByComparator)
		throws NoSuchHistoricalRealtimeException {
		HistoricalRealtime historicalRealtime = fetchByCompanyShare_Last(companyId,
				shareId, orderByComparator);

		if (historicalRealtime != null) {
			return historicalRealtime;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", shareId=");
		msg.append(shareId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchHistoricalRealtimeException(msg.toString());
	}

	/**
	 * Returns the last historical realtime in the ordered set where companyId = &#63; and shareId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching historical realtime, or <code>null</code> if a matching historical realtime could not be found
	 */
	@Override
	public HistoricalRealtime fetchByCompanyShare_Last(long companyId,
		long shareId, OrderByComparator<HistoricalRealtime> orderByComparator) {
		int count = countByCompanyShare(companyId, shareId);

		if (count == 0) {
			return null;
		}

		List<HistoricalRealtime> list = findByCompanyShare(companyId, shareId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the historical realtimes before and after the current historical realtime in the ordered set where companyId = &#63; and shareId = &#63;.
	 *
	 * @param realtimeId the primary key of the current historical realtime
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next historical realtime
	 * @throws NoSuchHistoricalRealtimeException if a historical realtime with the primary key could not be found
	 */
	@Override
	public HistoricalRealtime[] findByCompanyShare_PrevAndNext(
		long realtimeId, long companyId, long shareId,
		OrderByComparator<HistoricalRealtime> orderByComparator)
		throws NoSuchHistoricalRealtimeException {
		HistoricalRealtime historicalRealtime = findByPrimaryKey(realtimeId);

		Session session = null;

		try {
			session = openSession();

			HistoricalRealtime[] array = new HistoricalRealtimeImpl[3];

			array[0] = getByCompanyShare_PrevAndNext(session,
					historicalRealtime, companyId, shareId, orderByComparator,
					true);

			array[1] = historicalRealtime;

			array[2] = getByCompanyShare_PrevAndNext(session,
					historicalRealtime, companyId, shareId, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected HistoricalRealtime getByCompanyShare_PrevAndNext(
		Session session, HistoricalRealtime historicalRealtime, long companyId,
		long shareId, OrderByComparator<HistoricalRealtime> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_HISTORICALREALTIME_WHERE);

		query.append(_FINDER_COLUMN_COMPANYSHARE_COMPANYID_2);

		query.append(_FINDER_COLUMN_COMPANYSHARE_SHAREID_2);

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
			query.append(HistoricalRealtimeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(shareId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(historicalRealtime);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<HistoricalRealtime> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the historical realtimes where companyId = &#63; and shareId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 */
	@Override
	public void removeByCompanyShare(long companyId, long shareId) {
		for (HistoricalRealtime historicalRealtime : findByCompanyShare(
				companyId, shareId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(historicalRealtime);
		}
	}

	/**
	 * Returns the number of historical realtimes where companyId = &#63; and shareId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @return the number of matching historical realtimes
	 */
	@Override
	public int countByCompanyShare(long companyId, long shareId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYSHARE;

		Object[] finderArgs = new Object[] { companyId, shareId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_HISTORICALREALTIME_WHERE);

			query.append(_FINDER_COLUMN_COMPANYSHARE_COMPANYID_2);

			query.append(_FINDER_COLUMN_COMPANYSHARE_SHAREID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(shareId);

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

	private static final String _FINDER_COLUMN_COMPANYSHARE_COMPANYID_2 = "historicalRealtime.companyId = ? AND ";
	private static final String _FINDER_COLUMN_COMPANYSHARE_SHAREID_2 = "historicalRealtime.shareId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYSHAREDATE =
		new FinderPath(HistoricalRealtimeModelImpl.ENTITY_CACHE_ENABLED,
			HistoricalRealtimeModelImpl.FINDER_CACHE_ENABLED,
			HistoricalRealtimeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyShareDate",
			new String[] {
				Long.class.getName(), Long.class.getName(), Date.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYSHAREDATE =
		new FinderPath(HistoricalRealtimeModelImpl.ENTITY_CACHE_ENABLED,
			HistoricalRealtimeModelImpl.FINDER_CACHE_ENABLED,
			HistoricalRealtimeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCompanyShareDate",
			new String[] {
				Long.class.getName(), Long.class.getName(), Date.class.getName()
			},
			HistoricalRealtimeModelImpl.COMPANYID_COLUMN_BITMASK |
			HistoricalRealtimeModelImpl.SHAREID_COLUMN_BITMASK |
			HistoricalRealtimeModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYSHAREDATE = new FinderPath(HistoricalRealtimeModelImpl.ENTITY_CACHE_ENABLED,
			HistoricalRealtimeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCompanyShareDate",
			new String[] {
				Long.class.getName(), Long.class.getName(), Date.class.getName()
			});

	/**
	 * Returns all the historical realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63;.
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 * @return the matching historical realtimes
	 */
	@Override
	public List<HistoricalRealtime> findByCompanyShareDate(long companyId,
		long shareId, Date createDate) {
		return findByCompanyShareDate(companyId, shareId, createDate,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the historical realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoricalRealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 * @param start the lower bound of the range of historical realtimes
	 * @param end the upper bound of the range of historical realtimes (not inclusive)
	 * @return the range of matching historical realtimes
	 */
	@Override
	public List<HistoricalRealtime> findByCompanyShareDate(long companyId,
		long shareId, Date createDate, int start, int end) {
		return findByCompanyShareDate(companyId, shareId, createDate, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the historical realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoricalRealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 * @param start the lower bound of the range of historical realtimes
	 * @param end the upper bound of the range of historical realtimes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching historical realtimes
	 */
	@Override
	public List<HistoricalRealtime> findByCompanyShareDate(long companyId,
		long shareId, Date createDate, int start, int end,
		OrderByComparator<HistoricalRealtime> orderByComparator) {
		return findByCompanyShareDate(companyId, shareId, createDate, start,
			end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the historical realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoricalRealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 * @param start the lower bound of the range of historical realtimes
	 * @param end the upper bound of the range of historical realtimes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching historical realtimes
	 */
	@Override
	public List<HistoricalRealtime> findByCompanyShareDate(long companyId,
		long shareId, Date createDate, int start, int end,
		OrderByComparator<HistoricalRealtime> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYSHAREDATE;
			finderArgs = new Object[] { companyId, shareId, createDate };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYSHAREDATE;
			finderArgs = new Object[] {
					companyId, shareId, createDate,
					
					start, end, orderByComparator
				};
		}

		List<HistoricalRealtime> list = null;

		if (retrieveFromCache) {
			list = (List<HistoricalRealtime>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (HistoricalRealtime historicalRealtime : list) {
					if ((companyId != historicalRealtime.getCompanyId()) ||
							(shareId != historicalRealtime.getShareId()) ||
							!Objects.equals(createDate,
								historicalRealtime.getCreateDate())) {
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

			query.append(_SQL_SELECT_HISTORICALREALTIME_WHERE);

			query.append(_FINDER_COLUMN_COMPANYSHAREDATE_COMPANYID_2);

			query.append(_FINDER_COLUMN_COMPANYSHAREDATE_SHAREID_2);

			boolean bindCreateDate = false;

			if (createDate == null) {
				query.append(_FINDER_COLUMN_COMPANYSHAREDATE_CREATEDATE_1);
			}
			else {
				bindCreateDate = true;

				query.append(_FINDER_COLUMN_COMPANYSHAREDATE_CREATEDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(HistoricalRealtimeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(shareId);

				if (bindCreateDate) {
					qPos.add(new Timestamp(createDate.getTime()));
				}

				if (!pagination) {
					list = (List<HistoricalRealtime>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<HistoricalRealtime>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Returns the first historical realtime in the ordered set where companyId = &#63; and shareId = &#63; and createDate = &#63;.
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching historical realtime
	 * @throws NoSuchHistoricalRealtimeException if a matching historical realtime could not be found
	 */
	@Override
	public HistoricalRealtime findByCompanyShareDate_First(long companyId,
		long shareId, Date createDate,
		OrderByComparator<HistoricalRealtime> orderByComparator)
		throws NoSuchHistoricalRealtimeException {
		HistoricalRealtime historicalRealtime = fetchByCompanyShareDate_First(companyId,
				shareId, createDate, orderByComparator);

		if (historicalRealtime != null) {
			return historicalRealtime;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", shareId=");
		msg.append(shareId);

		msg.append(", createDate=");
		msg.append(createDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchHistoricalRealtimeException(msg.toString());
	}

	/**
	 * Returns the first historical realtime in the ordered set where companyId = &#63; and shareId = &#63; and createDate = &#63;.
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching historical realtime, or <code>null</code> if a matching historical realtime could not be found
	 */
	@Override
	public HistoricalRealtime fetchByCompanyShareDate_First(long companyId,
		long shareId, Date createDate,
		OrderByComparator<HistoricalRealtime> orderByComparator) {
		List<HistoricalRealtime> list = findByCompanyShareDate(companyId,
				shareId, createDate, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last historical realtime in the ordered set where companyId = &#63; and shareId = &#63; and createDate = &#63;.
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching historical realtime
	 * @throws NoSuchHistoricalRealtimeException if a matching historical realtime could not be found
	 */
	@Override
	public HistoricalRealtime findByCompanyShareDate_Last(long companyId,
		long shareId, Date createDate,
		OrderByComparator<HistoricalRealtime> orderByComparator)
		throws NoSuchHistoricalRealtimeException {
		HistoricalRealtime historicalRealtime = fetchByCompanyShareDate_Last(companyId,
				shareId, createDate, orderByComparator);

		if (historicalRealtime != null) {
			return historicalRealtime;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", shareId=");
		msg.append(shareId);

		msg.append(", createDate=");
		msg.append(createDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchHistoricalRealtimeException(msg.toString());
	}

	/**
	 * Returns the last historical realtime in the ordered set where companyId = &#63; and shareId = &#63; and createDate = &#63;.
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching historical realtime, or <code>null</code> if a matching historical realtime could not be found
	 */
	@Override
	public HistoricalRealtime fetchByCompanyShareDate_Last(long companyId,
		long shareId, Date createDate,
		OrderByComparator<HistoricalRealtime> orderByComparator) {
		int count = countByCompanyShareDate(companyId, shareId, createDate);

		if (count == 0) {
			return null;
		}

		List<HistoricalRealtime> list = findByCompanyShareDate(companyId,
				shareId, createDate, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the historical realtimes before and after the current historical realtime in the ordered set where companyId = &#63; and shareId = &#63; and createDate = &#63;.
	 *
	 * @param realtimeId the primary key of the current historical realtime
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next historical realtime
	 * @throws NoSuchHistoricalRealtimeException if a historical realtime with the primary key could not be found
	 */
	@Override
	public HistoricalRealtime[] findByCompanyShareDate_PrevAndNext(
		long realtimeId, long companyId, long shareId, Date createDate,
		OrderByComparator<HistoricalRealtime> orderByComparator)
		throws NoSuchHistoricalRealtimeException {
		HistoricalRealtime historicalRealtime = findByPrimaryKey(realtimeId);

		Session session = null;

		try {
			session = openSession();

			HistoricalRealtime[] array = new HistoricalRealtimeImpl[3];

			array[0] = getByCompanyShareDate_PrevAndNext(session,
					historicalRealtime, companyId, shareId, createDate,
					orderByComparator, true);

			array[1] = historicalRealtime;

			array[2] = getByCompanyShareDate_PrevAndNext(session,
					historicalRealtime, companyId, shareId, createDate,
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

	protected HistoricalRealtime getByCompanyShareDate_PrevAndNext(
		Session session, HistoricalRealtime historicalRealtime, long companyId,
		long shareId, Date createDate,
		OrderByComparator<HistoricalRealtime> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_HISTORICALREALTIME_WHERE);

		query.append(_FINDER_COLUMN_COMPANYSHAREDATE_COMPANYID_2);

		query.append(_FINDER_COLUMN_COMPANYSHAREDATE_SHAREID_2);

		boolean bindCreateDate = false;

		if (createDate == null) {
			query.append(_FINDER_COLUMN_COMPANYSHAREDATE_CREATEDATE_1);
		}
		else {
			bindCreateDate = true;

			query.append(_FINDER_COLUMN_COMPANYSHAREDATE_CREATEDATE_2);
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
			query.append(HistoricalRealtimeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(shareId);

		if (bindCreateDate) {
			qPos.add(new Timestamp(createDate.getTime()));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(historicalRealtime);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<HistoricalRealtime> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the historical realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 */
	@Override
	public void removeByCompanyShareDate(long companyId, long shareId,
		Date createDate) {
		for (HistoricalRealtime historicalRealtime : findByCompanyShareDate(
				companyId, shareId, createDate, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(historicalRealtime);
		}
	}

	/**
	 * Returns the number of historical realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63;.
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 * @return the number of matching historical realtimes
	 */
	@Override
	public int countByCompanyShareDate(long companyId, long shareId,
		Date createDate) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYSHAREDATE;

		Object[] finderArgs = new Object[] { companyId, shareId, createDate };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_HISTORICALREALTIME_WHERE);

			query.append(_FINDER_COLUMN_COMPANYSHAREDATE_COMPANYID_2);

			query.append(_FINDER_COLUMN_COMPANYSHAREDATE_SHAREID_2);

			boolean bindCreateDate = false;

			if (createDate == null) {
				query.append(_FINDER_COLUMN_COMPANYSHAREDATE_CREATEDATE_1);
			}
			else {
				bindCreateDate = true;

				query.append(_FINDER_COLUMN_COMPANYSHAREDATE_CREATEDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(shareId);

				if (bindCreateDate) {
					qPos.add(new Timestamp(createDate.getTime()));
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

	private static final String _FINDER_COLUMN_COMPANYSHAREDATE_COMPANYID_2 = "historicalRealtime.companyId = ? AND ";
	private static final String _FINDER_COLUMN_COMPANYSHAREDATE_SHAREID_2 = "historicalRealtime.shareId = ? AND ";
	private static final String _FINDER_COLUMN_COMPANYSHAREDATE_CREATEDATE_1 = "historicalRealtime.createDate IS NULL";
	private static final String _FINDER_COLUMN_COMPANYSHAREDATE_CREATEDATE_2 = "historicalRealtime.createDate = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYSHAREGGROUPDATE =
		new FinderPath(HistoricalRealtimeModelImpl.ENTITY_CACHE_ENABLED,
			HistoricalRealtimeModelImpl.FINDER_CACHE_ENABLED,
			HistoricalRealtimeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCompanySharegGroupDate",
			new String[] {
				Long.class.getName(), Long.class.getName(), Date.class.getName(),
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYSHAREGGROUPDATE =
		new FinderPath(HistoricalRealtimeModelImpl.ENTITY_CACHE_ENABLED,
			HistoricalRealtimeModelImpl.FINDER_CACHE_ENABLED,
			HistoricalRealtimeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCompanySharegGroupDate",
			new String[] {
				Long.class.getName(), Long.class.getName(), Date.class.getName(),
				Long.class.getName()
			},
			HistoricalRealtimeModelImpl.COMPANYID_COLUMN_BITMASK |
			HistoricalRealtimeModelImpl.SHAREID_COLUMN_BITMASK |
			HistoricalRealtimeModelImpl.CREATEDATE_COLUMN_BITMASK |
			HistoricalRealtimeModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYSHAREGGROUPDATE = new FinderPath(HistoricalRealtimeModelImpl.ENTITY_CACHE_ENABLED,
			HistoricalRealtimeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCompanySharegGroupDate",
			new String[] {
				Long.class.getName(), Long.class.getName(), Date.class.getName(),
				Long.class.getName()
			});

	/**
	 * Returns all the historical realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 * @param groupId the group ID
	 * @return the matching historical realtimes
	 */
	@Override
	public List<HistoricalRealtime> findByCompanySharegGroupDate(
		long companyId, long shareId, Date createDate, long groupId) {
		return findByCompanySharegGroupDate(companyId, shareId, createDate,
			groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the historical realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoricalRealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 * @param groupId the group ID
	 * @param start the lower bound of the range of historical realtimes
	 * @param end the upper bound of the range of historical realtimes (not inclusive)
	 * @return the range of matching historical realtimes
	 */
	@Override
	public List<HistoricalRealtime> findByCompanySharegGroupDate(
		long companyId, long shareId, Date createDate, long groupId, int start,
		int end) {
		return findByCompanySharegGroupDate(companyId, shareId, createDate,
			groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the historical realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoricalRealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 * @param groupId the group ID
	 * @param start the lower bound of the range of historical realtimes
	 * @param end the upper bound of the range of historical realtimes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching historical realtimes
	 */
	@Override
	public List<HistoricalRealtime> findByCompanySharegGroupDate(
		long companyId, long shareId, Date createDate, long groupId, int start,
		int end, OrderByComparator<HistoricalRealtime> orderByComparator) {
		return findByCompanySharegGroupDate(companyId, shareId, createDate,
			groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the historical realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoricalRealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 * @param groupId the group ID
	 * @param start the lower bound of the range of historical realtimes
	 * @param end the upper bound of the range of historical realtimes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching historical realtimes
	 */
	@Override
	public List<HistoricalRealtime> findByCompanySharegGroupDate(
		long companyId, long shareId, Date createDate, long groupId, int start,
		int end, OrderByComparator<HistoricalRealtime> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYSHAREGGROUPDATE;
			finderArgs = new Object[] { companyId, shareId, createDate, groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYSHAREGGROUPDATE;
			finderArgs = new Object[] {
					companyId, shareId, createDate, groupId,
					
					start, end, orderByComparator
				};
		}

		List<HistoricalRealtime> list = null;

		if (retrieveFromCache) {
			list = (List<HistoricalRealtime>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (HistoricalRealtime historicalRealtime : list) {
					if ((companyId != historicalRealtime.getCompanyId()) ||
							(shareId != historicalRealtime.getShareId()) ||
							!Objects.equals(createDate,
								historicalRealtime.getCreateDate()) ||
							(groupId != historicalRealtime.getGroupId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(6 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(6);
			}

			query.append(_SQL_SELECT_HISTORICALREALTIME_WHERE);

			query.append(_FINDER_COLUMN_COMPANYSHAREGGROUPDATE_COMPANYID_2);

			query.append(_FINDER_COLUMN_COMPANYSHAREGGROUPDATE_SHAREID_2);

			boolean bindCreateDate = false;

			if (createDate == null) {
				query.append(_FINDER_COLUMN_COMPANYSHAREGGROUPDATE_CREATEDATE_1);
			}
			else {
				bindCreateDate = true;

				query.append(_FINDER_COLUMN_COMPANYSHAREGGROUPDATE_CREATEDATE_2);
			}

			query.append(_FINDER_COLUMN_COMPANYSHAREGGROUPDATE_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(HistoricalRealtimeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(shareId);

				if (bindCreateDate) {
					qPos.add(new Timestamp(createDate.getTime()));
				}

				qPos.add(groupId);

				if (!pagination) {
					list = (List<HistoricalRealtime>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<HistoricalRealtime>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Returns the first historical realtime in the ordered set where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching historical realtime
	 * @throws NoSuchHistoricalRealtimeException if a matching historical realtime could not be found
	 */
	@Override
	public HistoricalRealtime findByCompanySharegGroupDate_First(
		long companyId, long shareId, Date createDate, long groupId,
		OrderByComparator<HistoricalRealtime> orderByComparator)
		throws NoSuchHistoricalRealtimeException {
		HistoricalRealtime historicalRealtime = fetchByCompanySharegGroupDate_First(companyId,
				shareId, createDate, groupId, orderByComparator);

		if (historicalRealtime != null) {
			return historicalRealtime;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", shareId=");
		msg.append(shareId);

		msg.append(", createDate=");
		msg.append(createDate);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchHistoricalRealtimeException(msg.toString());
	}

	/**
	 * Returns the first historical realtime in the ordered set where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching historical realtime, or <code>null</code> if a matching historical realtime could not be found
	 */
	@Override
	public HistoricalRealtime fetchByCompanySharegGroupDate_First(
		long companyId, long shareId, Date createDate, long groupId,
		OrderByComparator<HistoricalRealtime> orderByComparator) {
		List<HistoricalRealtime> list = findByCompanySharegGroupDate(companyId,
				shareId, createDate, groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last historical realtime in the ordered set where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching historical realtime
	 * @throws NoSuchHistoricalRealtimeException if a matching historical realtime could not be found
	 */
	@Override
	public HistoricalRealtime findByCompanySharegGroupDate_Last(
		long companyId, long shareId, Date createDate, long groupId,
		OrderByComparator<HistoricalRealtime> orderByComparator)
		throws NoSuchHistoricalRealtimeException {
		HistoricalRealtime historicalRealtime = fetchByCompanySharegGroupDate_Last(companyId,
				shareId, createDate, groupId, orderByComparator);

		if (historicalRealtime != null) {
			return historicalRealtime;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", shareId=");
		msg.append(shareId);

		msg.append(", createDate=");
		msg.append(createDate);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchHistoricalRealtimeException(msg.toString());
	}

	/**
	 * Returns the last historical realtime in the ordered set where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching historical realtime, or <code>null</code> if a matching historical realtime could not be found
	 */
	@Override
	public HistoricalRealtime fetchByCompanySharegGroupDate_Last(
		long companyId, long shareId, Date createDate, long groupId,
		OrderByComparator<HistoricalRealtime> orderByComparator) {
		int count = countByCompanySharegGroupDate(companyId, shareId,
				createDate, groupId);

		if (count == 0) {
			return null;
		}

		List<HistoricalRealtime> list = findByCompanySharegGroupDate(companyId,
				shareId, createDate, groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the historical realtimes before and after the current historical realtime in the ordered set where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63;.
	 *
	 * @param realtimeId the primary key of the current historical realtime
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next historical realtime
	 * @throws NoSuchHistoricalRealtimeException if a historical realtime with the primary key could not be found
	 */
	@Override
	public HistoricalRealtime[] findByCompanySharegGroupDate_PrevAndNext(
		long realtimeId, long companyId, long shareId, Date createDate,
		long groupId, OrderByComparator<HistoricalRealtime> orderByComparator)
		throws NoSuchHistoricalRealtimeException {
		HistoricalRealtime historicalRealtime = findByPrimaryKey(realtimeId);

		Session session = null;

		try {
			session = openSession();

			HistoricalRealtime[] array = new HistoricalRealtimeImpl[3];

			array[0] = getByCompanySharegGroupDate_PrevAndNext(session,
					historicalRealtime, companyId, shareId, createDate,
					groupId, orderByComparator, true);

			array[1] = historicalRealtime;

			array[2] = getByCompanySharegGroupDate_PrevAndNext(session,
					historicalRealtime, companyId, shareId, createDate,
					groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected HistoricalRealtime getByCompanySharegGroupDate_PrevAndNext(
		Session session, HistoricalRealtime historicalRealtime, long companyId,
		long shareId, Date createDate, long groupId,
		OrderByComparator<HistoricalRealtime> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(7 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(6);
		}

		query.append(_SQL_SELECT_HISTORICALREALTIME_WHERE);

		query.append(_FINDER_COLUMN_COMPANYSHAREGGROUPDATE_COMPANYID_2);

		query.append(_FINDER_COLUMN_COMPANYSHAREGGROUPDATE_SHAREID_2);

		boolean bindCreateDate = false;

		if (createDate == null) {
			query.append(_FINDER_COLUMN_COMPANYSHAREGGROUPDATE_CREATEDATE_1);
		}
		else {
			bindCreateDate = true;

			query.append(_FINDER_COLUMN_COMPANYSHAREGGROUPDATE_CREATEDATE_2);
		}

		query.append(_FINDER_COLUMN_COMPANYSHAREGGROUPDATE_GROUPID_2);

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
			query.append(HistoricalRealtimeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(shareId);

		if (bindCreateDate) {
			qPos.add(new Timestamp(createDate.getTime()));
		}

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(historicalRealtime);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<HistoricalRealtime> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the historical realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 * @param groupId the group ID
	 */
	@Override
	public void removeByCompanySharegGroupDate(long companyId, long shareId,
		Date createDate, long groupId) {
		for (HistoricalRealtime historicalRealtime : findByCompanySharegGroupDate(
				companyId, shareId, createDate, groupId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(historicalRealtime);
		}
	}

	/**
	 * Returns the number of historical realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 * @param groupId the group ID
	 * @return the number of matching historical realtimes
	 */
	@Override
	public int countByCompanySharegGroupDate(long companyId, long shareId,
		Date createDate, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYSHAREGGROUPDATE;

		Object[] finderArgs = new Object[] {
				companyId, shareId, createDate, groupId
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_HISTORICALREALTIME_WHERE);

			query.append(_FINDER_COLUMN_COMPANYSHAREGGROUPDATE_COMPANYID_2);

			query.append(_FINDER_COLUMN_COMPANYSHAREGGROUPDATE_SHAREID_2);

			boolean bindCreateDate = false;

			if (createDate == null) {
				query.append(_FINDER_COLUMN_COMPANYSHAREGGROUPDATE_CREATEDATE_1);
			}
			else {
				bindCreateDate = true;

				query.append(_FINDER_COLUMN_COMPANYSHAREGGROUPDATE_CREATEDATE_2);
			}

			query.append(_FINDER_COLUMN_COMPANYSHAREGGROUPDATE_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(shareId);

				if (bindCreateDate) {
					qPos.add(new Timestamp(createDate.getTime()));
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

	private static final String _FINDER_COLUMN_COMPANYSHAREGGROUPDATE_COMPANYID_2 =
		"historicalRealtime.companyId = ? AND ";
	private static final String _FINDER_COLUMN_COMPANYSHAREGGROUPDATE_SHAREID_2 = "historicalRealtime.shareId = ? AND ";
	private static final String _FINDER_COLUMN_COMPANYSHAREGGROUPDATE_CREATEDATE_1 =
		"historicalRealtime.createDate IS NULL AND ";
	private static final String _FINDER_COLUMN_COMPANYSHAREGGROUPDATE_CREATEDATE_2 =
		"historicalRealtime.createDate = ? AND ";
	private static final String _FINDER_COLUMN_COMPANYSHAREGGROUPDATE_GROUPID_2 = "historicalRealtime.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYSHAREGGROUPCLOSE =
		new FinderPath(HistoricalRealtimeModelImpl.ENTITY_CACHE_ENABLED,
			HistoricalRealtimeModelImpl.FINDER_CACHE_ENABLED,
			HistoricalRealtimeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCompanySharegGroupClose",
			new String[] {
				Long.class.getName(), Long.class.getName(), Date.class.getName(),
				Long.class.getName(), Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYSHAREGGROUPCLOSE =
		new FinderPath(HistoricalRealtimeModelImpl.ENTITY_CACHE_ENABLED,
			HistoricalRealtimeModelImpl.FINDER_CACHE_ENABLED,
			HistoricalRealtimeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCompanySharegGroupClose",
			new String[] {
				Long.class.getName(), Long.class.getName(), Date.class.getName(),
				Long.class.getName(), Boolean.class.getName()
			},
			HistoricalRealtimeModelImpl.COMPANYID_COLUMN_BITMASK |
			HistoricalRealtimeModelImpl.SHAREID_COLUMN_BITMASK |
			HistoricalRealtimeModelImpl.CREATEDATE_COLUMN_BITMASK |
			HistoricalRealtimeModelImpl.GROUPID_COLUMN_BITMASK |
			HistoricalRealtimeModelImpl.CLOSEPRICE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYSHAREGGROUPCLOSE = new FinderPath(HistoricalRealtimeModelImpl.ENTITY_CACHE_ENABLED,
			HistoricalRealtimeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCompanySharegGroupClose",
			new String[] {
				Long.class.getName(), Long.class.getName(), Date.class.getName(),
				Long.class.getName(), Boolean.class.getName()
			});

	/**
	 * Returns all the historical realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63; and closeprice = &#63;.
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 * @param groupId the group ID
	 * @param closeprice the closeprice
	 * @return the matching historical realtimes
	 */
	@Override
	public List<HistoricalRealtime> findByCompanySharegGroupClose(
		long companyId, long shareId, Date createDate, long groupId,
		boolean closeprice) {
		return findByCompanySharegGroupClose(companyId, shareId, createDate,
			groupId, closeprice, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the historical realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63; and closeprice = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoricalRealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 * @param groupId the group ID
	 * @param closeprice the closeprice
	 * @param start the lower bound of the range of historical realtimes
	 * @param end the upper bound of the range of historical realtimes (not inclusive)
	 * @return the range of matching historical realtimes
	 */
	@Override
	public List<HistoricalRealtime> findByCompanySharegGroupClose(
		long companyId, long shareId, Date createDate, long groupId,
		boolean closeprice, int start, int end) {
		return findByCompanySharegGroupClose(companyId, shareId, createDate,
			groupId, closeprice, start, end, null);
	}

	/**
	 * Returns an ordered range of all the historical realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63; and closeprice = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoricalRealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 * @param groupId the group ID
	 * @param closeprice the closeprice
	 * @param start the lower bound of the range of historical realtimes
	 * @param end the upper bound of the range of historical realtimes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching historical realtimes
	 */
	@Override
	public List<HistoricalRealtime> findByCompanySharegGroupClose(
		long companyId, long shareId, Date createDate, long groupId,
		boolean closeprice, int start, int end,
		OrderByComparator<HistoricalRealtime> orderByComparator) {
		return findByCompanySharegGroupClose(companyId, shareId, createDate,
			groupId, closeprice, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the historical realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63; and closeprice = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoricalRealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 * @param groupId the group ID
	 * @param closeprice the closeprice
	 * @param start the lower bound of the range of historical realtimes
	 * @param end the upper bound of the range of historical realtimes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching historical realtimes
	 */
	@Override
	public List<HistoricalRealtime> findByCompanySharegGroupClose(
		long companyId, long shareId, Date createDate, long groupId,
		boolean closeprice, int start, int end,
		OrderByComparator<HistoricalRealtime> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYSHAREGGROUPCLOSE;
			finderArgs = new Object[] {
					companyId, shareId, createDate, groupId, closeprice
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYSHAREGGROUPCLOSE;
			finderArgs = new Object[] {
					companyId, shareId, createDate, groupId, closeprice,
					
					start, end, orderByComparator
				};
		}

		List<HistoricalRealtime> list = null;

		if (retrieveFromCache) {
			list = (List<HistoricalRealtime>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (HistoricalRealtime historicalRealtime : list) {
					if ((companyId != historicalRealtime.getCompanyId()) ||
							(shareId != historicalRealtime.getShareId()) ||
							!Objects.equals(createDate,
								historicalRealtime.getCreateDate()) ||
							(groupId != historicalRealtime.getGroupId()) ||
							(closeprice != historicalRealtime.getCloseprice())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(7 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(7);
			}

			query.append(_SQL_SELECT_HISTORICALREALTIME_WHERE);

			query.append(_FINDER_COLUMN_COMPANYSHAREGGROUPCLOSE_COMPANYID_2);

			query.append(_FINDER_COLUMN_COMPANYSHAREGGROUPCLOSE_SHAREID_2);

			boolean bindCreateDate = false;

			if (createDate == null) {
				query.append(_FINDER_COLUMN_COMPANYSHAREGGROUPCLOSE_CREATEDATE_1);
			}
			else {
				bindCreateDate = true;

				query.append(_FINDER_COLUMN_COMPANYSHAREGGROUPCLOSE_CREATEDATE_2);
			}

			query.append(_FINDER_COLUMN_COMPANYSHAREGGROUPCLOSE_GROUPID_2);

			query.append(_FINDER_COLUMN_COMPANYSHAREGGROUPCLOSE_CLOSEPRICE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(HistoricalRealtimeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(shareId);

				if (bindCreateDate) {
					qPos.add(new Timestamp(createDate.getTime()));
				}

				qPos.add(groupId);

				qPos.add(closeprice);

				if (!pagination) {
					list = (List<HistoricalRealtime>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<HistoricalRealtime>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Returns the first historical realtime in the ordered set where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63; and closeprice = &#63;.
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 * @param groupId the group ID
	 * @param closeprice the closeprice
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching historical realtime
	 * @throws NoSuchHistoricalRealtimeException if a matching historical realtime could not be found
	 */
	@Override
	public HistoricalRealtime findByCompanySharegGroupClose_First(
		long companyId, long shareId, Date createDate, long groupId,
		boolean closeprice,
		OrderByComparator<HistoricalRealtime> orderByComparator)
		throws NoSuchHistoricalRealtimeException {
		HistoricalRealtime historicalRealtime = fetchByCompanySharegGroupClose_First(companyId,
				shareId, createDate, groupId, closeprice, orderByComparator);

		if (historicalRealtime != null) {
			return historicalRealtime;
		}

		StringBundler msg = new StringBundler(12);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", shareId=");
		msg.append(shareId);

		msg.append(", createDate=");
		msg.append(createDate);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(", closeprice=");
		msg.append(closeprice);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchHistoricalRealtimeException(msg.toString());
	}

	/**
	 * Returns the first historical realtime in the ordered set where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63; and closeprice = &#63;.
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 * @param groupId the group ID
	 * @param closeprice the closeprice
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching historical realtime, or <code>null</code> if a matching historical realtime could not be found
	 */
	@Override
	public HistoricalRealtime fetchByCompanySharegGroupClose_First(
		long companyId, long shareId, Date createDate, long groupId,
		boolean closeprice,
		OrderByComparator<HistoricalRealtime> orderByComparator) {
		List<HistoricalRealtime> list = findByCompanySharegGroupClose(companyId,
				shareId, createDate, groupId, closeprice, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last historical realtime in the ordered set where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63; and closeprice = &#63;.
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 * @param groupId the group ID
	 * @param closeprice the closeprice
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching historical realtime
	 * @throws NoSuchHistoricalRealtimeException if a matching historical realtime could not be found
	 */
	@Override
	public HistoricalRealtime findByCompanySharegGroupClose_Last(
		long companyId, long shareId, Date createDate, long groupId,
		boolean closeprice,
		OrderByComparator<HistoricalRealtime> orderByComparator)
		throws NoSuchHistoricalRealtimeException {
		HistoricalRealtime historicalRealtime = fetchByCompanySharegGroupClose_Last(companyId,
				shareId, createDate, groupId, closeprice, orderByComparator);

		if (historicalRealtime != null) {
			return historicalRealtime;
		}

		StringBundler msg = new StringBundler(12);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", shareId=");
		msg.append(shareId);

		msg.append(", createDate=");
		msg.append(createDate);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(", closeprice=");
		msg.append(closeprice);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchHistoricalRealtimeException(msg.toString());
	}

	/**
	 * Returns the last historical realtime in the ordered set where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63; and closeprice = &#63;.
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 * @param groupId the group ID
	 * @param closeprice the closeprice
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching historical realtime, or <code>null</code> if a matching historical realtime could not be found
	 */
	@Override
	public HistoricalRealtime fetchByCompanySharegGroupClose_Last(
		long companyId, long shareId, Date createDate, long groupId,
		boolean closeprice,
		OrderByComparator<HistoricalRealtime> orderByComparator) {
		int count = countByCompanySharegGroupClose(companyId, shareId,
				createDate, groupId, closeprice);

		if (count == 0) {
			return null;
		}

		List<HistoricalRealtime> list = findByCompanySharegGroupClose(companyId,
				shareId, createDate, groupId, closeprice, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the historical realtimes before and after the current historical realtime in the ordered set where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63; and closeprice = &#63;.
	 *
	 * @param realtimeId the primary key of the current historical realtime
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 * @param groupId the group ID
	 * @param closeprice the closeprice
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next historical realtime
	 * @throws NoSuchHistoricalRealtimeException if a historical realtime with the primary key could not be found
	 */
	@Override
	public HistoricalRealtime[] findByCompanySharegGroupClose_PrevAndNext(
		long realtimeId, long companyId, long shareId, Date createDate,
		long groupId, boolean closeprice,
		OrderByComparator<HistoricalRealtime> orderByComparator)
		throws NoSuchHistoricalRealtimeException {
		HistoricalRealtime historicalRealtime = findByPrimaryKey(realtimeId);

		Session session = null;

		try {
			session = openSession();

			HistoricalRealtime[] array = new HistoricalRealtimeImpl[3];

			array[0] = getByCompanySharegGroupClose_PrevAndNext(session,
					historicalRealtime, companyId, shareId, createDate,
					groupId, closeprice, orderByComparator, true);

			array[1] = historicalRealtime;

			array[2] = getByCompanySharegGroupClose_PrevAndNext(session,
					historicalRealtime, companyId, shareId, createDate,
					groupId, closeprice, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected HistoricalRealtime getByCompanySharegGroupClose_PrevAndNext(
		Session session, HistoricalRealtime historicalRealtime, long companyId,
		long shareId, Date createDate, long groupId, boolean closeprice,
		OrderByComparator<HistoricalRealtime> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(8 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(7);
		}

		query.append(_SQL_SELECT_HISTORICALREALTIME_WHERE);

		query.append(_FINDER_COLUMN_COMPANYSHAREGGROUPCLOSE_COMPANYID_2);

		query.append(_FINDER_COLUMN_COMPANYSHAREGGROUPCLOSE_SHAREID_2);

		boolean bindCreateDate = false;

		if (createDate == null) {
			query.append(_FINDER_COLUMN_COMPANYSHAREGGROUPCLOSE_CREATEDATE_1);
		}
		else {
			bindCreateDate = true;

			query.append(_FINDER_COLUMN_COMPANYSHAREGGROUPCLOSE_CREATEDATE_2);
		}

		query.append(_FINDER_COLUMN_COMPANYSHAREGGROUPCLOSE_GROUPID_2);

		query.append(_FINDER_COLUMN_COMPANYSHAREGGROUPCLOSE_CLOSEPRICE_2);

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
			query.append(HistoricalRealtimeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(shareId);

		if (bindCreateDate) {
			qPos.add(new Timestamp(createDate.getTime()));
		}

		qPos.add(groupId);

		qPos.add(closeprice);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(historicalRealtime);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<HistoricalRealtime> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the historical realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63; and closeprice = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 * @param groupId the group ID
	 * @param closeprice the closeprice
	 */
	@Override
	public void removeByCompanySharegGroupClose(long companyId, long shareId,
		Date createDate, long groupId, boolean closeprice) {
		for (HistoricalRealtime historicalRealtime : findByCompanySharegGroupClose(
				companyId, shareId, createDate, groupId, closeprice,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(historicalRealtime);
		}
	}

	/**
	 * Returns the number of historical realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63; and closeprice = &#63;.
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 * @param groupId the group ID
	 * @param closeprice the closeprice
	 * @return the number of matching historical realtimes
	 */
	@Override
	public int countByCompanySharegGroupClose(long companyId, long shareId,
		Date createDate, long groupId, boolean closeprice) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYSHAREGGROUPCLOSE;

		Object[] finderArgs = new Object[] {
				companyId, shareId, createDate, groupId, closeprice
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_COUNT_HISTORICALREALTIME_WHERE);

			query.append(_FINDER_COLUMN_COMPANYSHAREGGROUPCLOSE_COMPANYID_2);

			query.append(_FINDER_COLUMN_COMPANYSHAREGGROUPCLOSE_SHAREID_2);

			boolean bindCreateDate = false;

			if (createDate == null) {
				query.append(_FINDER_COLUMN_COMPANYSHAREGGROUPCLOSE_CREATEDATE_1);
			}
			else {
				bindCreateDate = true;

				query.append(_FINDER_COLUMN_COMPANYSHAREGGROUPCLOSE_CREATEDATE_2);
			}

			query.append(_FINDER_COLUMN_COMPANYSHAREGGROUPCLOSE_GROUPID_2);

			query.append(_FINDER_COLUMN_COMPANYSHAREGGROUPCLOSE_CLOSEPRICE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(shareId);

				if (bindCreateDate) {
					qPos.add(new Timestamp(createDate.getTime()));
				}

				qPos.add(groupId);

				qPos.add(closeprice);

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

	private static final String _FINDER_COLUMN_COMPANYSHAREGGROUPCLOSE_COMPANYID_2 =
		"historicalRealtime.companyId = ? AND ";
	private static final String _FINDER_COLUMN_COMPANYSHAREGGROUPCLOSE_SHAREID_2 =
		"historicalRealtime.shareId = ? AND ";
	private static final String _FINDER_COLUMN_COMPANYSHAREGGROUPCLOSE_CREATEDATE_1 =
		"historicalRealtime.createDate IS NULL AND ";
	private static final String _FINDER_COLUMN_COMPANYSHAREGGROUPCLOSE_CREATEDATE_2 =
		"historicalRealtime.createDate = ? AND ";
	private static final String _FINDER_COLUMN_COMPANYSHAREGGROUPCLOSE_GROUPID_2 =
		"historicalRealtime.groupId = ? AND ";
	private static final String _FINDER_COLUMN_COMPANYSHAREGGROUPCLOSE_CLOSEPRICE_2 =
		"historicalRealtime.closeprice = ?";

	public HistoricalRealtimePersistenceImpl() {
		setModelClass(HistoricalRealtime.class);
	}

	/**
	 * Caches the historical realtime in the entity cache if it is enabled.
	 *
	 * @param historicalRealtime the historical realtime
	 */
	@Override
	public void cacheResult(HistoricalRealtime historicalRealtime) {
		entityCache.putResult(HistoricalRealtimeModelImpl.ENTITY_CACHE_ENABLED,
			HistoricalRealtimeImpl.class, historicalRealtime.getPrimaryKey(),
			historicalRealtime);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				historicalRealtime.getUuid(), historicalRealtime.getGroupId()
			}, historicalRealtime);

		historicalRealtime.resetOriginalValues();
	}

	/**
	 * Caches the historical realtimes in the entity cache if it is enabled.
	 *
	 * @param historicalRealtimes the historical realtimes
	 */
	@Override
	public void cacheResult(List<HistoricalRealtime> historicalRealtimes) {
		for (HistoricalRealtime historicalRealtime : historicalRealtimes) {
			if (entityCache.getResult(
						HistoricalRealtimeModelImpl.ENTITY_CACHE_ENABLED,
						HistoricalRealtimeImpl.class,
						historicalRealtime.getPrimaryKey()) == null) {
				cacheResult(historicalRealtime);
			}
			else {
				historicalRealtime.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all historical realtimes.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(HistoricalRealtimeImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the historical realtime.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HistoricalRealtime historicalRealtime) {
		entityCache.removeResult(HistoricalRealtimeModelImpl.ENTITY_CACHE_ENABLED,
			HistoricalRealtimeImpl.class, historicalRealtime.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((HistoricalRealtimeModelImpl)historicalRealtime,
			true);
	}

	@Override
	public void clearCache(List<HistoricalRealtime> historicalRealtimes) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (HistoricalRealtime historicalRealtime : historicalRealtimes) {
			entityCache.removeResult(HistoricalRealtimeModelImpl.ENTITY_CACHE_ENABLED,
				HistoricalRealtimeImpl.class, historicalRealtime.getPrimaryKey());

			clearUniqueFindersCache((HistoricalRealtimeModelImpl)historicalRealtime,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		HistoricalRealtimeModelImpl historicalRealtimeModelImpl) {
		Object[] args = new Object[] {
				historicalRealtimeModelImpl.getUuid(),
				historicalRealtimeModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			historicalRealtimeModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		HistoricalRealtimeModelImpl historicalRealtimeModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					historicalRealtimeModelImpl.getUuid(),
					historicalRealtimeModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((historicalRealtimeModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					historicalRealtimeModelImpl.getOriginalUuid(),
					historicalRealtimeModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new historical realtime with the primary key. Does not add the historical realtime to the database.
	 *
	 * @param realtimeId the primary key for the new historical realtime
	 * @return the new historical realtime
	 */
	@Override
	public HistoricalRealtime create(long realtimeId) {
		HistoricalRealtime historicalRealtime = new HistoricalRealtimeImpl();

		historicalRealtime.setNew(true);
		historicalRealtime.setPrimaryKey(realtimeId);

		String uuid = PortalUUIDUtil.generate();

		historicalRealtime.setUuid(uuid);

		historicalRealtime.setCompanyId(companyProvider.getCompanyId());

		return historicalRealtime;
	}

	/**
	 * Removes the historical realtime with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param realtimeId the primary key of the historical realtime
	 * @return the historical realtime that was removed
	 * @throws NoSuchHistoricalRealtimeException if a historical realtime with the primary key could not be found
	 */
	@Override
	public HistoricalRealtime remove(long realtimeId)
		throws NoSuchHistoricalRealtimeException {
		return remove((Serializable)realtimeId);
	}

	/**
	 * Removes the historical realtime with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the historical realtime
	 * @return the historical realtime that was removed
	 * @throws NoSuchHistoricalRealtimeException if a historical realtime with the primary key could not be found
	 */
	@Override
	public HistoricalRealtime remove(Serializable primaryKey)
		throws NoSuchHistoricalRealtimeException {
		Session session = null;

		try {
			session = openSession();

			HistoricalRealtime historicalRealtime = (HistoricalRealtime)session.get(HistoricalRealtimeImpl.class,
					primaryKey);

			if (historicalRealtime == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchHistoricalRealtimeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(historicalRealtime);
		}
		catch (NoSuchHistoricalRealtimeException nsee) {
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
	protected HistoricalRealtime removeImpl(
		HistoricalRealtime historicalRealtime) {
		historicalRealtime = toUnwrappedModel(historicalRealtime);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(historicalRealtime)) {
				historicalRealtime = (HistoricalRealtime)session.get(HistoricalRealtimeImpl.class,
						historicalRealtime.getPrimaryKeyObj());
			}

			if (historicalRealtime != null) {
				session.delete(historicalRealtime);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (historicalRealtime != null) {
			clearCache(historicalRealtime);
		}

		return historicalRealtime;
	}

	@Override
	public HistoricalRealtime updateImpl(HistoricalRealtime historicalRealtime) {
		historicalRealtime = toUnwrappedModel(historicalRealtime);

		boolean isNew = historicalRealtime.isNew();

		HistoricalRealtimeModelImpl historicalRealtimeModelImpl = (HistoricalRealtimeModelImpl)historicalRealtime;

		if (Validator.isNull(historicalRealtime.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			historicalRealtime.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (historicalRealtime.getCreateDate() == null)) {
			if (serviceContext == null) {
				historicalRealtime.setCreateDate(now);
			}
			else {
				historicalRealtime.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!historicalRealtimeModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				historicalRealtime.setModifiedDate(now);
			}
			else {
				historicalRealtime.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (historicalRealtime.isNew()) {
				session.save(historicalRealtime);

				historicalRealtime.setNew(false);
			}
			else {
				historicalRealtime = (HistoricalRealtime)session.merge(historicalRealtime);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !HistoricalRealtimeModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((historicalRealtimeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						historicalRealtimeModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { historicalRealtimeModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((historicalRealtimeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						historicalRealtimeModelImpl.getOriginalUuid(),
						historicalRealtimeModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						historicalRealtimeModelImpl.getUuid(),
						historicalRealtimeModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((historicalRealtimeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DATE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						historicalRealtimeModelImpl.getOriginalCreateDate()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_DATE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DATE,
					args);

				args = new Object[] { historicalRealtimeModelImpl.getCreateDate() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_DATE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DATE,
					args);
			}

			if ((historicalRealtimeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYSHARE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						historicalRealtimeModelImpl.getOriginalCompanyId(),
						historicalRealtimeModelImpl.getOriginalShareId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYSHARE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYSHARE,
					args);

				args = new Object[] {
						historicalRealtimeModelImpl.getCompanyId(),
						historicalRealtimeModelImpl.getShareId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYSHARE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYSHARE,
					args);
			}

			if ((historicalRealtimeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYSHAREDATE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						historicalRealtimeModelImpl.getOriginalCompanyId(),
						historicalRealtimeModelImpl.getOriginalShareId(),
						historicalRealtimeModelImpl.getOriginalCreateDate()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYSHAREDATE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYSHAREDATE,
					args);

				args = new Object[] {
						historicalRealtimeModelImpl.getCompanyId(),
						historicalRealtimeModelImpl.getShareId(),
						historicalRealtimeModelImpl.getCreateDate()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYSHAREDATE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYSHAREDATE,
					args);
			}

			if ((historicalRealtimeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYSHAREGGROUPDATE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						historicalRealtimeModelImpl.getOriginalCompanyId(),
						historicalRealtimeModelImpl.getOriginalShareId(),
						historicalRealtimeModelImpl.getOriginalCreateDate(),
						historicalRealtimeModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYSHAREGGROUPDATE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYSHAREGGROUPDATE,
					args);

				args = new Object[] {
						historicalRealtimeModelImpl.getCompanyId(),
						historicalRealtimeModelImpl.getShareId(),
						historicalRealtimeModelImpl.getCreateDate(),
						historicalRealtimeModelImpl.getGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYSHAREGGROUPDATE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYSHAREGGROUPDATE,
					args);
			}

			if ((historicalRealtimeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYSHAREGGROUPCLOSE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						historicalRealtimeModelImpl.getOriginalCompanyId(),
						historicalRealtimeModelImpl.getOriginalShareId(),
						historicalRealtimeModelImpl.getOriginalCreateDate(),
						historicalRealtimeModelImpl.getOriginalGroupId(),
						historicalRealtimeModelImpl.getOriginalCloseprice()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYSHAREGGROUPCLOSE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYSHAREGGROUPCLOSE,
					args);

				args = new Object[] {
						historicalRealtimeModelImpl.getCompanyId(),
						historicalRealtimeModelImpl.getShareId(),
						historicalRealtimeModelImpl.getCreateDate(),
						historicalRealtimeModelImpl.getGroupId(),
						historicalRealtimeModelImpl.getCloseprice()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYSHAREGGROUPCLOSE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYSHAREGGROUPCLOSE,
					args);
			}
		}

		entityCache.putResult(HistoricalRealtimeModelImpl.ENTITY_CACHE_ENABLED,
			HistoricalRealtimeImpl.class, historicalRealtime.getPrimaryKey(),
			historicalRealtime, false);

		clearUniqueFindersCache(historicalRealtimeModelImpl, false);
		cacheUniqueFindersCache(historicalRealtimeModelImpl);

		historicalRealtime.resetOriginalValues();

		return historicalRealtime;
	}

	protected HistoricalRealtime toUnwrappedModel(
		HistoricalRealtime historicalRealtime) {
		if (historicalRealtime instanceof HistoricalRealtimeImpl) {
			return historicalRealtime;
		}

		HistoricalRealtimeImpl historicalRealtimeImpl = new HistoricalRealtimeImpl();

		historicalRealtimeImpl.setNew(historicalRealtime.isNew());
		historicalRealtimeImpl.setPrimaryKey(historicalRealtime.getPrimaryKey());

		historicalRealtimeImpl.setUuid(historicalRealtime.getUuid());
		historicalRealtimeImpl.setRealtimeId(historicalRealtime.getRealtimeId());
		historicalRealtimeImpl.setGroupId(historicalRealtime.getGroupId());
		historicalRealtimeImpl.setCompanyId(historicalRealtime.getCompanyId());
		historicalRealtimeImpl.setShareId(historicalRealtime.getShareId());
		historicalRealtimeImpl.setValue(historicalRealtime.getValue());
		historicalRealtimeImpl.setCreateDate(historicalRealtime.getCreateDate());
		historicalRealtimeImpl.setModifiedDate(historicalRealtime.getModifiedDate());
		historicalRealtimeImpl.setMax_value(historicalRealtime.getMax_value());
		historicalRealtimeImpl.setMin_value(historicalRealtime.getMin_value());
		historicalRealtimeImpl.setVolume(historicalRealtime.getVolume());
		historicalRealtimeImpl.setAvg_volume(historicalRealtime.getAvg_volume());
		historicalRealtimeImpl.setCloseprice(historicalRealtime.isCloseprice());

		return historicalRealtimeImpl;
	}

	/**
	 * Returns the historical realtime with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the historical realtime
	 * @return the historical realtime
	 * @throws NoSuchHistoricalRealtimeException if a historical realtime with the primary key could not be found
	 */
	@Override
	public HistoricalRealtime findByPrimaryKey(Serializable primaryKey)
		throws NoSuchHistoricalRealtimeException {
		HistoricalRealtime historicalRealtime = fetchByPrimaryKey(primaryKey);

		if (historicalRealtime == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchHistoricalRealtimeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return historicalRealtime;
	}

	/**
	 * Returns the historical realtime with the primary key or throws a {@link NoSuchHistoricalRealtimeException} if it could not be found.
	 *
	 * @param realtimeId the primary key of the historical realtime
	 * @return the historical realtime
	 * @throws NoSuchHistoricalRealtimeException if a historical realtime with the primary key could not be found
	 */
	@Override
	public HistoricalRealtime findByPrimaryKey(long realtimeId)
		throws NoSuchHistoricalRealtimeException {
		return findByPrimaryKey((Serializable)realtimeId);
	}

	/**
	 * Returns the historical realtime with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the historical realtime
	 * @return the historical realtime, or <code>null</code> if a historical realtime with the primary key could not be found
	 */
	@Override
	public HistoricalRealtime fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(HistoricalRealtimeModelImpl.ENTITY_CACHE_ENABLED,
				HistoricalRealtimeImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		HistoricalRealtime historicalRealtime = (HistoricalRealtime)serializable;

		if (historicalRealtime == null) {
			Session session = null;

			try {
				session = openSession();

				historicalRealtime = (HistoricalRealtime)session.get(HistoricalRealtimeImpl.class,
						primaryKey);

				if (historicalRealtime != null) {
					cacheResult(historicalRealtime);
				}
				else {
					entityCache.putResult(HistoricalRealtimeModelImpl.ENTITY_CACHE_ENABLED,
						HistoricalRealtimeImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(HistoricalRealtimeModelImpl.ENTITY_CACHE_ENABLED,
					HistoricalRealtimeImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return historicalRealtime;
	}

	/**
	 * Returns the historical realtime with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param realtimeId the primary key of the historical realtime
	 * @return the historical realtime, or <code>null</code> if a historical realtime with the primary key could not be found
	 */
	@Override
	public HistoricalRealtime fetchByPrimaryKey(long realtimeId) {
		return fetchByPrimaryKey((Serializable)realtimeId);
	}

	@Override
	public Map<Serializable, HistoricalRealtime> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, HistoricalRealtime> map = new HashMap<Serializable, HistoricalRealtime>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			HistoricalRealtime historicalRealtime = fetchByPrimaryKey(primaryKey);

			if (historicalRealtime != null) {
				map.put(primaryKey, historicalRealtime);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(HistoricalRealtimeModelImpl.ENTITY_CACHE_ENABLED,
					HistoricalRealtimeImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (HistoricalRealtime)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_HISTORICALREALTIME_WHERE_PKS_IN);

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

			for (HistoricalRealtime historicalRealtime : (List<HistoricalRealtime>)q.list()) {
				map.put(historicalRealtime.getPrimaryKeyObj(),
					historicalRealtime);

				cacheResult(historicalRealtime);

				uncachedPrimaryKeys.remove(historicalRealtime.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(HistoricalRealtimeModelImpl.ENTITY_CACHE_ENABLED,
					HistoricalRealtimeImpl.class, primaryKey, nullModel);
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
	 * Returns all the historical realtimes.
	 *
	 * @return the historical realtimes
	 */
	@Override
	public List<HistoricalRealtime> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the historical realtimes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoricalRealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of historical realtimes
	 * @param end the upper bound of the range of historical realtimes (not inclusive)
	 * @return the range of historical realtimes
	 */
	@Override
	public List<HistoricalRealtime> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the historical realtimes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoricalRealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of historical realtimes
	 * @param end the upper bound of the range of historical realtimes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of historical realtimes
	 */
	@Override
	public List<HistoricalRealtime> findAll(int start, int end,
		OrderByComparator<HistoricalRealtime> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the historical realtimes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoricalRealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of historical realtimes
	 * @param end the upper bound of the range of historical realtimes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of historical realtimes
	 */
	@Override
	public List<HistoricalRealtime> findAll(int start, int end,
		OrderByComparator<HistoricalRealtime> orderByComparator,
		boolean retrieveFromCache) {
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

		List<HistoricalRealtime> list = null;

		if (retrieveFromCache) {
			list = (List<HistoricalRealtime>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_HISTORICALREALTIME);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HISTORICALREALTIME;

				if (pagination) {
					sql = sql.concat(HistoricalRealtimeModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<HistoricalRealtime>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<HistoricalRealtime>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Removes all the historical realtimes from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (HistoricalRealtime historicalRealtime : findAll()) {
			remove(historicalRealtime);
		}
	}

	/**
	 * Returns the number of historical realtimes.
	 *
	 * @return the number of historical realtimes
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_HISTORICALREALTIME);

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
		return HistoricalRealtimeModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the historical realtime persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(HistoricalRealtimeImpl.class.getName());
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
	private static final String _SQL_SELECT_HISTORICALREALTIME = "SELECT historicalRealtime FROM HistoricalRealtime historicalRealtime";
	private static final String _SQL_SELECT_HISTORICALREALTIME_WHERE_PKS_IN = "SELECT historicalRealtime FROM HistoricalRealtime historicalRealtime WHERE realtimeId IN (";
	private static final String _SQL_SELECT_HISTORICALREALTIME_WHERE = "SELECT historicalRealtime FROM HistoricalRealtime historicalRealtime WHERE ";
	private static final String _SQL_COUNT_HISTORICALREALTIME = "SELECT COUNT(historicalRealtime) FROM HistoricalRealtime historicalRealtime";
	private static final String _SQL_COUNT_HISTORICALREALTIME_WHERE = "SELECT COUNT(historicalRealtime) FROM HistoricalRealtime historicalRealtime WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "historicalRealtime.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HistoricalRealtime exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No HistoricalRealtime exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(HistoricalRealtimePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}