package in.co.halexo.angry.righttobeauty.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "parlor")
public class Parlor implements Serializable {
    @NonNull
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private Integer id=0;

    @SerializedName("parlor_image_base_url")
    @NonNull
    @ColumnInfo(name="base_url")
    private String imageBaseUrl;

    @SerializedName("owner_name")
    @NonNull
    @ColumnInfo(name="owner_name")
    private String ownerName;

    @SerializedName("contact_number")
    @NonNull
    @ColumnInfo(name="owner_contact")
    private String ownerContact;

    @SerializedName("email")
    @NonNull
    @ColumnInfo(name="owner_email")
    private String ownerEmail;

    @SerializedName("parlor_name")
    @NonNull
    @ColumnInfo(name="parlor_name")
    private String parlorName;

    @SerializedName("parlor_image")
    @NonNull
    @ColumnInfo(name="image")
    private String parlorImage;

    @SerializedName("services")
    @Ignore
    @NonNull
    private List<ServiceCategory> parlorServices;

    @SerializedName("infrastructure_details")
    @Ignore
    @NonNull
    private List<ParlorInfrastructure> parlorInfrastructures;

    @NonNull
    @ColumnInfo(name="state")
    private String state;

    @NonNull
    @ColumnInfo(name="city")
    private String city;

    @NonNull
    @ColumnInfo(name="area")
    private String area;

    @ColumnInfo(name = "local_path")
    private String localPath;

    @Ignore
    public Parlor(@NonNull String imageBaseUrl, @NonNull String ownerName, @NonNull String ownerContact,
                  @NonNull String ownerEmail, @NonNull String parlorName, @NonNull String parlorImage,
                  @NonNull List<ServiceCategory> parlorServices, @NonNull List<ParlorInfrastructure> parlorInfrastructures) {
        //this.id = id;
        this.imageBaseUrl = imageBaseUrl;
        this.ownerName = ownerName;
        this.ownerContact = ownerContact;
        this.ownerEmail = ownerEmail;
        this.parlorName = parlorName;
        this.parlorImage = parlorImage;
        this.parlorServices = parlorServices;
        this.parlorInfrastructures = parlorInfrastructures;
        //this.state="";
        //this.city="";
        //this.area="";
    }


    public Parlor(@NonNull String imageBaseUrl, @NonNull String ownerName, @NonNull String ownerContact,
                  @NonNull String ownerEmail, @NonNull String parlorName, @NonNull String parlorImage, @NonNull String state,
                  @NonNull String city, @NonNull String area, String localPath) {
        this.imageBaseUrl = imageBaseUrl;
        this.ownerName = ownerName;
        this.ownerContact = ownerContact;
        this.ownerEmail = ownerEmail;
        this.parlorName = parlorName;
        this.parlorImage = parlorImage;
        this.state=state;
        this.city=city;
        this.area=area;
        this.localPath = localPath;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    @NonNull
    public String getImageBaseUrl() {
        return imageBaseUrl;
    }

    public void setImageBaseUrl(@NonNull String imageBaseUrl) {
        this.imageBaseUrl = imageBaseUrl;
    }

    @NonNull
    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(@NonNull String ownerName) {
        this.ownerName = ownerName;
    }

    @NonNull
    public String getOwnerContact() {
        return ownerContact;
    }

    public void setOwnerContact(@NonNull String ownerContact) {
        this.ownerContact = ownerContact;
    }

    @NonNull
    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(@NonNull String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    @NonNull
    public String getParlorName() {
        return parlorName;
    }

    public void setParlorName(@NonNull String parlorName) {
        this.parlorName = parlorName;
    }

    @NonNull
    public String getParlorImage() {
        return parlorImage;
    }

    public void setParlorImage(@NonNull String parlorImage) {
        this.parlorImage = parlorImage;
    }

    @NonNull
    public List<ServiceCategory> getParlorServices() {
        return parlorServices;
    }

    public void setParlorServices(@NonNull List<ServiceCategory> parlorServices) {
        this.parlorServices = parlorServices;
    }

    @NonNull
    public List<ParlorInfrastructure> getParlorInfrastructures() {
        return parlorInfrastructures;
    }

    public void setParlorInfrastructures(@NonNull List<ParlorInfrastructure> parlorInfrastructures) {
        this.parlorInfrastructures = parlorInfrastructures;
    }

    @NonNull
    public String getState() {
        return state;
    }

    public void setState(@NonNull String state) {
        this.state = state;
    }

    @NonNull
    public String getCity() {
        return city;
    }

    public void setCity(@NonNull String city) {
        this.city = city;
    }

    @NonNull
    public String getArea() {
        return area;
    }

    public void setArea(@NonNull String area) {
        this.area = area;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }
}
