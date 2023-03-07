package net.jordan.quran_club.repository;

import net.jordan.quran_club.datasource.remote.TeacherApiService;
import net.jordan.quran_club.model.Status;
import net.jordan.quran_club.model.teacher.ResponseTeacher;
import net.jordan.quran_club.model.teacher.Teacher;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class TeacherRepository {

    private TeacherApiService teacherApiService;

    @Inject
    public TeacherRepository(TeacherApiService teacherApiService) {
        this.teacherApiService = teacherApiService;
    }
    public Single<ResponseTeacher> getTeachers(){
        return  teacherApiService.getTeachers();
    }
    public Single<Status> insertTeacher(Teacher teacher){
        return teacherApiService.insertTeacher(teacher);
    }
    public Single<Status> deleteTeacher(Teacher teacher){
        return teacherApiService.deleteTeacher(teacher.getTeacherId());
    }
    public Single<ResponseTeacher> getTeachersByTeacherId(int teacherId){
 return teacherApiService.getTeachersByTeacherId(teacherId);
}


    public Single<Status> updateTeacher(Teacher teacher) {
        return teacherApiService.updateTeacher(teacher);
    }
    public Single<ResponseTeacher> getTeachersByUserLoginId(int userloginId){return teacherApiService.getTeachersByUserLoginId(userloginId);}


}
