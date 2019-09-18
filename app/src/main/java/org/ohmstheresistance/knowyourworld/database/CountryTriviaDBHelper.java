package org.ohmstheresistance.knowyourworld.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

import org.ohmstheresistance.knowyourworld.model.TriviaQuestions;
import org.ohmstheresistance.knowyourworld.activities.TriviaContract.*;


import java.util.ArrayList;


public class CountryTriviaDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "CountryTrivia.db";
    private static final int DATABASE_VERSION = 15;
    private SQLiteDatabase countryTriviaDatabase;
    private Context context;
    public static SharedPreferences sp;

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
    private static final String EIGHTH_COUNTRY_NAME = "eighthCountryName";
    private static final String EIGHTH_COUNTRY_FLAG = "eighthCountryFlag";
    private static final String NINTH_COUNTRY_NAME = "ninthCountryName";
    private static final String NINTH_COUNTRY_FLAG = "ninthCountryFlag";
    private static final String TENTH_COUNTRY_NAME = "tenthCountryName";
    private static final String TENTH_COUNTRY_FLAG = "tenthCountryFlag";
    private static final String ELEVENTH_COUNTRY_NAME = "eleventhCountryName";
    private static final String ELEVENTH_COUNTRY_CAPITAL = "eleventhCountryCapital";


    public CountryTriviaDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.countryTriviaDatabase = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.QUESTION_COLUMN + " TEXT, " +
                QuestionsTable.FIRST_ANSWER__OPTION_COLUMN + " TEXT, " +
                QuestionsTable.SECOND_ANSWER__OPTION_COLUMN + " TEXT, " +
                QuestionsTable.THIRD_ANSWER__OPTION_COLUMN + " TEXT, " +
                QuestionsTable.ANSWER_NUMBER_COLUMN + " INTEGER" +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillQuestionsTable() {

        sp = PreferenceManager
                .getDefaultSharedPreferences(context);

        String firstCountryName = sp.getString(FIRST_COUNTRY_NAME, "");
        String firstCountryCapital = sp.getString(FIRST_COUNTRY_CAPITAL, "");
        String secondCountryName = sp.getString(SECOND_COUNTRY_NAME, "");
        String secondCountryCapital = sp.getString(SECOND_COUNTRY_CAPITAL, "");
        String thirdCountryName = sp.getString(THIRD_COUNTRY_NAME, "");
        String thirdCountryRegion = sp.getString(THIRD_COUNTRY_REGION, "");
        String fourthCountryName = sp.getString(FOURTH_COUNTRY_NAME, "");
        String fourthCountryCapital = sp.getString(FOURTH_COUNTRY_CAPITAL, "");
        String fifthCountryName = sp.getString(FIFTH_COUNTRY_NAME, "");
        String fifthCountryRegion = sp.getString(FIFTH_COUNTRY_REGION, "");
        String sixthCountryName = sp.getString(SIXTH_COUNTRY_NAME, "");
        String sixthCountrySize = sp.getString(SIXTH_COUNTRY_SIZE, "");
        String seventhCountryName = sp.getString(SEVENTH_COUNTRY_NAME, "");
        String seventhCountryFlag = sp.getString(SEVENTH_COUNTRY_FLAG, "");
        String eighthCountryName = sp.getString(EIGHTH_COUNTRY_NAME, "");
        String eighthCountryFlag = sp.getString(EIGHTH_COUNTRY_FLAG, "");
        String ninthCountryName = sp.getString(NINTH_COUNTRY_NAME, "");
        String ninthCountryFlag = sp.getString(NINTH_COUNTRY_FLAG, "");
        String tenthCountryName = sp.getString(TENTH_COUNTRY_NAME, "");
        String tenthCountryFlag = sp.getString(TENTH_COUNTRY_FLAG, "");
        String eleventhCountryName = sp.getString(ELEVENTH_COUNTRY_NAME, "");
        String eleventhCountryCapital = sp.getString(ELEVENTH_COUNTRY_CAPITAL, "");


        TriviaQuestions questionOne = new TriviaQuestions("_____ is the capital of Jamaica", "Kingston", "Spanish Town", "Canada", 1);
        addQuestion(questionOne);

        TriviaQuestions questionTwo = new TriviaQuestions("What is the capital of " + eleventhCountryName + "?", "Albany", eleventhCountryCapital, "Clarendon", 2);
        addQuestion(questionTwo);

        TriviaQuestions questionThree = new TriviaQuestions(sixthCountryName + " has an area of ________ km² ", "398342.8km²", sixthCountrySize +"km²", "922404.2km²", 2);
        addQuestion(questionThree);

        TriviaQuestions questionFour = new TriviaQuestions(secondCountryCapital + " is to " + secondCountryName + " as " + fourthCountryCapital +" is to", firstCountryName, fifthCountryName, fourthCountryName, 3);
        addQuestion(questionFour);

        TriviaQuestions questionFive = new TriviaQuestions("Niger is on the continent of ", "Africa", firstCountryCapital, fourthCountryName, 1);
        addQuestion(questionFive);

        TriviaQuestions questionSix = new TriviaQuestions(firstCountryCapital + " is the capital of ", fourthCountryName, secondCountryCapital, firstCountryName, 3);
        addQuestion(questionSix);

        TriviaQuestions questionSeven = new TriviaQuestions(secondCountryCapital + " is the capital of ", secondCountryName, fifthCountryName, thirdCountryName , 1);
        addQuestion(questionSeven);

        TriviaQuestions questionEight = new TriviaQuestions(thirdCountryName + " is located in ", fifthCountryName, thirdCountryRegion, fourthCountryCapital , 2);
        addQuestion(questionEight);

        TriviaQuestions questionNine = new TriviaQuestions( thirdCountryName + " is located in " + thirdCountryRegion + " and " + fifthCountryName + " is located in ", fifthCountryRegion, secondCountryName, firstCountryCapital , 1);
        addQuestion(questionNine);

        TriviaQuestions questionTen = new TriviaQuestions( seventhCountryFlag,  seventhCountryName , firstCountryName, secondCountryName, 1);
        addQuestion(questionTen);

        TriviaQuestions questionEleven = new TriviaQuestions( eighthCountryFlag,  thirdCountryName , eighthCountryName, fifthCountryName, 2);
        addQuestion(questionEleven);

        TriviaQuestions questionTwelve = new TriviaQuestions( ninthCountryFlag,  secondCountryName , ninthCountryName, fifthCountryName, 2);
        addQuestion(questionTwelve);

        TriviaQuestions questionThirteen = new TriviaQuestions( tenthCountryFlag,  sixthCountryName , firstCountryName, tenthCountryName, 3);
        addQuestion(questionThirteen);
    }

    private void addQuestion(TriviaQuestions question) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(QuestionsTable.QUESTION_COLUMN, question.getQuestion());
        contentValues.put(QuestionsTable.FIRST_ANSWER__OPTION_COLUMN, question.getFirstAnswerOption());
        contentValues.put(QuestionsTable.SECOND_ANSWER__OPTION_COLUMN, question.getSecondAnswerOption());
        contentValues.put(QuestionsTable.THIRD_ANSWER__OPTION_COLUMN, question.getThirdAnswerOption());
        contentValues.put(QuestionsTable.ANSWER_NUMBER_COLUMN, question.getAnswerNumber());
        countryTriviaDatabase.insert(QuestionsTable.TABLE_NAME, null, contentValues);
    }

    public ArrayList<TriviaQuestions> getAllQuestions() {
        ArrayList<TriviaQuestions> questionList = new ArrayList<>();
        countryTriviaDatabase = getReadableDatabase();
        Cursor cursor = countryTriviaDatabase.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                TriviaQuestions question = new TriviaQuestions();
                question.setQuestion(cursor.getString(cursor.getColumnIndex(QuestionsTable.QUESTION_COLUMN)));
                question.setFirstAnswerOption(cursor.getString(cursor.getColumnIndex(QuestionsTable.FIRST_ANSWER__OPTION_COLUMN)));
                question.setSecondAnswerOption(cursor.getString(cursor.getColumnIndex(QuestionsTable.SECOND_ANSWER__OPTION_COLUMN)));
                question.setThirdAnswerOption(cursor.getString(cursor.getColumnIndex(QuestionsTable.THIRD_ANSWER__OPTION_COLUMN)));
                question.setAnswerNumber(cursor.getInt(cursor.getColumnIndex(QuestionsTable.ANSWER_NUMBER_COLUMN)));
                questionList.add(question);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return questionList;
    }


    public void clearDatabase() {

        context.deleteDatabase(DATABASE_NAME);
    }
}

