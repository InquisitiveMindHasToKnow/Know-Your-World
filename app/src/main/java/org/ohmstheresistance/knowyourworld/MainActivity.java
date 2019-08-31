package org.ohmstheresistance.knowyourworld;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.ohmstheresistance.knowyourworld.model.Country;
import org.ohmstheresistance.knowyourworld.network.CountryService;
import org.ohmstheresistance.knowyourworld.network.RetrofitSingleton;
import org.ohmstheresistance.knowyourworld.rv.CountryAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private List<Country> countryList;
    private RecyclerView countryRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countryRecyclerView = findViewById(R.id.country_recycler_view);

        countryList = new ArrayList<>();

        Retrofit countryRetrofit = RetrofitSingleton.getRetrofitInstance();
        CountryService countryService = countryRetrofit.create(CountryService.class);
        countryService.getCountries().enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {


                Log.d("Country ", "Retrofit call works " + response.body().get(0).getName());


                if (response.body() != null) {

                    countryList = response.body();
                    Log.d("Country ", "Retrofit call works " + response.body().get(6).getFlag());


                    CountryAdapter countryAdapter = new CountryAdapter(countryList);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
                    countryRecyclerView.setLayoutManager(gridLayoutManager);
                    countryRecyclerView.setAdapter(countryAdapter);
                }

                if (!response.isSuccessful()) {
                    Log.d("Country", "Unable To Display Empty List: " + response.body());
                    Toast.makeText(getApplicationContext(), "Unable To Display Empty List", Toast.LENGTH_LONG).show();

                    return;
                }

            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {

                Log.d("Country", "Retrofit call failed, Omar" + t.getMessage());


            }

        });


    }
}
