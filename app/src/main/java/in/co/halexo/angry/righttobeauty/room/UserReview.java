package in.co.halexo.angry.righttobeauty.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "user_review")
public class UserReview implements Serializable {
    @SerializedName("pera")
    @ColumnInfo(name="review")
    @NonNull
    private String userReview;

    @ColumnInfo(name="user_name")
    @SerializedName("name")
    @NonNull
    @PrimaryKey
    private String userName;

    public UserReview(@NonNull String userReview, @NonNull String userName) {
        this.userReview = userReview;
        this.userName = userName;
    }

    @NonNull
    public String getUserReview() {
        return userReview;
    }

    public void setUserReview(@NonNull String userReview) {
        this.userReview = userReview;
    }

    @NonNull
    public String getUserName() {
        return userName;
    }

    public void setUserName(@NonNull String userName) {
        this.userName = userName;
    }
}
