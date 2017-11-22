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

import com.ibtrader.data.exception.NoSuchStrategyShareException;
import com.ibtrader.data.model.StrategyShare;
import com.ibtrader.data.model.impl.StrategyShareImpl;
import com.ibtrader.data.model.impl.StrategyShareModelImpl;
import com.ibtrader.data.service.persistence.StrategySharePersistence;

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
 * The persistence implementation for the strategy share service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see StrategySharePersistence
 * @see com.ibtrader.data.service.persistence.StrategyShareUtil
 * @generated
 */
@ProviderType
public class StrategySharePersistenceImpl extends BasePersistenceImpl<StrategyShare>
	implements StrategySharePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link StrategyShareUtil} to access the strategy share persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = StrategyShareImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StrategyShareModelImpl.ENTITY_CACHE_ENABLED,
			StrategyShareModelImpl.FINDER_CACHE_ENABLED,
			StrategyShareImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StrategyShareModelImpl.ENTITY_CACHE_ENABLED,
			StrategyShareModelImpl.FINDER_CACHE_ENABLED,
			StrategyShareImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StrategyShareModelImpl.ENTITY_CACHE_ENABLED,
			StrategyShareModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(StrategyShareModelImpl.ENTITY_CACHE_ENABLED,
			StrategyShareModelImpl.FINDER_CACHE_ENABLED,
			StrategyShareImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(StrategyShareModelImpl.ENTITY_CACHE_ENABLED,
			StrategyShareModelImpl.FINDER_CACHE_ENABLED,
			StrategyShareImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid", new String[] { String.class.getName() },
			StrategyShareModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(StrategyShareModelImpl.ENTITY_CACHE_ENABLED,
			StrategyShareModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the strategy shares where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching strategy shares
	 */
	@Override
	public List<StrategyShare> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the strategy shares where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of strategy shares
	 * @param end the upper bound of the range of strategy shares (not inclusive)
	 * @return the range of matching strategy shares
	 */
	@Override
	public List<StrategyShare> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the strategy shares where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of strategy shares
	 * @param end the upper bound of the range of strategy shares (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching strategy shares
	 */
	@Override
	public List<StrategyShare> findByUuid(String uuid, int start, int end,
		OrderByComparator<StrategyShare> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the strategy shares where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of strategy shares
	 * @param end the upper bound of the range of strategy shares (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching strategy shares
	 */
	@Override
	public List<StrategyShare> findByUuid(String uuid, int start, int end,
		OrderByComparator<StrategyShare> orderByComparator,
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

		List<StrategyShare> list = null;

		if (retrieveFromCache) {
			list = (List<StrategyShare>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (StrategyShare strategyShare : list) {
					if (!Objects.equals(uuid, strategyShare.getUuid())) {
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

			query.append(_SQL_SELECT_STRATEGYSHARE_WHERE);

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
				query.append(StrategyShareModelImpl.ORDER_BY_JPQL);
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
					list = (List<StrategyShare>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<StrategyShare>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first strategy share in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching strategy share
	 * @throws NoSuchStrategyShareException if a matching strategy share could not be found
	 */
	@Override
	public StrategyShare findByUuid_First(String uuid,
		OrderByComparator<StrategyShare> orderByComparator)
		throws NoSuchStrategyShareException {
		StrategyShare strategyShare = fetchByUuid_First(uuid, orderByComparator);

		if (strategyShare != null) {
			return strategyShare;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchStrategyShareException(msg.toString());
	}

	/**
	 * Returns the first strategy share in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching strategy share, or <code>null</code> if a matching strategy share could not be found
	 */
	@Override
	public StrategyShare fetchByUuid_First(String uuid,
		OrderByComparator<StrategyShare> orderByComparator) {
		List<StrategyShare> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last strategy share in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching strategy share
	 * @throws NoSuchStrategyShareException if a matching strategy share could not be found
	 */
	@Override
	public StrategyShare findByUuid_Last(String uuid,
		OrderByComparator<StrategyShare> orderByComparator)
		throws NoSuchStrategyShareException {
		StrategyShare strategyShare = fetchByUuid_Last(uuid, orderByComparator);

		if (strategyShare != null) {
			return strategyShare;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchStrategyShareException(msg.toString());
	}

	/**
	 * Returns the last strategy share in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching strategy share, or <code>null</code> if a matching strategy share could not be found
	 */
	@Override
	public StrategyShare fetchByUuid_Last(String uuid,
		OrderByComparator<StrategyShare> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<StrategyShare> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the strategy shares before and after the current strategy share in the ordered set where uuid = &#63;.
	 *
	 * @param strategyshareId the primary key of the current strategy share
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next strategy share
	 * @throws NoSuchStrategyShareException if a strategy share with the primary key could not be found
	 */
	@Override
	public StrategyShare[] findByUuid_PrevAndNext(long strategyshareId,
		String uuid, OrderByComparator<StrategyShare> orderByComparator)
		throws NoSuchStrategyShareException {
		StrategyShare strategyShare = findByPrimaryKey(strategyshareId);

		Session session = null;

		try {
			session = openSession();

			StrategyShare[] array = new StrategyShareImpl[3];

			array[0] = getByUuid_PrevAndNext(session, strategyShare, uuid,
					orderByComparator, true);

			array[1] = strategyShare;

			array[2] = getByUuid_PrevAndNext(session, strategyShare, uuid,
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

	protected StrategyShare getByUuid_PrevAndNext(Session session,
		StrategyShare strategyShare, String uuid,
		OrderByComparator<StrategyShare> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_STRATEGYSHARE_WHERE);

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
			query.append(StrategyShareModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(strategyShare);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<StrategyShare> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the strategy shares where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (StrategyShare strategyShare : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(strategyShare);
		}
	}

	/**
	 * Returns the number of strategy shares where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching strategy shares
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_STRATEGYSHARE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "strategyShare.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "strategyShare.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(strategyShare.uuid IS NULL OR strategyShare.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(StrategyShareModelImpl.ENTITY_CACHE_ENABLED,
			StrategyShareModelImpl.FINDER_CACHE_ENABLED,
			StrategyShareImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			StrategyShareModelImpl.UUID_COLUMN_BITMASK |
			StrategyShareModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(StrategyShareModelImpl.ENTITY_CACHE_ENABLED,
			StrategyShareModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the strategy share where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchStrategyShareException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching strategy share
	 * @throws NoSuchStrategyShareException if a matching strategy share could not be found
	 */
	@Override
	public StrategyShare findByUUID_G(String uuid, long groupId)
		throws NoSuchStrategyShareException {
		StrategyShare strategyShare = fetchByUUID_G(uuid, groupId);

		if (strategyShare == null) {
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

			throw new NoSuchStrategyShareException(msg.toString());
		}

		return strategyShare;
	}

	/**
	 * Returns the strategy share where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching strategy share, or <code>null</code> if a matching strategy share could not be found
	 */
	@Override
	public StrategyShare fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the strategy share where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching strategy share, or <code>null</code> if a matching strategy share could not be found
	 */
	@Override
	public StrategyShare fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof StrategyShare) {
			StrategyShare strategyShare = (StrategyShare)result;

			if (!Objects.equals(uuid, strategyShare.getUuid()) ||
					(groupId != strategyShare.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_STRATEGYSHARE_WHERE);

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

				List<StrategyShare> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					StrategyShare strategyShare = list.get(0);

					result = strategyShare;

					cacheResult(strategyShare);

					if ((strategyShare.getUuid() == null) ||
							!strategyShare.getUuid().equals(uuid) ||
							(strategyShare.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, strategyShare);
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
			return (StrategyShare)result;
		}
	}

	/**
	 * Removes the strategy share where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the strategy share that was removed
	 */
	@Override
	public StrategyShare removeByUUID_G(String uuid, long groupId)
		throws NoSuchStrategyShareException {
		StrategyShare strategyShare = findByUUID_G(uuid, groupId);

		return remove(strategyShare);
	}

	/**
	 * Returns the number of strategy shares where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching strategy shares
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_STRATEGYSHARE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "strategyShare.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "strategyShare.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(strategyShare.uuid IS NULL OR strategyShare.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "strategyShare.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(StrategyShareModelImpl.ENTITY_CACHE_ENABLED,
			StrategyShareModelImpl.FINDER_CACHE_ENABLED,
			StrategyShareImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(StrategyShareModelImpl.ENTITY_CACHE_ENABLED,
			StrategyShareModelImpl.FINDER_CACHE_ENABLED,
			StrategyShareImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			StrategyShareModelImpl.UUID_COLUMN_BITMASK |
			StrategyShareModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(StrategyShareModelImpl.ENTITY_CACHE_ENABLED,
			StrategyShareModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the strategy shares where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching strategy shares
	 */
	@Override
	public List<StrategyShare> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the strategy shares where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of strategy shares
	 * @param end the upper bound of the range of strategy shares (not inclusive)
	 * @return the range of matching strategy shares
	 */
	@Override
	public List<StrategyShare> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the strategy shares where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of strategy shares
	 * @param end the upper bound of the range of strategy shares (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching strategy shares
	 */
	@Override
	public List<StrategyShare> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<StrategyShare> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the strategy shares where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of strategy shares
	 * @param end the upper bound of the range of strategy shares (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching strategy shares
	 */
	@Override
	public List<StrategyShare> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<StrategyShare> orderByComparator,
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

		List<StrategyShare> list = null;

		if (retrieveFromCache) {
			list = (List<StrategyShare>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (StrategyShare strategyShare : list) {
					if (!Objects.equals(uuid, strategyShare.getUuid()) ||
							(companyId != strategyShare.getCompanyId())) {
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

			query.append(_SQL_SELECT_STRATEGYSHARE_WHERE);

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
				query.append(StrategyShareModelImpl.ORDER_BY_JPQL);
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
					list = (List<StrategyShare>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<StrategyShare>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first strategy share in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching strategy share
	 * @throws NoSuchStrategyShareException if a matching strategy share could not be found
	 */
	@Override
	public StrategyShare findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<StrategyShare> orderByComparator)
		throws NoSuchStrategyShareException {
		StrategyShare strategyShare = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (strategyShare != null) {
			return strategyShare;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchStrategyShareException(msg.toString());
	}

	/**
	 * Returns the first strategy share in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching strategy share, or <code>null</code> if a matching strategy share could not be found
	 */
	@Override
	public StrategyShare fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<StrategyShare> orderByComparator) {
		List<StrategyShare> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last strategy share in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching strategy share
	 * @throws NoSuchStrategyShareException if a matching strategy share could not be found
	 */
	@Override
	public StrategyShare findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<StrategyShare> orderByComparator)
		throws NoSuchStrategyShareException {
		StrategyShare strategyShare = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (strategyShare != null) {
			return strategyShare;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchStrategyShareException(msg.toString());
	}

	/**
	 * Returns the last strategy share in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching strategy share, or <code>null</code> if a matching strategy share could not be found
	 */
	@Override
	public StrategyShare fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<StrategyShare> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<StrategyShare> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the strategy shares before and after the current strategy share in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param strategyshareId the primary key of the current strategy share
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next strategy share
	 * @throws NoSuchStrategyShareException if a strategy share with the primary key could not be found
	 */
	@Override
	public StrategyShare[] findByUuid_C_PrevAndNext(long strategyshareId,
		String uuid, long companyId,
		OrderByComparator<StrategyShare> orderByComparator)
		throws NoSuchStrategyShareException {
		StrategyShare strategyShare = findByPrimaryKey(strategyshareId);

		Session session = null;

		try {
			session = openSession();

			StrategyShare[] array = new StrategyShareImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, strategyShare, uuid,
					companyId, orderByComparator, true);

			array[1] = strategyShare;

			array[2] = getByUuid_C_PrevAndNext(session, strategyShare, uuid,
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

	protected StrategyShare getByUuid_C_PrevAndNext(Session session,
		StrategyShare strategyShare, String uuid, long companyId,
		OrderByComparator<StrategyShare> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_STRATEGYSHARE_WHERE);

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
			query.append(StrategyShareModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(strategyShare);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<StrategyShare> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the strategy shares where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (StrategyShare strategyShare : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(strategyShare);
		}
	}

	/**
	 * Returns the number of strategy shares where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching strategy shares
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_STRATEGYSHARE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "strategyShare.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "strategyShare.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(strategyShare.uuid IS NULL OR strategyShare.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "strategyShare.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMMPANYSHAREID =
		new FinderPath(StrategyShareModelImpl.ENTITY_CACHE_ENABLED,
			StrategyShareModelImpl.FINDER_CACHE_ENABLED,
			StrategyShareImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCommpanyShareId",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMPANYSHAREID =
		new FinderPath(StrategyShareModelImpl.ENTITY_CACHE_ENABLED,
			StrategyShareModelImpl.FINDER_CACHE_ENABLED,
			StrategyShareImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCommpanyShareId",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			StrategyShareModelImpl.SHAREID_COLUMN_BITMASK |
			StrategyShareModelImpl.GROUPID_COLUMN_BITMASK |
			StrategyShareModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMMPANYSHAREID = new FinderPath(StrategyShareModelImpl.ENTITY_CACHE_ENABLED,
			StrategyShareModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCommpanyShareId",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});

	/**
	 * Returns all the strategy shares where shareId = &#63; and groupId = &#63; and companyId = &#63;.
	 *
	 * @param shareId the share ID
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @return the matching strategy shares
	 */
	@Override
	public List<StrategyShare> findByCommpanyShareId(long shareId,
		long groupId, long companyId) {
		return findByCommpanyShareId(shareId, groupId, companyId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the strategy shares where shareId = &#63; and groupId = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param shareId the share ID
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param start the lower bound of the range of strategy shares
	 * @param end the upper bound of the range of strategy shares (not inclusive)
	 * @return the range of matching strategy shares
	 */
	@Override
	public List<StrategyShare> findByCommpanyShareId(long shareId,
		long groupId, long companyId, int start, int end) {
		return findByCommpanyShareId(shareId, groupId, companyId, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the strategy shares where shareId = &#63; and groupId = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param shareId the share ID
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param start the lower bound of the range of strategy shares
	 * @param end the upper bound of the range of strategy shares (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching strategy shares
	 */
	@Override
	public List<StrategyShare> findByCommpanyShareId(long shareId,
		long groupId, long companyId, int start, int end,
		OrderByComparator<StrategyShare> orderByComparator) {
		return findByCommpanyShareId(shareId, groupId, companyId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the strategy shares where shareId = &#63; and groupId = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param shareId the share ID
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param start the lower bound of the range of strategy shares
	 * @param end the upper bound of the range of strategy shares (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching strategy shares
	 */
	@Override
	public List<StrategyShare> findByCommpanyShareId(long shareId,
		long groupId, long companyId, int start, int end,
		OrderByComparator<StrategyShare> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMPANYSHAREID;
			finderArgs = new Object[] { shareId, groupId, companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMMPANYSHAREID;
			finderArgs = new Object[] {
					shareId, groupId, companyId,
					
					start, end, orderByComparator
				};
		}

		List<StrategyShare> list = null;

		if (retrieveFromCache) {
			list = (List<StrategyShare>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (StrategyShare strategyShare : list) {
					if ((shareId != strategyShare.getShareId()) ||
							(groupId != strategyShare.getGroupId()) ||
							(companyId != strategyShare.getCompanyId())) {
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

			query.append(_SQL_SELECT_STRATEGYSHARE_WHERE);

			query.append(_FINDER_COLUMN_COMMPANYSHAREID_SHAREID_2);

			query.append(_FINDER_COLUMN_COMMPANYSHAREID_GROUPID_2);

			query.append(_FINDER_COLUMN_COMMPANYSHAREID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(StrategyShareModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(shareId);

				qPos.add(groupId);

				qPos.add(companyId);

				if (!pagination) {
					list = (List<StrategyShare>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<StrategyShare>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first strategy share in the ordered set where shareId = &#63; and groupId = &#63; and companyId = &#63;.
	 *
	 * @param shareId the share ID
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching strategy share
	 * @throws NoSuchStrategyShareException if a matching strategy share could not be found
	 */
	@Override
	public StrategyShare findByCommpanyShareId_First(long shareId,
		long groupId, long companyId,
		OrderByComparator<StrategyShare> orderByComparator)
		throws NoSuchStrategyShareException {
		StrategyShare strategyShare = fetchByCommpanyShareId_First(shareId,
				groupId, companyId, orderByComparator);

		if (strategyShare != null) {
			return strategyShare;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("shareId=");
		msg.append(shareId);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchStrategyShareException(msg.toString());
	}

	/**
	 * Returns the first strategy share in the ordered set where shareId = &#63; and groupId = &#63; and companyId = &#63;.
	 *
	 * @param shareId the share ID
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching strategy share, or <code>null</code> if a matching strategy share could not be found
	 */
	@Override
	public StrategyShare fetchByCommpanyShareId_First(long shareId,
		long groupId, long companyId,
		OrderByComparator<StrategyShare> orderByComparator) {
		List<StrategyShare> list = findByCommpanyShareId(shareId, groupId,
				companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last strategy share in the ordered set where shareId = &#63; and groupId = &#63; and companyId = &#63;.
	 *
	 * @param shareId the share ID
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching strategy share
	 * @throws NoSuchStrategyShareException if a matching strategy share could not be found
	 */
	@Override
	public StrategyShare findByCommpanyShareId_Last(long shareId, long groupId,
		long companyId, OrderByComparator<StrategyShare> orderByComparator)
		throws NoSuchStrategyShareException {
		StrategyShare strategyShare = fetchByCommpanyShareId_Last(shareId,
				groupId, companyId, orderByComparator);

		if (strategyShare != null) {
			return strategyShare;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("shareId=");
		msg.append(shareId);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchStrategyShareException(msg.toString());
	}

	/**
	 * Returns the last strategy share in the ordered set where shareId = &#63; and groupId = &#63; and companyId = &#63;.
	 *
	 * @param shareId the share ID
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching strategy share, or <code>null</code> if a matching strategy share could not be found
	 */
	@Override
	public StrategyShare fetchByCommpanyShareId_Last(long shareId,
		long groupId, long companyId,
		OrderByComparator<StrategyShare> orderByComparator) {
		int count = countByCommpanyShareId(shareId, groupId, companyId);

		if (count == 0) {
			return null;
		}

		List<StrategyShare> list = findByCommpanyShareId(shareId, groupId,
				companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the strategy shares before and after the current strategy share in the ordered set where shareId = &#63; and groupId = &#63; and companyId = &#63;.
	 *
	 * @param strategyshareId the primary key of the current strategy share
	 * @param shareId the share ID
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next strategy share
	 * @throws NoSuchStrategyShareException if a strategy share with the primary key could not be found
	 */
	@Override
	public StrategyShare[] findByCommpanyShareId_PrevAndNext(
		long strategyshareId, long shareId, long groupId, long companyId,
		OrderByComparator<StrategyShare> orderByComparator)
		throws NoSuchStrategyShareException {
		StrategyShare strategyShare = findByPrimaryKey(strategyshareId);

		Session session = null;

		try {
			session = openSession();

			StrategyShare[] array = new StrategyShareImpl[3];

			array[0] = getByCommpanyShareId_PrevAndNext(session, strategyShare,
					shareId, groupId, companyId, orderByComparator, true);

			array[1] = strategyShare;

			array[2] = getByCommpanyShareId_PrevAndNext(session, strategyShare,
					shareId, groupId, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected StrategyShare getByCommpanyShareId_PrevAndNext(Session session,
		StrategyShare strategyShare, long shareId, long groupId,
		long companyId, OrderByComparator<StrategyShare> orderByComparator,
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

		query.append(_SQL_SELECT_STRATEGYSHARE_WHERE);

		query.append(_FINDER_COLUMN_COMMPANYSHAREID_SHAREID_2);

		query.append(_FINDER_COLUMN_COMMPANYSHAREID_GROUPID_2);

		query.append(_FINDER_COLUMN_COMMPANYSHAREID_COMPANYID_2);

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
			query.append(StrategyShareModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(shareId);

		qPos.add(groupId);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(strategyShare);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<StrategyShare> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the strategy shares where shareId = &#63; and groupId = &#63; and companyId = &#63; from the database.
	 *
	 * @param shareId the share ID
	 * @param groupId the group ID
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCommpanyShareId(long shareId, long groupId,
		long companyId) {
		for (StrategyShare strategyShare : findByCommpanyShareId(shareId,
				groupId, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(strategyShare);
		}
	}

	/**
	 * Returns the number of strategy shares where shareId = &#63; and groupId = &#63; and companyId = &#63;.
	 *
	 * @param shareId the share ID
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @return the number of matching strategy shares
	 */
	@Override
	public int countByCommpanyShareId(long shareId, long groupId, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMMPANYSHAREID;

		Object[] finderArgs = new Object[] { shareId, groupId, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_STRATEGYSHARE_WHERE);

			query.append(_FINDER_COLUMN_COMMPANYSHAREID_SHAREID_2);

			query.append(_FINDER_COLUMN_COMMPANYSHAREID_GROUPID_2);

			query.append(_FINDER_COLUMN_COMMPANYSHAREID_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(shareId);

				qPos.add(groupId);

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

	private static final String _FINDER_COLUMN_COMMPANYSHAREID_SHAREID_2 = "strategyShare.shareId = ? AND ";
	private static final String _FINDER_COLUMN_COMMPANYSHAREID_GROUPID_2 = "strategyShare.groupId = ? AND ";
	private static final String _FINDER_COLUMN_COMMPANYSHAREID_COMPANYID_2 = "strategyShare.companyId = ?";

	public StrategySharePersistenceImpl() {
		setModelClass(StrategyShare.class);
	}

	/**
	 * Caches the strategy share in the entity cache if it is enabled.
	 *
	 * @param strategyShare the strategy share
	 */
	@Override
	public void cacheResult(StrategyShare strategyShare) {
		entityCache.putResult(StrategyShareModelImpl.ENTITY_CACHE_ENABLED,
			StrategyShareImpl.class, strategyShare.getPrimaryKey(),
			strategyShare);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { strategyShare.getUuid(), strategyShare.getGroupId() },
			strategyShare);

		strategyShare.resetOriginalValues();
	}

	/**
	 * Caches the strategy shares in the entity cache if it is enabled.
	 *
	 * @param strategyShares the strategy shares
	 */
	@Override
	public void cacheResult(List<StrategyShare> strategyShares) {
		for (StrategyShare strategyShare : strategyShares) {
			if (entityCache.getResult(
						StrategyShareModelImpl.ENTITY_CACHE_ENABLED,
						StrategyShareImpl.class, strategyShare.getPrimaryKey()) == null) {
				cacheResult(strategyShare);
			}
			else {
				strategyShare.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all strategy shares.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(StrategyShareImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the strategy share.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(StrategyShare strategyShare) {
		entityCache.removeResult(StrategyShareModelImpl.ENTITY_CACHE_ENABLED,
			StrategyShareImpl.class, strategyShare.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((StrategyShareModelImpl)strategyShare);
	}

	@Override
	public void clearCache(List<StrategyShare> strategyShares) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (StrategyShare strategyShare : strategyShares) {
			entityCache.removeResult(StrategyShareModelImpl.ENTITY_CACHE_ENABLED,
				StrategyShareImpl.class, strategyShare.getPrimaryKey());

			clearUniqueFindersCache((StrategyShareModelImpl)strategyShare);
		}
	}

	protected void cacheUniqueFindersCache(
		StrategyShareModelImpl strategyShareModelImpl, boolean isNew) {
		if (isNew) {
			Object[] args = new Object[] {
					strategyShareModelImpl.getUuid(),
					strategyShareModelImpl.getGroupId()
				};

			finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
				Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
				strategyShareModelImpl);
		}
		else {
			if ((strategyShareModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						strategyShareModelImpl.getUuid(),
						strategyShareModelImpl.getGroupId()
					};

				finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
					Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
					strategyShareModelImpl);
			}
		}
	}

	protected void clearUniqueFindersCache(
		StrategyShareModelImpl strategyShareModelImpl) {
		Object[] args = new Object[] {
				strategyShareModelImpl.getUuid(),
				strategyShareModelImpl.getGroupId()
			};

		finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

		if ((strategyShareModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			args = new Object[] {
					strategyShareModelImpl.getOriginalUuid(),
					strategyShareModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new strategy share with the primary key. Does not add the strategy share to the database.
	 *
	 * @param strategyshareId the primary key for the new strategy share
	 * @return the new strategy share
	 */
	@Override
	public StrategyShare create(long strategyshareId) {
		StrategyShare strategyShare = new StrategyShareImpl();

		strategyShare.setNew(true);
		strategyShare.setPrimaryKey(strategyshareId);

		String uuid = PortalUUIDUtil.generate();

		strategyShare.setUuid(uuid);

		strategyShare.setCompanyId(companyProvider.getCompanyId());

		return strategyShare;
	}

	/**
	 * Removes the strategy share with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param strategyshareId the primary key of the strategy share
	 * @return the strategy share that was removed
	 * @throws NoSuchStrategyShareException if a strategy share with the primary key could not be found
	 */
	@Override
	public StrategyShare remove(long strategyshareId)
		throws NoSuchStrategyShareException {
		return remove((Serializable)strategyshareId);
	}

	/**
	 * Removes the strategy share with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the strategy share
	 * @return the strategy share that was removed
	 * @throws NoSuchStrategyShareException if a strategy share with the primary key could not be found
	 */
	@Override
	public StrategyShare remove(Serializable primaryKey)
		throws NoSuchStrategyShareException {
		Session session = null;

		try {
			session = openSession();

			StrategyShare strategyShare = (StrategyShare)session.get(StrategyShareImpl.class,
					primaryKey);

			if (strategyShare == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchStrategyShareException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(strategyShare);
		}
		catch (NoSuchStrategyShareException nsee) {
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
	protected StrategyShare removeImpl(StrategyShare strategyShare) {
		strategyShare = toUnwrappedModel(strategyShare);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(strategyShare)) {
				strategyShare = (StrategyShare)session.get(StrategyShareImpl.class,
						strategyShare.getPrimaryKeyObj());
			}

			if (strategyShare != null) {
				session.delete(strategyShare);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (strategyShare != null) {
			clearCache(strategyShare);
		}

		return strategyShare;
	}

	@Override
	public StrategyShare updateImpl(StrategyShare strategyShare) {
		strategyShare = toUnwrappedModel(strategyShare);

		boolean isNew = strategyShare.isNew();

		StrategyShareModelImpl strategyShareModelImpl = (StrategyShareModelImpl)strategyShare;

		if (Validator.isNull(strategyShare.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			strategyShare.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (strategyShare.getCreateDate() == null)) {
			if (serviceContext == null) {
				strategyShare.setCreateDate(now);
			}
			else {
				strategyShare.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!strategyShareModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				strategyShare.setModifiedDate(now);
			}
			else {
				strategyShare.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (strategyShare.isNew()) {
				session.save(strategyShare);

				strategyShare.setNew(false);
			}
			else {
				strategyShare = (StrategyShare)session.merge(strategyShare);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !StrategyShareModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((strategyShareModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						strategyShareModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { strategyShareModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((strategyShareModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						strategyShareModelImpl.getOriginalUuid(),
						strategyShareModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						strategyShareModelImpl.getUuid(),
						strategyShareModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((strategyShareModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMPANYSHAREID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						strategyShareModelImpl.getOriginalShareId(),
						strategyShareModelImpl.getOriginalGroupId(),
						strategyShareModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMMPANYSHAREID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMPANYSHAREID,
					args);

				args = new Object[] {
						strategyShareModelImpl.getShareId(),
						strategyShareModelImpl.getGroupId(),
						strategyShareModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMMPANYSHAREID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMPANYSHAREID,
					args);
			}
		}

		entityCache.putResult(StrategyShareModelImpl.ENTITY_CACHE_ENABLED,
			StrategyShareImpl.class, strategyShare.getPrimaryKey(),
			strategyShare, false);

		clearUniqueFindersCache(strategyShareModelImpl);
		cacheUniqueFindersCache(strategyShareModelImpl, isNew);

		strategyShare.resetOriginalValues();

		return strategyShare;
	}

	protected StrategyShare toUnwrappedModel(StrategyShare strategyShare) {
		if (strategyShare instanceof StrategyShareImpl) {
			return strategyShare;
		}

		StrategyShareImpl strategyShareImpl = new StrategyShareImpl();

		strategyShareImpl.setNew(strategyShare.isNew());
		strategyShareImpl.setPrimaryKey(strategyShare.getPrimaryKey());

		strategyShareImpl.setUuid(strategyShare.getUuid());
		strategyShareImpl.setStrategyshareId(strategyShare.getStrategyshareId());
		strategyShareImpl.setGroupId(strategyShare.getGroupId());
		strategyShareImpl.setCompanyId(strategyShare.getCompanyId());
		strategyShareImpl.setCreateDate(strategyShare.getCreateDate());
		strategyShareImpl.setModifiedDate(strategyShare.getModifiedDate());
		strategyShareImpl.setStrategyId(strategyShare.getStrategyId());
		strategyShareImpl.setShareId(strategyShare.getShareId());
		strategyShareImpl.setStrategyparamsoverride(strategyShare.getStrategyparamsoverride());

		return strategyShareImpl;
	}

	/**
	 * Returns the strategy share with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the strategy share
	 * @return the strategy share
	 * @throws NoSuchStrategyShareException if a strategy share with the primary key could not be found
	 */
	@Override
	public StrategyShare findByPrimaryKey(Serializable primaryKey)
		throws NoSuchStrategyShareException {
		StrategyShare strategyShare = fetchByPrimaryKey(primaryKey);

		if (strategyShare == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchStrategyShareException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return strategyShare;
	}

	/**
	 * Returns the strategy share with the primary key or throws a {@link NoSuchStrategyShareException} if it could not be found.
	 *
	 * @param strategyshareId the primary key of the strategy share
	 * @return the strategy share
	 * @throws NoSuchStrategyShareException if a strategy share with the primary key could not be found
	 */
	@Override
	public StrategyShare findByPrimaryKey(long strategyshareId)
		throws NoSuchStrategyShareException {
		return findByPrimaryKey((Serializable)strategyshareId);
	}

	/**
	 * Returns the strategy share with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the strategy share
	 * @return the strategy share, or <code>null</code> if a strategy share with the primary key could not be found
	 */
	@Override
	public StrategyShare fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(StrategyShareModelImpl.ENTITY_CACHE_ENABLED,
				StrategyShareImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		StrategyShare strategyShare = (StrategyShare)serializable;

		if (strategyShare == null) {
			Session session = null;

			try {
				session = openSession();

				strategyShare = (StrategyShare)session.get(StrategyShareImpl.class,
						primaryKey);

				if (strategyShare != null) {
					cacheResult(strategyShare);
				}
				else {
					entityCache.putResult(StrategyShareModelImpl.ENTITY_CACHE_ENABLED,
						StrategyShareImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(StrategyShareModelImpl.ENTITY_CACHE_ENABLED,
					StrategyShareImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return strategyShare;
	}

	/**
	 * Returns the strategy share with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param strategyshareId the primary key of the strategy share
	 * @return the strategy share, or <code>null</code> if a strategy share with the primary key could not be found
	 */
	@Override
	public StrategyShare fetchByPrimaryKey(long strategyshareId) {
		return fetchByPrimaryKey((Serializable)strategyshareId);
	}

	@Override
	public Map<Serializable, StrategyShare> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, StrategyShare> map = new HashMap<Serializable, StrategyShare>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			StrategyShare strategyShare = fetchByPrimaryKey(primaryKey);

			if (strategyShare != null) {
				map.put(primaryKey, strategyShare);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(StrategyShareModelImpl.ENTITY_CACHE_ENABLED,
					StrategyShareImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (StrategyShare)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_STRATEGYSHARE_WHERE_PKS_IN);

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

			for (StrategyShare strategyShare : (List<StrategyShare>)q.list()) {
				map.put(strategyShare.getPrimaryKeyObj(), strategyShare);

				cacheResult(strategyShare);

				uncachedPrimaryKeys.remove(strategyShare.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(StrategyShareModelImpl.ENTITY_CACHE_ENABLED,
					StrategyShareImpl.class, primaryKey, nullModel);
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
	 * Returns all the strategy shares.
	 *
	 * @return the strategy shares
	 */
	@Override
	public List<StrategyShare> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the strategy shares.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of strategy shares
	 * @param end the upper bound of the range of strategy shares (not inclusive)
	 * @return the range of strategy shares
	 */
	@Override
	public List<StrategyShare> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the strategy shares.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of strategy shares
	 * @param end the upper bound of the range of strategy shares (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of strategy shares
	 */
	@Override
	public List<StrategyShare> findAll(int start, int end,
		OrderByComparator<StrategyShare> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the strategy shares.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of strategy shares
	 * @param end the upper bound of the range of strategy shares (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of strategy shares
	 */
	@Override
	public List<StrategyShare> findAll(int start, int end,
		OrderByComparator<StrategyShare> orderByComparator,
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

		List<StrategyShare> list = null;

		if (retrieveFromCache) {
			list = (List<StrategyShare>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_STRATEGYSHARE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_STRATEGYSHARE;

				if (pagination) {
					sql = sql.concat(StrategyShareModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<StrategyShare>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<StrategyShare>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the strategy shares from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (StrategyShare strategyShare : findAll()) {
			remove(strategyShare);
		}
	}

	/**
	 * Returns the number of strategy shares.
	 *
	 * @return the number of strategy shares
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_STRATEGYSHARE);

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
		return StrategyShareModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the strategy share persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(StrategyShareImpl.class.getName());
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
	private static final String _SQL_SELECT_STRATEGYSHARE = "SELECT strategyShare FROM StrategyShare strategyShare";
	private static final String _SQL_SELECT_STRATEGYSHARE_WHERE_PKS_IN = "SELECT strategyShare FROM StrategyShare strategyShare WHERE strategyshareId IN (";
	private static final String _SQL_SELECT_STRATEGYSHARE_WHERE = "SELECT strategyShare FROM StrategyShare strategyShare WHERE ";
	private static final String _SQL_COUNT_STRATEGYSHARE = "SELECT COUNT(strategyShare) FROM StrategyShare strategyShare";
	private static final String _SQL_COUNT_STRATEGYSHARE_WHERE = "SELECT COUNT(strategyShare) FROM StrategyShare strategyShare WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "strategyShare.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StrategyShare exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No StrategyShare exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(StrategySharePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}