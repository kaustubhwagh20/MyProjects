
package com.kaustubh.count_solver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class inclusion2 extends AppCompatActivity {
String []resultarr=new String[10];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inclusion2);
        Intent intent = getIntent();
        String[] set1arr = intent.getStringArrayExtra("set1array");
        String[] set2arr = new String[5];
        Button n = findViewById(R.id.Next1);
        EditText v1 = findViewById(R.id.s1);
        EditText v2 = findViewById(R.id.s2);
        EditText v3 = findViewById(R.id.s3);
        EditText v4 = findViewById(R.id.s4);
        EditText v5 = findViewById(R.id.s5);
        n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set2arr[0] = v1.getText().toString();
                set2arr[1] = v2.getText().toString();
                set2arr[2] = v3.getText().toString();
                set2arr[3] = v4.getText().toString();
                set2arr[4] = v5.getText().toString();
                Inter(set1arr,set2arr,5,5);
                int size=0;
                for (int i = 0; i <10 ; i++) {
                    if (resultarr[i]!=null){
                        size++;
                    }
                }
                int result=10-size;
                Intent intent = new Intent(inclusion2.this, inclusionres.class);
                intent.putExtra("set1array", set1arr);
                intent.putExtra("set2array", set2arr);
                intent.putExtra("result",result);
                Toast.makeText(inclusion2.this,String.valueOf(result),Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
    void Inter(String arr1[], String arr2[], int len1, int len2) {

        String arr3[] = new String[10];
        int i, j, k = 0;
        for (i = 0; i < len1; i++) {
            for (j = 0; j < len2; j++) {
                if (arr1[i].equals(arr2[j])) {
                    arr3[k] = arr1[i];
                    k++;
                }
            }
            resultarr = arr3;
        }
    }

}