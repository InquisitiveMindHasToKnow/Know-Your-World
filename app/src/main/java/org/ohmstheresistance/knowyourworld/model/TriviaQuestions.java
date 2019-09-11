package org.ohmstheresistance.knowyourworld.model;


import android.os.Parcel;
import android.os.Parcelable;

public class TriviaQuestions implements Parcelable {

    private String question;
    private String firstAnswerOption;
    private String secondAnswerOption;
    private String thirdAnswerOption;
    private int answerNumber;

    public TriviaQuestions(){}

    public TriviaQuestions(String question, String firstAnswerOption, String secondAnswerOption, String thirdAnswerOption, int answerNumber) {
        this.question = question;
        this.firstAnswerOption = firstAnswerOption;
        this.secondAnswerOption = secondAnswerOption;
        this.thirdAnswerOption = thirdAnswerOption;
        this.answerNumber = answerNumber;
    }

    protected TriviaQuestions(Parcel in) {
        question = in.readString();
        firstAnswerOption = in.readString();
        secondAnswerOption = in.readString();
        thirdAnswerOption = in.readString();
        answerNumber = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeString(firstAnswerOption);
        dest.writeString(secondAnswerOption);
        dest.writeString(thirdAnswerOption);
        dest.writeInt(answerNumber);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TriviaQuestions> CREATOR = new Creator<TriviaQuestions>() {
        @Override
        public TriviaQuestions createFromParcel(Parcel in) {
            return new TriviaQuestions(in);
        }

        @Override
        public TriviaQuestions[] newArray(int size) {
            return new TriviaQuestions[size];
        }
    };

    public String getQuestion() {
        return question;
    }

    public String getFirstAnswerOption() {
        return firstAnswerOption;
    }

    public String getSecondAnswerOption() {
        return secondAnswerOption;
    }

    public String getThirdAnswerOption() {
        return thirdAnswerOption;
    }

    public int getAnswerNumber() {
        return answerNumber;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setFirstAnswerOption(String firstAnswerOption) {
        this.firstAnswerOption = firstAnswerOption;
    }

    public void setSecondAnswerOption(String secondAnswerOption) {
        this.secondAnswerOption = secondAnswerOption;
    }

    public void setThirdAnswerOption(String thirdAnswerOption) {
        this.thirdAnswerOption = thirdAnswerOption;
    }

    public void setAnswerNumber(int answerNumber) {
        this.answerNumber = answerNumber;
    }
}
