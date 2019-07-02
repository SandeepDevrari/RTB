package in.co.halexo.angry.righttobeauty.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "infrastructure",foreignKeys = @ForeignKey(entity = Parlor.class,
        parentColumns ="id",childColumns = "parlor_id",onDelete = ForeignKey.CASCADE),indices = {@Index("parlor_id")})
public class ParlorInfrastructure implements Serializable {
    @NonNull
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @NonNull
    @ColumnInfo(name = "parlor_id")
    private Integer parlorId;

    @NonNull
    @ColumnInfo(name = "item_name")
    @SerializedName("item_name")
    private String itemName;

    @NonNull
    @ColumnInfo(name = "item_quantity")
    @SerializedName("item_quantity")
    private String itemQuantity;

    public ParlorInfrastructure(@NonNull Integer parlorId, @NonNull String itemName, @NonNull String itemQuantity) {
        this.parlorId = parlorId;
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
    }

    @Ignore
    public ParlorInfrastructure(@NonNull String itemName, @NonNull String itemQuantity) {
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
    }

    @NonNull
    public Integer getParlorId() {
        return parlorId;
    }

    public void setParlorId(@NonNull Integer parlorId) {
        this.parlorId = parlorId;
    }

    @NonNull
    public String getItemName() {
        return itemName;
    }

    public void setItemName(@NonNull String itemName) {
        this.itemName = itemName;
    }

    @NonNull
    public String getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(@NonNull String itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

//    @NonNull
//    @ColumnInfo(name = "ac")
//    @SerializedName("ac")
//    private String ac;
//
//    @NonNull
//    @ColumnInfo(name = "shampoo_station")
//    @SerializedName("shampoo_station")
//    private String shampooStation;
//
//    @NonNull
//    @ColumnInfo(name = "chair")
//    @SerializedName("chair")
//    private String chair;
//
//    @NonNull
//    @ColumnInfo(name = "padicure_manicure")
//    @SerializedName("padicure_manicure")
//    private String padicureManicure;
//
//    @NonNull
//    @ColumnInfo(name = "fecial_bed")
//    @SerializedName("facial_bed")
//    private String facialBed;
//
//    @NonNull
//    @ColumnInfo(name = "employees")
//    @SerializedName("employees")
//    private String employees;
//
//    @NonNull
//    @ColumnInfo(name = "capacity_day")
//    @SerializedName("capacity_day")
//    private String capacityPerDay;
//
//    @NonNull
//    @ColumnInfo(name = "capacity_time")
//    @SerializedName("capacity_time")
//    private String capacityPerTime;

}
