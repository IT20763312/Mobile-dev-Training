package com.example.myapplication1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

    private DatabaseHelperClass databaseHelperClass;

    private Context context;

    private SQLiteDatabase sqLiteDatabase;

    public DBManager(Context c){
        context = c;
    }

    public DBManager open() throws SQLException {
        databaseHelperClass = new DatabaseHelperClass(context);
        sqLiteDatabase = databaseHelperClass.getWritableDatabase();
        return this;
    }

    public void close(){
        databaseHelperClass.close();
    }

    public void insert(String username, String password){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelperClass.USERNAME, username);
        contentValues.put(DatabaseHelperClass.PASSWORD, password);
        sqLiteDatabase.insert(DatabaseHelperClass.TABLE_NAME,null,contentValues);
    }

    public Cursor fetch(){
        String[] columns = new String[]{DatabaseHelperClass.ID,DatabaseHelperClass.USERNAME,DatabaseHelperClass.PASSWORD};
        Cursor cursor = sqLiteDatabase.query(DatabaseHelperClass.TABLE_NAME,columns,null,null,null,null,null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long id, String username){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelperClass.USERNAME, username);
        int i = sqLiteDatabase.update(DatabaseHelperClass.TABLE_NAME,contentValues,DatabaseHelperClass.ID+"="+id,null);
        return i;
    }

    public void delete(long id){
        sqLiteDatabase.delete(DatabaseHelperClass.TABLE_NAME,DatabaseHelperClass.ID+"="+id,null);
    }
}
