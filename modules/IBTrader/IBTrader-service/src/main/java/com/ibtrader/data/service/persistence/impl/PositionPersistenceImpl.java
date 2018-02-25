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

import com.ibtrader.data.exception.NoSuchPositionException;
import com.ibtrader.data.model.Position;
import com.ibtrader.data.model.impl.PositionImpl;
import com.ibtrader.data.model.impl.PositionModelImpl;
import com.ibtrader.data.service.persistence.PositionPersistence;

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
import com.liferay.portal.kernel.util.StringUtil;
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
 * The persistence implementation for the position service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PositionPersistence
 * @see com.ibtrader.data.service.persistence.PositionUtil
 * @generated
 */
@ProviderType
public class PositionPersistenceImpl extends BasePersistenceImpl<Position>
	implements PositionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link PositionUtil} to access the position persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = PositionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, PositionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, PositionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, PositionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, PositionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			PositionModelImpl.UUID_COLUMN_BITMASK |
			PositionModelImpl.POSITIONID_TWS_IN_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the positions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching positions
	 */
	@Override
	public List<Position> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the positions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of positions
	 * @param end the upper bound of the range of positions (not inclusive)
	 * @return the range of matching positions
	 */
	@Override
	public List<Position> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the positions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of positions
	 * @param end the upper bound of the range of positions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching positions
	 */
	@Override
	public List<Position> findByUuid(String uuid, int start, int end,
		OrderByComparator<Position> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the positions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of positions
	 * @param end the upper bound of the range of positions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching positions
	 */
	@Override
	public List<Position> findByUuid(String uuid, int start, int end,
		OrderByComparator<Position> orderByComparator, boolean retrieveFromCache) {
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

		List<Position> list = null;

		if (retrieveFromCache) {
			list = (List<Position>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Position position : list) {
					if (!Objects.equals(uuid, position.getUuid())) {
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

			query.append(_SQL_SELECT_POSITION_WHERE);

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
				query.append(PositionModelImpl.ORDER_BY_JPQL);
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
					list = (List<Position>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Position>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first position in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching position
	 * @throws NoSuchPositionException if a matching position could not be found
	 */
	@Override
	public Position findByUuid_First(String uuid,
		OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException {
		Position position = fetchByUuid_First(uuid, orderByComparator);

		if (position != null) {
			return position;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPositionException(msg.toString());
	}

	/**
	 * Returns the first position in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching position, or <code>null</code> if a matching position could not be found
	 */
	@Override
	public Position fetchByUuid_First(String uuid,
		OrderByComparator<Position> orderByComparator) {
		List<Position> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last position in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching position
	 * @throws NoSuchPositionException if a matching position could not be found
	 */
	@Override
	public Position findByUuid_Last(String uuid,
		OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException {
		Position position = fetchByUuid_Last(uuid, orderByComparator);

		if (position != null) {
			return position;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPositionException(msg.toString());
	}

	/**
	 * Returns the last position in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching position, or <code>null</code> if a matching position could not be found
	 */
	@Override
	public Position fetchByUuid_Last(String uuid,
		OrderByComparator<Position> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Position> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the positions before and after the current position in the ordered set where uuid = &#63;.
	 *
	 * @param positionId the primary key of the current position
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next position
	 * @throws NoSuchPositionException if a position with the primary key could not be found
	 */
	@Override
	public Position[] findByUuid_PrevAndNext(long positionId, String uuid,
		OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException {
		Position position = findByPrimaryKey(positionId);

		Session session = null;

		try {
			session = openSession();

			Position[] array = new PositionImpl[3];

			array[0] = getByUuid_PrevAndNext(session, position, uuid,
					orderByComparator, true);

			array[1] = position;

			array[2] = getByUuid_PrevAndNext(session, position, uuid,
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

	protected Position getByUuid_PrevAndNext(Session session,
		Position position, String uuid,
		OrderByComparator<Position> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_POSITION_WHERE);

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
			query.append(PositionModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(position);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Position> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the positions where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (Position position : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(position);
		}
	}

	/**
	 * Returns the number of positions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching positions
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_POSITION_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "position.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "position.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(position.uuid IS NULL OR position.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, PositionImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			PositionModelImpl.UUID_COLUMN_BITMASK |
			PositionModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the position where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchPositionException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching position
	 * @throws NoSuchPositionException if a matching position could not be found
	 */
	@Override
	public Position findByUUID_G(String uuid, long groupId)
		throws NoSuchPositionException {
		Position position = fetchByUUID_G(uuid, groupId);

		if (position == null) {
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

			throw new NoSuchPositionException(msg.toString());
		}

		return position;
	}

	/**
	 * Returns the position where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching position, or <code>null</code> if a matching position could not be found
	 */
	@Override
	public Position fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the position where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching position, or <code>null</code> if a matching position could not be found
	 */
	@Override
	public Position fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof Position) {
			Position position = (Position)result;

			if (!Objects.equals(uuid, position.getUuid()) ||
					(groupId != position.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_POSITION_WHERE);

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

				List<Position> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					Position position = list.get(0);

					result = position;

					cacheResult(position);

					if ((position.getUuid() == null) ||
							!position.getUuid().equals(uuid) ||
							(position.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, position);
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
			return (Position)result;
		}
	}

	/**
	 * Removes the position where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the position that was removed
	 */
	@Override
	public Position removeByUUID_G(String uuid, long groupId)
		throws NoSuchPositionException {
		Position position = findByUUID_G(uuid, groupId);

		return remove(position);
	}

	/**
	 * Returns the number of positions where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching positions
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_POSITION_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "position.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "position.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(position.uuid IS NULL OR position.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "position.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, PositionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, PositionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			PositionModelImpl.UUID_COLUMN_BITMASK |
			PositionModelImpl.COMPANYID_COLUMN_BITMASK |
			PositionModelImpl.POSITIONID_TWS_IN_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the positions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching positions
	 */
	@Override
	public List<Position> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the positions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of positions
	 * @param end the upper bound of the range of positions (not inclusive)
	 * @return the range of matching positions
	 */
	@Override
	public List<Position> findByUuid_C(String uuid, long companyId, int start,
		int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the positions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of positions
	 * @param end the upper bound of the range of positions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching positions
	 */
	@Override
	public List<Position> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<Position> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the positions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of positions
	 * @param end the upper bound of the range of positions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching positions
	 */
	@Override
	public List<Position> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<Position> orderByComparator,
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

		List<Position> list = null;

		if (retrieveFromCache) {
			list = (List<Position>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Position position : list) {
					if (!Objects.equals(uuid, position.getUuid()) ||
							(companyId != position.getCompanyId())) {
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

			query.append(_SQL_SELECT_POSITION_WHERE);

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
				query.append(PositionModelImpl.ORDER_BY_JPQL);
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
					list = (List<Position>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Position>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first position in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching position
	 * @throws NoSuchPositionException if a matching position could not be found
	 */
	@Override
	public Position findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException {
		Position position = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (position != null) {
			return position;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPositionException(msg.toString());
	}

	/**
	 * Returns the first position in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching position, or <code>null</code> if a matching position could not be found
	 */
	@Override
	public Position fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Position> orderByComparator) {
		List<Position> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last position in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching position
	 * @throws NoSuchPositionException if a matching position could not be found
	 */
	@Override
	public Position findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException {
		Position position = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (position != null) {
			return position;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPositionException(msg.toString());
	}

	/**
	 * Returns the last position in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching position, or <code>null</code> if a matching position could not be found
	 */
	@Override
	public Position fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Position> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<Position> list = findByUuid_C(uuid, companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the positions before and after the current position in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param positionId the primary key of the current position
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next position
	 * @throws NoSuchPositionException if a position with the primary key could not be found
	 */
	@Override
	public Position[] findByUuid_C_PrevAndNext(long positionId, String uuid,
		long companyId, OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException {
		Position position = findByPrimaryKey(positionId);

		Session session = null;

		try {
			session = openSession();

			Position[] array = new PositionImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, position, uuid,
					companyId, orderByComparator, true);

			array[1] = position;

			array[2] = getByUuid_C_PrevAndNext(session, position, uuid,
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

	protected Position getByUuid_C_PrevAndNext(Session session,
		Position position, String uuid, long companyId,
		OrderByComparator<Position> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_POSITION_WHERE);

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
			query.append(PositionModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(position);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Position> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the positions where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (Position position : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(position);
		}
	}

	/**
	 * Returns the number of positions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching positions
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_POSITION_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "position.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "position.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(position.uuid IS NULL OR position.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "position.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_POSITIONSHAREDATESINOUT =
		new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, PositionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByPositionShareDatesInOut",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Date.class.getName(), Date.class.getName(), Date.class.getName(),
				Date.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_POSITIONSHAREDATESINOUT =
		new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, PositionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByPositionShareDatesInOut",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Date.class.getName(), Date.class.getName(), Date.class.getName(),
				Date.class.getName()
			},
			PositionModelImpl.GROUPID_COLUMN_BITMASK |
			PositionModelImpl.COMPANYID_COLUMN_BITMASK |
			PositionModelImpl.SHAREID_COLUMN_BITMASK |
			PositionModelImpl.DATE_REAL_IN_COLUMN_BITMASK |
			PositionModelImpl.DATE_REAL_OUT_COLUMN_BITMASK |
			PositionModelImpl.DATE_IN_COLUMN_BITMASK |
			PositionModelImpl.DATE_OUT_COLUMN_BITMASK |
			PositionModelImpl.POSITIONID_TWS_IN_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_POSITIONSHAREDATESINOUT = new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByPositionShareDatesInOut",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Date.class.getName(), Date.class.getName(), Date.class.getName(),
				Date.class.getName()
			});

	/**
	 * Returns all the positions where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_real_in = &#63; and date_real_out = &#63; and date_in = &#63; and date_out = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param date_real_in the date_real_in
	 * @param date_real_out the date_real_out
	 * @param date_in the date_in
	 * @param date_out the date_out
	 * @return the matching positions
	 */
	@Override
	public List<Position> findByPositionShareDatesInOut(long groupId,
		long companyId, long shareId, Date date_real_in, Date date_real_out,
		Date date_in, Date date_out) {
		return findByPositionShareDatesInOut(groupId, companyId, shareId,
			date_real_in, date_real_out, date_in, date_out, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the positions where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_real_in = &#63; and date_real_out = &#63; and date_in = &#63; and date_out = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param date_real_in the date_real_in
	 * @param date_real_out the date_real_out
	 * @param date_in the date_in
	 * @param date_out the date_out
	 * @param start the lower bound of the range of positions
	 * @param end the upper bound of the range of positions (not inclusive)
	 * @return the range of matching positions
	 */
	@Override
	public List<Position> findByPositionShareDatesInOut(long groupId,
		long companyId, long shareId, Date date_real_in, Date date_real_out,
		Date date_in, Date date_out, int start, int end) {
		return findByPositionShareDatesInOut(groupId, companyId, shareId,
			date_real_in, date_real_out, date_in, date_out, start, end, null);
	}

	/**
	 * Returns an ordered range of all the positions where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_real_in = &#63; and date_real_out = &#63; and date_in = &#63; and date_out = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param date_real_in the date_real_in
	 * @param date_real_out the date_real_out
	 * @param date_in the date_in
	 * @param date_out the date_out
	 * @param start the lower bound of the range of positions
	 * @param end the upper bound of the range of positions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching positions
	 */
	@Override
	public List<Position> findByPositionShareDatesInOut(long groupId,
		long companyId, long shareId, Date date_real_in, Date date_real_out,
		Date date_in, Date date_out, int start, int end,
		OrderByComparator<Position> orderByComparator) {
		return findByPositionShareDatesInOut(groupId, companyId, shareId,
			date_real_in, date_real_out, date_in, date_out, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the positions where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_real_in = &#63; and date_real_out = &#63; and date_in = &#63; and date_out = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param date_real_in the date_real_in
	 * @param date_real_out the date_real_out
	 * @param date_in the date_in
	 * @param date_out the date_out
	 * @param start the lower bound of the range of positions
	 * @param end the upper bound of the range of positions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching positions
	 */
	@Override
	public List<Position> findByPositionShareDatesInOut(long groupId,
		long companyId, long shareId, Date date_real_in, Date date_real_out,
		Date date_in, Date date_out, int start, int end,
		OrderByComparator<Position> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_POSITIONSHAREDATESINOUT;
			finderArgs = new Object[] {
					groupId, companyId, shareId, date_real_in, date_real_out,
					date_in, date_out
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_POSITIONSHAREDATESINOUT;
			finderArgs = new Object[] {
					groupId, companyId, shareId, date_real_in, date_real_out,
					date_in, date_out,
					
					start, end, orderByComparator
				};
		}

		List<Position> list = null;

		if (retrieveFromCache) {
			list = (List<Position>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Position position : list) {
					if ((groupId != position.getGroupId()) ||
							(companyId != position.getCompanyId()) ||
							(shareId != position.getShareId()) ||
							!Objects.equals(date_real_in,
								position.getDate_real_in()) ||
							!Objects.equals(date_real_out,
								position.getDate_real_out()) ||
							!Objects.equals(date_in, position.getDate_in()) ||
							!Objects.equals(date_out, position.getDate_out())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(9 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(9);
			}

			query.append(_SQL_SELECT_POSITION_WHERE);

			query.append(_FINDER_COLUMN_POSITIONSHAREDATESINOUT_GROUPID_2);

			query.append(_FINDER_COLUMN_POSITIONSHAREDATESINOUT_COMPANYID_2);

			query.append(_FINDER_COLUMN_POSITIONSHAREDATESINOUT_SHAREID_2);

			boolean bindDate_real_in = false;

			if (date_real_in == null) {
				query.append(_FINDER_COLUMN_POSITIONSHAREDATESINOUT_DATE_REAL_IN_1);
			}
			else {
				bindDate_real_in = true;

				query.append(_FINDER_COLUMN_POSITIONSHAREDATESINOUT_DATE_REAL_IN_2);
			}

			boolean bindDate_real_out = false;

			if (date_real_out == null) {
				query.append(_FINDER_COLUMN_POSITIONSHAREDATESINOUT_DATE_REAL_OUT_1);
			}
			else {
				bindDate_real_out = true;

				query.append(_FINDER_COLUMN_POSITIONSHAREDATESINOUT_DATE_REAL_OUT_2);
			}

			boolean bindDate_in = false;

			if (date_in == null) {
				query.append(_FINDER_COLUMN_POSITIONSHAREDATESINOUT_DATE_IN_1);
			}
			else {
				bindDate_in = true;

				query.append(_FINDER_COLUMN_POSITIONSHAREDATESINOUT_DATE_IN_2);
			}

			boolean bindDate_out = false;

			if (date_out == null) {
				query.append(_FINDER_COLUMN_POSITIONSHAREDATESINOUT_DATE_OUT_1);
			}
			else {
				bindDate_out = true;

				query.append(_FINDER_COLUMN_POSITIONSHAREDATESINOUT_DATE_OUT_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PositionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(companyId);

				qPos.add(shareId);

				if (bindDate_real_in) {
					qPos.add(new Timestamp(date_real_in.getTime()));
				}

				if (bindDate_real_out) {
					qPos.add(new Timestamp(date_real_out.getTime()));
				}

				if (bindDate_in) {
					qPos.add(new Timestamp(date_in.getTime()));
				}

				if (bindDate_out) {
					qPos.add(new Timestamp(date_out.getTime()));
				}

				if (!pagination) {
					list = (List<Position>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Position>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first position in the ordered set where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_real_in = &#63; and date_real_out = &#63; and date_in = &#63; and date_out = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param date_real_in the date_real_in
	 * @param date_real_out the date_real_out
	 * @param date_in the date_in
	 * @param date_out the date_out
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching position
	 * @throws NoSuchPositionException if a matching position could not be found
	 */
	@Override
	public Position findByPositionShareDatesInOut_First(long groupId,
		long companyId, long shareId, Date date_real_in, Date date_real_out,
		Date date_in, Date date_out,
		OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException {
		Position position = fetchByPositionShareDatesInOut_First(groupId,
				companyId, shareId, date_real_in, date_real_out, date_in,
				date_out, orderByComparator);

		if (position != null) {
			return position;
		}

		StringBundler msg = new StringBundler(16);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(", shareId=");
		msg.append(shareId);

		msg.append(", date_real_in=");
		msg.append(date_real_in);

		msg.append(", date_real_out=");
		msg.append(date_real_out);

		msg.append(", date_in=");
		msg.append(date_in);

		msg.append(", date_out=");
		msg.append(date_out);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPositionException(msg.toString());
	}

	/**
	 * Returns the first position in the ordered set where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_real_in = &#63; and date_real_out = &#63; and date_in = &#63; and date_out = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param date_real_in the date_real_in
	 * @param date_real_out the date_real_out
	 * @param date_in the date_in
	 * @param date_out the date_out
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching position, or <code>null</code> if a matching position could not be found
	 */
	@Override
	public Position fetchByPositionShareDatesInOut_First(long groupId,
		long companyId, long shareId, Date date_real_in, Date date_real_out,
		Date date_in, Date date_out,
		OrderByComparator<Position> orderByComparator) {
		List<Position> list = findByPositionShareDatesInOut(groupId, companyId,
				shareId, date_real_in, date_real_out, date_in, date_out, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last position in the ordered set where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_real_in = &#63; and date_real_out = &#63; and date_in = &#63; and date_out = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param date_real_in the date_real_in
	 * @param date_real_out the date_real_out
	 * @param date_in the date_in
	 * @param date_out the date_out
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching position
	 * @throws NoSuchPositionException if a matching position could not be found
	 */
	@Override
	public Position findByPositionShareDatesInOut_Last(long groupId,
		long companyId, long shareId, Date date_real_in, Date date_real_out,
		Date date_in, Date date_out,
		OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException {
		Position position = fetchByPositionShareDatesInOut_Last(groupId,
				companyId, shareId, date_real_in, date_real_out, date_in,
				date_out, orderByComparator);

		if (position != null) {
			return position;
		}

		StringBundler msg = new StringBundler(16);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(", shareId=");
		msg.append(shareId);

		msg.append(", date_real_in=");
		msg.append(date_real_in);

		msg.append(", date_real_out=");
		msg.append(date_real_out);

		msg.append(", date_in=");
		msg.append(date_in);

		msg.append(", date_out=");
		msg.append(date_out);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPositionException(msg.toString());
	}

	/**
	 * Returns the last position in the ordered set where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_real_in = &#63; and date_real_out = &#63; and date_in = &#63; and date_out = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param date_real_in the date_real_in
	 * @param date_real_out the date_real_out
	 * @param date_in the date_in
	 * @param date_out the date_out
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching position, or <code>null</code> if a matching position could not be found
	 */
	@Override
	public Position fetchByPositionShareDatesInOut_Last(long groupId,
		long companyId, long shareId, Date date_real_in, Date date_real_out,
		Date date_in, Date date_out,
		OrderByComparator<Position> orderByComparator) {
		int count = countByPositionShareDatesInOut(groupId, companyId, shareId,
				date_real_in, date_real_out, date_in, date_out);

		if (count == 0) {
			return null;
		}

		List<Position> list = findByPositionShareDatesInOut(groupId, companyId,
				shareId, date_real_in, date_real_out, date_in, date_out,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the positions before and after the current position in the ordered set where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_real_in = &#63; and date_real_out = &#63; and date_in = &#63; and date_out = &#63;.
	 *
	 * @param positionId the primary key of the current position
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param date_real_in the date_real_in
	 * @param date_real_out the date_real_out
	 * @param date_in the date_in
	 * @param date_out the date_out
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next position
	 * @throws NoSuchPositionException if a position with the primary key could not be found
	 */
	@Override
	public Position[] findByPositionShareDatesInOut_PrevAndNext(
		long positionId, long groupId, long companyId, long shareId,
		Date date_real_in, Date date_real_out, Date date_in, Date date_out,
		OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException {
		Position position = findByPrimaryKey(positionId);

		Session session = null;

		try {
			session = openSession();

			Position[] array = new PositionImpl[3];

			array[0] = getByPositionShareDatesInOut_PrevAndNext(session,
					position, groupId, companyId, shareId, date_real_in,
					date_real_out, date_in, date_out, orderByComparator, true);

			array[1] = position;

			array[2] = getByPositionShareDatesInOut_PrevAndNext(session,
					position, groupId, companyId, shareId, date_real_in,
					date_real_out, date_in, date_out, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Position getByPositionShareDatesInOut_PrevAndNext(
		Session session, Position position, long groupId, long companyId,
		long shareId, Date date_real_in, Date date_real_out, Date date_in,
		Date date_out, OrderByComparator<Position> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(10 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(9);
		}

		query.append(_SQL_SELECT_POSITION_WHERE);

		query.append(_FINDER_COLUMN_POSITIONSHAREDATESINOUT_GROUPID_2);

		query.append(_FINDER_COLUMN_POSITIONSHAREDATESINOUT_COMPANYID_2);

		query.append(_FINDER_COLUMN_POSITIONSHAREDATESINOUT_SHAREID_2);

		boolean bindDate_real_in = false;

		if (date_real_in == null) {
			query.append(_FINDER_COLUMN_POSITIONSHAREDATESINOUT_DATE_REAL_IN_1);
		}
		else {
			bindDate_real_in = true;

			query.append(_FINDER_COLUMN_POSITIONSHAREDATESINOUT_DATE_REAL_IN_2);
		}

		boolean bindDate_real_out = false;

		if (date_real_out == null) {
			query.append(_FINDER_COLUMN_POSITIONSHAREDATESINOUT_DATE_REAL_OUT_1);
		}
		else {
			bindDate_real_out = true;

			query.append(_FINDER_COLUMN_POSITIONSHAREDATESINOUT_DATE_REAL_OUT_2);
		}

		boolean bindDate_in = false;

		if (date_in == null) {
			query.append(_FINDER_COLUMN_POSITIONSHAREDATESINOUT_DATE_IN_1);
		}
		else {
			bindDate_in = true;

			query.append(_FINDER_COLUMN_POSITIONSHAREDATESINOUT_DATE_IN_2);
		}

		boolean bindDate_out = false;

		if (date_out == null) {
			query.append(_FINDER_COLUMN_POSITIONSHAREDATESINOUT_DATE_OUT_1);
		}
		else {
			bindDate_out = true;

			query.append(_FINDER_COLUMN_POSITIONSHAREDATESINOUT_DATE_OUT_2);
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
			query.append(PositionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(companyId);

		qPos.add(shareId);

		if (bindDate_real_in) {
			qPos.add(new Timestamp(date_real_in.getTime()));
		}

		if (bindDate_real_out) {
			qPos.add(new Timestamp(date_real_out.getTime()));
		}

		if (bindDate_in) {
			qPos.add(new Timestamp(date_in.getTime()));
		}

		if (bindDate_out) {
			qPos.add(new Timestamp(date_out.getTime()));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(position);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Position> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the positions where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_real_in = &#63; and date_real_out = &#63; and date_in = &#63; and date_out = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param date_real_in the date_real_in
	 * @param date_real_out the date_real_out
	 * @param date_in the date_in
	 * @param date_out the date_out
	 */
	@Override
	public void removeByPositionShareDatesInOut(long groupId, long companyId,
		long shareId, Date date_real_in, Date date_real_out, Date date_in,
		Date date_out) {
		for (Position position : findByPositionShareDatesInOut(groupId,
				companyId, shareId, date_real_in, date_real_out, date_in,
				date_out, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(position);
		}
	}

	/**
	 * Returns the number of positions where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_real_in = &#63; and date_real_out = &#63; and date_in = &#63; and date_out = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param date_real_in the date_real_in
	 * @param date_real_out the date_real_out
	 * @param date_in the date_in
	 * @param date_out the date_out
	 * @return the number of matching positions
	 */
	@Override
	public int countByPositionShareDatesInOut(long groupId, long companyId,
		long shareId, Date date_real_in, Date date_real_out, Date date_in,
		Date date_out) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_POSITIONSHAREDATESINOUT;

		Object[] finderArgs = new Object[] {
				groupId, companyId, shareId, date_real_in, date_real_out,
				date_in, date_out
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(8);

			query.append(_SQL_COUNT_POSITION_WHERE);

			query.append(_FINDER_COLUMN_POSITIONSHAREDATESINOUT_GROUPID_2);

			query.append(_FINDER_COLUMN_POSITIONSHAREDATESINOUT_COMPANYID_2);

			query.append(_FINDER_COLUMN_POSITIONSHAREDATESINOUT_SHAREID_2);

			boolean bindDate_real_in = false;

			if (date_real_in == null) {
				query.append(_FINDER_COLUMN_POSITIONSHAREDATESINOUT_DATE_REAL_IN_1);
			}
			else {
				bindDate_real_in = true;

				query.append(_FINDER_COLUMN_POSITIONSHAREDATESINOUT_DATE_REAL_IN_2);
			}

			boolean bindDate_real_out = false;

			if (date_real_out == null) {
				query.append(_FINDER_COLUMN_POSITIONSHAREDATESINOUT_DATE_REAL_OUT_1);
			}
			else {
				bindDate_real_out = true;

				query.append(_FINDER_COLUMN_POSITIONSHAREDATESINOUT_DATE_REAL_OUT_2);
			}

			boolean bindDate_in = false;

			if (date_in == null) {
				query.append(_FINDER_COLUMN_POSITIONSHAREDATESINOUT_DATE_IN_1);
			}
			else {
				bindDate_in = true;

				query.append(_FINDER_COLUMN_POSITIONSHAREDATESINOUT_DATE_IN_2);
			}

			boolean bindDate_out = false;

			if (date_out == null) {
				query.append(_FINDER_COLUMN_POSITIONSHAREDATESINOUT_DATE_OUT_1);
			}
			else {
				bindDate_out = true;

				query.append(_FINDER_COLUMN_POSITIONSHAREDATESINOUT_DATE_OUT_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(companyId);

				qPos.add(shareId);

				if (bindDate_real_in) {
					qPos.add(new Timestamp(date_real_in.getTime()));
				}

				if (bindDate_real_out) {
					qPos.add(new Timestamp(date_real_out.getTime()));
				}

				if (bindDate_in) {
					qPos.add(new Timestamp(date_in.getTime()));
				}

				if (bindDate_out) {
					qPos.add(new Timestamp(date_out.getTime()));
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

	private static final String _FINDER_COLUMN_POSITIONSHAREDATESINOUT_GROUPID_2 =
		"position.groupId = ? AND ";
	private static final String _FINDER_COLUMN_POSITIONSHAREDATESINOUT_COMPANYID_2 =
		"position.companyId = ? AND ";
	private static final String _FINDER_COLUMN_POSITIONSHAREDATESINOUT_SHAREID_2 =
		"position.shareId = ? AND ";
	private static final String _FINDER_COLUMN_POSITIONSHAREDATESINOUT_DATE_REAL_IN_1 =
		"position.date_real_in IS NULL AND ";
	private static final String _FINDER_COLUMN_POSITIONSHAREDATESINOUT_DATE_REAL_IN_2 =
		"position.date_real_in = ? AND ";
	private static final String _FINDER_COLUMN_POSITIONSHAREDATESINOUT_DATE_REAL_OUT_1 =
		"position.date_real_out IS NULL AND ";
	private static final String _FINDER_COLUMN_POSITIONSHAREDATESINOUT_DATE_REAL_OUT_2 =
		"position.date_real_out = ? AND ";
	private static final String _FINDER_COLUMN_POSITIONSHAREDATESINOUT_DATE_IN_1 =
		"position.date_in IS NULL AND ";
	private static final String _FINDER_COLUMN_POSITIONSHAREDATESINOUT_DATE_IN_2 =
		"position.date_in = ? AND ";
	private static final String _FINDER_COLUMN_POSITIONSHAREDATESINOUT_DATE_OUT_1 =
		"position.date_out IS NULL";
	private static final String _FINDER_COLUMN_POSITIONSHAREDATESINOUT_DATE_OUT_2 =
		"position.date_out = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_POSITIONSHARESTATEDATEOUT =
		new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, PositionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByPositionShareStateDateOut",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				String.class.getName(), Date.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_POSITIONSHARESTATEDATEOUT =
		new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, PositionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByPositionShareStateDateOut",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				String.class.getName(), Date.class.getName()
			},
			PositionModelImpl.GROUPID_COLUMN_BITMASK |
			PositionModelImpl.COMPANYID_COLUMN_BITMASK |
			PositionModelImpl.SHAREID_COLUMN_BITMASK |
			PositionModelImpl.STATE_COLUMN_BITMASK |
			PositionModelImpl.DATE_OUT_COLUMN_BITMASK |
			PositionModelImpl.POSITIONID_TWS_IN_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_POSITIONSHARESTATEDATEOUT =
		new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByPositionShareStateDateOut",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				String.class.getName(), Date.class.getName()
			});

	/**
	 * Returns all the positions where groupId = &#63; and companyId = &#63; and shareId = &#63; and state = &#63; and date_out = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param state the state
	 * @param date_out the date_out
	 * @return the matching positions
	 */
	@Override
	public List<Position> findByPositionShareStateDateOut(long groupId,
		long companyId, long shareId, String state, Date date_out) {
		return findByPositionShareStateDateOut(groupId, companyId, shareId,
			state, date_out, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the positions where groupId = &#63; and companyId = &#63; and shareId = &#63; and state = &#63; and date_out = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param state the state
	 * @param date_out the date_out
	 * @param start the lower bound of the range of positions
	 * @param end the upper bound of the range of positions (not inclusive)
	 * @return the range of matching positions
	 */
	@Override
	public List<Position> findByPositionShareStateDateOut(long groupId,
		long companyId, long shareId, String state, Date date_out, int start,
		int end) {
		return findByPositionShareStateDateOut(groupId, companyId, shareId,
			state, date_out, start, end, null);
	}

	/**
	 * Returns an ordered range of all the positions where groupId = &#63; and companyId = &#63; and shareId = &#63; and state = &#63; and date_out = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param state the state
	 * @param date_out the date_out
	 * @param start the lower bound of the range of positions
	 * @param end the upper bound of the range of positions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching positions
	 */
	@Override
	public List<Position> findByPositionShareStateDateOut(long groupId,
		long companyId, long shareId, String state, Date date_out, int start,
		int end, OrderByComparator<Position> orderByComparator) {
		return findByPositionShareStateDateOut(groupId, companyId, shareId,
			state, date_out, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the positions where groupId = &#63; and companyId = &#63; and shareId = &#63; and state = &#63; and date_out = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param state the state
	 * @param date_out the date_out
	 * @param start the lower bound of the range of positions
	 * @param end the upper bound of the range of positions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching positions
	 */
	@Override
	public List<Position> findByPositionShareStateDateOut(long groupId,
		long companyId, long shareId, String state, Date date_out, int start,
		int end, OrderByComparator<Position> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_POSITIONSHARESTATEDATEOUT;
			finderArgs = new Object[] {
					groupId, companyId, shareId, state, date_out
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_POSITIONSHARESTATEDATEOUT;
			finderArgs = new Object[] {
					groupId, companyId, shareId, state, date_out,
					
					start, end, orderByComparator
				};
		}

		List<Position> list = null;

		if (retrieveFromCache) {
			list = (List<Position>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Position position : list) {
					if ((groupId != position.getGroupId()) ||
							(companyId != position.getCompanyId()) ||
							(shareId != position.getShareId()) ||
							!Objects.equals(state, position.getState()) ||
							!Objects.equals(date_out, position.getDate_out())) {
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

			query.append(_SQL_SELECT_POSITION_WHERE);

			query.append(_FINDER_COLUMN_POSITIONSHARESTATEDATEOUT_GROUPID_2);

			query.append(_FINDER_COLUMN_POSITIONSHARESTATEDATEOUT_COMPANYID_2);

			query.append(_FINDER_COLUMN_POSITIONSHARESTATEDATEOUT_SHAREID_2);

			boolean bindState = false;

			if (state == null) {
				query.append(_FINDER_COLUMN_POSITIONSHARESTATEDATEOUT_STATE_1);
			}
			else if (state.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_POSITIONSHARESTATEDATEOUT_STATE_3);
			}
			else {
				bindState = true;

				query.append(_FINDER_COLUMN_POSITIONSHARESTATEDATEOUT_STATE_2);
			}

			boolean bindDate_out = false;

			if (date_out == null) {
				query.append(_FINDER_COLUMN_POSITIONSHARESTATEDATEOUT_DATE_OUT_1);
			}
			else {
				bindDate_out = true;

				query.append(_FINDER_COLUMN_POSITIONSHARESTATEDATEOUT_DATE_OUT_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PositionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(companyId);

				qPos.add(shareId);

				if (bindState) {
					qPos.add(state);
				}

				if (bindDate_out) {
					qPos.add(new Timestamp(date_out.getTime()));
				}

				if (!pagination) {
					list = (List<Position>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Position>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first position in the ordered set where groupId = &#63; and companyId = &#63; and shareId = &#63; and state = &#63; and date_out = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param state the state
	 * @param date_out the date_out
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching position
	 * @throws NoSuchPositionException if a matching position could not be found
	 */
	@Override
	public Position findByPositionShareStateDateOut_First(long groupId,
		long companyId, long shareId, String state, Date date_out,
		OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException {
		Position position = fetchByPositionShareStateDateOut_First(groupId,
				companyId, shareId, state, date_out, orderByComparator);

		if (position != null) {
			return position;
		}

		StringBundler msg = new StringBundler(12);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(", shareId=");
		msg.append(shareId);

		msg.append(", state=");
		msg.append(state);

		msg.append(", date_out=");
		msg.append(date_out);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPositionException(msg.toString());
	}

	/**
	 * Returns the first position in the ordered set where groupId = &#63; and companyId = &#63; and shareId = &#63; and state = &#63; and date_out = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param state the state
	 * @param date_out the date_out
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching position, or <code>null</code> if a matching position could not be found
	 */
	@Override
	public Position fetchByPositionShareStateDateOut_First(long groupId,
		long companyId, long shareId, String state, Date date_out,
		OrderByComparator<Position> orderByComparator) {
		List<Position> list = findByPositionShareStateDateOut(groupId,
				companyId, shareId, state, date_out, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last position in the ordered set where groupId = &#63; and companyId = &#63; and shareId = &#63; and state = &#63; and date_out = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param state the state
	 * @param date_out the date_out
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching position
	 * @throws NoSuchPositionException if a matching position could not be found
	 */
	@Override
	public Position findByPositionShareStateDateOut_Last(long groupId,
		long companyId, long shareId, String state, Date date_out,
		OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException {
		Position position = fetchByPositionShareStateDateOut_Last(groupId,
				companyId, shareId, state, date_out, orderByComparator);

		if (position != null) {
			return position;
		}

		StringBundler msg = new StringBundler(12);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(", shareId=");
		msg.append(shareId);

		msg.append(", state=");
		msg.append(state);

		msg.append(", date_out=");
		msg.append(date_out);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPositionException(msg.toString());
	}

	/**
	 * Returns the last position in the ordered set where groupId = &#63; and companyId = &#63; and shareId = &#63; and state = &#63; and date_out = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param state the state
	 * @param date_out the date_out
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching position, or <code>null</code> if a matching position could not be found
	 */
	@Override
	public Position fetchByPositionShareStateDateOut_Last(long groupId,
		long companyId, long shareId, String state, Date date_out,
		OrderByComparator<Position> orderByComparator) {
		int count = countByPositionShareStateDateOut(groupId, companyId,
				shareId, state, date_out);

		if (count == 0) {
			return null;
		}

		List<Position> list = findByPositionShareStateDateOut(groupId,
				companyId, shareId, state, date_out, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the positions before and after the current position in the ordered set where groupId = &#63; and companyId = &#63; and shareId = &#63; and state = &#63; and date_out = &#63;.
	 *
	 * @param positionId the primary key of the current position
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param state the state
	 * @param date_out the date_out
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next position
	 * @throws NoSuchPositionException if a position with the primary key could not be found
	 */
	@Override
	public Position[] findByPositionShareStateDateOut_PrevAndNext(
		long positionId, long groupId, long companyId, long shareId,
		String state, Date date_out,
		OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException {
		Position position = findByPrimaryKey(positionId);

		Session session = null;

		try {
			session = openSession();

			Position[] array = new PositionImpl[3];

			array[0] = getByPositionShareStateDateOut_PrevAndNext(session,
					position, groupId, companyId, shareId, state, date_out,
					orderByComparator, true);

			array[1] = position;

			array[2] = getByPositionShareStateDateOut_PrevAndNext(session,
					position, groupId, companyId, shareId, state, date_out,
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

	protected Position getByPositionShareStateDateOut_PrevAndNext(
		Session session, Position position, long groupId, long companyId,
		long shareId, String state, Date date_out,
		OrderByComparator<Position> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(8 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(7);
		}

		query.append(_SQL_SELECT_POSITION_WHERE);

		query.append(_FINDER_COLUMN_POSITIONSHARESTATEDATEOUT_GROUPID_2);

		query.append(_FINDER_COLUMN_POSITIONSHARESTATEDATEOUT_COMPANYID_2);

		query.append(_FINDER_COLUMN_POSITIONSHARESTATEDATEOUT_SHAREID_2);

		boolean bindState = false;

		if (state == null) {
			query.append(_FINDER_COLUMN_POSITIONSHARESTATEDATEOUT_STATE_1);
		}
		else if (state.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_POSITIONSHARESTATEDATEOUT_STATE_3);
		}
		else {
			bindState = true;

			query.append(_FINDER_COLUMN_POSITIONSHARESTATEDATEOUT_STATE_2);
		}

		boolean bindDate_out = false;

		if (date_out == null) {
			query.append(_FINDER_COLUMN_POSITIONSHARESTATEDATEOUT_DATE_OUT_1);
		}
		else {
			bindDate_out = true;

			query.append(_FINDER_COLUMN_POSITIONSHARESTATEDATEOUT_DATE_OUT_2);
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
			query.append(PositionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(companyId);

		qPos.add(shareId);

		if (bindState) {
			qPos.add(state);
		}

		if (bindDate_out) {
			qPos.add(new Timestamp(date_out.getTime()));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(position);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Position> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the positions where groupId = &#63; and companyId = &#63; and shareId = &#63; and state = &#63; and date_out = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param state the state
	 * @param date_out the date_out
	 */
	@Override
	public void removeByPositionShareStateDateOut(long groupId, long companyId,
		long shareId, String state, Date date_out) {
		for (Position position : findByPositionShareStateDateOut(groupId,
				companyId, shareId, state, date_out, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(position);
		}
	}

	/**
	 * Returns the number of positions where groupId = &#63; and companyId = &#63; and shareId = &#63; and state = &#63; and date_out = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param state the state
	 * @param date_out the date_out
	 * @return the number of matching positions
	 */
	@Override
	public int countByPositionShareStateDateOut(long groupId, long companyId,
		long shareId, String state, Date date_out) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_POSITIONSHARESTATEDATEOUT;

		Object[] finderArgs = new Object[] {
				groupId, companyId, shareId, state, date_out
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_COUNT_POSITION_WHERE);

			query.append(_FINDER_COLUMN_POSITIONSHARESTATEDATEOUT_GROUPID_2);

			query.append(_FINDER_COLUMN_POSITIONSHARESTATEDATEOUT_COMPANYID_2);

			query.append(_FINDER_COLUMN_POSITIONSHARESTATEDATEOUT_SHAREID_2);

			boolean bindState = false;

			if (state == null) {
				query.append(_FINDER_COLUMN_POSITIONSHARESTATEDATEOUT_STATE_1);
			}
			else if (state.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_POSITIONSHARESTATEDATEOUT_STATE_3);
			}
			else {
				bindState = true;

				query.append(_FINDER_COLUMN_POSITIONSHARESTATEDATEOUT_STATE_2);
			}

			boolean bindDate_out = false;

			if (date_out == null) {
				query.append(_FINDER_COLUMN_POSITIONSHARESTATEDATEOUT_DATE_OUT_1);
			}
			else {
				bindDate_out = true;

				query.append(_FINDER_COLUMN_POSITIONSHARESTATEDATEOUT_DATE_OUT_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(companyId);

				qPos.add(shareId);

				if (bindState) {
					qPos.add(state);
				}

				if (bindDate_out) {
					qPos.add(new Timestamp(date_out.getTime()));
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

	private static final String _FINDER_COLUMN_POSITIONSHARESTATEDATEOUT_GROUPID_2 =
		"position.groupId = ? AND ";
	private static final String _FINDER_COLUMN_POSITIONSHARESTATEDATEOUT_COMPANYID_2 =
		"position.companyId = ? AND ";
	private static final String _FINDER_COLUMN_POSITIONSHARESTATEDATEOUT_SHAREID_2 =
		"position.shareId = ? AND ";
	private static final String _FINDER_COLUMN_POSITIONSHARESTATEDATEOUT_STATE_1 =
		"position.state IS NULL AND ";
	private static final String _FINDER_COLUMN_POSITIONSHARESTATEDATEOUT_STATE_2 =
		"position.state = ? AND ";
	private static final String _FINDER_COLUMN_POSITIONSHARESTATEDATEOUT_STATE_3 =
		"(position.state IS NULL OR position.state = '') AND ";
	private static final String _FINDER_COLUMN_POSITIONSHARESTATEDATEOUT_DATE_OUT_1 =
		"position.date_out IS NULL";
	private static final String _FINDER_COLUMN_POSITIONSHARESTATEDATEOUT_DATE_OUT_2 =
		"position.date_out = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_POSITIONSHAREDATEIN =
		new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, PositionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByPositionShareDateIn",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Date.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_POSITIONSHAREDATEIN =
		new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, PositionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByPositionShareDateIn",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Date.class.getName()
			},
			PositionModelImpl.GROUPID_COLUMN_BITMASK |
			PositionModelImpl.COMPANYID_COLUMN_BITMASK |
			PositionModelImpl.SHAREID_COLUMN_BITMASK |
			PositionModelImpl.DATE_IN_COLUMN_BITMASK |
			PositionModelImpl.POSITIONID_TWS_IN_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_POSITIONSHAREDATEIN = new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByPositionShareDateIn",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Date.class.getName()
			});

	/**
	 * Returns all the positions where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_in = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param date_in the date_in
	 * @return the matching positions
	 */
	@Override
	public List<Position> findByPositionShareDateIn(long groupId,
		long companyId, long shareId, Date date_in) {
		return findByPositionShareDateIn(groupId, companyId, shareId, date_in,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the positions where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_in = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param date_in the date_in
	 * @param start the lower bound of the range of positions
	 * @param end the upper bound of the range of positions (not inclusive)
	 * @return the range of matching positions
	 */
	@Override
	public List<Position> findByPositionShareDateIn(long groupId,
		long companyId, long shareId, Date date_in, int start, int end) {
		return findByPositionShareDateIn(groupId, companyId, shareId, date_in,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the positions where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_in = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param date_in the date_in
	 * @param start the lower bound of the range of positions
	 * @param end the upper bound of the range of positions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching positions
	 */
	@Override
	public List<Position> findByPositionShareDateIn(long groupId,
		long companyId, long shareId, Date date_in, int start, int end,
		OrderByComparator<Position> orderByComparator) {
		return findByPositionShareDateIn(groupId, companyId, shareId, date_in,
			start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the positions where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_in = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param date_in the date_in
	 * @param start the lower bound of the range of positions
	 * @param end the upper bound of the range of positions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching positions
	 */
	@Override
	public List<Position> findByPositionShareDateIn(long groupId,
		long companyId, long shareId, Date date_in, int start, int end,
		OrderByComparator<Position> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_POSITIONSHAREDATEIN;
			finderArgs = new Object[] { groupId, companyId, shareId, date_in };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_POSITIONSHAREDATEIN;
			finderArgs = new Object[] {
					groupId, companyId, shareId, date_in,
					
					start, end, orderByComparator
				};
		}

		List<Position> list = null;

		if (retrieveFromCache) {
			list = (List<Position>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Position position : list) {
					if ((groupId != position.getGroupId()) ||
							(companyId != position.getCompanyId()) ||
							(shareId != position.getShareId()) ||
							!Objects.equals(date_in, position.getDate_in())) {
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

			query.append(_SQL_SELECT_POSITION_WHERE);

			query.append(_FINDER_COLUMN_POSITIONSHAREDATEIN_GROUPID_2);

			query.append(_FINDER_COLUMN_POSITIONSHAREDATEIN_COMPANYID_2);

			query.append(_FINDER_COLUMN_POSITIONSHAREDATEIN_SHAREID_2);

			boolean bindDate_in = false;

			if (date_in == null) {
				query.append(_FINDER_COLUMN_POSITIONSHAREDATEIN_DATE_IN_1);
			}
			else {
				bindDate_in = true;

				query.append(_FINDER_COLUMN_POSITIONSHAREDATEIN_DATE_IN_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PositionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(companyId);

				qPos.add(shareId);

				if (bindDate_in) {
					qPos.add(new Timestamp(date_in.getTime()));
				}

				if (!pagination) {
					list = (List<Position>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Position>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first position in the ordered set where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_in = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param date_in the date_in
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching position
	 * @throws NoSuchPositionException if a matching position could not be found
	 */
	@Override
	public Position findByPositionShareDateIn_First(long groupId,
		long companyId, long shareId, Date date_in,
		OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException {
		Position position = fetchByPositionShareDateIn_First(groupId,
				companyId, shareId, date_in, orderByComparator);

		if (position != null) {
			return position;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(", shareId=");
		msg.append(shareId);

		msg.append(", date_in=");
		msg.append(date_in);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPositionException(msg.toString());
	}

	/**
	 * Returns the first position in the ordered set where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_in = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param date_in the date_in
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching position, or <code>null</code> if a matching position could not be found
	 */
	@Override
	public Position fetchByPositionShareDateIn_First(long groupId,
		long companyId, long shareId, Date date_in,
		OrderByComparator<Position> orderByComparator) {
		List<Position> list = findByPositionShareDateIn(groupId, companyId,
				shareId, date_in, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last position in the ordered set where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_in = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param date_in the date_in
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching position
	 * @throws NoSuchPositionException if a matching position could not be found
	 */
	@Override
	public Position findByPositionShareDateIn_Last(long groupId,
		long companyId, long shareId, Date date_in,
		OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException {
		Position position = fetchByPositionShareDateIn_Last(groupId, companyId,
				shareId, date_in, orderByComparator);

		if (position != null) {
			return position;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(", shareId=");
		msg.append(shareId);

		msg.append(", date_in=");
		msg.append(date_in);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPositionException(msg.toString());
	}

	/**
	 * Returns the last position in the ordered set where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_in = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param date_in the date_in
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching position, or <code>null</code> if a matching position could not be found
	 */
	@Override
	public Position fetchByPositionShareDateIn_Last(long groupId,
		long companyId, long shareId, Date date_in,
		OrderByComparator<Position> orderByComparator) {
		int count = countByPositionShareDateIn(groupId, companyId, shareId,
				date_in);

		if (count == 0) {
			return null;
		}

		List<Position> list = findByPositionShareDateIn(groupId, companyId,
				shareId, date_in, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the positions before and after the current position in the ordered set where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_in = &#63;.
	 *
	 * @param positionId the primary key of the current position
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param date_in the date_in
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next position
	 * @throws NoSuchPositionException if a position with the primary key could not be found
	 */
	@Override
	public Position[] findByPositionShareDateIn_PrevAndNext(long positionId,
		long groupId, long companyId, long shareId, Date date_in,
		OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException {
		Position position = findByPrimaryKey(positionId);

		Session session = null;

		try {
			session = openSession();

			Position[] array = new PositionImpl[3];

			array[0] = getByPositionShareDateIn_PrevAndNext(session, position,
					groupId, companyId, shareId, date_in, orderByComparator,
					true);

			array[1] = position;

			array[2] = getByPositionShareDateIn_PrevAndNext(session, position,
					groupId, companyId, shareId, date_in, orderByComparator,
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

	protected Position getByPositionShareDateIn_PrevAndNext(Session session,
		Position position, long groupId, long companyId, long shareId,
		Date date_in, OrderByComparator<Position> orderByComparator,
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

		query.append(_SQL_SELECT_POSITION_WHERE);

		query.append(_FINDER_COLUMN_POSITIONSHAREDATEIN_GROUPID_2);

		query.append(_FINDER_COLUMN_POSITIONSHAREDATEIN_COMPANYID_2);

		query.append(_FINDER_COLUMN_POSITIONSHAREDATEIN_SHAREID_2);

		boolean bindDate_in = false;

		if (date_in == null) {
			query.append(_FINDER_COLUMN_POSITIONSHAREDATEIN_DATE_IN_1);
		}
		else {
			bindDate_in = true;

			query.append(_FINDER_COLUMN_POSITIONSHAREDATEIN_DATE_IN_2);
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
			query.append(PositionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(companyId);

		qPos.add(shareId);

		if (bindDate_in) {
			qPos.add(new Timestamp(date_in.getTime()));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(position);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Position> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the positions where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_in = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param date_in the date_in
	 */
	@Override
	public void removeByPositionShareDateIn(long groupId, long companyId,
		long shareId, Date date_in) {
		for (Position position : findByPositionShareDateIn(groupId, companyId,
				shareId, date_in, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(position);
		}
	}

	/**
	 * Returns the number of positions where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_in = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param date_in the date_in
	 * @return the number of matching positions
	 */
	@Override
	public int countByPositionShareDateIn(long groupId, long companyId,
		long shareId, Date date_in) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_POSITIONSHAREDATEIN;

		Object[] finderArgs = new Object[] { groupId, companyId, shareId, date_in };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_POSITION_WHERE);

			query.append(_FINDER_COLUMN_POSITIONSHAREDATEIN_GROUPID_2);

			query.append(_FINDER_COLUMN_POSITIONSHAREDATEIN_COMPANYID_2);

			query.append(_FINDER_COLUMN_POSITIONSHAREDATEIN_SHAREID_2);

			boolean bindDate_in = false;

			if (date_in == null) {
				query.append(_FINDER_COLUMN_POSITIONSHAREDATEIN_DATE_IN_1);
			}
			else {
				bindDate_in = true;

				query.append(_FINDER_COLUMN_POSITIONSHAREDATEIN_DATE_IN_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(companyId);

				qPos.add(shareId);

				if (bindDate_in) {
					qPos.add(new Timestamp(date_in.getTime()));
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

	private static final String _FINDER_COLUMN_POSITIONSHAREDATEIN_GROUPID_2 = "position.groupId = ? AND ";
	private static final String _FINDER_COLUMN_POSITIONSHAREDATEIN_COMPANYID_2 = "position.companyId = ? AND ";
	private static final String _FINDER_COLUMN_POSITIONSHAREDATEIN_SHAREID_2 = "position.shareId = ? AND ";
	private static final String _FINDER_COLUMN_POSITIONSHAREDATEIN_DATE_IN_1 = "position.date_in IS NULL";
	private static final String _FINDER_COLUMN_POSITIONSHAREDATEIN_DATE_IN_2 = "position.date_in = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_POSITIONSHAREDATEOUT =
		new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, PositionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByPositionShareDateOut",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Date.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_POSITIONSHAREDATEOUT =
		new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, PositionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByPositionShareDateOut",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Date.class.getName()
			},
			PositionModelImpl.GROUPID_COLUMN_BITMASK |
			PositionModelImpl.COMPANYID_COLUMN_BITMASK |
			PositionModelImpl.SHAREID_COLUMN_BITMASK |
			PositionModelImpl.DATE_OUT_COLUMN_BITMASK |
			PositionModelImpl.POSITIONID_TWS_IN_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_POSITIONSHAREDATEOUT = new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByPositionShareDateOut",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Date.class.getName()
			});

	/**
	 * Returns all the positions where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_out = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param date_out the date_out
	 * @return the matching positions
	 */
	@Override
	public List<Position> findByPositionShareDateOut(long groupId,
		long companyId, long shareId, Date date_out) {
		return findByPositionShareDateOut(groupId, companyId, shareId,
			date_out, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the positions where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_out = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param date_out the date_out
	 * @param start the lower bound of the range of positions
	 * @param end the upper bound of the range of positions (not inclusive)
	 * @return the range of matching positions
	 */
	@Override
	public List<Position> findByPositionShareDateOut(long groupId,
		long companyId, long shareId, Date date_out, int start, int end) {
		return findByPositionShareDateOut(groupId, companyId, shareId,
			date_out, start, end, null);
	}

	/**
	 * Returns an ordered range of all the positions where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_out = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param date_out the date_out
	 * @param start the lower bound of the range of positions
	 * @param end the upper bound of the range of positions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching positions
	 */
	@Override
	public List<Position> findByPositionShareDateOut(long groupId,
		long companyId, long shareId, Date date_out, int start, int end,
		OrderByComparator<Position> orderByComparator) {
		return findByPositionShareDateOut(groupId, companyId, shareId,
			date_out, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the positions where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_out = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param date_out the date_out
	 * @param start the lower bound of the range of positions
	 * @param end the upper bound of the range of positions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching positions
	 */
	@Override
	public List<Position> findByPositionShareDateOut(long groupId,
		long companyId, long shareId, Date date_out, int start, int end,
		OrderByComparator<Position> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_POSITIONSHAREDATEOUT;
			finderArgs = new Object[] { groupId, companyId, shareId, date_out };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_POSITIONSHAREDATEOUT;
			finderArgs = new Object[] {
					groupId, companyId, shareId, date_out,
					
					start, end, orderByComparator
				};
		}

		List<Position> list = null;

		if (retrieveFromCache) {
			list = (List<Position>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Position position : list) {
					if ((groupId != position.getGroupId()) ||
							(companyId != position.getCompanyId()) ||
							(shareId != position.getShareId()) ||
							!Objects.equals(date_out, position.getDate_out())) {
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

			query.append(_SQL_SELECT_POSITION_WHERE);

			query.append(_FINDER_COLUMN_POSITIONSHAREDATEOUT_GROUPID_2);

			query.append(_FINDER_COLUMN_POSITIONSHAREDATEOUT_COMPANYID_2);

			query.append(_FINDER_COLUMN_POSITIONSHAREDATEOUT_SHAREID_2);

			boolean bindDate_out = false;

			if (date_out == null) {
				query.append(_FINDER_COLUMN_POSITIONSHAREDATEOUT_DATE_OUT_1);
			}
			else {
				bindDate_out = true;

				query.append(_FINDER_COLUMN_POSITIONSHAREDATEOUT_DATE_OUT_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PositionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(companyId);

				qPos.add(shareId);

				if (bindDate_out) {
					qPos.add(new Timestamp(date_out.getTime()));
				}

				if (!pagination) {
					list = (List<Position>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Position>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first position in the ordered set where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_out = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param date_out the date_out
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching position
	 * @throws NoSuchPositionException if a matching position could not be found
	 */
	@Override
	public Position findByPositionShareDateOut_First(long groupId,
		long companyId, long shareId, Date date_out,
		OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException {
		Position position = fetchByPositionShareDateOut_First(groupId,
				companyId, shareId, date_out, orderByComparator);

		if (position != null) {
			return position;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(", shareId=");
		msg.append(shareId);

		msg.append(", date_out=");
		msg.append(date_out);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPositionException(msg.toString());
	}

	/**
	 * Returns the first position in the ordered set where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_out = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param date_out the date_out
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching position, or <code>null</code> if a matching position could not be found
	 */
	@Override
	public Position fetchByPositionShareDateOut_First(long groupId,
		long companyId, long shareId, Date date_out,
		OrderByComparator<Position> orderByComparator) {
		List<Position> list = findByPositionShareDateOut(groupId, companyId,
				shareId, date_out, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last position in the ordered set where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_out = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param date_out the date_out
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching position
	 * @throws NoSuchPositionException if a matching position could not be found
	 */
	@Override
	public Position findByPositionShareDateOut_Last(long groupId,
		long companyId, long shareId, Date date_out,
		OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException {
		Position position = fetchByPositionShareDateOut_Last(groupId,
				companyId, shareId, date_out, orderByComparator);

		if (position != null) {
			return position;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(", shareId=");
		msg.append(shareId);

		msg.append(", date_out=");
		msg.append(date_out);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPositionException(msg.toString());
	}

	/**
	 * Returns the last position in the ordered set where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_out = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param date_out the date_out
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching position, or <code>null</code> if a matching position could not be found
	 */
	@Override
	public Position fetchByPositionShareDateOut_Last(long groupId,
		long companyId, long shareId, Date date_out,
		OrderByComparator<Position> orderByComparator) {
		int count = countByPositionShareDateOut(groupId, companyId, shareId,
				date_out);

		if (count == 0) {
			return null;
		}

		List<Position> list = findByPositionShareDateOut(groupId, companyId,
				shareId, date_out, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the positions before and after the current position in the ordered set where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_out = &#63;.
	 *
	 * @param positionId the primary key of the current position
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param date_out the date_out
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next position
	 * @throws NoSuchPositionException if a position with the primary key could not be found
	 */
	@Override
	public Position[] findByPositionShareDateOut_PrevAndNext(long positionId,
		long groupId, long companyId, long shareId, Date date_out,
		OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException {
		Position position = findByPrimaryKey(positionId);

		Session session = null;

		try {
			session = openSession();

			Position[] array = new PositionImpl[3];

			array[0] = getByPositionShareDateOut_PrevAndNext(session, position,
					groupId, companyId, shareId, date_out, orderByComparator,
					true);

			array[1] = position;

			array[2] = getByPositionShareDateOut_PrevAndNext(session, position,
					groupId, companyId, shareId, date_out, orderByComparator,
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

	protected Position getByPositionShareDateOut_PrevAndNext(Session session,
		Position position, long groupId, long companyId, long shareId,
		Date date_out, OrderByComparator<Position> orderByComparator,
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

		query.append(_SQL_SELECT_POSITION_WHERE);

		query.append(_FINDER_COLUMN_POSITIONSHAREDATEOUT_GROUPID_2);

		query.append(_FINDER_COLUMN_POSITIONSHAREDATEOUT_COMPANYID_2);

		query.append(_FINDER_COLUMN_POSITIONSHAREDATEOUT_SHAREID_2);

		boolean bindDate_out = false;

		if (date_out == null) {
			query.append(_FINDER_COLUMN_POSITIONSHAREDATEOUT_DATE_OUT_1);
		}
		else {
			bindDate_out = true;

			query.append(_FINDER_COLUMN_POSITIONSHAREDATEOUT_DATE_OUT_2);
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
			query.append(PositionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(companyId);

		qPos.add(shareId);

		if (bindDate_out) {
			qPos.add(new Timestamp(date_out.getTime()));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(position);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Position> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the positions where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_out = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param date_out the date_out
	 */
	@Override
	public void removeByPositionShareDateOut(long groupId, long companyId,
		long shareId, Date date_out) {
		for (Position position : findByPositionShareDateOut(groupId, companyId,
				shareId, date_out, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(position);
		}
	}

	/**
	 * Returns the number of positions where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_out = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param date_out the date_out
	 * @return the number of matching positions
	 */
	@Override
	public int countByPositionShareDateOut(long groupId, long companyId,
		long shareId, Date date_out) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_POSITIONSHAREDATEOUT;

		Object[] finderArgs = new Object[] { groupId, companyId, shareId, date_out };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_POSITION_WHERE);

			query.append(_FINDER_COLUMN_POSITIONSHAREDATEOUT_GROUPID_2);

			query.append(_FINDER_COLUMN_POSITIONSHAREDATEOUT_COMPANYID_2);

			query.append(_FINDER_COLUMN_POSITIONSHAREDATEOUT_SHAREID_2);

			boolean bindDate_out = false;

			if (date_out == null) {
				query.append(_FINDER_COLUMN_POSITIONSHAREDATEOUT_DATE_OUT_1);
			}
			else {
				bindDate_out = true;

				query.append(_FINDER_COLUMN_POSITIONSHAREDATEOUT_DATE_OUT_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(companyId);

				qPos.add(shareId);

				if (bindDate_out) {
					qPos.add(new Timestamp(date_out.getTime()));
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

	private static final String _FINDER_COLUMN_POSITIONSHAREDATEOUT_GROUPID_2 = "position.groupId = ? AND ";
	private static final String _FINDER_COLUMN_POSITIONSHAREDATEOUT_COMPANYID_2 = "position.companyId = ? AND ";
	private static final String _FINDER_COLUMN_POSITIONSHAREDATEOUT_SHAREID_2 = "position.shareId = ? AND ";
	private static final String _FINDER_COLUMN_POSITIONSHAREDATEOUT_DATE_OUT_1 = "position.date_out IS NULL";
	private static final String _FINDER_COLUMN_POSITIONSHAREDATEOUT_DATE_OUT_2 = "position.date_out = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYGROUPSHARE =
		new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, PositionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyGroupShare",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYGROUPSHARE =
		new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, PositionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCompanyGroupShare",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			PositionModelImpl.GROUPID_COLUMN_BITMASK |
			PositionModelImpl.COMPANYID_COLUMN_BITMASK |
			PositionModelImpl.SHAREID_COLUMN_BITMASK |
			PositionModelImpl.POSITIONID_TWS_IN_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYGROUPSHARE = new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCompanyGroupShare",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});

	/**
	 * Returns all the positions where groupId = &#63; and companyId = &#63; and shareId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @return the matching positions
	 */
	@Override
	public List<Position> findByCompanyGroupShare(long groupId, long companyId,
		long shareId) {
		return findByCompanyGroupShare(groupId, companyId, shareId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the positions where groupId = &#63; and companyId = &#63; and shareId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param start the lower bound of the range of positions
	 * @param end the upper bound of the range of positions (not inclusive)
	 * @return the range of matching positions
	 */
	@Override
	public List<Position> findByCompanyGroupShare(long groupId, long companyId,
		long shareId, int start, int end) {
		return findByCompanyGroupShare(groupId, companyId, shareId, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the positions where groupId = &#63; and companyId = &#63; and shareId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param start the lower bound of the range of positions
	 * @param end the upper bound of the range of positions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching positions
	 */
	@Override
	public List<Position> findByCompanyGroupShare(long groupId, long companyId,
		long shareId, int start, int end,
		OrderByComparator<Position> orderByComparator) {
		return findByCompanyGroupShare(groupId, companyId, shareId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the positions where groupId = &#63; and companyId = &#63; and shareId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param start the lower bound of the range of positions
	 * @param end the upper bound of the range of positions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching positions
	 */
	@Override
	public List<Position> findByCompanyGroupShare(long groupId, long companyId,
		long shareId, int start, int end,
		OrderByComparator<Position> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYGROUPSHARE;
			finderArgs = new Object[] { groupId, companyId, shareId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYGROUPSHARE;
			finderArgs = new Object[] {
					groupId, companyId, shareId,
					
					start, end, orderByComparator
				};
		}

		List<Position> list = null;

		if (retrieveFromCache) {
			list = (List<Position>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Position position : list) {
					if ((groupId != position.getGroupId()) ||
							(companyId != position.getCompanyId()) ||
							(shareId != position.getShareId())) {
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

			query.append(_SQL_SELECT_POSITION_WHERE);

			query.append(_FINDER_COLUMN_COMPANYGROUPSHARE_GROUPID_2);

			query.append(_FINDER_COLUMN_COMPANYGROUPSHARE_COMPANYID_2);

			query.append(_FINDER_COLUMN_COMPANYGROUPSHARE_SHAREID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PositionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(companyId);

				qPos.add(shareId);

				if (!pagination) {
					list = (List<Position>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Position>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first position in the ordered set where groupId = &#63; and companyId = &#63; and shareId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching position
	 * @throws NoSuchPositionException if a matching position could not be found
	 */
	@Override
	public Position findByCompanyGroupShare_First(long groupId, long companyId,
		long shareId, OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException {
		Position position = fetchByCompanyGroupShare_First(groupId, companyId,
				shareId, orderByComparator);

		if (position != null) {
			return position;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(", shareId=");
		msg.append(shareId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPositionException(msg.toString());
	}

	/**
	 * Returns the first position in the ordered set where groupId = &#63; and companyId = &#63; and shareId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching position, or <code>null</code> if a matching position could not be found
	 */
	@Override
	public Position fetchByCompanyGroupShare_First(long groupId,
		long companyId, long shareId,
		OrderByComparator<Position> orderByComparator) {
		List<Position> list = findByCompanyGroupShare(groupId, companyId,
				shareId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last position in the ordered set where groupId = &#63; and companyId = &#63; and shareId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching position
	 * @throws NoSuchPositionException if a matching position could not be found
	 */
	@Override
	public Position findByCompanyGroupShare_Last(long groupId, long companyId,
		long shareId, OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException {
		Position position = fetchByCompanyGroupShare_Last(groupId, companyId,
				shareId, orderByComparator);

		if (position != null) {
			return position;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(", shareId=");
		msg.append(shareId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPositionException(msg.toString());
	}

	/**
	 * Returns the last position in the ordered set where groupId = &#63; and companyId = &#63; and shareId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching position, or <code>null</code> if a matching position could not be found
	 */
	@Override
	public Position fetchByCompanyGroupShare_Last(long groupId, long companyId,
		long shareId, OrderByComparator<Position> orderByComparator) {
		int count = countByCompanyGroupShare(groupId, companyId, shareId);

		if (count == 0) {
			return null;
		}

		List<Position> list = findByCompanyGroupShare(groupId, companyId,
				shareId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the positions before and after the current position in the ordered set where groupId = &#63; and companyId = &#63; and shareId = &#63;.
	 *
	 * @param positionId the primary key of the current position
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next position
	 * @throws NoSuchPositionException if a position with the primary key could not be found
	 */
	@Override
	public Position[] findByCompanyGroupShare_PrevAndNext(long positionId,
		long groupId, long companyId, long shareId,
		OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException {
		Position position = findByPrimaryKey(positionId);

		Session session = null;

		try {
			session = openSession();

			Position[] array = new PositionImpl[3];

			array[0] = getByCompanyGroupShare_PrevAndNext(session, position,
					groupId, companyId, shareId, orderByComparator, true);

			array[1] = position;

			array[2] = getByCompanyGroupShare_PrevAndNext(session, position,
					groupId, companyId, shareId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Position getByCompanyGroupShare_PrevAndNext(Session session,
		Position position, long groupId, long companyId, long shareId,
		OrderByComparator<Position> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_POSITION_WHERE);

		query.append(_FINDER_COLUMN_COMPANYGROUPSHARE_GROUPID_2);

		query.append(_FINDER_COLUMN_COMPANYGROUPSHARE_COMPANYID_2);

		query.append(_FINDER_COLUMN_COMPANYGROUPSHARE_SHAREID_2);

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
			query.append(PositionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(companyId);

		qPos.add(shareId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(position);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Position> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the positions where groupId = &#63; and companyId = &#63; and shareId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 */
	@Override
	public void removeByCompanyGroupShare(long groupId, long companyId,
		long shareId) {
		for (Position position : findByCompanyGroupShare(groupId, companyId,
				shareId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(position);
		}
	}

	/**
	 * Returns the number of positions where groupId = &#63; and companyId = &#63; and shareId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param shareId the share ID
	 * @return the number of matching positions
	 */
	@Override
	public int countByCompanyGroupShare(long groupId, long companyId,
		long shareId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYGROUPSHARE;

		Object[] finderArgs = new Object[] { groupId, companyId, shareId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_POSITION_WHERE);

			query.append(_FINDER_COLUMN_COMPANYGROUPSHARE_GROUPID_2);

			query.append(_FINDER_COLUMN_COMPANYGROUPSHARE_COMPANYID_2);

			query.append(_FINDER_COLUMN_COMPANYGROUPSHARE_SHAREID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

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

	private static final String _FINDER_COLUMN_COMPANYGROUPSHARE_GROUPID_2 = "position.groupId = ? AND ";
	private static final String _FINDER_COLUMN_COMPANYGROUPSHARE_COMPANYID_2 = "position.companyId = ? AND ";
	private static final String _FINDER_COLUMN_COMPANYGROUPSHARE_SHAREID_2 = "position.shareId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYGROUP =
		new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, PositionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyGroup",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYGROUP =
		new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, PositionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyGroup",
			new String[] { Long.class.getName(), Long.class.getName() },
			PositionModelImpl.COMPANYID_COLUMN_BITMASK |
			PositionModelImpl.GROUPID_COLUMN_BITMASK |
			PositionModelImpl.POSITIONID_TWS_IN_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYGROUP = new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyGroup",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the positions where companyId = &#63; and groupId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @return the matching positions
	 */
	@Override
	public List<Position> findByCompanyGroup(long companyId, long groupId) {
		return findByCompanyGroup(companyId, groupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the positions where companyId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of positions
	 * @param end the upper bound of the range of positions (not inclusive)
	 * @return the range of matching positions
	 */
	@Override
	public List<Position> findByCompanyGroup(long companyId, long groupId,
		int start, int end) {
		return findByCompanyGroup(companyId, groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the positions where companyId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of positions
	 * @param end the upper bound of the range of positions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching positions
	 */
	@Override
	public List<Position> findByCompanyGroup(long companyId, long groupId,
		int start, int end, OrderByComparator<Position> orderByComparator) {
		return findByCompanyGroup(companyId, groupId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the positions where companyId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of positions
	 * @param end the upper bound of the range of positions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching positions
	 */
	@Override
	public List<Position> findByCompanyGroup(long companyId, long groupId,
		int start, int end, OrderByComparator<Position> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYGROUP;
			finderArgs = new Object[] { companyId, groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYGROUP;
			finderArgs = new Object[] {
					companyId, groupId,
					
					start, end, orderByComparator
				};
		}

		List<Position> list = null;

		if (retrieveFromCache) {
			list = (List<Position>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Position position : list) {
					if ((companyId != position.getCompanyId()) ||
							(groupId != position.getGroupId())) {
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

			query.append(_SQL_SELECT_POSITION_WHERE);

			query.append(_FINDER_COLUMN_COMPANYGROUP_COMPANYID_2);

			query.append(_FINDER_COLUMN_COMPANYGROUP_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PositionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<Position>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Position>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first position in the ordered set where companyId = &#63; and groupId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching position
	 * @throws NoSuchPositionException if a matching position could not be found
	 */
	@Override
	public Position findByCompanyGroup_First(long companyId, long groupId,
		OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException {
		Position position = fetchByCompanyGroup_First(companyId, groupId,
				orderByComparator);

		if (position != null) {
			return position;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPositionException(msg.toString());
	}

	/**
	 * Returns the first position in the ordered set where companyId = &#63; and groupId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching position, or <code>null</code> if a matching position could not be found
	 */
	@Override
	public Position fetchByCompanyGroup_First(long companyId, long groupId,
		OrderByComparator<Position> orderByComparator) {
		List<Position> list = findByCompanyGroup(companyId, groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last position in the ordered set where companyId = &#63; and groupId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching position
	 * @throws NoSuchPositionException if a matching position could not be found
	 */
	@Override
	public Position findByCompanyGroup_Last(long companyId, long groupId,
		OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException {
		Position position = fetchByCompanyGroup_Last(companyId, groupId,
				orderByComparator);

		if (position != null) {
			return position;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPositionException(msg.toString());
	}

	/**
	 * Returns the last position in the ordered set where companyId = &#63; and groupId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching position, or <code>null</code> if a matching position could not be found
	 */
	@Override
	public Position fetchByCompanyGroup_Last(long companyId, long groupId,
		OrderByComparator<Position> orderByComparator) {
		int count = countByCompanyGroup(companyId, groupId);

		if (count == 0) {
			return null;
		}

		List<Position> list = findByCompanyGroup(companyId, groupId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the positions before and after the current position in the ordered set where companyId = &#63; and groupId = &#63;.
	 *
	 * @param positionId the primary key of the current position
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next position
	 * @throws NoSuchPositionException if a position with the primary key could not be found
	 */
	@Override
	public Position[] findByCompanyGroup_PrevAndNext(long positionId,
		long companyId, long groupId,
		OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException {
		Position position = findByPrimaryKey(positionId);

		Session session = null;

		try {
			session = openSession();

			Position[] array = new PositionImpl[3];

			array[0] = getByCompanyGroup_PrevAndNext(session, position,
					companyId, groupId, orderByComparator, true);

			array[1] = position;

			array[2] = getByCompanyGroup_PrevAndNext(session, position,
					companyId, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Position getByCompanyGroup_PrevAndNext(Session session,
		Position position, long companyId, long groupId,
		OrderByComparator<Position> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_POSITION_WHERE);

		query.append(_FINDER_COLUMN_COMPANYGROUP_COMPANYID_2);

		query.append(_FINDER_COLUMN_COMPANYGROUP_GROUPID_2);

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
			query.append(PositionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(position);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Position> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the positions where companyId = &#63; and groupId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 */
	@Override
	public void removeByCompanyGroup(long companyId, long groupId) {
		for (Position position : findByCompanyGroup(companyId, groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(position);
		}
	}

	/**
	 * Returns the number of positions where companyId = &#63; and groupId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @return the number of matching positions
	 */
	@Override
	public int countByCompanyGroup(long companyId, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYGROUP;

		Object[] finderArgs = new Object[] { companyId, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_POSITION_WHERE);

			query.append(_FINDER_COLUMN_COMPANYGROUP_COMPANYID_2);

			query.append(_FINDER_COLUMN_COMPANYGROUP_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

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

	private static final String _FINDER_COLUMN_COMPANYGROUP_COMPANYID_2 = "position.companyId = ? AND ";
	private static final String _FINDER_COLUMN_COMPANYGROUP_GROUPID_2 = "position.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYGROUPDATE =
		new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, PositionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyGroupDate",
			new String[] {
				Long.class.getName(), Long.class.getName(), Date.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYGROUPDATE =
		new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, PositionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCompanyGroupDate",
			new String[] {
				Long.class.getName(), Long.class.getName(), Date.class.getName()
			},
			PositionModelImpl.COMPANYID_COLUMN_BITMASK |
			PositionModelImpl.GROUPID_COLUMN_BITMASK |
			PositionModelImpl.DATE_IN_COLUMN_BITMASK |
			PositionModelImpl.POSITIONID_TWS_IN_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYGROUPDATE = new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCompanyGroupDate",
			new String[] {
				Long.class.getName(), Long.class.getName(), Date.class.getName()
			});

	/**
	 * Returns all the positions where companyId = &#63; and groupId = &#63; and date_in = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param date_in the date_in
	 * @return the matching positions
	 */
	@Override
	public List<Position> findByCompanyGroupDate(long companyId, long groupId,
		Date date_in) {
		return findByCompanyGroupDate(companyId, groupId, date_in,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the positions where companyId = &#63; and groupId = &#63; and date_in = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param date_in the date_in
	 * @param start the lower bound of the range of positions
	 * @param end the upper bound of the range of positions (not inclusive)
	 * @return the range of matching positions
	 */
	@Override
	public List<Position> findByCompanyGroupDate(long companyId, long groupId,
		Date date_in, int start, int end) {
		return findByCompanyGroupDate(companyId, groupId, date_in, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the positions where companyId = &#63; and groupId = &#63; and date_in = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param date_in the date_in
	 * @param start the lower bound of the range of positions
	 * @param end the upper bound of the range of positions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching positions
	 */
	@Override
	public List<Position> findByCompanyGroupDate(long companyId, long groupId,
		Date date_in, int start, int end,
		OrderByComparator<Position> orderByComparator) {
		return findByCompanyGroupDate(companyId, groupId, date_in, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the positions where companyId = &#63; and groupId = &#63; and date_in = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param date_in the date_in
	 * @param start the lower bound of the range of positions
	 * @param end the upper bound of the range of positions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching positions
	 */
	@Override
	public List<Position> findByCompanyGroupDate(long companyId, long groupId,
		Date date_in, int start, int end,
		OrderByComparator<Position> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYGROUPDATE;
			finderArgs = new Object[] { companyId, groupId, date_in };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYGROUPDATE;
			finderArgs = new Object[] {
					companyId, groupId, date_in,
					
					start, end, orderByComparator
				};
		}

		List<Position> list = null;

		if (retrieveFromCache) {
			list = (List<Position>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Position position : list) {
					if ((companyId != position.getCompanyId()) ||
							(groupId != position.getGroupId()) ||
							!Objects.equals(date_in, position.getDate_in())) {
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

			query.append(_SQL_SELECT_POSITION_WHERE);

			query.append(_FINDER_COLUMN_COMPANYGROUPDATE_COMPANYID_2);

			query.append(_FINDER_COLUMN_COMPANYGROUPDATE_GROUPID_2);

			boolean bindDate_in = false;

			if (date_in == null) {
				query.append(_FINDER_COLUMN_COMPANYGROUPDATE_DATE_IN_1);
			}
			else {
				bindDate_in = true;

				query.append(_FINDER_COLUMN_COMPANYGROUPDATE_DATE_IN_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PositionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(groupId);

				if (bindDate_in) {
					qPos.add(new Timestamp(date_in.getTime()));
				}

				if (!pagination) {
					list = (List<Position>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Position>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first position in the ordered set where companyId = &#63; and groupId = &#63; and date_in = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param date_in the date_in
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching position
	 * @throws NoSuchPositionException if a matching position could not be found
	 */
	@Override
	public Position findByCompanyGroupDate_First(long companyId, long groupId,
		Date date_in, OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException {
		Position position = fetchByCompanyGroupDate_First(companyId, groupId,
				date_in, orderByComparator);

		if (position != null) {
			return position;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(", date_in=");
		msg.append(date_in);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPositionException(msg.toString());
	}

	/**
	 * Returns the first position in the ordered set where companyId = &#63; and groupId = &#63; and date_in = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param date_in the date_in
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching position, or <code>null</code> if a matching position could not be found
	 */
	@Override
	public Position fetchByCompanyGroupDate_First(long companyId, long groupId,
		Date date_in, OrderByComparator<Position> orderByComparator) {
		List<Position> list = findByCompanyGroupDate(companyId, groupId,
				date_in, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last position in the ordered set where companyId = &#63; and groupId = &#63; and date_in = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param date_in the date_in
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching position
	 * @throws NoSuchPositionException if a matching position could not be found
	 */
	@Override
	public Position findByCompanyGroupDate_Last(long companyId, long groupId,
		Date date_in, OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException {
		Position position = fetchByCompanyGroupDate_Last(companyId, groupId,
				date_in, orderByComparator);

		if (position != null) {
			return position;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(", date_in=");
		msg.append(date_in);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPositionException(msg.toString());
	}

	/**
	 * Returns the last position in the ordered set where companyId = &#63; and groupId = &#63; and date_in = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param date_in the date_in
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching position, or <code>null</code> if a matching position could not be found
	 */
	@Override
	public Position fetchByCompanyGroupDate_Last(long companyId, long groupId,
		Date date_in, OrderByComparator<Position> orderByComparator) {
		int count = countByCompanyGroupDate(companyId, groupId, date_in);

		if (count == 0) {
			return null;
		}

		List<Position> list = findByCompanyGroupDate(companyId, groupId,
				date_in, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the positions before and after the current position in the ordered set where companyId = &#63; and groupId = &#63; and date_in = &#63;.
	 *
	 * @param positionId the primary key of the current position
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param date_in the date_in
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next position
	 * @throws NoSuchPositionException if a position with the primary key could not be found
	 */
	@Override
	public Position[] findByCompanyGroupDate_PrevAndNext(long positionId,
		long companyId, long groupId, Date date_in,
		OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException {
		Position position = findByPrimaryKey(positionId);

		Session session = null;

		try {
			session = openSession();

			Position[] array = new PositionImpl[3];

			array[0] = getByCompanyGroupDate_PrevAndNext(session, position,
					companyId, groupId, date_in, orderByComparator, true);

			array[1] = position;

			array[2] = getByCompanyGroupDate_PrevAndNext(session, position,
					companyId, groupId, date_in, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Position getByCompanyGroupDate_PrevAndNext(Session session,
		Position position, long companyId, long groupId, Date date_in,
		OrderByComparator<Position> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_POSITION_WHERE);

		query.append(_FINDER_COLUMN_COMPANYGROUPDATE_COMPANYID_2);

		query.append(_FINDER_COLUMN_COMPANYGROUPDATE_GROUPID_2);

		boolean bindDate_in = false;

		if (date_in == null) {
			query.append(_FINDER_COLUMN_COMPANYGROUPDATE_DATE_IN_1);
		}
		else {
			bindDate_in = true;

			query.append(_FINDER_COLUMN_COMPANYGROUPDATE_DATE_IN_2);
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
			query.append(PositionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(groupId);

		if (bindDate_in) {
			qPos.add(new Timestamp(date_in.getTime()));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(position);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Position> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the positions where companyId = &#63; and groupId = &#63; and date_in = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param date_in the date_in
	 */
	@Override
	public void removeByCompanyGroupDate(long companyId, long groupId,
		Date date_in) {
		for (Position position : findByCompanyGroupDate(companyId, groupId,
				date_in, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(position);
		}
	}

	/**
	 * Returns the number of positions where companyId = &#63; and groupId = &#63; and date_in = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param date_in the date_in
	 * @return the number of matching positions
	 */
	@Override
	public int countByCompanyGroupDate(long companyId, long groupId,
		Date date_in) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYGROUPDATE;

		Object[] finderArgs = new Object[] { companyId, groupId, date_in };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_POSITION_WHERE);

			query.append(_FINDER_COLUMN_COMPANYGROUPDATE_COMPANYID_2);

			query.append(_FINDER_COLUMN_COMPANYGROUPDATE_GROUPID_2);

			boolean bindDate_in = false;

			if (date_in == null) {
				query.append(_FINDER_COLUMN_COMPANYGROUPDATE_DATE_IN_1);
			}
			else {
				bindDate_in = true;

				query.append(_FINDER_COLUMN_COMPANYGROUPDATE_DATE_IN_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(groupId);

				if (bindDate_in) {
					qPos.add(new Timestamp(date_in.getTime()));
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

	private static final String _FINDER_COLUMN_COMPANYGROUPDATE_COMPANYID_2 = "position.companyId = ? AND ";
	private static final String _FINDER_COLUMN_COMPANYGROUPDATE_GROUPID_2 = "position.groupId = ? AND ";
	private static final String _FINDER_COLUMN_COMPANYGROUPDATE_DATE_IN_1 = "position.date_in IS NULL";
	private static final String _FINDER_COLUMN_COMPANYGROUPDATE_DATE_IN_2 = "position.date_in = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYGROUPDATESTATUS =
		new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, PositionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCompanyGroupDateStatus",
			new String[] {
				Long.class.getName(), Long.class.getName(), Date.class.getName(),
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYGROUPDATESTATUS =
		new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, PositionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCompanyGroupDateStatus",
			new String[] {
				Long.class.getName(), Long.class.getName(), Date.class.getName(),
				String.class.getName()
			},
			PositionModelImpl.COMPANYID_COLUMN_BITMASK |
			PositionModelImpl.GROUPID_COLUMN_BITMASK |
			PositionModelImpl.DATE_IN_COLUMN_BITMASK |
			PositionModelImpl.STATE_COLUMN_BITMASK |
			PositionModelImpl.POSITIONID_TWS_IN_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYGROUPDATESTATUS = new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCompanyGroupDateStatus",
			new String[] {
				Long.class.getName(), Long.class.getName(), Date.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns all the positions where companyId = &#63; and groupId = &#63; and date_in = &#63; and state = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param date_in the date_in
	 * @param state the state
	 * @return the matching positions
	 */
	@Override
	public List<Position> findByCompanyGroupDateStatus(long companyId,
		long groupId, Date date_in, String state) {
		return findByCompanyGroupDateStatus(companyId, groupId, date_in, state,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the positions where companyId = &#63; and groupId = &#63; and date_in = &#63; and state = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param date_in the date_in
	 * @param state the state
	 * @param start the lower bound of the range of positions
	 * @param end the upper bound of the range of positions (not inclusive)
	 * @return the range of matching positions
	 */
	@Override
	public List<Position> findByCompanyGroupDateStatus(long companyId,
		long groupId, Date date_in, String state, int start, int end) {
		return findByCompanyGroupDateStatus(companyId, groupId, date_in, state,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the positions where companyId = &#63; and groupId = &#63; and date_in = &#63; and state = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param date_in the date_in
	 * @param state the state
	 * @param start the lower bound of the range of positions
	 * @param end the upper bound of the range of positions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching positions
	 */
	@Override
	public List<Position> findByCompanyGroupDateStatus(long companyId,
		long groupId, Date date_in, String state, int start, int end,
		OrderByComparator<Position> orderByComparator) {
		return findByCompanyGroupDateStatus(companyId, groupId, date_in, state,
			start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the positions where companyId = &#63; and groupId = &#63; and date_in = &#63; and state = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param date_in the date_in
	 * @param state the state
	 * @param start the lower bound of the range of positions
	 * @param end the upper bound of the range of positions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching positions
	 */
	@Override
	public List<Position> findByCompanyGroupDateStatus(long companyId,
		long groupId, Date date_in, String state, int start, int end,
		OrderByComparator<Position> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYGROUPDATESTATUS;
			finderArgs = new Object[] { companyId, groupId, date_in, state };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYGROUPDATESTATUS;
			finderArgs = new Object[] {
					companyId, groupId, date_in, state,
					
					start, end, orderByComparator
				};
		}

		List<Position> list = null;

		if (retrieveFromCache) {
			list = (List<Position>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Position position : list) {
					if ((companyId != position.getCompanyId()) ||
							(groupId != position.getGroupId()) ||
							!Objects.equals(date_in, position.getDate_in()) ||
							!Objects.equals(state, position.getState())) {
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

			query.append(_SQL_SELECT_POSITION_WHERE);

			query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUS_COMPANYID_2);

			query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUS_GROUPID_2);

			boolean bindDate_in = false;

			if (date_in == null) {
				query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUS_DATE_IN_1);
			}
			else {
				bindDate_in = true;

				query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUS_DATE_IN_2);
			}

			boolean bindState = false;

			if (state == null) {
				query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUS_STATE_1);
			}
			else if (state.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUS_STATE_3);
			}
			else {
				bindState = true;

				query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUS_STATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PositionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(groupId);

				if (bindDate_in) {
					qPos.add(new Timestamp(date_in.getTime()));
				}

				if (bindState) {
					qPos.add(state);
				}

				if (!pagination) {
					list = (List<Position>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Position>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first position in the ordered set where companyId = &#63; and groupId = &#63; and date_in = &#63; and state = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param date_in the date_in
	 * @param state the state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching position
	 * @throws NoSuchPositionException if a matching position could not be found
	 */
	@Override
	public Position findByCompanyGroupDateStatus_First(long companyId,
		long groupId, Date date_in, String state,
		OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException {
		Position position = fetchByCompanyGroupDateStatus_First(companyId,
				groupId, date_in, state, orderByComparator);

		if (position != null) {
			return position;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(", date_in=");
		msg.append(date_in);

		msg.append(", state=");
		msg.append(state);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPositionException(msg.toString());
	}

	/**
	 * Returns the first position in the ordered set where companyId = &#63; and groupId = &#63; and date_in = &#63; and state = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param date_in the date_in
	 * @param state the state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching position, or <code>null</code> if a matching position could not be found
	 */
	@Override
	public Position fetchByCompanyGroupDateStatus_First(long companyId,
		long groupId, Date date_in, String state,
		OrderByComparator<Position> orderByComparator) {
		List<Position> list = findByCompanyGroupDateStatus(companyId, groupId,
				date_in, state, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last position in the ordered set where companyId = &#63; and groupId = &#63; and date_in = &#63; and state = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param date_in the date_in
	 * @param state the state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching position
	 * @throws NoSuchPositionException if a matching position could not be found
	 */
	@Override
	public Position findByCompanyGroupDateStatus_Last(long companyId,
		long groupId, Date date_in, String state,
		OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException {
		Position position = fetchByCompanyGroupDateStatus_Last(companyId,
				groupId, date_in, state, orderByComparator);

		if (position != null) {
			return position;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(", date_in=");
		msg.append(date_in);

		msg.append(", state=");
		msg.append(state);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPositionException(msg.toString());
	}

	/**
	 * Returns the last position in the ordered set where companyId = &#63; and groupId = &#63; and date_in = &#63; and state = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param date_in the date_in
	 * @param state the state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching position, or <code>null</code> if a matching position could not be found
	 */
	@Override
	public Position fetchByCompanyGroupDateStatus_Last(long companyId,
		long groupId, Date date_in, String state,
		OrderByComparator<Position> orderByComparator) {
		int count = countByCompanyGroupDateStatus(companyId, groupId, date_in,
				state);

		if (count == 0) {
			return null;
		}

		List<Position> list = findByCompanyGroupDateStatus(companyId, groupId,
				date_in, state, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the positions before and after the current position in the ordered set where companyId = &#63; and groupId = &#63; and date_in = &#63; and state = &#63;.
	 *
	 * @param positionId the primary key of the current position
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param date_in the date_in
	 * @param state the state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next position
	 * @throws NoSuchPositionException if a position with the primary key could not be found
	 */
	@Override
	public Position[] findByCompanyGroupDateStatus_PrevAndNext(
		long positionId, long companyId, long groupId, Date date_in,
		String state, OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException {
		Position position = findByPrimaryKey(positionId);

		Session session = null;

		try {
			session = openSession();

			Position[] array = new PositionImpl[3];

			array[0] = getByCompanyGroupDateStatus_PrevAndNext(session,
					position, companyId, groupId, date_in, state,
					orderByComparator, true);

			array[1] = position;

			array[2] = getByCompanyGroupDateStatus_PrevAndNext(session,
					position, companyId, groupId, date_in, state,
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

	protected Position getByCompanyGroupDateStatus_PrevAndNext(
		Session session, Position position, long companyId, long groupId,
		Date date_in, String state,
		OrderByComparator<Position> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(7 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(6);
		}

		query.append(_SQL_SELECT_POSITION_WHERE);

		query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUS_COMPANYID_2);

		query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUS_GROUPID_2);

		boolean bindDate_in = false;

		if (date_in == null) {
			query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUS_DATE_IN_1);
		}
		else {
			bindDate_in = true;

			query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUS_DATE_IN_2);
		}

		boolean bindState = false;

		if (state == null) {
			query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUS_STATE_1);
		}
		else if (state.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUS_STATE_3);
		}
		else {
			bindState = true;

			query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUS_STATE_2);
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
			query.append(PositionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(groupId);

		if (bindDate_in) {
			qPos.add(new Timestamp(date_in.getTime()));
		}

		if (bindState) {
			qPos.add(state);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(position);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Position> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the positions where companyId = &#63; and groupId = &#63; and date_in = &#63; and state = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param date_in the date_in
	 * @param state the state
	 */
	@Override
	public void removeByCompanyGroupDateStatus(long companyId, long groupId,
		Date date_in, String state) {
		for (Position position : findByCompanyGroupDateStatus(companyId,
				groupId, date_in, state, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(position);
		}
	}

	/**
	 * Returns the number of positions where companyId = &#63; and groupId = &#63; and date_in = &#63; and state = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param date_in the date_in
	 * @param state the state
	 * @return the number of matching positions
	 */
	@Override
	public int countByCompanyGroupDateStatus(long companyId, long groupId,
		Date date_in, String state) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYGROUPDATESTATUS;

		Object[] finderArgs = new Object[] { companyId, groupId, date_in, state };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_POSITION_WHERE);

			query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUS_COMPANYID_2);

			query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUS_GROUPID_2);

			boolean bindDate_in = false;

			if (date_in == null) {
				query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUS_DATE_IN_1);
			}
			else {
				bindDate_in = true;

				query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUS_DATE_IN_2);
			}

			boolean bindState = false;

			if (state == null) {
				query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUS_STATE_1);
			}
			else if (state.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUS_STATE_3);
			}
			else {
				bindState = true;

				query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUS_STATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(groupId);

				if (bindDate_in) {
					qPos.add(new Timestamp(date_in.getTime()));
				}

				if (bindState) {
					qPos.add(state);
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

	private static final String _FINDER_COLUMN_COMPANYGROUPDATESTATUS_COMPANYID_2 =
		"position.companyId = ? AND ";
	private static final String _FINDER_COLUMN_COMPANYGROUPDATESTATUS_GROUPID_2 = "position.groupId = ? AND ";
	private static final String _FINDER_COLUMN_COMPANYGROUPDATESTATUS_DATE_IN_1 = "position.date_in IS NULL AND ";
	private static final String _FINDER_COLUMN_COMPANYGROUPDATESTATUS_DATE_IN_2 = "position.date_in = ? AND ";
	private static final String _FINDER_COLUMN_COMPANYGROUPDATESTATUS_STATE_1 = "position.state IS NULL";
	private static final String _FINDER_COLUMN_COMPANYGROUPDATESTATUS_STATE_2 = "position.state = ?";
	private static final String _FINDER_COLUMN_COMPANYGROUPDATESTATUS_STATE_3 = "(position.state IS NULL OR position.state = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYGROUPDATESTATUSIN =
		new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, PositionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCompanyGroupDateStatusIn",
			new String[] {
				Long.class.getName(), Long.class.getName(), Date.class.getName(),
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYGROUPDATESTATUSIN =
		new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, PositionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCompanyGroupDateStatusIn",
			new String[] {
				Long.class.getName(), Long.class.getName(), Date.class.getName(),
				String.class.getName()
			},
			PositionModelImpl.COMPANYID_COLUMN_BITMASK |
			PositionModelImpl.GROUPID_COLUMN_BITMASK |
			PositionModelImpl.DATE_IN_COLUMN_BITMASK |
			PositionModelImpl.STATE_IN_COLUMN_BITMASK |
			PositionModelImpl.POSITIONID_TWS_IN_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYGROUPDATESTATUSIN =
		new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCompanyGroupDateStatusIn",
			new String[] {
				Long.class.getName(), Long.class.getName(), Date.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns all the positions where companyId = &#63; and groupId = &#63; and date_in = &#63; and state_in = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param date_in the date_in
	 * @param state_in the state_in
	 * @return the matching positions
	 */
	@Override
	public List<Position> findByCompanyGroupDateStatusIn(long companyId,
		long groupId, Date date_in, String state_in) {
		return findByCompanyGroupDateStatusIn(companyId, groupId, date_in,
			state_in, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the positions where companyId = &#63; and groupId = &#63; and date_in = &#63; and state_in = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param date_in the date_in
	 * @param state_in the state_in
	 * @param start the lower bound of the range of positions
	 * @param end the upper bound of the range of positions (not inclusive)
	 * @return the range of matching positions
	 */
	@Override
	public List<Position> findByCompanyGroupDateStatusIn(long companyId,
		long groupId, Date date_in, String state_in, int start, int end) {
		return findByCompanyGroupDateStatusIn(companyId, groupId, date_in,
			state_in, start, end, null);
	}

	/**
	 * Returns an ordered range of all the positions where companyId = &#63; and groupId = &#63; and date_in = &#63; and state_in = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param date_in the date_in
	 * @param state_in the state_in
	 * @param start the lower bound of the range of positions
	 * @param end the upper bound of the range of positions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching positions
	 */
	@Override
	public List<Position> findByCompanyGroupDateStatusIn(long companyId,
		long groupId, Date date_in, String state_in, int start, int end,
		OrderByComparator<Position> orderByComparator) {
		return findByCompanyGroupDateStatusIn(companyId, groupId, date_in,
			state_in, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the positions where companyId = &#63; and groupId = &#63; and date_in = &#63; and state_in = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param date_in the date_in
	 * @param state_in the state_in
	 * @param start the lower bound of the range of positions
	 * @param end the upper bound of the range of positions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching positions
	 */
	@Override
	public List<Position> findByCompanyGroupDateStatusIn(long companyId,
		long groupId, Date date_in, String state_in, int start, int end,
		OrderByComparator<Position> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYGROUPDATESTATUSIN;
			finderArgs = new Object[] { companyId, groupId, date_in, state_in };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYGROUPDATESTATUSIN;
			finderArgs = new Object[] {
					companyId, groupId, date_in, state_in,
					
					start, end, orderByComparator
				};
		}

		List<Position> list = null;

		if (retrieveFromCache) {
			list = (List<Position>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Position position : list) {
					if ((companyId != position.getCompanyId()) ||
							(groupId != position.getGroupId()) ||
							!Objects.equals(date_in, position.getDate_in()) ||
							!Objects.equals(state_in, position.getState_in())) {
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

			query.append(_SQL_SELECT_POSITION_WHERE);

			query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUSIN_COMPANYID_2);

			query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUSIN_GROUPID_2);

			boolean bindDate_in = false;

			if (date_in == null) {
				query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUSIN_DATE_IN_1);
			}
			else {
				bindDate_in = true;

				query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUSIN_DATE_IN_2);
			}

			boolean bindState_in = false;

			if (state_in == null) {
				query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUSIN_STATE_IN_1);
			}
			else if (state_in.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUSIN_STATE_IN_3);
			}
			else {
				bindState_in = true;

				query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUSIN_STATE_IN_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PositionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(groupId);

				if (bindDate_in) {
					qPos.add(new Timestamp(date_in.getTime()));
				}

				if (bindState_in) {
					qPos.add(state_in);
				}

				if (!pagination) {
					list = (List<Position>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Position>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first position in the ordered set where companyId = &#63; and groupId = &#63; and date_in = &#63; and state_in = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param date_in the date_in
	 * @param state_in the state_in
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching position
	 * @throws NoSuchPositionException if a matching position could not be found
	 */
	@Override
	public Position findByCompanyGroupDateStatusIn_First(long companyId,
		long groupId, Date date_in, String state_in,
		OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException {
		Position position = fetchByCompanyGroupDateStatusIn_First(companyId,
				groupId, date_in, state_in, orderByComparator);

		if (position != null) {
			return position;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(", date_in=");
		msg.append(date_in);

		msg.append(", state_in=");
		msg.append(state_in);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPositionException(msg.toString());
	}

	/**
	 * Returns the first position in the ordered set where companyId = &#63; and groupId = &#63; and date_in = &#63; and state_in = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param date_in the date_in
	 * @param state_in the state_in
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching position, or <code>null</code> if a matching position could not be found
	 */
	@Override
	public Position fetchByCompanyGroupDateStatusIn_First(long companyId,
		long groupId, Date date_in, String state_in,
		OrderByComparator<Position> orderByComparator) {
		List<Position> list = findByCompanyGroupDateStatusIn(companyId,
				groupId, date_in, state_in, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last position in the ordered set where companyId = &#63; and groupId = &#63; and date_in = &#63; and state_in = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param date_in the date_in
	 * @param state_in the state_in
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching position
	 * @throws NoSuchPositionException if a matching position could not be found
	 */
	@Override
	public Position findByCompanyGroupDateStatusIn_Last(long companyId,
		long groupId, Date date_in, String state_in,
		OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException {
		Position position = fetchByCompanyGroupDateStatusIn_Last(companyId,
				groupId, date_in, state_in, orderByComparator);

		if (position != null) {
			return position;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(", date_in=");
		msg.append(date_in);

		msg.append(", state_in=");
		msg.append(state_in);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPositionException(msg.toString());
	}

	/**
	 * Returns the last position in the ordered set where companyId = &#63; and groupId = &#63; and date_in = &#63; and state_in = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param date_in the date_in
	 * @param state_in the state_in
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching position, or <code>null</code> if a matching position could not be found
	 */
	@Override
	public Position fetchByCompanyGroupDateStatusIn_Last(long companyId,
		long groupId, Date date_in, String state_in,
		OrderByComparator<Position> orderByComparator) {
		int count = countByCompanyGroupDateStatusIn(companyId, groupId,
				date_in, state_in);

		if (count == 0) {
			return null;
		}

		List<Position> list = findByCompanyGroupDateStatusIn(companyId,
				groupId, date_in, state_in, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the positions before and after the current position in the ordered set where companyId = &#63; and groupId = &#63; and date_in = &#63; and state_in = &#63;.
	 *
	 * @param positionId the primary key of the current position
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param date_in the date_in
	 * @param state_in the state_in
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next position
	 * @throws NoSuchPositionException if a position with the primary key could not be found
	 */
	@Override
	public Position[] findByCompanyGroupDateStatusIn_PrevAndNext(
		long positionId, long companyId, long groupId, Date date_in,
		String state_in, OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException {
		Position position = findByPrimaryKey(positionId);

		Session session = null;

		try {
			session = openSession();

			Position[] array = new PositionImpl[3];

			array[0] = getByCompanyGroupDateStatusIn_PrevAndNext(session,
					position, companyId, groupId, date_in, state_in,
					orderByComparator, true);

			array[1] = position;

			array[2] = getByCompanyGroupDateStatusIn_PrevAndNext(session,
					position, companyId, groupId, date_in, state_in,
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

	protected Position getByCompanyGroupDateStatusIn_PrevAndNext(
		Session session, Position position, long companyId, long groupId,
		Date date_in, String state_in,
		OrderByComparator<Position> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(7 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(6);
		}

		query.append(_SQL_SELECT_POSITION_WHERE);

		query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUSIN_COMPANYID_2);

		query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUSIN_GROUPID_2);

		boolean bindDate_in = false;

		if (date_in == null) {
			query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUSIN_DATE_IN_1);
		}
		else {
			bindDate_in = true;

			query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUSIN_DATE_IN_2);
		}

		boolean bindState_in = false;

		if (state_in == null) {
			query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUSIN_STATE_IN_1);
		}
		else if (state_in.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUSIN_STATE_IN_3);
		}
		else {
			bindState_in = true;

			query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUSIN_STATE_IN_2);
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
			query.append(PositionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(groupId);

		if (bindDate_in) {
			qPos.add(new Timestamp(date_in.getTime()));
		}

		if (bindState_in) {
			qPos.add(state_in);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(position);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Position> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the positions where companyId = &#63; and groupId = &#63; and date_in = &#63; and state_in = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param date_in the date_in
	 * @param state_in the state_in
	 */
	@Override
	public void removeByCompanyGroupDateStatusIn(long companyId, long groupId,
		Date date_in, String state_in) {
		for (Position position : findByCompanyGroupDateStatusIn(companyId,
				groupId, date_in, state_in, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(position);
		}
	}

	/**
	 * Returns the number of positions where companyId = &#63; and groupId = &#63; and date_in = &#63; and state_in = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param date_in the date_in
	 * @param state_in the state_in
	 * @return the number of matching positions
	 */
	@Override
	public int countByCompanyGroupDateStatusIn(long companyId, long groupId,
		Date date_in, String state_in) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYGROUPDATESTATUSIN;

		Object[] finderArgs = new Object[] { companyId, groupId, date_in, state_in };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_POSITION_WHERE);

			query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUSIN_COMPANYID_2);

			query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUSIN_GROUPID_2);

			boolean bindDate_in = false;

			if (date_in == null) {
				query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUSIN_DATE_IN_1);
			}
			else {
				bindDate_in = true;

				query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUSIN_DATE_IN_2);
			}

			boolean bindState_in = false;

			if (state_in == null) {
				query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUSIN_STATE_IN_1);
			}
			else if (state_in.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUSIN_STATE_IN_3);
			}
			else {
				bindState_in = true;

				query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUSIN_STATE_IN_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(groupId);

				if (bindDate_in) {
					qPos.add(new Timestamp(date_in.getTime()));
				}

				if (bindState_in) {
					qPos.add(state_in);
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

	private static final String _FINDER_COLUMN_COMPANYGROUPDATESTATUSIN_COMPANYID_2 =
		"position.companyId = ? AND ";
	private static final String _FINDER_COLUMN_COMPANYGROUPDATESTATUSIN_GROUPID_2 =
		"position.groupId = ? AND ";
	private static final String _FINDER_COLUMN_COMPANYGROUPDATESTATUSIN_DATE_IN_1 =
		"position.date_in IS NULL AND ";
	private static final String _FINDER_COLUMN_COMPANYGROUPDATESTATUSIN_DATE_IN_2 =
		"position.date_in = ? AND ";
	private static final String _FINDER_COLUMN_COMPANYGROUPDATESTATUSIN_STATE_IN_1 =
		"position.state_in IS NULL";
	private static final String _FINDER_COLUMN_COMPANYGROUPDATESTATUSIN_STATE_IN_2 =
		"position.state_in = ?";
	private static final String _FINDER_COLUMN_COMPANYGROUPDATESTATUSIN_STATE_IN_3 =
		"(position.state_in IS NULL OR position.state_in = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYGROUPDATESTATUSOUT =
		new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, PositionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCompanyGroupDateStatusOut",
			new String[] {
				Long.class.getName(), Long.class.getName(), Date.class.getName(),
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYGROUPDATESTATUSOUT =
		new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, PositionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCompanyGroupDateStatusOut",
			new String[] {
				Long.class.getName(), Long.class.getName(), Date.class.getName(),
				String.class.getName()
			},
			PositionModelImpl.COMPANYID_COLUMN_BITMASK |
			PositionModelImpl.GROUPID_COLUMN_BITMASK |
			PositionModelImpl.DATE_OUT_COLUMN_BITMASK |
			PositionModelImpl.STATE_OUT_COLUMN_BITMASK |
			PositionModelImpl.POSITIONID_TWS_IN_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYGROUPDATESTATUSOUT =
		new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCompanyGroupDateStatusOut",
			new String[] {
				Long.class.getName(), Long.class.getName(), Date.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns all the positions where companyId = &#63; and groupId = &#63; and date_out = &#63; and state_out = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param date_out the date_out
	 * @param state_out the state_out
	 * @return the matching positions
	 */
	@Override
	public List<Position> findByCompanyGroupDateStatusOut(long companyId,
		long groupId, Date date_out, String state_out) {
		return findByCompanyGroupDateStatusOut(companyId, groupId, date_out,
			state_out, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the positions where companyId = &#63; and groupId = &#63; and date_out = &#63; and state_out = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param date_out the date_out
	 * @param state_out the state_out
	 * @param start the lower bound of the range of positions
	 * @param end the upper bound of the range of positions (not inclusive)
	 * @return the range of matching positions
	 */
	@Override
	public List<Position> findByCompanyGroupDateStatusOut(long companyId,
		long groupId, Date date_out, String state_out, int start, int end) {
		return findByCompanyGroupDateStatusOut(companyId, groupId, date_out,
			state_out, start, end, null);
	}

	/**
	 * Returns an ordered range of all the positions where companyId = &#63; and groupId = &#63; and date_out = &#63; and state_out = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param date_out the date_out
	 * @param state_out the state_out
	 * @param start the lower bound of the range of positions
	 * @param end the upper bound of the range of positions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching positions
	 */
	@Override
	public List<Position> findByCompanyGroupDateStatusOut(long companyId,
		long groupId, Date date_out, String state_out, int start, int end,
		OrderByComparator<Position> orderByComparator) {
		return findByCompanyGroupDateStatusOut(companyId, groupId, date_out,
			state_out, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the positions where companyId = &#63; and groupId = &#63; and date_out = &#63; and state_out = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param date_out the date_out
	 * @param state_out the state_out
	 * @param start the lower bound of the range of positions
	 * @param end the upper bound of the range of positions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching positions
	 */
	@Override
	public List<Position> findByCompanyGroupDateStatusOut(long companyId,
		long groupId, Date date_out, String state_out, int start, int end,
		OrderByComparator<Position> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYGROUPDATESTATUSOUT;
			finderArgs = new Object[] { companyId, groupId, date_out, state_out };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYGROUPDATESTATUSOUT;
			finderArgs = new Object[] {
					companyId, groupId, date_out, state_out,
					
					start, end, orderByComparator
				};
		}

		List<Position> list = null;

		if (retrieveFromCache) {
			list = (List<Position>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Position position : list) {
					if ((companyId != position.getCompanyId()) ||
							(groupId != position.getGroupId()) ||
							!Objects.equals(date_out, position.getDate_out()) ||
							!Objects.equals(state_out, position.getState_out())) {
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

			query.append(_SQL_SELECT_POSITION_WHERE);

			query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUSOUT_COMPANYID_2);

			query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUSOUT_GROUPID_2);

			boolean bindDate_out = false;

			if (date_out == null) {
				query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUSOUT_DATE_OUT_1);
			}
			else {
				bindDate_out = true;

				query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUSOUT_DATE_OUT_2);
			}

			boolean bindState_out = false;

			if (state_out == null) {
				query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUSOUT_STATE_OUT_1);
			}
			else if (state_out.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUSOUT_STATE_OUT_3);
			}
			else {
				bindState_out = true;

				query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUSOUT_STATE_OUT_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PositionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(groupId);

				if (bindDate_out) {
					qPos.add(new Timestamp(date_out.getTime()));
				}

				if (bindState_out) {
					qPos.add(state_out);
				}

				if (!pagination) {
					list = (List<Position>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Position>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first position in the ordered set where companyId = &#63; and groupId = &#63; and date_out = &#63; and state_out = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param date_out the date_out
	 * @param state_out the state_out
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching position
	 * @throws NoSuchPositionException if a matching position could not be found
	 */
	@Override
	public Position findByCompanyGroupDateStatusOut_First(long companyId,
		long groupId, Date date_out, String state_out,
		OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException {
		Position position = fetchByCompanyGroupDateStatusOut_First(companyId,
				groupId, date_out, state_out, orderByComparator);

		if (position != null) {
			return position;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(", date_out=");
		msg.append(date_out);

		msg.append(", state_out=");
		msg.append(state_out);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPositionException(msg.toString());
	}

	/**
	 * Returns the first position in the ordered set where companyId = &#63; and groupId = &#63; and date_out = &#63; and state_out = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param date_out the date_out
	 * @param state_out the state_out
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching position, or <code>null</code> if a matching position could not be found
	 */
	@Override
	public Position fetchByCompanyGroupDateStatusOut_First(long companyId,
		long groupId, Date date_out, String state_out,
		OrderByComparator<Position> orderByComparator) {
		List<Position> list = findByCompanyGroupDateStatusOut(companyId,
				groupId, date_out, state_out, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last position in the ordered set where companyId = &#63; and groupId = &#63; and date_out = &#63; and state_out = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param date_out the date_out
	 * @param state_out the state_out
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching position
	 * @throws NoSuchPositionException if a matching position could not be found
	 */
	@Override
	public Position findByCompanyGroupDateStatusOut_Last(long companyId,
		long groupId, Date date_out, String state_out,
		OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException {
		Position position = fetchByCompanyGroupDateStatusOut_Last(companyId,
				groupId, date_out, state_out, orderByComparator);

		if (position != null) {
			return position;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(", date_out=");
		msg.append(date_out);

		msg.append(", state_out=");
		msg.append(state_out);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPositionException(msg.toString());
	}

	/**
	 * Returns the last position in the ordered set where companyId = &#63; and groupId = &#63; and date_out = &#63; and state_out = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param date_out the date_out
	 * @param state_out the state_out
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching position, or <code>null</code> if a matching position could not be found
	 */
	@Override
	public Position fetchByCompanyGroupDateStatusOut_Last(long companyId,
		long groupId, Date date_out, String state_out,
		OrderByComparator<Position> orderByComparator) {
		int count = countByCompanyGroupDateStatusOut(companyId, groupId,
				date_out, state_out);

		if (count == 0) {
			return null;
		}

		List<Position> list = findByCompanyGroupDateStatusOut(companyId,
				groupId, date_out, state_out, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the positions before and after the current position in the ordered set where companyId = &#63; and groupId = &#63; and date_out = &#63; and state_out = &#63;.
	 *
	 * @param positionId the primary key of the current position
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param date_out the date_out
	 * @param state_out the state_out
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next position
	 * @throws NoSuchPositionException if a position with the primary key could not be found
	 */
	@Override
	public Position[] findByCompanyGroupDateStatusOut_PrevAndNext(
		long positionId, long companyId, long groupId, Date date_out,
		String state_out, OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException {
		Position position = findByPrimaryKey(positionId);

		Session session = null;

		try {
			session = openSession();

			Position[] array = new PositionImpl[3];

			array[0] = getByCompanyGroupDateStatusOut_PrevAndNext(session,
					position, companyId, groupId, date_out, state_out,
					orderByComparator, true);

			array[1] = position;

			array[2] = getByCompanyGroupDateStatusOut_PrevAndNext(session,
					position, companyId, groupId, date_out, state_out,
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

	protected Position getByCompanyGroupDateStatusOut_PrevAndNext(
		Session session, Position position, long companyId, long groupId,
		Date date_out, String state_out,
		OrderByComparator<Position> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(7 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(6);
		}

		query.append(_SQL_SELECT_POSITION_WHERE);

		query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUSOUT_COMPANYID_2);

		query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUSOUT_GROUPID_2);

		boolean bindDate_out = false;

		if (date_out == null) {
			query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUSOUT_DATE_OUT_1);
		}
		else {
			bindDate_out = true;

			query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUSOUT_DATE_OUT_2);
		}

		boolean bindState_out = false;

		if (state_out == null) {
			query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUSOUT_STATE_OUT_1);
		}
		else if (state_out.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUSOUT_STATE_OUT_3);
		}
		else {
			bindState_out = true;

			query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUSOUT_STATE_OUT_2);
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
			query.append(PositionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(groupId);

		if (bindDate_out) {
			qPos.add(new Timestamp(date_out.getTime()));
		}

		if (bindState_out) {
			qPos.add(state_out);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(position);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Position> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the positions where companyId = &#63; and groupId = &#63; and date_out = &#63; and state_out = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param date_out the date_out
	 * @param state_out the state_out
	 */
	@Override
	public void removeByCompanyGroupDateStatusOut(long companyId, long groupId,
		Date date_out, String state_out) {
		for (Position position : findByCompanyGroupDateStatusOut(companyId,
				groupId, date_out, state_out, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(position);
		}
	}

	/**
	 * Returns the number of positions where companyId = &#63; and groupId = &#63; and date_out = &#63; and state_out = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param date_out the date_out
	 * @param state_out the state_out
	 * @return the number of matching positions
	 */
	@Override
	public int countByCompanyGroupDateStatusOut(long companyId, long groupId,
		Date date_out, String state_out) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYGROUPDATESTATUSOUT;

		Object[] finderArgs = new Object[] {
				companyId, groupId, date_out, state_out
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_POSITION_WHERE);

			query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUSOUT_COMPANYID_2);

			query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUSOUT_GROUPID_2);

			boolean bindDate_out = false;

			if (date_out == null) {
				query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUSOUT_DATE_OUT_1);
			}
			else {
				bindDate_out = true;

				query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUSOUT_DATE_OUT_2);
			}

			boolean bindState_out = false;

			if (state_out == null) {
				query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUSOUT_STATE_OUT_1);
			}
			else if (state_out.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUSOUT_STATE_OUT_3);
			}
			else {
				bindState_out = true;

				query.append(_FINDER_COLUMN_COMPANYGROUPDATESTATUSOUT_STATE_OUT_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(groupId);

				if (bindDate_out) {
					qPos.add(new Timestamp(date_out.getTime()));
				}

				if (bindState_out) {
					qPos.add(state_out);
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

	private static final String _FINDER_COLUMN_COMPANYGROUPDATESTATUSOUT_COMPANYID_2 =
		"position.companyId = ? AND ";
	private static final String _FINDER_COLUMN_COMPANYGROUPDATESTATUSOUT_GROUPID_2 =
		"position.groupId = ? AND ";
	private static final String _FINDER_COLUMN_COMPANYGROUPDATESTATUSOUT_DATE_OUT_1 =
		"position.date_out IS NULL AND ";
	private static final String _FINDER_COLUMN_COMPANYGROUPDATESTATUSOUT_DATE_OUT_2 =
		"position.date_out = ? AND ";
	private static final String _FINDER_COLUMN_COMPANYGROUPDATESTATUSOUT_STATE_OUT_1 =
		"position.state_out IS NULL";
	private static final String _FINDER_COLUMN_COMPANYGROUPDATESTATUSOUT_STATE_OUT_2 =
		"position.state_out = ?";
	private static final String _FINDER_COLUMN_COMPANYGROUPDATESTATUSOUT_STATE_OUT_3 =
		"(position.state_out IS NULL OR position.state_out = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_POSITIONOUTGROUPCOMPANY = new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, PositionImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByPositionOutGroupCompany",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			PositionModelImpl.GROUPID_COLUMN_BITMASK |
			PositionModelImpl.COMPANYID_COLUMN_BITMASK |
			PositionModelImpl.POSITIONID_TWS_OUT_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_POSITIONOUTGROUPCOMPANY = new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByPositionOutGroupCompany",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});

	/**
	 * Returns the position where groupId = &#63; and companyId = &#63; and positionId_tws_out = &#63; or throws a {@link NoSuchPositionException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param positionId_tws_out the position id_tws_out
	 * @return the matching position
	 * @throws NoSuchPositionException if a matching position could not be found
	 */
	@Override
	public Position findByPositionOutGroupCompany(long groupId, long companyId,
		long positionId_tws_out) throws NoSuchPositionException {
		Position position = fetchByPositionOutGroupCompany(groupId, companyId,
				positionId_tws_out);

		if (position == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", companyId=");
			msg.append(companyId);

			msg.append(", positionId_tws_out=");
			msg.append(positionId_tws_out);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchPositionException(msg.toString());
		}

		return position;
	}

	/**
	 * Returns the position where groupId = &#63; and companyId = &#63; and positionId_tws_out = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param positionId_tws_out the position id_tws_out
	 * @return the matching position, or <code>null</code> if a matching position could not be found
	 */
	@Override
	public Position fetchByPositionOutGroupCompany(long groupId,
		long companyId, long positionId_tws_out) {
		return fetchByPositionOutGroupCompany(groupId, companyId,
			positionId_tws_out, true);
	}

	/**
	 * Returns the position where groupId = &#63; and companyId = &#63; and positionId_tws_out = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param positionId_tws_out the position id_tws_out
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching position, or <code>null</code> if a matching position could not be found
	 */
	@Override
	public Position fetchByPositionOutGroupCompany(long groupId,
		long companyId, long positionId_tws_out, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] {
				groupId, companyId, positionId_tws_out
			};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_POSITIONOUTGROUPCOMPANY,
					finderArgs, this);
		}

		if (result instanceof Position) {
			Position position = (Position)result;

			if ((groupId != position.getGroupId()) ||
					(companyId != position.getCompanyId()) ||
					(positionId_tws_out != position.getPositionId_tws_out())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_POSITION_WHERE);

			query.append(_FINDER_COLUMN_POSITIONOUTGROUPCOMPANY_GROUPID_2);

			query.append(_FINDER_COLUMN_POSITIONOUTGROUPCOMPANY_COMPANYID_2);

			query.append(_FINDER_COLUMN_POSITIONOUTGROUPCOMPANY_POSITIONID_TWS_OUT_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(companyId);

				qPos.add(positionId_tws_out);

				List<Position> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_POSITIONOUTGROUPCOMPANY,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"PositionPersistenceImpl.fetchByPositionOutGroupCompany(long, long, long, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					Position position = list.get(0);

					result = position;

					cacheResult(position);

					if ((position.getGroupId() != groupId) ||
							(position.getCompanyId() != companyId) ||
							(position.getPositionId_tws_out() != positionId_tws_out)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_POSITIONOUTGROUPCOMPANY,
							finderArgs, position);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_POSITIONOUTGROUPCOMPANY,
					finderArgs);

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
			return (Position)result;
		}
	}

	/**
	 * Removes the position where groupId = &#63; and companyId = &#63; and positionId_tws_out = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param positionId_tws_out the position id_tws_out
	 * @return the position that was removed
	 */
	@Override
	public Position removeByPositionOutGroupCompany(long groupId,
		long companyId, long positionId_tws_out) throws NoSuchPositionException {
		Position position = findByPositionOutGroupCompany(groupId, companyId,
				positionId_tws_out);

		return remove(position);
	}

	/**
	 * Returns the number of positions where groupId = &#63; and companyId = &#63; and positionId_tws_out = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param positionId_tws_out the position id_tws_out
	 * @return the number of matching positions
	 */
	@Override
	public int countByPositionOutGroupCompany(long groupId, long companyId,
		long positionId_tws_out) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_POSITIONOUTGROUPCOMPANY;

		Object[] finderArgs = new Object[] {
				groupId, companyId, positionId_tws_out
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_POSITION_WHERE);

			query.append(_FINDER_COLUMN_POSITIONOUTGROUPCOMPANY_GROUPID_2);

			query.append(_FINDER_COLUMN_POSITIONOUTGROUPCOMPANY_COMPANYID_2);

			query.append(_FINDER_COLUMN_POSITIONOUTGROUPCOMPANY_POSITIONID_TWS_OUT_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(companyId);

				qPos.add(positionId_tws_out);

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

	private static final String _FINDER_COLUMN_POSITIONOUTGROUPCOMPANY_GROUPID_2 =
		"position.groupId = ? AND ";
	private static final String _FINDER_COLUMN_POSITIONOUTGROUPCOMPANY_COMPANYID_2 =
		"position.companyId = ? AND ";
	private static final String _FINDER_COLUMN_POSITIONOUTGROUPCOMPANY_POSITIONID_TWS_OUT_2 =
		"position.positionId_tws_out = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_POSITIONINGROUPCOMPANY = new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, PositionImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByPositionInGroupCompany",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			PositionModelImpl.GROUPID_COLUMN_BITMASK |
			PositionModelImpl.COMPANYID_COLUMN_BITMASK |
			PositionModelImpl.POSITIONID_TWS_IN_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_POSITIONINGROUPCOMPANY = new FinderPath(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByPositionInGroupCompany",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});

	/**
	 * Returns the position where groupId = &#63; and companyId = &#63; and positionId_tws_in = &#63; or throws a {@link NoSuchPositionException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param positionId_tws_in the position id_tws_in
	 * @return the matching position
	 * @throws NoSuchPositionException if a matching position could not be found
	 */
	@Override
	public Position findByPositionInGroupCompany(long groupId, long companyId,
		long positionId_tws_in) throws NoSuchPositionException {
		Position position = fetchByPositionInGroupCompany(groupId, companyId,
				positionId_tws_in);

		if (position == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", companyId=");
			msg.append(companyId);

			msg.append(", positionId_tws_in=");
			msg.append(positionId_tws_in);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchPositionException(msg.toString());
		}

		return position;
	}

	/**
	 * Returns the position where groupId = &#63; and companyId = &#63; and positionId_tws_in = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param positionId_tws_in the position id_tws_in
	 * @return the matching position, or <code>null</code> if a matching position could not be found
	 */
	@Override
	public Position fetchByPositionInGroupCompany(long groupId, long companyId,
		long positionId_tws_in) {
		return fetchByPositionInGroupCompany(groupId, companyId,
			positionId_tws_in, true);
	}

	/**
	 * Returns the position where groupId = &#63; and companyId = &#63; and positionId_tws_in = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param positionId_tws_in the position id_tws_in
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching position, or <code>null</code> if a matching position could not be found
	 */
	@Override
	public Position fetchByPositionInGroupCompany(long groupId, long companyId,
		long positionId_tws_in, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, companyId, positionId_tws_in };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_POSITIONINGROUPCOMPANY,
					finderArgs, this);
		}

		if (result instanceof Position) {
			Position position = (Position)result;

			if ((groupId != position.getGroupId()) ||
					(companyId != position.getCompanyId()) ||
					(positionId_tws_in != position.getPositionId_tws_in())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_POSITION_WHERE);

			query.append(_FINDER_COLUMN_POSITIONINGROUPCOMPANY_GROUPID_2);

			query.append(_FINDER_COLUMN_POSITIONINGROUPCOMPANY_COMPANYID_2);

			query.append(_FINDER_COLUMN_POSITIONINGROUPCOMPANY_POSITIONID_TWS_IN_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(companyId);

				qPos.add(positionId_tws_in);

				List<Position> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_POSITIONINGROUPCOMPANY,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"PositionPersistenceImpl.fetchByPositionInGroupCompany(long, long, long, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					Position position = list.get(0);

					result = position;

					cacheResult(position);

					if ((position.getGroupId() != groupId) ||
							(position.getCompanyId() != companyId) ||
							(position.getPositionId_tws_in() != positionId_tws_in)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_POSITIONINGROUPCOMPANY,
							finderArgs, position);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_POSITIONINGROUPCOMPANY,
					finderArgs);

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
			return (Position)result;
		}
	}

	/**
	 * Removes the position where groupId = &#63; and companyId = &#63; and positionId_tws_in = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param positionId_tws_in the position id_tws_in
	 * @return the position that was removed
	 */
	@Override
	public Position removeByPositionInGroupCompany(long groupId,
		long companyId, long positionId_tws_in) throws NoSuchPositionException {
		Position position = findByPositionInGroupCompany(groupId, companyId,
				positionId_tws_in);

		return remove(position);
	}

	/**
	 * Returns the number of positions where groupId = &#63; and companyId = &#63; and positionId_tws_in = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param positionId_tws_in the position id_tws_in
	 * @return the number of matching positions
	 */
	@Override
	public int countByPositionInGroupCompany(long groupId, long companyId,
		long positionId_tws_in) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_POSITIONINGROUPCOMPANY;

		Object[] finderArgs = new Object[] { groupId, companyId, positionId_tws_in };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_POSITION_WHERE);

			query.append(_FINDER_COLUMN_POSITIONINGROUPCOMPANY_GROUPID_2);

			query.append(_FINDER_COLUMN_POSITIONINGROUPCOMPANY_COMPANYID_2);

			query.append(_FINDER_COLUMN_POSITIONINGROUPCOMPANY_POSITIONID_TWS_IN_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(companyId);

				qPos.add(positionId_tws_in);

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

	private static final String _FINDER_COLUMN_POSITIONINGROUPCOMPANY_GROUPID_2 = "position.groupId = ? AND ";
	private static final String _FINDER_COLUMN_POSITIONINGROUPCOMPANY_COMPANYID_2 =
		"position.companyId = ? AND ";
	private static final String _FINDER_COLUMN_POSITIONINGROUPCOMPANY_POSITIONID_TWS_IN_2 =
		"position.positionId_tws_in = ?";

	public PositionPersistenceImpl() {
		setModelClass(Position.class);
	}

	/**
	 * Caches the position in the entity cache if it is enabled.
	 *
	 * @param position the position
	 */
	@Override
	public void cacheResult(Position position) {
		entityCache.putResult(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionImpl.class, position.getPrimaryKey(), position);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { position.getUuid(), position.getGroupId() }, position);

		finderCache.putResult(FINDER_PATH_FETCH_BY_POSITIONOUTGROUPCOMPANY,
			new Object[] {
				position.getGroupId(), position.getCompanyId(),
				position.getPositionId_tws_out()
			}, position);

		finderCache.putResult(FINDER_PATH_FETCH_BY_POSITIONINGROUPCOMPANY,
			new Object[] {
				position.getGroupId(), position.getCompanyId(),
				position.getPositionId_tws_in()
			}, position);

		position.resetOriginalValues();
	}

	/**
	 * Caches the positions in the entity cache if it is enabled.
	 *
	 * @param positions the positions
	 */
	@Override
	public void cacheResult(List<Position> positions) {
		for (Position position : positions) {
			if (entityCache.getResult(PositionModelImpl.ENTITY_CACHE_ENABLED,
						PositionImpl.class, position.getPrimaryKey()) == null) {
				cacheResult(position);
			}
			else {
				position.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all positions.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(PositionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the position.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Position position) {
		entityCache.removeResult(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionImpl.class, position.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((PositionModelImpl)position);
	}

	@Override
	public void clearCache(List<Position> positions) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Position position : positions) {
			entityCache.removeResult(PositionModelImpl.ENTITY_CACHE_ENABLED,
				PositionImpl.class, position.getPrimaryKey());

			clearUniqueFindersCache((PositionModelImpl)position);
		}
	}

	protected void cacheUniqueFindersCache(
		PositionModelImpl positionModelImpl, boolean isNew) {
		if (isNew) {
			Object[] args = new Object[] {
					positionModelImpl.getUuid(), positionModelImpl.getGroupId()
				};

			finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
				Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
				positionModelImpl);

			args = new Object[] {
					positionModelImpl.getGroupId(),
					positionModelImpl.getCompanyId(),
					positionModelImpl.getPositionId_tws_out()
				};

			finderCache.putResult(FINDER_PATH_COUNT_BY_POSITIONOUTGROUPCOMPANY,
				args, Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_POSITIONOUTGROUPCOMPANY,
				args, positionModelImpl);

			args = new Object[] {
					positionModelImpl.getGroupId(),
					positionModelImpl.getCompanyId(),
					positionModelImpl.getPositionId_tws_in()
				};

			finderCache.putResult(FINDER_PATH_COUNT_BY_POSITIONINGROUPCOMPANY,
				args, Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_POSITIONINGROUPCOMPANY,
				args, positionModelImpl);
		}
		else {
			if ((positionModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						positionModelImpl.getUuid(),
						positionModelImpl.getGroupId()
					};

				finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
					Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
					positionModelImpl);
			}

			if ((positionModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_POSITIONOUTGROUPCOMPANY.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						positionModelImpl.getGroupId(),
						positionModelImpl.getCompanyId(),
						positionModelImpl.getPositionId_tws_out()
					};

				finderCache.putResult(FINDER_PATH_COUNT_BY_POSITIONOUTGROUPCOMPANY,
					args, Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_POSITIONOUTGROUPCOMPANY,
					args, positionModelImpl);
			}

			if ((positionModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_POSITIONINGROUPCOMPANY.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						positionModelImpl.getGroupId(),
						positionModelImpl.getCompanyId(),
						positionModelImpl.getPositionId_tws_in()
					};

				finderCache.putResult(FINDER_PATH_COUNT_BY_POSITIONINGROUPCOMPANY,
					args, Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_POSITIONINGROUPCOMPANY,
					args, positionModelImpl);
			}
		}
	}

	protected void clearUniqueFindersCache(PositionModelImpl positionModelImpl) {
		Object[] args = new Object[] {
				positionModelImpl.getUuid(), positionModelImpl.getGroupId()
			};

		finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

		if ((positionModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			args = new Object[] {
					positionModelImpl.getOriginalUuid(),
					positionModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		args = new Object[] {
				positionModelImpl.getGroupId(), positionModelImpl.getCompanyId(),
				positionModelImpl.getPositionId_tws_out()
			};

		finderCache.removeResult(FINDER_PATH_COUNT_BY_POSITIONOUTGROUPCOMPANY,
			args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_POSITIONOUTGROUPCOMPANY,
			args);

		if ((positionModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_POSITIONOUTGROUPCOMPANY.getColumnBitmask()) != 0) {
			args = new Object[] {
					positionModelImpl.getOriginalGroupId(),
					positionModelImpl.getOriginalCompanyId(),
					positionModelImpl.getOriginalPositionId_tws_out()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_POSITIONOUTGROUPCOMPANY,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_POSITIONOUTGROUPCOMPANY,
				args);
		}

		args = new Object[] {
				positionModelImpl.getGroupId(), positionModelImpl.getCompanyId(),
				positionModelImpl.getPositionId_tws_in()
			};

		finderCache.removeResult(FINDER_PATH_COUNT_BY_POSITIONINGROUPCOMPANY,
			args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_POSITIONINGROUPCOMPANY,
			args);

		if ((positionModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_POSITIONINGROUPCOMPANY.getColumnBitmask()) != 0) {
			args = new Object[] {
					positionModelImpl.getOriginalGroupId(),
					positionModelImpl.getOriginalCompanyId(),
					positionModelImpl.getOriginalPositionId_tws_in()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_POSITIONINGROUPCOMPANY,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_POSITIONINGROUPCOMPANY,
				args);
		}
	}

	/**
	 * Creates a new position with the primary key. Does not add the position to the database.
	 *
	 * @param positionId the primary key for the new position
	 * @return the new position
	 */
	@Override
	public Position create(long positionId) {
		Position position = new PositionImpl();

		position.setNew(true);
		position.setPrimaryKey(positionId);

		String uuid = PortalUUIDUtil.generate();

		position.setUuid(uuid);

		position.setCompanyId(companyProvider.getCompanyId());

		return position;
	}

	/**
	 * Removes the position with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param positionId the primary key of the position
	 * @return the position that was removed
	 * @throws NoSuchPositionException if a position with the primary key could not be found
	 */
	@Override
	public Position remove(long positionId) throws NoSuchPositionException {
		return remove((Serializable)positionId);
	}

	/**
	 * Removes the position with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the position
	 * @return the position that was removed
	 * @throws NoSuchPositionException if a position with the primary key could not be found
	 */
	@Override
	public Position remove(Serializable primaryKey)
		throws NoSuchPositionException {
		Session session = null;

		try {
			session = openSession();

			Position position = (Position)session.get(PositionImpl.class,
					primaryKey);

			if (position == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPositionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(position);
		}
		catch (NoSuchPositionException nsee) {
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
	protected Position removeImpl(Position position) {
		position = toUnwrappedModel(position);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(position)) {
				position = (Position)session.get(PositionImpl.class,
						position.getPrimaryKeyObj());
			}

			if (position != null) {
				session.delete(position);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (position != null) {
			clearCache(position);
		}

		return position;
	}

	@Override
	public Position updateImpl(Position position) {
		position = toUnwrappedModel(position);

		boolean isNew = position.isNew();

		PositionModelImpl positionModelImpl = (PositionModelImpl)position;

		if (Validator.isNull(position.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			position.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (position.getCreateDate() == null)) {
			if (serviceContext == null) {
				position.setCreateDate(now);
			}
			else {
				position.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!positionModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				position.setModifiedDate(now);
			}
			else {
				position.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (position.isNew()) {
				session.save(position);

				position.setNew(false);
			}
			else {
				position = (Position)session.merge(position);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !PositionModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((positionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { positionModelImpl.getOriginalUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { positionModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((positionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						positionModelImpl.getOriginalUuid(),
						positionModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						positionModelImpl.getUuid(),
						positionModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((positionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_POSITIONSHAREDATESINOUT.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						positionModelImpl.getOriginalGroupId(),
						positionModelImpl.getOriginalCompanyId(),
						positionModelImpl.getOriginalShareId(),
						positionModelImpl.getOriginalDate_real_in(),
						positionModelImpl.getOriginalDate_real_out(),
						positionModelImpl.getOriginalDate_in(),
						positionModelImpl.getOriginalDate_out()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_POSITIONSHAREDATESINOUT,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_POSITIONSHAREDATESINOUT,
					args);

				args = new Object[] {
						positionModelImpl.getGroupId(),
						positionModelImpl.getCompanyId(),
						positionModelImpl.getShareId(),
						positionModelImpl.getDate_real_in(),
						positionModelImpl.getDate_real_out(),
						positionModelImpl.getDate_in(),
						positionModelImpl.getDate_out()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_POSITIONSHAREDATESINOUT,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_POSITIONSHAREDATESINOUT,
					args);
			}

			if ((positionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_POSITIONSHARESTATEDATEOUT.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						positionModelImpl.getOriginalGroupId(),
						positionModelImpl.getOriginalCompanyId(),
						positionModelImpl.getOriginalShareId(),
						positionModelImpl.getOriginalState(),
						positionModelImpl.getOriginalDate_out()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_POSITIONSHARESTATEDATEOUT,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_POSITIONSHARESTATEDATEOUT,
					args);

				args = new Object[] {
						positionModelImpl.getGroupId(),
						positionModelImpl.getCompanyId(),
						positionModelImpl.getShareId(),
						positionModelImpl.getState(),
						positionModelImpl.getDate_out()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_POSITIONSHARESTATEDATEOUT,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_POSITIONSHARESTATEDATEOUT,
					args);
			}

			if ((positionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_POSITIONSHAREDATEIN.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						positionModelImpl.getOriginalGroupId(),
						positionModelImpl.getOriginalCompanyId(),
						positionModelImpl.getOriginalShareId(),
						positionModelImpl.getOriginalDate_in()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_POSITIONSHAREDATEIN,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_POSITIONSHAREDATEIN,
					args);

				args = new Object[] {
						positionModelImpl.getGroupId(),
						positionModelImpl.getCompanyId(),
						positionModelImpl.getShareId(),
						positionModelImpl.getDate_in()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_POSITIONSHAREDATEIN,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_POSITIONSHAREDATEIN,
					args);
			}

			if ((positionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_POSITIONSHAREDATEOUT.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						positionModelImpl.getOriginalGroupId(),
						positionModelImpl.getOriginalCompanyId(),
						positionModelImpl.getOriginalShareId(),
						positionModelImpl.getOriginalDate_out()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_POSITIONSHAREDATEOUT,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_POSITIONSHAREDATEOUT,
					args);

				args = new Object[] {
						positionModelImpl.getGroupId(),
						positionModelImpl.getCompanyId(),
						positionModelImpl.getShareId(),
						positionModelImpl.getDate_out()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_POSITIONSHAREDATEOUT,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_POSITIONSHAREDATEOUT,
					args);
			}

			if ((positionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYGROUPSHARE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						positionModelImpl.getOriginalGroupId(),
						positionModelImpl.getOriginalCompanyId(),
						positionModelImpl.getOriginalShareId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYGROUPSHARE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYGROUPSHARE,
					args);

				args = new Object[] {
						positionModelImpl.getGroupId(),
						positionModelImpl.getCompanyId(),
						positionModelImpl.getShareId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYGROUPSHARE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYGROUPSHARE,
					args);
			}

			if ((positionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYGROUP.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						positionModelImpl.getOriginalCompanyId(),
						positionModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYGROUP, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYGROUP,
					args);

				args = new Object[] {
						positionModelImpl.getCompanyId(),
						positionModelImpl.getGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYGROUP, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYGROUP,
					args);
			}

			if ((positionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYGROUPDATE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						positionModelImpl.getOriginalCompanyId(),
						positionModelImpl.getOriginalGroupId(),
						positionModelImpl.getOriginalDate_in()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYGROUPDATE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYGROUPDATE,
					args);

				args = new Object[] {
						positionModelImpl.getCompanyId(),
						positionModelImpl.getGroupId(),
						positionModelImpl.getDate_in()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYGROUPDATE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYGROUPDATE,
					args);
			}

			if ((positionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYGROUPDATESTATUS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						positionModelImpl.getOriginalCompanyId(),
						positionModelImpl.getOriginalGroupId(),
						positionModelImpl.getOriginalDate_in(),
						positionModelImpl.getOriginalState()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYGROUPDATESTATUS,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYGROUPDATESTATUS,
					args);

				args = new Object[] {
						positionModelImpl.getCompanyId(),
						positionModelImpl.getGroupId(),
						positionModelImpl.getDate_in(),
						positionModelImpl.getState()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYGROUPDATESTATUS,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYGROUPDATESTATUS,
					args);
			}

			if ((positionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYGROUPDATESTATUSIN.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						positionModelImpl.getOriginalCompanyId(),
						positionModelImpl.getOriginalGroupId(),
						positionModelImpl.getOriginalDate_in(),
						positionModelImpl.getOriginalState_in()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYGROUPDATESTATUSIN,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYGROUPDATESTATUSIN,
					args);

				args = new Object[] {
						positionModelImpl.getCompanyId(),
						positionModelImpl.getGroupId(),
						positionModelImpl.getDate_in(),
						positionModelImpl.getState_in()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYGROUPDATESTATUSIN,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYGROUPDATESTATUSIN,
					args);
			}

			if ((positionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYGROUPDATESTATUSOUT.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						positionModelImpl.getOriginalCompanyId(),
						positionModelImpl.getOriginalGroupId(),
						positionModelImpl.getOriginalDate_out(),
						positionModelImpl.getOriginalState_out()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYGROUPDATESTATUSOUT,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYGROUPDATESTATUSOUT,
					args);

				args = new Object[] {
						positionModelImpl.getCompanyId(),
						positionModelImpl.getGroupId(),
						positionModelImpl.getDate_out(),
						positionModelImpl.getState_out()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYGROUPDATESTATUSOUT,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYGROUPDATESTATUSOUT,
					args);
			}
		}

		entityCache.putResult(PositionModelImpl.ENTITY_CACHE_ENABLED,
			PositionImpl.class, position.getPrimaryKey(), position, false);

		clearUniqueFindersCache(positionModelImpl);
		cacheUniqueFindersCache(positionModelImpl, isNew);

		position.resetOriginalValues();

		return position;
	}

	protected Position toUnwrappedModel(Position position) {
		if (position instanceof PositionImpl) {
			return position;
		}

		PositionImpl positionImpl = new PositionImpl();

		positionImpl.setNew(position.isNew());
		positionImpl.setPrimaryKey(position.getPrimaryKey());

		positionImpl.setUuid(position.getUuid());
		positionImpl.setPositionId(position.getPositionId());
		positionImpl.setGroupId(position.getGroupId());
		positionImpl.setCompanyId(position.getCompanyId());
		positionImpl.setShareId(position.getShareId());
		positionImpl.setCreateDate(position.getCreateDate());
		positionImpl.setModifiedDate(position.getModifiedDate());
		positionImpl.setState(position.getState());
		positionImpl.setState_in(position.getState_in());
		positionImpl.setState_out(position.getState_out());
		positionImpl.setDescription(position.getDescription());
		positionImpl.setPrice_in(position.getPrice_in());
		positionImpl.setPrice_real_in(position.getPrice_real_in());
		positionImpl.setLimit_price_in(position.getLimit_price_in());
		positionImpl.setDate_in(position.getDate_in());
		positionImpl.setDate_real_in(position.getDate_real_in());
		positionImpl.setPositionId_tws_in(position.getPositionId_tws_in());
		positionImpl.setPositionId_tws_out(position.getPositionId_tws_out());
		positionImpl.setType(position.getType());
		positionImpl.setPrice_out(position.getPrice_out());
		positionImpl.setPrice_real_out(position.getPrice_real_out());
		positionImpl.setLimit_price_out(position.getLimit_price_out());
		positionImpl.setDate_out(position.getDate_out());
		positionImpl.setDate_real_out(position.getDate_real_out());
		positionImpl.setShare_number(position.getShare_number());
		positionImpl.setShare_number_to_trade(position.getShare_number_to_trade());
		positionImpl.setShare_number_traded(position.getShare_number_traded());
		positionImpl.setRealtimeId_in(position.getRealtimeId_in());
		positionImpl.setRealtimeId_out(position.getRealtimeId_out());
		positionImpl.setStrategy_in(position.getStrategy_in());
		positionImpl.setStrategy_out(position.getStrategy_out());
		positionImpl.setPercentualstoplost_out(position.getPercentualstoplost_out());
		positionImpl.setPricestoplost_out(position.getPricestoplost_out());
		positionImpl.setPercentualstopprofit_out(position.getPercentualstopprofit_out());
		positionImpl.setPricestopprofit_out(position.getPricestopprofit_out());
		positionImpl.setPendingcancelled(position.getPendingcancelled());
		positionImpl.setTrading_data_operations(position.getTrading_data_operations());
		positionImpl.setSimulation_mode(position.isSimulation_mode());

		return positionImpl;
	}

	/**
	 * Returns the position with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the position
	 * @return the position
	 * @throws NoSuchPositionException if a position with the primary key could not be found
	 */
	@Override
	public Position findByPrimaryKey(Serializable primaryKey)
		throws NoSuchPositionException {
		Position position = fetchByPrimaryKey(primaryKey);

		if (position == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchPositionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return position;
	}

	/**
	 * Returns the position with the primary key or throws a {@link NoSuchPositionException} if it could not be found.
	 *
	 * @param positionId the primary key of the position
	 * @return the position
	 * @throws NoSuchPositionException if a position with the primary key could not be found
	 */
	@Override
	public Position findByPrimaryKey(long positionId)
		throws NoSuchPositionException {
		return findByPrimaryKey((Serializable)positionId);
	}

	/**
	 * Returns the position with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the position
	 * @return the position, or <code>null</code> if a position with the primary key could not be found
	 */
	@Override
	public Position fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(PositionModelImpl.ENTITY_CACHE_ENABLED,
				PositionImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Position position = (Position)serializable;

		if (position == null) {
			Session session = null;

			try {
				session = openSession();

				position = (Position)session.get(PositionImpl.class, primaryKey);

				if (position != null) {
					cacheResult(position);
				}
				else {
					entityCache.putResult(PositionModelImpl.ENTITY_CACHE_ENABLED,
						PositionImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(PositionModelImpl.ENTITY_CACHE_ENABLED,
					PositionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return position;
	}

	/**
	 * Returns the position with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param positionId the primary key of the position
	 * @return the position, or <code>null</code> if a position with the primary key could not be found
	 */
	@Override
	public Position fetchByPrimaryKey(long positionId) {
		return fetchByPrimaryKey((Serializable)positionId);
	}

	@Override
	public Map<Serializable, Position> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Position> map = new HashMap<Serializable, Position>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Position position = fetchByPrimaryKey(primaryKey);

			if (position != null) {
				map.put(primaryKey, position);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(PositionModelImpl.ENTITY_CACHE_ENABLED,
					PositionImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Position)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_POSITION_WHERE_PKS_IN);

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

			for (Position position : (List<Position>)q.list()) {
				map.put(position.getPrimaryKeyObj(), position);

				cacheResult(position);

				uncachedPrimaryKeys.remove(position.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(PositionModelImpl.ENTITY_CACHE_ENABLED,
					PositionImpl.class, primaryKey, nullModel);
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
	 * Returns all the positions.
	 *
	 * @return the positions
	 */
	@Override
	public List<Position> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the positions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of positions
	 * @param end the upper bound of the range of positions (not inclusive)
	 * @return the range of positions
	 */
	@Override
	public List<Position> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the positions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of positions
	 * @param end the upper bound of the range of positions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of positions
	 */
	@Override
	public List<Position> findAll(int start, int end,
		OrderByComparator<Position> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the positions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of positions
	 * @param end the upper bound of the range of positions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of positions
	 */
	@Override
	public List<Position> findAll(int start, int end,
		OrderByComparator<Position> orderByComparator, boolean retrieveFromCache) {
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

		List<Position> list = null;

		if (retrieveFromCache) {
			list = (List<Position>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_POSITION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_POSITION;

				if (pagination) {
					sql = sql.concat(PositionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Position>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Position>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the positions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Position position : findAll()) {
			remove(position);
		}
	}

	/**
	 * Returns the number of positions.
	 *
	 * @return the number of positions
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_POSITION);

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
		return PositionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the position persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(PositionImpl.class.getName());
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
	private static final String _SQL_SELECT_POSITION = "SELECT position FROM Position position";
	private static final String _SQL_SELECT_POSITION_WHERE_PKS_IN = "SELECT position FROM Position position WHERE positionId IN (";
	private static final String _SQL_SELECT_POSITION_WHERE = "SELECT position FROM Position position WHERE ";
	private static final String _SQL_COUNT_POSITION = "SELECT COUNT(position) FROM Position position";
	private static final String _SQL_COUNT_POSITION_WHERE = "SELECT COUNT(position) FROM Position position WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "position.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Position exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Position exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(PositionPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid", "state", "type"
			});
}