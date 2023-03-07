package net.jordan.quran_club.repository;

import net.jordan.quran_club.datasource.remote.SurahApiService;
import net.jordan.quran_club.model.Status;
import net.jordan.quran_club.model.surah.ResponseSurah;
import net.jordan.quran_club.model.surah.Surah;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class SurahRepository {
    private SurahApiService surahApiService;

    @Inject
    public SurahRepository(SurahApiService surahApiService) {
        this.surahApiService = surahApiService;
    }
    public Single<ResponseSurah> getSurahs(){return  surahApiService.getSurahs();}
    public Single<ResponseSurah> getSurahsBySurahId(int surahId){return surahApiService.getSurahsBySurahId(surahId);}
    public Single<ResponseSurah> getSurahsByQuranPartId(int quranPartId){return surahApiService.getSurahsByQuranPartId(quranPartId);}
    public Single<Status> insertSurah(Surah surah){return surahApiService.insertSurah(surah);}
    public Single<Status> updateSurah(Surah surah){return surahApiService.updateSurah(surah);}
    public Single<Status> deleteSurah(int id){return surahApiService.deleteSurah(id);}
}
