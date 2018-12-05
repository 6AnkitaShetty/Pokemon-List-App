package com.example.www.pokemonlistapp.webservice;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokeAPIClient {


    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if(retrofit == null) {
            String pokeAPIURL = "http://pokeapi.co/api/v2/";
            retrofit = new Retrofit.Builder()
                    .baseUrl(pokeAPIURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return  retrofit;
    }
}
