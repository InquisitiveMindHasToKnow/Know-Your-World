package org.ohmstheresistance.knowyourworld.network;

import org.ohmstheresistance.knowyourworld.model.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CountryService {

    @GET("/rest/v2/all")
    Call<List<Country>> getCountries();
}
