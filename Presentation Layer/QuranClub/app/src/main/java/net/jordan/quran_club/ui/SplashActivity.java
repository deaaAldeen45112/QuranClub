package net.jordan.quran_club.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import net.jordan.quran_club.R;
import net.jordan.quran_club.ui.Admin.AdminActivity;
import net.jordan.quran_club.ui.login.LoginActivity;
import net.jordan.quran_club.ui.student.StudentActivity;
import net.jordan.quran_club.ui.teacher.TeacherActivity;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPref =getSharedPreferences("sharedQuranClub",MODE_PRIVATE);
                String roleName=sharedPref.getString("role_name","no");
                transferToActivityByRoleName(roleName);
                finish();

            }
        }, 3000);


    }
    void transferToActivityByRoleName(String roleName){
        if(roleName.equals("Student")){
            startActivity(new Intent(SplashActivity.this, StudentActivity.class));
        }
        else if (roleName.equals("Teacher")){

            startActivity(new Intent(SplashActivity.this, TeacherActivity.class));
        }
        else if (roleName.equals("Admin")){
            startActivity(new Intent(SplashActivity.this, AdminActivity.class));

        }
        else if (roleName.equals("no")){
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));

        }
    }




}
