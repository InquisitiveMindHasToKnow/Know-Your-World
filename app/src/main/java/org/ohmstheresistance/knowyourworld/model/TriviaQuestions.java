package org.ohmstheresistance.knowyourworld.model;


public class TriviaQuestions {

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
