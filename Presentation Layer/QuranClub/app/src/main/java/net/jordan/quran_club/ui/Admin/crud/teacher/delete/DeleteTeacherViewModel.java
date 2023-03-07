package net.jordan.quran_club.ui.Admin.crud.teacher.delete;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import net.jordan.quran_club.model.Status;
import net.jordan.quran_club.model.userlogin.ResponseUserlogin;
import net.jordan.quran_club.repository.TeacherRepository;
import net.jordan.quran_club.repository.UserLoginRepository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DeleteTeacherViewModel extends ViewModel {

    private static final String TAG ="DeleteTeacherViewModel" ;
    private UserLoginRepository userLoginRepository;
    private MutableLiveData<Status>deleteTeacherMutableLiveData=new MutableLiveData<>();

    private MutableLiveData<ResponseUserlogin>teachersMutableLiveData=new MutableLiveData<>();

    @ViewModelInject
    public DeleteTeacherViewModel(UserLoginRepository userLoginRepository , TeacherRepository teacherRepository) {
        this.userLoginRepository =userLoginRepository ;
    }
    public void deleteTeacher(int userloginId){

        userLoginRepository.deleteUserLogin(userloginId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result-> {

                           deleteTeacherMutableLiveData.setValue(result);
                           Log.d(TAG,"deleteTeacher : "+result.getStatus());
                        },
                        error -> Log.d(TAG, "deleteTeacher : "+error));


    }
    LiveData<ResponseUserlogin> getTeachersLiveData(){

        return teachersMutableLiveData;
    }
    public void getTeachers(String roleName){
        userLoginRepository.getUserLoginsByRoleName(roleName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result-> {
                            teachersMutableLiveData.setValue(result);
                            Log.d(TAG,"getTeachers : "+result.getStatus());
                        },
                        error -> Log.d(TAG, "getTeachers : "+error));


    }

    public MutableLiveData<Status> getDeleteTeacherMutableLiveData() {
        return deleteTeacherMutableLiveData;
    }
}