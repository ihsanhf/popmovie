package id.ihsan.popmovie;

import android.app.Application;

/**
 * Created by Ihsan Helmi Faisal
 * on 7/6/2017.
 */

public class MovieApplication extends Application {

    private static MovieApplication instance;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static synchronized MovieApplication getInstance() {
        return instance;
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String apiKey();
}
