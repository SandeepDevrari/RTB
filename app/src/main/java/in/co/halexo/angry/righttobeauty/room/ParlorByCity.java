package in.co.halexo.angry.righttobeauty.room;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ParlorByCity implements Serializable {
    @SerializedName("city")
    private String city;
    @SerializedName("all_areas")
    private List<ParlorByArea> parlorByArea;

    public ParlorByCity(String city, List<ParlorByArea> parlorByArea) {
        this.city = city;
        this.parlorByArea = parlorByArea;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<ParlorByArea> getParlorByArea() {
        return parlorByArea;
    }

    public void setParlorByArea(List<ParlorByArea> parlorByArea) {
        this.parlorByArea = parlorByArea;
    }
}
