package org.ohmstheresistance.knowyourworld.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.ohmstheresistance.knowyourworld.R;
import org.ohmstheresistance.knowyourworld.database.CountryTriviaDBHelper;

public class TriviaTutorial extends AppCompatActivity {

    private TextView triviaInstructionsTextView;
    private Button triviaLetsBeginButton;

    CountryTriviaDBHelper  countryTriviaDBHelper = new CountryTriviaDBHelper(TriviaTutorial.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_tutorial);

        triviaInstructionsTextView = findViewById(R.id.trivia_instructions_textview);
        triviaLetsBeginButton = findViewById(R.id.trivia_tutorial_lets_begin_button);

        triviaLetsBeginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                countryTriviaDBHelper.clearDatabase();

                TriviaTutorial.this.finish();
                overridePendingTransition(0, 0);

            }
        });
    }
}
