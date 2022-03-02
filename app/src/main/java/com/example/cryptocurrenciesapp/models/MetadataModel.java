package com.example.cryptocurrenciesapp.models;

import com.google.gson.annotations.SerializedName;

public class MetadataModel {
    @SerializedName("id")
    public String id;
    @SerializedName("name")
    public String name;
    @SerializedName("website_url")
    public String website_url;
    @SerializedName("blog_url")
    public String blog_url;
    @SerializedName("github_url")
    public String github_url;
    @SerializedName("twitter_url")
    public String twitter_url;
    @SerializedName("whitepaper_url")
    public String whitepaper_url;
}