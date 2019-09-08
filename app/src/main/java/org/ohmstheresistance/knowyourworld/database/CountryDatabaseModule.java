package org.ohmstheresistance.knowyourworld.database;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class CountryDatabaseModule {
    @Provides
    @Singleton
    CountryDatabaseHelper provideDatabaseHelper(@App Context context) {
        return new CountryDatabaseHelper(context);
    }
}
