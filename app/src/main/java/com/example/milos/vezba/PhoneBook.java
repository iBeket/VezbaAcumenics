package com.example.milos.vezba;

/**
 * Created by Milos on 25-Jul-17.
 */

public class PhoneBook {

    private String name_;
    private String phoneNumber_;

    public PhoneBook() {

    }

    public PhoneBook(String name, String phoneNubmer) {
        this.name_ = name;
        this.phoneNumber_ = phoneNubmer;
    }

    public String getName() {
        return name_;
    }

    public void setName(String name) {
        this.name_ = name;
    }


    public String getPhoneNumber() {
        return phoneNumber_;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber_ = phoneNumber;
    }
}
