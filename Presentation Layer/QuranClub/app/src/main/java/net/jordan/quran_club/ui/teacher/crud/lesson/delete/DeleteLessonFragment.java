package net.jordan.quran_club.ui.teacher.crud.lesson.delete;

import android.os.Bundle;
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
import net.jordan.quran_club.model.lesson.Lesson;
import net.jordan.quran_club.model.lesson.ResponseLesson;
import net.jordan.quran_club.resourses.Constant;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DeleteLessonFragment extends Fragment  {

    private DeleteLessonViewModel mViewModel;
    private ArrayList<Class> classes;
    private ArrayList<Lesson> lessons;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public static DeleteLessonFragment newInstance(int teacherId) {


        DeleteLessonFragment deleteLessonFragment=new DeleteLessonFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("teacher_Id",teacherId);
        deleteLessonFragment.setArguments(bundle);
        return deleteLessonFragment;
    }




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = new ViewModelProvider(this).get(DeleteLessonViewModel.class);

        return inflater.inflate(R.layout.fragment_delete_lesson, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Button delete = view.findViewById(R.id.delete);
        AppCompatSpinner spinnerClass = view.findViewById(R.id.spinner_class);
        AppCompatSpinner spinnerLesson =view.findViewById(R.id.spinner_lesson);
        ArrayAdapter arrayAdapterClasses = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item);
        ArrayAdapter arrayAdapterLesson = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item);

        mViewModel.getClassesLiveData().observe(getViewLifecycleOwner(), new Observer<ResponseClases>() {
            @Override
            public void onChanged(ResponseClases responseClases) {
                classes = (ArrayList<Class>) responseClases.getData();
                ArrayList<String> classesName = new ArrayList<>();
                for (Class clas : classes) {
                    classesName.add(clas.getName());
                }
                arrayAdapterClasses.clear();
                arrayAdapterClasses.addAll(classesName);
                spinnerClass.setAdapter(arrayAdapterClasses);
            }
        });
        mViewModel.getClassesByTeacherId(getArguments().getInt("teacher_Id"));
        spinnerClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                int classId=classes.get(position).getClassId();
                mViewModel.getLessonsByClassId(classId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mViewModel.getLessonsByClassIdLiveData().observe(getViewLifecycleOwner(), new Observer<ResponseLesson>() {
            @Override
            public void onChanged(ResponseLesson responseLesson) {

                lessons =(ArrayList<Lesson>) responseLesson.getData();
                ArrayList<String> lessonNames = new ArrayList<>();
                for (Lesson lesson : lessons) {
                    lessonNames.add(lesson.getName());
                }
                arrayAdapterLesson.clear();
                arrayAdapterLesson.addAll(lessonNames);
                spinnerLesson.setAdapter(arrayAdapterLesson);

            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int lessonId=lessons.get(spinnerLesson.getSelectedItemPosition()).getLessonId();
                mViewModel.deleteLesson(lessonId);
            }
        });

        mViewModel.deleteLessonLiveData().observe(getViewLifecycleOwner(), new Observer<Status>() {
            @Override
            public void onChanged(Status status) {
                Toast.makeText(getContext(), Constant.detectStatus(status.getStatus()), Toast.LENGTH_SHORT).show();
                mViewModel.getClassesByTeacherId(getArguments().getInt("teacher_Id"));
            }
        });











    }






}