package net.jordan.quran_club.repository;

import android.util.Log;

import net.jordan.quran_club.datasource.local.UserLoginDao;
import net.jordan.quran_club.datasource.remote.UserLoginApiService;
import net.jordan.quran_club.model.Status;
import net.jordan.quran_club.model.userlogin.ResponseUserlogin;
import net.jordan.quran_club.model.userlogin.UserLogin;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class UserLoginRepository {
    private UserLoginApiService userLoginApiService;
    private UserLoginDao userLoginDao;
    @Inject
    public UserLoginRepository(UserLoginApiService userLoginApiService,UserLoginDao userLoginDao) {
        Log.d("TAG", "PokemonRepository: ");
        this.userLoginApiService = userLoginApiService;
        this.userLoginDao=userLoginDao;

    }
    public Single<ResponseUserlogin> getUserLogins(){
        return  userLoginApiService.getUserLogins();
    }
    public Single<Status> insertUserLogin(UserLogin userLogin){
       return   userLoginApiService.insertUserLogin(userLogin);
    }
    public Single<Status> updateUserLogin(UserLogin userLogin){return userLoginApiService.updateUserLogin(userLogin); }
    public Single<Status>  deleteUserLogin(int userloginId){return userLoginApiService.deleteUserLogin(userloginId);}
    public Single<ResponseUserlogin> getUserLoginByUserloginId(int userloginId){

        return userLoginApiService.getUserLoginByUserloginId(userloginId);
    }

    /*
   *
   * public void deleteAllUserloginLocal(){
        userLoginDao.delete();
   * }
   *   public List<UserLogin> getUserLoginsLocal() {
        return userLoginDao.getUserLogins();
    }

   *
   *
   *
   *
   * */

    /*public Single<List<UserLogin>> getUserLoginsLocal() {
        return userLoginDao.getUserLogins();
    }*/
    public Single<ResponseUserlogin>login(UserLogin userLogin){

            return  userLoginApiService.login(userLogin);


    }
    public Single<Status> verificationUserloginByEmail(String email){

     return userLoginApiService.verificationUserloginByEmail(email);
    }
    public Single<ResponseUserlogin> getUserLoginsByRoleName(String roleName){

        Log.d("TAG", "getUserLoginsByRoleName: "+roleName);
        return userLoginApiService.getUserLoginsByRoleName(roleName);
    }


}