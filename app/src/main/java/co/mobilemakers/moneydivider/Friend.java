package co.mobilemakers.moneydivider;

/**
 * Created by Gonzalo on 11/02/2015.
 */
public class Friend {

    public final static String FRIEND_NAME = "friend_name";
    public final static String FRIEND_MONEY = "friend_money";

    private String name;
    private int money;

    public Friend(String name, int money) {
        this.name = name;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
