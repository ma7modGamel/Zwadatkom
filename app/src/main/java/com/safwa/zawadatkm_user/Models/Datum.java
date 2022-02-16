package com.safwa.zawadatkm_user.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.safwa.zawadatkm_user.Models.products.ProductsModel;

import java.io.Serializable;
import java.util.List;

public class Datum implements Serializable
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
    private CartListModel.LowestOption lowestOption;
    @SerializedName("listCategory")
    @Expose
    private List<CartListModel.ListCategory> listCategory = null;
    @SerializedName("categories")
    @Expose
    private List<ProductsModel.Category> categories = null;
    @SerializedName("images")
    @Expose
    private List<Image> images = null;
    private final static long serialVersionUID = 1027639174141708077L;

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

    public CartListModel.LowestOption getLowestOption() {
        return lowestOption;
    }

    public void setLowestOption(CartListModel.LowestOption lowestOption) {
        this.lowestOption = lowestOption;
    }

    public List<CartListModel.ListCategory> getListCategory() {
        return listCategory;
    }

    public void setListCategory(List<CartListModel.ListCategory> listCategory) {
        this.listCategory = listCategory;
    }

    public List<ProductsModel.Category> getCategories() {
        return categories;
    }

    public void setCategories(List<ProductsModel.Category> categories) {
        this.categories = categories;
    }

    public List<Image> getImages() {
        return images;
    }



    public void setImages(List<Image> images) {
        this.images = images;
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

}