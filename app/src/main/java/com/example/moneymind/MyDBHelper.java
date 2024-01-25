package com.example.moneymind;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDBHelper extends SQLiteOpenHelper {
private  static final String DATABASE_NAME="MainDB";
private static final int DATABASE_VERSION=2;
private  static final String TABLE_REGISTER="register";
    private  static final String KEY_FullNAME="fullName";
    private  static final String KEY_EMAIL="Email";
    private  static final String KEY_USERNAME="Username";
    private  static final String KEY_PASSWORD="Password";

    public MyDBHelper( Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_REGISTER + "("+KEY_FullNAME+" TEXT,"+KEY_EMAIL+" TEXT,"+
                KEY_USERNAME+" TEXT PRIMARY KEY,"+KEY_PASSWORD+" TEXT NOT NULL"+")");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_REGISTER);
        onCreate(db);

    }
    public Boolean register_user(String name,String email,String username,String password){
        SQLiteDatabase db1=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_FullNAME,name);
        values.put(KEY_EMAIL,email);
        values.put(KEY_USERNAME,username);
        values.put(KEY_PASSWORD,password);

        long result=db1.insert(TABLE_REGISTER,null,values);
        if(result==-1) return false;
        else
            return true;

    }

    public boolean checkUsername(String Username)
    {
        SQLiteDatabase userdb=this.getWritableDatabase();
        Cursor cursor=userdb.rawQuery("SELECT * FROM " + TABLE_REGISTER + " WHERE " + KEY_USERNAME + "=?", new String[]{Username});
        if(cursor.getCount() > 0){
            return true;}
        else
            return false;

    }

    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM "+TABLE_REGISTER+" WHERE "+KEY_USERNAME+"=?"+" AND "+KEY_PASSWORD+"=?",new String[] {username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }


        public Userdata getuserdetails(String full_name) {
            SQLiteDatabase db = this.getWritableDatabase();

            Cursor cursor = db.rawQuery("SELECT "+ KEY_FullNAME+","+KEY_EMAIL+"  FROM " + TABLE_REGISTER + " WHERE " + KEY_USERNAME + "=?", new String[]{full_name});
            Userdata userinfo = new Userdata();

            if (cursor != null && cursor.moveToFirst()) { // Check if the cursor is not null and if it moves to the first row

                int fullNameIndex = cursor.getColumnIndex(KEY_FullNAME);
                int emailIndex = cursor.getColumnIndex(KEY_EMAIL);

                // Check if column indices are valid before retrieving data
                if (fullNameIndex >= 0) {
                    userinfo.user_name = cursor.getString(fullNameIndex);
                }

               if (emailIndex >= 0) {
                  userinfo.user_email = cursor.getString(emailIndex);
               }


            }


            return userinfo;
        }




    }
