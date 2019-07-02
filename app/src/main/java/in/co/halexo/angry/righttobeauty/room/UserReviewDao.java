package in.co.halexo.angry.righttobeauty.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface UserReviewDao {
    @Query("SELECT * FROM user_review")
    public LiveData<List<UserReview>>getAllUsersReview();

    @Insert
    public long insertUserReview(UserReview userReview);

    @Update
    public void updateUserReview(UserReview userReview);

    @Query("SELECT COUNT(*) FROM user_review WHERE user_name=:name")
    public int isUserReviewExist(String name);
}
