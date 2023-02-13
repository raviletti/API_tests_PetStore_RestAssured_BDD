package model;


import com.google.gson.annotations.SerializedName;

import java.util.Random;

public class Category{

    @SerializedName("name")
    private String categoryName;

    @SerializedName("id")
    private int categoryId;

    public void setCategoryName(String name){
        this.categoryName = name;
    }

    public String getCategoryName(){
        return categoryName;
    }

    public void setCategoryId(int id){
        this.categoryId = id;
    }

    public int getCategoryId(){
        return categoryId;
    }

    @Override
    public String toString(){
        return
                "Category{" +
                        "name = '" + categoryName + '\'' +
                        ",id = '" + categoryId + '\'' +
                        "}";
    }

    public Category(String name) {
        this.categoryName = name;
        this.categoryId = new Random().nextInt(1000);
    }

    public Category() {

    }
}
