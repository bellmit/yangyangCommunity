package cn.yangyang.community.pojo;


public class LoginRecoed {
    private Integer Recoed_ID;
    private Integer user_ID;
    private String Recoed_Datetime;

    public Integer getRecoed_ID() {
        return Recoed_ID;
    }

    public void setRecoed_ID(Integer recoed_ID) {
        Recoed_ID = recoed_ID;
    }

    public Integer getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(Integer user_ID) {
        this.user_ID = user_ID;
    }

    public String getRecoed_Datetime() {
        return Recoed_Datetime;
    }

    public void setRecoed_Datetime(String recoed_Datetime) {
        Recoed_Datetime = recoed_Datetime;
    }
}
