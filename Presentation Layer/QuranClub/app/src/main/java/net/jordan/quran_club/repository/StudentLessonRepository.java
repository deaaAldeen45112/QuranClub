package net.jordan.quran_club.repository;

import android.util.Log;

import net.jordan.quran_club.datasource.remote.StudentLessonApiService;
import net.jordan.quran_club.model.InsertionStatus;
import net.jordan.quran_club.model.Status;
import net.jordan.quran_club.model.studentLesson.ResponseStudentLesson;
import net.jordan.quran_club.model.studentLesson.ResponseStudentLessonWithFullName;
import net.jordan.quran_club.model.studentLesson.ResponseStudentLessonWithLessonName;
import net.jordan.quran_club.model.studentLesson.StudentLesson;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class StudentLessonRepository {

    private StudentLessonApiService studentLessonApiService;
    @Inject
    public StudentLessonRepository(StudentLessonApiService studentLessonApiService) {
        Log.d("TAG", "PokemonRepository: ");
        this.studentLessonApiService = studentLessonApiService;

    }
    public Single<ResponseStudentLessonWithLessonName> getStudentLessonsById(int id){
//        HashMap<String ,Integer>g=new HashMap<>();
//        g.put("id",4);
        StudentLesson studentLesson=new StudentLesson();
        studentLesson.setStudentId(4);
        return  studentLessonApiService.getStudentLessonsByStudentId(id);
    }


   public Single<ResponseStudentLesson> getStudentLessons(){return  studentLessonApiService.getStudentLessons();}
   public Single<InsertionStatus> insertStudentLesson(StudentLesson studentLesson){return  studentLessonApiService.insertStudentLesson(studentLesson);}
   public Single<Status> updateStudentLesson(StudentLesson studentLesson){return  studentLessonApiService.updateStudentLesson(studentLesson);}
   public Single<Status> deleteStudentLesson(int studentLessonId){return  studentLessonApiService.deleteStudentLesson(studentLessonId);}
   public Single<ResponseStudentLessonWithFullName> getStudentLessonsByLessonId(int lessonId){return studentLessonApiService.getStudentLessonsByLessonId(lessonId);}
   public Single<ResponseStudentLessonWithLessonName> getStudentLessonsByStudentId(int studentId){return  studentLessonApiService.getStudentLessonsByStudentId(studentId);}


    public Single<Status> updateStudentLessonNoteByStudentLessonId(StudentLesson studentLesson){return  studentLessonApiService.updateStudentLessonNoteByStudentLessonId(studentLesson);}




}
