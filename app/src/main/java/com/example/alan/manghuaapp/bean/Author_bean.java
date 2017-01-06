package com.example.alan.manghuaapp.bean;

import java.util.List;

/**
 * Created by Alan on 2016/12/22.
 */

public class Author_bean {


    /**
     * code : 200
     * data : {"avatar_url":"http://f2.kkmh.com/image/150918/jdla02iby.jpg-w180","bind_phone":15857189014,"follower_cnt":225125,"following":false,"grade":1,"id":2967943,"intro":"有懒癌的漫画作者","nickname":"金丘","pub_feed":1,"reg_type":"author","reply_remind_flag":1,"topics":[{"comics_count":52,"cover_image_url":"http://f2.kkmh.com/image/160808/18g8lnopi.webp-w750","created_at":1442563580,"description":"快看总榜热度第一名！现象级超经典作品，在整容游戏App上美化自己的照片，现实中脸就会跟着变美！但每一次改变都要付出相应的代价，可能会伤害你的朋友、恋人、家人！这样的App你愿意使用吗？连载一年长期霸榜TOP3，亿万超火爆热度，新人必看！  \r\n【独家/第一季完结  责编：33 】","discover_image_url":null,"exclusive_flag":1,"id":544,"is_favourite":false,"label_id":17,"order":608,"title":"整容游戏","update_day":"","update_status":2,"updated_at":1442563580,"user_id":2967943,"vertical_image_url":"http://f2.kkmh.com/image/161214/kh3oxvdyb.webp-w320.w"}],"u_intro":"认证：快看漫画签约作者，代表作《整容游戏》","update_remind_flag":0,"weibo":"http://weibo.com/u/1959526475","weibo_name":"J-金丘","works":"《整容游戏》"}
     * message : OK
     */

    private int code;
    private DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * avatar_url : http://f2.kkmh.com/image/150918/jdla02iby.jpg-w180
         * bind_phone : 15857189014
         * follower_cnt : 225125
         * following : false
         * grade : 1
         * id : 2967943
         * intro : 有懒癌的漫画作者
         * nickname : 金丘
         * pub_feed : 1
         * reg_type : author
         * reply_remind_flag : 1
         * topics : [{"comics_count":52,"cover_image_url":"http://f2.kkmh.com/image/160808/18g8lnopi.webp-w750","created_at":1442563580,"description":"快看总榜热度第一名！现象级超经典作品，在整容游戏App上美化自己的照片，现实中脸就会跟着变美！但每一次改变都要付出相应的代价，可能会伤害你的朋友、恋人、家人！这样的App你愿意使用吗？连载一年长期霸榜TOP3，亿万超火爆热度，新人必看！  \r\n【独家/第一季完结  责编：33 】","discover_image_url":null,"exclusive_flag":1,"id":544,"is_favourite":false,"label_id":17,"order":608,"title":"整容游戏","update_day":"","update_status":2,"updated_at":1442563580,"user_id":2967943,"vertical_image_url":"http://f2.kkmh.com/image/161214/kh3oxvdyb.webp-w320.w"}]
         * u_intro : 认证：快看漫画签约作者，代表作《整容游戏》
         * update_remind_flag : 0
         * weibo : http://weibo.com/u/1959526475
         * weibo_name : J-金丘
         * works : 《整容游戏》
         */

        private String avatar_url;
        private long bind_phone;
        private int follower_cnt;
        private boolean following;
        private int grade;
        private int id;
        private String intro;
        private String nickname;
        private int pub_feed;
        private String reg_type;
        private int reply_remind_flag;
        private String u_intro;
        private int update_remind_flag;
        private String weibo;
        private String weibo_name;
        private String works;
        private List<TopicsBean> topics;

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public long getBind_phone() {
            return bind_phone;
        }

        public void setBind_phone(long bind_phone) {
            this.bind_phone = bind_phone;
        }

        public int getFollower_cnt() {
            return follower_cnt;
        }

        public void setFollower_cnt(int follower_cnt) {
            this.follower_cnt = follower_cnt;
        }

        public boolean isFollowing() {
            return following;
        }

        public void setFollowing(boolean following) {
            this.following = following;
        }

        public int getGrade() {
            return grade;
        }

        public void setGrade(int grade) {
            this.grade = grade;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getPub_feed() {
            return pub_feed;
        }

        public void setPub_feed(int pub_feed) {
            this.pub_feed = pub_feed;
        }

        public String getReg_type() {
            return reg_type;
        }

        public void setReg_type(String reg_type) {
            this.reg_type = reg_type;
        }

        public int getReply_remind_flag() {
            return reply_remind_flag;
        }

        public void setReply_remind_flag(int reply_remind_flag) {
            this.reply_remind_flag = reply_remind_flag;
        }

        public String getU_intro() {
            return u_intro;
        }

        public void setU_intro(String u_intro) {
            this.u_intro = u_intro;
        }

        public int getUpdate_remind_flag() {
            return update_remind_flag;
        }

        public void setUpdate_remind_flag(int update_remind_flag) {
            this.update_remind_flag = update_remind_flag;
        }

        public String getWeibo() {
            return weibo;
        }

        public void setWeibo(String weibo) {
            this.weibo = weibo;
        }

        public String getWeibo_name() {
            return weibo_name;
        }

        public void setWeibo_name(String weibo_name) {
            this.weibo_name = weibo_name;
        }

        public String getWorks() {
            return works;
        }

        public void setWorks(String works) {
            this.works = works;
        }

        public List<TopicsBean> getTopics() {
            return topics;
        }

        public void setTopics(List<TopicsBean> topics) {
            this.topics = topics;
        }

        public static class TopicsBean {
            /**
             * comics_count : 52
             * cover_image_url : http://f2.kkmh.com/image/160808/18g8lnopi.webp-w750
             * created_at : 1442563580
             * description : 快看总榜热度第一名！现象级超经典作品，在整容游戏App上美化自己的照片，现实中脸就会跟着变美！但每一次改变都要付出相应的代价，可能会伤害你的朋友、恋人、家人！这样的App你愿意使用吗？连载一年长期霸榜TOP3，亿万超火爆热度，新人必看！
             【独家/第一季完结  责编：33 】
             * discover_image_url : null
             * exclusive_flag : 1
             * id : 544
             * is_favourite : false
             * label_id : 17
             * order : 608
             * title : 整容游戏
             * update_day :
             * update_status : 2
             * updated_at : 1442563580
             * user_id : 2967943
             * vertical_image_url : http://f2.kkmh.com/image/161214/kh3oxvdyb.webp-w320.w
             */

            private int comics_count;
            private String cover_image_url;
            private int created_at;
            private String description;
            private Object discover_image_url;
            private int exclusive_flag;
            private int id;
            private boolean is_favourite;
            private int label_id;
            private int order;
            private String title;
            private String update_day;
            private int update_status;
            private int updated_at;
            private int user_id;
            private String vertical_image_url;

            public int getComics_count() {
                return comics_count;
            }

            public void setComics_count(int comics_count) {
                this.comics_count = comics_count;
            }

            public String getCover_image_url() {
                return cover_image_url;
            }

            public void setCover_image_url(String cover_image_url) {
                this.cover_image_url = cover_image_url;
            }

            public int getCreated_at() {
                return created_at;
            }

            public void setCreated_at(int created_at) {
                this.created_at = created_at;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public Object getDiscover_image_url() {
                return discover_image_url;
            }

            public void setDiscover_image_url(Object discover_image_url) {
                this.discover_image_url = discover_image_url;
            }

            public int getExclusive_flag() {
                return exclusive_flag;
            }

            public void setExclusive_flag(int exclusive_flag) {
                this.exclusive_flag = exclusive_flag;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public boolean isIs_favourite() {
                return is_favourite;
            }

            public void setIs_favourite(boolean is_favourite) {
                this.is_favourite = is_favourite;
            }

            public int getLabel_id() {
                return label_id;
            }

            public void setLabel_id(int label_id) {
                this.label_id = label_id;
            }

            public int getOrder() {
                return order;
            }

            public void setOrder(int order) {
                this.order = order;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUpdate_day() {
                return update_day;
            }

            public void setUpdate_day(String update_day) {
                this.update_day = update_day;
            }

            public int getUpdate_status() {
                return update_status;
            }

            public void setUpdate_status(int update_status) {
                this.update_status = update_status;
            }

            public int getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(int updated_at) {
                this.updated_at = updated_at;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getVertical_image_url() {
                return vertical_image_url;
            }

            public void setVertical_image_url(String vertical_image_url) {
                this.vertical_image_url = vertical_image_url;
            }
        }
    }
}
