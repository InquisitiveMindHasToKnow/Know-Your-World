package org.ohmstheresistance.knowyourworld.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.ohmstheresistance.knowyourworld.model.Country;

import java.util.ArrayList;
import java.util.List;


    public class CountryDatabaseHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "favoritesDatabase";
        private static final int DATABASE_VERSION = 1;
        private static final String TABLE_FAVORITES = "favorites";

        private static CountryDatabaseHelper countryDatabaseInstance;

        public static synchronized CountryDatabaseHelper getInstance(Context context) {
            if (countryDatabaseInstance == null) {
                countryDatabaseInstance = new CountryDatabaseHelper(context.getApplicationContext());
            }
            return countryDatabaseInstance;
        }

        public CountryDatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }


        @Override public void onCreate(SQLiteDatabase db) {
            String CREATE_FAVORITES_TABLE =
                    "CREATE TABLE " + TABLE_FAVORITES + "("
                            + "country_name TEXT PRIMARY KEY,"
                            + "country_flag TEXT NOT NULL"
                            + ")";

            db.execSQL(CREATE_FAVORITES_TABLE);
        }

        @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            if (oldVersion != newVersion) {
                db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITES);
                onCreate(db);
            }
        }

        public void addFavorite(Country favorite) {
            SQLiteDatabase db = getWritableDatabase();

            try {
                ContentValues values = new ContentValues();
                values.put("country_name", favorite.getName());
                values.put("country_flag", favorite.getFlag());
                db.insertOrThrow(TABLE_FAVORITES, null, values);
            } catch (Exception e) {
                Log.e("Favorites", "Error while trying to add post to database", e);
            }
        }

        public boolean isFavorite(String countryName) {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor =
                    db.rawQuery("SELECT COUNT(1) as count FROM " + TABLE_FAVORITES + " WHERE country_name = ?",
                            new String[] { String.valueOf(countryName) });

            int count = 0;
            try {
                if (cursor.moveToFirst()) {
                    do {
                        count = cursor.getInt(cursor.getColumnIndex("count"));
                    } while (cursor.moveToNext());
                }
            } catch (Exception e) {
                Log.e("Favories", "Error while trying to get posts from database", e);
            } finally {
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
            }

            return count != 0;
        }

        public List<Country> getFavorites() {
            List<Country> favorites = new ArrayList<>();

            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_FAVORITES, null);
            try {
                if (cursor.moveToFirst()) {
                    do {
                        String countryName = (cursor.getString(cursor.getColumnIndex("country_name")));
                        String countryFlag = cursor.getString(cursor.getColumnIndex("country_flag"));
                        favorites.add(Country.from(countryName, countryFlag));
                    } while (cursor.moveToNext());
                }
            } catch (Exception e) {
                Log.e("Favorites", "Error while trying to get favorites from database", e);
            } finally {
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
            }
            return favorites;
        }


        public void deleteFavorite(String countryName) {
            SQLiteDatabase db = getWritableDatabase();

            try {
                db.delete(TABLE_FAVORITES, "country_name = ?", new String[] { String.valueOf(countryName) });
            } catch (Exception e) {
                Log.e("Favorites", "Error while trying to delete all posts and users", e);
            }
        }


    }
