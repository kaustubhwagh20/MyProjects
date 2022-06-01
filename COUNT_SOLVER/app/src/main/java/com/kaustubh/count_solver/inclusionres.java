package com.kaustubh.count_solver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class inclusionres extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inclusionres);
        TextView t1=findViewById(R.id.textView13);
        TextView t2=findViewById(R.id.textView14);
        TextView t3=findViewById(R.id.textView15);
        TextView t4=findViewById(R.id.textView12);
        Intent intent = getIntent();
        String []set1=intent.getStringArrayExtra("set1array");
        String []set2=intent.getStringArrayExtra("set2array");
        int result=intent.getIntExtra("result",0);
        t4.setText(R.string.inclusion_exclusion);
        t2.setText("Formula is |A|+|B| - |AnB|");
        t3.setText("final answer is "+String.valueOf(result));
        t1.setText("set 1 is ["+set1[0]+" "+set1[1]+" "+set1[2]+" "+set1[3]+" "+set1[4]+" ]"+"\n"+
                "set 2 is ["+set2[0]+" "+set2[1]+" "+set2[2]+" "+set2[3]+" "+set2[4]+"]");

    }
}