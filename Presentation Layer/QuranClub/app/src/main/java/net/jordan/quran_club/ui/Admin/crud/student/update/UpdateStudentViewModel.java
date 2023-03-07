package net.jordan.quran_club.ui.Admin.crud.student.update;

import android.util.Log;
import android.util.Patterns;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import net.jordan.quran_club.R;
import net.jordan.quran_club.model.Status;
import net.jordan.quran_club.model.classes.ResponseClases;
import net.jordan.quran_club.model.student.ResponseStudentName;
import net.jordan.quran_club.model.student.Student;
import net.jordan.quran_club.model.userlogin.ResponseUserlogin;
import net.jordan.quran_club.model.userlogin.UserLogin;
import net.jordan.quran_club.repository.ClassRepository;
import net.jordan.quran_club.repository.StudentRepository;
import net.jordan.quran_club.repository.UserLoginRepository;

import java.util.regex.Pattern;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class UpdateStudentViewModel extends ViewModel {


    private static final String TAG="UpdateStudentViewModel";
    private UserLoginRepository userLoginRepository;
    private ClassRepository classRepository;
    private StudentRepository studentRepository;
    private MutableLiveData<Status> updateStudentMutableLiveData=new MutableLiveData<>();
    private MutableLiveData<ResponseClases> getClassesMutableLiveData=new MutableLiveData<>();
    private MutableLiveData<ResponseStudentName> getStudentByClassIdMutableLiveData=new MutableLiveData<>();
    private MutableLiveData<ResponseUserlogin> getUserloginByUserloginIdMutableLiveData=new MutableLiveData<>();
    private MutableLiveData<UpdateStudentFormState> updateStudentFormStateMutableLiveData = new MutableLiveData<>();

    @ViewModelInject
    public UpdateStudentViewModel(UserLoginRepository userLoginRepository ,
                                  StudentRepository studentRepository ,
                                  ClassRepository classRepository) {
        this.studentRepository=studentRepository;
        this.userLoginRepository =userLoginRepository ;
        this.classRepository=classRepository;

    }

    public void updateUserLogin(UserLogin userLogin){

        userLoginRepository.updateUserLogin(userLogin)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result-> {

                           Log.d(TAG,"updateUserLogin(user) : "+result.getStatus());
                        },
                        error -> Log.d(TAG, "updateUserLogin(user) :  "+error));


    }

    public LiveData<ResponseClases>getClassesLiveData(){

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
    public void getUserloginByUserloginId(int userloginId){
        userLoginRepository.getUserLoginByUserloginId(userloginId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result->{

                    getUserloginByUserloginIdMutableLiveData.setValue(result);
                    Log.d(TAG, "getUserLoginByUserloginId : "+result.getStatus());
                }, error->{
                    Log.d(TAG, "getUserLoginByUserloginId : "+error);
                });

    }
    public LiveData<ResponseUserlogin> getUserloginByUserloginIdLiveData(){
        return getUserloginByUserloginIdMutableLiveData;


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


    public void DataChanged(UserLogin userLogin) {
        if(!isFullNameValid(userLogin.getFullName())){
            updateStudentFormStateMutableLiveData.setValue(new UpdateStudentFormState(R.string.invalid_full_name, null,null,null,null));
        }
        else if (!isEmailValid(userLogin.getEmail())) {

            updateStudentFormStateMutableLiveData.setValue(new UpdateStudentFormState(null, null,null,null,R.string.invalid_email));
        }
        else if(!isPhoneValid(userLogin.getPhone())){

            updateStudentFormStateMutableLiveData.setValue(new UpdateStudentFormState(null, null,R.string.invalid_phone,null,null));

        }
        else if(!isAgeValid(userLogin.getAgeString())){
            updateStudentFormStateMutableLiveData.setValue(new UpdateStudentFormState(null, null,null,R.string.invalid_age,null));

        }
        else if (!isPasswordValid(userLogin.getPassword())) {
            updateStudentFormStateMutableLiveData.setValue(new UpdateStudentFormState(null, R.string.invalid_password,null,null,null));
        }
        else {
            updateStudentFormStateMutableLiveData.setValue(new UpdateStudentFormState(true));
        }
    }
    LiveData<UpdateStudentFormState> getUpdateStudentFormState() {
        return updateStudentFormStateMutableLiveData ;
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