package net.jordan.quran_club.model;

import java.util.ArrayList;

public class PokemonResponse {

    private  int count;
    private  String next,previous;
    private ArrayList<Pokemon>results;

    public PokemonResponse(int count, String next, String previous, ArrayList<Pokemon> pokemons) {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = pokemons;
    }

    public int getCount() {
        return  count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public ArrayList<Pokemon> getPokemons() {
        return results;
    }

    public void setPokemons(ArrayList<Pokemon> pokemons) {
        this.results = pokemons;
    }
}
