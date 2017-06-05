package com.yishu.greendao;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.yishu.greendao.db.DaoMaster;
import com.yishu.greendao.db.DaoSession;

/**
 * Created by simon on 17/6/5.
 */

public class GreenDaoApplication extends Application {
    private static GreenDaoApplication instances;
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster daoMaster;

    public static GreenDaoApplication getInstances(){
        return instances;
    }
    public SQLiteDatabase getDb() {
        return db;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        instances = this;
        setDatabase();
    }

    private void setDatabase() {
        mHelper = new DaoMaster.DevOpenHelper(this, "green");
        db = mHelper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();

    }
}
