package org.ohmstheresistance.knowyourworld.activities;

import android.content.Intent;
import android.os.Parcelable;
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
    private static final String RANDOM_COUNTRY_FLAG_KEY = "randomCountryFlagKey";
    private static final String RANDOM_COUNTRY_POPULATION_KEY = "randomCountryPopulationKey";
    private static final String RANDOM_COUNTRY_CAPITAL_KEY = "randomCountryCapitalKey";
    private static final String RANDOM_COUNTRY_REGION_KEY = "randomCountryRegionKey";
    private static final String RANDOM_COUNTRY_SUBREGION_KEY = "randomCountrySubRegionKey";
    private static final String RANDOM_COUNTRY_BORDERS_KEY = "randomCountryBordersKey";
    private static final String RANDOM_COUNTRY_CURRENCIES_KEY = "randomCountryCurrenciesKey";
    private static final String RANDOM_COUNTRY_LANGUAGES_KEY = "randomCountryLanguagesKey";
    private static final String RANDOM_COUNTRY_CIOC_KEY = "randomCountryCiocKey";
    private static final String RANDOM_COUNTRY_AREA_KEY = "randomCountryAreaKey";
    private static final String RANDOM_COUNTRY_ALPHA_CODE_2_KEY = "randomCountryAlphaCode2Key";
    private static final String RANDOM_COUNTRY_ALPHA_CODE_3_KEY = "randomCountryAlphaCode3Key";


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

                String randomCountryLocationCoords = randomCountryPicked.getLatlng().toString();
                String randomCountryPickedName = randomCountryPicked.getName();
                String randomCountryPickedFlag = randomCountryPicked.getFlag();
                String randomCountryPickedPopulation = String.valueOf(randomCountryPicked.getPopulation());
                String randomCountryPickedCapital = randomCountryPicked.getCapital();
                String randomCountryPickedRegion = randomCountryPicked.getRegion();
                String randomCountryPickedSubRegion = randomCountryPicked.getSubregion();
                String randomCountryPickedCioc = randomCountryPicked.getCioc();
                String randomCountryPickedArea = String.valueOf(randomCountryPicked.getArea());
                String randomCountryPickedAlpha2Code = randomCountryPicked.getAlpha2Code();
                String randomCountryPickedAlpha3Code = randomCountryPicked.getAlpha3Code();

//                List<String> randomCountryPickedBorders = randomCountryPicked.getBorders();
//                List<Country.Currency>randomCountryPickedCurrencies = randomCountryPicked.getCurrencies();
//                List<Country.Language> randomCountryPickedLanguages = randomCountryPicked.getLanguages();


                String[] countryLocation = randomCountryLocationCoords.split(",", 2);
                String randomCountryPickedLatitude = countryLocation[0].substring(1);
                String randomCountryPickedLongitude = countryLocation[1].substring(0, countryLocation[1].length() - 1);

                randomCountryIntent.putExtra(RANDOM_COUNTRY_NAME_KEY, randomCountryPickedName);
                randomCountryIntent.putExtra(RANDOM_COUNTRY_LATITUDE_KEY, randomCountryPickedLatitude);
                randomCountryIntent.putExtra(RANDOM_COUNTRY_LONGITUDE_KEY, randomCountryPickedLongitude);
                randomCountryIntent.putExtra(RANDOM_COUNTRY_FLAG_KEY, randomCountryPickedFlag);
                randomCountryIntent.putExtra(RANDOM_COUNTRY_POPULATION_KEY, randomCountryPickedPopulation);
                randomCountryIntent.putExtra(RANDOM_COUNTRY_CAPITAL_KEY, randomCountryPickedCapital);
                randomCountryIntent.putExtra(RANDOM_COUNTRY_REGION_KEY, randomCountryPickedRegion);
                randomCountryIntent.putExtra(RANDOM_COUNTRY_SUBREGION_KEY, randomCountryPickedSubRegion);
//                randomCountryIntent.putExtra(RANDOM_COUNTRY_BORDERS_KEY, (Parcelable) randomCountryPickedBorders);
//                randomCountryIntent.putExtra(RANDOM_COUNTRY_CURRENCIES_KEY, (Parcelable) randomCountryPickedCurrencies);
//                randomCountryIntent.putExtra(RANDOM_COUNTRY_LANGUAGES_KEY, (Parcelable) randomCountryPickedLanguages);
                randomCountryIntent.putExtra(RANDOM_COUNTRY_CIOC_KEY, randomCountryPickedCioc);
                randomCountryIntent.putExtra(RANDOM_COUNTRY_AREA_KEY, randomCountryPickedArea);
                randomCountryIntent.putExtra(RANDOM_COUNTRY_ALPHA_CODE_2_KEY, randomCountryPickedAlpha2Code);
                randomCountryIntent.putExtra(RANDOM_COUNTRY_ALPHA_CODE_3_KEY, randomCountryPickedAlpha3Code);


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

