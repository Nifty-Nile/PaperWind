package Model;

public class User {
    private int money;
    private int permission;


    public User() {
    }

    public User(int money,int permission) {
        this.money = money;
        this.permission=permission;

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


