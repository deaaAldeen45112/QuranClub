package net.jordan.quran_club.ui.teacher.crud.lesson.insert;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import net.jordan.quran_club.model.InsertionStatus;
import net.jordan.quran_club.model.Status;
import net.jordan.quran_club.model.classes.ResponseClases;
import net.jordan.quran_club.model.lesson.Lesson;
import net.jordan.quran_club.model.student.ResponseStudentName;
import net.jordan.quran_club.model.studentLesson.StudentLesson;
import net.jordan.quran_club.model.studentLessonQuranPartSurah.StudentLessonQuranPartSurah;
import net.jordan.quran_club.repository.ClassRepository;
import net.jordan.quran_club.repository.LessonRepository;
import net.jordan.quran_club.repository.StudentLessonQuranPartSurahRepository;
import net.jordan.quran_club.repository.StudentLessonRepository;
import net.jordan.quran_club.repository.StudentRepository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class InsertLessonViewModel extends ViewModel {


    private static final String TAG ="InsertLessonViewModel" ;

    private ClassRepository classRepository;
    private LessonRepository lessonRepository;
    private StudentRepository studentRepository;
    private StudentLessonQuranPartSurahRepository studentLessonQuranPartSurahRepository;
    private StudentLessonRepository studentLessonRepository;


    private MutableLiveData<ResponseClases> getClassesMutableLiveData=new MutableLiveData<>();
    private MutableLiveData<InsertionStatus> insertLessonMutableLiveData=new MutableLiveData<>();
    private MutableLiveData<InsertionStatus> insertStudentLessonMutableLiveData=new MutableLiveData<>();
    private MutableLiveData<Status> insertStudentLessonQuranPartSurahMutableLiveData=new MutableLiveData<>();
    private MutableLiveData<ResponseStudentName> getStudentByClassIdMutableLiveData=new MutableLiveData<>();

    @ViewModelInject
    public InsertLessonViewModel(
                                  StudentRepository studentRepository,
                                  ClassRepository classRepository ,
                                  LessonRepository lessonRepository,
                                  StudentLessonQuranPartSurahRepository studentLessonQuranPartSurahRepository,
                                  StudentLessonRepository studentLessonRepository
                                             ) {
        this.lessonRepository=lessonRepository;
        this.classRepository=classRepository;
        this.studentLessonQuranPartSurahRepository=studentLessonQuranPartSurahRepository;
        this.studentLessonRepository=studentLessonRepository;
        this.studentRepository=studentRepository;
    }
    public LiveData<ResponseClases>getClassesLiveData(){
        return  getClassesMutableLiveData;
    }
    public void getClassesByTeacherId(int teacherId){
        classRepository.getClassesByTeacherId(teacherId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result-> {
                            getClassesMutableLiveData.setValue(result);

                            Log.d(TAG, "getClassesByTeacherId : "+result.getStatus());
                        },
                        error->{
                            Log.d(TAG, "getClassesByTeacherId : "+error);
                        }
                );



    }
    public void insertLesson(Lesson lesson){
        lessonRepository.insertLesson(lesson)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result-> {

                            insertLessonMutableLiveData.setValue(result);
                            Log.d(TAG, "insertLesson : "+result.getStatus());
                        },
                        error->{
                            Log.d(TAG, "insertLesson : "+error);
                        }
                );



    }


    public LiveData<InsertionStatus>insertLessonLiveData(){

        return  insertLessonMutableLiveData;

    }
    public void insertStudentLessonQuranPartSurah(StudentLessonQuranPartSurah studentLessonQuranPartSurah) {

        studentLessonQuranPartSurahRepository
                .insertStudentLessonQuranPartSurah(studentLessonQuranPartSurah)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> {
                            insertStudentLessonQuranPartSurahMutableLiveData.setValue(result);
                            Log.d(TAG, "insertStudentLessonQuranPartSurah : " + result.getStatus());
                        }
                        , error -> {
                            Log.d(TAG, "insertStudentLessonQuranPartSurah : " + error);


                        });



    }
    public void insertStudentLesson(StudentLesson studentLesson) {

        studentLessonRepository.insertStudentLesson(studentLesson)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> {
                            insertStudentLessonMutableLiveData.setValue(result);
                            Log.d(TAG, "insertStudentLesson : " + result);
                        }
                        , error -> {
                            Log.d(TAG, "insertStudentLesson : " + error);


                        });



    }

    public void getStudentByClassId(int classId){
        studentRepository.getStudentByClassId(classId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result->{
                    getStudentByClassIdMutableLiveData.setValue(result);

                    Log.d(TAG, "getStudentByClassId : "+result.getData());
                }, error->{
                    Log.d(TAG, "getStudentByClassId : "+error);
                });

    }
    public LiveData<ResponseStudentName> getStudentByClassIdLiveData(){

        return getStudentByClassIdMutableLiveData;
    }
    public LiveData<Status> insertStudentLessonQuranPartSurahLiveData(){
        return insertStudentLessonQuranPartSurahMutableLiveData;
    }
    public LiveData<InsertionStatus> insertStudentLessonLiveData(){
        return insertStudentLessonMutableLiveData;
    }

}