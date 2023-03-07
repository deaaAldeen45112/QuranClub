package net.jordan.quran_club.ui.student.showStudentLessons;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import net.jordan.quran_club.model.classes.ResponseClases;
import net.jordan.quran_club.model.quranPartSurah.ResponseQuranPartSurahJoinQuranPartJoinSurah;
import net.jordan.quran_club.model.studentLessonQuranPartSurah.ResponseStudentLessonQuranPartSurahJoinStudentLessonJoinUserlogin;
import net.jordan.quran_club.repository.ClassRepository;
import net.jordan.quran_club.repository.QuranPartSurahRepository;
import net.jordan.quran_club.repository.StudentLessonQuranPartSurahRepository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ShowStudentLessonViewModel extends ViewModel {


    private static final String TAG = "ShowStudentLessonVm";
    private ClassRepository classRepository;
    private StudentLessonQuranPartSurahRepository studentLessonQuranPartSurahRepository;
    private QuranPartSurahRepository quranPartSurahRepository;
    private MutableLiveData<ResponseClases> getClassesMutableLiveDate = new MutableLiveData<>();
    private MutableLiveData<ResponseStudentLessonQuranPartSurahJoinStudentLessonJoinUserlogin> getStudentLessonQuranPartSurahJoinStudentLessonJoinUserloginMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ResponseQuranPartSurahJoinQuranPartJoinSurah> getQuranPartSurahJoinQuranPartJoinSurah = new MutableLiveData<>();

    @ViewModelInject
    public ShowStudentLessonViewModel(
            ClassRepository classRepository,
            QuranPartSurahRepository quranPartSurahRepository,
            StudentLessonQuranPartSurahRepository studentLessonQuranPartSurahRepository) {
        this.classRepository = classRepository;
        this.quranPartSurahRepository = quranPartSurahRepository;
        this.studentLessonQuranPartSurahRepository = studentLessonQuranPartSurahRepository;
    }

    public void getClassesByTeacherId(int teacherId) {
        classRepository.getClassesByTeacherId(teacherId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> {
                            getClassesMutableLiveDate.setValue(result);
                            Log.d(TAG, "getClassesByTeacherId : " + result.getStatus());
                        },
                        error -> {
                            Log.d(TAG, "getClassesByTeacherId : " + error);
                        }
                );


    }
    public void getQuranPartSurahJoinQuranPartJoinSurah() {
        quranPartSurahRepository.getQuranPartSurahJoinQuranPartJoinSurah()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> {
                            getQuranPartSurahJoinQuranPartJoinSurah.setValue(result);
                            Log.d(TAG, "getQuranPartSurahJoinQuranPartJoinSurah : " + result.getStatus());
                        },
                        error -> {
                            Log.d(TAG, " getQuranPartSurahJoinQuranPartJoinSurah : " + error);
                        });


    }
    public LiveData<ResponseQuranPartSurahJoinQuranPartJoinSurah> getQuranPartSurahJoinQuranPartJoinSurahLiveData() {

        return getQuranPartSurahJoinQuranPartJoinSurah;

    }
    public void getStudentLessonQuranPartSurahJoinStudentLessonJoinUserloginByStudentId(int studentId) {

        studentLessonQuranPartSurahRepository.getStudentLessonQuranPartSurahJoinStudentLessonJoinUserloginByStudentId(studentId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> {
                            Log.d(TAG, "getStudentLessonQuranPartSurahJoinStudentLessonJoinUserlogin: " + result.getStatus());
                            getStudentLessonQuranPartSurahJoinStudentLessonJoinUserloginMutableLiveData.setValue(result);
                        }
                        , error -> {


                            Log.d(TAG, "getStudentLessonQuranPartSurahJoinStudentLessonJoinUserlogin: " + error);


                        });


    }
    public LiveData<ResponseStudentLessonQuranPartSurahJoinStudentLessonJoinUserlogin> getStudentLessonQuranPartSurahJoinStudentLessonJoinUserloginLiveData() {
        return getStudentLessonQuranPartSurahJoinStudentLessonJoinUserloginMutableLiveData;
    }

}