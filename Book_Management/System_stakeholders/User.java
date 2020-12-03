package 系统相关者;

import java.io.Serializable;

public class User implements Serializable {
    private int ID;
    private char[] password;
    private int type;
    private char[] name;
    private char[] unit;
    private long telephone;
    private int can_borrow;

    public User(int ID, char[] password, int type, char[] name, char[] unit, long telephone, int can_borrow) {
        this.ID = ID;
        this.password = password;
        this.type = type;
        this.name = name;
        this.unit = unit;
        this.telephone = telephone;
        this.can_borrow = can_borrow;
    }

    public User() {
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public char[] getPassword() {
        return this.password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public char[] getName() {
        return this.name;
    }

    public void setName(char[] name) {
        this.name = name;
    }

    public char[] getUnit() {
        return this.unit;
    }

    public void setUnit(char[] unit) {
        this.unit = unit;
    }

    public long getTelephone() {
        return this.telephone;
    }

    public void setTelephone(long telephone) {
        this.telephone = telephone;
    }

    public int getCan_borrow() {
        return this.can_borrow;
    }

    public void setCan_borrow(int can_borrow) {
        this.can_borrow = can_borrow;
    }
}
