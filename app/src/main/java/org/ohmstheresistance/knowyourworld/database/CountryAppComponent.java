package org.ohmstheresistance.knowyourworld.database;

import org.ohmstheresistance.knowyourworld.network.RetrofitSingleton;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RetrofitSingleton.class, AndroidModule.class, CountryDatabaseModule.class})
public interface CountryAppComponent {


}