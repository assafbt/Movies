/*
 * *
 *  * Created by Assaf Biton
 *
 */

package com.assafbt.movies;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {
    private String TAG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 5;

    // Database Name
    private static final String DATABASE_NAME = "movies_db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }//DatabaseHelper(Context

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // create notes table
        sqLiteDatabase.execSQL(Movie.CREATE_TABLE);
        Log.i(TAG, "isDatabaseIntegrityOk " + sqLiteDatabase.isDatabaseIntegrityOk());
    }//onCreate(SQLiteDatabase

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Movie.TABLE_NAME);

        // Create tables again
        onCreate(sqLiteDatabase);
    }//onUpgrade(SQLiteDatabase

    public long insertMovie(Movie movie) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();
        Log.i(TAG+" insertMovie,", "db = " + db.toString());
		
        ContentValues values = new ContentValues();

        values.put(Movie.COLUMN_TITLE, movie.getTitle());
        values.put(Movie.COLUMN_YEAR, movie.getYear());
        values.put(Movie.COLUMN_RATING, movie.getRating());
        values.put(Movie.COLUMN_IMAGE, movie.getImage());
        values.put(Movie.COLUMN_GENRE, movie.getGenre());
		
		
		Log.i(TAG +" insertMovie, Val", "getTitle: " + movie.getTitle());
		Log.i(TAG +" insertMovie, Val", "getYear: " + movie.getYear());
        Log.i(TAG +" insertMovie, Val", "values.containsKey: " + values.containsKey("year"));
		Log.i(TAG +" insertMovie, Val", "getRating: " + movie.getRating());
		Log.i(TAG +" insertMovie, Val", "getImage: " + movie.getImage());
        Log.i(TAG +" insertMovie, Val", "getGenre: " + movie.getGenre());

        // insert row
        long id = db.insert(Movie.TABLE_NAME, null, values);
        Log.i(TAG +" insertMovie,", "added in possition: " + id);
        Log.i(TAG +" insertMovie, value - ", values.toString());

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }//insertMovie

    public List<Movie> getAllMovies() { // #################
        String funTag = TAG + ", getAllMovies:";
        String query =  "SELECT  * FROM " + Movie.TABLE_NAME + " ORDER BY " + Movie.COLUMN_YEAR +" ASC";

        List<Movie> mMovieList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Movie mMovie;

        if (cursor.moveToFirst()) {
            do {
                mMovie = new Movie();
                mMovie.setTitle(cursor.getString(cursor.getColumnIndex(Movie.COLUMN_TITLE)));
                mMovie.setRating(cursor.getString(cursor.getColumnIndex(Movie.COLUMN_RATING)));
                mMovie.setYear(cursor.getInt(cursor.getColumnIndex(Movie.COLUMN_YEAR)));
                mMovie.setImage(cursor.getString(cursor.getColumnIndex(Movie.COLUMN_IMAGE)));
                mMovie.setGenre(cursor.getString(cursor.getColumnIndex(Movie.COLUMN_GENRE)));

                mMovieList.add(mMovie);
            } while (cursor.moveToNext());
        }
        // close db connection
        db.close();

        return mMovieList;
    }//getAllMovies


    public int getDatabaseVersion()
    {
        return this.DATABASE_VERSION;
    }

    public String getDatabaseName()
    {
        return this.DATABASE_NAME;
    }

}//DatabaseHelper extends
