package com.example.www.pokemonlistapp.model;

import com.google.gson.annotations.SerializedName;

public class PokemonAbilityName {

    @SerializedName("name")
    private String abilityname;

    @Override
    public String toString() {
        return ""+ abilityname;
    }

    public String getAbilityName() {
        return abilityname;
    }

    public void setAbilityName(String abilityname) {
        this.abilityname = abilityname;
    }
}
