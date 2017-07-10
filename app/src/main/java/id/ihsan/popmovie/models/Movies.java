
package id.ihsan.popmovie.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class Movies implements Parcelable {

    @SerializedName("page")
    private Long mPage;
    @SerializedName("results")
    private List<Movie> mMovies;
    @SerializedName("total_pages")
    private Long mTotalPages;
    @SerializedName("total_results")
    private Long mTotalResults;

    public Long getPage() {
        return mPage;
    }

    public void setPage(Long page) {
        mPage = page;
    }

    public List<Movie> getResults() {
        return mMovies;
    }

    public void setResults(List<Movie> movies) {
        mMovies = movies;
    }

    public Long getTotalPages() {
        return mTotalPages;
    }

    public void setTotalPages(Long totalPages) {
        mTotalPages = totalPages;
    }

    public Long getTotalResults() {
        return mTotalResults;
    }

    public void setTotalResults(Long totalResults) {
        mTotalResults = totalResults;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.mPage);
        dest.writeTypedList(this.mMovies);
        dest.writeValue(this.mTotalPages);
        dest.writeValue(this.mTotalResults);
    }

    public Movies() {
    }

    protected Movies(Parcel in) {
        this.mPage = (Long) in.readValue(Long.class.getClassLoader());
        this.mMovies = in.createTypedArrayList(Movie.CREATOR);
        this.mTotalPages = (Long) in.readValue(Long.class.getClassLoader());
        this.mTotalResults = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Parcelable.Creator<Movies> CREATOR = new Parcelable.Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel source) {
            return new Movies(source);
        }

        @Override
        public Movies[] newArray(int size) {
            return new Movies[size];
        }
    };
}
