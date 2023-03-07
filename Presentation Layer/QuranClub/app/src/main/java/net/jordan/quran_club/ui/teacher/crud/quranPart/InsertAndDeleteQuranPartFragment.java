package net.jordan.quran_club.ui.teacher.crud.quranPart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import net.jordan.quran_club.R;
import net.jordan.quran_club.adapter.ListStudentQuranPartAdapter;
import net.jordan.quran_club.model.Status;
import net.jordan.quran_club.model.classes.Class;
import net.jordan.quran_club.model.classes.ResponseClases;
import net.jordan.quran_club.model.student.ResponseStudentName;
import net.jordan.quran_club.model.student.StudentWithName;
import net.jordan.quran_club.model.studentQuranPart.ResponseStudentQuranPart;
import net.jordan.quran_club.model.studentQuranPart.StudentQuranPart;
import net.jordan.quran_club.resourses.Constant;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class InsertAndDeleteQuranPartFragment extends Fragment implements ListStudentQuranPartAdapter.ListListener {

    private InsertAndDeleteQuranPartViewModel mViewModel;
    Spinner spinner;
    private ArrayList<Class> classes;
    private ArrayList<StudentWithName> studenstWithName;
    private ArrayList<StudentQuranPart> studentQuranParts=new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public static InsertAndDeleteQuranPartFragment newInstance(int teacherId) {


        InsertAndDeleteQuranPartFragment insertAndDeleteQuranPartFragment=new InsertAndDeleteQuranPartFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("teacher_Id",teacherId);
        insertAndDeleteQuranPartFragment.setArguments(bundle);
        return insertAndDeleteQuranPartFragment;
    }




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = new ViewModelProvider(this).get(InsertAndDeleteQuranPartViewModel.class);

        return inflater.inflate(R.layout.fragment_insert_delete_quran_part, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        spinner = view.findViewById(R.id.spinner);
        Button insert = view.findViewById(R.id.insert);
        ListView listStudentQuranPart=view.findViewById(R.id.list_student_quran_part);
        ListStudentQuranPartAdapter listStudentQuranPartAdapter=new ListStudentQuranPartAdapter(getContext(), R.layout.student_quran_part_list, studentQuranParts,this);
        AppCompatSpinner numberOfDuanSpinner =view.findViewById(R.id.numberOfDuanSpinner);
        ArrayAdapter numberOfDuanArrayAdapter=new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item);
        ArrayList<Integer> numberOfDuan=new ArrayList<>();

        listStudentQuranPart.setAdapter(listStudentQuranPartAdapter);

        for (int i = 1; i <=30 ; i++) {
            numberOfDuan.add(i);
        }
        numberOfDuanArrayAdapter.addAll(numberOfDuan);
        numberOfDuanSpinner.setAdapter(numberOfDuanArrayAdapter);

        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item);

        spinner.setAdapter(arrayAdapter);
        AppCompatSpinner classSpinner = (AppCompatSpinner) view.findViewById(R.id.classSpinner);
        ArrayAdapter classArrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item);




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
        mViewModel.getClassesByTeacherId(getArguments().getInt("teacher_Id"));
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                StudentQuranPart studentQuranPart=new StudentQuranPart();
                int studentId=studenstWithName.get(spinner.getSelectedItemPosition()).getStudentId();
                studentQuranPart.setStudentId(studentId);
                int quranPartId= numberOfDuanSpinner.getSelectedItemPosition()+1;
                studentQuranPart.setQuranPartId(quranPartId);
                mViewModel.insertStudentQuranPart(studentQuranPart);
                mViewModel.getStudentQuranPartByStudentId(studenstWithName.get(spinner.getSelectedItemPosition()).getStudentId());

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
                studenstWithName=(ArrayList<StudentWithName>) responseStudentName.getData();
                arrayAdapter.clear();
                arrayAdapter.addAll(getNameOfArrayStudent(responseStudentName.getData()));
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mViewModel.getStudentQuranPartByStudentId(studenstWithName.get(spinner.getSelectedItemPosition()).getStudentId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mViewModel.getStudentQuranPartByStudentId().observe(getViewLifecycleOwner(), new Observer<ResponseStudentQuranPart>() {
            @Override
            public void onChanged(ResponseStudentQuranPart responseStudentQuranPart) {
                studentQuranParts= (ArrayList<StudentQuranPart>) responseStudentQuranPart.getData();
                listStudentQuranPartAdapter.AddAll(studentQuranParts);

            }
        });
        mViewModel.deleteStudentQuranPartLiveData().observe(getViewLifecycleOwner(), new Observer<Status>() {
            @Override
            public void onChanged(Status status) {
                Toast.makeText(getContext(), Constant.detectStatus(status.getStatus()), Toast.LENGTH_SHORT).show();
                mViewModel.getStudentQuranPartByStudentId(studenstWithName.get(spinner.getSelectedItemPosition()).getStudentId());

            }
        });
        mViewModel.insertStudentQuranPartLiveData().observe(getViewLifecycleOwner(), new Observer<Status>() {
            @Override
            public void onChanged(Status status) {
                Toast.makeText(getContext(), Constant.detectStatus(status.getStatus()), Toast.LENGTH_SHORT).show();
                mViewModel.getStudentQuranPartByStudentId(studenstWithName.get(spinner.getSelectedItemPosition()).getStudentId());

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


    @Override
    public void onClickDeleteButton(int  studentQuranPartId) {

        mViewModel.deleteStudentQuranPart(studentQuranPartId);
        mViewModel.getStudentQuranPartByStudentId(studenstWithName.get(spinner.getSelectedItemPosition()).getStudentId());

    }
}