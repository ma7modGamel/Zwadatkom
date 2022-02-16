package com.safwa.zawadatkm_user.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;


public class ChatListModel implements Serializable {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("messages")
    @Expose
    private Messages messages;
    private final static long serialVersionUID = 7164416287134038789L;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Messages getMessages() {
        return messages;
    }

    public void setMessages(Messages messages) {
        this.messages = messages;
    }

    public class Address implements Serializable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("latitude")
        @Expose
        private String latitude;
        @SerializedName("longitude")
        @Expose
        private String longitude;
        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("moreAddress")
        @Expose
        private String moreAddress;
        @SerializedName("addressType")
        @Expose
        private String addressType;
        @SerializedName("user_id")
        @Expose
        private Integer userId;
        @SerializedName("deleted_at")
        @Expose
        private Object deletedAt;
        private final static long serialVersionUID = -2725809140741593826L;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getMoreAddress() {
            return moreAddress;
        }

        public void setMoreAddress(String moreAddress) {
            this.moreAddress = moreAddress;
        }

        public String getAddressType() {
            return addressType;
        }

        public void setAddressType(String addressType) {
            this.addressType = addressType;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public Object getDeletedAt() {
            return deletedAt;
        }

        public void setDeletedAt(Object deletedAt) {
            this.deletedAt = deletedAt;
        }

    }

    public class Address__1 implements Serializable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("latitude")
        @Expose
        private String latitude;
        @SerializedName("longitude")
        @Expose
        private String longitude;
        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("moreAddress")
        @Expose
        private String moreAddress;
        @SerializedName("addressType")
        @Expose
        private String addressType;
        @SerializedName("user_id")
        @Expose
        private Integer userId;
        @SerializedName("deleted_at")
        @Expose
        private Object deletedAt;
        private final static long serialVersionUID = -3997239728078078590L;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getMoreAddress() {
            return moreAddress;
        }

        public void setMoreAddress(String moreAddress) {
            this.moreAddress = moreAddress;
        }

        public String getAddressType() {
            return addressType;
        }

        public void setAddressType(String addressType) {
            this.addressType = addressType;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public Object getDeletedAt() {
            return deletedAt;
        }

        public void setDeletedAt(Object deletedAt) {
            this.deletedAt = deletedAt;
        }

    }


    public class Message implements Serializable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("chat_id")
        @Expose
        private Integer chatId;
        @SerializedName("agent_id")
        @Expose
        private Integer agentId;
        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        private final static long serialVersionUID = -6121122095478489053L;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getChatId() {
            return chatId;
        }

        public void setChatId(Integer chatId) {
            this.chatId = chatId;
        }

        public Integer getAgentId() {
            return agentId;
        }

        public void setAgentId(Integer agentId) {
            this.agentId = agentId;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

    }

    public class Messages implements Serializable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("user_id")
        @Expose
        private Integer userId;
        @SerializedName("created_at")
        @Expose
        private Object createdAt;
        @SerializedName("updated_at")
        @Expose
        private Object updatedAt;
        @SerializedName("messages")
        @Expose
        private List<Message> messages = null;
        @SerializedName("user")
        @Expose
        private User user;
        @SerializedName("newMessage")
        @Expose
        private Boolean newMessage;
        private final static long serialVersionUID = -5704226092838637404L;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public Object getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(Object createdAt) {
            this.createdAt = createdAt;
        }

        public Object getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(Object updatedAt) {
            this.updatedAt = updatedAt;
        }

        public List<Message> getMessages() {
            return messages;
        }

        public void setMessages(List<Message> messages) {
            this.messages = messages;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public Boolean getNewMessage() {
            return newMessage;
        }

        public void setNewMessage(Boolean newMessage) {
            this.newMessage = newMessage;
        }

    }

    public class Name implements Serializable {

        @SerializedName("en")
        @Expose
        private String en;
        @SerializedName("ar")
        @Expose
        private String ar;
        private final static long serialVersionUID = -4288105356945635885L;

        public String getEn() {
            return en;
        }

        public void setEn(String en) {
            this.en = en;
        }

        public String getAr() {
            return ar;
        }

        public void setAr(String ar) {
            this.ar = ar;
        }

    }

    public class User implements Serializable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private Name name;
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
        private String latitude;
        @SerializedName("longitude")
        @Expose
        private String longitude;
        @SerializedName("deleted_at")
        @Expose
        private Object deletedAt;
        @SerializedName("verified")
        @Expose
        private Integer verified;
        @SerializedName("verification_token")
        @Expose
        private Object verificationToken;
        @SerializedName("device_key")
        @Expose
        private Object deviceKey;
        @SerializedName("wallet")
        @Expose
        private Integer wallet;
        @SerializedName("google_id")
        @Expose
        private Object googleId;
        @SerializedName("apple_id")
        @Expose
        private Object appleId;
        @SerializedName("facebook_id")
        @Expose
        private Object facebookId;
        @SerializedName("userRole")
        @Expose
        private String userRole;
        @SerializedName("address")
        @Expose
        private List<Address> address = null;
        @SerializedName("orderCount")
        @Expose
        private Integer orderCount;
        @SerializedName("shop")
        @Expose
        private Object shop;
        @SerializedName("addresses")
        @Expose
        private List<Address__1> addresses = null;
        private final static long serialVersionUID = -1790161480423641938L;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Name getName() {
            return name;
        }

        public void setName(Name name) {
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

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public Object getDeletedAt() {
            return deletedAt;
        }

        public void setDeletedAt(Object deletedAt) {
            this.deletedAt = deletedAt;
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

        public Object getDeviceKey() {
            return deviceKey;
        }

        public void setDeviceKey(Object deviceKey) {
            this.deviceKey = deviceKey;
        }

        public Integer getWallet() {
            return wallet;
        }

        public void setWallet(Integer wallet) {
            this.wallet = wallet;
        }

        public Object getGoogleId() {
            return googleId;
        }

        public void setGoogleId(Object googleId) {
            this.googleId = googleId;
        }

        public Object getAppleId() {
            return appleId;
        }

        public void setAppleId(Object appleId) {
            this.appleId = appleId;
        }

        public Object getFacebookId() {
            return facebookId;
        }

        public void setFacebookId(Object facebookId) {
            this.facebookId = facebookId;
        }

        public String getUserRole() {
            return userRole;
        }

        public void setUserRole(String userRole) {
            this.userRole = userRole;
        }

        public List<Address> getAddress() {
            return address;
        }

        public void setAddress(List<Address> address) {
            this.address = address;
        }

        public Integer getOrderCount() {
            return orderCount;
        }

        public void setOrderCount(Integer orderCount) {
            this.orderCount = orderCount;
        }

        public Object getShop() {
            return shop;
        }

        public void setShop(Object shop) {
            this.shop = shop;
        }

        public List<Address__1> getAddresses() {
            return addresses;
        }

        public void setAddresses(List<Address__1> addresses) {
            this.addresses = addresses;
        }
    }
}