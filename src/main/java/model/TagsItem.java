package model;

import com.google.gson.annotations.SerializedName;

import java.util.Random;

public class TagsItem{

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private int id;

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    @Override
    public String toString(){
        return
                "TagsItem{" +
                        "name = '" + name + '\'' +
                        ",id = '" + id + '\'' +
                        "}";
    }

    public TagsItem(String name) {
        this.name = name;
        this.id = new Random().nextInt(1000);
    }
}
