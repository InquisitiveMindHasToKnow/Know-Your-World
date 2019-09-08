package org.ohmstheresistance.knowyourworld.database;

import android.app.Application;

public class CountryApp extends Application{


        private CountryApp appComponent;

        @Override
        public void onCreate() {
            super.onCreate();
            appComponent = (CountryApp) DaggerCountryAppComponent.builder()
                    .androidModule(new AndroidModule(this))
                    .build();
        }

        public CountryApp component() {
            return appComponent;
        }
    }


