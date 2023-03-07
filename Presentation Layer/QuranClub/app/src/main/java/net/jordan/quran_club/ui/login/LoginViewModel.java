package net.jordan.quran_club.ui.login;

import android.util.Log;
import android.util.Patterns;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import net.jordan.quran_club.R;
import net.jordan.quran_club.model.userlogin.ResponseUserlogin;
import net.jordan.quran_club.model.userlogin.UserLogin;
import net.jordan.quran_club.repository.UserLoginRepository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LoginViewModel extends ViewModel {
    private static final String TAG = "LoginViewModel";
    private UserLoginRepository userLoginRepository;
    private MutableLiveData<ResponseUserlogin> listMutableLiveData=new MutableLiveData<ResponseUserlogin>();
    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();

    @ViewModelInject
    public LoginViewModel(UserLoginRepository userLoginRepository ) {
        Log.d("TAG", "MainViewModel: ");
        this.userLoginRepository = userLoginRepository;
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }
    public void login(UserLogin userLogin){
        userLoginRepository.login(userLogin)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            listMutableLiveData.setValue(result);
                            Log.d(TAG,"login : "+result.getStatus());},
                        error -> Log.d(TAG, "login : "+error));


    }

    public MutableLiveData<ResponseUserlogin> getUserLoginByPasswordAndEmail() {
        return listMutableLiveData;
    }

    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_email, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {


        if (username == null) {
            return false;
        }
        if (Patterns.EMAIL_ADDRESS.matcher(username).matches()&&!username.trim().isEmpty()) {
          return  true;
        } else {
            return false;
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }

}

