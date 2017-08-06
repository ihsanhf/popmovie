package id.ihsan.popmovie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import id.ihsan.popmovie.adapters.MoviesAdapter;
import id.ihsan.popmovie.helpers.ItemClickSupport;
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
    private Menu menu;
    private RecyclerView gridMovies;

    private MoviesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        initViews();
        initListeners();

        retrieveMovies(MoviesType.POPULAR);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            String itemTitle = item.getTitle().toString();
            if (itemTitle.equals(getString(R.string.action_settings_popular))) {
                retrieveMovies(MoviesType.POPULAR);
            } else if (itemTitle.equals(getString(R.string.action_settings_top))) {
                retrieveMovies(MoviesType.TOP_RATED);
            } else {
                retrieveFavoriteMovies();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void retrieveFavoriteMovies() {
        // TODO : Retrieve Fav Movies from DB
    }

    private void retrieveMovies(final MoviesType moviesType) {
        showDialog();
        RestClient.ApiService apiService = RestClient.getClient();
        Observable<Movies> call = apiService.getPopularMovie();
        if (moviesType == MoviesType.TOP_RATED) {
            call = apiService.getTopRatedMovie();
        }

        call.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Movies>() {
                    @Override
                    public void onCompleted() {
                        dismissDialog();
                        MenuItem menuItem = menu.findItem(R.id.action_settings);
                        if (moviesType == MoviesType.POPULAR) {
                            menuItem.setTitle(R.string.action_settings_top);
                            toolbar.setTitle(R.string.action_settings_popular);
                        } else if (moviesType == MoviesType.TOP_RATED) {
                            menuItem.setTitle(R.string.action_settings_popular);
                            toolbar.setTitle(R.string.action_settings_top);
                        }
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
