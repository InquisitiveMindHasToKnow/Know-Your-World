package org.ohmstheresistance.knowyourworld.activities;

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
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.ohmstheresistance.knowyourworld.R;
import org.ohmstheresistance.knowyourworld.database.CountryDatabaseHelper;
import org.ohmstheresistance.knowyourworld.fragments.FragmentNavigation;
import org.ohmstheresistance.knowyourworld.fragments.GoogleMapsFragment;
import org.ohmstheresistance.knowyourworld.model.Country;

import java.util.Arrays;

public class SelectedCountryDetails extends AppCompatActivity implements FragmentNavigation {

    private Intent getCountryDetailsIntent;
    private ConstraintLayout constraintLayout;


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
    private TextView selectedCountryLearnEvenMoreTextView;


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

    private FloatingActionButton fab;
    private CountryDatabaseHelper countryDatabaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_country_details);

        getCountryDetailsIntent = getIntent();
        countryDatabaseHelper = CountryDatabaseHelper.getInstance(this);

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
        fab = findViewById(R.id.favourites_fab_button);
        selectedCountryLearnEvenMoreTextView = findViewById(R.id.country_details_research_more_textview);
        constraintLayout = findViewById(R.id.selected_country_container);


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


        boolean isFavorite = countryDatabaseHelper.isFavorite(selectedCountryName);
        fab.setImageResource(isFavorite ? R.drawable.ic_done : R.drawable.ic_save);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                boolean isFavorite = countryDatabaseHelper.isFavorite(selectedCountryName);

                if (isFavorite) {
                    countryDatabaseHelper.deleteFavorite(selectedCountryName);
                    fab.setImageResource(R.drawable.ic_save);

                    Snackbar favoriteUnsavedSnackbar = Snackbar.make(constraintLayout, "Favorite unsaved.", Snackbar.LENGTH_LONG);
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

                    countryDatabaseHelper.addFavorite(Country.from(selectedCountryName, selectedCountryFlag));
                    fab.setImageResource(R.drawable.ic_done);

                    Snackbar favoriteSavedSnackbar = Snackbar.make(constraintLayout, "Favorite saved.", Snackbar.LENGTH_LONG);
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


        selectedCountryLearnEvenMoreTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String internetURLForSelectedCountry = "https://en.wikipedia.org/wiki/" + selectedCountryName;

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(internetURLForSelectedCountry));
                startActivity(intent);
            }
        });


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

