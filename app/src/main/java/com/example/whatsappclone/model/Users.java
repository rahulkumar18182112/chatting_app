package com.example.whatsappclone.model;

public class Users {

    public Users(String mail, String profile_pic, String userid, String lastmsg, String password, String username) {
        this.mail = mail;
        this.profile_pic = profile_pic;
        this.userid = userid;
        this.lastmsg = lastmsg;
        this.password = password;
        this.username = username;
    }

    public Users(){}

    //signUp constructor
    public Users(String mail, String password, String username) {
        this.mail = mail;
        this.password = password;
        this.username = username;
    }


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    String mail;
    String profile_pic;



    String userid;
    String lastmsg;
    String password;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }



    public String getLastmsg() {
        return lastmsg;
    }

    public void setLastmsg(String lastmsg) {
        this.lastmsg = lastmsg;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    String username;

}
