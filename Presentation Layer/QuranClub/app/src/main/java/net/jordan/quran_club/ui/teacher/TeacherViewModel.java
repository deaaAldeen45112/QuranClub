package net.jordan.quran_club.ui.teacher;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import net.jordan.quran_club.model.teacher.ResponseTeacher;
import net.jordan.quran_club.repository.TeacherRepository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class TeacherViewModel extends ViewModel {
    private static final String TAG = "TeacherViewModel";
    private MutableLiveData<ResponseTeacher> getTeacherMutableLiveData=new MutableLiveData<>();
    private TeacherRepository teacherRepository;

    @ViewModelInject
    public TeacherViewModel(TeacherRepository teacherRepository) {
        this.teacherRepository=teacherRepository;
    }

    public void getTeacherByUserLoginId(int userloginId){
            teacherRepository.getTeachersByUserLoginId(userloginId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(result -> {
                                getTeacherMutableLiveData.setValue(result);
                                Log.d(TAG,"getTeachersByUserLoginId : "+result.getStatus());
                            },
                            error -> Log.d(TAG, "getTeachersByUserLoginId : " +error));
        }

    public LiveData<ResponseTeacher>getTeacherLiveData(){
        return getTeacherMutableLiveData;

    }




}
