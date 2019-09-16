/*
 * *
 *  * Created by Assaf Biton
 *
 */

package com.assafbt.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {
    TextView tvTitle, tvRating,tvYear,tvGenre;
    ImageView ivImage;
    private String TAG = "MovieDetailsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        Bundle bundle = getIntent().getExtras();
        setTitle(bundle.getString("movie_title"));
        String functionTAG = TAG + ",onCreate";

        tvTitle = (TextView) findViewById(R.id.tv_Title);
        tvRating = (TextView) findViewById(R.id.tv_Rating);
        tvYear = (TextView) findViewById(R.id.tv_Year);
        tvGenre = (TextView) findViewById(R.id.tv_Genre);
        ivImage = (ImageView) findViewById(R.id.iv_Image);

        String title = "0";
        String rating = "0";
        String image = "0";
        String genre = "0";
        int year = 0;

        Movie mMovie = new Movie(title,rating, year, image,genre);

        if(bundle.getString("movie_title")!= null)
        {

            title = bundle.getString("movie_title");
            rating = bundle.getString("movie_rating");
            image = bundle.getString("movie_image");
            genre = bundle.getString("movie_genre");
            year = bundle.getInt("movie_year");
            Log.i(functionTAG, "functionTAG, 2 title-genre " + title + " - " + genre);
            Log.i(functionTAG, "functionTAG, 2 year-image " + year + " - " + image);


            mMovie.setTitle(title);
            mMovie.setRating(rating);
            mMovie.setYear(year);
            mMovie.setImage(image);
            mMovie.setGenre(genre);

            tvTitle.append(title);
            tvRating.append(rating);
            tvYear.append(String.valueOf(year));
            tvGenre.append(genre);


            Picasso.get().load(mMovie.getImage())
                    .placeholder(R.drawable.folder_image)
                    .fit().centerInside().into(ivImage);

        }
        Log.i(functionTAG, "functionTAG, 1 title-genre " + mMovie.title + " - " + mMovie.genre);
        Log.i(functionTAG, "functionTAG, 1 year-image " + mMovie.year + " - " + mMovie.image);




    }//onCreate

}//MovieDetailsActivity
