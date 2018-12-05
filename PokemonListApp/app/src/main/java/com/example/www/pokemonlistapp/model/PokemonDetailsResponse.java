package com.example.www.pokemonlistapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PokemonDetailsResponse {

    @SerializedName("name")
    private String name;

    @SerializedName("weight")
    private int weight;

    @SerializedName("height")
    private int height;

    @SerializedName("id")
    private int pokemonID;

    @SerializedName("sprites")
    private PokemonImage pokemonImage;

    @SerializedName("types")
    private
    List<PokemonTypeName> typeName;

    @SerializedName("abilities")
    private
    List<PokemonAbility> abilityName;

    public int getPokemonID() {
        return pokemonID;
    }

    public void setPokemonID(int pokemonID) {
        this.pokemonID = pokemonID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public PokemonImage getPokemonImage() {
        return pokemonImage;
    }

    public void setPokemonImage(PokemonImage pokemonImage) {
        this.pokemonImage = pokemonImage;
    }

    public List<PokemonTypeName> getTypeNames() {
        return typeName;
    }

    public void setTypeNames(List<PokemonTypeName> typeName) {
        this.typeName = typeName;
    }

    public List<PokemonAbility> getAbilityName() {
        return abilityName;
    }

    public void setAbilityName(List<PokemonAbility> abilityName) {
        this.abilityName = abilityName;
    }
}
