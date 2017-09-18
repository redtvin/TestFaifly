package com.elanis.citytestfaifly.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


public class CacheRepository {
    private SQLiteDatabase db;

    public CacheRepository(SQLiteDatabase db) {
        this.db = db;
    }

    public void insertCountry(String name, List<String> city) {
        ContentValues cv = new ContentValues();
        if (name == null || name.isEmpty()) {
            return;
        }
        cv.put(DataBaseHelper.COUNTRY, name);
        long id = db.insert(DataBaseHelper.TABLE_COUNTRY, null, cv);
        if (id != -1) {
            insertCities(id, city);
        }
    }

    public void insertCities(long id, List<String> cities) {
        db.beginTransaction();
        for (String currentCity : cities) {
            ContentValues cv = new ContentValues();
            cv.put(DataBaseHelper.CITY, currentCity);
            cv.put(DataBaseHelper.COUNTRY_ID, id);

            db.insert(DataBaseHelper.TABLE_CITY, null, cv);
        }
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public List<String> findCountries() {
        List<String> countryList = new ArrayList<>();
        Cursor c = db.query(DataBaseHelper.TABLE_COUNTRY, null, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            int countryColIndex = c.getColumnIndex(DataBaseHelper.COUNTRY);
            do {
                countryList.add(c.getString(countryColIndex));
            } while (c.moveToNext());
        }
        c.close();
        return countryList;
    }

    public List<String> findCities(int id) {
        List<String> cityList = new ArrayList<>();

        String whereClause = "country_id = " + id;
        Cursor c = db.query(DataBaseHelper.TABLE_CITY, null, whereClause, null, null, null, null, null);
        if (c.moveToFirst()) {
            int cityColIndex = c.getColumnIndex(DataBaseHelper.CITY);
            do {
                cityList.add(c.getString(cityColIndex));
            } while (c.moveToNext());
        }
        c.close();
        return cityList;
    }

    /**
     * Получение числа стран хранящихся в базе данных.
     */
    public int getCountriesCount() {
        Cursor c = db.rawQuery("SELECT COUNT (*) FROM " + DataBaseHelper.TABLE_COUNTRY, null);

        if (c.moveToFirst()) {
            int count = c.getInt(0);
            c.close();
            return count;
        }
        return 0;
    }
}
