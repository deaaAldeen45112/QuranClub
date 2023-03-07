package net.jordan.quran_club.ui.student.showStudentLessons;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.jordan.quran_club.R;
import net.jordan.quran_club.adapter.ListShowStudentLessonViewHolderTestAdapter;
import net.jordan.quran_club.model.quranPartSurah.QuranPartSurahJoinQuranPartJoinSurah;
import net.jordan.quran_club.model.quranPartSurah.ResponseQuranPartSurahJoinQuranPartJoinSurah;
import net.jordan.quran_club.model.studentLessonQuranPartSurah.ResponseStudentLessonQuranPartSurahJoinStudentLessonJoinUserlogin;
import net.jordan.quran_club.model.studentLessonQuranPartSurah.StudentLessonQuranPartSurahJoinStudentLessonJoinUserlogin;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ShowStudentLessonFragment extends Fragment {

    private ShowStudentLessonViewModel mViewModel;
    private ArrayList<QuranPartSurahJoinQuranPartJoinSurah> quranPartSurahJoinQuranPartJoinSurahs;
    private ArrayList<StudentLessonQuranPartSurahJoinStudentLessonJoinUserlogin> studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public static ShowStudentLessonFragment newInstance(int studentId) {


        ShowStudentLessonFragment deleteLessonFragment = new ShowStudentLessonFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("student_id", studentId);
        deleteLessonFragment.setArguments(bundle);
        return deleteLessonFragment;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = new ViewModelProvider(this).get(ShowStudentLessonViewModel.class);

        return inflater.inflate(R.layout.fragment_show_student_lesson, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerViewStudentLesson = view.findViewById(R.id.recyclerview_student_lesson);
        ListShowStudentLessonViewHolderTestAdapter listShowStudentLessonAdapter = new ListShowStudentLessonViewHolderTestAdapter(getContext(), R.layout.elements_of_listview_show_student_lesson_test, this.quranPartSurahJoinQuranPartJoinSurahs);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());

        recyclerViewStudentLesson.setHasFixedSize(false);
        recyclerViewStudentLesson.setLayoutManager(layoutManager);
        recyclerViewStudentLesson.setAdapter(listShowStudentLessonAdapter);

        mViewModel.getQuranPartSurahJoinQuranPartJoinSurah();
        mViewModel.getQuranPartSurahJoinQuranPartJoinSurahLiveData().observe(getViewLifecycleOwner(), new Observer<ResponseQuranPartSurahJoinQuranPartJoinSurah>() {
            @Override
            public void onChanged(ResponseQuranPartSurahJoinQuranPartJoinSurah responseQuranPartSurahJoinQuranPartJoinSurah) {

                quranPartSurahJoinQuranPartJoinSurahs = (ArrayList<QuranPartSurahJoinQuranPartJoinSurah>) responseQuranPartSurahJoinQuranPartJoinSurah.getData();
                listShowStudentLessonAdapter.setQuranPartSurahJoinQuranPartJoinSurahs(quranPartSurahJoinQuranPartJoinSurahs);
                mViewModel.getStudentLessonQuranPartSurahJoinStudentLessonJoinUserloginByStudentId(getArguments().getInt("student_id"));

            }
        });
        mViewModel.getStudentLessonQuranPartSurahJoinStudentLessonJoinUserloginLiveData().observe(getViewLifecycleOwner(), new Observer<ResponseStudentLessonQuranPartSurahJoinStudentLessonJoinUserlogin>() {
            @Override
            public void onChanged(ResponseStudentLessonQuranPartSurahJoinStudentLessonJoinUserlogin responseStudentLessonQuranPartSurahJoinStudentLessonJoinUserlogin) {

                studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins= (ArrayList<StudentLessonQuranPartSurahJoinStudentLessonJoinUserlogin>) responseStudentLessonQuranPartSurahJoinStudentLessonJoinUserlogin.getData();
                listShowStudentLessonAdapter.setStudentLessonQuranPartSurahJoinStudentLessonJoinUserlogins(studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins);

            }
        });





    }


}