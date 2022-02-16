package com.safwa.zawadatkm_user.Models.detailsorders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class OrderDetailsModel implements Serializable
{

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("order")
    @Expose
    private Order order;
    private final static long serialVersionUID = -5944831171658787814L;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
public class Address implements Serializable
{

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
    private final static long serialVersionUID = -9077443168691717386L;

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

public class Address__1 implements Serializable
{

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
    private final static long serialVersionUID = -3547129702422031302L;

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

public class Address__2 implements Serializable
{

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
    private final static long serialVersionUID = 6951075199997196642L;

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

public class Address__3 implements Serializable
{

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
    private final static long serialVersionUID = 5019862859865755007L;

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

public class Cart implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("items")
    @Expose
    private List<Item> items = null;
    private final static long serialVersionUID = 9183266115039380891L;

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

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

}

public class Client implements Serializable
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
    private List<Address__1> address = null;
    @SerializedName("orderCount")
    @Expose
    private Integer orderCount;
    @SerializedName("shop")
    @Expose
    private Shop shop;
    @SerializedName("addresses")
    @Expose
    private List<Address__2> addresses = null;
    private final static long serialVersionUID = -8327422482422404288L;

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

    public List<Address__1> getAddress() {
        return address;
    }

    public void setAddress(List<Address__1> address) {
        this.address = address;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public List<Address__2> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address__2> addresses) {
        this.addresses = addresses;
    }

}

public class Image implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("product_id")
    @Expose
    private Integer productId;
    private final static long serialVersionUID = -5627291731706097681L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

}

public class Item implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("qty")
    @Expose
    private Integer qty;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("product_option_id")
    @Expose
    private Integer productOptionId;
    @SerializedName("cart_id")
    @Expose
    private Integer cartId;
    @SerializedName("available")
    @Expose
    private Integer available;
    @SerializedName("product_option")
    @Expose
    private ProductOption productOption;
    private final static long serialVersionUID = 4939606010805551874L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getProductOptionId() {
        return productOptionId;
    }

    public void setProductOptionId(Integer productOptionId) {
        this.productOptionId = productOptionId;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public ProductOption getProductOption() {
        return productOption;
    }

    public void setProductOption(ProductOption productOption) {
        this.productOption = productOption;
    }

}

public class ListCategory implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private Object description;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("parent_id")
    @Expose
    private Object parentId;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;
    @SerializedName("pivot")
    @Expose
    private Pivot pivot;
    private final static long serialVersionUID = 1486049547045015306L;

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

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Object getParentId() {
        return parentId;
    }

    public void setParentId(Object parentId) {
        this.parentId = parentId;
    }

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Pivot getPivot() {
        return pivot;
    }

    public void setPivot(Pivot pivot) {
        this.pivot = pivot;
    }

}

public class LowestOption implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("stock")
    @Expose
    private String stock;
    @SerializedName("discount")
    @Expose
    private String discount;
    @SerializedName("discountType")
    @Expose
    private Object discountType;
    @SerializedName("required")
    @Expose
    private Integer required;
    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;
    private final static long serialVersionUID = 2586422031248409819L;

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public Object getDiscountType() {
        return discountType;
    }

    public void setDiscountType(Object discountType) {
        this.discountType = discountType;
    }

    public Integer getRequired() {
        return required;
    }

    public void setRequired(Integer required) {
        this.required = required;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }

}

public class Order implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("address_id")
    @Expose
    private Integer addressId;
    @SerializedName("cart_id")
    @Expose
    private Integer cartId;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("status_id")
    @Expose
    private Integer statusId;
    @SerializedName("delivery_id")
    @Expose
    private Object deliveryId;
    @SerializedName("shop_id")
    @Expose
    private Integer shopId;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("delivery")
    @Expose
    private Object delivery;
    @SerializedName("promotions")
    @Expose
    private List<Object> promotions = null;
    @SerializedName("address")
    @Expose
    private Address address;
    @SerializedName("cart")
    @Expose
    private Cart cart;
    @SerializedName("client")
    @Expose
    private Client client;
    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("shop")
    @Expose
    private Shop__1 shop;
    @SerializedName("payment")
    @Expose
    private Payment payment;
    private final static long serialVersionUID = -7876515834517800399L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Object getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Object deliveryId) {
        this.deliveryId = deliveryId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Object getDelivery() {
        return delivery;
    }

    public void setDelivery(Object delivery) {
        this.delivery = delivery;
    }

    public List<Object> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<Object> promotions) {
        this.promotions = promotions;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Shop__1 getShop() {
        return shop;
    }

    public void setShop(Shop__1 shop) {
        this.shop = shop;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

}




public class Payment implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("method")
    @Expose
    private String method;
    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("shippingFee")
    @Expose
    private String shippingFee;
    @SerializedName("order_id")
    @Expose
    private Integer orderId;
    @SerializedName("status_id")
    @Expose
    private Object statusId;
    @SerializedName("wallet")
    @Expose
    private Integer wallet;
    @SerializedName("wallet_discount")
    @Expose
    private String walletDiscount;
    @SerializedName("promotion")
    @Expose
    private Object promotion;
    private final static long serialVersionUID = -1201851182380325093L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(String shippingFee) {
        this.shippingFee = shippingFee;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Object getStatusId() {
        return statusId;
    }

    public void setStatusId(Object statusId) {
        this.statusId = statusId;
    }

    public Integer getWallet() {
        return wallet;
    }

    public void setWallet(Integer wallet) {
        this.wallet = wallet;
    }

    public String getWalletDiscount() {
        return walletDiscount;
    }

    public void setWalletDiscount(String walletDiscount) {
        this.walletDiscount = walletDiscount;
    }

    public Object getPromotion() {
        return promotion;
    }

    public void setPromotion(Object promotion) {
        this.promotion = promotion;
    }

}

public class Pivot implements Serializable
{

    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("category_id")
    @Expose
    private Integer categoryId;
    private final static long serialVersionUID = -2899246639272874431L;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

}

public class ProductDetails implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("lowestOption")
    @Expose
    private LowestOption lowestOption;
    @SerializedName("listCategory")
    @Expose
    private List<ListCategory> listCategory = null;
    @SerializedName("images")
    @Expose
    private List<Image> images = null;
    private final static long serialVersionUID = -3978221372756397021L;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public LowestOption getLowestOption() {
        return lowestOption;
    }

    public void setLowestOption(LowestOption lowestOption) {
        this.lowestOption = lowestOption;
    }

    public List<ListCategory> getListCategory() {
        return listCategory;
    }

    public void setListCategory(List<ListCategory> listCategory) {
        this.listCategory = listCategory;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

}

public class ProductOption implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("stock")
    @Expose
    private String stock;
    @SerializedName("discount")
    @Expose
    private String discount;
    @SerializedName("discountType")
    @Expose
    private Object discountType;
    @SerializedName("required")
    @Expose
    private Integer required;
    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;
    @SerializedName("product_details")
    @Expose
    private ProductDetails productDetails;
    private final static long serialVersionUID = 1231539938892925980L;

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public Object getDiscountType() {
        return discountType;
    }

    public void setDiscountType(Object discountType) {
        this.discountType = discountType;
    }

    public Integer getRequired() {
        return required;
    }

    public void setRequired(Integer required) {
        this.required = required;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }

    public ProductDetails getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(ProductDetails productDetails) {
        this.productDetails = productDetails;
    }

}

public class Shop implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("openAt")
    @Expose
    private Object openAt;
    @SerializedName("closeAt")
    @Expose
    private Object closeAt;
    @SerializedName("shopDescription")
    @Expose
    private Object shopDescription;
    @SerializedName("address_id")
    @Expose
    private Integer addressId;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;
    private final static long serialVersionUID = 1196658212791759105L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getOpenAt() {
        return openAt;
    }

    public void setOpenAt(Object openAt) {
        this.openAt = openAt;
    }

    public Object getCloseAt() {
        return closeAt;
    }

    public void setCloseAt(Object closeAt) {
        this.closeAt = closeAt;
    }

    public Object getShopDescription() {
        return shopDescription;
    }

    public void setShopDescription(Object shopDescription) {
        this.shopDescription = shopDescription;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
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

public class Shop__1 implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("openAt")
    @Expose
    private Object openAt;
    @SerializedName("closeAt")
    @Expose
    private Object closeAt;
    @SerializedName("shopDescription")
    @Expose
    private Object shopDescription;
    @SerializedName("address_id")
    @Expose
    private Integer addressId;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;
    @SerializedName("address")
    @Expose
    private Address__3 address;
    private final static long serialVersionUID = 5860198241120963807L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getOpenAt() {
        return openAt;
    }

    public void setOpenAt(Object openAt) {
        this.openAt = openAt;
    }

    public Object getCloseAt() {
        return closeAt;
    }

    public void setCloseAt(Object closeAt) {
        this.closeAt = closeAt;
    }

    public Object getShopDescription() {
        return shopDescription;
    }

    public void setShopDescription(Object shopDescription) {
        this.shopDescription = shopDescription;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
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

    public Address__3 getAddress() {
        return address;
    }

    public void setAddress(Address__3 address) {
        this.address = address;
    }

}


public class Status implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private String type;
    private final static long serialVersionUID = -7480407208584397966L;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
}