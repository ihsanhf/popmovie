package id.ihsan.popmovie.utils;

/**
 * Created by Ihsan Helmi Faisal
 * on 7/10/2017.
 */

public class Constans {

    public static final String BASE_URL = url();
    public static final String BASE_IMAGE_URL = urlImage();

    static {
        System.loadLibrary("native-lib");
    }

    public static class ApiUrl {
        static final String MOVIE = "movie/";
        public final static String MOVIE_POPULAR = MOVIE + "popular";
        public final static String MOVIE_TOP = MOVIE + "top_rated";
        public final static String MOVIE_DETAIL = MOVIE + "{movie_id}";
    }

    private static native String url();

    private static native String urlImage();
}
