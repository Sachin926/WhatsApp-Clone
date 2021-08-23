package com.example.whatsapp.Models;

public class Users {
    String displayPic, userName, email, password, userID, lastMessage;
    //to show the last message received or sent as seen original whatsapp

    public Users(String displayPic, String userName, String email, String password, String userID, String lastMessage) {
        this.displayPic = displayPic;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.userID = userID;
        this.lastMessage = lastMessage;
    }

    public Users(){
        //Firebase needs an empty constructor
    }

    //signup constructor
    public Users(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public String getDisplayPic() {
        return displayPic;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUserID() {
        return userID;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setDisplayPic(String displayPic) {
        this.displayPic = displayPic;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }
}
