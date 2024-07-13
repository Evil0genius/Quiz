package com.example.quiz;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.quiz.model.Question;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView titleTxt;                    // Создаем объект для текстового поля

    private ArrayList<Question> questions;

    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);    // Указываем файл разметки, к которому привязан класс
        titleTxt = findViewById(R.id.title_txt);   // Привязка элемента к объекту
        count = 0;                                 // Инициализирую счетчик вопросов нулем
        questions = new ArrayList<>();             // Выделяем память для массива

        questions.add(new Question("Москва столица России?", true));  // Добавляем вопросы
        questions.add(new Question("Питер столица России?", false));  // Добавляем вопросы
        questions.add(new Question("Амазонка самая длинная река?", true));  // Добавляем вопросы
        questions.add(new Question("Байкал самое глубокое озеро?", true));  // Добавляем вопросы


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
            if(questions.get(count).isAnswer()){
                res = "правильно";
            }
            else {
                res = "неправильно";
            }
            Toast.makeText(this, "Вы ответили: " + res, Toast.LENGTH_LONG).show();
        }
        else if(id == R.id.btn_no){
          // TODO Обработать нажатие на кнопку по аналогии с верхним случаем
        }
        else if(id == R.id.btn_previous){
            // TODO Обработать движение назад по аналогии с обработкой ниже
        }
        else if(id == R.id.btn_next){
            count++;           // Переходим к следующему вопросу
            if(count == questions.size()){   // Проверяем выход за границы массива
                count = 0;
            }
            titleTxt.setText(questions.get(count).getText()); // Выводим следующий вопрос
        }
    }
}