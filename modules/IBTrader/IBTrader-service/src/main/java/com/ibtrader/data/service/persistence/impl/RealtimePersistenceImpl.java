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

import com.ibtrader.data.exception.NoSuchRealtimeException;
import com.ibtrader.data.model.Realtime;
import com.ibtrader.data.model.impl.RealtimeImpl;
import com.ibtrader.data.model.impl.RealtimeModelImpl;
import com.ibtrader.data.service.persistence.RealtimePersistence;

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
 * The persistence implementation for the realtime service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RealtimePersistence
 * @see com.ibtrader.data.service.persistence.RealtimeUtil
 * @generated
 */
@ProviderType
public class RealtimePersistenceImpl extends BasePersistenceImpl<Realtime>
	implements RealtimePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link RealtimeUtil} to access the realtime persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = RealtimeImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(RealtimeModelImpl.ENTITY_CACHE_ENABLED,
			RealtimeModelImpl.FINDER_CACHE_ENABLED, RealtimeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(RealtimeModelImpl.ENTITY_CACHE_ENABLED,
			RealtimeModelImpl.FINDER_CACHE_ENABLED, RealtimeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(RealtimeModelImpl.ENTITY_CACHE_ENABLED,
			RealtimeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(RealtimeModelImpl.ENTITY_CACHE_ENABLED,
			RealtimeModelImpl.FINDER_CACHE_ENABLED, RealtimeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(RealtimeModelImpl.ENTITY_CACHE_ENABLED,
			RealtimeModelImpl.FINDER_CACHE_ENABLED, RealtimeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			RealtimeModelImpl.UUID_COLUMN_BITMASK |
			RealtimeModelImpl.SHAREID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(RealtimeModelImpl.ENTITY_CACHE_ENABLED,
			RealtimeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the realtimes where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching realtimes
	 */
	@Override
	public List<Realtime> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the realtimes where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of realtimes
	 * @param end the upper bound of the range of realtimes (not inclusive)
	 * @return the range of matching realtimes
	 */
	@Override
	public List<Realtime> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the realtimes where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of realtimes
	 * @param end the upper bound of the range of realtimes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching realtimes
	 */
	@Override
	public List<Realtime> findByUuid(String uuid, int start, int end,
		OrderByComparator<Realtime> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the realtimes where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of realtimes
	 * @param end the upper bound of the range of realtimes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching realtimes
	 */
	@Override
	public List<Realtime> findByUuid(String uuid, int start, int end,
		OrderByComparator<Realtime> orderByComparator, boolean retrieveFromCache) {
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

		List<Realtime> list = null;

		if (retrieveFromCache) {
			list = (List<Realtime>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Realtime realtime : list) {
					if (!Objects.equals(uuid, realtime.getUuid())) {
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

			query.append(_SQL_SELECT_REALTIME_WHERE);

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
				query.append(RealtimeModelImpl.ORDER_BY_JPQL);
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
					list = (List<Realtime>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Realtime>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first realtime in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching realtime
	 * @throws NoSuchRealtimeException if a matching realtime could not be found
	 */
	@Override
	public Realtime findByUuid_First(String uuid,
		OrderByComparator<Realtime> orderByComparator)
		throws NoSuchRealtimeException {
		Realtime realtime = fetchByUuid_First(uuid, orderByComparator);

		if (realtime != null) {
			return realtime;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRealtimeException(msg.toString());
	}

	/**
	 * Returns the first realtime in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching realtime, or <code>null</code> if a matching realtime could not be found
	 */
	@Override
	public Realtime fetchByUuid_First(String uuid,
		OrderByComparator<Realtime> orderByComparator) {
		List<Realtime> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last realtime in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching realtime
	 * @throws NoSuchRealtimeException if a matching realtime could not be found
	 */
	@Override
	public Realtime findByUuid_Last(String uuid,
		OrderByComparator<Realtime> orderByComparator)
		throws NoSuchRealtimeException {
		Realtime realtime = fetchByUuid_Last(uuid, orderByComparator);

		if (realtime != null) {
			return realtime;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRealtimeException(msg.toString());
	}

	/**
	 * Returns the last realtime in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching realtime, or <code>null</code> if a matching realtime could not be found
	 */
	@Override
	public Realtime fetchByUuid_Last(String uuid,
		OrderByComparator<Realtime> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Realtime> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the realtimes before and after the current realtime in the ordered set where uuid = &#63;.
	 *
	 * @param realtimeId the primary key of the current realtime
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next realtime
	 * @throws NoSuchRealtimeException if a realtime with the primary key could not be found
	 */
	@Override
	public Realtime[] findByUuid_PrevAndNext(long realtimeId, String uuid,
		OrderByComparator<Realtime> orderByComparator)
		throws NoSuchRealtimeException {
		Realtime realtime = findByPrimaryKey(realtimeId);

		Session session = null;

		try {
			session = openSession();

			Realtime[] array = new RealtimeImpl[3];

			array[0] = getByUuid_PrevAndNext(session, realtime, uuid,
					orderByComparator, true);

			array[1] = realtime;

			array[2] = getByUuid_PrevAndNext(session, realtime, uuid,
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

	protected Realtime getByUuid_PrevAndNext(Session session,
		Realtime realtime, String uuid,
		OrderByComparator<Realtime> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_REALTIME_WHERE);

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
			query.append(RealtimeModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(realtime);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Realtime> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the realtimes where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (Realtime realtime : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(realtime);
		}
	}

	/**
	 * Returns the number of realtimes where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching realtimes
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_REALTIME_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "realtime.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "realtime.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(realtime.uuid IS NULL OR realtime.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(RealtimeModelImpl.ENTITY_CACHE_ENABLED,
			RealtimeModelImpl.FINDER_CACHE_ENABLED, RealtimeImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			RealtimeModelImpl.UUID_COLUMN_BITMASK |
			RealtimeModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(RealtimeModelImpl.ENTITY_CACHE_ENABLED,
			RealtimeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the realtime where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchRealtimeException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching realtime
	 * @throws NoSuchRealtimeException if a matching realtime could not be found
	 */
	@Override
	public Realtime findByUUID_G(String uuid, long groupId)
		throws NoSuchRealtimeException {
		Realtime realtime = fetchByUUID_G(uuid, groupId);

		if (realtime == null) {
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

			throw new NoSuchRealtimeException(msg.toString());
		}

		return realtime;
	}

	/**
	 * Returns the realtime where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching realtime, or <code>null</code> if a matching realtime could not be found
	 */
	@Override
	public Realtime fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the realtime where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching realtime, or <code>null</code> if a matching realtime could not be found
	 */
	@Override
	public Realtime fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof Realtime) {
			Realtime realtime = (Realtime)result;

			if (!Objects.equals(uuid, realtime.getUuid()) ||
					(groupId != realtime.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_REALTIME_WHERE);

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

				List<Realtime> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					Realtime realtime = list.get(0);

					result = realtime;

					cacheResult(realtime);

					if ((realtime.getUuid() == null) ||
							!realtime.getUuid().equals(uuid) ||
							(realtime.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, realtime);
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
			return (Realtime)result;
		}
	}

	/**
	 * Removes the realtime where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the realtime that was removed
	 */
	@Override
	public Realtime removeByUUID_G(String uuid, long groupId)
		throws NoSuchRealtimeException {
		Realtime realtime = findByUUID_G(uuid, groupId);

		return remove(realtime);
	}

	/**
	 * Returns the number of realtimes where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching realtimes
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_REALTIME_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "realtime.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "realtime.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(realtime.uuid IS NULL OR realtime.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "realtime.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(RealtimeModelImpl.ENTITY_CACHE_ENABLED,
			RealtimeModelImpl.FINDER_CACHE_ENABLED, RealtimeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(RealtimeModelImpl.ENTITY_CACHE_ENABLED,
			RealtimeModelImpl.FINDER_CACHE_ENABLED, RealtimeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			RealtimeModelImpl.UUID_COLUMN_BITMASK |
			RealtimeModelImpl.COMPANYID_COLUMN_BITMASK |
			RealtimeModelImpl.SHAREID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(RealtimeModelImpl.ENTITY_CACHE_ENABLED,
			RealtimeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the realtimes where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching realtimes
	 */
	@Override
	public List<Realtime> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the realtimes where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of realtimes
	 * @param end the upper bound of the range of realtimes (not inclusive)
	 * @return the range of matching realtimes
	 */
	@Override
	public List<Realtime> findByUuid_C(String uuid, long companyId, int start,
		int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the realtimes where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of realtimes
	 * @param end the upper bound of the range of realtimes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching realtimes
	 */
	@Override
	public List<Realtime> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<Realtime> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the realtimes where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of realtimes
	 * @param end the upper bound of the range of realtimes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching realtimes
	 */
	@Override
	public List<Realtime> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<Realtime> orderByComparator,
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

		List<Realtime> list = null;

		if (retrieveFromCache) {
			list = (List<Realtime>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Realtime realtime : list) {
					if (!Objects.equals(uuid, realtime.getUuid()) ||
							(companyId != realtime.getCompanyId())) {
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

			query.append(_SQL_SELECT_REALTIME_WHERE);

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
				query.append(RealtimeModelImpl.ORDER_BY_JPQL);
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
					list = (List<Realtime>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Realtime>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first realtime in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching realtime
	 * @throws NoSuchRealtimeException if a matching realtime could not be found
	 */
	@Override
	public Realtime findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Realtime> orderByComparator)
		throws NoSuchRealtimeException {
		Realtime realtime = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (realtime != null) {
			return realtime;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRealtimeException(msg.toString());
	}

	/**
	 * Returns the first realtime in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching realtime, or <code>null</code> if a matching realtime could not be found
	 */
	@Override
	public Realtime fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Realtime> orderByComparator) {
		List<Realtime> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last realtime in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching realtime
	 * @throws NoSuchRealtimeException if a matching realtime could not be found
	 */
	@Override
	public Realtime findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Realtime> orderByComparator)
		throws NoSuchRealtimeException {
		Realtime realtime = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (realtime != null) {
			return realtime;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRealtimeException(msg.toString());
	}

	/**
	 * Returns the last realtime in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching realtime, or <code>null</code> if a matching realtime could not be found
	 */
	@Override
	public Realtime fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Realtime> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<Realtime> list = findByUuid_C(uuid, companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the realtimes before and after the current realtime in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param realtimeId the primary key of the current realtime
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next realtime
	 * @throws NoSuchRealtimeException if a realtime with the primary key could not be found
	 */
	@Override
	public Realtime[] findByUuid_C_PrevAndNext(long realtimeId, String uuid,
		long companyId, OrderByComparator<Realtime> orderByComparator)
		throws NoSuchRealtimeException {
		Realtime realtime = findByPrimaryKey(realtimeId);

		Session session = null;

		try {
			session = openSession();

			Realtime[] array = new RealtimeImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, realtime, uuid,
					companyId, orderByComparator, true);

			array[1] = realtime;

			array[2] = getByUuid_C_PrevAndNext(session, realtime, uuid,
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

	protected Realtime getByUuid_C_PrevAndNext(Session session,
		Realtime realtime, String uuid, long companyId,
		OrderByComparator<Realtime> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_REALTIME_WHERE);

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
			query.append(RealtimeModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(realtime);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Realtime> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the realtimes where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (Realtime realtime : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(realtime);
		}
	}

	/**
	 * Returns the number of realtimes where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching realtimes
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_REALTIME_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "realtime.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "realtime.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(realtime.uuid IS NULL OR realtime.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "realtime.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_DATE = new FinderPath(RealtimeModelImpl.ENTITY_CACHE_ENABLED,
			RealtimeModelImpl.FINDER_CACHE_ENABLED, RealtimeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByDate",
			new String[] {
				Date.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DATE = new FinderPath(RealtimeModelImpl.ENTITY_CACHE_ENABLED,
			RealtimeModelImpl.FINDER_CACHE_ENABLED, RealtimeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByDate",
			new String[] { Date.class.getName() },
			RealtimeModelImpl.CREATEDATE_COLUMN_BITMASK |
			RealtimeModelImpl.SHAREID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DATE = new FinderPath(RealtimeModelImpl.ENTITY_CACHE_ENABLED,
			RealtimeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByDate",
			new String[] { Date.class.getName() });

	/**
	 * Returns all the realtimes where createDate = &#63;.
	 *
	 * @param createDate the create date
	 * @return the matching realtimes
	 */
	@Override
	public List<Realtime> findByDate(Date createDate) {
		return findByDate(createDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the realtimes where createDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param start the lower bound of the range of realtimes
	 * @param end the upper bound of the range of realtimes (not inclusive)
	 * @return the range of matching realtimes
	 */
	@Override
	public List<Realtime> findByDate(Date createDate, int start, int end) {
		return findByDate(createDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the realtimes where createDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param start the lower bound of the range of realtimes
	 * @param end the upper bound of the range of realtimes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching realtimes
	 */
	@Override
	public List<Realtime> findByDate(Date createDate, int start, int end,
		OrderByComparator<Realtime> orderByComparator) {
		return findByDate(createDate, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the realtimes where createDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param start the lower bound of the range of realtimes
	 * @param end the upper bound of the range of realtimes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching realtimes
	 */
	@Override
	public List<Realtime> findByDate(Date createDate, int start, int end,
		OrderByComparator<Realtime> orderByComparator, boolean retrieveFromCache) {
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

		List<Realtime> list = null;

		if (retrieveFromCache) {
			list = (List<Realtime>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Realtime realtime : list) {
					if (!Objects.equals(createDate, realtime.getCreateDate())) {
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

			query.append(_SQL_SELECT_REALTIME_WHERE);

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
				query.append(RealtimeModelImpl.ORDER_BY_JPQL);
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
					list = (List<Realtime>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Realtime>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first realtime in the ordered set where createDate = &#63;.
	 *
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching realtime
	 * @throws NoSuchRealtimeException if a matching realtime could not be found
	 */
	@Override
	public Realtime findByDate_First(Date createDate,
		OrderByComparator<Realtime> orderByComparator)
		throws NoSuchRealtimeException {
		Realtime realtime = fetchByDate_First(createDate, orderByComparator);

		if (realtime != null) {
			return realtime;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createDate=");
		msg.append(createDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRealtimeException(msg.toString());
	}

	/**
	 * Returns the first realtime in the ordered set where createDate = &#63;.
	 *
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching realtime, or <code>null</code> if a matching realtime could not be found
	 */
	@Override
	public Realtime fetchByDate_First(Date createDate,
		OrderByComparator<Realtime> orderByComparator) {
		List<Realtime> list = findByDate(createDate, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last realtime in the ordered set where createDate = &#63;.
	 *
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching realtime
	 * @throws NoSuchRealtimeException if a matching realtime could not be found
	 */
	@Override
	public Realtime findByDate_Last(Date createDate,
		OrderByComparator<Realtime> orderByComparator)
		throws NoSuchRealtimeException {
		Realtime realtime = fetchByDate_Last(createDate, orderByComparator);

		if (realtime != null) {
			return realtime;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createDate=");
		msg.append(createDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRealtimeException(msg.toString());
	}

	/**
	 * Returns the last realtime in the ordered set where createDate = &#63;.
	 *
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching realtime, or <code>null</code> if a matching realtime could not be found
	 */
	@Override
	public Realtime fetchByDate_Last(Date createDate,
		OrderByComparator<Realtime> orderByComparator) {
		int count = countByDate(createDate);

		if (count == 0) {
			return null;
		}

		List<Realtime> list = findByDate(createDate, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the realtimes before and after the current realtime in the ordered set where createDate = &#63;.
	 *
	 * @param realtimeId the primary key of the current realtime
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next realtime
	 * @throws NoSuchRealtimeException if a realtime with the primary key could not be found
	 */
	@Override
	public Realtime[] findByDate_PrevAndNext(long realtimeId, Date createDate,
		OrderByComparator<Realtime> orderByComparator)
		throws NoSuchRealtimeException {
		Realtime realtime = findByPrimaryKey(realtimeId);

		Session session = null;

		try {
			session = openSession();

			Realtime[] array = new RealtimeImpl[3];

			array[0] = getByDate_PrevAndNext(session, realtime, createDate,
					orderByComparator, true);

			array[1] = realtime;

			array[2] = getByDate_PrevAndNext(session, realtime, createDate,
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

	protected Realtime getByDate_PrevAndNext(Session session,
		Realtime realtime, Date createDate,
		OrderByComparator<Realtime> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_REALTIME_WHERE);

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
			query.append(RealtimeModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(realtime);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Realtime> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the realtimes where createDate = &#63; from the database.
	 *
	 * @param createDate the create date
	 */
	@Override
	public void removeByDate(Date createDate) {
		for (Realtime realtime : findByDate(createDate, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(realtime);
		}
	}

	/**
	 * Returns the number of realtimes where createDate = &#63;.
	 *
	 * @param createDate the create date
	 * @return the number of matching realtimes
	 */
	@Override
	public int countByDate(Date createDate) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_DATE;

		Object[] finderArgs = new Object[] { createDate };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_REALTIME_WHERE);

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

	private static final String _FINDER_COLUMN_DATE_CREATEDATE_1 = "realtime.createDate IS NULL";
	private static final String _FINDER_COLUMN_DATE_CREATEDATE_2 = "realtime.createDate = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYSHARE =
		new FinderPath(RealtimeModelImpl.ENTITY_CACHE_ENABLED,
			RealtimeModelImpl.FINDER_CACHE_ENABLED, RealtimeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyShare",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYSHARE =
		new FinderPath(RealtimeModelImpl.ENTITY_CACHE_ENABLED,
			RealtimeModelImpl.FINDER_CACHE_ENABLED, RealtimeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyShare",
			new String[] { Long.class.getName(), Long.class.getName() },
			RealtimeModelImpl.COMPANYID_COLUMN_BITMASK |
			RealtimeModelImpl.SHAREID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYSHARE = new FinderPath(RealtimeModelImpl.ENTITY_CACHE_ENABLED,
			RealtimeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyShare",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the realtimes where companyId = &#63; and shareId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @return the matching realtimes
	 */
	@Override
	public List<Realtime> findByCompanyShare(long companyId, long shareId) {
		return findByCompanyShare(companyId, shareId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the realtimes where companyId = &#63; and shareId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param start the lower bound of the range of realtimes
	 * @param end the upper bound of the range of realtimes (not inclusive)
	 * @return the range of matching realtimes
	 */
	@Override
	public List<Realtime> findByCompanyShare(long companyId, long shareId,
		int start, int end) {
		return findByCompanyShare(companyId, shareId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the realtimes where companyId = &#63; and shareId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param start the lower bound of the range of realtimes
	 * @param end the upper bound of the range of realtimes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching realtimes
	 */
	@Override
	public List<Realtime> findByCompanyShare(long companyId, long shareId,
		int start, int end, OrderByComparator<Realtime> orderByComparator) {
		return findByCompanyShare(companyId, shareId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the realtimes where companyId = &#63; and shareId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param start the lower bound of the range of realtimes
	 * @param end the upper bound of the range of realtimes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching realtimes
	 */
	@Override
	public List<Realtime> findByCompanyShare(long companyId, long shareId,
		int start, int end, OrderByComparator<Realtime> orderByComparator,
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

		List<Realtime> list = null;

		if (retrieveFromCache) {
			list = (List<Realtime>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Realtime realtime : list) {
					if ((companyId != realtime.getCompanyId()) ||
							(shareId != realtime.getShareId())) {
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

			query.append(_SQL_SELECT_REALTIME_WHERE);

			query.append(_FINDER_COLUMN_COMPANYSHARE_COMPANYID_2);

			query.append(_FINDER_COLUMN_COMPANYSHARE_SHAREID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(RealtimeModelImpl.ORDER_BY_JPQL);
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
					list = (List<Realtime>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Realtime>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first realtime in the ordered set where companyId = &#63; and shareId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching realtime
	 * @throws NoSuchRealtimeException if a matching realtime could not be found
	 */
	@Override
	public Realtime findByCompanyShare_First(long companyId, long shareId,
		OrderByComparator<Realtime> orderByComparator)
		throws NoSuchRealtimeException {
		Realtime realtime = fetchByCompanyShare_First(companyId, shareId,
				orderByComparator);

		if (realtime != null) {
			return realtime;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", shareId=");
		msg.append(shareId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRealtimeException(msg.toString());
	}

	/**
	 * Returns the first realtime in the ordered set where companyId = &#63; and shareId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching realtime, or <code>null</code> if a matching realtime could not be found
	 */
	@Override
	public Realtime fetchByCompanyShare_First(long companyId, long shareId,
		OrderByComparator<Realtime> orderByComparator) {
		List<Realtime> list = findByCompanyShare(companyId, shareId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last realtime in the ordered set where companyId = &#63; and shareId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching realtime
	 * @throws NoSuchRealtimeException if a matching realtime could not be found
	 */
	@Override
	public Realtime findByCompanyShare_Last(long companyId, long shareId,
		OrderByComparator<Realtime> orderByComparator)
		throws NoSuchRealtimeException {
		Realtime realtime = fetchByCompanyShare_Last(companyId, shareId,
				orderByComparator);

		if (realtime != null) {
			return realtime;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", shareId=");
		msg.append(shareId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRealtimeException(msg.toString());
	}

	/**
	 * Returns the last realtime in the ordered set where companyId = &#63; and shareId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching realtime, or <code>null</code> if a matching realtime could not be found
	 */
	@Override
	public Realtime fetchByCompanyShare_Last(long companyId, long shareId,
		OrderByComparator<Realtime> orderByComparator) {
		int count = countByCompanyShare(companyId, shareId);

		if (count == 0) {
			return null;
		}

		List<Realtime> list = findByCompanyShare(companyId, shareId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the realtimes before and after the current realtime in the ordered set where companyId = &#63; and shareId = &#63;.
	 *
	 * @param realtimeId the primary key of the current realtime
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next realtime
	 * @throws NoSuchRealtimeException if a realtime with the primary key could not be found
	 */
	@Override
	public Realtime[] findByCompanyShare_PrevAndNext(long realtimeId,
		long companyId, long shareId,
		OrderByComparator<Realtime> orderByComparator)
		throws NoSuchRealtimeException {
		Realtime realtime = findByPrimaryKey(realtimeId);

		Session session = null;

		try {
			session = openSession();

			Realtime[] array = new RealtimeImpl[3];

			array[0] = getByCompanyShare_PrevAndNext(session, realtime,
					companyId, shareId, orderByComparator, true);

			array[1] = realtime;

			array[2] = getByCompanyShare_PrevAndNext(session, realtime,
					companyId, shareId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Realtime getByCompanyShare_PrevAndNext(Session session,
		Realtime realtime, long companyId, long shareId,
		OrderByComparator<Realtime> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_REALTIME_WHERE);

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
			query.append(RealtimeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(shareId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(realtime);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Realtime> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the realtimes where companyId = &#63; and shareId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 */
	@Override
	public void removeByCompanyShare(long companyId, long shareId) {
		for (Realtime realtime : findByCompanyShare(companyId, shareId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(realtime);
		}
	}

	/**
	 * Returns the number of realtimes where companyId = &#63; and shareId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @return the number of matching realtimes
	 */
	@Override
	public int countByCompanyShare(long companyId, long shareId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYSHARE;

		Object[] finderArgs = new Object[] { companyId, shareId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_REALTIME_WHERE);

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

	private static final String _FINDER_COLUMN_COMPANYSHARE_COMPANYID_2 = "realtime.companyId = ? AND ";
	private static final String _FINDER_COLUMN_COMPANYSHARE_SHAREID_2 = "realtime.shareId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYSHAREDATE =
		new FinderPath(RealtimeModelImpl.ENTITY_CACHE_ENABLED,
			RealtimeModelImpl.FINDER_CACHE_ENABLED, RealtimeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyShareDate",
			new String[] {
				Long.class.getName(), Long.class.getName(), Date.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYSHAREDATE =
		new FinderPath(RealtimeModelImpl.ENTITY_CACHE_ENABLED,
			RealtimeModelImpl.FINDER_CACHE_ENABLED, RealtimeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCompanyShareDate",
			new String[] {
				Long.class.getName(), Long.class.getName(), Date.class.getName()
			},
			RealtimeModelImpl.COMPANYID_COLUMN_BITMASK |
			RealtimeModelImpl.SHAREID_COLUMN_BITMASK |
			RealtimeModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYSHAREDATE = new FinderPath(RealtimeModelImpl.ENTITY_CACHE_ENABLED,
			RealtimeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCompanyShareDate",
			new String[] {
				Long.class.getName(), Long.class.getName(), Date.class.getName()
			});

	/**
	 * Returns all the realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63;.
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 * @return the matching realtimes
	 */
	@Override
	public List<Realtime> findByCompanyShareDate(long companyId, long shareId,
		Date createDate) {
		return findByCompanyShareDate(companyId, shareId, createDate,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 * @param start the lower bound of the range of realtimes
	 * @param end the upper bound of the range of realtimes (not inclusive)
	 * @return the range of matching realtimes
	 */
	@Override
	public List<Realtime> findByCompanyShareDate(long companyId, long shareId,
		Date createDate, int start, int end) {
		return findByCompanyShareDate(companyId, shareId, createDate, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 * @param start the lower bound of the range of realtimes
	 * @param end the upper bound of the range of realtimes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching realtimes
	 */
	@Override
	public List<Realtime> findByCompanyShareDate(long companyId, long shareId,
		Date createDate, int start, int end,
		OrderByComparator<Realtime> orderByComparator) {
		return findByCompanyShareDate(companyId, shareId, createDate, start,
			end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 * @param start the lower bound of the range of realtimes
	 * @param end the upper bound of the range of realtimes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching realtimes
	 */
	@Override
	public List<Realtime> findByCompanyShareDate(long companyId, long shareId,
		Date createDate, int start, int end,
		OrderByComparator<Realtime> orderByComparator, boolean retrieveFromCache) {
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

		List<Realtime> list = null;

		if (retrieveFromCache) {
			list = (List<Realtime>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Realtime realtime : list) {
					if ((companyId != realtime.getCompanyId()) ||
							(shareId != realtime.getShareId()) ||
							!Objects.equals(createDate, realtime.getCreateDate())) {
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

			query.append(_SQL_SELECT_REALTIME_WHERE);

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
				query.append(RealtimeModelImpl.ORDER_BY_JPQL);
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
					list = (List<Realtime>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Realtime>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first realtime in the ordered set where companyId = &#63; and shareId = &#63; and createDate = &#63;.
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching realtime
	 * @throws NoSuchRealtimeException if a matching realtime could not be found
	 */
	@Override
	public Realtime findByCompanyShareDate_First(long companyId, long shareId,
		Date createDate, OrderByComparator<Realtime> orderByComparator)
		throws NoSuchRealtimeException {
		Realtime realtime = fetchByCompanyShareDate_First(companyId, shareId,
				createDate, orderByComparator);

		if (realtime != null) {
			return realtime;
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

		throw new NoSuchRealtimeException(msg.toString());
	}

	/**
	 * Returns the first realtime in the ordered set where companyId = &#63; and shareId = &#63; and createDate = &#63;.
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching realtime, or <code>null</code> if a matching realtime could not be found
	 */
	@Override
	public Realtime fetchByCompanyShareDate_First(long companyId, long shareId,
		Date createDate, OrderByComparator<Realtime> orderByComparator) {
		List<Realtime> list = findByCompanyShareDate(companyId, shareId,
				createDate, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last realtime in the ordered set where companyId = &#63; and shareId = &#63; and createDate = &#63;.
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching realtime
	 * @throws NoSuchRealtimeException if a matching realtime could not be found
	 */
	@Override
	public Realtime findByCompanyShareDate_Last(long companyId, long shareId,
		Date createDate, OrderByComparator<Realtime> orderByComparator)
		throws NoSuchRealtimeException {
		Realtime realtime = fetchByCompanyShareDate_Last(companyId, shareId,
				createDate, orderByComparator);

		if (realtime != null) {
			return realtime;
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

		throw new NoSuchRealtimeException(msg.toString());
	}

	/**
	 * Returns the last realtime in the ordered set where companyId = &#63; and shareId = &#63; and createDate = &#63;.
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching realtime, or <code>null</code> if a matching realtime could not be found
	 */
	@Override
	public Realtime fetchByCompanyShareDate_Last(long companyId, long shareId,
		Date createDate, OrderByComparator<Realtime> orderByComparator) {
		int count = countByCompanyShareDate(companyId, shareId, createDate);

		if (count == 0) {
			return null;
		}

		List<Realtime> list = findByCompanyShareDate(companyId, shareId,
				createDate, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the realtimes before and after the current realtime in the ordered set where companyId = &#63; and shareId = &#63; and createDate = &#63;.
	 *
	 * @param realtimeId the primary key of the current realtime
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next realtime
	 * @throws NoSuchRealtimeException if a realtime with the primary key could not be found
	 */
	@Override
	public Realtime[] findByCompanyShareDate_PrevAndNext(long realtimeId,
		long companyId, long shareId, Date createDate,
		OrderByComparator<Realtime> orderByComparator)
		throws NoSuchRealtimeException {
		Realtime realtime = findByPrimaryKey(realtimeId);

		Session session = null;

		try {
			session = openSession();

			Realtime[] array = new RealtimeImpl[3];

			array[0] = getByCompanyShareDate_PrevAndNext(session, realtime,
					companyId, shareId, createDate, orderByComparator, true);

			array[1] = realtime;

			array[2] = getByCompanyShareDate_PrevAndNext(session, realtime,
					companyId, shareId, createDate, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Realtime getByCompanyShareDate_PrevAndNext(Session session,
		Realtime realtime, long companyId, long shareId, Date createDate,
		OrderByComparator<Realtime> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_REALTIME_WHERE);

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
			query.append(RealtimeModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(realtime);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Realtime> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 */
	@Override
	public void removeByCompanyShareDate(long companyId, long shareId,
		Date createDate) {
		for (Realtime realtime : findByCompanyShareDate(companyId, shareId,
				createDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(realtime);
		}
	}

	/**
	 * Returns the number of realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63;.
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 * @return the number of matching realtimes
	 */
	@Override
	public int countByCompanyShareDate(long companyId, long shareId,
		Date createDate) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYSHAREDATE;

		Object[] finderArgs = new Object[] { companyId, shareId, createDate };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_REALTIME_WHERE);

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

	private static final String _FINDER_COLUMN_COMPANYSHAREDATE_COMPANYID_2 = "realtime.companyId = ? AND ";
	private static final String _FINDER_COLUMN_COMPANYSHAREDATE_SHAREID_2 = "realtime.shareId = ? AND ";
	private static final String _FINDER_COLUMN_COMPANYSHAREDATE_CREATEDATE_1 = "realtime.createDate IS NULL";
	private static final String _FINDER_COLUMN_COMPANYSHAREDATE_CREATEDATE_2 = "realtime.createDate = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYSHAREGGROUPDATE =
		new FinderPath(RealtimeModelImpl.ENTITY_CACHE_ENABLED,
			RealtimeModelImpl.FINDER_CACHE_ENABLED, RealtimeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCompanySharegGroupDate",
			new String[] {
				Long.class.getName(), Long.class.getName(), Date.class.getName(),
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYSHAREGGROUPDATE =
		new FinderPath(RealtimeModelImpl.ENTITY_CACHE_ENABLED,
			RealtimeModelImpl.FINDER_CACHE_ENABLED, RealtimeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCompanySharegGroupDate",
			new String[] {
				Long.class.getName(), Long.class.getName(), Date.class.getName(),
				Long.class.getName()
			},
			RealtimeModelImpl.COMPANYID_COLUMN_BITMASK |
			RealtimeModelImpl.SHAREID_COLUMN_BITMASK |
			RealtimeModelImpl.CREATEDATE_COLUMN_BITMASK |
			RealtimeModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYSHAREGGROUPDATE = new FinderPath(RealtimeModelImpl.ENTITY_CACHE_ENABLED,
			RealtimeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCompanySharegGroupDate",
			new String[] {
				Long.class.getName(), Long.class.getName(), Date.class.getName(),
				Long.class.getName()
			});

	/**
	 * Returns all the realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 * @param groupId the group ID
	 * @return the matching realtimes
	 */
	@Override
	public List<Realtime> findByCompanySharegGroupDate(long companyId,
		long shareId, Date createDate, long groupId) {
		return findByCompanySharegGroupDate(companyId, shareId, createDate,
			groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 * @param groupId the group ID
	 * @param start the lower bound of the range of realtimes
	 * @param end the upper bound of the range of realtimes (not inclusive)
	 * @return the range of matching realtimes
	 */
	@Override
	public List<Realtime> findByCompanySharegGroupDate(long companyId,
		long shareId, Date createDate, long groupId, int start, int end) {
		return findByCompanySharegGroupDate(companyId, shareId, createDate,
			groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 * @param groupId the group ID
	 * @param start the lower bound of the range of realtimes
	 * @param end the upper bound of the range of realtimes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching realtimes
	 */
	@Override
	public List<Realtime> findByCompanySharegGroupDate(long companyId,
		long shareId, Date createDate, long groupId, int start, int end,
		OrderByComparator<Realtime> orderByComparator) {
		return findByCompanySharegGroupDate(companyId, shareId, createDate,
			groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 * @param groupId the group ID
	 * @param start the lower bound of the range of realtimes
	 * @param end the upper bound of the range of realtimes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching realtimes
	 */
	@Override
	public List<Realtime> findByCompanySharegGroupDate(long companyId,
		long shareId, Date createDate, long groupId, int start, int end,
		OrderByComparator<Realtime> orderByComparator, boolean retrieveFromCache) {
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

		List<Realtime> list = null;

		if (retrieveFromCache) {
			list = (List<Realtime>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Realtime realtime : list) {
					if ((companyId != realtime.getCompanyId()) ||
							(shareId != realtime.getShareId()) ||
							!Objects.equals(createDate, realtime.getCreateDate()) ||
							(groupId != realtime.getGroupId())) {
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

			query.append(_SQL_SELECT_REALTIME_WHERE);

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
				query.append(RealtimeModelImpl.ORDER_BY_JPQL);
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
					list = (List<Realtime>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Realtime>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first realtime in the ordered set where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching realtime
	 * @throws NoSuchRealtimeException if a matching realtime could not be found
	 */
	@Override
	public Realtime findByCompanySharegGroupDate_First(long companyId,
		long shareId, Date createDate, long groupId,
		OrderByComparator<Realtime> orderByComparator)
		throws NoSuchRealtimeException {
		Realtime realtime = fetchByCompanySharegGroupDate_First(companyId,
				shareId, createDate, groupId, orderByComparator);

		if (realtime != null) {
			return realtime;
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

		throw new NoSuchRealtimeException(msg.toString());
	}

	/**
	 * Returns the first realtime in the ordered set where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching realtime, or <code>null</code> if a matching realtime could not be found
	 */
	@Override
	public Realtime fetchByCompanySharegGroupDate_First(long companyId,
		long shareId, Date createDate, long groupId,
		OrderByComparator<Realtime> orderByComparator) {
		List<Realtime> list = findByCompanySharegGroupDate(companyId, shareId,
				createDate, groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last realtime in the ordered set where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching realtime
	 * @throws NoSuchRealtimeException if a matching realtime could not be found
	 */
	@Override
	public Realtime findByCompanySharegGroupDate_Last(long companyId,
		long shareId, Date createDate, long groupId,
		OrderByComparator<Realtime> orderByComparator)
		throws NoSuchRealtimeException {
		Realtime realtime = fetchByCompanySharegGroupDate_Last(companyId,
				shareId, createDate, groupId, orderByComparator);

		if (realtime != null) {
			return realtime;
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

		throw new NoSuchRealtimeException(msg.toString());
	}

	/**
	 * Returns the last realtime in the ordered set where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching realtime, or <code>null</code> if a matching realtime could not be found
	 */
	@Override
	public Realtime fetchByCompanySharegGroupDate_Last(long companyId,
		long shareId, Date createDate, long groupId,
		OrderByComparator<Realtime> orderByComparator) {
		int count = countByCompanySharegGroupDate(companyId, shareId,
				createDate, groupId);

		if (count == 0) {
			return null;
		}

		List<Realtime> list = findByCompanySharegGroupDate(companyId, shareId,
				createDate, groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the realtimes before and after the current realtime in the ordered set where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63;.
	 *
	 * @param realtimeId the primary key of the current realtime
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next realtime
	 * @throws NoSuchRealtimeException if a realtime with the primary key could not be found
	 */
	@Override
	public Realtime[] findByCompanySharegGroupDate_PrevAndNext(
		long realtimeId, long companyId, long shareId, Date createDate,
		long groupId, OrderByComparator<Realtime> orderByComparator)
		throws NoSuchRealtimeException {
		Realtime realtime = findByPrimaryKey(realtimeId);

		Session session = null;

		try {
			session = openSession();

			Realtime[] array = new RealtimeImpl[3];

			array[0] = getByCompanySharegGroupDate_PrevAndNext(session,
					realtime, companyId, shareId, createDate, groupId,
					orderByComparator, true);

			array[1] = realtime;

			array[2] = getByCompanySharegGroupDate_PrevAndNext(session,
					realtime, companyId, shareId, createDate, groupId,
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

	protected Realtime getByCompanySharegGroupDate_PrevAndNext(
		Session session, Realtime realtime, long companyId, long shareId,
		Date createDate, long groupId,
		OrderByComparator<Realtime> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(7 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(6);
		}

		query.append(_SQL_SELECT_REALTIME_WHERE);

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
			query.append(RealtimeModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(realtime);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Realtime> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 * @param groupId the group ID
	 */
	@Override
	public void removeByCompanySharegGroupDate(long companyId, long shareId,
		Date createDate, long groupId) {
		for (Realtime realtime : findByCompanySharegGroupDate(companyId,
				shareId, createDate, groupId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(realtime);
		}
	}

	/**
	 * Returns the number of realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param createDate the create date
	 * @param groupId the group ID
	 * @return the number of matching realtimes
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

			query.append(_SQL_COUNT_REALTIME_WHERE);

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
		"realtime.companyId = ? AND ";
	private static final String _FINDER_COLUMN_COMPANYSHAREGGROUPDATE_SHAREID_2 = "realtime.shareId = ? AND ";
	private static final String _FINDER_COLUMN_COMPANYSHAREGGROUPDATE_CREATEDATE_1 =
		"realtime.createDate IS NULL AND ";
	private static final String _FINDER_COLUMN_COMPANYSHAREGGROUPDATE_CREATEDATE_2 =
		"realtime.createDate = ? AND ";
	private static final String _FINDER_COLUMN_COMPANYSHAREGGROUPDATE_GROUPID_2 = "realtime.groupId = ?";

	public RealtimePersistenceImpl() {
		setModelClass(Realtime.class);
	}

	/**
	 * Caches the realtime in the entity cache if it is enabled.
	 *
	 * @param realtime the realtime
	 */
	@Override
	public void cacheResult(Realtime realtime) {
		entityCache.putResult(RealtimeModelImpl.ENTITY_CACHE_ENABLED,
			RealtimeImpl.class, realtime.getPrimaryKey(), realtime);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { realtime.getUuid(), realtime.getGroupId() }, realtime);

		realtime.resetOriginalValues();
	}

	/**
	 * Caches the realtimes in the entity cache if it is enabled.
	 *
	 * @param realtimes the realtimes
	 */
	@Override
	public void cacheResult(List<Realtime> realtimes) {
		for (Realtime realtime : realtimes) {
			if (entityCache.getResult(RealtimeModelImpl.ENTITY_CACHE_ENABLED,
						RealtimeImpl.class, realtime.getPrimaryKey()) == null) {
				cacheResult(realtime);
			}
			else {
				realtime.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all realtimes.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(RealtimeImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the realtime.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Realtime realtime) {
		entityCache.removeResult(RealtimeModelImpl.ENTITY_CACHE_ENABLED,
			RealtimeImpl.class, realtime.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((RealtimeModelImpl)realtime);
	}

	@Override
	public void clearCache(List<Realtime> realtimes) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Realtime realtime : realtimes) {
			entityCache.removeResult(RealtimeModelImpl.ENTITY_CACHE_ENABLED,
				RealtimeImpl.class, realtime.getPrimaryKey());

			clearUniqueFindersCache((RealtimeModelImpl)realtime);
		}
	}

	protected void cacheUniqueFindersCache(
		RealtimeModelImpl realtimeModelImpl, boolean isNew) {
		if (isNew) {
			Object[] args = new Object[] {
					realtimeModelImpl.getUuid(), realtimeModelImpl.getGroupId()
				};

			finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
				Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
				realtimeModelImpl);
		}
		else {
			if ((realtimeModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						realtimeModelImpl.getUuid(),
						realtimeModelImpl.getGroupId()
					};

				finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
					Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
					realtimeModelImpl);
			}
		}
	}

	protected void clearUniqueFindersCache(RealtimeModelImpl realtimeModelImpl) {
		Object[] args = new Object[] {
				realtimeModelImpl.getUuid(), realtimeModelImpl.getGroupId()
			};

		finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

		if ((realtimeModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			args = new Object[] {
					realtimeModelImpl.getOriginalUuid(),
					realtimeModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new realtime with the primary key. Does not add the realtime to the database.
	 *
	 * @param realtimeId the primary key for the new realtime
	 * @return the new realtime
	 */
	@Override
	public Realtime create(long realtimeId) {
		Realtime realtime = new RealtimeImpl();

		realtime.setNew(true);
		realtime.setPrimaryKey(realtimeId);

		String uuid = PortalUUIDUtil.generate();

		realtime.setUuid(uuid);

		realtime.setCompanyId(companyProvider.getCompanyId());

		return realtime;
	}

	/**
	 * Removes the realtime with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param realtimeId the primary key of the realtime
	 * @return the realtime that was removed
	 * @throws NoSuchRealtimeException if a realtime with the primary key could not be found
	 */
	@Override
	public Realtime remove(long realtimeId) throws NoSuchRealtimeException {
		return remove((Serializable)realtimeId);
	}

	/**
	 * Removes the realtime with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the realtime
	 * @return the realtime that was removed
	 * @throws NoSuchRealtimeException if a realtime with the primary key could not be found
	 */
	@Override
	public Realtime remove(Serializable primaryKey)
		throws NoSuchRealtimeException {
		Session session = null;

		try {
			session = openSession();

			Realtime realtime = (Realtime)session.get(RealtimeImpl.class,
					primaryKey);

			if (realtime == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchRealtimeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(realtime);
		}
		catch (NoSuchRealtimeException nsee) {
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
	protected Realtime removeImpl(Realtime realtime) {
		realtime = toUnwrappedModel(realtime);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(realtime)) {
				realtime = (Realtime)session.get(RealtimeImpl.class,
						realtime.getPrimaryKeyObj());
			}

			if (realtime != null) {
				session.delete(realtime);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (realtime != null) {
			clearCache(realtime);
		}

		return realtime;
	}

	@Override
	public Realtime updateImpl(Realtime realtime) {
		realtime = toUnwrappedModel(realtime);

		boolean isNew = realtime.isNew();

		RealtimeModelImpl realtimeModelImpl = (RealtimeModelImpl)realtime;

		if (Validator.isNull(realtime.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			realtime.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (realtime.getCreateDate() == null)) {
			if (serviceContext == null) {
				realtime.setCreateDate(now);
			}
			else {
				realtime.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!realtimeModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				realtime.setModifiedDate(now);
			}
			else {
				realtime.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (realtime.isNew()) {
				session.save(realtime);

				realtime.setNew(false);
			}
			else {
				realtime = (Realtime)session.merge(realtime);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !RealtimeModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((realtimeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { realtimeModelImpl.getOriginalUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { realtimeModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((realtimeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						realtimeModelImpl.getOriginalUuid(),
						realtimeModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						realtimeModelImpl.getUuid(),
						realtimeModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((realtimeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DATE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						realtimeModelImpl.getOriginalCreateDate()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_DATE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DATE,
					args);

				args = new Object[] { realtimeModelImpl.getCreateDate() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_DATE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DATE,
					args);
			}

			if ((realtimeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYSHARE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						realtimeModelImpl.getOriginalCompanyId(),
						realtimeModelImpl.getOriginalShareId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYSHARE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYSHARE,
					args);

				args = new Object[] {
						realtimeModelImpl.getCompanyId(),
						realtimeModelImpl.getShareId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYSHARE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYSHARE,
					args);
			}

			if ((realtimeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYSHAREDATE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						realtimeModelImpl.getOriginalCompanyId(),
						realtimeModelImpl.getOriginalShareId(),
						realtimeModelImpl.getOriginalCreateDate()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYSHAREDATE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYSHAREDATE,
					args);

				args = new Object[] {
						realtimeModelImpl.getCompanyId(),
						realtimeModelImpl.getShareId(),
						realtimeModelImpl.getCreateDate()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYSHAREDATE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYSHAREDATE,
					args);
			}

			if ((realtimeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYSHAREGGROUPDATE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						realtimeModelImpl.getOriginalCompanyId(),
						realtimeModelImpl.getOriginalShareId(),
						realtimeModelImpl.getOriginalCreateDate(),
						realtimeModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYSHAREGGROUPDATE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYSHAREGGROUPDATE,
					args);

				args = new Object[] {
						realtimeModelImpl.getCompanyId(),
						realtimeModelImpl.getShareId(),
						realtimeModelImpl.getCreateDate(),
						realtimeModelImpl.getGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYSHAREGGROUPDATE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYSHAREGGROUPDATE,
					args);
			}
		}

		entityCache.putResult(RealtimeModelImpl.ENTITY_CACHE_ENABLED,
			RealtimeImpl.class, realtime.getPrimaryKey(), realtime, false);

		clearUniqueFindersCache(realtimeModelImpl);
		cacheUniqueFindersCache(realtimeModelImpl, isNew);

		realtime.resetOriginalValues();

		return realtime;
	}

	protected Realtime toUnwrappedModel(Realtime realtime) {
		if (realtime instanceof RealtimeImpl) {
			return realtime;
		}

		RealtimeImpl realtimeImpl = new RealtimeImpl();

		realtimeImpl.setNew(realtime.isNew());
		realtimeImpl.setPrimaryKey(realtime.getPrimaryKey());

		realtimeImpl.setUuid(realtime.getUuid());
		realtimeImpl.setRealtimeId(realtime.getRealtimeId());
		realtimeImpl.setGroupId(realtime.getGroupId());
		realtimeImpl.setCompanyId(realtime.getCompanyId());
		realtimeImpl.setShareId(realtime.getShareId());
		realtimeImpl.setValue(realtime.getValue());
		realtimeImpl.setCreateDate(realtime.getCreateDate());
		realtimeImpl.setModifiedDate(realtime.getModifiedDate());
		realtimeImpl.setMax_value(realtime.getMax_value());
		realtimeImpl.setMin_value(realtime.getMin_value());
		realtimeImpl.setVolume(realtime.getVolume());
		realtimeImpl.setAvg_volume(realtime.getAvg_volume());

		return realtimeImpl;
	}

	/**
	 * Returns the realtime with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the realtime
	 * @return the realtime
	 * @throws NoSuchRealtimeException if a realtime with the primary key could not be found
	 */
	@Override
	public Realtime findByPrimaryKey(Serializable primaryKey)
		throws NoSuchRealtimeException {
		Realtime realtime = fetchByPrimaryKey(primaryKey);

		if (realtime == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchRealtimeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return realtime;
	}

	/**
	 * Returns the realtime with the primary key or throws a {@link NoSuchRealtimeException} if it could not be found.
	 *
	 * @param realtimeId the primary key of the realtime
	 * @return the realtime
	 * @throws NoSuchRealtimeException if a realtime with the primary key could not be found
	 */
	@Override
	public Realtime findByPrimaryKey(long realtimeId)
		throws NoSuchRealtimeException {
		return findByPrimaryKey((Serializable)realtimeId);
	}

	/**
	 * Returns the realtime with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the realtime
	 * @return the realtime, or <code>null</code> if a realtime with the primary key could not be found
	 */
	@Override
	public Realtime fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(RealtimeModelImpl.ENTITY_CACHE_ENABLED,
				RealtimeImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Realtime realtime = (Realtime)serializable;

		if (realtime == null) {
			Session session = null;

			try {
				session = openSession();

				realtime = (Realtime)session.get(RealtimeImpl.class, primaryKey);

				if (realtime != null) {
					cacheResult(realtime);
				}
				else {
					entityCache.putResult(RealtimeModelImpl.ENTITY_CACHE_ENABLED,
						RealtimeImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(RealtimeModelImpl.ENTITY_CACHE_ENABLED,
					RealtimeImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return realtime;
	}

	/**
	 * Returns the realtime with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param realtimeId the primary key of the realtime
	 * @return the realtime, or <code>null</code> if a realtime with the primary key could not be found
	 */
	@Override
	public Realtime fetchByPrimaryKey(long realtimeId) {
		return fetchByPrimaryKey((Serializable)realtimeId);
	}

	@Override
	public Map<Serializable, Realtime> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Realtime> map = new HashMap<Serializable, Realtime>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Realtime realtime = fetchByPrimaryKey(primaryKey);

			if (realtime != null) {
				map.put(primaryKey, realtime);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(RealtimeModelImpl.ENTITY_CACHE_ENABLED,
					RealtimeImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Realtime)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_REALTIME_WHERE_PKS_IN);

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

			for (Realtime realtime : (List<Realtime>)q.list()) {
				map.put(realtime.getPrimaryKeyObj(), realtime);

				cacheResult(realtime);

				uncachedPrimaryKeys.remove(realtime.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(RealtimeModelImpl.ENTITY_CACHE_ENABLED,
					RealtimeImpl.class, primaryKey, nullModel);
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
	 * Returns all the realtimes.
	 *
	 * @return the realtimes
	 */
	@Override
	public List<Realtime> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the realtimes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of realtimes
	 * @param end the upper bound of the range of realtimes (not inclusive)
	 * @return the range of realtimes
	 */
	@Override
	public List<Realtime> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the realtimes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of realtimes
	 * @param end the upper bound of the range of realtimes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of realtimes
	 */
	@Override
	public List<Realtime> findAll(int start, int end,
		OrderByComparator<Realtime> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the realtimes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of realtimes
	 * @param end the upper bound of the range of realtimes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of realtimes
	 */
	@Override
	public List<Realtime> findAll(int start, int end,
		OrderByComparator<Realtime> orderByComparator, boolean retrieveFromCache) {
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

		List<Realtime> list = null;

		if (retrieveFromCache) {
			list = (List<Realtime>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_REALTIME);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_REALTIME;

				if (pagination) {
					sql = sql.concat(RealtimeModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Realtime>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Realtime>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Removes all the realtimes from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Realtime realtime : findAll()) {
			remove(realtime);
		}
	}

	/**
	 * Returns the number of realtimes.
	 *
	 * @return the number of realtimes
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_REALTIME);

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
		return RealtimeModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the realtime persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(RealtimeImpl.class.getName());
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
	private static final String _SQL_SELECT_REALTIME = "SELECT realtime FROM Realtime realtime";
	private static final String _SQL_SELECT_REALTIME_WHERE_PKS_IN = "SELECT realtime FROM Realtime realtime WHERE realtimeId IN (";
	private static final String _SQL_SELECT_REALTIME_WHERE = "SELECT realtime FROM Realtime realtime WHERE ";
	private static final String _SQL_COUNT_REALTIME = "SELECT COUNT(realtime) FROM Realtime realtime";
	private static final String _SQL_COUNT_REALTIME_WHERE = "SELECT COUNT(realtime) FROM Realtime realtime WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "realtime.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Realtime exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Realtime exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(RealtimePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}