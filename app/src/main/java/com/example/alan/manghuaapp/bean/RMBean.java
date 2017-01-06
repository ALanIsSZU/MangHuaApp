package com.example.alan.manghuaapp.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Alan on 2016/12/20.
 */

public class RMBean {
    public int type;
    public String label_text;
    public String label_text_color;
    public String label_color;
    public String title;
    public String nickname;
    public String cover_image_url;
    public String content_title;
    public String likes_count;
    public String comments_count;
    public String detailUrl;
    public int id;
    public int author_id;

    public RMBean(JSONObject obj) throws JSONException {
        this.type = obj.optInt("info_type");
        this.label_text = obj.optString("label_text");
        this.label_text_color = obj.optString("label_text_color");
        this.label_color = obj.optString("label_color");
        this.title = obj.optJSONObject("topic").optString("title");
        this.nickname = obj.optJSONObject("topic").optJSONObject("user").optString("nickname");
        this.cover_image_url = obj.optString("cover_image_url");
        this.content_title = obj.optString("title");
        this.likes_count = obj.optString("likes_count");
        this.comments_count = obj.optString("comments_count");
        this.detailUrl = obj.optString("url");
        this.id = obj.optJSONObject("topic").optInt("id");
        this.author_id = obj.optJSONObject("topic").optJSONObject("user").optInt("id");
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "RMBean{" +
                "type=" + type +
                ", label_text='" + label_text + '\'' +
                ", label_text_color='" + label_text_color + '\'' +
                ", label_color='" + label_color + '\'' +
                ", title='" + title + '\'' +
                ", nickname='" + nickname + '\'' +
                ", cover_image_url='" + cover_image_url + '\'' +
                ", content_title='" + content_title + '\'' +
                ", likes_count='" + likes_count + '\'' +
                ", comments_count='" + comments_count + '\'' +
                '}';
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getLabel_text() {
        return label_text;
    }

    public void setLabel_text(String label_text) {
        this.label_text = label_text;
    }

    public String getLabel_color() {
        return label_color;
    }

    public void setLabel_color(String label_color) {
        this.label_color = label_color;
    }

    public String getLabel_text_color() {
        return label_text_color;
    }

    public void setLabel_text_color(String label_text_color) {
        this.label_text_color = label_text_color;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCover_image_url() {
        return cover_image_url;
    }

    public void setCover_image_url(String cover_image_url) {
        this.cover_image_url = cover_image_url;
    }

    public String getContent_title() {
        return content_title;
    }

    public void setContent_title(String content_title) {
        this.content_title = content_title;
    }

    public String getLikes_count() {
        return likes_count;
    }

    public void setLikes_count(String likes_count) {
        this.likes_count = likes_count;
    }

    public String getComments_count() {
        return comments_count;
    }

    public void setComments_count(String comments_count) {
        this.comments_count = comments_count;
    }
}
