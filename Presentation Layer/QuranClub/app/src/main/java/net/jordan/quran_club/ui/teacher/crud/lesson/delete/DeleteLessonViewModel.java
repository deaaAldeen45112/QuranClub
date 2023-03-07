package net.jordan.quran_club.ui.teacher.crud.lesson.delete;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import net.jordan.quran_club.model.Status;
import net.jordan.quran_club.model.classes.ResponseClases;
import net.jordan.quran_club.model.lesson.ResponseLesson;
import net.jordan.quran_club.repository.ClassRepository;
import net.jordan.quran_club.repository.LessonRepository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DeleteLessonViewModel extends ViewModel {

    private static final String TAG="DeleteLessonViewModel";

    private ClassRepository classRepository;
    private LessonRepository lessonRepository;
    private MutableLiveData<ResponseClases> getClassesMutableLiveData=new MutableLiveData<>();
    private MutableLiveData<ResponseLesson> getLessonsByClassIdMutableLiveData=new MutableLiveData<>();
    private MutableLiveData<Status> deleteLessonMutableLiveData=new MutableLiveData<>();

    @ViewModelInject
    public DeleteLessonViewModel(ClassRepository  classRepository,
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
                            Log.d(TAG, " getClassesByTeacherId : "+error);
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

    public void deleteLesson (int lessonId){
        lessonRepository.deleteLesson(lessonId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result-> {
                            deleteLessonMutableLiveData.setValue(result);
                             Log.d(TAG, "deleteLesson : "+result.getStatus());
                        },
                        error->{
                            Log.d(TAG, "deleteLesson: "+error);
                        }
                );



    }


    public LiveData<ResponseLesson> getLessonsByClassIdLiveData(){

        return getLessonsByClassIdMutableLiveData;

    }

    public LiveData<Status> deleteLessonLiveData(){

        return deleteLessonMutableLiveData;

    }


}