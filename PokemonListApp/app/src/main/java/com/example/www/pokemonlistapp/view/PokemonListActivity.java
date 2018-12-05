package com.example.www.pokemonlistapp.view;

import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.www.pokemonlistapp.R;
import com.example.www.pokemonlistapp.adapter.PokemonAdapter;
import com.example.www.pokemonlistapp.listener.ItemClickListener;
import com.example.www.pokemonlistapp.model.PokemonAbility;
import com.example.www.pokemonlistapp.model.PokemonDetailsResponse;
import com.example.www.pokemonlistapp.model.PokemonImage;
import com.example.www.pokemonlistapp.model.PokemonList;
import com.example.www.pokemonlistapp.model.PokemonListResponse;
import com.example.www.pokemonlistapp.model.PokemonTypeName;
import com.example.www.pokemonlistapp.webservice.PokeAPIClient;
import com.example.www.pokemonlistapp.webservice.PokeAPIInterface;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonListActivity extends AppCompatActivity {

    private static final String TAG = PokemonListActivity.class.getSimpleName();

    private static final int LIMIT = 30;

    private PokemonAdapter pokemonAdapter;
    private static Bundle mBundleRecyclerViewState;
    RecyclerView recyclerView;
    private final String KEY_RECYCLER_STATE = "recycler_state";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_list);

        /*-----Title display on toolbar-------*/
        final Toolbar toolbar = findViewById(R.id.pokemon_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setIcon(R.mipmap.ic_launcher);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Pokemon");

        recyclerView = findViewById(R.id.pokemon_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        final PokeAPIInterface apiService =
                PokeAPIClient.getClient().create(PokeAPIInterface.class);

        /*------Pokemon list api request call-------*/
        Call<PokemonListResponse> call = apiService.getPokemonList(LIMIT);
        call.enqueue(new Callback<PokemonListResponse>() {
            @Override
            public void onResponse(@NonNull Call<PokemonListResponse> call, @NonNull Response<PokemonListResponse> response) {
                final List<PokemonList> pokeMonList = response.body() != null ? response.body().getResults() : null;
                pokemonAdapter = new PokemonAdapter(pokeMonList, R.layout.pokemon_list_item, getApplicationContext());
                recyclerView.setAdapter(pokemonAdapter);

                pokemonAdapter.setOnItemClickListener(new ItemClickListener() {
                    @Override
                    public void onItemClick(final View view, final int position) {

                        /*-------Pokemon details api request call ---------*/
                        Call<PokemonDetailsResponse> detailedResponseCall = apiService.getPokemonDetails(position+1);
                        detailedResponseCall.enqueue(new Callback<PokemonDetailsResponse>() {
                            @Override
                            public void onResponse(@NonNull Call<PokemonDetailsResponse> call, @NonNull Response<PokemonDetailsResponse> response) {

                                final int height = response.body() != null ? response.body().getHeight() : 0;
                                final int weight = response.body() != null ? response.body().getWeight() : 0;
                                final PokemonImage spritesList = response.body() != null ? response.body().getPokemonImage() : null;
                                final List<PokemonTypeName> pokemonTypeNames = response.body() != null ? response.body().getTypeNames() : null;
                                String PokemonType = Objects.requireNonNull(pokemonTypeNames).toString()
                                        .replace("[", "")  //remove the right bracket
                                        .replace("]", "")  //remove the left bracket
                                        .trim();

                                final List<PokemonAbility> pokemonAbilityNames = response.body() != null ? response.body().getAbilityName() : null;
                                String PokemonAbilityType = Objects.requireNonNull(pokemonAbilityNames).toString()
                                        .replace("[", "")  //remove the right bracket
                                        .replace("]", "")  //remove the left bracket
                                        .trim();
                                Intent intent = new Intent(PokemonListActivity.this, PokemonDetailsActivity.class);
                                intent.putExtra("POKEMON_HEIGHT", height);
                                intent.putExtra("POKEMON_WEIGHT", weight);
                                intent.putExtra("POKEMON_SPRITE", spritesList != null ? spritesList.toString() : null);
                                intent.putExtra("POKEMON_TYPES", PokemonType);
                                intent.putExtra("POKEMON_NAME", pokeMonList != null ? pokeMonList.get(position).getName() : null);
                                intent.putExtra("POKEMON_ABILITY",PokemonAbilityType);
                                startActivity(intent);

                                /*------Activity transition animation-------*/
                                overridePendingTransition(R.anim.anim_slide_in_left,
                                        R.anim.anim_slide_out_left);

                            }

                            @Override
                            public void onFailure(@NonNull Call<PokemonDetailsResponse> call, @NonNull Throwable t) {

                                Log.e(TAG, "Error: " + t.toString());

                            }
                        });
                    }
                });
            }

            @Override
            public void onFailure(@NonNull Call<PokemonListResponse> call, @NonNull Throwable t) {

                Log.e(TAG, "Error: " + t.toString());

            }
        });
    }


    /*------Save recyclerview scroll position-------*/
    @Override
    protected void onPause()
    {
        super.onPause();

        // save RecyclerView state
        mBundleRecyclerViewState = new Bundle();
        Parcelable listState = recyclerView.getLayoutManager().onSaveInstanceState();
        mBundleRecyclerViewState.putParcelable(KEY_RECYCLER_STATE, listState);
    }

    /*------Restore recyclerview scroll position-------*/
    @Override
    protected void onResume()
    {
        super.onResume();

        // restore RecyclerView state
        if (mBundleRecyclerViewState != null) {
            Parcelable listState = mBundleRecyclerViewState.getParcelable(KEY_RECYCLER_STATE);
            recyclerView.getLayoutManager().onRestoreInstanceState(listState);
        }
    }
}
