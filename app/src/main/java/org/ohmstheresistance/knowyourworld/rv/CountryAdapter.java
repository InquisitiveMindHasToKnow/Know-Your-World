package org.ohmstheresistance.knowyourworld.rv;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.ohmstheresistance.knowyourworld.R;
import org.ohmstheresistance.knowyourworld.model.Country;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryViewHolder> {

    private List<Country> countryList;

    public CountryAdapter(List<Country> countryList) {

        this.countryList = countryList;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View childView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.country_item_view, viewGroup, false);
        return new CountryViewHolder(childView);
    }

    @Override
    public void onBindViewHolder(@NonNull final CountryViewHolder countryViewHolder, int i) {

        final Country country = countryList.get(i);
        countryViewHolder.onBind(country);

        if (country.getCapital().isEmpty()) {
            country.setCapital("No Capital City");
            countryViewHolder.countryCapitalTextView.setText("No Capital City");

        }

        if (country.getName().contains("Yugoslav")) {
            country.setName("Macedonia");
            countryViewHolder.countryNameTextView.setText("Macedonia");

        }

        if (country.getName().contains("Venezuela")) {
            country.setName("Venezuela");
            countryViewHolder.countryNameTextView.setText("Venezuela");
        }

        if (country.getName().contains("Democratic People's Republic of")) {
            country.setName("North Korea");
            countryViewHolder.countryNameTextView.setText("North Korea");
        }

        if (country.getName().contains("Plurinational")) {
            country.setName("Bolivia");
            countryViewHolder.countryNameTextView.setText("Bolivia");
        }

        if (country.getArea() == null) {
            country.setArea(0.0);
        }

        if(country.getName().equals("United States Minor Outlying Islands")){
            countryViewHolder.itemView.setClickable(false);
        }

        sortAlphabetically();
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }


    public void setData(List<Country> newCountryList) {
        this.countryList = newCountryList;
        notifyDataSetChanged();
    }

    private void sortAlphabetically() {
        Collections.sort(countryList, new Comparator<Country>() {
            @Override
            public int compare(Country o1, Country o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

    }

}

