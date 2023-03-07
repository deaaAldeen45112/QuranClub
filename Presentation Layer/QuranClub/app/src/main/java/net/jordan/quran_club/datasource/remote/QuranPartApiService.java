package net.jordan.quran_club.datasource.remote;

import static net.jordan.quran_club.resourses.Constant.QURAN_PART;

import net.jordan.quran_club.model.quranPart.ResponseQuranPart;
import net.jordan.quran_club.model.Status;
import net.jordan.quran_club.model.quranPart.QuranPart;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface QuranPartApiService {
    @GET(QURAN_PART+"/read.php")
    Single<ResponseQuranPart> getQuranParts();
    @POST(QURAN_PART+"/create.php")
    Single<Status> insertQuranPart(@Body QuranPart quranPart);
    @PUT(QURAN_PART+"/update.php")
    Single<Status> updateQuranPart(@Body QuranPart quranPart);
    @DELETE(QURAN_PART+"/delete.php")
    Single<Status> deleteQuranPart(@Query("quranPartId") int quranPartId);
}
