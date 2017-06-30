package youtu.android601.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.greendao.gen.BleBeanDao;
import com.greendao.gen.DaoMaster;
import com.greendao.gen.DaoSession;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by djf on 2017/6/6.
 */

public class DBManager {
    private final static String dbName = "test_db";
    private static DBManager mInstance;
    private DaoMaster.DevOpenHelper openHelper;
    private Context context;

    private SQLiteDatabase mReadableDatabase;
    private SQLiteDatabase mWritableDatabase;
    private DaoMaster mReadableDaoMaster;
    private DaoMaster mWritableDaoMaster;
    private DaoSession mReadableDaoSession;
    private DaoSession mWritableDaoSession;
    private BleBeanDao mReadableBleBeanDao;
    private BleBeanDao mWritableBleBeanDao;

    public DBManager(Context context) {
        this.context = context;
        openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
    }

    /**
     * 获取单例引用
     *
     * @param context
     * @return
     */
    public static DBManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (DBManager.class) {
                if (mInstance == null) {
                    mInstance = new DBManager(context);
                }
            }
        }
        return mInstance;
    }

    /**
     * 获取可读数据库
     */
    private SQLiteDatabase getReadableDatabase() {
        if (mReadableDatabase == null) {
            if (openHelper == null) {
                openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
            }
            mReadableDatabase = openHelper.getReadableDatabase();
        }
        return mReadableDatabase;
    }

    /**
     * 获取可写数据库
     */
    private SQLiteDatabase getWritableDatabase() {
        if (mWritableDatabase == null) {
            if (openHelper == null) {
                openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
            }
            mWritableDatabase = openHelper.getWritableDatabase();
        }
        return mWritableDatabase;
    }

    private DaoMaster getDaoMaster(@NonNull SQLiteDatabase db) {
        return new DaoMaster(db);
    }

    private DaoSession getDaoSession(@NonNull DaoMaster master) {
        return master.newSession();
    }

    private BleBeanDao getBleBeanDao(@NonNull DaoSession session) {
        return session.getBleBeanDao();
    }

    private DaoMaster getmWritableDaoMaster(boolean isWrite) {
        if (isWrite) {
            if (mWritableDaoMaster == null) {
                mWritableDaoMaster = getDaoMaster(getWritableDatabase());
            }
            return mWritableDaoMaster;
        } else {
            if (mReadableDaoMaster == null) {
                mReadableDaoMaster = getDaoMaster(getReadableDatabase());
            }
            return mReadableDaoMaster;
        }
    }

    private DaoSession getmWritableDaoSession(boolean isWrite) {
        if (isWrite) {
            if (mWritableDaoSession == null) {
                mWritableDaoSession = getDaoSession(getmWritableDaoMaster(isWrite));
            }
            return mWritableDaoSession;
        } else {
            if (mReadableDaoSession == null) {
                mReadableDaoSession = getDaoSession(getmWritableDaoMaster(!isWrite));
            }
            return mReadableDaoSession;
        }
    }

    private BleBeanDao getmWritableDaoBleBeanDao(boolean isWrite) {
        if (isWrite) {
            if (mWritableBleBeanDao == null) {
                mWritableBleBeanDao = getBleBeanDao(getmWritableDaoSession(isWrite));
            }
            return mWritableBleBeanDao;
        } else {
            if (mReadableBleBeanDao == null) {
                mReadableBleBeanDao = getBleBeanDao(getmWritableDaoSession(!isWrite));
            }
            return mReadableBleBeanDao;
        }
    }

    /**
     * 插入一条记录
     *
     * @param user
     */
    public void insertUser(BleBean user) {
        if (queryUserList(user.getMac()) != null && queryUserList(user.getMac()).size() > 0) {
            updateUserType(user.getMac(), user.getType());
        } else
            getmWritableDaoBleBeanDao(true).insert(user);
    }

    /**
     * 插入集合
     *
     * @param users
     */
    public void insertUserList(List<BleBean> users) {
        if (users == null || users.isEmpty()) {
            return;
        }
        getmWritableDaoBleBeanDao(true).insertInTx(users);
    }

    /**
     * 删除一条记录
     *
     * @param user
     */
    public void deleteUser(BleBean user) {
        getmWritableDaoBleBeanDao(true).delete(user);
    }

    /**
     * 删除一条
     *
     * @param id
     */
    public void deleteUserById(Long id) {
        getmWritableDaoBleBeanDao(true).deleteByKey(id);
    }

    /**
     * 根据mac删除一条记录
     *
     * @param mac
     */
    public void deleteUserByMac(String mac) {
        BleBean findUser = getmWritableDaoBleBeanDao(true).queryBuilder().where(BleBeanDao
                .Properties.Mac.eq(mac)).build().unique();
        if (findUser != null) {
            getmWritableDaoBleBeanDao(true).deleteByKey(findUser.getId());
        }
    }

    /**
     * 更新一条记录
     *
     * @param user
     */
    public void updateUser(BleBean user) {
        if (user.getId() != null)
            getmWritableDaoBleBeanDao(true).update(user);
    }

    public void updateUserType(String mac, int type) {
        BleBean bleBean = getmWritableDaoBleBeanDao(true).queryBuilder().where(BleBeanDao
                .Properties.Mac.eq(mac)).build().unique();
        if (bleBean != null) {
            bleBean.setType(type);
            updateUser(bleBean);
        }

    }

    /**
     * 查询列表
     */
    public List<BleBean> queryUserList() {
        QueryBuilder<BleBean> qb = getmWritableDaoBleBeanDao(false).queryBuilder();
        List<BleBean> list = qb.list();
        return list;
    }

    /**
     * 查询列表
     */
    public List<BleBean> queryUserList(int id) {
        QueryBuilder<BleBean> qb = getmWritableDaoBleBeanDao(false).queryBuilder();
        qb.where(BleBeanDao.Properties.Id.gt(id)).orderAsc(BleBeanDao.Properties.Id);
        List<BleBean> list = qb.list();
        return list;
    }

    /**
     * 查询列表
     */
    public List<BleBean> queryUserList(String mac) {
        QueryBuilder<BleBean> qb = getmWritableDaoBleBeanDao(false).queryBuilder();
        qb.where(BleBeanDao.Properties.Mac.eq(mac));
        List<BleBean> list = qb.list();
        return list;
    }
}
