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

import com.ibtrader.data.exception.NoSuchShareException;
import com.ibtrader.data.model.Share;
import com.ibtrader.data.model.impl.ShareImpl;
import com.ibtrader.data.model.impl.ShareModelImpl;
import com.ibtrader.data.service.persistence.SharePersistence;

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
 * The persistence implementation for the share service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SharePersistence
 * @see com.ibtrader.data.service.persistence.ShareUtil
 * @generated
 */
@ProviderType
public class SharePersistenceImpl extends BasePersistenceImpl<Share>
	implements SharePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ShareUtil} to access the share persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ShareImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ShareModelImpl.ENTITY_CACHE_ENABLED,
			ShareModelImpl.FINDER_CACHE_ENABLED, ShareImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ShareModelImpl.ENTITY_CACHE_ENABLED,
			ShareModelImpl.FINDER_CACHE_ENABLED, ShareImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ShareModelImpl.ENTITY_CACHE_ENABLED,
			ShareModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ShareModelImpl.ENTITY_CACHE_ENABLED,
			ShareModelImpl.FINDER_CACHE_ENABLED, ShareImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ShareModelImpl.ENTITY_CACHE_ENABLED,
			ShareModelImpl.FINDER_CACHE_ENABLED, ShareImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			ShareModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ShareModelImpl.ENTITY_CACHE_ENABLED,
			ShareModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the shares where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching shares
	 */
	@Override
	public List<Share> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the shares where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of shares
	 * @param end the upper bound of the range of shares (not inclusive)
	 * @return the range of matching shares
	 */
	@Override
	public List<Share> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the shares where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of shares
	 * @param end the upper bound of the range of shares (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching shares
	 */
	@Override
	public List<Share> findByUuid(String uuid, int start, int end,
		OrderByComparator<Share> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the shares where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of shares
	 * @param end the upper bound of the range of shares (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching shares
	 */
	@Override
	public List<Share> findByUuid(String uuid, int start, int end,
		OrderByComparator<Share> orderByComparator, boolean retrieveFromCache) {
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

		List<Share> list = null;

		if (retrieveFromCache) {
			list = (List<Share>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Share share : list) {
					if (!Objects.equals(uuid, share.getUuid())) {
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

			query.append(_SQL_SELECT_SHARE_WHERE);

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
				query.append(ShareModelImpl.ORDER_BY_JPQL);
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
					list = (List<Share>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Share>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first share in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching share
	 * @throws NoSuchShareException if a matching share could not be found
	 */
	@Override
	public Share findByUuid_First(String uuid,
		OrderByComparator<Share> orderByComparator) throws NoSuchShareException {
		Share share = fetchByUuid_First(uuid, orderByComparator);

		if (share != null) {
			return share;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchShareException(msg.toString());
	}

	/**
	 * Returns the first share in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching share, or <code>null</code> if a matching share could not be found
	 */
	@Override
	public Share fetchByUuid_First(String uuid,
		OrderByComparator<Share> orderByComparator) {
		List<Share> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last share in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching share
	 * @throws NoSuchShareException if a matching share could not be found
	 */
	@Override
	public Share findByUuid_Last(String uuid,
		OrderByComparator<Share> orderByComparator) throws NoSuchShareException {
		Share share = fetchByUuid_Last(uuid, orderByComparator);

		if (share != null) {
			return share;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchShareException(msg.toString());
	}

	/**
	 * Returns the last share in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching share, or <code>null</code> if a matching share could not be found
	 */
	@Override
	public Share fetchByUuid_Last(String uuid,
		OrderByComparator<Share> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Share> list = findByUuid(uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the shares before and after the current share in the ordered set where uuid = &#63;.
	 *
	 * @param shareId the primary key of the current share
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next share
	 * @throws NoSuchShareException if a share with the primary key could not be found
	 */
	@Override
	public Share[] findByUuid_PrevAndNext(long shareId, String uuid,
		OrderByComparator<Share> orderByComparator) throws NoSuchShareException {
		Share share = findByPrimaryKey(shareId);

		Session session = null;

		try {
			session = openSession();

			Share[] array = new ShareImpl[3];

			array[0] = getByUuid_PrevAndNext(session, share, uuid,
					orderByComparator, true);

			array[1] = share;

			array[2] = getByUuid_PrevAndNext(session, share, uuid,
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

	protected Share getByUuid_PrevAndNext(Session session, Share share,
		String uuid, OrderByComparator<Share> orderByComparator,
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

		query.append(_SQL_SELECT_SHARE_WHERE);

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
			query.append(ShareModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(share);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Share> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the shares where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (Share share : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(share);
		}
	}

	/**
	 * Returns the number of shares where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching shares
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SHARE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "share.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "share.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(share.uuid IS NULL OR share.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(ShareModelImpl.ENTITY_CACHE_ENABLED,
			ShareModelImpl.FINDER_CACHE_ENABLED, ShareImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			ShareModelImpl.UUID_COLUMN_BITMASK |
			ShareModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(ShareModelImpl.ENTITY_CACHE_ENABLED,
			ShareModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the share where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchShareException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching share
	 * @throws NoSuchShareException if a matching share could not be found
	 */
	@Override
	public Share findByUUID_G(String uuid, long groupId)
		throws NoSuchShareException {
		Share share = fetchByUUID_G(uuid, groupId);

		if (share == null) {
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

			throw new NoSuchShareException(msg.toString());
		}

		return share;
	}

	/**
	 * Returns the share where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching share, or <code>null</code> if a matching share could not be found
	 */
	@Override
	public Share fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the share where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching share, or <code>null</code> if a matching share could not be found
	 */
	@Override
	public Share fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof Share) {
			Share share = (Share)result;

			if (!Objects.equals(uuid, share.getUuid()) ||
					(groupId != share.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_SHARE_WHERE);

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

				List<Share> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					Share share = list.get(0);

					result = share;

					cacheResult(share);

					if ((share.getUuid() == null) ||
							!share.getUuid().equals(uuid) ||
							(share.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, share);
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
			return (Share)result;
		}
	}

	/**
	 * Removes the share where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the share that was removed
	 */
	@Override
	public Share removeByUUID_G(String uuid, long groupId)
		throws NoSuchShareException {
		Share share = findByUUID_G(uuid, groupId);

		return remove(share);
	}

	/**
	 * Returns the number of shares where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching shares
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SHARE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "share.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "share.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(share.uuid IS NULL OR share.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "share.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(ShareModelImpl.ENTITY_CACHE_ENABLED,
			ShareModelImpl.FINDER_CACHE_ENABLED, ShareImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(ShareModelImpl.ENTITY_CACHE_ENABLED,
			ShareModelImpl.FINDER_CACHE_ENABLED, ShareImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			ShareModelImpl.UUID_COLUMN_BITMASK |
			ShareModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(ShareModelImpl.ENTITY_CACHE_ENABLED,
			ShareModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the shares where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching shares
	 */
	@Override
	public List<Share> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the shares where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of shares
	 * @param end the upper bound of the range of shares (not inclusive)
	 * @return the range of matching shares
	 */
	@Override
	public List<Share> findByUuid_C(String uuid, long companyId, int start,
		int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the shares where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of shares
	 * @param end the upper bound of the range of shares (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching shares
	 */
	@Override
	public List<Share> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<Share> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the shares where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of shares
	 * @param end the upper bound of the range of shares (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching shares
	 */
	@Override
	public List<Share> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<Share> orderByComparator,
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

		List<Share> list = null;

		if (retrieveFromCache) {
			list = (List<Share>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Share share : list) {
					if (!Objects.equals(uuid, share.getUuid()) ||
							(companyId != share.getCompanyId())) {
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

			query.append(_SQL_SELECT_SHARE_WHERE);

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
				query.append(ShareModelImpl.ORDER_BY_JPQL);
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
					list = (List<Share>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Share>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first share in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching share
	 * @throws NoSuchShareException if a matching share could not be found
	 */
	@Override
	public Share findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Share> orderByComparator) throws NoSuchShareException {
		Share share = fetchByUuid_C_First(uuid, companyId, orderByComparator);

		if (share != null) {
			return share;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchShareException(msg.toString());
	}

	/**
	 * Returns the first share in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching share, or <code>null</code> if a matching share could not be found
	 */
	@Override
	public Share fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Share> orderByComparator) {
		List<Share> list = findByUuid_C(uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last share in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching share
	 * @throws NoSuchShareException if a matching share could not be found
	 */
	@Override
	public Share findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Share> orderByComparator) throws NoSuchShareException {
		Share share = fetchByUuid_C_Last(uuid, companyId, orderByComparator);

		if (share != null) {
			return share;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchShareException(msg.toString());
	}

	/**
	 * Returns the last share in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching share, or <code>null</code> if a matching share could not be found
	 */
	@Override
	public Share fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Share> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<Share> list = findByUuid_C(uuid, companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the shares before and after the current share in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param shareId the primary key of the current share
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next share
	 * @throws NoSuchShareException if a share with the primary key could not be found
	 */
	@Override
	public Share[] findByUuid_C_PrevAndNext(long shareId, String uuid,
		long companyId, OrderByComparator<Share> orderByComparator)
		throws NoSuchShareException {
		Share share = findByPrimaryKey(shareId);

		Session session = null;

		try {
			session = openSession();

			Share[] array = new ShareImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, share, uuid, companyId,
					orderByComparator, true);

			array[1] = share;

			array[2] = getByUuid_C_PrevAndNext(session, share, uuid, companyId,
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

	protected Share getByUuid_C_PrevAndNext(Session session, Share share,
		String uuid, long companyId,
		OrderByComparator<Share> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_SHARE_WHERE);

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
			query.append(ShareModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(share);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Share> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the shares where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (Share share : findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(share);
		}
	}

	/**
	 * Returns the number of shares where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching shares
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SHARE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "share.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "share.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(share.uuid IS NULL OR share.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "share.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYGROUPSHARE =
		new FinderPath(ShareModelImpl.ENTITY_CACHE_ENABLED,
			ShareModelImpl.FINDER_CACHE_ENABLED, ShareImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyGroupShare",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYGROUPSHARE =
		new FinderPath(ShareModelImpl.ENTITY_CACHE_ENABLED,
			ShareModelImpl.FINDER_CACHE_ENABLED, ShareImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCompanyGroupShare",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			ShareModelImpl.COMPANYID_COLUMN_BITMASK |
			ShareModelImpl.GROUPID_COLUMN_BITMASK |
			ShareModelImpl.SHAREID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYGROUPSHARE = new FinderPath(ShareModelImpl.ENTITY_CACHE_ENABLED,
			ShareModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCompanyGroupShare",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});

	/**
	 * Returns all the shares where companyId = &#63; and groupId = &#63; and shareId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param shareId the share ID
	 * @return the matching shares
	 */
	@Override
	public List<Share> findByCompanyGroupShare(long companyId, long groupId,
		long shareId) {
		return findByCompanyGroupShare(companyId, groupId, shareId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the shares where companyId = &#63; and groupId = &#63; and shareId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param shareId the share ID
	 * @param start the lower bound of the range of shares
	 * @param end the upper bound of the range of shares (not inclusive)
	 * @return the range of matching shares
	 */
	@Override
	public List<Share> findByCompanyGroupShare(long companyId, long groupId,
		long shareId, int start, int end) {
		return findByCompanyGroupShare(companyId, groupId, shareId, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the shares where companyId = &#63; and groupId = &#63; and shareId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param shareId the share ID
	 * @param start the lower bound of the range of shares
	 * @param end the upper bound of the range of shares (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching shares
	 */
	@Override
	public List<Share> findByCompanyGroupShare(long companyId, long groupId,
		long shareId, int start, int end,
		OrderByComparator<Share> orderByComparator) {
		return findByCompanyGroupShare(companyId, groupId, shareId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the shares where companyId = &#63; and groupId = &#63; and shareId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param shareId the share ID
	 * @param start the lower bound of the range of shares
	 * @param end the upper bound of the range of shares (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching shares
	 */
	@Override
	public List<Share> findByCompanyGroupShare(long companyId, long groupId,
		long shareId, int start, int end,
		OrderByComparator<Share> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYGROUPSHARE;
			finderArgs = new Object[] { companyId, groupId, shareId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYGROUPSHARE;
			finderArgs = new Object[] {
					companyId, groupId, shareId,
					
					start, end, orderByComparator
				};
		}

		List<Share> list = null;

		if (retrieveFromCache) {
			list = (List<Share>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Share share : list) {
					if ((companyId != share.getCompanyId()) ||
							(groupId != share.getGroupId()) ||
							(shareId != share.getShareId())) {
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

			query.append(_SQL_SELECT_SHARE_WHERE);

			query.append(_FINDER_COLUMN_COMPANYGROUPSHARE_COMPANYID_2);

			query.append(_FINDER_COLUMN_COMPANYGROUPSHARE_GROUPID_2);

			query.append(_FINDER_COLUMN_COMPANYGROUPSHARE_SHAREID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ShareModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(groupId);

				qPos.add(shareId);

				if (!pagination) {
					list = (List<Share>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Share>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first share in the ordered set where companyId = &#63; and groupId = &#63; and shareId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param shareId the share ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching share
	 * @throws NoSuchShareException if a matching share could not be found
	 */
	@Override
	public Share findByCompanyGroupShare_First(long companyId, long groupId,
		long shareId, OrderByComparator<Share> orderByComparator)
		throws NoSuchShareException {
		Share share = fetchByCompanyGroupShare_First(companyId, groupId,
				shareId, orderByComparator);

		if (share != null) {
			return share;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(", shareId=");
		msg.append(shareId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchShareException(msg.toString());
	}

	/**
	 * Returns the first share in the ordered set where companyId = &#63; and groupId = &#63; and shareId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param shareId the share ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching share, or <code>null</code> if a matching share could not be found
	 */
	@Override
	public Share fetchByCompanyGroupShare_First(long companyId, long groupId,
		long shareId, OrderByComparator<Share> orderByComparator) {
		List<Share> list = findByCompanyGroupShare(companyId, groupId, shareId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last share in the ordered set where companyId = &#63; and groupId = &#63; and shareId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param shareId the share ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching share
	 * @throws NoSuchShareException if a matching share could not be found
	 */
	@Override
	public Share findByCompanyGroupShare_Last(long companyId, long groupId,
		long shareId, OrderByComparator<Share> orderByComparator)
		throws NoSuchShareException {
		Share share = fetchByCompanyGroupShare_Last(companyId, groupId,
				shareId, orderByComparator);

		if (share != null) {
			return share;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(", shareId=");
		msg.append(shareId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchShareException(msg.toString());
	}

	/**
	 * Returns the last share in the ordered set where companyId = &#63; and groupId = &#63; and shareId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param shareId the share ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching share, or <code>null</code> if a matching share could not be found
	 */
	@Override
	public Share fetchByCompanyGroupShare_Last(long companyId, long groupId,
		long shareId, OrderByComparator<Share> orderByComparator) {
		int count = countByCompanyGroupShare(companyId, groupId, shareId);

		if (count == 0) {
			return null;
		}

		List<Share> list = findByCompanyGroupShare(companyId, groupId, shareId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Removes all the shares where companyId = &#63; and groupId = &#63; and shareId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param shareId the share ID
	 */
	@Override
	public void removeByCompanyGroupShare(long companyId, long groupId,
		long shareId) {
		for (Share share : findByCompanyGroupShare(companyId, groupId, shareId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(share);
		}
	}

	/**
	 * Returns the number of shares where companyId = &#63; and groupId = &#63; and shareId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param shareId the share ID
	 * @return the number of matching shares
	 */
	@Override
	public int countByCompanyGroupShare(long companyId, long groupId,
		long shareId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYGROUPSHARE;

		Object[] finderArgs = new Object[] { companyId, groupId, shareId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_SHARE_WHERE);

			query.append(_FINDER_COLUMN_COMPANYGROUPSHARE_COMPANYID_2);

			query.append(_FINDER_COLUMN_COMPANYGROUPSHARE_GROUPID_2);

			query.append(_FINDER_COLUMN_COMPANYGROUPSHARE_SHAREID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

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

	private static final String _FINDER_COLUMN_COMPANYGROUPSHARE_COMPANYID_2 = "share.companyId = ? AND ";
	private static final String _FINDER_COLUMN_COMPANYGROUPSHARE_GROUPID_2 = "share.groupId = ? AND ";
	private static final String _FINDER_COLUMN_COMPANYGROUPSHARE_SHAREID_2 = "share.shareId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYGROUP =
		new FinderPath(ShareModelImpl.ENTITY_CACHE_ENABLED,
			ShareModelImpl.FINDER_CACHE_ENABLED, ShareImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyGroup",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYGROUP =
		new FinderPath(ShareModelImpl.ENTITY_CACHE_ENABLED,
			ShareModelImpl.FINDER_CACHE_ENABLED, ShareImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyGroup",
			new String[] { Long.class.getName(), Long.class.getName() },
			ShareModelImpl.COMPANYID_COLUMN_BITMASK |
			ShareModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYGROUP = new FinderPath(ShareModelImpl.ENTITY_CACHE_ENABLED,
			ShareModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyGroup",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the shares where companyId = &#63; and groupId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @return the matching shares
	 */
	@Override
	public List<Share> findByCompanyGroup(long companyId, long groupId) {
		return findByCompanyGroup(companyId, groupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the shares where companyId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of shares
	 * @param end the upper bound of the range of shares (not inclusive)
	 * @return the range of matching shares
	 */
	@Override
	public List<Share> findByCompanyGroup(long companyId, long groupId,
		int start, int end) {
		return findByCompanyGroup(companyId, groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the shares where companyId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of shares
	 * @param end the upper bound of the range of shares (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching shares
	 */
	@Override
	public List<Share> findByCompanyGroup(long companyId, long groupId,
		int start, int end, OrderByComparator<Share> orderByComparator) {
		return findByCompanyGroup(companyId, groupId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the shares where companyId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of shares
	 * @param end the upper bound of the range of shares (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching shares
	 */
	@Override
	public List<Share> findByCompanyGroup(long companyId, long groupId,
		int start, int end, OrderByComparator<Share> orderByComparator,
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

		List<Share> list = null;

		if (retrieveFromCache) {
			list = (List<Share>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Share share : list) {
					if ((companyId != share.getCompanyId()) ||
							(groupId != share.getGroupId())) {
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

			query.append(_SQL_SELECT_SHARE_WHERE);

			query.append(_FINDER_COLUMN_COMPANYGROUP_COMPANYID_2);

			query.append(_FINDER_COLUMN_COMPANYGROUP_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ShareModelImpl.ORDER_BY_JPQL);
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
					list = (List<Share>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Share>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first share in the ordered set where companyId = &#63; and groupId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching share
	 * @throws NoSuchShareException if a matching share could not be found
	 */
	@Override
	public Share findByCompanyGroup_First(long companyId, long groupId,
		OrderByComparator<Share> orderByComparator) throws NoSuchShareException {
		Share share = fetchByCompanyGroup_First(companyId, groupId,
				orderByComparator);

		if (share != null) {
			return share;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchShareException(msg.toString());
	}

	/**
	 * Returns the first share in the ordered set where companyId = &#63; and groupId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching share, or <code>null</code> if a matching share could not be found
	 */
	@Override
	public Share fetchByCompanyGroup_First(long companyId, long groupId,
		OrderByComparator<Share> orderByComparator) {
		List<Share> list = findByCompanyGroup(companyId, groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last share in the ordered set where companyId = &#63; and groupId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching share
	 * @throws NoSuchShareException if a matching share could not be found
	 */
	@Override
	public Share findByCompanyGroup_Last(long companyId, long groupId,
		OrderByComparator<Share> orderByComparator) throws NoSuchShareException {
		Share share = fetchByCompanyGroup_Last(companyId, groupId,
				orderByComparator);

		if (share != null) {
			return share;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchShareException(msg.toString());
	}

	/**
	 * Returns the last share in the ordered set where companyId = &#63; and groupId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching share, or <code>null</code> if a matching share could not be found
	 */
	@Override
	public Share fetchByCompanyGroup_Last(long companyId, long groupId,
		OrderByComparator<Share> orderByComparator) {
		int count = countByCompanyGroup(companyId, groupId);

		if (count == 0) {
			return null;
		}

		List<Share> list = findByCompanyGroup(companyId, groupId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the shares before and after the current share in the ordered set where companyId = &#63; and groupId = &#63;.
	 *
	 * @param shareId the primary key of the current share
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next share
	 * @throws NoSuchShareException if a share with the primary key could not be found
	 */
	@Override
	public Share[] findByCompanyGroup_PrevAndNext(long shareId, long companyId,
		long groupId, OrderByComparator<Share> orderByComparator)
		throws NoSuchShareException {
		Share share = findByPrimaryKey(shareId);

		Session session = null;

		try {
			session = openSession();

			Share[] array = new ShareImpl[3];

			array[0] = getByCompanyGroup_PrevAndNext(session, share, companyId,
					groupId, orderByComparator, true);

			array[1] = share;

			array[2] = getByCompanyGroup_PrevAndNext(session, share, companyId,
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

	protected Share getByCompanyGroup_PrevAndNext(Session session, Share share,
		long companyId, long groupId,
		OrderByComparator<Share> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_SHARE_WHERE);

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
			query.append(ShareModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(share);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Share> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the shares where companyId = &#63; and groupId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 */
	@Override
	public void removeByCompanyGroup(long companyId, long groupId) {
		for (Share share : findByCompanyGroup(companyId, groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(share);
		}
	}

	/**
	 * Returns the number of shares where companyId = &#63; and groupId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @return the number of matching shares
	 */
	@Override
	public int countByCompanyGroup(long companyId, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYGROUP;

		Object[] finderArgs = new Object[] { companyId, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SHARE_WHERE);

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

	private static final String _FINDER_COLUMN_COMPANYGROUP_COMPANYID_2 = "share.companyId = ? AND ";
	private static final String _FINDER_COLUMN_COMPANYGROUP_GROUPID_2 = "share.groupId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_NAMEMARKETCOMPANYGROUP = new FinderPath(ShareModelImpl.ENTITY_CACHE_ENABLED,
			ShareModelImpl.FINDER_CACHE_ENABLED, ShareImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByNameMarketCompanyGroup",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), Long.class.getName()
			},
			ShareModelImpl.COMPANYID_COLUMN_BITMASK |
			ShareModelImpl.GROUPID_COLUMN_BITMASK |
			ShareModelImpl.NAME_COLUMN_BITMASK |
			ShareModelImpl.MARKETID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_NAMEMARKETCOMPANYGROUP = new FinderPath(ShareModelImpl.ENTITY_CACHE_ENABLED,
			ShareModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByNameMarketCompanyGroup",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), Long.class.getName()
			});

	/**
	 * Returns the share where companyId = &#63; and groupId = &#63; and name = &#63; and marketId = &#63; or throws a {@link NoSuchShareException} if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param name the name
	 * @param marketId the market ID
	 * @return the matching share
	 * @throws NoSuchShareException if a matching share could not be found
	 */
	@Override
	public Share findByNameMarketCompanyGroup(long companyId, long groupId,
		String name, long marketId) throws NoSuchShareException {
		Share share = fetchByNameMarketCompanyGroup(companyId, groupId, name,
				marketId);

		if (share == null) {
			StringBundler msg = new StringBundler(10);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append(", name=");
			msg.append(name);

			msg.append(", marketId=");
			msg.append(marketId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchShareException(msg.toString());
		}

		return share;
	}

	/**
	 * Returns the share where companyId = &#63; and groupId = &#63; and name = &#63; and marketId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param name the name
	 * @param marketId the market ID
	 * @return the matching share, or <code>null</code> if a matching share could not be found
	 */
	@Override
	public Share fetchByNameMarketCompanyGroup(long companyId, long groupId,
		String name, long marketId) {
		return fetchByNameMarketCompanyGroup(companyId, groupId, name,
			marketId, true);
	}

	/**
	 * Returns the share where companyId = &#63; and groupId = &#63; and name = &#63; and marketId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param name the name
	 * @param marketId the market ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching share, or <code>null</code> if a matching share could not be found
	 */
	@Override
	public Share fetchByNameMarketCompanyGroup(long companyId, long groupId,
		String name, long marketId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { companyId, groupId, name, marketId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_NAMEMARKETCOMPANYGROUP,
					finderArgs, this);
		}

		if (result instanceof Share) {
			Share share = (Share)result;

			if ((companyId != share.getCompanyId()) ||
					(groupId != share.getGroupId()) ||
					!Objects.equals(name, share.getName()) ||
					(marketId != share.getMarketId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_SELECT_SHARE_WHERE);

			query.append(_FINDER_COLUMN_NAMEMARKETCOMPANYGROUP_COMPANYID_2);

			query.append(_FINDER_COLUMN_NAMEMARKETCOMPANYGROUP_GROUPID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_NAMEMARKETCOMPANYGROUP_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_NAMEMARKETCOMPANYGROUP_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_NAMEMARKETCOMPANYGROUP_NAME_2);
			}

			query.append(_FINDER_COLUMN_NAMEMARKETCOMPANYGROUP_MARKETID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(groupId);

				if (bindName) {
					qPos.add(name);
				}

				qPos.add(marketId);

				List<Share> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_NAMEMARKETCOMPANYGROUP,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"SharePersistenceImpl.fetchByNameMarketCompanyGroup(long, long, String, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					Share share = list.get(0);

					result = share;

					cacheResult(share);

					if ((share.getCompanyId() != companyId) ||
							(share.getGroupId() != groupId) ||
							(share.getName() == null) ||
							!share.getName().equals(name) ||
							(share.getMarketId() != marketId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_NAMEMARKETCOMPANYGROUP,
							finderArgs, share);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_NAMEMARKETCOMPANYGROUP,
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
			return (Share)result;
		}
	}

	/**
	 * Removes the share where companyId = &#63; and groupId = &#63; and name = &#63; and marketId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param name the name
	 * @param marketId the market ID
	 * @return the share that was removed
	 */
	@Override
	public Share removeByNameMarketCompanyGroup(long companyId, long groupId,
		String name, long marketId) throws NoSuchShareException {
		Share share = findByNameMarketCompanyGroup(companyId, groupId, name,
				marketId);

		return remove(share);
	}

	/**
	 * Returns the number of shares where companyId = &#63; and groupId = &#63; and name = &#63; and marketId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param name the name
	 * @param marketId the market ID
	 * @return the number of matching shares
	 */
	@Override
	public int countByNameMarketCompanyGroup(long companyId, long groupId,
		String name, long marketId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_NAMEMARKETCOMPANYGROUP;

		Object[] finderArgs = new Object[] { companyId, groupId, name, marketId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_SHARE_WHERE);

			query.append(_FINDER_COLUMN_NAMEMARKETCOMPANYGROUP_COMPANYID_2);

			query.append(_FINDER_COLUMN_NAMEMARKETCOMPANYGROUP_GROUPID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_NAMEMARKETCOMPANYGROUP_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_NAMEMARKETCOMPANYGROUP_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_NAMEMARKETCOMPANYGROUP_NAME_2);
			}

			query.append(_FINDER_COLUMN_NAMEMARKETCOMPANYGROUP_MARKETID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(groupId);

				if (bindName) {
					qPos.add(name);
				}

				qPos.add(marketId);

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

	private static final String _FINDER_COLUMN_NAMEMARKETCOMPANYGROUP_COMPANYID_2 =
		"share.companyId = ? AND ";
	private static final String _FINDER_COLUMN_NAMEMARKETCOMPANYGROUP_GROUPID_2 = "share.groupId = ? AND ";
	private static final String _FINDER_COLUMN_NAMEMARKETCOMPANYGROUP_NAME_1 = "share.name IS NULL AND ";
	private static final String _FINDER_COLUMN_NAMEMARKETCOMPANYGROUP_NAME_2 = "share.name = ? AND ";
	private static final String _FINDER_COLUMN_NAMEMARKETCOMPANYGROUP_NAME_3 = "(share.name IS NULL OR share.name = '') AND ";
	private static final String _FINDER_COLUMN_NAMEMARKETCOMPANYGROUP_MARKETID_2 =
		"share.marketId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_SYMBOLCOMPANYGROUP = new FinderPath(ShareModelImpl.ENTITY_CACHE_ENABLED,
			ShareModelImpl.FINDER_CACHE_ENABLED, ShareImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchBySymbolCompanyGroup",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			ShareModelImpl.COMPANYID_COLUMN_BITMASK |
			ShareModelImpl.GROUPID_COLUMN_BITMASK |
			ShareModelImpl.SYMBOL_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SYMBOLCOMPANYGROUP = new FinderPath(ShareModelImpl.ENTITY_CACHE_ENABLED,
			ShareModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBySymbolCompanyGroup",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns the share where companyId = &#63; and groupId = &#63; and symbol = &#63; or throws a {@link NoSuchShareException} if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param symbol the symbol
	 * @return the matching share
	 * @throws NoSuchShareException if a matching share could not be found
	 */
	@Override
	public Share findBySymbolCompanyGroup(long companyId, long groupId,
		String symbol) throws NoSuchShareException {
		Share share = fetchBySymbolCompanyGroup(companyId, groupId, symbol);

		if (share == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append(", symbol=");
			msg.append(symbol);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchShareException(msg.toString());
		}

		return share;
	}

	/**
	 * Returns the share where companyId = &#63; and groupId = &#63; and symbol = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param symbol the symbol
	 * @return the matching share, or <code>null</code> if a matching share could not be found
	 */
	@Override
	public Share fetchBySymbolCompanyGroup(long companyId, long groupId,
		String symbol) {
		return fetchBySymbolCompanyGroup(companyId, groupId, symbol, true);
	}

	/**
	 * Returns the share where companyId = &#63; and groupId = &#63; and symbol = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param symbol the symbol
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching share, or <code>null</code> if a matching share could not be found
	 */
	@Override
	public Share fetchBySymbolCompanyGroup(long companyId, long groupId,
		String symbol, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { companyId, groupId, symbol };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_SYMBOLCOMPANYGROUP,
					finderArgs, this);
		}

		if (result instanceof Share) {
			Share share = (Share)result;

			if ((companyId != share.getCompanyId()) ||
					(groupId != share.getGroupId()) ||
					!Objects.equals(symbol, share.getSymbol())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_SHARE_WHERE);

			query.append(_FINDER_COLUMN_SYMBOLCOMPANYGROUP_COMPANYID_2);

			query.append(_FINDER_COLUMN_SYMBOLCOMPANYGROUP_GROUPID_2);

			boolean bindSymbol = false;

			if (symbol == null) {
				query.append(_FINDER_COLUMN_SYMBOLCOMPANYGROUP_SYMBOL_1);
			}
			else if (symbol.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SYMBOLCOMPANYGROUP_SYMBOL_3);
			}
			else {
				bindSymbol = true;

				query.append(_FINDER_COLUMN_SYMBOLCOMPANYGROUP_SYMBOL_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(groupId);

				if (bindSymbol) {
					qPos.add(symbol);
				}

				List<Share> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_SYMBOLCOMPANYGROUP,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"SharePersistenceImpl.fetchBySymbolCompanyGroup(long, long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					Share share = list.get(0);

					result = share;

					cacheResult(share);

					if ((share.getCompanyId() != companyId) ||
							(share.getGroupId() != groupId) ||
							(share.getSymbol() == null) ||
							!share.getSymbol().equals(symbol)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_SYMBOLCOMPANYGROUP,
							finderArgs, share);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_SYMBOLCOMPANYGROUP,
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
			return (Share)result;
		}
	}

	/**
	 * Removes the share where companyId = &#63; and groupId = &#63; and symbol = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param symbol the symbol
	 * @return the share that was removed
	 */
	@Override
	public Share removeBySymbolCompanyGroup(long companyId, long groupId,
		String symbol) throws NoSuchShareException {
		Share share = findBySymbolCompanyGroup(companyId, groupId, symbol);

		return remove(share);
	}

	/**
	 * Returns the number of shares where companyId = &#63; and groupId = &#63; and symbol = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param symbol the symbol
	 * @return the number of matching shares
	 */
	@Override
	public int countBySymbolCompanyGroup(long companyId, long groupId,
		String symbol) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SYMBOLCOMPANYGROUP;

		Object[] finderArgs = new Object[] { companyId, groupId, symbol };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_SHARE_WHERE);

			query.append(_FINDER_COLUMN_SYMBOLCOMPANYGROUP_COMPANYID_2);

			query.append(_FINDER_COLUMN_SYMBOLCOMPANYGROUP_GROUPID_2);

			boolean bindSymbol = false;

			if (symbol == null) {
				query.append(_FINDER_COLUMN_SYMBOLCOMPANYGROUP_SYMBOL_1);
			}
			else if (symbol.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SYMBOLCOMPANYGROUP_SYMBOL_3);
			}
			else {
				bindSymbol = true;

				query.append(_FINDER_COLUMN_SYMBOLCOMPANYGROUP_SYMBOL_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(groupId);

				if (bindSymbol) {
					qPos.add(symbol);
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

	private static final String _FINDER_COLUMN_SYMBOLCOMPANYGROUP_COMPANYID_2 = "share.companyId = ? AND ";
	private static final String _FINDER_COLUMN_SYMBOLCOMPANYGROUP_GROUPID_2 = "share.groupId = ? AND ";
	private static final String _FINDER_COLUMN_SYMBOLCOMPANYGROUP_SYMBOL_1 = "share.symbol IS NULL";
	private static final String _FINDER_COLUMN_SYMBOLCOMPANYGROUP_SYMBOL_2 = "share.symbol = ?";
	private static final String _FINDER_COLUMN_SYMBOLCOMPANYGROUP_SYMBOL_3 = "(share.symbol IS NULL OR share.symbol = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVEMARKETGROUPCOMPANY =
		new FinderPath(ShareModelImpl.ENTITY_CACHE_ENABLED,
			ShareModelImpl.FINDER_CACHE_ENABLED, ShareImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByActiveMarketGroupCompany",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEMARKETGROUPCOMPANY =
		new FinderPath(ShareModelImpl.ENTITY_CACHE_ENABLED,
			ShareModelImpl.FINDER_CACHE_ENABLED, ShareImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByActiveMarketGroupCompany",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName(), Long.class.getName()
			},
			ShareModelImpl.GROUPID_COLUMN_BITMASK |
			ShareModelImpl.COMPANYID_COLUMN_BITMASK |
			ShareModelImpl.ACTIVE_COLUMN_BITMASK |
			ShareModelImpl.MARKETID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ACTIVEMARKETGROUPCOMPANY =
		new FinderPath(ShareModelImpl.ENTITY_CACHE_ENABLED,
			ShareModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByActiveMarketGroupCompany",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName(), Long.class.getName()
			});

	/**
	 * Returns all the shares where groupId = &#63; and companyId = &#63; and active = &#63; and marketId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param active the active
	 * @param marketId the market ID
	 * @return the matching shares
	 */
	@Override
	public List<Share> findByActiveMarketGroupCompany(long groupId,
		long companyId, boolean active, long marketId) {
		return findByActiveMarketGroupCompany(groupId, companyId, active,
			marketId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the shares where groupId = &#63; and companyId = &#63; and active = &#63; and marketId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param active the active
	 * @param marketId the market ID
	 * @param start the lower bound of the range of shares
	 * @param end the upper bound of the range of shares (not inclusive)
	 * @return the range of matching shares
	 */
	@Override
	public List<Share> findByActiveMarketGroupCompany(long groupId,
		long companyId, boolean active, long marketId, int start, int end) {
		return findByActiveMarketGroupCompany(groupId, companyId, active,
			marketId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the shares where groupId = &#63; and companyId = &#63; and active = &#63; and marketId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param active the active
	 * @param marketId the market ID
	 * @param start the lower bound of the range of shares
	 * @param end the upper bound of the range of shares (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching shares
	 */
	@Override
	public List<Share> findByActiveMarketGroupCompany(long groupId,
		long companyId, boolean active, long marketId, int start, int end,
		OrderByComparator<Share> orderByComparator) {
		return findByActiveMarketGroupCompany(groupId, companyId, active,
			marketId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the shares where groupId = &#63; and companyId = &#63; and active = &#63; and marketId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param active the active
	 * @param marketId the market ID
	 * @param start the lower bound of the range of shares
	 * @param end the upper bound of the range of shares (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching shares
	 */
	@Override
	public List<Share> findByActiveMarketGroupCompany(long groupId,
		long companyId, boolean active, long marketId, int start, int end,
		OrderByComparator<Share> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEMARKETGROUPCOMPANY;
			finderArgs = new Object[] { groupId, companyId, active, marketId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVEMARKETGROUPCOMPANY;
			finderArgs = new Object[] {
					groupId, companyId, active, marketId,
					
					start, end, orderByComparator
				};
		}

		List<Share> list = null;

		if (retrieveFromCache) {
			list = (List<Share>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Share share : list) {
					if ((groupId != share.getGroupId()) ||
							(companyId != share.getCompanyId()) ||
							(active != share.getActive()) ||
							(marketId != share.getMarketId())) {
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

			query.append(_SQL_SELECT_SHARE_WHERE);

			query.append(_FINDER_COLUMN_ACTIVEMARKETGROUPCOMPANY_GROUPID_2);

			query.append(_FINDER_COLUMN_ACTIVEMARKETGROUPCOMPANY_COMPANYID_2);

			query.append(_FINDER_COLUMN_ACTIVEMARKETGROUPCOMPANY_ACTIVE_2);

			query.append(_FINDER_COLUMN_ACTIVEMARKETGROUPCOMPANY_MARKETID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ShareModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(companyId);

				qPos.add(active);

				qPos.add(marketId);

				if (!pagination) {
					list = (List<Share>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Share>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first share in the ordered set where groupId = &#63; and companyId = &#63; and active = &#63; and marketId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param active the active
	 * @param marketId the market ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching share
	 * @throws NoSuchShareException if a matching share could not be found
	 */
	@Override
	public Share findByActiveMarketGroupCompany_First(long groupId,
		long companyId, boolean active, long marketId,
		OrderByComparator<Share> orderByComparator) throws NoSuchShareException {
		Share share = fetchByActiveMarketGroupCompany_First(groupId, companyId,
				active, marketId, orderByComparator);

		if (share != null) {
			return share;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(", active=");
		msg.append(active);

		msg.append(", marketId=");
		msg.append(marketId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchShareException(msg.toString());
	}

	/**
	 * Returns the first share in the ordered set where groupId = &#63; and companyId = &#63; and active = &#63; and marketId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param active the active
	 * @param marketId the market ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching share, or <code>null</code> if a matching share could not be found
	 */
	@Override
	public Share fetchByActiveMarketGroupCompany_First(long groupId,
		long companyId, boolean active, long marketId,
		OrderByComparator<Share> orderByComparator) {
		List<Share> list = findByActiveMarketGroupCompany(groupId, companyId,
				active, marketId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last share in the ordered set where groupId = &#63; and companyId = &#63; and active = &#63; and marketId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param active the active
	 * @param marketId the market ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching share
	 * @throws NoSuchShareException if a matching share could not be found
	 */
	@Override
	public Share findByActiveMarketGroupCompany_Last(long groupId,
		long companyId, boolean active, long marketId,
		OrderByComparator<Share> orderByComparator) throws NoSuchShareException {
		Share share = fetchByActiveMarketGroupCompany_Last(groupId, companyId,
				active, marketId, orderByComparator);

		if (share != null) {
			return share;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(", active=");
		msg.append(active);

		msg.append(", marketId=");
		msg.append(marketId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchShareException(msg.toString());
	}

	/**
	 * Returns the last share in the ordered set where groupId = &#63; and companyId = &#63; and active = &#63; and marketId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param active the active
	 * @param marketId the market ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching share, or <code>null</code> if a matching share could not be found
	 */
	@Override
	public Share fetchByActiveMarketGroupCompany_Last(long groupId,
		long companyId, boolean active, long marketId,
		OrderByComparator<Share> orderByComparator) {
		int count = countByActiveMarketGroupCompany(groupId, companyId, active,
				marketId);

		if (count == 0) {
			return null;
		}

		List<Share> list = findByActiveMarketGroupCompany(groupId, companyId,
				active, marketId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the shares before and after the current share in the ordered set where groupId = &#63; and companyId = &#63; and active = &#63; and marketId = &#63;.
	 *
	 * @param shareId the primary key of the current share
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param active the active
	 * @param marketId the market ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next share
	 * @throws NoSuchShareException if a share with the primary key could not be found
	 */
	@Override
	public Share[] findByActiveMarketGroupCompany_PrevAndNext(long shareId,
		long groupId, long companyId, boolean active, long marketId,
		OrderByComparator<Share> orderByComparator) throws NoSuchShareException {
		Share share = findByPrimaryKey(shareId);

		Session session = null;

		try {
			session = openSession();

			Share[] array = new ShareImpl[3];

			array[0] = getByActiveMarketGroupCompany_PrevAndNext(session,
					share, groupId, companyId, active, marketId,
					orderByComparator, true);

			array[1] = share;

			array[2] = getByActiveMarketGroupCompany_PrevAndNext(session,
					share, groupId, companyId, active, marketId,
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

	protected Share getByActiveMarketGroupCompany_PrevAndNext(Session session,
		Share share, long groupId, long companyId, boolean active,
		long marketId, OrderByComparator<Share> orderByComparator,
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

		query.append(_SQL_SELECT_SHARE_WHERE);

		query.append(_FINDER_COLUMN_ACTIVEMARKETGROUPCOMPANY_GROUPID_2);

		query.append(_FINDER_COLUMN_ACTIVEMARKETGROUPCOMPANY_COMPANYID_2);

		query.append(_FINDER_COLUMN_ACTIVEMARKETGROUPCOMPANY_ACTIVE_2);

		query.append(_FINDER_COLUMN_ACTIVEMARKETGROUPCOMPANY_MARKETID_2);

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
			query.append(ShareModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(companyId);

		qPos.add(active);

		qPos.add(marketId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(share);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Share> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the shares where groupId = &#63; and companyId = &#63; and active = &#63; and marketId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param active the active
	 * @param marketId the market ID
	 */
	@Override
	public void removeByActiveMarketGroupCompany(long groupId, long companyId,
		boolean active, long marketId) {
		for (Share share : findByActiveMarketGroupCompany(groupId, companyId,
				active, marketId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(share);
		}
	}

	/**
	 * Returns the number of shares where groupId = &#63; and companyId = &#63; and active = &#63; and marketId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param active the active
	 * @param marketId the market ID
	 * @return the number of matching shares
	 */
	@Override
	public int countByActiveMarketGroupCompany(long groupId, long companyId,
		boolean active, long marketId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ACTIVEMARKETGROUPCOMPANY;

		Object[] finderArgs = new Object[] { groupId, companyId, active, marketId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_SHARE_WHERE);

			query.append(_FINDER_COLUMN_ACTIVEMARKETGROUPCOMPANY_GROUPID_2);

			query.append(_FINDER_COLUMN_ACTIVEMARKETGROUPCOMPANY_COMPANYID_2);

			query.append(_FINDER_COLUMN_ACTIVEMARKETGROUPCOMPANY_ACTIVE_2);

			query.append(_FINDER_COLUMN_ACTIVEMARKETGROUPCOMPANY_MARKETID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(companyId);

				qPos.add(active);

				qPos.add(marketId);

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

	private static final String _FINDER_COLUMN_ACTIVEMARKETGROUPCOMPANY_GROUPID_2 =
		"share.groupId = ? AND ";
	private static final String _FINDER_COLUMN_ACTIVEMARKETGROUPCOMPANY_COMPANYID_2 =
		"share.companyId = ? AND ";
	private static final String _FINDER_COLUMN_ACTIVEMARKETGROUPCOMPANY_ACTIVE_2 =
		"share.active = ? AND ";
	private static final String _FINDER_COLUMN_ACTIVEMARKETGROUPCOMPANY_MARKETID_2 =
		"share.marketId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_MARKETGROUPCOMPANY =
		new FinderPath(ShareModelImpl.ENTITY_CACHE_ENABLED,
			ShareModelImpl.FINDER_CACHE_ENABLED, ShareImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByMarketGroupCompany",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MARKETGROUPCOMPANY =
		new FinderPath(ShareModelImpl.ENTITY_CACHE_ENABLED,
			ShareModelImpl.FINDER_CACHE_ENABLED, ShareImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByMarketGroupCompany",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			ShareModelImpl.GROUPID_COLUMN_BITMASK |
			ShareModelImpl.COMPANYID_COLUMN_BITMASK |
			ShareModelImpl.MARKETID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_MARKETGROUPCOMPANY = new FinderPath(ShareModelImpl.ENTITY_CACHE_ENABLED,
			ShareModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByMarketGroupCompany",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});

	/**
	 * Returns all the shares where groupId = &#63; and companyId = &#63; and marketId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param marketId the market ID
	 * @return the matching shares
	 */
	@Override
	public List<Share> findByMarketGroupCompany(long groupId, long companyId,
		long marketId) {
		return findByMarketGroupCompany(groupId, companyId, marketId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the shares where groupId = &#63; and companyId = &#63; and marketId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param marketId the market ID
	 * @param start the lower bound of the range of shares
	 * @param end the upper bound of the range of shares (not inclusive)
	 * @return the range of matching shares
	 */
	@Override
	public List<Share> findByMarketGroupCompany(long groupId, long companyId,
		long marketId, int start, int end) {
		return findByMarketGroupCompany(groupId, companyId, marketId, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the shares where groupId = &#63; and companyId = &#63; and marketId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param marketId the market ID
	 * @param start the lower bound of the range of shares
	 * @param end the upper bound of the range of shares (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching shares
	 */
	@Override
	public List<Share> findByMarketGroupCompany(long groupId, long companyId,
		long marketId, int start, int end,
		OrderByComparator<Share> orderByComparator) {
		return findByMarketGroupCompany(groupId, companyId, marketId, start,
			end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the shares where groupId = &#63; and companyId = &#63; and marketId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param marketId the market ID
	 * @param start the lower bound of the range of shares
	 * @param end the upper bound of the range of shares (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching shares
	 */
	@Override
	public List<Share> findByMarketGroupCompany(long groupId, long companyId,
		long marketId, int start, int end,
		OrderByComparator<Share> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MARKETGROUPCOMPANY;
			finderArgs = new Object[] { groupId, companyId, marketId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_MARKETGROUPCOMPANY;
			finderArgs = new Object[] {
					groupId, companyId, marketId,
					
					start, end, orderByComparator
				};
		}

		List<Share> list = null;

		if (retrieveFromCache) {
			list = (List<Share>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Share share : list) {
					if ((groupId != share.getGroupId()) ||
							(companyId != share.getCompanyId()) ||
							(marketId != share.getMarketId())) {
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

			query.append(_SQL_SELECT_SHARE_WHERE);

			query.append(_FINDER_COLUMN_MARKETGROUPCOMPANY_GROUPID_2);

			query.append(_FINDER_COLUMN_MARKETGROUPCOMPANY_COMPANYID_2);

			query.append(_FINDER_COLUMN_MARKETGROUPCOMPANY_MARKETID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ShareModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(companyId);

				qPos.add(marketId);

				if (!pagination) {
					list = (List<Share>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Share>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first share in the ordered set where groupId = &#63; and companyId = &#63; and marketId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param marketId the market ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching share
	 * @throws NoSuchShareException if a matching share could not be found
	 */
	@Override
	public Share findByMarketGroupCompany_First(long groupId, long companyId,
		long marketId, OrderByComparator<Share> orderByComparator)
		throws NoSuchShareException {
		Share share = fetchByMarketGroupCompany_First(groupId, companyId,
				marketId, orderByComparator);

		if (share != null) {
			return share;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(", marketId=");
		msg.append(marketId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchShareException(msg.toString());
	}

	/**
	 * Returns the first share in the ordered set where groupId = &#63; and companyId = &#63; and marketId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param marketId the market ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching share, or <code>null</code> if a matching share could not be found
	 */
	@Override
	public Share fetchByMarketGroupCompany_First(long groupId, long companyId,
		long marketId, OrderByComparator<Share> orderByComparator) {
		List<Share> list = findByMarketGroupCompany(groupId, companyId,
				marketId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last share in the ordered set where groupId = &#63; and companyId = &#63; and marketId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param marketId the market ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching share
	 * @throws NoSuchShareException if a matching share could not be found
	 */
	@Override
	public Share findByMarketGroupCompany_Last(long groupId, long companyId,
		long marketId, OrderByComparator<Share> orderByComparator)
		throws NoSuchShareException {
		Share share = fetchByMarketGroupCompany_Last(groupId, companyId,
				marketId, orderByComparator);

		if (share != null) {
			return share;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(", marketId=");
		msg.append(marketId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchShareException(msg.toString());
	}

	/**
	 * Returns the last share in the ordered set where groupId = &#63; and companyId = &#63; and marketId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param marketId the market ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching share, or <code>null</code> if a matching share could not be found
	 */
	@Override
	public Share fetchByMarketGroupCompany_Last(long groupId, long companyId,
		long marketId, OrderByComparator<Share> orderByComparator) {
		int count = countByMarketGroupCompany(groupId, companyId, marketId);

		if (count == 0) {
			return null;
		}

		List<Share> list = findByMarketGroupCompany(groupId, companyId,
				marketId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the shares before and after the current share in the ordered set where groupId = &#63; and companyId = &#63; and marketId = &#63;.
	 *
	 * @param shareId the primary key of the current share
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param marketId the market ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next share
	 * @throws NoSuchShareException if a share with the primary key could not be found
	 */
	@Override
	public Share[] findByMarketGroupCompany_PrevAndNext(long shareId,
		long groupId, long companyId, long marketId,
		OrderByComparator<Share> orderByComparator) throws NoSuchShareException {
		Share share = findByPrimaryKey(shareId);

		Session session = null;

		try {
			session = openSession();

			Share[] array = new ShareImpl[3];

			array[0] = getByMarketGroupCompany_PrevAndNext(session, share,
					groupId, companyId, marketId, orderByComparator, true);

			array[1] = share;

			array[2] = getByMarketGroupCompany_PrevAndNext(session, share,
					groupId, companyId, marketId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Share getByMarketGroupCompany_PrevAndNext(Session session,
		Share share, long groupId, long companyId, long marketId,
		OrderByComparator<Share> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_SHARE_WHERE);

		query.append(_FINDER_COLUMN_MARKETGROUPCOMPANY_GROUPID_2);

		query.append(_FINDER_COLUMN_MARKETGROUPCOMPANY_COMPANYID_2);

		query.append(_FINDER_COLUMN_MARKETGROUPCOMPANY_MARKETID_2);

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
			query.append(ShareModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(companyId);

		qPos.add(marketId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(share);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Share> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the shares where groupId = &#63; and companyId = &#63; and marketId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param marketId the market ID
	 */
	@Override
	public void removeByMarketGroupCompany(long groupId, long companyId,
		long marketId) {
		for (Share share : findByMarketGroupCompany(groupId, companyId,
				marketId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(share);
		}
	}

	/**
	 * Returns the number of shares where groupId = &#63; and companyId = &#63; and marketId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param marketId the market ID
	 * @return the number of matching shares
	 */
	@Override
	public int countByMarketGroupCompany(long groupId, long companyId,
		long marketId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_MARKETGROUPCOMPANY;

		Object[] finderArgs = new Object[] { groupId, companyId, marketId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_SHARE_WHERE);

			query.append(_FINDER_COLUMN_MARKETGROUPCOMPANY_GROUPID_2);

			query.append(_FINDER_COLUMN_MARKETGROUPCOMPANY_COMPANYID_2);

			query.append(_FINDER_COLUMN_MARKETGROUPCOMPANY_MARKETID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(companyId);

				qPos.add(marketId);

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

	private static final String _FINDER_COLUMN_MARKETGROUPCOMPANY_GROUPID_2 = "share.groupId = ? AND ";
	private static final String _FINDER_COLUMN_MARKETGROUPCOMPANY_COMPANYID_2 = "share.companyId = ? AND ";
	private static final String _FINDER_COLUMN_MARKETGROUPCOMPANY_MARKETID_2 = "share.marketId = ?";

	public SharePersistenceImpl() {
		setModelClass(Share.class);
	}

	/**
	 * Caches the share in the entity cache if it is enabled.
	 *
	 * @param share the share
	 */
	@Override
	public void cacheResult(Share share) {
		entityCache.putResult(ShareModelImpl.ENTITY_CACHE_ENABLED,
			ShareImpl.class, share.getPrimaryKey(), share);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { share.getUuid(), share.getGroupId() }, share);

		finderCache.putResult(FINDER_PATH_FETCH_BY_NAMEMARKETCOMPANYGROUP,
			new Object[] {
				share.getCompanyId(), share.getGroupId(), share.getName(),
				share.getMarketId()
			}, share);

		finderCache.putResult(FINDER_PATH_FETCH_BY_SYMBOLCOMPANYGROUP,
			new Object[] {
				share.getCompanyId(), share.getGroupId(), share.getSymbol()
			}, share);

		share.resetOriginalValues();
	}

	/**
	 * Caches the shares in the entity cache if it is enabled.
	 *
	 * @param shares the shares
	 */
	@Override
	public void cacheResult(List<Share> shares) {
		for (Share share : shares) {
			if (entityCache.getResult(ShareModelImpl.ENTITY_CACHE_ENABLED,
						ShareImpl.class, share.getPrimaryKey()) == null) {
				cacheResult(share);
			}
			else {
				share.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all shares.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ShareImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the share.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Share share) {
		entityCache.removeResult(ShareModelImpl.ENTITY_CACHE_ENABLED,
			ShareImpl.class, share.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ShareModelImpl)share, true);
	}

	@Override
	public void clearCache(List<Share> shares) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Share share : shares) {
			entityCache.removeResult(ShareModelImpl.ENTITY_CACHE_ENABLED,
				ShareImpl.class, share.getPrimaryKey());

			clearUniqueFindersCache((ShareModelImpl)share, true);
		}
	}

	protected void cacheUniqueFindersCache(ShareModelImpl shareModelImpl) {
		Object[] args = new Object[] {
				shareModelImpl.getUuid(), shareModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			shareModelImpl, false);

		args = new Object[] {
				shareModelImpl.getCompanyId(), shareModelImpl.getGroupId(),
				shareModelImpl.getName(), shareModelImpl.getMarketId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_NAMEMARKETCOMPANYGROUP,
			args, Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_NAMEMARKETCOMPANYGROUP,
			args, shareModelImpl, false);

		args = new Object[] {
				shareModelImpl.getCompanyId(), shareModelImpl.getGroupId(),
				shareModelImpl.getSymbol()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_SYMBOLCOMPANYGROUP, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_SYMBOLCOMPANYGROUP, args,
			shareModelImpl, false);
	}

	protected void clearUniqueFindersCache(ShareModelImpl shareModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					shareModelImpl.getUuid(), shareModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((shareModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					shareModelImpl.getOriginalUuid(),
					shareModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					shareModelImpl.getCompanyId(), shareModelImpl.getGroupId(),
					shareModelImpl.getName(), shareModelImpl.getMarketId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_NAMEMARKETCOMPANYGROUP,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_NAMEMARKETCOMPANYGROUP,
				args);
		}

		if ((shareModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_NAMEMARKETCOMPANYGROUP.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					shareModelImpl.getOriginalCompanyId(),
					shareModelImpl.getOriginalGroupId(),
					shareModelImpl.getOriginalName(),
					shareModelImpl.getOriginalMarketId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_NAMEMARKETCOMPANYGROUP,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_NAMEMARKETCOMPANYGROUP,
				args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					shareModelImpl.getCompanyId(), shareModelImpl.getGroupId(),
					shareModelImpl.getSymbol()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SYMBOLCOMPANYGROUP,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_SYMBOLCOMPANYGROUP,
				args);
		}

		if ((shareModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_SYMBOLCOMPANYGROUP.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					shareModelImpl.getOriginalCompanyId(),
					shareModelImpl.getOriginalGroupId(),
					shareModelImpl.getOriginalSymbol()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SYMBOLCOMPANYGROUP,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_SYMBOLCOMPANYGROUP,
				args);
		}
	}

	/**
	 * Creates a new share with the primary key. Does not add the share to the database.
	 *
	 * @param shareId the primary key for the new share
	 * @return the new share
	 */
	@Override
	public Share create(long shareId) {
		Share share = new ShareImpl();

		share.setNew(true);
		share.setPrimaryKey(shareId);

		String uuid = PortalUUIDUtil.generate();

		share.setUuid(uuid);

		share.setCompanyId(companyProvider.getCompanyId());

		return share;
	}

	/**
	 * Removes the share with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param shareId the primary key of the share
	 * @return the share that was removed
	 * @throws NoSuchShareException if a share with the primary key could not be found
	 */
	@Override
	public Share remove(long shareId) throws NoSuchShareException {
		return remove((Serializable)shareId);
	}

	/**
	 * Removes the share with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the share
	 * @return the share that was removed
	 * @throws NoSuchShareException if a share with the primary key could not be found
	 */
	@Override
	public Share remove(Serializable primaryKey) throws NoSuchShareException {
		Session session = null;

		try {
			session = openSession();

			Share share = (Share)session.get(ShareImpl.class, primaryKey);

			if (share == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchShareException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(share);
		}
		catch (NoSuchShareException nsee) {
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
	protected Share removeImpl(Share share) {
		share = toUnwrappedModel(share);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(share)) {
				share = (Share)session.get(ShareImpl.class,
						share.getPrimaryKeyObj());
			}

			if (share != null) {
				session.delete(share);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (share != null) {
			clearCache(share);
		}

		return share;
	}

	@Override
	public Share updateImpl(Share share) {
		share = toUnwrappedModel(share);

		boolean isNew = share.isNew();

		ShareModelImpl shareModelImpl = (ShareModelImpl)share;

		if (Validator.isNull(share.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			share.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (share.getCreateDate() == null)) {
			if (serviceContext == null) {
				share.setCreateDate(now);
			}
			else {
				share.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!shareModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				share.setModifiedDate(now);
			}
			else {
				share.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (share.isNew()) {
				session.save(share);

				share.setNew(false);
			}
			else {
				share = (Share)session.merge(share);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !ShareModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((shareModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { shareModelImpl.getOriginalUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { shareModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((shareModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						shareModelImpl.getOriginalUuid(),
						shareModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						shareModelImpl.getUuid(), shareModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((shareModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYGROUPSHARE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						shareModelImpl.getOriginalCompanyId(),
						shareModelImpl.getOriginalGroupId(),
						shareModelImpl.getOriginalShareId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYGROUPSHARE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYGROUPSHARE,
					args);

				args = new Object[] {
						shareModelImpl.getCompanyId(),
						shareModelImpl.getGroupId(), shareModelImpl.getShareId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYGROUPSHARE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYGROUPSHARE,
					args);
			}

			if ((shareModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYGROUP.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						shareModelImpl.getOriginalCompanyId(),
						shareModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYGROUP, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYGROUP,
					args);

				args = new Object[] {
						shareModelImpl.getCompanyId(),
						shareModelImpl.getGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYGROUP, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYGROUP,
					args);
			}

			if ((shareModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEMARKETGROUPCOMPANY.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						shareModelImpl.getOriginalGroupId(),
						shareModelImpl.getOriginalCompanyId(),
						shareModelImpl.getOriginalActive(),
						shareModelImpl.getOriginalMarketId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACTIVEMARKETGROUPCOMPANY,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEMARKETGROUPCOMPANY,
					args);

				args = new Object[] {
						shareModelImpl.getGroupId(),
						shareModelImpl.getCompanyId(),
						shareModelImpl.getActive(), shareModelImpl.getMarketId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACTIVEMARKETGROUPCOMPANY,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEMARKETGROUPCOMPANY,
					args);
			}

			if ((shareModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MARKETGROUPCOMPANY.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						shareModelImpl.getOriginalGroupId(),
						shareModelImpl.getOriginalCompanyId(),
						shareModelImpl.getOriginalMarketId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_MARKETGROUPCOMPANY,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MARKETGROUPCOMPANY,
					args);

				args = new Object[] {
						shareModelImpl.getGroupId(),
						shareModelImpl.getCompanyId(),
						shareModelImpl.getMarketId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_MARKETGROUPCOMPANY,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MARKETGROUPCOMPANY,
					args);
			}
		}

		entityCache.putResult(ShareModelImpl.ENTITY_CACHE_ENABLED,
			ShareImpl.class, share.getPrimaryKey(), share, false);

		clearUniqueFindersCache(shareModelImpl, false);
		cacheUniqueFindersCache(shareModelImpl);

		share.resetOriginalValues();

		return share;
	}

	protected Share toUnwrappedModel(Share share) {
		if (share instanceof ShareImpl) {
			return share;
		}

		ShareImpl shareImpl = new ShareImpl();

		shareImpl.setNew(share.isNew());
		shareImpl.setPrimaryKey(share.getPrimaryKey());

		shareImpl.setUuid(share.getUuid());
		shareImpl.setShareId(share.getShareId());
		shareImpl.setName(share.getName());
		shareImpl.setSymbol(share.getSymbol());
		shareImpl.setGroupId(share.getGroupId());
		shareImpl.setCompanyId(share.getCompanyId());
		shareImpl.setCreateDate(share.getCreateDate());
		shareImpl.setModifiedDate(share.getModifiedDate());
		shareImpl.setActive(share.isActive());
		shareImpl.setNumbertopurchase(share.getNumbertopurchase());
		shareImpl.setPercentual_limit_buy(share.getPercentual_limit_buy());
		shareImpl.setPercentual_stop_lost(share.getPercentual_stop_lost());
		shareImpl.setPercentual_stop_profit(share.getPercentual_stop_profit());
		shareImpl.setPercentual_stop_profit_position(share.getPercentual_stop_profit_position());
		shareImpl.setPercentual_trailling_stop_lost(share.getPercentual_trailling_stop_lost());
		shareImpl.setExpiry_date(share.getExpiry_date());
		shareImpl.setExpiry_expression(share.getExpiry_expression());
		shareImpl.setTick_futures(share.getTick_futures());
		shareImpl.setMultiplier(share.getMultiplier());
		shareImpl.setSecurity_type(share.getSecurity_type());
		shareImpl.setExchange(share.getExchange());
		shareImpl.setPrimary_exchange(share.getPrimary_exchange());
		shareImpl.setUserCreatedId(share.getUserCreatedId());
		shareImpl.setMarketId(share.getMarketId());
		shareImpl.setValidated_trader_provider(share.isValidated_trader_provider());
		shareImpl.setDate_validated_trader_provider(share.getDate_validated_trader_provider());
		shareImpl.setLast_error_trader_provider(share.getLast_error_trader_provider());
		shareImpl.setSimulation_end_date(share.getSimulation_end_date());
		shareImpl.setTrading_hours(share.getTrading_hours());
		shareImpl.setDate_filled_realtime_gaps(share.getDate_filled_realtime_gaps());

		return shareImpl;
	}

	/**
	 * Returns the share with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the share
	 * @return the share
	 * @throws NoSuchShareException if a share with the primary key could not be found
	 */
	@Override
	public Share findByPrimaryKey(Serializable primaryKey)
		throws NoSuchShareException {
		Share share = fetchByPrimaryKey(primaryKey);

		if (share == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchShareException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return share;
	}

	/**
	 * Returns the share with the primary key or throws a {@link NoSuchShareException} if it could not be found.
	 *
	 * @param shareId the primary key of the share
	 * @return the share
	 * @throws NoSuchShareException if a share with the primary key could not be found
	 */
	@Override
	public Share findByPrimaryKey(long shareId) throws NoSuchShareException {
		return findByPrimaryKey((Serializable)shareId);
	}

	/**
	 * Returns the share with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the share
	 * @return the share, or <code>null</code> if a share with the primary key could not be found
	 */
	@Override
	public Share fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ShareModelImpl.ENTITY_CACHE_ENABLED,
				ShareImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Share share = (Share)serializable;

		if (share == null) {
			Session session = null;

			try {
				session = openSession();

				share = (Share)session.get(ShareImpl.class, primaryKey);

				if (share != null) {
					cacheResult(share);
				}
				else {
					entityCache.putResult(ShareModelImpl.ENTITY_CACHE_ENABLED,
						ShareImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ShareModelImpl.ENTITY_CACHE_ENABLED,
					ShareImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return share;
	}

	/**
	 * Returns the share with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param shareId the primary key of the share
	 * @return the share, or <code>null</code> if a share with the primary key could not be found
	 */
	@Override
	public Share fetchByPrimaryKey(long shareId) {
		return fetchByPrimaryKey((Serializable)shareId);
	}

	@Override
	public Map<Serializable, Share> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Share> map = new HashMap<Serializable, Share>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Share share = fetchByPrimaryKey(primaryKey);

			if (share != null) {
				map.put(primaryKey, share);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ShareModelImpl.ENTITY_CACHE_ENABLED,
					ShareImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Share)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_SHARE_WHERE_PKS_IN);

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

			for (Share share : (List<Share>)q.list()) {
				map.put(share.getPrimaryKeyObj(), share);

				cacheResult(share);

				uncachedPrimaryKeys.remove(share.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ShareModelImpl.ENTITY_CACHE_ENABLED,
					ShareImpl.class, primaryKey, nullModel);
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
	 * Returns all the shares.
	 *
	 * @return the shares
	 */
	@Override
	public List<Share> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the shares.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of shares
	 * @param end the upper bound of the range of shares (not inclusive)
	 * @return the range of shares
	 */
	@Override
	public List<Share> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the shares.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of shares
	 * @param end the upper bound of the range of shares (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of shares
	 */
	@Override
	public List<Share> findAll(int start, int end,
		OrderByComparator<Share> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the shares.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of shares
	 * @param end the upper bound of the range of shares (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of shares
	 */
	@Override
	public List<Share> findAll(int start, int end,
		OrderByComparator<Share> orderByComparator, boolean retrieveFromCache) {
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

		List<Share> list = null;

		if (retrieveFromCache) {
			list = (List<Share>)finderCache.getResult(finderPath, finderArgs,
					this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_SHARE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SHARE;

				if (pagination) {
					sql = sql.concat(ShareModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Share>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Share>)QueryUtil.list(q, getDialect(), start,
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
	 * Removes all the shares from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Share share : findAll()) {
			remove(share);
		}
	}

	/**
	 * Returns the number of shares.
	 *
	 * @return the number of shares
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SHARE);

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
		return ShareModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the share persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ShareImpl.class.getName());
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
	private static final String _SQL_SELECT_SHARE = "SELECT share FROM Share share";
	private static final String _SQL_SELECT_SHARE_WHERE_PKS_IN = "SELECT share FROM Share share WHERE shareId IN (";
	private static final String _SQL_SELECT_SHARE_WHERE = "SELECT share FROM Share share WHERE ";
	private static final String _SQL_COUNT_SHARE = "SELECT COUNT(share) FROM Share share";
	private static final String _SQL_COUNT_SHARE_WHERE = "SELECT COUNT(share) FROM Share share WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "share.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Share exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Share exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(SharePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid", "active"
			});
}