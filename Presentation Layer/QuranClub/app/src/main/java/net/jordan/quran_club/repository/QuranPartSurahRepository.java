package net.jordan.quran_club.repository;

import net.jordan.quran_club.datasource.remote.QuranPartSurahApiService;
import net.jordan.quran_club.model.Status;
import net.jordan.quran_club.model.quranPartSurah.QuranPartSurah;
import net.jordan.quran_club.model.quranPartSurah.ResponseQuranPartSurah;
import net.jordan.quran_club.model.quranPartSurah.ResponseQuranPartSurahJoinQuranPartJoinSurah;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class QuranPartSurahRepository {
    private QuranPartSurahApiService quranPartSurahApiService;
    @Inject
    public QuranPartSurahRepository(QuranPartSurahApiService quranPartSurahApiService) {
        this.quranPartSurahApiService =quranPartSurahApiService ;
    }
    public Single<ResponseQuranPartSurah> getQuranPartSurah(){return  quranPartSurahApiService.getQuranPartSurah();}
    public Single<ResponseQuranPartSurahJoinQuranPartJoinSurah> getQuranPartSurahJoinQuranPartJoinSurah(){return  quranPartSurahApiService.getQuranPartSurahJoinQuranPartJoinSurah();}
    public Single<Status> insertQuranPartSurah(QuranPartSurah quranPartSurah){return quranPartSurahApiService.insertQuranPartSurah(quranPartSurah);}
    public Single<Status> updateQuranPartSurah( QuranPartSurah quranPartSurah){return  quranPartSurahApiService.updateQuranPartSurah(quranPartSurah);}
    public Single<Status> deleteQuranPartSurah( int quranPartSurahId){return  quranPartSurahApiService.deleteQuranPartSurah(quranPartSurahId);}
}
