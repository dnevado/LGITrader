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

import com.ibtrader.data.exception.NoSuchMarkeShareException;
import com.ibtrader.data.model.MarkeShare;
import com.ibtrader.data.model.impl.MarkeShareImpl;
import com.ibtrader.data.model.impl.MarkeShareModelImpl;
import com.ibtrader.data.service.persistence.MarkeSharePersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the marke share service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MarkeSharePersistence
 * @see com.ibtrader.data.service.persistence.MarkeShareUtil
 * @generated
 */
@ProviderType
public class MarkeSharePersistenceImpl extends BasePersistenceImpl<MarkeShare>
	implements MarkeSharePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link MarkeShareUtil} to access the marke share persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = MarkeShareImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MarkeShareModelImpl.ENTITY_CACHE_ENABLED,
			MarkeShareModelImpl.FINDER_CACHE_ENABLED, MarkeShareImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MarkeShareModelImpl.ENTITY_CACHE_ENABLED,
			MarkeShareModelImpl.FINDER_CACHE_ENABLED, MarkeShareImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MarkeShareModelImpl.ENTITY_CACHE_ENABLED,
			MarkeShareModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(MarkeShareModelImpl.ENTITY_CACHE_ENABLED,
			MarkeShareModelImpl.FINDER_CACHE_ENABLED, MarkeShareImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(MarkeShareModelImpl.ENTITY_CACHE_ENABLED,
			MarkeShareModelImpl.FINDER_CACHE_ENABLED, MarkeShareImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			MarkeShareModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(MarkeShareModelImpl.ENTITY_CACHE_ENABLED,
			MarkeShareModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the marke shares where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching marke shares
	 */
	@Override
	public List<MarkeShare> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the marke shares where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarkeShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of marke shares
	 * @param end the upper bound of the range of marke shares (not inclusive)
	 * @return the range of matching marke shares
	 */
	@Override
	public List<MarkeShare> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the marke shares where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarkeShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of marke shares
	 * @param end the upper bound of the range of marke shares (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching marke shares
	 */
	@Override
	public List<MarkeShare> findByUuid(String uuid, int start, int end,
		OrderByComparator<MarkeShare> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the marke shares where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarkeShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of marke shares
	 * @param end the upper bound of the range of marke shares (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching marke shares
	 */
	@Override
	public List<MarkeShare> findByUuid(String uuid, int start, int end,
		OrderByComparator<MarkeShare> orderByComparator,
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

		List<MarkeShare> list = null;

		if (retrieveFromCache) {
			list = (List<MarkeShare>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (MarkeShare markeShare : list) {
					if (!Objects.equals(uuid, markeShare.getUuid())) {
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

			query.append(_SQL_SELECT_MARKESHARE_WHERE);

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
				query.append(MarkeShareModelImpl.ORDER_BY_JPQL);
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
					list = (List<MarkeShare>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MarkeShare>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first marke share in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching marke share
	 * @throws NoSuchMarkeShareException if a matching marke share could not be found
	 */
	@Override
	public MarkeShare findByUuid_First(String uuid,
		OrderByComparator<MarkeShare> orderByComparator)
		throws NoSuchMarkeShareException {
		MarkeShare markeShare = fetchByUuid_First(uuid, orderByComparator);

		if (markeShare != null) {
			return markeShare;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMarkeShareException(msg.toString());
	}

	/**
	 * Returns the first marke share in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching marke share, or <code>null</code> if a matching marke share could not be found
	 */
	@Override
	public MarkeShare fetchByUuid_First(String uuid,
		OrderByComparator<MarkeShare> orderByComparator) {
		List<MarkeShare> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last marke share in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching marke share
	 * @throws NoSuchMarkeShareException if a matching marke share could not be found
	 */
	@Override
	public MarkeShare findByUuid_Last(String uuid,
		OrderByComparator<MarkeShare> orderByComparator)
		throws NoSuchMarkeShareException {
		MarkeShare markeShare = fetchByUuid_Last(uuid, orderByComparator);

		if (markeShare != null) {
			return markeShare;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMarkeShareException(msg.toString());
	}

	/**
	 * Returns the last marke share in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching marke share, or <code>null</code> if a matching marke share could not be found
	 */
	@Override
	public MarkeShare fetchByUuid_Last(String uuid,
		OrderByComparator<MarkeShare> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<MarkeShare> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the marke shares before and after the current marke share in the ordered set where uuid = &#63;.
	 *
	 * @param marketshareId the primary key of the current marke share
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next marke share
	 * @throws NoSuchMarkeShareException if a marke share with the primary key could not be found
	 */
	@Override
	public MarkeShare[] findByUuid_PrevAndNext(long marketshareId, String uuid,
		OrderByComparator<MarkeShare> orderByComparator)
		throws NoSuchMarkeShareException {
		MarkeShare markeShare = findByPrimaryKey(marketshareId);

		Session session = null;

		try {
			session = openSession();

			MarkeShare[] array = new MarkeShareImpl[3];

			array[0] = getByUuid_PrevAndNext(session, markeShare, uuid,
					orderByComparator, true);

			array[1] = markeShare;

			array[2] = getByUuid_PrevAndNext(session, markeShare, uuid,
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

	protected MarkeShare getByUuid_PrevAndNext(Session session,
		MarkeShare markeShare, String uuid,
		OrderByComparator<MarkeShare> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MARKESHARE_WHERE);

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
			query.append(MarkeShareModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(markeShare);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MarkeShare> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the marke shares where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (MarkeShare markeShare : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(markeShare);
		}
	}

	/**
	 * Returns the number of marke shares where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching marke shares
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MARKESHARE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "markeShare.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "markeShare.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(markeShare.uuid IS NULL OR markeShare.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(MarkeShareModelImpl.ENTITY_CACHE_ENABLED,
			MarkeShareModelImpl.FINDER_CACHE_ENABLED, MarkeShareImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			MarkeShareModelImpl.UUID_COLUMN_BITMASK |
			MarkeShareModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(MarkeShareModelImpl.ENTITY_CACHE_ENABLED,
			MarkeShareModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the marke share where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchMarkeShareException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching marke share
	 * @throws NoSuchMarkeShareException if a matching marke share could not be found
	 */
	@Override
	public MarkeShare findByUUID_G(String uuid, long groupId)
		throws NoSuchMarkeShareException {
		MarkeShare markeShare = fetchByUUID_G(uuid, groupId);

		if (markeShare == null) {
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

			throw new NoSuchMarkeShareException(msg.toString());
		}

		return markeShare;
	}

	/**
	 * Returns the marke share where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching marke share, or <code>null</code> if a matching marke share could not be found
	 */
	@Override
	public MarkeShare fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the marke share where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching marke share, or <code>null</code> if a matching marke share could not be found
	 */
	@Override
	public MarkeShare fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof MarkeShare) {
			MarkeShare markeShare = (MarkeShare)result;

			if (!Objects.equals(uuid, markeShare.getUuid()) ||
					(groupId != markeShare.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_MARKESHARE_WHERE);

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

				List<MarkeShare> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					MarkeShare markeShare = list.get(0);

					result = markeShare;

					cacheResult(markeShare);

					if ((markeShare.getUuid() == null) ||
							!markeShare.getUuid().equals(uuid) ||
							(markeShare.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, markeShare);
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
			return (MarkeShare)result;
		}
	}

	/**
	 * Removes the marke share where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the marke share that was removed
	 */
	@Override
	public MarkeShare removeByUUID_G(String uuid, long groupId)
		throws NoSuchMarkeShareException {
		MarkeShare markeShare = findByUUID_G(uuid, groupId);

		return remove(markeShare);
	}

	/**
	 * Returns the number of marke shares where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching marke shares
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_MARKESHARE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "markeShare.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "markeShare.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(markeShare.uuid IS NULL OR markeShare.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "markeShare.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(MarkeShareModelImpl.ENTITY_CACHE_ENABLED,
			MarkeShareModelImpl.FINDER_CACHE_ENABLED, MarkeShareImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(MarkeShareModelImpl.ENTITY_CACHE_ENABLED,
			MarkeShareModelImpl.FINDER_CACHE_ENABLED, MarkeShareImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			MarkeShareModelImpl.UUID_COLUMN_BITMASK |
			MarkeShareModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(MarkeShareModelImpl.ENTITY_CACHE_ENABLED,
			MarkeShareModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the marke shares where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching marke shares
	 */
	@Override
	public List<MarkeShare> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the marke shares where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarkeShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of marke shares
	 * @param end the upper bound of the range of marke shares (not inclusive)
	 * @return the range of matching marke shares
	 */
	@Override
	public List<MarkeShare> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the marke shares where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarkeShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of marke shares
	 * @param end the upper bound of the range of marke shares (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching marke shares
	 */
	@Override
	public List<MarkeShare> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<MarkeShare> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the marke shares where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarkeShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of marke shares
	 * @param end the upper bound of the range of marke shares (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching marke shares
	 */
	@Override
	public List<MarkeShare> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<MarkeShare> orderByComparator,
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

		List<MarkeShare> list = null;

		if (retrieveFromCache) {
			list = (List<MarkeShare>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (MarkeShare markeShare : list) {
					if (!Objects.equals(uuid, markeShare.getUuid()) ||
							(companyId != markeShare.getCompanyId())) {
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

			query.append(_SQL_SELECT_MARKESHARE_WHERE);

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
				query.append(MarkeShareModelImpl.ORDER_BY_JPQL);
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
					list = (List<MarkeShare>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MarkeShare>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first marke share in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching marke share
	 * @throws NoSuchMarkeShareException if a matching marke share could not be found
	 */
	@Override
	public MarkeShare findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<MarkeShare> orderByComparator)
		throws NoSuchMarkeShareException {
		MarkeShare markeShare = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (markeShare != null) {
			return markeShare;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMarkeShareException(msg.toString());
	}

	/**
	 * Returns the first marke share in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching marke share, or <code>null</code> if a matching marke share could not be found
	 */
	@Override
	public MarkeShare fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<MarkeShare> orderByComparator) {
		List<MarkeShare> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last marke share in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching marke share
	 * @throws NoSuchMarkeShareException if a matching marke share could not be found
	 */
	@Override
	public MarkeShare findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<MarkeShare> orderByComparator)
		throws NoSuchMarkeShareException {
		MarkeShare markeShare = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (markeShare != null) {
			return markeShare;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMarkeShareException(msg.toString());
	}

	/**
	 * Returns the last marke share in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching marke share, or <code>null</code> if a matching marke share could not be found
	 */
	@Override
	public MarkeShare fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<MarkeShare> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<MarkeShare> list = findByUuid_C(uuid, companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the marke shares before and after the current marke share in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param marketshareId the primary key of the current marke share
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next marke share
	 * @throws NoSuchMarkeShareException if a marke share with the primary key could not be found
	 */
	@Override
	public MarkeShare[] findByUuid_C_PrevAndNext(long marketshareId,
		String uuid, long companyId,
		OrderByComparator<MarkeShare> orderByComparator)
		throws NoSuchMarkeShareException {
		MarkeShare markeShare = findByPrimaryKey(marketshareId);

		Session session = null;

		try {
			session = openSession();

			MarkeShare[] array = new MarkeShareImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, markeShare, uuid,
					companyId, orderByComparator, true);

			array[1] = markeShare;

			array[2] = getByUuid_C_PrevAndNext(session, markeShare, uuid,
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

	protected MarkeShare getByUuid_C_PrevAndNext(Session session,
		MarkeShare markeShare, String uuid, long companyId,
		OrderByComparator<MarkeShare> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_MARKESHARE_WHERE);

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
			query.append(MarkeShareModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(markeShare);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MarkeShare> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the marke shares where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (MarkeShare markeShare : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(markeShare);
		}
	}

	/**
	 * Returns the number of marke shares where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching marke shares
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_MARKESHARE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "markeShare.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "markeShare.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(markeShare.uuid IS NULL OR markeShare.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "markeShare.companyId = ?";

	public MarkeSharePersistenceImpl() {
		setModelClass(MarkeShare.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("uuid", "uuid_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the marke share in the entity cache if it is enabled.
	 *
	 * @param markeShare the marke share
	 */
	@Override
	public void cacheResult(MarkeShare markeShare) {
		entityCache.putResult(MarkeShareModelImpl.ENTITY_CACHE_ENABLED,
			MarkeShareImpl.class, markeShare.getPrimaryKey(), markeShare);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { markeShare.getUuid(), markeShare.getGroupId() },
			markeShare);

		markeShare.resetOriginalValues();
	}

	/**
	 * Caches the marke shares in the entity cache if it is enabled.
	 *
	 * @param markeShares the marke shares
	 */
	@Override
	public void cacheResult(List<MarkeShare> markeShares) {
		for (MarkeShare markeShare : markeShares) {
			if (entityCache.getResult(
						MarkeShareModelImpl.ENTITY_CACHE_ENABLED,
						MarkeShareImpl.class, markeShare.getPrimaryKey()) == null) {
				cacheResult(markeShare);
			}
			else {
				markeShare.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all marke shares.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(MarkeShareImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the marke share.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MarkeShare markeShare) {
		entityCache.removeResult(MarkeShareModelImpl.ENTITY_CACHE_ENABLED,
			MarkeShareImpl.class, markeShare.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((MarkeShareModelImpl)markeShare, true);
	}

	@Override
	public void clearCache(List<MarkeShare> markeShares) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (MarkeShare markeShare : markeShares) {
			entityCache.removeResult(MarkeShareModelImpl.ENTITY_CACHE_ENABLED,
				MarkeShareImpl.class, markeShare.getPrimaryKey());

			clearUniqueFindersCache((MarkeShareModelImpl)markeShare, true);
		}
	}

	protected void cacheUniqueFindersCache(
		MarkeShareModelImpl markeShareModelImpl) {
		Object[] args = new Object[] {
				markeShareModelImpl.getUuid(), markeShareModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			markeShareModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		MarkeShareModelImpl markeShareModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					markeShareModelImpl.getUuid(),
					markeShareModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((markeShareModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					markeShareModelImpl.getOriginalUuid(),
					markeShareModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new marke share with the primary key. Does not add the marke share to the database.
	 *
	 * @param marketshareId the primary key for the new marke share
	 * @return the new marke share
	 */
	@Override
	public MarkeShare create(long marketshareId) {
		MarkeShare markeShare = new MarkeShareImpl();

		markeShare.setNew(true);
		markeShare.setPrimaryKey(marketshareId);

		String uuid = PortalUUIDUtil.generate();

		markeShare.setUuid(uuid);

		markeShare.setCompanyId(companyProvider.getCompanyId());

		return markeShare;
	}

	/**
	 * Removes the marke share with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param marketshareId the primary key of the marke share
	 * @return the marke share that was removed
	 * @throws NoSuchMarkeShareException if a marke share with the primary key could not be found
	 */
	@Override
	public MarkeShare remove(long marketshareId)
		throws NoSuchMarkeShareException {
		return remove((Serializable)marketshareId);
	}

	/**
	 * Removes the marke share with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the marke share
	 * @return the marke share that was removed
	 * @throws NoSuchMarkeShareException if a marke share with the primary key could not be found
	 */
	@Override
	public MarkeShare remove(Serializable primaryKey)
		throws NoSuchMarkeShareException {
		Session session = null;

		try {
			session = openSession();

			MarkeShare markeShare = (MarkeShare)session.get(MarkeShareImpl.class,
					primaryKey);

			if (markeShare == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMarkeShareException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(markeShare);
		}
		catch (NoSuchMarkeShareException nsee) {
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
	protected MarkeShare removeImpl(MarkeShare markeShare) {
		markeShare = toUnwrappedModel(markeShare);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(markeShare)) {
				markeShare = (MarkeShare)session.get(MarkeShareImpl.class,
						markeShare.getPrimaryKeyObj());
			}

			if (markeShare != null) {
				session.delete(markeShare);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (markeShare != null) {
			clearCache(markeShare);
		}

		return markeShare;
	}

	@Override
	public MarkeShare updateImpl(MarkeShare markeShare) {
		markeShare = toUnwrappedModel(markeShare);

		boolean isNew = markeShare.isNew();

		MarkeShareModelImpl markeShareModelImpl = (MarkeShareModelImpl)markeShare;

		if (Validator.isNull(markeShare.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			markeShare.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (markeShare.isNew()) {
				session.save(markeShare);

				markeShare.setNew(false);
			}
			else {
				markeShare = (MarkeShare)session.merge(markeShare);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!MarkeShareModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { markeShareModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					markeShareModelImpl.getUuid(),
					markeShareModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((markeShareModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						markeShareModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { markeShareModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((markeShareModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						markeShareModelImpl.getOriginalUuid(),
						markeShareModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						markeShareModelImpl.getUuid(),
						markeShareModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}
		}

		entityCache.putResult(MarkeShareModelImpl.ENTITY_CACHE_ENABLED,
			MarkeShareImpl.class, markeShare.getPrimaryKey(), markeShare, false);

		clearUniqueFindersCache(markeShareModelImpl, false);
		cacheUniqueFindersCache(markeShareModelImpl);

		markeShare.resetOriginalValues();

		return markeShare;
	}

	protected MarkeShare toUnwrappedModel(MarkeShare markeShare) {
		if (markeShare instanceof MarkeShareImpl) {
			return markeShare;
		}

		MarkeShareImpl markeShareImpl = new MarkeShareImpl();

		markeShareImpl.setNew(markeShare.isNew());
		markeShareImpl.setPrimaryKey(markeShare.getPrimaryKey());

		markeShareImpl.setUuid(markeShare.getUuid());
		markeShareImpl.setMarketshareId(markeShare.getMarketshareId());
		markeShareImpl.setGroupId(markeShare.getGroupId());
		markeShareImpl.setCompanyId(markeShare.getCompanyId());
		markeShareImpl.setShareId(markeShare.getShareId());
		markeShareImpl.setMarketId(markeShare.getMarketId());

		return markeShareImpl;
	}

	/**
	 * Returns the marke share with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the marke share
	 * @return the marke share
	 * @throws NoSuchMarkeShareException if a marke share with the primary key could not be found
	 */
	@Override
	public MarkeShare findByPrimaryKey(Serializable primaryKey)
		throws NoSuchMarkeShareException {
		MarkeShare markeShare = fetchByPrimaryKey(primaryKey);

		if (markeShare == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchMarkeShareException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return markeShare;
	}

	/**
	 * Returns the marke share with the primary key or throws a {@link NoSuchMarkeShareException} if it could not be found.
	 *
	 * @param marketshareId the primary key of the marke share
	 * @return the marke share
	 * @throws NoSuchMarkeShareException if a marke share with the primary key could not be found
	 */
	@Override
	public MarkeShare findByPrimaryKey(long marketshareId)
		throws NoSuchMarkeShareException {
		return findByPrimaryKey((Serializable)marketshareId);
	}

	/**
	 * Returns the marke share with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the marke share
	 * @return the marke share, or <code>null</code> if a marke share with the primary key could not be found
	 */
	@Override
	public MarkeShare fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(MarkeShareModelImpl.ENTITY_CACHE_ENABLED,
				MarkeShareImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		MarkeShare markeShare = (MarkeShare)serializable;

		if (markeShare == null) {
			Session session = null;

			try {
				session = openSession();

				markeShare = (MarkeShare)session.get(MarkeShareImpl.class,
						primaryKey);

				if (markeShare != null) {
					cacheResult(markeShare);
				}
				else {
					entityCache.putResult(MarkeShareModelImpl.ENTITY_CACHE_ENABLED,
						MarkeShareImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(MarkeShareModelImpl.ENTITY_CACHE_ENABLED,
					MarkeShareImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return markeShare;
	}

	/**
	 * Returns the marke share with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param marketshareId the primary key of the marke share
	 * @return the marke share, or <code>null</code> if a marke share with the primary key could not be found
	 */
	@Override
	public MarkeShare fetchByPrimaryKey(long marketshareId) {
		return fetchByPrimaryKey((Serializable)marketshareId);
	}

	@Override
	public Map<Serializable, MarkeShare> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, MarkeShare> map = new HashMap<Serializable, MarkeShare>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			MarkeShare markeShare = fetchByPrimaryKey(primaryKey);

			if (markeShare != null) {
				map.put(primaryKey, markeShare);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(MarkeShareModelImpl.ENTITY_CACHE_ENABLED,
					MarkeShareImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (MarkeShare)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_MARKESHARE_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((long)primaryKey);

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (MarkeShare markeShare : (List<MarkeShare>)q.list()) {
				map.put(markeShare.getPrimaryKeyObj(), markeShare);

				cacheResult(markeShare);

				uncachedPrimaryKeys.remove(markeShare.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(MarkeShareModelImpl.ENTITY_CACHE_ENABLED,
					MarkeShareImpl.class, primaryKey, nullModel);
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
	 * Returns all the marke shares.
	 *
	 * @return the marke shares
	 */
	@Override
	public List<MarkeShare> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the marke shares.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarkeShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of marke shares
	 * @param end the upper bound of the range of marke shares (not inclusive)
	 * @return the range of marke shares
	 */
	@Override
	public List<MarkeShare> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the marke shares.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarkeShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of marke shares
	 * @param end the upper bound of the range of marke shares (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of marke shares
	 */
	@Override
	public List<MarkeShare> findAll(int start, int end,
		OrderByComparator<MarkeShare> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the marke shares.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarkeShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of marke shares
	 * @param end the upper bound of the range of marke shares (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of marke shares
	 */
	@Override
	public List<MarkeShare> findAll(int start, int end,
		OrderByComparator<MarkeShare> orderByComparator,
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

		List<MarkeShare> list = null;

		if (retrieveFromCache) {
			list = (List<MarkeShare>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_MARKESHARE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_MARKESHARE;

				if (pagination) {
					sql = sql.concat(MarkeShareModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<MarkeShare>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MarkeShare>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the marke shares from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (MarkeShare markeShare : findAll()) {
			remove(markeShare);
		}
	}

	/**
	 * Returns the number of marke shares.
	 *
	 * @return the number of marke shares
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_MARKESHARE);

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
		return MarkeShareModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the marke share persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(MarkeShareImpl.class.getName());
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
	private static final String _SQL_SELECT_MARKESHARE = "SELECT markeShare FROM MarkeShare markeShare";
	private static final String _SQL_SELECT_MARKESHARE_WHERE_PKS_IN = "SELECT markeShare FROM MarkeShare markeShare WHERE marketshareId IN (";
	private static final String _SQL_SELECT_MARKESHARE_WHERE = "SELECT markeShare FROM MarkeShare markeShare WHERE ";
	private static final String _SQL_COUNT_MARKESHARE = "SELECT COUNT(markeShare) FROM MarkeShare markeShare";
	private static final String _SQL_COUNT_MARKESHARE_WHERE = "SELECT COUNT(markeShare) FROM MarkeShare markeShare WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "markeShare.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MarkeShare exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No MarkeShare exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(MarkeSharePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}