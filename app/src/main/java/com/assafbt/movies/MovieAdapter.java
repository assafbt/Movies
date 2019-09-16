/*
 * *
 *  * Created by Assaf Biton
 *
 */

package com.assafbt.movies;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private Context context;
    private List<Movie> movieList;

    public MovieAdapter(Context context, List<Movie> list) {
        this.context = context;
        this.movieList = list;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.single_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = movieList.get(position);

        holder.textTitle.setText(movie.getTitle());
       // holder.textRating.setText(String.valueOf(movie.getRating()));
        holder.textYear.setText(String.valueOf(movie.getYear()));

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textTitle, textYear;

        public ViewHolder(View itemView) {
            super(itemView);

            textTitle = itemView.findViewById(R.id.main_title);
            textYear = itemView.findViewById(R.id.main_year);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    // get position
                    int pos = getAdapterPosition();

                    // check if item still exists
                    if(pos != RecyclerView.NO_POSITION){
                        Movie clickedMovie = movieList.get(pos);
                        Toast.makeText(v.getContext(), "You Choose " + clickedMovie.title, Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(context, MovieDetailsActivity.class);
                        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("movie_title",clickedMovie.title);
                        intent.putExtra("movie_rating",clickedMovie.rating);
                        intent.putExtra("movie_year",clickedMovie.year);
                        intent.putExtra("movie_image",clickedMovie.image);
                        intent.putExtra("movie_genre",clickedMovie.genre);

                        intent.putExtra("movie_Possiton", pos);
//                        intent.putExtra("Movie_obj", (Serializable) movieList.get(pos));
                        context.getApplicationContext().startActivity(intent);

                    }
                }
            });


        }//ViewHolder
    }//ViewHolder extends



}