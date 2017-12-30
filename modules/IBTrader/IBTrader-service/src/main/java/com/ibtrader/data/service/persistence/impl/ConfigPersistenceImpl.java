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

import com.ibtrader.data.exception.NoSuchConfigException;
import com.ibtrader.data.model.Config;
import com.ibtrader.data.model.impl.ConfigImpl;
import com.ibtrader.data.model.impl.ConfigModelImpl;
import com.ibtrader.data.service.persistence.ConfigPersistence;

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
 * The persistence implementation for the config service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ConfigPersistence
 * @see com.ibtrader.data.service.persistence.ConfigUtil
 * @generated
 */
@ProviderType
public class ConfigPersistenceImpl extends BasePersistenceImpl<Config>
	implements ConfigPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ConfigUtil} to access the config persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ConfigImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ConfigModelImpl.ENTITY_CACHE_ENABLED,
			ConfigModelImpl.FINDER_CACHE_ENABLED, ConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ConfigModelImpl.ENTITY_CACHE_ENABLED,
			ConfigModelImpl.FINDER_CACHE_ENABLED, ConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ConfigModelImpl.ENTITY_CACHE_ENABLED,
			ConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ConfigModelImpl.ENTITY_CACHE_ENABLED,
			ConfigModelImpl.FINDER_CACHE_ENABLED, ConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ConfigModelImpl.ENTITY_CACHE_ENABLED,
			ConfigModelImpl.FINDER_CACHE_ENABLED, ConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			ConfigModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ConfigModelImpl.ENTITY_CACHE_ENABLED,
			ConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the configs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching configs
	 */
	@Override
	public List<Config> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the configs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of configs
	 * @param end the upper bound of the range of configs (not inclusive)
	 * @return the range of matching configs
	 */
	@Override
	public List<Config> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the configs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of configs
	 * @param end the upper bound of the range of configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching configs
	 */
	@Override
	public List<Config> findByUuid(String uuid, int start, int end,
		OrderByComparator<Config> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the configs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of configs
	 * @param end the upper bound of the range of configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching configs
	 */
	@Override
	public List<Config> findByUuid(String uuid, int start, int end,
		OrderByComparator<Config> orderByComparator, boolean retrieveFromCache) {
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

		List<Config> list = null;

		if (retrieveFromCache) {
			list = (List<Config>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Config config : list) {
					if (!Objects.equals(uuid, config.getUuid())) {
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

			query.append(_SQL_SELECT_CONFIG_WHERE);

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
				query.append(ConfigModelImpl.ORDER_BY_JPQL);
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
					list = (List<Config>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Config>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first config in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching config
	 * @throws NoSuchConfigException if a matching config could not be found
	 */
	@Override
	public Config findByUuid_First(String uuid,
		OrderByComparator<Config> orderByComparator)
		throws NoSuchConfigException {
		Config config = fetchByUuid_First(uuid, orderByComparator);

		if (config != null) {
			return config;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchConfigException(msg.toString());
	}

	/**
	 * Returns the first config in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching config, or <code>null</code> if a matching config could not be found
	 */
	@Override
	public Config fetchByUuid_First(String uuid,
		OrderByComparator<Config> orderByComparator) {
		List<Config> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last config in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching config
	 * @throws NoSuchConfigException if a matching config could not be found
	 */
	@Override
	public Config findByUuid_Last(String uuid,
		OrderByComparator<Config> orderByComparator)
		throws NoSuchConfigException {
		Config config = fetchByUuid_Last(uuid, orderByComparator);

		if (config != null) {
			return config;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchConfigException(msg.toString());
	}

	/**
	 * Returns the last config in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching config, or <code>null</code> if a matching config could not be found
	 */
	@Override
	public Config fetchByUuid_Last(String uuid,
		OrderByComparator<Config> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Config> list = findByUuid(uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the configs before and after the current config in the ordered set where uuid = &#63;.
	 *
	 * @param configId the primary key of the current config
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next config
	 * @throws NoSuchConfigException if a config with the primary key could not be found
	 */
	@Override
	public Config[] findByUuid_PrevAndNext(long configId, String uuid,
		OrderByComparator<Config> orderByComparator)
		throws NoSuchConfigException {
		Config config = findByPrimaryKey(configId);

		Session session = null;

		try {
			session = openSession();

			Config[] array = new ConfigImpl[3];

			array[0] = getByUuid_PrevAndNext(session, config, uuid,
					orderByComparator, true);

			array[1] = config;

			array[2] = getByUuid_PrevAndNext(session, config, uuid,
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

	protected Config getByUuid_PrevAndNext(Session session, Config config,
		String uuid, OrderByComparator<Config> orderByComparator,
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

		query.append(_SQL_SELECT_CONFIG_WHERE);

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
			query.append(ConfigModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(config);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Config> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the configs where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (Config config : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(config);
		}
	}

	/**
	 * Returns the number of configs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching configs
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CONFIG_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "config.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "config.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(config.uuid IS NULL OR config.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(ConfigModelImpl.ENTITY_CACHE_ENABLED,
			ConfigModelImpl.FINDER_CACHE_ENABLED, ConfigImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			ConfigModelImpl.UUID_COLUMN_BITMASK |
			ConfigModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(ConfigModelImpl.ENTITY_CACHE_ENABLED,
			ConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the config where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchConfigException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching config
	 * @throws NoSuchConfigException if a matching config could not be found
	 */
	@Override
	public Config findByUUID_G(String uuid, long groupId)
		throws NoSuchConfigException {
		Config config = fetchByUUID_G(uuid, groupId);

		if (config == null) {
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

			throw new NoSuchConfigException(msg.toString());
		}

		return config;
	}

	/**
	 * Returns the config where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching config, or <code>null</code> if a matching config could not be found
	 */
	@Override
	public Config fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the config where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching config, or <code>null</code> if a matching config could not be found
	 */
	@Override
	public Config fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof Config) {
			Config config = (Config)result;

			if (!Objects.equals(uuid, config.getUuid()) ||
					(groupId != config.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_CONFIG_WHERE);

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

				List<Config> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					Config config = list.get(0);

					result = config;

					cacheResult(config);

					if ((config.getUuid() == null) ||
							!config.getUuid().equals(uuid) ||
							(config.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, config);
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
			return (Config)result;
		}
	}

	/**
	 * Removes the config where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the config that was removed
	 */
	@Override
	public Config removeByUUID_G(String uuid, long groupId)
		throws NoSuchConfigException {
		Config config = findByUUID_G(uuid, groupId);

		return remove(config);
	}

	/**
	 * Returns the number of configs where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching configs
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CONFIG_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "config.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "config.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(config.uuid IS NULL OR config.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "config.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(ConfigModelImpl.ENTITY_CACHE_ENABLED,
			ConfigModelImpl.FINDER_CACHE_ENABLED, ConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(ConfigModelImpl.ENTITY_CACHE_ENABLED,
			ConfigModelImpl.FINDER_CACHE_ENABLED, ConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			ConfigModelImpl.UUID_COLUMN_BITMASK |
			ConfigModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(ConfigModelImpl.ENTITY_CACHE_ENABLED,
			ConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the configs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching configs
	 */
	@Override
	public List<Config> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the configs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of configs
	 * @param end the upper bound of the range of configs (not inclusive)
	 * @return the range of matching configs
	 */
	@Override
	public List<Config> findByUuid_C(String uuid, long companyId, int start,
		int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the configs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of configs
	 * @param end the upper bound of the range of configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching configs
	 */
	@Override
	public List<Config> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<Config> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the configs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of configs
	 * @param end the upper bound of the range of configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching configs
	 */
	@Override
	public List<Config> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<Config> orderByComparator,
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

		List<Config> list = null;

		if (retrieveFromCache) {
			list = (List<Config>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Config config : list) {
					if (!Objects.equals(uuid, config.getUuid()) ||
							(companyId != config.getCompanyId())) {
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

			query.append(_SQL_SELECT_CONFIG_WHERE);

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
				query.append(ConfigModelImpl.ORDER_BY_JPQL);
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
					list = (List<Config>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Config>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first config in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching config
	 * @throws NoSuchConfigException if a matching config could not be found
	 */
	@Override
	public Config findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Config> orderByComparator)
		throws NoSuchConfigException {
		Config config = fetchByUuid_C_First(uuid, companyId, orderByComparator);

		if (config != null) {
			return config;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchConfigException(msg.toString());
	}

	/**
	 * Returns the first config in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching config, or <code>null</code> if a matching config could not be found
	 */
	@Override
	public Config fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Config> orderByComparator) {
		List<Config> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last config in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching config
	 * @throws NoSuchConfigException if a matching config could not be found
	 */
	@Override
	public Config findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Config> orderByComparator)
		throws NoSuchConfigException {
		Config config = fetchByUuid_C_Last(uuid, companyId, orderByComparator);

		if (config != null) {
			return config;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchConfigException(msg.toString());
	}

	/**
	 * Returns the last config in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching config, or <code>null</code> if a matching config could not be found
	 */
	@Override
	public Config fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Config> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<Config> list = findByUuid_C(uuid, companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the configs before and after the current config in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param configId the primary key of the current config
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next config
	 * @throws NoSuchConfigException if a config with the primary key could not be found
	 */
	@Override
	public Config[] findByUuid_C_PrevAndNext(long configId, String uuid,
		long companyId, OrderByComparator<Config> orderByComparator)
		throws NoSuchConfigException {
		Config config = findByPrimaryKey(configId);

		Session session = null;

		try {
			session = openSession();

			Config[] array = new ConfigImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, config, uuid,
					companyId, orderByComparator, true);

			array[1] = config;

			array[2] = getByUuid_C_PrevAndNext(session, config, uuid,
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

	protected Config getByUuid_C_PrevAndNext(Session session, Config config,
		String uuid, long companyId,
		OrderByComparator<Config> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_CONFIG_WHERE);

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
			query.append(ConfigModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(config);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Config> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the configs where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (Config config : findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(config);
		}
	}

	/**
	 * Returns the number of configs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching configs
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CONFIG_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "config.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "config.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(config.uuid IS NULL OR config.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "config.companyId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_KEYCOMPANYGROUP = new FinderPath(ConfigModelImpl.ENTITY_CACHE_ENABLED,
			ConfigModelImpl.FINDER_CACHE_ENABLED, ConfigImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByKeyCompanyGroup",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			ConfigModelImpl.COMPANYID_COLUMN_BITMASK |
			ConfigModelImpl.GROUPID_COLUMN_BITMASK |
			ConfigModelImpl.CONFIG_KEY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KEYCOMPANYGROUP = new FinderPath(ConfigModelImpl.ENTITY_CACHE_ENABLED,
			ConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByKeyCompanyGroup",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns the config where companyId = &#63; and groupId = &#63; and config_key = &#63; or throws a {@link NoSuchConfigException} if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param config_key the config_key
	 * @return the matching config
	 * @throws NoSuchConfigException if a matching config could not be found
	 */
	@Override
	public Config findByKeyCompanyGroup(long companyId, long groupId,
		String config_key) throws NoSuchConfigException {
		Config config = fetchByKeyCompanyGroup(companyId, groupId, config_key);

		if (config == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append(", config_key=");
			msg.append(config_key);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchConfigException(msg.toString());
		}

		return config;
	}

	/**
	 * Returns the config where companyId = &#63; and groupId = &#63; and config_key = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param config_key the config_key
	 * @return the matching config, or <code>null</code> if a matching config could not be found
	 */
	@Override
	public Config fetchByKeyCompanyGroup(long companyId, long groupId,
		String config_key) {
		return fetchByKeyCompanyGroup(companyId, groupId, config_key, true);
	}

	/**
	 * Returns the config where companyId = &#63; and groupId = &#63; and config_key = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param config_key the config_key
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching config, or <code>null</code> if a matching config could not be found
	 */
	@Override
	public Config fetchByKeyCompanyGroup(long companyId, long groupId,
		String config_key, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { companyId, groupId, config_key };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_KEYCOMPANYGROUP,
					finderArgs, this);
		}

		if (result instanceof Config) {
			Config config = (Config)result;

			if ((companyId != config.getCompanyId()) ||
					(groupId != config.getGroupId()) ||
					!Objects.equals(config_key, config.getConfig_key())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_CONFIG_WHERE);

			query.append(_FINDER_COLUMN_KEYCOMPANYGROUP_COMPANYID_2);

			query.append(_FINDER_COLUMN_KEYCOMPANYGROUP_GROUPID_2);

			boolean bindConfig_key = false;

			if (config_key == null) {
				query.append(_FINDER_COLUMN_KEYCOMPANYGROUP_CONFIG_KEY_1);
			}
			else if (config_key.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_KEYCOMPANYGROUP_CONFIG_KEY_3);
			}
			else {
				bindConfig_key = true;

				query.append(_FINDER_COLUMN_KEYCOMPANYGROUP_CONFIG_KEY_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(groupId);

				if (bindConfig_key) {
					qPos.add(config_key);
				}

				List<Config> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_KEYCOMPANYGROUP,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"ConfigPersistenceImpl.fetchByKeyCompanyGroup(long, long, String, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					Config config = list.get(0);

					result = config;

					cacheResult(config);

					if ((config.getCompanyId() != companyId) ||
							(config.getGroupId() != groupId) ||
							(config.getConfig_key() == null) ||
							!config.getConfig_key().equals(config_key)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_KEYCOMPANYGROUP,
							finderArgs, config);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_KEYCOMPANYGROUP,
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
			return (Config)result;
		}
	}

	/**
	 * Removes the config where companyId = &#63; and groupId = &#63; and config_key = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param config_key the config_key
	 * @return the config that was removed
	 */
	@Override
	public Config removeByKeyCompanyGroup(long companyId, long groupId,
		String config_key) throws NoSuchConfigException {
		Config config = findByKeyCompanyGroup(companyId, groupId, config_key);

		return remove(config);
	}

	/**
	 * Returns the number of configs where companyId = &#63; and groupId = &#63; and config_key = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param config_key the config_key
	 * @return the number of matching configs
	 */
	@Override
	public int countByKeyCompanyGroup(long companyId, long groupId,
		String config_key) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_KEYCOMPANYGROUP;

		Object[] finderArgs = new Object[] { companyId, groupId, config_key };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_CONFIG_WHERE);

			query.append(_FINDER_COLUMN_KEYCOMPANYGROUP_COMPANYID_2);

			query.append(_FINDER_COLUMN_KEYCOMPANYGROUP_GROUPID_2);

			boolean bindConfig_key = false;

			if (config_key == null) {
				query.append(_FINDER_COLUMN_KEYCOMPANYGROUP_CONFIG_KEY_1);
			}
			else if (config_key.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_KEYCOMPANYGROUP_CONFIG_KEY_3);
			}
			else {
				bindConfig_key = true;

				query.append(_FINDER_COLUMN_KEYCOMPANYGROUP_CONFIG_KEY_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(groupId);

				if (bindConfig_key) {
					qPos.add(config_key);
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

	private static final String _FINDER_COLUMN_KEYCOMPANYGROUP_COMPANYID_2 = "config.companyId = ? AND ";
	private static final String _FINDER_COLUMN_KEYCOMPANYGROUP_GROUPID_2 = "config.groupId = ? AND ";
	private static final String _FINDER_COLUMN_KEYCOMPANYGROUP_CONFIG_KEY_1 = "config.config_key IS NULL";
	private static final String _FINDER_COLUMN_KEYCOMPANYGROUP_CONFIG_KEY_2 = "config.config_key = ?";
	private static final String _FINDER_COLUMN_KEYCOMPANYGROUP_CONFIG_KEY_3 = "(config.config_key IS NULL OR config.config_key = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_KEYGLOBALDEFAULT =
		new FinderPath(ConfigModelImpl.ENTITY_CACHE_ENABLED,
			ConfigModelImpl.FINDER_CACHE_ENABLED, ConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByKeyGlobalDefault",
			new String[] {
				String.class.getName(), Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KEYGLOBALDEFAULT =
		new FinderPath(ConfigModelImpl.ENTITY_CACHE_ENABLED,
			ConfigModelImpl.FINDER_CACHE_ENABLED, ConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByKeyGlobalDefault",
			new String[] { String.class.getName(), Boolean.class.getName() },
			ConfigModelImpl.CONFIG_KEY_COLUMN_BITMASK |
			ConfigModelImpl.GLOBALDEFAULT_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KEYGLOBALDEFAULT = new FinderPath(ConfigModelImpl.ENTITY_CACHE_ENABLED,
			ConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByKeyGlobalDefault",
			new String[] { String.class.getName(), Boolean.class.getName() });

	/**
	 * Returns all the configs where config_key = &#63; and globaldefault = &#63;.
	 *
	 * @param config_key the config_key
	 * @param globaldefault the globaldefault
	 * @return the matching configs
	 */
	@Override
	public List<Config> findByKeyGlobalDefault(String config_key,
		boolean globaldefault) {
		return findByKeyGlobalDefault(config_key, globaldefault,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the configs where config_key = &#63; and globaldefault = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param config_key the config_key
	 * @param globaldefault the globaldefault
	 * @param start the lower bound of the range of configs
	 * @param end the upper bound of the range of configs (not inclusive)
	 * @return the range of matching configs
	 */
	@Override
	public List<Config> findByKeyGlobalDefault(String config_key,
		boolean globaldefault, int start, int end) {
		return findByKeyGlobalDefault(config_key, globaldefault, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the configs where config_key = &#63; and globaldefault = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param config_key the config_key
	 * @param globaldefault the globaldefault
	 * @param start the lower bound of the range of configs
	 * @param end the upper bound of the range of configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching configs
	 */
	@Override
	public List<Config> findByKeyGlobalDefault(String config_key,
		boolean globaldefault, int start, int end,
		OrderByComparator<Config> orderByComparator) {
		return findByKeyGlobalDefault(config_key, globaldefault, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the configs where config_key = &#63; and globaldefault = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param config_key the config_key
	 * @param globaldefault the globaldefault
	 * @param start the lower bound of the range of configs
	 * @param end the upper bound of the range of configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching configs
	 */
	@Override
	public List<Config> findByKeyGlobalDefault(String config_key,
		boolean globaldefault, int start, int end,
		OrderByComparator<Config> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KEYGLOBALDEFAULT;
			finderArgs = new Object[] { config_key, globaldefault };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_KEYGLOBALDEFAULT;
			finderArgs = new Object[] {
					config_key, globaldefault,
					
					start, end, orderByComparator
				};
		}

		List<Config> list = null;

		if (retrieveFromCache) {
			list = (List<Config>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Config config : list) {
					if (!Objects.equals(config_key, config.getConfig_key()) ||
							(globaldefault != config.getGlobaldefault())) {
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

			query.append(_SQL_SELECT_CONFIG_WHERE);

			boolean bindConfig_key = false;

			if (config_key == null) {
				query.append(_FINDER_COLUMN_KEYGLOBALDEFAULT_CONFIG_KEY_1);
			}
			else if (config_key.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_KEYGLOBALDEFAULT_CONFIG_KEY_3);
			}
			else {
				bindConfig_key = true;

				query.append(_FINDER_COLUMN_KEYGLOBALDEFAULT_CONFIG_KEY_2);
			}

			query.append(_FINDER_COLUMN_KEYGLOBALDEFAULT_GLOBALDEFAULT_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ConfigModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindConfig_key) {
					qPos.add(config_key);
				}

				qPos.add(globaldefault);

				if (!pagination) {
					list = (List<Config>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Config>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first config in the ordered set where config_key = &#63; and globaldefault = &#63;.
	 *
	 * @param config_key the config_key
	 * @param globaldefault the globaldefault
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching config
	 * @throws NoSuchConfigException if a matching config could not be found
	 */
	@Override
	public Config findByKeyGlobalDefault_First(String config_key,
		boolean globaldefault, OrderByComparator<Config> orderByComparator)
		throws NoSuchConfigException {
		Config config = fetchByKeyGlobalDefault_First(config_key,
				globaldefault, orderByComparator);

		if (config != null) {
			return config;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("config_key=");
		msg.append(config_key);

		msg.append(", globaldefault=");
		msg.append(globaldefault);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchConfigException(msg.toString());
	}

	/**
	 * Returns the first config in the ordered set where config_key = &#63; and globaldefault = &#63;.
	 *
	 * @param config_key the config_key
	 * @param globaldefault the globaldefault
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching config, or <code>null</code> if a matching config could not be found
	 */
	@Override
	public Config fetchByKeyGlobalDefault_First(String config_key,
		boolean globaldefault, OrderByComparator<Config> orderByComparator) {
		List<Config> list = findByKeyGlobalDefault(config_key, globaldefault,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last config in the ordered set where config_key = &#63; and globaldefault = &#63;.
	 *
	 * @param config_key the config_key
	 * @param globaldefault the globaldefault
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching config
	 * @throws NoSuchConfigException if a matching config could not be found
	 */
	@Override
	public Config findByKeyGlobalDefault_Last(String config_key,
		boolean globaldefault, OrderByComparator<Config> orderByComparator)
		throws NoSuchConfigException {
		Config config = fetchByKeyGlobalDefault_Last(config_key, globaldefault,
				orderByComparator);

		if (config != null) {
			return config;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("config_key=");
		msg.append(config_key);

		msg.append(", globaldefault=");
		msg.append(globaldefault);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchConfigException(msg.toString());
	}

	/**
	 * Returns the last config in the ordered set where config_key = &#63; and globaldefault = &#63;.
	 *
	 * @param config_key the config_key
	 * @param globaldefault the globaldefault
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching config, or <code>null</code> if a matching config could not be found
	 */
	@Override
	public Config fetchByKeyGlobalDefault_Last(String config_key,
		boolean globaldefault, OrderByComparator<Config> orderByComparator) {
		int count = countByKeyGlobalDefault(config_key, globaldefault);

		if (count == 0) {
			return null;
		}

		List<Config> list = findByKeyGlobalDefault(config_key, globaldefault,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the configs before and after the current config in the ordered set where config_key = &#63; and globaldefault = &#63;.
	 *
	 * @param configId the primary key of the current config
	 * @param config_key the config_key
	 * @param globaldefault the globaldefault
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next config
	 * @throws NoSuchConfigException if a config with the primary key could not be found
	 */
	@Override
	public Config[] findByKeyGlobalDefault_PrevAndNext(long configId,
		String config_key, boolean globaldefault,
		OrderByComparator<Config> orderByComparator)
		throws NoSuchConfigException {
		Config config = findByPrimaryKey(configId);

		Session session = null;

		try {
			session = openSession();

			Config[] array = new ConfigImpl[3];

			array[0] = getByKeyGlobalDefault_PrevAndNext(session, config,
					config_key, globaldefault, orderByComparator, true);

			array[1] = config;

			array[2] = getByKeyGlobalDefault_PrevAndNext(session, config,
					config_key, globaldefault, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Config getByKeyGlobalDefault_PrevAndNext(Session session,
		Config config, String config_key, boolean globaldefault,
		OrderByComparator<Config> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_CONFIG_WHERE);

		boolean bindConfig_key = false;

		if (config_key == null) {
			query.append(_FINDER_COLUMN_KEYGLOBALDEFAULT_CONFIG_KEY_1);
		}
		else if (config_key.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_KEYGLOBALDEFAULT_CONFIG_KEY_3);
		}
		else {
			bindConfig_key = true;

			query.append(_FINDER_COLUMN_KEYGLOBALDEFAULT_CONFIG_KEY_2);
		}

		query.append(_FINDER_COLUMN_KEYGLOBALDEFAULT_GLOBALDEFAULT_2);

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
			query.append(ConfigModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindConfig_key) {
			qPos.add(config_key);
		}

		qPos.add(globaldefault);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(config);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Config> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the configs where config_key = &#63; and globaldefault = &#63; from the database.
	 *
	 * @param config_key the config_key
	 * @param globaldefault the globaldefault
	 */
	@Override
	public void removeByKeyGlobalDefault(String config_key,
		boolean globaldefault) {
		for (Config config : findByKeyGlobalDefault(config_key, globaldefault,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(config);
		}
	}

	/**
	 * Returns the number of configs where config_key = &#63; and globaldefault = &#63;.
	 *
	 * @param config_key the config_key
	 * @param globaldefault the globaldefault
	 * @return the number of matching configs
	 */
	@Override
	public int countByKeyGlobalDefault(String config_key, boolean globaldefault) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_KEYGLOBALDEFAULT;

		Object[] finderArgs = new Object[] { config_key, globaldefault };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CONFIG_WHERE);

			boolean bindConfig_key = false;

			if (config_key == null) {
				query.append(_FINDER_COLUMN_KEYGLOBALDEFAULT_CONFIG_KEY_1);
			}
			else if (config_key.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_KEYGLOBALDEFAULT_CONFIG_KEY_3);
			}
			else {
				bindConfig_key = true;

				query.append(_FINDER_COLUMN_KEYGLOBALDEFAULT_CONFIG_KEY_2);
			}

			query.append(_FINDER_COLUMN_KEYGLOBALDEFAULT_GLOBALDEFAULT_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindConfig_key) {
					qPos.add(config_key);
				}

				qPos.add(globaldefault);

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

	private static final String _FINDER_COLUMN_KEYGLOBALDEFAULT_CONFIG_KEY_1 = "config.config_key IS NULL AND ";
	private static final String _FINDER_COLUMN_KEYGLOBALDEFAULT_CONFIG_KEY_2 = "config.config_key = ? AND ";
	private static final String _FINDER_COLUMN_KEYGLOBALDEFAULT_CONFIG_KEY_3 = "(config.config_key IS NULL OR config.config_key = '') AND ";
	private static final String _FINDER_COLUMN_KEYGLOBALDEFAULT_GLOBALDEFAULT_2 = "config.globaldefault = ?";

	public ConfigPersistenceImpl() {
		setModelClass(Config.class);
	}

	/**
	 * Caches the config in the entity cache if it is enabled.
	 *
	 * @param config the config
	 */
	@Override
	public void cacheResult(Config config) {
		entityCache.putResult(ConfigModelImpl.ENTITY_CACHE_ENABLED,
			ConfigImpl.class, config.getPrimaryKey(), config);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { config.getUuid(), config.getGroupId() }, config);

		finderCache.putResult(FINDER_PATH_FETCH_BY_KEYCOMPANYGROUP,
			new Object[] {
				config.getCompanyId(), config.getGroupId(),
				config.getConfig_key()
			}, config);

		config.resetOriginalValues();
	}

	/**
	 * Caches the configs in the entity cache if it is enabled.
	 *
	 * @param configs the configs
	 */
	@Override
	public void cacheResult(List<Config> configs) {
		for (Config config : configs) {
			if (entityCache.getResult(ConfigModelImpl.ENTITY_CACHE_ENABLED,
						ConfigImpl.class, config.getPrimaryKey()) == null) {
				cacheResult(config);
			}
			else {
				config.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all configs.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ConfigImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the config.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Config config) {
		entityCache.removeResult(ConfigModelImpl.ENTITY_CACHE_ENABLED,
			ConfigImpl.class, config.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ConfigModelImpl)config);
	}

	@Override
	public void clearCache(List<Config> configs) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Config config : configs) {
			entityCache.removeResult(ConfigModelImpl.ENTITY_CACHE_ENABLED,
				ConfigImpl.class, config.getPrimaryKey());

			clearUniqueFindersCache((ConfigModelImpl)config);
		}
	}

	protected void cacheUniqueFindersCache(ConfigModelImpl configModelImpl,
		boolean isNew) {
		if (isNew) {
			Object[] args = new Object[] {
					configModelImpl.getUuid(), configModelImpl.getGroupId()
				};

			finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
				Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
				configModelImpl);

			args = new Object[] {
					configModelImpl.getCompanyId(), configModelImpl.getGroupId(),
					configModelImpl.getConfig_key()
				};

			finderCache.putResult(FINDER_PATH_COUNT_BY_KEYCOMPANYGROUP, args,
				Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_KEYCOMPANYGROUP, args,
				configModelImpl);
		}
		else {
			if ((configModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						configModelImpl.getUuid(), configModelImpl.getGroupId()
					};

				finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
					Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
					configModelImpl);
			}

			if ((configModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_KEYCOMPANYGROUP.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						configModelImpl.getCompanyId(),
						configModelImpl.getGroupId(),
						configModelImpl.getConfig_key()
					};

				finderCache.putResult(FINDER_PATH_COUNT_BY_KEYCOMPANYGROUP,
					args, Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_KEYCOMPANYGROUP,
					args, configModelImpl);
			}
		}
	}

	protected void clearUniqueFindersCache(ConfigModelImpl configModelImpl) {
		Object[] args = new Object[] {
				configModelImpl.getUuid(), configModelImpl.getGroupId()
			};

		finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

		if ((configModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			args = new Object[] {
					configModelImpl.getOriginalUuid(),
					configModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		args = new Object[] {
				configModelImpl.getCompanyId(), configModelImpl.getGroupId(),
				configModelImpl.getConfig_key()
			};

		finderCache.removeResult(FINDER_PATH_COUNT_BY_KEYCOMPANYGROUP, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_KEYCOMPANYGROUP, args);

		if ((configModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_KEYCOMPANYGROUP.getColumnBitmask()) != 0) {
			args = new Object[] {
					configModelImpl.getOriginalCompanyId(),
					configModelImpl.getOriginalGroupId(),
					configModelImpl.getOriginalConfig_key()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_KEYCOMPANYGROUP, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_KEYCOMPANYGROUP, args);
		}
	}

	/**
	 * Creates a new config with the primary key. Does not add the config to the database.
	 *
	 * @param configId the primary key for the new config
	 * @return the new config
	 */
	@Override
	public Config create(long configId) {
		Config config = new ConfigImpl();

		config.setNew(true);
		config.setPrimaryKey(configId);

		String uuid = PortalUUIDUtil.generate();

		config.setUuid(uuid);

		config.setCompanyId(companyProvider.getCompanyId());

		return config;
	}

	/**
	 * Removes the config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param configId the primary key of the config
	 * @return the config that was removed
	 * @throws NoSuchConfigException if a config with the primary key could not be found
	 */
	@Override
	public Config remove(long configId) throws NoSuchConfigException {
		return remove((Serializable)configId);
	}

	/**
	 * Removes the config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the config
	 * @return the config that was removed
	 * @throws NoSuchConfigException if a config with the primary key could not be found
	 */
	@Override
	public Config remove(Serializable primaryKey) throws NoSuchConfigException {
		Session session = null;

		try {
			session = openSession();

			Config config = (Config)session.get(ConfigImpl.class, primaryKey);

			if (config == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchConfigException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(config);
		}
		catch (NoSuchConfigException nsee) {
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
	protected Config removeImpl(Config config) {
		config = toUnwrappedModel(config);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(config)) {
				config = (Config)session.get(ConfigImpl.class,
						config.getPrimaryKeyObj());
			}

			if (config != null) {
				session.delete(config);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (config != null) {
			clearCache(config);
		}

		return config;
	}

	@Override
	public Config updateImpl(Config config) {
		config = toUnwrappedModel(config);

		boolean isNew = config.isNew();

		ConfigModelImpl configModelImpl = (ConfigModelImpl)config;

		if (Validator.isNull(config.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			config.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (config.getCreateDate() == null)) {
			if (serviceContext == null) {
				config.setCreateDate(now);
			}
			else {
				config.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!configModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				config.setModifiedDate(now);
			}
			else {
				config.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (config.isNew()) {
				session.save(config);

				config.setNew(false);
			}
			else {
				config = (Config)session.merge(config);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !ConfigModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((configModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { configModelImpl.getOriginalUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { configModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((configModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						configModelImpl.getOriginalUuid(),
						configModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						configModelImpl.getUuid(),
						configModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((configModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KEYGLOBALDEFAULT.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						configModelImpl.getOriginalConfig_key(),
						configModelImpl.getOriginalGlobaldefault()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_KEYGLOBALDEFAULT,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KEYGLOBALDEFAULT,
					args);

				args = new Object[] {
						configModelImpl.getConfig_key(),
						configModelImpl.getGlobaldefault()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_KEYGLOBALDEFAULT,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KEYGLOBALDEFAULT,
					args);
			}
		}

		entityCache.putResult(ConfigModelImpl.ENTITY_CACHE_ENABLED,
			ConfigImpl.class, config.getPrimaryKey(), config, false);

		clearUniqueFindersCache(configModelImpl);
		cacheUniqueFindersCache(configModelImpl, isNew);

		config.resetOriginalValues();

		return config;
	}

	protected Config toUnwrappedModel(Config config) {
		if (config instanceof ConfigImpl) {
			return config;
		}

		ConfigImpl configImpl = new ConfigImpl();

		configImpl.setNew(config.isNew());
		configImpl.setPrimaryKey(config.getPrimaryKey());

		configImpl.setUuid(config.getUuid());
		configImpl.setConfigId(config.getConfigId());
		configImpl.setGroupId(config.getGroupId());
		configImpl.setCompanyId(config.getCompanyId());
		configImpl.setName(config.getName());
		configImpl.setValue(config.getValue());
		configImpl.setCreateDate(config.getCreateDate());
		configImpl.setModifiedDate(config.getModifiedDate());
		configImpl.setGlobaldefault(config.isGlobaldefault());
		configImpl.setConfig_key(config.getConfig_key());
		configImpl.setDescription(config.getDescription());

		return configImpl;
	}

	/**
	 * Returns the config with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the config
	 * @return the config
	 * @throws NoSuchConfigException if a config with the primary key could not be found
	 */
	@Override
	public Config findByPrimaryKey(Serializable primaryKey)
		throws NoSuchConfigException {
		Config config = fetchByPrimaryKey(primaryKey);

		if (config == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchConfigException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return config;
	}

	/**
	 * Returns the config with the primary key or throws a {@link NoSuchConfigException} if it could not be found.
	 *
	 * @param configId the primary key of the config
	 * @return the config
	 * @throws NoSuchConfigException if a config with the primary key could not be found
	 */
	@Override
	public Config findByPrimaryKey(long configId) throws NoSuchConfigException {
		return findByPrimaryKey((Serializable)configId);
	}

	/**
	 * Returns the config with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the config
	 * @return the config, or <code>null</code> if a config with the primary key could not be found
	 */
	@Override
	public Config fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ConfigModelImpl.ENTITY_CACHE_ENABLED,
				ConfigImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Config config = (Config)serializable;

		if (config == null) {
			Session session = null;

			try {
				session = openSession();

				config = (Config)session.get(ConfigImpl.class, primaryKey);

				if (config != null) {
					cacheResult(config);
				}
				else {
					entityCache.putResult(ConfigModelImpl.ENTITY_CACHE_ENABLED,
						ConfigImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ConfigModelImpl.ENTITY_CACHE_ENABLED,
					ConfigImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return config;
	}

	/**
	 * Returns the config with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param configId the primary key of the config
	 * @return the config, or <code>null</code> if a config with the primary key could not be found
	 */
	@Override
	public Config fetchByPrimaryKey(long configId) {
		return fetchByPrimaryKey((Serializable)configId);
	}

	@Override
	public Map<Serializable, Config> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Config> map = new HashMap<Serializable, Config>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Config config = fetchByPrimaryKey(primaryKey);

			if (config != null) {
				map.put(primaryKey, config);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ConfigModelImpl.ENTITY_CACHE_ENABLED,
					ConfigImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Config)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CONFIG_WHERE_PKS_IN);

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

			for (Config config : (List<Config>)q.list()) {
				map.put(config.getPrimaryKeyObj(), config);

				cacheResult(config);

				uncachedPrimaryKeys.remove(config.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ConfigModelImpl.ENTITY_CACHE_ENABLED,
					ConfigImpl.class, primaryKey, nullModel);
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
	 * Returns all the configs.
	 *
	 * @return the configs
	 */
	@Override
	public List<Config> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of configs
	 * @param end the upper bound of the range of configs (not inclusive)
	 * @return the range of configs
	 */
	@Override
	public List<Config> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of configs
	 * @param end the upper bound of the range of configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of configs
	 */
	@Override
	public List<Config> findAll(int start, int end,
		OrderByComparator<Config> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of configs
	 * @param end the upper bound of the range of configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of configs
	 */
	@Override
	public List<Config> findAll(int start, int end,
		OrderByComparator<Config> orderByComparator, boolean retrieveFromCache) {
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

		List<Config> list = null;

		if (retrieveFromCache) {
			list = (List<Config>)finderCache.getResult(finderPath, finderArgs,
					this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CONFIG);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CONFIG;

				if (pagination) {
					sql = sql.concat(ConfigModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Config>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Config>)QueryUtil.list(q, getDialect(), start,
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
	 * Removes all the configs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Config config : findAll()) {
			remove(config);
		}
	}

	/**
	 * Returns the number of configs.
	 *
	 * @return the number of configs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CONFIG);

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
		return ConfigModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the config persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ConfigImpl.class.getName());
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
	private static final String _SQL_SELECT_CONFIG = "SELECT config FROM Config config";
	private static final String _SQL_SELECT_CONFIG_WHERE_PKS_IN = "SELECT config FROM Config config WHERE configId IN (";
	private static final String _SQL_SELECT_CONFIG_WHERE = "SELECT config FROM Config config WHERE ";
	private static final String _SQL_COUNT_CONFIG = "SELECT COUNT(config) FROM Config config";
	private static final String _SQL_COUNT_CONFIG_WHERE = "SELECT COUNT(config) FROM Config config WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "config.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Config exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Config exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ConfigPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}