package com.safwa.zawadatkm_user.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class LoginModel implements Serializable
{


    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("mobile")
    @Expose
    private String mobile;

    @SerializedName("otp")
    @Expose
    private Otp otp;
    private final static long serialVersionUID = -6292237666827690257L;


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



    public Otp getOtp() {
        return otp;
    }

    public void setOtp(Otp otp) {
        this.otp = otp;
    }

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("user")
    @Expose
    private User user;


    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


public class User implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("avatar")
    @Expose
    private Object avatar;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("email_verified_at")
    @Expose
    private Object emailVerifiedAt;
    @SerializedName("mobile_verified_at")
    @Expose
    private String mobileVerifiedAt;
    @SerializedName("online")
    @Expose
    private Integer online;
    @SerializedName("latitude")
    @Expose
    private Object latitude;
    @SerializedName("longitude")
    @Expose
    private Object longitude;
    @SerializedName("verified")
    @Expose
    private Integer verified;
    @SerializedName("verification_token")
    @Expose
    private Object verificationToken;
    @SerializedName("walletBalance")
    @Expose
    private String walletBalance;
    @SerializedName("userRole")
    @Expose
    private String userRole;
    @SerializedName("address")
    @Expose
    private List<ProfileUserModel.Address> address = null;
    @SerializedName("shop")
    @Expose
    private Object shop;
    @SerializedName("addresses")
    @Expose
    private List<Object> addresses = null;
    @SerializedName("message")
    @Expose
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    private final static long serialVersionUID = 3531051549476360919L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getAvatar() {
        return avatar;
    }

    public void setAvatar(Object avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Object getEmailVerifiedAt() {
        return emailVerifiedAt;
    }

    public void setEmailVerifiedAt(Object emailVerifiedAt) {
        this.emailVerifiedAt = emailVerifiedAt;
    }

    public String getMobileVerifiedAt() {
        return mobileVerifiedAt;
    }

    public void setMobileVerifiedAt(String mobileVerifiedAt) {
        this.mobileVerifiedAt = mobileVerifiedAt;
    }

    public Integer getOnline() {
        return online;
    }

    public void setOnline(Integer online) {
        this.online = online;
    }

    public Object getLatitude() {
        return latitude;
    }

    public void setLatitude(Object latitude) {
        this.latitude = latitude;
    }

    public Object getLongitude() {
        return longitude;
    }

    public void setLongitude(Object longitude) {
        this.longitude = longitude;
    }

    public Integer getVerified() {
        return verified;
    }

    public void setVerified(Integer verified) {
        this.verified = verified;
    }

    public Object getVerificationToken() {
        return verificationToken;
    }

    public void setVerificationToken(Object verificationToken) {
        this.verificationToken = verificationToken;
    }

    public String getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(String walletBalance) {
        this.walletBalance = walletBalance;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public List<ProfileUserModel.Address> getAddress() {
        return address;
    }

    public void setAddress(List<ProfileUserModel.Address> address) {
        this.address = address;
    }

    public Object getShop() {
        return shop;
    }

    public void setShop(Object shop) {
        this.shop = shop;
    }

    public List<Object> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Object> addresses) {
        this.addresses = addresses;
    }

}
    public class Headers implements Serializable
    {

        private final static long serialVersionUID = -7888151590140137559L;

    }






    public class Original implements Serializable
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
        private final static long serialVersionUID = -6674505439720104853L;

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

    public class Otp implements Serializable
    {

        @SerializedName("headers")
        @Expose
        private Headers headers;
        @SerializedName("original")
        @Expose
        private Original original;
        @SerializedName("exception")
        @Expose
        private Object exception;
        private final static long serialVersionUID = -6846912248868061212L;

        public Headers getHeaders() {
            return headers;
        }

        public void setHeaders(Headers headers) {
            this.headers = headers;
        }

        public Original getOriginal() {
            return original;
        }

        public void setOriginal(Original original) {
            this.original = original;
        }

        public Object getException() {
            return exception;
        }

        public void setException(Object exception) {
            this.exception = exception;
        }

    }}