package in.co.halexo.angry.righttobeauty.room;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ParlorByArea implements Serializable {
    @SerializedName("area")
    private String area;
    @SerializedName("parlor")
    private Parlor parlor;

    public ParlorByArea(String area, Parlor parlor) {
        this.area = area;
        this.parlor = parlor;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Parlor getParlor() {
        return parlor;
    }

    public void setParlor(Parlor parlor) {
        this.parlor = parlor;
    }
}
