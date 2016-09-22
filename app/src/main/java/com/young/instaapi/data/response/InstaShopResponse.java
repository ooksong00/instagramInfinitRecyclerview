package com.young.instaapi.data.response;


import com.young.instaapi.data.model.InstaShop;

import java.util.List;

public class InstaShopResponse {
    public Data data;

    public class Data {
      public List<InstaShop> shops;
    }
}
