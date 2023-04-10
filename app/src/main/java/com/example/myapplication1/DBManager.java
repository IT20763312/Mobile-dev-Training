package com.example.myapplication1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DBManager {

    private DatabaseHelperClass databaseHelperClass;

    private Context context;

    public SQLiteDatabase sqLiteDatabase;

    public DBManager(UpdateActivity c){
        context = c;
    }
    public DBManager(RegisterActivity c){
        context = c;
    }
    public DBManager(LoginActivity c){
        context = c;
    }
    public DBManager(UserlistActivity c){
        context = c;
    }

    public DBManager open() throws SQLException {
        databaseHelperClass = new DatabaseHelperClass(context);
        sqLiteDatabase = databaseHelperClass.getWritableDatabase();
        return this;
    }

    public DBManager openRead() throws SQLException {
        databaseHelperClass = new DatabaseHelperClass(context);
        sqLiteDatabase = databaseHelperClass.getReadableDatabase();
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

    public boolean checkUser(String username,String password){
        //array of columns to fetch
        String[] columns = {DatabaseHelperClass.ID};

        sqLiteDatabase = databaseHelperClass.getReadableDatabase();

        //Selection
        String selection = DatabaseHelperClass.USERNAME + " = ? " + " AND " + DatabaseHelperClass.PASSWORD + " = ?";

        //Selection Args
        String[] selectionArgs = {username, password};

        Cursor cursor = sqLiteDatabase.query(DatabaseHelperClass.TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);

        int cursorCount = cursor.getCount();

        cursor.close();
        sqLiteDatabase.close();
        if (cursorCount > 0){
            return true;
        }
        return false;
    }

    public List<EmployeeModelClass> getUsersList(){
        String sql = "Select * from " + DatabaseHelperClass.TABLE_NAME;
        openRead();
        List<EmployeeModelClass> storeEmployee = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String username = cursor.getString(1);
                String password = cursor.getString(2);
                storeEmployee.add(new EmployeeModelClass(id,username,password));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeEmployee;
    }
}
