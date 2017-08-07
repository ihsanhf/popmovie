package id.ihsan.popmovie;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import id.ihsan.popmovie.adapters.MoviesAdapter;
import id.ihsan.popmovie.helpers.ItemClickSupport;
import id.ihsan.popmovie.helpers.MovieContract;
import id.ihsan.popmovie.helpers.MoviesType;
import id.ihsan.popmovie.helpers.ViewHelper;
import id.ihsan.popmovie.models.Movie;
import id.ihsan.popmovie.models.Movies;
import id.ihsan.popmovie.networks.RestClient;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * @author Ihsan Helmi Faisal <ihsan.helmi@ovo.id>
 * @since 2017.10.07
 */
public class MainActivity extends AppBaseActivity {

    private Toolbar toolbar;
    private RecyclerView gridMovies;

    private MoviesAdapter adapter;

    private MoviesType moviesType = MoviesType.POPULAR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        initViews();
        initListeners();
    }

    @Override
    public void findViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        gridMovies = (RecyclerView) findViewById(R.id.view_grid_movies);
    }

    @Override
    public void initViews() {
        setSupportActionBar(toolbar);

        adapter = new MoviesAdapter(this);

        GridLayoutManager manager = new GridLayoutManager(this, 2);
        gridMovies.setLayoutManager(manager);
        gridMovies.setAdapter(adapter);
    }

    @Override
    public void initListeners() {
        ItemClickSupport.addTo(gridMovies).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Movie movie = adapter.getMovie(position);
                if (movie != null) {
                    Intent intent = new Intent(MainActivity.this, MovieDetailActivity.class);
                    intent.putExtra("movie", movie);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (moviesType == MoviesType.POPULAR) {
            retrieveMovies(MoviesType.POPULAR);
        } else if (moviesType == MoviesType.TOP_RATED) {
            retrieveMovies(MoviesType.TOP_RATED);
        } else if (moviesType == MoviesType.FAVORITES) {
            retrieveFavoriteMovies();
            moviesType = MoviesType.FAVORITES;
            invalidateOptionsMenu();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        menu.findItem(R.id.action_popular).setVisible(true);
        menu.findItem(R.id.action_top).setVisible(true);
        menu.findItem(R.id.action_favorite).setVisible(true);

        if (moviesType == MoviesType.POPULAR) {
            menu.findItem(R.id.action_popular).setVisible(false);
            toolbar.setTitle(R.string.action_settings_popular);
        } else if (moviesType == MoviesType.TOP_RATED) {
            menu.findItem(R.id.action_top).setVisible(false);
            toolbar.setTitle(R.string.action_settings_top);
        } else {
            menu.findItem(R.id.action_favorite).setVisible(false);
            toolbar.setTitle(R.string.action_settings_favorite);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_popular) {
            retrieveMovies(MoviesType.POPULAR);
        } else if (id == R.id.action_top) {
            retrieveMovies(MoviesType.TOP_RATED);
        } else if (id == R.id.action_favorite) {
            retrieveFavoriteMovies();
            moviesType = MoviesType.FAVORITES;
            invalidateOptionsMenu();
        }

        return super.onOptionsItemSelected(item);
    }

    private void retrieveFavoriteMovies() {
        List<Movie> movies = new ArrayList<>();
        Cursor c = getContentResolver().query(MovieContract.MovieEntry.CONTENT_URI, new String[]{MovieContract.MovieEntry._ID, MovieContract.MovieEntry.COLUMN_MOVIE},
                null, null, null);
        if (c != null && c.moveToFirst()) {
            do {
                String json = c.getString(c.getColumnIndex(MovieContract.MovieEntry.COLUMN_MOVIE));
                Gson gson = new Gson();
                Movie movie = gson.fromJson(json, Movie.class);
                movies.add(movie);
            } while (c.moveToNext());
        }
        adapter.setMovies(movies);
    }

    private void retrieveMovies(final MoviesType type) {
        showDialog();
        RestClient.ApiService apiService = RestClient.getClient();
        Observable<Movies> call = apiService.getPopularMovie();
        if (type == MoviesType.TOP_RATED) {
            call = apiService.getTopRatedMovie();
        }

        call.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Movies>() {
                    @Override
                    public void onCompleted() {
                        dismissDialog();
                        if (type == MoviesType.POPULAR) {
                            moviesType = MoviesType.POPULAR;
                        } else if (type == MoviesType.TOP_RATED) {
                            moviesType = MoviesType.TOP_RATED;
                        }
                        invalidateOptionsMenu();
                    }

                    @Override
                    public void onError(Throwable e) {
                        dismissDialog();
                        ViewHelper.showToast(MainActivity.this, RestClient.getErrorFail(MainActivity.this, e));
                    }

                    @Override
                    public void onNext(Movies response) {
                        dismissDialog();
                        if (response != null) {
                            adapter.setMovies(response.getResults());
                        }
                    }
                });
    }
}
