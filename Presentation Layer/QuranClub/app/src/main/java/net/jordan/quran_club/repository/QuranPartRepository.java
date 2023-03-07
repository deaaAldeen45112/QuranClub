package net.jordan.quran_club.repository;

import net.jordan.quran_club.datasource.remote.QuranPartApiService;
import net.jordan.quran_club.model.Status;
import net.jordan.quran_club.model.quranPart.QuranPart;
import net.jordan.quran_club.model.quranPart.ResponseQuranPart;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class QuranPartRepository{

    private QuranPartApiService quranPartApiService;

    @Inject
    public QuranPartRepository(QuranPartApiService quranPartApiService) {
        this.quranPartApiService =quranPartApiService ;
    }

   public Single<ResponseQuranPart> getQuranParts(){return  quranPartApiService.getQuranParts();}
   public Single<Status> insertQuranPart( QuranPart quranPart){return quranPartApiService.insertQuranPart(quranPart);}
   public Single<Status> updateQuranPart( QuranPart quranPart){return  quranPartApiService.updateQuranPart(quranPart);}
   public Single<Status> deleteQuranPart( int quranPartId){return  quranPartApiService.deleteQuranPart(quranPartId);}


}
