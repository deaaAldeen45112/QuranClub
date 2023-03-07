package net.jordan.quran_club.ui.teacher.crud.lesson.insert;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import net.jordan.quran_club.R;
import net.jordan.quran_club.model.InsertionStatus;
import net.jordan.quran_club.model.Status;
import net.jordan.quran_club.model.classes.Class;
import net.jordan.quran_club.model.classes.ResponseClases;
import net.jordan.quran_club.model.lesson.Lesson;
import net.jordan.quran_club.model.student.ResponseStudentName;
import net.jordan.quran_club.model.student.StudentWithName;
import net.jordan.quran_club.model.studentLesson.StudentLesson;
import net.jordan.quran_club.model.studentLessonQuranPartSurah.StudentLessonQuranPartSurah;
import net.jordan.quran_club.resourses.Constant;

import java.util.ArrayList;
import java.util.Calendar;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class InsertLessonFragment extends Fragment  {

    private InsertLessonViewModel mViewModel;
    private ArrayList<Class> classes;
    private ArrayList<StudentWithName>students;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public static InsertLessonFragment newInstance(int teacherId) {


        InsertLessonFragment insertLessonFragment=new InsertLessonFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("teacher_Id",teacherId);
        insertLessonFragment.setArguments(bundle);
        return insertLessonFragment;
    }




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = new ViewModelProvider(this).get(InsertLessonViewModel.class);
        return inflater.inflate(R.layout.fragment_insert_lesson, container, false);

    }
    int lessonLastInsertId=-1;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Button insert = view.findViewById(R.id.insert);
        TextView textViewShowDate = view.findViewById(R.id.textView_show_date);
        Button changeDate=view.findViewById(R.id.change_date);
        TextView textViewShowTime = view.findViewById(R.id.textView_show_time);
        Button changeTime=view.findViewById(R.id.change_time);
        EditText editTextLessonName=view.findViewById(R.id.lesson_name);
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
                classArrayAdapter.clear();
                classArrayAdapter.addAll(classesName);
                classSpinner.setAdapter(classArrayAdapter);
            }
        });
        mViewModel.getClassesByTeacherId(getArguments().getInt("teacher_Id"));
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Lesson lesson=new Lesson();

                lesson.setName(editTextLessonName.getText().toString());
                lesson.setClassId(classes.get(classSpinner.getSelectedItemPosition()).getClassId());


                String dateConnectTime=textViewShowDate.getText().toString()+" "+textViewShowTime.getText().toString();
                lesson.setDateCreated(dateConnectTime);
                mViewModel.insertLesson(lesson);
              }
        });


        mViewModel.getStudentByClassIdLiveData().observe(getViewLifecycleOwner(), new Observer<ResponseStudentName>() {
            @Override
            public void onChanged(ResponseStudentName responseStudentName) {
                students= (ArrayList<StudentWithName>) responseStudentName.getData();
                if(lessonLastInsertId!=-1&&students!=null){

                    for (int i = 0; i < students.size(); i++) {
                        StudentLesson studentLesson=new StudentLesson();
                        studentLesson.setLessonId(lessonLastInsertId);
                        studentLesson.setStudentId(students.get(i).getStudentId());
                        studentLesson.setNote("");
                        mViewModel.insertStudentLesson(studentLesson);
                    }


                }

                else if(students==null){

                    Toast.makeText(getContext(), "waiting until get student and reclick", Toast.LENGTH_SHORT).show();
                }
            }
        });

        changeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // on below line we are setting date to our text view.
                                textViewShowDate.setText(year + "-" + (monthOfYear + 1) + "-" +dayOfMonth);

                            }
                        },
                         year, month, day);
                datePickerDialog.show();
            }
        });
        changeTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are getting the
                // instance of our calendar.
                final Calendar c = Calendar.getInstance();

                // on below line we are getting our hour, minute.
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                // on below line we are initializing our Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                // on below line we are setting selected time
                                // in our text view.
                                textViewShowTime.setText(hourOfDay + ":" + minute+":00");
                            }
                        }, hour, minute, false);
                // at last we are calling show to
                // display our time picker dialog.
                timePickerDialog.show();
            }
        });
        classSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                 }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        mViewModel.insertLessonLiveData().observe(getViewLifecycleOwner(), new Observer<InsertionStatus>() {
            @Override
            public void onChanged(InsertionStatus insertionStatus) {
                mViewModel.getStudentByClassId(classes.get(classSpinner.getSelectedItemPosition()).getClassId());

                if(insertionStatus.getStatus().equals("success")){
                     lessonLastInsertId= insertionStatus.getLastInsertId();
                    Toast.makeText(getContext(), Constant.detectStatus(insertionStatus.getStatus()), Toast.LENGTH_SHORT).show();
                   }
            }
        });

        mViewModel.insertStudentLessonQuranPartSurahLiveData().observe(getViewLifecycleOwner(), new Observer<Status>() {
            @Override
            public void onChanged(Status status) {
              }
        });


        mViewModel.insertStudentLessonLiveData().observe(getViewLifecycleOwner(), new Observer<InsertionStatus>() {
            @Override
            public void onChanged(InsertionStatus insertionStatus) {
                StudentLessonQuranPartSurah studentLessonQuranPartSurah=new StudentLessonQuranPartSurah();
                if(insertionStatus.getStatus().equals("success")){
                studentLessonQuranPartSurah.setStudentLessonId(insertionStatus.getLastInsertId());
                studentLessonQuranPartSurah.setQuranPartSurahId(1);
                mViewModel.insertStudentLessonQuranPartSurah(studentLessonQuranPartSurah);
                }

            }
        });







    }






}