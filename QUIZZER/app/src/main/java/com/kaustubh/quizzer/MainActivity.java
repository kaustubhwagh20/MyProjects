package com.kaustubh.quizzer;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
TextView sign_up,login,email,password,semail,spassword,confirm_pass;
Button log,sing;
SharedPreferences sharedPreferences,userids;
    private static final String shared_pref_name= "user_log";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout sign_uplay,log_inlay;
        log_inlay=findViewById(R.id.logInLayout);
        sign_uplay =  findViewById(R.id.singUpLayout);
        sign_up=findViewById(R.id.singUp);
         login=findViewById(R.id.logIn);
         log=findViewById(R.id.log);
         sing=findViewById(R.id.singIn);
         email=findViewById(R.id.eMail);
         password=findViewById(R.id.passwords);
         semail=findViewById(R.id.eMails);
         spassword=findViewById(R.id.passwordss);
         confirm_pass=findViewById(R.id.passwords01);
         sharedPreferences=getSharedPreferences(shared_pref_name,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("admin", "123456");
        editor.putString("teacher", "1234");
        editor.apply();
        log.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 try {
                     String emailchk = email.getText().toString();
                     String passchk = password.getText().toString();
                     String final_pass = "";
                     try {
                         final_pass = sharedPreferences.getString(emailchk, null).toString();
                     } catch (Exception e) {
                         Toast.makeText(MainActivity.this, "Invalid username try again", Toast.LENGTH_SHORT).show();
                     }
                    // Toast.makeText(MainActivity.this, final_pass, Toast.LENGTH_SHORT).show();
//                     if (passchk.equals("123456") && emailchk.equals("admin")) {
//                         Intent admin_log = new Intent(MainActivity.this, admin_activity.class);
//                         startActivity(admin_log);
//                         Toast.makeText(MainActivity.this, "welcome admin", Toast.LENGTH_SHORT).show();
//                     }
                      if (passchk.equals("1234") && emailchk.equals("teacher")) {
                         Intent teacher_log = new Intent(MainActivity.this, teacherActivity.class);
                         startActivity(teacher_log);
                     } else if (passchk.equals(final_pass)&&!emailchk.isEmpty()&&!passchk.isEmpty()) {
                        Toast.makeText(MainActivity.this, "welcome student", Toast.LENGTH_SHORT).show();
                         Intent student_log = new Intent(MainActivity.this, student_activity.class);
                         startActivity(student_log);
                     }
                      else
                          Toast.makeText(MainActivity.this, "Invalid username try again", Toast.LENGTH_SHORT).show();

                 }catch (Exception e){
                     Toast.makeText(MainActivity.this,e.toString(), Toast.LENGTH_SHORT).show();
                 }
             }
         });
         sing.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if (!semail.getText().toString().isEmpty()&&spassword.getText().toString().equals(confirm_pass.getText().toString()))
                 {
                     Toast.makeText(MainActivity.this,"confirm",Toast.LENGTH_SHORT).show();
                     SharedPreferences.Editor editor = sharedPreferences.edit();
                     editor.putString(semail.getText().toString(), spassword.getText().toString());
                     editor.apply();
                 }
                 else{
                     Toast.makeText(MainActivity.this,"fail",Toast.LENGTH_SHORT).show();
                 }
             }
         });
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sign_up.setTextColor(getResources().getColor(R.color.textColor));
                login.setTextColor(getResources().getColor(R.color.black));
                sign_up.setBackgroundDrawable(getResources().getDrawable(R.drawable.switch_trcks));
                login.setBackground(null);
                sign_uplay.setVisibility(View.VISIBLE);
                sing.setVisibility(View.VISIBLE);
                log.setVisibility(View.GONE);
                log_inlay.setVisibility(View.GONE);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sign_up.setBackground(null);
                sign_up.setTextColor(getResources().getColor(R.color.black));
                login.setBackgroundDrawable(getResources().getDrawable(R.drawable.switch_trcks));
                sign_uplay.setVisibility(View.GONE);
                log_inlay.setVisibility(View.VISIBLE);
                log.setVisibility(View.VISIBLE);
                sing.setVisibility(View.GONE);
                login.setTextColor(getResources().getColor(R.color.textColor));
            }
        });
    }
}