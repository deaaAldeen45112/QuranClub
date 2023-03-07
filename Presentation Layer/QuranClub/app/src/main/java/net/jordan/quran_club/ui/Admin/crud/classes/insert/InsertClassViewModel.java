package net.jordan.quran_club.ui.Admin.crud.classes.insert;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import net.jordan.quran_club.model.Status;
import net.jordan.quran_club.model.classes.Class;
import net.jordan.quran_club.model.teacher.ResponseTeacher;
import net.jordan.quran_club.model.userlogin.ResponseUserlogin;
import net.jordan.quran_club.repository.ClassRepository;
import net.jordan.quran_club.repository.TeacherRepository;
import net.jordan.quran_club.repository.UserLoginRepository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class InsertClassViewModel extends ViewModel {

    private static final String TAG="InsertClassViewModel";
    private MutableLiveData<ResponseUserlogin>teachersMutableLiveData=new MutableLiveData<>();
    private UserLoginRepository userLoginRepository;
    private ClassRepository classRepository;
    private TeacherRepository teacherRepository;
    private MutableLiveData<ResponseTeacher> getTeacherIdMutableLiveData=new MutableLiveData<>();
    private MutableLiveData<Status> insertClassMutableLiveData=new MutableLiveData<>();

   @ViewModelInject
    public InsertClassViewModel(UserLoginRepository userLoginRepository,
                                ClassRepository classRepository,
                                TeacherRepository teacherRepository
                                ) {

        this.userLoginRepository =userLoginRepository ;
        this.classRepository=classRepository;
        this.teacherRepository=teacherRepository;

    }

    public void insertClass(Class clas){

        classRepository.insertClass(clas)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result-> {
                         insertClassMutableLiveData.setValue(result);
                            Log.d(TAG,"insertClass : "+result.getStatus());
                        },
                        error -> Log.d(TAG, "insertClass : "+error));


    }
    public void getTeacherId(int userloginId){

        teacherRepository.getTeachersByUserLoginId(userloginId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result->{
                    getTeacherIdMutableLiveData.setValue(result);
                            Log.d(TAG,"getTeachersByUserLoginId : "+result.getStatus());

                        },
                  error->{

                      Log.d(TAG,"getTeachersByUserLoginId : "+error);

                  }


                );
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
    public  LiveData<Status> insertClassLiveData(){


        return insertClassMutableLiveData;
    }
    public  LiveData<ResponseTeacher> getTeacherIdLiveData(){


        return getTeacherIdMutableLiveData;
    }

}