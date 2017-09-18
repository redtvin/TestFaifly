package com.elanis.citytestfaifly.data;

import com.elanis.citytestfaifly.data.dto.CityDetail;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiDetailCity {
    @GET("wikipediaSearchJSON?username=elanis&maxRows=1")
    Call<CityDetail> getDetailCity(@Query("q") String cityName);
}
