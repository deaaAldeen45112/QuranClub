package net.jordan.quran_club.datasource.remote;

import static net.jordan.quran_club.resourses.Constant.IMAGE_LEVEL;

import net.jordan.quran_club.model.ImageLevel;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ImageLevelApiService {
    @GET(IMAGE_LEVEL+"/read.php")
    Observable<List<ImageLevel>> getImageLevels();
    @POST(IMAGE_LEVEL+"/create.php")
    Completable insertImageLevel(ImageLevel imageLevel);
    @PUT(IMAGE_LEVEL+"/update.php")
    Completable updateImageLevel(ImageLevel imageLevel);
    @DELETE(IMAGE_LEVEL+"/delete.php")
    Completable deleteImageLevel(int id);
}
