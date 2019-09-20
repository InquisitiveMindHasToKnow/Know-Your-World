package org.ohmstheresistance.knowyourworld.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.ohmstheresistance.knowyourworld.R;

public class CountryTrivia extends AppCompatActivity {

    private static final int TRIVIA_REQUEST_CODE = 1;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String HIGH_SCORE_KEY = "highScoreKey";

    private int highScore;
    private int score;

    private TextView triviaHeaderTextview;
    private TextView triviaHighScoreTextview;
    private TextView yourScoreTextView;
    private Button resetHighScoreButton;
    private Button startTestKnowledgeButton;

    ImageView confettiImage;
    private static int SHOW_COFETTI_TIMER = 6000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_trivia);

        triviaHeaderTextview = findViewById(R.id.trivia_name_header_textview);
        triviaHighScoreTextview = findViewById(R.id.trivia_highscore_textview);
        yourScoreTextView = findViewById(R.id.your_score_textview);
        startTestKnowledgeButton = findViewById(R.id.start_trivia_button);
        resetHighScoreButton = findViewById(R.id.reset_high_score_button);
        confettiImage = findViewById(R.id.confetti_imageview);

        loadHighScore();

        startTestKnowledgeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent toTriviaIntent = new Intent(CountryTrivia.this, Trivia.class);
                startActivityForResult(toTriviaIntent, TRIVIA_REQUEST_CODE);
                overridePendingTransition(0, 0);

            }
        });


        showOrHideResetHighScoreButton();

        resetHighScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder dialog = new AlertDialog.Builder(CountryTrivia.this);
                dialog.setCancelable(false);
                dialog.setTitle("Wait, Wait, Wait!");
                dialog.setMessage("Are you sure you want to clear your high score?");
                dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog1, int id) {

                        resetHighScore();
                        loadHighScore();
                        showOrHideResetHighScoreButton();


                    }
                })

                        .setNegativeButton("NO ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog12, int which) {

                            }
                        });

                final AlertDialog alert = dialog.create();
                alert.show();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TRIVIA_REQUEST_CODE) {

            if (resultCode == RESULT_OK) {
                score = data.getIntExtra(Trivia.EXTRA_SCORE, 0);

                yourScoreTextView.setText("Your Score: " + score);

                if (score > highScore) {

                    updateHighScore(score);
                    resetHighScoreButton.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    private void updateHighScore(int newHighScore) {

        highScore = newHighScore;
        triviaHighScoreTextview.setText("New High Score: " + highScore + "!");


        Glide.with(CountryTrivia.this)
                .load(R.drawable.confetti)
                .into(confettiImage);


        confettiImage.setVisibility(View.VISIBLE);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                confettiImage.setVisibility(View.INVISIBLE);
                triviaHighScoreTextview.setText("High Score: " + highScore);


            }
        }, SHOW_COFETTI_TIMER);


        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
        sharedPreferencesEditor.putInt(HIGH_SCORE_KEY, highScore);
        sharedPreferencesEditor.putInt(Trivia.EXTRA_SCORE, score);
        sharedPreferencesEditor.apply();
    }

    private void loadHighScore(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        highScore = sharedPreferences.getInt(HIGH_SCORE_KEY, 0);

        triviaHighScoreTextview.setText("High Score: " + highScore);

    }

    public void resetHighScore(){

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        highScore = sharedPreferences.getInt(HIGH_SCORE_KEY, 0);

        sharedPreferences.edit().remove(HIGH_SCORE_KEY).apply();

    }

    public void showOrHideResetHighScoreButton(){

        if(highScore == 0){
            resetHighScoreButton.setVisibility(View.INVISIBLE);
        }else{

            if(highScore > 0)
                resetHighScoreButton.setVisibility(View.VISIBLE);
        }

    }
}
