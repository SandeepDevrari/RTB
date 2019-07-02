package in.co.halexo.angry.righttobeauty.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "Banner")
public class Banner implements Serializable{
    @SerializedName("base_url")
    @NonNull
    @ColumnInfo(name="baseUrl")
    private String baseUrl;

    @SerializedName("image")
    @NonNull
    @ColumnInfo(name = "image")
    @PrimaryKey
    private String image;


    @ColumnInfo(name = "local_path")
    private String localPath;

    public Banner(@NonNull String baseUrl, @NonNull String image,@NonNull String localPath) {
        this.baseUrl = baseUrl;
        this.image = image;
        this.localPath=localPath;
    }

    @Ignore
    public Banner(@NonNull String baseUrl, @NonNull String image) {
        this.baseUrl = baseUrl;
        this.image = image;
    }

    @NonNull
    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(@NonNull String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @NonNull
    public String getImage() {
        return image;
    }

    public void setImage(@NonNull String image) {
        this.image = image;
    }

    @NonNull
    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(@NonNull String localPath) {
        this.localPath = localPath;
    }
}
