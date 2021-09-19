package com.example.githouse.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {
    @SerializedName("login")
    @Expose
    private  String login;

    @SerializedName("avatar_url")
    @Expose
    private  String avatarUrl;

    @SerializedName("html_url")
    @Expose
    private String htmlUrl;

    public Item(String login, String avatarUrl,String htmlUrl){
        this.login = login;
        this.htmlUrl=htmlUrl;
        this.avatarUrl=avatarUrl;
    }

    public String getLogin(){
        return login;
    }
    public void setLogin(String Login){
        this.login= login;
    }
    public String getAvatarUrl(){
        return avatarUrl;
    }
    public String getHtmlUrl(){
        return htmlUrl;
    }
    public void setHtmlUrl(String htmlUrl){
        this.htmlUrl= htmlUrl;
    }

}
