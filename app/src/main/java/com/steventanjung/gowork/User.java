package com.steventanjung.gowork;

/**
 * Created by asus on 11/27/2017.
 */

public class User {
    String username, password;
    int pangkat;

    public User(String username, String password, int pangkat) {
        this.username = username;
        this.password = password;
        this.pangkat = pangkat;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPangkat() {
        return pangkat;
    }

    public void setPangkat(int pangkat) {
        this.pangkat = pangkat;
    }
}
