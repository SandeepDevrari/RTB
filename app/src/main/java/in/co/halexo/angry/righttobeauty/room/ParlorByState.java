package in.co.halexo.angry.righttobeauty.room;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ParlorByState implements Serializable {
    @SerializedName("state")
    private String state;
    @SerializedName("all_city")
    private List<ParlorByCity> parlorByCity;

    public ParlorByState(String state, List<ParlorByCity> parlorByCity) {
        this.state = state;
        this.parlorByCity = parlorByCity;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<ParlorByCity> getParlorByCity() {
        return parlorByCity;
    }

    public void setParlorByCity(List<ParlorByCity> parlorByCity) {
        this.parlorByCity = parlorByCity;
    }
}
