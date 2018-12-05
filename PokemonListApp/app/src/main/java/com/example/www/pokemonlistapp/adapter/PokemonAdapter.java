package com.example.www.pokemonlistapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.www.pokemonlistapp.R;
import com.example.www.pokemonlistapp.listener.ItemClickListener;
import com.example.www.pokemonlistapp.model.PokemonList;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*
Adapters provide a binding from an app-specific data set to views that are displayed within a RecyclerView
*/
public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {

    private List<PokemonList> pokemons;
    private  int rowLayout;
    private Context context;
    private ItemClickListener itemClickListener;

    public class PokemonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        LinearLayout pokemonListLayout;
        TextView pokemonName;
        CardView cv;

        PokemonViewHolder(View v) {
            super(v);
            pokemonListLayout = v.findViewById(R.id.pokemon_name_layout);
            pokemonName = v.findViewById(R.id.pokemon_name);
            cv = v.findViewById(R.id.card_view);
            pokemonName.setTag(pokemonName);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(itemClickListener != null) {
                itemClickListener.onItemClick(v, getAdapterPosition());
            }
        }
    }

    public PokemonAdapter(List<PokemonList> pokemons, int rowLayout, Context context){
        this.pokemons = pokemons;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @NonNull
    @Override
    public PokemonAdapter.PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new PokemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, final int position) {
        // display pokemon list in table format
        holder.pokemonName.setText(String.format("%s: %s", String.valueOf(position + 1), capitalize(pokemons.get(position).getName())));

        // Animation for pokemon list display
        Animation animation = AnimationUtils.loadAnimation(context,android.R.anim.slide_in_left);
        animation.setStartOffset(5 * position);//Provide delay here
        holder.itemView.startAnimation(animation);

    }


    @Override
    public int getItemCount() {

        /* -----  restrict number of pokemon list item display because poke api limit number of items display is not working */
        return pokemons.size();
    }

    public void setOnItemClickListener(final ItemClickListener
                                               itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    /* -------------- Capitalize the first letter of pokemon list items ------------ */
    private String capitalize(String capString){
        StringBuffer capBuffer = new StringBuffer();
        Matcher capMatcher = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(capString);
        while (capMatcher.find()){
            capMatcher.appendReplacement(capBuffer, capMatcher.group(1).toUpperCase() + capMatcher.group(2).toLowerCase());
        }

        return capMatcher.appendTail(capBuffer).toString();
    }
}
