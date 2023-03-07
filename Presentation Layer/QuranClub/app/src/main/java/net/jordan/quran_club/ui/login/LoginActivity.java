package net.jordan.quran_club.ui.login;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import net.jordan.quran_club.R;
import net.jordan.quran_club.model.userlogin.ResponseUserlogin;
import net.jordan.quran_club.model.userlogin.UserLogin;
import net.jordan.quran_club.ui.Admin.AdminActivity;
import net.jordan.quran_club.ui.student.StudentActivity;
import net.jordan.quran_club.ui.teacher.TeacherActivity;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginActivity extends AppCompatActivity {
    LoginViewModel loginViewModel;
    TextView passwordEditText;
    TextView usernameEditText;
    Button loginButton;
    ProgressBar loadingProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


          super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loadingProgressBar=findViewById(R.id.loading);
        usernameEditText=findViewById(R.id.username);
        passwordEditText=findViewById(R.id.password);
        loginButton=findViewById(R.id.login);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);


        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

       // loginViewModel.deleteAllUserloginLocal();

        loginViewModel.getUserLoginByPasswordAndEmail().observe(this, new Observer<ResponseUserlogin>() {
            @Override
            public void onChanged(ResponseUserlogin userLogin) {


                loadingProgressBar.setVisibility(View.INVISIBLE);

                if(userLogin.getStatus().equals("success"))
                {
                    List<UserLogin>userLogins=userLogin.getData();
                    String roleName=userLogins.get(0).getRoleName();
                    try {

                       //List<UserLogin>userloginLocal= loginViewModel.getUserLoginsLocal();
                      // if(userloginLocal.get(0).getUserloginId()!=userLogins.get(0).getUserloginId())
                        //loginViewModel.insertUserloginLocal(userLogins.get(0));


                        SharedPreferences sharedPref =getSharedPreferences("sharedQuranClub",MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putInt("userlogin_Id", userLogins.get(0).getUserloginId());
                        editor.putString("role_name", userLogins.get(0).getRoleName());
                        editor.putString("email", userLogins.get(0).getEmail());
                        editor.putString("full_name", userLogins.get(0).getFullName());



                        editor.commit();
                    }

                    catch(Exception e){


                }

                  transferToActivityByRoleName(roleName);
                  finish();


                }
                else
                {


                    ErrorDialogFragment errorDialogFragment=ErrorDialogFragment.newInstance("Information","there are fault username or password", R.drawable.ic_launcher_foreground);

                   errorDialogFragment.show(getSupportFragmentManager(),null);



                 //fff   Log.d("error", "onChanged:error ");
                }

            }
        });
       /* login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                UserLogin userLogin=new UserLogin();
                userLogin.setEmail(email.getText().toString());
                userLogin.setPassword(password.getText().toString());
                loginViewModel.login(userLogin);

            }
        });
        *///loginViewModel.verificationUserloginByEmail("deaa@gmail.com");


        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);



        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {

                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadingProgressBar.setVisibility(View.VISIBLE);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }
                UserLogin userLogin=new UserLogin();
                userLogin.setEmail(usernameEditText.getText().toString());
                userLogin.setPassword(passwordEditText.getText().toString());
                loginViewModel.login(userLogin);
            }
        });


    }


    void transferToActivityByRoleName(String roleName){
        if(roleName.equals("Student")){
            startActivity(new Intent(LoginActivity.this, StudentActivity.class));
        }
        else if (roleName.equals("Teacher")){

            startActivity(new Intent(LoginActivity.this, TeacherActivity.class));
        }
        else if (roleName.equals("Admin")){
            startActivity(new Intent(LoginActivity.this, AdminActivity.class));

        }

    }

}