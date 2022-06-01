package com.kaustubh.count_solver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class inclusion_exclusion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inclusion_exclusion);
        String[] set1arr = new String[5];
        Button n = findViewById(R.id.Next1);
        EditText v1 = findViewById(R.id.s1);
        EditText v2 = findViewById(R.id.s2);
        EditText v3 = findViewById(R.id.s3);
        EditText v4 = findViewById(R.id.s4);
        EditText v5 = findViewById(R.id.s5);
        n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set1arr[0]=v1.getText().toString();
                set1arr[1]=v2.getText().toString();
                set1arr[2]=v3.getText().toString();
                set1arr[3]=v4.getText().toString();
                set1arr[4]=v5.getText().toString();
                Intent intent = new Intent(inclusion_exclusion.this, inclusion2.class);
                intent.putExtra("set1array",set1arr);
                startActivity(intent);
            }
        });
    }
}