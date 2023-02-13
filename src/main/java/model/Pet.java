package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** Объект тикета (POJO) */

public class Pet extends Body {



    // todo: serialized поля, геттеры и сеттеры
    @SerializedName("id")
    private Integer id;

    @SerializedName("category")
    private Category category;

    @SerializedName("name")
    private String name;

    @SerializedName("photoUrls")
    private List<String> photoUrls;

    @SerializedName("tags")
    private List<TagsItem> tags;

    @SerializedName("status")
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public void addPhotoUrl(String url){
        if(this.photoUrls == null){
            this.photoUrls = new ArrayList<>();
            this.photoUrls.add(url);
        }
        else this.photoUrls.add(url);


    }

    public List<TagsItem> getTags() {
        return tags;
    }

    public void setTags(List<TagsItem> tags) {
        this.tags = tags;
    }

    public void addTagsItem(String tagsName){
        if(this.tags == null){
            this.tags = new ArrayList<>();

        }
         this.tags.add(new TagsItem(tagsName));


    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", category=" + category +
                ", name='" + name + '\'' +
                ", photoUrls=" + photoUrls +
                ", tags=" + tags +
                ", status='" + status + '\'' +
                '}';
    }

    public void setFields(String field, String value){
        switch (field){
            case "id" : setId(Integer.parseInt(value));
                 break;
            case "category" : setCategory(new Category(value));
                break;
            case "name" : setName(value);
                break;
            case "photoUrls" : addPhotoUrl(value);
                break;
            case "tags" : addTagsItem(value);
                break;
            case "status" : setStatus(value);
                break;
        }
    }
}

//id, title, status, priority, queue