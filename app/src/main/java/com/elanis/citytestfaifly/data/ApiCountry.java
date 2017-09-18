package com.elanis.citytestfaifly.data;


import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiCountry {
    @GET("David-Haim/CountriesToCitiesJSON/master/countriesToCities.json")
    Call<Map<String, List<String>>> getData();
}
