package org.ohmstheresistance.knowyourworld.rv;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.ohmstheresistance.knowyourworld.R;
import org.ohmstheresistance.knowyourworld.model.Country;



public class CountryViewHolder  extends RecyclerView.ViewHolder {

    private ImageView countryFlagImageView;
    private TextView countryNameTextView;
    private TextView countryCapitalTextView;

    public CountryViewHolder(@NonNull View itemView) {
        super(itemView);

        countryFlagImageView = itemView.findViewById(R.id.country_flag_imageview);
        countryNameTextView = itemView.findViewById(R.id.country_name_textview);
        countryCapitalTextView = itemView.findViewById(R.id.country_capital_textview);

    }

    public void onBind(Country country) {

        String countryFlag = country.getFlag();
        String countryName = country.getName();
        String countryCapital = country.getCapital();

        countryNameTextView.setText(countryName);
        countryCapitalTextView.setText(countryCapital);


        Picasso.get()
                .load(countryFlag)
                .into(countryFlagImageView);



    }
}
