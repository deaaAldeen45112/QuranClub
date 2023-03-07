package net.jordan.quran_club.ui.student.level;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import net.jordan.quran_club.model.student.ResponseSavedPercentage;
import net.jordan.quran_club.model.studentQuranPart.ResponseStudentJoinQuranPart;
import net.jordan.quran_club.repository.StudentQuranPartRepository;
import net.jordan.quran_club.repository.StudentRepository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class StudentLevelViewModel extends ViewModel {


    private static final String TAG ="StudentLevelViewModel" ;

    private StudentRepository studentRepository;
    private StudentQuranPartRepository studentQuranPartRepository;
    private MutableLiveData<ResponseStudentJoinQuranPart> getStudentQuranPartsJoinQuranPartMutableLiveData=new MutableLiveData<>();
    private MutableLiveData<ResponseSavedPercentage> getSavedPercentageForEachQuranPartByStudentIdMutableLiveData=new MutableLiveData<>();


    @ViewModelInject
    public StudentLevelViewModel(StudentRepository studentRepository, StudentQuranPartRepository studentQuranPartRepository) {
        this.studentRepository=studentRepository;
        this.studentQuranPartRepository=studentQuranPartRepository;

    }

    public void getStudentQuranPartsJoinQuranPart(int studentId){

        studentQuranPartRepository.getStudentQuranPartsJoinQuranPart(studentId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result->{
                    getStudentQuranPartsJoinQuranPartMutableLiveData.setValue(result);
                    Log.d(TAG, "getStudentQuranPartByStudentId : "+result.getStatus());

                },error->{
                    Log.d(TAG, "getStudentQuranPartByStudentId : "+error);


                });

    }
    public LiveData<ResponseStudentJoinQuranPart>getStudentQuranPartsJoinQuranPartLiveData(){
        return getStudentQuranPartsJoinQuranPartMutableLiveData;
    }

    public void getSavedPercentageForEachQuranPartByStudentId(int studentId){

        studentRepository.getSavedPercentageForEachQuranPartByStudentId(studentId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result->{
                    getSavedPercentageForEachQuranPartByStudentIdMutableLiveData.setValue(result);
                    Log.d(TAG, "getSavedPercentageForEachQuranPartByStudentId: "+result.getStatus());

                },error->{
                    Log.d(TAG, "getSavedPercentageForEachQuranPartByStudentId: "+error);


                });

    }
    public LiveData<ResponseSavedPercentage>getSavedPercentageForEachQuranPartByStudentIdLiveData(){
        return getSavedPercentageForEachQuranPartByStudentIdMutableLiveData;
    }





}