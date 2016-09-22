package com.young.instaapi.data.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.young.instaapi.data.model.MediaItem;


import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class InstaMediaResponse {

    public List<MediaItem> items;

    @SerializedName("more_available")
    @Expose
    public boolean moreAvailable;
}
