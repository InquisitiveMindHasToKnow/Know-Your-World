package org.ohmstheresistance.knowyourworld.rv;


import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import org.ohmstheresistance.knowyourworld.R;
import org.ohmstheresistance.knowyourworld.model.Country;



public class CountryViewHolder extends RecyclerView.ViewHolder {

    private WebView countryFlagImageView;
    private TextView countryNameTextView;
    private TextView countryCapitalTextView;

    public CountryViewHolder(@NonNull View itemView) {
        super(itemView);

        countryFlagImageView = itemView.findViewById(R.id.country_flag_imageview);
        countryNameTextView = itemView.findViewById(R.id.country_name_textview);
        countryCapitalTextView = itemView.findViewById(R.id.country_capital_textview);

    }

    public void onBind(Country country) {

        String countryFlag = country.getFlag();
        String countryName = country.getName();
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

    }

}
