package com.codabase.password.validator;

public class UserPassword {
    @PasswordConstraint
    private String password;

    public UserPassword(String password)
    {
        this.password=password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}