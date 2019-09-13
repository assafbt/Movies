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
    private static final int DATABASE_VERSION = 2;

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
        Log.i(TAG +" insertMovie, Val", "getTitle: " + movie.getTitle());
        values.put(Movie.COLUMN_YEAR, movie.getYear());
        Log.i(TAG +" insertMovie, Val", "getYear: " + movie.getYear());
        Log.i(TAG +" insertMovie, Val", "values.containsKey: " + values.containsKey("year"));
        values.put(Movie.COLUMN_RATING, movie.getRating());
        Log.i(TAG +" insertMovie, Val", "getRating: " + movie.getRating());
        values.put(Movie.COLUMN_IMAGE, movie.getImage());
        Log.i(TAG +" insertMovie, Val", "getImage: " + movie.getImage());
        values.put(Movie.COLUMN_GENRE, movie.getGenre());
        Log.i(TAG +" insertMovie, Val", "getGenre: " + movie.getGenre());

        // insert row
        long id = db.insert(Movie.TABLE_NAME, null, values);
        Log.i(TAG +" insertMovie,", "added in possition: " + id);
        Log.i(TAG +" insertMovie, value - ", values.toString());
//        Log.i(TAG +" insertMovie, movie - ", movie.toString());

        // close db connection
        db.close();


        // return newly inserted row id
        return id;
    }//insertMovie

    public Movie getMovie(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Movie.TABLE_NAME,
                new String[]{Movie.COLUMN_TITLE, Movie.COLUMN_RATING, Movie.COLUMN_YEAR},
                Movie.COLUMN_TITLE + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        Movie mMovie = new Movie(
                cursor.getString(cursor.getColumnIndex(Movie.COLUMN_TITLE)),
                cursor.getInt(cursor.getColumnIndex(Movie.COLUMN_RATING)),
                cursor.getInt(cursor.getColumnIndex(Movie.COLUMN_YEAR)),
                cursor.getString(cursor.getColumnIndex(Movie.COLUMN_GENRE)),
                cursor.getString(cursor.getColumnIndex(Movie.COLUMN_IMAGE)));

        // close the db connection
        cursor.close();

        return mMovie;
    }//getNote

    public List<Movie> getAllMovies() { // #################
        String funTag = TAG + ", getAllMovies:";
        List<Movie> mMovieList = new ArrayList<>();

        Log.i(funTag," ");
        // Select All Query
        String selectQuery = "SELECT * FROM " + Movie.TABLE_NAME + " ORDER BY " +
                Movie.COLUMN_YEAR + " DESC";
        Log.i(funTag," selectQuery - " + selectQuery);

        SQLiteDatabase db = this.getWritableDatabase();
      //  Cursor cursor = db.rawQuery(selectQuery, null);
       // String[] colums = {COLUMN_TITLE};
       // Cursor cursor = db.query(Movie.TABLE_NAME, colums,null, null, null, null,Movie.COLUMN_YEAR +" DESC");
      //  Cursor c = scoreDb.query(DATABASE_TABLE, rank, null, null, null, null, yourColumn+" DESC");

        String[] columns = {Movie.COLUMN_YEAR, Movie.COLUMN_TITLE};
        Cursor cursor = db.query(Movie.TABLE_NAME,columns,null,null,null,null,null);

        Log.i(funTag,"cursor 1st - " + cursor.moveToFirst());

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Movie mMovie = new Movie();
                mMovie.setTitle(cursor.getString(cursor.getColumnIndex(Movie.COLUMN_TITLE)));
                mMovie.setRating(cursor.getInt(cursor.getColumnIndex(Movie.COLUMN_RATING)));
                mMovie.setYear(cursor.getInt(cursor.getColumnIndex(Movie.COLUMN_YEAR)));
                mMovie.setGenre(cursor.getString(cursor.getColumnIndex(Movie.COLUMN_GENRE)));
                mMovie.setImage(cursor.getString(cursor.getColumnIndex(Movie.COLUMN_IMAGE)));

                Log.i(funTag,"cursor - " + cursor.toString());
                Log.i(funTag, "movie - " + mMovie.toString());

                mMovieList.add(mMovie);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return mMovieList;
    }//getAllMovies

    public int getMoviesCount() {
        String countQuery = "SELECT  * FROM " + Movie.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }

    public int getDatabaseVersion()
    {
        return this.DATABASE_VERSION;
    }

    public String getDatabaseName()
    {
        return this.DATABASE_NAME;
    }

}//DatabaseHelper extends
