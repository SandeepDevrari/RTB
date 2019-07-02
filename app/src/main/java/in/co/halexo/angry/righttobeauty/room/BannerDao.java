package in.co.halexo.angry.righttobeauty.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface BannerDao {
    @Query("SELECT * FROM Banner")
    LiveData<List<Banner>> getAllBanner();

    @Query("SELECT * FROM Banner")
    List<Banner> getAllBannerList();

    @Insert
    long insertBanners(Banner banners);

    @Update
    void updateBanner(Banner banner);

    @Query("SELECT COUNT(*) FROM Banner WHERE image=:im")
    int isBannerExist(String im);

    @Query("SELECT local_path FROM Banner WHERE image=:im ")
    String getImageLocalPath(String im);
}
