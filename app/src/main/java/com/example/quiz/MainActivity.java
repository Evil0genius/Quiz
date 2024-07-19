package com.example.quiz;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.quiz.model.Question;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final static String qCount = "qCount";
    private final static String answMass = "answMass";

    private TextView titleTxt;                    // Создаем объект для текстового поля

    private ArrayList<Question> questions;

    private int[] answers;           //массив для учета ответов

    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);    // Указываем файл разметки, к которому привязан класс
        titleTxt = findViewById(R.id.title_txt);   // Привязка элемента к объекту
        if(savedInstanceState != null){
            count = savedInstanceState.getInt(qCount);
        }else count = 0;               // Инициализирую счетчик вопросов сохраненным значением или нулем
        questions = new ArrayList<>();             // Выделяем память для массива

        questions.add(new Question("Москва столица России?", true));  // Добавляем вопросы
        questions.add(new Question("Питер столица России?", false));  // Добавляем вопросы
        questions.add(new Question("Амазонка самая длинная река?", true));  // Добавляем вопросы
        questions.add(new Question("Байкал самое глубокое озеро?", true));  // Добавляем вопросы

        if (savedInstanceState != null) {       //массив для учета ответов инциализируем схраненным значением
            answers = savedInstanceState.getIntArray(answMass);
        }else answers = new int[questions.size()];   //или создаем новый

        titleTxt.setText(questions.get(count).getText());          // Выводим первый вопрос
    }

    /**
     * Метод для обработки нажатий на элементы
     * @param view элемент, на который нажали
     */
    public void onClick(View view) {
        int id = view.getId();        // Получаем id элемента, на который нажали
        String res = "";
        if(id == R.id.btn_yes){
            if(!(answers[count] == 0)){  //проверяем, отвечен ли вопрос ранее
                Toast.makeText(this, "Ответ уже засчитан", Toast.LENGTH_SHORT).show();

            }
            if(questions.get(count).isAnswer()){
                res = "правильно";
                if(answers[count] == 0) answers[count] = 1;  //вносим в массив ответов как правильный
                titleTxt.setText(questions.get(count).getText());
            }
            else {
                res = "неправильно";
                if(answers[count] == 0) answers[count] = 2;  //вносим в массив ответов как неправильный
            }
            Toast.makeText(this, "Вы ответили " + res, Toast.LENGTH_LONG).show();
        }
        else if(id == R.id.btn_no){
            if(!(answers[count] == 0)){  //проверяем, отвечен ли вопрос ранее
                Toast.makeText(this, "Ответ уже засчитан", Toast.LENGTH_SHORT).show();
            }
            if(questions.get(count).isAnswer()){
                res = "неправильно";
                if(answers[count] == 0) answers[count] = 2;
            }
            else {
                res = "правильно";
                if(answers[count] == 0) answers[count] = 1;
            }
            Toast.makeText(this, "Вы ответили " + res, Toast.LENGTH_LONG).show();
        }
        else if(id == R.id.btn_previous){
            count--;           // Переходим к предыдущему вопросу
            if(count <= 0){   // Проверяем выход за границы массива
                count = 0;
                Toast.makeText(this, "Это первый вопрос", Toast.LENGTH_SHORT).show();
            }
            titleTxt.setText(questions.get(count).getText()); // Выводим предыдущий вопрос
        }
        else if(id == R.id.btn_next){
            count++;           // Переходим к следующему вопросу
            if(count == questions.size()){   // Проверяем выход за границы массива
                count = 0;
            }
            titleTxt.setText(questions.get(count).getText()); // Выводим следующий вопрос
        }
        else if(id == R.id.btn_answ){
            if(questions.get(count).isAnswer()){  //подсматриваем правильный ответ
                res = "Да";
            }
            else {
                res = "Нет";
            }
            answers[count] = 2;     //записываем в массив ответов как неправильный ответ
            Toast.makeText(this, "Правильный ответ: " + res, Toast.LENGTH_SHORT).show();

        }
        else if(id == R.id.btn_drop){                       //сброс на первый вопрос
            for(int i = 0; i < questions.size(); i++){
                answers[i] = 0;
            }
            count = 0;
            titleTxt.setText(questions.get(count).getText());
            Toast.makeText(this, "Викторина сброшена", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.btn_answCount){               //выводим количество правильных ответов
            int answCounter = 0;
            for (int answer : answers) {
                if (answer == 1) answCounter++;
            }
            Toast.makeText(this, "Правильных ответов: " + answCounter+" из " + questions.size(), Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(qCount, count);
        outState.putIntArray(answMass, answers);
        super.onSaveInstanceState(outState);
    }
}

