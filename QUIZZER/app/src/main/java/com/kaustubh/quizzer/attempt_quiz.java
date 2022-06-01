package com.kaustubh.quizzer;

import static com.kaustubh.quizzer.set_test2.lisofQ;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Collections;
import java.util.List;
public class attempt_quiz extends AppCompatActivity {
CountDownTimer countDownTimer;
List<module> allquestion;
int timervalue=21;
int index=0;
int correctcount=0;
int wrongcount=0;
boolean isCounterRunning;
TextView textView;
ImageView imageView;
    module moduleclass;
ProgressBar progressBar;
TextView question,option1,option2,option3,option4;
CardView cardoa,cardob,cardoc,cardod;
Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_attempt_quiz);
            progressBar = findViewById(R.id.progressBar);
            question = findViewById(R.id.textView3);
            option1 = findViewById(R.id.textView4);
            option2 = findViewById(R.id.textView5);
            option3 = findViewById(R.id.textView6);
            option4 = findViewById(R.id.textView7);
            cardoa = findViewById(R.id.option1);
            cardob = findViewById(R.id.option2);
            cardoc = findViewById(R.id.option3);
            cardod = findViewById(R.id.option4);
            next=findViewById(R.id.button4);
            textView=findViewById(R.id.textView2);
            imageView=findViewById(R.id.imageView2);
            progressBar.setMax(20);
            allquestion = lisofQ;
            Collections.shuffle(allquestion);
            moduleclass = lisofQ.get(index);
            next.setClickable(false);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(attempt_quiz.this,MainActivity.class);
                    startActivity(intent);
                }
            });
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    index=0;
                finish();

                }
            });
            countDownTimer = new CountDownTimer(21000, 1000) {
                @Override
                public void onTick(long l) {
                    timervalue -= 1;
                    progressBar.setProgress(timervalue);
                    isCounterRunning=true;
                }

                @Override
                public void onFinish() {
                    isCounterRunning=false;
                    Dialog dialog = new Dialog(attempt_quiz.this);
                    dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                    dialog.setContentView(R.layout.box);
                    Button bt = dialog.findViewById(R.id.button5);
                    bt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(attempt_quiz.this, student_activity.class);
                            startActivity(intent);
                        }
                    });dialog.show();

                }
            }.start();
            setalldata();
            //Toast.makeText(attempt_quiz.this,moduleclass.getQuestion().toString(),Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Toast.makeText(attempt_quiz.this,"you have no tests",Toast.LENGTH_SHORT).show();
        }
    }
public  void correct(CardView card){
    card.setCardBackgroundColor(getResources().getColor(R.color.green));
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correctcount++;
                if (index<lisofQ.size()-1){//-1
                    index++;
                    moduleclass=lisofQ.get(index);
                    reset_col();
                    setalldata();

                }else {
                    gamewon();
                }
            }
        });

}
public  void wrong(CardView card){

    card.setCardBackgroundColor(getResources().getColor(R.color.red));
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wrongcount++;
                if (index<lisofQ.size()-1){//-1

                    index++;
                    moduleclass=lisofQ.get(index);
                    reset_col();
                    setalldata();

                }else {
                    gamewon();
                }
            }
        });
}

    private void gamewon() {
        try {
            Intent intent = new Intent();
            intent.putExtra("correct_count", correctcount);
            intent.putExtra("wrong_count", wrongcount);
            setResult(RESULT_OK,intent);
           // startActivity(intent);
            index=0;
            finish();
        }
        catch (Exception e){
            Toast.makeText(attempt_quiz.this,e.toString(),Toast.LENGTH_SHORT).show();
        }
    }
    public void enable_bt(){
        cardoa.setClickable(true);
        cardob.setClickable(true);
        cardoc.setClickable(true);
        cardod.setClickable(true);

    }
    public void disable_bt(){
        cardoa.setClickable(false);
        cardob.setClickable(false);
        cardoc.setClickable(false);
        cardod.setClickable(false);

    }
    public void reset_col(){
        enable_bt();
        cardoa.setCardBackgroundColor(getResources().getColor(R.color.white));
        cardob.setCardBackgroundColor(getResources().getColor(R.color.white));
        cardoc.setCardBackgroundColor(getResources().getColor(R.color.white));
        cardod.setCardBackgroundColor(getResources().getColor(R.color.white));
    }

    private void setalldata() {
        timervalue=21;
        if( !isCounterRunning ){
            isCounterRunning = true;
            countDownTimer.start();
        }
        else{
            countDownTimer.cancel(); // cancel
            countDownTimer.start();  // then restart
        }
         next.setClickable(false);
        question.setText(moduleclass.getQuestion());
        option1.setText(moduleclass.getOption1());
        option2.setText(moduleclass.getOption2());
        option3.setText(moduleclass.getOption3());
        option4.setText(moduleclass.getOption4());


    }

    public void optiona(View view) {
        disable_bt();
        next.setClickable(true);
        if (moduleclass.getOption1().equals(moduleclass.getAns())){
            if (index<=lisofQ.size()-1){//-1
                correct(cardoa);
            }
            else{
                gamewon();
            }
        }
        else {
            wrong(cardoa);
        }
    }
    public void optionb(View view) {
        disable_bt();
        next.setClickable(true);
        if (moduleclass.getOption2().equals(moduleclass.getAns())){
            if (index<=lisofQ.size()-1){//-1
                correct(cardob);
            }
            else{
                gamewon();
            }
        }
        else {
            wrong(cardob);
        }
    }
    public void optionc(View view) {
        disable_bt();
        next.setClickable(true);
        if (moduleclass.getOption3().equals(moduleclass.getAns())){
            if (index<=lisofQ.size()-1){//-1
                correct(cardoc);
            }
            else{
                gamewon();
            }
        }
        else {
            wrong(cardoc);
        }
    }
    public void optiond(View view) {
        disable_bt();
        next.setClickable(true);
        if (moduleclass.getOption4().equals(moduleclass.getAns())){
            if (index<=lisofQ.size()-1){//-1
               correct(cardod);
            }
            else{
                gamewon();
            }
        }
        else {
            wrong(cardod);
        }
    }
}