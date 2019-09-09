package org.ohmstheresistance.knowyourworld.rv;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.ohmstheresistance.knowyourworld.R;
import org.ohmstheresistance.knowyourworld.model.Country;

import java.util.List;

public class FavoriteCountriesAdapter extends RecyclerView.Adapter<FavoriteCountriesViewHolder> {

    private List<Country> favoriteCountryList;

    public FavoriteCountriesAdapter(List<Country> favoriteCountryList) {

        this.favoriteCountryList = favoriteCountryList;

    }


    @NonNull
    @Override
    public FavoriteCountriesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View childView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.favorite_country_item_view, viewGroup, false);
        return new FavoriteCountriesViewHolder(childView);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteCountriesViewHolder favoriteCountriesViewHolder, int i) {

        final Country country = favoriteCountryList.get(i);
        favoriteCountriesViewHolder.onBind(country);
    }

    @Override
    public int getItemCount() {
        return favoriteCountryList.size();
    }


    public void setData(List<Country> faveCountries) {
        this.favoriteCountryList = faveCountries;
        notifyDataSetChanged();
    }

}
