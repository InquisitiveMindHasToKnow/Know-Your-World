package org.ohmstheresistance.knowyourworld;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.ohmstheresistance.knowyourworld.activities.GetRandomCountry;
import org.ohmstheresistance.knowyourworld.activities.Study;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView globeImageView;
    private Button quizButton;
    private Button studyButton;
    private Button randomizeButton;
    private Button dontKnowYet;
    private Intent toQuizIntent;
    private Intent toStudyIntent;
    private Intent randomizeIntent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        globeImageView = findViewById(R.id.globe_imageview);
        quizButton = findViewById(R.id.quiz_button);
        studyButton = findViewById(R.id.study_button);;
        randomizeButton = findViewById(R.id.randomize_button);
        dontKnowYet = findViewById(R.id.button4);


        quizButton.setOnClickListener(this);
        studyButton.setOnClickListener(this);
        randomizeButton.setOnClickListener(this);
        dontKnowYet.setOnClickListener(this);

        Glide.with(this).load(R.drawable.rotating_earth).into(globeImageView);
        globeImageView.setBackgroundColor(Color.TRANSPARENT);


        }

    @Override
    public void onClick(View v) {

        int id=v.getId();
        switch(id) {
            case R.id.study_button:
                toStudyIntent = new Intent(MainActivity.this, Study.class);
                startActivity(toStudyIntent);
                break;

            case R.id.quiz_button:
                Toast.makeText(getBaseContext(), "quiz", Toast.LENGTH_LONG).show();

                break;

            case R.id.randomize_button:

                randomizeIntent = new Intent(MainActivity.this, GetRandomCountry.class);
                startActivity(randomizeIntent);

                break;

            case R.id.button4:
                Toast.makeText(getBaseContext(), "idky", Toast.LENGTH_LONG).show();

                break;
        }

    }

}
