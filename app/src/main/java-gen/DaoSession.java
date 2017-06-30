package ;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import youtu.android601.db.BleBean;

import .BleBeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig bleBeanDaoConfig;

    private final BleBeanDao bleBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        bleBeanDaoConfig = daoConfigMap.get(BleBeanDao.class).clone();
        bleBeanDaoConfig.initIdentityScope(type);

        bleBeanDao = new BleBeanDao(bleBeanDaoConfig, this);

        registerDao(BleBean.class, bleBeanDao);
    }
    
    public void clear() {
        bleBeanDaoConfig.getIdentityScope().clear();
    }

    public BleBeanDao getBleBeanDao() {
        return bleBeanDao;
    }

}
