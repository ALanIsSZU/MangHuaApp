package com.example.alan.manghuaapp.bean;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/21 0021.
 *
 */
public class GCBean {

    public String likes_count;
    public long updated_at;
    public String comments_count;
    public int feed_type;
    public String avatar_url;
    public String nickname;
    public String image_base;
    public List<String> images;
    public String text;
    public int author_id;

    public GCBean(JSONObject obj) throws JSONException {
        this.images = new ArrayList<>();
        this.likes_count = obj.optString("likes_count");
        this.updated_at = obj.optLong("updated_at");
        this.comments_count = obj.optString("comments_count");
        this.feed_type = obj.optInt("feed_type");
        this.avatar_url = obj.optJSONObject("user").optString("avatar_url");
        this.nickname = obj.optJSONObject("user").optString("nickname");
        this.image_base = obj.optJSONObject("content").optString("image_base");
        List<String> list = new ArrayList<>();
        JSONArray arr = obj.optJSONObject("content").optJSONArray("images");
        if (arr!=null){
            Log.d("ABCDE","images几张图："+arr.length());
            for (int i=0;i<arr.length();i++){
                String img = arr.optString(i);
                list.add(getImage_base()+img);
            }
            images.clear();
            images.addAll(list);
            Log.d("ABDCE","images="+images.toString());
        }

        this.text = obj.optJSONObject("content").getString("text");
        this.author_id = obj.optJSONObject("user").optInt("id");
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    @Override
    public String toString() {
        return "GCBean{" +
                "likes_count='" + likes_count + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", comments_count='" + comments_count + '\'' +
                ", feed_type=" + feed_type +
                ", avatar_url='" + avatar_url + '\'' +
                ", nickname='" + nickname + '\'' +
                ", image_base='" + image_base + '\'' +
                ", images=" + images +
                ", text='" + text + '\'' +
                '}';
    }

    public String getLikes_count() {
        return likes_count;
    }

    public void setLikes_count(String likes_count) {
        this.likes_count = likes_count;
    }

    public long getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(long updated_at) {
        this.updated_at = updated_at;
    }

    public String getComments_count() {
        return comments_count;
    }

    public void setComments_count(String comments_count) {
        this.comments_count = comments_count;
    }

    public int getFeed_type() {
        return feed_type;
    }

    public void setFeed_type(int feed_type) {
        this.feed_type = feed_type;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getImage_base() {
        return image_base;
    }

    public void setImage_base(String image_base) {
        this.image_base = image_base;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
