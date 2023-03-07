package net.jordan.quran_club.datasource.remote;

import static net.jordan.quran_club.resourses.Constant.STUDENT;

import net.jordan.quran_club.model.student.ResponseSavedPercentage;
import net.jordan.quran_club.model.student.ResponseStudent;
import net.jordan.quran_club.model.student.ResponseStudentName;
import net.jordan.quran_club.model.Status;
import net.jordan.quran_club.model.student.Student;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface StudentApiService {
    @GET(STUDENT+"/read.php")
    Single<ResponseStudent> getStudents();
    @POST(STUDENT+"/create.php")
    Single<Status> insertStudent(@Body Student student);
    @PUT(STUDENT+"/update.php")
    Single<Status> updateStudent(@Body Student student);
    @DELETE(STUDENT+"/delete.php")
    Single<Status> deleteStudent(@Query("studentId") int studentId);
    @GET(STUDENT+"/read_by_userlogin_id.php")
    Single<ResponseStudent>getStudentByUserLoginId(@Query("userloginId") int userloginId);
    @GET(STUDENT+"/read_by_student_id.php")
    Single<ResponseStudent>getStudentByStudentId(@Query("studentId") int studentId);
    @GET(STUDENT+"/read_by_class_id.php")
    Single<ResponseStudentName>getStudentByClassId(@Query("classId") int classId);
    @GET(STUDENT+"/read_by_email.php")
    Single<ResponseStudent>getStudentByEmail(@Query("email") String email);
    @GET(STUDENT+"/read_saved_percentage_for_each_part.php")
    Single<ResponseSavedPercentage>
    getSavedPercentageForEachQuranPartByStudentId(@Query("studentId") int studentId);




}
