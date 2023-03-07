package net.jordan.quran_club.ui.Admin.crud.classes.delete;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import net.jordan.quran_club.model.Status;
import net.jordan.quran_club.model.classes.ResponseClases;
import net.jordan.quran_club.repository.ClassRepository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DeleteClassViewModel extends ViewModel {
    private static final String TAG="DeleteClassViewModel";
    private ClassRepository classRepository;
    private MutableLiveData<ResponseClases> getClassesMutableLiveData=new MutableLiveData<>();
    private MutableLiveData<Status> deleteClassMutableLiveData=new MutableLiveData<>();

   @ViewModelInject
    public DeleteClassViewModel(ClassRepository classRepository) {
        this.classRepository=classRepository;

    }
    public LiveData<ResponseClases> getClassesLiveData(){
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
    public void deleteClass(int classId){

       classRepository.deleteClass(classId)
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(result->{
                   deleteClassMutableLiveData.setValue(result);
                   Log.d(TAG, "deleteClass: "+result);
               }, error->{
                   Log.d(TAG, "deleteClass: "+error);
               });

    }
    public LiveData<Status>deleteClassLiveData(){

       return  deleteClassMutableLiveData;
    }

}
