package org.ohmstheresistance.knowyourworld.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import org.ohmstheresistance.knowyourworld.R;
import org.ohmstheresistance.knowyourworld.fragments.FragmentNavigation;
import org.ohmstheresistance.knowyourworld.fragments.GoogleMapsFragment;

import java.util.Arrays;

public class SelectedCountryDetails extends AppCompatActivity implements FragmentNavigation {

    private Intent getCountryDetailsIntent;


    private static final String SELECTED_COUNTRY_NAME_KEY = "selectedCountryNameKey";
    private static final String SELECTED_COUNTRY_LATITUDE_KEY = "selectedCountryLatitudeKey";
    private static final String SELECTED_COUNTRY_LONGITUDE_KEY = "selectedCountryLongitudeKey";
    private static final String SELECTED_COUNTRY_FLAG_KEY = "selectedCountryFlagKey";
    private static final String SELECTED_COUNTRY_POPULATION_KEY = "selectedCountryPopulationKey";
    private static final String SELECTED_COUNTRY_CAPITAL_KEY = "selectedCountryCapitalKey";
    private static final String SELECTED_COUNTRY_REGION_KEY = "selectedCountryRegionKey";
    private static final String SELECTED_COUNTRY_SUBREGION_KEY = "selectedCountrySubRegionKey";
    private static final String SELECTED_COUNTRY_CIOC_KEY = "selectedCountryCiocKey";
    private static final String SELECTED_COUNTRY_AREA_KEY = "selectedCountryAreaKey";
    private static final String SELECTED_COUNTRY_ALPHA_CODE_2_KEY = "selectedCountryAlphaCode2Key";
    private static final String SELECTED_COUNTRY_ALPHA_CODE_3_KEY = "randomCountryAlphaCode3Key";
    private static final String SELECTED_COUNTRY_BORDERS_KEY = "selectedCountryBordersKey";
    private static final String SELECTED_COUNTRY_CURRENCIES_KEY = "selectedCountryCurrenciesKey";
    private static final String SELECTED_COUNTRY_LANGUAGES_KEY = "selectedCountryLanguagesKey";
    private static final String SELECTED_COUNTRY_NATIVE_NAME_KEY = "selectedCountryNativeNameKey";

    private TextView selectedCountryDetailsNameTextView;
    private TextView selectedCountryDetailsCapitalTextView;
    private TextView selectedCountryDetailsPopulationTextView;
    private TextView selectedCountryDetailsRegionTextView;
    private TextView selectedCountryDetailsSubregionTextView;
    private TextView selectedCountryDetailsLocationTextView;
    private TextView selectedCountryDetailsCiocTextView;
    private TextView selectedCountryDetailsAreaTextView;
    private TextView selectedCountryDetailsAlphaCodesTextView;
    private TextView selectedCountryDetailsBordersTextView;
    private TextView selectedCountryDetailsLanguageTextView;
    private TextView selectedCountryDetailsCurrencyTextView;
    private WebView selectedCountryFlagWebView;
    private NestedScrollView nestedScrollView;


    private String selectedCountryName;
    private String selectedCountryLatitude;
    private String selectedCountryLongitude;
    private String selectedCountryFlag;
    private String selectedCountryCapital;
    private String selectedCountryPopulation;
    private String selectedCountryRegion;
    private String selectedCountrySubRegion;
    private String selectedCountryLocation;
    private String selectedCountryCioc;
    private String selectedCountryArea;
    private String selectedCountryAlphaCode2;
    private String selectedCountryAlphaCode3;
    private String selectedCountryLanguages;
    private String selectedCountryBorders;
    private String selectedCountryCurrency;
    private String selectedCountryLanguageNativeName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_country_details);

        getCountryDetailsIntent = getIntent();

        selectedCountryFlagWebView = findViewById(R.id.country_details_flag_imageview);
        selectedCountryDetailsNameTextView = findViewById(R.id.country_details_name_textview);
        selectedCountryDetailsCapitalTextView = findViewById(R.id.country_details_capital_textview);
        selectedCountryDetailsPopulationTextView = findViewById(R.id.country_details_population_textview);
        selectedCountryDetailsRegionTextView = findViewById(R.id.country_details_region_textview);
        selectedCountryDetailsSubregionTextView = findViewById(R.id.country_details_subregion_textview);
        selectedCountryDetailsLocationTextView = findViewById(R.id.country_details_location_textview);
        selectedCountryDetailsCiocTextView = findViewById(R.id.country_details_cioc_textview);
        selectedCountryDetailsAreaTextView = findViewById(R.id.country_details_area_textview);
        selectedCountryDetailsAlphaCodesTextView = findViewById(R.id.country_details_alpha_codes_textview);
        selectedCountryDetailsBordersTextView = findViewById(R.id.country_details_borders_textview);
        selectedCountryDetailsLanguageTextView = findViewById(R.id.country_details_language_textview);
        selectedCountryDetailsCurrencyTextView = findViewById(R.id.country_details_currency_textview);
        nestedScrollView = findViewById(R.id.country_details_nested_scrollview);


        Bundle selectedCountryDetailsBundle = getIntent().getExtras();
        if (selectedCountryDetailsBundle != null) {

            selectedCountryLatitude = selectedCountryDetailsBundle.getString(SELECTED_COUNTRY_LATITUDE_KEY);
            selectedCountryLongitude = selectedCountryDetailsBundle.getString(SELECTED_COUNTRY_LONGITUDE_KEY);
            selectedCountryName = selectedCountryDetailsBundle.getString(SELECTED_COUNTRY_NAME_KEY);
            selectedCountryFlag = selectedCountryDetailsBundle.getString(SELECTED_COUNTRY_FLAG_KEY);
            selectedCountryCapital = selectedCountryDetailsBundle.getString(SELECTED_COUNTRY_CAPITAL_KEY);
            selectedCountryPopulation = selectedCountryDetailsBundle.getString(SELECTED_COUNTRY_POPULATION_KEY);
            selectedCountryRegion = selectedCountryDetailsBundle.getString(SELECTED_COUNTRY_REGION_KEY);
            selectedCountrySubRegion = selectedCountryDetailsBundle.getString(SELECTED_COUNTRY_SUBREGION_KEY);
            selectedCountryLocation = "Lat: " + selectedCountryLatitude + ", " + "Long:" + selectedCountryLongitude;
            selectedCountryCioc = selectedCountryDetailsBundle.getString(SELECTED_COUNTRY_CIOC_KEY);
            selectedCountryArea = selectedCountryDetailsBundle.getString(SELECTED_COUNTRY_AREA_KEY);
            selectedCountryAlphaCode2 = selectedCountryDetailsBundle.getString(SELECTED_COUNTRY_ALPHA_CODE_2_KEY);
            selectedCountryAlphaCode3 = selectedCountryDetailsBundle.getString(SELECTED_COUNTRY_ALPHA_CODE_3_KEY);
            selectedCountryLanguages = (String) selectedCountryDetailsBundle.getSerializable(SELECTED_COUNTRY_LANGUAGES_KEY);
            selectedCountryBorders = selectedCountryDetailsBundle.getString(SELECTED_COUNTRY_BORDERS_KEY);
            selectedCountryCurrency = selectedCountryDetailsBundle.getString(SELECTED_COUNTRY_CURRENCIES_KEY);
            selectedCountryLanguageNativeName = selectedCountryDetailsBundle.getString(SELECTED_COUNTRY_NATIVE_NAME_KEY);

            Arrays.toString(new String[]{selectedCountryBorders});


            selectedCountryFlagWebView.getSettings().setLoadWithOverviewMode(true);
            selectedCountryFlagWebView.getSettings().setUseWideViewPort(true);
            selectedCountryFlagWebView.setVerticalScrollBarEnabled(false);
            selectedCountryFlagWebView.setHorizontalScrollBarEnabled(false);
            selectedCountryFlagWebView.setScrollContainer(false);
            selectedCountryFlagWebView.setInitialScale(1);
            selectedCountryFlagWebView.setBackgroundColor(Color.TRANSPARENT);
            selectedCountryFlagWebView.loadUrl(selectedCountryFlag);


            selectedCountryDetailsNameTextView.setText(selectedCountryName);
            selectedCountryDetailsAreaTextView.setText(selectedCountryArea + " km²");
            selectedCountryDetailsCapitalTextView.setText(selectedCountryCapital);
            selectedCountryDetailsPopulationTextView.setText(selectedCountryPopulation);
            selectedCountryDetailsRegionTextView.setText(selectedCountryRegion);
            selectedCountryDetailsSubregionTextView.setText(selectedCountrySubRegion);
            selectedCountryDetailsLocationTextView.setText(selectedCountryLocation);
            selectedCountryDetailsCiocTextView.setText(selectedCountryCioc);
            selectedCountryDetailsAlphaCodesTextView.setText(selectedCountryAlphaCode2 + ", " + selectedCountryAlphaCode3);
            selectedCountryDetailsCurrencyTextView.setText(selectedCountryCurrency);


            if (selectedCountryBorders.length() <= 2) {
                selectedCountryDetailsBordersTextView.setText("No Bordering Countries");
            } else if (selectedCountryBorders.length() > 2)
                selectedCountryDetailsBordersTextView.setText(selectedCountryBorders.substring(1, selectedCountryBorders.length() - 1));


            if (selectedCountryLanguages.equals("English")) {

                selectedCountryDetailsLanguageTextView.setText(selectedCountryLanguages);
            } else
                selectedCountryDetailsLanguageTextView.setText(selectedCountryLanguages + " ( " + selectedCountryLanguageNativeName + " )");
        }

        FragmentNavigation fragmentNavigation = (FragmentNavigation) SelectedCountryDetails.this;
        fragmentNavigation.goToLocationOnMap(selectedCountryLongitude, selectedCountryLatitude, selectedCountryName, selectedCountryFlag);

    }

    @Override
    public void goToLocationOnMap(String lon, String lat, String name, String countryFlag) {
        GoogleMapsFragment googleMapsFragment = GoogleMapsFragment.getInstance(lon, lat, name, countryFlag);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.selected_country_container, googleMapsFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }


}

