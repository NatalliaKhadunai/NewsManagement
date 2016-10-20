package com.epam.newsmanagement.entity;

public class Account {
    private int id;
    private String username;
    private int password;

    public Account() {

    }

    public Account(String username, int password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return getUsername().equals(account.getUsername());

    }

    @Override
    public int hashCode() {
        int result = getUsername().hashCode();
        result = 31 * result + getPassword();
        return result;
    }

    @Override
    public String toString() {
        return "Account{" +
                "username='" + getUsername() + '\'' +
                ", password=" + password +
                '}';
    }
}
