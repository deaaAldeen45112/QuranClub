package net.jordan.quran_club.ui.Admin.crud.teacher.insert;

import android.util.Log;
import android.util.Patterns;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import net.jordan.quran_club.R;
import net.jordan.quran_club.model.Status;
import net.jordan.quran_club.model.userlogin.UserLogin;
import net.jordan.quran_club.repository.UserLoginRepository;

import java.util.regex.Pattern;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class InsertTeacherViewModel extends ViewModel {

    private static final String TAG ="InsertTeacherViewModel" ;
    private UserLoginRepository userLoginRepository;
    private MutableLiveData<Status> statusInsertUserLogin=new MutableLiveData<>();
    private MutableLiveData<InsertTeacherFormState> insertTeacherFormStateMutableLiveData = new MutableLiveData<>();
    @ViewModelInject
    public InsertTeacherViewModel(UserLoginRepository userLoginRepository ) {
        this.userLoginRepository =userLoginRepository ;
    }
    LiveData<InsertTeacherFormState> getInsertTeacherFormState() {
        return insertTeacherFormStateMutableLiveData ;
    }

    public void insertUserLogin(UserLogin userLogin){

        userLoginRepository.insertUserLogin(userLogin)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result-> {

                         statusInsertUserLogin.setValue(result);
                            Log.d(TAG,"insertUserLogin : "+result.getStatus());
                        },
                        error -> Log.d(TAG, "insertUserLogin : "+error));


    }


    public  LiveData<Status> getStatusInsertUserLogin(){


        return statusInsertUserLogin;
    }

    public void DataChanged(UserLogin userLogin) {
        if(!isFullNameValid(userLogin.getFullName())){
            insertTeacherFormStateMutableLiveData.setValue(new InsertTeacherFormState(R.string.invalid_full_name, null,null,null,null));
        }
        else if (!isEmailValid(userLogin.getEmail())) {
            insertTeacherFormStateMutableLiveData.setValue(new InsertTeacherFormState(null, null,null,null,R.string.invalid_email));
        }
        else if(!isPhoneValid(userLogin.getPhone())){
            insertTeacherFormStateMutableLiveData.setValue(new InsertTeacherFormState(null, null,R.string.invalid_phone,null,null));

        }
        else if(!isAgeValid(userLogin.getAgeString())){
            insertTeacherFormStateMutableLiveData.setValue(new InsertTeacherFormState(null, null,null,R.string.invalid_age,null));

        }
        else if (!isPasswordValid(userLogin.getPassword())) {
            insertTeacherFormStateMutableLiveData.setValue(new InsertTeacherFormState(null, R.string.invalid_password,null,null,null));
        }

        else {
            insertTeacherFormStateMutableLiveData.setValue(new InsertTeacherFormState(true));
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