package org.ohmstheresistance.knowyourworld.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.ohmstheresistance.knowyourworld.R;
import org.ohmstheresistance.knowyourworld.fragments.FragmentNavigation;
import org.ohmstheresistance.knowyourworld.fragments.GoogleMapsFragment;

public class RandomCountryPicked extends AppCompatActivity implements FragmentNavigation  {


    private Intent getRandomCountryIntent;
    private TextView randomCountryChosenNameTextview;
    private TextView randomCountryLat;
    private TextView randomCountryLonng;

    private String randomCountry;
    private String latitude;
    private String longitude;
    private static final String RANDOM_COUNTRY_KEY = "randomCountryKey";
    private static final String RANDOM_COUNTRY_LATITUDE_KEY = "randomCountryLatitudeKey";
    private static final String RANDOM_COUNTRY_LONGITUDE_KEY = "randomCountryLongitudeKey";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_country_picked);

        randomCountryChosenNameTextview = findViewById(R.id.random_country_chosen_textview);
        randomCountryLat = findViewById(R.id.random_country_lat);
        randomCountryLonng = findViewById(R.id.random_country_long);


        getRandomCountryIntent = getIntent();

        randomCountry = getRandomCountryIntent.getStringExtra(RANDOM_COUNTRY_KEY);
        latitude = getRandomCountryIntent.getStringExtra(RANDOM_COUNTRY_LATITUDE_KEY);
        longitude = getRandomCountryIntent.getStringExtra(RANDOM_COUNTRY_LONGITUDE_KEY);

        randomCountryChosenNameTextview.setText(randomCountry);
        randomCountryLonng.setText(longitude);
        randomCountryLat.setText(latitude);

        FragmentNavigation fragmentNavigation = (FragmentNavigation) RandomCountryPicked.this;
        fragmentNavigation.goToLocationOnMap(longitude, latitude, randomCountry);

    }

    @Override
    public void goToLocationOnMap(String lon, String lat, String name) {
        GoogleMapsFragment googleMapsFragment = GoogleMapsFragment.getInstance(lon, lat, name);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_container, googleMapsFragment)
                .addToBackStack(null)
                .commit();
    }
}
