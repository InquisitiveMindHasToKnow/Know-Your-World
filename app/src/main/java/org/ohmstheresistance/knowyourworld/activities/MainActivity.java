package org.ohmstheresistance.knowyourworld.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.ohmstheresistance.knowyourworld.R;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView globeImageView;
    private Button quizButton;
    private Button studyButton;
    private Button randomizeButton;
    private Button dontKnowYet;
    private Intent toQuizIntent;
    private Intent toStudyIntent;
    private Intent randomizeIntent;
    private Intent toFavoritesIntent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        globeImageView = findViewById(R.id.globe_imageview);
        quizButton = findViewById(R.id.quiz_button);
        studyButton = findViewById(R.id.study_button);;
        randomizeButton = findViewById(R.id.randomize_button);
        dontKnowYet = findViewById(R.id.favorites_button);


        quizButton.setOnClickListener(this);
        studyButton.setOnClickListener(this);
        randomizeButton.setOnClickListener(this);
        dontKnowYet.setOnClickListener(this);

        Glide.with(this).load(R.drawable.spinningearth).into(globeImageView);
        globeImageView.setBackgroundColor(Color.TRANSPARENT);


        }

    @Override
    public void onClick(View v) {

        int id=v.getId();
        switch(id) {
            case R.id.study_button:
                toStudyIntent = new Intent(MainActivity.this, Study.class);
                startActivity(toStudyIntent);
                overridePendingTransition(0, 0);

                break;

            case R.id.quiz_button:
                toQuizIntent = new Intent(MainActivity.this, CountryTrivia.class);
                startActivity(toQuizIntent);
                overridePendingTransition(0, 0);


                break;

            case R.id.randomize_button:

                randomizeIntent = new Intent(MainActivity.this, GetRandomCountry.class);
                startActivity(randomizeIntent);
                overridePendingTransition(0, 0);

                break;

            case R.id.favorites_button:
                toFavoritesIntent = new Intent(MainActivity.this, FavoriteCountries.class);
                startActivity(toFavoritesIntent);
                overridePendingTransition(0, 0);
                break;
        }

    }

}
