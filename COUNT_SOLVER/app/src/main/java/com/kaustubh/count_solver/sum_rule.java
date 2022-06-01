package com.kaustubh.count_solver;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class sum_rule extends AppCompatActivity {
    LinearLayout list;
    EditText size;
    Button load;
    public  int sum;
    public  String []resultarr;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sum_rule);
        TextView t=findViewById(R.id.textView8);
        t.setText(R.string.sum_rule);
         load=findViewById(R.id.load);
          sum=0;
         size=findViewById(R.id.editTextNumber2);
         list=findViewById(R.id.linearLayout);
        Button cal=findViewById(R.id.button2);
        EditText entites=findViewById(R.id.editTextNumber);
        context =this;
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   list.removeAllViews();
                if (!entites.getText().toString().isEmpty()) {
                    int num = Integer.parseInt(entites.getText().toString());
                    for (int i = 0; i <num; i++) {
                        final View aa = getLayoutInflater().inflate(R.layout.resource_file, null, false);
                        list.addView(aa);
                    }
                }

            }
        });
        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultarr=new String[list.getChildCount()];
                for (int i = 0; i <list.getChildCount() ; i++) {
                    View v=list.getChildAt(i);
                    EditText et=v.findViewById(R.id.editTextNumber2);
                    int num=Integer.parseInt(et.getText().toString());
                    sum+=num;
                    resultarr[i]=et.getText().toString();
                }
              Intent intent=new Intent(sum_rule.this, result_sum.class);
                intent.putExtra("resultarray",resultarr);
                intent.putExtra("sum",sum);
                startActivity(intent);
            }
        });
    }
}
