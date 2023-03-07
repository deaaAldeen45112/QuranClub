package net.jordan.quran_club.ui.student;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import net.jordan.quran_club.model.student.ResponseStudent;
import net.jordan.quran_club.repository.StudentRepository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class StudentViewModel extends ViewModel {
    private static final String TAG = "StudentViewModel";
    private StudentRepository studentRepository;
    private MutableLiveData<ResponseStudent> studentMutableLiveData=new MutableLiveData<>();



    @ViewModelInject
    public StudentViewModel(StudentRepository studentRepository  ) {
       this.studentRepository=studentRepository;
    }



    public MutableLiveData<ResponseStudent> getStudentMutableLiveData() {
        return studentMutableLiveData;
    }

    public void getStudentByUserloginId(int id){



        studentRepository.getStudentByUserloginId(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            studentMutableLiveData.setValue(result);
                            Log.d(TAG,"getStudentByUserloginId : "+result.getStatus());
                        },
                        error -> Log.d(TAG, "getStudentByUserloginId : "+error));
    }

}
