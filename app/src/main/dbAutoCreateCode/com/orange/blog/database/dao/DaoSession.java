package com.orange.blog.database.dao;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.orange.blog.database.bean.UserFriends;

import com.orange.blog.database.dao.UserFriendsDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig userFriendsDaoConfig;

    private final UserFriendsDao userFriendsDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        userFriendsDaoConfig = daoConfigMap.get(UserFriendsDao.class).clone();
        userFriendsDaoConfig.initIdentityScope(type);

        userFriendsDao = new UserFriendsDao(userFriendsDaoConfig, this);

        registerDao(UserFriends.class, userFriendsDao);
    }
    
    public void clear() {
        userFriendsDaoConfig.getIdentityScope().clear();
    }

    public UserFriendsDao getUserFriendsDao() {
        return userFriendsDao;
    }

}
