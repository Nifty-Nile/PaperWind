package Model;

public class User {
    private int money;
    private int permission;
    String propic;


    public User(String imageurl) {
    }

    public User(int money,int permission,String propic) {
        this.money = money;
        this.permission=permission;
        this.propic=propic;

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


