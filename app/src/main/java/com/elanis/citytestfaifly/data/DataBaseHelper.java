package com.elanis.citytestfaifly.data;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "countrybase";
    public static final String TABLE_COUNTRY = "country";
    public static final String TABLE_CITY = "city";
    public static final String COUNTRY = "country";
    public static final String CITY = "city";
    public static final String COUNTRY_ID = "country_id";
    private static final String CREATE_TABLE_COUNTRY = "create table " + TABLE_COUNTRY
            + "( _id integer primary key autoincrement, " + COUNTRY + " TEXT NOT NULL" + ");";
    private static final String CREATE_TABLE_CITY = "create table " + TABLE_CITY
            + "( _id integer primary key autoincrement, " + COUNTRY_ID + " INTEGER, " + CITY + " TEXT NOT NULL" + ");";

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CITY);
        db.execSQL(CREATE_TABLE_COUNTRY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_COUNTRY);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CITY);

        onCreate(sqLiteDatabase);
    }
}
