package org.ohmstheresistance.knowyourworld.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import org.ohmstheresistance.knowyourworld.model.Country;
import org.ohmstheresistance.knowyourworld.model.TriviaQuestions;
import org.ohmstheresistance.knowyourworld.activities.TriviaContract.*;

import java.util.ArrayList;

public class CountryTriviaDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "CountryTrivia.db";
    private static final int DATABASE_VERSION = 3;
    private SQLiteDatabase countryTriviaDatabase;

    public CountryTriviaDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
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
//        final Country country = new Country();
//
//        String countryName = country.getName();
//        String countryCapital = country.getCapital();

        TriviaQuestions questionOne = new TriviaQuestions("_____ is the capital of Jamaica", "Kingston", "Spanish Town", "Canada", 1);
        addQuestion(questionOne);

        TriviaQuestions questionTwo = new TriviaQuestions("What is the capital of Comoros", "Brazzaville", "Moroni", "Congo", 2);
        addQuestion(questionTwo);

        TriviaQuestions questionThree = new TriviaQuestions("What is the currency of Finland? ", "US Dollar", "Euro", "Yen", 2);
        addQuestion(questionThree);

        TriviaQuestions questionFour = new TriviaQuestions("New Delhi is to India as Astana is to", "Ecuador", "Bolivia", "Kazakhstan", 3);
        addQuestion(questionFour);

        TriviaQuestions questionFive = new TriviaQuestions("Niger is on the continent of ", "Africa", "Europe", "Antarctica", 1);
        addQuestion(questionFive);

//        TriviaQuestions questionSix = new TriviaQuestions(countryCapital + " is the capital of: ", "Virginia", "New York ", countryName, 3);
//        addQuestion(questionSix);
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
}
