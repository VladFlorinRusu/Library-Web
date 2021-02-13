package com.library.vlad.Model;

public class CurrentUser {
    private static CurrentUser instance = null;
    private User user ;

    private CurrentUser() {
    user=new User();
    user=null;
    }

    public boolean isNull() {
        if(getInstance().user==null)
            return true;
        return false;
    }

    public static CurrentUser getInstance() {
        if (instance==null)
            instance=new CurrentUser();
        return instance;
    }

    public User getUser() {
        return user;
    }

    public static void setUser(User u) {
        CurrentUser.instance.user = u;
    }
}
