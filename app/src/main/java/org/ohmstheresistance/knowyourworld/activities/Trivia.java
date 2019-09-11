package org.ohmstheresistance.knowyourworld.activities;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.ohmstheresistance.knowyourworld.R;
import org.ohmstheresistance.knowyourworld.database.CountryTriviaDBHelper;
import org.ohmstheresistance.knowyourworld.model.TriviaQuestions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class Trivia extends AppCompatActivity {

    public static final String EXTRA_SCORE = "extraScore";
    private static final long COUNTDOWN_TIMER_IN_MILLIS = 20000;
    private static final String SCORE_KEY = "score";
    private static final String QUESTION_COUNT = "questionCount";
    private static final String MILLIS_LEFT_KEY = "millisLeft";
    private static final String ANSWERED_KEY = "answered";
    private static final String QUESTION_LIST_KEY = "questionListKey";



    private RelativeLayout triviaRelativeLayout;
    private TextView questionTextView;
    private TextView scoreTextView;
    private TextView questionCountTextView;
    private TextView countdownTextView;
    private RadioGroup radioButtonGroup;
    private RadioButton firstRadioButton;
    private RadioButton secondRadioButton;
    private RadioButton thirdRadioButton;
    private Button confirmButton;

    private int questionCounter;
    private int questionCountTotal;
    private TriviaQuestions currentQuestion;
    private ColorStateList textColorDefaultRadioButtons;
    private ColorStateList textColorDefaultCountDown;

    private CountDownTimer countDownTimer;
    private  long timeLeftInMillis;

    private int score;
    private boolean answered;

    private long backPressedTime;

    private ArrayList<TriviaQuestions> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);

        questionTextView = findViewById(R.id.question_textview);
        scoreTextView = findViewById(R.id.trivia_score_textview);
        questionCountTextView = findViewById(R.id.question_count_textview);
        countdownTextView = findViewById(R.id.countdown_timer_textview);
        radioButtonGroup = findViewById(R.id.radio_group);
        firstRadioButton = findViewById(R.id.radio_button1);
        secondRadioButton = findViewById(R.id.radio_button2);
        thirdRadioButton = findViewById(R.id.radio_button3);
        confirmButton = findViewById(R.id.trivia_confirm_button);
        triviaRelativeLayout = findViewById(R.id.trivia_relative_layout);

        textColorDefaultRadioButtons = firstRadioButton.getTextColors();
        textColorDefaultCountDown = countdownTextView.getTextColors();


        if(savedInstanceState == null) {

            CountryTriviaDBHelper countryTriviaDBHelper = new CountryTriviaDBHelper(this);
            questionList = countryTriviaDBHelper.getAllQuestions();

            questionCountTotal = questionList.size();
            Collections.shuffle(questionList);

            showNextQuestion();
        }else{

            questionList = savedInstanceState.getParcelableArrayList(QUESTION_LIST_KEY);

            if(questionList == null){
                finish();
            }

            questionCountTotal = questionList.size();
            questionCounter = savedInstanceState.getInt(QUESTION_COUNT);
            currentQuestion = questionList.get(questionCounter - 1);
            score = savedInstanceState.getInt(SCORE_KEY);
            timeLeftInMillis = savedInstanceState.getLong(MILLIS_LEFT_KEY);
            answered = savedInstanceState.getBoolean(ANSWERED_KEY);

            if(!answered){
                startCountDown();
            }else{
                updateCountDownText();
                showSolution();
            }
        }

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!answered) {
                    if (firstRadioButton.isChecked() || secondRadioButton.isChecked() || thirdRadioButton.isChecked()) {
                        checkAnswer();
                    } else {

                        Snackbar selectAnswerSnackbar = Snackbar.make(v, "Please select an answer.", Snackbar.LENGTH_LONG);
                        View snackbarView = selectAnswerSnackbar.getView();
                        TextView snackBarTextView = snackbarView.findViewById(android.support.design.R.id.snackbar_text);

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
                            snackBarTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        else
                            snackBarTextView.setGravity(Gravity.CENTER_HORIZONTAL);

                        selectAnswerSnackbar.show();

                    }
                } else {
                    showNextQuestion();
                }
            }
        });
    }

    private void showNextQuestion() {
        firstRadioButton.setTextColor(textColorDefaultRadioButtons);
        secondRadioButton.setTextColor(textColorDefaultRadioButtons);
        thirdRadioButton.setTextColor(textColorDefaultRadioButtons);
        radioButtonGroup.clearCheck();

        if (questionCounter < questionCountTotal) {
            currentQuestion = questionList.get(questionCounter);

            questionTextView.setText(currentQuestion.getQuestion());
            firstRadioButton.setText(currentQuestion.getFirstAnswerOption());
            secondRadioButton.setText(currentQuestion.getSecondAnswerOption());
            thirdRadioButton.setText(currentQuestion.getThirdAnswerOption());

            questionCounter++;
            questionCountTextView.setText("Question: " + questionCounter + "/" + questionCountTotal);
            answered = false;
            confirmButton.setText("Confirm");

            timeLeftInMillis = COUNTDOWN_TIMER_IN_MILLIS;
            startCountDown();
        } else {
            finishQuiz();
        }
    }

    private void startCountDown(){

        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {

                timeLeftInMillis = 0;
                updateCountDownText();
                checkAnswer();
            }
        }.start();
    }

    private void updateCountDownText(){

        int minutes =(int) (timeLeftInMillis / 1000 / 60);
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String formattedTime = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        countdownTextView.setText(formattedTime);

        if(timeLeftInMillis < 5000){
            countdownTextView.setTextColor(Color.RED);
        }else{
            countdownTextView.setTextColor(textColorDefaultCountDown);
        }

    }

    private void checkAnswer() {
        answered = true;
        countDownTimer.cancel();

        RadioButton radioButtonSelected = findViewById(radioButtonGroup.getCheckedRadioButtonId());
        int answerNumber = radioButtonGroup.indexOfChild(radioButtonSelected) + 1;

        if (answerNumber == currentQuestion.getAnswerNumber()) {
            score++;
            scoreTextView.setText("Score: " + score);
        }

        showSolution();
    }

    private void showSolution() {
        firstRadioButton.setTextColor(Color.RED);
        secondRadioButton.setTextColor(Color.RED);
        thirdRadioButton.setTextColor(Color.RED);

        switch (currentQuestion.getAnswerNumber()) {
            case 1:
                firstRadioButton.setTextColor(Color.GREEN);
                questionTextView.setText("Answer 1 is correct");
                break;
            case 2:
                secondRadioButton.setTextColor(Color.GREEN);
                questionTextView.setText("Answer 2 is correct");
                break;
            case 3:
                thirdRadioButton.setTextColor(Color.GREEN);
                questionTextView.setText("Answer 3 is correct");
                break;
        }

        if (questionCounter < questionCountTotal) {
            confirmButton.setText("Next");
        } else {
            confirmButton.setText("Finish");
        }
    }

    private void finishQuiz() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE, score);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {

            finishQuiz();
        } else {

            Snackbar onBackPressedSnackBar = Snackbar.make(triviaRelativeLayout, "Press BACK again to exit.", Snackbar.LENGTH_LONG);
            View onBackPressedSnackBarView = onBackPressedSnackBar.getView();
            TextView snackBarTextView = onBackPressedSnackBarView.findViewById(android.support.design.R.id.snackbar_text);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
                snackBarTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            else
                snackBarTextView.setGravity(Gravity.CENTER_HORIZONTAL);

            onBackPressedSnackBar.show();

        }
        backPressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(countDownTimer != null){
            countDownTimer.cancel();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(SCORE_KEY, score);
        outState.putInt(QUESTION_COUNT, questionCounter);
        outState.putLong(MILLIS_LEFT_KEY, timeLeftInMillis);
        outState.putBoolean(ANSWERED_KEY, answered);
        outState.putParcelableArrayList(QUESTION_LIST_KEY, questionList);
    }
}

