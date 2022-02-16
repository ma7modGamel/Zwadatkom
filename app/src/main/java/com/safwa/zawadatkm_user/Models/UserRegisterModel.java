package com.safwa.zawadatkm_user.Models;


import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserRegisterModel implements Serializable
{
    @SerializedName("errors")
    @Expose
    private Errors errors;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("otp")
    @Expose
    private Otp otp;
    private final static long serialVersionUID = 2083450651212392340L;

    public Boolean getSuccess() {
        return success;
    }

    public void setErrors(Errors errors) {
        this.errors = errors;

    }

    public Errors getErrors() {
        return errors;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

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

public class Otp implements Serializable
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
    private final static long serialVersionUID = 1891241836114313733L;

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
    public class Errors implements Serializable
    {

        @SerializedName("name")
        @Expose
        private List<String> name = null;
        @SerializedName("email")
        @Expose
        private List<String> email = null;
        @SerializedName("password")
        @Expose
        private List<String> password = null;
        @SerializedName("mobile")
        @Expose
        private List<String> mobile = null;
        private final static long serialVersionUID = -3215190792328700042L;

        public List<String> getEmail() {
            return email;
        }

        public List<String> getName() {
            return name;
        }

        public void setName(List<String> name) {
            this.name = name;
        }


        public void setEmail(List<String> email) {
            this.email = email;
        }

        public List<String> getPassword() {
            return password;
        }

        public void setPassword(List<String> password) {
            this.password = password;
        }

        public List<String> getMobile() {
            return mobile;
        }

        public void setMobile(List<String> mobile) {
            this.mobile = mobile;
        }

    }
}