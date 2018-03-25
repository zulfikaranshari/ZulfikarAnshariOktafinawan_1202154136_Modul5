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

    //variable String yang berisikan nama DB
    private static final String DB_NAME="TODOList";
    private static final int DB_VER = 1;

    //variable String yang berisikan nama table
    public static final String DB_TABLE="todo";

    //variable query untuk memberikan nama kolom
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";

    public static final String COLUMN_PRIORITY = "priority";

    //variable query untuk membuat DB
    public static final String CREATE_DB = "create table "+DB_TABLE+"("
            +COLUMN_ID+" integer primary key autoincrement, " +
            ""+COLUMN_NAME+" text, " +
            ""+COLUMN_DESCRIPTION+" text, " +
            ""+COLUMN_PRIORITY+" text)";
    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //mengeksekusi query dan membuat DB beseta tablenya
        sqLiteDatabase.execSQL(CREATE_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DB_TABLE);
    }

    public boolean insertData(String name, String desc, String priority){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //memasukkan setiap nilai kedalam masing2 kolom
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_DESCRIPTION, desc);
        values.put(COLUMN_PRIORITY, priority);

        //mengecek apakah input data berhasil atau tidak
        long result = db.insert(DB_TABLE,null,values);
        if (result==-1){
            return false;
        }else{
            return true;
        }
    }

    public void deleteData(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        //query untuk menghapus data
        String q = "DELETE FROM " + DB_TABLE + " WHERE " + COLUMN_ID + " = '" + id + "'";
        //query untuk eksekusi pengahapusan data
        db.execSQL(q);
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        //query untuk mendapatkan semua data dari Table
        String query = "select * from "+DB_TABLE;

        //mengembalikan data kepada cursor
        Cursor c = db.rawQuery(query,null);
        return c;
    }
}
