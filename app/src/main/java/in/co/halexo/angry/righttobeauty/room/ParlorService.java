package in.co.halexo.angry.righttobeauty.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "parlor_service",foreignKeys = {@ForeignKey(entity = Parlor.class,parentColumns = "id",
        childColumns = "parlor_id",onDelete = ForeignKey.CASCADE),
@ForeignKey(entity = ServiceCategory.class,parentColumns = "category_id",
        childColumns = "service_category_id",onDelete = ForeignKey.CASCADE)},indices = {@Index("parlor_id"),@Index("service_category_id")})
public class ParlorService {
    @ColumnInfo(name = "id")
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @NonNull
    @ColumnInfo(name = "parlor_id")
    private Integer parlorId;

    @NonNull
    @ColumnInfo(name = "service_category_id")
    private Integer serviceCategoryId;

    public ParlorService(@NonNull Integer parlorId, @NonNull Integer serviceCategoryId) {
        //this.id = id;
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
    public Integer getServiceCategoryId() {
        return serviceCategoryId;
    }

    public void setServiceCategoryId(@NonNull Integer serviceCategoryId) {
        this.serviceCategoryId = serviceCategoryId;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }
}
