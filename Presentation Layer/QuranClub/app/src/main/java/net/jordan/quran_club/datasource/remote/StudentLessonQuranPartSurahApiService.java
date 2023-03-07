package net.jordan.quran_club.datasource.remote;
import static net.jordan.quran_club.resourses.Constant.STUDENT_LESSON_QURAN_PART_SURAH;

import net.jordan.quran_club.model.Status;
import net.jordan.quran_club.model.studentLessonQuranPartSurah.ResponseStudentLessonQuranPartSurah;
import net.jordan.quran_club.model.studentLessonQuranPartSurah.ResponseStudentLessonQuranPartSurahJoinStudentLessonJoinUserlogin;
import net.jordan.quran_club.model.studentLessonQuranPartSurah.StudentLessonQuranPartSurah;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface StudentLessonQuranPartSurahApiService {
    @GET(STUDENT_LESSON_QURAN_PART_SURAH+"/read_studentLessonQuranPartSurah_join_studentLesson_join_userlogin.php")
    Single<ResponseStudentLessonQuranPartSurahJoinStudentLessonJoinUserlogin>
    getStudentLessonQuranPartSurahJoinStudentLessonJoinUserlogin(@Query("lessonId") int lessonId);

    @GET(STUDENT_LESSON_QURAN_PART_SURAH+"/read_studentLessonQuranPartSurah_join_studentLesson_join_userlogin_by_student_id.php")
    Single<ResponseStudentLessonQuranPartSurahJoinStudentLessonJoinUserlogin>
    getStudentLessonQuranPartSurahJoinStudentLessonJoinUserloginByStudentId(@Query("studentId") int studentId);

    @GET(STUDENT_LESSON_QURAN_PART_SURAH+"/read.php")
    Single<ResponseStudentLessonQuranPartSurah> getStudentLessonQuranPartSurah();

    @POST(STUDENT_LESSON_QURAN_PART_SURAH+"/create.php")
    Single<Status> insertStudentLessonQuranPartSurah(@Body StudentLessonQuranPartSurah studentLessonQuranPartSurah);

    @PUT(STUDENT_LESSON_QURAN_PART_SURAH+"/update.php")
    Single<Status> updateStudentLessonQuranPartSurah(@Body StudentLessonQuranPartSurah studentLessonQuranPartSurah);

    @DELETE(STUDENT_LESSON_QURAN_PART_SURAH+"/delete.php")
    Single<Status> deleteStudentLessonQuranPartSurah(@Query("studentLessonQuranPartSurahId") int studentLessonQuranPartSurahId);

}
