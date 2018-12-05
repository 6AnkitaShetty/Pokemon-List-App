package com.example.www.pokemonlistapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PokemonListResponse {

    @SerializedName("results")
    private List<PokemonList> results;

    public List<PokemonList> getResults() {
        return results;
    }

    public void setResults(List<PokemonList> results) {
        this.results = results;
    }


}
