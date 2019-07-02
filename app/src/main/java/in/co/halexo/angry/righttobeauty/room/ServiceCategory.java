package in.co.halexo.angry.righttobeauty.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "service_category")
public class ServiceCategory implements Serializable {
    @SerializedName("image_base_url")
    @NonNull
    @ColumnInfo(name = "base_url")
    private String baseUrl;

    //@SerializedName("cat_id")
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "category_id")
    private Integer categoryId=0;

    @SerializedName("s_cat")
    @NonNull
    @ColumnInfo(name = "category_heading")
    private String categoryHeading;

    @SerializedName("img")
    @NonNull
    @ColumnInfo(name="image")
    private String imageUrl;

    @NonNull
    @ColumnInfo(name = "local_path")
    private String localPath;

    @SerializedName("des")
    @NonNull
    @ColumnInfo(name = "category_description")
    private String categoryDescription;

    public ServiceCategory(@NonNull String baseUrl, @NonNull String categoryHeading, @NonNull String imageUrl, @NonNull String localPath, @NonNull String categoryDescription) {
        this.baseUrl = baseUrl;
        //this.categoryId = categoryId;
        this.categoryHeading = categoryHeading;
        this.imageUrl = imageUrl;
        this.localPath = localPath;
        this.categoryDescription = categoryDescription;
    }

    @NonNull
    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(@NonNull String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @NonNull
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(@NonNull Integer categoryId) {
        this.categoryId = categoryId;
    }

    @NonNull
    public String getCategoryHeading() {
        return categoryHeading;
    }

    public void setCategoryHeading(@NonNull String categoryHeading) {
        this.categoryHeading = categoryHeading;
    }

    @NonNull
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(@NonNull String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @NonNull
    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(@NonNull String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    @NonNull
    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(@NonNull String localPath) {
        this.localPath = localPath;
    }
}
