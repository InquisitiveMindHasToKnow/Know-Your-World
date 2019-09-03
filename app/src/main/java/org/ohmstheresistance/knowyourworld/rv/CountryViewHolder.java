package org.ohmstheresistance.knowyourworld.rv;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import org.ohmstheresistance.knowyourworld.R;
import org.ohmstheresistance.knowyourworld.fragments.FragmentNavigation;
import org.ohmstheresistance.knowyourworld.fragments.GoogleMapsFragment;
import org.ohmstheresistance.knowyourworld.model.Country;



public class CountryViewHolder extends RecyclerView.ViewHolder {

    private WebView countryFlagImageView;
    private TextView countryNameTextView;
    private TextView countryCapitalTextView;

    private FragmentNavigation fragmentNavigation;

    private String randomCountry;
    private String latitude;
    private String longitude;

    private static final String RANDOM_COUNTRY_KEY = "randomCountryKey";
    private static final String RANDOM_COUNTRY_LATITUDE_KEY = "randomCountryLatitudeKey";
    private static final String RANDOM_COUNTRY_LONGITUDE_KEY = "randomCountryLongitudeKey";

    public CountryViewHolder(@NonNull View itemView) {
        super(itemView);

        countryFlagImageView = itemView.findViewById(R.id.country_flag_imageview);
        countryNameTextView = itemView.findViewById(R.id.country_name_textview);
        countryCapitalTextView = itemView.findViewById(R.id.country_capital_textview);

    }

    public void onBind(final Country country) {

        String countryFlag = country.getFlag();
        final String countryName = country.getName();
        String countryCapital = country.getCapital();


        Log.d("Flag ", "Flag Link: " + countryFlag);

        countryNameTextView.setText(countryName);
        countryCapitalTextView.setText(countryCapital);


        countryFlagImageView.getSettings().setLoadWithOverviewMode(true);
        countryFlagImageView.getSettings().setUseWideViewPort(true);
        countryFlagImageView.setVerticalScrollBarEnabled(false);
        countryFlagImageView.setHorizontalScrollBarEnabled(false);
        countryFlagImageView.setScrollContainer(false);
        countryFlagImageView.setInitialScale(1);
        countryFlagImageView.setBackgroundColor(Color.TRANSPARENT);
        countryFlagImageView.loadUrl(countryFlag);


        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mapIntent = new Intent(v.getContext(), GoogleMapsFragment.class);
                Bundle mapBundle = new Bundle();

                String countryLocationCoords = country.getLatlng().toString();

                String[] countryLocation = countryLocationCoords.split(",", 2);
                String latitude = countryLocation[0].substring(1);
                String longitude = countryLocation[1].substring(0, countryLocation[1].length() - 1);


                mapBundle.putString(RANDOM_COUNTRY_KEY, countryName );
                mapBundle.putString(RANDOM_COUNTRY_LATITUDE_KEY, latitude);
                mapBundle.putString(RANDOM_COUNTRY_LONGITUDE_KEY, longitude);
                mapIntent.putExtras(mapBundle);

                fragmentNavigation = (FragmentNavigation) v.getContext();
                fragmentNavigation.goToLocationOnMap(longitude, latitude, countryName);

            }
        });

    }

}
