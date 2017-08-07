package id.ihsan.popmovie;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.Locale;

import id.ihsan.popmovie.adapters.TrailersAdapter;
import id.ihsan.popmovie.helpers.Formatter;
import id.ihsan.popmovie.helpers.ItemClickSupport;
import id.ihsan.popmovie.helpers.MovieContract;
import id.ihsan.popmovie.helpers.ViewHelper;
import id.ihsan.popmovie.models.Movie;
import id.ihsan.popmovie.models.MovieDetail;
import id.ihsan.popmovie.models.Video;
import id.ihsan.popmovie.models.Videos;
import id.ihsan.popmovie.networks.RestClient;
import id.ihsan.popmovie.utils.Constans;
import id.ihsan.popmovie.widgets.MovieImageView;
import id.ihsan.popmovie.widgets.MovieTitleImageView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * @author Ihsan Helmi Faisal <ihsan.helmi@ovo.id>
 * @since 2017.10.07
 */
public class MovieDetailActivity extends AppBaseActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private MovieImageView imgMovie;
    private ImageView imgFavorite;
    private MovieTitleImageView imgMovieTitle;
    private TextView txtTitle, txtRelease, txtTime, txtRating, txtOverview;
    private RecyclerView listTrailers;

    private TrailersAdapter adapter;
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
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        imgMovieTitle = (MovieTitleImageView) findViewById(R.id.img_title);
        imgFavorite = (ImageView) findViewById(R.id.img_star);
        imgMovie = (MovieImageView) findViewById(R.id.img_movie);
        txtTitle = (TextView) findViewById(R.id.txt_title);
        txtRelease = (TextView) findViewById(R.id.txt_release_year);
        txtTime = (TextView) findViewById(R.id.txt_time);
        txtRating = (TextView) findViewById(R.id.txt_rating);
        txtOverview = (TextView) findViewById(R.id.txt_overview);
        listTrailers = (RecyclerView) findViewById(R.id.list_trailers);
    }

    @Override
    public void initViews() {
        setupToolbar(toolbar, getString(R.string.movie_detail_title));
        collapsingToolbarLayout.setTitle(getString(R.string.movie_detail_title));
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));

        adapter = new TrailersAdapter();

        LinearLayoutManager manager = new LinearLayoutManager(this);
        listTrailers.setLayoutManager(manager);
        listTrailers.setAdapter(adapter);

        if (movie != null) {
            String urlImage = Constans.BASE_IMAGE_URL + "w342" + movie.getPosterPath();
            String urlImage2 = Constans.BASE_IMAGE_URL + "w500" + movie.getBackdropPath();
            Picasso.with(this).load(urlImage).into(imgMovie);
            Picasso.with(this).load(urlImage2).into(imgMovieTitle);

            imgFavorite.setOnClickListener(this);
            setFavoriteStar(isFavoriteMovie(String.valueOf(movie.getId())));

            txtTitle.setText(movie.getTitle());
            txtRelease.setText(Formatter.parseYear(movie.getReleaseDate()));
            txtRating.setText(String.format(Locale.US, "%s / 10", Formatter.formatDoubleToString(movie.getVoteAverage())));
            txtOverview.setText(movie.getOverview());
        }
    }

    @Override
    public void initListeners() {
        ItemClickSupport.addTo(listTrailers).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Video video = adapter.getVideo(position);
                if (video != null) {
                    String url = "https://www.youtube.com/watch?v=" + video.getKey();
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
            }
        });
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

    @Override
    public void onClick(View view) {
        if (view == imgFavorite) {
            boolean isFavoriteMovie = isFavoriteMovie(String.valueOf(movie.getId()));
            if (!isFavoriteMovie) {
                setFavoriteStar(true);

                ContentValues contentValues = new ContentValues();
                contentValues.put(MovieContract.MovieEntry._ID, movie.getId());
                contentValues.put(MovieContract.MovieEntry.COLUMN_MOVIE, new Gson().toJson(movie));

                getContentResolver().insert(MovieContract.MovieEntry.CONTENT_URI, contentValues);
            } else {
                setFavoriteStar(false);

                getContentResolver().delete(MovieContract.MovieEntry.CONTENT_URI, "_id=?", new String[]{String.valueOf(movie.getId())});
            }
        }
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

    private boolean isFavoriteMovie(String id) {
        Cursor c = getContentResolver().query(MovieContract.MovieEntry.CONTENT_URI, new String[]{MovieContract.MovieEntry._ID, MovieContract.MovieEntry.COLUMN_MOVIE},
                "_id=?", new String[]{id}, null);
        return (c != null ? c.getCount() : 0) > 0;
    }

    private void setFavoriteStar(boolean isFavoriteMovie) {
        if (isFavoriteMovie)
            imgFavorite.setImageResource(android.R.drawable.star_big_on);
        else
            imgFavorite.setImageResource(android.R.drawable.star_big_off);
    }

    private void requestMovieDetail(long movieId) {
        showDialog();
        RestClient.ApiService apiService = RestClient.getClient();
        Observable<MovieDetail> call = apiService.getMovieDetail(movieId, "videos");

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
                            Videos videos = response.getmVideos();
                            if (videos != null)
                                adapter.setMovies(videos.getmVideos());
                        }
                    }
                });
    }
}
