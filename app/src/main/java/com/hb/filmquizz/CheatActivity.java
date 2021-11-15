package com.hb.filmquizz;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.hb.filmquizz.pojos.Question;

import java.util.Locale;

public class CheatActivity extends AppCompatActivity {
   private TextView tvReponse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        tvReponse = findViewById(R.id.tvReponse);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        Question question = (Question) intent.getSerializableExtra(MainActivity.KEY_QUESTION);
        tvReponse.setText(String.format(Locale.FRANCE,"%s : %s",question.getText(),question.isAnswer()));
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}