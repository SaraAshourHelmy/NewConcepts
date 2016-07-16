package com.sara.Gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sara on 7/16/2016.
 */
public class CategoryGson {

    @Expose
    @SerializedName("id")
    int cat_id;

    @Expose
    String cat_name_en;

    @Expose
    String cat_name_ar;

    @SerializedName("section_type")
    @Expose
    int section_id;

    @SerializedName("image")
    @Expose
    String img;

    public CategoryGson(int cat_id, String cat_name) {

        this.cat_id = cat_id;
        this.cat_name_en = cat_name;
    }

}
