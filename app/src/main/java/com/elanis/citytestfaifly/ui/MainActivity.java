package com.elanis.citytestfaifly.ui;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.elanis.citytestfaifly.App;
import com.elanis.citytestfaifly.R;
import com.elanis.citytestfaifly.data.CacheRepository;
import com.elanis.citytestfaifly.data.SaveService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements CityAdapter.ItemClickListener {
    @BindView(R.id.spinner_country)
    Spinner spinnerCountry;
    @BindView(R.id.rv_city)
    RecyclerView rvCity;
    private ProgressDialog progressDialog;
    private SQLiteDatabase db;
    private DataLoadedBroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        receiver = new DataLoadedBroadcastReceiver();

        db = App.get().getDb();
        showProgress();

        CacheRepository cacheRepository = new CacheRepository(App.get().getDb());
        int countEntry = cacheRepository.getCountriesCount();
        if (countEntry != 0) {
            initiList();
        }
    }

    private void initiList() {
        hideProgress();
        final CacheRepository cacheRepository = new CacheRepository(db);

        final CityAdapter cityAdapter = new CityAdapter();
        cityAdapter.setListener(this);
        rvCity.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvCity.setAdapter(cityAdapter);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cacheRepository.findCountries());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCountry.setAdapter(adapter);
        spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                List<String> cityList = cacheRepository.findCities(i + 1);
                cityAdapter.setData(cityList);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    public void onItemClicked(String city) {
        Intent intent = DetailsActivity.getIntent(this, city);
        startActivity(intent);
    }

    private void showProgress() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setCancelable(false);
        }
        progressDialog.show();
    }

    private void hideProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, new IntentFilter(SaveService.BROADCAST_ACTION_FILTER));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    public class DataLoadedBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            initiList();
        }

    }
}
