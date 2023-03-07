package net.jordan.quran_club.ui.Admin.crud.student.insert;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import net.jordan.quran_club.model.classes.Class;
import net.jordan.quran_club.model.classes.ResponseClases;
import net.jordan.quran_club.model.student.ResponseStudent;
import net.jordan.quran_club.model.student.Student;
import net.jordan.quran_club.model.userlogin.UserLogin;
import net.jordan.quran_club.resourses.Constant;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class InsertStudent extends Fragment {

    private InsertStudentViewModel mViewModel;
    private ArrayList<Class>classes;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public static InsertStudent newInstance() {
        return new InsertStudent();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = new ViewModelProvider(this).get(InsertStudentViewModel.class);

        return inflater.inflate(R.layout.fragment_insert_student, container, false);
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

        AppCompatSpinner classSpinner =view.findViewById(R.id.classSpinner);
        ArrayAdapter classArrayAdapter=new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item);

        mViewModel.getClassesLiveData().observe(getViewLifecycleOwner(), new Observer<ResponseClases>() {
            @Override
            public void onChanged(ResponseClases responseClases) {

                classes=(ArrayList<Class>) responseClases.getData();
                ArrayList<String>classesName=new ArrayList<>();
                for(Class clas:classes){
                    classesName.add(clas.getName());
                }
                classArrayAdapter.addAll(classesName);
                classSpinner.setAdapter(classArrayAdapter);

            }
        });
        mViewModel.getClasses();
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserLogin userLogin=new UserLogin();
                userLogin.setPassword(password.getText().toString());
                userLogin.setFullName(fullName.getText().toString());
                userLogin.setPhone(phone.getText().toString());
                userLogin.setEmail(email.getText().toString());
                userLogin.setAge(Integer.parseInt(age.getText().toString()));
                userLogin.setRoleName("Student");
                mViewModel.insertUserLogin(userLogin);

            }
        });
        mViewModel.getStatusInsertUserLogin().observe(getViewLifecycleOwner(), new Observer<Status>() {
             @Override
             public void onChanged(Status status) {
                 if(status.getStatus().compareTo("success")==0)
                 mViewModel.getStudentByEmail(email.getText().toString().trim());

             }
         });
        mViewModel.getStudentByEmailLiveData().observe(getViewLifecycleOwner(), new Observer<ResponseStudent>() {
            @Override
            public void onChanged(ResponseStudent responseStudent) {

                Student student=responseStudent.getData().get(0);
                student.setClassId(classes.get(classSpinner.getSelectedItemPosition()).getClassId());
                mViewModel.updateStudent(student);

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
        mViewModel.getInsertStudentFormState().observe(getViewLifecycleOwner(), new Observer<InsertStudentFormState>() {
            @Override
            public void onChanged(@Nullable InsertStudentFormState insertStudentFormState) {
                if (insertStudentFormState == null) {
                    return;
                }
                insert.setEnabled(insertStudentFormState.isDataValid());

                if (insertStudentFormState.getUsernameError() != null) {
                    fullName.setError(getString(insertStudentFormState.getUsernameError()));
                }
                if(insertStudentFormState.getEmailError()!=null){
                    email.setError(getString(insertStudentFormState.getEmailError()));
                }
                if(insertStudentFormState.getPhoneError()!=null){
                    phone.setError(getString(insertStudentFormState.getPhoneError()));
                }
                if(insertStudentFormState.getAgeError()!=null){
                    age.setError(getString(insertStudentFormState.getAgeError()));
                }
                if (insertStudentFormState.getPasswordError() != null) {
                    password.setError(getString(insertStudentFormState.getPasswordError()));
                }

            }
        });

        mViewModel.updateStudentLiveData().observe(getViewLifecycleOwner(), new Observer<Status>() {
            @Override
            public void onChanged(Status status) {
                Toast.makeText(getContext(), Constant.detectStatus(status.getStatus()), Toast.LENGTH_SHORT).show();
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