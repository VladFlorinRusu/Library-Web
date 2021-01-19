package com.library.vlad.Model;

public class CurrentUser {
    private static User instance = null;

    private CurrentUser() {
    }

    public static User getInstance() {
        return instance;
    }

    public static void setInstance(User instance) {
        CurrentUser.instance = instance;
    }
}
