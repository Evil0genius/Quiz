package com.example.quiz.model;

/**
 * Класс модели
 * @author Vlad
 * @verion 1.0
 * @data 13.07.2024
 */
public class Question {

    private String text;
    private boolean answer;

    public Question(String text, boolean answer) {
        this.text = text;
        this.answer = answer;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public String getText() {
        return text;
    }

    public boolean isAnswer() {
        return answer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "Вопрос='" + text + '\'' +
                ", ответ=" + answer +
                '}';
    }
}
