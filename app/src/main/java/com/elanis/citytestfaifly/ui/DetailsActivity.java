package com.elanis.citytestfaifly.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.elanis.citytestfaifly.App;
import com.elanis.citytestfaifly.R;
import com.elanis.citytestfaifly.data.dto.CityDetail;
import com.squareup.picasso.Picasso;


import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends AppCompatActivity {

    private static final String CITY = "city";

    @BindView(R.id.iv_thumb)
    ImageView ivThumb;
    @BindView(R.id.tv_city_name)
    TextView tvCityName;
    @BindView(R.id.tv_summary)
    TextView tvSummary;
    @BindView(R.id.tv_link)
    TextView tvLink;
    @BindView(R.id.tv_elevation)
    TextView tvElevation;

    public static Intent getIntent(Context context, String city) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(CITY, city);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        String city = getIntent().getStringExtra(CITY);
        loadData(city);
    }

    private void loadData(String city) {
        App.get().getApiDetailCity()
                .getDetailCity(city)
                .enqueue(new Callback<CityDetail>() {
                    @Override
                    public void onResponse(Call<CityDetail> call, Response<CityDetail> response) {
                        CityDetail detailList = response.body();
                        if (detailList != null && !detailList.getGeonames().isEmpty()) {
                            showData(detailList.getGeonames().get(0));
                        }
                    }

                    @Override
                    public void onFailure(Call<CityDetail> call, Throwable t) {
                        Toast.makeText(DetailsActivity.this, t.getMessage(), Toast.LENGTH_LONG)
                                .show();
                    }
                });
    }

    private void showData(CityDetail.City city) {
        tvCityName.setText(city.getTitle());
        tvSummary.setText(city.getSummary());
        tvLink.setText(city.getWikipediaUrl());
        tvElevation.setText(getString(R.string.elevation, city.getElevation()));
        Picasso.with(this).load(city.getThumbnailImg()).into(ivThumb);
    }
}
