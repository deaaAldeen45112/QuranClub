package net.jordan.quran_club.ui.Admin.crud.student.insert;

import android.util.Log;
import android.util.Patterns;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import net.jordan.quran_club.R;
import net.jordan.quran_club.model.Status;
import net.jordan.quran_club.model.classes.ResponseClases;
import net.jordan.quran_club.model.student.ResponseStudent;
import net.jordan.quran_club.model.student.Student;
import net.jordan.quran_club.model.userlogin.UserLogin;
import net.jordan.quran_club.repository.ClassRepository;
import net.jordan.quran_club.repository.StudentRepository;
import net.jordan.quran_club.repository.UserLoginRepository;

import java.util.regex.Pattern;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class InsertStudentViewModel extends ViewModel {

    private static final String TAG="InsertStudentViewModel";
    private MutableLiveData<ResponseClases> getClassesMutableLiveData=new MutableLiveData<>();
    private UserLoginRepository userLoginRepository;
    private ClassRepository classRepository;
    private StudentRepository studentRepository;
    private MutableLiveData<Status> updateStudentMutableLiveData=new MutableLiveData<>();
    private MutableLiveData<Status> statusInsertUserLogin=new MutableLiveData<>();
    private MutableLiveData<InsertStudentFormState> insertStudentFormStateMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ResponseStudent> getStudentByEmailMutableLiveData = new MutableLiveData<>();

    @ViewModelInject
    public InsertStudentViewModel(UserLoginRepository userLoginRepository,
                                  ClassRepository classRepository,
                                  StudentRepository studentRepository
                                  ) {
        this.userLoginRepository =userLoginRepository ;
        this.classRepository=classRepository;
        this.studentRepository=studentRepository;
    }
    LiveData<InsertStudentFormState> getInsertStudentFormState() {
        return insertStudentFormStateMutableLiveData ;
    }

    public void insertUserLogin(UserLogin userLogin){

        userLoginRepository.insertUserLogin(userLogin)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result-> {

                         statusInsertUserLogin.setValue(result);
                            Log.d(TAG,"insertUserLogin : "+result.getStatus());
                        },
                        error -> Log.d(TAG,   "insertUserLogin : "+error));
    }
    public void getStudentByEmail(String email){
        studentRepository.getStudentByEmail(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result->{
                            getStudentByEmailMutableLiveData.setValue(result);
                            Log.d(TAG, "getStudentByEmail: "+result.getStatus() );
                        },
                        error->{
                            Log.d(TAG, "getStudentByEmail: "+error);

                        }

                );



    }
    public LiveData<ResponseStudent>getStudentByEmailLiveData(){

        return  getStudentByEmailMutableLiveData;
    }
    public void updateStudent(Student student){
        studentRepository.updateStudent(student)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result->{
                            updateStudentMutableLiveData.setValue(result);
                            Log.d(TAG, "updateStudent: "+result.getStatus());
                        },
                        error->{
                            Log.d(TAG, "updateStudent: "+error);}
                        );

    }

    public LiveData<Status> updateStudentLiveData(){
        return updateStudentMutableLiveData;
    }
    public  LiveData<Status> getStatusInsertUserLogin(){


        return statusInsertUserLogin;
    }
    public void DataChanged(UserLogin userLogin) {
          if(!isFullNameValid(userLogin.getFullName())){
            insertStudentFormStateMutableLiveData.setValue(new InsertStudentFormState(R.string.invalid_full_name, null,null,null,null));
        }
          else if (!isEmailValid(userLogin.getEmail())) {
              insertStudentFormStateMutableLiveData.setValue(new InsertStudentFormState(null, null,null,null,R.string.invalid_email));
          }
          else if(!isPhoneValid(userLogin.getPhone())){
            insertStudentFormStateMutableLiveData.setValue(new InsertStudentFormState(null, null,R.string.invalid_phone,null,null));

        }
          else if(!isAgeValid(userLogin.getAgeString())){
              insertStudentFormStateMutableLiveData.setValue(new InsertStudentFormState(null, null,null,R.string.invalid_age,null));

          }
          else if (!isPasswordValid(userLogin.getPassword())) {
              insertStudentFormStateMutableLiveData.setValue(new InsertStudentFormState(null, R.string.invalid_password,null,null,null));
          }

          else {
            insertStudentFormStateMutableLiveData.setValue(new InsertStudentFormState(true));
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
                            Log.d("TAG", "getClasses: "+result.getStatus());
                        },
                        error->{
                            Log.d("TAG", "getClasses: "+error);
                        }
                );
    }



}