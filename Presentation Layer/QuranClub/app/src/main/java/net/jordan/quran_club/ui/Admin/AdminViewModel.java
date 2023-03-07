package net.jordan.quran_club.ui.Admin;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import net.jordan.quran_club.model.admin.ResponseAdmin;
import net.jordan.quran_club.repository.AdminRepository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AdminViewModel extends ViewModel {


    private static final String TAG = "AdminViewModel";
    private AdminRepository adminRepository;
    private MutableLiveData<ResponseAdmin> getAdminMutableLiveDate=new MutableLiveData<ResponseAdmin>();
    @ViewModelInject
    public AdminViewModel(AdminRepository adminRepository ) {
        this.adminRepository = adminRepository;
    }


    LiveData<ResponseAdmin> getAdminLiveDate(){
        return  getAdminMutableLiveDate;
    }


    public void getAdminsByUserloginId(int userloginId){
        adminRepository
                .getAdminsByUserloginId(userloginId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result-> {

                         getAdminMutableLiveDate.setValue(result);

                            Log.d(TAG,"getAdminsByUserloginId : "+result.getStatus());
                        },
                        error -> Log.d(TAG, "getAdminsByUserloginId : "+error));


    }






}
