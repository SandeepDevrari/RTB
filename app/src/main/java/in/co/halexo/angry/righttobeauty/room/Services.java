package in.co.halexo.angry.righttobeauty.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "services")
public class Services {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Integer id;

    @NonNull
    @ColumnInfo(name = "parlor_id")
    private Integer parlorId;

    @NonNull
    @ColumnInfo(name = "service_category_id")
    private Integer serviceCategoryId;

    @Ignore
    public Services(@NonNull Integer parlorId, @NonNull Integer serviceCategoryId) {
        this.id=0;
        this.parlorId = parlorId;
        this.serviceCategoryId = serviceCategoryId;
    }

    public Services(@NonNull Integer id, @NonNull Integer parlorId, @NonNull Integer serviceCategoryId){
        this.id=0;
        this.parlorId = parlorId;
        this.serviceCategoryId = serviceCategoryId;
    }

    @NonNull
    public Integer getParlorId() {
        return parlorId;
    }

    public void setParlorId(@NonNull Integer parlorId) {
        this.parlorId = parlorId;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    @NonNull
    public Integer getServiceCategoryId() {
        return serviceCategoryId;
    }

    public void setServiceCategoryId(@NonNull Integer serviceCategoryId) {
        this.serviceCategoryId = serviceCategoryId;
    }
}
