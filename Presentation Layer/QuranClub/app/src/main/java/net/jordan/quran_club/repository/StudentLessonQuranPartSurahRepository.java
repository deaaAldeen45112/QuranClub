package net.jordan.quran_club.repository;

import android.util.Log;

import net.jordan.quran_club.datasource.remote.StudentLessonQuranPartSurahApiService;
import net.jordan.quran_club.model.Status;
import net.jordan.quran_club.model.studentLessonQuranPartSurah.ResponseStudentLessonQuranPartSurahJoinStudentLessonJoinUserlogin;
import net.jordan.quran_club.model.studentLessonQuranPartSurah.StudentLessonQuranPartSurah;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class StudentLessonQuranPartSurahRepository {

    private StudentLessonQuranPartSurahApiService studentLessonQuranPartSurahApiService;
    @Inject
    public StudentLessonQuranPartSurahRepository(StudentLessonQuranPartSurahApiService studentLessonQuranPartSurahApiService) {
        Log.d("TAG", "PokemonRepository: ");
        this.studentLessonQuranPartSurahApiService = studentLessonQuranPartSurahApiService;

    }
    public Single<ResponseStudentLessonQuranPartSurahJoinStudentLessonJoinUserlogin> getStudentLessonQuranPartSurahJoinStudentLessonJoinUserlogin(int lessonId){
        return  studentLessonQuranPartSurahApiService.getStudentLessonQuranPartSurahJoinStudentLessonJoinUserlogin(lessonId);
    }
    public Single<ResponseStudentLessonQuranPartSurahJoinStudentLessonJoinUserlogin> getStudentLessonQuranPartSurahJoinStudentLessonJoinUserloginByStudentId(int studentId){
        return  studentLessonQuranPartSurahApiService.getStudentLessonQuranPartSurahJoinStudentLessonJoinUserloginByStudentId(studentId);
    }


    public Single<Status> updateStudentLessonQuranPartSurah(StudentLessonQuranPartSurah studentLessonQuranPartSurah){
        return  studentLessonQuranPartSurahApiService.updateStudentLessonQuranPartSurah(studentLessonQuranPartSurah);
    }

    public Single<Status> insertStudentLessonQuranPartSurah(StudentLessonQuranPartSurah studentLessonQuranPartSurah){
        return  studentLessonQuranPartSurahApiService.insertStudentLessonQuranPartSurah(studentLessonQuranPartSurah);
    }
}
