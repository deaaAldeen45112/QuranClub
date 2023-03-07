package net.jordan.quran_club.ui.teacher.crud.quranPart;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import net.jordan.quran_club.model.Status;
import net.jordan.quran_club.model.classes.ResponseClases;
import net.jordan.quran_club.model.student.ResponseStudentName;
import net.jordan.quran_club.model.studentQuranPart.ResponseStudentQuranPart;
import net.jordan.quran_club.model.studentQuranPart.StudentQuranPart;
import net.jordan.quran_club.repository.ClassRepository;
import net.jordan.quran_club.repository.StudentQuranPartRepository;
import net.jordan.quran_club.repository.StudentRepository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class InsertAndDeleteQuranPartViewModel extends ViewModel {


    private static final String TAG ="InsAndDelQuranPartVm" ;

    private ClassRepository classRepository;
    private StudentRepository studentRepository;
    private StudentQuranPartRepository studentQuranPartRepository;
    private MutableLiveData<ResponseClases> getClassesMutableLiveData=new MutableLiveData<>();
    private MutableLiveData<ResponseStudentName> getStudentByClassIdMutableLiveData=new MutableLiveData<>();
    private MutableLiveData<ResponseStudentQuranPart> getStudentQuranPartByStudentIdMutableLiveData=new MutableLiveData<>();
    private MutableLiveData<Status> insertStudentQuranPartMutableLiveData =new MutableLiveData<>();
    private MutableLiveData<Status> deleteStudentQuranPartMutableLiveData=new MutableLiveData<>();


    @ViewModelInject
    public InsertAndDeleteQuranPartViewModel(
                                  StudentRepository studentRepository ,
                                  ClassRepository classRepository ,
                                  StudentQuranPartRepository studentQuranPartRepository
                                             ) {
        this.studentRepository=studentRepository;
        this.classRepository=classRepository;
        this.studentQuranPartRepository=studentQuranPartRepository;

    }

    public LiveData<ResponseClases>getClassesLiveData(){

        return  getClassesMutableLiveData;

    }

    public void insertStudentQuranPart(StudentQuranPart studentQuranPart){

        studentQuranPartRepository.insertStudentQuranPart(studentQuranPart)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result->{
                            insertStudentQuranPartMutableLiveData.setValue(result);
                            Log.d(TAG, "insertStudentQuranPart: "+result.getStatus());

                        },
                        error->{
                            Log.d(TAG, "insertStudentQuranPart: "+error);
                        }
                );



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
    public void getStudentByClassId(int classId){
        studentRepository.getStudentByClassId(classId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result->{
                    getStudentByClassIdMutableLiveData.setValue(result);

                    Log.d(TAG, "getStudentByClassId : "+result.getStatus());
                }, error->{
                    Log.d(TAG, "getStudentByClassId : "+error);
                });

    }
    public LiveData<ResponseStudentName> getStudentByClassIdLiveData(){

        return getStudentByClassIdMutableLiveData;
    }
    public void getStudentQuranPartByStudentId(int studentId){

        studentQuranPartRepository.getStudentQuranPartsByStudentId(studentId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result->{
                    getStudentQuranPartByStudentIdMutableLiveData.setValue(result);
                    Log.d(TAG, "getStudentQuranPartByStudentId: "+result);

                },error->{
                    Log.d(TAG, "getStudentQuranPartByStudentId: "+error);


                });

    }
    public LiveData<ResponseStudentQuranPart>getStudentQuranPartByStudentId(){
        return getStudentQuranPartByStudentIdMutableLiveData;
    }
    public void deleteStudentQuranPart(int  studentQuranPartId){
        studentQuranPartRepository.
                deleteStudentQuranPart(studentQuranPartId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result->{
                    deleteStudentQuranPartMutableLiveData.setValue(result);
                    Log.d(TAG, "deleteStudentQuranPart: "+result.getStatus());
                },error->{
                    Log.d(TAG, "deleteStudentQuranPart: "+error);
                });


    }
    public LiveData<Status> deleteStudentQuranPartLiveData(){
        return  deleteStudentQuranPartMutableLiveData;
    }
    public LiveData<Status> insertStudentQuranPartLiveData(){
        return  insertStudentQuranPartMutableLiveData;
    }
}