package com.hb.filmquizz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hb.filmquizz.pojos.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private final List<Question> questions = new ArrayList<>();
    private TextView tvQuestion;
    private TextView tvScore;
    private Button btnTrue;
    private Button btnFalse;
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

        questions.add(new Question(getString(R.string.question_ai), true));
        questions.add(new Question(getString(R.string.question_taxi_driver), true));
        questions.add(new Question(getString(R.string.question_2001), false));
        questions.add(new Question(getString(R.string.question_reservoir_dogs), true));
        questions.add(new Question(getString(R.string.question_citizen_kane), false));

        question = questions.get(cpt);
        if (cpt == 0) {
            tvQuestion.setText(question.getText());
            tvScore.setText(String.format(Locale.FRANCE,"%s: %d", getString(R.string.score), score));
        }
        btnTrue.setOnClickListener(v -> checkAnswer(true));

        btnFalse.setOnClickListener(v -> checkAnswer(false));

    }

    public void checkAnswer(boolean answer) {
        Toast toast;
        question = questions.get(cpt);
        if (answer == question.isAnswer()) {
            toast = Toast.makeText(getApplicationContext(), "CORRECT", Toast.LENGTH_SHORT);
            if (cpt < questions.size()) {
                ++score;
            }

        } else {
            toast = Toast.makeText(getApplicationContext(), "INCORRECT", Toast.LENGTH_SHORT);
        }
        toast.show();
        cpt++;

        if (cpt < questions.size()) {
            question = questions.get(cpt);
            tvQuestion.setText(question.getText());
        } else {
            tvQuestion.setText(getString(R.string.end));
            btnTrue.setVisibility(View.INVISIBLE);
            btnFalse.setVisibility(View.INVISIBLE);
            btnReset.setVisibility(View.VISIBLE);
            btnReset.setOnClickListener(v -> {
                score = 0;
                cpt = 0;
                tvQuestion.setText(questions.get(cpt).getText());
                btnTrue.setVisibility(View.VISIBLE);
                btnFalse.setVisibility(View.VISIBLE);
                tvScore.setText(String.format(Locale.FRANCE,"%s: %d", getString(R.string.score), score));
                btnReset.setVisibility(View.INVISIBLE);
            });
        }
        tvScore.setText(String.format(Locale.FRANCE,"%s: %d", getString(R.string.score), score));

    }
}