package com.safwa.zawadatkm_user.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CartListModel implements Serializable {

    @SerializedName("promotion")
    @Expose
    private Promotion promotion;
    @SerializedName("cart")
    @Expose
    private Cart cart;
    private final static long serialVersionUID = -1216827400509011756L;

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }


    public class Data implements Serializable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("promotionCode")
        @Expose
        private String promotionCode;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("startDate")
        @Expose
        private String startDate;
        @SerializedName("endDate")
        @Expose
        private String endDate;
        @SerializedName("promotionValue")
        @Expose
        private String promotionValue;
        private final static long serialVersionUID = 317352644309713743L;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getPromotionCode() {
            return promotionCode;
        }

        public void setPromotionCode(String promotionCode) {
            this.promotionCode = promotionCode;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getPromotionValue() {
            return promotionValue;
        }

        public void setPromotionValue(String promotionValue) {
            this.promotionValue = promotionValue;
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
        private Double total;
        @SerializedName("items")
        @Expose
        private List<Item> items = null;
        private final static long serialVersionUID = -1730729608181247986L;

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

        public Double getTotal() {
            return total;
        }

        public void setTotal(Double total) {
            this.total = total;
        }

        public List<Item> getItems() {
            return items;
        }

        public void setItems(List<Item> items) {
            this.items = items;
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
        @SerializedName("product_option")
        @Expose
        private ProductOption productOption;
        private final static long serialVersionUID = -679473705214076451L;

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
        @SerializedName("pivot")
        @Expose
        private Pivot pivot;
        private final static long serialVersionUID = -4932177318594841098L;

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

        public Pivot getPivot() {
            return pivot;
        }

        public void setPivot(Pivot pivot) {
            this.pivot = pivot;
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
        private final static long serialVersionUID = -2713050840613927517L;

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
        private final static long serialVersionUID = 2168695847893845135L;

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

    public class Pivot implements Serializable
    {

        @SerializedName("product_id")
        @Expose
        private Integer productId;
        @SerializedName("category_id")
        @Expose
        private Integer categoryId;
        private final static long serialVersionUID = -1465802797222299463L;

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
        @SerializedName("images")
        @Expose
        private List<Image> images = null;
        private final static long serialVersionUID = -1103738045660290768L;

        public List<Image> getImages() {
            return images;
        }

        public void setImages(List<Image> images) {
            this.images = images;
        }
        @SerializedName("lowestOption")
        @Expose
        private LowestOption lowestOption;
        @SerializedName("listCategory")
        @Expose
        private List<ListCategory> listCategory = null;

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
        private final static long serialVersionUID = -2744928707858916876L;

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

    public class Promotion implements Serializable
    {

        @SerializedName("success")
        @Expose
        private Boolean success;
        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("data")
        @Expose
        private Data data;
        private final static long serialVersionUID = -8149331890747116981L;

        public Boolean getSuccess() {
            return success;
        }

        public void setData(Data data) {
            this.data = data;
        }

        public Data getData() {
            return data;
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

    }
}