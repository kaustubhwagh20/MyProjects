package com.kaustubh.quizzer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class set_test extends AppCompatActivity {
Button bt;
EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_test);
        bt=findViewById(R.id.button7);
        editText=findViewById(R.id.editTextNumber);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(set_test.this,set_test2.class);
                intent.putExtra("limit",editText.getText().toString());
                startActivity(intent);
            }
        });
    }
}