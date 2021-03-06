package org.ohmstheresistance.knowyourworld.activities;


import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.ohmstheresistance.knowyourworld.R;
import org.ohmstheresistance.knowyourworld.database.CountryDatabaseHelper;
import org.ohmstheresistance.knowyourworld.model.Country;
import org.ohmstheresistance.knowyourworld.rv.FavoriteCountriesAdapter;

import java.util.Collections;
import java.util.Comparator;
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
    private TextView favoriteCountryHeaderTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_countries);

        emptyFavoriteCountryNameTextView = findViewById(R.id.favorites_empty_textview);
        favoritesRecyclerView = findViewById(R.id.favorites_recyclerview);
        emptyFavoriteListImageView = findViewById(R.id.empty_favorite_list_imageview);
        favoriteCountriesConstraintLayout = findViewById(R.id.favorite_countries_constraint_layout);
        favoriteCountryHeaderTextView = findViewById(R.id.favorite_country_rv_header);

        Glide.with(this).load(R.drawable.sadtears).into(emptyFavoriteListImageView);

        favorites = countryDatabaseHelper.getFavorites();

        favoriteCountryAdapter = new FavoriteCountriesAdapter(favorites);
        favoritesRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        favoritesRecyclerView.setAdapter(favoriteCountryAdapter);
        new ItemTouchHelper(swipeLeftOrRightToDeleteFavorites).attachToRecyclerView(favoritesRecyclerView);

        checkWhatBackgroundToUse();
        sortAlphabetically();

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

                    Snackbar noFavoritesToDeleteSnackbar = Snackbar.make(favoriteCountriesConstraintLayout, "Nothing to delete.", Snackbar.LENGTH_LONG);
                    View snackbarView = noFavoritesToDeleteSnackbar.getView();
                    TextView snackBarTextView = snackbarView.findViewById(android.support.design.R.id.snackbar_text);

                    snackBarTextView.setBackgroundResource(R.drawable.rounded_shape_for_ran_and_sel_snackbars);
                    snackBarTextView.setTextColor(getResources().getColor(R.color.colorAccent));
                    snackbarView.setBackgroundColor(Color.TRANSPARENT);

                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) snackbarView.getLayoutParams();
                    layoutParams.width = FrameLayout.LayoutParams.WRAP_CONTENT;
                    layoutParams.gravity = Gravity.CENTER|Gravity.BOTTOM;
                    snackbarView.setLayoutParams(layoutParams);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
                        snackBarTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    else
                        snackBarTextView.setGravity(Gravity.CENTER_HORIZONTAL);

                    noFavoritesToDeleteSnackbar.show();
                } else {


                    AlertDialog.Builder dialog = new AlertDialog.Builder(FavoriteCountries.this);
                    dialog.setTitle("Delete Favorites?");
                    dialog.setMessage("Are you sure you want to delete all your favorite countries?");
                    dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog1, int id) {

                            countryDatabaseHelper.clearFavoriteCountryDatabase();
                            favoriteCountryAdapter.setData(favorites);
                            FavoriteCountries.this.finish();


                        }
                    })

                            .setNegativeButton("NO ", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog12, int which) {

                                }
                            });

                    final AlertDialog alert = dialog.create();
                    alert.show();

                    break;
                }

        }
        return true;
    }

    ItemTouchHelper.SimpleCallback swipeLeftOrRightToDeleteFavorites = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

            String countryToDeleteFromDB = favorites.get(viewHolder.getAdapterPosition()).getName();
            Log.e("countryToDeleteFromDB", countryToDeleteFromDB);

            countryDatabaseHelper.deleteFavorite(countryToDeleteFromDB);

            favorites.remove(viewHolder.getAdapterPosition());
            favoriteCountryAdapter.notifyDataSetChanged();

            checkWhatBackgroundToUse();

            Snackbar favoriteRemovedSnackbar = Snackbar.make(favoriteCountriesConstraintLayout, countryToDeleteFromDB + " removed.", Snackbar.LENGTH_LONG);
            View snackbarView = favoriteRemovedSnackbar.getView();
            TextView snackBarTextView = snackbarView.findViewById(android.support.design.R.id.snackbar_text);
            snackBarTextView.setBackgroundResource(R.drawable.rounded_shape_for_snackbar);
            snackBarTextView.setTextColor(getResources().getColor(R.color.mainBackgroundColor));
            snackbarView.setBackgroundColor(Color.TRANSPARENT);

            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) snackbarView.getLayoutParams();
            layoutParams.width = FrameLayout.LayoutParams.WRAP_CONTENT;
            layoutParams.gravity = Gravity.CENTER|Gravity.BOTTOM;
            snackbarView.setLayoutParams(layoutParams);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
                snackBarTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            else
                snackBarTextView.setGravity(Gravity.CENTER_HORIZONTAL);

            favoriteRemovedSnackbar.show();

        }
    };

    private void checkWhatBackgroundToUse(){

        View root = ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);

        ViewGroup.LayoutParams layoutParams = root.getLayoutParams();
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;



        if (favorites.isEmpty()) {
            favoritesRecyclerView.setVisibility(GONE);
            emptyFavoriteCountryNameTextView.setVisibility(VISIBLE);
            emptyFavoriteListImageView.setVisibility(VISIBLE);
            favoriteCountryHeaderTextView.setVisibility(View.INVISIBLE);
            root.setBackgroundColor(Color.parseColor("#FFFFFF"));
        } else {
            favoriteCountryAdapter.setData(favorites);
            root.setBackgroundColor(getResources().getColor(R.color.mainBackgroundColor));
            root.setLayoutParams(layoutParams);
        }

    }

    private void sortAlphabetically() {
        Collections.sort(favorites, new Comparator<Country>() {
            @Override
            public int compare(Country o1, Country o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        favoriteCountryAdapter.notifyDataSetChanged();

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0,0);
    }
}
