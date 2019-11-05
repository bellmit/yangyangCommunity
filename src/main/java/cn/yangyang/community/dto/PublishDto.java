package cn.yangyang.community.dto;

import cn.yangyang.community.pojo.User;

public class PublishDto {
    private Integer publish_ID;
    private String publish_Title;
    private String publish_Desc;
    private String publish_Datetime;
    //以后有时间可以改为时间差
    private String datetime_difference;
    private Integer user_ID;
    //阅读数
    private Integer Read_Count;
    //评论数
    private Integer Comment_Count;
    //点赞数
    private Integer Like_Count;
    //标签
    private String Tag;
    private User user;

    public Integer getPublish_ID() {
        return publish_ID;
    }

    public void setPublish_ID(Integer publish_ID) {
        this.publish_ID = publish_ID;
    }

    public String getPublish_Title() {
        return publish_Title;
    }

    public void setPublish_Title(String publish_Title) {
        this.publish_Title = publish_Title;
    }

    public String getPublish_Desc() {
        return publish_Desc;
    }

    public void setPublish_Desc(String publish_Desc) {
        this.publish_Desc = publish_Desc;
    }

    public String getPublish_Datetime() {
        return publish_Datetime;
    }

    public void setPublish_Datetime(String publish_Datetime) {
        this.publish_Datetime = publish_Datetime;
    }

    public Integer getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(Integer user_ID) {
        this.user_ID = user_ID;
    }

    public Integer getRead_Count() {
        return Read_Count;
    }

    public void setRead_Count(Integer read_Count) {
        Read_Count = read_Count;
    }

    public Integer getComment_Count() {
        return Comment_Count;
    }

    public void setComment_Count(Integer comment_Count) {
        Comment_Count = comment_Count;
    }

    public Integer getLike_Count() {
        return Like_Count;
    }

    public void setLike_Count(Integer like_Count) {
        Like_Count = like_Count;
    }

    public String getTag() {
        return Tag;
    }

    public void setTag(String tag) {
        Tag = tag;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDatetime_difference() {
        return datetime_difference;
    }

    public void setDatetime_difference(String datetime_difference) {
        this.datetime_difference = datetime_difference;
    }
}
