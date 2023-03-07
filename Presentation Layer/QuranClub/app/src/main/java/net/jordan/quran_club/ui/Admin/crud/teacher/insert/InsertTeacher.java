package net.jordan.quran_club.ui.Admin.crud.teacher.insert;

import androidx.appcompat.widget.AppCompatEditText;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import net.jordan.quran_club.R;
import net.jordan.quran_club.model.Status;
import net.jordan.quran_club.model.userlogin.UserLogin;
import net.jordan.quran_club.resourses.Constant;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class InsertTeacher extends Fragment {

    private InsertTeacherViewModel mViewModel;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public static InsertTeacher newInstance() {
        return new InsertTeacher();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = new ViewModelProvider(this).get(InsertTeacherViewModel.class);

        return inflater.inflate(R.layout.fragment_insert_teacher, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        UserLogin userLoginFormState=new UserLogin();
        AppCompatEditText fullName=view.findViewById(R.id.username);
        AppCompatEditText email=view.findViewById(R.id.email);
        AppCompatEditText phone=view.findViewById(R.id.phone);
        AppCompatEditText age=view.findViewById(R.id.age);
        AppCompatEditText password=view.findViewById(R.id.password);
        Button insert=view.findViewById(R.id.insert);


        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserLogin userLogin=new UserLogin();
                userLogin.setPassword(password.getText().toString());
                userLogin.setFullName(fullName.getText().toString());
                userLogin.setPhone(phone.getText().toString());
                userLogin.setEmail(email.getText().toString());
                userLogin.setAge(Integer.parseInt(age.getText().toString()));
                userLogin.setRoleName("Teacher");
                mViewModel.insertUserLogin(userLogin);


            }
        });


        mViewModel.getStatusInsertUserLogin().observe(getViewLifecycleOwner(), new Observer<Status>() {
           @Override
           public void onChanged(Status status) {
               Toast.makeText(getContext(), Constant.detectStatus(status.getStatus()), Toast.LENGTH_SHORT).show();
           }
       });
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
                userLoginFormState.setPassword(password.getText().toString());
                userLoginFormState.setEmail(email.getText().toString());
                userLoginFormState.setPhone(phone.getText().toString());
                userLoginFormState.setAgeString(age.getText().toString());
                userLoginFormState.setFullName(fullName.getText().toString());

                mViewModel.DataChanged(userLoginFormState);
            }
        };
        mViewModel.getInsertTeacherFormState().observe(getViewLifecycleOwner(), new Observer<InsertTeacherFormState>() {
            @Override
            public void onChanged(@Nullable InsertTeacherFormState insertTeacherFormState) {
                if (insertTeacherFormState == null) {
                    return;
                }
                insert.setEnabled(insertTeacherFormState.isDataValid());

                if (insertTeacherFormState.getUsernameError() != null) {
                    fullName.setError(getString(insertTeacherFormState.getUsernameError()));
                }
                if(insertTeacherFormState.getEmailError()!=null){
                    email.setError(getString(insertTeacherFormState.getEmailError()));
                }
                if(insertTeacherFormState.getPhoneError()!=null){
                    phone.setError(getString(insertTeacherFormState.getPhoneError()));
                }
                if(insertTeacherFormState.getAgeError()!=null){
                    age.setError(getString(insertTeacherFormState.getAgeError()));
                }
                if (insertTeacherFormState.getPasswordError() != null) {
                    password.setError(getString(insertTeacherFormState.getPasswordError()));
                }

            }
        });



        fullName.addTextChangedListener(afterTextChangedListener);
        phone.addTextChangedListener(afterTextChangedListener);
        age.addTextChangedListener(afterTextChangedListener);
        email.addTextChangedListener(afterTextChangedListener);
        password.addTextChangedListener(afterTextChangedListener);
        mViewModel.DataChanged(userLoginFormState);




    }
}