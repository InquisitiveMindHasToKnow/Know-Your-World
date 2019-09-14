package org.ohmstheresistance.knowyourworld.rv;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.ohmstheresistance.knowyourworld.R;
import org.ohmstheresistance.knowyourworld.activities.SelectedCountryDetails;
import org.ohmstheresistance.knowyourworld.fragments.FragmentNavigation;
import org.ohmstheresistance.knowyourworld.fragments.GoogleMapsFragment;
import org.ohmstheresistance.knowyourworld.model.Country;

import java.util.List;


public class CountryViewHolder extends RecyclerView.ViewHolder {

    private WebView countryFlagImageView;
    private TextView countryNameTextView;
    private TextView countryCapitalTextView;
    private Button learnMoreButton;

    private FragmentNavigation fragmentNavigation;

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


    public CountryViewHolder(@NonNull View itemView) {
        super(itemView);

        countryFlagImageView = itemView.findViewById(R.id.country_flag_imageview);
        countryNameTextView = itemView.findViewById(R.id.country_name_textview);
        countryCapitalTextView = itemView.findViewById(R.id.country_capital_textview);
        learnMoreButton = itemView.findViewById(R.id.learn_more_button);

    }

    public void onBind(final Country country) {

        final String countryFlag = country.getFlag();
        final String countryName = country.getName();
        String countryCapital = country.getCapital();

        countryNameTextView.setText(countryName);
        countryCapitalTextView.setText(countryCapital);

        learnMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent toCountryDetailsIntent = new Intent(v.getContext(), SelectedCountryDetails.class);
                Bundle mapBundle = new Bundle();

                String countryLocationCoords = country.getLatlng().toString();

                String[] countryLocation = countryLocationCoords.split(",", 2);
                String selectedCountryLatitude = countryLocation[0].substring(1);
                String selectedCountryLongitude = countryLocation[1].substring(0, countryLocation[1].length() - 1);


                String selectedCountryFlag = country.getFlag();
                String selectedCountryName = country.getName();
                String selectedCountryCapital = country.getCapital();
                String selectedCountryPopulation = String.valueOf(country.getPopulation());
                String selectedCountryRegion = country.getRegion();
                String selectedCountrySubRegion = country.getSubregion();
                String selectedCountryCioc = country.getCioc();
                String selectedCountryArea = String.valueOf(country.getArea());
                String selectedCountryAlpha2Code = country.getAlpha2Code();
                String selectedCountryAlpha3Code = country.getAlpha3Code();
                String selectedCountryLanguageNativeName = country.getNativeName();


                List<String> selectedCountryBorders = country.getBorders();
                Country.Currency selectedCountryCurrencies = country.getCurrencies().get(0);
                Country.Language selectedCountryLanguagesSpoken = country.getLanguages().get(0);

                mapBundle.putString(SELECTED_COUNTRY_NAME_KEY, selectedCountryName);
                mapBundle.putString(SELECTED_COUNTRY_LATITUDE_KEY, selectedCountryLatitude);
                mapBundle.putString(SELECTED_COUNTRY_LONGITUDE_KEY, selectedCountryLongitude);
                mapBundle.putString(SELECTED_COUNTRY_FLAG_KEY, selectedCountryFlag);
                mapBundle.putString(SELECTED_COUNTRY_POPULATION_KEY, selectedCountryPopulation);
                mapBundle.putString(SELECTED_COUNTRY_CAPITAL_KEY, selectedCountryCapital);
                mapBundle.putString(SELECTED_COUNTRY_REGION_KEY, selectedCountryRegion);
                mapBundle.putString(SELECTED_COUNTRY_SUBREGION_KEY, selectedCountrySubRegion);
                mapBundle.putString(SELECTED_COUNTRY_BORDERS_KEY, String.valueOf(selectedCountryBorders));
                mapBundle.putString(SELECTED_COUNTRY_CURRENCIES_KEY, String.valueOf(selectedCountryCurrencies.getName()));
                mapBundle.putString(SELECTED_COUNTRY_LANGUAGES_KEY, String.valueOf(selectedCountryLanguagesSpoken.getName()));
                mapBundle.putString(SELECTED_COUNTRY_CIOC_KEY, selectedCountryCioc);
                mapBundle.putString(SELECTED_COUNTRY_AREA_KEY, selectedCountryArea);
                mapBundle.putString(SELECTED_COUNTRY_ALPHA_CODE_2_KEY, selectedCountryAlpha2Code);
                mapBundle.putString(SELECTED_COUNTRY_ALPHA_CODE_3_KEY, selectedCountryAlpha3Code);
                mapBundle.putString(SELECTED_COUNTRY_NATIVE_NAME_KEY, selectedCountryLanguageNativeName);

                toCountryDetailsIntent.putExtras(mapBundle);
                v.getContext().startActivity(toCountryDetailsIntent);

            }
        });

        //        Picasso.get()
//                .load(R.drawable.cupid2)
//                .into(loginPageImage);

        String html = "<html><body><img src=\"" + countryFlag + "\" width=\"100%\" height=\"100%\"\"/></body></html>";
        countryFlagImageView.setBackgroundColor(Color.TRANSPARENT);
        countryFlagImageView.loadData(html, "text/html", null);


        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mapIntent = new Intent(v.getContext(), GoogleMapsFragment.class);
                Bundle mapBundle = new Bundle();

                String countryLocationCoords = country.getLatlng().toString();

                String[] countryLocation = countryLocationCoords.split(",", 2);
                String selectedCountryLatitude = countryLocation[0].substring(1);
                String selectedCountryLongitude = countryLocation[1].substring(0, countryLocation[1].length() - 1);


                String selectedCountryFlag = country.getFlag();
                String selectedCountryName = country.getName();
                String selectedCountryCapital = country.getCapital();
                String selectedCountryPopulation = String.valueOf(country.getPopulation());
                String selectedCountryRegion = country.getRegion();
                String selectedCountrySubRegion = country.getSubregion();
                String selectedCountryCioc = country.getCioc();
                String selectedCountryArea = String.valueOf(country.getArea());
                String selectedCountryAlpha2Code = country.getAlpha2Code();
                String selectedCountryAlpha3Code = country.getAlpha3Code();

                List<String> selectedCountryBorders = country.getBorders();
                Country.Currency selectedCountryCurrencies = country.getCurrencies().get(0);
                Country.Language selectedCountryLanguagesSpoken = country.getLanguages().get(0);

                mapBundle.putString(SELECTED_COUNTRY_NAME_KEY, selectedCountryName);
                mapBundle.putString(SELECTED_COUNTRY_LATITUDE_KEY, selectedCountryLatitude);
                mapBundle.putString(SELECTED_COUNTRY_LONGITUDE_KEY, selectedCountryLongitude);
                mapBundle.putString(SELECTED_COUNTRY_FLAG_KEY, selectedCountryFlag);
                mapBundle.putString(SELECTED_COUNTRY_POPULATION_KEY, selectedCountryPopulation);
                mapBundle.putString(SELECTED_COUNTRY_CAPITAL_KEY, selectedCountryCapital);
                mapBundle.putString(SELECTED_COUNTRY_REGION_KEY, selectedCountryRegion);
                mapBundle.putString(SELECTED_COUNTRY_SUBREGION_KEY, selectedCountrySubRegion);
                mapBundle.putString(SELECTED_COUNTRY_BORDERS_KEY, String.valueOf(selectedCountryBorders));
                mapBundle.putString(SELECTED_COUNTRY_CURRENCIES_KEY, String.valueOf(selectedCountryCurrencies.getName()));
                mapBundle.putString(SELECTED_COUNTRY_LANGUAGES_KEY, String.valueOf(selectedCountryLanguagesSpoken.getName()));
                mapBundle.putString(SELECTED_COUNTRY_CIOC_KEY, selectedCountryCioc);
                mapBundle.putString(SELECTED_COUNTRY_AREA_KEY, selectedCountryArea);
                mapBundle.putString(SELECTED_COUNTRY_ALPHA_CODE_2_KEY, selectedCountryAlpha2Code);
                mapBundle.putString(SELECTED_COUNTRY_ALPHA_CODE_3_KEY, selectedCountryAlpha3Code);

                mapIntent.putExtras(mapBundle);


                fragmentNavigation = (FragmentNavigation) v.getContext();
                fragmentNavigation.goToLocationOnMap(selectedCountryLongitude, selectedCountryLatitude, countryName, countryFlag);


            }
        });

    }

}
