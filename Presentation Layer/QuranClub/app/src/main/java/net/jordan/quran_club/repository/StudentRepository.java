package net.jordan.quran_club.repository;

import android.util.Log;

import net.jordan.quran_club.datasource.remote.StudentApiService;
import net.jordan.quran_club.model.Status;
import net.jordan.quran_club.model.student.ResponseSavedPercentage;
import net.jordan.quran_club.model.student.ResponseStudent;
import net.jordan.quran_club.model.student.ResponseStudentName;
import net.jordan.quran_club.model.student.Student;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class StudentRepository {
    private StudentApiService studentApiService;
    @Inject
    StudentRepository(StudentApiService studentApiService){
        this.studentApiService=studentApiService;
    }

    public Single<ResponseStudent>getStudentByUserloginId(int userloginId){

        return studentApiService.getStudentByUserLoginId(userloginId);
    }

  public Single<ResponseStudent> getStudents(){return studentApiService.getStudents();}
  public Single<Status> insertStudent(Student student){return studentApiService.insertStudent(student);}
  public Single<Status> updateStudent(Student student){return studentApiService.updateStudent(student);}
  public Single<Status> deleteStudent(int studentId){return studentApiService.deleteStudent(studentId);}
  public Single<ResponseStudent>getStudentByUserLoginId(int userloginId){return studentApiService.getStudentByUserLoginId(userloginId);}
  public Single<ResponseStudent>getStudentByStudentId(int studentId){return  studentApiService.getStudentByStudentId(studentId);}
  public Single<ResponseStudentName>getStudentByClassId(int classId){return studentApiService.getStudentByClassId(classId);}
  public Single<ResponseStudent>getStudentByEmail(String email){return studentApiService.getStudentByEmail(email);}
  public Single<ResponseSavedPercentage>getSavedPercentageForEachQuranPartByStudentId(int studentId){
        return studentApiService.getSavedPercentageForEachQuranPartByStudentId(studentId);
  }


}
