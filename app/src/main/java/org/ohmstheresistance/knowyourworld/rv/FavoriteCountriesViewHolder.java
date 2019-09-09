package org.ohmstheresistance.knowyourworld.rv;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import org.ohmstheresistance.knowyourworld.R;
import org.ohmstheresistance.knowyourworld.model.Country;


public class FavoriteCountriesViewHolder extends RecyclerView.ViewHolder {

    private WebView favoriteCountryFlagWebView;
    private TextView favoriteCountryNameTextView;

    public FavoriteCountriesViewHolder(@NonNull View itemView) {
        super(itemView);

        favoriteCountryFlagWebView = itemView.findViewById(R.id.favorite_country_flag_webview);
        favoriteCountryNameTextView = itemView.findViewById(R.id.favorite_country_name_textview);

    }

    public void onBind(final Country favoriteCountries) {

        final String favoriteCountryFlag = favoriteCountries.getFlag();
        final String favoriteCountryName = favoriteCountries.getName();

        favoriteCountryFlagWebView.getSettings().setLoadWithOverviewMode(true);
        favoriteCountryFlagWebView.getSettings().setUseWideViewPort(true);
        favoriteCountryFlagWebView.setVerticalScrollBarEnabled(false);
        favoriteCountryFlagWebView.setHorizontalScrollBarEnabled(false);
        favoriteCountryFlagWebView.setScrollContainer(false);
        favoriteCountryFlagWebView.setInitialScale(1);
        favoriteCountryFlagWebView.setBackgroundColor(Color.TRANSPARENT);
        favoriteCountryFlagWebView.loadUrl(favoriteCountryFlag);

        favoriteCountryNameTextView.setText(favoriteCountryName);

    }
}