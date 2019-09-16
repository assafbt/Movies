/*
 * *
 *  * Created by Assaf Biton
 *
 */

package com.assafbt.movies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MovieDetailsActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        Bundle bundle = getIntent().getExtras();
        setTitle(bundle.getString("movie_title"));

        if(bundle.getString("title")!= null)
        {
            Movie mMovie = new Movie();
            mMovie.setTitle(bundle.getString("movie_title"));
            mMovie.setRating(bundle.getString("movie_rating"));
            mMovie.setYear(bundle.getInt("movie_year"));
            mMovie.setImage(bundle.getString("movie_image"));
            mMovie.setGenre(bundle.getString("movie_genre"));

        }

    }//onCreate

}//MovieDetailsActivity
