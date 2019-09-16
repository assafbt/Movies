/*
 * *
 *  * Created by Assaf Biton
 *
 */

package com.assafbt.movies;

public class Movie {

    public String title;
    public String rating;
    public int year;
    public String image;
    public String genre;

    public static final String TABLE_NAME = "MoviesDB";

    public static final String COLUMN_TITLE = "movie";
    public static final String COLUMN_YEAR = "year";
    public static final String COLUMN_RATING = "rating";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_GENRE = "genre";

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_TITLE +    " TEXT PRIMARY KEY,"
                    + COLUMN_RATING +     " TEXT,"
                    + COLUMN_YEAR +   " INTEGER,"
                    + COLUMN_IMAGE +    " TEXT,"
                    + COLUMN_GENRE +    " TEXT"
                    + ");";

    public Movie() {
        /*
        this.title = "1";
        setTitle("1");
        setRating("1");
        setYear(1111);
        setImage("1");
        setGenre("1");
         */
    }

    public Movie(String title, String rating, int year, String image, String genre) {
        /*
        setTitle(title);
        setRating(rating);
        setYear(year);
        setImage(image);
        setGenre(genre);
        */

        this.title = title;
        this.rating = rating;
        this.year = year;
        this.image = image;
        this.genre = genre;


    }

    //Movie Title
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    //Movie Rating
    public String getRating() {
        return rating;
    }
    public void setRating(String rating) {
        this.rating = rating;
    }

    //Movie Image
    public String getImage() {
        return image;
    }
    void setImage(String image) {
        this.image = image;
    }

    //Movie Genre
    public String getGenre(){
        return genre;
    }
    public void setGenre(String genre){
        this.genre = genre;
    }

    //Movie Year
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
}
