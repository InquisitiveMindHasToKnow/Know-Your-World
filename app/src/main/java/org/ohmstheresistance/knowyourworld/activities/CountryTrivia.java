package org.ohmstheresistance.knowyourworld.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.ohmstheresistance.knowyourworld.R;

public class CountryTrivia extends AppCompatActivity {

    private static final int TRIVIA_REQUEST_CODE = 1;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String HIGH_SCORE_KEY = "highScoreKey";

    private int highScore;

    private TextView triviaHeaderTextview;
    private TextView triviaHighScoreTextview;
    private Button startTestKnowledgeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_trivia);

        triviaHeaderTextview = findViewById(R.id.trivia_name_header_textview);
        triviaHighScoreTextview = findViewById(R.id.trivia_highscore_textview);
        startTestKnowledgeButton = findViewById(R.id.start_trivia_button);

        loadHighScore();

        startTestKnowledgeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent toTriviaIntent = new Intent(CountryTrivia.this, Trivia.class);
                startActivityForResult(toTriviaIntent, TRIVIA_REQUEST_CODE);
                overridePendingTransition(0, 0);


            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == TRIVIA_REQUEST_CODE){

            if(resultCode == RESULT_OK){
                int score = data.getIntExtra(Trivia.EXTRA_SCORE, 0);
                if(score > highScore){

                    updateHighScore(score);

                }
            }
        }
    }

    private void updateHighScore(int newHighScore ){
        highScore = newHighScore;
        triviaHighScoreTextview.setText("High Score: " + highScore);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
        sharedPreferencesEditor.putInt(HIGH_SCORE_KEY, highScore);
        sharedPreferencesEditor.apply();
    }

    private void loadHighScore(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        highScore = sharedPreferences.getInt(HIGH_SCORE_KEY, 0);
        triviaHighScoreTextview.setText("High Score: " + highScore);


    }
}
