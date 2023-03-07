package net.jordan.quran_club.ui.Admin.crud.classes.update;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import net.jordan.quran_club.model.Status;
import net.jordan.quran_club.model.classes.Class;
import net.jordan.quran_club.model.classes.ResponseClases;
import net.jordan.quran_club.model.teacher.ResponseTeacher;
import net.jordan.quran_club.model.userlogin.ResponseUserlogin;
import net.jordan.quran_club.repository.ClassRepository;
import net.jordan.quran_club.repository.TeacherRepository;
import net.jordan.quran_club.repository.UserLoginRepository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class UpdateClassViewModel extends ViewModel {

    private static final String TAG="UpdateClassViewModel";
    private MutableLiveData<ResponseUserlogin>teachersMutableLiveData=new MutableLiveData<>();
    private UserLoginRepository userLoginRepository;
    private ClassRepository classRepository;
    private TeacherRepository teacherRepository;
    private MutableLiveData<Status> updateClassMutableLiveData=new MutableLiveData<>();
    private MutableLiveData<ResponseClases> getClassesMutableLiveData=new MutableLiveData<>();
    private MutableLiveData<ResponseTeacher> getTeacherByTeacherIdMutableLiveData=new MutableLiveData<>();
    private MutableLiveData<ResponseTeacher> getTeacherByUserLoginIdIdMutableLiveData=new MutableLiveData<>();

    @ViewModelInject
    public UpdateClassViewModel(UserLoginRepository userLoginRepository,
                                ClassRepository classRepository,
                                TeacherRepository teacherRepository
                                ) {

        this.userLoginRepository =userLoginRepository ;
        this.classRepository=classRepository;
        this.teacherRepository=teacherRepository;

    }




    public LiveData<ResponseClases>getClassesLiveData(){
        return  getClassesMutableLiveData;
    }
    public void getClasses(){
        classRepository.getClasses()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result-> {
                            getClassesMutableLiveData.setValue(result);

                            Log.d(TAG, "getClasses: "+result.getStatus());
                        },
                        error->{
                            Log.d(TAG, "getClasses: "+error);
                        }
                );



    }

    public  LiveData<ResponseTeacher>getTeacherByTeacherIdLiveData(){
       return getTeacherByTeacherIdMutableLiveData;
    }
    public void getTeacherByTeacherId(int teacherId){
       teacherRepository.getTeachersByTeacherId(teacherId)
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(
                       result -> {
                            getTeacherByTeacherIdMutableLiveData.setValue(result);
                           Log.d(TAG, "getTeacherByTeacherId : "+result.getStatus());
                       },
                       error->{
                           Log.d(TAG, "getTeacherByTeacherId : "+error );
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

    public void updateClass(Class clas){

        classRepository.updateClass(clas)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result-> {
                            updateClassMutableLiveData.setValue(result);

                            Log.d(TAG,"updateClass : "+result.getStatus());
                        },
                        error -> Log.d(TAG,   "updateClass : "+error));


    }
    public  LiveData<Status> updateClassLiveData(){


        return updateClassMutableLiveData;
    }

    public void getTeacherByUserLoginId(int userloginId){

        teacherRepository.getTeachersByUserLoginId(userloginId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result->{
                            getTeacherByUserLoginIdIdMutableLiveData.setValue(result);
                            Log.d(TAG, "getTeacherByUserLoginId: "+result.getStatus());
                        } ,
                        error->{
                            Log.d(TAG, "getTeacherByUserLoginId: "+error);
                        }


                );


    }
    public LiveData<ResponseTeacher>getTeacherByUserLoginIdLiveData(){

     return getTeacherByUserLoginIdIdMutableLiveData;
    }



}