/*
 * *
 *  * Created by Assaf Biton
 *
 */

package com.assafbt.movies;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.assafbt.movies.R.id.fabAddMovie;

public class MainActivity extends AppCompatActivity {
    String movieUrl = "https://api.androidhive.info/json/movies.json";
    private Context context;

    private RecyclerView mList;
    private String TAG = "MainActivity";
    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<Movie> movieList = new ArrayList<>();
    private RecyclerView.Adapter adapter;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String functionTAG = TAG +" dbHelper";

        FloatingActionButton fab = findViewById(fabAddMovie);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Current movie already exist in the Database", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
        dbHelper = new DatabaseHelper(this);

        fetchData();

        mList = findViewById(R.id.main_list);

        Log.i(functionTAG,"list1234321");
        movieList = new ArrayList<>();
        movieList = dbHelper.getAllMovies();
        Log.i(functionTAG,"list1234321");

        /*// testing
        Movie tempMovie = new Movie();
        tempMovie = dbHelper.getMovie("District 9");
        Log.i(functionTAG, "get first Movie " + tempMovie.getTitle());

*/
        Log.i(functionTAG, "DatabaseVersion " + dbHelper.getDatabaseVersion());
        Log.i(functionTAG, "MoviesCount  " + dbHelper.getMoviesCount());
        adapter = new MovieAdapter(getApplicationContext(),movieList);


        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(mList.getContext(), linearLayoutManager.getOrientation());

        mList.setHasFixedSize(true);
        mList.setLayoutManager(linearLayoutManager);
        mList.addItemDecoration(dividerItemDecoration);
        mList.setAdapter(adapter);


    }

    private void fetchData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(movieUrl, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        Movie movie = new Movie("0","0",0,"0","0");
                        movie.setTitle(jsonObject.getString("title"));
                        movie.setRating(jsonObject.getString("rating"));
                        movie.setYear(jsonObject.getInt("releaseYear"));
                        movie.setImage(jsonObject.getString("image"));
                        String tempGenre ="";
                        JSONArray genreArr = jsonObject.getJSONArray("genre");
                        for (int j=0; j<genreArr.length(); j++) {
                            if (j>0)
                                tempGenre += ", ";
                            tempGenre += genreArr.getString(j);
                        }
                       // Log.i(TAG, "tempGenre: " + tempGenre);

                        movie.setGenre(tempGenre);
                        long possition = dbHelper.insertMovie(movie);
                        Log.i(TAG, "added in possition: " + possition);


                        //movieList.add(movie);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e("Error on JSONException", e.toString());
                        progressDialog.dismiss();
                    }
                }
                adapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error on Volley", error.toString());
                progressDialog.dismiss();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }//fetchData



}//MainActivity
