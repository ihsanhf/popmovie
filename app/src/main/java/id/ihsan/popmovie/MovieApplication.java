package id.ihsan.popmovie;

import android.app.Application;

/**
 * @author Ihsan Helmi Faisal <ihsan.helmi@ovo.id>
 * @since 2017.10.07
 */
public class MovieApplication extends Application {

    private static MovieApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static synchronized MovieApplication getInstance() {
        return instance;
    }
}
