package net.jordan.quran_club.datasource.remote;

import static net.jordan.quran_club.resourses.Constant.STUDENT_LESSON;

import net.jordan.quran_club.model.InsertionStatus;
import net.jordan.quran_club.model.studentLesson.ResponseStudentLesson;
import net.jordan.quran_club.model.Status;
import net.jordan.quran_club.model.studentLesson.ResponseStudentLessonWithFullName;
import net.jordan.quran_club.model.studentLesson.ResponseStudentLessonWithLessonName;
import net.jordan.quran_club.model.studentLesson.StudentLesson;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface StudentLessonApiService {
    @GET(STUDENT_LESSON+"/read.php")
    Single<ResponseStudentLesson> getStudentLessons();
    @POST(STUDENT_LESSON+"/create.php")
    Single<InsertionStatus> insertStudentLesson(@Body StudentLesson studentLesson);
    @PUT(STUDENT_LESSON+"/update.php")
    Single<Status> updateStudentLesson(@Body StudentLesson studentLesson);
    @DELETE(STUDENT_LESSON+"/delete.php")
    Single<Status> deleteStudentLesson(@Query("studentLessonId") int studentLessonId);
    @GET(STUDENT_LESSON+"/read_by_lesson_id.php")
    Single<ResponseStudentLessonWithFullName> getStudentLessonsByLessonId(@Query("lessonId") int lessonId);
    @GET(STUDENT_LESSON+"/read_by_student_id.php")
    Single<ResponseStudentLessonWithLessonName> getStudentLessonsByStudentId(@Query("studentId") int studentId);

    @PUT(STUDENT_LESSON+"/update_student_lesson_note_by_student_lesson_id.php")
    Single<Status> updateStudentLessonNoteByStudentLessonId(@Body StudentLesson studentLesson);


}
