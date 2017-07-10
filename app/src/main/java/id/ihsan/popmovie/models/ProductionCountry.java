package id.ihsan.popmovie.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ProductionCountry implements Parcelable {

    @SerializedName("iso_3166_1")
    private String iso31661;

    @SerializedName("name")
    private String name;

    public void setIso31661(String iso31661) {
        this.iso31661 = iso31661;
    }

    public String getIso31661() {
        return iso31661;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.iso31661);
        dest.writeString(this.name);
    }

    public ProductionCountry() {
    }

    protected ProductionCountry(Parcel in) {
        this.iso31661 = in.readString();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<ProductionCountry> CREATOR = new Parcelable.Creator<ProductionCountry>() {
        @Override
        public ProductionCountry createFromParcel(Parcel source) {
            return new ProductionCountry(source);
        }

        @Override
        public ProductionCountry[] newArray(int size) {
            return new ProductionCountry[size];
        }
    };
}