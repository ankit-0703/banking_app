package com.banking.entity;

import java.util.Objects;

public class User {
    private String username;
    private String password;
    private String contactNumber;
    private String role;
    private Double accountBalance;

    public User(String username, String contactNumber, String password, String role, Double accountBalance) {
        this.username = username;
        this.contactNumber = contactNumber;
        this.password = password;
        this.role = role;
        this.accountBalance = accountBalance;
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

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", role='" + role + '\'' +
                ", accountBalance=" + accountBalance +
                '}';
    }
    // thishelp to get the value instead of object references

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        return Objects.equals(password, user.password) && Objects.equals(contactNumber, user.contactNumber);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(password);
        result = 31 * result + Objects.hashCode(contactNumber);
        return result;
    }
}

