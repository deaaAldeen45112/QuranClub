package net.jordan.quran_club.ui.teacher.crud.lesson.update;

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
import net.jordan.quran_club.model.Status;
import net.jordan.quran_club.model.classes.Class;
import net.jordan.quran_club.model.classes.ResponseClases;
import net.jordan.quran_club.model.lesson.Lesson;
import net.jordan.quran_club.model.lesson.ResponseLesson;
import net.jordan.quran_club.resourses.Constant;

import java.util.ArrayList;
import java.util.Calendar;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class UpdateLessonFragment extends Fragment  {
    private UpdateLessonViewModel mViewModel;
    private ArrayList<Class> classes;
    private ArrayList<Lesson> lessons;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public static UpdateLessonFragment newInstance(int teacherId) {


        UpdateLessonFragment updateLessonFragment=new UpdateLessonFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("teacher_Id",teacherId);
        updateLessonFragment.setArguments(bundle);
        return updateLessonFragment;
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mViewModel = new ViewModelProvider(this).get(UpdateLessonViewModel.class);

        return inflater.inflate(R.layout.fragment_update_lesson, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Button update = view.findViewById(R.id.update);
        TextView textViewShowDate = view.findViewById(R.id.textView_show_date);
        Button changeDate=view.findViewById(R.id.change_date);
        TextView textViewShowTime = view.findViewById(R.id.textView_show_time);
        Button changeTime=view.findViewById(R.id.change_time);
        EditText editTextLessonName=view.findViewById(R.id.lesson_name);
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
                arrayAdapterLesson.clear();
                lessons =(ArrayList<Lesson>) responseLesson.getData();
                ArrayList<String> lessonNames = new ArrayList<>();
                for (Lesson lesson : lessons) {
                    lessonNames.add(lesson.getName());
                }
                arrayAdapterLesson.addAll(lessonNames);
                spinnerLesson.setAdapter(arrayAdapterLesson);

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

        spinnerLesson.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

              if(lessons.size()>0) {
                  Lesson lesson = lessons.get(position);


                  if (lesson.getDateCreated() != null) {
                      String[] date = lesson.getDateCreated().split(" ");
                      textViewShowDate.setText(date[0]);
                      textViewShowTime.setText(date[1]);
                  }
                  editTextLessonName.setText(lesson.getName());
              }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Lesson lesson=new Lesson();
            lesson.setName(editTextLessonName.getText().toString());
            lesson.setClassId(classes.get(spinnerClass.getSelectedItemPosition()).getClassId());
            String dateConnectTime=textViewShowDate.getText().toString()+" "+textViewShowTime.getText().toString();
            lesson.setDateCreated(dateConnectTime);
            lesson.setLessonId(lessons.get(spinnerLesson.getSelectedItemPosition()).getLessonId());
            mViewModel.updateLesson(lesson);
            }
        });
        mViewModel.updateLessonLiveData().observe(getViewLifecycleOwner(), new Observer<Status>() {
            @Override
            public void onChanged(Status status) {
                Toast.makeText(getContext(), Constant.detectStatus(status.getStatus()), Toast.LENGTH_SHORT).show();
                mViewModel.getLessonsByClassId(classes.get(spinnerClass.getSelectedItemPosition()).getClassId());
            }
        });
    }

}