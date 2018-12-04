//package cn.aorise.common.core.module.multilang;
//
//import android.content.Context;
//
//import org.greenrobot.greendao.AbstractDao;
//
//import cn.aorise.common.core.module.database.DBManager;
//import cn.aorise.common.core.module.multilang.entity.LangEntity;
//
///**
// * <pre>
// *     author : tangjy
// *     e-mail : jianye.tang@aorise.org
// *     time   : 2017/03/17
// *     desc   : 多语言支持数据库操作类
// *     version: 1.0
// * </pre>
// */
//public class LangDbHelper {
//    private static final String DB_NAME = "lang.db";
//    private static LangDbHelper sInstance;
//    private DaoMaster.DevOpenHelper mHelper;
//    private DaoMaster mDaoMaster;
//    private DaoSession mDaoSession;
//    private static DBManager<LangEntity, Long> mLangEntityDao;
//
//    private LangDbHelper() {
//    }
//
//    public static LangDbHelper getInstance() {
//        if (sInstance == null) {
//            synchronized (LangDbHelper.class) {
//                if (sInstance == null) {
//                    sInstance = new LangDbHelper();
//                }
//            }
//        }
//        return sInstance;
//    }
//
//    public void init(Context context) {
//        init(context, DB_NAME);
//    }
//
//    public void init(Context context, String dbName) {
//        mHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
//        mDaoMaster = new DaoMaster(mHelper.getWritableDatabase());
//        mDaoSession = mDaoMaster.newSession();
//    }
//
//    public DaoMaster getDaoMaster() {
//        return mDaoMaster;
//    }
//
//    public DaoSession getDaoSession() {
//        return mDaoSession;
//    }
//
//    private void clear() {
//        if (mDaoSession != null) {
//            mDaoSession.clear();
//            mDaoSession = null;
//        }
//    }
//
//    public void close() {
//        clear();
//        if (mHelper != null) {
//            mHelper.close();
//            mHelper = null;
//        }
//    }
//
//    public DBManager<LangEntity, Long> getLangEntityDao() {
//        if (mLangEntityDao == null) {
//            mLangEntityDao = new DBManager<LangEntity, Long>() {
//                @Override
//                public AbstractDao<LangEntity, Long> getAbstractDao() {
//                    return mDaoSession.getLangEntityDao();
//                }
//            };
//        }
//        return mLangEntityDao;
//    }
//}
