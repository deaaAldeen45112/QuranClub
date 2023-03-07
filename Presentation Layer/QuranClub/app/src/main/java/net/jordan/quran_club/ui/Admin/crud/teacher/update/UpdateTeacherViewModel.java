package net.jordan.quran_club.ui.Admin.crud.teacher.update;

import android.util.Log;
import android.util.Patterns;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import net.jordan.quran_club.R;
import net.jordan.quran_club.model.Status;
import net.jordan.quran_club.model.userlogin.ResponseUserlogin;
import net.jordan.quran_club.model.userlogin.UserLogin;
import net.jordan.quran_club.repository.UserLoginRepository;

import java.util.regex.Pattern;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class UpdateTeacherViewModel extends ViewModel {

    private static final String TAG ="UpdateTeacherViewModel" ;
    private MutableLiveData<ResponseUserlogin>teachersMutableLiveData=new MutableLiveData<>();
    private UserLoginRepository userLoginRepository;
    private MutableLiveData<Status> statusUpdateUserLogin=new MutableLiveData<>();
    private MutableLiveData<UpdateTeacherFormState> UpdateTeacherFormStateMutableLiveData = new MutableLiveData<>();
    @ViewModelInject
    public UpdateTeacherViewModel(UserLoginRepository userLoginRepository ) {
        this.userLoginRepository =userLoginRepository ;
    }
    LiveData<UpdateTeacherFormState> getUpdateTeacherFormState() {
        return UpdateTeacherFormStateMutableLiveData ;
    }

    public void updateUserLogin(UserLogin userLogin){

        userLoginRepository.updateUserLogin(userLogin)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result-> {
                         statusUpdateUserLogin.setValue(result);
                            Log.d(TAG,"updateUserLogin : "+result.getStatus());
                        },
                        error -> Log.d(TAG,   "updateUserLogin : "+error));


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
    public  LiveData<Status> getStatusUpdateUserLogin(){


        return statusUpdateUserLogin;
    }


    public void DataChanged(UserLogin userLogin) {
        if(!isFullNameValid(userLogin.getFullName())){
            UpdateTeacherFormStateMutableLiveData.setValue(new UpdateTeacherFormState(R.string.invalid_full_name, null,null,null,null));
        }
        else if (!isEmailValid(userLogin.getEmail())) {
            UpdateTeacherFormStateMutableLiveData.setValue(new UpdateTeacherFormState(null, null,null,null,R.string.invalid_email));
        }
        else if(!isPhoneValid(userLogin.getPhone())){
            UpdateTeacherFormStateMutableLiveData.setValue(new UpdateTeacherFormState(null, null,R.string.invalid_phone,null,null));

        }
        else if(!isAgeValid(userLogin.getAgeString())){
            UpdateTeacherFormStateMutableLiveData.setValue(new UpdateTeacherFormState(null, null,null,R.string.invalid_age,null));

        }
        else if (!isPasswordValid(userLogin.getPassword())) {
            UpdateTeacherFormStateMutableLiveData.setValue(new UpdateTeacherFormState(null, R.string.invalid_password,null,null,null));
        }

        else {
            UpdateTeacherFormStateMutableLiveData.setValue(new UpdateTeacherFormState(true));
        }
    }

    private boolean isAgeValid(String age) {
        return age != null && Pattern.compile("[0-9]+").matcher(age).matches()&& age.trim().length() < 3 && age.trim().length() >0;

    }
    private boolean isPhoneValid(String phone) {

        return phone != null && phone.trim().length() >= 10 && Patterns.PHONE.matcher(phone).matches();
    }
    private boolean isFullNameValid(String fullName) {
        return fullName != null && fullName.trim().length() > 2;
    }
    private boolean isEmailValid(String email) {


        if (email == null) {
            return false;
        }
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()&&!email.trim().isEmpty()) {
            return  true;
        } else {
            return false;
        }
    }
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }
}