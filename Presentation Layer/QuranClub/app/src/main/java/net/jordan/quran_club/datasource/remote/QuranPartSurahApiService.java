package net.jordan.quran_club.datasource.remote;

import static net.jordan.quran_club.resourses.Constant.QURAN_PART_SURAH;

import net.jordan.quran_club.model.Status;
import net.jordan.quran_club.model.quranPartSurah.QuranPartSurah;
import net.jordan.quran_club.model.quranPartSurah.ResponseQuranPartSurah;
import net.jordan.quran_club.model.quranPartSurah.ResponseQuranPartSurahJoinQuranPartJoinSurah;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface QuranPartSurahApiService {
    @GET(QURAN_PART_SURAH+"/read.php")
    Single<ResponseQuranPartSurah> getQuranPartSurah();
    @GET(QURAN_PART_SURAH+"/read_quran_part_surah_join_quran_part_join_surah.php")
    Single<ResponseQuranPartSurahJoinQuranPartJoinSurah> getQuranPartSurahJoinQuranPartJoinSurah();
    @POST(QURAN_PART_SURAH+"/create.php")
    Single<Status> insertQuranPartSurah(@Body QuranPartSurah quranPartSurah);
    @PUT(QURAN_PART_SURAH+"/update.php")
    Single<Status> updateQuranPartSurah(@Body QuranPartSurah quranPartSurah);
    @DELETE(QURAN_PART_SURAH+"/delete.php")
    Single<Status> deleteQuranPartSurah(@Query("quranPartSurahId") int quranPartSurahId);

}
