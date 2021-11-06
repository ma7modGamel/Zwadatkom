package com.safwa.zwadatkom.Models;
import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class LoginModel implements Serializable
{

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("user")
    @Expose
    private User user;
    private final static long serialVersionUID = -5055456709409572803L;

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
    private List<Object> address = null;
    @SerializedName("shop")
    @Expose
    private Object shop;
    @SerializedName("addresses")
    @Expose
    private List<Object> addresses = null;
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

    public List<Object> getAddress() {
        return address;
    }

    public void setAddress(List<Object> address) {
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
}