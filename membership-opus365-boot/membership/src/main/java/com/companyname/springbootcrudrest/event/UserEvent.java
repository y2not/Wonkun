package com.companyname.springbootcrudrest.event;


public class UserEvent extends AbstractEvent {

    String name;
    String userId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
