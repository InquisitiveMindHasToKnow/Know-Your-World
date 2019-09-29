package org.ohmstheresistance.knowyourworld.rv;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.ohmstheresistance.knowyourworld.R;
import org.ohmstheresistance.knowyourworld.model.Country;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class CountryAdapter extends RecyclerView.Adapter<CountryViewHolder> {

    private long lastClickTime = 0;
    private List<Country> countryList;
    public TextToSpeech textToSpeech;

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

        if(country.getCapital().isEmpty()){
            country.setCapital("No Capital City");
            countryViewHolder.countryCapitalTextView.setText("No Capital City");

        }

        if(country.getName().contains("Yugoslav")){
            country.setName("Macedonia");
            countryViewHolder.countryNameTextView.setText("Macedonia");

        }

        if(country.getName().contains("Venezuela")){
            country.setName("Venezuela");
            countryViewHolder.countryNameTextView.setText("Venezuela");
        }

        if(country.getName().contains("Democratic People's Republic of")){
            country.setName("North Korea");
            countryViewHolder.countryNameTextView.setText("North Korea");
        }

        if(country.getName().contains("Plurinational")){
            country.setName("Bolivia");
            countryViewHolder.countryNameTextView.setText("Bolivia");
        }

        if(country.getArea() == null){
            country.setArea(0.0);
        }

        sortAlphabetically();

        countryViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (SystemClock.elapsedRealtime() - lastClickTime < 5000) {
                    return;
                }
                lastClickTime = SystemClock.elapsedRealtime();


                textToSpeech = new TextToSpeech(countryViewHolder.itemView.getContext(), new TextToSpeech.OnInitListener() {

                    @Override
                    public void onInit(int status) {

                        String countryName = country.getName();
                        String countryCapital = country.getCapital();
                        Integer countryPopulation = country.getPopulation();
                        String countryRegion = country.getSubregion();
                        String countryCitizens = country.getDemonym();


                        if (status == TextToSpeech.SUCCESS) {

                            textToSpeech.setLanguage(Locale.US);
                            playNextChunk(countryName + ". The capital of " + countryName + " is " + countryCapital + ". "
                                      + countryName + " is located in " + countryRegion + "." + " There are approximately " + countryPopulation + countryCitizens + " living in " + countryName + ".");

                        }

                        if(countryName.equals("Antarctica") || countryName.equals("Bouvet Island") || countryName.equals("United States Minor Outlying Islands")
                                || countryName.equals("Heard Island and McDonald Islands") || countryName.equals("Macao") || countryName.contains("Saint Helena")) {


                            textToSpeech.setLanguage(Locale.US);
                            playNextChunk(countryName + "." + " There is no capital city in  " + countryName + ". " + countryName + " is located in " + countryName + "." + " There are approximately " + countryPopulation + " people living in " + countryName + ".");


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
        }
    }

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

