package org.ohmstheresistance.knowyourworld.rv;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.ohmstheresistance.knowyourworld.R;
import org.ohmstheresistance.knowyourworld.model.Country;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class CountryAdapter extends RecyclerView.Adapter<CountryViewHolder> {

    private List<Country> countryList;
    private TextToSpeech textToSpeech;

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

        countryViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               final String countryName = country.getName();
               final String countryCapital = country.getCapital();
               final Integer countryPopulation = country.getPopulation();
              // final String bragJustCause = " Just F Y Y... you've been taught!";


                textToSpeech = new TextToSpeech(countryViewHolder.itemView.getContext(), new TextToSpeech.OnInitListener() {

                    @Override
                    public void onInit(int status) {

                        if (status == TextToSpeech.SUCCESS) {

                            textToSpeech.setLanguage(Locale.US);
                            playNextChunk("You selected " + countryName  + "...The capital of " + countryName + " is " + countryCapital
                                    + "...There are approximately " + countryPopulation + " people living in " + countryName);

                        }

                    }
                });
            }

        });
    }


    private void playNextChunk(String text) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ttsGreater21(text);
        } else {
            ttsUnder20(text);
        }}
    @SuppressWarnings("deprecation")
    private void ttsUnder20(String text) {
        HashMap<String, String> map = new HashMap<>();
        map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "MessageId");
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, map);
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void ttsGreater21(String text) {
        String utteranceId = this.hashCode() + "";
        Bundle params = new Bundle();
        params.putString(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "");
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, params, utteranceId);
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

}

