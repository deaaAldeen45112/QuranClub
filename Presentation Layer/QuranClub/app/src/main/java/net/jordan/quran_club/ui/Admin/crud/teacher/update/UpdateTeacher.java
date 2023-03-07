package net.jordan.quran_club.ui.Admin.crud.teacher.update;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import net.jordan.quran_club.R;
import net.jordan.quran_club.model.Status;
import net.jordan.quran_club.model.userlogin.ResponseUserlogin;
import net.jordan.quran_club.model.userlogin.UserLogin;
import net.jordan.quran_club.resourses.Constant;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class UpdateTeacher extends Fragment {

    private UpdateTeacherViewModel mViewModel;
    private  ArrayList<UserLogin>teachers;
    private UserLogin teacher;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public static UpdateTeacher newInstance() {
        return new UpdateTeacher();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = new ViewModelProvider(this).get(UpdateTeacherViewModel.class);

        return inflater.inflate(R.layout.fragment_update_teacher, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        UserLogin userLoginFormState =new UserLogin();
        AppCompatEditText fullName=view.findViewById(R.id.username);
        AppCompatEditText email=view.findViewById(R.id.email);
        AppCompatEditText phone=view.findViewById(R.id.phone);
        AppCompatEditText age=view.findViewById(R.id.age);
        AppCompatEditText password=view.findViewById(R.id.password);
        Button update=view.findViewById(R.id.update);



        AppCompatSpinner spinner =(AppCompatSpinner) view.findViewById(R.id.teacherSpinner);
        ArrayAdapter arrayAdapter=new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item);
        ArrayList<String> nameOfTeachers=new ArrayList<>();


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                teacher =teachers.get(position);

               fullName.setText(teacher.getFullName());
               email.setText(teacher.getEmail());
               phone.setText(teacher.getPhone());
               age.setText(teacher.getAge()+"");
               password.setText(teacher.getPassword());




            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        mViewModel.getTeachersLiveData().observe(getViewLifecycleOwner(), new Observer<ResponseUserlogin>() {
            @Override
            public void onChanged(ResponseUserlogin responseUserlogin) {
                arrayAdapter.clear();
                teachers= (ArrayList<UserLogin>) responseUserlogin.getData();

                for(UserLogin userLogin:teachers){

                    nameOfTeachers.add(userLogin.getFullName());

                }

                arrayAdapter.addAll(nameOfTeachers);
                spinner.setAdapter(arrayAdapter);

            }
        });
        mViewModel.getTeachers("Teacher");








        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                teacher.setPassword(password.getText().toString());
                teacher.setFullName(fullName.getText().toString());
                teacher.setPhone(phone.getText().toString());
                teacher.setEmail(email.getText().toString());
                teacher.setAge(Integer.parseInt(age.getText().toString()));

                mViewModel.updateUserLogin(teacher);


            }
        });


        mViewModel.getStatusUpdateUserLogin().observe(getViewLifecycleOwner(), new Observer<Status>() {
           @Override
           public void onChanged(Status status) {
               Toast.makeText(getContext(), Constant.detectStatus(status.getStatus()), Toast.LENGTH_SHORT).show();
               mViewModel.getTeachers("Teacher");
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
        mViewModel.getUpdateTeacherFormState().observe(getViewLifecycleOwner(), new Observer<UpdateTeacherFormState>() {
            @Override
            public void onChanged(@Nullable UpdateTeacherFormState updateTeacherFormState) {
                if (updateTeacherFormState == null) {
                    return;
                }
                update.setEnabled(updateTeacherFormState.isDataValid());

                if (updateTeacherFormState.getUsernameError() != null) {
                    fullName.setError(getString(updateTeacherFormState.getUsernameError()));
                }
                else{
                    fullName.setError(null);
                }
                if(updateTeacherFormState.getEmailError()!=null){
                    email.setError(getString(updateTeacherFormState.getEmailError()));
                }
                else {
                    email.setError(null);
                }
                if(updateTeacherFormState.getPhoneError()!=null){
                    phone.setError(getString(updateTeacherFormState.getPhoneError()));
                }
                else {
                    phone.setError(null);
                }
                if(updateTeacherFormState.getAgeError()!=null){
                    age.setError(getString(updateTeacherFormState.getAgeError()));
                }
                else {
                    age.setError(null);
                }
                if (updateTeacherFormState.getPasswordError() != null) {
                    password.setError(getString(updateTeacherFormState.getPasswordError()));
                }
                else{
                    password.setError(null);
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