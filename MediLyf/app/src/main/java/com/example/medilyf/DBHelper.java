package com.example.medilyf;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String col1="name";
    public static final String col2="username";
    public static final String col3="email";
    public static final String col4="PhoneNo";
    public static final String col5="password";

    public static final String NAME = "name";
    public static final String PHONE = "phone";

    public static final String col6="Appointment_id ";
    public static final String col7="full_Name ";
    public static final String col9="Doc_name ";
    public static final String col12="ATime ";
    public static final String col8="Phone_Number";

    public DBHelper(@Nullable Context context) {
        super(context,"Login.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase myDB) {

        myDB.execSQL("create Table users(name Text, username Text primary key, email Text, PhoneNo Text, password Text)");
        myDB.execSQL("create table appo(Dr Text,time Text, date Text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int oldVersion, int newVersion) {

        myDB.execSQL("Drop Table if exists users");
        myDB.execSQL("Drop Table if exists appo");

    }

    public boolean insertData(String name, String username, String email, String PhoneNo, String password){

        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col1,name);
        contentValues.put(col2,username);
        contentValues.put(col3,email);
        contentValues.put(col4,PhoneNo);
        contentValues.put(col5,password);
        long result = myDB.insert("users",null,contentValues);

        return result != -1;
    }

    public boolean insertData2(String dr_name, String time, String date){

        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Dr",dr_name);
        contentValues.put("time",time);
        contentValues.put("date",date);
        long result = myDB.insert("appo",null,contentValues);

        return result != -1;
    }

    public boolean updatePassword(String username, String password) {

        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col5, password);
        long result = myDB.update("users", contentValues,"username = ?",new String[] {username});

        return result != -1;
    }

    public boolean checkusername(String username){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from users where username = ?", new String[] {username});
        return cursor.getCount() > 0;
    }

    public boolean checkusernamepassword(String username, String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from users where username = ? and password = ?", new String[] {username,password});
        return cursor.getCount() > 0;
    }




}
