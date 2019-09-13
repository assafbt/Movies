/*
 * *
 *  * Created by Assaf Biton
 *
 */

package com.assafbt.movies;

public class Movie {

    public String title;
    public double rating;
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
                    + COLUMN_RATING +     " REAL,"
                    + COLUMN_YEAR +   " INTEGER,"
                    + COLUMN_IMAGE +    " TEXT,"
                    + COLUMN_GENRE +    " TEXT"
                    + ");";

    public Movie() {

    }

    public Movie(String title, double rating, int year, String image, String genre) {
/*        setTitle(title);
        setRating(rating);
        setYear(year);
        setImage(image);
        setGenre(genre);*/
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
    public double getRating() {
        return rating;
    }
    public void setRating(double rating) {
        /*
        //show 2 numbers after the dot
        BigDecimal bd = new BigDecimal(input).setScale(2, RoundingMode.HALF_UP);
        this.rating = bd.doubleValue();
         */
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
