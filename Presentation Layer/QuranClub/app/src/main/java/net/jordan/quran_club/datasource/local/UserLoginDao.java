package net.jordan.quran_club.datasource.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import net.jordan.quran_club.model.userlogin.UserLogin;

import java.util.List;

@Dao
public interface UserLoginDao {

    @Insert
    public void insert(UserLogin userLogin);
    //@Query("delete from fav_table where fav_table.name=:pokemonName")
    //public void delete(String pokemonName);
    @Query("select * from userlogin")
    public List<UserLogin> getUserLogins();

    @Query("delete from userlogin")
    public void delete();
}
