package com.kaustubh.count_solver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class result_product extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView t1=findViewById(R.id.textView13);
        TextView t2=findViewById(R.id.textView14);
        TextView t3=findViewById(R.id.textView15);
        TextView t4=findViewById(R.id.textView12);
        Intent intent = getIntent();
        String []resultarr=intent.getStringArrayExtra("resultarray");
        t4.setText(R.string.product_rule);
        int sum=intent.getIntExtra("multipliaction",0);
        for (int i = 0; i <resultarr.length; i++) {
            if (resultarr[i]==null) {
                break;
            }
            if (!t1.getText().toString().isEmpty()){
                String test=t1.getText().toString();
                t1.setText(test+resultarr[i]+" ");
            }
            else {
                t1.setText(" INSERTED SET IS :["+resultarr[i]+" ");
            }
        }
        String test1=t1.getText().toString();
        t1.setText(test1+"]");
        for (int i = 0; i <resultarr.length; i++) {
            if (resultarr[i]==null) {
                break;
            }
            if (i==(resultarr.length)-1){
                String test=t2.getText().toString();
                t2.setText(test+resultarr[i]);
                break;
            }
            if (!t2.getText().toString().isEmpty()){
                String test=t2.getText().toString();
                t2.setText(test+resultarr[i]+"X");
            }

            else {
                t2.setText(" FORMULA  IS :\n["+resultarr[i]+"X");
            }
        }
        String test2=t2.getText().toString();
        t2.setText(test2+"]");
        t3.setText("THE VALUE OF PRODUCT IS :"+String.valueOf(sum));

    }
}