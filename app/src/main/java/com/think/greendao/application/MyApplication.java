package com.think.greendao.application;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.think.greendao.db.DaoMaster;
import com.think.greendao.db.DaoSession;
import com.think.greendao.db.StudentDao;


/**
 * Created by think on 2017/12/2.
 */

public class MyApplication extends Application {

    private static MyApplication mApp;
    private SQLiteDatabase database;
    private DaoSession daoSession;
    private StudentDao studentDao;

    public StudentDao getStudentDao() {
        return studentDao;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        initDB();
    }

    private void initDB() {
        DaoMaster.DevOpenHelper devOpenHelper
                = new DaoMaster.DevOpenHelper(this, "student-db", null);
        database = devOpenHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
        studentDao = daoSession.getStudentDao();

        //自定义DBHelper的方式,进行初始化.
//        MyDBHelper dbHelper = new MyDBHelper(this);
//        DaoMaster master = new DaoMaster(dbHelper.getWritableDatabase());
//        StudentDao dao = master.newSession().getStudentDao();
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public static MyApplication getInstance(){
        return mApp;
    }
}
