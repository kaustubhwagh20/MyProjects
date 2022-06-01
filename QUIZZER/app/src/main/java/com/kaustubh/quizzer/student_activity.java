package com.kaustubh.quizzer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import static com.kaustubh.quizzer.set_test2.lisofQ;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class student_activity extends AppCompatActivity {
Button attempt,result;
    int correct,wrong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        attempt=findViewById(R.id.button);
        result=findViewById(R.id.button2);
        attempt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    wrong=0;
                    correct=0;
                        Intent intent = new Intent(student_activity.this, attempt_quiz.class);
                     startActivityForResult(intent,1);
                }catch (Exception e){
                    Toast.makeText(student_activity.this,e.toString(), Toast.LENGTH_LONG).show();

                }
            }
        });
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(student_activity.this,view_result.class);
                intent.putExtra("correct_count", correct);
                intent.putExtra("wrong_count", wrong);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
            if (resultCode==RESULT_OK){
                 correct=data.getIntExtra("correct_count",0);
                 wrong=data.getIntExtra("wrong_count",0);
            }
        }
    }
}