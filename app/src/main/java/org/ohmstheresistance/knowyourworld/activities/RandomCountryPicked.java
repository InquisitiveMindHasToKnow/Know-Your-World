package org.ohmstheresistance.knowyourworld.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.ohmstheresistance.knowyourworld.R;
import org.ohmstheresistance.knowyourworld.fragments.FragmentNavigation;
import org.ohmstheresistance.knowyourworld.fragments.GoogleMapsFragment;

public class RandomCountryPicked extends AppCompatActivity implements FragmentNavigation {

    private static final String RANDOM_COUNTRY_KEY = "randomCountryKey";
    private static final String RANDOM_COUNTRY_LATITUDE_KEY = "randomCountryLatitudeKey";
    private static final String RANDOM_COUNTRY_LONGITUDE_KEY = "randomCountryLongitudeKey";
    private static final String RANDOM_COUNTRY_FLAG_KEY = "randomCountryFlagKey";
    private static final String RANDOM_COUNTRY_POPULATION_KEY = "randomCountryPopulationKey";
    private static final String RANDOM_COUNTRY_CAPITAL_KEY = "randomCountryCapitalKey";
    private static final String RANDOM_COUNTRY_REGION_KEY = "randomCountryRegionKey";
    private static final String RANDOM_COUNTRY_SUBREGION_KEY = "randomCountrySubRegionKey";
    private static final String RANDOM_COUNTRY_CIOC_KEY = "randomCountryCiocKey";
    private static final String RANDOM_COUNTRY_AREA_KEY = "randomCountryAreaKey";
    private static final String RANDOM_COUNTRY_ALPHA_CODE_2_KEY = "randomCountryAlphaCode2Key";
    private static final String RANDOM_COUNTRY_ALPHA_CODE_3_KEY = "randomCountryAlphaCode3Key";




    private Intent getRandomCountryIntent;
    private TextView randomCountryChosenNameTextView;
    private TextView randomCountryCapitalTagTextView;
    private TextView randomCountryCapitalTextView;
    private TextView randomCountryPopulationTagTextView;
    private TextView randomCountryPopulationTextView;
    private TextView randomCountryRegionTagTextView;
    private TextView randomCountryRegionTextView;
    private TextView randomCountrySubregionTagTextView;
    private TextView randomCountrySubregionTextView;
    private TextView randomCountryLocationTagTextView;
    private TextView randomCountryLocationTextView;
    private TextView randomCountryCiocTagTextView;
    private TextView randomCountryCiocTextView;
    private TextView randomCountryAreaTagTextView;
    private TextView randomCountryAreaTextView;
    private TextView randomCountryAlphaCodesTagTextView;
    private TextView randomCountryAlphaCodesTextView;
    private WebView randomCountryChoseFlagWebView;


    private String randomCountry;
    private String latitude;
    private String longitude;
    private String randomCountryFlag;
    private String randomCountryCapital;
    private String randomCountryPopulation;
    private String randomCountryRegion;
    private String randomCountrySubRegion;
    private String randomCountryLocation;
    private String randomCountryCioc;
    private String randomCountryArea;
    private String randomCountryAlphaCode2;
    private String randomCountryAlphaCode3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_country_picked);

        randomCountryChoseFlagWebView = findViewById(R.id.random_country_flag_imageview);
        randomCountryChosenNameTextView = findViewById(R.id.random_country_chosen_textview);
        randomCountryCapitalTagTextView = findViewById(R.id.random_country_capital_tag_textview);
        randomCountryCapitalTextView = findViewById(R.id.random_country_capital_textview);
        randomCountryPopulationTagTextView = findViewById(R.id.random_country_population_tag_textview);
        randomCountryPopulationTextView = findViewById(R.id.random_country_population_textview);
        randomCountryRegionTagTextView = findViewById(R.id.random_country_region_tag_textview);
        randomCountryRegionTextView = findViewById(R.id.random_country_region_textview);
        randomCountrySubregionTagTextView = findViewById(R.id.random_country_subregion_tag_textview);
        randomCountrySubregionTextView = findViewById(R.id.random_country_subregion_textview);
        randomCountryLocationTagTextView = findViewById(R.id.random_country_location_tag_textview);
        randomCountryLocationTextView = findViewById(R.id.random_country_location_textview);
        randomCountryCiocTagTextView = findViewById(R.id.random_country_cioc_tag_textview);
        randomCountryCiocTextView = findViewById(R.id.random_country_cioc_textview);
        randomCountryAreaTagTextView = findViewById(R.id.random_country_area_tag_textview);
        randomCountryAreaTextView = findViewById(R.id.random_country_area_textview);
        randomCountryAlphaCodesTagTextView = findViewById(R.id.random_country_alpha_codes_tag_textview);
        randomCountryAlphaCodesTextView = findViewById(R.id.random_country_alpha_codes_textview);


        getRandomCountryIntent = getIntent();

        latitude = getRandomCountryIntent.getStringExtra(RANDOM_COUNTRY_LATITUDE_KEY);
        longitude = getRandomCountryIntent.getStringExtra(RANDOM_COUNTRY_LONGITUDE_KEY);
        randomCountry = getRandomCountryIntent.getStringExtra(RANDOM_COUNTRY_KEY);
        randomCountryFlag = getRandomCountryIntent.getStringExtra(RANDOM_COUNTRY_FLAG_KEY);
        randomCountryCapital = getRandomCountryIntent.getStringExtra(RANDOM_COUNTRY_CAPITAL_KEY);
        randomCountryPopulation = getRandomCountryIntent.getStringExtra(RANDOM_COUNTRY_POPULATION_KEY);
        randomCountryRegion = getRandomCountryIntent.getStringExtra(RANDOM_COUNTRY_REGION_KEY);
        randomCountrySubRegion = getRandomCountryIntent.getStringExtra(RANDOM_COUNTRY_SUBREGION_KEY);
        randomCountryLocation = "Lat: " + latitude+ ", " + "Long:" + longitude;
        randomCountryCioc = getRandomCountryIntent.getStringExtra(RANDOM_COUNTRY_CIOC_KEY);
        randomCountryArea = getRandomCountryIntent.getStringExtra(RANDOM_COUNTRY_AREA_KEY);
        randomCountryAlphaCode2 = getRandomCountryIntent.getStringExtra(RANDOM_COUNTRY_ALPHA_CODE_2_KEY);
        randomCountryAlphaCode3 = getRandomCountryIntent.getStringExtra(RANDOM_COUNTRY_ALPHA_CODE_3_KEY);



        randomCountryChoseFlagWebView.getSettings().setLoadWithOverviewMode(true);
        randomCountryChoseFlagWebView.getSettings().setUseWideViewPort(true);
        randomCountryChoseFlagWebView.setVerticalScrollBarEnabled(false);
        randomCountryChoseFlagWebView.setHorizontalScrollBarEnabled(false);
        randomCountryChoseFlagWebView.setScrollContainer(false);
        randomCountryChoseFlagWebView.setInitialScale(1);
        randomCountryChoseFlagWebView.setBackgroundColor(Color.TRANSPARENT);
        randomCountryChoseFlagWebView.loadUrl(randomCountryFlag);

        randomCountryChosenNameTextView.setText(randomCountry);
        randomCountryAreaTextView.setText(randomCountryArea + " km²");
        randomCountryCapitalTextView.setText(randomCountryCapital);
        randomCountryPopulationTextView.setText(randomCountryPopulation);
        randomCountryRegionTextView.setText(randomCountryRegion);
        randomCountrySubregionTextView.setText(randomCountrySubRegion);
        randomCountryLocationTextView.setText(randomCountryLocation);
        randomCountryCiocTextView.setText(randomCountryCioc);
        randomCountryAlphaCodesTextView.setText(randomCountryAlphaCode2 + ", " + randomCountryAlphaCode3);

        FragmentNavigation fragmentNavigation = (FragmentNavigation) RandomCountryPicked.this;
        fragmentNavigation.goToLocationOnMap(longitude, latitude, randomCountry, randomCountryFlag);

    }

    @Override
    public void goToLocationOnMap(String lon, String lat, String name, String countryFlag) {
        GoogleMapsFragment googleMapsFragment = GoogleMapsFragment.getInstance(lon, lat, name, countryFlag);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_container, googleMapsFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }
}
