package com.young.instaapi.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class InstaShop {

    public int id;
    public String thumbnail;
    public String name;
    public String website;

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @SerializedName("insta_id")
    @Expose
    public String instaId;

    @SerializedName("age_type")
    @Expose
    public String ageType;

    public InstaShop(String thumbnail, String name, String ageType) {
        this.name = name;
        this.thumbnail = thumbnail;
        this.ageType = ageType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getInstaId() {
        return instaId;
    }

    public void setInstaId(String instaId) {
        this.instaId = instaId;
    }

    public String getAgeType() {
        return ageType;
    }

    public void setAgeType(String ageType) {
        this.ageType = ageType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSiteImge() {
        return thumbnail;
    }

    public void setSiteImge(String siteImge) {
        this.thumbnail = siteImge;
    }
}
