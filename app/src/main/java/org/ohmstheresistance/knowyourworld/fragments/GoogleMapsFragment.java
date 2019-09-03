package org.ohmstheresistance.knowyourworld.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.ohmstheresistance.knowyourworld.R;


public class GoogleMapsFragment extends Fragment implements OnMapReadyCallback {


    private static final String RANDOM_COUNTRY_LATITUDE_KEY = "randomCountryLatitudeKey";
    private static final String RANDOM_COUNTRY_LONGITUDE_KEY = "randomCountryLongitudeKey";
    private static final String RANDOM_COUNTRY_KEY = "randomCountryKey";
    private String TAG_FOR_MAP_ICON = "";


    private GoogleMap countriesOfTheWorldMap;
    private MapView countriesOfTheWorldMapView;
    private View googleMapView;


    private String latitude;
    private String longitude;
    private String randomCountry;

    private double lat;
    private double lon;


    public GoogleMapsFragment() {
        // Required empty public constructor
    }


    public static GoogleMapsFragment getInstance(String locationLongitude, String locationLatitude , String countryName) {
        GoogleMapsFragment googleMapFragment = new GoogleMapsFragment();


        Bundle args = new Bundle();

        args.putString(RANDOM_COUNTRY_LATITUDE_KEY, locationLatitude);
        args.putString(RANDOM_COUNTRY_LONGITUDE_KEY, locationLongitude);
        args.putString(RANDOM_COUNTRY_KEY, countryName);
        googleMapFragment.setArguments(args);
        return googleMapFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return googleMapView = inflater.inflate(R.layout.fragment_google_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        countriesOfTheWorldMapView = googleMapView.findViewById(R.id.googlemaps);

        if (countriesOfTheWorldMapView != null) {
            countriesOfTheWorldMapView.onCreate(null);
            countriesOfTheWorldMapView.onResume();
            countriesOfTheWorldMapView.getMapAsync(this);


            if (getArguments() != null) {


                longitude = getArguments().getString(RANDOM_COUNTRY_LONGITUDE_KEY);
                latitude = getArguments().getString(RANDOM_COUNTRY_LATITUDE_KEY);
                randomCountry = getArguments().getString(RANDOM_COUNTRY_KEY);

                TAG_FOR_MAP_ICON = randomCountry;

                lat = Double.parseDouble(latitude);
                lon = Double.parseDouble(longitude);

            }
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {


        countriesOfTheWorldMap = googleMap;


        LatLng latLng = new LatLng(lat, lon);
        countriesOfTheWorldMap.addMarker(new MarkerOptions().position(latLng).title(TAG_FOR_MAP_ICON).icon(BitmapDescriptorFactory.fromResource(R.mipmap.atmformap)));

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 20);
        countriesOfTheWorldMap.animateCamera(cameraUpdate);
        UiSettings uiSettings = countriesOfTheWorldMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.setMyLocationButtonEnabled(true);

    }
}
