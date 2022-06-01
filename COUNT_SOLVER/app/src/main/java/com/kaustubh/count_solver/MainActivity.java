package com.kaustubh.count_solver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CardView sumr=findViewById(R.id.sum);
        CardView productr=findViewById(R.id.product);
        CardView inclusionr=findViewById(R.id.inclusion);
        CardView permutationr=findViewById(R.id.permutation);
        CardView combinationr=findViewById(R.id.combination);
        sumr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sum_intent=new Intent(MainActivity.this,sum_rule.class);
                startActivity(sum_intent);
            }
        });
        productr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent product_intent=new Intent(MainActivity.this,product_rule.class);
                startActivity(product_intent);
            }
        });
        inclusionr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inclusion_intent=new Intent(MainActivity.this,inclusion_exclusion.class);
                startActivity(inclusion_intent);
            }
        });
        permutationr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent permutation_intent=new Intent(MainActivity.this,permutation.class);
                startActivity(permutation_intent);
            }
        });
        combinationr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent combination_intent=new Intent(MainActivity.this,combination.class);
                startActivity(combination_intent);
            }
        });
    }
}