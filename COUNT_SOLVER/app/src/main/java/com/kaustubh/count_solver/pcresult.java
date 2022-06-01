package com.kaustubh.count_solver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class pcresult extends AppCompatActivity {
TextView t1,t2,t3,t4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent=getIntent();
        long n=intent.getLongExtra("number",0);
        long r=intent.getLongExtra("rreturn",0);
        String result=intent.getStringExtra("result");
        String formula=intent.getStringExtra("formula");
        String name=intent.getStringExtra("name");
        t1=findViewById(R.id.textView12);
        t2=findViewById(R.id.textView13);
        t3=findViewById(R.id.textView14);
        t4=findViewById(R.id.textView15);
        t1.setText(name);
        t2.setText("YOUR ENTERED VALUE OF N IS : "+n+"\n"+"YOUR ENTERD VALUE OF R IS :"+r);
        t3.setText("FORMULA FOR CALCULATION is : "+formula);
        t4.setText(result);



    }
}