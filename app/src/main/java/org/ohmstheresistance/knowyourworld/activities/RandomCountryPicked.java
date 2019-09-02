package org.ohmstheresistance.knowyourworld.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.ohmstheresistance.knowyourworld.R;

public class RandomCountryPicked extends AppCompatActivity {


    private Intent getRandomCountryIntent;
    private TextView randomCountryChosenNameTextview;

    private String randomCountry;
    private static final String RANDOM_COUNTRY_KEY = "randomCountryKey";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_country_picked);

        randomCountryChosenNameTextview = findViewById(R.id.random_country_chosen_textview);


        getRandomCountryIntent = getIntent();

        randomCountry = getRandomCountryIntent.getStringExtra(RANDOM_COUNTRY_KEY);

        randomCountryChosenNameTextview.setText(randomCountry);

    }
}
