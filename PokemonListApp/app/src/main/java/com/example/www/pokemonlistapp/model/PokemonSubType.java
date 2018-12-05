package com.example.www.pokemonlistapp.model;

import com.google.gson.annotations.SerializedName;

public class PokemonSubType {

    @SerializedName("name")
    private String name;

    @Override
    public String toString() {
        return ""+ name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
