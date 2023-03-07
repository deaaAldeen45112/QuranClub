package net.jordan.quran_club.dp;

import android.util.Log;

import net.jordan.quran_club.datasource.remote.AdminApiService;
import net.jordan.quran_club.datasource.remote.ClassApiService;
import net.jordan.quran_club.datasource.remote.ImageLevelApiService;
import net.jordan.quran_club.datasource.remote.LessonApiService;
import net.jordan.quran_club.datasource.remote.PokemonApiService;
import net.jordan.quran_club.datasource.remote.QuranPartApiService;
import net.jordan.quran_club.datasource.remote.QuranPartSurahApiService;
import net.jordan.quran_club.datasource.remote.StudentApiService;
import net.jordan.quran_club.datasource.remote.StudentLessonApiService;
import net.jordan.quran_club.datasource.remote.StudentLessonQuranPartSurahApiService;
import net.jordan.quran_club.datasource.remote.StudentQuranPartApiService;
import net.jordan.quran_club.datasource.remote.SurahApiService;
import net.jordan.quran_club.datasource.remote.TeacherApiService;
import net.jordan.quran_club.datasource.remote.UserLoginApiService;
import net.jordan.quran_club.resourses.Constant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(ApplicationComponent.class)
public class RetrofitModel {


    @Provides
    @Singleton
    public static PokemonApiService providePokemonApiService(){

       return  new Retrofit.Builder()
        .baseUrl(Constant.url)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(PokemonApiService.class);
        }



    @Provides
    @Singleton
    public static TeacherApiService provideTeacherApiService(){

        return  new Retrofit.Builder()
                .baseUrl(Constant.url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(TeacherApiService.class);
    }
    @Provides
    @Singleton
    public static SurahApiService provideSurahApiService(){

        return  new Retrofit.Builder()
                .baseUrl(Constant.url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(SurahApiService.class);
    }
    @Provides
    @Singleton
    public static StudentQuranPartApiService provideStudentQuranPartApiService(){

        return  new Retrofit.Builder()
                .baseUrl(Constant.url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(StudentQuranPartApiService.class);
    }
    @Provides
    @Singleton
    public static StudentLessonApiService provideStudentLessonApiService(){

        return  new Retrofit.Builder()
                .baseUrl(Constant.url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(StudentLessonApiService.class);
    }
    @Provides
    @Singleton
    public static StudentApiService provideStudentApiService(){

        return  new Retrofit.Builder()
                .baseUrl(Constant.url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(StudentApiService.class);
    }
    @Provides
    @Singleton
    public static QuranPartApiService provideQuranPartApiService(){

        return  new Retrofit.Builder()
                .baseUrl(Constant.url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(QuranPartApiService.class);
    }
    @Provides
    @Singleton
    public static UserLoginApiService provideUserLoginApiService(){


        Gson gson = new GsonBuilder()
                .setLenient()
                .create();


        return  new Retrofit.Builder()
                .baseUrl(Constant.url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(UserLoginApiService.class);
    }
    @Provides
    @Singleton
    public static LessonApiService provideLessonApiService(){

        return  new Retrofit.Builder()
                .baseUrl(Constant.url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(LessonApiService.class);
    }
    @Provides
    @Singleton
    public static ImageLevelApiService provideImageLevelApiService(){

        return  new Retrofit.Builder()
                .baseUrl(Constant.url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(ImageLevelApiService.class);
    }

    @Provides
    @Singleton
    public static ClassApiService provideClassApiService(){

        return  new Retrofit.Builder()
                .baseUrl(Constant.url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(ClassApiService.class);
    }


    @Provides
    @Singleton
    public static AdminApiService provideAdminApiService(){

        return  new Retrofit.Builder()
                .baseUrl(Constant.url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(AdminApiService.class);
    }


    @Provides
    @Singleton
    public static QuranPartSurahApiService provideQuranPartSurahApiService(){

        return  new Retrofit.Builder()
                .baseUrl(Constant.url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(QuranPartSurahApiService.class);
    }


    @Provides
    @Singleton
    public static StudentLessonQuranPartSurahApiService provideStudentLessonQuranPartSurahApiService(){

        return  new Retrofit.Builder()
                .baseUrl(Constant.url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(StudentLessonQuranPartSurahApiService.class);
    }








}
