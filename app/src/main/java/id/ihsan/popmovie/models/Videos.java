package id.ihsan.popmovie.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ihsan Helmi Faisal
 * on 8/6/2017.
 */

public class Videos implements Parcelable {

    @SerializedName("results")
    private List<Video> mVideos;

    public List<Video> getmVideos() {
        return mVideos;
    }

    public void setmVideos(List<Video> mVideos) {
        this.mVideos = mVideos;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.mVideos);
    }

    public Videos() {
    }

    protected Videos(Parcel in) {
        this.mVideos = in.createTypedArrayList(Video.CREATOR);
    }

    public static final Parcelable.Creator<Videos> CREATOR = new Parcelable.Creator<Videos>() {
        @Override
        public Videos createFromParcel(Parcel source) {
            return new Videos(source);
        }

        @Override
        public Videos[] newArray(int size) {
            return new Videos[size];
        }
    };
}
