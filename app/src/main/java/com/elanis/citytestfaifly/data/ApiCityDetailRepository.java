package com.elanis.citytestfaifly.data;

import com.elanis.citytestfaifly.data.dto.CityDetail;


import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Query;


public class ApiCityDetailRepository implements ApiDetailCity {
    private ApiDetailCity api;

    public ApiCityDetailRepository() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.geonames.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.api = retrofit.create(ApiDetailCity.class);
    }

    @Override
    public Call<CityDetail> getDetailCity(@Query("q") String cityName) {
        return api.getDetailCity(cityName);
    }
}
