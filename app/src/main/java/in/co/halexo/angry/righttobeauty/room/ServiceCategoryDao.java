package in.co.halexo.angry.righttobeauty.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.support.annotation.NonNull;

import java.util.List;

@Dao
public interface ServiceCategoryDao {
    @Query("SELECT * FROM service_category")
    public LiveData<List<ServiceCategory>>getAllServicesCategories();

    @Insert
    public long insertServiceCategory(ServiceCategory serviceCategory);

    @Update
    void updateServiceCategory(ServiceCategory serviceCategory);

    @Query("SELECT COUNT(*) FROM service_category WHERE image=:im")
    int isServiceCategoryExist(String im);

    @Query("SELECT * FROM service_category WHERE category_heading=:headingName")
    public ServiceCategory getServiceCategoryByName(@NonNull String headingName);

    @Query("SELECT * FROM service_category WHERE category_id=:serviceCategoryId")
    public ServiceCategory getServiceCategoryById(@NonNull Integer serviceCategoryId);
}
