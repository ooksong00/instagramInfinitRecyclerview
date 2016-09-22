package com.young.instaapi.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class MediaUser {

    @SerializedName("username")
    @Expose
    String userName;

    @SerializedName("profile_picture")
    @Expose
    String profilePicture;

    @SerializedName("full_name")
    @Expose
    String fullName;

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfile_picture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
