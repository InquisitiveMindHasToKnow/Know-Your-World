package org.ohmstheresistance.knowyourworld.activities;

import android.provider.BaseColumns;

public final class TriviaContract {

    private TriviaContract() {
    }

    public static class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME = "quiz_questions";
        public static final String QUESTION_COLUMN = "question";
        public static final String FIRST_ANSWER__OPTION_COLUMN = "first_answer_option";
        public static final String SECOND_ANSWER__OPTION_COLUMN = "second_answer_option";
        public static final String THIRD_ANSWER__OPTION_COLUMN = "third_answer_option";
        public static final String ANSWER_NUMBER_COLUMN = "answer_number";
    }
}
