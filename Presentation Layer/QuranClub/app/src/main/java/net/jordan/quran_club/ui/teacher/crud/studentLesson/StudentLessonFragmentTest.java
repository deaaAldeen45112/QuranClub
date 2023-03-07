package net.jordan.quran_club.ui.teacher.crud.studentLesson;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.jordan.quran_club.R;
import net.jordan.quran_club.adapter.ListShowStudentLessonViewHolderTestAdapter;
import net.jordan.quran_club.adapter.ListStudentLessonAdapter;
import net.jordan.quran_club.adapter.ListTeacherStudentLessonViewHolderAdapter;
import net.jordan.quran_club.model.classes.Class;
import net.jordan.quran_club.model.classes.ResponseClases;
import net.jordan.quran_club.model.lesson.Lesson;
import net.jordan.quran_club.model.lesson.ResponseLesson;
import net.jordan.quran_club.model.quranPartSurah.QuranPartSurahJoinQuranPartJoinSurah;
import net.jordan.quran_club.model.quranPartSurah.ResponseQuranPartSurahJoinQuranPartJoinSurah;
import net.jordan.quran_club.model.studentLesson.StudentLesson;
import net.jordan.quran_club.model.studentLessonQuranPartSurah.ResponseStudentLessonQuranPartSurahJoinStudentLessonJoinUserlogin;
import net.jordan.quran_club.model.studentLessonQuranPartSurah.StudentLessonQuranPartSurah;
import net.jordan.quran_club.model.studentLessonQuranPartSurah.StudentLessonQuranPartSurahJoinStudentLessonJoinUserlogin;
import net.jordan.quran_club.service.notification.Notification;
import net.jordan.quran_club.service.notification.SMS;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class StudentLessonFragmentTest extends Fragment {

    private StudentLessonViewModel mViewModel;
    private ArrayList<Class> classes;
    private ArrayList<Lesson> lessons;
    private ArrayList<QuranPartSurahJoinQuranPartJoinSurah> quranPartSurahJoinQuranPartJoinSurahs;
    private ArrayList<StudentLessonQuranPartSurahJoinStudentLessonJoinUserlogin> studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public static StudentLessonFragmentTest newInstance(int teacherId) {


        StudentLessonFragmentTest deleteLessonFragment = new StudentLessonFragmentTest();
        Bundle bundle = new Bundle();
        bundle.putInt("teacher_Id", teacherId);
        deleteLessonFragment.setArguments(bundle);
        return deleteLessonFragment;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = new ViewModelProvider(this).get(StudentLessonViewModel.class);

        return inflater.inflate(R.layout.frgment_student_lesson, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Button save = view.findViewById(R.id.save);
        AppCompatSpinner spinnerClass = view.findViewById(R.id.spinner_class);
        AppCompatSpinner spinnerLesson = view.findViewById(R.id.spinner_lesson);

        RecyclerView recyclerViewStudentLesson = view.findViewById(R.id.recyclerview_student_lesson);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());

        recyclerViewStudentLesson.setHasFixedSize(false);
        recyclerViewStudentLesson.setLayoutManager(layoutManager);




        ListTeacherStudentLessonViewHolderAdapter listStudentLessonAdapter = new ListTeacherStudentLessonViewHolderAdapter(getContext(), R.layout.elements_of_listview_show_student_lesson_teacher, this.quranPartSurahJoinQuranPartJoinSurahs);
        ArrayAdapter arrayAdapterClasses = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item);
        ArrayAdapter arrayAdapterLesson = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item);



        recyclerViewStudentLesson.setAdapter(listStudentLessonAdapter);
        mViewModel.getClassesByTeacherId(getArguments().getInt("teacher_Id"));
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

        spinnerClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                int classId = classes.get(position).getClassId();
                mViewModel.getLessonsByClassId(classId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mViewModel.getLessonsByClassIdLiveData().observe(getViewLifecycleOwner(), new Observer<ResponseLesson>() {
            @Override
            public void onChanged(ResponseLesson responseLesson) {

                lessons = (ArrayList<Lesson>) responseLesson.getData();
                ArrayList<String> lessonNames = new ArrayList<>();
                for (Lesson lesson : lessons) {
                    lessonNames.add(lesson.getName());
                }
                arrayAdapterLesson.clear();
                arrayAdapterLesson.addAll(lessonNames);
                spinnerLesson.setAdapter(arrayAdapterLesson);

            }
        });

        spinnerLesson.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                    int lessonId = lessons.get(position).getLessonId();
                    mViewModel.getStudentLessonQuranPartSurahJoinStudentLessonJoinUserlogin(lessonId);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        mViewModel.getQuranPartSurahJoinQuranPartJoinSurah();
        mViewModel.getQuranPartSurahJoinQuranPartJoinSurahLiveData().observe(getViewLifecycleOwner(), new Observer<ResponseQuranPartSurahJoinQuranPartJoinSurah>() {
            @Override
            public void onChanged(ResponseQuranPartSurahJoinQuranPartJoinSurah responseQuranPartSurahJoinQuranPartJoinSurah) {
                quranPartSurahJoinQuranPartJoinSurahs = (ArrayList<QuranPartSurahJoinQuranPartJoinSurah>) responseQuranPartSurahJoinQuranPartJoinSurah.getData();
                listStudentLessonAdapter.setQuranPartSurahJoinQuranPartJoinSurahs(quranPartSurahJoinQuranPartJoinSurahs);
               }
        });
        mViewModel.getStudentLessonQuranPartSurahJoinStudentLessonJoinUserloginLiveData().observe(getViewLifecycleOwner(), new Observer<ResponseStudentLessonQuranPartSurahJoinStudentLessonJoinUserlogin>() {
            @Override
            public void onChanged(ResponseStudentLessonQuranPartSurahJoinStudentLessonJoinUserlogin responseStudentLessonQuranPartSurahJoinStudentLessonJoinUserlogin) {

                studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins= (ArrayList<StudentLessonQuranPartSurahJoinStudentLessonJoinUserlogin>) responseStudentLessonQuranPartSurahJoinStudentLessonJoinUserlogin.getData();
                listStudentLessonAdapter.setStudentLessonQuranPartSurahJoinStudentLessonJoinUserlogins(studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins);

            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // UPDATE
                ArrayList<StudentLessonQuranPartSurah> arrayListForUpdating =listStudentLessonAdapter.getDataForUpdating();

                for (StudentLessonQuranPartSurah studentLessonQuranPartSurah : arrayListForUpdating) {
                    mViewModel.updateStudentLessonQuranPartSurah(studentLessonQuranPartSurah);


                }


                // SAVE
                ArrayList<StudentLessonQuranPartSurah> arrayListForSaving =listStudentLessonAdapter.getDataForSaving();

                for (StudentLessonQuranPartSurah studentLessonQuranPartSurah : arrayListForSaving)
                    mViewModel.insertStudentLessonQuranPartSurah(studentLessonQuranPartSurah);

                 //UPDATE NOTE

                ArrayList<StudentLesson>arrayListNotes=listStudentLessonAdapter.getNotes();

//
                for (StudentLesson studentLesson : arrayListNotes) {
                    mViewModel.updateStudentLessonNoteByStudentLessonId(studentLesson);

                }

                try {

                    ArrayList<String> messages=listStudentLessonAdapter.getStudentsMessages();

                    for (int i = 0; i <messages.size() ; i++) {
                        String message=messages.get(i);
                        int startIndex=listStudentLessonAdapter.getArrayListStartIndex().get(i);
                        SMS sms=new SMS();
                        String phone =studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.get(startIndex).getPhone();
                        sms.setTo(phone);
                        Notification notification=new Notification(sms);
                        notification.send(message);


                         }
                }

                catch (Exception exception){
                    Toast.makeText(getContext(), "الرسالة لم ترسل", Toast.LENGTH_SHORT).show();

                }

//
//
//                FragmentManager fragmentManager=getParentFragmentManager();
//                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.nav_host_fragment_content_main
//                        , StudentLessonFragmentTest
//                                .newInstance(getArguments()
//                                        .getInt("teacher_Id"))).commit();


                Toast.makeText(getContext(), "تمت العملية بنجاح", Toast.LENGTH_SHORT).show();




            }
        });



    }




}