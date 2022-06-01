package com.kaustubh.quizzer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class set_test2 extends AppCompatActivity {
EditText question,option1,option2,option3,option4,answer;
Button submit;
     public int limit;
    public static ArrayList<module> lisofQ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_test2);
        question = findViewById(R.id.editTextTextPersonName);
        option1 = findViewById(R.id.editTextTextPersonName2);
        option2 = findViewById(R.id.editTextTextPersonName3);
        option3 = findViewById(R.id.editTextTextPersonName4);
        option4 = findViewById(R.id.editTextTextPersonName5);
        answer = findViewById(R.id.editTextTextPersonName6);
        submit = findViewById(R.id.button77);
        lisofQ=new ArrayList<>();
        Intent intent=getIntent();
        limit=Integer.parseInt(intent.getStringExtra("limit"));
        if (limit==0){
         //   Toast.makeText(set_test2.this,"successs....",Toast.LENGTH_SHORT).show();
            Intent intent1=new Intent(set_test2.this,MainActivity.class);
            startActivity(intent1);
        }
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    limit--;
                    if (limit==0){
                        Toast.makeText(set_test2.this,"successs....",Toast.LENGTH_SHORT).show();
                        Intent intent1=new Intent(set_test2.this,teacherActivity.class);
                        startActivity(intent1);
                    }
                    String que=question.getText().toString();
                    String op1=option1.getText().toString();
                    String op2=option2.getText().toString();
                    String op3=option3.getText().toString();
                    String op4=option4.getText().toString();
                    String ans=answer.getText().toString();
                    lisofQ.add(new module(que,op1,op2,op3,op4,ans));
                    question.getText().clear();
                    option1.getText().clear();
                    option2.getText().clear();
                    option3.getText().clear();
                    option4.getText().clear();
                    answer.getText().clear();

                }
            });

    }
}