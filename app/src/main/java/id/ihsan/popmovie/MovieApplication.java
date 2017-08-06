package id.ihsan.popmovie;

import android.app.Application;

import com.facebook.stetho.Stetho;

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

        initStetho();
    }

    public static synchronized MovieApplication getInstance() {
        return instance;
    }

    private void initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(
                                Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(
                                Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
}
