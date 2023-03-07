package net.jordan.quran_club.ui.Admin.crud.student.delete;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import net.jordan.quran_club.model.Status;
import net.jordan.quran_club.model.classes.ResponseClases;
import net.jordan.quran_club.model.student.ResponseStudentName;
import net.jordan.quran_club.repository.ClassRepository;
import net.jordan.quran_club.repository.StudentRepository;
import net.jordan.quran_club.repository.UserLoginRepository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DeleteStudentViewModel extends ViewModel {


    private static final String TAG ="DeleteStudentViewModel" ;
    private UserLoginRepository userLoginRepository;
    private ClassRepository classRepository;
    private StudentRepository studentRepository;
    private MutableLiveData<Status> deleteMutableLiveData=new MutableLiveData<>();
    private MutableLiveData<ResponseClases> getClassesMutableLiveData=new MutableLiveData<>();
    private MutableLiveData<ResponseStudentName> getStudentByClassIdMutableLiveData=new MutableLiveData<>();

    @ViewModelInject
    public DeleteStudentViewModel(UserLoginRepository userLoginRepository ,
                                  StudentRepository studentRepository ,
                                  ClassRepository classRepository) {

        this.studentRepository=studentRepository;
        this.userLoginRepository =userLoginRepository ;
        this.classRepository=classRepository;

    }
    public void deleteStudent(int userloginId){

        userLoginRepository.deleteUserLogin(userloginId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result-> {
                    deleteMutableLiveData.setValue(result);
                    Log.d(TAG,"deleteStudent : "+result.getStatus());
                    }, error -> Log.d(TAG,   "deleteStudent : "+error));


    }
    public LiveData<Status>deleteStudentLiveData(){

        return deleteMutableLiveData;
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
    public LiveData<ResponseClases>getClassesLiveData(){

        return  getClassesMutableLiveData;

    }

    public void getStudentByClassId(int classId){
        studentRepository.getStudentByClassId(classId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result->{
                    getStudentByClassIdMutableLiveData.setValue(result);

                    Log.d(TAG, "Result: "+result.getData());
                }, error->{
                    Log.d(TAG, "Error: "+error);
                });

    }
    public LiveData<ResponseStudentName> getStudentByClassIdLiveData(){

        return getStudentByClassIdMutableLiveData;
    }

}