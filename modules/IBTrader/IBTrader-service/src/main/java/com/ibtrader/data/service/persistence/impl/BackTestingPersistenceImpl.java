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

import com.ibtrader.data.exception.NoSuchBackTestingException;
import com.ibtrader.data.model.BackTesting;
import com.ibtrader.data.model.impl.BackTestingImpl;
import com.ibtrader.data.model.impl.BackTestingModelImpl;
import com.ibtrader.data.service.persistence.BackTestingPersistence;

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
 * The persistence implementation for the back testing service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BackTestingPersistence
 * @see com.ibtrader.data.service.persistence.BackTestingUtil
 * @generated
 */
@ProviderType
public class BackTestingPersistenceImpl extends BasePersistenceImpl<BackTesting>
	implements BackTestingPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link BackTestingUtil} to access the back testing persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = BackTestingImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(BackTestingModelImpl.ENTITY_CACHE_ENABLED,
			BackTestingModelImpl.FINDER_CACHE_ENABLED, BackTestingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(BackTestingModelImpl.ENTITY_CACHE_ENABLED,
			BackTestingModelImpl.FINDER_CACHE_ENABLED, BackTestingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(BackTestingModelImpl.ENTITY_CACHE_ENABLED,
			BackTestingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(BackTestingModelImpl.ENTITY_CACHE_ENABLED,
			BackTestingModelImpl.FINDER_CACHE_ENABLED, BackTestingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(BackTestingModelImpl.ENTITY_CACHE_ENABLED,
			BackTestingModelImpl.FINDER_CACHE_ENABLED, BackTestingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			BackTestingModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(BackTestingModelImpl.ENTITY_CACHE_ENABLED,
			BackTestingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the back testings where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching back testings
	 */
	@Override
	public List<BackTesting> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the back testings where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackTestingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of back testings
	 * @param end the upper bound of the range of back testings (not inclusive)
	 * @return the range of matching back testings
	 */
	@Override
	public List<BackTesting> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the back testings where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackTestingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of back testings
	 * @param end the upper bound of the range of back testings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching back testings
	 */
	@Override
	public List<BackTesting> findByUuid(String uuid, int start, int end,
		OrderByComparator<BackTesting> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the back testings where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackTestingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of back testings
	 * @param end the upper bound of the range of back testings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching back testings
	 */
	@Override
	public List<BackTesting> findByUuid(String uuid, int start, int end,
		OrderByComparator<BackTesting> orderByComparator,
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

		List<BackTesting> list = null;

		if (retrieveFromCache) {
			list = (List<BackTesting>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (BackTesting backTesting : list) {
					if (!Objects.equals(uuid, backTesting.getUuid())) {
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

			query.append(_SQL_SELECT_BACKTESTING_WHERE);

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
				query.append(BackTestingModelImpl.ORDER_BY_JPQL);
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
					list = (List<BackTesting>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<BackTesting>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first back testing in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching back testing
	 * @throws NoSuchBackTestingException if a matching back testing could not be found
	 */
	@Override
	public BackTesting findByUuid_First(String uuid,
		OrderByComparator<BackTesting> orderByComparator)
		throws NoSuchBackTestingException {
		BackTesting backTesting = fetchByUuid_First(uuid, orderByComparator);

		if (backTesting != null) {
			return backTesting;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBackTestingException(msg.toString());
	}

	/**
	 * Returns the first back testing in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching back testing, or <code>null</code> if a matching back testing could not be found
	 */
	@Override
	public BackTesting fetchByUuid_First(String uuid,
		OrderByComparator<BackTesting> orderByComparator) {
		List<BackTesting> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last back testing in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching back testing
	 * @throws NoSuchBackTestingException if a matching back testing could not be found
	 */
	@Override
	public BackTesting findByUuid_Last(String uuid,
		OrderByComparator<BackTesting> orderByComparator)
		throws NoSuchBackTestingException {
		BackTesting backTesting = fetchByUuid_Last(uuid, orderByComparator);

		if (backTesting != null) {
			return backTesting;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBackTestingException(msg.toString());
	}

	/**
	 * Returns the last back testing in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching back testing, or <code>null</code> if a matching back testing could not be found
	 */
	@Override
	public BackTesting fetchByUuid_Last(String uuid,
		OrderByComparator<BackTesting> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<BackTesting> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the back testings before and after the current back testing in the ordered set where uuid = &#63;.
	 *
	 * @param backTId the primary key of the current back testing
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next back testing
	 * @throws NoSuchBackTestingException if a back testing with the primary key could not be found
	 */
	@Override
	public BackTesting[] findByUuid_PrevAndNext(long backTId, String uuid,
		OrderByComparator<BackTesting> orderByComparator)
		throws NoSuchBackTestingException {
		BackTesting backTesting = findByPrimaryKey(backTId);

		Session session = null;

		try {
			session = openSession();

			BackTesting[] array = new BackTestingImpl[3];

			array[0] = getByUuid_PrevAndNext(session, backTesting, uuid,
					orderByComparator, true);

			array[1] = backTesting;

			array[2] = getByUuid_PrevAndNext(session, backTesting, uuid,
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

	protected BackTesting getByUuid_PrevAndNext(Session session,
		BackTesting backTesting, String uuid,
		OrderByComparator<BackTesting> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_BACKTESTING_WHERE);

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
			query.append(BackTestingModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(backTesting);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BackTesting> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the back testings where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (BackTesting backTesting : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(backTesting);
		}
	}

	/**
	 * Returns the number of back testings where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching back testings
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_BACKTESTING_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "backTesting.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "backTesting.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(backTesting.uuid IS NULL OR backTesting.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(BackTestingModelImpl.ENTITY_CACHE_ENABLED,
			BackTestingModelImpl.FINDER_CACHE_ENABLED, BackTestingImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			BackTestingModelImpl.UUID_COLUMN_BITMASK |
			BackTestingModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(BackTestingModelImpl.ENTITY_CACHE_ENABLED,
			BackTestingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the back testing where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchBackTestingException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching back testing
	 * @throws NoSuchBackTestingException if a matching back testing could not be found
	 */
	@Override
	public BackTesting findByUUID_G(String uuid, long groupId)
		throws NoSuchBackTestingException {
		BackTesting backTesting = fetchByUUID_G(uuid, groupId);

		if (backTesting == null) {
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

			throw new NoSuchBackTestingException(msg.toString());
		}

		return backTesting;
	}

	/**
	 * Returns the back testing where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching back testing, or <code>null</code> if a matching back testing could not be found
	 */
	@Override
	public BackTesting fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the back testing where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching back testing, or <code>null</code> if a matching back testing could not be found
	 */
	@Override
	public BackTesting fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof BackTesting) {
			BackTesting backTesting = (BackTesting)result;

			if (!Objects.equals(uuid, backTesting.getUuid()) ||
					(groupId != backTesting.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_BACKTESTING_WHERE);

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

				List<BackTesting> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					BackTesting backTesting = list.get(0);

					result = backTesting;

					cacheResult(backTesting);

					if ((backTesting.getUuid() == null) ||
							!backTesting.getUuid().equals(uuid) ||
							(backTesting.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, backTesting);
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
			return (BackTesting)result;
		}
	}

	/**
	 * Removes the back testing where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the back testing that was removed
	 */
	@Override
	public BackTesting removeByUUID_G(String uuid, long groupId)
		throws NoSuchBackTestingException {
		BackTesting backTesting = findByUUID_G(uuid, groupId);

		return remove(backTesting);
	}

	/**
	 * Returns the number of back testings where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching back testings
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_BACKTESTING_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "backTesting.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "backTesting.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(backTesting.uuid IS NULL OR backTesting.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "backTesting.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(BackTestingModelImpl.ENTITY_CACHE_ENABLED,
			BackTestingModelImpl.FINDER_CACHE_ENABLED, BackTestingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(BackTestingModelImpl.ENTITY_CACHE_ENABLED,
			BackTestingModelImpl.FINDER_CACHE_ENABLED, BackTestingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			BackTestingModelImpl.UUID_COLUMN_BITMASK |
			BackTestingModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(BackTestingModelImpl.ENTITY_CACHE_ENABLED,
			BackTestingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the back testings where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching back testings
	 */
	@Override
	public List<BackTesting> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the back testings where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackTestingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of back testings
	 * @param end the upper bound of the range of back testings (not inclusive)
	 * @return the range of matching back testings
	 */
	@Override
	public List<BackTesting> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the back testings where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackTestingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of back testings
	 * @param end the upper bound of the range of back testings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching back testings
	 */
	@Override
	public List<BackTesting> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<BackTesting> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the back testings where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackTestingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of back testings
	 * @param end the upper bound of the range of back testings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching back testings
	 */
	@Override
	public List<BackTesting> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<BackTesting> orderByComparator,
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

		List<BackTesting> list = null;

		if (retrieveFromCache) {
			list = (List<BackTesting>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (BackTesting backTesting : list) {
					if (!Objects.equals(uuid, backTesting.getUuid()) ||
							(companyId != backTesting.getCompanyId())) {
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

			query.append(_SQL_SELECT_BACKTESTING_WHERE);

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
				query.append(BackTestingModelImpl.ORDER_BY_JPQL);
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
					list = (List<BackTesting>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<BackTesting>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first back testing in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching back testing
	 * @throws NoSuchBackTestingException if a matching back testing could not be found
	 */
	@Override
	public BackTesting findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<BackTesting> orderByComparator)
		throws NoSuchBackTestingException {
		BackTesting backTesting = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (backTesting != null) {
			return backTesting;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBackTestingException(msg.toString());
	}

	/**
	 * Returns the first back testing in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching back testing, or <code>null</code> if a matching back testing could not be found
	 */
	@Override
	public BackTesting fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<BackTesting> orderByComparator) {
		List<BackTesting> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last back testing in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching back testing
	 * @throws NoSuchBackTestingException if a matching back testing could not be found
	 */
	@Override
	public BackTesting findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<BackTesting> orderByComparator)
		throws NoSuchBackTestingException {
		BackTesting backTesting = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (backTesting != null) {
			return backTesting;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBackTestingException(msg.toString());
	}

	/**
	 * Returns the last back testing in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching back testing, or <code>null</code> if a matching back testing could not be found
	 */
	@Override
	public BackTesting fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<BackTesting> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<BackTesting> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the back testings before and after the current back testing in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param backTId the primary key of the current back testing
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next back testing
	 * @throws NoSuchBackTestingException if a back testing with the primary key could not be found
	 */
	@Override
	public BackTesting[] findByUuid_C_PrevAndNext(long backTId, String uuid,
		long companyId, OrderByComparator<BackTesting> orderByComparator)
		throws NoSuchBackTestingException {
		BackTesting backTesting = findByPrimaryKey(backTId);

		Session session = null;

		try {
			session = openSession();

			BackTesting[] array = new BackTestingImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, backTesting, uuid,
					companyId, orderByComparator, true);

			array[1] = backTesting;

			array[2] = getByUuid_C_PrevAndNext(session, backTesting, uuid,
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

	protected BackTesting getByUuid_C_PrevAndNext(Session session,
		BackTesting backTesting, String uuid, long companyId,
		OrderByComparator<BackTesting> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_BACKTESTING_WHERE);

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
			query.append(BackTestingModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(backTesting);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BackTesting> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the back testings where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (BackTesting backTesting : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(backTesting);
		}
	}

	/**
	 * Returns the number of back testings where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching back testings
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_BACKTESTING_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "backTesting.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "backTesting.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(backTesting.uuid IS NULL OR backTesting.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "backTesting.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SHARECOMPANYGROUP =
		new FinderPath(BackTestingModelImpl.ENTITY_CACHE_ENABLED,
			BackTestingModelImpl.FINDER_CACHE_ENABLED, BackTestingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByShareCompanyGroup",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SHARECOMPANYGROUP =
		new FinderPath(BackTestingModelImpl.ENTITY_CACHE_ENABLED,
			BackTestingModelImpl.FINDER_CACHE_ENABLED, BackTestingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByShareCompanyGroup",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			BackTestingModelImpl.SHAREID_COLUMN_BITMASK |
			BackTestingModelImpl.COMPANYID_COLUMN_BITMASK |
			BackTestingModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SHARECOMPANYGROUP = new FinderPath(BackTestingModelImpl.ENTITY_CACHE_ENABLED,
			BackTestingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByShareCompanyGroup",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});

	/**
	 * Returns all the back testings where shareId = &#63; and companyId = &#63; and groupId = &#63;.
	 *
	 * @param shareId the share ID
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @return the matching back testings
	 */
	@Override
	public List<BackTesting> findByShareCompanyGroup(long shareId,
		long companyId, long groupId) {
		return findByShareCompanyGroup(shareId, companyId, groupId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the back testings where shareId = &#63; and companyId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackTestingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param shareId the share ID
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of back testings
	 * @param end the upper bound of the range of back testings (not inclusive)
	 * @return the range of matching back testings
	 */
	@Override
	public List<BackTesting> findByShareCompanyGroup(long shareId,
		long companyId, long groupId, int start, int end) {
		return findByShareCompanyGroup(shareId, companyId, groupId, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the back testings where shareId = &#63; and companyId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackTestingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param shareId the share ID
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of back testings
	 * @param end the upper bound of the range of back testings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching back testings
	 */
	@Override
	public List<BackTesting> findByShareCompanyGroup(long shareId,
		long companyId, long groupId, int start, int end,
		OrderByComparator<BackTesting> orderByComparator) {
		return findByShareCompanyGroup(shareId, companyId, groupId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the back testings where shareId = &#63; and companyId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackTestingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param shareId the share ID
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of back testings
	 * @param end the upper bound of the range of back testings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching back testings
	 */
	@Override
	public List<BackTesting> findByShareCompanyGroup(long shareId,
		long companyId, long groupId, int start, int end,
		OrderByComparator<BackTesting> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SHARECOMPANYGROUP;
			finderArgs = new Object[] { shareId, companyId, groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SHARECOMPANYGROUP;
			finderArgs = new Object[] {
					shareId, companyId, groupId,
					
					start, end, orderByComparator
				};
		}

		List<BackTesting> list = null;

		if (retrieveFromCache) {
			list = (List<BackTesting>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (BackTesting backTesting : list) {
					if ((shareId != backTesting.getShareId()) ||
							(companyId != backTesting.getCompanyId()) ||
							(groupId != backTesting.getGroupId())) {
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

			query.append(_SQL_SELECT_BACKTESTING_WHERE);

			query.append(_FINDER_COLUMN_SHARECOMPANYGROUP_SHAREID_2);

			query.append(_FINDER_COLUMN_SHARECOMPANYGROUP_COMPANYID_2);

			query.append(_FINDER_COLUMN_SHARECOMPANYGROUP_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(BackTestingModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(shareId);

				qPos.add(companyId);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<BackTesting>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<BackTesting>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first back testing in the ordered set where shareId = &#63; and companyId = &#63; and groupId = &#63;.
	 *
	 * @param shareId the share ID
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching back testing
	 * @throws NoSuchBackTestingException if a matching back testing could not be found
	 */
	@Override
	public BackTesting findByShareCompanyGroup_First(long shareId,
		long companyId, long groupId,
		OrderByComparator<BackTesting> orderByComparator)
		throws NoSuchBackTestingException {
		BackTesting backTesting = fetchByShareCompanyGroup_First(shareId,
				companyId, groupId, orderByComparator);

		if (backTesting != null) {
			return backTesting;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("shareId=");
		msg.append(shareId);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBackTestingException(msg.toString());
	}

	/**
	 * Returns the first back testing in the ordered set where shareId = &#63; and companyId = &#63; and groupId = &#63;.
	 *
	 * @param shareId the share ID
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching back testing, or <code>null</code> if a matching back testing could not be found
	 */
	@Override
	public BackTesting fetchByShareCompanyGroup_First(long shareId,
		long companyId, long groupId,
		OrderByComparator<BackTesting> orderByComparator) {
		List<BackTesting> list = findByShareCompanyGroup(shareId, companyId,
				groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last back testing in the ordered set where shareId = &#63; and companyId = &#63; and groupId = &#63;.
	 *
	 * @param shareId the share ID
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching back testing
	 * @throws NoSuchBackTestingException if a matching back testing could not be found
	 */
	@Override
	public BackTesting findByShareCompanyGroup_Last(long shareId,
		long companyId, long groupId,
		OrderByComparator<BackTesting> orderByComparator)
		throws NoSuchBackTestingException {
		BackTesting backTesting = fetchByShareCompanyGroup_Last(shareId,
				companyId, groupId, orderByComparator);

		if (backTesting != null) {
			return backTesting;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("shareId=");
		msg.append(shareId);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBackTestingException(msg.toString());
	}

	/**
	 * Returns the last back testing in the ordered set where shareId = &#63; and companyId = &#63; and groupId = &#63;.
	 *
	 * @param shareId the share ID
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching back testing, or <code>null</code> if a matching back testing could not be found
	 */
	@Override
	public BackTesting fetchByShareCompanyGroup_Last(long shareId,
		long companyId, long groupId,
		OrderByComparator<BackTesting> orderByComparator) {
		int count = countByShareCompanyGroup(shareId, companyId, groupId);

		if (count == 0) {
			return null;
		}

		List<BackTesting> list = findByShareCompanyGroup(shareId, companyId,
				groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the back testings before and after the current back testing in the ordered set where shareId = &#63; and companyId = &#63; and groupId = &#63;.
	 *
	 * @param backTId the primary key of the current back testing
	 * @param shareId the share ID
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next back testing
	 * @throws NoSuchBackTestingException if a back testing with the primary key could not be found
	 */
	@Override
	public BackTesting[] findByShareCompanyGroup_PrevAndNext(long backTId,
		long shareId, long companyId, long groupId,
		OrderByComparator<BackTesting> orderByComparator)
		throws NoSuchBackTestingException {
		BackTesting backTesting = findByPrimaryKey(backTId);

		Session session = null;

		try {
			session = openSession();

			BackTesting[] array = new BackTestingImpl[3];

			array[0] = getByShareCompanyGroup_PrevAndNext(session, backTesting,
					shareId, companyId, groupId, orderByComparator, true);

			array[1] = backTesting;

			array[2] = getByShareCompanyGroup_PrevAndNext(session, backTesting,
					shareId, companyId, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected BackTesting getByShareCompanyGroup_PrevAndNext(Session session,
		BackTesting backTesting, long shareId, long companyId, long groupId,
		OrderByComparator<BackTesting> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_BACKTESTING_WHERE);

		query.append(_FINDER_COLUMN_SHARECOMPANYGROUP_SHAREID_2);

		query.append(_FINDER_COLUMN_SHARECOMPANYGROUP_COMPANYID_2);

		query.append(_FINDER_COLUMN_SHARECOMPANYGROUP_GROUPID_2);

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
			query.append(BackTestingModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(shareId);

		qPos.add(companyId);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(backTesting);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BackTesting> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the back testings where shareId = &#63; and companyId = &#63; and groupId = &#63; from the database.
	 *
	 * @param shareId the share ID
	 * @param companyId the company ID
	 * @param groupId the group ID
	 */
	@Override
	public void removeByShareCompanyGroup(long shareId, long companyId,
		long groupId) {
		for (BackTesting backTesting : findByShareCompanyGroup(shareId,
				companyId, groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(backTesting);
		}
	}

	/**
	 * Returns the number of back testings where shareId = &#63; and companyId = &#63; and groupId = &#63;.
	 *
	 * @param shareId the share ID
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @return the number of matching back testings
	 */
	@Override
	public int countByShareCompanyGroup(long shareId, long companyId,
		long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SHARECOMPANYGROUP;

		Object[] finderArgs = new Object[] { shareId, companyId, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_BACKTESTING_WHERE);

			query.append(_FINDER_COLUMN_SHARECOMPANYGROUP_SHAREID_2);

			query.append(_FINDER_COLUMN_SHARECOMPANYGROUP_COMPANYID_2);

			query.append(_FINDER_COLUMN_SHARECOMPANYGROUP_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(shareId);

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

	private static final String _FINDER_COLUMN_SHARECOMPANYGROUP_SHAREID_2 = "backTesting.shareId = ? AND ";
	private static final String _FINDER_COLUMN_SHARECOMPANYGROUP_COMPANYID_2 = "backTesting.companyId = ? AND ";
	private static final String _FINDER_COLUMN_SHARECOMPANYGROUP_GROUPID_2 = "backTesting.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_STATUSSHARECOMPANYGROUP =
		new FinderPath(BackTestingModelImpl.ENTITY_CACHE_ENABLED,
			BackTestingModelImpl.FINDER_CACHE_ENABLED, BackTestingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByStatusShareCompanyGroup",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUSSHARECOMPANYGROUP =
		new FinderPath(BackTestingModelImpl.ENTITY_CACHE_ENABLED,
			BackTestingModelImpl.FINDER_CACHE_ENABLED, BackTestingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByStatusShareCompanyGroup",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Long.class.getName(), Long.class.getName()
			},
			BackTestingModelImpl.STATUS_COLUMN_BITMASK |
			BackTestingModelImpl.COMPANYID_COLUMN_BITMASK |
			BackTestingModelImpl.GROUPID_COLUMN_BITMASK |
			BackTestingModelImpl.SHAREID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_STATUSSHARECOMPANYGROUP = new FinderPath(BackTestingModelImpl.ENTITY_CACHE_ENABLED,
			BackTestingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByStatusShareCompanyGroup",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Long.class.getName(), Long.class.getName()
			});

	/**
	 * Returns all the back testings where status = &#63; and companyId = &#63; and groupId = &#63; and shareId = &#63;.
	 *
	 * @param status the status
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param shareId the share ID
	 * @return the matching back testings
	 */
	@Override
	public List<BackTesting> findByStatusShareCompanyGroup(String status,
		long companyId, long groupId, long shareId) {
		return findByStatusShareCompanyGroup(status, companyId, groupId,
			shareId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the back testings where status = &#63; and companyId = &#63; and groupId = &#63; and shareId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackTestingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param status the status
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param shareId the share ID
	 * @param start the lower bound of the range of back testings
	 * @param end the upper bound of the range of back testings (not inclusive)
	 * @return the range of matching back testings
	 */
	@Override
	public List<BackTesting> findByStatusShareCompanyGroup(String status,
		long companyId, long groupId, long shareId, int start, int end) {
		return findByStatusShareCompanyGroup(status, companyId, groupId,
			shareId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the back testings where status = &#63; and companyId = &#63; and groupId = &#63; and shareId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackTestingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param status the status
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param shareId the share ID
	 * @param start the lower bound of the range of back testings
	 * @param end the upper bound of the range of back testings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching back testings
	 */
	@Override
	public List<BackTesting> findByStatusShareCompanyGroup(String status,
		long companyId, long groupId, long shareId, int start, int end,
		OrderByComparator<BackTesting> orderByComparator) {
		return findByStatusShareCompanyGroup(status, companyId, groupId,
			shareId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the back testings where status = &#63; and companyId = &#63; and groupId = &#63; and shareId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackTestingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param status the status
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param shareId the share ID
	 * @param start the lower bound of the range of back testings
	 * @param end the upper bound of the range of back testings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching back testings
	 */
	@Override
	public List<BackTesting> findByStatusShareCompanyGroup(String status,
		long companyId, long groupId, long shareId, int start, int end,
		OrderByComparator<BackTesting> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUSSHARECOMPANYGROUP;
			finderArgs = new Object[] { status, companyId, groupId, shareId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_STATUSSHARECOMPANYGROUP;
			finderArgs = new Object[] {
					status, companyId, groupId, shareId,
					
					start, end, orderByComparator
				};
		}

		List<BackTesting> list = null;

		if (retrieveFromCache) {
			list = (List<BackTesting>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (BackTesting backTesting : list) {
					if (!Objects.equals(status, backTesting.getStatus()) ||
							(companyId != backTesting.getCompanyId()) ||
							(groupId != backTesting.getGroupId()) ||
							(shareId != backTesting.getShareId())) {
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

			query.append(_SQL_SELECT_BACKTESTING_WHERE);

			boolean bindStatus = false;

			if (status == null) {
				query.append(_FINDER_COLUMN_STATUSSHARECOMPANYGROUP_STATUS_1);
			}
			else if (status.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_STATUSSHARECOMPANYGROUP_STATUS_3);
			}
			else {
				bindStatus = true;

				query.append(_FINDER_COLUMN_STATUSSHARECOMPANYGROUP_STATUS_2);
			}

			query.append(_FINDER_COLUMN_STATUSSHARECOMPANYGROUP_COMPANYID_2);

			query.append(_FINDER_COLUMN_STATUSSHARECOMPANYGROUP_GROUPID_2);

			query.append(_FINDER_COLUMN_STATUSSHARECOMPANYGROUP_SHAREID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(BackTestingModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindStatus) {
					qPos.add(status);
				}

				qPos.add(companyId);

				qPos.add(groupId);

				qPos.add(shareId);

				if (!pagination) {
					list = (List<BackTesting>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<BackTesting>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first back testing in the ordered set where status = &#63; and companyId = &#63; and groupId = &#63; and shareId = &#63;.
	 *
	 * @param status the status
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param shareId the share ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching back testing
	 * @throws NoSuchBackTestingException if a matching back testing could not be found
	 */
	@Override
	public BackTesting findByStatusShareCompanyGroup_First(String status,
		long companyId, long groupId, long shareId,
		OrderByComparator<BackTesting> orderByComparator)
		throws NoSuchBackTestingException {
		BackTesting backTesting = fetchByStatusShareCompanyGroup_First(status,
				companyId, groupId, shareId, orderByComparator);

		if (backTesting != null) {
			return backTesting;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("status=");
		msg.append(status);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(", shareId=");
		msg.append(shareId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBackTestingException(msg.toString());
	}

	/**
	 * Returns the first back testing in the ordered set where status = &#63; and companyId = &#63; and groupId = &#63; and shareId = &#63;.
	 *
	 * @param status the status
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param shareId the share ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching back testing, or <code>null</code> if a matching back testing could not be found
	 */
	@Override
	public BackTesting fetchByStatusShareCompanyGroup_First(String status,
		long companyId, long groupId, long shareId,
		OrderByComparator<BackTesting> orderByComparator) {
		List<BackTesting> list = findByStatusShareCompanyGroup(status,
				companyId, groupId, shareId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last back testing in the ordered set where status = &#63; and companyId = &#63; and groupId = &#63; and shareId = &#63;.
	 *
	 * @param status the status
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param shareId the share ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching back testing
	 * @throws NoSuchBackTestingException if a matching back testing could not be found
	 */
	@Override
	public BackTesting findByStatusShareCompanyGroup_Last(String status,
		long companyId, long groupId, long shareId,
		OrderByComparator<BackTesting> orderByComparator)
		throws NoSuchBackTestingException {
		BackTesting backTesting = fetchByStatusShareCompanyGroup_Last(status,
				companyId, groupId, shareId, orderByComparator);

		if (backTesting != null) {
			return backTesting;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("status=");
		msg.append(status);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(", shareId=");
		msg.append(shareId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBackTestingException(msg.toString());
	}

	/**
	 * Returns the last back testing in the ordered set where status = &#63; and companyId = &#63; and groupId = &#63; and shareId = &#63;.
	 *
	 * @param status the status
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param shareId the share ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching back testing, or <code>null</code> if a matching back testing could not be found
	 */
	@Override
	public BackTesting fetchByStatusShareCompanyGroup_Last(String status,
		long companyId, long groupId, long shareId,
		OrderByComparator<BackTesting> orderByComparator) {
		int count = countByStatusShareCompanyGroup(status, companyId, groupId,
				shareId);

		if (count == 0) {
			return null;
		}

		List<BackTesting> list = findByStatusShareCompanyGroup(status,
				companyId, groupId, shareId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the back testings before and after the current back testing in the ordered set where status = &#63; and companyId = &#63; and groupId = &#63; and shareId = &#63;.
	 *
	 * @param backTId the primary key of the current back testing
	 * @param status the status
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param shareId the share ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next back testing
	 * @throws NoSuchBackTestingException if a back testing with the primary key could not be found
	 */
	@Override
	public BackTesting[] findByStatusShareCompanyGroup_PrevAndNext(
		long backTId, String status, long companyId, long groupId,
		long shareId, OrderByComparator<BackTesting> orderByComparator)
		throws NoSuchBackTestingException {
		BackTesting backTesting = findByPrimaryKey(backTId);

		Session session = null;

		try {
			session = openSession();

			BackTesting[] array = new BackTestingImpl[3];

			array[0] = getByStatusShareCompanyGroup_PrevAndNext(session,
					backTesting, status, companyId, groupId, shareId,
					orderByComparator, true);

			array[1] = backTesting;

			array[2] = getByStatusShareCompanyGroup_PrevAndNext(session,
					backTesting, status, companyId, groupId, shareId,
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

	protected BackTesting getByStatusShareCompanyGroup_PrevAndNext(
		Session session, BackTesting backTesting, String status,
		long companyId, long groupId, long shareId,
		OrderByComparator<BackTesting> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(7 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(6);
		}

		query.append(_SQL_SELECT_BACKTESTING_WHERE);

		boolean bindStatus = false;

		if (status == null) {
			query.append(_FINDER_COLUMN_STATUSSHARECOMPANYGROUP_STATUS_1);
		}
		else if (status.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_STATUSSHARECOMPANYGROUP_STATUS_3);
		}
		else {
			bindStatus = true;

			query.append(_FINDER_COLUMN_STATUSSHARECOMPANYGROUP_STATUS_2);
		}

		query.append(_FINDER_COLUMN_STATUSSHARECOMPANYGROUP_COMPANYID_2);

		query.append(_FINDER_COLUMN_STATUSSHARECOMPANYGROUP_GROUPID_2);

		query.append(_FINDER_COLUMN_STATUSSHARECOMPANYGROUP_SHAREID_2);

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
			query.append(BackTestingModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindStatus) {
			qPos.add(status);
		}

		qPos.add(companyId);

		qPos.add(groupId);

		qPos.add(shareId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(backTesting);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BackTesting> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the back testings where status = &#63; and companyId = &#63; and groupId = &#63; and shareId = &#63; from the database.
	 *
	 * @param status the status
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param shareId the share ID
	 */
	@Override
	public void removeByStatusShareCompanyGroup(String status, long companyId,
		long groupId, long shareId) {
		for (BackTesting backTesting : findByStatusShareCompanyGroup(status,
				companyId, groupId, shareId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(backTesting);
		}
	}

	/**
	 * Returns the number of back testings where status = &#63; and companyId = &#63; and groupId = &#63; and shareId = &#63;.
	 *
	 * @param status the status
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param shareId the share ID
	 * @return the number of matching back testings
	 */
	@Override
	public int countByStatusShareCompanyGroup(String status, long companyId,
		long groupId, long shareId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_STATUSSHARECOMPANYGROUP;

		Object[] finderArgs = new Object[] { status, companyId, groupId, shareId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_BACKTESTING_WHERE);

			boolean bindStatus = false;

			if (status == null) {
				query.append(_FINDER_COLUMN_STATUSSHARECOMPANYGROUP_STATUS_1);
			}
			else if (status.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_STATUSSHARECOMPANYGROUP_STATUS_3);
			}
			else {
				bindStatus = true;

				query.append(_FINDER_COLUMN_STATUSSHARECOMPANYGROUP_STATUS_2);
			}

			query.append(_FINDER_COLUMN_STATUSSHARECOMPANYGROUP_COMPANYID_2);

			query.append(_FINDER_COLUMN_STATUSSHARECOMPANYGROUP_GROUPID_2);

			query.append(_FINDER_COLUMN_STATUSSHARECOMPANYGROUP_SHAREID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindStatus) {
					qPos.add(status);
				}

				qPos.add(companyId);

				qPos.add(groupId);

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

	private static final String _FINDER_COLUMN_STATUSSHARECOMPANYGROUP_STATUS_1 = "backTesting.status IS NULL AND ";
	private static final String _FINDER_COLUMN_STATUSSHARECOMPANYGROUP_STATUS_2 = "backTesting.status = ? AND ";
	private static final String _FINDER_COLUMN_STATUSSHARECOMPANYGROUP_STATUS_3 = "(backTesting.status IS NULL OR backTesting.status = '') AND ";
	private static final String _FINDER_COLUMN_STATUSSHARECOMPANYGROUP_COMPANYID_2 =
		"backTesting.companyId = ? AND ";
	private static final String _FINDER_COLUMN_STATUSSHARECOMPANYGROUP_GROUPID_2 =
		"backTesting.groupId = ? AND ";
	private static final String _FINDER_COLUMN_STATUSSHARECOMPANYGROUP_SHAREID_2 =
		"backTesting.shareId = ?";

	public BackTestingPersistenceImpl() {
		setModelClass(BackTesting.class);
	}

	/**
	 * Caches the back testing in the entity cache if it is enabled.
	 *
	 * @param backTesting the back testing
	 */
	@Override
	public void cacheResult(BackTesting backTesting) {
		entityCache.putResult(BackTestingModelImpl.ENTITY_CACHE_ENABLED,
			BackTestingImpl.class, backTesting.getPrimaryKey(), backTesting);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { backTesting.getUuid(), backTesting.getGroupId() },
			backTesting);

		backTesting.resetOriginalValues();
	}

	/**
	 * Caches the back testings in the entity cache if it is enabled.
	 *
	 * @param backTestings the back testings
	 */
	@Override
	public void cacheResult(List<BackTesting> backTestings) {
		for (BackTesting backTesting : backTestings) {
			if (entityCache.getResult(
						BackTestingModelImpl.ENTITY_CACHE_ENABLED,
						BackTestingImpl.class, backTesting.getPrimaryKey()) == null) {
				cacheResult(backTesting);
			}
			else {
				backTesting.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all back testings.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(BackTestingImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the back testing.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(BackTesting backTesting) {
		entityCache.removeResult(BackTestingModelImpl.ENTITY_CACHE_ENABLED,
			BackTestingImpl.class, backTesting.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((BackTestingModelImpl)backTesting, true);
	}

	@Override
	public void clearCache(List<BackTesting> backTestings) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (BackTesting backTesting : backTestings) {
			entityCache.removeResult(BackTestingModelImpl.ENTITY_CACHE_ENABLED,
				BackTestingImpl.class, backTesting.getPrimaryKey());

			clearUniqueFindersCache((BackTestingModelImpl)backTesting, true);
		}
	}

	protected void cacheUniqueFindersCache(
		BackTestingModelImpl backTestingModelImpl) {
		Object[] args = new Object[] {
				backTestingModelImpl.getUuid(),
				backTestingModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			backTestingModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		BackTestingModelImpl backTestingModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					backTestingModelImpl.getUuid(),
					backTestingModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((backTestingModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					backTestingModelImpl.getOriginalUuid(),
					backTestingModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new back testing with the primary key. Does not add the back testing to the database.
	 *
	 * @param backTId the primary key for the new back testing
	 * @return the new back testing
	 */
	@Override
	public BackTesting create(long backTId) {
		BackTesting backTesting = new BackTestingImpl();

		backTesting.setNew(true);
		backTesting.setPrimaryKey(backTId);

		String uuid = PortalUUIDUtil.generate();

		backTesting.setUuid(uuid);

		backTesting.setCompanyId(companyProvider.getCompanyId());

		return backTesting;
	}

	/**
	 * Removes the back testing with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param backTId the primary key of the back testing
	 * @return the back testing that was removed
	 * @throws NoSuchBackTestingException if a back testing with the primary key could not be found
	 */
	@Override
	public BackTesting remove(long backTId) throws NoSuchBackTestingException {
		return remove((Serializable)backTId);
	}

	/**
	 * Removes the back testing with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the back testing
	 * @return the back testing that was removed
	 * @throws NoSuchBackTestingException if a back testing with the primary key could not be found
	 */
	@Override
	public BackTesting remove(Serializable primaryKey)
		throws NoSuchBackTestingException {
		Session session = null;

		try {
			session = openSession();

			BackTesting backTesting = (BackTesting)session.get(BackTestingImpl.class,
					primaryKey);

			if (backTesting == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchBackTestingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(backTesting);
		}
		catch (NoSuchBackTestingException nsee) {
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
	protected BackTesting removeImpl(BackTesting backTesting) {
		backTesting = toUnwrappedModel(backTesting);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(backTesting)) {
				backTesting = (BackTesting)session.get(BackTestingImpl.class,
						backTesting.getPrimaryKeyObj());
			}

			if (backTesting != null) {
				session.delete(backTesting);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (backTesting != null) {
			clearCache(backTesting);
		}

		return backTesting;
	}

	@Override
	public BackTesting updateImpl(BackTesting backTesting) {
		backTesting = toUnwrappedModel(backTesting);

		boolean isNew = backTesting.isNew();

		BackTestingModelImpl backTestingModelImpl = (BackTestingModelImpl)backTesting;

		if (Validator.isNull(backTesting.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			backTesting.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (backTesting.getCreateDate() == null)) {
			if (serviceContext == null) {
				backTesting.setCreateDate(now);
			}
			else {
				backTesting.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!backTestingModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				backTesting.setModifiedDate(now);
			}
			else {
				backTesting.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (backTesting.isNew()) {
				session.save(backTesting);

				backTesting.setNew(false);
			}
			else {
				backTesting = (BackTesting)session.merge(backTesting);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !BackTestingModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((backTestingModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						backTestingModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { backTestingModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((backTestingModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						backTestingModelImpl.getOriginalUuid(),
						backTestingModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						backTestingModelImpl.getUuid(),
						backTestingModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((backTestingModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SHARECOMPANYGROUP.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						backTestingModelImpl.getOriginalShareId(),
						backTestingModelImpl.getOriginalCompanyId(),
						backTestingModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SHARECOMPANYGROUP,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SHARECOMPANYGROUP,
					args);

				args = new Object[] {
						backTestingModelImpl.getShareId(),
						backTestingModelImpl.getCompanyId(),
						backTestingModelImpl.getGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SHARECOMPANYGROUP,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SHARECOMPANYGROUP,
					args);
			}

			if ((backTestingModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUSSHARECOMPANYGROUP.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						backTestingModelImpl.getOriginalStatus(),
						backTestingModelImpl.getOriginalCompanyId(),
						backTestingModelImpl.getOriginalGroupId(),
						backTestingModelImpl.getOriginalShareId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_STATUSSHARECOMPANYGROUP,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUSSHARECOMPANYGROUP,
					args);

				args = new Object[] {
						backTestingModelImpl.getStatus(),
						backTestingModelImpl.getCompanyId(),
						backTestingModelImpl.getGroupId(),
						backTestingModelImpl.getShareId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_STATUSSHARECOMPANYGROUP,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUSSHARECOMPANYGROUP,
					args);
			}
		}

		entityCache.putResult(BackTestingModelImpl.ENTITY_CACHE_ENABLED,
			BackTestingImpl.class, backTesting.getPrimaryKey(), backTesting,
			false);

		clearUniqueFindersCache(backTestingModelImpl, false);
		cacheUniqueFindersCache(backTestingModelImpl);

		backTesting.resetOriginalValues();

		return backTesting;
	}

	protected BackTesting toUnwrappedModel(BackTesting backTesting) {
		if (backTesting instanceof BackTestingImpl) {
			return backTesting;
		}

		BackTestingImpl backTestingImpl = new BackTestingImpl();

		backTestingImpl.setNew(backTesting.isNew());
		backTestingImpl.setPrimaryKey(backTesting.getPrimaryKey());

		backTestingImpl.setUuid(backTesting.getUuid());
		backTestingImpl.setBackTId(backTesting.getBackTId());
		backTestingImpl.setGroupId(backTesting.getGroupId());
		backTestingImpl.setCompanyId(backTesting.getCompanyId());
		backTestingImpl.setCreateDate(backTesting.getCreateDate());
		backTestingImpl.setModifiedDate(backTesting.getModifiedDate());
		backTestingImpl.setFromDate(backTesting.getFromDate());
		backTestingImpl.setToDate(backTesting.getToDate());
		backTestingImpl.setLastRunDate(backTesting.getLastRunDate());
		backTestingImpl.setShareId(backTesting.getShareId());
		backTestingImpl.setCountordersBUY(backTesting.getCountordersBUY());
		backTestingImpl.setCountordersSELL(backTesting.getCountordersSELL());
		backTestingImpl.setProfitordersBUY(backTesting.getProfitordersBUY());
		backTestingImpl.setProfitordersSELL(backTesting.getProfitordersSELL());
		backTestingImpl.setStatus(backTesting.getStatus());
		backTestingImpl.setDescription(backTesting.getDescription());
		backTestingImpl.setStartDate(backTesting.getStartDate());
		backTestingImpl.setEndDate(backTesting.getEndDate());

		return backTestingImpl;
	}

	/**
	 * Returns the back testing with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the back testing
	 * @return the back testing
	 * @throws NoSuchBackTestingException if a back testing with the primary key could not be found
	 */
	@Override
	public BackTesting findByPrimaryKey(Serializable primaryKey)
		throws NoSuchBackTestingException {
		BackTesting backTesting = fetchByPrimaryKey(primaryKey);

		if (backTesting == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchBackTestingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return backTesting;
	}

	/**
	 * Returns the back testing with the primary key or throws a {@link NoSuchBackTestingException} if it could not be found.
	 *
	 * @param backTId the primary key of the back testing
	 * @return the back testing
	 * @throws NoSuchBackTestingException if a back testing with the primary key could not be found
	 */
	@Override
	public BackTesting findByPrimaryKey(long backTId)
		throws NoSuchBackTestingException {
		return findByPrimaryKey((Serializable)backTId);
	}

	/**
	 * Returns the back testing with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the back testing
	 * @return the back testing, or <code>null</code> if a back testing with the primary key could not be found
	 */
	@Override
	public BackTesting fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(BackTestingModelImpl.ENTITY_CACHE_ENABLED,
				BackTestingImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		BackTesting backTesting = (BackTesting)serializable;

		if (backTesting == null) {
			Session session = null;

			try {
				session = openSession();

				backTesting = (BackTesting)session.get(BackTestingImpl.class,
						primaryKey);

				if (backTesting != null) {
					cacheResult(backTesting);
				}
				else {
					entityCache.putResult(BackTestingModelImpl.ENTITY_CACHE_ENABLED,
						BackTestingImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(BackTestingModelImpl.ENTITY_CACHE_ENABLED,
					BackTestingImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return backTesting;
	}

	/**
	 * Returns the back testing with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param backTId the primary key of the back testing
	 * @return the back testing, or <code>null</code> if a back testing with the primary key could not be found
	 */
	@Override
	public BackTesting fetchByPrimaryKey(long backTId) {
		return fetchByPrimaryKey((Serializable)backTId);
	}

	@Override
	public Map<Serializable, BackTesting> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, BackTesting> map = new HashMap<Serializable, BackTesting>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			BackTesting backTesting = fetchByPrimaryKey(primaryKey);

			if (backTesting != null) {
				map.put(primaryKey, backTesting);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(BackTestingModelImpl.ENTITY_CACHE_ENABLED,
					BackTestingImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (BackTesting)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_BACKTESTING_WHERE_PKS_IN);

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

			for (BackTesting backTesting : (List<BackTesting>)q.list()) {
				map.put(backTesting.getPrimaryKeyObj(), backTesting);

				cacheResult(backTesting);

				uncachedPrimaryKeys.remove(backTesting.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(BackTestingModelImpl.ENTITY_CACHE_ENABLED,
					BackTestingImpl.class, primaryKey, nullModel);
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
	 * Returns all the back testings.
	 *
	 * @return the back testings
	 */
	@Override
	public List<BackTesting> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the back testings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackTestingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of back testings
	 * @param end the upper bound of the range of back testings (not inclusive)
	 * @return the range of back testings
	 */
	@Override
	public List<BackTesting> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the back testings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackTestingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of back testings
	 * @param end the upper bound of the range of back testings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of back testings
	 */
	@Override
	public List<BackTesting> findAll(int start, int end,
		OrderByComparator<BackTesting> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the back testings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackTestingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of back testings
	 * @param end the upper bound of the range of back testings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of back testings
	 */
	@Override
	public List<BackTesting> findAll(int start, int end,
		OrderByComparator<BackTesting> orderByComparator,
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

		List<BackTesting> list = null;

		if (retrieveFromCache) {
			list = (List<BackTesting>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_BACKTESTING);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_BACKTESTING;

				if (pagination) {
					sql = sql.concat(BackTestingModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<BackTesting>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<BackTesting>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the back testings from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (BackTesting backTesting : findAll()) {
			remove(backTesting);
		}
	}

	/**
	 * Returns the number of back testings.
	 *
	 * @return the number of back testings
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_BACKTESTING);

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
		return BackTestingModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the back testing persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(BackTestingImpl.class.getName());
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
	private static final String _SQL_SELECT_BACKTESTING = "SELECT backTesting FROM BackTesting backTesting";
	private static final String _SQL_SELECT_BACKTESTING_WHERE_PKS_IN = "SELECT backTesting FROM BackTesting backTesting WHERE backTId IN (";
	private static final String _SQL_SELECT_BACKTESTING_WHERE = "SELECT backTesting FROM BackTesting backTesting WHERE ";
	private static final String _SQL_COUNT_BACKTESTING = "SELECT COUNT(backTesting) FROM BackTesting backTesting";
	private static final String _SQL_COUNT_BACKTESTING_WHERE = "SELECT COUNT(backTesting) FROM BackTesting backTesting WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "backTesting.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No BackTesting exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No BackTesting exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(BackTestingPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}