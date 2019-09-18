package org.ohmstheresistance.knowyourworld.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;

import android.widget.FrameLayout;
import android.widget.TextView;


import org.ohmstheresistance.knowyourworld.R;
import org.ohmstheresistance.knowyourworld.database.CountryDatabaseHelper;
import org.ohmstheresistance.knowyourworld.fragments.FragmentNavigation;
import org.ohmstheresistance.knowyourworld.fragments.GoogleMapsFragment;
import org.ohmstheresistance.knowyourworld.model.Country;

import java.util.Arrays;

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
    private static final String RANDOM_COUNTRY_BORDERS_KEY = "randomCountryBordersKey";
    private static final String RANDOM_COUNTRY_CURRENCIES_KEY = "randomCountryCurrenciesKey";
    private static final String RANDOM_COUNTRY_LANGUAGES_KEY = "randomCountryLanguagesKey";
    private static final String RANDOM_COUNTRY_NATIVE_NAME_KEY = "randomCountryNativeNameKey";



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
    private TextView randomCountryBordersTagTextView;
    private TextView randomCountryBordersTextView;
    private TextView randomCountryLanguageTagTextView;
    private TextView randomCountryLanguageTextView;
    private TextView randomCountryCurrencyTagTextView;
    private TextView randomCountryCurrencyTextView;
    private WebView randomCountryChosenFlagWebView;
    private NestedScrollView nestedScrollView;
    private Button nextButton;
    private Button moreInfoButton;
    private ConstraintLayout constraintLayout;


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
    private String randomCountryLanguages;
    private String randomCountryBorders;
    private String randomCountryCurrency;
    private String randomCountryLanguageNativeName;

    private FloatingActionButton randomCountryFab;
    private CountryDatabaseHelper countryDatabaseHelper;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_country_picked);

        countryDatabaseHelper = CountryDatabaseHelper.getInstance(this);


        randomCountryChosenFlagWebView = findViewById(R.id.random_country_flag_imageview);
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
        randomCountryBordersTagTextView = findViewById(R.id.random_country_borders_tag_textview);
        randomCountryBordersTextView = findViewById(R.id.random_country_borders_textview);
        randomCountryLanguageTagTextView = findViewById(R.id.random_country_language_tag_textview);
        randomCountryLanguageTextView = findViewById(R.id.random_country_language_textview);
        randomCountryCurrencyTagTextView = findViewById(R.id.random_country_currency_tag_textview);
        randomCountryCurrencyTextView = findViewById(R.id.random_country_currency_textview);
        nestedScrollView = findViewById(R.id.random_country_nested_scrollview);
        nextButton = findViewById(R.id.nextButton);
        randomCountryFab = findViewById(R.id.random_country_favourites_fab_button);
        moreInfoButton = findViewById(R.id.more_info_button);
        constraintLayout = findViewById(R.id.main_container);


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextRandomCountryIntent = new Intent(RandomCountryPicked.this, GetRandomCountry.class);
                startActivity(nextRandomCountryIntent);
                overridePendingTransition(0, 0);

            }
        });

        moreInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String internetURLForSelectedCountry = "https://en.wikipedia.org/wiki/" + randomCountry;

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(internetURLForSelectedCountry));
                startActivity(intent);
                overridePendingTransition(0, 0);

            }
        });


        Bundle countryDetailsBundle = getIntent().getExtras();
        if (countryDetailsBundle != null) {

            latitude = countryDetailsBundle.getString(RANDOM_COUNTRY_LATITUDE_KEY);
            longitude = countryDetailsBundle.getString(RANDOM_COUNTRY_LONGITUDE_KEY);
            randomCountry = countryDetailsBundle.getString(RANDOM_COUNTRY_KEY);
            randomCountryFlag = countryDetailsBundle.getString(RANDOM_COUNTRY_FLAG_KEY);
            randomCountryCapital = countryDetailsBundle.getString(RANDOM_COUNTRY_CAPITAL_KEY);
            randomCountryPopulation = countryDetailsBundle.getString(RANDOM_COUNTRY_POPULATION_KEY);
            randomCountryRegion = countryDetailsBundle.getString(RANDOM_COUNTRY_REGION_KEY);
            randomCountrySubRegion = countryDetailsBundle.getString(RANDOM_COUNTRY_SUBREGION_KEY);
            randomCountryLocation = "Lat: " + latitude + ", " + "Long:" + longitude;
            randomCountryCioc = countryDetailsBundle.getString(RANDOM_COUNTRY_CIOC_KEY);
            randomCountryArea = countryDetailsBundle.getString(RANDOM_COUNTRY_AREA_KEY);
            randomCountryAlphaCode2 = countryDetailsBundle.getString(RANDOM_COUNTRY_ALPHA_CODE_2_KEY);
            randomCountryAlphaCode3 = countryDetailsBundle.getString(RANDOM_COUNTRY_ALPHA_CODE_3_KEY);
            randomCountryLanguages = (String) countryDetailsBundle.getSerializable(RANDOM_COUNTRY_LANGUAGES_KEY);
            randomCountryBorders = countryDetailsBundle.getString(RANDOM_COUNTRY_BORDERS_KEY);
            randomCountryCurrency = countryDetailsBundle.getString(RANDOM_COUNTRY_CURRENCIES_KEY);
            randomCountryLanguageNativeName = countryDetailsBundle.getString(RANDOM_COUNTRY_NATIVE_NAME_KEY);

            Arrays.toString(new String[]{randomCountryBorders});


            randomCountryChosenFlagWebView.getSettings().setLoadWithOverviewMode(true);
            randomCountryChosenFlagWebView.getSettings().setUseWideViewPort(true);
            randomCountryChosenFlagWebView.setVerticalScrollBarEnabled(false);
            randomCountryChosenFlagWebView.setHorizontalScrollBarEnabled(false);
            randomCountryChosenFlagWebView.setScrollContainer(false);
            randomCountryChosenFlagWebView.setInitialScale(1);
            randomCountryChosenFlagWebView.setBackgroundColor(Color.TRANSPARENT);
            randomCountryChosenFlagWebView.loadUrl(randomCountryFlag);


            randomCountryChosenNameTextView.setText(randomCountry);
            randomCountryAreaTextView.setText(randomCountryArea + " km²");
            randomCountryCapitalTextView.setText(randomCountryCapital);
            randomCountryPopulationTextView.setText(randomCountryPopulation);
            randomCountryRegionTextView.setText(randomCountryRegion);
            randomCountrySubregionTextView.setText(randomCountrySubRegion);
            randomCountryLocationTextView.setText(randomCountryLocation);
            randomCountryCiocTextView.setText(randomCountryCioc);
            randomCountryAlphaCodesTextView.setText(randomCountryAlphaCode2 + ", " + randomCountryAlphaCode3);
            randomCountryCurrencyTextView.setText(randomCountryCurrency);


            if (randomCountryBorders.length() <= 2) {
                randomCountryBordersTextView.setText("No Bordering Countries");
            } else if (randomCountryBorders.length() > 2)
            randomCountryBordersTextView.setText(randomCountryBorders.substring(1, randomCountryBorders.length() - 1));


            if(randomCountryLanguages.equals("English")){

                randomCountryLanguageTextView.setText(randomCountryLanguages);
            }else
            randomCountryLanguageTextView.setText(randomCountryLanguages + " ( " + randomCountryLanguageNativeName + " )");


        FragmentNavigation fragmentNavigation = (FragmentNavigation) RandomCountryPicked.this;
        fragmentNavigation.goToLocationOnMap(longitude, latitude, randomCountry, randomCountryFlag);
    }


        boolean isFavorite = countryDatabaseHelper.isFavorite(randomCountry);
        randomCountryFab.setImageResource(isFavorite ? R.drawable.ic_done : R.drawable.ic_save);

        randomCountryFab.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                boolean isFavorite = countryDatabaseHelper.isFavorite(randomCountry);

                if (isFavorite) {
                    countryDatabaseHelper.deleteFavorite(randomCountry);
                    randomCountryFab.setImageResource(R.drawable.ic_save);

                    Snackbar favoriteUnsavedSnackbar = Snackbar.make(constraintLayout, randomCountry + " removed from favorites.", Snackbar.LENGTH_LONG);
                    View favoriteUnsavedSnackbarView = favoriteUnsavedSnackbar.getView();
                    TextView snackBarTextView = favoriteUnsavedSnackbarView.findViewById(android.support.design.R.id.snackbar_text);

                    snackBarTextView.setBackgroundResource(R.drawable.rounded_shape_for_ran_and_sel_snackbars);
                    snackBarTextView.setTextColor(getResources().getColor(R.color.colorAccent));
                    favoriteUnsavedSnackbarView.setBackgroundColor(Color.TRANSPARENT);


                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) favoriteUnsavedSnackbarView.getLayoutParams();
                    layoutParams.width = FrameLayout.LayoutParams.WRAP_CONTENT;
                    layoutParams.gravity = Gravity.CENTER|Gravity.BOTTOM;
                    favoriteUnsavedSnackbarView.setLayoutParams(layoutParams);



                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
                        snackBarTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    else
                        snackBarTextView.setGravity(Gravity.CENTER_HORIZONTAL);

                    favoriteUnsavedSnackbar.show();
                } else {

                    countryDatabaseHelper.addFavorite(Country.from(randomCountry, randomCountryFlag));
                    randomCountryFab.setImageResource(R.drawable.ic_done);


                    Snackbar favoriteSavedSnackbar = Snackbar.make(constraintLayout, randomCountry + " added to favorites.", Snackbar.LENGTH_LONG);
                    View favoriteSavedSnackbarView = favoriteSavedSnackbar.getView();
                    TextView snackBarTextView = favoriteSavedSnackbarView.findViewById(android.support.design.R.id.snackbar_text);

                    snackBarTextView.setBackgroundResource(R.drawable.rounded_shape_for_ran_and_sel_snackbars);
                    snackBarTextView.setTextColor(getResources().getColor(R.color.colorAccent));
                    favoriteSavedSnackbarView.setBackgroundColor(Color.TRANSPARENT);

                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) favoriteSavedSnackbarView.getLayoutParams();
                    layoutParams.width = FrameLayout.LayoutParams.WRAP_CONTENT;
                    layoutParams.gravity = Gravity.CENTER|Gravity.BOTTOM;
                    favoriteSavedSnackbarView.setLayoutParams(layoutParams);


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
                        snackBarTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    else
                        snackBarTextView.setGravity(Gravity.CENTER_HORIZONTAL);

                    favoriteSavedSnackbar.show();
                }
            }
        });

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
