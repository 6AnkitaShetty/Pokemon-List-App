package com.example.www.pokemonlistapp.webservice;

import com.example.www.pokemonlistapp.model.PokemonDetailsResponse;
import com.example.www.pokemonlistapp.model.PokemonListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;  //Path is use to replace item defined in your api request url
import retrofit2.http.Query; // appends ?limit=100 to api request url

public interface PokeAPIInterface {

    @GET("pokemon/")
    Call<PokemonListResponse> getPokemonList(@Query("limit") int limit);

    @GET("pokemon/{id}/")
    Call<PokemonDetailsResponse> getPokemonDetails(@Path("id") int pokemonID);

}
