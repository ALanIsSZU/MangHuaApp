package com.example.alan.manghuaapp.bean;

import java.util.List;

/**
 * Created by ly on 2016/12/21.
 *
 * 推荐 Bean
 */

public class DiscoverBean {

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
        private List<InfosBean> infos;

        public List<InfosBean> getInfos() {
            return infos;
        }

        public void setInfos(List<InfosBean> infos) {
            this.infos = infos;
        }

        public static class InfosBean {

            private int action_type;
            private int item_type;
            private String action;
            private String title;
            private boolean more_flag;
            private String guide_text;
            private int style;
            private List<BannersBean> banners;
            private List<TopicsBean> topics;

            public int getAction_type() {
                return action_type;
            }

            public void setAction_type(int action_type) {
                this.action_type = action_type;
            }

            public int getItem_type() {
                return item_type;
            }

            public void setItem_type(int item_type) {
                this.item_type = item_type;
            }

            public String getAction() {
                return action;
            }

            public void setAction(String action) {
                this.action = action;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public boolean isMore_flag() {
                return more_flag;
            }

            public void setMore_flag(boolean more_flag) {
                this.more_flag = more_flag;
            }

            public String getGuide_text() {
                return guide_text;
            }

            public void setGuide_text(String guide_text) {
                this.guide_text = guide_text;
            }

            public int getStyle() {
                return style;
            }

            public void setStyle(int style) {
                this.style = style;
            }

            public List<BannersBean> getBanners() {
                return banners;
            }

            public void setBanners(List<BannersBean> banners) {
                this.banners = banners;
            }

            public List<TopicsBean> getTopics() {
                return topics;
            }

            public void setTopics(List<TopicsBean> topics) {
                this.topics = topics;
            }

            public static class BannersBean {
                /**
                 * target_app_url :
                 * good_price :
                 * sub_title :
                 * special_list_url :
                 * target_id : 693
                 * pic : http://f2.kkmh.com/image/161121/1sn7zvkda.webp
                 * type : 2
                 * target_package_name :
                 * hybrid_url :
                 * target_web_url :
                 * target_title : 头牌名媛
                 * id : 669
                 * request_id : -1
                 * good_alias :
                 * chapter_count : 6
                 */

                private String target_app_url;
                private String good_price;
                private String sub_title;
                private String special_list_url;
                private int target_id;
                private String pic;
                private int type;
                private String target_package_name;
                private String hybrid_url;
                private String target_web_url;
                private String target_title;
                private int id;
                private String request_id;
                private String good_alias;
                private int chapter_count;

                public String getTarget_app_url() {
                    return target_app_url;
                }

                public void setTarget_app_url(String target_app_url) {
                    this.target_app_url = target_app_url;
                }

                public String getGood_price() {
                    return good_price;
                }

                public void setGood_price(String good_price) {
                    this.good_price = good_price;
                }

                public String getSub_title() {
                    return sub_title;
                }

                public void setSub_title(String sub_title) {
                    this.sub_title = sub_title;
                }

                public String getSpecial_list_url() {
                    return special_list_url;
                }

                public void setSpecial_list_url(String special_list_url) {
                    this.special_list_url = special_list_url;
                }

                public int getTarget_id() {
                    return target_id;
                }

                public void setTarget_id(int target_id) {
                    this.target_id = target_id;
                }

                public String getPic() {
                    return pic;
                }

                public void setPic(String pic) {
                    this.pic = pic;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public String getTarget_package_name() {
                    return target_package_name;
                }

                public void setTarget_package_name(String target_package_name) {
                    this.target_package_name = target_package_name;
                }

                public String getHybrid_url() {
                    return hybrid_url;
                }

                public void setHybrid_url(String hybrid_url) {
                    this.hybrid_url = hybrid_url;
                }

                public String getTarget_web_url() {
                    return target_web_url;
                }

                public void setTarget_web_url(String target_web_url) {
                    this.target_web_url = target_web_url;
                }

                public String getTarget_title() {
                    return target_title;
                }

                public void setTarget_title(String target_title) {
                    this.target_title = target_title;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getRequest_id() {
                    return request_id;
                }

                public void setRequest_id(String request_id) {
                    this.request_id = request_id;
                }

                public String getGood_alias() {
                    return good_alias;
                }

                public void setGood_alias(String good_alias) {
                    this.good_alias = good_alias;
                }

                public int getChapter_count() {
                    return chapter_count;
                }

                public void setChapter_count(int chapter_count) {
                    this.chapter_count = chapter_count;
                }
            }

            public static class TopicsBean {
                /**
                 * label_color : #fa6499
                 * description : 上线第一话空降周榜TOP1，让你脸红心跳的少女漫！舞台剧中扮演王子的青梅竹马，竟当众触碰了我的唇，那可是我保留多年的初吻啊！可是这柔软而温暖的触感，以及快要无法呼吸的感觉，是怎么回事？男主超会撩妹！少女们快到碗里来！【独家/每周五更新，责编：哑铃lynn】
                 * target_id : 979
                 * pic : http://f2.kkmh.com/image/161214/rhwm57v5f.webp
                 * type : 2
                 * title : 还有一秒吻上你
                 * recommended_text :
                 * likes_count : 0
                 * label_text : 恋爱
                 * comments_count : 0
                 * label_text_color : #ffffff
                 * category : ["恋爱","校园"]
                 * user : {"pub_feed":0,"avatar_url":"http://f2.kkmh.com/image/161214/ld1a5vxlc.webp-w180.w","grade":1,"nickname":"Sui Souda","reg_type":"author","id":28374759}
                 * label_id : 15
                 */

                private String label_color;
                private String description;
                private int target_id;
                private String pic;
                private int type;
                private String title;
                private String recommended_text;
                private int likes_count;
                private String label_text;
                private int comments_count;
                private String label_text_color;
                private UserBean user;
                private int label_id;
                private List<String> category;

                public String getLabel_color() {
                    return label_color;
                }

                public void setLabel_color(String label_color) {
                    this.label_color = label_color;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public int getTarget_id() {
                    return target_id;
                }

                public void setTarget_id(int target_id) {
                    this.target_id = target_id;
                }

                public String getPic() {
                    return pic;
                }

                public void setPic(String pic) {
                    this.pic = pic;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getRecommended_text() {
                    return recommended_text;
                }

                public void setRecommended_text(String recommended_text) {
                    this.recommended_text = recommended_text;
                }

                public int getLikes_count() {
                    return likes_count;
                }

                public void setLikes_count(int likes_count) {
                    this.likes_count = likes_count;
                }

                public String getLabel_text() {
                    return label_text;
                }

                public void setLabel_text(String label_text) {
                    this.label_text = label_text;
                }

                public int getComments_count() {
                    return comments_count;
                }

                public void setComments_count(int comments_count) {
                    this.comments_count = comments_count;
                }

                public String getLabel_text_color() {
                    return label_text_color;
                }

                public void setLabel_text_color(String label_text_color) {
                    this.label_text_color = label_text_color;
                }

                public UserBean getUser() {
                    return user;
                }

                public void setUser(UserBean user) {
                    this.user = user;
                }

                public int getLabel_id() {
                    return label_id;
                }

                public void setLabel_id(int label_id) {
                    this.label_id = label_id;
                }

                public List<String> getCategory() {
                    return category;
                }

                public void setCategory(List<String> category) {
                    this.category = category;
                }

                public static class UserBean {
                    /**
                     * pub_feed : 0
                     * avatar_url : http://f2.kkmh.com/image/161214/ld1a5vxlc.webp-w180.w
                     * grade : 1
                     * nickname : Sui Souda
                     * reg_type : author
                     * id : 28374759
                     */

                    private int pub_feed;
                    private String avatar_url;
                    private int grade;
                    private String nickname;
                    private String reg_type;
                    private int id;

                    public int getPub_feed() {
                        return pub_feed;
                    }

                    public void setPub_feed(int pub_feed) {
                        this.pub_feed = pub_feed;
                    }

                    public String getAvatar_url() {
                        return avatar_url;
                    }

                    public void setAvatar_url(String avatar_url) {
                        this.avatar_url = avatar_url;
                    }

                    public int getGrade() {
                        return grade;
                    }

                    public void setGrade(int grade) {
                        this.grade = grade;
                    }

                    public String getNickname() {
                        return nickname;
                    }

                    public void setNickname(String nickname) {
                        this.nickname = nickname;
                    }

                    public String getReg_type() {
                        return reg_type;
                    }

                    public void setReg_type(String reg_type) {
                        this.reg_type = reg_type;
                    }

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }
                }
            }
        }
    }
}
