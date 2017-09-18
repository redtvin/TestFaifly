package com.elanis.citytestfaifly.data;


import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiCountryRepository implements ApiCountry {
    private ApiCountry api;

    public ApiCountryRepository() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.api = retrofit.create(ApiCountry.class);
    }


    @Override
    public Call<Map<String, List<String>>> getData() {
        return api.getData();
    }

}
