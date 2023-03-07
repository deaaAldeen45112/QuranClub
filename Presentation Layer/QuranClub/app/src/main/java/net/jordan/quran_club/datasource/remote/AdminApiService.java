package net.jordan.quran_club.datasource.remote;

import static net.jordan.quran_club.resourses.Constant.ADMIN;

import net.jordan.quran_club.model.admin.Admin;
import net.jordan.quran_club.model.admin.ResponseAdmin;
import net.jordan.quran_club.model.Status;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface AdminApiService {
    @GET(ADMIN+"/read.php")
    Single<ResponseAdmin> getAdmins();
    @GET(ADMIN+"/read_by_userlogin_id.php")
    Single<ResponseAdmin> getAdminsByUserloginId(@Query("userloginId")int userloginId);
    @POST(ADMIN+"/create.php")
    Single<Status> insertAdmin(@Body Admin admin);
    @PUT(ADMIN+"/update.php")
    Single<Status> updateAdmin(@Body Admin admin);
    @DELETE(ADMIN+"/delete.php")
    Single<Status> deleteAdmin(@Body Admin admin);

}
