package in.co.halexo.angry.righttobeauty.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ParlorServiceDao {
    @Insert
    public void insertParlorService(ParlorService parlorService);

    @Query("SELECT service_category_id FROM parlor_service WHERE parlor_id=:parlor_id")
    public LiveData<List<Integer>>getParlorService(Integer parlor_id);
}
