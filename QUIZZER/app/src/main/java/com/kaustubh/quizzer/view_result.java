package com.kaustubh.quizzer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class view_result extends AppCompatActivity {
ProgressBar progressBar;
TextView textView;
Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_result);
        Intent intent= getIntent();
        int correct=intent.getIntExtra("correct_count",0);
        int wrong=intent.getIntExtra("wrong_count",0);
        int total=correct+wrong;
        String set=String.valueOf(correct)+"/"+String.valueOf(total);
        progressBar=findViewById(R.id.progressBar);
        textView=findViewById(R.id.textView9);
        bt=findViewById(R.id.button6);
        progressBar.setMax(total);
        progressBar.setProgress(correct);
        textView.setText(set);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Quizzer a quiz application");
                    String shareMessage= "\nI got "+set +" in quiz\n you can also try this \n\n";
                    shareMessage = shareMessage + BuildConfig.APPLICATION_ID +"\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch(Exception e) {
                    //e.toString();
                }
            }
        });

    }
}