package com.safwa.zwadatkom.Models;


import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserRegisterModel implements Serializable {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("otp")
    @Expose
    private Otp otp;
    private final static long serialVersionUID = 4518865801684168201L;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Otp getOtp() {
        return otp;
    }

    public void setOtp(Otp otp) {
        this.otp = otp;
    }

    public class Otp implements Serializable {

        @SerializedName("status")
        @Expose
        private Boolean status;
        @SerializedName("token")
        @Expose
        private String token;
        @SerializedName("message")
        @Expose
        private String message;
        private final static long serialVersionUID = -1109255987907132503L;

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
}
