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

import com.ibtrader.data.exception.NoSuchAuditIndicatorsStrategyException;
import com.ibtrader.data.model.AuditIndicatorsStrategy;
import com.ibtrader.data.model.impl.AuditIndicatorsStrategyImpl;
import com.ibtrader.data.model.impl.AuditIndicatorsStrategyModelImpl;
import com.ibtrader.data.service.persistence.AuditIndicatorsStrategyPK;
import com.ibtrader.data.service.persistence.AuditIndicatorsStrategyPersistence;

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
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the audit indicators strategy service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AuditIndicatorsStrategyPersistence
 * @see com.ibtrader.data.service.persistence.AuditIndicatorsStrategyUtil
 * @generated
 */
@ProviderType
public class AuditIndicatorsStrategyPersistenceImpl extends BasePersistenceImpl<AuditIndicatorsStrategy>
	implements AuditIndicatorsStrategyPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AuditIndicatorsStrategyUtil} to access the audit indicators strategy persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AuditIndicatorsStrategyImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AuditIndicatorsStrategyModelImpl.ENTITY_CACHE_ENABLED,
			AuditIndicatorsStrategyModelImpl.FINDER_CACHE_ENABLED,
			AuditIndicatorsStrategyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AuditIndicatorsStrategyModelImpl.ENTITY_CACHE_ENABLED,
			AuditIndicatorsStrategyModelImpl.FINDER_CACHE_ENABLED,
			AuditIndicatorsStrategyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AuditIndicatorsStrategyModelImpl.ENTITY_CACHE_ENABLED,
			AuditIndicatorsStrategyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(AuditIndicatorsStrategyModelImpl.ENTITY_CACHE_ENABLED,
			AuditIndicatorsStrategyModelImpl.FINDER_CACHE_ENABLED,
			AuditIndicatorsStrategyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(AuditIndicatorsStrategyModelImpl.ENTITY_CACHE_ENABLED,
			AuditIndicatorsStrategyModelImpl.FINDER_CACHE_ENABLED,
			AuditIndicatorsStrategyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			AuditIndicatorsStrategyModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(AuditIndicatorsStrategyModelImpl.ENTITY_CACHE_ENABLED,
			AuditIndicatorsStrategyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the audit indicators strategies where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching audit indicators strategies
	 */
	@Override
	public List<AuditIndicatorsStrategy> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the audit indicators strategies where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditIndicatorsStrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of audit indicators strategies
	 * @param end the upper bound of the range of audit indicators strategies (not inclusive)
	 * @return the range of matching audit indicators strategies
	 */
	@Override
	public List<AuditIndicatorsStrategy> findByUuid(String uuid, int start,
		int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the audit indicators strategies where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditIndicatorsStrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of audit indicators strategies
	 * @param end the upper bound of the range of audit indicators strategies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching audit indicators strategies
	 */
	@Override
	public List<AuditIndicatorsStrategy> findByUuid(String uuid, int start,
		int end, OrderByComparator<AuditIndicatorsStrategy> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the audit indicators strategies where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditIndicatorsStrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of audit indicators strategies
	 * @param end the upper bound of the range of audit indicators strategies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching audit indicators strategies
	 */
	@Override
	public List<AuditIndicatorsStrategy> findByUuid(String uuid, int start,
		int end, OrderByComparator<AuditIndicatorsStrategy> orderByComparator,
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

		List<AuditIndicatorsStrategy> list = null;

		if (retrieveFromCache) {
			list = (List<AuditIndicatorsStrategy>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AuditIndicatorsStrategy auditIndicatorsStrategy : list) {
					if (!Objects.equals(uuid, auditIndicatorsStrategy.getUuid())) {
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

			query.append(_SQL_SELECT_AUDITINDICATORSSTRATEGY_WHERE);

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
				query.append(AuditIndicatorsStrategyModelImpl.ORDER_BY_JPQL);
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
					list = (List<AuditIndicatorsStrategy>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AuditIndicatorsStrategy>)QueryUtil.list(q,
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
	 * Returns the first audit indicators strategy in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit indicators strategy
	 * @throws NoSuchAuditIndicatorsStrategyException if a matching audit indicators strategy could not be found
	 */
	@Override
	public AuditIndicatorsStrategy findByUuid_First(String uuid,
		OrderByComparator<AuditIndicatorsStrategy> orderByComparator)
		throws NoSuchAuditIndicatorsStrategyException {
		AuditIndicatorsStrategy auditIndicatorsStrategy = fetchByUuid_First(uuid,
				orderByComparator);

		if (auditIndicatorsStrategy != null) {
			return auditIndicatorsStrategy;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAuditIndicatorsStrategyException(msg.toString());
	}

	/**
	 * Returns the first audit indicators strategy in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit indicators strategy, or <code>null</code> if a matching audit indicators strategy could not be found
	 */
	@Override
	public AuditIndicatorsStrategy fetchByUuid_First(String uuid,
		OrderByComparator<AuditIndicatorsStrategy> orderByComparator) {
		List<AuditIndicatorsStrategy> list = findByUuid(uuid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last audit indicators strategy in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit indicators strategy
	 * @throws NoSuchAuditIndicatorsStrategyException if a matching audit indicators strategy could not be found
	 */
	@Override
	public AuditIndicatorsStrategy findByUuid_Last(String uuid,
		OrderByComparator<AuditIndicatorsStrategy> orderByComparator)
		throws NoSuchAuditIndicatorsStrategyException {
		AuditIndicatorsStrategy auditIndicatorsStrategy = fetchByUuid_Last(uuid,
				orderByComparator);

		if (auditIndicatorsStrategy != null) {
			return auditIndicatorsStrategy;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAuditIndicatorsStrategyException(msg.toString());
	}

	/**
	 * Returns the last audit indicators strategy in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit indicators strategy, or <code>null</code> if a matching audit indicators strategy could not be found
	 */
	@Override
	public AuditIndicatorsStrategy fetchByUuid_Last(String uuid,
		OrderByComparator<AuditIndicatorsStrategy> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<AuditIndicatorsStrategy> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the audit indicators strategies before and after the current audit indicators strategy in the ordered set where uuid = &#63;.
	 *
	 * @param auditIndicatorsStrategyPK the primary key of the current audit indicators strategy
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next audit indicators strategy
	 * @throws NoSuchAuditIndicatorsStrategyException if a audit indicators strategy with the primary key could not be found
	 */
	@Override
	public AuditIndicatorsStrategy[] findByUuid_PrevAndNext(
		AuditIndicatorsStrategyPK auditIndicatorsStrategyPK, String uuid,
		OrderByComparator<AuditIndicatorsStrategy> orderByComparator)
		throws NoSuchAuditIndicatorsStrategyException {
		AuditIndicatorsStrategy auditIndicatorsStrategy = findByPrimaryKey(auditIndicatorsStrategyPK);

		Session session = null;

		try {
			session = openSession();

			AuditIndicatorsStrategy[] array = new AuditIndicatorsStrategyImpl[3];

			array[0] = getByUuid_PrevAndNext(session, auditIndicatorsStrategy,
					uuid, orderByComparator, true);

			array[1] = auditIndicatorsStrategy;

			array[2] = getByUuid_PrevAndNext(session, auditIndicatorsStrategy,
					uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AuditIndicatorsStrategy getByUuid_PrevAndNext(Session session,
		AuditIndicatorsStrategy auditIndicatorsStrategy, String uuid,
		OrderByComparator<AuditIndicatorsStrategy> orderByComparator,
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

		query.append(_SQL_SELECT_AUDITINDICATORSSTRATEGY_WHERE);

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
			query.append(AuditIndicatorsStrategyModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(auditIndicatorsStrategy);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AuditIndicatorsStrategy> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the audit indicators strategies where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (AuditIndicatorsStrategy auditIndicatorsStrategy : findByUuid(
				uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(auditIndicatorsStrategy);
		}
	}

	/**
	 * Returns the number of audit indicators strategies where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching audit indicators strategies
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_AUDITINDICATORSSTRATEGY_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "auditIndicatorsStrategy.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "auditIndicatorsStrategy.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(auditIndicatorsStrategy.uuid IS NULL OR auditIndicatorsStrategy.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(AuditIndicatorsStrategyModelImpl.ENTITY_CACHE_ENABLED,
			AuditIndicatorsStrategyModelImpl.FINDER_CACHE_ENABLED,
			AuditIndicatorsStrategyImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			AuditIndicatorsStrategyModelImpl.UUID_COLUMN_BITMASK |
			AuditIndicatorsStrategyModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(AuditIndicatorsStrategyModelImpl.ENTITY_CACHE_ENABLED,
			AuditIndicatorsStrategyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the audit indicators strategy where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchAuditIndicatorsStrategyException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching audit indicators strategy
	 * @throws NoSuchAuditIndicatorsStrategyException if a matching audit indicators strategy could not be found
	 */
	@Override
	public AuditIndicatorsStrategy findByUUID_G(String uuid, long groupId)
		throws NoSuchAuditIndicatorsStrategyException {
		AuditIndicatorsStrategy auditIndicatorsStrategy = fetchByUUID_G(uuid,
				groupId);

		if (auditIndicatorsStrategy == null) {
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

			throw new NoSuchAuditIndicatorsStrategyException(msg.toString());
		}

		return auditIndicatorsStrategy;
	}

	/**
	 * Returns the audit indicators strategy where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching audit indicators strategy, or <code>null</code> if a matching audit indicators strategy could not be found
	 */
	@Override
	public AuditIndicatorsStrategy fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the audit indicators strategy where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching audit indicators strategy, or <code>null</code> if a matching audit indicators strategy could not be found
	 */
	@Override
	public AuditIndicatorsStrategy fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof AuditIndicatorsStrategy) {
			AuditIndicatorsStrategy auditIndicatorsStrategy = (AuditIndicatorsStrategy)result;

			if (!Objects.equals(uuid, auditIndicatorsStrategy.getUuid()) ||
					(groupId != auditIndicatorsStrategy.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_AUDITINDICATORSSTRATEGY_WHERE);

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

				List<AuditIndicatorsStrategy> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					AuditIndicatorsStrategy auditIndicatorsStrategy = list.get(0);

					result = auditIndicatorsStrategy;

					cacheResult(auditIndicatorsStrategy);

					if ((auditIndicatorsStrategy.getUuid() == null) ||
							!auditIndicatorsStrategy.getUuid().equals(uuid) ||
							(auditIndicatorsStrategy.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, auditIndicatorsStrategy);
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
			return (AuditIndicatorsStrategy)result;
		}
	}

	/**
	 * Removes the audit indicators strategy where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the audit indicators strategy that was removed
	 */
	@Override
	public AuditIndicatorsStrategy removeByUUID_G(String uuid, long groupId)
		throws NoSuchAuditIndicatorsStrategyException {
		AuditIndicatorsStrategy auditIndicatorsStrategy = findByUUID_G(uuid,
				groupId);

		return remove(auditIndicatorsStrategy);
	}

	/**
	 * Returns the number of audit indicators strategies where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching audit indicators strategies
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_AUDITINDICATORSSTRATEGY_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "auditIndicatorsStrategy.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "auditIndicatorsStrategy.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(auditIndicatorsStrategy.uuid IS NULL OR auditIndicatorsStrategy.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "auditIndicatorsStrategy.id.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(AuditIndicatorsStrategyModelImpl.ENTITY_CACHE_ENABLED,
			AuditIndicatorsStrategyModelImpl.FINDER_CACHE_ENABLED,
			AuditIndicatorsStrategyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(AuditIndicatorsStrategyModelImpl.ENTITY_CACHE_ENABLED,
			AuditIndicatorsStrategyModelImpl.FINDER_CACHE_ENABLED,
			AuditIndicatorsStrategyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			AuditIndicatorsStrategyModelImpl.UUID_COLUMN_BITMASK |
			AuditIndicatorsStrategyModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(AuditIndicatorsStrategyModelImpl.ENTITY_CACHE_ENABLED,
			AuditIndicatorsStrategyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the audit indicators strategies where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching audit indicators strategies
	 */
	@Override
	public List<AuditIndicatorsStrategy> findByUuid_C(String uuid,
		long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the audit indicators strategies where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditIndicatorsStrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of audit indicators strategies
	 * @param end the upper bound of the range of audit indicators strategies (not inclusive)
	 * @return the range of matching audit indicators strategies
	 */
	@Override
	public List<AuditIndicatorsStrategy> findByUuid_C(String uuid,
		long companyId, int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the audit indicators strategies where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditIndicatorsStrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of audit indicators strategies
	 * @param end the upper bound of the range of audit indicators strategies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching audit indicators strategies
	 */
	@Override
	public List<AuditIndicatorsStrategy> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<AuditIndicatorsStrategy> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the audit indicators strategies where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditIndicatorsStrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of audit indicators strategies
	 * @param end the upper bound of the range of audit indicators strategies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching audit indicators strategies
	 */
	@Override
	public List<AuditIndicatorsStrategy> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<AuditIndicatorsStrategy> orderByComparator,
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

		List<AuditIndicatorsStrategy> list = null;

		if (retrieveFromCache) {
			list = (List<AuditIndicatorsStrategy>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AuditIndicatorsStrategy auditIndicatorsStrategy : list) {
					if (!Objects.equals(uuid, auditIndicatorsStrategy.getUuid()) ||
							(companyId != auditIndicatorsStrategy.getCompanyId())) {
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

			query.append(_SQL_SELECT_AUDITINDICATORSSTRATEGY_WHERE);

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
				query.append(AuditIndicatorsStrategyModelImpl.ORDER_BY_JPQL);
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
					list = (List<AuditIndicatorsStrategy>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AuditIndicatorsStrategy>)QueryUtil.list(q,
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
	 * Returns the first audit indicators strategy in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit indicators strategy
	 * @throws NoSuchAuditIndicatorsStrategyException if a matching audit indicators strategy could not be found
	 */
	@Override
	public AuditIndicatorsStrategy findByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<AuditIndicatorsStrategy> orderByComparator)
		throws NoSuchAuditIndicatorsStrategyException {
		AuditIndicatorsStrategy auditIndicatorsStrategy = fetchByUuid_C_First(uuid,
				companyId, orderByComparator);

		if (auditIndicatorsStrategy != null) {
			return auditIndicatorsStrategy;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAuditIndicatorsStrategyException(msg.toString());
	}

	/**
	 * Returns the first audit indicators strategy in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit indicators strategy, or <code>null</code> if a matching audit indicators strategy could not be found
	 */
	@Override
	public AuditIndicatorsStrategy fetchByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<AuditIndicatorsStrategy> orderByComparator) {
		List<AuditIndicatorsStrategy> list = findByUuid_C(uuid, companyId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last audit indicators strategy in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit indicators strategy
	 * @throws NoSuchAuditIndicatorsStrategyException if a matching audit indicators strategy could not be found
	 */
	@Override
	public AuditIndicatorsStrategy findByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<AuditIndicatorsStrategy> orderByComparator)
		throws NoSuchAuditIndicatorsStrategyException {
		AuditIndicatorsStrategy auditIndicatorsStrategy = fetchByUuid_C_Last(uuid,
				companyId, orderByComparator);

		if (auditIndicatorsStrategy != null) {
			return auditIndicatorsStrategy;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAuditIndicatorsStrategyException(msg.toString());
	}

	/**
	 * Returns the last audit indicators strategy in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit indicators strategy, or <code>null</code> if a matching audit indicators strategy could not be found
	 */
	@Override
	public AuditIndicatorsStrategy fetchByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<AuditIndicatorsStrategy> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<AuditIndicatorsStrategy> list = findByUuid_C(uuid, companyId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the audit indicators strategies before and after the current audit indicators strategy in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param auditIndicatorsStrategyPK the primary key of the current audit indicators strategy
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next audit indicators strategy
	 * @throws NoSuchAuditIndicatorsStrategyException if a audit indicators strategy with the primary key could not be found
	 */
	@Override
	public AuditIndicatorsStrategy[] findByUuid_C_PrevAndNext(
		AuditIndicatorsStrategyPK auditIndicatorsStrategyPK, String uuid,
		long companyId,
		OrderByComparator<AuditIndicatorsStrategy> orderByComparator)
		throws NoSuchAuditIndicatorsStrategyException {
		AuditIndicatorsStrategy auditIndicatorsStrategy = findByPrimaryKey(auditIndicatorsStrategyPK);

		Session session = null;

		try {
			session = openSession();

			AuditIndicatorsStrategy[] array = new AuditIndicatorsStrategyImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session,
					auditIndicatorsStrategy, uuid, companyId,
					orderByComparator, true);

			array[1] = auditIndicatorsStrategy;

			array[2] = getByUuid_C_PrevAndNext(session,
					auditIndicatorsStrategy, uuid, companyId,
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

	protected AuditIndicatorsStrategy getByUuid_C_PrevAndNext(Session session,
		AuditIndicatorsStrategy auditIndicatorsStrategy, String uuid,
		long companyId,
		OrderByComparator<AuditIndicatorsStrategy> orderByComparator,
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

		query.append(_SQL_SELECT_AUDITINDICATORSSTRATEGY_WHERE);

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
			query.append(AuditIndicatorsStrategyModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(auditIndicatorsStrategy);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AuditIndicatorsStrategy> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the audit indicators strategies where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (AuditIndicatorsStrategy auditIndicatorsStrategy : findByUuid_C(
				uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(auditIndicatorsStrategy);
		}
	}

	/**
	 * Returns the number of audit indicators strategies where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching audit indicators strategies
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_AUDITINDICATORSSTRATEGY_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "auditIndicatorsStrategy.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "auditIndicatorsStrategy.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(auditIndicatorsStrategy.uuid IS NULL OR auditIndicatorsStrategy.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "auditIndicatorsStrategy.id.companyId = ?";

	public AuditIndicatorsStrategyPersistenceImpl() {
		setModelClass(AuditIndicatorsStrategy.class);
	}

	/**
	 * Caches the audit indicators strategy in the entity cache if it is enabled.
	 *
	 * @param auditIndicatorsStrategy the audit indicators strategy
	 */
	@Override
	public void cacheResult(AuditIndicatorsStrategy auditIndicatorsStrategy) {
		entityCache.putResult(AuditIndicatorsStrategyModelImpl.ENTITY_CACHE_ENABLED,
			AuditIndicatorsStrategyImpl.class,
			auditIndicatorsStrategy.getPrimaryKey(), auditIndicatorsStrategy);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				auditIndicatorsStrategy.getUuid(),
				auditIndicatorsStrategy.getGroupId()
			}, auditIndicatorsStrategy);

		auditIndicatorsStrategy.resetOriginalValues();
	}

	/**
	 * Caches the audit indicators strategies in the entity cache if it is enabled.
	 *
	 * @param auditIndicatorsStrategies the audit indicators strategies
	 */
	@Override
	public void cacheResult(
		List<AuditIndicatorsStrategy> auditIndicatorsStrategies) {
		for (AuditIndicatorsStrategy auditIndicatorsStrategy : auditIndicatorsStrategies) {
			if (entityCache.getResult(
						AuditIndicatorsStrategyModelImpl.ENTITY_CACHE_ENABLED,
						AuditIndicatorsStrategyImpl.class,
						auditIndicatorsStrategy.getPrimaryKey()) == null) {
				cacheResult(auditIndicatorsStrategy);
			}
			else {
				auditIndicatorsStrategy.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all audit indicators strategies.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AuditIndicatorsStrategyImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the audit indicators strategy.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AuditIndicatorsStrategy auditIndicatorsStrategy) {
		entityCache.removeResult(AuditIndicatorsStrategyModelImpl.ENTITY_CACHE_ENABLED,
			AuditIndicatorsStrategyImpl.class,
			auditIndicatorsStrategy.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((AuditIndicatorsStrategyModelImpl)auditIndicatorsStrategy,
			true);
	}

	@Override
	public void clearCache(
		List<AuditIndicatorsStrategy> auditIndicatorsStrategies) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AuditIndicatorsStrategy auditIndicatorsStrategy : auditIndicatorsStrategies) {
			entityCache.removeResult(AuditIndicatorsStrategyModelImpl.ENTITY_CACHE_ENABLED,
				AuditIndicatorsStrategyImpl.class,
				auditIndicatorsStrategy.getPrimaryKey());

			clearUniqueFindersCache((AuditIndicatorsStrategyModelImpl)auditIndicatorsStrategy,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		AuditIndicatorsStrategyModelImpl auditIndicatorsStrategyModelImpl) {
		Object[] args = new Object[] {
				auditIndicatorsStrategyModelImpl.getUuid(),
				auditIndicatorsStrategyModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			auditIndicatorsStrategyModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		AuditIndicatorsStrategyModelImpl auditIndicatorsStrategyModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					auditIndicatorsStrategyModelImpl.getUuid(),
					auditIndicatorsStrategyModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((auditIndicatorsStrategyModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					auditIndicatorsStrategyModelImpl.getOriginalUuid(),
					auditIndicatorsStrategyModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new audit indicators strategy with the primary key. Does not add the audit indicators strategy to the database.
	 *
	 * @param auditIndicatorsStrategyPK the primary key for the new audit indicators strategy
	 * @return the new audit indicators strategy
	 */
	@Override
	public AuditIndicatorsStrategy create(
		AuditIndicatorsStrategyPK auditIndicatorsStrategyPK) {
		AuditIndicatorsStrategy auditIndicatorsStrategy = new AuditIndicatorsStrategyImpl();

		auditIndicatorsStrategy.setNew(true);
		auditIndicatorsStrategy.setPrimaryKey(auditIndicatorsStrategyPK);

		String uuid = PortalUUIDUtil.generate();

		auditIndicatorsStrategy.setUuid(uuid);

		auditIndicatorsStrategy.setCompanyId(companyProvider.getCompanyId());

		return auditIndicatorsStrategy;
	}

	/**
	 * Removes the audit indicators strategy with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param auditIndicatorsStrategyPK the primary key of the audit indicators strategy
	 * @return the audit indicators strategy that was removed
	 * @throws NoSuchAuditIndicatorsStrategyException if a audit indicators strategy with the primary key could not be found
	 */
	@Override
	public AuditIndicatorsStrategy remove(
		AuditIndicatorsStrategyPK auditIndicatorsStrategyPK)
		throws NoSuchAuditIndicatorsStrategyException {
		return remove((Serializable)auditIndicatorsStrategyPK);
	}

	/**
	 * Removes the audit indicators strategy with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the audit indicators strategy
	 * @return the audit indicators strategy that was removed
	 * @throws NoSuchAuditIndicatorsStrategyException if a audit indicators strategy with the primary key could not be found
	 */
	@Override
	public AuditIndicatorsStrategy remove(Serializable primaryKey)
		throws NoSuchAuditIndicatorsStrategyException {
		Session session = null;

		try {
			session = openSession();

			AuditIndicatorsStrategy auditIndicatorsStrategy = (AuditIndicatorsStrategy)session.get(AuditIndicatorsStrategyImpl.class,
					primaryKey);

			if (auditIndicatorsStrategy == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAuditIndicatorsStrategyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(auditIndicatorsStrategy);
		}
		catch (NoSuchAuditIndicatorsStrategyException nsee) {
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
	protected AuditIndicatorsStrategy removeImpl(
		AuditIndicatorsStrategy auditIndicatorsStrategy) {
		auditIndicatorsStrategy = toUnwrappedModel(auditIndicatorsStrategy);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(auditIndicatorsStrategy)) {
				auditIndicatorsStrategy = (AuditIndicatorsStrategy)session.get(AuditIndicatorsStrategyImpl.class,
						auditIndicatorsStrategy.getPrimaryKeyObj());
			}

			if (auditIndicatorsStrategy != null) {
				session.delete(auditIndicatorsStrategy);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (auditIndicatorsStrategy != null) {
			clearCache(auditIndicatorsStrategy);
		}

		return auditIndicatorsStrategy;
	}

	@Override
	public AuditIndicatorsStrategy updateImpl(
		AuditIndicatorsStrategy auditIndicatorsStrategy) {
		auditIndicatorsStrategy = toUnwrappedModel(auditIndicatorsStrategy);

		boolean isNew = auditIndicatorsStrategy.isNew();

		AuditIndicatorsStrategyModelImpl auditIndicatorsStrategyModelImpl = (AuditIndicatorsStrategyModelImpl)auditIndicatorsStrategy;

		if (Validator.isNull(auditIndicatorsStrategy.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			auditIndicatorsStrategy.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (auditIndicatorsStrategy.isNew()) {
				session.save(auditIndicatorsStrategy);

				auditIndicatorsStrategy.setNew(false);
			}
			else {
				auditIndicatorsStrategy = (AuditIndicatorsStrategy)session.merge(auditIndicatorsStrategy);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !AuditIndicatorsStrategyModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((auditIndicatorsStrategyModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						auditIndicatorsStrategyModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { auditIndicatorsStrategyModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((auditIndicatorsStrategyModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						auditIndicatorsStrategyModelImpl.getOriginalUuid(),
						auditIndicatorsStrategyModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						auditIndicatorsStrategyModelImpl.getUuid(),
						auditIndicatorsStrategyModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}
		}

		entityCache.putResult(AuditIndicatorsStrategyModelImpl.ENTITY_CACHE_ENABLED,
			AuditIndicatorsStrategyImpl.class,
			auditIndicatorsStrategy.getPrimaryKey(), auditIndicatorsStrategy,
			false);

		clearUniqueFindersCache(auditIndicatorsStrategyModelImpl, false);
		cacheUniqueFindersCache(auditIndicatorsStrategyModelImpl);

		auditIndicatorsStrategy.resetOriginalValues();

		return auditIndicatorsStrategy;
	}

	protected AuditIndicatorsStrategy toUnwrappedModel(
		AuditIndicatorsStrategy auditIndicatorsStrategy) {
		if (auditIndicatorsStrategy instanceof AuditIndicatorsStrategyImpl) {
			return auditIndicatorsStrategy;
		}

		AuditIndicatorsStrategyImpl auditIndicatorsStrategyImpl = new AuditIndicatorsStrategyImpl();

		auditIndicatorsStrategyImpl.setNew(auditIndicatorsStrategy.isNew());
		auditIndicatorsStrategyImpl.setPrimaryKey(auditIndicatorsStrategy.getPrimaryKey());

		auditIndicatorsStrategyImpl.setUuid(auditIndicatorsStrategy.getUuid());
		auditIndicatorsStrategyImpl.setGroupId(auditIndicatorsStrategy.getGroupId());
		auditIndicatorsStrategyImpl.setCompanyId(auditIndicatorsStrategy.getCompanyId());
		auditIndicatorsStrategyImpl.setAuditDate(auditIndicatorsStrategy.getAuditDate());
		auditIndicatorsStrategyImpl.setAuditstrategy(auditIndicatorsStrategy.getAuditstrategy());
		auditIndicatorsStrategyImpl.setShareId(auditIndicatorsStrategy.getShareId());
		auditIndicatorsStrategyImpl.setAuditData(auditIndicatorsStrategy.getAuditData());

		return auditIndicatorsStrategyImpl;
	}

	/**
	 * Returns the audit indicators strategy with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the audit indicators strategy
	 * @return the audit indicators strategy
	 * @throws NoSuchAuditIndicatorsStrategyException if a audit indicators strategy with the primary key could not be found
	 */
	@Override
	public AuditIndicatorsStrategy findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAuditIndicatorsStrategyException {
		AuditIndicatorsStrategy auditIndicatorsStrategy = fetchByPrimaryKey(primaryKey);

		if (auditIndicatorsStrategy == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAuditIndicatorsStrategyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return auditIndicatorsStrategy;
	}

	/**
	 * Returns the audit indicators strategy with the primary key or throws a {@link NoSuchAuditIndicatorsStrategyException} if it could not be found.
	 *
	 * @param auditIndicatorsStrategyPK the primary key of the audit indicators strategy
	 * @return the audit indicators strategy
	 * @throws NoSuchAuditIndicatorsStrategyException if a audit indicators strategy with the primary key could not be found
	 */
	@Override
	public AuditIndicatorsStrategy findByPrimaryKey(
		AuditIndicatorsStrategyPK auditIndicatorsStrategyPK)
		throws NoSuchAuditIndicatorsStrategyException {
		return findByPrimaryKey((Serializable)auditIndicatorsStrategyPK);
	}

	/**
	 * Returns the audit indicators strategy with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the audit indicators strategy
	 * @return the audit indicators strategy, or <code>null</code> if a audit indicators strategy with the primary key could not be found
	 */
	@Override
	public AuditIndicatorsStrategy fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(AuditIndicatorsStrategyModelImpl.ENTITY_CACHE_ENABLED,
				AuditIndicatorsStrategyImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		AuditIndicatorsStrategy auditIndicatorsStrategy = (AuditIndicatorsStrategy)serializable;

		if (auditIndicatorsStrategy == null) {
			Session session = null;

			try {
				session = openSession();

				auditIndicatorsStrategy = (AuditIndicatorsStrategy)session.get(AuditIndicatorsStrategyImpl.class,
						primaryKey);

				if (auditIndicatorsStrategy != null) {
					cacheResult(auditIndicatorsStrategy);
				}
				else {
					entityCache.putResult(AuditIndicatorsStrategyModelImpl.ENTITY_CACHE_ENABLED,
						AuditIndicatorsStrategyImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(AuditIndicatorsStrategyModelImpl.ENTITY_CACHE_ENABLED,
					AuditIndicatorsStrategyImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return auditIndicatorsStrategy;
	}

	/**
	 * Returns the audit indicators strategy with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param auditIndicatorsStrategyPK the primary key of the audit indicators strategy
	 * @return the audit indicators strategy, or <code>null</code> if a audit indicators strategy with the primary key could not be found
	 */
	@Override
	public AuditIndicatorsStrategy fetchByPrimaryKey(
		AuditIndicatorsStrategyPK auditIndicatorsStrategyPK) {
		return fetchByPrimaryKey((Serializable)auditIndicatorsStrategyPK);
	}

	@Override
	public Map<Serializable, AuditIndicatorsStrategy> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, AuditIndicatorsStrategy> map = new HashMap<Serializable, AuditIndicatorsStrategy>();

		for (Serializable primaryKey : primaryKeys) {
			AuditIndicatorsStrategy auditIndicatorsStrategy = fetchByPrimaryKey(primaryKey);

			if (auditIndicatorsStrategy != null) {
				map.put(primaryKey, auditIndicatorsStrategy);
			}
		}

		return map;
	}

	/**
	 * Returns all the audit indicators strategies.
	 *
	 * @return the audit indicators strategies
	 */
	@Override
	public List<AuditIndicatorsStrategy> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the audit indicators strategies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditIndicatorsStrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of audit indicators strategies
	 * @param end the upper bound of the range of audit indicators strategies (not inclusive)
	 * @return the range of audit indicators strategies
	 */
	@Override
	public List<AuditIndicatorsStrategy> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the audit indicators strategies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditIndicatorsStrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of audit indicators strategies
	 * @param end the upper bound of the range of audit indicators strategies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of audit indicators strategies
	 */
	@Override
	public List<AuditIndicatorsStrategy> findAll(int start, int end,
		OrderByComparator<AuditIndicatorsStrategy> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the audit indicators strategies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditIndicatorsStrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of audit indicators strategies
	 * @param end the upper bound of the range of audit indicators strategies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of audit indicators strategies
	 */
	@Override
	public List<AuditIndicatorsStrategy> findAll(int start, int end,
		OrderByComparator<AuditIndicatorsStrategy> orderByComparator,
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

		List<AuditIndicatorsStrategy> list = null;

		if (retrieveFromCache) {
			list = (List<AuditIndicatorsStrategy>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_AUDITINDICATORSSTRATEGY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_AUDITINDICATORSSTRATEGY;

				if (pagination) {
					sql = sql.concat(AuditIndicatorsStrategyModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<AuditIndicatorsStrategy>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AuditIndicatorsStrategy>)QueryUtil.list(q,
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
	 * Removes all the audit indicators strategies from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AuditIndicatorsStrategy auditIndicatorsStrategy : findAll()) {
			remove(auditIndicatorsStrategy);
		}
	}

	/**
	 * Returns the number of audit indicators strategies.
	 *
	 * @return the number of audit indicators strategies
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_AUDITINDICATORSSTRATEGY);

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
		return AuditIndicatorsStrategyModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the audit indicators strategy persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(AuditIndicatorsStrategyImpl.class.getName());
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
	private static final String _SQL_SELECT_AUDITINDICATORSSTRATEGY = "SELECT auditIndicatorsStrategy FROM AuditIndicatorsStrategy auditIndicatorsStrategy";
	private static final String _SQL_SELECT_AUDITINDICATORSSTRATEGY_WHERE = "SELECT auditIndicatorsStrategy FROM AuditIndicatorsStrategy auditIndicatorsStrategy WHERE ";
	private static final String _SQL_COUNT_AUDITINDICATORSSTRATEGY = "SELECT COUNT(auditIndicatorsStrategy) FROM AuditIndicatorsStrategy auditIndicatorsStrategy";
	private static final String _SQL_COUNT_AUDITINDICATORSSTRATEGY_WHERE = "SELECT COUNT(auditIndicatorsStrategy) FROM AuditIndicatorsStrategy auditIndicatorsStrategy WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "auditIndicatorsStrategy.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AuditIndicatorsStrategy exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AuditIndicatorsStrategy exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(AuditIndicatorsStrategyPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}