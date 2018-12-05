package com.example.www.pokemonlistapp.model;

import com.google.gson.annotations.SerializedName;

public class PokemonTypeName {

    @SerializedName("type")
    private PokemonSubType pokemonSubType;

    public PokemonSubType getPokemonSubType() {
        return pokemonSubType;
    }

    public void setPokemonSubType(PokemonSubType pokemonSubType) {
        this.pokemonSubType = pokemonSubType;
    }

    @Override
    public String toString() {
        return ""+pokemonSubType;
    }
}
