package com.safwa.zawadatkm_user.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BaseModel implements Serializable
{

    @SerializedName("success")
    @Expose
    private Boolean sucess;
    @SerializedName("message")
    @Expose
    private String message;
    private final static long serialVersionUID = -1409616286698926993L;

    public Boolean getSucess() {
        return sucess;
    }

    public void setSucess(Boolean sucess) {
        this.sucess = sucess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}