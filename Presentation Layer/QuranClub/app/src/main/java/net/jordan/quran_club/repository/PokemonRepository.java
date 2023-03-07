package net.jordan.quran_club.repository;

import android.util.Log;

import net.jordan.quran_club.datasource.local.PekemonDao;
import net.jordan.quran_club.datasource.remote.PokemonApiService;
import net.jordan.quran_club.model.Pokemon;
import net.jordan.quran_club.model.PokemonResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class PokemonRepository {

    private PokemonApiService pokemonApiService;
    private PekemonDao pekemonDao;
    @Inject
    public PokemonRepository(PokemonApiService pokemonApiService,PekemonDao pekemonDao) {
        Log.d("TAG", "PokemonRepository: ");
        this.pokemonApiService = pokemonApiService;
        this.pekemonDao=pekemonDao;
    }
    public Observable<PokemonResponse> getPokemons(){
        return  pokemonApiService.getPokemons();
    }
    public void insert(Pokemon pokemon){
        pekemonDao.insert(pokemon);
    }

    public void delete(String name){
        pekemonDao.delete(name);
    }

    public Single<List<Pokemon>> getFavPekemon() {
        return pekemonDao.getPokemons();
    }
}
