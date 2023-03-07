package net.jordan.quran_club.repository;

import net.jordan.quran_club.datasource.remote.StudentQuranPartApiService;
import net.jordan.quran_club.model.Status;
import net.jordan.quran_club.model.studentQuranPart.ResponseStudentJoinQuranPart;
import net.jordan.quran_club.model.studentQuranPart.ResponseStudentQuranPart;
import net.jordan.quran_club.model.studentQuranPart.StudentQuranPart;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class StudentQuranPartRepository {
    private StudentQuranPartApiService studentQuranPartApiService;

    @Inject
    public StudentQuranPartRepository(StudentQuranPartApiService studentQuranPartApiService) {
        this.studentQuranPartApiService = studentQuranPartApiService;
    }

   public Single<ResponseStudentQuranPart> getStudentQuranParts(){return  studentQuranPartApiService.getStudentQuranParts();}
   public Single<ResponseStudentQuranPart> getStudentQuranPartsByQuranPartId(int quranPartId){return studentQuranPartApiService.getStudentQuranPartsByQuranPartId(quranPartId);}
   public Single<ResponseStudentQuranPart> getStudentQuranPartsByStudentId(int studentId){return  studentQuranPartApiService.getStudentQuranPartsByStudentId(studentId);}
   public Single<ResponseStudentQuranPart> getStudentQuranParts(int studentQuranPartId){return  studentQuranPartApiService.getStudentQuranParts();}
   public Single<ResponseStudentJoinQuranPart> getStudentQuranPartsJoinQuranPart(int studentId){return studentQuranPartApiService.getStudentQuranPartsJoinQuranPart(studentId);}
   public Single<Status> insertStudentQuranPart(StudentQuranPart studentQuranPart){ return studentQuranPartApiService.insertStudentQuranPart(studentQuranPart);}
   public Single<Status> updateStudentQuranPart(StudentQuranPart studentQuranPart){ return studentQuranPartApiService.updateStudentQuranPart(studentQuranPart);}
   public Single<Status> deleteStudentQuranPart(int studentQuranPartId){return studentQuranPartApiService.deleteStudentQuranPart(studentQuranPartId);}

}
