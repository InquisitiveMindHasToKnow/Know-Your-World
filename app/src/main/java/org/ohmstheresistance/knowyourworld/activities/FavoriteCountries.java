package org.ohmstheresistance.knowyourworld.activities;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.ohmstheresistance.knowyourworld.R;
import org.ohmstheresistance.knowyourworld.database.CountryDatabaseHelper;
import org.ohmstheresistance.knowyourworld.model.Country;
import org.ohmstheresistance.knowyourworld.rv.FavoriteCountriesAdapter;

import java.util.List;


import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class FavoriteCountries extends AppCompatActivity {

    FavoriteCountriesAdapter favoriteCountryAdapter;
    RecyclerView favoritesRecyclerView;
    private TextView emptyFavoriteCountryNameTextView;
    private ImageView emptyFavoriteListImageView;
    CountryDatabaseHelper countryDatabaseHelper = new CountryDatabaseHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_countries);

        emptyFavoriteCountryNameTextView = findViewById(R.id.favorites_empty_textview);
        favoritesRecyclerView = findViewById(R.id.favorites_recyclerviiew);
        emptyFavoriteListImageView = findViewById(R.id.empty_favorite_list_imageview);


        Glide.with(this).load(R.drawable.sadtears).into(emptyFavoriteListImageView);



        List<Country> favorites = countryDatabaseHelper.getFavorites();

        favoriteCountryAdapter = new FavoriteCountriesAdapter(favorites);
        favoritesRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        favoritesRecyclerView.setAdapter(favoriteCountryAdapter);

        if (favorites.isEmpty()) {
            favoritesRecyclerView.setVisibility(GONE);
            emptyFavoriteCountryNameTextView.setVisibility(VISIBLE);
            emptyFavoriteListImageView.setVisibility(VISIBLE);
        } else {
            favoriteCountryAdapter.setData(favorites);
        }


    }
}
