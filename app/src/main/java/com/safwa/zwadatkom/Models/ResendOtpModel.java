package com.safwa.zwadatkom.Models;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ResendOtpModel implements Serializable
{

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("message")
    @Expose
    private String message;
    private final static long serialVersionUID = 3190742526027756676L;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}