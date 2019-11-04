package cn.yangyang.community.pojo;

public class User {
    private Integer user_ID;
    private String user_Tel;
    private String user_Email;
    private String user_Pwd;
    private String user_NickName;
    private Integer user_Age;
    private char user_Sex;
    private String user_Adress;
    private String user_Token;

    public User(){}
    public User(String user_Tel,String user_Pwd){
        this.user_Tel=user_Tel;
        this.user_Pwd=user_Pwd;
    }

    public Integer getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(Integer user_ID) {
        this.user_ID = user_ID;
    }

    public String getUser_Tel() {
        return user_Tel;
    }

    public void setUser_Tel(String user_Tel) {
        this.user_Tel = user_Tel;
    }

    public String getUser_Email() {
        return user_Email;
    }

    public void setUser_Email(String user_Email) {
        this.user_Email = user_Email;
    }

    public String getUser_Pwd() {
        return user_Pwd;
    }

    public void setUser_Pwd(String user_Pwd) {
        this.user_Pwd = user_Pwd;
    }

    public String getUser_NickName() {
        return user_NickName;
    }

    public void setUser_NickName(String user_NickName) {
        this.user_NickName = user_NickName;
    }

    public Integer getUser_Age() {
        return user_Age;
    }

    public void setUser_Age(Integer user_Age) {
        this.user_Age = user_Age;
    }

    public char getUser_Sex() {
        return user_Sex;
    }

    public void setUser_Sex(char user_Sex) {
        this.user_Sex = user_Sex;
    }

    public String getUser_Adress() {
        return user_Adress;
    }

    public void setUser_Adress(String user_Adress) {
        this.user_Adress = user_Adress;
    }

    public String getUser_Token() {
        return user_Token;
    }

    public void setUser_Token(String user_Token) {
        this.user_Token = user_Token;
    }
}
