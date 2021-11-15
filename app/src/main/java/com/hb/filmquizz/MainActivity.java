package com.hb.filmquizz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hb.filmquizz.pojos.Question;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView tvQuestion;
    private TextView tvScore;
    private Button btnTrue;
    private Button btnFalse;
    private final List<Question> listeQuestions = new ArrayList<Question>();
    private int cpt = 0, score = 0;
    private Question question;
    private Button btnReset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvQuestion = findViewById(R.id.tvQuestion);
        tvScore = findViewById(R.id.tvScore);
        btnTrue = findViewById(R.id.btnTrue);
        btnFalse = findViewById(R.id.btnFalse);
        btnReset = findViewById(R.id.btnReset);

        listeQuestions.add(new Question(getString(R.string.question_ai),true));
        listeQuestions.add(new Question(getString(R.string.question_taxi_driver),true));
        listeQuestions.add(new Question(getString(R.string.question_2001),false));
        listeQuestions.add(new Question(getString(R.string.question_reservoir_dogs),true));
        listeQuestions.add(new Question(getString(R.string.question_citizen_kane),false));

        question = listeQuestions.get(cpt);
        if(cpt == 0) {
            tvQuestion.setText(question.getText());
            tvScore.setText(String.format("%s: %d", getString(R.string.score), score));
        }
        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               checkAnswer(true);
            }
        });

        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

    }

   public void checkAnswer(boolean answer) {
       Toast toast;
       question = listeQuestions.get(cpt);
       if(answer == question.isAnswer()){
           toast = Toast.makeText(getApplicationContext(), "CORRECT", Toast.LENGTH_SHORT);
           ++score;
       }else{
           toast = Toast.makeText(getApplicationContext(), "INCORRECT", Toast.LENGTH_SHORT);
       }
       toast.show();
        cpt++;

        if(cpt <listeQuestions.size() ){
            question = listeQuestions.get(cpt);
            tvQuestion.setText(question.getText());
        }else{
            tvQuestion.setText(getString(R.string.end));
            btnTrue.setVisibility(View.INVISIBLE);
            btnFalse.setVisibility(View.INVISIBLE);
            btnReset.setVisibility(View.VISIBLE);
            btnReset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   score = 0;
                   cpt = 0;
                   tvQuestion.setText(listeQuestions.get(cpt).getText());
                   btnTrue.setVisibility(View.VISIBLE);
                   btnFalse.setVisibility(View.VISIBLE);
                   tvScore.setText(String.format("%s: %d", getString(R.string.score), score));
                   btnReset.setVisibility(View.INVISIBLE);
                }
            });
        }
       tvScore.setText(String.format("%s: %d", getString(R.string.score), score));

   }
}