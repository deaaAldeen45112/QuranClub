package net.jordan.quran_club.ui.teacher.crud.studentLesson;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import net.jordan.quran_club.model.classes.ResponseClases;
import net.jordan.quran_club.model.lesson.ResponseLesson;
import net.jordan.quran_club.model.quranPartSurah.ResponseQuranPartSurahJoinQuranPartJoinSurah;
import net.jordan.quran_club.model.studentLesson.StudentLesson;
import net.jordan.quran_club.model.studentLessonQuranPartSurah.ResponseStudentLessonQuranPartSurahJoinStudentLessonJoinUserlogin;
import net.jordan.quran_club.model.studentLessonQuranPartSurah.StudentLessonQuranPartSurah;
import net.jordan.quran_club.repository.ClassRepository;
import net.jordan.quran_club.repository.LessonRepository;
import net.jordan.quran_club.repository.QuranPartSurahRepository;
import net.jordan.quran_club.repository.StudentLessonQuranPartSurahRepository;
import net.jordan.quran_club.repository.StudentLessonRepository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class StudentLessonViewModel extends ViewModel {


    private static final String TAG = "StudentLessonViewModel";

    private ClassRepository classRepository;
    private LessonRepository lessonRepository;
    private StudentLessonRepository studentLessonRepository;
    private StudentLessonQuranPartSurahRepository studentLessonQuranPartSurahRepository;
    private QuranPartSurahRepository quranPartSurahRepository;
    private MutableLiveData<ResponseClases> getClassesMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ResponseStudentLessonQuranPartSurahJoinStudentLessonJoinUserlogin> getStudentLessonQuranPartSurahJoinStudentLessonJoinUserloginMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ResponseQuranPartSurahJoinQuranPartJoinSurah> getQuranPartSurahJoinQuranPartJoinSurah = new MutableLiveData<>();
    private MutableLiveData<ResponseLesson> getLessonsByClassIdMutableLiveData = new MutableLiveData<>();

    @ViewModelInject
    public StudentLessonViewModel(
            ClassRepository classRepository,
            StudentLessonRepository studentLessonRepository,
            LessonRepository lessonRepository,
            QuranPartSurahRepository quranPartSurahRepository,
            StudentLessonQuranPartSurahRepository studentLessonQuranPartSurahRepository
    ) {
        this.lessonRepository = lessonRepository;
        this.classRepository = classRepository;
        this.studentLessonRepository = studentLessonRepository;
        this.quranPartSurahRepository = quranPartSurahRepository;
        this.studentLessonQuranPartSurahRepository = studentLessonQuranPartSurahRepository;
    }

    public LiveData<ResponseClases> getClassesLiveData() {
        return getClassesMutableLiveData;
    }

    public void getClassesByTeacherId(int teacherId) {
        classRepository.getClassesByTeacherId(teacherId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> {
                            getClassesMutableLiveData.setValue(result);

                            Log.d(TAG, "getClassesByTeacherId : " + result.getStatus());
                        },
                        error -> {
                            Log.d(TAG, "getClassesByTeacherId : " + error);
                        }
                );


    }

    public void getLessonsByClassId(int classId) {
        lessonRepository.getLessonsByClassId(classId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> {
                            getLessonsByClassIdMutableLiveData.setValue(result);
                            Log.d(TAG, "getLessonsByClassId : " + result.getStatus());
                        },
                        error -> {
                            Log.d(TAG, "getLessonsByClassId : " + error);
                        }
                );


    }

    public LiveData<ResponseLesson> getLessonsByClassIdLiveData() {

        return getLessonsByClassIdMutableLiveData;

    }
    public void getQuranPartSurahJoinQuranPartJoinSurah() {
        quranPartSurahRepository.getQuranPartSurahJoinQuranPartJoinSurah()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> {
                            getQuranPartSurahJoinQuranPartJoinSurah.setValue(result);
                            Log.d(TAG, "getQuranPartSurah: " + result.getStatus());
                        },
                        error -> {
                            Log.d(TAG, "getQuranPartSurah: " + error);
                        });


    }
    public LiveData<ResponseQuranPartSurahJoinQuranPartJoinSurah> getQuranPartSurahJoinQuranPartJoinSurahLiveData() {

        return getQuranPartSurahJoinQuranPartJoinSurah;

    }
    public void getStudentLessonQuranPartSurahJoinStudentLessonJoinUserlogin(int lessonId) {

        studentLessonQuranPartSurahRepository.getStudentLessonQuranPartSurahJoinStudentLessonJoinUserlogin(lessonId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        resutl -> {
                            getStudentLessonQuranPartSurahJoinStudentLessonJoinUserloginMutableLiveData.setValue(resutl);
                            Log.d(TAG, "getStudentLessonQuranPartSurahJoinStudentLessonJoinUserlogin: " + resutl.getStatus());
                             }
                        , error -> {


                            Log.d(TAG, "getStudentLessonQuranPartSurahJoinStudentLessonJoinUserlogin: " + error);


                        });


    }
    public LiveData<ResponseStudentLessonQuranPartSurahJoinStudentLessonJoinUserlogin> getStudentLessonQuranPartSurahJoinStudentLessonJoinUserloginLiveData() {


        return getStudentLessonQuranPartSurahJoinStudentLessonJoinUserloginMutableLiveData;
    }
    public void updateStudentLessonQuranPartSurah(StudentLessonQuranPartSurah studentLessonQuranPartSurah) {

        studentLessonQuranPartSurahRepository.updateStudentLessonQuranPartSurah(studentLessonQuranPartSurah)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> {
                            Log.d(TAG, "updateStudentLessonQuranPartSurah: " + result.getStatus());
                            }
                        , error -> {
                            Log.d(TAG, "updateStudentLessonQuranPartSurah: " + error);


                        });


    }
    public void insertStudentLessonQuranPartSurah(StudentLessonQuranPartSurah studentLessonQuranPartSurah) {

        studentLessonQuranPartSurahRepository
                .insertStudentLessonQuranPartSurah(studentLessonQuranPartSurah)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> {
                            Log.d(TAG, "insertStudentLessonQuranPartSurah: " + result.getStatus());
                        }
                        , error -> {
                            Log.d(TAG, "insertStudentLessonQuranPartSurah: " + error);


                        });



    }
    public void updateStudentLessonNoteByStudentLessonId(StudentLesson studentLesson) {

        studentLessonRepository.updateStudentLessonNoteByStudentLessonId
                (studentLesson)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                         result -> {
                            Log.d(TAG, "updateStudentLessonNoteByStudentLessonId: " + result.getStatus());
                        }
                        , error -> {
                            Log.d(TAG, "updateStudentLessonNoteByStudentLessonId: " + error);


                        });



    }


}