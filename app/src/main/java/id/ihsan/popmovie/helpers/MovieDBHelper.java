package id.ihsan.popmovie.helpers;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Ihsan Helmi Faisal
 * on 8/7/2017.
 */

public class MovieDBHelper extends SQLiteOpenHelper {

    public static final String TAG = MovieDBHelper.class.getSimpleName();

    //name & version
    private static final String DATABASE_NAME = "PopMovie.db";
    private static final int DATABASE_VERSION = 1;

    public MovieDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " +
                MovieContract.FlavorEntry.TABLE_FLAVORS + "(" + MovieContract.FlavorEntry._ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MovieContract.FlavorEntry.COLUMN_VERSION_NAME + " TEXT NOT NULL, " +
                MovieContract.FlavorEntry.COLUMN_DESCRIPTION +
                " TEXT NOT NULL, " +
                MovieContract.FlavorEntry.COLUMN_ICON +
                " INTEGER NOT NULL);";

        sqLiteDatabase.execSQL(SQL_CREATE_MOVIE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgrading database from version " + oldVersion + " to " +
                newVersion + ". OLD DATA WILL BE DESTROYED");
        // Drop the table
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MovieContract.FlavorEntry.TABLE_FLAVORS);
        sqLiteDatabase.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" +
                MovieContract.FlavorEntry.TABLE_FLAVORS + "'");

        // re-create database
        onCreate(sqLiteDatabase);
    }
}
