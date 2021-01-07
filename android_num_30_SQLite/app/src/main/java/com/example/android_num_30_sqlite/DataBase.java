package com.example.android_num_30_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {

    public static final String data = "data.db";
    public static final String Scp_table = "SCP_table";
    public static final String USER_ID1 = "ID";
    public static final String USER_ID2 = "NAME";
    public static final String USER_ID3 = "LEVEL";
    public static final String USER_ID4 = "SITE";

    public DataBase(@Nullable Context context) {
        super(context, data, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + Scp_table + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, LEVEL TEXT, SITE INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Scp_table);
        onCreate(db);
    }

    public boolean insertData(String name, String level, String site){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_ID2, name);
        contentValues.put(USER_ID3, level);
        contentValues.put(USER_ID4, site);
        long result = db.insert(Scp_table, null, contentValues);
        if (result == -1){
            return false;
        }else{
            return true;
        }
    }
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + Scp_table, null);
        return res;
    }

    public boolean updateData(String id, String name, String level, String site){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_ID1, id);
        contentValues.put(USER_ID2, name);
        contentValues.put(USER_ID3, level);
        contentValues.put(USER_ID4, site);
        db.update(Scp_table, contentValues, "ID = ?", new String[]{ id });
        return  true;
    }
    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Scp_table, "ID = ?", new String[]{ id });
    }
}
