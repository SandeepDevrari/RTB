package in.co.halexo.angry.righttobeauty.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.support.annotation.NonNull;

import java.util.List;

@Dao
public interface ParlorDao {
    @Query("SELECT * FROM parlor")
    public LiveData<List<Parlor>>getAllParlors();

    @Query("SELECT * FROM parlor WHERE id=:id LIMIT 1")
    public Parlor getParlorById(@NonNull Integer id);

    @Insert
    public long insertParlor(Parlor parlor);

    @Query("SELECT COUNT(*) FROM parlor WHERE parlor_name=:parlorName")
    public int isParlorExist(String parlorName);
}
