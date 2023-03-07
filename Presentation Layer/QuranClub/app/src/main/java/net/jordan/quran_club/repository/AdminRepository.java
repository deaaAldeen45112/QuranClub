package net.jordan.quran_club.repository;

import net.jordan.quran_club.datasource.remote.AdminApiService;
import net.jordan.quran_club.model.admin.Admin;
import net.jordan.quran_club.model.admin.ResponseAdmin;
import net.jordan.quran_club.model.Status;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class AdminRepository {
    private AdminApiService adminApiService;

    @Inject
    public AdminRepository(AdminApiService adminApiService) {
        this.adminApiService = adminApiService;
    }

    public Single<ResponseAdmin>getAdminsByUserloginId(int userloginId){return  adminApiService.getAdminsByUserloginId(userloginId);}
    public Single<ResponseAdmin> getAdmins(){
        return  adminApiService.getAdmins();
    }
    public Single<Status> insertAdmin(Admin admin){
       return adminApiService.insertAdmin(admin);
    }
    public Single<Status> deleteAdmin(Admin admin){
       return adminApiService.deleteAdmin(admin);
    }
    public Single<Status> updateAdmin(Admin admin) {
       return adminApiService.updateAdmin(admin);
    }
}
