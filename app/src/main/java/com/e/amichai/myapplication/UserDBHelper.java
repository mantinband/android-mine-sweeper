package com.e.amichai.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class UserDBHelper  extends SQLiteOpenHelper{
    private static boolean fileCreated = false;
    private static final String DATABASENAME = "GAMELEVELINFO.DB";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_QUERY =
            "CREATE TABLE " + "LEVEL_INFO" + " (" +"BEGINNER_BEST_TIME"+ " INTEGER," +
                    "INTERMEDIATE_BEST_TIME" + " INTEGER," +
                    "PRO_BEST_TIME" + " INTEGER);";

    public UserDBHelper(Context context){
        super(context,DATABASENAME,null,DATABASE_VERSION);
        Log.e("DATABASE OPERTAIONS","Database created/ opened...");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_QUERY);
        Log.e("DATABASE OPERATIONS","Table created...");
    }

    public void addInformation(int beginnerBestTime, int intermediateBestTime, int proBestTime, SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();
        contentValues.put("BEGINNER_BEST_TIME",beginnerBestTime);
        contentValues.put("INTERMEDIATE_BEST_TIME",intermediateBestTime);
        contentValues.put("PRO_BEST_TIME",proBestTime);
        db.insert("LEVEL_INFO",null,contentValues);
        Log.e("DATABASE OPERATIONS","One row inserted...");
    }

    public Cursor getInformation(SQLiteDatabase db){
        Cursor cursor;
        String []projections = {"BEGINNER_BEST_TIME", "INTERMEDIATE_BEST_TIME", "PRO_BEST_TIME FROM"};

        cursor = db.rawQuery("select * from LEVEL_INFO",null);
        return cursor;
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean getFileCreated(){
        return fileCreated;
    }

    public void setFileCreated(boolean status){
        fileCreated = status;
    }
}
