package id.ihsan.popmovie.utils;

/**
 * @author Ihsan Helmi Faisal <ihsan.helmi@ovo.id>
 * @since 2017.10.07
 */
public class Constans {

    public static final String API_KEY = "MOVIE-DB-API-KEY";
    public static final String BASE_URL = "http://api.themoviedb.org/3/";
    public static final String BASE_IMAGE_URL = "http://image.tmdb.org/t/p/";

    public static class ApiUrl {
        static final String MOVIE = "movie/";
        public final static String MOVIE_POPULAR = MOVIE + "popular";
        public final static String MOVIE_TOP = MOVIE + "top_rated";
        public final static String MOVIE_DETAIL = MOVIE + "{movie_id}";
    }
}
