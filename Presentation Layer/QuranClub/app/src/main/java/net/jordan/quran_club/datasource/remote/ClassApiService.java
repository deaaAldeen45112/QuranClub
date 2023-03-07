package net.jordan.quran_club.datasource.remote;

import static net.jordan.quran_club.resourses.Constant.CLASS;

import net.jordan.quran_club.model.classes.Class;
import net.jordan.quran_club.model.classes.ResponseClases;
import net.jordan.quran_club.model.Status;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ClassApiService {
    @GET(CLASS+"/read.php")
    Single<ResponseClases> getClasses();
    @GET(CLASS+"/read_by_techer_id.php")
    Single<ResponseClases> getClassesByTeacherId(@Query("teacherId") int teacherId);
    @POST(CLASS+"/create.php")
    Single<Status> insertClass(@Body Class classes);
    @PUT(CLASS+"/update.php")
    Single<Status> updateClass(@Body Class classes);
    @DELETE(CLASS+"/delete.php")
    Single<Status> deleteClass(@Query("classId") int classId);
}
