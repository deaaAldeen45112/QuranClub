package net.jordan.quran_club.ui.Admin.crud.student.update;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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
import net.jordan.quran_club.model.student.ResponseStudentName;
import net.jordan.quran_club.model.student.Student;
import net.jordan.quran_club.model.student.StudentWithName;
import net.jordan.quran_club.model.userlogin.ResponseUserlogin;
import net.jordan.quran_club.model.userlogin.UserLogin;
import net.jordan.quran_club.resourses.Constant;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class UpdateStudent extends Fragment {



    private UpdateStudentViewModel mViewModel;
    private UserLogin studentUpdate;
    private ArrayList<Class> classes;
    private ArrayList<StudentWithName> studentsWithName;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {super.onCreate(savedInstanceState);
    }
    public static UpdateStudent newInstance() {
        return new UpdateStudent();
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(UpdateStudentViewModel.class);
        return inflater.inflate(R.layout.fragment_update_student, container, false);
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




        Spinner spinner = view.findViewById(R.id.spinner);
        Button update = view.findViewById(R.id.update);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item);
        AppCompatSpinner classSpinner = (AppCompatSpinner) view.findViewById(R.id.classSpinner);
        ArrayAdapter classArrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item);
        AppCompatSpinner classSelectSpinner = (AppCompatSpinner) view.findViewById(R.id.classSelectSpinner);


        mViewModel.getClassesLiveData().observe(getViewLifecycleOwner(), new Observer<ResponseClases>() {
            @Override
            public void onChanged(ResponseClases responseClases) {
                classes = (ArrayList<Class>) responseClases.getData();
                ArrayList<String> classesName = new ArrayList<>();
                for (Class clas : classes) {
                    classesName.add(clas.getName());
                }
                classArrayAdapter.addAll(classesName);
                classSpinner.setAdapter(classArrayAdapter);
                classSelectSpinner.setAdapter(classArrayAdapter);
            }
        });
        mViewModel.getClasses();
        spinner.setAdapter(arrayAdapter);


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                studentUpdate.setFullName(fullName.getText().toString());
                studentUpdate.setAge(Integer.parseInt(age.getText().toString()));
                studentUpdate.setEmail(email.getText().toString());
                studentUpdate.setPassword(password.getText().toString());
                studentUpdate.setPhone(phone.getText().toString());


                Student student=new Student();

                StudentWithName studentWithName = studentsWithName.get(spinner.getSelectedItemPosition());
                student.setStudentId(studentWithName.getStudentId());
                student.setUserloginId(studentWithName.getUserloginId());
                student.setClassId(classes.get(classSelectSpinner.getSelectedItemPosition()).getClassId());
                mViewModel.updateUserLogin(studentUpdate);
                mViewModel.updateStudent(student);

            }
        });
        classSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mViewModel.getStudentByClassId(classes.get(classSpinner.getSelectedItemPosition()).getClassId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        mViewModel.getStudentByClassIdLiveData().observe(getViewLifecycleOwner(), new Observer<ResponseStudentName>() {
            @Override
            public void onChanged(ResponseStudentName responseStudentName) {

                studentsWithName=(ArrayList<StudentWithName>) responseStudentName.getData();
                arrayAdapter.clear();
                arrayAdapter.addAll(getNameOfArrayStudent(responseStudentName.getData()));
                spinner.setAdapter(arrayAdapter);
            }
        });



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              int userloginId=  studentsWithName.get(position).getUserloginId();
              mViewModel.getUserloginByUserloginId(userloginId);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mViewModel.getUserloginByUserloginIdLiveData().observe(getViewLifecycleOwner(), new Observer<ResponseUserlogin>() {
            @Override
            public void onChanged(ResponseUserlogin responseUserlogin) {


                studentUpdate=responseUserlogin.getData().get(0);
                fullName.setText(studentUpdate.getFullName());
                email.setText(studentUpdate.getEmail());
                phone.setText(studentUpdate.getPhone());
                age.setText(studentUpdate.getAge()+"");
                password.setText(studentUpdate.getPassword());


            }
        });
        mViewModel.updateStudentLiveData().observe(getViewLifecycleOwner(), new Observer<Status>() {
            @Override
            public void onChanged(Status status) {
                mViewModel.getStudentByClassId(classes.get(classSpinner.getSelectedItemPosition()).getClassId());
                Toast.makeText(getContext(), Constant.detectStatus(status.getStatus()), Toast.LENGTH_SHORT).show();
            }
        });

        mViewModel.getUpdateStudentFormState().observe(getViewLifecycleOwner(), new Observer<UpdateStudentFormState>() {
            @Override
            public void onChanged(@Nullable UpdateStudentFormState updateStudentFormState) {
                if (updateStudentFormState == null) {
                    return;
                }
                update.setEnabled(updateStudentFormState.isDataValid());

                if (updateStudentFormState.getUsernameError() != null) {
                    fullName.setError(getString(updateStudentFormState.getUsernameError()));

                }
                else{
                    fullName.setError(null);
                }
                if(updateStudentFormState.getEmailError()!=null){
                    email.setError(getString(updateStudentFormState.getEmailError()));

                }
                else {
                    email.setError(null);
                }
                if(updateStudentFormState.getPhoneError()!=null){

                    phone.setError(getString(updateStudentFormState.getPhoneError()));

                }
                else {
                    phone.setError(null);
                }
                if(updateStudentFormState.getAgeError()!=null){
                    age.setError(getString(updateStudentFormState.getAgeError()));
                }
                else {
                    age.setError(null);
                }
                if (updateStudentFormState.getPasswordError() != null) {
                    password.setError(getString(updateStudentFormState.getPasswordError()));
                }
                else{
                    password.setError(null);
                }

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

        fullName.addTextChangedListener(afterTextChangedListener);
        phone.addTextChangedListener(afterTextChangedListener);
        age.addTextChangedListener(afterTextChangedListener);
        email.addTextChangedListener(afterTextChangedListener);
        password.addTextChangedListener(afterTextChangedListener);


    }


    private ArrayList<String> getNameOfArrayStudent(List<StudentWithName> responseStudentNames) {

        ArrayList<String> studentsName = new ArrayList<>();
        if (responseStudentNames != null) {

            for (StudentWithName studentWithName : responseStudentNames) {
                studentsName.add(studentWithName.getFullName());

            }

        }
        return studentsName;
    }

}