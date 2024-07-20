package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.quiz.model.Question;

import java.util.ArrayList;
import java.util.List;

public class CustomQuizActivity extends AppCompatActivity {

    private ArrayAdapter<Question> adapter;
    private EditText custQuestion, custAnswer;
    private List<Question> questions;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_quiz);
        custQuestion = findViewById(R.id.custQuestion);
        custAnswer = findViewById(R.id.custAnswer);
        listView = findViewById(R.id.list);
        questions = new ArrayList<>();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, questions);
        listView.setAdapter(adapter);

    }

    public void onClick(View view) {
        int id = view.getId();

        if(id == R.id.btn_mainAct){               //переход на главный экран
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }
    public void addUser(View view) throws Exception {
        String question = custQuestion.getText().toString();
        String answ = custAnswer.getText().toString();
        boolean answer;
        if (answ.equalsIgnoreCase("true") || answ.equalsIgnoreCase("false")) {
            answer = Boolean.parseBoolean(answ);
        } else {
            throw new Exception();
        }
        Question custQue = new Question(question, answer);
        questions.add(custQue);
        adapter.notifyDataSetChanged();
    }

    public void save(View view){

        boolean result = JSONHelper.exportToJSON(this, questions);
        if(result){
            Toast.makeText(this, "Данные сохранены", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Не удалось сохранить данные", Toast.LENGTH_LONG).show();
        }
    }
    public void open(View view){
        questions = JSONHelper.importFromJSON(this);
        if(questions!=null){
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, questions);
            listView.setAdapter(adapter);
            Toast.makeText(this, "Данные восстановлены", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Не удалось открыть данные", Toast.LENGTH_LONG).show();
        }
    }


}