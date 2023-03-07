package net.jordan.quran_club.ui.Admin.crud.student.delete;

import android.os.Bundle;
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
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import net.jordan.quran_club.R;
import net.jordan.quran_club.model.Status;
import net.jordan.quran_club.model.classes.Class;
import net.jordan.quran_club.model.classes.ResponseClases;
import net.jordan.quran_club.model.student.ResponseStudentName;
import net.jordan.quran_club.model.student.StudentWithName;
import net.jordan.quran_club.resourses.Constant;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DeleteStudent extends Fragment {

    private DeleteStudentViewModel mViewModel;
    private ArrayList<Class> classes;
    private ArrayList<StudentWithName> studentsWithName;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public static DeleteStudent newInstance() {
        return new DeleteStudent();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = new ViewModelProvider(this).get(DeleteStudentViewModel.class);

        return inflater.inflate(R.layout.fragment_delete_student, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Spinner spinner = view.findViewById(R.id.spinner);
        Button delete = view.findViewById(R.id.delete);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item);
        spinner.setAdapter(arrayAdapter);
        AppCompatSpinner classSpinner = (AppCompatSpinner) view.findViewById(R.id.classSpinner);
        ArrayAdapter classArrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item);

        classSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mViewModel.getStudentByClassId(classes.get(classSpinner.getSelectedItemPosition()).getClassId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
            }
        });
        mViewModel.getClasses();


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int idUserlogin =studentsWithName.get(spinner.getSelectedItemPosition()).getUserloginId();
                mViewModel.deleteStudent(idUserlogin);
               }
        });
        mViewModel.deleteStudentLiveData().observe(getViewLifecycleOwner(), new Observer<Status>() {
            @Override
            public void onChanged(Status status) {
                Toast.makeText(getContext(), Constant.detectStatus(status.getStatus()), Toast.LENGTH_SHORT).show();
                mViewModel.getStudentByClassId(classes.get(classSpinner.getSelectedItemPosition()).getClassId());

            }
        });
        mViewModel.getStudentByClassIdLiveData().observe(getViewLifecycleOwner(), new Observer<ResponseStudentName>() {
            @Override
            public void onChanged(ResponseStudentName responseStudentName) {
                studentsWithName=(ArrayList<StudentWithName>) responseStudentName.getData();
                arrayAdapter.clear();
                arrayAdapter.addAll(getNameOfArrayStudent(responseStudentName.getData()));
            }
        });


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