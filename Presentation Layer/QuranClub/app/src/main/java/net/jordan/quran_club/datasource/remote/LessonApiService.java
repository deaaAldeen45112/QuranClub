package net.jordan.quran_club.datasource.remote;

import static net.jordan.quran_club.resourses.Constant.LESSON;

import net.jordan.quran_club.model.InsertionStatus;
import net.jordan.quran_club.model.lesson.Lesson;
import net.jordan.quran_club.model.lesson.ResponseLesson;
import net.jordan.quran_club.model.Status;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface LessonApiService {
    @GET(LESSON+"/read.php")
    Single<ResponseLesson> getLessons();
    @GET(LESSON+"/read_lesson_by_class_id.php")
    Single<ResponseLesson> getLessonsByClassId(@Query("classId") int classId);
    @POST(LESSON+"/create.php")
    Single<InsertionStatus> insertLesson(@Body Lesson lesson);
    @PUT(LESSON+"/update.php")
    Single<Status> updateLesson(@Body Lesson lesson);
    @DELETE(LESSON+"/delete.php")
    Single<Status> deleteLesson(@Query("lessonId") int lessonId);
}
