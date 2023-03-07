package net.jordan.quran_club.datasource.remote;

import static net.jordan.quran_club.resourses.Constant.SURAH;

import net.jordan.quran_club.model.Status;
import net.jordan.quran_club.model.surah.ResponseSurah;
import net.jordan.quran_club.model.surah.Surah;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface SurahApiService {
    @GET(SURAH+"/read.php")
    Single<ResponseSurah> getSurahs();
    @GET(SURAH+"/read_by_surah_id.php")
    Single<ResponseSurah> getSurahsBySurahId(@Query("surahId") int surahId);
    @GET(SURAH+"/read_quran_part_id.php")
    Single<ResponseSurah> getSurahsByQuranPartId(@Query("quranPartId") int quranPartId);
    @POST(SURAH+"/create.php")
    Single<Status> insertSurah(@Body Surah surah);
    @PUT(SURAH+"/update.php")
    Single<Status> updateSurah(@Body Surah surah);
    @DELETE(SURAH+"/delete.php")
    Single<Status> deleteSurah(@Query("surahId") int id);
}

