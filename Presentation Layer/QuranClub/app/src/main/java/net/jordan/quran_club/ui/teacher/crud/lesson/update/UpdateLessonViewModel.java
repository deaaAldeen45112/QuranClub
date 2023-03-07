package net.jordan.quran_club.ui.teacher.crud.lesson.update;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import net.jordan.quran_club.model.Status;
import net.jordan.quran_club.model.classes.ResponseClases;
import net.jordan.quran_club.model.lesson.Lesson;
import net.jordan.quran_club.model.lesson.ResponseLesson;
import net.jordan.quran_club.repository.ClassRepository;
import net.jordan.quran_club.repository.LessonRepository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class UpdateLessonViewModel extends ViewModel {


    private static final String TAG ="UpdateLessonViewModel" ;

    private ClassRepository classRepository;
    private LessonRepository lessonRepository;
    private MutableLiveData<ResponseClases> getClassesMutableLiveData=new MutableLiveData<>();
    private MutableLiveData<ResponseLesson> getLessonsByClassIdMutableLiveData=new MutableLiveData<>();
    private MutableLiveData<Status> updateLessonMutableLiveData=new MutableLiveData<>();
    @ViewModelInject
    public UpdateLessonViewModel( ClassRepository classRepository,
                                  LessonRepository lessonRepository) {
        this.lessonRepository=lessonRepository;
        this.classRepository=classRepository;

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

    public void updateLesson(Lesson lesson){
        lessonRepository.updateLesson(lesson)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result-> {
                            updateLessonMutableLiveData.setValue(result);
                            Log.d(TAG, "updateLesson : "+result.getStatus());
                        },
                        error->{
                            Log.d(TAG, "updateLesson : "+error);
                        }
                );



    }


    public void getLessonsByClassId (int classId){
        lessonRepository.getLessonsByClassId(classId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result-> {
                            getLessonsByClassIdMutableLiveData.setValue(result);
                            Log.d(TAG, "getLessonsByClassId : "+result.getStatus());
                        },
                        error->{
                            Log.d(TAG, "getLessonsByClassId : "+error);
                        }
                );



    }
    public LiveData<ResponseLesson> getLessonsByClassIdLiveData(){
        return getLessonsByClassIdMutableLiveData;

    }

    public LiveData<Status> updateLessonLiveData(){
        return updateLessonMutableLiveData;

    }


}