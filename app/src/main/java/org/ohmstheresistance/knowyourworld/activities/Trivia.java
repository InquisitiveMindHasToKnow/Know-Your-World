package org.ohmstheresistance.knowyourworld.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.ohmstheresistance.knowyourworld.R;
import org.ohmstheresistance.knowyourworld.database.CountryTriviaDBHelper;
import org.ohmstheresistance.knowyourworld.model.Country;
import org.ohmstheresistance.knowyourworld.model.TriviaQuestions;
import org.ohmstheresistance.knowyourworld.network.CountryService;
import org.ohmstheresistance.knowyourworld.network.RetrofitSingleton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Trivia extends AppCompatActivity {

    public static final String EXTRA_SCORE = "extraScore";
    private static final long COUNTDOWN_TIMER_IN_MILLIS = 20000;
    private static final String SCORE_KEY = "score";
    private static final String QUESTION_COUNT = "questionCount";
    private static final String MILLIS_LEFT_KEY = "millisLeft";
    private static final String ANSWERED_KEY = "answered";
    private static final String QUESTION_LIST_KEY = "questionListKey";

    private static final String FIRST_COUNTRY_NAME = "firstCountryName";
    private static final String FIRST_COUNTRY_CAPITAL = "firstCountryCapital";
    private static final String SECOND_COUNTRY_NAME = "secondCountryName";
    private static final String SECOND_COUNTRY_CAPITAL = "secondCountryCapital";
    private static final String THIRD_COUNTRY_NAME = "thirdCountryName";
    private static final String THIRD_COUNTRY_REGION = "thirdCountryRegion";
    private static final String FOURTH_COUNTRY_NAME = "fourthCountryName";
    private static final String FOURTH_COUNTRY_CAPITAL = "fourthCountryCapital";
    private static final String FIFTH_COUNTRY_NAME = "fifthCountryName";
    private static final String FIFTH_COUNTRY_REGION = "fifthCountryRegion";
    private static final String SIXTH_COUNTRY_NAME = "sixthCountryName";
    private static final String SIXTH_COUNTRY_SIZE = "sixthCountrySize";
    private static final String SEVENTH_COUNTRY_NAME = "seventhCountryName";
    private static final String SEVENTH_COUNTRY_FLAG = "seventhCountryFlag";



    private CountryTriviaDBHelper countryTriviaDBHelper;


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
    private WebView flagForTriviaWebview;

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

    private List<Country> countryListForTrivia;
    SharedPreferences.Editor triviaSharedPrefsEditor;
    SharedPreferences triviaSharedPrefs;


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
        flagForTriviaWebview = findViewById(R.id.flag_picture_for_trivia_webview);


        textColorDefaultRadioButtons = firstRadioButton.getTextColors();
        textColorDefaultCountDown = countdownTextView.getTextColors();

        getCountryInformation();

        countryTriviaDBHelper = new CountryTriviaDBHelper(Trivia.this);

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

            if(currentQuestion.getQuestion().equals(countryTriviaDBHelper.getAllQuestions().get(9).getQuestion())){

                flagForTriviaWebview.setVisibility(View.VISIBLE);
                questionTextView.setText("The above flag represents what country?");

                String html = "<html><body><img src=\"" + triviaSharedPrefs.getString(SEVENTH_COUNTRY_FLAG, "No Flag") + "\" width=\"100%\" height=\"100%\"\"/></body></html>";
                flagForTriviaWebview.setBackgroundColor(Color.TRANSPARENT);
                flagForTriviaWebview.loadData(html, "text/html", null);


                firstRadioButton.setText(currentQuestion.getFirstAnswerOption());
                secondRadioButton.setText(currentQuestion.getSecondAnswerOption());
                thirdRadioButton.setText(currentQuestion.getThirdAnswerOption());

            }else{

                flagForTriviaWebview.setVisibility(View.INVISIBLE);
            }

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

            countryTriviaDBHelper.clearDatabase();
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
            score+=5;
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
            countryTriviaDBHelper.clearDatabase();
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

    public void getCountryInformation() {

        Retrofit countryRetrofit = RetrofitSingleton.getRetrofitInstance();
        CountryService countryService = countryRetrofit.create(CountryService.class);
        countryService.getCountries().enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {


                Log.d("TR ", "Retrofit call works " + response.body().get(0).getName());


                if (response.body() != null) {

                    countryListForTrivia = response.body();
                    Collections.shuffle(countryListForTrivia);


                   String countryName = countryListForTrivia.get(0).getName();
                   String countryCapital = countryListForTrivia.get(0).getCapital();
                   String countryNameOne = countryListForTrivia.get(1).getName();
                   String countryCapitalOne = countryListForTrivia.get(1).getCapital();
                   String countryThreeName = countryListForTrivia.get(2).getName();
                   String countryThreeRegion = countryListForTrivia.get(2).getRegion();
                   String countryFourName = countryListForTrivia.get(10).getName();
                   String countryFourCapital = countryListForTrivia.get(10).getCapital();
                   String countryFiveName = countryListForTrivia.get(25).getName();
                   String countryFiveRegion = countryListForTrivia.get(25).getRegion();
                   String countrySixName = countryListForTrivia.get(50).getName();
                   String countrySixSize = String.valueOf(countryListForTrivia.get(50).getArea());
                   String countrySevenName = countryListForTrivia.get(43).getName();
                   String countrySevenFlag = countryListForTrivia.get(43).getFlag();





                    triviaSharedPrefs = PreferenceManager.getDefaultSharedPreferences(Trivia.this);
                    triviaSharedPrefsEditor = triviaSharedPrefs.edit();
                    triviaSharedPrefsEditor.putString(FIRST_COUNTRY_NAME, countryName);
                    triviaSharedPrefsEditor.putString(FIRST_COUNTRY_CAPITAL, countryCapital);
                    triviaSharedPrefsEditor.putString(SECOND_COUNTRY_NAME, countryNameOne);
                    triviaSharedPrefsEditor.putString(SECOND_COUNTRY_CAPITAL, countryCapitalOne);
                    triviaSharedPrefsEditor.putString(THIRD_COUNTRY_NAME, countryThreeName);
                    triviaSharedPrefsEditor.putString(THIRD_COUNTRY_REGION, countryThreeRegion);
                    triviaSharedPrefsEditor.putString(FOURTH_COUNTRY_NAME, countryFourName);
                    triviaSharedPrefsEditor.putString(FOURTH_COUNTRY_CAPITAL, countryFourCapital);
                    triviaSharedPrefsEditor.putString(FIFTH_COUNTRY_NAME, countryFiveName);
                    triviaSharedPrefsEditor.putString(FIFTH_COUNTRY_REGION, countryFiveRegion);
                    triviaSharedPrefsEditor.putString(SIXTH_COUNTRY_NAME, countrySixName);
                    triviaSharedPrefsEditor.putString(SIXTH_COUNTRY_SIZE, countrySixSize);
                    triviaSharedPrefsEditor.putString(SEVENTH_COUNTRY_NAME, countrySevenName);
                    triviaSharedPrefsEditor.putString(SEVENTH_COUNTRY_FLAG, countrySevenFlag);
                    triviaSharedPrefsEditor.apply();


                    if(countryName != null && countryCapital != null) {
                        Log.e("COUNTRYNAMEFORTRIV", countryName);
                        Log.e("COUNTRYCAPFORTRIV", countryCapital);
                        Log.e("COUNTRYNAMEFORTRIV1", countryNameOne);
                        Log.e("COUNTRYCAPFORTRIV1", countryCapitalOne);
                        Log.e("SIZEOFTRIVIALISTNEW", String.valueOf(countryListForTrivia.size()));
                        Log.e("COUNTRYNAMEFORTRIV2", countryThreeName);

                    }


                }

                if (!response.isSuccessful()) {
                    Log.d("Country", "Unable To Display Empty List: " + response.body());

                    return;
                }

            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {

                Log.d("Country", "Retrofit call failed, Omar" + t.getMessage());


            }

        });


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

