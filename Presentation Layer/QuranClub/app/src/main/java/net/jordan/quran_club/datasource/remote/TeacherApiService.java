package net.jordan.quran_club.datasource.remote;

import static net.jordan.quran_club.resourses.Constant.TEACHER;

import net.jordan.quran_club.model.Status;
import net.jordan.quran_club.model.teacher.ResponseTeacher;
import net.jordan.quran_club.model.teacher.Teacher;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface TeacherApiService {
    @GET(TEACHER+"/read.php")
    Single<ResponseTeacher> getTeachers();
    @GET(TEACHER+"/read_by_userlogin_id.php")
    Single<ResponseTeacher> getTeachersByUserLoginId(@Query("userloginId") int userloginId);
    @GET(TEACHER+"/read_by_teacher_id.php")
    Single<ResponseTeacher> getTeachersByTeacherId(@Query("teacherId") int teacherId);
    @POST(TEACHER+"/create.php")
    Single<Status> insertTeacher(@Body Teacher teacher);
    @PUT(TEACHER+"/update.php")
    Single<Status> updateTeacher(@Body Teacher teacher);
    @DELETE(TEACHER+"/delete.php")
    Single<Status> deleteTeacher(@Query("teacherId") int teacherId);


}
