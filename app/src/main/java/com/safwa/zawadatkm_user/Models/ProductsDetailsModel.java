package com.safwa.zawadatkm_user.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ProductsDetailsModel implements Serializable
{

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("product")
    @Expose
    private Product product;
    private final static long serialVersionUID = -3234087036001159980L;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    public static class Image implements Serializable
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

    public class Option implements Serializable
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
        private String discountType;
        @SerializedName("required")
        @Expose
        private Integer required;
        @SerializedName("product_id")
        @Expose
        private Integer productId;
        @SerializedName("deleted_at")
        @Expose
        private Object deletedAt;
        private final static long serialVersionUID = -6242377890676698951L;

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

        public String getDiscountType() {
            return discountType;
        }

        public void setDiscountType(String discountType) {
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

    public class Product implements Serializable
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
        @SerializedName("options")
        @Expose
        private List<Option> options = null;
        private final static long serialVersionUID = 5978140562162409794L;

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

        public List<Option> getOptions() {
            return options;
        }

        public void setOptions(List<Option> options) {
            this.options = options;
        }

    }




    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }


    @SerializedName("count")
    @Expose
    private Integer count;



}