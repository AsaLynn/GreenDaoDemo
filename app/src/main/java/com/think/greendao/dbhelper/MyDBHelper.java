package com.think.greendao.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.think.greendao.db.DaoMaster;

/**
 * Created by think on 2017/12/2.
 */

public class MyDBHelper extends DaoMaster.OpenHelper {

    public static final String DBNAME = "student.db";

    public MyDBHelper(Context context) {
        super(context, DBNAME);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        //在onUpgrade方法中进行数据库的迁移
    }
}
