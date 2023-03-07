package net.jordan.quran_club.repository;

import net.jordan.quran_club.datasource.remote.ClassApiService;
import net.jordan.quran_club.model.Status;
import net.jordan.quran_club.model.classes.Class;
import net.jordan.quran_club.model.classes.ResponseClases;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;


public class ClassRepository {



    private ClassApiService classApiService;

    @Inject
    public ClassRepository(ClassApiService classApiService) {
        this.classApiService = classApiService;
    }



    public Single<ResponseClases> getClasses(){return classApiService.getClasses();}
    public Single<ResponseClases> getClassesByTeacherId( int teacherId){return  classApiService.getClassesByTeacherId(teacherId);}
    public Single<Status> insertClass( Class classes){return  classApiService.insertClass(classes);}
    public Single<Status> updateClass( Class classes){return  classApiService.updateClass(classes);}
    public Single<Status> deleteClass( int classId){return  classApiService.deleteClass(classId);}



}
