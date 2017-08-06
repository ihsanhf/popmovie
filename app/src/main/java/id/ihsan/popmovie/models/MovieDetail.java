package id.ihsan.popmovie.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieDetail implements Parcelable {

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("imdb_id")
    private String imdbId;

    @SerializedName("video")
    private boolean video;

    @SerializedName("title")
    private String title;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("revenue")
    private int revenue;

    @SerializedName("genres")
    private List<Genre> genres;

    @SerializedName("popularity")
    private double popularity;

    @SerializedName("production_countries")
    private List<ProductionCountry> productionCountries;

    @SerializedName("id")
    private int id;

    @SerializedName("vote_count")
    private int voteCount;

    @SerializedName("budget")
    private int budget;

    @SerializedName("overview")
    private String overview;

    @SerializedName("original_title")
    private String originalTitle;

    @SerializedName("runtime")
    private int runtime;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("spoken_languages")
    private List<SpokenLanguage> spokenLanguages;

    @SerializedName("production_companies")
    private List<ProductionCompany> productionCompanies;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("vote_average")
    private double voteAverage;

    @SerializedName("belongs_to_collection")
    private Object belongsToCollection;

    @SerializedName("tagline")
    private String tagline;

    @SerializedName("adult")
    private boolean adult;

    @SerializedName("homepage")
    private String homepage;

    @SerializedName("status")
    private String status;

    @SerializedName("videos")
    private Videos mVideos;

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public boolean isVideo() {
        return video;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setProductionCountries(List<ProductionCountry> productionCountries) {
        this.productionCountries = productionCountries;
    }

    public List<ProductionCountry> getProductionCountries() {
        return productionCountries;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getBudget() {
        return budget;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOverview() {
        return overview;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setSpokenLanguages(List<SpokenLanguage> spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
    }

    public List<SpokenLanguage> getSpokenLanguages() {
        return spokenLanguages;
    }

    public void setProductionCompanies(List<ProductionCompany> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public List<ProductionCompany> getProductionCompanies() {
        return productionCompanies;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setBelongsToCollection(Object belongsToCollection) {
        this.belongsToCollection = belongsToCollection;
    }

    public Object getBelongsToCollection() {
        return belongsToCollection;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getTagline() {
        return tagline;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public Videos getmVideos() {
        return mVideos;
    }

    public void setmVideos(Videos mVideos) {
        this.mVideos = mVideos;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.originalLanguage);
        dest.writeString(this.imdbId);
        dest.writeByte(this.video ? (byte) 1 : (byte) 0);
        dest.writeString(this.title);
        dest.writeString(this.backdropPath);
        dest.writeInt(this.revenue);
        dest.writeTypedList(this.genres);
        dest.writeDouble(this.popularity);
        dest.writeTypedList(this.productionCountries);
        dest.writeInt(this.id);
        dest.writeInt(this.voteCount);
        dest.writeInt(this.budget);
        dest.writeString(this.overview);
        dest.writeString(this.originalTitle);
        dest.writeInt(this.runtime);
        dest.writeString(this.posterPath);
        dest.writeTypedList(this.spokenLanguages);
        dest.writeTypedList(this.productionCompanies);
        dest.writeString(this.releaseDate);
        dest.writeDouble(this.voteAverage);
        dest.writeValue(this.belongsToCollection);
        dest.writeString(this.tagline);
        dest.writeByte(this.adult ? (byte) 1 : (byte) 0);
        dest.writeString(this.homepage);
        dest.writeString(this.status);
        dest.writeValue(this.mVideos);
    }

    public MovieDetail() {
    }

    protected MovieDetail(Parcel in) {
        this.originalLanguage = in.readString();
        this.imdbId = in.readString();
        this.video = in.readByte() != 0;
        this.title = in.readString();
        this.backdropPath = in.readString();
        this.revenue = in.readInt();
        this.genres = in.createTypedArrayList(Genre.CREATOR);
        this.popularity = in.readDouble();
        this.productionCountries = in.createTypedArrayList(ProductionCountry.CREATOR);
        this.id = in.readInt();
        this.voteCount = in.readInt();
        this.budget = in.readInt();
        this.overview = in.readString();
        this.originalTitle = in.readString();
        this.runtime = in.readInt();
        this.posterPath = in.readString();
        this.spokenLanguages = in.createTypedArrayList(SpokenLanguage.CREATOR);
        this.productionCompanies = in.createTypedArrayList(ProductionCompany.CREATOR);
        this.releaseDate = in.readString();
        this.voteAverage = in.readDouble();
        this.belongsToCollection = in.readValue(Object.class.getClassLoader());
        this.tagline = in.readString();
        this.adult = in.readByte() != 0;
        this.homepage = in.readString();
        this.status = in.readString();
        this.mVideos = (Videos) in.readValue(Videos.class.getClassLoader());
    }

    public static final Parcelable.Creator<MovieDetail> CREATOR = new Parcelable.Creator<MovieDetail>() {
        @Override
        public MovieDetail createFromParcel(Parcel source) {
            return new MovieDetail(source);
        }

        @Override
        public MovieDetail[] newArray(int size) {
            return new MovieDetail[size];
        }
    };
}