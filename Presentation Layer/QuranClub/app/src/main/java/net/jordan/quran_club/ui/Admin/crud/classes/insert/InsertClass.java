package net.jordan.quran_club.ui.Admin.crud.classes.insert;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import net.jordan.quran_club.R;
import net.jordan.quran_club.model.Status;
import net.jordan.quran_club.model.classes.Class;
import net.jordan.quran_club.model.teacher.ResponseTeacher;
import net.jordan.quran_club.model.userlogin.ResponseUserlogin;
import net.jordan.quran_club.model.userlogin.UserLogin;

import net.jordan.quran_club.resourses.Constant;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class InsertClass extends Fragment {

    private InsertClassViewModel mViewModel;
    private  ArrayList<UserLogin>teachers;
    private UserLogin teacher;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public static InsertClass newInstance() {
        return new InsertClass();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = new ViewModelProvider(this).get(InsertClassViewModel.class);

        return inflater.inflate(R.layout.fragment_insert_class, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AppCompatSpinner spinner =(AppCompatSpinner) view.findViewById(R.id.teacherSpinner);
        ArrayAdapter arrayAdapter=new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item);
        ArrayList<String> nameOfTeachers=new ArrayList<>();
        TextInputEditText classNameEditText=view.findViewById(R.id.classNameEditText);
        Button insert= view.findViewById(R.id.insert);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                teacher =teachers.get(position);





            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mViewModel.getTeachersLiveData().observe(getViewLifecycleOwner(), new Observer<ResponseUserlogin>() {
            @Override
            public void onChanged(ResponseUserlogin responseUserlogin) {

                teachers= (ArrayList<UserLogin>) responseUserlogin.getData();

                for(UserLogin userLogin:teachers){

                    nameOfTeachers.add(userLogin.getFullName());

                }

                arrayAdapter.addAll(nameOfTeachers);
                spinner.setAdapter(arrayAdapter);



            }
        });
        mViewModel.getTeachers("Teacher");
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 mViewModel.getTeacherId(teacher.getUserloginId());

            }
        });
        mViewModel.getTeacherIdLiveData().observe(getViewLifecycleOwner(), new Observer<ResponseTeacher>() {
            @Override
            public void onChanged(ResponseTeacher responseTeacher) {
                Class clas=new Class();
                clas.setName(classNameEditText.getText().toString());
                clas.setTeacherId(responseTeacher.getData().get(0).getTeacherId());
                mViewModel.insertClass(clas);
            }
        });
        mViewModel.insertClassLiveData().observe(getViewLifecycleOwner(), new Observer<Status>() {
            @Override
            public void onChanged(Status status) {
                Toast.makeText(getContext(), Constant.detectStatus(status.getStatus()), Toast.LENGTH_SHORT).show();

            }
        });


    }
}