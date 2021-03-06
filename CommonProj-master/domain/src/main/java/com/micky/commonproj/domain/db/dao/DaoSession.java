package com.micky.commonproj.domain.db.dao;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.micky.commonproj.domain.model.IpInfo;
import com.micky.commonproj.domain.model.Place;
import com.micky.commonproj.domain.model.ChannelItem;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig ipInfoDaoConfig;
    private final DaoConfig placeDaoConfig;
    private final DaoConfig channelItemDaoConfig;

    private final IpInfoDao ipInfoDao;
    private final PlaceDao placeDao;
    private final ChannelItemDao channelItemDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        ipInfoDaoConfig = daoConfigMap.get(IpInfoDao.class).clone();
        ipInfoDaoConfig.initIdentityScope(type);

        placeDaoConfig = daoConfigMap.get(PlaceDao.class).clone();
        placeDaoConfig.initIdentityScope(type);

        channelItemDaoConfig = daoConfigMap.get(ChannelItemDao.class).clone();
        channelItemDaoConfig.initIdentityScope(type);

        ipInfoDao = new IpInfoDao(ipInfoDaoConfig, this);
        placeDao = new PlaceDao(placeDaoConfig, this);
        channelItemDao = new ChannelItemDao(channelItemDaoConfig, this);

        registerDao(IpInfo.class, ipInfoDao);
        registerDao(Place.class, placeDao);
        registerDao(ChannelItem.class, channelItemDao);
    }
    
    public void clear() {
        ipInfoDaoConfig.getIdentityScope().clear();
        placeDaoConfig.getIdentityScope().clear();
        channelItemDaoConfig.getIdentityScope().clear();
    }

    public IpInfoDao getIpInfoDao() {
        return ipInfoDao;
    }

    public PlaceDao getPlaceDao() {
        return placeDao;
    }

    public ChannelItemDao getChannelItemDao() {
        return channelItemDao;
    }

}
