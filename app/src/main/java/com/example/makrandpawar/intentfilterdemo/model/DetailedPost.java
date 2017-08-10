package com.example.makrandpawar.intentfilterdemo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by MAKRAND PAWAR on 8/10/2017.
 */

public class DetailedPost {

        @SerializedName("ID")
        public String id;
        public String content;
        @SerializedName("featured_image")
        public String featuredImage;
        public String title;
        public Author author;

        public class Author {
            public String name;
        }
}
