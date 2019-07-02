package in.co.halexo.angry.righttobeauty.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import android.support.annotation.NonNull;

import java.util.List;

@Dao
public interface ServicesDao {
    @Query("SELECT * FROM Services WHERE parlor_id=:parlor_id")
    public LiveData<List<Services>>getAllServicesByParlorId(@NonNull Integer parlor_id);
}
