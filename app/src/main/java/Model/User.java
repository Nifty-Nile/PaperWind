package Model;

public class User {
    private int money;
    private int permission;
    String propic;
    String sharecode;
    int firstrefcode;


    public User(String imageurl) {
    }

    public User(int money,int permission,String propic,String sharecode,int firstrefcode) {
        this.money = money;
        this.permission=permission;
        this.propic=propic;
        this.sharecode=sharecode;
        this.firstrefcode=firstrefcode;

    }

    public int getFirstrefcode() {
        return firstrefcode;
    }

    public void setFirstrefcode(int firstrefcode) {
        this.firstrefcode = firstrefcode;
    }

    public String getSharecode() {
        return sharecode;
    }

    public void setSharecode(String sharecode) {
        this.sharecode = sharecode;
    }

    public String getPropic() {
        return propic;
    }

    public void setPropic(String propic) {
        this.propic = propic;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }


}


