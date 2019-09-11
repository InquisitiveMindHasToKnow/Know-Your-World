package org.ohmstheresistance.knowyourworld.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.ohmstheresistance.knowyourworld.R;

public class CountryTrivia extends AppCompatActivity {

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

        startTestKnowledgeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CountryTrivia.this, Trivia.class);
                startActivity(intent);
                overridePendingTransition(0, 0);


            }
        });

    }
}
