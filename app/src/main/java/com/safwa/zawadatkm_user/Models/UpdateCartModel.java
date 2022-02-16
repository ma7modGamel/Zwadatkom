package com.safwa.zawadatkm_user.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class UpdateCartModel implements Serializable
{
public static class CartItem implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("qty")
    @Expose
    private Integer qty;
    private final static long serialVersionUID = -4083765847267714981L;

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

}



    @SerializedName("cartItems")
    @Expose
    private List<CartItem> cartItems = null;
    private final static long serialVersionUID = 9167310203968148163L;

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

}
