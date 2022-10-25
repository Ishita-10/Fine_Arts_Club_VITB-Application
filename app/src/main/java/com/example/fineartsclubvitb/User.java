package com.example.fineartsclubvitb;
public class User {
    private String name;
    private String regitration_no;
    private String email;
    public User(){
        //constructor
    }
    // created getter and setter methods
    // for all our variables.
    public String getUserName() {
        return name;
    }

    public void setUserName(String name) {
        this.name = name;
    }

    public String getUserRegistrationNumber() {
        return regitration_no;
    }

    public void setUserRegitrationNumber(String regitration_no) {
        this.regitration_no = regitration_no;
    }

    public String getemailAddress() {
        return email;
    }

    public void setemailAddress(String email) {
        this.email = email;
    }
}

