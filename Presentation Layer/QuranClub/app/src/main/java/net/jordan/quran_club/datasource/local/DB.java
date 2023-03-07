package net.jordan.quran_club.datasource.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import net.jordan.quran_club.model.Pokemon;
import net.jordan.quran_club.model.userlogin.UserLogin;

@Database(entities = {Pokemon.class, UserLogin.class} ,version = 10,exportSchema = false)
public abstract class DB extends RoomDatabase {

    public  abstract PekemonDao pekemonDao();
    public  abstract UserLoginDao userLoginDao();




}
