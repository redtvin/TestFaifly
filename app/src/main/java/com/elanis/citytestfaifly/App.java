package com.elanis.citytestfaifly;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.elanis.citytestfaifly.data.ApiCityDetailRepository;
import com.elanis.citytestfaifly.data.ApiCountry;
import com.elanis.citytestfaifly.data.ApiCountryRepository;
import com.elanis.citytestfaifly.data.ApiDetailCity;
import com.elanis.citytestfaifly.data.DataBaseHelper;
import com.elanis.citytestfaifly.data.SaveService;


public class App extends Application {
    private static App instance;
    private ApiCountry api;
    private ApiDetailCity apiDetailCity;
    private SQLiteDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();
        SaveService.startActionDownload(this);
        instance = this;
        api = new ApiCountryRepository();
        apiDetailCity = new ApiCityDetailRepository();
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        db = dataBaseHelper.getWritableDatabase();
    }

    public static App get() {
        return instance;
    }

    public ApiCountry getApi() {
        return api;
    }

    public ApiDetailCity getApiDetailCity() {
        return apiDetailCity;
    }

    public SQLiteDatabase getDb() {
        return db;
    }
}
