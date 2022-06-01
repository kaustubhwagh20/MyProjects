package com.kaustubh.count_solver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class combination extends AppCompatActivity {
    EditText n,r;
TextView pc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permu_com);
        n=findViewById(R.id.editTextNumber3);
        r=findViewById(R.id.editTextNumber4);
        pc=findViewById(R.id.textView9);
        pc.setText(R.string.combination);
        Button submit=findViewById(R.id.button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long num;
                long run;
                num = Long.parseLong(n.getText().toString());
                run = Long.parseLong(r.getText().toString());
                // Toast.makeText(permutation.this,String.valueOf(num),Toast.LENGTH_SHORT).show();
                long permu=factorial(num)/(factorial(run)*factorial(num-run));
                Intent intent=new Intent(combination.this,pcresult.class);
                intent.putExtra("number",num);
                intent.putExtra("rreturn",run);
                intent.putExtra("result","your result for combination is: "+permu);
                intent.putExtra("formula","n!/r!(n-r)!");
                intent.putExtra("name",R.string.combination);
                startActivity(intent);
            }
        });
    }
    public long factorial(Long n){
        if (n == 0)
            return 1;
        else
            return(n * factorial(n-1));
    }
}