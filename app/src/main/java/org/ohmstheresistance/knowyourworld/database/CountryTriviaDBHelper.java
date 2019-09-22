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
    private  SharedPreferences sp;

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
    private static final String TWELFTH_COUNTRY_NAME = "twelfthCountryName";
    private static final String TWELFTH_COUNTRY_CAPITAL = "twelfthCountryCapital";
    private static final String THIRTEENTH_COUNTRY_NAME = "thirteenthCountryName";
    private static final String THIRTEENTH_COUNTRY_CAPITAL = "thirteenthCountryCapital";
    private static final String FOURTEENTH_COUNTRY_NAME = "fourteenthCountryName";
    private static final String FOURTEENTH_COUNTRY_FLAG = "fourteenthCountryFlag";
    private static final String FIFTEENTH_COUNTRY_NAME = "fifteenthCountryName";
    private static final String FIFTEENTH_COUNTRY_FLAG = "fifteenthCountryFlag";
    private static final String SIXTEENTH_COUNTRY_NAME = "sixteenthCountryName";
    private static final String SIXTEENTH_COUNTRY_FLAG = "sixteenthCountryFlag";
    private static final String SEVENTEENTH_COUNTRY_NAME = "seventeenthCountryName";
    private static final String SEVENTEENTH_COUNTRY_CAPITAL = "seventeenthCountryCapital";
    private static final String EIGHTEENTH_COUNTRY_NAME = "eighteenthCountryName";
    private static final String EIGHTEENTH_COUNTRY_CAPITAL = "eighteenthCountryCapital";
    private static final String NINETEENTH_COUNTRY_NAME = "nineteenthCountryName";
    private static final String NINETEENTH_COUNTRY_CAPITAL = "nineteenthCountryCapital";
    private static final String TWENTIETH_COUNTRY_NAME = "twentiethCountryName";
    private static final String TWENTIETH_COUNTRY_REGION = "twentiethCountryRegion";
    private static final String TWENTYFIRST_COUNTRY_NAME = "twentyFirstCountryName";
    private static final String TWENTYFIRST_COUNTRY_FLAG = "twentyFirstCountryFlag";
    private static final String TWENTYSECOND_COUNTRY_NAME = "twentySecondCountryName";
    private static final String TWENTYSECOND_COUNTRY_FLAG = "twentySecondCountryFlag";
    private static final String TWENTYTHIRD_COUNTRY_NAME = "twentyThirdCountryName";
    private static final String TWENTYTHIRD_COUNTRY_FLAG = "twentyThirdCountryFlag";
    private static final String TWENTYFOURTH_COUNTRY_NAME = "twentyFourthCountryName";
    private static final String TWENTYFOURTH_COUNTRY_CAPITAL = "twentyFourthCountryCapital";
    private static final String TWENTYFIFTH_COUNTRY_NAME = "twentyFifthCountryName";
    private static final String TWENTYFIFTH_COUNTRY_REGION = "twentyFifthCountryRegion";


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
        String twelfthCountryName = sp.getString(TWELFTH_COUNTRY_NAME, "");
        String twelfthCountryCapital = sp.getString(TWELFTH_COUNTRY_CAPITAL, "");
        String thirteenthCountryName = sp.getString(THIRTEENTH_COUNTRY_NAME, "");
        String thirteenthCountryCapital = sp.getString(THIRTEENTH_COUNTRY_CAPITAL, "");
        String fourteenthCountryName = sp.getString(FOURTEENTH_COUNTRY_NAME, "");
        String fourteenthCountryFlag = sp.getString(FOURTEENTH_COUNTRY_FLAG, "");
        String fifteenthCountryName = sp.getString(FIFTEENTH_COUNTRY_NAME, "");
        String fifteenthCountryFlag = sp.getString(FIFTEENTH_COUNTRY_FLAG, "");
        String sixteenthCountryName = sp.getString(SIXTEENTH_COUNTRY_NAME, "");
        String sixteenthCountryFlag = sp.getString(SIXTEENTH_COUNTRY_FLAG, "");
        String seventeenthCountryName = sp.getString(SEVENTEENTH_COUNTRY_NAME, "");
        String seventeenthCountryCapital = sp.getString(SEVENTEENTH_COUNTRY_CAPITAL, "");
        String eighteenthCountryName = sp.getString(EIGHTEENTH_COUNTRY_NAME, "");
        String eighteenthCountryCapital = sp.getString(EIGHTEENTH_COUNTRY_CAPITAL, "");
        String nineteenthCountryName = sp.getString(NINETEENTH_COUNTRY_NAME, "");
        String nineteenthCountryCapital = sp.getString(NINETEENTH_COUNTRY_CAPITAL, "");
        String twentiethCountryName = sp.getString(TWENTIETH_COUNTRY_NAME, "");
        String twentiethCountryRegion = sp.getString(TWENTIETH_COUNTRY_REGION, "");
        String twentyFirstCountryName = sp.getString(TWENTYFIRST_COUNTRY_NAME, "");
        String twentyFirstCountryFlag = sp.getString(TWENTYFIRST_COUNTRY_FLAG, "");
        String twentySecondCountryName = sp.getString(TWENTYSECOND_COUNTRY_NAME, "");
        String twentySecondCountryFlag = sp.getString(TWENTYSECOND_COUNTRY_FLAG, "");
        String twentyThirdCountryName = sp.getString(TWENTYTHIRD_COUNTRY_NAME, "");
        String twentyThirdCountryFlag = sp.getString(TWENTYTHIRD_COUNTRY_FLAG, "");
        String twentyFourthCountryName = sp.getString(TWENTYFOURTH_COUNTRY_NAME, "");
        String twentyFourthCountryCapital = sp.getString(TWENTYFOURTH_COUNTRY_CAPITAL, "");
        String twentyFifthCountryName = sp.getString(TWENTYFIFTH_COUNTRY_NAME, "");
        String twentyFifthCountryRegion = sp.getString(TWENTYFIFTH_COUNTRY_REGION, "");


        TriviaQuestions questionOne = new TriviaQuestions("_____ is the capital of " + seventeenthCountryName, seventeenthCountryCapital, twentyFourthCountryCapital, thirteenthCountryCapital, 1);
        addQuestion(questionOne);

        TriviaQuestions questionTwo = new TriviaQuestions("What is the capital of " + eleventhCountryName + "?", twelfthCountryCapital, eleventhCountryCapital, eighteenthCountryCapital, 2);
        addQuestion(questionTwo);

        TriviaQuestions questionThree = new TriviaQuestions(sixthCountryName + " has an area of ________ km² ", "398342.8km²", sixthCountrySize +"km²", "922404.2km²", 2);
        addQuestion(questionThree);

        TriviaQuestions questionFour = new TriviaQuestions(secondCountryCapital + " is to " + secondCountryName + " as " + fourthCountryCapital +" is to", eleventhCountryName, twelfthCountryName, fourthCountryName, 3);
        addQuestion(questionFour);

        TriviaQuestions questionFive = new TriviaQuestions(twentyFifthCountryName + " is located in ", twentyFifthCountryRegion, firstCountryCapital, seventeenthCountryCapital, 1);
        addQuestion(questionFive);

        TriviaQuestions questionSix = new TriviaQuestions(firstCountryCapital + " is the capital of ", fifteenthCountryName, twentySecondCountryName, firstCountryName, 3);
        addQuestion(questionSix);

        TriviaQuestions questionSeven = new TriviaQuestions(secondCountryCapital + " is the capital of ", secondCountryName, eighteenthCountryCapital, twentyFifthCountryName , 1);
        addQuestion(questionSeven);

        TriviaQuestions questionEight = new TriviaQuestions(thirdCountryName + " is located in ", thirteenthCountryName, thirdCountryRegion, twentyFirstCountryName , 2);
        addQuestion(questionEight);

        TriviaQuestions questionNine = new TriviaQuestions( thirdCountryName + " is located in " + thirdCountryRegion + " and " + fifthCountryName + " is located in ", fifthCountryRegion, twentyFourthCountryName, fifteenthCountryName , 1);
        addQuestion(questionNine);

        TriviaQuestions questionTen = new TriviaQuestions( seventhCountryFlag,  seventhCountryName , twentiethCountryName, seventeenthCountryName, 1);
        addQuestion(questionTen);

        TriviaQuestions questionEleven = new TriviaQuestions( eighthCountryFlag,  twentyFourthCountryName , eighthCountryName, fourteenthCountryName, 2);
        addQuestion(questionEleven);

        TriviaQuestions questionTwelve = new TriviaQuestions( ninthCountryFlag,  secondCountryName , ninthCountryName, fifthCountryName, 2);
        addQuestion(questionTwelve);

        TriviaQuestions questionThirteen = new TriviaQuestions( tenthCountryFlag,  sixthCountryName , firstCountryName, tenthCountryName, 3);
        addQuestion(questionThirteen);

        TriviaQuestions questionFourteen = new TriviaQuestions( twelfthCountryCapital + " is the capital of ",  twelfthCountryName , twentySecondCountryName, sixteenthCountryName, 1);
        addQuestion(questionFourteen);

        TriviaQuestions questionFifteen = new TriviaQuestions( thirteenthCountryName +"'s capital city is ",  twentyFifthCountryName , thirteenthCountryCapital, sixteenthCountryName, 2);
        addQuestion(questionFifteen);

        TriviaQuestions questionSixteen = new TriviaQuestions( fourteenthCountryFlag,  twelfthCountryName , fourteenthCountryName, twentyFourthCountryCapital, 2);
        addQuestion(questionSixteen);

        TriviaQuestions questionSeventeen = new TriviaQuestions( fifteenthCountryFlag,  fifteenthCountryName , seventeenthCountryName, sixthCountryName, 1);
        addQuestion(questionSeventeen);

        TriviaQuestions questionEighteen = new TriviaQuestions( sixteenthCountryFlag,  firstCountryName , ninthCountryName, sixteenthCountryName, 3);
        addQuestion(questionEighteen);

        TriviaQuestions questionNineteen = new TriviaQuestions( eighteenthCountryCapital + " is the capital of ",  fourthCountryName , tenthCountryName, eighteenthCountryName, 3);
        addQuestion(questionNineteen);

        TriviaQuestions questionTwenty = new TriviaQuestions( nineteenthCountryCapital + " is the capital of",  thirdCountryName , nineteenthCountryName, twentyFifthCountryName, 2);
        addQuestion(questionTwenty);

        TriviaQuestions questionTwentyOne = new TriviaQuestions( twentiethCountryName + " is located in ",  twentiethCountryRegion , eighthCountryName, nineteenthCountryName, 1);
        addQuestion(questionTwentyOne);

        TriviaQuestions questionTwentyTwo = new TriviaQuestions( twentyFirstCountryFlag,  twentyFirstCountryName , twentiethCountryName, secondCountryName, 1);
        addQuestion(questionTwentyTwo);

        TriviaQuestions questionTwentyThree = new TriviaQuestions( twentySecondCountryFlag,  thirdCountryName , twentySecondCountryName, seventhCountryName, 2);
        addQuestion(questionTwentyThree);

        TriviaQuestions questionTwentyFour = new TriviaQuestions( twentyThirdCountryFlag,  sixteenthCountryName , thirdCountryRegion, twentyThirdCountryName, 3);
        addQuestion(questionTwentyFour);

        TriviaQuestions questionTwentyFive = new TriviaQuestions( "The capital of " + twentyFourthCountryName + " is",  twentyFourthCountryCapital , seventeenthCountryName, firstCountryName, 1);
        addQuestion(questionTwentyFive);


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

