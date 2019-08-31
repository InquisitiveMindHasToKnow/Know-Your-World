package org.ohmstheresistance.knowyourworld.network;

import org.ohmstheresistance.knowyourworld.model.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CountryService {

    @GET("/words/{word}")
    Call<List<Country>> getCountries();
}
