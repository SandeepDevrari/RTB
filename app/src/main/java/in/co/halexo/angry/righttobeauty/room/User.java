package in.co.halexo.angry.righttobeauty.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "user")
public class User implements Serializable{
    @NonNull
    @ColumnInfo(name = "name")
    @SerializedName("name")
    private String name;

    @NonNull
    @ColumnInfo(name = "email")
    @SerializedName("email")
    private String email;

    @NonNull
    @ColumnInfo(name = "phone_number")
    @SerializedName("phone")
    private String phone;

    @NonNull
    @ColumnInfo(name = "address")
    @SerializedName("address")
    private String address;

    @NonNull
    @ColumnInfo(name = "id")
    @SerializedName("user_id")
    @PrimaryKey
    private String id;

    @NonNull
    @ColumnInfo(name = "password")
    @SerializedName("password")
    private String password;

    @NonNull
    @ColumnInfo(name = "card_number")
    @SerializedName("card_no")
    private String cardNumber;

    public User(@NonNull String name, @NonNull String email,
                @NonNull String phone, @NonNull String address,@NonNull String id, @NonNull String password, @NonNull String cardNumber) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.id = id;
        this.password = password;
        this.cardNumber = cardNumber;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    @NonNull
    public String getPhone() {
        return phone;
    }

    public void setPhone(@NonNull String phone) {
        this.phone = phone;
    }

    @NonNull
    public String getAddress() {
        return address;
    }

    public void setAddress(@NonNull String address) {
        this.address = address;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    @NonNull
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(@NonNull String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
