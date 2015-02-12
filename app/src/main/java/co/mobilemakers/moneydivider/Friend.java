package co.mobilemakers.moneydivider;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Gonzalo on 11/02/2015.
 */
public class Friend implements Parcelable{

    public final static String FRIEND_NAME = "friend_name";
    public final static String FRIEND_MONEY = "friend_money";
    public final static String FRIEND_PARCELABLE_ARRAY = "parcelable_array";

    private String name;
    private float money;

    public Friend(String name, float money) {
        this.name = name;
        this.money = money;
    }

    public Friend(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeFloat(money);
    }

    public static final Creator<Friend> CREATOR = new Creator<Friend>(){

        @Override
        public Friend createFromParcel(Parcel source) {
            return new Friend(source);
        }

        @Override
        public Friend[] newArray(int size) {
            return new Friend[size];
        }
    };

    private Friend (Parcel source){
        name = source.readString();
        money = source.readFloat();
    }
}
