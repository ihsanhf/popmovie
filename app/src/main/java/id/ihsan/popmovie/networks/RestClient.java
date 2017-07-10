package id.ihsan.popmovie.networks;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import id.ihsan.popmovie.MovieApplication;
import id.ihsan.popmovie.R;
import id.ihsan.popmovie.models.MovieDetail;
import id.ihsan.popmovie.models.Movies;
import id.ihsan.popmovie.utils.Constans;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.HttpException;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by Ihsan Helmi Faisal
 * on 7/10/2017.
 */

public class RestClient {

    private static final String TAG = RestClient.class.getSimpleName();

    public static ApiService getClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        HttpUrl url = request.url().newBuilder().addQueryParameter("api_key", MovieApplication.getInstance().apiKey()).build();
                        request = request.newBuilder().url(url).build();
                        return chain.proceed(request);
                    }
                })
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS).build();

        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());

        Retrofit client = new Retrofit.Builder()
                .baseUrl(Constans.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(rxAdapter)
                .build();
        return client.create(ApiService.class);
    }

    public static String getErrorFail(Context context, Throwable t) {
        String error;
        if (!isInternetConnected(context)) {
            error = context.getString(R.string.no_internet_connection);
        } else if (t instanceof HttpException) {
            HttpException response = (HttpException) t;
            Log.d(TAG, "onError : " + response.code() + " : " + response.message());
            error = response.message();
        } else {
            error = context.getString(R.string.internal_server_error);
            try {
                error = error + " : " + t.getMessage();
            } catch (NullPointerException ignored) {
            }
        }
        return error;
    }

    private static boolean isInternetConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }

    public interface ApiService {

        @GET(Constans.ApiUrl.MOVIE_POPULAR)
        Observable<Movies> getPopularMovie();

        @GET(Constans.ApiUrl.MOVIE_TOP)
        Observable<Movies> getTopRatedMovie();

        @GET(Constans.ApiUrl.MOVIE_DETAIL)
        Observable<MovieDetail> getMovieDetail(@Path("movie_id") long movieId);
    }
}
