package com.example.zulfikaranshari.zulfikaransharioktafinawan_1202154136_modul5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zulfikaranshari on 24/03/2018.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME="TODOList";
    private static final int DB_VER = 1;
    public static final String DB_TABLE="todo";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_PRIORITY = "priority";
    public static final String CREATE_DB = "create table "+DB_TABLE+"(" +COLUMN_ID+" integer primary key, "+COLUMN_NAME+" text, "+COLUMN_DESCRIPTION+" text, "+COLUMN_PRIORITY+" integer)";
    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DB_TABLE);
    }

    public boolean insertData(String name, String desc, int priority){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_DESCRIPTION, desc);
        values.put(COLUMN_PRIORITY, priority);
        long result = db.insert(DB_TABLE,null,values);
        if (result==-1){
            return false;
        }else{
            return true;
        }
    }

    public boolean deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DB_TABLE, COLUMN_ID+" = ?", new String[] {id});
        return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select * from "+DB_TABLE,null);
        return c;
    }
}
