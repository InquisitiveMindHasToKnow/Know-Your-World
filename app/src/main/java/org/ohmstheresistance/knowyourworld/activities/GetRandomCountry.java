package org.ohmstheresistance.knowyourworld.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.ohmstheresistance.knowyourworld.R;
import org.ohmstheresistance.knowyourworld.model.Country;
import org.ohmstheresistance.knowyourworld.network.CountryService;
import org.ohmstheresistance.knowyourworld.network.RetrofitSingleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GetRandomCountry extends AppCompatActivity {

    private static final String RANDOM_COUNTRY_NAME_KEY = "randomCountryKey";
    private static final String RANDOM_COUNTRY_LATITUDE_KEY = "randomCountryLatitudeKey";
    private static final String RANDOM_COUNTRY_LONGITUDE_KEY = "randomCountryLongitudeKey";
    private List<Country> countryList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_randomize_country);


        countryList = new ArrayList<>();

        Retrofit countryRetrofit = RetrofitSingleton.getRetrofitInstance();
        CountryService countryService = countryRetrofit.create(CountryService.class);
        countryService.getCountries().enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {

                countryList = response.body();


                Random randomCountry = new Random();
                Country randomCountryPicked = countryList.get(randomCountry.nextInt(countryList.size() - 1) + 1);
                Intent randomCountryIntent = new Intent(getApplicationContext(), RandomCountryPicked.class);

                String countryLocationCoords = randomCountryPicked.getLatlng().toString();

                String[] countryLocation = countryLocationCoords.split(",", 2);
                String latitude = countryLocation[0].substring(1);
                String longitude = countryLocation[1].substring(0, countryLocation[1].length() - 1);

                Log.e("GRC LAT ", latitude);
                Log.e("GRC LON", longitude);

                randomCountryIntent.putExtra(RANDOM_COUNTRY_NAME_KEY, randomCountryPicked.getName());
                randomCountryIntent.putExtra(RANDOM_COUNTRY_LATITUDE_KEY, latitude);
                randomCountryIntent.putExtra(RANDOM_COUNTRY_LONGITUDE_KEY, longitude);

                GetRandomCountry.this.finish();
                startActivity(randomCountryIntent);
                overridePendingTransition(0, 0);



                if (!response.isSuccessful()) {
                    Log.d("Country", "Unable To Display Empty List: " + response.body());
                    Toast.makeText(getApplicationContext(), "Unable To Display Empty List", Toast.LENGTH_LONG).show();

                    return;
                }

            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {


            }

        });


    }


}

