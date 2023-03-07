package net.jordan.quran_club.ui.Admin.crud.classes.update;

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
import net.jordan.quran_club.model.classes.ResponseClases;
import net.jordan.quran_club.model.teacher.ResponseTeacher;
import net.jordan.quran_club.model.userlogin.ResponseUserlogin;
import net.jordan.quran_club.model.userlogin.UserLogin;
import net.jordan.quran_club.resourses.Constant;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class UpdateClass extends Fragment {

    private UpdateClassViewModel mViewModel;
    private  ArrayList<UserLogin>teachers;
    private  ArrayList<Class>classes;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public static UpdateClass newInstance() {
        return new UpdateClass();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = new ViewModelProvider(this).get(UpdateClassViewModel.class);

        return inflater.inflate(R.layout.fragment_update_class, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AppCompatSpinner classSpinner =(AppCompatSpinner) view.findViewById(R.id.classSpinner);
        AppCompatSpinner teacherSpinner =(AppCompatSpinner) view.findViewById(R.id.teacherSpinner);
        TextInputEditText classNameEditText=view.findViewById(R.id.classNameEditText);
        Button update= view.findViewById(R.id.update);

        ArrayAdapter arrayAdapter=new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item);
        ArrayAdapter classArrayAdapter=new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item);
        ArrayList<String> nameOfTeachers=new ArrayList<>();

        classSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                int teacherId=classes.get(position).getTeacherId();
                mViewModel.getTeacherByTeacherId(teacherId);




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
                teacherSpinner.setAdapter(arrayAdapter);


            }
        });
        mViewModel.getTeachers("Teacher");


        mViewModel.getClassesLiveData().observe(getViewLifecycleOwner(), new Observer<ResponseClases>() {
            @Override
            public void onChanged(ResponseClases responseClases) {
                classArrayAdapter.clear();
                classes= (ArrayList<Class>) responseClases.getData();
                ArrayList<String>classesName=new ArrayList<>();
                for(Class clas:classes){

                    classesName.add(clas.getName());

                }

                classArrayAdapter.addAll(classesName);
                classSpinner.setAdapter(classArrayAdapter);



            }
        });
        mViewModel.getClasses();


        mViewModel.getTeacherByTeacherIdLiveData().observe(getViewLifecycleOwner(), new Observer<ResponseTeacher>() {
            @Override
            public void onChanged(ResponseTeacher responseTeacher) {

                int userloginId= responseTeacher.getData().get(0).getUserloginId();
                int i=0;
                for(UserLogin teacher:teachers){

                    if(teacher.getUserloginId()==userloginId){
                        teacherSpinner.setSelection(i);

                    }
                    i++;
                }

            }
        });



        mViewModel.getTeacherByUserLoginIdLiveData().observe(getViewLifecycleOwner(), new Observer<ResponseTeacher>() {
            @Override
            public void onChanged(ResponseTeacher responseTeacher) {
                Class clas=new Class();
                clas.setName(classNameEditText.getText().toString());
                clas.setTeacherId(responseTeacher.getData().get(0).getTeacherId());
                clas.setClassId(classes.get(classSpinner.getSelectedItemPosition()).getClassId());
                 mViewModel.updateClass(clas);

            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mViewModel.getTeacherByUserLoginId(teachers.get(teacherSpinner.getSelectedItemPosition()).getUserloginId());


            }
        });

        mViewModel.updateClassLiveData().observe(getViewLifecycleOwner(), new Observer<Status>() {
            @Override
            public void onChanged(Status status) {
                Toast.makeText(getContext(), Constant.detectStatus(status.getStatus()), Toast.LENGTH_SHORT).show();
                mViewModel.getClasses();

            }
        });

    }
}