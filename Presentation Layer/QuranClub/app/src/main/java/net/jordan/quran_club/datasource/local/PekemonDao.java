package net.jordan.quran_club.datasource.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import net.jordan.quran_club.model.Pokemon;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

@Dao
public interface PekemonDao {

    @Insert
    public void insert(Pokemon pokemon);
    @Query("delete from fav_table where fav_table.name=:pokemonName")
    public void delete(String pokemonName);
    @Query("select * from fav_table")
    public Single<List<Pokemon>>getPokemons();



}
