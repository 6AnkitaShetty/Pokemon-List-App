package com.example.www.pokemonlistapp.model;

import com.google.gson.annotations.SerializedName;

public class PokemonImage {

    @SerializedName("front_default")
    private String frontDefault;

    public String getFrontDefault() {
        return frontDefault;
    }

    public void setFrontDefault(String frontDefault) {
        this.frontDefault = frontDefault;
    }

    @Override
    public String toString() {
        return ""+ frontDefault;
    }
}
