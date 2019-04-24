package com.example.level_6_assignment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Serializable {

    private MoviesRepository moviesRepository;
    RecyclerViewAdapter adapter;
    RecyclerView recyclerView;
    private List<Movie> movieList;
    TextView yearView;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moviesRepository = MoviesRepository.getInstance();

        yearView = findViewById(R.id.yearInput);

        submitButton = findViewById(R.id.submitButton);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                String value = yearView.getText().toString();
                int release = Integer.parseInt(value);

                moviesRepository.getMovies(new OnGetMoviesCallback() {
                    @Override
                    public void onSuccess(List<Movie> movies) {
                        movieList = movies;
                        updateUI();

                    }

                    @Override

                    public void onError() {
                        Toast.makeText(MainActivity.this, "Please check your internet connection.", Toast.LENGTH_SHORT).show();
                    }
                }, release);

            }
        });


        recyclerView = findViewById(R.id.recyclerv_view);
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                Intent intent = new Intent(MainActivity.this, MovieDetailActivity.class);
                Movie movie = movieList.get(position);
                intent.putExtra("movie", movie);

                startActivityForResult(intent, 1);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


    }

    private void updateUI() {
        if (adapter == null) {
            adapter = new RecyclerViewAdapter(movieList);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.swapList(movieList);
        }
    }
}
