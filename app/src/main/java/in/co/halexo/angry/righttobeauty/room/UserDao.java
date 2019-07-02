package in.co.halexo.angry.righttobeauty.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.support.annotation.NonNull;

@Dao
public interface UserDao {
    @Insert
    public long insertUser(@NonNull User user);

    @Delete
    public int deleteUser(@NonNull User user);

    @Update
    public void updateUser(@NonNull User user);

    @Query("Select * FROM user WHERE id= :id LIMIT 1")
    public User getUserById(@NonNull Integer id);
}
