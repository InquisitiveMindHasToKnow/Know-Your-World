package org.ohmstheresistance.knowyourworld.activities;


import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
    private List<Country> favorites;
    private ConstraintLayout favoriteCountriesConstraintLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_countries);

        emptyFavoriteCountryNameTextView = findViewById(R.id.favorites_empty_textview);
        favoritesRecyclerView = findViewById(R.id.favorites_recyclerview);
        emptyFavoriteListImageView = findViewById(R.id.empty_favorite_list_imageview);
        favoriteCountriesConstraintLayout = findViewById(R.id.favorite_countries_constraint_layout);


        Glide.with(this).load(R.drawable.sadtears).into(emptyFavoriteListImageView);

        View root = ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);

        ViewGroup.LayoutParams layoutParams = root.getLayoutParams();
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;

        favorites = countryDatabaseHelper.getFavorites();

        favoriteCountryAdapter = new FavoriteCountriesAdapter(favorites);
        favoritesRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        favoritesRecyclerView.setAdapter(favoriteCountryAdapter);


        if (favorites.isEmpty()) {
            favoritesRecyclerView.setVisibility(GONE);
            emptyFavoriteCountryNameTextView.setVisibility(VISIBLE);
            emptyFavoriteListImageView.setVisibility(VISIBLE);
        } else {
            favoriteCountryAdapter.setData(favorites);
            root.setBackgroundColor(Color.parseColor("#112631"));
            root.setLayoutParams(layoutParams);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.delete_database_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.database_delete_all:

                if (favorites.isEmpty()) {

                    Snackbar selectAnswerSnackbar = Snackbar.make(favoriteCountriesConstraintLayout, "Nothing to delete.", Snackbar.LENGTH_LONG);
                    View snackbarView = selectAnswerSnackbar.getView();
                    TextView snackBarTextView = snackbarView.findViewById(android.support.design.R.id.snackbar_text);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
                        snackBarTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    else
                        snackBarTextView.setGravity(Gravity.CENTER_HORIZONTAL);

                    selectAnswerSnackbar.show();
                }else {


                    new AlertDialog.Builder(FavoriteCountries.this)
                            .setTitle("Delete Favorites")
                            .setMessage("Are you sure you want to delete all your favorite countries?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {


                                    countryDatabaseHelper.clearFavoriteCountryDatabase();
                                    favoriteCountryAdapter.setData(favorites);
                                    FavoriteCountries.this.finish();
                                }
                            })
                            .setNegativeButton(android.R.string.no, null)
                            .setIcon(R.drawable.stop)
                            .show();


                    break;
                }

                }
                return true;
        }
    }
