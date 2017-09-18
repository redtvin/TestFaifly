package com.elanis.citytestfaifly.data;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.elanis.citytestfaifly.App;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import retrofit2.Response;


public class SaveService extends IntentService {
    private static final String ACTION_DOWNLOAD = "download";
    public static final String BROADCAST_ACTION_FILTER = "filter";

    public SaveService() {
        super("SaveService");
    }

    public static void startActionDownload(Context context) {
        Intent intent = new Intent(context, SaveService.class);
        intent.setAction(ACTION_DOWNLOAD);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_DOWNLOAD.equals(action)) {
                download();
            }
        }
    }

    private void download() {
        CacheRepository cacheRepository = new CacheRepository(App.get().getDb());
        int countEntry = cacheRepository.getCountriesCount();
        if (countEntry == 0) {
            loadData();
        }
    }

    private void loadData() {
        try {
            Response<Map<String, List<String>>> response = App.get().getApi().getData().execute();
            Map<String, List<String>> data = response.body();
            if (data != null && !data.isEmpty()) {
                saveToDb(data);
                sendBroadcast(new Intent(BROADCAST_ACTION_FILTER));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveToDb(Map<String, List<String>> data) {
        SQLiteDatabase db = App.get().getDb();
        CacheRepository cacheRepository = new CacheRepository(db);
        for (Map.Entry<String, List<String>> entry : data.entrySet()) {
            String country = entry.getKey();
            List<String> city = entry.getValue();
            cacheRepository.insertCountry(country, city);
        }
    }

}
