package com.example.whatsappclone.model;

public class message_model {
    String uId,msg;
    Long time;

    public message_model(String uId, String msg, Long time) {
        this.uId = uId;
        this.msg = msg;
        this.time = time;
    }
    public message_model(String uId,String msg){
        this.uId=uId;
        this.msg=msg;
    }
    // this empty constructor will be used for fire base
    public message_model(){

    }



    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }




}
