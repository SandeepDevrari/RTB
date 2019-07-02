package in.co.halexo.angry.righttobeauty.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.support.annotation.NonNull;

import java.util.List;

@Dao
public interface ParlorInfrastructureDao {

    @Insert
    public long insertParlorInfrastructure(ParlorInfrastructure parlorInfrastructure);

    @Query("SELECT * FROM infrastructure WHERE parlor_id=:parlorId")
    public LiveData<List<ParlorInfrastructure>> getParlorInfrastructureByParlorId(@NonNull int parlorId);

    @Query("SELECT COUNT(*) FROM infrastructure WHERE parlor_id=:parlorId")
    public int isParlorInfrastructureExist(@NonNull int parlorId);
}
