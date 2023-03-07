package net.jordan.quran_club.repository;

import net.jordan.quran_club.datasource.remote.LessonApiService;
import net.jordan.quran_club.model.InsertionStatus;
import net.jordan.quran_club.model.Status;
import net.jordan.quran_club.model.lesson.Lesson;
import net.jordan.quran_club.model.lesson.ResponseLesson;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class LessonRepository {


    private LessonApiService lessonApiService;

    @Inject
    public LessonRepository(LessonApiService lessonApiService) {
        this.lessonApiService = lessonApiService;
    }


    public Single<ResponseLesson> getLessons(){return lessonApiService.getLessons(); }
    public Single<ResponseLesson> getLessonsByClassId(int classId){return  lessonApiService.getLessonsByClassId(classId);}
    public Single<InsertionStatus> insertLesson(Lesson lesson){return lessonApiService.insertLesson(lesson);}
    public Single<Status> updateLesson( Lesson lesson){return lessonApiService.updateLesson(lesson);}
    public Single<Status> deleteLesson(int lessonId){return  lessonApiService.deleteLesson(lessonId);}
}
