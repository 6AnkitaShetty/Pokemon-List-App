package com.example.www.pokemonlistapp.view;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.www.pokemonlistapp.R;
import com.squareup.picasso.Picasso;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static com.example.www.pokemonlistapp.R.drawable.ic_arrow_back_black_24dp;
public class PokemonDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pokemon_details);
        final Toolbar toolbar = findViewById(R.id.pokemon_detailed_toolbar);
        setSupportActionBar(toolbar);

        /*-----Back arrow display in toolbar-----*/
        final Drawable upArrow = getResources().getDrawable(ic_arrow_back_black_24dp, null);
        upArrow.setColorFilter(getResources().getColor(R.color.White), PorterDuff.Mode.SRC_ATOP);
        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(upArrow);

        /*-------This method just controls whether to show the Activity icon/logo or not. ------*/
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        /*---This method makes the icon and title in the action bar clickable so that “up” navigation can be provided.----*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        getSupportActionBar().setTitle(capitalize(intent.getStringExtra("POKEMON_NAME")));

        /*-------Display pokemon details display and animation--------*/
        final TextView pokemonName = findViewById(R.id.pokemon_name);
        pokemonName.setText(String.format("Name: %s", String.valueOf(capitalize(Objects.requireNonNull(intent.getExtras()).getString("POKEMON_NAME")))));
        pokemonName.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_slide_in_right));

        final ImageView pokemonFrontSprite = findViewById(R.id.pokemon_front_sprite);
        Picasso.with(this).load(Objects.requireNonNull(intent.getExtras()).getString("POKEMON_SPRITE")).into(pokemonFrontSprite);
        pokemonFrontSprite.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_slide_in_left));

        final TextView pokemonHeight = findViewById(R.id.pokemon_height);
        pokemonHeight.setText(String.format("%sm",(String.format("Height: %s", String.valueOf(intent.getExtras().getInt("POKEMON_HEIGHT")/10.0)))));
        pokemonHeight.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_slide_in_left));

        final TextView pokemonWeight = findViewById(R.id.pokemon_weight);
        pokemonWeight.setText(String.format("  %sKg", String.format("Weight: %s", String.valueOf(intent.getExtras().getInt("POKEMON_WEIGHT")/10.0))));
        pokemonWeight.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_slide_in_right));

        final TextView pokemonTypes = findViewById(R.id.pokemon_types);
        pokemonTypes.setText((Objects.requireNonNull(intent.getExtras().getString("POKEMON_TYPES"))));
        pokemonTypes.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_slide_out_left));

        final TextView pokemonAbilities = findViewById(R.id.pokemon_abilities);
        pokemonAbilities.setText(intent.getExtras().getString("POKEMON_ABILITY"));
        pokemonAbilities.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_slide_out_right));
    }


    private String capitalize(String capString){
        StringBuffer capBuffer = new StringBuffer();
        Matcher capMatcher = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(capString);
        while (capMatcher.find()){
            capMatcher.appendReplacement(capBuffer, capMatcher.group(1).toUpperCase() + capMatcher.group(2).toLowerCase());
        }

        return capMatcher.appendTail(capBuffer).toString();
    }

    /*------Back button click animation-------*/
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        overridePendingTransition(R.anim.anim_slide_in_right,
                R.anim.anim_slide_out_right);

        return true;
    }

    /*------Back button click animation-------*/
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, PokemonListActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        overridePendingTransition(R.anim.anim_slide_in_right,
                R.anim.anim_slide_out_right);
        finish();
    }
}