package com.hb.filmquizz;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.hb.filmquizz.pojos.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "QuizzActivity";
    private static final String KEY_INDEX = "index";
    private static final String KEY_SCORE = "score";
    private static final String KEY_RESET = "reset";

    private final List<Question> questions = new ArrayList<>();
    private TextView tvQuestion;
    private TextView tvScore;
    private Button btnTrue;
    private Button btnFalse;
    private int idx = 0, score = 0, btnResetState = View.INVISIBLE;
    private Question question;
    private Button btnReset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG,"onCreate() called");

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

        if(savedInstanceState != null){
            idx = savedInstanceState.getInt(KEY_INDEX);
            score = savedInstanceState.getInt(KEY_SCORE);
            btnResetState = savedInstanceState.getInt(KEY_RESET);
            btnReset.setVisibility(btnResetState);
        }
        if(idx<questions.size()){
            question = questions.get(idx);
            tvQuestion.setText(question.getText());
        }else{
            tvQuestion.setText(getString(R.string.end));
            question = questions.get(0);
        }

        tvScore.setText(String.format(Locale.FRANCE,"%s: %d", getString(R.string.score), score));


        btnTrue.setOnClickListener(v -> checkAnswer(true));
        btnFalse.setOnClickListener(v -> checkAnswer(false));
        btnReset.setOnClickListener(v -> {
            score = 0;
            idx = 0;
            tvQuestion.setText(questions.get(idx).getText());
            btnTrue.setVisibility(View.VISIBLE);
            btnFalse.setVisibility(View.VISIBLE);
            tvScore.setText(String.format(Locale.FRANCE,"%s: %d", getString(R.string.score), score));
            btnReset.setVisibility(View.INVISIBLE);
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState() called");
        outState.putInt(KEY_INDEX, idx);
        outState.putInt(KEY_SCORE,score);
        outState.putInt(KEY_RESET,btnResetState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    public void checkAnswer(boolean answer) {
        Toast toast;
        question = questions.get(idx);
        if (answer == question.isAnswer()) {
            toast = Toast.makeText(getApplicationContext(), "CORRECT", Toast.LENGTH_SHORT);
            if (idx < questions.size()) {
                ++score;
            }

        } else {
            toast = Toast.makeText(getApplicationContext(), "INCORRECT", Toast.LENGTH_SHORT);
        }
        toast.show();
        idx++;

        if (idx < questions.size()) {
            question = questions.get(idx);
            tvQuestion.setText(question.getText());

        } else {
            tvQuestion.setText(getString(R.string.end));
            btnTrue.setVisibility(View.INVISIBLE);
            btnFalse.setVisibility(View.INVISIBLE);
            btnReset.setVisibility(View.VISIBLE);
            btnResetState = View.VISIBLE;

        }
        tvScore.setText(String.format(Locale.FRANCE,"%s: %d", getString(R.string.score), score));

    }
}