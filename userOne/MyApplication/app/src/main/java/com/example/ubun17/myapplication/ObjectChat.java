package com.example.ubun17.myapplication;

/**
 * Created by ubun17 on 8/23/16.
 */
public class ObjectChat {
    private String userName;
    private String chat;

    private ObjectChat() {}

    public ObjectChat(String userName, String chat) {
        this.userName = userName;
        this.chat = chat;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }
}
