package id.ihsan.popmovie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Locale;

import id.ihsan.popmovie.helpers.Formatter;
import id.ihsan.popmovie.helpers.ViewHelper;
import id.ihsan.popmovie.models.Movie;
import id.ihsan.popmovie.models.MovieDetail;
import id.ihsan.popmovie.networks.RestClient;
import id.ihsan.popmovie.utils.Constans;
import id.ihsan.popmovie.widgets.MovieImageView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * @author Ihsan Helmi Faisal <ihsan.helmi@ovo.id>
 * @since 2017.10.07
 */
public class MovieDetailActivity extends AppBaseActivity {

    private Toolbar toolbar;
    private MovieImageView imgMovie;
    private TextView txtRelease, txtTime, txtRating, txtOverview;

    private Movie movie;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        findViews();

        if (savedInstanceState != null) {
            movie = savedInstanceState.getParcelable("movie");
        }

        if (getIntent() != null) {
            movie = getIntent().getParcelableExtra("movie");
            if (movie != null) {
                initViews();
                initListeners();
                requestMovieDetail(movie.getId());
            }
        }
    }

    @Override
    public void findViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        imgMovie = (MovieImageView) findViewById(R.id.img_movie);
        txtRelease = (TextView) findViewById(R.id.txt_release_year);
        txtTime = (TextView) findViewById(R.id.txt_time);
        txtRating = (TextView) findViewById(R.id.txt_rating);
        txtOverview = (TextView) findViewById(R.id.txt_overview);
    }

    @Override
    public void initViews() {
        setSupportActionBar(toolbar);
        if (movie != null) {
            setupToolbar(toolbar, movie.getTitle());
            String urlImage = Constans.BASE_IMAGE_URL + "w342" + movie.getPosterPath();
            Picasso.with(this).load(urlImage).into(imgMovie);
            txtRelease.setText(Formatter.parseYear(movie.getReleaseDate()));
            txtRating.setText(String.format(Locale.US, "%s / 10", Formatter.formatDoubleToString(movie.getVoteAverage())));
            txtOverview.setText(movie.getOverview());
        }
    }

    @Override
    public void initListeners() {

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable("movie", movie);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            MovieDetailActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    protected void setupToolbar(Toolbar toolbar, String title) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setTitle(title);
        }
    }

    private void requestMovieDetail(long movieId) {
        showDialog();
        RestClient.ApiService apiService = RestClient.getClient();
        Observable<MovieDetail> call = apiService.getMovieDetail(movieId);

        call.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MovieDetail>() {
                    @Override
                    public void onCompleted() {
                        dismissDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        dismissDialog();
                        ViewHelper.showToast(MovieDetailActivity.this, RestClient.getErrorFail(MovieDetailActivity.this, e));
                    }

                    @Override
                    public void onNext(MovieDetail response) {
                        dismissDialog();
                        if (response != null) {
                            txtTime.setText(String.format(Locale.US, "%d mins", response.getRuntime()));
                        }
                    }
                });
    }
}
