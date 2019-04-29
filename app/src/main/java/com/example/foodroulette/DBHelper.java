package com.example.foodroulette;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "FoodRoulette.db";
    public static final String USER_TABLE_NAME = "logins";
    public static final String USER_COLUMN_ID = "userid";
    public static final String USER_COLUMN_NAME = "name";
    public static final int USER_COLUMN_STATUS = 0;
    public static final String USER_COLUMN_PASSWORD = "password";
    public static final String FOODTYPE_COLUMN_NAMES = "foodtypes";
    public static final String FOODTYPE_COLUMN_SELECT = "selectedfoodtypes";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(
                    "create table logins " +
                            "(id integer primary key autoincrement,status integer, name text,password text,foodtypes text,selectedfoodtypes)"
            );

            Log.v("DBHelper","Table created");

            String s = "INSERT INTO logins (status,name, password, foodtypes, selectedfoodtypes) VALUES(0,'Kym','money','fastfood,italian,bbq,chinese,seafood,vegetarian','fastfood,italian,bbq,chinese');";
            String q = "INSERT INTO logins (status,name, password, foodtypes, selectedfoodtypes) VALUES(0,'Chino','bhino','fastfood,italin,bbq,chinese,seafood,vegetarian','fastfood,chinese,seafood');";

            db.execSQL(s);
            db.execSQL(q);
        }
        catch (Exception e){
            Log.v("DBHelper","FAIL!!!!");
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS logins");
        onCreate(db);
    }

    public int setLoggedIn(String name){
        try{
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("update logins set status=1 where name='"+name+"'");
        return 0;
        }catch(Exception e){
            return 1;
        }
    }

    public boolean getLoggedIn(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select status from logins where name='"+name+"'", null);
        res.moveToFirst();
        int status = res.getInt((res.getColumnIndex("status")));

        if(status == 1){
            return true;
        }
        return false;
    }

    public void makeQuery(String query){
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(query);

    }


    public ArrayList<String> getSelGenre() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select selectedfoodtypes from logins where status=1", null );
        res.moveToFirst();

        Log.v("ListSize",""+res.getCount());
        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(FOODTYPE_COLUMN_SELECT)));
          //  array_list.add(res.getString(res.getColumnIndex(USER_COLUMN_PASSWORD)));
            res.moveToNext();
        }
        return array_list;
    }


    public ArrayList<String> getAllUsers() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select name,password,status from logins", null );
        res.moveToFirst();

        Log.v("Status",""+res.getInt(res.getColumnIndex(("status"))));
        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(USER_COLUMN_NAME)));
            array_list.add(res.getString(res.getColumnIndex(USER_COLUMN_PASSWORD)));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getSelected(){
        ArrayList <String> select = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select selectedfoodtypes from logins", null );
        res.moveToFirst();

        Log.v("These are Selected",res.getString(res.getColumnIndex(FOODTYPE_COLUMN_SELECT)));
        while(res.isAfterLast() == false){
            select.add(res.getString(res.getColumnIndex(FOODTYPE_COLUMN_SELECT)));
            res.moveToNext();
        }
        return select;
    }

}
