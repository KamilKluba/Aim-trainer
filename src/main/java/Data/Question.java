package Data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Question {
    private String key;
    private StringProperty question;
    private int questionNumber;

    public Question(String key, int questionNumber) {
        this.key = key;
        this.question = new SimpleStringProperty();
        this.question.bind(I18N.createStringBinding(key));
        this.questionNumber = questionNumber;
    }

    public String getKey() {
        return key;
    }

    public String getQuestion(){
        return question.getValue();
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    @Override
    public String toString() {
        return question.getValue();
    }
}
