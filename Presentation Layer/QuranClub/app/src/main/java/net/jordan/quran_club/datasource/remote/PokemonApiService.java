package net.jordan.quran_club.datasource.remote;

import net.jordan.quran_club.model.PokemonResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface PokemonApiService {
    @GET("pokemon")
    Observable<PokemonResponse> getPokemons();
}
