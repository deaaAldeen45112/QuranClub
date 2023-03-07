package net.jordan.quran_club.datasource.remote;

import static net.jordan.quran_club.resourses.Constant.STUDENT_QURAN_PART;

import net.jordan.quran_club.model.Status;
import net.jordan.quran_club.model.studentQuranPart.ResponseStudentJoinQuranPart;
import net.jordan.quran_club.model.studentQuranPart.StudentQuranPart;
import net.jordan.quran_club.model.studentQuranPart.ResponseStudentQuranPart;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface StudentQuranPartApiService {

    @GET(STUDENT_QURAN_PART+"/read.php")
    Single<ResponseStudentQuranPart> getStudentQuranParts();
    @GET(STUDENT_QURAN_PART+"/read_by_quran_part_id.php")
    Single<ResponseStudentQuranPart> getStudentQuranPartsByQuranPartId(@Query("quranPartId") int quranPartId);
    @GET(STUDENT_QURAN_PART+"/read_by_student_id.php")
    Single<ResponseStudentQuranPart> getStudentQuranPartsByStudentId(@Query("studentId")int studentId);
    @GET(STUDENT_QURAN_PART+"/read_by_student_quran_part_id.php")
    Single<ResponseStudentQuranPart> getStudentQuranParts(@Query("studentQuranPartId")int studentQuranPartId);
    @GET(STUDENT_QURAN_PART+"/read_join_with_QuranPart_by_student_id.php")
    Single<ResponseStudentJoinQuranPart> getStudentQuranPartsJoinQuranPart(@Query("studentId")int studentId);
    @POST(STUDENT_QURAN_PART+"/create.php")
    Single<Status> insertStudentQuranPart(@Body StudentQuranPart studentQuranPart);
    @PUT(STUDENT_QURAN_PART+"/edit.php")
    Single<Status> updateStudentQuranPart(@Body StudentQuranPart studentQuranPart);
    @DELETE(STUDENT_QURAN_PART+"/delete.php")
    Single<Status> deleteStudentQuranPart(@Query("studentQuranPartId") int studentQuranPartId);

}
