package com.example.www.pokemonlistapp.model;

import com.google.gson.annotations.SerializedName;

public class PokemonAbility {

    @SerializedName("ability")
    private PokemonAbilityName pokemonAbilityName;

    public PokemonAbilityName getPokemonAbilityName() {
        return pokemonAbilityName;
    }

    public void setPokemonAbilityName(PokemonAbilityName pokemonAbilityName) {
        this.pokemonAbilityName = pokemonAbilityName;
    }

    @Override
    public String toString() {
        return ""+pokemonAbilityName;
    }
}
