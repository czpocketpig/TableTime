package cz.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.List;

import cz.bean.LocalUser;

import static android.content.ContentValues.TAG;

/**
 * Created by chenzhang on 2017/4/8.
 */

public class DBManager {
    private MyDBhelper helper;
    private SQLiteDatabase db;

    public DBManager(Context context) {
        helper = new MyDBhelper(context);
        //因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0, mFactory);
        //所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
        db = helper.getWritableDatabase();
    }

    public void add(List<LocalUser> users) {
        db.beginTransaction();  //开始事务
        try {
            for (LocalUser student : users) {

                db.execSQL("INSERT INTO localuser VALUES(?, ?, ?, ?)"
                        , new Object[]{student.object_id, student.student_id ,student.password,student.stu_name});
                Log.d(TAG, "add: success ");
            }
            db.setTransactionSuccessful();  //设置事务成功完成

        } finally {
            db.endTransaction();    //结束事务
        }
    }



    public void closeDB() {
        db.close();
    }


}
